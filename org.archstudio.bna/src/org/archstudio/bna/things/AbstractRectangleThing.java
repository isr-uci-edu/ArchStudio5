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
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public abstract class AbstractRectangleThing extends AbstractRelativeMovableReferencePointThing implements
		IHasMutableBoundingBox, IHasMutableMinimumSize, IRelativeMovable, IHasMutableLocalInsets, IIsSticky {

	public AbstractRectangleThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setMinimumSize(new Dimension(5, 5));
		setBoundingBox(new Rectangle(0, 0, 10, 10));
		addShapeModifyingKey(BOUNDING_BOX_KEY);
	}

	@Override
	public Dimension getMinimumSize() {
		return get(MINIMUM_SIZE_KEY, new Dimension(5, 5));
	}

	@Override
	public void setMinimumSize(final Dimension minimumDimension) {
		set(MINIMUM_SIZE_KEY, minimumDimension);
		Rectangle r = getBoundingBox();
		if (r != null) {
			setBoundingBox(r);
		}
	}

	@Override
	public Rectangle getBoundingBox() {
		return get(BOUNDING_BOX_KEY, new Rectangle(0, 0, 0, 0));
	}

	@Override
	public void setBoundingBox(final Rectangle r) {
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

	@Override
	public Point getReferencePoint() {
		Rectangle bounds = getBoundingBox();
		return new Point(bounds.x + bounds.width / 2, bounds.y + bounds.height / 2);
	}

	@Override
	public void moveRelative(final Point worldDelta) {
		Rectangle bounds = getBoundingBox();
		bounds.x += worldDelta.x;
		bounds.y += worldDelta.y;
		setBoundingBox(bounds);
	}

	@Override
	public Insets getLocalInsets() {
		return get(LOCAL_INSETS_KEY, new Insets(0, 0, 0, 0));
	}

	@Override
	public void setLocalInsets(Insets insets) {
		set(LOCAL_INSETS_KEY, insets);
	}

	@Override
	public Point getStickyPointNear(StickyMode stickyMode, Point nearPoint) {
		Rectangle bb = getBoundingBox();
		switch (stickyMode) {
		case CENTER:
			return new Point(bb.x + bb.width / 2, bb.y + bb.height / 2);
		case EDGE:
			return BNAUtils.getClosestPointOnRectangle(bb, new Dimension(0, 0), nearPoint);
		case EDGE_FROM_CENTER:
			return BNAUtils.getClosestPointOnRectangle(bb, new Dimension(0, 0), nearPoint, new Point(bb.x + bb.width
					/ 2, bb.y + bb.height / 2));
		}
		throw new IllegalArgumentException(stickyMode.name());
	}
}
