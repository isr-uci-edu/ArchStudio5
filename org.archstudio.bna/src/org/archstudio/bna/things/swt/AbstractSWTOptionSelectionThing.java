package org.archstudio.bna.things.swt;

import org.archstudio.bna.constants.CompletionStatus;
import org.archstudio.bna.constants.IFontConstants;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableCompletionStatus;
import org.archstudio.bna.facets.IHasMutableEditing;
import org.archstudio.bna.facets.IHasMutableFontData;
import org.archstudio.bna.facets.IHasMutableHorizontalAlignment;
import org.archstudio.bna.facets.IHasMutableText;
import org.archstudio.bna.things.AbstractThing;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.RGB;


public abstract class AbstractSWTOptionSelectionThing extends AbstractThing implements IHasMutableText,
		IHasMutableAnchorPoint, IHasBoundingBox, IMutableMovesWith, IHasMutableColor, IHasMutableHorizontalAlignment,
		IHasMutableFontData, IHasMutableCompletionStatus, IHasMutableEditing {

	public AbstractSWTOptionSelectionThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		Point p = new Point(50, 50);
		setAnchorPoint(p);
		//setColor(new RGB(0, 0, 0));
		setFontName(IFontConstants.DEFAULT_FONT_NAME);
		setFontSize(10);
		setFontStyle(FontStyle.NORMAL);
		setHorizontalAlignment(HorizontalAlignment.CENTER);
		setEditing(false);
		setMovesWithMode(IMovesWith.TRACK_ANCHOR_POINT_FIRST);
		setCompletionStatus(CompletionStatus.INCOMPLETE);
		setOptions(new String[] { "[No Options]" });
	}

	public void setOptions(String[] options) {
		setProperty("options", options);
	}

	public String[] getOptions() {
		return getProperty("options");
	}

	public void setAnchorPoint(Point newAnchorPoint) {
		setProperty(ANCHOR_POINT_KEY, newAnchorPoint);
	}

	public Point getAnchorPoint() {
		Point p = (Point) getProperty(ANCHOR_POINT_KEY);
		return new Point(p.x, p.y);
	}

	public Rectangle getBoundingBox() {
		Rectangle r = (Rectangle) getProperty("#boundingBox");
		if (r != null) {
			return r;
		}
		Point p = getAnchorPoint();
		if (p != null) {
			return new Rectangle(p.x, p.y, 0, 0);
		}
		return new Rectangle(0, 0, 0, 0);
	}

	public void setColor(RGB c) {
		setProperty(COLOR_KEY, c);
	}

	public RGB getColor() {
		return getProperty(COLOR_KEY);
	}

	public String getText() {
		return (String) getProperty(TEXT_KEY);
	}

	public void setText(String text) {
		setProperty(TEXT_KEY, text);
	}

	public HorizontalAlignment getHorizontalAlignment() {
		return (HorizontalAlignment) getProperty(HORIZONTAL_ALIGNMENT_KEY);
	}

	public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		setProperty(HORIZONTAL_ALIGNMENT_KEY, horizontalAlignment);
	}

	public String getFontName() {
		return (String) getProperty(FONT_NAME_KEY);
	}

	public void setFontName(String fontName) {
		setProperty(FONT_NAME_KEY, fontName);
	}

	public int getFontSize() {
		return getProperty(FONT_SIZE_KEY);
	}

	public void setFontSize(int fontSize) {
		setProperty(FONT_SIZE_KEY, fontSize);
	}

	public FontStyle getFontStyle() {
		return (FontStyle) getProperty(FONT_STYLE_KEY);
	}

	public void setFontStyle(FontStyle fontStyle) {
		setProperty(FONT_STYLE_KEY, fontStyle);
	}

	public void setEditing(boolean editing) {
		setProperty(EDITING_KEY, editing);
	}

	public boolean isEditing() {
		return getProperty(EDITING_KEY);
	}

	public void setMovesWithMode(int mode) {
		setProperty(MOVES_WITH_MODE_KEY, mode);
	}

	public int getMovesWithMode() {
		return getProperty(MOVES_WITH_MODE_KEY);
	}

	public void setMovesWithThingID(String movesWithThingID) {
		setProperty(MOVES_WITH_THING_ID_KEY, movesWithThingID);
	}

	public String getMovesWithThingID() {
		return getProperty(MOVES_WITH_THING_ID_KEY);
	}

	public Point getReferencePoint() {
		return getAnchorPoint();
	}

	public void setReferencePoint(Point p) {
		setAnchorPoint(p);
	}

	public CompletionStatus getCompletionStatus() {
		return getProperty(COMPLETION_STATUS_KEY);
	}

	public void setCompletionStatus(CompletionStatus completionStatus) {
		setProperty(COMPLETION_STATUS_KEY, completionStatus);
	}
}
