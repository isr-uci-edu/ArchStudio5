package org.archstudio.bna.keys;

import org.archstudio.bna.IThing.IThingKey;
import org.eclipse.draw2d.geometry.Rectangle;

public class RectangleThingKey<D> extends AbstractCloneThingKey<D, Rectangle> {

	public static final <D> IThingKey<Rectangle> create(D keyData) {
		return new RectangleThingKey<D>(keyData, true);
	}

	protected RectangleThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}

	@Override
	protected Rectangle clone(Rectangle value) {
		return value != null ? value.getCopy() : null;
	}

}
