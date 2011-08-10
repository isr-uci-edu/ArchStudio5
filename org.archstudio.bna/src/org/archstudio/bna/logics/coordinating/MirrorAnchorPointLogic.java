package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.PrecisionPoint;

import com.google.common.base.Function;

public class MirrorAnchorPointLogic extends AbstractMirrorValueLogic<IHasAnchorPoint, IHasMutableAnchorPoint> {

	public MirrorAnchorPointLogic() {
		super(IHasAnchorPoint.class, IHasMutableAnchorPoint.class);
	}

	public void mirrorAnchorPoint(IHasAnchorPoint fromThing, IHasMutableAnchorPoint toThing, final Point deltaPoint) {
		mirrorValue(fromThing, IHasAnchorPoint.ANCHOR_POINT_KEY, toThing, IHasAnchorPoint.ANCHOR_POINT_KEY,
				new Function<Point, Point>() {
					@Override
					public Point apply(Point input) {
						return new PrecisionPoint(input.preciseX() + deltaPoint.preciseX(), input.preciseY()
								+ deltaPoint.preciseY());
					}
				});
	}

	public void unmirrorAnchorPoint(IHasMutableAnchorPoint toThing) {
		unpropagate(toThing, IHasAnchorPoint.ANCHOR_POINT_KEY);
	}

}
