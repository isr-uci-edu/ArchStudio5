package org.archstudio.bna.things.utility;

import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.facets.IHasMutableEdgeColor;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.things.AbstractThing;
import org.eclipse.swt.graphics.RGB;

public class GridThing extends AbstractThing implements IHasMutableEdgeColor {

	public static final IThingKey<Integer> GRID_SPACING_KEY = ThingKey.create("gridSpacing");
	public static final IThingKey<GridDisplayType> GRID_DISPLAY_TYPE_KEY = ThingKey.create("gridDisplayType");

	public static GridThing createIn(IBNAWorld world) {
		GridThing thing = getIn(world);
		if (thing == null) {
			thing = world.getBNAModel().addThing(new GridThing());
		}
		return thing;
	}

	public static GridThing getIn(IBNAWorld world) {
		return (GridThing) world.getBNAModel().getThing(GridThing.class);
	}

	protected GridThing() {
		super(GridThing.class);
	}

	@Override
	protected void initProperties() {
		setEdgeColor(new RGB(192, 192, 192));
		setGridSpacing(2 * 2 * 2 * 3); // supports subdividing by 2, 4, 8, 3, 6, etc.
		setGridDisplayType(GridDisplayType.DOTTED_LINES);
		super.initProperties();
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

	@Override
	public void setEdgeColor(RGB c) {
		set(EDGE_COLOR_KEY, c);
	}

	@Override
	public RGB getEdgeColor() {
		return get(EDGE_COLOR_KEY);
	}

}
