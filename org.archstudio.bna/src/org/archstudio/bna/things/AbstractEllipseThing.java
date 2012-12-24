package org.archstudio.bna.things;

import java.awt.Dimension;
import java.awt.Insets;

import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableLocalInsets;
import org.archstudio.bna.facets.IHasMutableMinimumSize;
import org.archstudio.bna.facets.IIsSticky;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public abstract class AbstractEllipseThing extends AbstractRelativeMovableReferencePointThing implements
		IHasMutableBoundingBox, IHasMutableMinimumSize, IRelativeMovable, IIsSticky, IHasMutableLocalInsets {

	public AbstractEllipseThing(Object id) {
		super(id);
	}

	protected void initProperties() {
		super.initProperties();
		setMinimumSize(new Dimension(5, 5));
		setBoundingBox(new Rectangle(0, 0, 10, 10));
		addShapeModifyingKey(BOUNDING_BOX_KEY);
	}

	public Dimension getMinimumSize() {
		return get(MINIMUM_SIZE_KEY, new Dimension(5, 5));
	}

	public void setMinimumSize(Dimension minimumDimension) {
		set(MINIMUM_SIZE_KEY, minimumDimension);
		Rectangle r = getBoundingBox();
		if (r != null) {
			setBoundingBox(r);
		}
	}

	public Rectangle getBoundingBox() {
		return get(BOUNDING_BOX_KEY, new Rectangle(0, 0, 0, 0));
	}

	public void setBoundingBox(Rectangle r) {
		BNAUtils.normalizeRectangle(r);
		Dimension minDimension = getMinimumSize();
		if (minDimension != null) {
			if (r.width < minDimension.width) {
				r.width = minDimension.width;
			}
			if (r.height < minDimension.height) {
				r.height = minDimension.height;
			}
		}
		set(BOUNDING_BOX_KEY, r);
	}

	public Point getReferencePoint() {
		Rectangle r = getBoundingBox();
		return new Point(r.x + r.width / 2, r.y + r.height / 2);
	}

	public void moveRelative(final Point worldDelta) {
		Rectangle bounds = getBoundingBox();
		bounds.x += worldDelta.x;
		bounds.y += worldDelta.y;
		setBoundingBox(bounds);
	}

	public Point getStickyPointNear(StickyMode stickyMode, Point nearPoint) {
		Rectangle r = getBoundingBox();
		switch (stickyMode) {
		case EDGE:
		case EDGE_FROM_CENTER: {
			double cx = r.x + r.width / 2d;
			double cy = r.y + r.height / 2d;
			double dx = cx - nearPoint.x;
			double dy = cy - nearPoint.y;
			double h = Math.sqrt(dx * dx + dy * dy);
			double angle = Math.asin(dx / h);
			if (dy < 0) {
				angle = Math.PI - angle;
			}
			return new Point(BNAUtils.round(cx + -Math.sin(angle) * r.width / 2d), BNAUtils.round(cy + -Math.cos(angle)
					* r.height / 2d));
		}
		case CENTER:
			return new Point(r.x + r.width / 2, r.y + r.height / 2);
		}
		throw new IllegalArgumentException();
	}

	public Insets getLocalInsets() {
		return get(LOCAL_INSETS_KEY, new Insets(0, 0, 0, 0));
	}

	public void setLocalInsets(Insets insets) {
		set(LOCAL_INSETS_KEY, insets);
	}

}
