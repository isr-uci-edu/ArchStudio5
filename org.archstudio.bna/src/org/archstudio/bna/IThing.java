package org.archstudio.bna;

import java.util.Set;

import org.archstudio.bna.keys.IThingMetakey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.IThingRefMetakey;
import org.archstudio.sysutils.TypedMap;

/**
 * Describes a BNA Thing with zero or more properties.
 */
public interface IThing {

	/**
	 * Describes a key that stores a property value of type V.
	 * <p>
	 * There are currently four types of keys, illustrated by the following
	 * table:
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
	 * A metakey stores data about another key's data whereas a reference stores
	 * references to other things.
	 * 
	 * @param <V>
	 *            The type of value stored by the key.
	 */
	public static interface IThingKey<V> extends TypedMap.Key<V> {
		public boolean isFireEventOnChange();

		public V preWrite(V value);

		public V postRead(V value);
	}

	/**
	 * Gets the class that maintains and draws the graphical representation of
	 * this Thing.
	 * 
	 * @return The Thing's Peer's class.
	 */
	public Class<? extends IThingPeer<?>> getPeerClass();

	/**
	 * Gets the unique ID of this Thing.
	 * 
	 * @return Thing ID.
	 */
	public Object getID();

	public void addThingListener(IThingListener thingListener);

	public void removeThingListener(IThingListener thingListener);

	public <V> V get(IThingKey<V> key);

	public <V> V set(IThingKey<V> key, V value);

	public boolean has(IThingKey<?> key);

	public <V> boolean has(IThingKey<V> key, V value);

	public <V> V remove(IThingKey<V> key);

	public Set<IThingKey<?>> keySet();
}
