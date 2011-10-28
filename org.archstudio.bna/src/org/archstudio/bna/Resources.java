package org.archstudio.bna;

import java.util.concurrent.TimeUnit;

import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.facets.IHasFontData;
import org.archstudio.bna.facets.IHasLineData;
import org.archstudio.swtutils.constants.FontStyle;
import org.eclipse.draw2d.Graphics;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class Resources implements IResources {

	// Note: this needs to be a composite (not a control) to support adding controls as part of thing peers
	final Composite composite;
	final Device device;

	final Cache<RGB, Color> colorCache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES)
			.removalListener(new RemovalListener<RGB, Color>() {
				@Override
				public void onRemoval(RemovalNotification<RGB, Color> notification) {
					notification.getValue().dispose();
				}
			}).build(new CacheLoader<RGB, Color>() {
				@Override
				public Color load(RGB input) {
					return new Color(device, input);
				}
			});

	final Cache<FontData, Font> fontCache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES)
			.removalListener(new RemovalListener<FontData, Font>() {
				@Override
				public void onRemoval(RemovalNotification<FontData, Font> notification) {
					notification.getValue().dispose();
				}
			}).build(new CacheLoader<FontData, Font>() {
				@Override
				public Font load(FontData input) {
					return new Font(device, input);
				}
			});

	final Cache<ImageData, Image> imageCache = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES)
			.removalListener(new RemovalListener<ImageData, Image>() {
				@Override
				public void onRemoval(RemovalNotification<ImageData, Image> notification) {
					notification.getValue().dispose();
				}
			}).build(new CacheLoader<ImageData, Image>() {
				@Override
				public Image load(ImageData input) {
					return new Image(device, input);
				}
			});

	public Resources(Composite c) {
		this(c, c.getDisplay());
	}

	public Resources(Composite c, Device d) {
		super();
		this.composite = c;
		this.device = d;
	}

	@Override
	public void dispose() {
		colorCache.invalidateAll();
		colorCache.cleanUp();
		fontCache.invalidateAll();
		fontCache.cleanUp();
		imageCache.invalidateAll();
		imageCache.cleanUp();
	}

	@Override
	public Color getColor(int systemColor) {
		return device.getSystemColor(systemColor);
	}

	@Override
	public Color getColor(RGB color) {
		return colorCache.getUnchecked(color);
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
		return fontCache.getUnchecked(new FontData(fontName, size, style.toSWT()));
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
		// see https://bugs.eclipse.org/bugs/show_bug.cgi?id=155511
		int width = thing.getLineWidth();
		g.setLineWidth(width == 1 ? 0 : width);
		return true;
	}

	@Override
	public Device getDevice() {
		return device;
	}

	@Override
	public Composite getComposite() {
		return composite;
	}

	@Override
	public Image getImage(ImageData imageData) {
		return imageCache.getUnchecked(imageData);
	}
}
