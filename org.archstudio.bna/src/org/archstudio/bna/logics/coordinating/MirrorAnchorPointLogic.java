package org.archstudio.bna.logics.coordinating;

import java.util.concurrent.locks.Lock;

import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.logics.tracking.ReferenceTrackingLogic;
import org.eclipse.draw2d.geometry.Point;

public class MirrorAnchorPointLogic extends
		AbstractMirrorValueLogic MaintainReferencedThingsLogic<IHasAnchorPoint, IHasMutableAnchorPoint> {

	private static final String ANCHOR_POINT_MASTER_THING_ID_KEY = "&anchorPointMaster";
	private static final String ANCHOR_POINT_MIRROR_OFFSETS_PROPETY_NAME = "anchorPointMirrorOffsets";

	public static final void mirrorAnchorPoint(IHasAnchorPoint sourceThing, Point offset,
			IHasMutableAnchorPoint... targetThings) {
		for (IThing targetThing : targetThings) {
			targetThing.setProperty(ANCHOR_POINT_MIRROR_OFFSETS_PROPETY_NAME, offset);
			targetThing.setProperty(ANCHOR_POINT_MASTER_THING_ID_KEY, sourceThing.getID());
		}
	}

	public MirrorAnchorPointLogic(ReferenceTrackingLogic rtl) {
		super(IHasAnchorPoint.class, new String[] { IHasAnchorPoint.ANCHOR_POINT_KEY }, IHasMutableAnchorPoint.class,
				new String[] { IHasAnchorPoint.ANCHOR_POINT_KEY, ANCHOR_POINT_MIRROR_OFFSETS_PROPETY_NAME }, rtl,
				ANCHOR_POINT_MASTER_THING_ID_KEY);
	}

	@Override
	protected void maintain(IBNAModel sourceModel, IHasAnchorPoint sourceThing, IHasMutableAnchorPoint targetThing,
			ThingEvent thingEvent) {
		Lock lock = targetThing.getPropertyLock();
		lock.lock();
		try {
			Point ap = sourceThing.getAnchorPoint();
			Point apo = targetThing.get(ANCHOR_POINT_MIRROR_OFFSETS_PROPETY_NAME);
			Point nap = new Point(ap.x, ap.y);
			if (apo != null) {
				nap.x += apo.x;
				nap.y += apo.y;
			}
			targetThing.setAnchorPoint(nap);
		}
		finally {
			lock.unlock();
		}
	}
}
