package org.archstudio.bna.things.shapes;

import java.awt.Dimension;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.things.AbstractAnchorPointThing;
import org.eclipse.swt.graphics.RGB;

public class ReshapeHandleThing extends AbstractAnchorPointThing implements IHasMutableSize, IHasMutableColor {

	public ReshapeHandleThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setSize(new Dimension(8, 8));
		setColor(new RGB(0, 0, 255));
	}

	@Override
	public Dimension getSize() {
		return get(SIZE_KEY);
	}

	@Override
	public void setSize(Dimension size) {
		set(SIZE_KEY, size);
	}

	@Override
	public void setColor(RGB c) {
		set(COLOR_KEY, c);
	}

	@Override
	public RGB getColor() {
		return get(COLOR_KEY);
	}

}
