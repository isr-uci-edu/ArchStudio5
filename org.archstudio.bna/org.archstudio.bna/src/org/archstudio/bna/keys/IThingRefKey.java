package org.archstudio.bna.keys;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;

public interface IThingRefKey<T extends IThing> extends IThingKey<Object> {

	public T get(IThing fromThing, IBNAModel inModel);

}
