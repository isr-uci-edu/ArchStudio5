package org.archstudio.bna.things.glass;

import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.PathData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThing;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.facets.*;

public class PathGlassThing extends AbstractThing implements IHasMutablePathData, IMarqueeSelectable, IHasMutableSelected, IHasMutableUserEditable,
        IHasMutableRotatingOffset, IDragMovable, IHasBoundingBox, IHasMutableToolTip {

	public PathGlassThing(String id) {
		super(id);
		initProperties();
	}

	protected void initProperties() {
		setProperty(ROTATING_OFFSET_PROPERTY_NAME, 0);
		setSelected(false);
		setUserEditable(true);

		PathData pathData = new PathData();
		pathData.points = new float[0];
		pathData.types = new byte[0];
		setPathData(pathData);
	}

	public Point getReferencePoint() {
		PathData pathData = getPathData();
		float[] points = pathData.points;
		if (points.length >= 2) {
			return new Point(Math.round(points[0]), Math.round(points[1]));
		}
		else {
			return new Point(0, 0);
		}
	}

	public void setReferencePoint(Point p) {
		Point rp = getReferencePoint();
		int dx = rp.x - p.x;
		int dy = rp.y - p.y;
		PathData pathData = getPathData();
		float[] points = pathData.points;
		for (int i = 0; i < points.length; i += 2) {
			points[i] += dx;
			points[i + 1] += dy;
		}
		setPathData(pathData);
	}

	public void moveRelative(int dx, int dy) {
		PathData pathData = getPathData();
		float[] points = pathData.points;
		for (int i = 0; i < points.length; i += 2) {
			points[i] += dx;
			points[i + 1] += dy;
		}
		setPathData(pathData);
	}

	public void setSelected(boolean selected) {
		setProperty(SELECTED_PROPERTY_NAME, selected);
	}

	public boolean isSelected() {
		return getProperty(SELECTED_PROPERTY_NAME);
	}

	public boolean isUserEditable() {
		return getProperty(USER_EDITABLE_PROPERTY_NAME);
	}

	public void setUserEditable(boolean userEditable) {
		setProperty(USER_EDITABLE_PROPERTY_NAME, userEditable);
	}

	public int getRotatingOffset() {
		return getProperty(ROTATING_OFFSET_PROPERTY_NAME);
	}

	public void incrementRotatingOffset() {
		setProperty(ROTATING_OFFSET_PROPERTY_NAME, (getRotatingOffset() + 1) % 6);
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

	public Rectangle getBoundingBox() {
		Path path = null;
		try {
			path = new Path(null);
			BNAUtils.fillPath(path, getPathData(), null, null);
			return BNAUtils.getBounds(path);
		}
		finally {
			if (path != null)
				path.dispose();
			path = null;
		}
	}

	public String getToolTip() {
		return getProperty(TOOL_TIP_PROPERTY_NAME);
	}

	public void setToolTip(String toolTip) {
		setProperty(TOOL_TIP_PROPERTY_NAME, toolTip);
	}

}
