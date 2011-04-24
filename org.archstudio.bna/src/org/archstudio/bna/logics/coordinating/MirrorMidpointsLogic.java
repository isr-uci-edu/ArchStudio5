package org.archstudio.bna.logics.coordinating;

import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasMidpoints;
import org.archstudio.bna.facets.IMirrorsMidpoints;
import org.archstudio.bna.logics.tracking.IMidpointsTrackingListener;
import org.archstudio.bna.logics.tracking.MidpointsChangedEvent;
import org.archstudio.bna.logics.tracking.MidpointsTrackingLogic;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;

public class MirrorMidpointsLogic extends AbstractThingLogic implements IMidpointsTrackingListener, IBNAModelListener {

	protected ThingRefTrackingLogic trtl = null;
	protected MidpointsTrackingLogic mpstl = null;

	public MirrorMidpointsLogic(ThingRefTrackingLogic trtl, MidpointsTrackingLogic mpstl) {
		this.trtl = trtl;
		this.mpstl = mpstl;
	}

	public void init() {
		mpstl.addTrackingListener(this);
	}

	public void destroy() {
		mpstl.removeTrackingListener(this);
	}

	protected void checkAndMirror(IMirrorsMidpoints targetThing) {
		String apmID = targetThing.getMidpointsMasterThingID();
		if (apmID != null) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				IThing apm = m.getThing(apmID);
				if (apm != null) {
					if (apm instanceof IHasMidpoints) {
						Point[] midpoints = ((IHasMidpoints) apm).getMidpoints();
						targetThing.setMidpoints(midpoints);
					}
				}
			}
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_ADDED) {
			IThing targetThing = evt.getTargetThing();
			if (targetThing instanceof IMirrorsMidpoints) {
				checkAndMirror((IMirrorsMidpoints) evt.getTargetThing());
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			IThing targetThing = evt.getTargetThing();
			ThingEvent tevt = evt.getThingEvent();
			if (targetThing instanceof IMirrorsMidpoints) {
				if ((tevt.getPropertyName() != null) && (tevt.getPropertyName().equals(IMirrorsMidpoints.MIDPOINTS_MASTER_THING_ID_PROPERTY_NAME))) {
					checkAndMirror((IMirrorsMidpoints) targetThing);
				}
			}
		}
	}

	public void midpointsChanged(MidpointsChangedEvent evt) {
		IThing targetThing = evt.getTargetThing();
		IBNAModel m = getBNAModel();
		if (m != null) {
			String[] thingIDs = trtl.findReferencingThings(targetThing, IMirrorsMidpoints.MIDPOINTS_MASTER_THING_ID_PROPERTY_NAME);
			for (int i = 0; i < thingIDs.length; i++) {
				IThing thing = m.getThing(thingIDs[i]);
				if (thing instanceof IMirrorsMidpoints) {
					checkAndMirror((IMirrorsMidpoints) thing);
				}
			}
		}
	}
}
