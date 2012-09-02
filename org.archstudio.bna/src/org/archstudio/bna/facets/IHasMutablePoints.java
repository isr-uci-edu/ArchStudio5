package org.archstudio.bna.facets;

import java.util.List;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.swt.graphics.Point;

@NonNullByDefault
public interface IHasMutablePoints extends IHasPoints {

	public void setPoints(List<Point> points);

	public void setPoint(int index, Point point);
}
