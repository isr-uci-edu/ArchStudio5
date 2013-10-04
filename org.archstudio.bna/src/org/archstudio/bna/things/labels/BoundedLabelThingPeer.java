package org.archstudio.bna.things.labels;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.text.BreakIterator;
import java.util.Arrays;
import java.util.List;

import javax.media.opengl.GL2;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.Resources;
import org.archstudio.bna.things.AbstractRectangleThingPeer;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.TextUtils;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.sysutils.RegExBreakIterator;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import com.google.common.collect.Lists;

public class BoundedLabelThingPeer<T extends BoundedLabelThing> extends AbstractRectangleThingPeer<T> {

	private static final int MIN_FONT_SIZE = 2;
	private static final int MIN_LOCAL_WIDTH = 4;
	private static final int MIN_LOCAL_HEIGHT = MIN_FONT_SIZE + 1;

	private static final class LayoutData {
		Font font = null;
		List<String> lines = Lists.newArrayList();
		List<Float> lineWidths = Lists.newArrayList();
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

	private static final LayoutData calculateLayout(BoundedLabelThing t, Dimension localSize,
			FontRenderContext fontRenderContext) {

		LayoutData layoutData = new LayoutData();

		if (localSize.width > MIN_LOCAL_WIDTH && localSize.height > MIN_LOCAL_HEIGHT) {

			Font font = null;
			List<String> lines = Lists.newArrayList();
			List<Float> lineWidths = Lists.newArrayList();
			float lineAscent = 0;
			float lineDescent = 0;
			float totalWidth = 0;
			float totalHeight = 0;

			String text = t.getText();
			String fontName = t.getFontName();
			FontStyle fontStyle = t.getFontStyle();
			int minFontSize = MIN_FONT_SIZE;
			int originalMinFontSize = minFontSize;
			int maxFontSize = t.getFontSize();

			float[] breakWidths = new float[text.length()];
			Font breakWidthsFont = new Font(fontName, fontStyle.toAWT(), maxFontSize);
			{
				BreakIterator breakIterator = new RegExBreakIterator(null, "\\s+|-");
				breakIterator.setText(text);
				int start = breakIterator.first();
				float previousWidth = 0;
				for (int end = breakIterator.next(); end != BreakIterator.DONE; start = end, end = breakIterator.next()) {
					String lineText = text.substring(start, end);
					String leftLineText = trimRight(lineText);
					String rightLineText = lineText.substring(leftLineText.length());
					float width = (float) breakWidthsFont.getStringBounds(leftLineText, fontRenderContext).getWidth();
					Arrays.fill(breakWidths, start, end, previousWidth += width);
					if (rightLineText.length() > 0) {
						Rectangle2D bounds = breakWidthsFont.getStringBounds(rightLineText, fontRenderContext);
						previousWidth += (float) bounds.getWidth();
					}
				}
			}

			RESIZING: while (minFontSize <= maxFontSize) {
				int trialFontSize;
				if (minFontSize == maxFontSize) {
					trialFontSize = minFontSize;
				}
				else {
					trialFontSize = (maxFontSize + minFontSize + 1) / 2; // round up
				}

				font = new Font(fontName, fontStyle.toAWT(), trialFontSize);
				lines.clear();
				lineWidths.clear();
				LineMetrics lineMetrics = font.getLineMetrics(text, fontRenderContext);
				lineAscent = lineMetrics.getAscent();
				lineDescent = lineMetrics.getDescent();
				totalWidth = 0;
				totalHeight = 0;
				float lineHeight = lineAscent + lineDescent;
				float adjustedSize = localSize.width * breakWidthsFont.getSize2D() / trialFontSize;

				int start = 0;
				float startingOffset = 0;
				while (start < text.length()) {
					totalHeight += lineHeight;
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
					float width = (breakWidths[end - 1] - startingOffset) * trialFontSize / breakWidthsFont.getSize2D();
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
				layoutData.lineAscent = lineAscent;
				layoutData.lineDescent = lineDescent;
				//layoutData.totalWidth = totalWidth;
				layoutData.totalHeight = totalHeight;
			}
		}

		return layoutData;
	}

	public BoundedLabelThingPeer(T thing, IBNAView view, ICoordinateMapper cm) {
		super(thing, view, cm);
	}

	@Override
	public void draw(GL2 gl, Rectangle localBounds, Resources r) {

		Rectangle lbb = BNAUtils.getLocalBoundingBox(cm, t);
		if (!lbb.intersects(localBounds)) {
			return;
		}

		if (t.getText().trim().length() == 0) {
			return;
		}

		RGB rgb = t.getColor();
		if (rgb != null) {
			gl.glColor3f(rgb.red / 255f, rgb.green / 255f, rgb.blue / 255f);

			TextUtils textUtils = r.getTextUtils();
			LayoutData layoutData = calculateLayout(t, BNAUtils.getSize(lbb), textUtils.getFontRenderContext());

			if (layoutData.font != null) {
				float scale = 1.0f;
				textUtils.beginRendering();
				try {
					float y = lbb.y;
					switch (t.getVerticalAlignment()) {
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
						switch (t.getHorizontalAlignment()) {
						case RIGHT:
							x += lbb.width - layoutData.lineWidths.get(i) * scale;
							break;
						case CENTER:
							x += (lbb.width - layoutData.lineWidths.get(i) * scale) / 2;
							break;
						case LEFT:
							break;
						}
						textUtils.draw(layoutData.font, line, x, localBounds.height
								- (y + layoutData.lineAscent * scale));
						y += (layoutData.lineAscent + layoutData.lineDescent) * scale;
					}
				}
				finally {
					textUtils.endRendering(gl, localBounds);
				}
			}
		}
	}
}
