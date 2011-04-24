package org.archstudio.bna.logics.information;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAMouseMoveListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasToolTip;

public class ToolTipLogic extends AbstractThingLogic implements IBNAMouseMoveListener {

	protected IThing lastThing = null;

	public void mouseMove(IBNAView view, MouseEvent e, IThing t, int worldX, int worldY) {
		if (t == lastThing) {
			return;
		}
		lastThing = t;

		Composite c = BNAUtils.getParentComposite(view);
		if (c != null) {
			if (t instanceof IHasToolTip) {
				String toolTip = ((IHasToolTip) t).getToolTip();
				if (toolTip != null) {
					String existingToolTipText = c.getToolTipText();
					if (BNAUtils.nulleq(existingToolTipText, toolTip)) {
						return;
					}
					c.setToolTipText(toolTip);
					return;
				}
			}
			c.setToolTipText(null);
		}
	}
}
