package org.archstudio.bna.logics.editing;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAComposite;
import org.archstudio.bna.IBNAMouseListener;
import org.archstudio.bna.IBNAMouseMoveListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasStandardCursor;

public class StandardCursorLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMouseMoveListener {

	boolean isDown = false;
	boolean downOnCursor = false;

	public void mouseDown(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		isDown = true;
		if (t instanceof IHasStandardCursor) {
			downOnCursor = true;
		}
	}

	public void mouseUp(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		isDown = false;
		if (downOnCursor) {
			Object src = evt.getSource();
			if ((src != null) && (src instanceof BNAComposite)) {
				BNAComposite bnaComposite = (BNAComposite) src;
				bnaComposite.setCursor(null);
			}
		}
		downOnCursor = false;
	}

	public void mouseClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseMove(IBNAView view, MouseEvent e, IThing t, int worldX, int worldY) {
		if (!isDown) {
			Object src = e.getSource();
			if ((src != null) && (src instanceof BNAComposite)) {
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
				if ((bnaComposite != null) && (!bnaComposite.isDisposed())) {
					bnaComposite.setCursor(null);
					return;
				}
			}
		}
	}
}
