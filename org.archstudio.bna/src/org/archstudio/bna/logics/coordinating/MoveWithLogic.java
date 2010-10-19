package org.archstudio.bna.logics.coordinating;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IMovesWith;
import org.archstudio.bna.facets.IRelativeMovable;
import org.archstudio.bna.logics.tracking.AnchorPointChangedEvent;
import org.archstudio.bna.logics.tracking.AnchorPointTrackingLogic;
import org.archstudio.bna.logics.tracking.BoundingBoxChangedEvent;
import org.archstudio.bna.logics.tracking.BoundingBoxTrackingLogic;
import org.archstudio.bna.logics.tracking.IAnchorPointTrackingListener;
import org.archstudio.bna.logics.tracking.IBoundingBoxTrackingListener;
import org.archstudio.bna.logics.tracking.ThingRefTrackingLogic;

public class MoveWithLogic extends AbstractThingLogic implements IBoundingBoxTrackingListener, IAnchorPointTrackingListener {

	protected ThingRefTrackingLogic thingRefTrackingLogic = null;
	protected BoundingBoxTrackingLogic boundingBoxTrackingLogic = null;
	protected AnchorPointTrackingLogic anchorPointTrackingLogic = null;

	public MoveWithLogic(ThingRefTrackingLogic thingRefTrackingLogic, BoundingBoxTrackingLogic boundingBoxTrackingLogic,
	        AnchorPointTrackingLogic anchorPointTrackingLogic) {
		this.thingRefTrackingLogic = thingRefTrackingLogic;
		this.boundingBoxTrackingLogic = boundingBoxTrackingLogic;
		this.anchorPointTrackingLogic = anchorPointTrackingLogic;
	}

	public void init() {
		boundingBoxTrackingLogic.addTrackingListener(this);
		anchorPointTrackingLogic.addTrackingListener(this);
	}

	public void destroy() {
		anchorPointTrackingLogic.removeTrackingListener(this);
		boundingBoxTrackingLogic.removeTrackingListener(this);
	}

	public void boundingBoxChanged(BoundingBoxChangedEvent evt) {
		IBNAModel m = getBNAModel();
		if (m != null) {
			IThing targetThing = evt.getTargetThing();
			String[] reffingThingIDs = thingRefTrackingLogic.findReferencingThings(targetThing, IMovesWith.MOVES_WITH_THING_ID_PROPERTY_NAME);
			for (int i = 0; i < reffingThingIDs.length; i++) {
				IThing reffingThing = m.getThing(reffingThingIDs[i]);
				if (reffingThing != null) {
					if (reffingThing instanceof IMovesWith) {
						int movesWithMode = ((IMovesWith) reffingThing).getMovesWithMode();
						if (movesWithMode == IMovesWith.TRACK_ANCHOR_POINT_ONLY) {
							return;
						}
						else if (movesWithMode == IMovesWith.TRACK_ANCHOR_POINT_FIRST) {
							if (targetThing instanceof IHasAnchorPoint) {
								return;
							}
						}
					}
					//OK, we should move it
					Rectangle obb = evt.getOldBoundingBox();
					Rectangle nbb = evt.getNewBoundingBox();
					if ((obb != null) && (nbb != null)) {
						int dx = (nbb.x + (nbb.width / 2)) - (obb.x + (obb.width / 2));
						int dy = (nbb.y + (nbb.height / 2)) - (obb.y + (obb.height / 2));
						if (reffingThing instanceof IRelativeMovable) {
							moveRelative((IRelativeMovable) reffingThing, dx, dy);
						}
					}
				}
			}
		}
	}

	public void anchorPointChanged(AnchorPointChangedEvent evt) {
		IBNAModel m = getBNAModel();
		if (m != null) {
			IThing targetThing = evt.getTargetThing();
			String[] reffingThingIDs = thingRefTrackingLogic.findReferencingThings(targetThing, IMovesWith.MOVES_WITH_THING_ID_PROPERTY_NAME);
			for (int i = 0; i < reffingThingIDs.length; i++) {
				IThing reffingThing = m.getThing(reffingThingIDs[i]);
				if (reffingThing != null) {
					if (reffingThing instanceof IMovesWith) {
						int movesWithMode = ((IMovesWith) reffingThing).getMovesWithMode();
						if (movesWithMode == IMovesWith.TRACK_BOUNDING_BOX_ONLY) {
							return;
						}
						else if (movesWithMode == IMovesWith.TRACK_BOUNDING_BOX_FIRST) {
							if (targetThing instanceof IHasBoundingBox) {
								return;
							}
						}
					}
					//OK, we should move it
					Point oap = evt.getOldAnchorPoint();
					Point nap = evt.getNewAnchorPoint();
					if ((oap != null) && (nap != null)) {
						int dx = nap.x - oap.x;
						int dy = nap.y - oap.y;
						if (reffingThing instanceof IRelativeMovable) {
							moveRelative((IRelativeMovable) reffingThing, dx, dy);
						}
					}
				}
			}
		}
	}

	public static void moveRelative(IRelativeMovable t, int dx, int dy) {
		Point p = t.getReferencePoint();
		Point np = new Point(p.x + dx, p.y + dy);
		t.setReferencePoint(np);
	}

}
