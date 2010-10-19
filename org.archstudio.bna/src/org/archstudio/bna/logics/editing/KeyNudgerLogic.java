package org.archstudio.bna.logics.editing;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.GridUtils;
import org.archstudio.bna.IBNAKeyListener;
import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IDragMovable;
import org.archstudio.swtutils.constants.Orientation;

public class KeyNudgerLogic extends AbstractThingLogic implements IBNAKeyListener {

	public void keyPressed(IBNAView view, KeyEvent e) {
	}

	public void keyReleased(IBNAView view, KeyEvent e) {
		if ((e.keyCode == SWT.ARROW_LEFT) || (e.keyCode == SWT.ARROW_UP) || (e.keyCode == SWT.ARROW_DOWN) || (e.keyCode == SWT.ARROW_RIGHT)) {
			List<IThing> selectedThings = BNAUtils.getSelectedThings(view.getWorld().getBNAModel());
			Orientation o = orientationForKeyCode(e.keyCode);
			int gridSpacing = GridUtils.getGridSpacing(view.getWorld().getBNAModel());
			int distance = (gridSpacing == 0) ? 5 : gridSpacing;

			if (selectedThings.size() > 0) {
				for (IThing t : selectedThings) {
					if (t instanceof IDragMovable) {
						nudge(o, distance, (IDragMovable) t);
						if (gridSpacing != 0) {
							GridUtils.rectifyToGrid(gridSpacing, (IDragMovable) t);
						}
					}
				}
			}
		}
	}

	private Orientation orientationForKeyCode(int keyCode) {
		switch (keyCode) {
		case SWT.ARROW_LEFT:
			return Orientation.WEST;
		case SWT.ARROW_UP:
			return Orientation.NORTH;
		case SWT.ARROW_RIGHT:
			return Orientation.EAST;
		case SWT.ARROW_DOWN:
			return Orientation.SOUTH;
		default:
			throw new IllegalArgumentException("Invalid key code");
		}
	}

	private Point nudge(Orientation o, int distance, Point p) {
		Point p2 = new Point(p.x, p.y);
		switch (o) {
		case NORTH:
			p2.y -= distance;
			break;
		case EAST:
			p2.x += distance;
			break;
		case SOUTH:
			p2.y += distance;
			break;
		case WEST:
			p2.x -= distance;
			break;
		}
		return p2;
	}

	protected void nudge(Orientation o, int distance, IDragMovable t) {
		Point p = t.getReferencePoint();
		t.setReferencePoint(nudge(o, distance, p));
	}

}
