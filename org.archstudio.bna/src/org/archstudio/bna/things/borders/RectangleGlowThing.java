package org.archstudio.bna.things.borders;

import java.awt.Dimension;

import org.archstudio.bna.facets.IHasColor;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableSize;
import org.archstudio.bna.facets.IHasSize;
import org.archstudio.bna.things.AbstractRoundedRectangleThing;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

public class RectangleGlowThing extends AbstractRoundedRectangleThing implements IHasMutableColor, IHasMutableSize {

	public RectangleGlowThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setSize(new Dimension(10, 10));
		setColor(new RGB(255, 0, 0));
	}

	@Override
	public Dimension getSize() {
		return get(IHasSize.SIZE_KEY);
	}

	@Override
	public void setSize(Dimension size) {
		set(IHasSize.SIZE_KEY, size);
	}

	@Override
	public RGB getColor() {
		return get(IHasColor.COLOR_KEY);
	}

	@Override
	public void setColor(RGB color) {
		set(IHasColor.COLOR_KEY, color);
	}
}
