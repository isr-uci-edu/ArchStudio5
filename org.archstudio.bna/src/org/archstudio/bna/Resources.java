package org.archstudio.bna;

import java.awt.Font;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.media.opengl.GL2;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.bna.facets.IHasLineStyle;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.collect.Lists;
import com.jogamp.opengl.util.awt.TextRenderer;

public class Resources implements IResources {

	final Composite composite;
	final GL2 gl;

	public Resources(Composite composite, GL2 gl) {
		super();
		this.composite = composite;
		this.gl = gl;
	}

	@Override
	public void destroy() {
	}

	@Override
	public Device getDevice() {
		return composite.getDisplay();
	}

	@Override
	public Composite getComposite() {
		return composite;
	}

	@Override
	public GL2 getGL() {
		return gl;
	}

	@Override
	public boolean setColor(IThing thing, IThingKey<RGB> colorKey) {
		RGB color = thing.get(colorKey);
		if (color != null) {
			setColor(color, 1f);
			return true;
		}
		return false;
	}

	@Override
	public void setColor(RGB color, float alpha) {
		gl.glColor4f(color.red / 255f, color.green / 255f, color.blue / 255f, alpha);
	}

	@Override
	public boolean setLineStyle(IHasLineData thing) {
		Integer width = thing.getLineWidth();
		Integer style = thing.getLineStyle();
		if (width != null && style != null) {
			gl.glLineWidth(width - 0.275f);
			int pattern = 0xffff;
			switch (style) {
			case IHasLineStyle.LINE_STYLE_DASH:
				pattern = 0x0f0f;
				break;
			case IHasLineStyle.LINE_STYLE_DOT:
				pattern = 0x5555;
				break;
			case IHasLineStyle.LINE_STYLE_DASHDOT:
				pattern = 0x2727;
				break;
			case IHasLineStyle.LINE_STYLE_DASHDOTDOT:
				pattern = 0x111f;
				break;
			case IHasLineStyle.LINE_STYLE_SOLID:
			default:
				break;
			}
			gl.glLineStipple(1, (short) pattern);
			return true;
		}
		return false;
	}

	Cache<List<Object>, Font> fontCache = CacheBuilder.newBuilder().build(new CacheLoader<List<Object>, Font>() {
		@Override
		public Font load(List<Object> key) throws Exception {
			return new Font((String) key.get(0), (Integer) key.get(1), (Integer) key.get(2));
		}
	});

	@Override
	public Font getFont(IHasFontData thing, int size) {
		FontStyle fontStyle = thing.getFontStyle();
		int awtFontStyle = Font.PLAIN;
		switch (fontStyle) {
		case BOLD:
			awtFontStyle = Font.BOLD;
			break;
		case ITALIC:
			awtFontStyle = Font.ITALIC;
			break;
		case NORMAL:
		default:
			break;
		}
		try {
			return fontCache.get(Lists.<Object> newArrayList(thing.getFontName(), awtFontStyle, size));
		}
		catch (ExecutionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Font getFont(IHasFontData thing) {
		return getFont(thing, thing.getFontSize());
	}

	boolean antialiasText = true;
	Cache<Font, TextRenderer> textRendererCache = CacheBuilder.newBuilder().build(
			new CacheLoader<Font, TextRenderer>() {
				@Override
				public TextRenderer load(Font key) throws Exception {
					return new TextRenderer(key, antialiasText, false, null, false);
				}
			});

	public void setAntialiasText(boolean antialiasText) {
		if (this.antialiasText != antialiasText) {
			textRendererCache.invalidateAll();
			this.antialiasText = antialiasText;
		}
	}

	@Override
	public TextRenderer getTextRenderer(Font f) {
		try {
			return textRendererCache.get(f);
		}
		catch (ExecutionException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
