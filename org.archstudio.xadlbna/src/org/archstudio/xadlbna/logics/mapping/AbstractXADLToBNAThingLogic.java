package org.archstudio.xadlbna.logics.mapping;

import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNASynchronousModelListener;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.xadl.IXArchRelativePathTrackerListener;
import org.archstudio.xadl.XArchRelativePathTracker;
import org.archstudio.xadlbna.things.IHasObjRef;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTPath;

import com.google.common.collect.Lists;

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
	public void processAdd(List<ObjRef> relLineageRefs, ObjRef objRef) {
		IBNAModel model = getBNAModel();
		if (model != null) {
			model.beginBulkChange();
			updatingThings++;
			try {
				T thing = addThing(relLineageRefs, objRef);
				updateThing(relLineageRefs, null, objRef, null, thing);
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
	public void processUpdate(List<ObjRef> relLineageRefs, XArchADTPath relPath, ObjRef objRef, XArchADTModelEvent evt) {
		IBNAModel model = getBNAModel();
		if (model != null) {
			model.beginBulkChange();
			updatingThings++;
			try {
				for (IThing t : model.getThings(tvtl.getThingIDs(IHasObjRef.OBJREF_KEY, objRef, MAPPING_KEY, this))) {
					updateThing(relLineageRefs, relPath, objRef, evt, (T) t);
				}
			}
			finally {
				updatingThings--;
				model.endBulkChange();
			}
		}
	}

	@Override
	public void processRemove(List<ObjRef> relLineageRefs, ObjRef objRef) {
		IBNAModel model = getBNAModel();
		if (model != null) {
			model.beginBulkChange();
			updatingThings++;
			try {
				for (IThing t : model.getThings(tvtl.getThingIDs(IHasObjRef.OBJREF_KEY, objRef, MAPPING_KEY, this))) {
					Assemblies.removeRootAndParts(model, t);
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
				IBNAModel model = getBNAModel();
				if (model != null) {
					IThing thing = evt.getTargetThing();
					List<IThingRefKey<?>> bnaPathSegments = Lists.newArrayList();
					while (thing != null) {
						if (thing.has(MAPPING_KEY, this)) {
							ObjRef objRef = thing.get(IHasObjRef.OBJREF_KEY);
							if (objRef != null) {
								storeThingData(objRef, (T) thing, new BNAPath(Lists.reverse(bnaPathSegments)), evt);
								break;
							}
						}
						bnaPathSegments.add(Assemblies.getPartKey(thing));
						thing = Assemblies.getAssemblyWithPart(model, thing);
						// TODO: finish
					}
				}
			}
		}
	}

	protected abstract T addThing(List<ObjRef> relativeLineageRefs, ObjRef objRef);

	protected void updateThing(List<ObjRef> relativeLineageRefs, XArchADTPath relativePath, ObjRef objRef,
			XArchADTModelEvent evt, T thing) {
	}

	protected <ET extends IThing, EK extends IThingKey<EV>, EV> void storeThingData(ObjRef objRef, T rootThing,
			BNAPath relativeBNAPath, BNAModelEvent<ET, EK, EV> evt) {
	}
}
