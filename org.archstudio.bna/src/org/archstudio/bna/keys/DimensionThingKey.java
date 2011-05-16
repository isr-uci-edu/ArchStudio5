package org.archstudio.bna.keys;

import org.archstudio.bna.IThing.IThingKey;
import org.eclipse.draw2d.geometry.Dimension;

public class DimensionThingKey<D> extends AbstractCloneThingKey<D, Dimension> {

	public static final <D> IThingKey<Dimension> create(D keyData) {
		return new DimensionThingKey<D>(keyData, true);
	}

	protected DimensionThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}

	@Override
	protected Dimension clone(Dimension value) {
		return value == null ? null : value.getCopy();
	}

}
