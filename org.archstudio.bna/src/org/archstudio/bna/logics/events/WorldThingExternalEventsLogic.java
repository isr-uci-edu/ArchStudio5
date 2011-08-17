package org.archstudio.bna.logics.events;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.DropEventType;
import org.archstudio.bna.constants.MouseEventType;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingTypeTrackingLogic;
import org.archstudio.bna.things.utility.WorldThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNADragAndDropListener;
import org.archstudio.bna.utils.IBNAFocusListener;
import org.archstudio.bna.utils.IBNAKeyListener;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.archstudio.bna.utils.IBNAMouseClickListener;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.archstudio.bna.utils.IBNAMouseMoveListener;
import org.archstudio.bna.utils.IBNAMouseTrackListener;
import org.archstudio.bna.utils.IBNAUntypedListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

public class WorldThingExternalEventsLogic extends AbstractThingLogic implements IBNAMouseListener,
		IBNAMouseMoveListener, IBNAMouseTrackListener, IBNAMenuListener, IBNAFocusListener, IBNAKeyListener,
		IBNADragAndDropListener, IBNAUntypedListener {

	protected ThingTypeTrackingLogic tttl = null;

	public WorldThingExternalEventsLogic() {
	}

	@Override
	protected void init() {
		super.init();
		tttl = addThingLogic(ThingTypeTrackingLogic.class);
	}

	protected IHasWorld draggingIn = null;

	protected void mouseEvt(IBNAView view, MouseEvent e, IThing t, MouseEventType type) {
		IHasWorld vt = null;
		if (draggingIn != null) {
			vt = draggingIn;
		}
		else {
			if (t != null && t instanceof IHasWorld) {
				vt = (IHasWorld) t;
			}
		}
		if (vt == null) {
			return;
		}
		IBNAWorld internalWorld = vt.getWorld();
		if (internalWorld == null) {
			return;
		}
		//		if(vt instanceof IHasUserEditable){
		//			if(!((IHasUserEditable)vt).isUserEditable()) return;
		//		}
		WorldThingPeer vtp = (WorldThingPeer) view.getPeerCacheEntry(vt);
		IBNAView internalView = vtp.getInnerView();
		if (internalView == null) {
			return;
		}

		IThing internalThing = internalView.getThingAt(e.x, e.y);
		int worldX = internalView.getCoordinateMapper().localXtoWorldX(e.x);
		int worldY = internalView.getCoordinateMapper().localYtoWorldY(e.y);

		switch (type) {
		case MOUSE_UP:
			draggingIn = null;
			for (IBNAMouseListener logic : internalWorld.getThingLogicManager().getThingLogics(IBNAMouseListener.class)) {
				logic.mouseUp(internalView, e, internalThing, worldX, worldY);
			}
			break;
		case MOUSE_DOWN:
			draggingIn = vt;
			for (IBNAMouseListener logic : internalWorld.getThingLogicManager().getThingLogics(IBNAMouseListener.class)) {
				logic.mouseDown(internalView, e, internalThing, worldX, worldY);
			}
			break;
		case MOUSE_CLICK:
			for (IBNAMouseClickListener logic : internalWorld.getThingLogicManager().getThingLogics(
					IBNAMouseListener.class)) {
				logic.mouseClick(internalView, e, internalThing, worldX, worldY);
			}
			break;
		case MOUSE_DOUBLECLICK:
			for (IBNAMouseClickListener logic : internalWorld.getThingLogicManager().getThingLogics(
					IBNAMouseListener.class)) {
				logic.mouseDoubleClick(internalView, e, internalThing, worldX, worldY);
			}
			break;
		case MOUSE_MOVE:
			for (IBNAMouseMoveListener logic : internalWorld.getThingLogicManager().getThingLogics(
					IBNAMouseMoveListener.class)) {
				logic.mouseMove(internalView, e, internalThing, worldX, worldY);
			}
			break;
		case MOUSE_ENTER:
			for (IBNAMouseTrackListener logic : internalWorld.getThingLogicManager().getThingLogics(
					IBNAMouseTrackListener.class)) {
				logic.mouseEnter(internalView, e, internalThing, worldX, worldY);
			}
			break;
		case MOUSE_EXIT:
			for (IBNAMouseTrackListener logic : internalWorld.getThingLogicManager().getThingLogics(
					IBNAMouseTrackListener.class)) {
				logic.mouseExit(internalView, e, internalThing, worldX, worldY);
			}
			break;
		case MOUSE_HOVER:
			for (IBNAMouseTrackListener logic : internalWorld.getThingLogicManager().getThingLogics(
					IBNAMouseTrackListener.class)) {
				logic.mouseHover(internalView, e, internalThing, worldX, worldY);
			}
			break;
		}
	}

	public void mouseDown(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
		mouseEvt(view, evt, t, MouseEventType.MOUSE_DOWN);
	}

	public void mouseMove(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		mouseEvt(view, evt, t, MouseEventType.MOUSE_MOVE);
	}

	public void mouseUp(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
		mouseEvt(view, evt, t, MouseEventType.MOUSE_UP);
	}

	public void mouseClick(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
		mouseEvt(view, evt, t, MouseEventType.MOUSE_CLICK);
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
		mouseEvt(view, evt, t, MouseEventType.MOUSE_DOUBLECLICK);
	}

	public void mouseEnter(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		mouseEvt(view, evt, t, MouseEventType.MOUSE_ENTER);
	}

	public void mouseExit(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		mouseEvt(view, evt, t, MouseEventType.MOUSE_EXIT);
	}

	public void mouseHover(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		mouseEvt(view, evt, t, MouseEventType.MOUSE_HOVER);
	}

	@Override
	public void focusGained(IBNAView view, FocusEvent e) {
		for (IHasWorld vt : tttl.getThings(IHasWorld.class)) {
			//if(vt instanceof IHasUserEditable){
			//	//				if(!((IHasUserEditable)vt).isUserEditable()){
			//	//					continue;
			//	//				}
			//}

			IBNAWorld internalWorld = vt.getWorld();
			if (internalWorld == null) {
				return;
			}

			WorldThingPeer vtp = (WorldThingPeer) view.getPeerCacheEntry(vt);
			IBNAView internalView = vtp.getInnerView();
			if (internalView == null) {
				return;
			}

			if (internalView != null) {
				for (IBNAFocusListener logic : internalWorld.getThingLogicManager().getThingLogics(
						IBNAFocusListener.class)) {
					logic.focusGained(internalView, e);
				}
			}
		}
	}

	@Override
	public void focusLost(IBNAView view, FocusEvent e) {
		for (IHasWorld vt : tttl.getThings(IHasWorld.class)) {
			//if(vt instanceof IHasUserEditable){
			//	//				if(!((IHasUserEditable)vt).isUserEditable()){
			//	//					continue;
			//	//				}
			//}
			IBNAWorld internalWorld = vt.getWorld();
			if (internalWorld == null) {
				return;
			}

			WorldThingPeer vtp = (WorldThingPeer) view.getPeerCacheEntry(vt);
			IBNAView internalView = vtp.getInnerView();
			if (internalView == null) {
				return;
			}

			if (internalView != null) {
				for (IBNAFocusListener logic : internalWorld.getThingLogicManager().getThingLogics(
						IBNAFocusListener.class)) {
					logic.focusLost(internalView, e);
				}
			}
		}
	}

	@Override
	public void keyPressed(IBNAView view, KeyEvent e) {
		for (IHasWorld vt : tttl.getThings(IHasWorld.class)) {
			//if(vt instanceof IHasUserEditable){
			//	//				if(!((IHasUserEditable)vt).isUserEditable()){
			//	//					continue;
			//	//				}
			//}
			IBNAWorld internalWorld = vt.getWorld();
			if (internalWorld == null) {
				return;
			}

			WorldThingPeer vtp = (WorldThingPeer) view.getPeerCacheEntry(vt);
			IBNAView internalView = vtp.getInnerView();
			if (internalView == null) {
				return;
			}

			if (internalView != null) {
				for (IBNAKeyListener logic : internalWorld.getThingLogicManager().getThingLogics(IBNAKeyListener.class)) {
					logic.keyPressed(internalView, e);
				}
			}
		}
	}

	@Override
	public void keyReleased(IBNAView view, KeyEvent e) {
		for (IHasWorld vt : tttl.getThings(IHasWorld.class)) {
			//if(vt instanceof IHasUserEditable){
			//	//				if(!((IHasUserEditable)vt).isUserEditable()){
			//	//					continue;
			//	//				}
			//}

			IBNAWorld internalWorld = vt.getWorld();
			if (internalWorld == null) {
				return;
			}

			WorldThingPeer vtp = (WorldThingPeer) view.getPeerCacheEntry(vt);
			IBNAView internalView = vtp.getInnerView();
			if (internalView == null) {
				return;
			}

			if (internalView != null) {
				for (IBNAKeyListener logic : internalWorld.getThingLogicManager().getThingLogics(IBNAKeyListener.class)) {
					logic.keyReleased(internalView, e);
				}
			}
		}
	}

	@Override
	public void handleEvent(IBNAView view, Event event) {
		for (IHasWorld vt : tttl.getThings(IHasWorld.class)) {
			//if(vt instanceof IHasUserEditable){
			//	//				if(!((IHasUserEditable)vt).isUserEditable()){
			//	//					continue;
			//	//				}
			//}

			IBNAWorld internalWorld = vt.getWorld();
			if (internalWorld == null) {
				return;
			}

			WorldThingPeer vtp = (WorldThingPeer) view.getPeerCacheEntry(vt);
			IBNAView internalView = vtp.getInnerView();
			if (internalView == null) {
				return;
			}

			if (internalView != null) {
				for (IBNAUntypedListener logic : internalWorld.getThingLogicManager().getThingLogics(
						IBNAUntypedListener.class)) {
					logic.handleEvent(internalView, event);
				}
			}
		}
	}

	public void fillMenu(IBNAView view, Point localPoint, Point worldPoint, Iterable<IThing> things, IMenuManager m) {
		for (IHasWorld vt : tttl.getThings(IHasWorld.class)) {
			if (things == vt) {
				//if(vt instanceof IHasUserEditable){
				//	//					if(!((IHasUserEditable)vt).isUserEditable()){
				//	//						continue;
				//	//					}
				//}

				IBNAWorld internalWorld = vt.getWorld();
				if (internalWorld == null) {
					return;
				}

				WorldThingPeer vtp = (WorldThingPeer) view.getPeerCacheEntry(vt);
				IBNAView internalView = vtp.getInnerView();
				if (internalView == null) {
					return;
				}

				if (internalView != null) {
					IThing internalThing = internalView.getThingAt(localPoint, localY);
					int internalWorldX = internalView.getCoordinateMapper().localXtoWorldX(localPoint);
					int internalWorldY = internalView.getCoordinateMapper().localYtoWorldY(localY);
					for (IBNAMenuListener logic : internalView.getBNAWorld().getThingLogicManager()
							.getThingLogics(IBNAMenuListener.class)) {
						logic.fillMenu(internalView, localPoint, worldPoint, internalThing, m);
					}
				}
			}
		}
	}

	protected void dropEvt(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY, DropEventType evtType) {
		for (IHasWorld vt : tttl.getThings(IHasWorld.class)) {
			if (t == vt) {
				//if(vt instanceof IHasUserEditable){
				//	//					if(!((IHasUserEditable)vt).isUserEditable()){
				//	//						continue;
				//	//					}
				//}

				IBNAWorld internalWorld = vt.getWorld();
				if (internalWorld == null) {
					return;
				}

				WorldThingPeer vtp = (WorldThingPeer) view.getPeerCacheEntry(vt);
				IBNAView internalView = vtp.getInnerView();
				if (internalView == null) {
					return;
				}

				if (internalView != null) {
					IBNADragAndDropListener[] logics = internalView.getBNAWorld().getThingLogicManager()
							.getThingLogics(IBNADragAndDropListener.class);
					if (logics.length > 0) {
						Composite parentComposite = BNAUtils.getParentComposite(view);
						Point p = null;
						if (parentComposite != null) {
							p = BNAUtils.toPoint(parentComposite.toControl(event.x, event.y));
						}
						else {
							p = new Point(event.x, event.y);
						}

						IThing internalThing = internalView.getThingAt(p.x, p.y);
						int internalWorldX = internalView.getCoordinateMapper().localXtoWorldX(p.x);
						int internalWorldY = internalView.getCoordinateMapper().localYtoWorldY(p.y);
						switch (evtType) {
						case DRAG_ENTER:
							for (IBNADragAndDropListener logic : logics) {
								logic.dragEnter(internalView, event, internalThing, internalWorldX, internalWorldY);
							}
							break;
						case DRAG_OVER:
							for (IBNADragAndDropListener logic : logics) {
								logic.dragOver(internalView, event, internalThing, internalWorldX, internalWorldY);
							}
							break;
						case DRAG_LEAVE:
							for (IBNADragAndDropListener logic : logics) {
								logic.dragLeave(internalView, event, internalThing, internalWorldX, internalWorldY);
							}
							break;
						case DRAG_OPERATION_CHANGED:
							for (IBNADragAndDropListener logic : logics) {
								logic.dragOperationChanged(internalView, event, internalThing, internalWorldX,
										internalWorldY);
							}
							break;
						case DROP_ACCEPT:
							for (IBNADragAndDropListener logic : logics) {
								logic.dropAccept(internalView, event, internalThing, internalWorldX, internalWorldY);
							}
							break;
						case DROP:
							for (IBNADragAndDropListener logic : logics) {
								logic.drop(internalView, event, internalThing, internalWorldX, internalWorldY);
							}
							break;
						}
					}
				}
			}
		}
	}

	public void dragEnter(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY) {
		dropEvt(view, event, t, worldX, worldY, DropEventType.DRAG_ENTER);
	}

	public void dragOver(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY) {
		dropEvt(view, event, t, worldX, worldY, DropEventType.DRAG_OVER);
	}

	public void dragOperationChanged(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY) {
		dropEvt(view, event, t, worldX, worldY, DropEventType.DRAG_OPERATION_CHANGED);
	}

	public void dragLeave(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY) {
		dropEvt(view, event, t, worldX, worldY, DropEventType.DRAG_LEAVE);
	}

	public void dropAccept(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY) {
		dropEvt(view, event, t, worldX, worldY, DropEventType.DROP_ACCEPT);
	}

	public void drop(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY) {
		dropEvt(view, event, t, worldX, worldY, DropEventType.DROP);
	}
}
