package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasReferencePoint;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.events.DragMoveEvent;
import org.archstudio.bna.logics.events.DragMoveEventsLogic;
import org.archstudio.bna.logics.events.IDragMoveListener;
import org.archstudio.bna.utils.DefaultCoordinate;
import org.archstudio.bna.utils.GridUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;

public class SnapToGridLogic extends AbstractThingLogic implements IDragMoveListener {

	public SnapToGridLogic(IBNAWorld world) {
		super(world);
		logics.addThingLogic(DragMoveEventsLogic.class);
	};

	Point referencePointToInitialMousePointDelta = new Point(0, 0);

	@Override
	synchronized public void dragStarted(DragMoveEvent evt) {
		referencePointToInitialMousePointDelta.x = referencePointToInitialMousePointDelta.y = 0;
		if (evt.getInitialThing() instanceof IHasReferencePoint) {
			int gridSpacing = GridUtils.getGridSpacing(world);
			if (gridSpacing != 0) {
				// calculate relative movable and mouse point delta, used to translate between them when snapping 
				Point initialRelativeMovablePoint = ((IHasReferencePoint) evt.getInitialThing()).getReferencePoint();
				Point initialMousePoint = evt.getInitialLocation().getWorldPoint();
				Point delta = new Point(initialMousePoint.x - initialRelativeMovablePoint.x, initialMousePoint.y
						- initialRelativeMovablePoint.y);
				referencePointToInitialMousePointDelta.x = delta.x;
				referencePointToInitialMousePointDelta.y = delta.y;
			}
		}

		Point adjustedThingWorldPoint = evt.getAdjustedThingLocation().getWorldPoint();
		Point adjustedMouseWorldPoint = evt.getAdjustedMouseLocation().getWorldPoint();

		if ((evt.getEvt().stateMask & SWT.MOD3) == 0) {
			int gridSpacing = GridUtils.getGridSpacing(world);
			if (gridSpacing != 0) {
				// adjust the mouse point to the relative movable point 
				adjustedThingWorldPoint.x -= referencePointToInitialMousePointDelta.x;
				adjustedThingWorldPoint.y -= referencePointToInitialMousePointDelta.y;
				// snap it to the grid
				adjustedThingWorldPoint = GridUtils.snapToGrid(gridSpacing, adjustedThingWorldPoint);
				// adjust it back to the mouse point
				adjustedThingWorldPoint.x += referencePointToInitialMousePointDelta.x;
				adjustedThingWorldPoint.y += referencePointToInitialMousePointDelta.y;
				// adjust the absolute mouse point
				adjustedMouseWorldPoint = GridUtils.snapToGrid(gridSpacing, adjustedMouseWorldPoint);
			}
		}

		evt.setAdjustedThingLocation(new DefaultCoordinate(evt.getView().getCoordinateMapper()
				.worldToLocal(adjustedThingWorldPoint), adjustedThingWorldPoint));
		evt.setAdjustedMouseLocation(new DefaultCoordinate(evt.getView().getCoordinateMapper()
				.worldToLocal(adjustedMouseWorldPoint), adjustedMouseWorldPoint));
	}

	@Override
	synchronized public void dragMoved(DragMoveEvent evt) {
		Point adjustedThingWorldPoint = evt.getAdjustedThingLocation().getWorldPoint();
		Point adjustedMouseWorldPoint = evt.getAdjustedMouseLocation().getWorldPoint();

		if ((evt.getEvt().stateMask & SWT.MOD3) == 0) {
			int gridSpacing = GridUtils.getGridSpacing(world);
			if (gridSpacing != 0) {
				// adjust the mouse point to the relative movable point 
				adjustedThingWorldPoint.x -= referencePointToInitialMousePointDelta.x;
				adjustedThingWorldPoint.y -= referencePointToInitialMousePointDelta.y;
				// snap it to the grid
				adjustedThingWorldPoint = GridUtils.snapToGrid(gridSpacing, adjustedThingWorldPoint);
				// adjust it back to the mouse point
				adjustedThingWorldPoint.x += referencePointToInitialMousePointDelta.x;
				adjustedThingWorldPoint.y += referencePointToInitialMousePointDelta.y;
				// adjust the absolute mouse point
				adjustedMouseWorldPoint = GridUtils.snapToGrid(gridSpacing, adjustedMouseWorldPoint);
			}
		}

		evt.setAdjustedThingLocation(new DefaultCoordinate(evt.getView().getCoordinateMapper()
				.worldToLocal(adjustedThingWorldPoint), adjustedThingWorldPoint));
		evt.setAdjustedMouseLocation(new DefaultCoordinate(evt.getView().getCoordinateMapper()
				.worldToLocal(adjustedMouseWorldPoint), adjustedMouseWorldPoint));
	}

	@Override
	synchronized public void dragFinished(DragMoveEvent evt) {
	}

}
