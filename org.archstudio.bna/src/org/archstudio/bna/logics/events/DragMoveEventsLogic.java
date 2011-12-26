package org.archstudio.bna.logics.events;

import java.util.List;

import org.archstudio.bna.DefaultCoordinate;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.archstudio.bna.utils.IBNAMouseMoveListener;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Point;

public class DragMoveEventsLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMouseMoveListener {

	DragMoveEvent currentEvent = null;

	public DragMoveEventsLogic() {
	}

	@Override
	public void mouseDown(IBNAView view, MouseEvent evt, List<IThing> t, ICoordinate location) {
		if (evt.button == 1 && (evt.stateMask & SWT.MODIFIER_MASK) == 0) {
			IRelativeMovable relativeMovableThing = SystemUtils.firstOrNull(t, IRelativeMovable.class);
			if (relativeMovableThing != null
					&& UserEditableUtils
							.isEditableForAllQualities(relativeMovableThing, IRelativeMovable.USER_MAY_MOVE)) {
				view.getComposite().setCursor(view.getComposite().getDisplay().getSystemCursor(SWT.CURSOR_HAND));
				fireDragStartedEvent(currentEvent = new DragMoveEvent(view, evt, relativeMovableThing,
						DefaultCoordinate.forLocal(new Point(evt.x, evt.y), view.getCoordinateMapper())));

			}
		}
	}

	@Override
	public void mouseMove(IBNAView view, MouseEvent evt, List<IThing> t, ICoordinate location) {
		if (currentEvent != null) {
			fireDragMoveEvent(currentEvent = new DragMoveEvent(currentEvent, evt, DefaultCoordinate.forLocal(new Point(
					evt.x, evt.y), view.getCoordinateMapper())));
		}
	}

	@Override
	public void mouseUp(IBNAView view, MouseEvent evt, List<IThing> t, ICoordinate location) {
		if (currentEvent != null) {
			view.getComposite().setCursor(view.getComposite().getDisplay().getSystemCursor(SWT.CURSOR_ARROW));
			fireDragFinishedEvent(currentEvent = new DragMoveEvent(currentEvent, evt, DefaultCoordinate.forLocal(
					new Point(evt.x, evt.y), view.getCoordinateMapper())));
			currentEvent = null;
		}
	}

	protected void fireDragStartedEvent(DragMoveEvent evt) {
		for (IDragMoveListener logic : getBNAWorld().getThingLogicManager().getThingLogics(IDragMoveListener.class)) {
			logic.dragStarted(evt);
		}
	}

	protected void fireDragMoveEvent(DragMoveEvent evt) {
		for (IDragMoveListener logic : getBNAWorld().getThingLogicManager().getThingLogics(IDragMoveListener.class)) {
			logic.dragMoved(evt);
		}
	}

	protected void fireDragFinishedEvent(DragMoveEvent evt) {
		for (IDragMoveListener logic : getBNAWorld().getThingLogicManager().getThingLogics(IDragMoveListener.class)) {
			logic.dragFinished(evt);
		}
	}
}
