package org.archstudio.bna;

import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.widgets.Display;

public abstract class AbstractThingPeer implements IThingPeer {
	protected IThing lt;

	public AbstractThingPeer(IThing thing) {
		this.lt = thing;
	}

	public abstract void draw(IBNAView view, GC g);

	public abstract boolean isInThing(IBNAView view, int worldX, int worldY);

	//public abstract Rectangle getLocalBoundingBox(IBNAContext context, GC g, ICoordinateMapper cm);

	/*
	 * protected BNAComposite getBNAComposite(IBNAContext context){ return
	 * BNAUtils.getBNAComposite(context); }
	 */

	protected Display getDisplay() {
		Display d = Display.getCurrent();
		if (d != null)
			return d;
		return Display.getDefault();
	}
}
