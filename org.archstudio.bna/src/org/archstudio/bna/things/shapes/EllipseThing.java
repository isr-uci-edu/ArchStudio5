package org.archstudio.bna.things.shapes;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableGradientFill;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.things.EllipseEssenceThing;
import org.eclipse.swt.graphics.RGB;

public class EllipseThing extends EllipseEssenceThing implements IHasMutableColor, IHasMutableSecondaryColor,
		IHasMutableGradientFill {

	public EllipseThing() {
		this(null);
	}

	public EllipseThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setGradientFilled(false);
	}

	public void setColor(RGB c) {
		put(COLOR_KEY, c);
	}

	public RGB getColor() {
		return get(COLOR_KEY);
	}

	public void setSecondaryColor(RGB c) {
		put(SECONDARY_COLOR_KEY, c);
	}

	public RGB getSecondaryColor() {
		return get(SECONDARY_COLOR_KEY);
	}

	public boolean isGradientFilled() {
		return get(GRADIENT_FILLED_KEY);
	}

	public void setGradientFilled(boolean newHasGradientFill) {
		put(GRADIENT_FILLED_KEY, newHasGradientFill);
	}
}
