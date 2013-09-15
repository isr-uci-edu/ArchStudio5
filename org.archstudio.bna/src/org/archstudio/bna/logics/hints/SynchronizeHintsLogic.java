package org.archstudio.bna.logics.hints;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAlpha;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasAngle;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasHighlight;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.facets.IHasMutableAlpha;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableMidpoints;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.editing.ShowHideTagsLogic;
import org.archstudio.bna.logics.hints.synchronizers.PropertyHintSynchronizer;
import org.archstudio.bna.logics.information.HighlightLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.swtutils.SWTWidgetUtils;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Lists;

public class SynchronizeHintsLogic extends AbstractThingLogic implements IBNAModelListener,
		IHintRepositoryChangeListener {

	protected static final boolean DEBUG = false;

	private static final IThingKey<Object> HINT_CONTEXT_KEY = ThingKey.create(SynchronizeHintsLogic.class);

	final protected IHintRepository hintRepository;

	protected ThingValueTrackingLogic tvtl = null;

	public SynchronizeHintsLogic(IHintRepository hintRepository) {
		this.hintRepository = hintRepository;
		addHintSynchronizer(new PropertyHintSynchronizer("bounds", IHasBoundingBox.BOUNDING_BOX_KEY,
				IHasMutableBoundingBox.class, IRelativeMovable.USER_MAY_MOVE, IHasMutableSize.USER_MAY_RESIZE));
		addHintSynchronizer(new PropertyHintSynchronizer("location", IHasAnchorPoint.ANCHOR_POINT_KEY,
				IHasMutableAnchorPoint.class, IRelativeMovable.USER_MAY_MOVE));
		addHintSynchronizer(new PropertyHintSynchronizer("tagged", ShowHideTagsLogic.SHOW_TAG_KEY, IThing.class,
				ShowHideTagsLogic.USER_MAY_SHOW_HIDE_TAG));
		addHintSynchronizer(new PropertyHintSynchronizer("angle", IHasAngle.ANGLE_KEY, IHasMutableAngle.class,
				IHasMutableAngle.USER_MAY_CHANGE_ANGLE));
		addHintSynchronizer(new PropertyHintSynchronizer("endpoint1", IHasEndpoints.ENDPOINT_1_KEY,
				IHasMutableEndpoints.class, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT1));
		addHintSynchronizer(new PropertyHintSynchronizer("endpoint2", IHasEndpoints.ENDPOINT_2_KEY,
				IHasMutableEndpoints.class, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT2));
		addHintSynchronizer(new PropertyHintSynchronizer("midpoints", IHasMidpoints.MIDPOINTS_KEY,
				IHasMutableMidpoints.class, IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS));
		addHintSynchronizer(new PropertyHintSynchronizer("color", IHasColor.COLOR_KEY, IHasMutableColor.class,
				IHasMutableColor.USER_MAY_EDIT_COLOR));
		addHintSynchronizer(new PropertyHintSynchronizer("highlight", IHasHighlight.HIGHLIGHT_KEY, IThing.class,
				HighlightLogic.USER_MAY_HIGHLIGHT));
		addHintSynchronizer(new PropertyHintSynchronizer("alpha", IHasAlpha.ALPHA_KEY, IThing.class,
				IHasMutableAlpha.USER_MAY_CHANGE_ALPHA));
	}

	final protected CopyOnWriteArrayList<IHintSynchronizer> hintSynchronizers = Lists.newCopyOnWriteArrayList();

	public void addHintSynchronizer(IHintSynchronizer hintSynchronizer) {
		checkState(getBNAWorld() == null, "Hint synchronizer updates must occur before logic initialization");

		hintSynchronizers.add(hintSynchronizer);
	}

	public void removeHintSynchronizer(IHintSynchronizer hintSynchronizer) {
		checkState(getBNAWorld() == null, "Hint synchronizer updates must occur before logic initialization");

		hintSynchronizers.remove(hintSynchronizer);
	}

	@Override
	public void init() {
		checkState(getBNAModel().getAllThings().isEmpty(), "SynchronizeHintsLogic must be added before things.");
		tvtl = addThingLogic(ThingValueTrackingLogic.class);

		super.init();
		for (IHintSynchronizer hintSynchronizer : hintSynchronizers) {
			hintSynchronizer.setBNAWorld(getBNAWorld());
		}
		hintRepository.addHintRepositoryChangeListener(this);
	}

	@Override
	public void destroy() {
		hintRepository.removeHintRepositoryChangeListener(this);
		for (IHintSynchronizer hintSynchronizer : hintSynchronizers) {
			hintSynchronizer.setBNAWorld(null);
		}
		super.destroy();
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED:
		case THING_CHANGED:
			IThing thing = evt.getTargetThing();
			ThingEvent te = evt.getThingEvent();
			if (thing != null && (te == null || !HINT_CONTEXT_KEY.equals(te.getPropertyName()))) {
				Object context = createHintContext(thing);
				if (context != null) {
					storeHints(thing, context, evt);
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
			IBNAWorld world = getBNAWorld();
			if (world != null) {
				context = hintRepository.getContextForThing(world, thing);
				if (context != null) {
					restoreHints(thing, context, null);
					storeHints(thing, context, null);
					thing.set(HINT_CONTEXT_KEY, context);
					for (IThing t : Assemblies.getParts(world.getBNAModel(), thing).values()) {
						if (t != null) {
							createHintContext(t);
						}
					}
				}
			}
		}
		return context;
	}

	protected void restoreHints(IThing thing, Object context, @Nullable String name) {
		if (DEBUG) {
			System.out.println("Restore hints: " + context + " " + thing.getID());
		}
		for (IHintSynchronizer hintSynchronizer : hintSynchronizers) {
			hintSynchronizer.restoreHints(hintRepository, context, thing, name);
		}
	}

	protected void storeHints(IThing thing, Object context, @Nullable BNAModelEvent evt) {
		if (DEBUG) {
			System.out.println("Store hints  : " + context + " " + thing.getID() + " " + evt);
		}
		for (IHintSynchronizer hintSynchronizer : hintSynchronizers) {
			hintSynchronizer.storeHints(hintRepository, context, thing, evt);
		}
	}

	@Override
	public void hintRepositoryChanged(final IHintRepository repository, final Object context,
			final @Nullable String name) {
		final IBNAModel model = getBNAModel();
		if (model != null) {
			SWTWidgetUtils.async(Display.getDefault(), new Runnable() {
				@Override
				public void run() {
					for (IThing t : model.getThingsByID(tvtl.getThingIDs(HINT_CONTEXT_KEY, context))) {
						if (t != null) {
							restoreHints(t, context, name);
						}
					}
				}
			});
		}
	}

}