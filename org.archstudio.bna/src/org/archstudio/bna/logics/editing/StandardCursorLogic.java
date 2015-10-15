package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasStandardCursor;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAAllEventsListener2;
import org.archstudio.bna.ui.IBNAMouseClickListener2;
import org.archstudio.bna.ui.IBNAMouseMoveListener2;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;

public class StandardCursorLogic extends AbstractThingLogic
		implements IBNAMouseClickListener2, IBNAMouseMoveListener2, IBNAAllEventsListener2 {

	boolean isDown = false;
	boolean downOnCursor = false;

	public StandardCursorLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void mouseDown(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation) {
		BNAUtils.checkLock();

		// only handle events for the top world
		if (view.getParentView() != null) {
			return;
		}

		isDown = true;
	}

	@Override
	public void mouseUp(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation) {
		BNAUtils.checkLock();

		// only handle events for the top world
		if (view.getParentView() != null) {
			return;
		}

		isDown = false;
		updateCursor(view, evt, location, thingsAtLocation);
	}

	@Override
	public void mouseClick(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation) {
	}

	@Override
	public void mouseMove(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation) {
		BNAUtils.checkLock();

		// only handle events for the top world
		if (view.getParentView() != null) {
			return;
		}

		if (!isDown) {
			updateCursor(view, evt, location, thingsAtLocation);
		}
	}

	protected void updateCursor(IBNAView view, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation) {
		int cursor = SWT.NONE;
		if (thingsAtLocation.getThing() != null) {
			IThing cursorThing = Assemblies.getThingWithProperty(thingsAtLocation.getThingAtLocation().getModel(),
					thingsAtLocation.getThingAtLocation().getThing(), IHasStandardCursor.STANDARD_CURSOR_KEY);
			if (cursorThing != null) {
				cursor = cursorThing.get(IHasStandardCursor.STANDARD_CURSOR_KEY);
			}
		}
		if (cursor == SWT.NONE) {
			view.getBNAUI().getComposite().setCursor(null);
		}
		else {
			view.getBNAUI().getComposite().setCursor(evt.display.getSystemCursor(cursor));
		}
	}
}
