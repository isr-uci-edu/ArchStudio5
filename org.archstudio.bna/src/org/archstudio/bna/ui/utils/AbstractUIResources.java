package org.archstudio.bna.ui.utils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Rectangle2D;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.sysutils.SystemUtils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

public abstract class AbstractUIResources implements IUIResources {

	protected static class FontMetrics implements IUIResources.FontMetrics {

		private final float leading, ascent, descent;

		public FontMetrics(float leading, float ascent, float descent) {
			this.leading = leading;
			this.ascent = ascent;
			this.descent = descent;
		}

		@Override
		public float getLeading() {
			return leading;
		}

		@Override
		public float getAscent() {
			return ascent;
		}

		@Override
		public float getDescent() {
			return descent;
		}

		@Override
		public String toString() {
			return "FontMetrics [leading=" + leading + ", ascent=" + ascent + ", descent=" + descent + "]";
		}

	}

	private final LoadingCache<List<? extends Object>, Font> fonts = CacheBuilder.newBuilder().build(
			new CacheLoader<List<? extends Object>, Font>() {
				@Override
				public Font load(List<? extends Object> data) throws Exception {
					return _getFont((String) data.get(0), (FontStyle) data.get(1), (Integer) data.get(2));
				}
			});
	private final LoadingCache<Font, IUIResources.FontMetrics> fontMetrics = CacheBuilder.newBuilder().build(
			new CacheLoader<Font, IUIResources.FontMetrics>() {
				@Override
				public IUIResources.FontMetrics load(Font font) throws Exception {
					LineMetrics fontMetrics = font.getLineMetrics("", fontRenderContext);
					Rectangle2D stdMetrics = font.createGlyphVector(fontRenderContext, "O")
							.getOutline(0, fontMetrics.getAscent()).getBounds2D();
					float leading = (float) stdMetrics.getY();
					float ascent = (float) stdMetrics.getMaxY() - leading;
					float descent = fontMetrics.getHeight() - (leading + ascent);
					return new FontMetrics(leading, ascent, descent);
				}
			});
	private boolean antialiasGraphics = true;
	private boolean antialiasText = true;
	private FontRenderContext fontRenderContext = new FontRenderContext(null, antialiasText, false);
	private boolean decorativeGraphics = true;
	private boolean displayShadows = true;
	private Deque<Float> globalAlphaStack = new LinkedList<>();
	private float globalAlpha = 1f;

	public AbstractUIResources() {
	}

	@Override
	public void dispose() {
		fonts.invalidateAll();
		fontMetrics.invalidateAll();
	}

	@Override
	public boolean isAntialiasGraphics() {
		return antialiasGraphics;
	}

	@Override
	public void setAntialiasGraphics(boolean antialiasGraphics) {
		this.antialiasGraphics = antialiasGraphics;
	}

	@Override
	public boolean isAntialiasText() {
		return antialiasText;
	}

	@Override
	public void setAntialiasText(boolean antialiasText) {
		if (this.antialiasText != antialiasText) {
			this.antialiasText = antialiasText;
			fontRenderContext = new FontRenderContext(null, antialiasText, false);
		}
	}

	@Override
	public boolean isDecorativeGraphics() {
		return decorativeGraphics;
	}

	@Override
	public void setDecorativeGraphics(boolean decorativeGraphics) {
		this.decorativeGraphics = decorativeGraphics;
	}

	@Override
	public boolean isDisplayShadows() {
		return displayShadows;
	}

	@Override
	public void setDisplayShadows(boolean displayShadows) {
		this.displayShadows = displayShadows;
	}

	@Override
	public double pushAlpha(double alpha) {
		globalAlphaStack.push(globalAlpha);
		globalAlpha *= alpha;
		return globalAlpha;
	}

	@Override
	public double popAlpha() {
		return globalAlpha = globalAlphaStack.pop();
	}

	protected float getGlobalAlpha() {
		return globalAlpha;
	}

	@Override
	public Font getFont(String name, FontStyle style, int size) {
		/*
		 * AWT fonts are noticeably smaller than SWT fonts. We scale them based on their ascent to make them
		 * approximately the same size.
		 */
		IUIResources.FontMetrics metrics = getFontMetrics(_getFont(name, style, size));
		return fonts.getUnchecked(Lists.<Object> newArrayList(name, style,
				SystemUtils.round(size * size / metrics.getAscent())));
	}

	protected Font _getFont(String name, FontStyle style, int size) {
		return new Font(name, style.toAWT(), size);
	}

	@Override
	public IUIResources.FontMetrics getFontMetrics(Font font) {
		return fontMetrics.getUnchecked(font);
	}

	@Override
	public Dimension getTextSize(Font font, String text) {
		return BNAUtils.toDimension(font.getStringBounds(text, fontRenderContext));
	}

}