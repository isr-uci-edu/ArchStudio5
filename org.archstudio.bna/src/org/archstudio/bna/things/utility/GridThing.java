package org.archstudio.bna.things.utility;

import org.eclipse.swt.graphics.RGB;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.constants.GridDisplayType;
import org.archstudio.bna.facets.IHasMutableColor;

public class GridThing extends AbstractThing implements IHasMutableColor {
	public static final String GRID_SPACING_PROPERTY_NAME = "gridSpacing";
	public static final String GRID_DISPLAY_TYPE_PROPERTY_NAME = "gridDisplayType";

	public GridThing() {
		this(BNAUtils.generateUID(GridThing.class.getName()));
	}

	public GridThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setGridSpacing(0);
		setGridDisplayType(GridDisplayType.NONE);
	}

	public void setGridSpacing(int gridSpacing) {
		setProperty(GRID_SPACING_PROPERTY_NAME, gridSpacing);
	}

	public int getGridSpacing() {
		return getProperty(GRID_SPACING_PROPERTY_NAME);
	}

	public void setGridDisplayType(GridDisplayType gridDisplayType) {
		setProperty(GRID_DISPLAY_TYPE_PROPERTY_NAME, gridDisplayType);
	}

	public GridDisplayType getGridDisplayType() {
		return getProperty(GRID_DISPLAY_TYPE_PROPERTY_NAME);
	}

	public void setColor(RGB c) {
		setProperty(COLOR_PROPERTY_NAME, c);
	}

	public RGB getColor() {
		return getProperty(COLOR_PROPERTY_NAME);
	}

}
