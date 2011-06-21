package org.archstudio.bna.things.shapes;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableOrientation;
import org.archstudio.bna.things.AbstractBoundedAnchorPointThing;
import org.archstudio.swtutils.constants.Orientation;
import org.eclipse.swt.graphics.RGB;

public class ReshapeHandleThing extends AbstractBoundedAnchorPointThing implements IHasMutableColor,
		IHasMutableOrientation {

	public ReshapeHandleThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setColor(new RGB(0, 0, 255));
		setOrientation(Orientation.NONE);
	}

	@Override
	public void setColor(RGB c) {
		set(COLOR_KEY, c);
	}

	@Override
	public RGB getColor() {
		return get(COLOR_KEY);
	}

	@Override
	public Orientation getOrientation() {
		return get(ORIENTATION_KEY);
	}

	@Override
	public void setOrientation(Orientation orientation) {
		set(ORIENTATION_KEY, orientation);
	}
}