package org.archstudio.bna.logics.coordinating;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasBoundingBoxRail;
import org.archstudio.bna.facets.IHasMutableAnchorPoint;
import org.archstudio.bna.logics.tracking.AnchorPointChangedEvent;
import org.archstudio.bna.logics.tracking.AnchorPointTrackingLogic;
import org.archstudio.bna.logics.tracking.BoundingBoxChangedEvent;
import org.archstudio.bna.logics.tracking.BoundingBoxTrackingLogic;
import org.archstudio.bna.logics.tracking.IAnchorPointTrackingListener;
import org.archstudio.bna.logics.tracking.IBoundingBoxTrackingListener;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;

public class BoundingBoxRailLogic extends AbstractThingLogic implements IBoundingBoxTrackingListener, IAnchorPointTrackingListener, IBNAModelListener {

	protected ThingRefTrackingLogic trtl = null;
	protected BoundingBoxTrackingLogic bbtl = null;
	protected AnchorPointTrackingLogic aptl = null;

	public BoundingBoxRailLogic(ThingRefTrackingLogic trtl, BoundingBoxTrackingLogic bbtl, AnchorPointTrackingLogic aptl) {
		this.trtl = trtl;
		this.bbtl = bbtl;
		this.aptl = aptl;
	}

	public void init() {
		bbtl.addTrackingListener(this);
		aptl.addTrackingListener(this);
	}

	public void destroy() {
		aptl.removeTrackingListener(this);
		bbtl.removeTrackingListener(this);
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED:
			checkAnchorPoint(evt.getTargetThing());
			break;
		}
	}

	protected void checkAnchorPoint(IThing apt) {
		IBNAModel m = getBNAModel();
		if (m == null)
			return;
		if (apt instanceof IHasBoundingBoxRail) {
			String bbtID = ((IHasBoundingBoxRail) apt).getBoundingBoxRailMasterThingID();
			if (bbtID != null) {
				IThing bbt = m.getThing(bbtID);
				if ((bbt != null) && (bbt instanceof IHasBoundingBox)) {
					Rectangle bb = ((IHasBoundingBox) bbt).getBoundingBox();
					//Point p = evt.getNewAnchorPoint();
					Point p = ((IHasAnchorPoint) apt).getAnchorPoint();
					if (BNAUtils.isEdgePoint(p, bb))
						return;
					Point np = BNAUtils.findClosestEdgePoint(p, bb);
					((IHasMutableAnchorPoint) apt).setAnchorPoint(np);
				}
			}
		}
	}

	public void anchorPointChanged(AnchorPointChangedEvent evt) {
		if (BNAUtils.nulleq(evt.getOldAnchorPoint(), evt.getNewAnchorPoint()))
			return;
		IThing apt = evt.getTargetThing();
		checkAnchorPoint(apt);
	}

	public void boundingBoxChanged(BoundingBoxChangedEvent evt) {
		IBNAModel m = getBNAModel();
		if (m == null)
			return;
		IThing bbt = evt.getTargetThing();
		String[] endpointThingIDs = trtl.findReferencingThings(bbt, IHasBoundingBoxRail.BOUNDING_BOX_RAIL_MASTER_THING_ID_PROPERTY_NAME);
		for (String endpointThingID : endpointThingIDs) {
			IThing endpointThing = m.getThing(endpointThingID);
			if ((endpointThing != null) && (endpointThing instanceof IHasMutableAnchorPoint)) {
				Point oldPoint = ((IHasAnchorPoint) endpointThing).getAnchorPoint();
				Point newPoint = BNAUtils.scaleAndMoveBorderPoint(oldPoint, evt.getOldBoundingBox(), evt.getNewBoundingBox());
				((IHasMutableAnchorPoint) endpointThing).setAnchorPoint(newPoint);
			}
		}
	}

}
