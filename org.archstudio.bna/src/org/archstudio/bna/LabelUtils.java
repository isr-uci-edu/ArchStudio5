package org.archstudio.bna;

import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.TextLayout;
import org.eclipse.swt.widgets.Display;

import org.archstudio.swtutils.constants.FontStyle;

public class LabelUtils {

	public static Rectangle setFontToRender(Display d, TextLayout tl, int maxHeight, String name, int size, FontStyle style) {
		Font initialFont = ResourceUtils.getFont(d, name, size, style);
		tl.setFont(initialFont);
		Rectangle bounds = tl.getBounds();
		if (bounds.height <= maxHeight) {
			return bounds;
		}
		//It doesn't fit, heightwise, when we lay out the text.
		//We need to find the largest font size less than the given
		//size that will fit when the text is laid out.
		int max = size;
		int min = 1;
		int maxThatFits = 0;
		int fontSizeSet = size;
		int comps = 0;
		while (true) {
			if ((max <= min) || ((max - 1) == min)) {
				if (maxThatFits == 0)
					return null;
				if (fontSizeSet != maxThatFits) {
					tl.setFont(ResourceUtils.getFont(d, name, maxThatFits, style));
				}
				return tl.getBounds();
			}
			int s = ((max - min) / 2) + min;

			Font f = ResourceUtils.getFont(d, name, s, style);
			fontSizeSet = s;
			tl.setFont(f);
			comps++;

			bounds = tl.getBounds();
			if (bounds.height > maxHeight) {
				max = s;
			}
			else {
				min = s;
				maxThatFits = min;
			}
		}
	}

}
