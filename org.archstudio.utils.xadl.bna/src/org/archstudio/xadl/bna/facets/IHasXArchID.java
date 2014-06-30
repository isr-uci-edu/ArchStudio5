package org.archstudio.xadl.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasXArchID extends IThing {

	public static final IThingKey<String> XARCH_ID_KEY = ThingKey.create("xArchID");

}
