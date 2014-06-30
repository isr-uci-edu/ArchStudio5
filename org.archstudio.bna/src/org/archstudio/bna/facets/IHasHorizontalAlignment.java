package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.swtutils.constants.HorizontalAlignment;

public interface IHasHorizontalAlignment extends IThing {

	public static final IThingKey<HorizontalAlignment> HORIZONTAL_ALIGNMENT_KEY = ThingKey
			.create("horizontalAlignment");

	public HorizontalAlignment getHorizontalAlignment();
}
