package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.swtutils.constants.VerticalAlignment;

public interface IHasVerticalAlignment extends IThing {

	public static final IThingKey<VerticalAlignment> VERTICAL_ALIGNMENT_KEY = ThingKey.create("verticalAlignment");

	public VerticalAlignment getVerticalAlignment();

}
