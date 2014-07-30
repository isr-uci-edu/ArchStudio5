package org.archstudio.bna.logics.events;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasReferencePoint;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMouseListener;
import org.archstudio.bna.ui.IBNAMouseMoveListener;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.DefaultCoordinate;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;

public class DragMoveEventsLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMouseMoveListener {

	DragMoveEvent currentEvent = null;

	public DragMoveEventsLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void mouseDown(IBNAView view, MouseType type, MouseEvent evt, List<IThing> t, ICoordinate location) {
		BNAUtils.checkLock();

		if (evt.button == 1 && (evt.stateMask & SWT.MODIFIER_MASK) == 0) {
			IHasReferencePoint relativeMovableThing = Assemblies.getEditableThing(model, firstOrNull(t),
					IHasReferencePoint.class, IHasMutableReferencePoint.USER_MAY_MOVE);
			if (relativeMovableThing != null) {
				Composite composite = view.getBNAUI().getComposite();
				composite.setCursor(composite.getDisplay().getSystemCursor(SWT.CURSOR_HAND));
				fireDragStartedEvent(currentEvent = new DragMoveEvent(view, evt, relativeMovableThing,
						DefaultCoordinate.forLocal(new Point(evt.x, evt.y), view.getCoordinateMapper())));
			}
		}
	}

	@Override
	public void mouseMove(IBNAView view, MouseType type, MouseEvent evt, List<IThing> t, ICoordinate location) {
		BNAUtils.checkLock();

		if (currentEvent != null) {
			fireDragMoveEvent(currentEvent = new DragMoveEvent(currentEvent, evt, DefaultCoordinate.forLocal(new Point(
					evt.x, evt.y), view.getCoordinateMapper())));
		}
	}

	@Override
	public void mouseUp(IBNAView view, MouseType type, MouseEvent evt, List<IThing> t, ICoordinate location) {
		BNAUtils.checkLock();

		if (currentEvent != null) {
			Composite composite = view.getBNAUI().getComposite();
			composite.setCursor(composite.getDisplay().getSystemCursor(SWT.CURSOR_ARROW));
			fireDragFinishedEvent(currentEvent = new DragMoveEvent(currentEvent, evt, DefaultCoordinate.forLocal(
					new Point(evt.x, evt.y), view.getCoordinateMapper())));
			currentEvent = null;
		}
	}

	protected void fireDragStartedEvent(DragMoveEvent evt) {
		for (IDragMoveListener logic : logics.getThingLogics(IDragMoveListener.class)) {
			logic.dragStarted(evt);
		}
	}

	protected void fireDragMoveEvent(DragMoveEvent evt) {
		for (IDragMoveListener logic : logics.getThingLogics(IDragMoveListener.class)) {
			logic.dragMoved(evt);
		}
	}

	protected void fireDragFinishedEvent(DragMoveEvent evt) {
		for (IDragMoveListener logic : logics.getThingLogics(IDragMoveListener.class)) {
			logic.dragFinished(evt);
		}
	}
}
