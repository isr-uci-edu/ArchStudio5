package org.archstudio.bna.facets;

import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;

/*
 * DO NOT EDIT THIS FILE, it is automatically generated. ANY MODIFICATIONS WILL BE OVERWRITTEN. To modify, update the
 * thingdefinition extension at org.archstudio.bna/Package[name=org.archstudio.bna.facets]/Facet[name=LineStyle].
 */

@SuppressWarnings("all")
@NonNullByDefault
public interface IHasLineStyle extends org.archstudio.bna.IThing {

	public static final IThingKey<org.archstudio.swtutils.constants.LineStyle> LINE_STYLE_KEY =
			ThingKey.create(com.google.common.collect.Lists.newArrayList("lineStyle", IHasLineStyle.class));

	public org.archstudio.swtutils.constants.LineStyle getLineStyle();

}
