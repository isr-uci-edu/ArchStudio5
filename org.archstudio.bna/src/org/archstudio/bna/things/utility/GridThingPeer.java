package org.archstudio.bna.things.utility;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.*;
import org.archstudio.bna.constants.GridDisplayType;

public class GridThingPeer extends AbstractThingPeer {

	protected GridThing lt;

	public GridThingPeer(IThing t) {
		super(t);
		if (!(t instanceof GridThing)) {
			throw new IllegalArgumentException("GridThingPeer can only peer for GridThing");
		}
		this.lt = (GridThing) t;
	}

	public void draw(IBNAView view, GC g) {
		int gridSpacing = lt.getGridSpacing();
		if (gridSpacing == 0) {
			return;
		}

		GridDisplayType gdt = lt.getGridDisplayType();
		if ((gdt == null) || (gdt.equals(GridDisplayType.NONE))) {
			return;
		}

		Color fg = ResourceUtils.getColor(getDisplay(), lt.getColor());
		if (fg == null) {
			fg = ResourceUtils.getColor(getDisplay(), new RGB(192, 192, 192));
		}

		g.setForeground(fg);

		drawGrid(view, g, gridSpacing, gdt);
	}

	protected void drawGrid(IBNAView view, GC g, int gridSpacing, GridDisplayType gdt) {
		Rectangle clip = g.getClipping();

		ICoordinateMapper cm = view.getCoordinateMapper();
		int wx = cm.localXtoWorldX(clip.x);
		int wy = cm.localYtoWorldY(clip.y);
		int wx2 = cm.localXtoWorldX(clip.x + clip.width);
		int wy2 = cm.localYtoWorldY(clip.y + clip.height);

		int dx = wx % gridSpacing;
		int dy = wy % gridSpacing;

		int jump = 1;
		int localGridSpacing = BNAUtils.round(((double) gridSpacing) * cm.getScale());
		if (localGridSpacing < 1)
			localGridSpacing = 1;
		while ((localGridSpacing * jump) < 10) {
			jump++;
		}

		if ((gdt == GridDisplayType.SOLID_LINES) || (gdt == GridDisplayType.DOTTED_LINES)) {
			if (gdt == GridDisplayType.DOTTED_LINES) {
				g.setLineWidth(1);
				g.setLineStyle(SWT.LINE_DOT);
			}
			else {
				g.setLineWidth(1);
				g.setLineStyle(SWT.LINE_SOLID);
			}
			for (int i = wx - dx; i <= wx2; i += (gridSpacing * jump)) {
				int gx = cm.worldXtoLocalX(i);
				g.drawLine(gx, clip.y, gx, clip.y + clip.height);
			}
			for (int i = wy - dy; i <= wy2; i += (gridSpacing * jump)) {
				int gy = cm.worldYtoLocalY(i);
				g.drawLine(clip.x, gy, clip.x + clip.width, gy);
			}
		}
		else if (gdt == GridDisplayType.DOTS_AT_CORNERS) {
			g.setLineWidth(1);
			for (int i = wx - dx; i <= wx2; i += (gridSpacing * jump)) {
				int gx = cm.worldXtoLocalX(i);
				for (int j = wy - dy; j <= wy2; j += (gridSpacing * jump)) {
					int gy = cm.worldYtoLocalY(j);
					g.drawPoint(gx, gy);
				}
			}
		}
		else if (gdt == GridDisplayType.CROSSES_AT_CORNERS) {
			g.setLineWidth(1);
			for (int i = wx - dx; i <= wx2; i += (gridSpacing * jump)) {
				int gx = cm.worldXtoLocalX(i);
				for (int j = wy - dy; j <= wy2; j += (gridSpacing * jump)) {
					int gy = cm.worldYtoLocalY(j);
					g.drawLine(gx - 3, gy, gx + 3, gy);
					g.drawLine(gx, gy - 3, gx, gy + 3);
				}
			}
		}
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}

}
