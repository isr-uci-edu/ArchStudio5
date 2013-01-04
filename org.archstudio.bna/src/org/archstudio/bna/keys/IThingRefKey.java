package org.archstudio.bna.keys;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.logics.tracking.ThingReferenceTrackingLogic;

/**
 * Describes a key that expects to store a reference to a thing as its value. This is a convenience class, which could
 * be accomplished using <code>{@link IThingKey}&lt;Object&gt;</code>. However, using this class has the following
 * advantages:
 * <ul>
 * <li>{@link AbstractThingRefKey#preWrite(Object)} prohibits {@link IThing}s to prevent accidentally setting a thing as
 * a property value,</li>
 * <li>{@link #get(IThing, IBNAModel)} retrieves the the corresponding thing and automatically typecasts it,</li>
 * <li>type information for the expected thing is captured in T, and</li>
 * <li>such properties are tracked by {@link ThingReferenceTrackingLogic}.</li>
 * </ul>
 * 
 * @param <T>
 *            The type of {@link IThing} the value should reference
 */

public interface IThingRefKey<T extends IThing> extends IThingKey<Object> {

	public Object getKeyData();

	public T get(IThing fromThing, IBNAModel inModel);

	public void set(IThing fromThing, T thing);

}
