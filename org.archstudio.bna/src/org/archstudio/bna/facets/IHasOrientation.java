package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.swtutils.constants.Orientation;

public interface IHasOrientation extends IThing {
	public static final IThingKey<Orientation> ORIENTATION_KEY = ThingKey.create("orientation");

	public Orientation getOrientation();
}
