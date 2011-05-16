package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasStandardCursor;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAComposite;
import org.archstudio.bna.utils.IBNAMouseListener;
import org.archstudio.bna.utils.IBNAMouseMoveListener;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;

public class StandardCursorLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMouseMoveListener {

	boolean isDown = false;
	boolean downOnCursor = false;

	public void mouseDown(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
		isDown = true;
		if (t instanceof IHasStandardCursor) {
			downOnCursor = true;
		}
	}

	public void mouseUp(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
		isDown = false;
		if (downOnCursor) {
			Object src = evt.getSource();
			if (src != null && src instanceof BNAComposite) {
				BNAComposite bnaComposite = (BNAComposite) src;
				bnaComposite.setCursor(null);
			}
		}
		downOnCursor = false;
	}

	public void mouseClick(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, Iterable<IThing> t, Point localPoint, Point worldPoint) {
	}

	public void mouseMove(IBNAView view, MouseEvent e, IThing t, int worldX, int worldY) {
		if (!isDown) {
			Object src = e.getSource();
			if (src != null && src instanceof BNAComposite) {
				BNAComposite bnaComposite = (BNAComposite) src;
				if (t instanceof IHasStandardCursor) {
					IHasStandardCursor sct = (IHasStandardCursor) t;
					int cursor = sct.getStandardCursor();
					if (cursor == SWT.NONE) {
						bnaComposite.setCursor(null);
						return;
					}
					else {
						bnaComposite.setCursor(e.display.getSystemCursor(cursor));
						return;
					}
				}
				if (bnaComposite != null && !bnaComposite.isDisposed()) {
					bnaComposite.setCursor(null);
					return;
				}
			}
		}
	}
}
