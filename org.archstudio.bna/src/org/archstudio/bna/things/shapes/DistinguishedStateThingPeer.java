package org.archstudio.bna.things.shapes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.*;

public class DistinguishedStateThingPeer extends AbstractThingPeer {

	protected DistinguishedStateThing lt;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public DistinguishedStateThingPeer(IThing t) {
		super(t);
		if (!(t instanceof DistinguishedStateThing)) {
			throw new IllegalArgumentException("DistinguishedStateThingPeer can only peer for DistinguishedStateThing");
		}
		this.lt = (DistinguishedStateThing) t;
	}

	protected void updateLocalBoundingBox(ICoordinateMapper cm) {
		localBoundingBox = BNAUtils.worldToLocal(cm, BNAUtils.normalizeRectangle(lt.getBoundingBox()));
	}

	public void draw(IBNAView view, GC g) {
		updateLocalBoundingBox(view.getCoordinateMapper());

		if (!g.getClipping().intersects(localBoundingBox))
			return;
		Color fg = ResourceUtils.getColor(getDisplay(), lt.getColor());
		if (fg == null) {
			fg = g.getDevice().getSystemColor(SWT.COLOR_BLACK);
		}

		g.setForeground(fg);
		g.setBackground(fg);

		if (localBoundingBox.width > 6) {
			boolean isFinalState = lt.isFinalState();

			if (isFinalState) {
				g.drawOval(localBoundingBox.x, localBoundingBox.y, localBoundingBox.width, localBoundingBox.height);
			}
			g.fillOval(localBoundingBox.x + 3, localBoundingBox.y + 3, localBoundingBox.width - 5, localBoundingBox.height - 5);
		}
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return BNAUtils.isWithin(lt.getBoundingBox(), worldX, worldY);
	}

}
