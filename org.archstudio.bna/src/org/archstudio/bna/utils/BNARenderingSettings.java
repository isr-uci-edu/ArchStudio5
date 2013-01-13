package org.archstudio.bna.utils;

import org.eclipse.swt.widgets.Widget;

public class BNARenderingSettings {
	private BNARenderingSettings() {
	}

	public static final String SETTING_ANTIALIAS_TEXT = "antialiasText";
	public static final String SETTING_ANTIALIAS_GRAPHICS = "antialiasGraphics";
	public static final String SETTING_DECORATIVE_GRAPHICS = "decorativeGraphics";
	public static final String SETTING_DISPLAY_SHADOWS = "displayShadows";

	public static boolean getAntialiasText(Widget c) {
		Object o = c.getData(SETTING_ANTIALIAS_TEXT);
		if (o != null && o instanceof Boolean) {
			return ((Boolean) o).booleanValue();
		}
		return false;
	}

	public static boolean getAntialiasGraphics(Widget c) {
		Object o = c.getData(SETTING_ANTIALIAS_GRAPHICS);
		if (o != null && o instanceof Boolean) {
			return ((Boolean) o).booleanValue();
		}
		return false;
	}

	public static boolean getDecorativeGraphics(Widget c) {
		Object o = c.getData(SETTING_DECORATIVE_GRAPHICS);
		if (o != null && o instanceof Boolean) {
			return ((Boolean) o).booleanValue();
		}
		return false;
	}

	public static boolean getDisplayShadows(Widget c) {
		Object o = c.getData(SETTING_DISPLAY_SHADOWS);
		if (o != null && o instanceof Boolean) {
			return ((Boolean) o).booleanValue();
		}
		return false;
	}

	public static void setAntialiasText(Widget c, boolean antialiasText) {
		c.setData(SETTING_ANTIALIAS_TEXT, antialiasText);
	}

	public static void setAntialiasGraphics(Widget c, boolean antialiasGraphics) {
		c.setData(SETTING_ANTIALIAS_GRAPHICS, antialiasGraphics);
	}

	public static void setDecorativeGraphics(Widget c, boolean decorativeGraphics) {
		c.setData(SETTING_DECORATIVE_GRAPHICS, decorativeGraphics);
	}

	public static void setDisplayShadows(Widget c, boolean showShadows) {
		c.setData(SETTING_DISPLAY_SHADOWS, showShadows);
	}
}
