package org.archstudio.bna.logics.navigating;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.ui.IBNAAllEventsListener2;
import org.archstudio.bna.ui.IBNAMenuListener2;
import org.archstudio.bna.utils.BNAAction;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.BNAUtils2.ThingsAtLocation;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class ViewAllLogic extends AbstractThingLogic implements IBNAMenuListener2, IBNAAllEventsListener2 {

	public static void viewAll(IBNAView view) {
		ModelBoundsTrackingLogic mbtl =
				view.getBNAWorld().getThingLogicManager().addThingLogic(ModelBoundsTrackingLogic.class);
		IMutableCoordinateMapper cm = (IMutableCoordinateMapper) view.getCoordinateMapper();
		Rectangle m = mbtl.getModelBounds();
		org.eclipse.swt.graphics.Rectangle v = view.getBNAUI().getComposite().getClientArea();
		v.x = v.y = 0;
		Point mc = new Point(m.x + m.width / 2, m.y + m.height / 2);
		Point vc = new Point(v.x + v.width / 2, v.y + v.height / 2);
		double s = Math.min(1, Math.min((double) v.width / m.width, (double) v.height / m.height));
		cm.setLocalScaleAndAlign(s, vc, mc);
	}

	public ViewAllLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void fillMenu(final IBNAView view, ICoordinate location, ThingsAtLocation thingsAtLocation,
			IMenuManager menu) {
		BNAUtils.checkLock();
		if (view.getParentView() == null) {
			menu.add(new BNAAction("View All") {

				@Override
				public void runWithLock() {
					viewAll(view);
				}
			});
		}
	}

}
