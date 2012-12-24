package org.archstudio.bna.logics.navigating;

import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.IMutableCoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ModelBoundsTrackingLogic;
import org.archstudio.bna.utils.IBNAMenuListener;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public class ViewAllLogic extends AbstractThingLogic implements IBNAMenuListener {

	public ViewAllLogic() {
	}

	public void fillMenu(final IBNAView view, List<IThing> things, final ICoordinate location, IMenuManager menu) {
		if (things.size() == 0) {
			menu.add(new Action("View All") {

				public void run() {
					viewAll(view);
				}
			});
		}
	}

	public static void viewAll(IBNAView view) {
		ModelBoundsTrackingLogic mbtl = view.getBNAWorld().getThingLogicManager()
				.addThingLogic(ModelBoundsTrackingLogic.class);
		IMutableCoordinateMapper cm = (IMutableCoordinateMapper) view.getCoordinateMapper();
		Rectangle m = mbtl.getModelBounds();
		org.eclipse.swt.graphics.Rectangle v = view.getComposite().getClientArea();
		v.x = v.y = 0;
		Point mc = new Point(m.x + m.width / 2, m.y + m.height / 2);
		Point vc = new Point(v.x + v.width / 2, v.y + v.height / 2);
		double s = Math.min(1, Math.min((double) v.width / m.width, (double) v.height / m.height));
		cm.setLocalScaleAndAlign(s, vc, mc);
	}

}
