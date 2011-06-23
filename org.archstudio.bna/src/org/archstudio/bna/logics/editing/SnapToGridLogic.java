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
			IBNAModel model = getBNAModel();
			if (model != null) {
				int gridSpacing = GridUtils.getGridSpacing(model);
				if (gridSpacing != 0) {
					// calculate relative movable and mouse point delta, used to translate between them when snapping 
					Point initialRelativeMovablePoint = ((IRelativeMovable) evt.getInitialThing()).getReferencePoint();
					Point initialMousePoint = evt.getInitialLocation().getWorldPoint(new Point());
					Point delta = initialMousePoint.getTranslated(initialRelativeMovablePoint.getNegated());
					referencePointToInitialMousePointDelta.setLocation(delta);
				}
			}
		}
	}

	@Override
	public void dragMoved(DragMoveEvent evt) {
		Point adjustedThingWorldPoint = evt.getAdjustedThingLocation().getWorldPoint(new Point());
		Point adjustedMouseWorldPoint = evt.getAdjustedMouseLocation().getWorldPoint(new Point());

		if ((evt.getEvt().stateMask & SWT.MOD3) == 0) {
			IBNAModel model = getBNAModel();
			if (model != null) {
				int gridSpacing = GridUtils.getGridSpacing(model);
				if (gridSpacing != 0) {
					// adjust the mouse point to the relative movable point 
					adjustedThingWorldPoint.translate(referencePointToInitialMousePointDelta.getNegated());
					// snap it to the grid
					adjustedThingWorldPoint = GridUtils.snapToGrid(gridSpacing, adjustedThingWorldPoint.getCopy());
					// adjust it back to the mouse point
					adjustedThingWorldPoint.translate(referencePointToInitialMousePointDelta);
					// adjust the absolute mouse point
					adjustedMouseWorldPoint = GridUtils.snapToGrid(gridSpacing, adjustedMouseWorldPoint.getCopy());
				}
			}
		}

		evt.setAdjustedThingLocation(new DefaultCoordinate(evt.getView().getCoordinateMapper()
				.worldToLocal(adjustedThingWorldPoint.getCopy()), adjustedThingWorldPoint.getCopy()));
		evt.setAdjustedMouseLocation(new DefaultCoordinate(evt.getView().getCoordinateMapper()
				.worldToLocal(adjustedMouseWorldPoint.getCopy()), adjustedMouseWorldPoint.getCopy()));
	}

	@Override
	public void dragFinished(DragMoveEvent evt) {
	}

}
