package org.archstudio.bna;

import javax.media.opengl.GL2;

import org.archstudio.bna.utils.GL2Delegate;
import org.eclipse.swt.graphics.RGB;

public class ObscuredGL2 extends GL2Delegate {

	double alpha = 1d;
	RGB tint = new RGB(0, 0, 0);

	public ObscuredGL2(GL2 gl) {
		super(gl);
	}

	public double getAlpha() {
		return alpha;
	}

	public void setAlpha(double alpha) {
		this.alpha = alpha;
	}

	public RGB getTint() {
		return tint;
	}

	public void setTint(RGB tint) {
		this.tint = tint;
	}

	protected double c(double v, double f) {
		return v * (1d - f) + f;
	}

	protected double r(double v) {
		return c(v, tint.red / 255d);
	}

	protected double g(double v) {
		return c(v, tint.green / 255d);
	}

	protected double b(double v) {
		return c(v, tint.blue / 255d);
	}

	protected double a(double v) {
		return v * alpha;
	}

	@Override
	public void glColor3f(float arg0, float arg1, float arg2) {
		gl.glColor4d(r(arg0), g(arg1), b(arg2), alpha);
	}

	@Override
	public void glColor4f(float arg0, float arg1, float arg2, float arg3) {
		gl.glColor4d(r(arg0), g(arg1), b(arg2), a(arg3));
	}

	@Override
	public void glColor3d(double arg0, double arg1, double arg2) {
		gl.glColor4d(r(arg0), g(arg1), b(arg2), alpha);
	}

	@Override
	public void glColor4d(double arg0, double arg1, double arg2, double arg3) {
		gl.glColor4d(r(arg0), g(arg1), b(arg2), a(arg3));
	}

}
