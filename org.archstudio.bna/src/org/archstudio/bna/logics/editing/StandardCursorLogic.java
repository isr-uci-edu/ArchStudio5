package org.archstudio.bna.logics.editing;

import static org.archstudio.sysutils.SystemUtils.firstOrNull;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasStandardCursor;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMouseListener;
import org.archstudio.bna.ui.IBNAMouseMoveListener;
import org.archstudio.bna.utils.Assemblies;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Control;

public class StandardCursorLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMouseMoveListener {

	boolean isDown = false;
	boolean downOnCursor = false;

	public StandardCursorLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	synchronized public void mouseDown(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things,
			ICoordinate location) {
		isDown = true;
	}

	@Override
	synchronized public void mouseUp(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things,
			ICoordinate location) {
		isDown = false;
		updateCursor(view, evt, things, location);
	}

	@Override
	synchronized public void mouseMove(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things,
			ICoordinate location) {
		if (!isDown) {
			updateCursor(view, evt, things, location);
		}
	}

	protected void updateCursor(IBNAView view, MouseEvent evt, List<IThing> things, ICoordinate location) {
		IThing thing = firstOrNull(things);
		if (thing instanceof IHasWorld && ((IHasWorld) thing).getWorld() != null) {
			// let sub world logics deal with it
			return;
		}
		int cursor = SWT.NONE;
		Object src = evt.getSource();
		if (src != null && src instanceof Control) {
			Control control = (Control) src;
			if (control != null && !control.isDisposed()) {
				IThing cursorThing = Assemblies.getThingWithProperty(model, firstOrNull(things),
						IHasStandardCursor.STANDARD_CURSOR_KEY);
				if (cursorThing != null) {
					cursor = cursorThing.get(IHasStandardCursor.STANDARD_CURSOR_KEY);
				}
				if (cursor == SWT.NONE) {
					control.setCursor(null);
				}
				else {
					control.setCursor(evt.display.getSystemCursor(cursor));
				}
			}
		}
	}
}
