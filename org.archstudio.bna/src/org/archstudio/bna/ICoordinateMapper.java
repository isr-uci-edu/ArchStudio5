package org.archstudio.bna;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public interface ICoordinateMapper {

	public static final ICoordinateMapper IDENTITY = new ICoordinateMapper() {

		@Override
		public void addCoordinateMapperListener(ICoordinateMapperListener l) {
			// ignored: this class will never generate an event
		}

		@Override
		public void removeCoordinateMapperListener(ICoordinateMapperListener l) {
		}

		@Override
		public Rectangle getWorldBounds(Rectangle result) {
			return new Rectangle(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MAX_VALUE, Integer.MAX_VALUE);
		}

		@Override
		public Rectangle getLocalBounds(Rectangle result) {
			return new Rectangle(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MAX_VALUE, Integer.MAX_VALUE);
		}

		@Override
		public Point getLocalOrigin(Point result) {
			return new Point(0, 0);
		}

		@Override
		public double getLocalScale() {
			return 1d;
		}

		@Override
		public Point worldToLocal(Point worldPointResult) {
			return worldPointResult;
		}

		@Override
		public Rectangle worldToLocal(Rectangle worldRectangleResult) {
			return worldRectangleResult;
		}

		@Override
		public Point localToWorld(Point localPointResult) {
			return localPointResult;
		}

		@Override
		public Rectangle localToWorld(Rectangle localRectangleResult) {
			return localRectangleResult;
		}

		@Override
		public ICoordinateMapper copy() {
			return this;
		};
	};

	public abstract void addCoordinateMapperListener(ICoordinateMapperListener l);

	public abstract void removeCoordinateMapperListener(ICoordinateMapperListener l);

	public abstract Rectangle getWorldBounds(Rectangle result);

	public abstract Rectangle getLocalBounds(Rectangle result);

	public abstract Point getLocalOrigin(Point result);

	public abstract double getLocalScale();

	public abstract Point worldToLocal(Point worldPointResult);

	public abstract Rectangle worldToLocal(Rectangle worldRectangleResult);

	public abstract Point localToWorld(Point localPointResult);

	public abstract Rectangle localToWorld(Rectangle localRectangleResult);

	public ICoordinateMapper copy();
}
