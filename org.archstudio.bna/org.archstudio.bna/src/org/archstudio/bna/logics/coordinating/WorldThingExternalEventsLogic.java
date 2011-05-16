package org.archstudio.bna.logics.coordinating;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.DropEventType;
import org.archstudio.bna.constants.MouseEventType;
import org.archstudio.bna.facets.IHasUserEditable;
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
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;

public class WorldThingExternalEventsLogic extends AbstractThingLogic implements IBNAMouseListener,
		IBNAMouseMoveListener, IBNAMouseTrackListener, IBNAMenuListener, IBNAFocusListener, IBNAKeyListener,
		IBNADragAndDropListener, IBNAUntypedListener {
	protected ThingTypeTrackingLogic ttstl = null;

	public WorldThingExternalEventsLogic(ThingTypeTrackingLogic ttstl) {
		this.ttstl = ttstl;
	}

	protected IHasWorld draggingIn = null;

	protected void mouseEvt(IBNAView view, MouseEvent e, IThing t, MouseEventType type) {
		IHasWorld vt = null;
		if (draggingIn != null) {
			vt = draggingIn;
		}
		else {
			if ((t != null) && (t instanceof IHasWorld)) {
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
		if (vt instanceof IHasUserEditable) {
			if (!((IHasUserEditable) vt).isUserEditable())
				return;
		}
		WorldThingPeer vtp = (WorldThingPeer) view.getPeerCache(vt);
		IBNAView internalView = vtp.getInnerView();
		if (internalView == null)
			return;

		if ((type == MouseEventType.MOUSE_UP) || (type == MouseEventType.MOUSE_DOWN)
				|| (type == MouseEventType.MOUSE_CLICK) || (type == MouseEventType.MOUSE_DOUBLECLICK)) {
			List<IBNAMouseListener> logics = internalWorld.getThingLogicManager().getThingLogics(
					IBNAMouseListener.class);
			if (!logics.isEmpty()) {
				IThing internalThing = internalView.getThingAt(e.x, e.y);
				int worldX = internalView.getCoordinateMapper().localXtoWorldX(e.x);
				int worldY = internalView.getCoordinateMapper().localYtoWorldY(e.y);

				switch (type) {
				case MOUSE_UP:
					draggingIn = null;
					for (IBNAMouseListener l : logics) {
						l.mouseUp(internalView, e, internalThing, worldX, worldY);
					}
					break;
				case MOUSE_DOWN:
					draggingIn = vt;
					for (IBNAMouseListener l : logics) {
						l.mouseDown(internalView, e, internalThing, worldX, worldY);
					}
					break;
				case MOUSE_CLICK:
					for (IBNAMouseClickListener l : logics) {
						l.mouseClick(internalView, e, internalThing, worldX, worldY);
					}
					break;
				case MOUSE_DOUBLECLICK:
					for (IBNAMouseClickListener l : logics) {
						l.mouseDoubleClick(internalView, e, internalThing, worldX, worldY);
					}
					break;
				}
			}
		}
		else if (type == MouseEventType.MOUSE_MOVE) {
			List<IBNAMouseMoveListener> logics = internalWorld.getThingLogicManager().getThingLogics(
					IBNAMouseMoveListener.class);
			if (!logics.isEmpty()) {
				IThing internalThing = internalView.getThingAt(e.x, e.y);
				int worldX = internalView.getCoordinateMapper().localXtoWorldX(e.x);
				int worldY = internalView.getCoordinateMapper().localYtoWorldY(e.y);

				for (IBNAMouseMoveListener l : logics) {
					l.mouseMove(internalView, e, internalThing, worldX, worldY);
				}
			}
		}
		else if ((type == MouseEventType.MOUSE_ENTER) || (type == MouseEventType.MOUSE_EXIT)
				|| (type == MouseEventType.MOUSE_HOVER)) {
			List<IBNAMouseTrackListener> logics = internalWorld.getThingLogicManager().getThingLogics(
					IBNAMouseTrackListener.class);

			if (!logics.isEmpty()) {
				IThing internalThing = internalView.getThingAt(e.x, e.y);
				int worldX = internalView.getCoordinateMapper().localXtoWorldX(e.x);
				int worldY = internalView.getCoordinateMapper().localYtoWorldY(e.y);

				switch (type) {
				case MOUSE_ENTER:
					for (IBNAMouseTrackListener l : logics) {
						l.mouseEnter(internalView, e, internalThing, worldX, worldY);
					}
					break;
				case MOUSE_EXIT:
					for (IBNAMouseTrackListener l : logics) {
						l.mouseExit(internalView, e, internalThing, worldX, worldY);
					}
					break;
				case MOUSE_HOVER:
					for (IBNAMouseTrackListener l : logics) {
						l.mouseHover(internalView, e, internalThing, worldX, worldY);
					}
					break;
				}
			}
		}
	}

	public void mouseDown(IBNAView view, MouseEvent evt, Iterable<IThing> t,
			org.eclipse.draw2d.geometry.Point localPoint, org.eclipse.draw2d.geometry.Point worldPoint) {
		mouseEvt(view, evt, t, MouseEventType.MOUSE_DOWN);
	}

	public void mouseMove(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		mouseEvt(view, evt, t, MouseEventType.MOUSE_MOVE);
	}

	public void mouseUp(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
		mouseEvt(view, evt, t, MouseEventType.MOUSE_UP);
	}

	public void mouseClick(IBNAView view, MouseEvent evt, Iterable<IThing> t,
			org.eclipse.draw2d.geometry.Point localPoint, org.eclipse.draw2d.geometry.Point worldPoint) {
		mouseEvt(view, evt, t, MouseEventType.MOUSE_CLICK);
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, Iterable<IThing> t,
			org.eclipse.draw2d.geometry.Point localPoint, org.eclipse.draw2d.geometry.Point worldPoint) {
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

	public void focusGained(IBNAView view, FocusEvent e) {
		for (IHasWorld vt : ttstl.getThings()) {
			if (vt instanceof IHasUserEditable) {
				if (!((IHasUserEditable) vt).isUserEditable())
					continue;
			}

			IBNAWorld internalWorld = vt.getWorld();
			if (internalWorld == null)
				return;

			WorldThingPeer vtp = (WorldThingPeer) view.getPeerCache(vt);
			IBNAView internalView = vtp.getInnerView();
			if (internalView == null)
				return;

			if (internalView != null) {
				for (IBNAFocusListener l : internalWorld.getThingLogicManager().getThingLogics(IBNAFocusListener.class)) {
					l.focusGained(internalView, e);
				}
			}
		}
	}

	public void focusLost(IBNAView view, FocusEvent e) {
		for (IHasWorld vt : ttstl.getThings()) {
			if (vt instanceof IHasUserEditable) {
				if (!((IHasUserEditable) vt).isUserEditable())
					continue;
			}
			IBNAWorld internalWorld = vt.getWorld();
			if (internalWorld == null)
				return;

			WorldThingPeer vtp = (WorldThingPeer) view.getPeerCache(vt);
			IBNAView internalView = vtp.getInnerView();
			if (internalView == null)
				return;

			if (internalView != null) {
				for (IBNAFocusListener l : internalWorld.getThingLogicManager().getThingLogics(IBNAFocusListener.class)) {
					l.focusLost(internalView, e);
				}
			}
		}
	}

	public void keyPressed(IBNAView view, KeyEvent e) {
		for (IHasWorld vt : ttstl.getThings()) {
			if (vt instanceof IHasUserEditable) {
				if (!((IHasUserEditable) vt).isUserEditable())
					continue;
			}
			IBNAWorld internalWorld = vt.getWorld();
			if (internalWorld == null)
				return;

			WorldThingPeer vtp = (WorldThingPeer) view.getPeerCache(vt);
			IBNAView internalView = vtp.getInnerView();
			if (internalView == null)
				return;

			if (internalView != null) {
				for (IBNAKeyListener l : internalWorld.getThingLogicManager().getThingLogics(IBNAKeyListener.class)) {
					l.keyPressed(internalView, e);
				}
			}
		}
	}

	public void keyReleased(IBNAView view, KeyEvent e) {
		for (IHasWorld vt : ttstl.getThings()) {
			if (vt instanceof IHasUserEditable) {
				if (!((IHasUserEditable) vt).isUserEditable())
					continue;
			}

			IBNAWorld internalWorld = vt.getWorld();
			if (internalWorld == null)
				return;

			WorldThingPeer vtp = (WorldThingPeer) view.getPeerCache(vt);
			IBNAView internalView = vtp.getInnerView();
			if (internalView == null)
				return;

			if (internalView != null) {
				for (IBNAKeyListener l : internalWorld.getThingLogicManager().getThingLogics(IBNAKeyListener.class)) {
					l.keyReleased(internalView, e);
				}
			}
		}
	}

	public void handleEvent(IBNAView view, Event event) {
		for (IHasWorld vt : ttstl.getThings()) {
			if (vt instanceof IHasUserEditable) {
				if (!((IHasUserEditable) vt).isUserEditable())
					continue;
			}

			IBNAWorld internalWorld = vt.getWorld();
			if (internalWorld == null)
				return;

			WorldThingPeer vtp = (WorldThingPeer) view.getPeerCache(vt);
			IBNAView internalView = vtp.getInnerView();
			if (internalView == null)
				return;

			if (internalView != null) {
				for (IBNAUntypedListener l : internalWorld.getThingLogicManager().getThingLogics(
						IBNAUntypedListener.class)) {
					l.handleEvent(internalView, event);
				}
			}
		}
	}

	public void fillMenu(IBNAView view, Point localPoint, org.eclipse.draw2d.geometry.Point worldPoint,
			Iterable<IThing> things, IMenuManager m) {
		for (IHasWorld vt : ttstl.getThings()) {
			if (things == vt) {
				if (vt instanceof IHasUserEditable) {
					if (!((IHasUserEditable) vt).isUserEditable())
						continue;
				}

				IBNAWorld internalWorld = vt.getWorld();
				if (internalWorld == null)
					return;

				WorldThingPeer vtp = (WorldThingPeer) view.getPeerCache(vt);
				IBNAView internalView = vtp.getInnerView();
				if (internalView == null)
					return;

				if (internalView != null) {
					IThing internalThing = internalView.getThingAt(localPoint, localY);
					int internalWorldX = internalView.getCoordinateMapper().localXtoWorldX(localPoint);
					int internalWorldY = internalView.getCoordinateMapper().localYtoWorldY(localY);
					for (IBNAMenuListener l : internalView.getBNAWorld().getThingLogicManager()
							.getThingLogics(IBNAMenuListener.class)) {
						l.fillMenu(internalView, localPoint, worldPoint, internalThing, m);
					}
				}
			}
		}
	}

	protected void dropEvt(IBNAView view, DropTargetEvent event, IThing t, int worldX, int worldY, DropEventType evtType) {
		for (IHasWorld vt : ttstl.getThings()) {
			if (t == vt) {
				if (vt instanceof IHasUserEditable) {
					if (!((IHasUserEditable) vt).isUserEditable())
						continue;
				}

				IBNAWorld internalWorld = vt.getWorld();
				if (internalWorld == null)
					return;

				WorldThingPeer vtp = (WorldThingPeer) view.getPeerCache(vt);
				IBNAView internalView = vtp.getInnerView();
				if (internalView == null)
					return;

				if (internalView != null) {
					List<IBNADragAndDropListener> tls = internalView.getBNAWorld().getThingLogicManager()
							.getThingLogics(IBNADragAndDropListener.class);
					if (!tls.isEmpty()) {
						Composite parentComposite = BNAUtils.getParentComposite(view);
						Point p = null;
						if (parentComposite != null) {
							p = parentComposite.toControl(event.x, event.y);
						}
						else {
							p = new Point(event.x, event.y);
						}

						IThing internalThing = internalView.getThingAt(p.x, p.y);
						int internalWorldX = internalView.getCoordinateMapper().localXtoWorldX(p.x);
						int internalWorldY = internalView.getCoordinateMapper().localYtoWorldY(p.y);
						switch (evtType) {
						case DRAG_ENTER:
							for (IBNADragAndDropListener l : tls) {
								l.dragEnter(internalView, event, internalThing, internalWorldX, internalWorldY);
							}
							break;
						case DRAG_OVER:
							for (IBNADragAndDropListener l : tls) {
								l.dragOver(internalView, event, internalThing, internalWorldX, internalWorldY);
							}
							break;
						case DRAG_LEAVE:
							for (IBNADragAndDropListener l : tls) {
								l.dragLeave(internalView, event, internalThing, internalWorldX, internalWorldY);
							}
							break;
						case DRAG_OPERATION_CHANGED:
							for (IBNADragAndDropListener l : tls) {
								l.dragOperationChanged(internalView, event, internalThing, internalWorldX,
										internalWorldY);
							}
							break;
						case DROP_ACCEPT:
							for (IBNADragAndDropListener l : tls) {
								l.dropAccept(internalView, event, internalThing, internalWorldX, internalWorldY);
							}
							break;
						case DROP:
							for (IBNADragAndDropListener l : tls) {
								l.drop(internalView, event, internalThing, internalWorldX, internalWorldY);
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
