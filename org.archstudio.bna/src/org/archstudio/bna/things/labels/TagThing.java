package org.archstudio.bna.things.labels;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.constants.IFontConstants;
import org.archstudio.bna.facets.IDragMovable;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableAngle;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableFontData;
import org.archstudio.bna.facets.IHasMutableIndicatorPoint;
import org.archstudio.bna.facets.IHasMutableTargetThing;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.facets.IHasMutableUserEditable;
import org.archstudio.bna.facets.IHasMutableVisible;
import org.archstudio.bna.facets.IIndicator;
import org.archstudio.bna.facets.IMovesWith;
import org.archstudio.bna.facets.IMutableMovesWith;
import org.archstudio.swtutils.constants.FontStyle;

public class TagThing extends AbstractThing implements IHasBoundingBox, IHasMutableColor, IHasMutableAnchorPoint, IHasMutableText, IHasMutableTargetThing,
        IHasMutableAngle, IHasMutableUserEditable, IDragMovable, IHasMutableIndicatorPoint, IHasMutableFontData, IHasMutableVisible, IMutableMovesWith,
        IIndicator {
	public static final String DONT_INCREASE_FONT_SIZE_PROPERTY_NAME = "dontIncreaseFontSize";

	public TagThing() {
		this(BNAUtils.generateUID(TagThing.class.getName()));
	}

	public TagThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setAnchorPoint(new Point(0, 0));
		setText("");
		setAngle(0);
		setUserEditable(true);
		setFontName(IFontConstants.DEFAULT_FONT_NAME);
		setFontSize(10);
		setFontStyle(FontStyle.NORMAL);
		setDontIncreaseFontSize(true);

		setMovesWithMode(IMovesWith.TRACK_ANCHOR_POINT_FIRST);
		setVisible(false);
	}

	public void setAnchorPoint(Point newAnchorPoint) {
		setProperty(ANCHOR_POINT_PROPERTY_NAME, newAnchorPoint);
	}

	public Point getAnchorPoint() {
		Point ap = getProperty(ANCHOR_POINT_PROPERTY_NAME);
		return new Point(ap.x, ap.y);
	}

	public Rectangle getBoundingBox() {
		Rectangle r = getProperty("#" + BOUNDING_BOX_PROPERTY_NAME);
		if (r != null) {
			return new Rectangle(r.x, r.y, r.width, r.height);
		}

		Point p = getProperty(ANCHOR_POINT_PROPERTY_NAME);
		return new Rectangle(p.x, p.y, 1, 1);
	}

	public RGB getColor() {
		return getProperty(COLOR_PROPERTY_NAME);
	}

	public void setColor(RGB newColor) {
		setProperty(COLOR_PROPERTY_NAME, newColor);
	}

	public String getText() {
		return getProperty(TEXT_PROPERTY_NAME);
	}

	public void setText(String text) {
		setProperty(TEXT_PROPERTY_NAME, text);
	}

	public String getTargetThingID() {
		return getProperty(TARGET_THING_ID_PROPERTY_NAME);
	}

	public void setTargetThingID(String targetThingID) {
		setProperty(TARGET_THING_ID_PROPERTY_NAME, targetThingID);
	}

	public int getAngle() {
		return getProperty(ANGLE_PROPERTY_NAME);
	}

	public void setAngle(int angle) {
		setProperty(ANGLE_PROPERTY_NAME, angle);
	}

	public boolean isUserEditable() {
		return getProperty(USER_EDITABLE_PROPERTY_NAME);
	}

	public void setUserEditable(boolean userEditable) {
		setProperty(USER_EDITABLE_PROPERTY_NAME, userEditable);
	}

	public Point getIndicatorPoint() {
		return getProperty(INDICATOR_POINT_PROPERTY_NAME);
	}

	public void setIndicatorPoint(Point p) {
		setProperty(INDICATOR_POINT_PROPERTY_NAME, p);
	}

	public String getFontName() {
		return (String) getProperty(FONT_NAME_PROPERTY_NAME);
	}

	public void setFontName(String fontName) {
		setProperty(FONT_NAME_PROPERTY_NAME, fontName);
	}

	public int getFontSize() {
		return getProperty(FONT_SIZE_PROPERTY_NAME);
	}

	public void setFontSize(int fontSize) {
		setProperty(FONT_SIZE_PROPERTY_NAME, fontSize);
	}

	public FontStyle getFontStyle() {
		return (FontStyle) getProperty(FONT_STYLE_PROPERTY_NAME);
	}

	public void setFontStyle(FontStyle fontStyle) {
		setProperty(FONT_STYLE_PROPERTY_NAME, fontStyle);
	}

	public void setDontIncreaseFontSize(boolean dontIncrease) {
		setProperty(DONT_INCREASE_FONT_SIZE_PROPERTY_NAME, dontIncrease);
	}

	public boolean getDontIncreaseFontSize() {
		return getProperty(DONT_INCREASE_FONT_SIZE_PROPERTY_NAME);
	}

	public boolean isVisible() {
		return getProperty(VISIBLE_PROPERTY_NAME);
	}

	public void setVisible(boolean visible) {
		setProperty(VISIBLE_PROPERTY_NAME, visible);
	}

	public int getMovesWithMode() {
		return getProperty(MOVES_WITH_MODE_PROPERTY_NAME);
	}

	public void setMovesWithMode(int mode) {
		setProperty(MOVES_WITH_MODE_PROPERTY_NAME, mode);
	}

	public String getMovesWithThingID() {
		return getProperty(MOVES_WITH_THING_ID_PROPERTY_NAME);
	}

	public void setMovesWithThingID(String movesWithThingID) {
		setProperty(MOVES_WITH_THING_ID_PROPERTY_NAME, movesWithThingID);
	}

	public Point getReferencePoint() {
		return getAnchorPoint();
	}

	public void setReferencePoint(Point p) {
		setAnchorPoint(p);
	}
}
