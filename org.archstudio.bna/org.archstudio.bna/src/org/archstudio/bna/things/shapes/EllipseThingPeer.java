package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAComposite;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Pattern;

public class EllipseThingPeer<T extends EllipseThing> extends AbstractThingPeer<T> {

	public EllipseThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res) {
		Rectangle bb = t.getBoundingBox();
		Rectangle lbb = BNAUtils.worldToLocal(view.getCoordinateMapper(), bb);
		if (!clip.intersects(lbb)) {
			return;
		}

		Color fg = res.getColor(t.getColor(), SWT.COLOR_GRAY);

		boolean isGradientFilled = t.isGradientFilled();
		if (isGradientFilled) {
			BNAComposite c = (BNAComposite) BNAUtils.getParentComposite(view);
			if (c != null) {
				if (!BNARenderingSettings.getDecorativeGraphics(c)) {
					isGradientFilled = false;
				}
			}
		}

		if (!isGradientFilled) {
			g.setBackgroundColor(fg);
			g.fillOval(lbb.x, lbb.y, lbb.width, lbb.height);
		}
		else {
			Color bg = res.getColor(t.getSecondaryColor(), SWT.COLOR_DARK_GRAY);
			g.setForegroundColor(fg);
			g.setBackgroundColor(bg);
			Pattern pattern = new Pattern(res.getDevice(), lbb.x, lbb.y, lbb.x, lbb.y + lbb.height,
					g.getForegroundColor(), g.getBackgroundColor());
			g.setBackgroundPattern(pattern);
			g.fillOval(lbb.x, lbb.y, lbb.width, lbb.height);
			pattern.dispose();
		}
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return BNAUtils.isWithin(t.getBoundingBox(), worldX, worldY);
	}
}
