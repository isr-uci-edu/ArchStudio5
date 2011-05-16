package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.archstudio.sysutils.SystemUtils.castOrNull;
import static org.archstudio.sysutils.SystemUtils.emptyIfNull;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.keys.IThingKeyKey;
import org.archstudio.bna.keys.ThingKeyKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Sets;

public abstract class AbstractPropagateValueLogic<F extends IThing, T extends IThing, D> extends AbstractThingLogic
		implements IBNASynchronousModelListener {

	private final class FromThingData {
		public boolean currentlyPropagating = false;
		public final Object source;
		public final Iterable<Object> toThingIDs;
		@Nullable
		public final IThingKey<?> toKey;
		@Nullable
		public final D toData;

		public FromThingData(Object source, Iterable<Object> toThingIDs, @Nullable IThingKey<?> toKey,
				@Nullable D toData) {
			this.source = checkNotNull(source);
			this.toThingIDs = checkNotNull(toThingIDs);
			this.toKey = toKey;
			this.toData = toData;
		}
	}

	private final class ToThingData {
		public boolean currentlyPropagating = false;
		public final Object source;
		public final Object fromThingID;
		public final IThingKey<?> fromThingKey;
		@Nullable
		public final D toData;

		private ToThingData(Object source, Object fromThingID, IThingKey<?> fromKey, @Nullable D toData) {
			this.source = checkNotNull(source);
			this.fromThingID = checkNotNull(fromThingID);
			this.fromThingKey = checkNotNull(fromKey);
			this.toData = toData;
		}
	}

	private final Map<IThingKey<?>, IThingKeyKey<?, ?, List<FromThingData>>> keyToFromThingDataKey = new MapMaker()
			.makeComputingMap(new Function<IThingKey<?>, IThingKeyKey<?, ?, List<FromThingData>>>() {
				@Override
				public IThingKeyKey<?, ?, List<FromThingData>> apply(IThingKey<?> input) {
					checkNotNull(input);
					return ThingKeyKey.create(FromThingData.class, input);
				}
			});

	private final Map<IThingKey<?>, IThingKeyKey<?, ?, ToThingData>> keyToToThingDataKey = new MapMaker()
			.makeComputingMap(new Function<IThingKey<?>, IThingKeyKey<?, ?, ToThingData>>() {
				@Override
				public IThingKeyKey<?, ?, ToThingData> apply(IThingKey<?> input) {
					checkNotNull(input);
					return ThingKeyKey.create(ToThingData.class, input);
				}
			});

	private final Class<F> fromThingClass;
	private final Class<T> toThingClass;

	protected AbstractPropagateValueLogic(Class<F> fromThingClass, Class<T> toThingClass) {
		this.fromThingClass = fromThingClass;
		this.toThingClass = toThingClass;
	}

	protected void setPropagate(final F fromThing, IThingKey<?> fromKey, @Nullable final IThingKey<?> toKey,
			final @Nullable D toData, final IThing... toThings) {
		checkNotNull(fromThing);
		checkNotNull(fromKey);

		final IThingKeyKey<?, ?, List<FromThingData>> fromDataKey = keyToFromThingDataKey.get(fromKey);
		fromThing.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				List<FromThingData> data = Lists.newArrayList(emptyIfNull(fromThing.get(fromDataKey)));
				data.add(new FromThingData(AbstractPropagateValueLogic.this, BNAUtils.getThingIDs(Arrays
						.asList(toThings)), toKey, toData));
				fromThing.set(fromDataKey, data);
			}
		});

		if (toKey != null) {
			final IThingKeyKey<?, ?, ToThingData> toDataKey = keyToToThingDataKey.get(toKey);
			ToThingData toThingData = new ToThingData(AbstractPropagateValueLogic.this, fromThing.getID(), fromKey,
					toData);
			for (IThing toThing : toThings) {
				toThing.set(toDataKey, toThingData);
			}
		}
	}

	@SuppressWarnings("unchecked")
	protected <ET extends IThing> void doPropagation(IBNAModel model, ET thing, IThingKey<?> key,
			@Nullable ThingEvent<ET, ?, ?> thingEvent) {
		checkNotNull(model);
		checkNotNull(thing);

		if (key instanceof IThingKeyKey) {
			IThingKeyKey<?, ?, ?> keyKey = (IThingKeyKey<?, ?, ?>) key;
			if (ToThingData.class.equals(keyKey.getName()) || FromThingData.class.equals(keyKey.getName())) {
				// we also want to handle when the (from/to)Key changes
				key = keyKey.getKey();
			}
		}

		if (keyToToThingDataKey.containsKey(key)) {
			T toThing = castOrNull(thing, toThingClass);
			if (toThing != null) {
				IThingKeyKey<?, ?, ToThingData> toDataKey = keyToToThingDataKey.get(key);
				ToThingData data = toThing.get(toDataKey);
				if (data != null) {
					if (data.source == this && !data.currentlyPropagating) {
						model.beginBulkChange();
						data.currentlyPropagating = true;
						try {
							F fromThing = castOrNull(model.getThing(data.fromThingID), fromThingClass);
							if (fromThing != null) {
								doPropagation(model, fromThing, data.fromThingKey, null, data.toData, toThing, key,
										(ThingEvent<T, ?, ?>) thingEvent);
							}
						}
						finally {
							data.currentlyPropagating = false;
							model.endBulkChange();
						}
					}
				}
			}
		}
		if (keyToFromThingDataKey.containsKey(key)) {
			F fromThing = castOrNull(thing, fromThingClass);
			if (fromThing != null) {
				for (FromThingData data : emptyIfNull(fromThing.get(keyToFromThingDataKey.get(key)))) {
					if (data.source == this && !data.currentlyPropagating) {
						model.beginBulkChange();
						data.currentlyPropagating = true;
						try {
							for (Object toThingID : data.toThingIDs) {
								T toThing = castOrNull(model.getThing(toThingID), toThingClass);
								if (toThing != null) {
									doPropagation(model, fromThing, key, (ThingEvent<F, ?, ?>) thingEvent, data.toData,
											toThing, data.toKey, null);
								}
							}
						}
						finally {
							data.currentlyPropagating = false;
							model.endBulkChange();
						}
					}
				}
			}
		}
	}

	protected abstract void doPropagation(IBNAModel model, F fromThing, IThingKey<?> fromKey,
			@Nullable ThingEvent<F, ?, ?> fromThingEvent, D data, T toThing, @Nullable IThingKey<?> toKey,
			@Nullable ThingEvent<T, ?, ?> toThingEvent);

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		if (evt.getEventType() == EventType.THING_ADDED) {
			ET t = evt.getTargetThing();
			for (IThingKey<?> key : Sets.intersection(t.keySet(), keyToFromThingDataKey.keySet())) {
				doPropagation(evt.getSource(), t, key, null);
			}
		}
		if (evt.getEventType() == EventType.THING_CHANGED) {
			ET t = evt.getTargetThing();
			IThingKey<?> key = evt.getThingEvent().getPropertyName();
			doPropagation(evt.getSource(), t, key, evt.getThingEvent());
		}
	}
}
