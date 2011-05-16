package org.archstudio.bna.keys;

import org.archstudio.bna.IThing.IThingKey;
import org.eclipse.draw2d.geometry.Insets;

public class InsetsThingKey<D> extends AbstractCloneThingKey<D, Insets> {

	public static final <D> IThingKey<Insets> create(D keyData) {
		return new InsetsThingKey<D>(keyData, true);
	}

	protected InsetsThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}

	@Override
	protected Insets clone(Insets value) {
		return value != null ? new Insets(value) : null;
	}

}
