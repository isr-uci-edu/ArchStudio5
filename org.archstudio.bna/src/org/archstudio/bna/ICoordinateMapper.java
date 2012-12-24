package org.archstudio.bna;

import java.awt.geom.Point2D;

import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

public interface ICoordinateMapper {

	public static final ICoordinateMapper IDENTITY = new ICoordinateMapper() {

		public void addCoordinateMapperListener(ICoordinateMapperListener l) {
			// ignored: this class will never generate an event
		}

		public void removeCoordinateMapperListener(ICoordinateMapperListener l) {
		}

		public Rectangle getWorldBounds() {
			return new Rectangle(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MAX_VALUE, Integer.MAX_VALUE);
		}

		public Rectangle getLocalBounds() {
			return new Rectangle(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MAX_VALUE, Integer.MAX_VALUE);
		}

		public Point getLocalOrigin() {
			return new Point(0, 0);
		}

		public double getLocalScale() {
			return 1d;
		}

		public Point worldToLocal(Point worldPoint) {
			return BNAUtils.clone(worldPoint);
		}

		public Point2D worldToLocal(Point2D worldPoint) {
			return new Point2D.Double(worldPoint.getX(), worldPoint.getY());
		}

		public Rectangle worldToLocal(Rectangle worldRectangle) {
			return BNAUtils.clone(worldRectangle);
		}

		public Point localToWorld(Point localPoint) {
			return BNAUtils.clone(localPoint);
		}

		public Point2D localToWorld(Point2D localPoint) {
			return new Point2D.Double(localPoint.getX(), localPoint.getY());
		}

		public Rectangle localToWorld(Rectangle localRectangle) {
			return BNAUtils.clone(localRectangle);
		}
	};

	public abstract void addCoordinateMapperListener(ICoordinateMapperListener l);

	public abstract void removeCoordinateMapperListener(ICoordinateMapperListener l);

	public abstract Rectangle getWorldBounds();

	public abstract Rectangle getLocalBounds();

	public abstract Point getLocalOrigin();

	public abstract double getLocalScale();

	public abstract Point worldToLocal(Point worldPoint);

	public abstract Point2D worldToLocal(Point2D worldPoint);

	public abstract Rectangle worldToLocal(Rectangle worldRectangle);

	public abstract Point localToWorld(Point localPoint);

	public abstract Point2D localToWorld(Point2D localPoint);

	public abstract Rectangle localToWorld(Rectangle localRectangle);
}
