package org.archstudio.bna;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableBoundingBox;

public class BNADistributeUtils {
	private BNADistributeUtils() {
	}

	static final XComparator xComparator = new XComparator();
	static final YComparator yComparator = new YComparator();

	public static int getX(IThing t) {
		if (t instanceof IHasBoundingBox) {
			return ((IHasBoundingBox) t).getBoundingBox().x;
		}
		else if (t instanceof IHasAnchorPoint) {
			return ((IHasAnchorPoint) t).getAnchorPoint().x;
		}
		else
			return 0;
	}

	public static int getY(IThing t) {
		if (t instanceof IHasBoundingBox) {
			return ((IHasBoundingBox) t).getBoundingBox().y;
		}
		else if (t instanceof IHasAnchorPoint) {
			return ((IHasAnchorPoint) t).getAnchorPoint().y;
		}
		else
			return 0;
	}

	public static int getWidth(IThing t) {
		if (t instanceof IHasBoundingBox) {
			return ((IHasBoundingBox) t).getBoundingBox().width;
		}
		else if (t instanceof IHasAnchorPoint) {
			return 1;
		}
		else
			return 0;
	}

	public static int getHeight(IThing t) {
		if (t instanceof IHasBoundingBox) {
			return ((IHasBoundingBox) t).getBoundingBox().height;
		}
		else if (t instanceof IHasAnchorPoint) {
			return 1;
		}
		else
			return 0;
	}

	public static void distributeHorizontalTight(List<IThing> distributableThings) {
		distributableThings = new ArrayList<IThing>(distributableThings);
		Collections.sort(distributableThings, xComparator);
		int distributeAt = getX(distributableThings.get(0)) + getWidth(distributableThings.get(0)) + 10;
		for (int i = 1; i < distributableThings.size(); i++) {
			if (distributableThings.get(i) instanceof IHasMutableBoundingBox) {
				Rectangle currentBounds = ((IHasBoundingBox) distributableThings.get(i)).getBoundingBox();
				currentBounds.x = distributeAt;
				((IHasMutableBoundingBox) distributableThings.get(i)).setBoundingBox(currentBounds);
			}
			else if ((distributableThings.get(i) instanceof IHasMutableAnchorPoint) && (distributableThings.get(i) instanceof IHasBoundingBox)) {
				Rectangle currentBounds = ((IHasBoundingBox) distributableThings.get(i)).getBoundingBox();
				Point currentPoint = ((IHasAnchorPoint) distributableThings.get(i)).getAnchorPoint();
				currentPoint.x = distributeAt + (currentPoint.x - currentBounds.x);
				((IHasMutableAnchorPoint) distributableThings.get(i)).setAnchorPoint(currentPoint);
			}
			else if (distributableThings.get(i) instanceof IHasMutableAnchorPoint) {
				Point currentPoint = ((IHasAnchorPoint) distributableThings.get(i)).getAnchorPoint();
				currentPoint.x = distributeAt;
				((IHasMutableAnchorPoint) distributableThings.get(i)).setAnchorPoint(currentPoint);
			}
			distributeAt = distributeAt + getWidth(distributableThings.get(i)) + 10;
		}
	}

	public static void distributeVerticalTight(List<IThing> distributableThings) {
		distributableThings = new ArrayList<IThing>(distributableThings);
		Collections.sort(distributableThings, yComparator);
		int distributeAt = getY(distributableThings.get(0)) + getHeight(distributableThings.get(0)) + 10;
		for (int i = 1; i < distributableThings.size(); i++) {
			if (distributableThings.get(i) instanceof IHasMutableBoundingBox) {
				Rectangle currentBounds = ((IHasBoundingBox) distributableThings.get(i)).getBoundingBox();
				currentBounds.y = distributeAt;
				((IHasMutableBoundingBox) distributableThings.get(i)).setBoundingBox(currentBounds);
			}
			else if ((distributableThings.get(i) instanceof IHasMutableAnchorPoint) && (distributableThings.get(i) instanceof IHasBoundingBox)) {
				Rectangle currentBounds = ((IHasBoundingBox) distributableThings.get(i)).getBoundingBox();
				Point currentPoint = ((IHasAnchorPoint) distributableThings.get(i)).getAnchorPoint();
				currentPoint.y = distributeAt + (currentPoint.y - currentBounds.y);
				((IHasMutableAnchorPoint) distributableThings.get(i)).setAnchorPoint(currentPoint);
			}
			else if (distributableThings.get(i) instanceof IHasMutableAnchorPoint) {
				Point currentPoint = ((IHasAnchorPoint) distributableThings.get(i)).getAnchorPoint();
				currentPoint.y = distributeAt;
				((IHasMutableAnchorPoint) distributableThings.get(i)).setAnchorPoint(currentPoint);
			}
			distributeAt = distributeAt + getHeight(distributableThings.get(i)) + 10;
		}
	}

	public static void distributeHorizontalLoose(List<IThing> distributableThings) {
		distributableThings = new ArrayList<IThing>(distributableThings);
		Collections.sort(distributableThings, xComparator);

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		int widthSum = 0;
		for (int i = 0; i < distributableThings.size(); i++) {
			int x = getX(distributableThings.get(i));
			int width = getWidth(distributableThings.get(i));
			int x2 = x + width;

			if (x < min)
				min = x;
			if (x2 > max)
				max = x2;
			widthSum += width;
		}

		int leftoverSpace = (max - min) - widthSum;
		if (leftoverSpace < ((distributableThings.size() - 1) * 10)) {
			distributeHorizontalTight(distributableThings);
			return;
		}
		int delta = leftoverSpace / (distributableThings.size() - 1);

		int distributeAt = getX(distributableThings.get(0)) + getWidth(distributableThings.get(0)) + delta;
		for (int i = 1; i < distributableThings.size(); i++) {
			if (distributableThings.get(i) instanceof IHasMutableBoundingBox) {
				Rectangle currentBounds = ((IHasBoundingBox) distributableThings.get(i)).getBoundingBox();
				currentBounds.x = distributeAt;
				((IHasMutableBoundingBox) distributableThings.get(i)).setBoundingBox(currentBounds);
				distributeAt += currentBounds.width;
			}
			else if ((distributableThings.get(i) instanceof IHasMutableAnchorPoint) && (distributableThings.get(i) instanceof IHasBoundingBox)) {
				Rectangle currentBounds = ((IHasBoundingBox) distributableThings.get(i)).getBoundingBox();
				Point currentPoint = ((IHasAnchorPoint) distributableThings.get(i)).getAnchorPoint();
				currentPoint.x = distributeAt + (currentPoint.x - currentBounds.x) - (currentBounds.width / 2);
				((IHasMutableAnchorPoint) distributableThings.get(i)).setAnchorPoint(currentPoint);
				distributeAt += currentBounds.width;
			}
			else if (distributableThings.get(i) instanceof IHasMutableAnchorPoint) {
				Point currentPoint = ((IHasAnchorPoint) distributableThings.get(i)).getAnchorPoint();
				currentPoint.x = distributeAt;
				((IHasMutableAnchorPoint) distributableThings.get(i)).setAnchorPoint(currentPoint);
				distributeAt += 1;
			}
			distributeAt += delta;
		}
	}

	public static void distributeVerticalLoose(List<IThing> distributableThings) {
		distributableThings = new ArrayList<IThing>(distributableThings);
		Collections.sort(distributableThings, yComparator);

		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;

		int heightSum = 0;
		for (int i = 0; i < distributableThings.size(); i++) {
			int y = getY(distributableThings.get(i));
			int height = getHeight(distributableThings.get(i));
			int y2 = y + height;

			if (y < min)
				min = y;
			if (y2 > max)
				max = y2;
			heightSum += height;
		}

		int leftoverSpace = (max - min) - heightSum;
		if (leftoverSpace < ((distributableThings.size() - 1) * 10)) {
			distributeVerticalTight(distributableThings);
			return;
		}
		int delta = leftoverSpace / (distributableThings.size() - 1);

		int distributeAt = getY(distributableThings.get(0)) + getHeight(distributableThings.get(0)) + delta;
		for (int i = 1; i < distributableThings.size(); i++) {
			if (distributableThings.get(i) instanceof IHasMutableBoundingBox) {
				Rectangle currentBounds = ((IHasBoundingBox) distributableThings.get(i)).getBoundingBox();
				currentBounds.y = distributeAt;
				((IHasMutableBoundingBox) distributableThings.get(i)).setBoundingBox(currentBounds);
				distributeAt += currentBounds.height;
			}
			else if ((distributableThings.get(i) instanceof IHasMutableAnchorPoint) && (distributableThings.get(i) instanceof IHasBoundingBox)) {
				Rectangle currentBounds = ((IHasBoundingBox) distributableThings.get(i)).getBoundingBox();
				Point currentPoint = ((IHasAnchorPoint) distributableThings.get(i)).getAnchorPoint();
				currentPoint.y = distributeAt + (currentPoint.y - currentBounds.y) - (currentBounds.height / 2);
				((IHasMutableAnchorPoint) distributableThings.get(i)).setAnchorPoint(currentPoint);
				distributeAt += currentBounds.height;
			}
			else if (distributableThings.get(i) instanceof IHasMutableAnchorPoint) {
				Point currentPoint = ((IHasAnchorPoint) distributableThings.get(i)).getAnchorPoint();
				currentPoint.y = distributeAt;
				((IHasMutableAnchorPoint) distributableThings.get(i)).setAnchorPoint(currentPoint);
				distributeAt += 1;
			}
			distributeAt += delta;
		}
	}

	static class XComparator implements Comparator<IThing> {
		public int compare(IThing o1, IThing o2) {
			int x1, x2;

			if (o1 instanceof IHasBoundingBox) {
				Rectangle r1 = ((IHasBoundingBox) o1).getBoundingBox();
				x1 = r1.x + (r1.width / 2);
			}
			else if (o1 instanceof IHasAnchorPoint) {
				Point p1 = ((IHasAnchorPoint) o1).getAnchorPoint();
				x1 = p1.x;
			}
			else {
				return 1;
			}

			if (o2 instanceof IHasBoundingBox) {
				Rectangle r2 = ((IHasBoundingBox) o2).getBoundingBox();
				x2 = r2.x + (r2.width / 2);
			}
			else if (o2 instanceof IHasAnchorPoint) {
				Point p2 = ((IHasAnchorPoint) o2).getAnchorPoint();
				x2 = p2.x;
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
		public int compare(IThing o1, IThing o2) {
			int y1, y2;

			if (o1 instanceof IHasBoundingBox) {
				Rectangle r1 = ((IHasBoundingBox) o1).getBoundingBox();
				y1 = r1.y + (r1.height / 2);
			}
			else if (o1 instanceof IHasAnchorPoint) {
				Point p1 = ((IHasAnchorPoint) o1).getAnchorPoint();
				y1 = p1.y;
			}
			else {
				return 1;
			}

			if (o2 instanceof IHasBoundingBox) {
				Rectangle r2 = ((IHasBoundingBox) o2).getBoundingBox();
				y2 = r2.y + (r2.height / 2);
			}
			else if (o2 instanceof IHasAnchorPoint) {
				Point p2 = ((IHasAnchorPoint) o2).getAnchorPoint();
				y2 = p2.y;
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
