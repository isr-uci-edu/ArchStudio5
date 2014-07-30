package org.archstudio.bna.logics.editing;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.constants.KeyType;
import org.archstudio.bna.facets.IHasMutableReferencePoint;
import org.archstudio.bna.facets.IHasReferencePoint;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.ui.IBNAKeyListener;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.bna.utils.GridUtils;
import org.archstudio.bna.utils.UserEditableUtils;
import org.archstudio.swtutils.constants.Orientation;
import org.archstudio.sysutils.Finally;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Point;

public class KeyNudgerLogic extends AbstractThingLogic implements IBNAKeyListener {

	public KeyNudgerLogic(IBNAWorld world) {
		super(world);
	}

	@Override
	public void keyPressed(IBNAView view, KeyType type, KeyEvent e) {
		BNAUtils.checkLock();

		if (e.keyCode == SWT.ARROW_LEFT || e.keyCode == SWT.ARROW_UP || e.keyCode == SWT.ARROW_DOWN
				|| e.keyCode == SWT.ARROW_RIGHT) {
			try (Finally bulkChange = model.beginBulkChange()) {
				Orientation o = orientationForKeyCode(e.keyCode);
				int gridSpacing = GridUtils.getGridSpacing(world);
				int distance = gridSpacing == 0 ? 5 : gridSpacing;

				boolean nudged = false;
				Runnable undoRunnable = BNAOperations.takeSnapshotOfLocations(model, BNAUtils.getSelectedThings(model));
				for (IThing t : BNAUtils.getSelectedThings(model)) {
					if (t instanceof IHasReferencePoint) {
						nudged = true;
						nudge(o, distance, (IHasReferencePoint) t);
						//if(gridSpacing != 0){
						//	GridUtils.rectifyToGrid(gridSpacing, (IRelativeMovable)t);
						//}
					}
				}
				if (nudged) {
					Runnable redoRunnable = BNAOperations.takeSnapshotOfLocations(model,
							BNAUtils.getSelectedThings(model));
					BNAOperations.runnable("Nudge", undoRunnable, redoRunnable, false);
				}
			}
		}
	}

	@Override
	public void keyReleased(IBNAView view, KeyType type, KeyEvent e) {
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
		default:
			throw new IllegalArgumentException();
		}
		return p2;
	}

	protected void nudge(Orientation o, int distance, IHasReferencePoint t) {
		if (UserEditableUtils.isEditableForAllQualities(t, IHasMutableReferencePoint.USER_MAY_MOVE)) {
			t.setReferencePoint(nudge(o, distance, t.getReferencePoint()));
		}
	}
}
