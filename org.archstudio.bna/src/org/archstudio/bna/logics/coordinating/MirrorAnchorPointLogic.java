package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.eclipse.swt.graphics.Point;

import com.google.common.base.Function;

public class MirrorAnchorPointLogic extends AbstractThingLogic {

	protected final MirrorValueLogic mirrorLogic;

	public MirrorAnchorPointLogic(IBNAWorld world) {
		super(world);
		mirrorLogic = logics.addThingLogic(MirrorValueLogic.class);
	}

	synchronized public void mirrorAnchorPoint(IHasAnchorPoint fromThing, IHasMutableAnchorPoint toThing,
			final Point deltaPoint) {
		mirrorLogic.mirrorValue(fromThing, IHasAnchorPoint.ANCHOR_POINT_KEY, toThing, IHasAnchorPoint.ANCHOR_POINT_KEY,
				new Function<Point, Point>() {

					@Override
					public Point apply(Point input) {
						return new Point(input.x + deltaPoint.x, input.y + deltaPoint.y);
					}
				});
	}

	synchronized public void unmirrorAnchorPoint(IHasMutableAnchorPoint toThing) {
		mirrorLogic.unmirror(toThing, IHasAnchorPoint.ANCHOR_POINT_KEY);
	}

}
