package org.archstudio.bna.things.shapes;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.things.AbstractThingPeer;
import org.archstudio.bna.utils.BNAComposite;
import org.archstudio.bna.utils.BNARenderingSettings;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.PathDataUtils;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Path;
import org.eclipse.swt.graphics.PathData;
import org.eclipse.swt.graphics.Pattern;

public class PathThingPeer<T extends PathThing> extends AbstractThingPeer<T> {

	public PathThingPeer(T thing) {
		super(thing);
	}

	@Override
	public void draw(IBNAView view, Graphics g, Rectangle clip, ResourceUtils res) {
		Rectangle bb = t.getBoundingBox();
		Rectangle lbb = BNAUtils.worldToLocal(view.getCoordinateMapper(), bb);
		if (!clip.intersects(lbb)) {
			return;
		}

		Path path = null;
		Pattern pattern = null;
		try {
			Color fg = res.getColor(t.getColor(), SWT.COLOR_GRAY);

			path = new Path(res.getDevice());
			PathData pathData = t.getPathData();
			Point o = t.getAnchorPoint();
			PathDataUtils.addPathDataToPath(path, pathData, o, view.getCoordinateMapper());

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
				g.fillPath(path);
			}
			else {
				Color bg = res.getColor(t.getSecondaryColor(), SWT.COLOR_DARK_GRAY);

				g.setForegroundColor(fg);
				g.setBackgroundColor(bg);

				g.setClip(path);
				g.fillGradient(lbb.x, lbb.y, lbb.width, lbb.height, true);
			}
		}
		finally {
			if (path != null) {
				path.dispose();
				path = null;
			}

			if (pattern != null) {
				pattern.dispose();
				pattern = null;
			}
		}
	}

	@Override
	public boolean isInThing(IBNAView view, int worldX, int worldY) {
		return false;
	}
}
