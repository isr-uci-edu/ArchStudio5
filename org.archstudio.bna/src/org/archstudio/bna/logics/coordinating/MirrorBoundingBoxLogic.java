package org.archstudio.bna.logics.coordinating;

import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IMirrorsBoundingBox;
import org.archstudio.bna.logics.tracking.BoundingBoxChangedEvent;
import org.archstudio.bna.logics.tracking.BoundingBoxTrackingLogic;
import org.archstudio.bna.logics.tracking.IBoundingBoxTrackingListener;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;

public class MirrorBoundingBoxLogic extends AbstractThingLogic implements IBoundingBoxTrackingListener, IBNAModelListener {

	protected ThingRefTrackingLogic trtl = null;
	protected BoundingBoxTrackingLogic bbtl = null;

	public MirrorBoundingBoxLogic(ThingRefTrackingLogic trtl, BoundingBoxTrackingLogic bbtl) {
		this.trtl = trtl;
		this.bbtl = bbtl;
	}

	public synchronized void init() {
		bbtl.addTrackingListener(this);
		IBNAModel m = getBNAModel();
		if (m != null) {
			for (IThing t : m.getAllThings()) {
				if (t instanceof IMirrorsBoundingBox) {
					checkAndMirror((IMirrorsBoundingBox) t);
				}
			}
		}
	}

	public void destroy() {
		bbtl.removeTrackingListener(this);
	}

	protected void checkAndMirror(IMirrorsBoundingBox targetThing) {
		String bbmID = targetThing.getBoundingBoxMasterThingID();
		if (bbmID != null) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				IThing bbm = m.getThing(bbmID);
				if (bbm != null) {
					if (bbm instanceof IHasBoundingBox) {
						Rectangle bb = ((IHasBoundingBox) bbm).getBoundingBox();
						Rectangle nbb = new Rectangle(bb.x, bb.y, bb.width, bb.height);
						Rectangle offsets = targetThing.getBoundingBoxMirrorOffsets();
						if (offsets != null) {
							nbb.x += offsets.x;
							nbb.y += offsets.y;
							nbb.width += offsets.width;
							if (nbb.width < 0)
								nbb.width = 0;
							nbb.height += offsets.height;
							if (nbb.height < 0)
								nbb.height = 0;
						}
						targetThing.setBoundingBox(nbb);
					}
				}
			}
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_ADDED) {
			IThing targetThing = evt.getTargetThing();
			if (targetThing instanceof IMirrorsBoundingBox) {
				checkAndMirror((IMirrorsBoundingBox) evt.getTargetThing());
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			IThing targetThing = evt.getTargetThing();
			ThingEvent tevt = evt.getThingEvent();
			if (targetThing instanceof IMirrorsBoundingBox) {
				if ((tevt.getPropertyName() != null) && (tevt.getPropertyName().equals(IMirrorsBoundingBox.BOUNDING_BOX_MASTER_THING_ID_PROPERTY_NAME))) {
					checkAndMirror((IMirrorsBoundingBox) targetThing);
				}
			}
		}
	}

	public void boundingBoxChanged(BoundingBoxChangedEvent evt) {
		IThing targetThing = evt.getTargetThing();
		IBNAModel m = getBNAModel();
		if (m != null) {
			String[] thingIDs = trtl.findReferencingThings(targetThing, IMirrorsBoundingBox.BOUNDING_BOX_MASTER_THING_ID_PROPERTY_NAME);
			for (int i = 0; i < thingIDs.length; i++) {
				IThing thing = m.getThing(thingIDs[i]);
				if (thing instanceof IMirrorsBoundingBox) {
					checkAndMirror((IMirrorsBoundingBox) thing);
				}
			}
		}
	}
}
