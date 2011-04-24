package org.archstudio.bna.logics.background;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNATickListener;
import org.archstudio.bna.facets.IHasMutableRotatingOffset;
import org.archstudio.bna.logics.tracking.IThingSetListener;
import org.archstudio.bna.logics.tracking.ThingSetChangedEvent;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;

public class RotatingOffsetLogic extends AbstractThingLogic implements IThingSetListener, IBNATickListener {

	protected TypedThingSetTrackingLogic<IHasMutableRotatingOffset> tttl = null;

	public RotatingOffsetLogic(TypedThingSetTrackingLogic<IHasMutableRotatingOffset> tttl) {
		this.tttl = tttl;
	}

	public void init() {
		tttl.addThingSetListener(this);
	}

	public void destroy() {
		tttl.removeThingSetListener(this);
	}
	
	@Override
	public void thingSetChanged(ThingSetChangedEvent evt) {
	}

	@Override
	public synchronized void timerTick(int count) {
		IHasMutableRotatingOffset[] things = tttl.getThings();
		if (things.length > 0) {
			IBNAModel m = getBNAModel();
			if (m != null) {
				m.beginBulkChange();
			}
			for (IHasMutableRotatingOffset t : things) {
				t.incrementRotatingOffset();
			}
			if (m != null) {
				m.endBulkChange();
			}
		}
	}
}
