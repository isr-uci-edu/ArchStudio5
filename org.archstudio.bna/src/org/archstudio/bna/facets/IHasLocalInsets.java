package org.archstudio.bna.facets;

import java.awt.Insets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.ThingKey;

public interface IHasLocalInsets {

	public static final IThingKey<Insets> LOCAL_INSETS_KEY = ThingKey.create("local insets");

	public Insets getLocalInsets();

}
