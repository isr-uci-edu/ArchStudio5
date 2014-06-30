package org.archstudio.bna;

import java.util.Map;
import java.util.Set;

import org.archstudio.bna.keys.IThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Describes a BNA Thing with zero or more properties.
 */
@NonNullByDefault
public interface IThing {

	/**
	 * Gets the class that maintains and draws the graphical representation of this Thing.
	 * 
	 * @return The Thing's Peer's class.
	 */
	public Class<? extends IThingPeer<?>> getPeerClass();

	/**
	 * Gets the unique ID of this Thing. The ID is used by {@link IBNAModel#getThing(Object)}.
	 * 
	 * @return Thing ID.
	 */
	public Object getID();

	public void insertThingListener(IThingListener thingListener);

	public void addThingListener(IThingListener thingListener);

	public void removeThingListener(IThingListener thingListener);

	public @Nullable
	<V> V get(IThingKey<V> key);

	public <V> V get(IThingKey<V> key, V valueIfNull);

	public <V> void set(IThingKey<V> key, @Nullable V value);

	public @Nullable
	<V> V getAndSet(IThingKey<V> key, @Nullable V value);

	public boolean has(IThingKey<?> key);

	public <V> boolean has(IThingKey<V> key, @Nullable V value);

	public @Nullable
	<V> V remove(IThingKey<V> key);

	public Set<Map.Entry<IThingKey<?>, ?>> entrySet();
}
