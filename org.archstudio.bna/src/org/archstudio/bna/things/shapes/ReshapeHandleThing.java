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

	protected void initProperties() {
		super.initProperties();
		setSize(new Dimension(8, 8));
		setColor(new RGB(0, 0, 255));
	}

	public Dimension getSize() {
		return get(SIZE_KEY);
	}

	public void setSize(Dimension size) {
		set(SIZE_KEY, size);
	}

	public void setColor(RGB c) {
		set(COLOR_KEY, c);
	}

	public RGB getColor() {
		return get(COLOR_KEY);
	}

}
