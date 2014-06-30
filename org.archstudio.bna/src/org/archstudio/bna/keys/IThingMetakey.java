package org.archstudio.bna.keys;

import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;

/**
 * Describes a key that captures metadata about another key's data: K is the other key, N is the name of the metadata
 * for that other key's data, and V is the metadata type. Logics use this to capture extra data about specific
 * properties and to relate that data to the property.
 * <p>
 * 
 * <p>
 * As an example, the {@link DynamicStickPointLogic#getStickyModeKey(IThingKey)} uses this type of key to capture the
 * desired sticky mode for the provided point key.
 * 
 * @param <N>
 *            The name of the metadata being captured
 * @param <K>
 *            The other key for which metadata is being captured
 * @param <V>
 *            The type of the metadata value
 */

public interface IThingMetakey<N, K extends IThingKey<?>, V> extends IThingKey<V> {

	N getName();

	K getKey();
}