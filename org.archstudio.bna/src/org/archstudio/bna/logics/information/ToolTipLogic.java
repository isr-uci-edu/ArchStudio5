package org.archstudio.bna.logics.information;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAMouseMoveListener;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Control;

public class ToolTipLogic extends AbstractThingLogic implements IBNAMouseMoveListener {

	public static final void setToolTip(IThing thing, String toolTip) {
		thing.set(IHasToolTip.TOOL_TIP_KEY, toolTip);
	}

	public static final String getToolTip(IThing thing) {
		return thing == null ? null : thing.get(IHasToolTip.TOOL_TIP_KEY);
	}

	protected IThing lastThing = null;

	public ToolTipLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	synchronized public void mouseMove(IBNAView view, MouseType type, MouseEvent evt, List<IThing> things,
			ICoordinate location) {
		IThing newThing = SystemUtils.firstOrNull(things);
		if (newThing != lastThing) {
			lastThing = newThing;
			String toolTip = ToolTipLogic.getToolTip(newThing);
			Control c = view.getBNAUI().getComposite();
			if (!SystemUtils.nullEquals(toolTip, c.getToolTipText())) {
				c.setToolTipText(toolTip);
			}
		}
	}
}
