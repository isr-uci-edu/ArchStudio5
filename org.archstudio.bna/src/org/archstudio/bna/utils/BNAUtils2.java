package org.archstudio.bna.utils;

import java.awt.geom.Point2D;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasLife;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.archstudio.bna.logics.background.LifeSapperLogic;
import org.archstudio.bna.logics.background.RotatingOffsetLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.bna.logics.editing.SnapToGridLogic;
import org.archstudio.bna.things.borders.PulsingBorderThing;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.things.utility.WorldThingPeer;
import org.archstudio.sysutils.Finally;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Lists;

public class BNAUtils2 {
	/** The set of running flying threads. Synchronized with {@link BNAUtils#lock()}. */
	private static final Map<ICoordinateMapper, FlyingThread> flyingThreads = new WeakHashMap<>();

	/**
	 * A reference to a thing.
	 */
	public static final class ThingReference {
		/** The view containing the thing, or <code>null</code> if unavailable. */
		private final IBNAView view;
		/** The world containing the thing. */
		private final IBNAWorld world;
		/** The model containing the thing. */
		private final IBNAModel model;
		/** The thing. */
		private final IThing thing;

		/**
		 * Creates a new instance with a <code>null</code> view.
		 *
		 * @param world
		 *            The world containing the thing.
		 * @param thing
		 *            The thing.
		 */
		public ThingReference(IBNAWorld world, IThing thing) {
			this.view = null;
			this.world = Preconditions.checkNotNull(world);
			this.model = world.getBNAModel();
			this.thing = Preconditions.checkNotNull(thing);
		}

		/**
		 * Creates a new instance with a view, world, and thing.
		 *
		 * @param view
		 *            The view containing the thing.
		 * @param thing
		 *            The thing.
		 */
		public ThingReference(IBNAView view, IThing thing) {
			this.view = Preconditions.checkNotNull(view);
			this.world = view.getBNAWorld();
			this.model = world.getBNAModel();
			this.thing = Preconditions.checkNotNull(thing);
		}

		/**
		 * Returns the view containing the thing, if available, or <code>null</code> otherwise.
		 *
		 * @return the view containing the thing, if available, or <code>null</code> otherwise.
		 */
		public @Nullable IBNAView getView() {
			return view;
		}

		/**
		 * Returns the world containing the thing.
		 *
		 * @return the world containing the thing.
		 */
		public IBNAWorld getWorld() {
			return world;
		}

		/**
		 * Returns the model containing the thing.
		 *
		 * @return the model containing the thing.
		 */
		public IBNAModel getModel() {
			return model;
		}

		/**
		 * Returns the thing.
		 *
		 * @return the thing.
		 */
		public IThing getThing() {
			return thing;
		}
	}

	/**
	 * A thing or a view under a location, not both. See {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)}
	 * for a description of how these are determined.
	 */
	public static final class ThingsAtLocation {
		/** The original view searched. */
		private final IBNAView originalView;

		/** The original location searched. */
		private final ICoordinate originalLocation;

		/**
		 * The thing under the original location of the original view. See
		 * {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)} for a description of how this is calculated.
		 * (Mutable because {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)} will directly set this).
		 */
		private ThingReference thingAtLocation = null;

		/**
		 * The view under the original location of the original view. See
		 * {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)} for a description of how this is calculated.
		 * (Mutable because {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)} will directly set this).
		 */
		private IBNAView viewAtLocation = null;

		/**
		 * The thing under the original location, passing through empty space in worlds until a thing is hit, or
		 * <code>null</code> if nothing is under the location. See
		 * {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)} for a description of how this is calculated.
		 * (Mutable because {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)} will directly set this).
		 */
		private ThingReference backgroundThingAtLocation = null;

		/**
		 * Initializes this reference with the original view and location.
		 *
		 * @param originalView
		 *            The original view searched.
		 * @param originalLocation
		 *            The original location searched.
		 */
		public ThingsAtLocation(IBNAView originalView, ICoordinate originalLocation) {
			this.originalView = Preconditions.checkNotNull(originalView);
			this.originalLocation = Preconditions.checkNotNull(originalLocation);
		}

		/**
		 * Returns the original view searched.
		 *
		 * @return the original view searched.
		 */
		public IBNAView getOriginalView() {
			return originalView;
		}

		/**
		 * Returns the original location searched.
		 *
		 * @return the original location searched.
		 */
		public ICoordinate getOriginalLocation() {
			return originalLocation;
		}

		/**
		 * Returns the thing under the original location of the original view as calculated by
		 * {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)}.
		 *
		 * @return the thing under the original location of the original view as calculated by
		 *         {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)}.
		 */
		@Nullable
		public ThingReference getThingAtLocation() {
			return thingAtLocation;
		}

		/**
		 * Returns the view under the original location of the original view as calculated by
		 * {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)}.
		 *
		 * @return the view under the original location of the original view as calculated by
		 *         {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)}.
		 */
		@Nullable
		public IBNAView getViewAtLocation() {
			return viewAtLocation;
		}

		/**
		 * Returns the thing under the original location, passing through empty space in worlds until a thing is hit, or
		 * <code>null</code> if nothing is under the location.
		 *
		 * @returns the thing under the original location, passing through empty space in worlds until a thing is hit,
		 *          or <code>null</code> if nothing is under the location.
		 */
		public ThingReference getBackgroundThingAtLocation() {
			return backgroundThingAtLocation;
		}

		/**
		 * A convenience method that returns the thing under the location if {@link #getThingAtLocation()} returns a
		 * non-null value, or <code>null</code> otherwise.
		 *
		 * @return the thing under the location if {@link #getThingAtLocation()} returns a non-null value, or
		 *         <code>null</code> otherwise.
		 */
		public IThing getThing() {
			return thingAtLocation != null ? thingAtLocation.getThing() : null;
		}

		/**
		 * A convenience method that returns the thing's view under the location if {@link #getThingAtLocation()}
		 * returns a non-null value, or {@link #getViewAtLocation()} otherwise.
		 *
		 * @return the thing's view under the location if {@link #getThingAtLocation()} returns a non-null value, or
		 *         {@link #getViewAtLocation()} otherwise.
		 */
		public IBNAView getView() {
			return thingAtLocation != null ? thingAtLocation.getView() : viewAtLocation;
		}
	}

	/**
	 * A thread to "fly" to a {@link ICoordinateMapper} from its current location to a new location.
	 */
	private static final class FlyingThread extends Thread {
		/** The {@link ICoordinateMapper} used to fly. */
		private final IMutableCoordinateMapper cm;

		/** The destination world point. */
		private final Point toWorldPoint;

		/** The duration to take for the flight, in milliseconds. */
		private final int durationMillis;

		/** The central point of the IBNAView control. */
		private final Point controlCentralPoint;

		/** The starting world point. */
		private final Point fromWorldPoint;

		/** The starting scale. */
		private final double originalScale;

		/** The difference between the starting and destination world point. */
		private final Point worldPointDiff;

		/** The difference between the starting and destination world point. */
		private final double midpointScale;

		/** Whether the thread has finished. Used to stop the thread early if another flight takes place. */
		private boolean finished = false;

		/**
		 * Flies the coordinate mapper from its current location to a new location.
		 *
		 * @param view
		 *            The view that is being flown over.
		 * @param cm
		 *            The coordinate mapper used to fly.
		 * @param toWorldPoint
		 *            The destination world point.
		 * @param durationMillis
		 *            The duration to take for the flight, in milliseconds.
		 */
		public FlyingThread(IBNAView view, IMutableCoordinateMapper cm, Point toWorldPoint, int durationMillis) {
			BNAUtils.checkLock();
			Preconditions.checkArgument(durationMillis >= 0);
			this.cm = Preconditions.checkNotNull(cm);
			this.toWorldPoint = Preconditions.checkNotNull(toWorldPoint);
			this.durationMillis = durationMillis;
			// Determine the destination local point.
			Point controlSize = view.getBNAUI().getComposite().getSize();
			controlCentralPoint = new Point(controlSize.x / 2, controlSize.y / 2);
			fromWorldPoint = cm.localToWorld(controlCentralPoint);
			originalScale = cm.getLocalScale();
			worldPointDiff = new Point(toWorldPoint.x - fromWorldPoint.x, toWorldPoint.y - fromWorldPoint.y);
			// Determine the scale necessary at the midpoint to show both the start and end points in the view.
			double xScale = (double) controlSize.x / Math.abs(worldPointDiff.x);
			double yScale = (double) controlSize.y / Math.abs(worldPointDiff.y);
			midpointScale = Math.min(Math.min(xScale, yScale), cm.getLocalScale());
			// Destroy any previously running threads. Otherwise, they will interfere with each other.
			try (Finally lock = BNAUtils.lock()) {
				FlyingThread previousThread = flyingThreads.get(cm);
				if (previousThread != null) {
					previousThread.finish();
				}
			}
		}

		/** Performs the flight. */
		@Override
		public void run() {
			try {
				long startTime = System.currentTimeMillis();
				long endTime = startTime + durationMillis;
				double midpointScaleDiff = midpointScale - originalScale;
				long currentTimeMillis;
				while ((currentTimeMillis = System.currentTimeMillis()) < endTime) {
					// A value from 0 (origin) to 1 (destination).
					double distanceFactor = Math.sin(Math.PI / 2 * (currentTimeMillis - startTime) / durationMillis);
					// An intermediate point between fromWorldPoint and toWorldPoint, determined by distanceFactor.
					Point intermediateWorldPoint = new Point(//
							fromWorldPoint.x + SystemUtils.round(worldPointDiff.x * distanceFactor), //
							fromWorldPoint.y + SystemUtils.round(worldPointDiff.y * distanceFactor));
					// An intermediate scale from 0 (origin), 1 (midpoint), 0 (destination).
					double scaleFactor = Math.sin(Math.PI * (currentTimeMillis - startTime) / durationMillis);
					double intermediateScale = Math.max(0.0001, originalScale + midpointScaleDiff * scaleFactor);
					try (Finally lock = BNAUtils.lock()) {
						if (finished) {
							break;
						}
						cm.setLocalScaleAndAlign(intermediateScale, controlCentralPoint, intermediateWorldPoint);
					}
					try {
						// Sleep for the remaining milliseconds in this frame (60 fps).
						synchronized (this) {
							sleep(System.currentTimeMillis() - currentTimeMillis + 1000 / 60);
						}
					}
					catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			finally {
				finish();
			}
		}

		/**
		 * Marks the flight finished, moves to the destination, and removes the thread. May be called multiple times and
		 * before the flight finishes.
		 */
		public synchronized void finish() {
			try (Finally lock = BNAUtils.lock()) {
				if (!finished) {
					finished = true;
					cm.setLocalScaleAndAlign(originalScale, controlCentralPoint, toWorldPoint);
					flyingThreads.remove(cm);
				}
			}
		}
	}

	/**
	 * Sets the "new thing spot" that logics use as the location for newly created things.
	 *
	 * @param world
	 *            The world in which to set the new thing spot.
	 * @param worldX
	 *            The x location for the new spot.
	 * @param worldY
	 *            The y location for the new spot.
	 */
	public static final void setNewThingSpot(IBNAWorld world, int worldX, int worldY) {
		Preconditions.checkNotNull(world);
		BNAUtils.checkLock();
		EnvironmentPropertiesThing ept = EnvironmentPropertiesThing.createIn(world);
		if (world.getThingLogicManager().getThingLogic(SnapToGridLogic.class) != null) {
			int gridSpacing = GridUtils.getGridSpacing(world);
			worldX -= worldX % gridSpacing;
			worldY -= worldY % gridSpacing;
			ept.setNewThingSpot(new Point(worldX, worldY));
		}
		else {
			ept.setNewThingSpot(new Point(worldX, worldY));
		}
	}

	/**
	 * Gets the "new thing spot" that logics use as the location for newly created things.
	 *
	 * @param world
	 *            The world in which to get the new thing spot.
	 * @param increment
	 *            Whether to increment the "new thing spot" so that future calls will return a location to the right and
	 *            below that returned by this call.
	 */
	public static final Point getNewThingSpot(IBNAWorld world, boolean increment) {
		Preconditions.checkNotNull(world);
		BNAUtils.checkLock();
		EnvironmentPropertiesThing ept = EnvironmentPropertiesThing.createIn(world);
		Point newThingSpot = ept.getNewThingSpot();
		if (increment) {
			int gridSpacing = GridUtils.getGridSpacing(world);
			ept.setNewThingSpot(new Point(newThingSpot.x + gridSpacing, newThingSpot.y + gridSpacing));
		}
		return newThingSpot;
	}

	/**
	 * Performs a breadth first search of a view and all its descendants for things matching the predicate filter.
	 *
	 * @param view
	 *            The view to search.
	 * @param filter
	 *            The filter to select things.
	 * @return a list of things matching the filter.
	 */
	public static final List<ThingReference> findThings(IBNAView view, Predicate<IThing> filter) {
		List<ThingReference> references = Lists.newArrayList();
		Set<IBNAWorld> worldsSearched = new HashSet<>();
		Queue<IBNAView> viewsToSearch = new LinkedList<>();
		viewsToSearch.add(view);
		while (!viewsToSearch.isEmpty()) {
			IBNAView viewToSearch = viewsToSearch.remove();
			if (worldsSearched.add(viewToSearch.getBNAWorld())) {
				for (IThing thing : viewToSearch.getBNAWorld().getBNAModel().getAllThings()) {
					if (filter.apply(thing)) {
						references.add(new ThingReference(viewToSearch, thing));
					}
					if (thing instanceof IHasWorld) {
						IBNAWorld world = ((IHasWorld) thing).getWorld();
						if (world != null) {
							WorldThingPeer<?> peer = (WorldThingPeer<?>) viewToSearch.getThingPeer(thing);
							viewsToSearch.add(peer.getInnerView());
						}
					}
				}
			}
		}
		return references;
	}

	/**
	 * "Flies" a view to the specific location.
	 *
	 * @param view
	 *            The view with the world point to fly to.
	 * @param toWorldPoint
	 *            The world point in the view to move to the center of the view.
	 * @param duration
	 *            The number of milliseconds to remain in flight.
	 */
	public static final void flyViewTo(IBNAView view, Point toWorldPoint, int duration) {
		// If the given view is not the top-level view then translate the world coordinates into world coordinates
		// for the top view. Then change the view to be the top level view.
		if (view.getParentView() != null) {
			IBNAView topLevelView = view.getParentView();
			while (topLevelView.getParentView() != null) {
				topLevelView = topLevelView.getParentView();
			}
			Point2D localPoint = view.getCoordinateMapper().worldToLocal(BNAUtils.toPoint2D(toWorldPoint));
			toWorldPoint = BNAUtils.toPoint(topLevelView.getCoordinateMapper().localToWorld(localPoint));
			view = topLevelView;
		}
		final Control control = view.getBNAUI().getComposite();
		if (control == null) {
			return;
		}
		final IMutableCoordinateMapper cm =
				SystemUtils.castOrNull(view.getCoordinateMapper(), IMutableCoordinateMapper.class);
		if (cm == null) {
			return;
		}
		FlyingThread thread = new FlyingThread(view, cm, toWorldPoint, duration);
		thread.start();
	}

	/**
	 * Creates a pulsing, expanding marker around a thing. Used to highlight the position of the thing when searching
	 * for it.
	 *
	 * @param world
	 *            The world in which to create the pulse.
	 * @param thing
	 *            The thing around which to do the pulse notification, or <code>null</code>, in which case no pulse is
	 *            created.
	 * @param duration
	 *            The number of milliseconds to perform the pulse.
	 */
	public static final void pulseNotify(IBNAWorld world, @Nullable IThing thing, int duration) {
		Preconditions.checkNotNull(world);
		BNAUtils.checkLock();
		if (thing != null) {
			MirrorValueLogic mirrorLogic = world.getThingLogicManager().addThingLogic(MirrorValueLogic.class);
			world.getThingLogicManager().addThingLogic(RotatingOffsetLogic.class);
			world.getThingLogicManager().addThingLogic(LifeSapperLogic.class);
			PulsingBorderThing pulseThing = null;
			if (thing instanceof IHasBoundingBox) {
				pulseThing = world.getBNAModel().addThing(new PulsingBorderThing(null));
				mirrorLogic.mirrorValue(thing, IHasBoundingBox.BOUNDING_BOX_KEY, pulseThing);
			}
			if (pulseThing != null) {
				pulseThing.set(IHasLife.LIFE_KEY, duration);
			}
		}
	}

	/**
	 * Returns a reasonable point that represents a thing, or <code>null</code> if none can be determined.
	 *
	 * @param world
	 *            The world containing the thing.
	 * @param thing
	 *            The thing to find a central point for.
	 * @return A central point for the thing, or <code>null</code> if none can be found.
	 */
	public static final Point getRepresentativePoint(IBNAWorld world, IThing thing) {
		IThing rootThing = Assemblies.getRoot(world.getBNAModel(), thing);
		// Check for an anchor point.
		IHasAnchorPoint anchoredThing =
				Assemblies.getThingOfType(world.getBNAModel(), rootThing, IHasAnchorPoint.class);
		if (anchoredThing != null) {
			return BNAUtils.toPoint(anchoredThing.getAnchorPoint());
		}
		// Check for a bounding box.
		IHasBoundingBox boundedThing = Assemblies.getThingOfType(world.getBNAModel(), rootThing, IHasBoundingBox.class);
		if (boundedThing != null) {
			Rectangle r = boundedThing.getBoundingBox();
			return new Point(r.x + r.width / 2, r.y + r.height / 2);
		}
		return null;
	}

	/**
	 * Determining what is under a point is complicated by the presence of sub-worlds and, specifically, empty space in
	 * sub-worlds. For purposes of explaining the complication, assume there are three things, stacked in the following
	 * order (from back to front):
	 * <ol>
	 * <li>A brick thing representing the outer component containing the sub-structure.</li>
	 * <li>A mapping thing from an interface on the outer component to an interface in the sub-structure.</li>
	 * <li>The world thing containing the sub-structure.</li>
	 * </ol>
	 * <p/>
	 * In this context, consider the possible user actions and behaviors:
	 * <ul>
	 * <li>Left clicking on a component in the sub-structure selects that component.</li>
	 * <li>Right clicking on a component in the sub-structure displays the context menu for that component.</li>
	 * <li>Left clicking on the interface mapping selects that mapping.</li>
	 * <li>Right clicking on the interface mapping displays the context menu for that interface mapping.</li>
	 * <li>However, left clicking on an empty space in the sub-structure could mean two different things:
	 * <ul>
	 * <li>Select the outer component.</li>
	 * <li>Start a marquee selection box in the sub-structure.</li>
	 * </ul>
	 * <li>Right clicking on an empty space in the sub-structure can also mean two different things:
	 * <ul>
	 * <li>Display a context menu for the outer component.</li>
	 * <li>Display a context menu for the sub-structure.</li>
	 * </ul>
	 * </ul>
	 * We handle the ambiguities as follows:
	 * <ol>
	 * <li>If a thing is under a location (possibly passing through empty space in worlds), and it does <b>not</b> have
	 * a world in its assembly, we assume the user is interacting with that thing. In out example, if the user clicks on
	 * the mapping, they are interacting with the mapping.</li>
	 * <li>If a thing is under the location, and it <b>does</b> have a world in its assembly, and we have passed through
	 * empty space in a world, we assume the user is interacting with the world rather than the thing. In our example,
	 * if a user clicks on empty space in the sub-structure (even though there is the outer component behind it), they
	 * are interacting with the sub-structure.</li>
	 * <li>If a thing is under the location, and it <b>does</b> have a world in its assembly, and we have <b>not</b>
	 * passed through empty space in a world, we assume the user is interacting with the thing. In our example, if a
	 * user clicks on the outer component, outside the sub-structure, they are interacting with the outer component.
	 * </li>
	 * <li>If there is only empty space (possibly passing through empty space in worlds) under the location, we return
	 * the inner most world.</li>
	 * <li>If there is nothing under the location, we return the top-most world.</li>
	 * </ol>
	 * This doesn't work in all circumstances, but seems to work in most cases.
	 * <p/>
	 * Finally, the background thing is the thing that is under the location when simply passing through empty space of
	 * worlds. This is used, for example, when determining what tool tip should be displayed for the location of the
	 * pointer.
	 *
	 * @param view
	 *            The view containing the top world to search.
	 * @param location
	 *            The location to search.
	 * @return an instance of {@link ThingsAtLocation} with a non-null {@link ThingsAtLocation#getThingAtLocation()} if
	 *         a thing is under the location, otherwise a non-null {@link ThingsAtLocation#getViewAtLocation()} for the
	 *         view under the location.
	 */
	public static final ThingsAtLocation getThingsAtLocation(IBNAView view, ICoordinate location) {
		boolean passedThroughWorld = false;
		ThingsAtLocation thingsAtLocation = new ThingsAtLocation(view, location);
		thingsAtLocation.viewAtLocation = view;

		for (IThing thing : view.getThingsAt(location)) {
			if (thing instanceof IHasWorld) {
				// If a world, search within it.
				IThingPeer<?> worldPeer = view.getThingPeer(thing);
				if (worldPeer instanceof IHasInnerViewPeer) {
					IBNAView innerView = ((IHasInnerViewPeer<?>) worldPeer).getInnerView();
					if (innerView != null) {
						passedThroughWorld = true;
						ICoordinate innerLocation =
								DefaultCoordinate.forLocal(location.getLocalPoint(), innerView.getCoordinateMapper());
						ThingsAtLocation innerThingsAtLocation = getThingsAtLocation(innerView, innerLocation);
						if (innerThingsAtLocation.thingAtLocation != null) {
							return innerThingsAtLocation;
						}
						if (innerThingsAtLocation.viewAtLocation != null) {
							thingsAtLocation.viewAtLocation = innerThingsAtLocation.viewAtLocation;
						}
					}
				}
			}
			else {
				if (passedThroughWorld && Assemblies.getThingOfType(view.getBNAWorld().getBNAModel(), thing,
						IHasWorld.class) != null) {
					thingsAtLocation.backgroundThingAtLocation = new ThingReference(view, thing);
					return thingsAtLocation;
				}
				thingsAtLocation.viewAtLocation = null;
				thingsAtLocation.thingAtLocation = new ThingReference(view, thing);
				thingsAtLocation.backgroundThingAtLocation = thingsAtLocation.thingAtLocation;
				return thingsAtLocation;
			}
		}
		return thingsAtLocation;
	}
}
