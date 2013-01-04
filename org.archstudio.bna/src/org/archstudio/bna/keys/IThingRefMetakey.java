package org.archstudio.bna.keys;

import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.logics.coordinating.DynamicStickPointLogic;

/**
 * Describes a key that captures metadata about another key's data: K is the other key, N is the name of the metadata
 * property for that other key's data, and T is the type of {@link IThing} that the reference should refer to. Logics
 * use this to capture extra data about specific properties and to relate that data to the property. See
 * {@link IThingMetakey} for more detail.
 * <p>
 * As an example, the {@link DynamicStickPointLogic#getStickyThingKey(IThingKey)} uses this type of key to capture the
 * desired thing that a particular point is stuck to.
 * <p>
 * 
 * @param <T>
 *            The type of {@link IThing} the value should reference
 * @param <N>
 *            The name of the metadata being captured
 * @param <K>
 *            The other key for which metadata is being captured
 */
public interface IThingRefMetakey<T extends IThing, N, K extends IThingKey<?>> extends IThingRefKey<T>,
		IThingMetakey<N, K, Object> {

	@Override
	N getName();

	@Override
	K getKey();
}