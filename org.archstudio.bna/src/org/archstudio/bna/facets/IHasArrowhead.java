package org.archstudio.bna.facets;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN. To modify, update the
 * thingdefinition extension at org.archstudio.bna/Package[name=org.archstudio.bna.facets]/Facet[name=Arrowhead].
 */

@SuppressWarnings("all")
@NonNullByDefault
public interface IHasArrowhead extends org.archstudio.bna.IThing {

	public static final IThingKey<org.eclipse.swt.graphics.RGB> ARROWHEAD_COLOR_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("arrowheadColor", IHasArrowhead.class), true);

	public static final IThingKey<org.eclipse.swt.graphics.RGB> ARROWHEAD_EDGE_COLOR_KEY = ThingKey
			.create(com.google.common.collect.Lists.newArrayList("arrowheadEdgeColor", IHasArrowhead.class), true);

	public static final IThingKey<java.lang.Integer> ARROWHEAD_LENGTH_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("arrowheadLength", IHasArrowhead.class));

	public static final IThingKey<org.archstudio.bna.constants.ArrowheadShape> ARROWHEAD_SHAPE_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("arrowheadShape", IHasArrowhead.class));

	public static final IThingKey<java.lang.Integer> ARROWHEAD_WIDTH_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("arrowheadWidth", IHasArrowhead.class));

	public @Nullable org.eclipse.swt.graphics.RGB getArrowheadColor();

	public @Nullable org.eclipse.swt.graphics.RGB getArrowheadEdgeColor();

	public int getArrowheadLength();

	public org.archstudio.bna.constants.ArrowheadShape getArrowheadShape();

	public int getArrowheadWidth();

}
