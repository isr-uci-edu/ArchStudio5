package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasFontData extends IThing {

	public static final IThingKey<String> FONT_NAME_KEY = ThingKey.create("fontName");
	public static final IThingKey<Integer> FONT_SIZE_KEY = ThingKey.create("fontSize");
	public static final IThingKey<FontStyle> FONT_STYLE_KEY = ThingKey.create("fontStyle");
	public static final IThingKey<Boolean> DONT_INCREASE_FONT_SIZE_KEY = ThingKey.create("dontIncreaseFontSize");

	public String getFontName();

	public int getFontSize();

	public FontStyle getFontStyle();

	public boolean getDontIncreaseFontSize();
}
