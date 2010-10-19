package org.archstudio.bna.things.shapes;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Pattern;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.*;

public class BoxThingPeer extends AbstractThingPeer {

	protected BoxThing lt;

	protected Rectangle localBoundingBox = new Rectangle(0, 0, 0, 0);

	public BoxThingPeer(IThing t) {
		super(t);
		if (!(t instanceof BoxThing)) {
			throw new IllegalArgumentException("BoxThingPeer can only peer for BoxThing");
		}
		this.lt = (BoxThing) t;
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
			fg = g.getDevice().getSystemColor(SWT.COLOR_GRAY);
		}

		boolean isGradientFilled = lt.isGradientFilled();

		if (isGradientFilled) {
			BNAComposite c = (BNAComposite) BNAUtils.getParentComposite(view);
			if (c != null) {
				if (!BNARenderingSettings.getDecorativeGraphics(c)) {
					isGradientFilled = false;
				}
			}
		}

		Color bg = null;
		if (isGradientFilled) {
			bg = ResourceUtils.getColor(getDisplay(), lt.getSecondaryColor());
			if (bg == null) {
				bg = g.getDevice().getSystemColor(SWT.COLOR_DARK_GRAY);
			}
		}

		int cornerWidth = lt.getCornerWidth();
		int cornerHeight = lt.getCornerHeight();

		boolean hasRoundedCorners = ((cornerWidth + cornerHeight) > 0);

		if (!isGradientFilled) {
			g.setBackground(fg);
			if (!hasRoundedCorners) {
				g.fillRectangle(localBoundingBox);
			}
			else {
				g.fillRoundRectangle(localBoundingBox.x, localBoundingBox.y, localBoundingBox.width, localBoundingBox.height, cornerWidth, cornerHeight);
			}
		}
		else {
			g.setForeground(fg);
			g.setBackground(bg);
			if (!hasRoundedCorners) {
				g.fillGradientRectangle(localBoundingBox.x, localBoundingBox.y, localBoundingBox.width, localBoundingBox.height, true);
			}
			else {
				Pattern pattern = new Pattern(g.getDevice(), localBoundingBox.x, localBoundingBox.y, localBoundingBox.x, localBoundingBox.y
				        + localBoundingBox.height, fg, bg);
				g.setBackgroundPattern(pattern);
				g.fillRoundRectangle(localBoundingBox.x, localBoundingBox.y, localBoundingBox.width, localBoundingBox.height, cornerWidth, cornerHeight);
				g.setForegroundPattern(null);
				pattern.dispose();
			}
		}
	}

	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return BNAUtils.isWithin(lt.getBoundingBox(), worldX, worldY);
	}

}
