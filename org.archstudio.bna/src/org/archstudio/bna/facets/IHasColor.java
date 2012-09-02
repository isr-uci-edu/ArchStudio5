package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.AbstractCloneThingKey;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

@NonNullByDefault
public interface IHasColor extends IThing {

	public static final IThingKey<RGB> COLOR_KEY = CloneThingKey.create("color", AbstractCloneThingKey.rgb());

	public @Nullable RGB getColor();
}
