package org.archstudio.bna.things.shapes;

import org.eclipse.swt.graphics.PathData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableGradientFill;
import org.archstudio.bna.facets.IHasMutableSecondaryColor;
import org.archstudio.bna.facets.IMirrorsPathData;

public class PathThing extends AbstractThing implements IHasMutableColor, IHasMutableSecondaryColor, IHasMutableGradientFill, IMirrorsPathData {

	public static final String PATH_DATA_PROPERTY_NAME = "pathData";

	public PathThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setGradientFilled(false);

		PathData pathData = new PathData();
		pathData.points = new float[0];
		pathData.types = new byte[0];
		setPathData(pathData);
		setPathDataMirrorOffsets(new Point(0, 0));
	}

	public void setColor(RGB c) {
		setProperty(COLOR_PROPERTY_NAME, c);
	}

	public RGB getColor() {
		return getProperty(COLOR_PROPERTY_NAME);
	}

	public void setSecondaryColor(RGB c) {
		setProperty(SECONDARY_COLOR_PROPERTY_NAME, c);
	}

	public RGB getSecondaryColor() {
		return getProperty(SECONDARY_COLOR_PROPERTY_NAME);
	}

	public boolean isGradientFilled() {
		return getProperty(GRADIENT_FILLED_PROPERTY_NAME);
	}

	public void setGradientFilled(boolean newHasGradientFill) {
		setProperty(GRADIENT_FILLED_PROPERTY_NAME, newHasGradientFill);
	}

	public void setPathData(PathData pathData) {
		PathData pathDataCopy = new PathData();
		pathDataCopy.types = pathData.types.clone();
		pathDataCopy.points = pathData.points.clone();
		setProperty(PATH_DATA_PROPERTY_NAME, pathDataCopy);
	}

	public PathData getPathData() {
		PathData pathData = getProperty(PATH_DATA_PROPERTY_NAME);
		PathData pathDataCopy = new PathData();
		pathDataCopy.types = pathData.types.clone();
		pathDataCopy.points = pathData.points.clone();
		return pathDataCopy;
	}

	public String getPathDataMasterThingID() {
		return (String) getProperty(PATH_DATA_MASTER_THING_ID_PROPERTY_NAME);
	}

	public void setPathDataMasterThingID(String pathMasterThingID) {
		setProperty(PATH_DATA_MASTER_THING_ID_PROPERTY_NAME, pathMasterThingID);
	}

	public Point getPathDataMirrorOffsets() {
		return getProperty(PATH_DATA_MIRROR_OFFSETS_PROPETY_NAME);
	}

	public void setPathDataMirrorOffsets(Point newOffsets) {
		setProperty(PATH_DATA_MIRROR_OFFSETS_PROPETY_NAME, newOffsets);
	}
}
