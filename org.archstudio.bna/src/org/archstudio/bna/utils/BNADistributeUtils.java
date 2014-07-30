package org.archstudio.bna.utils;

import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Comparator;

import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Rectangle;

public class BNADistributeUtils {
	private BNADistributeUtils() {
	}

	static final XComparator xComparator = new XComparator();
	static final YComparator yComparator = new YComparator();

	public static double getX(IThing t) {
		if (t instanceof IHasBoundingBox) {
			return ((IHasBoundingBox) t).getBoundingBox().x;
		}
		else if (t instanceof IHasAnchorPoint) {
			return ((IHasAnchorPoint) t).getAnchorPoint().getX();
		}
		else {
			return 0;
		}
	}

	public static double getY(IThing t) {
		if (t instanceof IHasBoundingBox) {
			return ((IHasBoundingBox) t).getBoundingBox().y;
		}
		else if (t instanceof IHasAnchorPoint) {
			return ((IHasAnchorPoint) t).getAnchorPoint().getY();
		}
		else {
			return 0;
		}
	}

	public static int getWidth(IThing t) {
		if (t instanceof IHasBoundingBox) {
			return ((IHasBoundingBox) t).getBoundingBox().width;
		}
		else if (t instanceof IHasAnchorPoint) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public static int getHeight(IThing t) {
		if (t instanceof IHasBoundingBox) {
			return ((IHasBoundingBox) t).getBoundingBox().height;
		}
		else if (t instanceof IHasAnchorPoint) {
			return 1;
		}
		else {
			return 0;
		}
	}

	public static void distributeHorizontalTight(IThing[] distributableThings) {
		Arrays.sort(distributableThings, xComparator);
		double distributeAt = getX(distributableThings[0]) + getWidth(distributableThings[0]) + 10;
		for (int i = 1; i < distributableThings.length; i++) {
			if (distributableThings[i] instanceof IHasMutableBoundingBox) {
				Rectangle currentBounds = ((IHasMutableBoundingBox) distributableThings[i]).getBoundingBox();
				currentBounds.x = SystemUtils.round(distributeAt);
				((IHasMutableBoundingBox) distributableThings[i]).setBoundingBox(currentBounds);
				distributeAt = distributeAt + getWidth(distributableThings[i]) + 10;
			}
			else if (distributableThings[i] instanceof IHasMutableAnchorPoint
					&& distributableThings[i] instanceof IHasBoundingBox) {
				Rectangle currentBounds = ((IHasBoundingBox) distributableThings[i]).getBoundingBox();
				Point2D currentPoint = ((IHasAnchorPoint) distributableThings[i]).getAnchorPoint();
				currentPoint.setLocation(distributeAt + currentPoint.getX() - currentBounds.x, currentBounds.y);
				((IHasMutableAnchorPoint) distributableThings[i]).setAnchorPoint(currentPoint);
				distributeAt = distributeAt + getWidth(distributableThings[i]) + 10;
			}
			else if (distributableThings[i] instanceof IHasMutableAnchorPoint) {
				Point2D currentPoint = ((IHasMutableAnchorPoint) distributableThings[i]).getAnchorPoint();
				currentPoint.setLocation(distributeAt, currentPoint.getY());
				((IHasMutableAnchorPoint) distributableThings[i]).setAnchorPoint(currentPoint);
				distributeAt = distributeAt + 1 + 10;
			}
		}
	}

	public static void distributeVerticalTight(IThing[] distributableThings) {
		Arrays.sort(distributableThings, yComparator);
		double distributeAt = getY(distributableThings[0]) + getHeight(distributableThings[0]) + 10;
		for (int i = 1; i < distributableThings.length; i++) {
			if (distributableThings[i] instanceof IHasMutableBoundingBox) {
				Rectangle currentBounds = ((IHasMutableBoundingBox) distributableThings[i]).getBoundingBox();
				currentBounds.y = SystemUtils.round(distributeAt);
				((IHasMutableBoundingBox) distributableThings[i]).setBoundingBox(currentBounds);
				distributeAt = distributeAt + getHeight(distributableThings[i]) + 10;
			}
			else if (distributableThings[i] instanceof IHasMutableAnchorPoint
					&& distributableThings[i] instanceof IHasBoundingBox) {
				Rectangle currentBounds = ((IHasBoundingBox) distributableThings[i]).getBoundingBox();
				Point2D currentPoint = ((IHasMutableAnchorPoint) distributableThings[i]).getAnchorPoint();
				currentPoint.setLocation(currentPoint.getX(), distributeAt + currentPoint.getY() - currentBounds.y);
				((IHasMutableAnchorPoint) distributableThings[i]).setAnchorPoint(currentPoint);
				distributeAt = distributeAt + getHeight(distributableThings[i]) + 10;
			}
			else if (distributableThings[i] instanceof IHasMutableAnchorPoint) {
				Point2D currentPoint = ((IHasMutableAnchorPoint) distributableThings[i]).getAnchorPoint();
				currentPoint.setLocation(currentPoint.getX(), distributeAt);
				((IHasMutableAnchorPoint) distributableThings[i]).setAnchorPoint(currentPoint);
				distributeAt = distributeAt + 1 + 10;
			}
		}
	}

	public static void distributeHorizontalLoose(IThing[] distributableThings) {
		Arrays.sort(distributableThings, xComparator);

		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;

		int widthSum = 0;
		int count = 0;
		for (IThing t : distributableThings) {
			if (!(t instanceof IHasMutableBoundingBox || t instanceof IHasMutableAnchorPoint)) {
				continue;
			}
			count++;
			double x = getX(t);
			int width = getWidth(t);
			double x2 = x + width;
			min = Math.min(x, min);
			max = Math.max(x2, max);
			widthSum += width;
		}

		if (count == 0) {
			return;
		}

		double leftoverSpace = max - min - widthSum;
		if (leftoverSpace < (distributableThings.length - 1) * 10) {
			distributeHorizontalTight(distributableThings);
			return;
		}
		double delta = leftoverSpace / count;

		double distributeAt = getX(distributableThings[0]);
		for (IThing distributableThing : distributableThings) {
			if (distributableThing instanceof IHasMutableBoundingBox) {
				Rectangle currentBounds = ((IHasMutableBoundingBox) distributableThing).getBoundingBox();
				currentBounds.x = SystemUtils.round(distributeAt);
				((IHasMutableBoundingBox) distributableThing).setBoundingBox(currentBounds);
				distributeAt += currentBounds.width + delta;
			}
			else if (distributableThing instanceof IHasMutableAnchorPoint
					&& distributableThing instanceof IHasBoundingBox) {
				Rectangle currentBounds = ((IHasBoundingBox) distributableThing).getBoundingBox();
				Point2D currentPoint = ((IHasMutableAnchorPoint) distributableThing).getAnchorPoint();
				currentPoint.setLocation(
						distributeAt + currentPoint.getX() - currentBounds.x - currentBounds.width / 2,
						currentPoint.getY());
				((IHasMutableAnchorPoint) distributableThing).setAnchorPoint(currentPoint);
				distributeAt += currentBounds.width + delta;
			}
			else if (distributableThing instanceof IHasMutableAnchorPoint) {
				Point2D currentPoint = ((IHasMutableAnchorPoint) distributableThing).getAnchorPoint();
				currentPoint.setLocation(distributeAt, currentPoint.getY());
				((IHasMutableAnchorPoint) distributableThing).setAnchorPoint(currentPoint);
				distributeAt += 1 + delta;
			}
		}
	}

	public static void distributeVerticalLoose(IThing[] distributableThings) {
		Arrays.sort(distributableThings, yComparator);

		double min = Double.POSITIVE_INFINITY;
		double max = Double.NEGATIVE_INFINITY;

		int heightSum = 0;
		int count = 0;
		for (IThing t : distributableThings) {
			if (!(t instanceof IHasMutableBoundingBox || t instanceof IHasMutableAnchorPoint)) {
				continue;
			}
			count++;
			double y = getY(t);
			int height = getHeight(t);
			double y2 = y + height;
			min = Math.min(y, min);
			max = Math.max(y2, max);
			heightSum += height;
		}

		if (count == 0) {
			return;
		}

		double leftoverSpace = max - min - heightSum;
		if (leftoverSpace < (distributableThings.length - 1) * 10) {
			distributeVerticalTight(distributableThings);
			return;
		}
		double delta = leftoverSpace / count;

		double distributeAt = getY(distributableThings[0]);
		for (IThing distributableThing : distributableThings) {
			if (distributableThing instanceof IHasMutableBoundingBox) {
				Rectangle currentBounds = ((IHasMutableBoundingBox) distributableThing).getBoundingBox();
				currentBounds.y = SystemUtils.round(distributeAt);
				((IHasMutableBoundingBox) distributableThing).setBoundingBox(currentBounds);
				distributeAt += currentBounds.height + delta;
			}
			else if (distributableThing instanceof IHasMutableAnchorPoint
					&& distributableThing instanceof IHasBoundingBox) {
				Rectangle currentBounds = ((IHasBoundingBox) distributableThing).getBoundingBox();
				Point2D currentPoint = ((IHasMutableAnchorPoint) distributableThing).getAnchorPoint();
				currentPoint.setLocation(currentPoint.getX(), distributeAt - currentBounds.y + currentPoint.getY());
				((IHasMutableAnchorPoint) distributableThing).setAnchorPoint(currentPoint);
				distributeAt += currentBounds.height + delta;
			}
			else if (distributableThing instanceof IHasMutableAnchorPoint) {
				Point2D currentPoint = ((IHasMutableAnchorPoint) distributableThing).getAnchorPoint();
				currentPoint.setLocation(currentPoint.getX(), distributeAt);
				((IHasMutableAnchorPoint) distributableThing).setAnchorPoint(currentPoint);
				distributeAt += 1 + delta;
			}
		}
	}

	static class XComparator implements Comparator<IThing> {

		@Override
		public int compare(IThing o1, IThing o2) {
			double x1, x2;

			if (o1 instanceof IHasBoundingBox) {
				Rectangle r1 = ((IHasBoundingBox) o1).getBoundingBox();
				x1 = r1.x + r1.width / 2;
			}
			else if (o1 instanceof IHasAnchorPoint) {
				Point2D p1 = ((IHasAnchorPoint) o1).getAnchorPoint();
				x1 = p1.getX();
			}
			else {
				return 1;
			}

			if (o2 instanceof IHasBoundingBox) {
				Rectangle r2 = ((IHasBoundingBox) o2).getBoundingBox();
				x2 = r2.x + r2.width / 2;
			}
			else if (o2 instanceof IHasAnchorPoint) {
				Point2D p2 = ((IHasAnchorPoint) o2).getAnchorPoint();
				x2 = p2.getX();
			}
			else {
				return -1;
			}

			if (x1 < x2) {
				return -1;
			}
			else if (x1 == x2) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}

	static class YComparator implements Comparator<IThing> {

		@Override
		public int compare(IThing o1, IThing o2) {
			double y1, y2;

			if (o1 instanceof IHasBoundingBox) {
				Rectangle r1 = ((IHasBoundingBox) o1).getBoundingBox();
				y1 = r1.y + r1.height / 2;
			}
			else if (o1 instanceof IHasAnchorPoint) {
				Point2D p1 = ((IHasAnchorPoint) o1).getAnchorPoint();
				y1 = p1.getY();
			}
			else {
				return 1;
			}

			if (o2 instanceof IHasBoundingBox) {
				Rectangle r2 = ((IHasBoundingBox) o2).getBoundingBox();
				y2 = r2.y + r2.height / 2;
			}
			else if (o2 instanceof IHasAnchorPoint) {
				Point2D p2 = ((IHasAnchorPoint) o2).getAnchorPoint();
				y2 = p2.getY();
			}
			else {
				return -1;
			}

			if (y1 < y2) {
				return -1;
			}
			else if (y1 == y2) {
				return 0;
			}
			else {
				return 1;
			}
		}
	}

}
