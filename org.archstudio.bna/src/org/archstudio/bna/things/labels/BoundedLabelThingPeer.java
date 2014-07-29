package org.archstudio.bna.things.labels;

import java.awt.Dimension;
import java.awt.Font;
import java.text.BreakIterator;
import java.util.Arrays;
import java.util.List;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinate;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.ui.IUIResources.FontMetrics;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.RegExBreakIterator;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;

public class BoundedLabelThingPeer<T extends BoundedLabelThing> extends AbstractThingPeer<T> {

	private static final int MIN_FONT_SIZE = 2;
	private static final int MIN_LOCAL_WIDTH = 4;
	private static final int MIN_LOCAL_HEIGHT = MIN_FONT_SIZE + 1;

	private static final class LayoutData {
		Font font = null;
		List<String> lines = Lists.newArrayList();
		List<Float> lineWidths = Lists.newArrayList();
		float lineLeading = 0;
		float lineAscent = 0;
		float lineDescent = 0;
		//float totalWidth = 0;
		float totalHeight = 0;
	}

	private static final String trimRight(String string) {
		int end = string.length();
		while (end > 0 && Character.isWhitespace(string.charAt(end - 1))) {
			end--;
		}
		return string.substring(0, end);
	}

	Font breakWidthsFont;
	float[] breakWidths;
	List<Object> breakWidthsCacheConditions;

	public BoundedLabelThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	protected LayoutData calculateLayout(BoundedLabelThing t, Dimension localSize, IUIResources r) {

		LayoutData layoutData = new LayoutData();

		if (localSize.width > MIN_LOCAL_WIDTH && localSize.height > MIN_LOCAL_HEIGHT) {

			Font font = null;
			List<String> lines = Lists.newArrayList();
			List<Float> lineWidths = Lists.newArrayList();
			float lineLeading = 0;
			float lineAscent = 0;
			float lineDescent = 0;
			float totalWidth = 0;
			float totalHeight = 0;

			String text = t.getRawText();
			int minFontSize = MIN_FONT_SIZE;
			int originalMinFontSize = minFontSize;
			int maxFontSize = t.getFontSize();

			breakWidthsFont = r.getFont(t.getRawFontName(), t.getRawFontStyle(), maxFontSize);
			/*
			 * Here, we list the set of conditions under which the cache is valid. When one of the conditions changes,
			 * the cache must be recalculated. This is done by creating a list of the conditions then comparing it to
			 * the conditions stored alongside the cached value. If the stored conditions do not match, we recalculate
			 * the cache and store the new conditions.
			 */
			List<Object> breakWidthsCacheConditions = Lists.newArrayList();
			breakWidthsCacheConditions.add(text);
			breakWidthsCacheConditions.add(breakWidthsFont);
			breakWidthsCacheConditions.add(r.isAntialiasText());
			breakWidthsCacheConditions.add(r.getClass());
			if (!breakWidthsCacheConditions.equals(this.breakWidthsCacheConditions)) {
				this.breakWidthsCacheConditions = breakWidthsCacheConditions;
				breakWidths = new float[text.length()];
				BreakIterator breakIterator = new RegExBreakIterator(null, "\\s+|-");
				breakIterator.setText(text);
				int start = breakIterator.first();
				for (int end = breakIterator.next(); end != BreakIterator.DONE; start = end, end = breakIterator.next()) {
					String lineText = text.substring(0, end);
					String leftLineText = trimRight(lineText);
					int width = r.getTextSize(breakWidthsFont, leftLineText).width;
					Arrays.fill(breakWidths, start, end, width);
				}
			}

			RESIZING: while (minFontSize <= maxFontSize) {
				int trialFontSize;
				if (minFontSize == maxFontSize) {
					trialFontSize = minFontSize;
				}
				else {
					// must round up otherwise minFontSize may keep getting set to the same value
					trialFontSize = (maxFontSize + minFontSize + 1) / 2;
				}

				font = r.getFont(t.getRawFontName(), t.getRawFontStyle(), trialFontSize);
				lines.clear();
				lineWidths.clear();
				FontMetrics metrics = r.getFontMetrics(font);
				lineLeading = metrics.getLeading();
				lineAscent = metrics.getAscent();
				lineDescent = metrics.getDescent();
				totalWidth = 0;
				totalHeight = 0;
				float lineHeight = lineLeading + lineAscent + lineDescent;
				float adjustedSize = (float) localSize.width * breakWidthsFont.getSize() / font.getSize();

				int start = 0;
				float startingOffset = 0;
				while (start < text.length()) {
					if (totalHeight == 0) {
						// for the first line, only add the ascent
						totalHeight += lineAscent;
					}
					else {
						// for the remaining lines, add the descent too
						totalHeight += lineHeight;
					}
					if (totalHeight > localSize.height) {
						maxFontSize = trialFontSize - 1;
						continue RESIZING;
					}
					int end = Arrays
							.binarySearch(breakWidths, start, breakWidths.length, startingOffset + adjustedSize);
					if (end < 0) {
						end = -end - 1;
					}
					else {
						while (++end < breakWidths.length) {
							if (breakWidths[end] != breakWidths[end - 1]) {
								break;
							}
						}
					}
					if (start == end) {
						maxFontSize = trialFontSize - 1;
						continue RESIZING;
					}
					lines.add(trimRight(text.substring(start, end)));
					float width = (breakWidths[end - 1] - startingOffset) * font.getSize() / breakWidthsFont.getSize();
					lineWidths.add(width);
					totalWidth = Math.max(totalWidth, width);
					start = end;
					startingOffset = breakWidths[end - 1];
				}
				if (minFontSize == maxFontSize) {
					break RESIZING;
				}
				minFontSize = trialFontSize;
			}

			if (maxFontSize >= originalMinFontSize) {
				layoutData.font = font;
				layoutData.lines = lines;
				layoutData.lineWidths = lineWidths;
				layoutData.lineLeading = lineLeading;
				layoutData.lineAscent = lineAscent;
				layoutData.lineDescent = lineDescent;
				//layoutData.totalWidth = totalWidth;
				layoutData.totalHeight = totalHeight;
			}
		}

		return layoutData;
	}

	LayoutData layoutData;
	List<Object> layoutDataCacheConditions;

	@Override
	public boolean draw(Rectangle localBounds, IUIResources r) {
		Rectangle lbb = cm.worldToLocal(t.getRawBoundingBox());
		if (!lbb.intersects(localBounds)) {
			return false;
		}

		if (t.getText().trim().length() == 0) {
			return false;
		}

		RGB color = t.getRawColor();
		if (color != null) {

			List<Object> layoutDataCacheConditions = Lists.newArrayList();
			layoutDataCacheConditions.add(t.getRawText());
			layoutDataCacheConditions.add(t.getRawFontName());
			layoutDataCacheConditions.add(t.getRawFontStyle());
			layoutDataCacheConditions.add(t.getRawFontSize());
			layoutDataCacheConditions.add(t.isRawDontIncreaseFontSize());
			layoutDataCacheConditions.add(r.isAntialiasText());
			layoutDataCacheConditions.add(r.getClass());
			// don't use the local bounding box as it can vary slightly depending on location
			layoutDataCacheConditions.add(BNAUtils.toDimension(t.getRawBoundingBox()));
			layoutDataCacheConditions.add(cm.getLocalScale());
			if (!layoutDataCacheConditions.equals(this.layoutDataCacheConditions)) {
				this.layoutDataCacheConditions = layoutDataCacheConditions;
				layoutData = calculateLayout(t, BNAUtils.getSize(lbb), r);
			}

			if (layoutData.font != null) {
				float scale = 1.0f;
				float y = lbb.y;
				switch (t.getRawVerticalAlignment()) {
				case BOTTOM:
					y += lbb.height - layoutData.totalHeight * scale;
					break;
				case MIDDLE:
					y += Math.floor((lbb.height - layoutData.totalHeight * scale) / 2);
					break;
				case TOP:
					break;
				}
				for (int i = 0; i < layoutData.lines.size(); i++) {
					String line = layoutData.lines.get(i);
					float x = lbb.x;
					switch (t.getRawHorizontalAlignment()) {
					case RIGHT:
						x += lbb.width - layoutData.lineWidths.get(i) * scale;
						break;
					case CENTER:
						x += (lbb.width - layoutData.lineWidths.get(i) * scale) / 2;
						break;
					case LEFT:
						break;
					}
					r.drawText(layoutData.font, line, x, y - layoutData.lineLeading, color, 1);
					y += (layoutData.lineLeading + layoutData.lineAscent + layoutData.lineDescent) * scale;
				}
			}
		}

		return true;
	}

	@Override
	public boolean isInThing(ICoordinate location) {
		return t.getRawBoundingBox().contains(location.getWorldPoint());
	}
}
