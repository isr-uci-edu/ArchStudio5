package org.archstudio.bna;

import java.util.Collection;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;

public class BNAAlignUtils {
	private BNAAlignUtils() {
	}

	public static void align(Collection<IThing> things, VerticalAlignment a) {
		switch (a) {
		case TOP: {
			int y = Integer.MAX_VALUE;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					if ((boundingBox.y) < y)
						y = boundingBox.y;
				}
				else if (t instanceof IHasAnchorPoint) {
					Point anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					if (anchorPoint.y < y)
						y = anchorPoint.y;
				}
			}
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					Rectangle newBoundingBox = new Rectangle(boundingBox.x, y, boundingBox.width, boundingBox.height);
					((IHasMutableBoundingBox) t).setBoundingBox(newBoundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					Point newAnchorPoint = new Point(anchorPoint.x, y);
					((IHasMutableAnchorPoint) t).setAnchorPoint(newAnchorPoint);
				}
			}
		}
			break;
		case MIDDLE: {
			int y = 0;
			int i = 0;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					y = y + (boundingBox.y + (boundingBox.height / 2));
					i++;
				}
				else if (t instanceof IHasAnchorPoint) {
					Point anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					y = y + anchorPoint.y;
					i++;
				}
			}
			y = y / i;
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					Rectangle newBoundingBox = new Rectangle(boundingBox.x, y - (boundingBox.height / 2), boundingBox.width, boundingBox.height);
					((IHasMutableBoundingBox) t).setBoundingBox(newBoundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					Point newAnchorPoint = new Point(anchorPoint.x, y);
					((IHasMutableAnchorPoint) t).setAnchorPoint(newAnchorPoint);
				}
			}
		}
			break;
		case BOTTOM: {
			int y = Integer.MIN_VALUE;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					if (boundingBox.y + boundingBox.height > y)
						y = boundingBox.y + boundingBox.height;
				}
				else if (t instanceof IHasAnchorPoint) {
					Point anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					if (anchorPoint.y > y)
						y = anchorPoint.y;
				}
			}
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					Rectangle newBoundingBox = new Rectangle(boundingBox.x, y - boundingBox.height, boundingBox.width, boundingBox.height);
					((IHasMutableBoundingBox) t).setBoundingBox(newBoundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					Point newAnchorPoint = new Point(anchorPoint.x, y);
					((IHasMutableAnchorPoint) t).setAnchorPoint(newAnchorPoint);
				}
			}
		}
			break;
		}
	}

	public static void align(Collection<IThing> things, HorizontalAlignment a) {
		switch (a) {
		case LEFT: {
			int x = Integer.MAX_VALUE;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					if ((boundingBox.x) < x)
						x = boundingBox.x;
				}
				else if (t instanceof IHasAnchorPoint) {
					Point anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					if (anchorPoint.y < x)
						x = anchorPoint.x;
				}
			}
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					Rectangle newBoundingBox = new Rectangle(x, boundingBox.y, boundingBox.width, boundingBox.height);
					((IHasMutableBoundingBox) t).setBoundingBox(newBoundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					Point newAnchorPoint = new Point(x, anchorPoint.y);
					((IHasMutableAnchorPoint) t).setAnchorPoint(newAnchorPoint);
				}
			}
		}
			break;
		case CENTER: {
			int x = 0;
			int i = 0;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					x = x + (boundingBox.x + (boundingBox.width / 2));
					i++;
				}
				else if (t instanceof IHasAnchorPoint) {
					Point anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					x = x + anchorPoint.x;
					i++;
				}
			}
			x = x / i;
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					Rectangle newBoundingBox = new Rectangle(x - (boundingBox.width / 2), boundingBox.y, boundingBox.width, boundingBox.height);
					((IHasMutableBoundingBox) t).setBoundingBox(newBoundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					Point newAnchorPoint = new Point(x, anchorPoint.y);
					((IHasMutableAnchorPoint) t).setAnchorPoint(newAnchorPoint);
				}
			}
		}
			break;
		case RIGHT: {
			int x = Integer.MIN_VALUE;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					if (boundingBox.x + boundingBox.width > x)
						x = boundingBox.x + boundingBox.width;
				}
				else if (t instanceof IHasAnchorPoint) {
					Point anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					if (anchorPoint.x > x)
						x = anchorPoint.x;
				}
			}
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					Rectangle newBoundingBox = new Rectangle(x - boundingBox.width, boundingBox.y, boundingBox.width, boundingBox.height);
					((IHasMutableBoundingBox) t).setBoundingBox(newBoundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					Point newAnchorPoint = new Point(x, anchorPoint.y);
					((IHasMutableAnchorPoint) t).setAnchorPoint(newAnchorPoint);
				}
			}
		}
			break;
		}
	}
}
