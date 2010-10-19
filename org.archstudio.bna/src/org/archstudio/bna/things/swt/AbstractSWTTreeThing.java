package org.archstudio.bna.things.swt;

import org.archstudio.bna.*;
import org.archstudio.bna.constants.CompletionStatus;
import org.archstudio.bna.facets.*;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractSWTTreeThing extends AbstractThing implements IHasMutableAnchorPoint, IHasBoundingBox, IMutableMovesWith,
        IHasMutableCompletionStatus, IHasMutableEditing {

	public AbstractSWTTreeThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setAnchorPoint(new Point(0, 0));
		setEditing(false);
		setMovesWithMode(IMovesWith.TRACK_ANCHOR_POINT_FIRST);
		setCompletionStatus(CompletionStatus.INCOMPLETE);
	}

	public void setValue(Object value) {
		setProperty("value", value);
	}

	public Object getValue() {
		return getProperty("value");
	}

	public void setAnchorPoint(Point newAnchorPoint) {
		setProperty(ANCHOR_POINT_PROPERTY_NAME, newAnchorPoint);
	}

	public Point getAnchorPoint() {
		Point p = (Point) getProperty(ANCHOR_POINT_PROPERTY_NAME);
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

	public void setEditing(boolean editing) {
		setProperty(EDITING_PROPERTY_NAME, editing);
	}

	public boolean isEditing() {
		return getProperty(EDITING_PROPERTY_NAME);
	}

	public void setMovesWithMode(int mode) {
		setProperty(MOVES_WITH_MODE_PROPERTY_NAME, mode);
	}

	public int getMovesWithMode() {
		return getProperty(MOVES_WITH_MODE_PROPERTY_NAME);
	}

	public void setMovesWithThingID(String movesWithThingID) {
		setProperty(MOVES_WITH_THING_ID_PROPERTY_NAME, movesWithThingID);
	}

	public String getMovesWithThingID() {
		return getProperty(MOVES_WITH_THING_ID_PROPERTY_NAME);
	}

	public Point getReferencePoint() {
		return getAnchorPoint();
	}

	public void setReferencePoint(Point p) {
		setAnchorPoint(p);
	}

	public CompletionStatus getCompletionStatus() {
		return getProperty(COMPLETION_STATUS_PROPERTY_NAME);
	}

	public void setCompletionStatus(CompletionStatus completionStatus) {
		setProperty(COMPLETION_STATUS_PROPERTY_NAME, completionStatus);
	}
}
