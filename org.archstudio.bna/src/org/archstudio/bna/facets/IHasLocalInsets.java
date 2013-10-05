package org.archstudio.bna.facets;

import java.awt.Insets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.ThingKey;

public interface IHasLocalInsets extends IThing {

	public static final IThingKey<Insets> LOCAL_INSETS_KEY = ThingKey.create("local insets");

	public Insets getLocalInsets();

}
