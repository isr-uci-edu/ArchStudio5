package org.archstudio.bna;

import org.eclipse.draw2d.geometry.Rectangle;

public interface IRegion {

	public static final IRegion ALWAYS_TRUE = new IRegion() {

		@Override
		public boolean intersects(Rectangle r) {
			return true;
		}
	};

	public boolean intersects(Rectangle r);
}
