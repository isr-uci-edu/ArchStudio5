package org.archstudio.bna.facets;

import org.archstudio.swtutils.constants.FontStyle;

public interface IHasMutableFontData extends IHasFontData {

	public void setFontName(String fontName);

	public void setFontSize(int fontSize);

	public void setFontStyle(FontStyle fontStyle);
}
