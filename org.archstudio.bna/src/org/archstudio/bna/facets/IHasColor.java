package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.CloneThingKey;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.graphics.RGB;

@NonNullByDefault
public interface IHasColor extends IThing {

	public static final IThingKey<RGB> COLOR_KEY = CloneThingKey.create("color", CloneThingKey.rgb());

	/* This is necessary to indicate to EditColorLogic which thing to copy a color from in an Assembly */
	public static final String USER_MAY_COPY_COLOR = "userMayCopyColor";

	public @Nullable
	RGB getColor();
}
