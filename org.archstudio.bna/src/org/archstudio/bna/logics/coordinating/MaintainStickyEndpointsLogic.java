package org.archstudio.bna.logics.coordinating;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.BNAUtils.PointToRectangleDistanceData;
import org.archstudio.bna.facets.IHasEndpoints;
import org.archstudio.bna.facets.IHasStickyBox;
import org.archstudio.bna.facets.IHasStickyEndpoints;
import org.archstudio.bna.logics.tracking.IStickyBoxTrackingListener;
import org.archstudio.bna.logics.tracking.IThingSetListener;
import org.archstudio.bna.logics.tracking.StickyBoxChangedEvent;
import org.archstudio.bna.logics.tracking.StickyBoxTrackingLogic;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;
import org.archstudio.bna.logics.tracking.ThingSetChangedEvent;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;

public class MaintainStickyEndpointsLogic extends AbstractThingLogic implements IStickyBoxTrackingListener, IThingSetListener, IBNASynchronousModelListener {

	protected ThingRefTrackingLogic trtl = null;
	protected TypedThingSetTrackingLogic<IHasStickyEndpoints> ttstl = null;
	protected StickyBoxTrackingLogic sbtl = null;

	public MaintainStickyEndpointsLogic(ThingRefTrackingLogic trtl, TypedThingSetTrackingLogic<IHasStickyEndpoints> ttstl, StickyBoxTrackingLogic sbtl) {
		this.trtl = trtl;
		this.ttstl = ttstl;
		this.sbtl = sbtl;
	}

	public void init() {
		ttstl.addThingSetListener(this);
		sbtl.addTrackingListener(this);
		checkAllEndpoints();
	}

	public void destroy() {
		sbtl.removeTrackingListener(this);
		ttstl.removeThingSetListener(this);
	}

	protected void checkAllEndpoints() {
		IHasStickyEndpoints[] ts = ttstl.getThings();
		for (IHasStickyEndpoints t : ts) {
			checkEndpoints(t);
		}
	}

	protected void checkEndpoints(IHasStickyEndpoints t) {
		IBNAModel m = getBNAModel();
		if (m != null) {
			String ep1stID = t.getEndpoint1StuckToThingID();
			Point ep1 = t.getEndpoint1();
			IThing ep1st = m.getThing(ep1stID);
			if ((ep1st != null) && (ep1st instanceof IHasStickyBox)) {
				Rectangle sb = ((IHasStickyBox) ep1st).getStickyBox();
				if (sb != null) {
					if (!BNAUtils.isPointOnRectangle(ep1, sb)) {
						// The endpoint is stuck, but its endpoint is not on the sticky box.
						PointToRectangleDistanceData dd = BNAUtils.getPointToRectangleDistance(ep1, sb);
						Point newEndpoint = BNAUtils.snapToNormal(ep1, sb, dd.closestSide);
						t.setEndpoint1(newEndpoint);
					}
				}
			}

			String ep2stID = t.getEndpoint2StuckToThingID();
			Point ep2 = t.getEndpoint2();
			IThing ep2st = m.getThing(ep2stID);
			if ((ep2st != null) && (ep2st instanceof IHasStickyBox)) {
				Rectangle sb = ((IHasStickyBox) ep2st).getStickyBox();
				if (sb != null) {
					if (!BNAUtils.isPointOnRectangle(ep2, sb)) {
						// The endpoint is stuck, but its endpoint is not on the sticky box.
						PointToRectangleDistanceData dd = BNAUtils.getPointToRectangleDistance(ep2, sb);
						Point newEndpoint = BNAUtils.snapToNormal(ep2, sb, dd.closestSide);
						t.setEndpoint2(newEndpoint);
					}
				}
			}
		}
	}

	public void bnaModelChangedSync(BNAModelEvent evt) {
		IThing t = evt.getTargetThing();
		if (t instanceof IHasStickyEndpoints) {
			IHasStickyEndpoints st = (IHasStickyEndpoints) t;
			ThingEvent te = evt.getThingEvent();
			if (te != null) {
				String propertyName = te.getPropertyName();
				if (propertyName != null) {
					if (propertyName.equals(IHasStickyEndpoints.ENDPOINT_1_STUCK_TO_THING_ID_PROPERTY_NAME)
					        || propertyName.equals(IHasStickyEndpoints.ENDPOINT_2_STUCK_TO_THING_ID_PROPERTY_NAME)) {
						checkEndpoints(st);
					}
					if (propertyName.equals(IHasEndpoints.ENDPOINT_1_PROPERTY_NAME) || propertyName.equals(IHasEndpoints.ENDPOINT_2_PROPERTY_NAME)) {
						checkEndpoints(st);
					}
				}
			}
		}
	}

	public void stickyBoxChanged(StickyBoxChangedEvent evt) {
		IBNAModel m = getBNAModel();
		if (m != null) {
			IThing t = evt.getTargetThing();
			Rectangle oldStickyBox = evt.getOldStickyBox();
			Rectangle newStickyBox = evt.getNewStickyBox();
			if (newStickyBox == null) {
				return;
			}
			if (t instanceof IHasStickyBox) {
				IHasStickyBox sbt = (IHasStickyBox) t;
				String[] ep1stIDs = trtl.findReferencingThings(sbt, IHasStickyEndpoints.ENDPOINT_1_STUCK_TO_THING_ID_PROPERTY_NAME);
				for (String ep1stID : ep1stIDs) {
					IThing rt = m.getThing(ep1stID);
					if (rt instanceof IHasStickyEndpoints) {
						IHasStickyEndpoints st = (IHasStickyEndpoints) rt;
						Point ep = st.getEndpoint1();
						if (oldStickyBox == null) {
							checkEndpoints(st);
						}
						else {
							Point nep = BNAUtils.scaleAndMoveBorderPoint(ep, oldStickyBox, newStickyBox);
							st.setEndpoint1(nep);
						}
					}
				}

				String[] ep2stIDs = trtl.findReferencingThings(sbt, IHasStickyEndpoints.ENDPOINT_2_STUCK_TO_THING_ID_PROPERTY_NAME);
				for (String ep2stID : ep2stIDs) {
					IThing rt = m.getThing(ep2stID);
					if (rt instanceof IHasStickyEndpoints) {
						IHasStickyEndpoints st = (IHasStickyEndpoints) rt;
						Point ep = st.getEndpoint2();
						if (oldStickyBox == null) {
							checkEndpoints(st);
						}
						else {
							Point nep = BNAUtils.scaleAndMoveBorderPoint(ep, oldStickyBox, newStickyBox);
							st.setEndpoint2(nep);
						}
					}
				}
			}
		}
	}

	public void thingSetChanged(ThingSetChangedEvent evt) {
		EventType et = evt.getEventType();
		if (et == EventType.THING_ADDED) {
			IHasStickyEndpoints t = (IHasStickyEndpoints) evt.getTargetThing();
			checkEndpoints(t);
		}
	}
}
