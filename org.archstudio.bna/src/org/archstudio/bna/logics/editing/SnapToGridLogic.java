package org.archstudio.bna.logics.editing;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.GridUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IDragMovable;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.facets.IHasUserEditable;
import org.archstudio.bna.logics.tracking.SelectionTrackingLogic;

public class SnapToGridLogic extends AbstractThingLogic implements IDragMoveListener {

	protected DragMovableLogic dml = null;
	protected SelectionTrackingLogic stl = null;

	public SnapToGridLogic(DragMovableLogic dml, SelectionTrackingLogic stl) {
		this.dml = dml;
		this.stl = stl;
	}

	public void init() {
		super.init();
		dml.addDragMoveListener(this);
	}

	public void destroy() {
		dml.removeDragMoveListener(this);
		super.destroy();
	}

	public void dragMoved(DragMoveEvent evt) {
		MouseEvent mevt = evt.getMouseEvent();
		if ((mevt.stateMask & SWT.CONTROL) != 0)
			return;

		IBNAModel m = evt.getSource().getBNAModel();
		int gridSpacing = GridUtils.getGridSpacing(m);
		if (gridSpacing == 0)
			return;
		IDragMovable t = evt.getMovedThing();
		Point p = t.getReferencePoint();
		Point p2 = GridUtils.snapToGrid(gridSpacing, p);
		t.setReferencePoint(p2);

		int dx = p2.x - p.x;
		int dy = p2.y - p.y;

		IThing movedThing = evt.getMovedThing();
		if (movedThing instanceof IHasSelected) {
			if (((IHasSelected) movedThing).isSelected()) {
				IHasSelected[] otherSelectedThings = stl.getSelectedThings();
				for (IHasSelected otherSelectedThing : otherSelectedThings) {
					if (!otherSelectedThing.equals(movedThing)) {
						if ((otherSelectedThing instanceof IDragMovable) && (otherSelectedThing instanceof IHasUserEditable)) {
							if (((IHasUserEditable) otherSelectedThing).isUserEditable()) {
								IDragMovable rmt = (IDragMovable) otherSelectedThing;
								Point rp = rmt.getReferencePoint();
								rp.x += dx;
								rp.y += dy;
								rmt.setReferencePoint(rp);
							}
						}
					}
				}
			}
		}
	}

	public void dragFinished(DragMoveEvent evt) {
	}
}
