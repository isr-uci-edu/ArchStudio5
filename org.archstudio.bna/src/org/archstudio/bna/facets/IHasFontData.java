package org.archstudio.bna.facets;

import org.archstudio.bna.IThing;
import org.archstudio.swtutils.constants.FontStyle;

public interface IHasFontData extends IThing {
	public static final String FONT_NAME_PROPERTY_NAME = "fontName";
	public static final String FONT_SIZE_PROPERTY_NAME = "fontSize";
	public static final String FONT_STYLE_PROPERTY_NAME = "fontStyle";

	public String getFontName();

	public int getFontSize();

	public FontStyle getFontStyle();
}
