package org.archstudio.bna.logics.tracking;

import org.archstudio.bna.IBNAView;
import org.archstudio.bna.IThing;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.utils.IBNAMouseMoveListener;
import org.eclipse.swt.events.MouseEvent;

public class MouseLocationTrackingLogic extends AbstractThingLogic implements IBNAMouseMoveListener {

	protected int mlx, mly = Integer.MIN_VALUE;
	protected int mwx, mwy = Integer.MIN_VALUE;

	public void mouseMove(IBNAView view, MouseEvent e, IThing t, int worldX, int worldY) {
		this.mlx = e.x;
		this.mly = e.y;
		this.mwx = worldX;
		this.mwy = worldY;
	}

	public int getMouseLocalX() {
		return mlx;
	}

	public int getMouseLocalY() {
		return mly;
	}

	public int getMouseWorldX() {
		return mwx;
	}

	public int getMouseWorldY() {
		return mwy;
	}

}
