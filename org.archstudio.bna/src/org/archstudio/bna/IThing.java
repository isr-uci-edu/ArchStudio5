package org.archstudio.bna;

import java.util.Set;

import org.archstudio.sysutils.TypedMap;

/**
 * Describes a BNA Thing with zero or more properties.
 */
public interface IThing {

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
