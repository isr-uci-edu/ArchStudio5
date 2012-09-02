package org.archstudio.bna.logics.tracking;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThingLogicManager;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasBoundingBox;
import org.archstudio.bna.facets.IIsHidden;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.eclipse.swt.graphics.Rectangle;

public class ModelBoundsTrackingLogic extends AbstractThingLogic implements IBNAModelListener {

	public static Rectangle getModelBounds(IBNAWorld world) {
		IThingLogicManager tlm = world.getThingLogicManager();
		ModelBoundsTrackingLogic mbtl = tlm.addThingLogic(ModelBoundsTrackingLogic.class);
		return mbtl.getModelBounds();
	}

	protected ThingTypeTrackingLogic typeLogic = null;

	private Rectangle modelBounds = null;

	public ModelBoundsTrackingLogic() {
	}

	@Override
	protected void init() {
		super.init();
		typeLogic = addThingLogic(ThingTypeTrackingLogic.class);
	}

	@Override
	public void bnaModelChanged(BNAModelEvent evt) {
		switch (evt.getEventType()) {
		case THING_ADDED: {
			IThing thing = evt.getTargetThing();
			if (thing instanceof IHasBoundingBox) {
				synchronized (this) {
					updateModelBounds(null, ((IHasBoundingBox) thing).getBoundingBox());
				}
			}
		}
			break;
		case THING_REMOVED: {
			IThing thing = evt.getTargetThing();
			if (thing instanceof IHasBoundingBox) {
				synchronized (this) {
					updateModelBounds(((IHasBoundingBox) thing).getBoundingBox(), null);
				}
			}
		}
			break;
		case THING_CHANGED: {
			ThingEvent te = evt.getThingEvent();
			if (IHasBoundingBox.BOUNDING_BOX_KEY.equals(te.getPropertyName())) {
				synchronized (this) {
					modelBounds = null;
					//updateModelBounds((Rectangle) te.getOldPropertyValue(), (Rectangle) te.getNewPropertyValue());
				}
			}
		}
			break;
		default:
			// do nothing
		}
	}

	private void updateModelBounds(Rectangle obb, Rectangle nbb) {
		assert Thread.holdsLock(this);

		if (modelBounds != null) {
			int modelBoundsX1 = modelBounds.x;
			int modelBoundsY1 = modelBounds.y;
			int modelBoundsX2 = modelBounds.x + modelBounds.width;
			int modelBoundsY2 = modelBounds.y + modelBounds.height;
			if (obb != null && obb.width > 0 && obb.height > 0) {
				int obbX1 = obb.x;
				int obbY1 = obb.y;
				int obbX2 = obb.x + obb.width;
				int obbY2 = obb.y + obb.height;
				//See if it was on the edge of the old model
				if (obbX1 == modelBoundsX1 || obbY1 == modelBoundsY1 || obbX2 == modelBoundsX2
						|| obbY2 == modelBoundsY2) {
					//It was, let's recalc the whole thing since it may have moved inward by some dimension
					modelBounds = null;
					return;
				}
			}
			if (nbb != null && nbb.width > 0 && nbb.height > 0) {
				int nbbX1 = nbb.x;
				int nbbY1 = nbb.y;
				int nbbX2 = nbb.x + nbb.width;
				int nbbY2 = nbb.y + nbb.height;
				if (nbbX1 < modelBoundsX1) {
					modelBoundsX1 = nbbX1;
				}
				if (nbbY1 < modelBoundsY1) {
					modelBoundsY1 = nbbY1;
				}
				if (nbbX2 > modelBoundsX2) {
					modelBoundsX2 = nbbX2;
				}
				if (nbbY2 > modelBoundsY2) {
					modelBoundsY2 = nbbY2;
				}
				modelBounds = new Rectangle(modelBoundsX1, modelBoundsY1, modelBoundsX2 - modelBoundsX1, modelBoundsY2
						- modelBoundsY1);
			}
		}
	}

	private void updateModelBounds() {
		assert Thread.holdsLock(this);

		if (modelBounds == null) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				int x1 = Integer.MAX_VALUE;
				int y1 = Integer.MAX_VALUE;
				int x2 = Integer.MIN_VALUE;
				int y2 = Integer.MIN_VALUE;

				boolean foundSomething = false;
				for (IHasBoundingBox t : typeLogic.getThings(m, IHasBoundingBox.class)) {
					if (!Boolean.TRUE.equals(t.get(IIsHidden.HIDDEN_KEY))) {
						Rectangle bb = t.getBoundingBox();
						if (bb != null) {
							if (bb.width <= 0 || bb.height <= 0) {
								continue;
							}
							foundSomething = true;
							if (bb.x < x1) {
								x1 = bb.x;
							}
							if (bb.y < y1) {
								y1 = bb.y;
							}
							if (bb.x + bb.width > x2) {
								x2 = bb.x + bb.width;
							}
							if (bb.y + bb.height > y2) {
								y2 = bb.y + bb.height;
							}
						}
					}
				}
				if (foundSomething)
					modelBounds = new Rectangle(x1, y1, x2 - x1, y2 - y1);
			}
		}
	}

	public synchronized Rectangle getModelBounds() {
		synchronized (this) {
			modelBounds = null;
			updateModelBounds();
			if (modelBounds != null) {
				return new Rectangle(modelBounds.x, modelBounds.y, modelBounds.width, modelBounds.height);
			}
			return new Rectangle(0, 0, 0, 0);
		}
	}
}
