package org.archstudio.bna.logics.background;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNATickListener;
import org.archstudio.bna.facets.IHasMutableLife;
import org.archstudio.bna.logics.tracking.IThingSetListener;
import org.archstudio.bna.logics.tracking.ThingSetChangedEvent;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;

public class LifeSapperLogic extends AbstractThingLogic implements IThingSetListener, IBNATickListener {

	protected TypedThingSetTrackingLogic<IHasMutableLife> tttl = null;

	public LifeSapperLogic(TypedThingSetTrackingLogic<IHasMutableLife> tttl) {
		this.tttl = tttl;
	}

	public void init() {
		tttl.addThingSetListener(this);
	}

	public void destroy() {
		tttl.removeThingSetListener(this);
	}

	public void thingSetChanged(ThingSetChangedEvent evt) {
	}
	
	@Override
	public synchronized void timerTick(int count) {
		IHasMutableLife[] things = tttl.getThings();
		if (things.length > 0) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				m.beginBulkChange();
			}
			for (IHasMutableLife t : things) {
				int life = t.getLife();
				int newLife = life - 1;
				if (newLife < 0) {
					m.removeThing(t);
				}
				else {
					t.setLife(newLife);
				}
			}
			if (m != null) {
				m.endBulkChange();
			}
		}
	}
}
