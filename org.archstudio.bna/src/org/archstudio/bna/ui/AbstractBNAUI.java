package org.archstudio.bna.ui;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.archstudio.bna.DefaultCoordinate;
import org.archstudio.bna.IBNAMouseWheelListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.constants.DNDData;
import org.archstudio.bna.constants.DNDType;
import org.archstudio.bna.constants.FocusType;
import org.archstudio.bna.constants.GestureType;
import org.archstudio.bna.constants.KeyType;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
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

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

public abstract class AbstractBNAUI implements IBNAUI {

	protected static final boolean DEBUG = false;
	protected static final boolean PROFILE = false;
	protected static final LoadingCache<Object, AtomicLong> profileStats = CacheBuilder.newBuilder().weakKeys()
			.build(new CacheLoader<Object, AtomicLong>() {
				@Override
				public AtomicLong load(Object input) {
					return new AtomicLong();
				}
			});

	protected final IBNAView view;
	protected final IBNAWorld world;
	protected final IThingLogicManager logics;
	protected Control eventControl;

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
		if (DEBUG) {
			if (type != MouseType.MOVE) {
				System.err.println(type + " " + e);
			}
		}
		try {
			ICoordinate location = DefaultCoordinate.forLocal(new Point(e.x, e.y), view.getCoordinateMapper());
			List<IThing> things = view.getThingsAt(location);

			switch (type) {
			case UP:
				for (IBNAMouseListener logic : logics.getThingLogics(IBNAMouseListener.class)) {
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
				break;
			case DOWN:
				for (IBNAMouseListener logic : logics.getThingLogics(IBNAMouseListener.class)) {
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
				break;
			case CLICK:
				for (IBNAMouseClickListener logic : logics.getThingLogics(IBNAMouseClickListener.class)) {
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
				break;
			case MOVE:
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
				break;
			case ENTER:
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
				break;
			case EXIT:
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
				break;
			case VERTICAL_WHEEL:
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
				break;
			case HORIZONTAL_WHEEL:
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
				break;
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	protected void gestureEventSWT(GestureType type, GestureEvent e) {
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

	protected void keyEventSWT(KeyType type, KeyEvent e) {
		if (DEBUG) {
			System.err.println(type + " " + e);
		}
		try {
			switch (type) {
			case PRESSED:
				for (IBNAKeyListener logic : logics.getThingLogics(IBNAKeyListener.class)) {
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
				break;
			case RELEASED:
				for (IBNAKeyListener logic : logics.getThingLogics(IBNAKeyListener.class)) {
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
				break;
			}
		}
		catch (Exception ex) {
			handleException(ex);
		}
	}

	protected void focusEventSWT(FocusType type, FocusEvent e) {
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

	protected void menuEventSWT(MenuDetectEvent e) {
		if (DEBUG) {
			System.err.println(e);
		}
		ICoordinate location;
		List<IThing> things;
		Point menuLocation = new Point(e.x, e.y);
		if (e.detail == SWT.MENU_MOUSE) {
			Point p = eventControl.toControl(e.x, e.y);
			location = DefaultCoordinate.forLocal(new Point(p.x, p.y), view.getCoordinateMapper());
			things = view.getThingsAt(location);
		}
		else if (e.detail == SWT.MENU_KEYBOARD) {
			menuLocation = eventControl.toDisplay(new Point(5, 5));
			location = DefaultCoordinate.forLocal(new Point(0, 0), view.getCoordinateMapper());
			things = Lists.newArrayList();
		}
		else {
			throw new IllegalArgumentException("Unrecognized menu detail: " + e.detail);
		}

		MenuManager menuMgr = null;
		try {
			menuMgr = new MenuManager("BNA Popup Menu");
			Menu menu = menuMgr.createContextMenu(eventControl);
			menuMgr.add(new Action("Test") {

				@Override
				public void run() {
				}
			});
			for (IBNAMenuListener logic : logics.getThingLogics(IBNAMenuListener.class)) {
				try {
					logic.fillMenu(view, things, location, menuMgr);
				}
				catch (Throwable t) {
					t.printStackTrace();
				}
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

	protected void dndEvent(DNDType type, DNDData data, List<IThing> things, ICoordinate location) {
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

	protected void handleException(Exception e) {
		e.printStackTrace();
		MessageDialog.openError(eventControl.getShell(), "Error", "An exception (" + e.getClass().getName()
				+ ") has occurred. Please see the platform log file for details.");
	}
}