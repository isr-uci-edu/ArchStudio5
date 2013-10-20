package org.archstudio.bna.utils;

import org.eclipse.swt.widgets.Widget;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class BNARenderingSettings {

	private static class RenderSettings {
		boolean antialiasText = true;
		boolean antialiasGraphics = true;
		boolean decorativeGraphics = true;
		boolean displayShadows = true;
	}

	private static LoadingCache<Widget, RenderSettings> renderSettings = CacheBuilder.newBuilder().weakKeys()
			.build(new CacheLoader<Widget, RenderSettings>() {
				@Override
				public RenderSettings load(Widget key) throws Exception {
					return new RenderSettings();
				};
			});

	private BNARenderingSettings() {
	}

	public static boolean getAntialiasText(Widget c) {
		return renderSettings.getUnchecked(c).antialiasText;
	}

	public static boolean getAntialiasGraphics(Widget c) {
		return renderSettings.getUnchecked(c).antialiasGraphics;
	}

	public static boolean getDecorativeGraphics(Widget c) {
		return renderSettings.getUnchecked(c).decorativeGraphics;
	}

	public static boolean getDisplayShadows(Widget c) {
		return renderSettings.getUnchecked(c).displayShadows;
	}

	public static void setAntialiasText(Widget c, boolean antialiasText) {
		renderSettings.getUnchecked(c).antialiasText = antialiasText;
	}

	public static void setAntialiasGraphics(Widget c, boolean antialiasGraphics) {
		renderSettings.getUnchecked(c).antialiasGraphics = antialiasGraphics;
	}

	public static void setDecorativeGraphics(Widget c, boolean decorativeGraphics) {
		renderSettings.getUnchecked(c).decorativeGraphics = decorativeGraphics;
	}

	public static void setDisplayShadows(Widget c, boolean displayShadows) {
		renderSettings.getUnchecked(c).displayShadows = displayShadows;
	}
}
