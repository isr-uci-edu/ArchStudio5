package org.archstudio.bna;

import org.archstudio.bna.utils.BNAUtils;
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
		public Rectangle getWorldBounds() {
			return new Rectangle(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MAX_VALUE, Integer.MAX_VALUE);
		}

		@Override
		public Rectangle getLocalBounds() {
			return new Rectangle(Integer.MIN_VALUE / 2, Integer.MIN_VALUE / 2, Integer.MAX_VALUE, Integer.MAX_VALUE);
		}

		@Override
		public Point getLocalOrigin() {
			return new Point(0, 0);
		}

		@Override
		public double getLocalScale() {
			return 1d;
		}

		@Override
		public Point worldToLocal(Point worldPoint) {
			return BNAUtils.clone(worldPoint);
		}

		@Override
		public Rectangle worldToLocal(Rectangle worldRectangle) {
			return BNAUtils.clone(worldRectangle);
		}

		@Override
		public Point localToWorld(Point localPoint) {
			return BNAUtils.clone(localPoint);
		}

		@Override
		public Rectangle localToWorld(Rectangle localRectangle) {
			return BNAUtils.clone(localRectangle);
		}

		@Override
		public ICoordinateMapper copy() {
			return this;
		};
	};

	public abstract void addCoordinateMapperListener(ICoordinateMapperListener l);

	public abstract void removeCoordinateMapperListener(ICoordinateMapperListener l);

	public abstract Rectangle getWorldBounds();

	public abstract Rectangle getLocalBounds();

	public abstract Point getLocalOrigin();

	public abstract double getLocalScale();

	public abstract Point worldToLocal(Point worldPoint);

	public abstract Rectangle worldToLocal(Rectangle worldRectangle);

	public abstract Point localToWorld(Point localPoint);

	public abstract Rectangle localToWorld(Rectangle localRectangle);

	public ICoordinateMapper copy();
}
