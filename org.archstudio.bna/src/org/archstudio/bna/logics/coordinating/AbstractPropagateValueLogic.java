package org.archstudio.bna.logics.coordinating;

import static com.google.common.base.Preconditions.checkNotNull;
import static org.archstudio.sysutils.SystemUtils.castOrNull;
import static org.archstudio.sysutils.SystemUtils.emptyIfNull;

import java.util.Arrays;
import java.util.Collection;
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

	private static final class FromThingData {
		public volatile boolean currentlyPropagating = false;
		public final Object source;
		public final Object toThingID;
		@Nullable
		public final IThingKey<?> toThingKey;
		@Nullable
		public final Object toData;

		public FromThingData(Object source, Object toThingID, @Nullable IThingKey<?> toThingKey, @Nullable Object toData) {
			this.source = checkNotNull(source);
			this.toThingID = checkNotNull(toThingID);
			this.toThingKey = toThingKey;
			this.toData = toData;
		}

		@Override
		public String toString() {
			return "FromThingData [source=" + source.getClass() + ", toThingID=" + toThingID + ", toThingKey="
					+ toThingKey + ", toData=" + toData + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (source == null ? 0 : source.hashCode());
			result = prime * result + (toData == null ? 0 : toData.hashCode());
			result = prime * result + (toThingID == null ? 0 : toThingID.hashCode());
			result = prime * result + (toThingKey == null ? 0 : toThingKey.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			FromThingData other = (FromThingData) obj;
			if (source == null) {
				if (other.source != null) {
					return false;
				}
			}
			else if (!source.equals(other.source)) {
				return false;
			}
			if (toData == null) {
				if (other.toData != null) {
					return false;
				}
			}
			else if (!toData.equals(other.toData)) {
				return false;
			}
			if (toThingID == null) {
				if (other.toThingID != null) {
					return false;
				}
			}
			else if (!toThingID.equals(other.toThingID)) {
				return false;
			}
			if (toThingKey == null) {
				if (other.toThingKey != null) {
					return false;
				}
			}
			else if (!toThingKey.equals(other.toThingKey)) {
				return false;
			}
			return true;
		}
	}

	private static final class ToThingData {
		public volatile boolean currentlyPropagating = false;
		public final Object source;
		public final Object fromThingID;
		public final IThingKey<?> fromThingKey;
		@Nullable
		public final Object toData;

		private ToThingData(Object source, Object fromThingID, IThingKey<?> fromThingKey, @Nullable Object toData) {
			this.source = checkNotNull(source);
			this.fromThingID = checkNotNull(fromThingID);
			this.fromThingKey = checkNotNull(fromThingKey);
			this.toData = toData;
		}

		@Override
		public String toString() {
			return "ToThingData [fromThingID=" + fromThingID + ", fromThingKey=" + fromThingKey + ", toData=" + toData
					+ ", source=" + source.getClass() + "]";
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + (fromThingID == null ? 0 : fromThingID.hashCode());
			result = prime * result + (fromThingKey == null ? 0 : fromThingKey.hashCode());
			result = prime * result + (source == null ? 0 : source.hashCode());
			result = prime * result + (toData == null ? 0 : toData.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			if (getClass() != obj.getClass()) {
				return false;
			}
			ToThingData other = (ToThingData) obj;
			if (fromThingID == null) {
				if (other.fromThingID != null) {
					return false;
				}
			}
			else if (!fromThingID.equals(other.fromThingID)) {
				return false;
			}
			if (fromThingKey == null) {
				if (other.fromThingKey != null) {
					return false;
				}
			}
			else if (!fromThingKey.equals(other.fromThingKey)) {
				return false;
			}
			if (source == null) {
				if (other.source != null) {
					return false;
				}
			}
			else if (!source.equals(other.source)) {
				return false;
			}
			if (toData == null) {
				if (other.toData != null) {
					return false;
				}
			}
			else if (!toData.equals(other.toData)) {
				return false;
			}
			return true;
		}
	}

	private final Map<IThingKey<?>, IThingKeyKey<?, ?, Collection<FromThingData>>> keyToFromThingDataKey = new MapMaker()
			.makeComputingMap(new Function<IThingKey<?>, IThingKeyKey<?, ?, Collection<FromThingData>>>() {
				@Override
				public IThingKeyKey<?, ?, Collection<FromThingData>> apply(IThingKey<?> input) {
					return ThingKeyKey.create(FromThingData.class, input);
				}
			});

	private final Map<IThingKey<?>, IThingKeyKey<?, ?, Collection<ToThingData>>> keyToToThingDataKey = new MapMaker()
			.makeComputingMap(new Function<IThingKey<?>, IThingKeyKey<?, ?, Collection<ToThingData>>>() {
				@Override
				public IThingKeyKey<?, ?, Collection<ToThingData>> apply(IThingKey<?> input) {
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

	protected void setPropagate(final F fromThing, final IThingKey<?> fromKey, @Nullable final IThingKey<?> toKey,
			final @Nullable D toData, final T... toThings) {
		checkNotNull(fromThing);
		checkNotNull(fromKey);

		final IThingKeyKey<?, ?, Collection<FromThingData>> fromDataKey = keyToFromThingDataKey.get(fromKey);
		fromThing.synchronizedUpdate(new Runnable() {
			@Override
			public void run() {
				Collection<FromThingData> fromThingDatas = Sets.newHashSet(emptyIfNull(fromThing.get(fromDataKey)));
				for (Object toThingID : BNAUtils.getThingIDs(Arrays.asList(toThings))) {
					fromThingDatas.add(new FromThingData(AbstractPropagateValueLogic.this, toThingID, toKey, toData));
				}
				fromThing.set(fromDataKey, Lists.newArrayList(fromThingDatas));
			}
		});
		if (toKey != null) {
			checkPropagation(getBNAModel(), fromThing, toKey, null);
			setPropagateTrigger(fromThing, fromKey, toKey, toData, toThings);
		}

	}

	protected void setPropagateTrigger(final F fromThing, final IThingKey<?> fromKey, final IThingKey<?> toKey,
			final D toData, final T... toThings) {
		final IThingKeyKey<?, ?, Collection<ToThingData>> toDataKey = keyToToThingDataKey.get(toKey);
		final ToThingData toThingData = new ToThingData(AbstractPropagateValueLogic.this, fromThing.getID(), fromKey,
				toData);
		for (final IThing toThing : toThings) {
			toThing.synchronizedUpdate(new Runnable() {
				@Override
				public void run() {
					Collection<ToThingData> toThingDatas = Sets.newHashSet(emptyIfNull(toThing.get(toDataKey)));
					toThingDatas.add(toThingData);
					toThing.set(toDataKey, Lists.newArrayList(toThingDatas));
				}
			});
		}
	}

	@SuppressWarnings("unchecked")
	protected <ET extends IThing> void checkPropagation(IBNAModel model, ET thing, IThingKey<?> key,
			@Nullable ThingEvent<ET, ?, ?> thingEvent) {
		checkNotNull(model);
		checkNotNull(thing);
		checkNotNull(key);

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
				for (ToThingData toThingData : emptyIfNull(toThing.get(keyToToThingDataKey.get(key)))) {
					if (toThingData.source == this && !toThingData.currentlyPropagating) {
						model.beginBulkChange();
						toThingData.currentlyPropagating = true;
						try {
							F fromThing = castOrNull(model.getThing(toThingData.fromThingID), fromThingClass);
							if (fromThing != null) {
								doPropagation(model, fromThing, toThingData.fromThingKey, null, (D) toThingData.toData,
										toThing, key, (ThingEvent<T, ?, ?>) thingEvent);
							}
						}
						finally {
							toThingData.currentlyPropagating = false;
							model.endBulkChange();
						}
					}
				}
			}
		}
		if (keyToFromThingDataKey.containsKey(key)) {
			F fromThing = castOrNull(thing, fromThingClass);
			if (fromThing != null) {
				for (FromThingData fromThingData : emptyIfNull(fromThing.get(keyToFromThingDataKey.get(key)))) {
					if (fromThingData.source == this && !fromThingData.currentlyPropagating) {
						model.beginBulkChange();
						fromThingData.currentlyPropagating = true;
						try {
							T toThing = castOrNull(model.getThing(fromThingData.toThingID), toThingClass);
							if (toThing != null) {
								doPropagation(model, fromThing, key, (ThingEvent<F, ?, ?>) thingEvent,
										(D) fromThingData.toData, toThing, fromThingData.toThingKey, null);
							}
						}
						finally {
							fromThingData.currentlyPropagating = false;
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
			for (IThingKey<?> key : Lists.newArrayList(t.keySet())) {
				checkPropagation(evt.getSource(), t, key, null);
			}
		}
		if (evt.getEventType() == EventType.THING_CHANGED) {
			ET t = evt.getTargetThing();
			IThingKey<?> key = evt.getThingEvent().getPropertyName();
			checkPropagation(evt.getSource(), t, key, evt.getThingEvent());
		}
	}
}
