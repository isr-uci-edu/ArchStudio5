package org.archstudio.bna.ui.utils;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.LineMetrics;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import org.archstudio.bna.ICoordinateMapper;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.ui.IUIResources;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.swtutils.constants.FontStyle;
import org.archstudio.sysutils.SystemUtils;
import org.eclipse.swt.graphics.RGB;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

public abstract class AbstractUIResources implements IUIResources {

	protected final static boolean DEBUG = false;

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
					return _getFontMetrics(font);
				}
			});
	private final LoadingCache<List<? extends Object>, Dimension> fontTextSizes = CacheBuilder.newBuilder().build(
			new CacheLoader<List<? extends Object>, Dimension>() {
				@Override
				public Dimension load(List<? extends Object> data) throws Exception {
					return _getTextSize((Font) data.get(0), (String) data.get(1));
				}
			});
	private LoadingCache<Shape, int[]> shapeXYInts = CacheBuilder.newBuilder().weakKeys().maximumSize(16)
			.build(new CacheLoader<Shape, int[]>() {
				@Override
				public int[] load(Shape shape) throws Exception {
					return BNAUtils.toXYIntArray(shape);
				}
			});
	private LoadingCache<Shape, float[]> shapeXYFloats = CacheBuilder.newBuilder().weakKeys().maximumSize(16)
			.build(new CacheLoader<Shape, float[]>() {
				@Override
				public float[] load(Shape shape) throws Exception {
					return BNAUtils.toXYFloatArray(shape);
				}
			});
	private LoadingCache<Shape, List<Point2D>> shapePointsList = CacheBuilder.newBuilder().weakKeys().maximumSize(16)
			.build(new CacheLoader<Shape, List<Point2D>>() {
				@Override
				public List<Point2D> load(Shape shape) throws Exception {
					return BNAUtils.toPoints(shape);
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

	public void dispose() {
		fontMetrics.invalidateAll();
		fontTextSizes.invalidateAll();
		shapeXYInts.invalidateAll();
		shapeXYFloats.invalidateAll();
		shapePointsList.invalidateAll();
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
			fontTextSizes.invalidateAll();
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
	public boolean setColor(IThing thing, IThingKey<RGB> colorKey) {
		return setColor(thing.get(colorKey), 1f);
	}

	@Override
	public boolean setColor(IThing thing, IThingKey<RGB> colorKey, double alpha) {
		return setColor(thing.get(colorKey), alpha);
	}

	@Override
	public Font getFont(IHasFontData thing, ICoordinateMapper cm) {
		int size = thing.getFontSize();
		double scaledSize = size * cm.getLocalScale();
		if (thing.getDontIncreaseFontSize() && scaledSize > size) {
			scaledSize = size;
		}
		return getFont(thing, SystemUtils.round(scaledSize));
	}

	@SuppressWarnings("unchecked")
	@Override
	final public Font getFont(IHasFontData thing, int size) {
		return fonts.getUnchecked(Lists.newArrayList(thing.getFontName(), thing.getFontStyle(), size));
	}

	protected Font _getFont(String fontName, FontStyle fontStyle, int size) {
		/*
		 * AWT fonts are noticeably smaller than SWT fonts. We scale them based on their ascent to make them
		 * approximately the same size.
		 */
		IUIResources.FontMetrics metrics = getFontMetrics(new Font(fontName, fontStyle.toAWT(), size));
		return new Font(fontName, fontStyle.toAWT(), SystemUtils.round(size * size / metrics.getAscent()));
	}

	@Override
	final public IUIResources.FontMetrics getFontMetrics(Font font) {
		return fontMetrics.getUnchecked(font);
	}

	protected IUIResources.FontMetrics _getFontMetrics(Font font) {
		LineMetrics fontMetrics = font.getLineMetrics("", fontRenderContext);
		Rectangle2D stdMetrics = font.createGlyphVector(fontRenderContext, "O").getOutline(0, fontMetrics.getAscent())
				.getBounds2D();
		float leading = (float) stdMetrics.getY();
		float ascent = (float) stdMetrics.getMaxY() - leading;
		float descent = fontMetrics.getHeight() - (leading + ascent);
		return new FontMetrics(leading, ascent, descent);
	}

	@Override
	final public Dimension getTextSize(Font font, String text) {
		return BNAUtils.clone(fontTextSizes.getUnchecked(Lists.newArrayList(font, text)));
	}

	protected Dimension _getTextSize(Font font, String text) {
		return BNAUtils.toDimension(font.getStringBounds(text, fontRenderContext));
	}

	protected int[] toXYIntArray(Shape shape) {
		return shapeXYInts.getUnchecked(shape);
	}

	protected float[] toXYFloatArray(Shape shape) {
		return shapeXYFloats.getUnchecked(shape);
	}

	protected List<Point2D> toPointsList(Shape shape) {
		return shapePointsList.getUnchecked(shape);
	}

	@Override
	public void fillShape(Shape localShape) {
		fillShape(localShape, null, null, Double.NaN);
	}

	@Override
	public void fillShape(Shape localShape, RGB color1, RGB color2) {
		fillShape(localShape, color1, color2, Double.NaN);
	}

}