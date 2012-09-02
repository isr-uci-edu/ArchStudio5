package org.archstudio.bna.facets;

import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.jdt.annotation.NonNullByDefault;

@NonNullByDefault
public interface IHasMutableFontData extends IHasFontData {

	public void setFontName(String fontName);

	public void setFontSize(int fontSize);

	public void setFontStyle(FontStyle fontStyle);

	public void setDontIncreaseFontSize(boolean dontIncreaseFontSize);
}
