package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;
import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * Captures a key that stores a value in an {@link IThing}.
 * 
 * @param <D>
 *            The type of data used to identify the key itself, often a {@link String}.
 * @param <V>
 *            The type of value stored by the key, e.g., a Rectangle, RGB, an Integer, etc.
 */
@NonNullByDefault
public class ThingKey<D, V> extends AbstractGenericThingKey<D, V> {

	public static final <D, V> IThing.IThingKey<V> create(D keyData) {
		return new ThingKey<D, V>(keyData, true);
	}

	public static final <D, V> IThing.IThingKey<V> create(D keyData, boolean isFireEventOnChange) {
		return new ThingKey<D, V>(keyData, isFireEventOnChange);
	}

	protected ThingKey(D keyData, boolean isFireEventOnChange) {
		super(keyData, isFireEventOnChange);
	}
}
