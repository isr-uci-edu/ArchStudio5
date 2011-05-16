package org.archstudio.bna.things.utility;

import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.things.AbstractThing;
import org.eclipse.swt.graphics.RGB;

public class GridThing extends AbstractThing implements IHasMutableColor {

	public static final IThingKey<Integer> GRID_SPACING_KEY = ThingKey.create("gridSpacing");
	public static final IThingKey<GridDisplayType> GRID_DISPLAY_TYPE_KEY = ThingKey.create("gridDisplayType");

	public GridThing(Object id) {
		super(id);
	}

	@Override
	protected void initProperties() {
		super.initProperties();
		setGridSpacing(0);
		setGridDisplayType(GridDisplayType.NONE);
	}

	public void setGridSpacing(int gridSpacing) {
		put(GRID_SPACING_KEY, gridSpacing);
	}

	public int getGridSpacing() {
		return get(GRID_SPACING_KEY);
	}

	public void setGridDisplayType(GridDisplayType gridDisplayType) {
		put(GRID_DISPLAY_TYPE_KEY, gridDisplayType);
	}

	public GridDisplayType getGridDisplayType() {
		return get(GRID_DISPLAY_TYPE_KEY);
	}

	public void setColor(RGB c) {
		put(COLOR_KEY, c);
	}

	public RGB getColor() {
		return get(COLOR_KEY);
	}

}
