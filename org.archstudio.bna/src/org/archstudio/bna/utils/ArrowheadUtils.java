package org.archstudio.bna.utils;

public class ArrowheadUtils {
	/*
	 * NOTICE Copyright 1997-2003 by: Institut National de Recherche en
	 * Informatique et Automatique (INRIA). This software is being provided by
	 * the copyright holders under the following license. By obtaining, using
	 * and/or copying this software, you agree that you have read, understood,
	 * and will comply with the following terms and conditions: Permission to
	 * use, copy, modify, and distribute this software and its documentation for
	 * any purpose and without fee or royalty is hereby granted, provided that
	 * the full text of this NOTICE appears on ALL copies of the software and
	 * documentation or portions thereof, including modifications, that you
	 * make. THIS SOFTWARE IS PROVIDED "AS IS," AND COPYRIGHT HOLDERS MAKE NO
	 * REPRESENTATIONS OR WARRANTIES, EXPRESS OR IMPLIED. BY WAY OF EXAMPLE, BUT
	 * NOT LIMITATION, COPYRIGHT HOLDERS MAKE NO REPRESENTATIONS OR WARRANTIES
	 * OF MERCHANTABILITY OR FITNESS FOR ANY PARTICULAR PURPOSE OR THAT THE USE
	 * OF THE SOFTWARE OR DOCUMENTATION WILL NOT INFRINGE ANY THIRD PARTY
	 * PATENTS, COPYRIGHTS, TRADEMARKS OR OTHER RIGHTS. COPYRIGHT HOLDERS WILL
	 * BEAR NO LIABILITY FOR ANY USE OF THIS SOFTWARE OR DOCUMENTATION. The name
	 * and trademarks of copyright holders may NOT be used in advertising or
	 * publicity pertaining to the software without specific, written prior
	 * permission. Title to copyright in this software and any associated
	 * documentation will at all times remain with copyright holders.
	 * ------------
	 * ----------------------------------------------------------------
	 */

	public static int[] calculateTriangularArrowhead(int x1, int y1, int x2, int y2, int thick) {
		int[] p = new int[6];
		float x, y, xb, yb, dx, dy, l, sin, cos;
		float width, height;
		int xc, yc, xd, yd;

		width = 5 + thick;
		height = 10;
		dx = x2 - x1;
		dy = y1 - y2;
		l = (float) Math.sqrt(dx * dx + dy * dy);
		if (l == 0) {
			return null;
		}
		sin = dy / l;
		cos = dx / l;
		xb = x2 * cos - y2 * sin;
		yb = x2 * sin + y2 * cos;
		x = xb - height;
		y = yb - width / 2;
		xc = BNAUtils.round((float) (x * cos + y * sin + .5));
		yc = BNAUtils.round((float) (-x * sin + y * cos + .5));
		y = yb + width / 2;
		xd = BNAUtils.round((float) (x * cos + y * sin + .5));
		yd = BNAUtils.round((float) (-x * sin + y * cos + .5));

		p[0] = xc;
		p[1] = yc;
		p[2] = x2;
		p[3] = y2;
		p[4] = xd;
		p[5] = yd;
		return p;
	}

}
