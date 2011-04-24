package org.archstudio.bna.logics.coordinating;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IIndicator;
import org.archstudio.bna.logics.tracking.AnchorPointChangedEvent;
import org.archstudio.bna.logics.tracking.AnchorPointTrackingLogic;
import org.archstudio.bna.logics.tracking.BoundingBoxChangedEvent;
import org.archstudio.bna.logics.tracking.BoundingBoxTrackingLogic;
import org.archstudio.bna.logics.tracking.IAnchorPointTrackingListener;
import org.archstudio.bna.logics.tracking.IBoundingBoxTrackingListener;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;

public class MaintainIndicatorLogic extends AbstractThingLogic implements IAnchorPointTrackingListener, IBoundingBoxTrackingListener, IBNAModelListener {

	protected ThingRefTrackingLogic trtl = null;
	protected AnchorPointTrackingLogic aptl = null;
	protected BoundingBoxTrackingLogic bbtl = null;

	public MaintainIndicatorLogic(ThingRefTrackingLogic trtl, BoundingBoxTrackingLogic bbtl, AnchorPointTrackingLogic aptl) {
		this.trtl = trtl;
		this.aptl = aptl;
		this.bbtl = bbtl;
	}

	public void init() {
		aptl.addTrackingListener(this);
		bbtl.addTrackingListener(this);
	}

	public void destroy() {
		bbtl.removeTrackingListener(this);
		aptl.removeTrackingListener(this);
	}

	protected void check(IIndicator indicatorThing) {
		String ttID = indicatorThing.getTargetThingID();
		if (ttID != null) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				IThing tt = m.getThing(ttID);
				if (tt != null) {
					if (tt instanceof IHasAnchorPoint) {
						Point ap = ((IHasAnchorPoint) tt).getAnchorPoint();
						indicatorThing.setIndicatorPoint(ap);
					}
					else if (tt instanceof IHasBoundingBox) {
						Point ap = BNAUtils.getCentralPoint(tt);
						if (ap != null) {
							indicatorThing.setIndicatorPoint(ap);
						}
					}
				}
			}
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_ADDED) {
			IThing targetThing = evt.getTargetThing();
			if (targetThing instanceof IIndicator) {
				check((IIndicator) evt.getTargetThing());
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			IThing targetThing = evt.getTargetThing();
			ThingEvent tevt = evt.getThingEvent();
			if (targetThing instanceof IIndicator) {
				if ((tevt.getPropertyName() != null) && (tevt.getPropertyName().equals(IIndicator.TARGET_THING_ID_PROPERTY_NAME))) {
					check((IIndicator) targetThing);
				}
			}
		}
	}

	public void anchorPointChanged(AnchorPointChangedEvent evt) {
		IThing targetThing = evt.getTargetThing();
		IBNAModel m = getBNAModel();
		if (m != null) {
			String[] thingIDs = trtl.findReferencingThings(targetThing, IIndicator.TARGET_THING_ID_PROPERTY_NAME);
			for (int i = 0; i < thingIDs.length; i++) {
				IThing thing = m.getThing(thingIDs[i]);
				if (thing instanceof IIndicator) {
					check((IIndicator) thing);
				}
			}
		}
	}

	public void boundingBoxChanged(BoundingBoxChangedEvent evt) {
		IThing targetThing = evt.getTargetThing();
		IBNAModel m = getBNAModel();
		if (m != null) {
			String[] thingIDs = trtl.findReferencingThings(targetThing, IIndicator.TARGET_THING_ID_PROPERTY_NAME);
			for (int i = 0; i < thingIDs.length; i++) {
				IThing thing = m.getThing(thingIDs[i]);
				if (thing instanceof IIndicator) {
					check((IIndicator) thing);
				}
			}
		}
	}

}
