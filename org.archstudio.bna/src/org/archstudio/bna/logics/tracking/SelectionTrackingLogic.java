package org.archstudio.bna.logics.tracking;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.facets.IHasSelected;
import org.archstudio.bna.logics.tracking.SelectionChangedEvent.EventType;

public class SelectionTrackingLogic extends AbstractThingLogic implements IBNASynchronousModelListener {

	protected Set<IHasSelected> selectedThingsSet = Collections.synchronizedSet(new HashSet<IHasSelected>());
	protected boolean initialized = false;

	protected List<ISelectionTrackingListener> selectionTrackingListeners = new CopyOnWriteArrayList<ISelectionTrackingListener>();

	public void addSelectionTrackingListener(ISelectionTrackingListener l) {
		selectionTrackingListeners.add(l);
	}

	public void removeSelectionTrackingListener(ISelectionTrackingListener l) {
		selectionTrackingListeners.remove(l);
	}

	protected void fireSelectionChangedEvent(EventType eventType, IThing targetThing) {
		SelectionChangedEvent evt = new SelectionChangedEvent(this, eventType, targetThing);

		for (ISelectionTrackingListener l : selectionTrackingListeners) {
			l.selectionChanged(evt);
		}
	}

	protected void init(IBNAModel m) {
		synchronized (selectedThingsSet) {
			for (IThing t : m) {
				if (t instanceof IHasSelected) {
					IHasSelected st = (IHasSelected) t;
					if (st.isSelected()) {
						selectedThingsSet.add(st);
					}
				}
			}
			initialized = true;
		}
	}

	public void init() {
		IBNAModel m = getBNAModel();
		if (m != null) {
			this.init(m);
		}
	}

	public void bnaModelChangedSync(BNAModelEvent evt) {
		if (!initialized) {
			init(evt.getSource());
		}
		if (evt.getEventType() == BNAModelEvent.EventType.THING_ADDED) {
			IThing addedThing = evt.getTargetThing();
			if (addedThing instanceof IHasSelected) {
				IHasSelected st = (IHasSelected) addedThing;
				if (st.isSelected()) {
					selectedThingsSet.add(st);
					fireSelectionChangedEvent(SelectionChangedEvent.EventType.THING_SELECTED, st);
				}
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_REMOVED) {
			if (selectedThingsSet.contains(evt.getTargetThing())) {
				selectedThingsSet.remove(evt.getTargetThing());
				fireSelectionChangedEvent(SelectionChangedEvent.EventType.THING_DESELECTED, evt.getTargetThing());
			}
		}
		else if (evt.getEventType() == BNAModelEvent.EventType.THING_CHANGED) {
			IThing changedThing = evt.getTargetThing();
			if (changedThing instanceof IHasSelected) {
				IHasSelected st = (IHasSelected) changedThing;
				if (st.isSelected()) {
					if (!selectedThingsSet.contains(st)) {
						selectedThingsSet.add(st);
						fireSelectionChangedEvent(SelectionChangedEvent.EventType.THING_SELECTED, st);
					}
				}
				else {
					if (selectedThingsSet.contains(st)) {
						selectedThingsSet.remove(st);
						fireSelectionChangedEvent(SelectionChangedEvent.EventType.THING_DESELECTED, st);
					}
				}
			}
		}
	}

	public String[] getSelectedThingIDs() {
		IHasSelected[] selectedThings = getSelectedThings();
		String[] selectedThingIDs = new String[selectedThings.length];
		for (int i = 0; i < selectedThings.length; i++) {
			selectedThingIDs[i] = selectedThings[i].getID();
		}
		return selectedThingIDs;
	}

	public IHasSelected[] getSelectedThings() {
		return selectedThingsSet.toArray(new IHasSelected[selectedThingsSet.size()]);
	}
}
