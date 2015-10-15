package org.archstudio.bna.logics.information;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.MouseType;
import org.archstudio.bna.facets.IHasToolTip;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAAllEventsListener2;
import org.archstudio.bna.ui.IBNAMouseMoveListener2;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Control;

public class ToolTipLogic extends AbstractThingLogic implements IBNAMouseMoveListener2, IBNAAllEventsListener2 {

	public static final void setToolTip(IThing thing, String toolTip) {
		thing.set(IHasToolTip.TOOL_TIP_KEY, toolTip);
	}

	public static final String getToolTip(IThing thing) {
		return thing == null ? null : thing.get(IHasToolTip.TOOL_TIP_KEY);
	}

	protected String lastToolTip = null;

	public ToolTipLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void mouseMove(IBNAView view, MouseType type, MouseEvent evt, ICoordinate location,
			ThingsAtLocation thingsAtLocation) {
		BNAUtils.checkLock();

		// only handle events for the top world
		if (view.getParentView() != null) {
			return;
		}

		String toolTip = null;
		if (thingsAtLocation.getBackgroundThingAtLocation() != null) {
			IThing tooltipThing = Assemblies.getThingWithProperty(
					thingsAtLocation.getBackgroundThingAtLocation().getView().getBNAWorld().getBNAModel(),
					thingsAtLocation.getBackgroundThingAtLocation().getThing(), IHasToolTip.TOOL_TIP_KEY);
			if (tooltipThing != null) {
				toolTip = tooltipThing.get(IHasToolTip.TOOL_TIP_KEY);
			}
		}
		if (!SystemUtils.nullEquals(toolTip, lastToolTip)) {
			lastToolTip = toolTip;
			Control c = view.getBNAUI().getComposite();
			if (!SystemUtils.nullEquals(toolTip, c.getToolTipText())) {
				c.setToolTipText(toolTip);
			}
		}
	}
}
