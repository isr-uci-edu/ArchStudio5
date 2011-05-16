package org.archstudio.bna.keys;

import org.archstudio.bna.IThing.IThingKey;
import org.eclipse.draw2d.geometry.Point;

public class PointThingKey<D> extends AbstractCloneThingKey<D, Point> {

	public static final <D> IThingKey<Point> create(D keyData) {
		return new PointThingKey<D>(keyData, true);
	}

	protected PointThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}

	@Override
	protected Point clone(Point value) {
		return value != null ? value.getCopy() : null;
	}

}
