package org.archstudio.bna.things.labels;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasIndicatorPoint;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableFontData;
import org.archstudio.bna.facets.IHasMutableHorizontalAlignment;
import org.archstudio.bna.facets.IHasMutableIndicatorPoint;
import org.archstudio.bna.facets.IHasMutableLineData;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasMutableVerticalAlignment;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.coordinating.StickPointLogic;
import org.archstudio.bna.logics.coordinating.StickPointLogic.IHasSecondaryPoint;
import org.archstudio.bna.things.AbstractMutableAnchorPointThing;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.LineStyle;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

@NonNullByDefault
public class AnchoredLabelThing extends AbstractMutableAnchorPointThing implements IHasMutableText, IHasMutableColor,
		IHasMutableHorizontalAlignment, IHasMutableVerticalAlignment, IHasMutableFontData, IRelativeMovable,
		IHasMutableAngle, IHasMutableIndicatorPoint, IHasMutableLineData, IHasMutableEdgeColor, IHasSecondaryPoint {

	public AnchoredLabelThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setText("");
		setColor(new RGB(0, 0, 0));
		setFontName(null);
		setFontSize(10);
		setDontIncreaseFontSize(Boolean.TRUE);
		setFontStyle(FontStyle.NORMAL);
		setHorizontalAlignment(HorizontalAlignment.CENTER);
		setVerticalAlignment(VerticalAlignment.MIDDLE);
		setAngle(0);
		setLineStyle(LINE_STYLE_SOLID);
		setLineWidth(1);
		setEdgeColor(null);
		super.initProperties();
	}

	@Override
	public void setEdgeColor(RGB c) {
		set(EDGE_COLOR_KEY, c);
	}

	@Override
	public RGB getEdgeColor() {
		return get(EDGE_COLOR_KEY);
	}

	@Override
	public @Nullable
	RGB getColor() {
		return get(COLOR_KEY);
	}

	@Override
	public void setColor(@Nullable RGB c) {
		set(COLOR_KEY, c);
	}

	@Override
	public String getText() {
		return get(TEXT_KEY);
	}

	@Override
	public void setText(String text) {
		set(TEXT_KEY, text);
	}

	@Override
	public HorizontalAlignment getHorizontalAlignment() {
		return get(HORIZONTAL_ALIGNMENT_KEY);
	}

	@Override
	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		set(HORIZONTAL_ALIGNMENT_KEY, horizontalAlignment);
	}

	@Override
	public VerticalAlignment getVerticalAlignment() {
		return get(VERTICAL_ALIGNMENT_KEY);
	}

	@Override
	public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
		set(VERTICAL_ALIGNMENT_KEY, verticalAlignment);
	}

	@Override
	public String getFontName() {
		return get(FONT_NAME_KEY);
	}

	@Override
	public void setFontName(String fontName) {
		set(FONT_NAME_KEY, fontName);
	}

	@Override
	public int getFontSize() {
		return get(FONT_SIZE_KEY);
	}

	@Override
	public void setFontSize(int fontSize) {
		set(FONT_SIZE_KEY, fontSize);
	}

	@Override
	public FontStyle getFontStyle() {
		return get(FONT_STYLE_KEY);
	}

	@Override
	public void setFontStyle(FontStyle fontStyle) {
		set(FONT_STYLE_KEY, fontStyle);
	}

	@Override
	public boolean getDontIncreaseFontSize() {
		return has(DONT_INCREASE_FONT_SIZE_KEY, true);
	}

	@Override
	public void setDontIncreaseFontSize(boolean dontIncrease) {
		set(DONT_INCREASE_FONT_SIZE_KEY, dontIncrease);
	}

	@Override
	public int getAngle() {
		return get(ANGLE_KEY);
	}

	@Override
	public void setAngle(int angle) {
		set(ANGLE_KEY, angle);
	}

	@Override
	public @Nullable
	Point getIndicatorPoint() {
		return get(INDICATOR_POINT_KEY);
	}

	@Override
	public void setIndicatorPoint(@Nullable Point indicatorPoint) {
		set(INDICATOR_POINT_KEY, indicatorPoint);
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
	public Point getSecondaryPoint(IBNAModel model, StickPointLogic stickLogic, IThingKey<Point> pointKey) {
		if (IHasIndicatorPoint.INDICATOR_POINT_KEY.equals(pointKey)) {
			return getAnchorPoint();
		}
		if (IHasAnchorPoint.ANCHOR_POINT_KEY.equals(pointKey)) {
			return getIndicatorPoint();
		}
		return null;
	}
}
