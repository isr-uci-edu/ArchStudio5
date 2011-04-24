package org.archstudio.bna.logics.coordinating;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.facets.IHasMutableAssemblyData;

/**
 * When a thing is added to or removed from the model, this logic checks its
 * ancestors for an assembly root thing. If it finds one, it sets the assembly
 * root's changed property to true, which will give the assembly a chance to
 * reindex itself (if it uses indexing, which it should).
 * 
 * @author edashofy
 */
public class AssemblyLogic extends AbstractThingLogic implements IBNASynchronousModelListener {

	public void bnaModelChangedSync(BNAModelEvent evt) {
		EventType evtType = evt.getEventType();
		if (evtType.equals(EventType.THING_ADDED) || (evtType.equals(EventType.THING_REMOVING))) {
			for (IThing t : evt.getSource().getAncestorThings(evt.getTargetThing())) {
				if (t instanceof IHasMutableAssemblyData) {
					int lastValue = ((IHasMutableAssemblyData) t).getIndexValue();
					((IHasMutableAssemblyData) t).setIndexValue(lastValue + 1);
					return;
				}
			}
		}
	}
}
