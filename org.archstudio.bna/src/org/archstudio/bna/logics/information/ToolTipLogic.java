package org.archstudio.bna.logics.information;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.IBNAMouseMoveListener;
import org.eclipse.swt.events.MouseEvent;

public class ToolTipLogic extends AbstractThingLogic implements IBNAMouseMoveListener {

	public static final String USER_MAY_EDIT_TOOL_TIP = "userMayEditToolTip";

	public static final IThingKey<String> TOOL_TIP_KEY = ThingKey.create("toolTip");

	public static final void setToolTip(IThing thing, String toolTip) {
		thing.set(TOOL_TIP_KEY, toolTip);
	}

	public static final String getToolTip(IThing thing) {
		return thing == null ? null : thing.get(TOOL_TIP_KEY);
	}

	protected IThing lastThing = null;

	@Override
	public void mouseMove(IBNAView view, MouseEvent evt, Iterable<IThing> things, ICoordinate location) {
		//if (t != lastThing) {
		//	lastThing = t;
		//	Composite c = BNAUtils.getParentComposite(view);
		//	if (c != null) {
		//		String toolTip = ToolTipLogic.getToolTip(t);
		//		if (!BNAUtils.nulleq(toolTip, c.getToolTipText())) {
		//			c.setToolTipText(toolTip);
		//		}
		//	}
		//}
	}
}
