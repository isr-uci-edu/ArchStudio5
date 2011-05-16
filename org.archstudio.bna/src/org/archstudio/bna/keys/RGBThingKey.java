package org.archstudio.bna.keys;

import org.archstudio.bna.IThing.IThingKey;
import org.eclipse.swt.graphics.RGB;

public class RGBThingKey<D> extends AbstractCloneThingKey<D, RGB> {

	public static final <D> IThingKey<RGB> create(D keyData) {
		return new RGBThingKey<D>(keyData, true);
	}

	protected RGBThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}

	@Override
	protected RGB clone(RGB value) {
		return value != null ? new RGB(value.red, value.green, value.blue) : null;
	}

}
