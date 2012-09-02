package org.archstudio.bna.things;

import java.awt.Dimension;

import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableRoundedCorners;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public abstract class AbstractRoundedRectangleThing extends AbstractRectangleThing implements IHasMutableRoundedCorners {

	public AbstractRoundedRectangleThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setCornerSize(new Dimension(0, 0));
		addShapeModifyingKey(CORNER_SIZE_KEY);
	}

	@Override
	public Dimension getCornerSize() {
		Dimension d = get(CORNER_SIZE_KEY, new Dimension(0, 0));
		if (d.width != 0 || d.height != 0) {
			Rectangle r = get(IHasBoundingBox.BOUNDING_BOX_KEY, new Rectangle(0, 0, 0, 0));
			d.width = Math.min(d.width, r.width / 2);
			d.height = Math.min(d.height, r.height / 2);
		}
		return d;
	}

	@Override
	public void setCornerSize(Dimension dimension) {
		set(CORNER_SIZE_KEY, dimension);
	}

	@Override
	public Point getStickyPointNear(StickyMode stickyMode, Point nearPoint) {
		Rectangle bb = getBoundingBox();
		switch (stickyMode) {
		case CENTER:
			return new Point(bb.x + bb.width / 2, bb.y + bb.height / 2);
		case EDGE:
			return BNAUtils.getClosestPointOnRectangle(bb, getCornerSize(), nearPoint);
		case EDGE_FROM_CENTER:
			return BNAUtils.getClosestPointOnRectangle(bb, getCornerSize(), nearPoint, new Point(bb.x + bb.width / 2,
					bb.y + bb.height / 2));
		}
		throw new IllegalArgumentException(stickyMode.name());
	}

}
