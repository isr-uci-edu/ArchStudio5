package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasWorld;

/*
 * Note! This class cannot rely on a TypedThingSetTrackingLogic, because it
 * needs the typed thing set during the destroy operation and it can't be
 * guaranteed to exist at that time (the TTSTL is probably already destroyed
 * before we get here)
 */
public class WorldThingDestroyLogic extends AbstractThingLogic {
	protected boolean removeDestroyedViews = false;

	public WorldThingDestroyLogic(boolean removeDestroyedViews) {
		this.removeDestroyedViews = removeDestroyedViews;
	}

	public boolean getRemoveDestroyedViews() {
		return removeDestroyedViews;
	}

	public void destroy() {
		IBNAModel model = getBNAModel();
		if (model != null) {
			for (IThing t : model.getAllThings()) {
				if (t instanceof IHasWorld) {
					IBNAWorld internalWorld = ((IHasWorld) t).getWorld();
					if (internalWorld != null) {
						internalWorld.destroy();
						if (removeDestroyedViews) {
							((IHasWorld) t).removeProperty(IHasWorld.WORLD_PROPERTY_NAME);
						}
					}
				}
			}
		}
		super.destroy();
	}
}
