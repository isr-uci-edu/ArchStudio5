package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ThingKey;

public interface IHasText extends IThing {

	public static final IThingKey<String> TEXT_KEY = ThingKey.create("text");

	public String getText();
}
