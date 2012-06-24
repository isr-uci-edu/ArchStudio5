package org.archstudio.xadl.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.xarchadt.ObjRef;

public interface IHasObjRef extends IThing {

	public static final IThingKey<ObjRef> OBJREF_KEY = ThingKey.create("objRef");

}
