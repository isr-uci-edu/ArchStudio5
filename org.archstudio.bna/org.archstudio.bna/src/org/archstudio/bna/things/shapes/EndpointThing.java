package org.archstudio.bna.things.shapes;

import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.facets.IHasMutableGradientFill;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.things.AbstractEndpointThing;
import org.eclipse.swt.graphics.RGB;

public class EndpointThing extends AbstractEndpointThing implements IHasMutableColor, IHasMutableSecondaryColor,
		IHasMutableGradientFill, IHasMutableEdgeColor {

	public EndpointThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setColor(new RGB(255, 255, 255));
		setSecondaryColor(new RGB(192, 192, 192));
		setGradientFilled(true);
		setEdgeColor(new RGB(0, 0, 0));
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
	public void setSecondaryColor(RGB c) {
		set(SECONDARY_COLOR_KEY, c);
	}

	@Override
	public RGB getSecondaryColor() {
		return get(SECONDARY_COLOR_KEY);
	}

	@Override
	public boolean isGradientFilled() {
		return get(GRADIENT_FILLED_KEY);
	}

	@Override
	public void setGradientFilled(boolean newHasGradientFill) {
		set(GRADIENT_FILLED_KEY, newHasGradientFill);
	}

	@Override
	public void setEdgeColor(RGB c) {
		set(EDGE_COLOR_KEY, c);
	}

	@Override
	public RGB getEdgeColor() {
		return get(EDGE_COLOR_KEY);
	}
}
