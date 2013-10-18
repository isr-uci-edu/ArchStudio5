package org.archstudio.bna.logics.hints;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.archstudio.bna.BNAModelEvent;
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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Lists;

public class SynchronizeHintsLogic extends AbstractThingLogic implements IBNAModelListener,
		IHintRepositoryChangeListener {

	public boolean DEBUG = false;

	private static final IThingKey<Object> HINT_CONTEXT_KEY = ThingKey.create(SynchronizeHintsLogic.class);

	protected final ThingValueTrackingLogic valueLogic;
	protected final IHintRepository hintRepository;
	protected final static ExecutorService asyncExecutor = new ThreadPoolExecutor(1, 5, 5L, TimeUnit.SECONDS,
			new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
				@Override
				public Thread newThread(Runnable r) {
					Thread t = new Thread(r);
					t.setName(SynchronizeHintsLogic.class.getName());
					t.setDaemon(true);
					return t;
				}
			});

	public SynchronizeHintsLogic(IBNAWorld world, IHintRepository hintRepository) {
		super(world);
		this.valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
		this.hintRepository = hintRepository;

		addHintSynchronizer(new PropertyHintSynchronizer(world, "bounds", IHasBoundingBox.BOUNDING_BOX_KEY,
				IHasMutableBoundingBox.class, IRelativeMovable.USER_MAY_MOVE, IHasMutableSize.USER_MAY_RESIZE));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "location", IHasAnchorPoint.ANCHOR_POINT_KEY,
				IHasMutableAnchorPoint.class, IRelativeMovable.USER_MAY_MOVE));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "tagged", ShowHideTagsLogic.SHOW_TAG_KEY, IThing.class,
				ShowHideTagsLogic.USER_MAY_SHOW_HIDE_TAG));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "angle", IHasAngle.ANGLE_KEY, IHasMutableAngle.class,
				IHasMutableAngle.USER_MAY_CHANGE_ANGLE));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "endpoint1", IHasEndpoints.ENDPOINT_1_KEY,
				IHasMutableEndpoints.class, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT1));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "endpoint2", IHasEndpoints.ENDPOINT_2_KEY,
				IHasMutableEndpoints.class, IHasMutableEndpoints.USER_MAY_MOVE_ENDPOINT2));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "midpoints", IHasMidpoints.MIDPOINTS_KEY,
				IHasMutableMidpoints.class, IHasMutableMidpoints.USER_MAY_MOVE_MIDPOINTS));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "color", IHasColor.COLOR_KEY, IHasMutableColor.class,
				IHasMutableColor.USER_MAY_EDIT_COLOR));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "highlight", IHasHighlight.HIGHLIGHT_KEY, IThing.class,
				HighlightLogic.USER_MAY_HIGHLIGHT));
		addHintSynchronizer(new PropertyHintSynchronizer(world, "alpha", IHasAlpha.ALPHA_KEY, IThing.class,
				IHasMutableAlpha.USER_MAY_CHANGE_ALPHA));

		hintRepository.addHintRepositoryChangeListener(this);
		for (IThing thing : model.getAllThings()) {
			createHintContext(thing);
		}
	}

	final protected CopyOnWriteArrayList<IHintSynchronizer> hintSynchronizers = Lists.newCopyOnWriteArrayList();

	synchronized public void addHintSynchronizer(IHintSynchronizer hintSynchronizer) {
		hintSynchronizers.add(hintSynchronizer);
	}

	synchronized public void removeHintSynchronizer(IHintSynchronizer hintSynchronizer) {
		hintSynchronizers.remove(hintSynchronizer);
	}

	@Override
	synchronized public void dispose() {
		hintRepository.removeHintRepositoryChangeListener(this);
		asyncExecutor.shutdown();
		try {
			asyncExecutor.awaitTermination(30, TimeUnit.SECONDS);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
			MessageDialog.openError(Display.getDefault().getActiveShell(), "Error", "An exception ("
					+ e.getClass().getName() + ") has occurred. Please see the platform log file for details.");
		}
		super.dispose();
	}

	@Override
	synchronized public void bnaModelChanged(final BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED: {
			IThing thing = evt.getTargetThing();
			Object context = createHintContext(thing);
			if (context != null) {
				storeHints(thing, context, evt);
			}
		}
			break;
		case THING_CHANGED: {
			final IThing thing = evt.getTargetThing();
			final ThingEvent thingEvent = evt.getThingEvent();
			if (!HINT_CONTEXT_KEY.equals(thingEvent.getPropertyName())) {
				asyncExecutor.execute(new Runnable() {
					@Override
					public void run() {
						Object context = createHintContext(thing);
						if (context != null) {
							storeHints(thing, context, evt);
						}
					}
				});
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
				restoreHints(thing, context, null);
				storeHints(thing, context, null);
				thing.set(HINT_CONTEXT_KEY, context);
				for (IThing t : Assemblies.getParts(model, thing).values()) {
					if (t != null) {
						createHintContext(t);
					}
				}
			}
		}
		return context;
	}

	synchronized public void restoreHints(IThing thing) {
		createHintContext(thing);
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
	synchronized public void hintRepositoryChanged(final IHintRepository repository, final Object context,
			final @Nullable String name) {
		for (IThing t : valueLogic.getThings(HINT_CONTEXT_KEY, context)) {
			restoreHints(t, context, name);
		}
	}

}