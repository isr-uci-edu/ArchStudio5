package org.archstudio.bna.ui.utils;

import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.constants.DNDData;
import org.archstudio.bna.constants.DNDType;
import org.archstudio.bna.constants.FocusType;
import org.archstudio.bna.constants.GestureType;
import org.archstudio.bna.constants.KeyType;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.facets.peers.IHasInnerViewPeer;
import org.archstudio.bna.logics.events.WorldThingExternalEventsLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;
import org.archstudio.bna.ui.IBNAAllEventsListener2;
import org.archstudio.bna.ui.IBNADragAndDropListener;
import org.archstudio.bna.ui.IBNAFocusListener;
import org.archstudio.bna.ui.IBNAKeyListener;
import org.archstudio.bna.ui.IBNAKeyListener2;
import org.archstudio.bna.ui.IBNAMagnifyGestureListener;
import org.archstudio.bna.ui.IBNAMenuListener;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.ui.IBNAMouseClickListener;
import org.archstudio.bna.ui.IBNAMouseClickListener2;
import org.archstudio.bna.ui.IBNAMouseListener;
import org.archstudio.bna.ui.IBNAMouseMoveListener;
import org.archstudio.bna.ui.IBNAMouseMoveListener2;
import org.archstudio.bna.ui.IBNAMouseTrackListener;
import org.archstudio.bna.ui.IBNAMouseWheelListener;
import org.archstudio.bna.ui.IBNAMouseWheelListener2;
import org.archstudio.bna.ui.IBNAPanGestureListener;
import org.archstudio.bna.ui.IBNARotateGestureListener;
import org.archstudio.bna.ui.IBNASwipeGestureListener;
import org.archstudio.bna.ui.IBNATrackGestureListener;
import org.archstudio.bna.ui.IBNAUI;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.archstudio.bna.utils.DefaultCoordinate;
import org.archstudio.sysutils.Finally;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.GestureEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MenuDetectEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IWorkbenchActionConstants;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@SuppressWarnings("deprecation")
public abstract class AbstractBNAUI implements IBNAUI {

	public static void addMenuSeparators(IMenuManager menuMgr) {
		menuMgr.add(new Separator(IBNAMenuListener2.NEW_ELEMENTS_GROUP));
		menuMgr.add(new Separator(IBNAMenuListener2.PRIMARY_PROPERTIES_GROUP));
		menuMgr.add(new Separator(IBNAMenuListener2.SECONDARY_PROPERTIES_GROUP));
		menuMgr.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		menuMgr.add(new Separator(IBNAMenuListener2.FINAL_ENTRIES_GROUP));
	}

	public boolean DEBUG = false;
	public boolean PROFILE = false;
	protected static final LoadingCache<Object, AtomicLong> profileStats =
			CacheBuilder.newBuilder().weakKeys().build(new CacheLoader<Object, AtomicLong>() {
				@Override
				public AtomicLong load(Object input) {
					return new AtomicLong();
				}
			});

	protected final IBNAView view;
	protected final IBNAWorld world;
	protected final IThingLogicManager logics;
	protected Control eventControl;
	protected IBNAView eventView = null;
	protected IBNAView focusedView = null;
	protected IBNAView capturedView = null;
	protected IBNAView capturedView2 = null;

	public AbstractBNAUI(IBNAView view) {
		this.view = view;
		this.world = view.getBNAWorld();
		this.logics = world.getThingLogicManager();
	}

	protected void init(Control eventControl) {
		this.eventControl = eventControl;
	}

	@Override
	public void dispose() {
	}

	protected void loadPreferences(IUIResources resources, Composite composite) {
		resources.setAntialiasGraphics(BNARenderingSettings.getAntialiasGraphics(composite));
		resources.setAntialiasText(BNARenderingSettings.getAntialiasText(composite));
		resources.setDecorativeGraphics(BNARenderingSettings.getDecorativeGraphics(composite));
		resources.setDisplayShadows(BNARenderingSettings.getDisplayShadows(composite));
	}

	protected void mouseEventSWT(MouseType type, MouseEvent e) {
		mouseEventSWT2(type, e);
		mouseEventSWT1(type, e);
	}

	protected void mouseEventSWT1(MouseType type, MouseEvent e) {
		try (Finally lock = BNAUtils.lock()) {
			if (DEBUG) {
				if (type != MouseType.MOVE) {
					System.err.println(type + " " + e);
				}
			}
			try {
				switch (type) {
				case UP: {
					if (capturedView == null) {
						break;
					}
					try {
						IBNAView view = capturedView;
						ICoordinate location =
								DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
						List<IThing> things = view.getThingsAt(location);

						for (IBNAMouseListener logic : capturedView.getBNAWorld().getThingLogicManager()
								.getThingLogics(IBNAMouseListener.class)) {
							try {
								long time = System.nanoTime();
								logic.mouseUp(view, type, e, things, location);
								if (PROFILE) {
									time = System.nanoTime() - time;
									profileStats.get(logic).addAndGet(time);
								}
							}
							catch (Throwable t) {
								t.printStackTrace();
							}
						}
					}
					finally {
						capturedView = null;
					}
				}
					break;
				case DOWN: {
					focusedView = getInnerView(view,
							DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper()));
					capturedView = focusedView;
					IBNAView view = capturedView;
					ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
					List<IThing> things = view.getThingsAt(location);

					for (IBNAMouseListener logic : view.getBNAWorld().getThingLogicManager()
							.getThingLogics(IBNAMouseListener.class)) {
						try {
							long time = System.nanoTime();
							logic.mouseDown(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				case CLICK: {
					focusedView = getInnerView(view,
							DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper()));
					IBNAView view = focusedView;
					ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
					List<IThing> things = view.getThingsAt(location);

					for (IBNAMouseClickListener logic : view.getBNAWorld().getThingLogicManager()
							.getThingLogics(IBNAMouseClickListener.class)) {
						try {
							long time = System.nanoTime();
							logic.mouseClick(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				case MOVE: {
					IBNAView view = capturedView != null ? capturedView : getInnerView(this.view,
							DefaultCoordinate.forLocal(new Point(e.x, e.y), this.view.getCoordinateMapper()));
					ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
					List<IThing> things = view.getThingsAt(location);

					for (IBNAMouseMoveListener logic : logics.getThingLogics(IBNAMouseMoveListener.class)) {
						try {
							long time = System.nanoTime();
							logic.mouseMove(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				case ENTER: {
					IBNAView view = capturedView != null ? capturedView : getInnerView(this.view,
							DefaultCoordinate.forLocal(new Point(e.x, e.y), this.view.getCoordinateMapper()));
					ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
					List<IThing> things = view.getThingsAt(location);

					for (IBNAMouseTrackListener logic : logics.getThingLogics(IBNAMouseTrackListener.class)) {
						try {
							long time = System.nanoTime();
							logic.mouseEnter(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				case EXIT: {
					IBNAView view = capturedView != null ? capturedView : getInnerView(this.view,
							DefaultCoordinate.forLocal(new Point(e.x, e.y), this.view.getCoordinateMapper()));
					ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
					List<IThing> things = view.getThingsAt(location);

					for (IBNAMouseTrackListener logic : logics.getThingLogics(IBNAMouseTrackListener.class)) {
						try {
							long time = System.nanoTime();
							logic.mouseExit(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				case VERTICAL_WHEEL: {
					IBNAView view = capturedView != null ? capturedView : getInnerView(this.view,
							DefaultCoordinate.forLocal(new Point(e.x, e.y), this.view.getCoordinateMapper()));
					ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
					List<IThing> things = view.getThingsAt(location);

					for (IBNAMouseWheelListener logic : logics.getThingLogics(IBNAMouseWheelListener.class)) {
						try {
							long time = System.nanoTime();
							logic.mouseVerticalWheel(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				case HORIZONTAL_WHEEL: {
					IBNAView view = capturedView != null ? capturedView : getInnerView(this.view,
							DefaultCoordinate.forLocal(new Point(e.x, e.y), this.view.getCoordinateMapper()));
					ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
					List<IThing> things = view.getThingsAt(location);

					for (IBNAMouseWheelListener logic : logics.getThingLogics(IBNAMouseWheelListener.class)) {
						try {
							long time = System.nanoTime();
							logic.mouseHorizontalWheel(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				}
			}
			catch (Exception ex) {
				handleException(ex);
			}
		}
	}

	private static class RelevantLogic<T> {
		final IBNAView view;
		final ICoordinate location;
		final T logic;

		public RelevantLogic(IBNAView view, Point localPoint, T logic) {
			this.view = view;
			this.location = DefaultCoordinate.forLocal(localPoint, view.getCoordinateMapper());
			this.logic = logic;
		}
	}

	/**
	 * Retrieves the list of logics for a given view and additionally ones that implement {@link IBNAAllEventsListener2}
	 *
	 * @param eventView
	 *            The view from which all logics should always be returned regardless of whether they implement
	 *            {@link IBNAAllEventsListener2}.
	 * @param localPoint
	 *            The local point that should be used to create the {@link ICoordinate location} associated with a
	 *            view's logic.
	 * @param type
	 *            The interface that the logic should implement.
	 * @return All logics that are either in the given view or that implement {@link IBNAAllEventsListener2}.
	 */
	private <T> Iterable<RelevantLogic<T>> getAllLogics(IBNAView eventView, Point localPoint, Class<T> type) {
		Set<IBNAWorld> worldsSearched = Sets.newHashSet();
		List<IBNAView> viewsToSearch = Lists.newLinkedList();
		List<RelevantLogic<T>> logicsFound = Lists.newArrayList();
		if (eventView != null) {
			viewsToSearch.add(eventView);
		}
		viewsToSearch.add(this.view);
		while (!viewsToSearch.isEmpty()) {
			IBNAView view = viewsToSearch.remove(0);
			if (worldsSearched.add(view.getBNAWorld())) {
				// Add mouse logics.
				for (T logic : view.getBNAWorld().getThingLogicManager().getThingLogics(type)) {
					if (view == eventView) {
						logicsFound.add(new RelevantLogic<T>(view, localPoint, logic));
					}
					else if (logic instanceof IBNAAllEventsListener2) {
						logicsFound.add(new RelevantLogic<T>(view, localPoint, logic));
					}
				}
				// Search world things.
				ThingTypeTrackingLogic typeLogic =
						view.getBNAWorld().getThingLogicManager().addThingLogic(ThingTypeTrackingLogic.class);
				for (IHasWorld worldThing : typeLogic.getThings(IHasWorld.class)) {
					IBNAWorld world = worldThing.getWorld();
					if (world != null) {
						IThingPeer<?> peer = view.getThingPeer(worldThing);
						if (peer instanceof IHasInnerViewPeer) {
							IBNAView innerView = ((IHasInnerViewPeer<?>) peer).getInnerView();
							if (innerView != null) {
								viewsToSearch.add(innerView);
							}
						}
					}
				}
			}
		}
		return logicsFound;
	}

	/**
	 * Processes mouse events according to the discussion in
	 * {@link BNAUtils2#getThingsAtLocation(IBNAView, ICoordinate)}.
	 */
	protected void mouseEventSWT2(MouseType type, MouseEvent e) {
		try (Finally lock = BNAUtils.lock()) {
			if (DEBUG) {
				if (type != MouseType.MOVE) {
					System.err.println(type + " " + e);
				}
			}
			try {
				switch (type) {
				case DOWN: {
					ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
					ThingsAtLocation thingsAtLocation = BNAUtils2.getThingsAtLocation(view, location);
					for (RelevantLogic<IBNAMouseClickListener2> mouseLogic : getAllLogics(
							capturedView2 = thingsAtLocation.getView(), new Point(e.x, e.y),
							IBNAMouseClickListener2.class)) {
						try {
							long time = System.nanoTime();
							mouseLogic.logic.mouseDown(mouseLogic.view, type, e, mouseLogic.location, thingsAtLocation);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(mouseLogic.logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				case CLICK: {
					try {
						ICoordinate location =
								DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
						ThingsAtLocation thingsAtLocation = BNAUtils2.getThingsAtLocation(view, location);
						for (RelevantLogic<IBNAMouseClickListener2> mouseLogic : getAllLogics(
								capturedView2 = thingsAtLocation.getView(), new Point(e.x, e.y),
								IBNAMouseClickListener2.class)) {
							try {
								long time = System.nanoTime();
								mouseLogic.logic.mouseClick(mouseLogic.view, type, e, mouseLogic.location,
										thingsAtLocation);
								if (PROFILE) {
									time = System.nanoTime() - time;
									profileStats.get(mouseLogic.logic).addAndGet(time);
								}
							}
							catch (Throwable t) {
								t.printStackTrace();
							}
						}
					}
					finally {
						capturedView2 = null;
					}
				}
					break;
				case VERTICAL_WHEEL: {
					ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
					ThingsAtLocation thingsAtLocation = BNAUtils2.getThingsAtLocation(view, location);
					for (RelevantLogic<IBNAMouseWheelListener2> mouseLogic : getAllLogics(thingsAtLocation.getView(),
							new Point(e.x, e.y), IBNAMouseWheelListener2.class)) {
						try {
							long time = System.nanoTime();
							mouseLogic.logic.mouseVerticalWheel(mouseLogic.view, type, e, mouseLogic.location,
									thingsAtLocation);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(mouseLogic.logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				case HORIZONTAL_WHEEL: {
					ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
					ThingsAtLocation thingsAtLocation = BNAUtils2.getThingsAtLocation(view, location);
					for (RelevantLogic<IBNAMouseWheelListener2> mouseLogic : getAllLogics(thingsAtLocation.getView(),
							new Point(e.x, e.y), IBNAMouseWheelListener2.class)) {
						try {
							long time = System.nanoTime();
							mouseLogic.logic.mouseHorizontalWheel(mouseLogic.view, type, e, mouseLogic.location,
									thingsAtLocation);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(mouseLogic.logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				case UP:
				case MOVE:
					mouseEventSWT2Captured(type, e);
					break;
				case ENTER:
				case EXIT:
					// Do nothing.
					break;
				}
			}
			catch (Exception ex) {
				handleException(ex);
			}
		}

	}

	protected void mouseEventSWT2Captured(MouseType type, MouseEvent e) {
		try (Finally lock = BNAUtils.lock()) {
			IBNAView view = capturedView2 != null ? capturedView2 : this.view;
			ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
			ThingsAtLocation thingsAtLocation = BNAUtils2.getThingsAtLocation(view, location);
			try {
				switch (type) {
				case UP: {
					try {
						for (RelevantLogic<IBNAMouseClickListener2> mouseLogic : getAllLogics(view, new Point(e.x, e.y),
								IBNAMouseClickListener2.class)) {
							try {
								long time = System.nanoTime();
								mouseLogic.logic.mouseUp(mouseLogic.view, type, e, mouseLogic.location,
										thingsAtLocation);
								if (PROFILE) {
									time = System.nanoTime() - time;
									profileStats.get(mouseLogic.logic).addAndGet(time);
								}
							}
							catch (Throwable t) {
								t.printStackTrace();
							}
						}
					}
					finally {
						if (e.button == 0) {
							capturedView2 = null;
						}
					}
				}
					break;
				case MOVE: {
					for (RelevantLogic<IBNAMouseMoveListener2> mouseLogic : getAllLogics(view, new Point(e.x, e.y),
							IBNAMouseMoveListener2.class)) {
						try {
							long time = System.nanoTime();
							mouseLogic.logic.mouseMove(mouseLogic.view, type, e, mouseLogic.location, thingsAtLocation);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(mouseLogic.logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				case DOWN: // Fall through.
				case CLICK: // Fall through.
				case VERTICAL_WHEEL: // Fall through.
				case HORIZONTAL_WHEEL: // Fall through.
					throw new IllegalArgumentException();
				case ENTER:
				case EXIT:
					// Unimplemented.
					break;
				default:
					break;
				}
			}
			catch (Exception ex) {
				handleException(ex);
			}
		}
	}

	private IBNAView getInnerView(IBNAView view, ICoordinate location) {
		while (true) {
			IThing thing = SystemUtils.firstOrNull(view.getThingsAt(location));
			if (thing instanceof IHasWorld) {
				IBNAWorld world = ((IHasWorld) thing).getWorld();
				if (world != null) {
					IThingPeer<?> peer = view.getThingPeer(thing);
					if (peer instanceof IHasInnerViewPeer<?>) {
						view = ((IHasInnerViewPeer<?>) peer).getInnerView();
						if (view != null) {
							location = DefaultCoordinate.forLocal(location.getLocalPoint(), view.getCoordinateMapper());
						}
						continue;
					}
				}
			}
			break;
		}
		return view;
	}

	protected void gestureEventSWT(GestureType type, GestureEvent e) {
		try (Finally lock = BNAUtils.lock()) {
			if (DEBUG) {
				System.err.println(type + " " + e);
			}
			try {
				ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
				List<IThing> things = view.getThingsAt(location);

				switch (type) {
				case BEGIN:
					for (IBNATrackGestureListener logic : logics.getThingLogics(IBNATrackGestureListener.class)) {
						try {
							long time = System.nanoTime();
							logic.beginGesture(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
					break;
				case END:
					for (IBNATrackGestureListener logic : logics.getThingLogics(IBNATrackGestureListener.class)) {
						try {
							long time = System.nanoTime();
							logic.endGesture(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
					break;
				case MAGNIFY:
					for (IBNAMagnifyGestureListener logic : logics.getThingLogics(IBNAMagnifyGestureListener.class)) {
						try {
							long time = System.nanoTime();
							logic.magnifyGesture(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
					break;
				case PAN:
					for (IBNAPanGestureListener logic : logics.getThingLogics(IBNAPanGestureListener.class)) {
						try {
							long time = System.nanoTime();
							logic.panGesture(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
					break;
				case ROTATE:
					for (IBNARotateGestureListener logic : logics.getThingLogics(IBNARotateGestureListener.class)) {
						try {
							long time = System.nanoTime();
							logic.rotateGesture(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
					break;
				case SWIPE:
					for (IBNASwipeGestureListener logic : logics.getThingLogics(IBNASwipeGestureListener.class)) {
						try {
							long time = System.nanoTime();
							logic.swipeGesture(view, type, e, things, location);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
					break;
				}
			}
			catch (Exception ex) {
				handleException(ex);
			}
		}
	}

	protected void keyEventSWT(KeyType type, KeyEvent e) {
		keyEventSWT2(type, e);
		keyEventSWT1(type, e);
	}

	protected void keyEventSWT1(KeyType type, KeyEvent e) {
		try (Finally lock = BNAUtils.lock()) {
			if (DEBUG) {
				System.err.println(type + " " + e);
			}
			try {
				switch (type) {
				case PRESSED: {
					IBNAView view = capturedView != null ? capturedView : this.view;

					for (IBNAKeyListener logic : view.getBNAWorld().getThingLogicManager()
							.getThingLogics(IBNAKeyListener.class)) {
						try {
							long time = System.nanoTime();
							logic.keyPressed(view, type, e);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				case RELEASED: {
					IBNAView view = capturedView != null ? capturedView : this.view;

					for (IBNAKeyListener logic : view.getBNAWorld().getThingLogicManager()
							.getThingLogics(IBNAKeyListener.class)) {
						try {
							long time = System.nanoTime();
							logic.keyReleased(view, type, e);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
				}
					break;
				}
			}
			catch (Exception ex) {
				handleException(ex);
			}
		}
	}

	/**
	 * Sends key events to the top level view as well as logics implementing {@link IBNAAllEventsListener2}.
	 */
	protected void keyEventSWT2(KeyType type, KeyEvent e) {
		try (Finally lock = BNAUtils.lock()) {
			if (DEBUG) {
				System.err.println(type + " " + e);
			}
			IBNAView view = capturedView2 != null ? capturedView2 : this.view;
			switch (type) {
			case PRESSED: {
				for (RelevantLogic<IBNAKeyListener2> mouseLogic : getAllLogics(view, new Point(0, 0),
						IBNAKeyListener2.class)) {
					try {
						long time = System.nanoTime();
						mouseLogic.logic.keyPressed(mouseLogic.view, type, e);
						if (PROFILE) {
							time = System.nanoTime() - time;
							profileStats.get(mouseLogic.logic).addAndGet(time);
						}
					}
					catch (Throwable t) {
						t.printStackTrace();
					}
				}
			}
				break;
			case RELEASED: {
				for (RelevantLogic<IBNAKeyListener2> mouseLogic : getAllLogics(view, new Point(0, 0),
						IBNAKeyListener2.class)) {
					try {
						long time = System.nanoTime();
						mouseLogic.logic.keyReleased(mouseLogic.view, type, e);
						if (PROFILE) {
							time = System.nanoTime() - time;
							profileStats.get(mouseLogic.logic).addAndGet(time);
						}
					}
					catch (Throwable t) {
						t.printStackTrace();
					}
				}
			}
				break;
			}
		}
	}

	protected void focusEventSWT(FocusType type, FocusEvent e) {
		try (Finally lock = BNAUtils.lock()) {
			if (DEBUG) {
				System.err.println(type + " " + e);
			}
			try {
				switch (type) {
				case GAINED:
					for (IBNAFocusListener logic : logics.getThingLogics(IBNAFocusListener.class)) {
						try {
							long time = System.nanoTime();
							logic.focusGained(view, type, e);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
					break;
				case LOST:
					for (IBNAFocusListener logic : logics.getThingLogics(IBNAFocusListener.class)) {
						try {
							long time = System.nanoTime();
							logic.focusLost(view, type, e);
							if (PROFILE) {
								time = System.nanoTime() - time;
								profileStats.get(logic).addAndGet(time);
							}
						}
						catch (Throwable t) {
							t.printStackTrace();
						}
					}
					break;
				}
			}
			catch (Exception ex) {
				handleException(ex);
			}
		}
	}

	protected void menuEventSWT(MenuDetectEvent e) {
		MenuManager menuMgr = null;
		try {
			menuMgr = new MenuManager("BNA Popup Menu");
			addMenuSeparators(menuMgr);
			Menu menu = menuMgr.createContextMenu(eventControl);

			menuEventSWT2(e, menuMgr, menu);
			menuEventSWT1(e, menuMgr, menu);

			Point menuLocation = new Point(e.x, e.y);
			if (e.detail == SWT.MENU_KEYBOARD) {
				menuLocation = eventControl.toDisplay(new Point(5, 5));
			}
			menu.setLocation(menuLocation.x, menuLocation.y);
			menu.setVisible(true);
			Display display = eventControl.getDisplay();
			while (!menu.isDisposed() && menu.isVisible()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		}
		finally {
			if (menuMgr != null) {
				menuMgr.dispose();
			}
		}
	}

	protected void menuEventSWT1(MenuDetectEvent e, MenuManager menuMgr, Menu menu) {
		try (Finally lock = BNAUtils.lock()) {
			if (DEBUG) {
				System.err.println(e);
			}
			ICoordinate location;
			List<IThing> things;
			if (e.detail == SWT.MENU_MOUSE) {
				Point p = eventControl.toControl(e.x, e.y);
				location = DefaultCoordinate.forLocal(new Point(p.x, p.y), view.getCoordinateMapper());
				EnvironmentPropertiesThing.createIn(world).setNewThingSpot(location.getWorldPoint());
				things = view.getThingsAt(location);
			}
			else if (e.detail == SWT.MENU_KEYBOARD) {
				location = DefaultCoordinate.forLocal(new Point(0, 0), view.getCoordinateMapper());
				things = Lists.newArrayList();
			}
			else {
				throw new IllegalArgumentException("Unrecognized menu detail: " + e.detail);
			}

			IHasWorld world = SystemUtils.firstOrNull(things, IHasWorld.class);
			if (world != null && world.getWorld() != null) {
				logics.addThingLogic(WorldThingExternalEventsLogic.class).proxy(IBNAMenuListener.class).fillMenu(view,
						things, location, menuMgr);
			}
			for (IBNAMenuListener logic : logics.getThingLogics(IBNAMenuListener.class)) {
				try {
					logic.fillMenu(view, things, location, menuMgr);
				}
				catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}

	protected void menuEventSWT2(MenuDetectEvent e, MenuManager menuMgr, Menu menu) {
		try (Finally lock = BNAUtils.lock()) {
			if (DEBUG) {
				System.err.println(e);
			}
			Point p = eventControl.toControl(e.x, e.y);
			ICoordinate location = DefaultCoordinate.forLocal(new Point(p.x, p.y), view.getCoordinateMapper());
			ThingsAtLocation thingsAtLocation = BNAUtils2.getThingsAtLocation(view, location);
			IBNAView view = thingsAtLocation.getView();
			if (e.detail == SWT.MENU_MOUSE) {
				location = DefaultCoordinate.forLocal(new Point(p.x, p.y), view.getCoordinateMapper());
				EnvironmentPropertiesThing.createIn(world).setNewThingSpot(location.getWorldPoint());
			}
			else if (e.detail == SWT.MENU_KEYBOARD) {
				location = DefaultCoordinate.forLocal(new Point(0, 0), view.getCoordinateMapper());
			}
			else {
				throw new IllegalArgumentException("Unrecognized menu detail: " + e.detail);
			}

			for (RelevantLogic<IBNAMenuListener2> mouseLogic : getAllLogics(view, new Point(0, 0),
					IBNAMenuListener2.class)) {
				try {
					long time = System.nanoTime();
					mouseLogic.logic.fillMenu(mouseLogic.view, mouseLogic.location, thingsAtLocation, menuMgr);
					if (PROFILE) {
						time = System.nanoTime() - time;
						profileStats.get(mouseLogic.logic).addAndGet(time);
					}
				}
				catch (Throwable t) {
					t.printStackTrace();
				}
			}
		}
	}

	protected void dndEvent(DNDType type, DNDData data, List<IThing> things, ICoordinate location) {
		try (Finally lock = BNAUtils.lock()) {
			if (DEBUG) {
				System.err.println(type + " " + data);
			}
			switch (type) {
			case ENTER:
				for (IBNADragAndDropListener logic : logics.getThingLogics(IBNADragAndDropListener.class)) {
					try {
						long time = System.nanoTime();
						logic.dragEnter(view, type, data, things, location);
						if (PROFILE) {
							time = System.nanoTime() - time;
							profileStats.get(logic).addAndGet(time);
						}
					}
					catch (Throwable x) {
						x.printStackTrace();
					}
				}
				break;
			case DRAG:
				for (IBNADragAndDropListener logic : logics.getThingLogics(IBNADragAndDropListener.class)) {
					try {
						long time = System.nanoTime();
						logic.drag(view, type, data, things, location);
						if (PROFILE) {
							time = System.nanoTime() - time;
							profileStats.get(logic).addAndGet(time);
						}
					}
					catch (Throwable x) {
						x.printStackTrace();
					}
				}
				break;
			case EXIT:
				for (IBNADragAndDropListener logic : logics.getThingLogics(IBNADragAndDropListener.class)) {
					try {
						long time = System.nanoTime();
						logic.dragExit(view, type);
						if (PROFILE) {
							time = System.nanoTime() - time;
							profileStats.get(logic).addAndGet(time);
						}
					}
					catch (Throwable x) {
						x.printStackTrace();
					}
				}
				break;
			case DROP:
				for (IBNADragAndDropListener logic : logics.getThingLogics(IBNADragAndDropListener.class)) {
					try {
						long time = System.nanoTime();
						logic.drop(view, type, data, things, location);
						if (PROFILE) {
							time = System.nanoTime() - time;
							profileStats.get(logic).addAndGet(time);
						}
					}
					catch (Throwable x) {
						x.printStackTrace();
					}
				}
				break;
			}
		}
	}

	protected void handleException(Exception e) {
		e.printStackTrace();
		MessageDialog.openError(eventControl.getShell(), "Error", "An exception (" + e.getClass().getName()
				+ ") has occurred. Please see the platform log file for details.");
	}
}