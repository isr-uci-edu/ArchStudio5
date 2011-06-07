package edu.uci.isr.archstudio4.comp.archipelago.generic.logics.mapping;

import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.xadl.IXArchRelativePathTrackerListener;
import org.archstudio.xadl.XArchRelativePathTracker;
import org.archstudio.xadlbna.IHasObjRef;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTPath;

public abstract class AbstractXADLToBNAThingLogic<T extends IThing> extends AbstractThingLogic implements
		IBNASynchronousModelListener, IXArchADTModelListener, IXArchRelativePathTrackerListener {

	protected final static IThingKey<Object> MAPPING_KEY = ThingKey.create(AbstractXADLToBNAThingLogic.class);

	protected final IXArchADT xarch;
	protected final XArchRelativePathTracker tracker;

	private int updatingThings = 0;

	public AbstractXADLToBNAThingLogic(IXArchADT xarch, ObjRef rootObjRef, String relativePath) {
		this.xarch = xarch;
		this.tracker = new XArchRelativePathTracker(xarch, rootObjRef, relativePath, false);
		tracker.addTrackerListener(this);
	}

	protected ThingValueTrackingLogic tvtl = null;

	@Override
	public void init() {
		super.init();
		tvtl = getBNAWorld().getThingLogicManager().addThingLogic(ThingValueTrackingLogic.class);
		tracker.startScanning();
	}

	@Override
	public void destroy() {
		tracker.stopScanning();
		tvtl = null;
		super.destroy();
	}

	@Override
	public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		if (getBNAWorld() != null) {
			tracker.handleXArchADTModelEvent(evt);
		}
	}

	@Override
	public void processAdd(ObjRef objRef, List<ObjRef> relativeAncestorRefs) {
		IBNAModel model = getBNAModel();
		if (model != null) {
			model.beginBulkChange();
			updatingThings++;
			try {
				T thing = addThing(objRef, relativeAncestorRefs);
				updateThing(objRef, relativeAncestorRefs, null, null, thing);
				thing.set(IHasObjRef.OBJREF_KEY, objRef);
				thing.set(MAPPING_KEY, this);
			}
			finally {
				updatingThings--;
				model.endBulkChange();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void processUpdate(ObjRef objRef, List<ObjRef> relativeAncestorRefs, XArchADTModelEvent evt,
			XArchADTPath relativeSourceTargetPath) {
		IBNAModel model = getBNAModel();
		if (model != null) {
			model.beginBulkChange();
			updatingThings++;
			try {
				for (IThing t : model.getThings(tvtl.getThingIDs(IHasObjRef.OBJREF_KEY, objRef, MAPPING_KEY, this))) {
					updateThing(objRef, relativeAncestorRefs, evt, relativeSourceTargetPath, (T) t);
				}
			}
			finally {
				updatingThings--;
				model.endBulkChange();
			}
		}
	}

	@Override
	public void processRemove(ObjRef objRef, List<ObjRef> relativeAncestorRefs) {
		IBNAModel model = getBNAModel();
		if (model != null) {
			model.beginBulkChange();
			updatingThings++;
			try {
				for (IThing t : model.getThings(tvtl.getThingIDs(IHasObjRef.OBJREF_KEY, objRef, MAPPING_KEY, this))) {
					for (IThing t2 : Assemblies.getParts(model, t)) {
						model.removeThing(t2);
					}
					model.removeThing(t);
				}
			}
			finally {
				updatingThings--;
				model.endBulkChange();
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <ET extends IThing, EK extends IThingKey<EV>, EV> void bnaModelChangedSync(BNAModelEvent<ET, EK, EV> evt) {
		if (updatingThings == 0) {
			switch (evt.getEventType()) {
			case THING_CHANGED:
				ET thing = evt.getTargetThing();
				if (thing.has(MAPPING_KEY, this)) {
					ObjRef objRef = thing.get(IHasObjRef.OBJREF_KEY);
					if (objRef != null) {
						storeThingData(objRef, (T) thing, evt);
					}
				}
			}
		}
	}

	protected abstract T addThing(ObjRef objRef, List<ObjRef> relativeAncestorRefs);

	protected void updateThing(ObjRef objRef, List<ObjRef> relativeAncestorRefs, XArchADTModelEvent evt, XArchADTPath relativeSourceTargetPath,
			T thing) {
	}

	protected <ET extends IThing, EK extends IThingKey<EV>, EV> void storeThingData(ObjRef objRef, T thing,
			BNAModelEvent<ET, EK, EV> evt) {
	}
}
