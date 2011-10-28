package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IIsSticky;
import org.eclipse.draw2d.geometry.Point;

public class StickPointLogic extends AbstractStickPointLogic implements IBNASynchronousModelListener {

	public StickPointLogic() {
	}

	public void stick(IThing pointThing, IThingKey<Point> forPointKey, StickyMode stickyMode, IIsSticky stickyThing) {
		setSetting(pointThing, forPointKey, STICKY_MODE_KEY, stickyMode);
		setThingRef(pointThing, forPointKey, stickyThing.getID());
	}

}
