package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.IThing;

public interface IHasStickyBox extends IThing {
	public static final String STICKY_BOX_PROPERTY_NAME = "stickyBox";

	public Rectangle getStickyBox();
}
