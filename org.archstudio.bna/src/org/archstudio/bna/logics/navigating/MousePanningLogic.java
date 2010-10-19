package org.archstudio.bna.logics.navigating;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Cursor;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAComposite;
import org.archstudio.bna.IBNAMouseListener;
import org.archstudio.bna.IBNAMouseMoveListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IThing;

public class MousePanningLogic extends AbstractThingLogic implements IBNAMouseListener, IBNAMouseMoveListener {
	public static final int DEFAULT_PAN_BUTTON = 2;

	protected int panButton = DEFAULT_PAN_BUTTON;

	protected boolean buttonDown = false;
	protected int lastX = 0;
	protected int lastY = 0;

	protected Cursor handCursor = null;

	public MousePanningLogic() {
		this(DEFAULT_PAN_BUTTON);
	}

	public MousePanningLogic(int panButton) {
		this.panButton = panButton;
	}

	public void mouseDown(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		//Only handle events for the top world
		if (view.getParentComposite() == null)
			return;

		if (evt.button == panButton) {
			buttonDown = true;
			lastX = evt.x;
			lastY = evt.y;
			Object src = evt.getSource();
			if ((src != null) && (src instanceof BNAComposite)) {
				BNAComposite bnaComposite = (BNAComposite) src;
				Cursor handCursor = evt.display.getSystemCursor(SWT.CURSOR_HAND);
				bnaComposite.setCursor(handCursor);
			}
		}
	}

	public void mouseUp(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
		//Only handle events for the top world
		if (view.getParentComposite() == null)
			return;

		if (evt.button == panButton) {
			buttonDown = false;
			Object src = evt.getSource();
			if ((src != null) && (src instanceof BNAComposite)) {
				BNAComposite bnaComposite = (BNAComposite) src;
				bnaComposite.setCursor(null);
			}
		}
	}

	public void mouseMove(IBNAView view, MouseEvent e, IThing t, int worldX, int worldY) {
		//Only handle events for the top world
		if (view.getParentComposite() == null)
			return;

		if (!buttonDown)
			return;

		ICoordinateMapper cm = view.getCoordinateMapper();

		int dwx = cm.localXtoWorldX(e.x) - cm.localXtoWorldX(lastX);
		int dwy = cm.localYtoWorldY(e.y) - cm.localYtoWorldY(lastY);

		if (cm instanceof IMutableCoordinateMapper) {
			((IMutableCoordinateMapper) cm).repositionRelative(-dwx, -dwy);
		}

		lastX = e.x;
		lastY = e.y;
	}

	public void mouseClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}

	public void mouseDoubleClick(IBNAView view, MouseEvent evt, IThing t, int worldX, int worldY) {
	}
}
