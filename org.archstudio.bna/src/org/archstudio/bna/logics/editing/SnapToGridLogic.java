package org.archstudio.bna.logics.editing;

import org.archstudio.bna.DefaultCoordinate;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.logics.events.DragMoveEventsLogic;
import org.archstudio.bna.logics.events.IDragMoveListener;
import org.archstudio.bna.utils.GridUtils;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;

public class SnapToGridLogic extends AbstractThingLogic implements IDragMoveListener {

	public SnapToGridLogic() {
	}

	@Override
	protected void init() {
		super.init();
		// this logic listens to events from the following
		getBNAWorld().getThingLogicManager().addThingLogic(DragMoveEventsLogic.class);
	}

	Point referencePointToInitialMousePointDelta = new Point();

	@Override
	public void dragStarted(DragMoveEvent evt) {
		referencePointToInitialMousePointDelta.setLocation(0, 0);
		if (evt.getInitialThing() instanceof IRelativeMovable) {
			/*
			 * Include the delta from the reference point to the initial mouse point in order for the relative movable
			 * thing to be aligned with the grid.
			 */
			Point initialRelativeMovablePoint = ((IRelativeMovable) evt.getInitialThing()).getReferencePoint();
			Point initialMousePoint = evt.getInitialLocation().getWorldPoint(new Point());
			Point delta = initialMousePoint.getTranslated(initialRelativeMovablePoint.getNegated());
			referencePointToInitialMousePointDelta.setLocation(delta);
		}

		dragMoved(evt);
	}

	@Override
	public void dragMoved(DragMoveEvent evt) {
		Point adjustedWorldPoint = evt.getAdjustedLocation().getWorldPoint(new Point());
		if ((evt.getEvt().stateMask & SWT.MOD3) == 0) {
			IBNAModel model = getBNAModel();
			if (model != null) {
				int gridSpacing = GridUtils.getGridSpacing(model);
				if (gridSpacing != 0) {
					// adjust the point so that it is on the grid
					adjustedWorldPoint = GridUtils.snapToGrid(gridSpacing, adjustedWorldPoint);
					adjustedWorldPoint.translate(referencePointToInitialMousePointDelta);
				}
			}
		}

		evt.setAdjustedLocation(new DefaultCoordinate(evt.getView().getCoordinateMapper()
				.worldToLocal(adjustedWorldPoint.getCopy()), adjustedWorldPoint));
	}

	@Override
	public void dragFinished(DragMoveEvent evt) {
	}

}
