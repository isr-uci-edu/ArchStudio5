package org.archstudio.bna.things.shapes;

import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableOrientation;
import org.archstudio.bna.things.AbstractThing;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.swt.graphics.RGB;

public class ReshapeHandleThing extends AbstractThing implements IHasMutableColor, IHasMutableAnchorPoint,
		IHasMutableOrientation {

	public ReshapeHandleThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setAnchorPoint(new Point(0, 0));
		setOrientation(Orientation.NONE);
	}

	public void setColor(RGB c) {
		put(COLOR_KEY, c);
	}

	public RGB getColor() {
		return get(COLOR_KEY);
	}

	public Point getAnchorPoint() {
		Point p = get(ANCHOR_POINT_KEY);
		return new Point(p.x, p.y);
	}

	public void setAnchorPoint(Point p) {
		put(ANCHOR_POINT_KEY, p);
	}

	public Orientation getOrientation() {
		return get(ORIENTATION_KEY);
	}

	public void setOrientation(Orientation orientation) {
		put(ORIENTATION_KEY, orientation);
	}
}