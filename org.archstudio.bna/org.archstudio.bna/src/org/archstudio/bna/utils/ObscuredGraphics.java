package org.archstudio.bna.utils;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.ScaledGraphics;

final class ObscuredGraphics extends ScaledGraphics {

	ObscuredGraphics(Graphics g) {
		super(g);
	}

	public void setAlpha(int alpha) {
		// do nothing
	}

	public void setLineDash(float[] value) {
		// do nothing
	}

	public void setLineDash(int[] dash) {
		// do nothing
	}

	public void setLineStyle(int arg0) {
		// do nothing
	}
}