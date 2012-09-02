package org.archstudio.bna.keys;

import org.archstudio.bna.IThing.IThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;

import com.google.common.base.Function;

@NonNullByDefault
public class CloneThingKey<D, V> extends AbstractCloneThingKey<D, V> {

	public static final <D, V> IThingKey<V> create(D keyData, Function<V, V> cloneFunction) {
		return new CloneThingKey<D, V>(keyData, true, cloneFunction);
	}

	public static final <D, V> IThingKey<V> create(D keyData, boolean isFireEventOnChange, Function<V, V> cloneFunction) {
		return new CloneThingKey<D, V>(keyData, isFireEventOnChange, cloneFunction);
	}

	public CloneThingKey(D keyData, boolean isFireEventOnChange, Function<V, V> cloneFunction) {
		super(keyData, isFireEventOnChange, cloneFunction);
	}
}
