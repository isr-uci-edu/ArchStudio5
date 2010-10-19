package org.archstudio.bna.logics.coordinating;

import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasBoundingBoxRail;
import org.archstudio.bna.logics.tracking.AnchorPointChangedEvent;
import org.archstudio.bna.logics.tracking.AnchorPointTrackingLogic;
import org.archstudio.bna.logics.tracking.IAnchorPointTrackingListener;
import org.archstudio.bna.things.glass.EndpointGlassThing;
import org.archstudio.bna.things.labels.DirectionalLabelThing;
import org.archstudio.bna.things.utility.AssemblyRootThing;
import org.archstudio.swtutils.constants.Orientation;

public class EndpointFlowOrientationLogic extends AbstractThingLogic implements IAnchorPointTrackingListener, IBNASynchronousModelListener {

	protected AnchorPointTrackingLogic aptl = null;

	public EndpointFlowOrientationLogic(AnchorPointTrackingLogic aptl) {
		this.aptl = aptl;
	}

	public void init() {
		aptl.addTrackingListener(this);
	}

	public void destroy() {
		aptl.removeTrackingListener(this);
	}

	public void bnaModelChangedSync(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED:
			IThing t = evt.getTargetThing();
			if (t instanceof EndpointGlassThing) {
				checkFlow((EndpointGlassThing) t);
			}
			break;
		case THING_CHANGED:
			IThing t2 = evt.getTargetThing();
			if ((t2 != null) && (t2 instanceof EndpointGlassThing)) {
				ThingEvent tevt = evt.getThingEvent();
				if (tevt != null) {
					String propertyName = tevt.getPropertyName();
					if ((propertyName != null) && (propertyName.equals(IHasBoundingBoxRail.BOUNDING_BOX_RAIL_MASTER_THING_ID_PROPERTY_NAME))) {
						checkFlow((EndpointGlassThing) t2);
					}
				}
			}
			break;
		}
	}

	protected void checkFlow(EndpointGlassThing egt) {
		IBNAModel m = getBNAModel();
		if (m != null) {

			String bbtID = egt.getBoundingBoxRailMasterThingID();
			if (bbtID != null) {
				IThing bbt = m.getThing(bbtID);
				if ((bbt != null) && (bbt instanceof IHasBoundingBox)) {
					Rectangle bb = ((IHasBoundingBox) bbt).getBoundingBox();
					Orientation o = BNAUtils.getOrientationOfEdgePoint(egt.getAnchorPoint(), bb);
					if (o != Orientation.NONE) {
						IThing parentThing = m.getParentThing(egt);
						if ((parentThing != null) && (parentThing instanceof AssemblyRootThing)) {
							for (IThing t : m.getChildThings(parentThing)) {
								if (t instanceof DirectionalLabelThing) {
									((DirectionalLabelThing) t).setOrientation(o);
									return;
								}
							}
						}
					}
				}
			}
		}
	}

	public void anchorPointChanged(AnchorPointChangedEvent evt) {
		IThing t = evt.getTargetThing();
		if (t instanceof EndpointGlassThing) {
			checkFlow((EndpointGlassThing) t);
		}
	}
}
