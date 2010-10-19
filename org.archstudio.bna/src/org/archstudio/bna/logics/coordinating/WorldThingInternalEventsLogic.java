package org.archstudio.bna.logics.coordinating;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.archstudio.bna.AbstractThingLogic;
import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.ThingEvent;
import org.archstudio.bna.facets.IHasWorld;
import org.archstudio.bna.logics.tracking.TypedThingSetTrackingLogic;

public class WorldThingInternalEventsLogic extends AbstractThingLogic implements IBNASynchronousModelListener {
	protected TypedThingSetTrackingLogic<IHasWorld> ttstl = null;
	protected Map<IHasWorld, InternalModelListener> thingToListenerMap = new HashMap<IHasWorld, InternalModelListener>();
	protected static int counter = 1000;

	public WorldThingInternalEventsLogic(TypedThingSetTrackingLogic<IHasWorld> ttstl) {
		this.ttstl = ttstl;
	}

	class InternalModelListener implements IBNAModelListener, IBNASynchronousModelListener {
		private IHasWorld viewThing = null;

		public InternalModelListener(IHasWorld viewThing) {
			this.viewThing = viewThing;
		}

		public void bnaModelChanged(BNAModelEvent evt) {
			fireInternalBNAModelEvent(viewThing, evt);
		}

		public void bnaModelChangedSync(BNAModelEvent evt) {
			fireInternalBNAModelEventSync(viewThing, evt);
			viewThing.setProperty("internalModelChangedTicker", ++counter);
		}
	}

	public void init() {
		IHasWorld[] viewThings = ttstl.getThings();
		for (IHasWorld vt : viewThings) {
			addListener(vt);
		}
	}

	public void destroy() {
		for (IHasWorld vt : thingToListenerMap.keySet()) {
			removeListener(vt);
		}
	}

	protected void addListener(IHasWorld vt) {
		IBNAWorld world = vt.getWorld();
		if (world != null) {
			InternalModelListener l = new InternalModelListener(vt);
			thingToListenerMap.put(vt, l);
			world.getBNAModel().addBNAModelListener(l);
			world.getBNAModel().addSynchronousBNAModelListener(l);
		}
	}

	protected void removeListener(IHasWorld vt) {
		InternalModelListener l = thingToListenerMap.get(vt);
		IBNAWorld world = vt.getWorld();
		if (world != null) {
			world.getBNAModel().removeSynchronousBNAModelListener(l);
			world.getBNAModel().removeBNAModelListener(l);
		}
	}

	public void bnaModelChangedSync(BNAModelEvent evt) {
		if ((evt.getTargetThing() != null) && (evt.getTargetThing() instanceof IHasWorld)) {
			IHasWorld vt = (IHasWorld) evt.getTargetThing();
			switch (evt.getEventType()) {
			case THING_ADDED:
				addListener(vt);
				break;
			case THING_REMOVING:
				removeListener(vt);
				break;
			case THING_CHANGED:
				ThingEvent te = evt.getThingEvent();
				if ((te != null) && (te.getPropertyName().equals(IHasWorld.WORLD_PROPERTY_NAME))) {
					removeListener(vt);
					addListener(vt);
				}
				break;
			}
		}
	}

	protected Vector<IInternalBNAModelListener> internalBNAModelListeners = new Vector<IInternalBNAModelListener>();
	protected IInternalBNAModelListener[] internalBNAModelListenerArray = new IInternalBNAModelListener[0];

	public void addInternalBNAModelListener(IInternalBNAModelListener l) {
		internalBNAModelListeners.addElement(l);
		internalBNAModelListenerArray = internalBNAModelListeners.toArray(new IInternalBNAModelListener[internalBNAModelListeners.size()]);
	}

	public void removeInternalBNAModelListener(IInternalBNAModelListener l) {
		internalBNAModelListeners.removeElement(l);
		internalBNAModelListenerArray = internalBNAModelListeners.toArray(new IInternalBNAModelListener[internalBNAModelListeners.size()]);
	}

	protected void fireInternalBNAModelEvent(IHasWorld src, BNAModelEvent evt) {
		for (IInternalBNAModelListener l : internalBNAModelListenerArray) {
			l.internalBNAModelChanged(src, evt);
		}
	}

	protected void fireInternalBNAModelEventSync(IHasWorld src, BNAModelEvent evt) {
		for (IInternalBNAModelListener l : internalBNAModelListenerArray) {
			l.internalBNAModelChangedSync(src, evt);
		}
	}
}
