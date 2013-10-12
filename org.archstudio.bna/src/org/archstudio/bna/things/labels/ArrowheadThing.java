package org.archstudio.bna.things.labels;

import java.awt.Shape;

import org.archstudio.bna.IThingListener;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.constants.ArrowheadShape;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasLineStyle;
import org.archstudio.bna.facets.IHasMutableArrowhead;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableLineData;
import org.archstudio.bna.facets.IHasMutableSecondaryAnchorPoint;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.things.AbstractMutableAnchorPointThing;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.LinearCoordinateMapper;
import org.archstudio.bna.utils.ShapeUtils;
import org.archstudio.swtutils.constants.LineStyle;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

public class ArrowheadThing extends AbstractMutableAnchorPointThing implements IHasMutableColor,
		IHasMutableSecondaryColor, IHasMutableEdgeColor, IHasMutableArrowhead, IHasMutableSecondaryAnchorPoint,
		IHasMutableLineData, IHasBoundingBox {

	public ArrowheadThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setArrowheadShape(ArrowheadShape.TRIANGLE);
		setArrowheadStemLength(20);
		setArrowheadAngle(40);
		setSecondaryAnchorPoint(new Point(0, 0));
		setColor(new RGB(128, 128, 128));
		setSecondaryColor(new RGB(0, 0, 0));
		setEdgeColor(new RGB(0, 0, 0));
		setLineStyle(IHasLineStyle.LINE_STYLE_SOLID);
		setLineWidth(1);
		set(BOUNDING_BOX_KEY, new Rectangle(0, 0, 0, 0));
		super.initProperties();
		addThingListener(new IThingListener() {
			@Override
			public void thingChanged(ThingEvent thingEvent) {
				if (!BOUNDING_BOX_KEY.equals(thingEvent.getPropertyName())) {
					set(BOUNDING_BOX_KEY, calculateBoundingBox());
				}
			}
		});
		set(BOUNDING_BOX_KEY, calculateBoundingBox());
	}

	protected Rectangle calculateBoundingBox() {
		Shape shape = ShapeUtils.createArrowhead(this, LinearCoordinateMapper.IDENTITY);
		if (shape != null) {
			return BNAUtils.toRectangle(shape.getBounds());
		}
		Point ap = getAnchorPoint();
		return new Rectangle(ap.x, ap.y, 0, 0);
	}

	@Override
	public int getArrowheadStemLength() {
		return get(ARROWHEAD_STEM_LENGTH_KEY);
	}

	@Override
	public void setArrowheadStemLength(int arrowheadStemLength) {
		set(ARROWHEAD_STEM_LENGTH_KEY, arrowheadStemLength);
	}

	@Override
	public int getArrowheadAngle() {
		return get(ARROWHEAD_ANGLE_KEY);
	}

	@Override
	public void setArrowheadAngle(int arrowheadAngle) {
		set(ARROWHEAD_ANGLE_KEY, arrowheadAngle);
	}

	@Override
	public void setColor(@Nullable RGB c) {
		set(COLOR_KEY, c);
	}

	@Override
	public @Nullable
	RGB getColor() {
		return get(COLOR_KEY);
	}

	@Override
	public void setSecondaryColor(@Nullable RGB c) {
		set(SECONDARY_COLOR_KEY, c);
	}

	@Override
	public @Nullable
	RGB getSecondaryColor() {
		return get(SECONDARY_COLOR_KEY);
	}

	@Override
	public void setEdgeColor(@Nullable RGB c) {
		set(EDGE_COLOR_KEY, c);
	}

	@Override
	public @Nullable
	RGB getEdgeColor() {
		return get(EDGE_COLOR_KEY);
	}

	@Override
	public ArrowheadShape getArrowheadShape() {
		return get(ARROWHEAD_SHAPE_KEY);
	}

	@Override
	public void setArrowheadShape(ArrowheadShape arrowheadShape) {
		set(ARROWHEAD_SHAPE_KEY, arrowheadShape);
	}

	@Override
	public Point getSecondaryAnchorPoint() {
		return get(SECONDARY_ANCHOR_POINT_KEY);
	}

	@Override
	public void setSecondaryAnchorPoint(Point secondaryAnchorPoint) {
		set(SECONDARY_ANCHOR_POINT_KEY, secondaryAnchorPoint);
	}

	@Override
	public LineStyle getLineStyle() {
		return get(LINE_STYLE_KEY);
	}

	@Override
	public void setLineStyle(LineStyle lineStyle) {
		set(LINE_STYLE_KEY, lineStyle);
	}

	@Override
	public int getLineWidth() {
		return get(LINE_WIDTH_KEY);
	}

	@Override
	public void setLineWidth(int lineWidth) {
		set(LINE_WIDTH_KEY, lineWidth);
	}

	@Override
	public Rectangle getBoundingBox() {
		return get(BOUNDING_BOX_KEY);
	}
}
