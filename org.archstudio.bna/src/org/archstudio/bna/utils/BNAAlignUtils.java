package org.archstudio.bna.utils;

import java.awt.geom.Point2D;

import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.facets.IHasMutableBoundingBox;
import org.archstudio.swtutils.constants.HorizontalAlignment;
import org.archstudio.swtutils.constants.VerticalAlignment;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.Rectangle;

public class BNAAlignUtils {
	private BNAAlignUtils() {
	}

	public static void align(IThing[] things, VerticalAlignment a) {
		switch (a) {
		case TOP: {
			double y = Double.POSITIVE_INFINITY;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					y = Math.min(boundingBox.y, y);
				}
				else if (t instanceof IHasAnchorPoint) {
					Point2D anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					y = Math.min(anchorPoint.getY(), y);
				}
			}
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					boundingBox.y = SystemUtils.round(y);
					((IHasMutableBoundingBox) t).setBoundingBox(boundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point2D anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					anchorPoint.setLocation(anchorPoint.getX(), y);
					((IHasMutableAnchorPoint) t).setAnchorPoint(anchorPoint);
				}
			}
		}
			break;
		case MIDDLE: {
			double y = 0;
			int i = 0;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					y += boundingBox.y + boundingBox.height / 2;
					i++;
				}
				else if (t instanceof IHasAnchorPoint) {
					Point2D anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					y += anchorPoint.getY();
					i++;
				}
			}
			y = y / i;
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					boundingBox.y = SystemUtils.round(y) - boundingBox.height / 2;
					((IHasMutableBoundingBox) t).setBoundingBox(boundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point2D anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					anchorPoint.setLocation(anchorPoint.getX(), y);
					((IHasMutableAnchorPoint) t).setAnchorPoint(anchorPoint);
				}
			}
		}
			break;
		case BOTTOM: {
			double y = Double.NEGATIVE_INFINITY;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					y = Math.max(boundingBox.y + boundingBox.height, y);
				}
				else if (t instanceof IHasAnchorPoint) {
					Point2D anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					y = Math.max(anchorPoint.getY(), y);
				}
			}
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					boundingBox.y = SystemUtils.round(y) - boundingBox.height;
					((IHasMutableBoundingBox) t).setBoundingBox(boundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point2D anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					anchorPoint.setLocation(anchorPoint.getX(), y);
					((IHasMutableAnchorPoint) t).setAnchorPoint(anchorPoint);
				}
			}
		}
			break;
		}
	}

	public static void align(IThing[] things, HorizontalAlignment a) {
		switch (a) {
		case LEFT: {
			double x = Double.POSITIVE_INFINITY;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					x = Math.min(boundingBox.x, x);
				}
				else if (t instanceof IHasAnchorPoint) {
					Point2D anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					x = Math.min(anchorPoint.getX(), x);
				}
			}
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					boundingBox.x = SystemUtils.round(x);
					((IHasMutableBoundingBox) t).setBoundingBox(boundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point2D anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					anchorPoint.setLocation(x, anchorPoint.getY());
					((IHasMutableAnchorPoint) t).setAnchorPoint(anchorPoint);
				}
			}
		}
			break;
		case CENTER: {
			double x = 0;
			int i = 0;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					x += boundingBox.x + boundingBox.width / 2;
					i++;
				}
				else if (t instanceof IHasAnchorPoint) {
					Point2D anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					x += anchorPoint.getX();
					i++;
				}
			}
			x = x / i;
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					boundingBox.x = SystemUtils.round(x) - boundingBox.width / 2;
					((IHasMutableBoundingBox) t).setBoundingBox(boundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point2D anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					anchorPoint.setLocation(x, anchorPoint.getY());
					((IHasMutableAnchorPoint) t).setAnchorPoint(anchorPoint);
				}
			}
		}
			break;
		case RIGHT: {
			double x = Double.NEGATIVE_INFINITY;
			for (IThing t : things) {
				if (t instanceof IHasBoundingBox) {
					Rectangle boundingBox = ((IHasBoundingBox) t).getBoundingBox();
					x = Math.max(boundingBox.x + boundingBox.width, x);
				}
				else if (t instanceof IHasAnchorPoint) {
					Point2D anchorPoint = ((IHasAnchorPoint) t).getAnchorPoint();
					x = Math.max(anchorPoint.getX(), x);
				}
			}
			for (IThing t : things) {
				if (t instanceof IHasMutableBoundingBox) {
					Rectangle boundingBox = ((IHasMutableBoundingBox) t).getBoundingBox();
					boundingBox.x = SystemUtils.round(x) - boundingBox.width;
					((IHasMutableBoundingBox) t).setBoundingBox(boundingBox);
				}
				else if (t instanceof IHasMutableAnchorPoint) {
					Point2D anchorPoint = ((IHasMutableAnchorPoint) t).getAnchorPoint();
					anchorPoint.setLocation(x, anchorPoint.getY());
					((IHasMutableAnchorPoint) t).setAnchorPoint(anchorPoint);
				}
			}
		}
			break;
		}
	}
}
