package org.archstudio.bna;

public class BNARenderingSettings {
	private BNARenderingSettings() {
	}

	public static final String SETTING_ANTIALIAS_TEXT = "antialiasText";
	public static final String SETTING_ANTIALIAS_GRAPHICS = "antialiasGraphics";
	public static final String SETTING_DECORATIVE_GRAPHICS = "decorativeGraphics";

	public static boolean getAntialiasText(BNAComposite c) {
		Object o = c.getData(SETTING_ANTIALIAS_TEXT);
		if ((o != null) && (o instanceof Boolean)) {
			return ((Boolean) o).booleanValue();
		}
		return false;
	}

	public static boolean getAntialiasGraphics(BNAComposite c) {
		Object o = c.getData(SETTING_ANTIALIAS_GRAPHICS);
		if ((o != null) && (o instanceof Boolean)) {
			return ((Boolean) o).booleanValue();
		}
		return false;
	}

	public static boolean getDecorativeGraphics(BNAComposite c) {
		Object o = c.getData(SETTING_DECORATIVE_GRAPHICS);
		if ((o != null) && (o instanceof Boolean)) {
			return ((Boolean) o).booleanValue();
		}
		return false;
	}

	public static void setAntialiasText(BNAComposite c, boolean antialiasText) {
		c.setData(SETTING_ANTIALIAS_TEXT, antialiasText);
	}

	public static void setAntialiasGraphics(BNAComposite c, boolean antialiasGraphics) {
		c.setData(SETTING_ANTIALIAS_GRAPHICS, antialiasGraphics);
	}

	public static void setDecorativeGraphics(BNAComposite c, boolean decorativeGraphics) {
		c.setData(SETTING_DECORATIVE_GRAPHICS, decorativeGraphics);
	}
}
