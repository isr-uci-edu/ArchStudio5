package org.archstudio.bna.logics.coordinating;

import org.eclipse.swt.graphics.PathData;
import org.eclipse.swt.graphics.Point;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasPathData;
import org.archstudio.bna.facets.IMirrorsPathData;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;

public class MirrorPathDataLogic extends AbstractThingLogic implements IBNAModelListener {

	protected ThingRefTrackingLogic trtl = null;

	public MirrorPathDataLogic(ThingRefTrackingLogic trtl) {
		this.trtl = trtl;
	}

	public void init() {
		IBNAModel m = getBNAModel();
		if (m != null) {
			for (IThing t : m.getAllThings()) {
				if (t instanceof IMirrorsPathData) {
					checkAndMirror((IMirrorsPathData) t);
				}
			}
		}
	}

	public void destroy() {
	}

	protected void checkAndMirror(IMirrorsPathData targetThing) {
		String bbmID = targetThing.getPathDataMasterThingID();
		if (bbmID != null) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				IThing bbm = m.getThing(bbmID);
				if (bbm != null) {
					if (bbm instanceof IHasPathData) {
						PathData bb = ((IHasPathData) bbm).getPathData();
						PathData nbb = bb; // new Rectangle(bb.x, bb.y, bb.width, bb.height);
						Point offsets = targetThing.getPathDataMirrorOffsets();
						if (offsets != null) {
							//							nbb.x += offsets.x;
							//							nbb.y += offsets.y;
							//							nbb.width += offsets.width;
							//							if(nbb.width < 0) nbb.width = 0;
							//							nbb.height += offsets.height;
							//							if(nbb.height < 0) nbb.height = 0;
						}
						targetThing.setPathData(nbb);
					}
				}
			}
		}
	}

	public void bnaModelChanged(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_ADDED) {
			IThing targetThing = evt.getTargetThing();
			if (targetThing instanceof IMirrorsPathData) {
				checkAndMirror((IMirrorsPathData) evt.getTargetThing());
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			IThing targetThing = evt.getTargetThing();
			ThingEvent tevt = evt.getThingEvent();
			if (targetThing instanceof IMirrorsPathData) {
				if (IMirrorsPathData.PATH_DATA_MASTER_THING_ID_PROPERTY_NAME.equals(tevt.getPropertyName())) {
					checkAndMirror((IMirrorsPathData) targetThing);
				}
			}
			if (targetThing instanceof IHasPathData) {
				if (IHasPathData.PATH_DATA_PROPERTY_NAME.equals(tevt.getPropertyName())) {
					IBNAModel m = getBNAModel();
					String[] thingIDs = trtl.findReferencingThings(targetThing, IMirrorsPathData.PATH_DATA_MASTER_THING_ID_PROPERTY_NAME);
					for (int i = 0; i < thingIDs.length; i++) {
						IThing thing = m.getThing(thingIDs[i]);
						if (thing instanceof IMirrorsPathData) {
							checkAndMirror((IMirrorsPathData) thing);
						}
					}
				}
			}
		}
	}
}
