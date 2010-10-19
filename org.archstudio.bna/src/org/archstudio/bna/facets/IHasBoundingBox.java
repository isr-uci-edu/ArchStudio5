package org.archstudio.bna.facets;

import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.IThing;

public interface IHasBoundingBox extends IThing {

	public static final String BOUNDING_BOX_PROPERTY_NAME = "boundingBox";

	//Get bounding box in world coordinates
	public Rectangle getBoundingBox();

}
