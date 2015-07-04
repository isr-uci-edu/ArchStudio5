package org.archstudio.bna.facets;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN. To modify, update the
 * thingdefinition extension at org.archstudio.bna/Package[name=org.archstudio.bna.facets]/Facet[name=EdgeColor].
 */

@SuppressWarnings("all")
@NonNullByDefault
public interface IHasEdgeColor extends org.archstudio.bna.IThing {

	public static final IThingKey<org.eclipse.swt.graphics.RGB> EDGE_COLOR_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("edgeColor", IHasEdgeColor.class), true);

	public @Nullable org.eclipse.swt.graphics.RGB getEdgeColor();

}
