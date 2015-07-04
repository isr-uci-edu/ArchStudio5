package org.archstudio.bna.facets;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN. To modify, update the
 * thingdefinition extension at org.archstudio.bna/Package[name=org.archstudio.bna.facets]/Facet[name=Arrowheads].
 */

@SuppressWarnings("all")
@NonNullByDefault
public interface IHasArrowheads extends org.archstudio.bna.IThing {

	public static final IThingKey<org.eclipse.swt.graphics.RGB> ARROWHEAD_1_COLOR_KEY = ThingKey
			.create(com.google.common.collect.Lists.newArrayList("arrowhead1Color", IHasArrowheads.class), true);

	public static final IThingKey<org.eclipse.swt.graphics.RGB> ARROWHEAD_1_EDGE_COLOR_KEY = ThingKey
			.create(com.google.common.collect.Lists.newArrayList("arrowhead1EdgeColor", IHasArrowheads.class), true);

	public static final IThingKey<java.lang.Integer> ARROWHEAD_1_LENGTH_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("arrowhead1Length", IHasArrowheads.class));

	public static final IThingKey<org.archstudio.bna.constants.ArrowheadShape> ARROWHEAD_1_SHAPE_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("arrowhead1Shape", IHasArrowheads.class));

	public static final IThingKey<java.lang.Integer> ARROWHEAD_1_WIDTH_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("arrowhead1Width", IHasArrowheads.class));

	public static final IThingKey<org.eclipse.swt.graphics.RGB> ARROWHEAD_2_COLOR_KEY = ThingKey
			.create(com.google.common.collect.Lists.newArrayList("arrowhead2Color", IHasArrowheads.class), true);

	public static final IThingKey<org.eclipse.swt.graphics.RGB> ARROWHEAD_2_EDGE_COLOR_KEY = ThingKey
			.create(com.google.common.collect.Lists.newArrayList("arrowhead2EdgeColor", IHasArrowheads.class), true);

	public static final IThingKey<java.lang.Integer> ARROWHEAD_2_LENGTH_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("arrowhead2Length", IHasArrowheads.class));

	public static final IThingKey<org.archstudio.bna.constants.ArrowheadShape> ARROWHEAD_2_SHAPE_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("arrowhead2Shape", IHasArrowheads.class));

	public static final IThingKey<java.lang.Integer> ARROWHEAD_2_WIDTH_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("arrowhead2Width", IHasArrowheads.class));

	public @Nullable org.eclipse.swt.graphics.RGB getArrowhead1Color();

	public @Nullable org.eclipse.swt.graphics.RGB getArrowhead1EdgeColor();

	public int getArrowhead1Length();

	public org.archstudio.bna.constants.ArrowheadShape getArrowhead1Shape();

	public int getArrowhead1Width();

	public @Nullable org.eclipse.swt.graphics.RGB getArrowhead2Color();

	public @Nullable org.eclipse.swt.graphics.RGB getArrowhead2EdgeColor();

	public int getArrowhead2Length();

	public org.archstudio.bna.constants.ArrowheadShape getArrowhead2Shape();

	public int getArrowhead2Width();

}
