package org.archstudio.bna.constants;

import java.awt.RenderingHints;

public enum Antialias {

	OFF(RenderingHints.VALUE_TEXT_ANTIALIAS_OFF, false, 0, 0, 0, 0, 0, 0), //
	ON(RenderingHints.VALUE_TEXT_ANTIALIAS_ON, false, 0, 0, 0, 0, 0, 0), //
	HRGB(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB, true, -1 / 3f, 0, 0, 0, +1 / 3f, 0), //
	HBGR(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HBGR, true, +1 / 3f, 0, 0, 0, -1 / 3f, 0), //
	VRGB(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VRGB, true, 0, -1 / 3f, 0, 0, 0, +1 / 3f), //
	VBGR(RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_VBGR, true, 0, +1 / 3f, 0, 0, 0, -1 / 3f); //

	private final Object antialias;
	private final boolean subpixelAntialiasing;
	private final float rx, ry, gx, gy, bx, by;

	private Antialias(Object antialias, boolean subpixelAntialiasing, float rx, float ry, float gx, float gy, float bx,
			float by) {
		this.antialias = antialias;
		this.subpixelAntialiasing = subpixelAntialiasing;
		this.rx = rx;
		this.ry = ry;
		this.gx = gx;
		this.gy = gy;
		this.bx = bx;
		this.by = by;
	}

	public Object getAntialiasRendeingHint() {
		return antialias;
	}

	public boolean isSubpixelAntialiasing() {
		return subpixelAntialiasing;
	}

	public float getSubpixelRedXDelta() {
		return rx;
	}

	public float getSubpixelRedYDelta() {
		return ry;
	}

	public float getSubpixelGreenXDelta() {
		return gx;
	}

	public float getSubpixelGreenYDelta() {
		return gy;
	}

	public float getSubpixelBlueXDelta() {
		return bx;
	}

	public float getSubpixelBlueYDelta() {
		return by;
	}

	public float[] getSubpixelRGBXYDeltas() {
		return new float[] { rx, ry, gx, gy, bx, by };
	}

}
