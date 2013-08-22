package org.archstudio.archipelago.statechart.core.logics;

import java.util.List;

import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasHorizontalAlignment;
import org.archstudio.bna.facets.IHasPoints;
import org.archstudio.bna.facets.IHasText;
import org.archstudio.bna.facets.IHasVerticalAlignment;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.coordinating.MirrorValueLogic;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.eclipse.swt.graphics.Point;

import com.google.common.base.Function;

public class OrientTextLogic extends AbstractThingLogic {

	public OrientTextLogic() {
	}

	public void orientText(IHasEndpoints pointsThing, final IHasText orientationThing) {

		MirrorValueLogic mvl = getBNAWorld().getThingLogicManager().addThingLogic(MirrorValueLogic.class);

		mvl.mirrorValue(pointsThing, IHasPoints.POINTS_KEY, orientationThing,
				IHasHorizontalAlignment.HORIZONTAL_ALIGNMENT_KEY, new Function<List<Point>, HorizontalAlignment>() {
					@Override
					public HorizontalAlignment apply(List<Point> points) {
						Point p1 = points.get(0);
						Point p2 = points.get(points.size() - 1);
						double dx = p2.x - p1.x;
						double dy = p2.y - p1.y;
						double angle = Math.atan2(dy, dx);
						if (angle > Math.PI / 8 && angle < Math.PI * 7 / 8) {
							return HorizontalAlignment.RIGHT;
						}
						if (-angle > Math.PI / 8 && -angle < Math.PI * 7 / 8) {
							return HorizontalAlignment.LEFT;
						}
						return HorizontalAlignment.CENTER;
					}
				});

		mvl.mirrorValue(pointsThing, IHasPoints.POINTS_KEY, orientationThing,
				IHasVerticalAlignment.VERTICAL_ALIGNMENT_KEY, new Function<List<Point>, VerticalAlignment>() {
					@Override
					public VerticalAlignment apply(List<Point> points) {
						Point p1 = points.get(0);
						Point p2 = points.get(points.size() - 1);
						double dx = p2.x - p1.x;
						double dy = p2.y - p1.y;
						double angle = Math.atan2(dx, dy);
						if (angle > Math.PI / 8 && angle < Math.PI * 7 / 8) {
							return VerticalAlignment.TOP;
						}
						if (-angle > Math.PI / 8 && -angle < Math.PI * 7 / 8) {
							return VerticalAlignment.BOTTOM;
						}
						return VerticalAlignment.MIDDLE;
					}
				});
	}

}
