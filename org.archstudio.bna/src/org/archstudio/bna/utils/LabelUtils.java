package org.archstudio.bna.utils;

import java.util.Set;

import org.archstudio.bna.IResources;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.graphics.TextLayout;

import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;

public class LabelUtils {

	/**
	 * Updates the {@link TextLayout} so that it fits within its given width and
	 * specified height, returning the boundaries of the resulting layout; or
	 * <code>null</code> if none exists.
	 */
	public static int resizeTextLayoutToFit(IResources r, TextLayout textLayout, int maxHeight, String name, int size,
			FontStyle style) {

		textLayout.setFont(r.getFont(name, size, style));
		Set<Integer> allowableLineBreaks = getAllowableLinebreaks(textLayout.getText());
		Rectangle bounds = BNAUtils.toRectangle(textLayout.getBounds());
		if (allowableLineBreaks.containsAll(Ints.asList(textLayout.getLineOffsets())) && bounds.height <= maxHeight) {
			return size;
		}

		/*
		 * It doesn't fit, height-wise, when we lay out the text. We need to
		 * find the largest font size than will fit when the text is laid out,
		 * but which is within the maximum height specified. We essentially do a
		 * binary search for a valid font size.
		 */
		int maxSize = size - 1;
		int minSize = 1;
		int maxSizeThatFits = 0;
		while (maxSize >= minSize) {
			int testSize = (maxSize - minSize) / 2 + minSize;
			textLayout.setFont(r.getFont(name, testSize, style));
			bounds = BNAUtils.toRectangle(textLayout.getBounds());
			boolean allowedLineBreaks = allowableLineBreaks.containsAll(Ints.asList(textLayout.getLineOffsets()));
			if (bounds.height > maxHeight || !allowedLineBreaks) {
				maxSize = testSize - 1;
				continue;
			}
			else {
				if (allowedLineBreaks) {
					maxSizeThatFits = testSize;
				}
				minSize = testSize + 1;
				continue;
			}
		}
		if (maxSizeThatFits == 0) {
			return 0;
		}
		textLayout.setFont(r.getFont(name, maxSizeThatFits, style));
		return maxSizeThatFits;
	}

	private static Set<Integer> getAllowableLinebreaks(String text) {
		Set<Integer> breaks = Sets.newHashSet(0, text.length());
		int index = 0;
		for (String s : text.split("\\s")) {
			index += s.length();
			breaks.add(index);
			index++;
			breaks.add(index);
		}
		breaks.remove(text.length() + 1);
		return breaks;
	}
}
