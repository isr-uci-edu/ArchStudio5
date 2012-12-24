package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.eclipse.swt.graphics.Point;

import com.google.common.base.Function;

public class MirrorAnchorPointLogic extends AbstractThingLogic {

	private MirrorValueLogic mvl;

	public MirrorAnchorPointLogic() {
	}

	@Override
	protected void init() {
		super.init();
		mvl = addThingLogic(MirrorValueLogic.class);
	}

	public void mirrorAnchorPoint(IHasAnchorPoint fromThing, IHasMutableAnchorPoint toThing, final Point deltaPoint) {
		mvl.mirrorValue(fromThing, IHasAnchorPoint.ANCHOR_POINT_KEY, toThing, IHasAnchorPoint.ANCHOR_POINT_KEY,
				new Function<Point, Point>() {

					@Override
					public Point apply(Point input) {
						return new Point(input.x + deltaPoint.x, input.y + deltaPoint.y);
					}
				});
	}

	public void unmirrorAnchorPoint(IHasMutableAnchorPoint toThing) {
		mvl.unmirror(toThing, IHasAnchorPoint.ANCHOR_POINT_KEY);
	}

}
