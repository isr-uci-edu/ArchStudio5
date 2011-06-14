package org.archstudio.bna;

import java.util.Map;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;

public class CompositeResource implements IResources {

	final Composite c;
	final Device d;

	final Map<RGB, Color> autoColorCache = new MapMaker().makeComputingMap(new Function<RGB, Color>() {
		@Override
		public Color apply(RGB input) {
			return new Color(d, input);
		}
	});
	final Map<FontData, Font> autoFontCache = new MapMaker().makeComputingMap(new Function<FontData, Font>() {
		@Override
		public Font apply(FontData input) {
			return new Font(d, input);
		}
	});

	public CompositeResource(Composite c) {
		this.c = c;
		this.d = c.getDisplay();
	}

	@Override
	public void dispose() {
		for (Color c : autoColorCache.values()) {
			try {
				c.dispose();
			}
			catch (Throwable t) {
			}
		}
		autoColorCache.clear();
	}

	@Override
	public Color getColor(int systemColor) {
		return d.getSystemColor(systemColor);
	}

	@Override
	public Color getColor(RGB color) {
		return autoColorCache.get(color);
	}

	@Override
	public boolean setForegroundColor(Graphics g, IThing thing, IThingKey<RGB> colorKey) {
		RGB color = thing.get(colorKey);
		if (color != null) {
			g.setForegroundColor(getColor(color));
			return true;
		}
		return false;
	}

	@Override
	public boolean setBackgroundColor(Graphics g, IThing thing, IThingKey<RGB> colorKey) {
		RGB color = thing.get(colorKey);
		if (color != null) {
			g.setBackgroundColor(getColor(color));
			return true;
		}
		return false;
	}

	@Override
	public Font getFont(String fontName, int size, FontStyle style) {
		return autoFontCache.get(new FontData(fontName, size, style.toSWT()));
	}

	@Override
	public boolean setFont(Graphics g, IHasFontData thing) {
		String fontName = thing.getFontName();
		int size = thing.getFontSize();
		FontStyle style = thing.getFontStyle();
		g.setFont(getFont(fontName, size, style));
		return true;
	}

	@Override
	public boolean setLineStyle(Graphics g, IHasLineData thing) {
		g.setLineStyle(thing.getLineStyle());
		g.setLineWidth(thing.getLineWidth());
		return true;
	}

	@Override
	public Device getDevice() {
		return d;
	}

	@Override
	public Composite getComposite() {
		return c;
	}
}
