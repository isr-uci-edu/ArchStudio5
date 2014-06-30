package org.archstudio.bna.keys;

import org.archstudio.sysutils.TypedMap;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Describes a key that stores a property value of type V.
 * <p>
 * There are currently four types of keys, illustrated by the following table:
 * <table border='1' cellspacing='0'>
 * <tr>
 * <td>key type \ value type</td>
 * <th>Value</th>
 * <th>Reference</th>
 * </tr>
 * <tr>
 * <th>Key</th>
 * <td>{@link IThingKey}</td>
 * <td>{@link IThingRefKey}</td>
 * </tr>
 * <tr>
 * <th>Metakey</th>
 * <td>{@link IThingMetakey}</td>
 * <td>{@link IThingRefMetakey}</td>
 * </tr>
 * </table>
 * A metakey stores data about another key's data whereas a reference key stores references to other things.
 * 
 * @param <V>
 *            The type of value stored by the key.
 */
public interface IThingKey<V> extends TypedMap.Key<V> {
	public @Nullable
	V clone(@Nullable V value);
}