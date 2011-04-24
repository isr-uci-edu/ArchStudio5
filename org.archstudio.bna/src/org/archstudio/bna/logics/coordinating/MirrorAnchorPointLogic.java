package org.archstudio.bna.logics.coordinating;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IMirrorsAnchorPoint;
import org.archstudio.bna.logics.tracking.AnchorPointChangedEvent;
import org.archstudio.bna.logics.tracking.AnchorPointTrackingLogic;
import org.archstudio.bna.logics.tracking.IAnchorPointTrackingListener;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;

public class MirrorAnchorPointLogic extends AbstractThingLogic implements IAnchorPointTrackingListener, IBNAModelListener {

	protected ThingRefTrackingLogic trtl = null;
	protected AnchorPointTrackingLogic aptl = null;

	public MirrorAnchorPointLogic(ThingRefTrackingLogic trtl, AnchorPointTrackingLogic aptl) {
		this.trtl = trtl;
		this.aptl = aptl;
	}

	public void init() {
		aptl.addTrackingListener(this);
	}

	public void destroy() {
		aptl.removeTrackingListener(this);
	}

	protected void checkAndMirror(IMirrorsAnchorPoint targetThing) {
		String apmID = targetThing.getAnchorPointMasterThingID();
		if (apmID != null) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				IThing apm = m.getThing(apmID);
				if (apm != null) {
					if (apm instanceof IHasAnchorPoint) {
						Point ap = ((IHasAnchorPoint) apm).getAnchorPoint();
						Point nap = new Point(ap.x, ap.y);
						Point offsets = targetThing.getAnchorPointMirrorOffsets();
						if (offsets != null) {
							nap.x += offsets.x;
							nap.y += offsets.y;
						}
						targetThing.setAnchorPoint(nap);
					}
				}
			}
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_ADDED) {
			IThing targetThing = evt.getTargetThing();
			if (targetThing instanceof IMirrorsAnchorPoint) {
				checkAndMirror((IMirrorsAnchorPoint) evt.getTargetThing());
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			IThing targetThing = evt.getTargetThing();
			ThingEvent tevt = evt.getThingEvent();
			if (targetThing instanceof IMirrorsAnchorPoint) {
				if ((tevt.getPropertyName() != null) && (tevt.getPropertyName().equals(IMirrorsAnchorPoint.ANCHOR_POINT_MASTER_THING_ID_PROPERTY_NAME))) {
					checkAndMirror((IMirrorsAnchorPoint) targetThing);
				}
			}
		}
	}

	public void anchorPointChanged(AnchorPointChangedEvent evt) {
		IThing targetThing = evt.getTargetThing();
		IBNAModel m = getBNAModel();
		if (m != null) {
			String[] thingIDs = trtl.findReferencingThings(targetThing, IMirrorsAnchorPoint.ANCHOR_POINT_MASTER_THING_ID_PROPERTY_NAME);
			for (int i = 0; i < thingIDs.length; i++) {
				IThing thing = m.getThing(thingIDs[i]);
				if (thing instanceof IMirrorsAnchorPoint) {
					checkAndMirror((IMirrorsAnchorPoint) thing);
				}
			}
		}
	}
}
