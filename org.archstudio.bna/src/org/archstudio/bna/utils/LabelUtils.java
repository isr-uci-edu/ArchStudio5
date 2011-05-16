package org.archstudio.bna.utils;

import org.archstudio.bna.IResources;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.TextLayout;

public class LabelUtils {

	/**
	 * Updates the {@link TextLayout} so that it fits within its given width and specified height, returning the
	 * boundaries of the resulting layout; or <code>null</code> if none exists.
	 */
	public static Rectangle setFontToRender(IResources r, TextLayout textLayout, int maxHeight, String name, int size,
			FontStyle style) {

		Font initialFont = r.getFont(name, size, style);
		textLayout.setFont(initialFont);
		Rectangle bounds = BNAUtils.toRectangle(textLayout.getBounds());
		if (bounds.height <= maxHeight) {
			return bounds;
		}

		/*
		 * It doesn't fit, height-wise, when we lay out the text. We need to find the largest font size than will fit
		 * when the text is laid out, but which is within the maximum height specified. We essentially do a binary
		 * search for a valid font size.
		 */
		int maxSize = size;
		int minSize = 1;
		int maxSizeThatFits = 0;
		int fontSize = size;
		while (true) {
			if (maxSize <= minSize || maxSize - 1 == minSize) {
				if (maxSizeThatFits == 0) {
					return null;
				}
				if (fontSize != maxSizeThatFits) {
					textLayout.setFont(r.getFont(name, maxSizeThatFits, style));
				}
				return BNAUtils.toRectangle(textLayout.getBounds());
			}
			int testSize = (maxSize - minSize) / 2 + minSize;
			textLayout.setFont(r.getFont(name, fontSize = testSize, style));
			bounds = BNAUtils.toRectangle(textLayout.getBounds());
			if (bounds.height > maxHeight) {
				maxSize = testSize;
			}
			else {
				minSize = testSize;
				maxSizeThatFits = minSize;
			}
		}
	}
}
