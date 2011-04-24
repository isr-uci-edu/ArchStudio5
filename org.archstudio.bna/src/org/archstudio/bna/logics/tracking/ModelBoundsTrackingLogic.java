package org.archstudio.bna.logics.tracking;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAUtils;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasAnchorPoint;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IHasVisible;
import org.archstudio.bna.things.utility.EnvironmentPropertiesThing;

public class ModelBoundsTrackingLogic extends AbstractThingLogic implements IBoundingBoxTrackingListener, IAnchorPointTrackingListener,
        IBNASynchronousModelListener {
	protected BoundingBoxTrackingLogic bbtl = null;
	protected AnchorPointTrackingLogic aptl = null;

	protected Rectangle modelBounds = BNAUtils.NONEXISTENT_RECTANGLE;

	public ModelBoundsTrackingLogic(BoundingBoxTrackingLogic bbtl, AnchorPointTrackingLogic aptl) {
		this.bbtl = bbtl;
		this.aptl = aptl;
	}

	public synchronized void init() {
		bbtl.addTrackingListener(this);
		aptl.addTrackingListener(this);
		recalculate();
	}

	public void destroy() {
		aptl.removeTrackingListener(this);
		bbtl.removeTrackingListener(this);
	}

	public void bnaModelChangedSync(BNAModelEvent evt) {
		if (evt.getEventType() == BNAModelEvent.EventType.THING_ADDED) {
			if ((evt.getTargetThing() instanceof IHasBoundingBox) || (evt.getTargetThing() instanceof IHasAnchorPoint)) {
				recalculate();
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_REMOVED) {
			if ((evt.getTargetThing() instanceof IHasBoundingBox) || (evt.getTargetThing() instanceof IHasAnchorPoint)) {
				recalculate();
			}
		}
	}

	public void recalculateNow() {
		recalculate();
	}

	protected void recalculate() {
		IBNAModel m = getBNAModel();
		if (m != null) {
			int x1 = Integer.MAX_VALUE;
			int y1 = Integer.MAX_VALUE;
			int x2 = Integer.MIN_VALUE;
			int y2 = Integer.MIN_VALUE;

			boolean thingFound = false;
			for (IThing t : m.getAllThings()) {
				if ((t instanceof IHasVisible) && (!((IHasVisible) t).isVisible()))
					continue;
				if (t instanceof IHasBoundingBox) {
					Rectangle bb = ((IHasBoundingBox) t).getBoundingBox();
					if ((bb.x <= 0) || (bb.y <= 0))
						continue;
					if (bb != null) {
						thingFound = true;
						if (bb.x < x1) {
							x1 = bb.x;
						}
						if (bb.y < y1) {
							y1 = bb.y;
						}
						if ((bb.x + bb.width) > x2)
							x2 = bb.x + bb.width;
						if ((bb.y + bb.height) > y2)
							y2 = bb.y + bb.height;
					}
				}
				if (t instanceof IHasAnchorPoint) {
					Point ap = ((IHasAnchorPoint) t).getAnchorPoint();
					if (ap != null) {
						if ((ap.x <= 0) || (ap.y <= 0))
							continue;
						thingFound = true;
						if (ap.x < x1) {
							x1 = ap.x;
						}
						if (ap.y < y1) {
							y1 = ap.y;
						}
						if (ap.x > x2) {
							x2 = ap.x;
						}
						if (ap.y > y2) {
							y2 = ap.y;
						}
					}
				}
			}

			Rectangle oldModelBounds = modelBounds;
			if (!thingFound) {
				modelBounds = BNAUtils.NONEXISTENT_RECTANGLE;
				EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(m);
				ept.setProperty("#modelBounds", modelBounds);
			}
			else {
				modelBounds = new Rectangle(x1, y1, x2 - x1, y2 - y1);
				EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(m);
				ept.setProperty("#modelBounds", modelBounds);
			}
			fireModelBoundsChangedEvent(oldModelBounds, modelBounds);
		}
	}

	public void boundingBoxChanged(BoundingBoxChangedEvent evt) {
		Rectangle obb = evt.getOldBoundingBox();
		Rectangle nbb = evt.getNewBoundingBox();

		if (obb != null) {
			//See if it was on the edge of the old model
			if ((obb.x == modelBounds.x) || (obb.y == modelBounds.y)
			        || ((obb.x + obb.width) == (modelBounds.x + modelBounds.width) || ((obb.y + obb.height) == (modelBounds.y + modelBounds.height)))) {
				//It was, let's recalc the whole thing since it may have moved inward by some dimension
				recalculate();
				return;
			}
		}
		if (nbb != null) {
			Rectangle newModelBounds = new Rectangle(modelBounds.x, modelBounds.y, modelBounds.width, modelBounds.height);
			if ((nbb.x <= 0) || (nbb.y <= 0))
				return;
			if (nbb.x < modelBounds.x)
				newModelBounds.x = nbb.x;
			if (nbb.y < modelBounds.y)
				newModelBounds.y = nbb.y;
			if ((nbb.x + nbb.width) > (modelBounds.x + modelBounds.width))
				newModelBounds.width = (nbb.x + nbb.width) - newModelBounds.x;
			if ((nbb.y + nbb.height) > (modelBounds.y + modelBounds.height))
				newModelBounds.height = (nbb.y + nbb.height) - newModelBounds.y;
			if (!BNAUtils.nulleq(modelBounds, newModelBounds)) {
				Rectangle oldModelBounds = modelBounds;
				modelBounds = newModelBounds;
				EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(getBNAModel());
				ept.setProperty("#modelBounds", modelBounds);
				fireModelBoundsChangedEvent(oldModelBounds, modelBounds);
			}
		}
	}

	public void anchorPointChanged(AnchorPointChangedEvent evt) {
		if ((evt.getTargetThing() != null) && (evt.getTargetThing() instanceof IHasBoundingBox)) {
			if (((IHasBoundingBox) evt.getTargetThing()).getBoundingBox() != null)
				return;
		}
		Point oap = evt.getOldAnchorPoint();
		Point nap = evt.getNewAnchorPoint();

		if (oap != null) {
			//See if it was on the edge of the old model
			if ((oap.x == modelBounds.x) || (oap.y == modelBounds.y)
			        || (oap.x == (modelBounds.x + modelBounds.width) || (oap.y == (modelBounds.y + modelBounds.height)))) {
				//It was, let's recalc the whole thing since it may have moved inward by some dimension
				recalculate();
				return;
			}
		}
		if (nap != null) {
			if ((nap.x <= 0) || (nap.y <= 0))
				return;
			Rectangle newModelBounds = new Rectangle(modelBounds.x, modelBounds.y, modelBounds.width, modelBounds.height);
			if (nap.x < modelBounds.x)
				newModelBounds.x = nap.x;
			if (nap.y < modelBounds.y)
				newModelBounds.y = nap.y;
			if (nap.x > (modelBounds.x + modelBounds.width))
				newModelBounds.width = nap.x - newModelBounds.x;
			if (nap.y > (modelBounds.y + modelBounds.height))
				newModelBounds.height = nap.y - newModelBounds.y;
			if (!BNAUtils.nulleq(modelBounds, newModelBounds)) {
				Rectangle oldModelBounds = modelBounds;
				modelBounds = newModelBounds;
				EnvironmentPropertiesThing ept = BNAUtils.getEnvironmentPropertiesThing(getBNAModel());
				ept.setProperty("#modelBounds", modelBounds);
				fireModelBoundsChangedEvent(oldModelBounds, modelBounds);
			}
		}
	}

	public synchronized Rectangle getModelBounds() {
		return new Rectangle(modelBounds.x, modelBounds.y, modelBounds.width, modelBounds.height);
	}

	protected List<IModelBoundsTrackingListener> trackingListeners = new CopyOnWriteArrayList<IModelBoundsTrackingListener>();

	public void addTrackingListener(IModelBoundsTrackingListener l) {
		trackingListeners.add(l);
	}

	public void removeTrackingListener(IModelBoundsTrackingListener l) {
		trackingListeners.remove(l);
	}

	protected void fireModelBoundsChangedEvent(Rectangle oldModelBounds, Rectangle newModelBounds) {
		if (BNAUtils.nulleq(oldModelBounds, newModelBounds)) {
			return;
		}
		ModelBoundsChangedEvent evt = new ModelBoundsChangedEvent(this, getBNAModel(), oldModelBounds, newModelBounds);
		for (IModelBoundsTrackingListener l : trackingListeners) {
			l.modelBoundsChanged(evt);
		}
	}
}
