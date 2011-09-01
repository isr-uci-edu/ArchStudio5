package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.constants.StickyMode;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.facets.IIsSticky;
import org.eclipse.draw2d.geometry.Point;

public class StickInternalPointLogic extends AbstractStickPointLogic implements IInternalBNAModelListener {

	public StickInternalPointLogic() {
	}

	public void stick(IThing pointThing, IThingKey<Point> forPointKey, StickyMode stickyMode, IHasWorld worldThing,
			IIsSticky worldStickyThing) {
		setSetting(pointThing, forPointKey, STICKY_MODE_KEY, stickyMode);
		setSetting(pointThing, forPointKey, getWorldThingRefKey(forPointKey), worldThing.getID());
		setThingRef(pointThing, forPointKey, worldStickyThing.getID());
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void internalBNAModelChanged(IHasWorld src,
			BNAModelEvent<ET, EK, EV> evt) {
	}

	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void internalBNAModelChangedSync(IHasWorld src,
			BNAModelEvent<ET, EK, EV> evt) {
		bnaModelChangedSync(evt);
	}

	@Override
	protected Point getNearPoint(IThing pointThing, IThingKey<Point> pointKey, StickyMode stickyMode) {
		Point p = super.getNearPoint(pointThing, pointKey, stickyMode);
		// TODO: translate p to the internal coordinate space
		return p;
	}
	
}
