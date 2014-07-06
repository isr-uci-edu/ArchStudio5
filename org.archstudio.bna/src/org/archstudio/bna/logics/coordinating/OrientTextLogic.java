package org.archstudio.bna.logics.coordinating;

import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasHorizontalAlignment;
import org.archstudio.bna.facets.IHasVerticalAlignment;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;

import com.google.common.base.Function;

public class OrientTextLogic extends AbstractThingLogic {

	protected final MirrorValueLogic mirrorLogic;

	public OrientTextLogic(IBNAWorld world) {
		super(world);
		mirrorLogic = logics.addThingLogic(MirrorValueLogic.class);
	}

	synchronized public <T extends IHasHorizontalAlignment & IHasVerticalAlignment> void orientText(
			final IHasEndpoints pointsThing, final T orientationThing) {

		Function<Object, HorizontalAlignment> horizontalFunction = new Function<Object, HorizontalAlignment>() {
			@Override
			public HorizontalAlignment apply(Object ignored) {
				Point2D p1 = pointsThing.getEndpoint1();
				Point2D p2 = pointsThing.getEndpoint2();
				double dx = p2.getX() - p1.getX();
				double dy = p2.getY() - p1.getY();
				double angle = Math.atan2(dy, dx);
				if (angle > Math.PI / 8 && angle < Math.PI * 7 / 8) {
					return HorizontalAlignment.LEFT;
				}
				if (-angle > Math.PI / 8 && -angle < Math.PI * 7 / 8) {
					return HorizontalAlignment.RIGHT;
				}
				return HorizontalAlignment.CENTER;
			}
		};
		Function<Object, VerticalAlignment> verticalFunction = new Function<Object, VerticalAlignment>() {
			@Override
			public VerticalAlignment apply(Object ignored) {
				Point2D p1 = pointsThing.getEndpoint1();
				Point2D p2 = pointsThing.getEndpoint2();
				double dx = p2.getX() - p1.getX();
				double dy = p2.getY() - p1.getY();
				double angle = Math.atan2(dx, dy);
				if (angle > Math.PI / 8 && angle < Math.PI * 7 / 8) {
					return VerticalAlignment.BOTTOM;
				}
				if (-angle > Math.PI / 8 && -angle < Math.PI * 7 / 8) {
					return VerticalAlignment.TOP;
				}
				return VerticalAlignment.MIDDLE;
			}
		};

		mirrorLogic.mirrorValue(pointsThing, IHasEndpoints.ENDPOINT_1_KEY, orientationThing,
				IHasHorizontalAlignment.HORIZONTAL_ALIGNMENT_KEY, horizontalFunction);
		mirrorLogic.mirrorValue(pointsThing, IHasEndpoints.ENDPOINT_2_KEY, orientationThing,
				IHasHorizontalAlignment.HORIZONTAL_ALIGNMENT_KEY, horizontalFunction);
		mirrorLogic.mirrorValue(pointsThing, IHasEndpoints.ENDPOINT_1_KEY, orientationThing,
				IHasVerticalAlignment.VERTICAL_ALIGNMENT_KEY, verticalFunction);
		mirrorLogic.mirrorValue(pointsThing, IHasEndpoints.ENDPOINT_2_KEY, orientationThing,
				IHasVerticalAlignment.VERTICAL_ALIGNMENT_KEY, verticalFunction);
	}
}
