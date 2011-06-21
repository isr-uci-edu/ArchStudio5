package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasStandardCursor;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.archstudio.bna.utils.IBNAMouseMoveListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Control;

public class StandardCursorLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMouseMoveListener {

	boolean isDown = false;
	boolean downOnCursor = false;

	@Override
	public void mouseDown(IBNAView view, MouseEvent evt, Iterable<IThing> t, ICoordinate location) {
		isDown = true;
		if (t instanceof IHasStandardCursor) {
			downOnCursor = true;
		}
	}

	@Override
	public void mouseUp(IBNAView view, MouseEvent evt, Iterable<IThing> t, ICoordinate location) {
		isDown = false;
		if (downOnCursor) {
			Object src = evt.getSource();
			if (src != null && src instanceof Control) {
				Control control = (Control) src;
				control.setCursor(null);
			}
		}
		downOnCursor = false;
	}

	@Override
	public void mouseMove(IBNAView view, MouseEvent evt, Iterable<IThing> things, ICoordinate location) {
		if (!isDown) {
			Object src = evt.getSource();
			if (src != null && src instanceof Control) {
				Control control = (Control) src;
				for (IThing t : things) {
					if (t instanceof IHasStandardCursor) {
						IHasStandardCursor sct = (IHasStandardCursor) t;
						int cursor = sct.getStandardCursor();
						if (cursor == SWT.NONE) {
							control.setCursor(null);
						}
						else {
							control.setCursor(evt.display.getSystemCursor(cursor));
						}
						break;
					}
					if (control != null && !control.isDisposed()) {
						control.setCursor(null);
						return;
					}
				}
			}
		}
	}
}
