package org.archstudio.bna.facets;

import java.util.List;

import org.eclipse.swt.graphics.Point;

public interface IHasMutablePoints extends IHasPoints {

	public void setPoints(List<Point> points);

	public void setPoint(int index, Point point);
}
