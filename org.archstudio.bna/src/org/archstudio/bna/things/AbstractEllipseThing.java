package org.archstudio.bna.things;

import java.awt.Dimension;
import java.awt.Insets;

import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.bna.facets.IHasMutableLocalInsets;
import org.archstudio.bna.facets.IHasMutableMinimumSize;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public abstract class AbstractEllipseThing extends AbstractRelativeMovableReferencePointThing implements
		IHasMutableBoundingBox, IHasMutableMinimumSize, IRelativeMovable, IHasMutableLocalInsets {

	public AbstractEllipseThing(Object id) {
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
		return get(MINIMUM_SIZE_KEY);
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
		return get(BOUNDING_BOX_KEY);
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
		Rectangle r = getBoundingBox();
		return new Point(r.x + r.width / 2, r.y + r.height / 2);
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
		return get(LOCAL_INSETS_KEY);
	}

	@Override
	public void setLocalInsets(Insets insets) {
		set(LOCAL_INSETS_KEY, insets);
	}
}
