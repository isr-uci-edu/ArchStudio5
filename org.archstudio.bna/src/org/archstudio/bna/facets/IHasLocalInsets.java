package org.archstudio.bna.facets;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.eclipse.draw2d.geometry.Insets;

public interface IHasLocalInsets {

	public static final IThingKey<Insets> LOCAL_INSETS_KEY = ThingKey.create("local insets");

	public Insets getLocalInsets();

}
