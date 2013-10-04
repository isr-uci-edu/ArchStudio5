package org.archstudio.bna;

import org.archstudio.bna.keys.IThingMetakey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.IThingRefMetakey;
import org.archstudio.sysutils.TypedMap;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/**
 * Describes a BNA Thing with zero or more properties.
 */
@NonNullByDefault
public interface IThing {

	public static interface IEntry {
		public IThingKey<?> getKey();

		public Object getValue();
	}

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
	public static interface IThingKey<V> extends TypedMap.Key<V> {
		public boolean isFireEventOnChange();

		public Object getID();

		public int getUID();

		public @Nullable
		V preWrite(@Nullable V value);

		public @Nullable
		V postRead(@Nullable V value);
	}

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

	/**
	 * Gets the unique ID of this Thing. The UID is used by {@link IBNAModel#getThing(int)} and
	 * {@link IBNAModel#getThing(Integer)}.
	 * 
	 * @return Thing UID.
	 */
	public int getUID();

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

	public Iterable<IEntry> entries();
}
