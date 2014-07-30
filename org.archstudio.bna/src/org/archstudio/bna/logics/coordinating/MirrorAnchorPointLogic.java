package org.archstudio.bna.logics.coordinating;

import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;

import com.google.common.base.Function;

public class MirrorAnchorPointLogic extends AbstractThingLogic {

	protected final MirrorValueLogic mirrorLogic;

	public MirrorAnchorPointLogic(IBNAWorld world) {
		super(world);
		mirrorLogic = logics.addThingLogic(MirrorValueLogic.class);
	}

	public void mirrorAnchorPoint(IHasAnchorPoint fromThing, IHasAnchorPoint toThing, final Point deltaPoint) {
		BNAUtils.checkLock();

		mirrorLogic.mirrorValue(fromThing, IHasAnchorPoint.ANCHOR_POINT_KEY, toThing, IHasAnchorPoint.ANCHOR_POINT_KEY,
				new Function<Point2D, Point2D>() {

					@Override
					public Point2D apply(Point2D input) {
						return new Point2D.Double(input.getX() + deltaPoint.x, input.getX() + deltaPoint.y);
					}
				});
	}

	public void unmirrorAnchorPoint(IHasAnchorPoint toThing) {
		BNAUtils.checkLock();

		mirrorLogic.unmirror(toThing, IHasAnchorPoint.ANCHOR_POINT_KEY);
	}

}
