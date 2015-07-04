package org.archstudio.bna.facets;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN. To modify, update the
 * thingdefinition extension at org.archstudio.bna/Package[name=org.archstudio.bna.facets]/Facet[name=BoundingBox].
 */

/**
 * For mutable properties, see {@link IHasReferencePoint} and {@link IHasSize}.
 */
@SuppressWarnings("all")
@NonNullByDefault
public interface IHasBoundingBox extends org.archstudio.bna.IThing {

	public static final IThingKey<org.eclipse.swt.graphics.Rectangle> BOUNDING_BOX_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("boundingBox", IHasBoundingBox.class));

	public org.eclipse.swt.graphics.Rectangle getBoundingBox();

}
