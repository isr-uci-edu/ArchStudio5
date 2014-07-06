package org.archstudio.bna.things.labels;

import java.awt.geom.Point2D;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.facets.IHasIndicatorPoint;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public class AnchoredLabelThing extends AnchoredLabelThingBase {

	public AnchoredLabelThing(@Nullable Object id) {
		super(id);
	}

	@Override
	public Point getReferencePoint() {
		return BNAUtils.toPoint(getAnchorPoint());
	}

	@Override
	public void setReferencePoint(Point value) {
		setAnchorPoint(BNAUtils.toPoint2D(value));
	}

	@Override
	public Point2D getSecondaryPoint(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point2D> pointKey) {
		if (IHasIndicatorPoint.INDICATOR_POINT_KEY.equals(pointKey)) {
			return getAnchorPoint();
		}
		return null;
	}

}
