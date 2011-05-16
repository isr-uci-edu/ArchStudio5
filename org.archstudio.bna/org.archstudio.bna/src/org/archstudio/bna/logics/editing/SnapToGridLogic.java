package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.logics.events.DragMoveEventsLogic;
import org.archstudio.bna.logics.events.IDragMoveListener;
import org.archstudio.bna.utils.GridUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;

public class SnapToGridLogic extends AbstractThingLogic implements IDragMoveListener {

	public SnapToGridLogic(DragMoveEventsLogic dml) {
		// dml: this logic listens to dml events, this ensures that the designer remembers to include it
	}

	int initialAdjustedOffsetX = 0;
	int initialAdjustedOffsetY = 0;

	public void dragStarted(DragMoveEvent evt) {
		initialAdjustedOffsetX = 0;
		initialAdjustedOffsetY = 0;
		IBNAModel model = getBNAModel();
		if (model != null) {
			int gridSpacing = GridUtils.getGridSpacing(model);
			if (gridSpacing != 0) {
				Point initialAdjustedPoint = evt.getAdjustedWorldPoint();
				Point snappedAdjustedPoint = GridUtils.snapToGrid(gridSpacing, initialAdjustedPoint);
				initialAdjustedOffsetX = snappedAdjustedPoint.x - initialAdjustedPoint.x;
				initialAdjustedOffsetY = snappedAdjustedPoint.y - initialAdjustedPoint.y;

				// adjust the point so that it is on the grid
				evt.setAdjustedWorldPoint(GridUtils.snapToGrid(gridSpacing, evt.getAdjustedWorldPoint()));

				// we also include the delta of the snapped reference point
				// the first time only in order to initially move it to the 
				// snapped postiion
				IThing initialThing = evt.getInitialThing();
				if (initialThing instanceof IRelativeMovable) {
					Point referencePoint = ((IRelativeMovable) initialThing).getReferencePoint();
					Point snappedReferencePoint = GridUtils.snapToGrid(gridSpacing, referencePoint);
					evt.setAdjustedWorldX(evt.getAdjustedWorldX() - (snappedReferencePoint.x - referencePoint.x));
					evt.setAdjustedWorldY(evt.getAdjustedWorldY() - (snappedReferencePoint.y - referencePoint.y));
				}
			}
		}
	}

	public void dragMoved(DragMoveEvent evt) {
		Point adjustedWorldPoint = evt.getAdjustedWorldPoint();
		adjustedWorldPoint = new Point(adjustedWorldPoint.x + initialAdjustedOffsetX, adjustedWorldPoint.y
				+ initialAdjustedOffsetY);
		if ((evt.getEvt().stateMask & SWT.MOD3) == 0) {
			IBNAModel model = getBNAModel();
			if (model != null) {
				int gridSpacing = GridUtils.getGridSpacing(model);
				if (gridSpacing != 0) {
					// adjust the point so that it is on the grid
					adjustedWorldPoint = GridUtils.snapToGrid(gridSpacing, adjustedWorldPoint);
				}
			}
		}
		evt.setAdjustedWorldPoint(adjustedWorldPoint);
	}

	public void dragFinished(DragMoveEvent evt) {
	}
}
