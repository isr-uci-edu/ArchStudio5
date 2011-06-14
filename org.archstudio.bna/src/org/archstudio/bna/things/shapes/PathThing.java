package org.archstudio.bna.things.shapes;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableGradientFill;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.things.PathEssenceThing;
import org.eclipse.swt.graphics.RGB;

public class PathThing extends PathEssenceThing implements IHasMutableColor, IHasMutableSecondaryColor,
		IHasMutableGradientFill {

	public PathThing() {
		this(null);
	}

	public PathThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setGradientFilled(false);
	}

	public RGB getColor() {
		return get(COLOR_KEY);
	}

	public void setColor(RGB c) {
		set(COLOR_KEY, c);
	}

	public RGB getSecondaryColor() {
		return get(SECONDARY_COLOR_KEY);
	}

	public void setSecondaryColor(RGB c) {
		set(SECONDARY_COLOR_KEY, c);
	}

	public boolean isGradientFilled() {
		return get(GRADIENT_FILLED_KEY);
	}

	public void setGradientFilled(boolean newHasGradientFill) {
		set(GRADIENT_FILLED_KEY, newHasGradientFill);
	}
}
