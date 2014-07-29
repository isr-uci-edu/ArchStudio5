package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThingPeer;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.keys.ThingRefKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN.
 *
 * To modify, update the thingdefinition extension at
 * org.archstudio.bna/Package[name=org.archstudio.bna.things.shapes]/Thing[name=Polygon].
 */
 
@SuppressWarnings("all")
@NonNullByDefault
public abstract class PolygonThingBase extends org.archstudio.bna.things.AbstractThing
	implements org.archstudio.bna.IThing,
			org.archstudio.bna.facets.IHasBoundingBox,
			org.archstudio.bna.facets.IHasMutableGlow,
			org.archstudio.bna.facets.IHasMutableGradientFilled,
			org.archstudio.bna.facets.IHasMutableLineData,
			org.archstudio.bna.facets.IHasMutablePoints,
			org.archstudio.bna.facets.IHasMutableReferencePoint,
			org.archstudio.bna.facets.IHasMutableSelected,
			org.archstudio.bna.facets.IHasMutableStickyShape {

	public PolygonThingBase(@Nullable Object id) {
		super(id);
	}

	@Override
	public IThingPeer<? extends PolygonThing> createPeer(IBNAView view, ICoordinateMapper cm) {
		return new PolygonThingPeer<>((PolygonThing)this, view, cm);
	}

	@Override
	protected void initProperties() {
		initProperty(org.archstudio.bna.facets.IHasBoundingBox.BOUNDING_BOX_KEY, new org.eclipse.swt.graphics.Rectangle(0, 0, 30, 20));
		initProperty(org.archstudio.bna.facets.IHasColor.COLOR_KEY, new org.eclipse.swt.graphics.RGB(0, 0, 0));
		initProperty(org.archstudio.bna.facets.IHasEdgeColor.EDGE_COLOR_KEY, new org.eclipse.swt.graphics.RGB(0, 0, 0));
		initProperty(org.archstudio.bna.facets.IHasGlow.GLOW_ALPHA_KEY, 0.75d);
		initProperty(org.archstudio.bna.facets.IHasGlow.GLOW_COLOR_KEY, null);
		initProperty(org.archstudio.bna.facets.IHasGlow.GLOW_WIDTH_KEY, 20);
		initProperty(org.archstudio.bna.facets.IHasGradientFilled.GRADIENT_FILLED_KEY, true);
		initProperty(org.archstudio.bna.facets.IHasLineStyle.LINE_STYLE_KEY, org.archstudio.swtutils.constants.LineStyle.SOLID);
		initProperty(org.archstudio.bna.facets.IHasLineWidth.LINE_WIDTH_KEY, 1);
		initProperty(org.archstudio.bna.facets.IHasPoints.POINTS_KEY, com.google.common.collect.Lists.<java.awt.geom.Point2D>newArrayList(new java.awt.geom.Point2D.Double(-1, 1), new java.awt.geom.Point2D.Double(0, -1), new java.awt.geom.Point2D.Double(1, 1)));
		addShapeModifyingKey(org.archstudio.bna.facets.IHasPoints.POINTS_KEY);
		initProperty(org.archstudio.bna.facets.IHasRotatingOffset.ROTATING_OFFSET_KEY, 0);
		initProperty(org.archstudio.bna.facets.IHasSecondaryColor.SECONDARY_COLOR_KEY, new org.eclipse.swt.graphics.RGB(192, 192, 192));
		initProperty(org.archstudio.bna.facets.IHasSelected.SELECTED_KEY, false);
		super.initProperties();
	}

	public org.eclipse.swt.graphics.Rectangle getBoundingBox() {
		return get(org.archstudio.bna.facets.IHasBoundingBox.BOUNDING_BOX_KEY);
	}

	/*package*/ org.eclipse.swt.graphics.Rectangle getRawBoundingBox() {
		return getRaw(org.archstudio.bna.facets.IHasBoundingBox.BOUNDING_BOX_KEY);
	}

	/*package*/ void setBoundingBox(org.eclipse.swt.graphics.Rectangle boundingBox) {
		set(org.archstudio.bna.facets.IHasBoundingBox.BOUNDING_BOX_KEY, boundingBox);
	}

	/*package*/ org.eclipse.swt.graphics.Rectangle setRawBoundingBox(org.eclipse.swt.graphics.Rectangle boundingBox) {
		return setRaw(org.archstudio.bna.facets.IHasBoundingBox.BOUNDING_BOX_KEY, boundingBox);
	}

	public @Nullable org.eclipse.swt.graphics.RGB getColor() {
		return get(org.archstudio.bna.facets.IHasColor.COLOR_KEY);
	}

	/*package*/ @Nullable org.eclipse.swt.graphics.RGB getRawColor() {
		return getRaw(org.archstudio.bna.facets.IHasColor.COLOR_KEY);
	}

	public void setColor(@Nullable org.eclipse.swt.graphics.RGB color) {
		set(org.archstudio.bna.facets.IHasColor.COLOR_KEY, color);
	}

	/*package*/ @Nullable org.eclipse.swt.graphics.RGB setRawColor(@Nullable org.eclipse.swt.graphics.RGB color) {
		return setRaw(org.archstudio.bna.facets.IHasColor.COLOR_KEY, color);
	}

	public @Nullable org.eclipse.swt.graphics.RGB getEdgeColor() {
		return get(org.archstudio.bna.facets.IHasEdgeColor.EDGE_COLOR_KEY);
	}

	/*package*/ @Nullable org.eclipse.swt.graphics.RGB getRawEdgeColor() {
		return getRaw(org.archstudio.bna.facets.IHasEdgeColor.EDGE_COLOR_KEY);
	}

	public void setEdgeColor(@Nullable org.eclipse.swt.graphics.RGB edgeColor) {
		set(org.archstudio.bna.facets.IHasEdgeColor.EDGE_COLOR_KEY, edgeColor);
	}

	/*package*/ @Nullable org.eclipse.swt.graphics.RGB setRawEdgeColor(@Nullable org.eclipse.swt.graphics.RGB edgeColor) {
		return setRaw(org.archstudio.bna.facets.IHasEdgeColor.EDGE_COLOR_KEY, edgeColor);
	}

	public double getGlowAlpha() {
		return get(org.archstudio.bna.facets.IHasGlow.GLOW_ALPHA_KEY);
	}

	/*package*/ double getRawGlowAlpha() {
		return getRaw(org.archstudio.bna.facets.IHasGlow.GLOW_ALPHA_KEY);
	}

	public void setGlowAlpha(double glowAlpha) {
		set(org.archstudio.bna.facets.IHasGlow.GLOW_ALPHA_KEY, glowAlpha);
	}

	/*package*/ double setRawGlowAlpha(double glowAlpha) {
		return setRaw(org.archstudio.bna.facets.IHasGlow.GLOW_ALPHA_KEY, glowAlpha);
	}

	public @Nullable org.eclipse.swt.graphics.RGB getGlowColor() {
		return get(org.archstudio.bna.facets.IHasGlow.GLOW_COLOR_KEY);
	}

	/*package*/ @Nullable org.eclipse.swt.graphics.RGB getRawGlowColor() {
		return getRaw(org.archstudio.bna.facets.IHasGlow.GLOW_COLOR_KEY);
	}

	public void setGlowColor(@Nullable org.eclipse.swt.graphics.RGB glowColor) {
		set(org.archstudio.bna.facets.IHasGlow.GLOW_COLOR_KEY, glowColor);
	}

	/*package*/ @Nullable org.eclipse.swt.graphics.RGB setRawGlowColor(@Nullable org.eclipse.swt.graphics.RGB glowColor) {
		return setRaw(org.archstudio.bna.facets.IHasGlow.GLOW_COLOR_KEY, glowColor);
	}

	public int getGlowWidth() {
		return get(org.archstudio.bna.facets.IHasGlow.GLOW_WIDTH_KEY);
	}

	/*package*/ int getRawGlowWidth() {
		return getRaw(org.archstudio.bna.facets.IHasGlow.GLOW_WIDTH_KEY);
	}

	public void setGlowWidth(int glowWidth) {
		set(org.archstudio.bna.facets.IHasGlow.GLOW_WIDTH_KEY, glowWidth);
	}

	/*package*/ int setRawGlowWidth(int glowWidth) {
		return setRaw(org.archstudio.bna.facets.IHasGlow.GLOW_WIDTH_KEY, glowWidth);
	}

	public boolean isGradientFilled() {
		return get(org.archstudio.bna.facets.IHasGradientFilled.GRADIENT_FILLED_KEY);
	}

	/*package*/ boolean isRawGradientFilled() {
		return getRaw(org.archstudio.bna.facets.IHasGradientFilled.GRADIENT_FILLED_KEY);
	}

	public void setGradientFilled(boolean gradientFilled) {
		set(org.archstudio.bna.facets.IHasGradientFilled.GRADIENT_FILLED_KEY, gradientFilled);
	}

	/*package*/ boolean isRawGradientFilled(boolean gradientFilled) {
		return setRaw(org.archstudio.bna.facets.IHasGradientFilled.GRADIENT_FILLED_KEY, gradientFilled);
	}

	public org.archstudio.swtutils.constants.LineStyle getLineStyle() {
		return get(org.archstudio.bna.facets.IHasLineStyle.LINE_STYLE_KEY);
	}

	/*package*/ org.archstudio.swtutils.constants.LineStyle getRawLineStyle() {
		return getRaw(org.archstudio.bna.facets.IHasLineStyle.LINE_STYLE_KEY);
	}

	public void setLineStyle(org.archstudio.swtutils.constants.LineStyle lineStyle) {
		set(org.archstudio.bna.facets.IHasLineStyle.LINE_STYLE_KEY, lineStyle);
	}

	/*package*/ org.archstudio.swtutils.constants.LineStyle setRawLineStyle(org.archstudio.swtutils.constants.LineStyle lineStyle) {
		return setRaw(org.archstudio.bna.facets.IHasLineStyle.LINE_STYLE_KEY, lineStyle);
	}

	public int getLineWidth() {
		return get(org.archstudio.bna.facets.IHasLineWidth.LINE_WIDTH_KEY);
	}

	/*package*/ int getRawLineWidth() {
		return getRaw(org.archstudio.bna.facets.IHasLineWidth.LINE_WIDTH_KEY);
	}

	public void setLineWidth(int lineWidth) {
		set(org.archstudio.bna.facets.IHasLineWidth.LINE_WIDTH_KEY, lineWidth);
	}

	/*package*/ int setRawLineWidth(int lineWidth) {
		return setRaw(org.archstudio.bna.facets.IHasLineWidth.LINE_WIDTH_KEY, lineWidth);
	}

	public java.util.List<java.awt.geom.Point2D> getPoints() {
		return get(org.archstudio.bna.facets.IHasPoints.POINTS_KEY);
	}

	/*package*/ java.util.List<java.awt.geom.Point2D> getRawPoints() {
		return getRaw(org.archstudio.bna.facets.IHasPoints.POINTS_KEY);
	}

	public void setPoints(java.util.List<java.awt.geom.Point2D> points) {
		set(org.archstudio.bna.facets.IHasPoints.POINTS_KEY, points);
	}

	/*package*/ java.util.List<java.awt.geom.Point2D> setRawPoints(java.util.List<java.awt.geom.Point2D> points) {
		return setRaw(org.archstudio.bna.facets.IHasPoints.POINTS_KEY, points);
	}

	public int getRotatingOffset() {
		return get(org.archstudio.bna.facets.IHasRotatingOffset.ROTATING_OFFSET_KEY);
	}

	/*package*/ int getRawRotatingOffset() {
		return getRaw(org.archstudio.bna.facets.IHasRotatingOffset.ROTATING_OFFSET_KEY);
	}

	public void setRotatingOffset(int rotatingOffset) {
		set(org.archstudio.bna.facets.IHasRotatingOffset.ROTATING_OFFSET_KEY, rotatingOffset);
	}

	/*package*/ int setRawRotatingOffset(int rotatingOffset) {
		return setRaw(org.archstudio.bna.facets.IHasRotatingOffset.ROTATING_OFFSET_KEY, rotatingOffset);
	}

	public @Nullable org.eclipse.swt.graphics.RGB getSecondaryColor() {
		return get(org.archstudio.bna.facets.IHasSecondaryColor.SECONDARY_COLOR_KEY);
	}

	/*package*/ @Nullable org.eclipse.swt.graphics.RGB getRawSecondaryColor() {
		return getRaw(org.archstudio.bna.facets.IHasSecondaryColor.SECONDARY_COLOR_KEY);
	}

	public void setSecondaryColor(@Nullable org.eclipse.swt.graphics.RGB secondaryColor) {
		set(org.archstudio.bna.facets.IHasSecondaryColor.SECONDARY_COLOR_KEY, secondaryColor);
	}

	/*package*/ @Nullable org.eclipse.swt.graphics.RGB setRawSecondaryColor(@Nullable org.eclipse.swt.graphics.RGB secondaryColor) {
		return setRaw(org.archstudio.bna.facets.IHasSecondaryColor.SECONDARY_COLOR_KEY, secondaryColor);
	}

	public boolean isSelected() {
		return get(org.archstudio.bna.facets.IHasSelected.SELECTED_KEY);
	}

	/*package*/ boolean isRawSelected() {
		return getRaw(org.archstudio.bna.facets.IHasSelected.SELECTED_KEY);
	}

	public void setSelected(boolean selected) {
		set(org.archstudio.bna.facets.IHasSelected.SELECTED_KEY, selected);
	}

	/*package*/ boolean isRawSelected(boolean selected) {
		return setRaw(org.archstudio.bna.facets.IHasSelected.SELECTED_KEY, selected);
	}

}
