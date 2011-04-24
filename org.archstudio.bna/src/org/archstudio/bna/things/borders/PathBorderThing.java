package org.archstudio.bna.things.borders;

import org.eclipse.swt.graphics.PathData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.facets.IHasMutableColor;
import org.archstudio.bna.facets.IHasMutableLineStyle;
import org.archstudio.bna.facets.IHasMutableLineWidth;
import org.archstudio.bna.facets.IMutableMirrorsPathData;

public class PathBorderThing extends AbstractThing implements IHasMutableColor, IHasMutableLineStyle, IHasMutableLineWidth, IMutableMirrorsPathData {

	public PathBorderThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		PathData pathData = new PathData();
		pathData.points = new float[0];
		pathData.types = new byte[0];
		setPathData(pathData);
		setPathDataMirrorOffsets(new Point(0, 0));

		setLineStyle(LINE_STYLE_SOLID);
		setLineWidth(1);
	}

	public void setColor(RGB c) {
		setProperty(COLOR_PROPERTY_NAME, c);
	}

	public RGB getColor() {
		return getProperty(COLOR_PROPERTY_NAME);
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

	public int getLineStyle() {
		return getProperty(LINE_STYLE_PROPERTY_NAME);
	}

	public void setLineStyle(int lineStyle) {
		setProperty(LINE_STYLE_PROPERTY_NAME, lineStyle);
	}

	public int getLineWidth() {
		return getProperty(LINE_WIDTH_PROPERTY_NAME);
	}

	public void setLineWidth(int lineWidth) {
		setProperty(LINE_WIDTH_PROPERTY_NAME, lineWidth);
	}
}
