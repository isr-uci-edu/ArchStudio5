package org.archstudio.bna.logics.coordinating;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.DefaultCoordinate;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.constants.DropEventType;
import org.archstudio.bna.constants.MouseEventType;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;
import org.archstudio.bna.things.utility.WorldThingPeer;
import org.archstudio.bna.utils.IBNADragAndDropListener;
import org.archstudio.bna.utils.IBNAFocusListener;
import org.archstudio.bna.utils.IBNAKeyListener;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.bna.utils.IBNAMouseClickListener;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.archstudio.bna.utils.IBNAMouseMoveListener;
import org.archstudio.bna.utils.IBNAMouseTrackListener;
import org.archstudio.bna.utils.IBNAUntypedListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Event;

public class WorldThingExternalEventsLogic extends AbstractThingLogic implements IBNAMouseListener,
		IBNAMouseClickListener, IBNAMouseMoveListener, IBNAMouseTrackListener, IBNAMenuListener, IBNAFocusListener,
		IBNAKeyListener, IBNADragAndDropListener, IBNAUntypedListener {

	protected final ThingTypeTrackingLogic typeLogic;

	public WorldThingExternalEventsLogic(IBNAWorld world) {
		super(world);
		typeLogic = logics.addThingLogic(ThingTypeTrackingLogic.class);
	}

	protected IHasWorld draggingIn = null;

	protected void mouseEvt(IBNAView view, MouseEvent e, List<IThing> t, ICoordinate location, MouseEventType type) {
		IHasWorld worldThing = null;
		if (draggingIn != null) {
			worldThing = draggingIn;
		}
		else {
			worldThing = firstOrNull(t, IHasWorld.class);
		}
		if (worldThing == null) {
			return;
		}
		IBNAWorld innerWorld = worldThing.getWorld();
		if (innerWorld == null) {
			return;
		}
		WorldThingPeer<?> worldThingPeer = (WorldThingPeer<?>) view.getThingPeer(worldThing);
		IBNAView innerView = worldThingPeer.getInnerView();
		if (innerView == null) {
			return;
		}

		IThingLogicManager innerTlm = innerWorld.getThingLogicManager();
		ICoordinate innerLocation = DefaultCoordinate.forLocal(location.getLocalPoint(),
				innerView.getCoordinateMapper());
		List<IThing> innerThings = innerView.getThingsAt(innerLocation);

		switch (type) {
		case MOUSE_UP:
			draggingIn = null;
			for (IBNAMouseListener l : innerTlm.getThingLogics(IBNAMouseListener.class)) {
				l.mouseUp(innerView, e, innerThings, innerLocation);
			}
			break;
		case MOUSE_DOWN:
			draggingIn = worldThing;
			for (IBNAMouseListener l : innerTlm.getThingLogics(IBNAMouseListener.class)) {
				l.mouseDown(innerView, e, innerThings, innerLocation);
			}
			break;
		case MOUSE_CLICK:
			for (IBNAMouseClickListener l : innerTlm.getThingLogics(IBNAMouseClickListener.class)) {
				l.mouseClick(innerView, e, innerThings, innerLocation);
			}
			break;
		case MOUSE_DOUBLECLICK:
			for (IBNAMouseClickListener l : innerTlm.getThingLogics(IBNAMouseClickListener.class)) {
				l.mouseDoubleClick(innerView, e, innerThings, innerLocation);
			}
			break;
		case MOUSE_MOVE:
			for (IBNAMouseMoveListener l : innerTlm.getThingLogics(IBNAMouseMoveListener.class)) {
				l.mouseMove(innerView, e, innerThings, innerLocation);
			}
			break;
		case MOUSE_ENTER:
			for (IBNAMouseTrackListener l : innerTlm.getThingLogics(IBNAMouseTrackListener.class)) {
				l.mouseEnter(innerView, e, innerThings, innerLocation);
			}
			break;
		case MOUSE_EXIT:
			for (IBNAMouseTrackListener l : innerTlm.getThingLogics(IBNAMouseTrackListener.class)) {
				l.mouseExit(innerView, e, innerThings, innerLocation);
			}
			break;
		case MOUSE_HOVER:
			for (IBNAMouseTrackListener l : innerTlm.getThingLogics(IBNAMouseTrackListener.class)) {
				l.mouseHover(innerView, e, innerThings, innerLocation);
			}
			break;
		default:
			// do nothing
		}
	}

	@Override
	public void mouseDown(IBNAView view, MouseEvent evt, List<IThing> things, ICoordinate location) {
		if (firstOrNull(things, IHasWorld.class) != null) {
			mouseEvt(view, evt, things, location, MouseEventType.MOUSE_DOWN);
		}
	}

	@Override
	public void mouseMove(IBNAView view, MouseEvent evt, List<IThing> things, ICoordinate location) {
		mouseEvt(view, evt, things, location, MouseEventType.MOUSE_MOVE);
	}

	@Override
	public void mouseUp(IBNAView view, MouseEvent evt, List<IThing> things, ICoordinate location) {
		mouseEvt(view, evt, things, location, MouseEventType.MOUSE_UP);
	}

	@Override
	public void mouseClick(IBNAView view, MouseEvent evt, List<IThing> things, ICoordinate location) {
		if (firstOrNull(things, IHasWorld.class) != null) {
			mouseEvt(view, evt, things, location, MouseEventType.MOUSE_CLICK);
		}
	}

	@Override
	public void mouseDoubleClick(IBNAView view, MouseEvent evt, List<IThing> things, ICoordinate location) {
		if (firstOrNull(things, IHasWorld.class) != null) {
			mouseEvt(view, evt, things, location, MouseEventType.MOUSE_DOUBLECLICK);
		}
	}

	@Override
	public void mouseEnter(IBNAView view, MouseEvent evt, List<IThing> things, ICoordinate location) {
		mouseEvt(view, evt, things, location, MouseEventType.MOUSE_ENTER);
	}

	@Override
	public void mouseExit(IBNAView view, MouseEvent evt, List<IThing> things, ICoordinate location) {
		mouseEvt(view, evt, things, location, MouseEventType.MOUSE_EXIT);
	}

	@Override
	public void mouseHover(IBNAView view, MouseEvent evt, List<IThing> things, ICoordinate location) {
		mouseEvt(view, evt, things, location, MouseEventType.MOUSE_HOVER);
	}

	@Override
	public void focusGained(IBNAView view, FocusEvent e) {
		for (IHasWorld worldThing : typeLogic.getThings(IHasWorld.class)) {
			IBNAWorld innerWorld = worldThing.getWorld();
			if (innerWorld == null) {
				return;
			}
			WorldThingPeer<?> worldThingPeer = (WorldThingPeer<?>) view.getThingPeer(worldThing);
			IBNAView innerView = worldThingPeer.getInnerView();
			if (innerView == null) {
				return;
			}

			for (IBNAFocusListener l : innerWorld.getThingLogicManager().getThingLogics(IBNAFocusListener.class)) {
				l.focusGained(innerView, e);
			}
		}
	}

	@Override
	public void focusLost(IBNAView view, FocusEvent e) {
		for (IHasWorld worldThing : typeLogic.getThings(IHasWorld.class)) {
			IBNAWorld innerWorld = worldThing.getWorld();
			if (innerWorld == null) {
				return;
			}
			WorldThingPeer<?> worldThingPeer = (WorldThingPeer<?>) view.getThingPeer(worldThing);
			IBNAView innerView = worldThingPeer.getInnerView();
			if (innerView == null) {
				return;
			}

			for (IBNAFocusListener l : innerWorld.getThingLogicManager().getThingLogics(IBNAFocusListener.class)) {
				l.focusLost(innerView, e);
			}
		}
	}

	@Override
	public void keyPressed(IBNAView view, KeyEvent e) {
		for (IHasWorld worldThing : typeLogic.getThings(IHasWorld.class)) {
			IBNAWorld innerWorld = worldThing.getWorld();
			if (innerWorld == null) {
				return;
			}
			WorldThingPeer<?> worldThingPeer = (WorldThingPeer<?>) view.getThingPeer(worldThing);
			IBNAView innerView = worldThingPeer.getInnerView();
			if (innerView == null) {
				return;
			}

			for (IBNAKeyListener l : innerWorld.getThingLogicManager().getThingLogics(IBNAKeyListener.class)) {
				l.keyPressed(innerView, e);
			}
		}
	}

	@Override
	public void keyReleased(IBNAView view, KeyEvent e) {
		for (IHasWorld worldThing : typeLogic.getThings(IHasWorld.class)) {
			IBNAWorld innerWorld = worldThing.getWorld();
			if (innerWorld == null) {
				return;
			}
			WorldThingPeer<?> worldThingPeer = (WorldThingPeer<?>) view.getThingPeer(worldThing);
			IBNAView innerView = worldThingPeer.getInnerView();
			if (innerView == null) {
				return;
			}

			for (IBNAKeyListener l : innerWorld.getThingLogicManager().getThingLogics(IBNAKeyListener.class)) {
				l.keyReleased(innerView, e);
			}
		}
	}

	@Override
	public void handleEvent(IBNAView view, Event event) {
		for (IHasWorld worldThing : typeLogic.getThings(IHasWorld.class)) {
			IBNAWorld innerWorld = worldThing.getWorld();
			if (innerWorld == null) {
				return;
			}
			WorldThingPeer<?> worldThingPeer = (WorldThingPeer<?>) view.getThingPeer(worldThing);
			IBNAView innerView = worldThingPeer.getInnerView();
			if (innerView == null) {
				return;
			}

			for (IBNAUntypedListener l : innerWorld.getThingLogicManager().getThingLogics(IBNAUntypedListener.class)) {
				l.handleEvent(innerView, event);
			}
		}
	}

	@Override
	public void fillMenu(IBNAView view, List<IThing> things, ICoordinate location, IMenuManager menu) {
		IThing thing = firstOrNull(things);
		if (thing instanceof IHasWorld) {
			IHasWorld worldThing = (IHasWorld) thing;

			IBNAWorld innerWorld = worldThing.getWorld();
			if (innerWorld == null) {
				return;
			}
			WorldThingPeer<?> worldThingPeer = (WorldThingPeer<?>) view.getThingPeer(worldThing);
			IBNAView innerView = worldThingPeer.getInnerView();
			if (innerView == null) {
				return;
			}

			IThingLogicManager innerTlm = innerWorld.getThingLogicManager();
			ICoordinate innerLocation = DefaultCoordinate.forLocal(location.getLocalPoint(),
					innerView.getCoordinateMapper());
			List<IThing> innerThings = innerView.getThingsAt(innerLocation);

			for (IBNAMenuListener l : innerTlm.getThingLogics(IBNAMenuListener.class)) {
				l.fillMenu(innerView, innerThings, innerLocation, menu);
			}
		}
	}

	public void dropEvt(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location,
			DropEventType eventType) {
		IThing thing = firstOrNull(things);
		if (thing instanceof IHasWorld) {
			IHasWorld worldThing = (IHasWorld) thing;

			IBNAWorld innerWorld = worldThing.getWorld();
			if (innerWorld == null) {
				return;
			}
			WorldThingPeer<?> worldThingPeer = (WorldThingPeer<?>) view.getThingPeer(worldThing);
			IBNAView innerView = worldThingPeer.getInnerView();
			if (innerView == null) {
				return;
			}

			IThingLogicManager innerTlm = innerWorld.getThingLogicManager();
			ICoordinate innerLocation = DefaultCoordinate.forLocal(location.getLocalPoint(),
					innerView.getCoordinateMapper());
			List<IThing> innerThings = innerView.getThingsAt(innerLocation);

			//Composite parentComposite = BNAUtils.getParentComposite(view);
			//Point p = null;
			//if (parentComposite != null) {
			//	p = parentComposite.toControl(event.x, event.y);
			//}
			//else {
			//	p = new Point(event.x, event.y);
			//}

			switch (eventType) {
			case DRAG_ENTER:
				for (IBNADragAndDropListener l : innerTlm.getThingLogics(IBNADragAndDropListener.class)) {
					l.dragEnter(innerView, event, innerThings, innerLocation);
				}
				break;
			case DRAG_OVER:
				for (IBNADragAndDropListener l : innerTlm.getThingLogics(IBNADragAndDropListener.class)) {
					l.dragOver(innerView, event, innerThings, innerLocation);
				}
				break;
			case DRAG_LEAVE:
				for (IBNADragAndDropListener l : innerTlm.getThingLogics(IBNADragAndDropListener.class)) {
					l.dragLeave(innerView, event, innerThings, innerLocation);
				}
				break;
			case DRAG_OPERATION_CHANGED:
				for (IBNADragAndDropListener l : innerTlm.getThingLogics(IBNADragAndDropListener.class)) {
					l.dragOperationChanged(innerView, event, innerThings, innerLocation);
				}
				break;
			case DROP_ACCEPT:
				for (IBNADragAndDropListener l : innerTlm.getThingLogics(IBNADragAndDropListener.class)) {
					l.dropAccept(innerView, event, innerThings, innerLocation);
				}
				break;
			case DROP:
				for (IBNADragAndDropListener l : innerTlm.getThingLogics(IBNADragAndDropListener.class)) {
					l.drop(innerView, event, innerThings, innerLocation);
				}
				break;
			}
		}
	}

	@Override
	public void dragEnter(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location) {
		dropEvt(view, event, things, location, DropEventType.DRAG_ENTER);
	}

	@Override
	public void dragOver(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location) {
		dropEvt(view, event, things, location, DropEventType.DRAG_OVER);
	}

	@Override
	public void dragOperationChanged(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location) {
		dropEvt(view, event, things, location, DropEventType.DRAG_OPERATION_CHANGED);
	}

	@Override
	public void dragLeave(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location) {
		dropEvt(view, event, things, location, DropEventType.DRAG_LEAVE);
	}

	@Override
	public void dropAccept(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location) {
		dropEvt(view, event, things, location, DropEventType.DROP_ACCEPT);
	}

	@Override
	public void drop(IBNAView view, DropTargetEvent event, List<IThing> things, ICoordinate location) {
		dropEvt(view, event, things, location, DropEventType.DROP);
	}
}
