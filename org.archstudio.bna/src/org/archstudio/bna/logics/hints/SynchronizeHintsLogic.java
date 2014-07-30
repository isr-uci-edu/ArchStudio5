package org.archstudio.bna.logics.hints;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasAngle;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasGlow;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableGlow;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.editing.ShowHideTagsLogic;
import org.archstudio.bna.logics.hints.IHintRepository.HintValue;
import org.archstudio.bna.logics.hints.synchronizers.PropertyHintSynchronizer;
import org.archstudio.bna.logics.information.HighlightLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.things.labels.AnchoredLabelThing;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.Finally;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public class SynchronizeHintsLogic extends AbstractThingLogic implements IBNAModelListener,
		IHintRepositoryChangeListener {

	public boolean DEBUG = false;

	private static final IThingKey<Object> HINT_CONTEXT_KEY = ThingKey.create(Lists.newArrayList("hints-context",
			SynchronizeHintsLogic.class));
	private static final IThingKey<Boolean> HINTS_RESTORED_KEY = ThingKey.create(Lists.newArrayList("hints-restored",
			SynchronizeHintsLogic.class));

	protected final ThingValueTrackingLogic valueLogic;
	protected final IHintRepository hintRepository;

	public SynchronizeHintsLogic(IBNAWorld world, IHintRepository hintRepository) {
		super(world);
		this.valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
		this.hintRepository = hintRepository;

		final Function<Object, Object> toPoint2D = new Function<Object, Object>() {
			@Override
			public Object apply(Object input) {
				if (input instanceof Point) {
					return BNAUtils.toPoint2D((Point) input);
				}
				return input;
			}
		};

		final Function<Object, Object> toPoint2Ds = new Function<Object, Object>() {
			@Override
			public Object apply(Object input) {
				@SuppressWarnings("unchecked")
				List<Object> list = (List<Object>) input;
				for (int i = 0; i < list.size(); i++) {
					list.set(i, toPoint2D.apply(list.get(i)));
				}
				return input;
			}
		};

		addHintSynchronizer(new PropertyHintSynchronizer(world, "local-scale",
				EnvironmentPropertiesThing.LOCAL_SCALE_KEY, null, EnvironmentPropertiesThing.class));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "local-origin",
				EnvironmentPropertiesThing.LOCAL_ORIGIN_KEY, null, EnvironmentPropertiesThing.class));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "bounds", IHasBoundingBox.BOUNDING_BOX_KEY, null,
				IHasMutableBoundingBox.class, IHasMutableReferencePoint.USER_MAY_MOVE));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "bounds", IHasBoundingBox.BOUNDING_BOX_KEY, null,
				IHasMutableBoundingBox.class, IHasMutableSize.USER_MAY_RESIZE));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "location", IHasAnchorPoint.ANCHOR_POINT_KEY,
				toPoint2D, IHasMutableAnchorPoint.class, new Predicate<IThing>() {
					@Override
					public boolean apply(IThing input) {
						return !(input instanceof AnchoredLabelThing);
					}
				}, IHasMutableReferencePoint.USER_MAY_MOVE));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "tag-location", IHasAnchorPoint.ANCHOR_POINT_KEY,
				toPoint2D, IHasMutableAnchorPoint.class, new Predicate<IThing>() {
					@Override
					public boolean apply(IThing input) {
						return input instanceof AnchoredLabelThing;
					}
				}, IHasMutableReferencePoint.USER_MAY_MOVE));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "tagged", ShowHideTagsLogic.SHOW_TAG_KEY, null,
				IThing.class, ShowHideTagsLogic.USER_MAY_SHOW_HIDE_TAG));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "angle", IHasAngle.ANGLE_KEY, null,
				IHasMutableAngle.class, IHasMutableAngle.USER_MAY_CHANGE_ANGLE));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "endpoint1", IHasEndpoints.ENDPOINT_1_KEY, toPoint2D,
				IHasMutableEndpoints.class, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_1));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "endpoint2", IHasEndpoints.ENDPOINT_2_KEY, toPoint2D,
				IHasMutableEndpoints.class, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT_2));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "midpoints", IHasMidpoints.MIDPOINTS_KEY, toPoint2Ds,
				IHasMutableMidpoints.class, IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "color", IHasColor.COLOR_KEY, null,
				IHasMutableColor.class, IHasMutableColor.USER_MAY_EDIT_COLOR));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "highlight", IHasGlow.GLOW_COLOR_KEY, null,
				IHasMutableGlow.class, HighlightLogic.USER_MAY_HIGHLIGHT));

		hintRepository.addHintRepositoryChangeListener(this);
		for (IThing thing : model.getAllThings()) {
			// createHintContext automatically restores and stores hints
			createHintContext(thing);
		}
	}

	final protected CopyOnWriteArrayList<IHintSynchronizer> hintSynchronizers = Lists.newCopyOnWriteArrayList();
	final protected Multimap<IThingKey<?>, IHintSynchronizer> hintSynchronizersByKey = ArrayListMultimap.create();
	final protected Multimap<String, IHintSynchronizer> hintSynchronizersByName = ArrayListMultimap.create();

	public void addHintSynchronizer(IHintSynchronizer hintSynchronizer) {
		hintSynchronizers.add(hintSynchronizer);
		for (IThingKey<?> key : hintSynchronizer.getThingPropertiesOfInterest()) {
			hintSynchronizersByKey.put(key, hintSynchronizer);
		}
		for (String name : hintSynchronizer.getRepositoryNamesOfInterest()) {
			hintSynchronizersByName.put(name, hintSynchronizer);
		}
	}

	@Override
	public void dispose() {
		BNAUtils.checkLock();

		hintRepository.removeHintRepositoryChangeListener(this);

		super.dispose();
	}

	@Override
	public void bnaModelChanged(final BNAModelEvent evt) {
		BNAUtils.checkLock();

		switch (evt.getEventType()) {
		case THING_ADDED: {
			IThing thing = evt.getTargetThing();
			// createHintContext automatically restores and stores hints
			createHintContext(thing);
		}
			break;
		case THING_CHANGED: {
			final IThing thing = evt.getTargetThing();
			final ThingEvent thingEvent = evt.getThingEvent();
			if (!HINT_CONTEXT_KEY.equals(thingEvent.getPropertyName())) {
				final Object context = createHintContext(thing);
				if (context != null) {
					storeHints(thing, context, thingEvent);
				}
			}
		}
			break;
		default:
			// do nothing
		}
	}

	protected @Nullable
	Object createHintContext(IThing thing) {
		checkNotNull(thing);

		Object context = thing.get(HINT_CONTEXT_KEY);
		if (context == null) {
			context = hintRepository.getContextForThing(world, thing);
			if (context != null) {
				thing.set(HINT_CONTEXT_KEY, context);
				if (DEBUG) {
					System.out.println("Restoring hints: " + context + " " + toString(thing));
				}
				restoreHints(thing, context, null);
				if (DEBUG) {
					System.out.println("Restored hints : " + context + " " + toString(thing));
				}
				thing.set(HINTS_RESTORED_KEY, true);
				if (DEBUG) {
					System.out.println("Storing hints  : " + context + " " + toString(thing));
				}
				storeHints(thing, context, null);
				for (IThing t : Assemblies.getParts(model, thing).values()) {
					if (t != null) {
						createHintContext(t);
					}
				}
			}
		}
		return context;
	}

	public void restoreHints(IThing thing) {
		BNAUtils.checkLock();

		createHintContext(thing);
	}

	protected void restoreHints(IThing thing, Object context, @Nullable String name) {
		BNAUtils.checkLock();

		try {
			if (name == null) {
				for (Entry<String, HintValue> hint : hintRepository.getHints(context).entrySet()) {
					_restoreHints(thing, context, hint.getKey(), hint.getValue());
				}
			}
			else {
				HintValue hintValue = hintRepository.getHint(context, name);
				_restoreHints(thing, context, name, hintValue);
			}
		}
		catch (PropertyDecodeException e) {
			e.printStackTrace();
		}
	}

	private void _restoreHints(IThing thing, Object context, String name, HintValue hintValue) {
		for (IHintSynchronizer hintSynchronizer : hintSynchronizersByName.get(name)) {
			if (DEBUG) {
				System.out.println("Restoring hint : " + context + " " + toString(thing) + " " + hintSynchronizer);
			}
			hintSynchronizer.restoreHints(hintRepository, context, thing, name, hintValue);
		}
	}

	protected void storeHints(IThing thing, Object context, @Nullable ThingEvent evt) {
		BNAUtils.checkLock();

		if (!thing.has(HINTS_RESTORED_KEY, true)) {
			return;
		}

		if (evt == null) {
			for (IHintSynchronizer hintSynchronizer : hintSynchronizers) {
				if (DEBUG) {
					System.out.println("Store hints    : " + context + " " + toString(thing) + " " + hintSynchronizer);
				}
				hintSynchronizer.storeHints(hintRepository, context, thing, null);
			}
		}
		else {
			for (IHintSynchronizer hintSynchronizer : hintSynchronizersByKey.get(evt.getPropertyName())) {
				if (DEBUG) {
					System.out.println("Store hints    : " + context + " " + toString(thing) + " " + hintSynchronizer
							+ " " + evt);
				}
				hintSynchronizer.storeHints(hintRepository, context, thing, evt.getPropertyName());
			}
		}
	}

	@Override
	public void hintRepositoryChanged(final IHintRepository repository, final Object context,
			final @Nullable String name) {
		try (Finally lock = BNAUtils.lock()) {
			for (IThing t : valueLogic.getThings(HINT_CONTEXT_KEY, context)) {
				restoreHints(t, context, name);
			}
		}
	}

	private String toString(IThing thing) {
		return SystemUtils.simpleName(thing.getClass()) + "." + thing.getID();
	}

}