package org.archstudio.bna;

import java.awt.Font;
import java.util.List;

import javax.media.opengl.GL2;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasEdgeColor;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.bna.utils.TextUtils;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

public class Resources {

	final Composite composite;

	private final GL2 gl;
	private final TextUtils textUtils;

	public Resources(Composite composite, GL2 gl, boolean antialiasText) {
		this.composite = composite;
		this.gl = gl;
		this.textUtils = new TextUtils(antialiasText);
	}

	public void dispose() {
		textUtils.dispose();
	}

	public Device getDevice() {
		return composite.getDisplay();
	}

	public Composite getComposite() {
		return composite;
	}

	public GL2 getGL() {
		return gl;
	}

	public TextUtils getTextUtils() {
		return textUtils;
	}

	public boolean setColor(IThing thing, IThingKey<RGB> colorKey) {
		RGB color = thing.get(colorKey);
		if (color != null) {
			setColor(color, 1f);
			return true;
		}
		return false;
	}

	public boolean setColor(IThing thing, IThingKey<RGB> colorKey, float alpha) {
		RGB color = thing.get(colorKey);
		if (color != null) {
			setColor(color, alpha);
			return true;
		}
		return false;
	}

	public boolean setColor(RGB color, float alpha) {
		if (color != null) {
			gl.glColor4f(color.red / 255f, color.green / 255f, color.blue / 255f, alpha);
			return true;
		}
		return false;
	}

	public boolean setLineStyle(IHasLineData thing) {
		if (setColor(thing, IHasEdgeColor.EDGE_COLOR_KEY)) {
			gl.glLineWidth(thing.getLineWidth());
			gl.glLineStipple(1, (short) thing.getLineStyle().toBitPattern());
			return true;
		}
		return false;
	}

	public void resetLineStyle() {
		gl.glLineWidth(1);
		gl.glLineStipple(1, (short) 0xffffffff);
	}

	Cache<List<Object>, Font> fontCache = CacheBuilder.newBuilder().build(new CacheLoader<List<Object>, Font>() {

		@Override
		public Font load(List<Object> key) throws Exception {
			return new Font((String) key.get(0), (Integer) key.get(1), (Integer) key.get(2));
		}
	});

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
		return new Font(thing.getFontName(), awtFontStyle, size);
	}

	public Font getFont(IHasFontData thing) {
		return getFont(thing, thing.getFontSize());
	}

}
