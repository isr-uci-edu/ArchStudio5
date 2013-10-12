package org.archstudio.bna.things;

import static com.google.common.base.Preconditions.checkNotNull;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableEndpoints;
import org.archstudio.bna.facets.IHasMutableValue;
import org.archstudio.bna.keys.ThingMetakey;
import org.archstudio.bna.logics.coordinating.ArrowheadLogic.IHasArrowheadStemPoint;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic.IHasLoopablePoint;
import org.archstudio.bna.things.AbstractCurvedSplineThingPeer.ShapeInfo;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.LinearCoordinateMapper;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractCurvedSplineThing extends AbstractAnchorPointThing implements IHasMutableEndpoints,
		IHasMutableValue<Integer>, IHasBoundingBox, IHasLoopablePoint, IHasArrowheadStemPoint {

	private static final IThingKey<Point> ENDPOINT_1_ARROWHEAD_STEM_POINT_KEY = ThingMetakey.create(
			".arrowheadStemPoint", ENDPOINT_1_KEY);
	private static final IThingKey<Point> ENDPOINT_2_ARROWHEAD_STEM_POINT_KEY = ThingMetakey.create(
			".arrowheadStemPoint", ENDPOINT_2_KEY);

	public AbstractCurvedSplineThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		Point p1 = new Point(10, -10);
		Point p2 = new Point(-10, 10);
		setEndpoint1(p1);
		set(ENDPOINT_1_ARROWHEAD_STEM_POINT_KEY, p2);
		setEndpoint2(p2);
		set(ENDPOINT_2_ARROWHEAD_STEM_POINT_KEY, p1);
		setLoopType(LoopType.NONE);
		setValue(10);
		setAnchorPoint(new Point(0, 0));
		set(BOUNDING_BOX_KEY, new Rectangle(0, 0, 0, 0));
		addShapeModifyingKey(ENDPOINT_1_KEY);
		addShapeModifyingKey(ENDPOINT_2_KEY);
		addShapeModifyingKey(VALUE_KEY);
		super.initProperties();
		addThingListener(new IThingListener() {
			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (isShapeModifyingKey(thingEvent.getPropertyName())) {
					updateCurvedSplineProperties();
				}
			}
		});
		updateCurvedSplineProperties();
	}

	protected void updateCurvedSplineProperties() {
		ShapeInfo shapeInfo = AbstractCurvedSplineThingPeer.getShapeInfo(this, LinearCoordinateMapper.IDENTITY);
		set(ENDPOINT_1_ARROWHEAD_STEM_POINT_KEY, shapeInfo.endpoint1StemPoint);
		set(ENDPOINT_2_ARROWHEAD_STEM_POINT_KEY, shapeInfo.endpoint2StemPoint);
		setAnchorPoint(shapeInfo.anchorPoint);
		set(BOUNDING_BOX_KEY, BNAUtils.toRectangle(shapeInfo.shape.getBounds()));
	}

	@Override
	public void moveRelative(Point worldDelta) {
		Point p1 = getEndpoint1();
		p1.x += worldDelta.x;
		p1.y += worldDelta.y;
		setEndpoint1(p1);

		Point p2 = getEndpoint2();
		p2.x += worldDelta.x;
		p2.y += worldDelta.y;
		setEndpoint2(p2);
	}

	@Override
	public Point getEndpoint1() {
		return get(ENDPOINT_1_KEY);
	}

	@Override
	public void setEndpoint1(Point endpoint1) {
		checkNotNull(endpoint1);
		set(ENDPOINT_1_KEY, endpoint1);
	}

	@Override
	public Point getEndpoint2() {
		return get(ENDPOINT_2_KEY);
	}

	@Override
	public void setEndpoint2(Point endpoint2) {
		checkNotNull(endpoint2);
		set(ENDPOINT_2_KEY, endpoint2);
	}

	@Override
	public Integer getValue() {
		return (Integer) get(VALUE_KEY);
	}

	@Override
	public void setValue(Integer value) {
		set(VALUE_KEY, value);
	}

	@Override
	public Rectangle getBoundingBox() {
		return get(BOUNDING_BOX_KEY);
	}

	@Override
	public void setLoopType(LoopType loopType) {
		set(LOOP_TYPE_KEY, loopType);
	}

	public LoopType getLoopType() {
		return get(LOOP_TYPE_KEY);
	}

	@Override
	public Point getArroheadStempPoint(IThingKey<Point> pointKey) {
		if (ENDPOINT_1_KEY.equals(pointKey)) {
			return get(ENDPOINT_1_ARROWHEAD_STEM_POINT_KEY);
		}
		else if (ENDPOINT_2_KEY.equals(pointKey)) {
			return get(ENDPOINT_2_ARROWHEAD_STEM_POINT_KEY);
		}
		else {
			throw new IllegalArgumentException(pointKey.toString());
		}
	}

	@Override
	public Point getSecondaryPoint(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point> pointKey) {
		if (stickLogic.isLoopingPoint(this, ENDPOINT_1_KEY, ENDPOINT_2_KEY)) {
			if (ENDPOINT_1_KEY.equals(pointKey)) {
				return get(ENDPOINT_1_ARROWHEAD_STEM_POINT_KEY);
			}
			else if (ENDPOINT_2_KEY.equals(pointKey)) {
				return get(ENDPOINT_2_ARROWHEAD_STEM_POINT_KEY);
			}
			else {
				throw new IllegalArgumentException(pointKey.toString());
			}
		}
		else {
			if (ENDPOINT_1_KEY.equals(pointKey) || ENDPOINT_2_KEY.equals(pointKey)) {
				Point p1 = stickLogic.getStuckPoint(this, ENDPOINT_1_KEY);
				Point p2 = stickLogic.getStuckPoint(this, ENDPOINT_2_KEY);
				double l = getValue();
				double mx = (p1.x + p2.x) / 2;
				double my = (p1.y + p2.y) / 2;
				double dx = p2.x - p1.x;
				double dy = p2.y - p1.y;
				double angle = Math.PI - Math.atan2(dy, dx);
				double cx = mx + 2 * l * -Math.sin(angle);
				double cy = my + 2 * l * -Math.cos(angle);
				return BNAUtils.toPoint(cx, cy);
			}
			else {
				throw new IllegalArgumentException(pointKey.toString());
			}
		}
	}

	@Override
	public LoopType getLoopType(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point> pointKey) {
		if (ENDPOINT_1_KEY.equals(pointKey) || ENDPOINT_2_KEY.equals(pointKey)) {
			if (stickLogic.isLoopingPoint(this, ENDPOINT_1_KEY, ENDPOINT_2_KEY)) {
				return getValue() >= 0 ? LoopType.TOP_RIGHT : LoopType.BOTTOM_LEFT;
			}
			return LoopType.NONE;
		}
		throw new IllegalArgumentException(pointKey.toString());
	}

}
