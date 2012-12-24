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

	protected void initProperties() {
		super.initProperties();
		setColor(new RGB(192, 192, 192));
		setGridSpacing(2 * 2 * 2 * 3); // supports subdividing by 2, 4, 8, 3, 6, etc.
		setGridDisplayType(GridDisplayType.DOTTED_LINES);
	}

	public void setGridSpacing(int gridSpacing) {
		set(GRID_SPACING_KEY, gridSpacing);
	}

	public int getGridSpacing() {
		return get(GRID_SPACING_KEY);
	}

	public void setGridDisplayType(GridDisplayType gridDisplayType) {
		set(GRID_DISPLAY_TYPE_KEY, gridDisplayType);
	}

	public GridDisplayType getGridDisplayType() {
		return get(GRID_DISPLAY_TYPE_KEY);
	}

	public void setColor(RGB c) {
		set(COLOR_KEY, c);
	}

	public RGB getColor() {
		return get(COLOR_KEY);
	}

}
