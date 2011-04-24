package org.archstudio.bna.logics.editing;

import java.util.Vector;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAComposite;
import org.archstudio.bna.IBNAMouseListener;
import org.archstudio.bna.IBNAMouseMoveListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IDragMovable;
import org.archstudio.bna.facets.IHasCursor;

public class DragMovableLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMouseMoveListener {
	protected IDragMovable grabbedThing = null;
	protected boolean dragged = false;

	protected int lastMouseX = -1;

	protected int lastMouseY = -1;

	public void destroy() {
		grabbedThing = null;
		super.destroy();
	}

	private int dx = 0;
	private int dy = 0;

	public void mouseDown(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		if (evt.button == 1) {
			if ((t instanceof IDragMovable) && ((IDragMovable) t).isUserEditable()) {
				dragged = false;
				grabbedThing = (IDragMovable) t;
				lastMouseX = evt.x;
				lastMouseY = evt.y;

				Point referencePoint = grabbedThing.getReferencePoint();
				dx = worldX - referencePoint.x;
				dy = worldY - referencePoint.y;

				if (!(grabbedThing instanceof IHasCursor)) {
					Object src = evt.getSource();
					if ((src != null) && (src instanceof BNAComposite)) {
						BNAComposite bnaComposite = (BNAComposite) src;
						bnaComposite.setCursor(evt.display.getSystemCursor(SWT.CURSOR_SIZEALL));
					}
				}
			}
		}
	}

	public void mouseUp(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		if (grabbedThing != null) {
			if ((dragged) && (grabbedThing instanceof IDragMovable)) {
				fireDragFinishedEvent((IDragMovable) grabbedThing, evt);
			}
			Object src = evt.getSource();
			if ((src != null) && (src instanceof BNAComposite)) {
				BNAComposite bnaComposite = (BNAComposite) src;
				bnaComposite.setCursor(null);
			}
		}
		dx = dy = 0;
		dragged = false;
		grabbedThing = null;
	}

	public void mouseClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseMove(IBNAView view, MouseEvent e, IThing t, int worldX, int worldY) {
		if (grabbedThing != null) {
			dragged = true;
			Point rp = ((IDragMovable) grabbedThing).getReferencePoint();
			Point np = new Point(worldX - dx, worldY - dy);
			grabbedThing.setReferencePoint(np);
			fireDragMoveEvent(grabbedThing, e, np.x - rp.x, np.y - rp.y);
		}
	}

	protected Vector<IDragMoveListener> listeners = new Vector<IDragMoveListener>();

	public void addDragMoveListener(IDragMoveListener l) {
		listeners.add(l);
	}

	public void removeDragMoveListener(IDragMoveListener l) {
		listeners.remove(l);
	}

	protected void fireDragMoveEvent(IDragMovable t, MouseEvent mevt, int dx, int dy) {
		DragMoveEvent evt = new DragMoveEvent(this, t, mevt, dx, dy);
		for (IDragMoveListener l : listeners) {
			l.dragMoved(evt);
		}
	}

	protected void fireDragFinishedEvent(IDragMovable t, MouseEvent mevt) {
		DragMoveEvent evt = new DragMoveEvent(this, t, mevt, 0, 0);
		for (IDragMoveListener l : listeners) {
			l.dragFinished(evt);
		}
	}
}
