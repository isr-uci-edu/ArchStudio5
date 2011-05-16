package org.archstudio.bna.logics.information;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.IBNAMouseMoveListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;

public class ToolTipLogic extends AbstractThingLogic implements IBNAMouseMoveListener {

	public static final String USER_MAY_EDIT_TOOL_TIP = "userMayEditToolTip";

	public static final ThingKey<String, V> TOOL_TIP_KEY = ThingKey.createKey("toolTip");

	public static final void setToolTip(IThing thing, String toolTip) {
		thing.setProperty(TOOL_TIP_KEY, toolTip);
	}

	public static final String getToolTip(IThing thing) {
		return thing == null ? null : (String) thing.getProperty(TOOL_TIP_KEY);
	}

	protected IThing lastThing = null;

	public void mouseMove(IBNAView view, MouseEvent e, IThing t, int worldX, int worldY) {
		if (t != lastThing) {
			lastThing = t;
			Composite c = BNAUtils.getParentComposite(view);
			if (c != null) {
				String toolTip = ToolTipLogic.getToolTip(t);
				if (!BNAUtils.nulleq(toolTip, c.getToolTipText())) {
					c.setToolTipText(toolTip);
				}
			}
		}
	}
}
