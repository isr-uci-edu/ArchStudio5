package org.archstudio.bna.things.borders;

import org.archstudio.bna.facets.IHasGlowData;
import org.archstudio.bna.facets.IHasMutableAlpha;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableWidth;
import org.archstudio.bna.things.AbstractSplineThing;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

public class SplineGlowThing extends AbstractSplineThing implements IHasMutableColor, IHasMutableWidth,
		IHasMutableAlpha, IHasGlowData {

	public SplineGlowThing(@Nullable Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		setWidth(10);
		setColor(new RGB(255, 0, 0));
		setAlpha(0.5f);
		super.initProperties();
	}

	@Override
	public int getWidth() {
		return get(WIDTH_KEY);
	}

	@Override
	public void setWidth(int width) {
		set(WIDTH_KEY, width);
	}

	@Override
	public RGB getColor() {
		return get(COLOR_KEY);
	}

	@Override
	public void setColor(RGB color) {
		set(COLOR_KEY, color);
	}

	@Override
	public float getAlpha() {
		return get(ALPHA_KEY);
	}

	@Override
	public void setAlpha(float alpha) {
		set(ALPHA_KEY, alpha);
	}
}
