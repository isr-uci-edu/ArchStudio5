package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.swt.graphics.Rectangle;

public interface IHasStickyBox extends IThing {
	public static final IThingKey<Rectangle> STICKY_BOX_KEY = ThingKey.create("stickyBox");

	public Rectangle getStickyBox();
}
