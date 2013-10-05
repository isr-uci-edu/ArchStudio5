package org.archstudio.bna.things;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;

import org.archstudio.bna.facets.IHasMutableRoundedCorners;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.Rectangle;

@NonNullByDefault
public abstract class AbstractRoundedRectangleThing extends AbstractRectangleThing implements IHasMutableRoundedCorners {

	public AbstractRoundedRectangleThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setCornerSize(new Dimension(0, 0));
		addShapeModifyingKey(CORNER_SIZE_KEY);
		super.initProperties();
	}

	@Override
	protected Shape createStickyShape() {
		Dimension c = getCornerSize();
		if (c.width != 0 || c.height != 0) {
			Rectangle r = getBoundingBox();
			return new RoundRectangle2D.Float(r.x, r.y, r.width, r.height, c.width, c.height);
		}
		return super.createStickyShape();
	}

	@Override
	public Dimension getCornerSize() {
		Dimension d = get(CORNER_SIZE_KEY);
		if (d.width != 0 || d.height != 0) {
			Rectangle r = get(BOUNDING_BOX_KEY);
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
	public Shape getStickyShape() {
		Dimension c = getCornerSize();
		if (c.width != 0 || c.height != 0) {
			Rectangle r = getBoundingBox();
			return new RoundRectangle2D.Float(r.x, r.y, r.width, r.height, c.width, c.height);
		}
		return super.getStickyShape();
	}
}
