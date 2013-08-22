package org.archstudio.xadl.bna.logics.mapping;

import java.util.List;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModel;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.xadl.IXArchRelativePathTrackerListener;
import org.archstudio.xadl.XArchRelativePathTracker;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.swt.widgets.Display;

import com.google.common.collect.Lists;

/**
 * Synchronizes a xADL ObjRef with a single BNA Assembly (or plain Thing). Changes to the xADL ObjRef and its children
 * will be reflected in the BNA Assembly, and changes to things in the BNA Assembly will be reflected in the xADL
 * ObjRef. This class facilitates the synchronization by preventing update cycles.
 * 
 * @param <T>
 *            The type of BNA Assembly/Thing that will be created by this class to represent targeted ObjRefs, see
 *            {@link #addThing(List, ObjRef)}
 */
public abstract class AbstractXADLToBNAThingLogic<T extends IThing> extends AbstractThingLogic implements
		IBNAModelListener, IXArchADTModelListener, IXArchRelativePathTrackerListener {

	protected final static IThingKey<Object> MAPPING_KEY = ThingKey.create(AbstractXADLToBNAThingLogic.class);

	protected ThingValueTrackingLogic valuesLogic = null;

	protected final IXArchADT xarch;

	/**
	 * The {@link XArchRelativePathTracker} used to identify the set of ObjRefs that will be mapped to BNA Assemblies
	 */
	protected final XArchRelativePathTracker tracker;

	/**
	 * Prevents cycles in updates--when the value is &gt; 0, the BNA events that are being monitored originated either
	 * directly from this logic or indirectly from other logics, but should be ignored.
	 */
	private int updatingThings = 0;

	public AbstractXADLToBNAThingLogic(IXArchADT xarch, ObjRef rootObjRef, String relativePath) {
		this.xarch = xarch;
		this.tracker = new XArchRelativePathTracker(xarch, rootObjRef, relativePath, false);
		tracker.addTrackerListener(this);
	}

	@Override
	public void setBNAWorld(IBNAWorld newBNAWorld) {
		super.setBNAWorld(newBNAWorld);
		if (newBNAWorld != null) {
			// tracking is started here so that it occurs after the init() method has been fully processed
			tracker.startScanning();
		}
	}

	@Override
	protected void init() {
		super.init();
		valuesLogic = addThingLogic(ThingValueTrackingLogic.class);
	}

	@Override
	public void destroy() {
		tracker.stopScanning();
		super.destroy();
	}

	@Override
	public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		if (getBNAWorld() != null) {
			tracker.handleXArchADTModelEvent(evt);
		}
	}

	/**
	 * Gets the things that have been added by this logic.
	 */
	@SuppressWarnings("unchecked")
	protected Iterable<T> getAddedThings() {
		return (Iterable<T>) getBNAModel().getThingsByID(valuesLogic.getThingIDs(MAPPING_KEY, this));
	}

	/**
	 * Ensures that the runnable is run in the display thread (runs it immediately if already in the display thread).
	 * 
	 * @param r
	 *            The runnable to run.
	 */
	private void execInDisplayThread(Runnable r) {
		if (Display.getDefault() == Display.getCurrent()) {
			r.run();
		}
		else {
			Display.getDefault().asyncExec(r);
		}
	}

	/**
	 * Takes the newly added ObjRef, translates it into a BNA Assembly through a call to {@link #addThing(List, ObjRef)}
	 * and updates the BNA Assembly through a call to
	 * {@link #updateThing(List, XArchADTPath, ObjRef, XArchADTModelEvent, IThing)}
	 */

	@Override
	public void processAdd(final List<ObjRef> relLineageRefs, final ObjRef objRef) {
		execInDisplayThread(new Runnable() {
			@Override
			public void run() {
				IBNAModel model = getBNAModel();
				if (model != null) {
					model.beginBulkChange();
					updatingThings++;
					try {
						// start by creating and updating the thing
						T thing = addThing(relLineageRefs, objRef);
						if (thing != null) {
							updateThing(relLineageRefs, null, objRef, null, thing);
							// note which ObjRef the thing represents
							thing.set(IHasObjRef.OBJREF_KEY, objRef);
							// mark the thing as originating from, and being synchronized by this logic
							thing.set(MAPPING_KEY, AbstractXADLToBNAThingLogic.this);
						}
					}
					finally {
						updatingThings--;
						model.endBulkChange();
					}
				}
			}
		});
	}

	/**
	 * Finds any BNA Assemblies that correspond to the modified ObjRef and updates them through calls to
	 * {@link #updateThing(List, XArchADTPath, ObjRef, XArchADTModelEvent, IThing)}
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void processUpdate(final List<ObjRef> relLineageRefs, final String relPath, final ObjRef objRef,
			final XArchADTModelEvent evt) {
		execInDisplayThread(new Runnable() {
			@Override
			public void run() {
				IBNAModel model = getBNAModel();
				if (model != null) {
					model.beginBulkChange();
					updatingThings++;
					try {
						for (IThing t : model.getThingsByID(valuesLogic.getThingIDs(IHasObjRef.OBJREF_KEY, objRef,
								MAPPING_KEY, AbstractXADLToBNAThingLogic.this))) {
							updateThing(relLineageRefs, relPath, objRef, evt, (T) t);
						}
					}
					finally {
						updatingThings--;
						model.endBulkChange();
					}
				}
			}
		});
	}

	/**
	 * Removes all BNA Assemblies that were created by this logic and correspond to the removed ObjRef
	 */

	@Override
	public void processRemove(final List<ObjRef> relLineageRefs, final ObjRef objRef) {
		execInDisplayThread(new Runnable() {
			@Override
			public void run() {
				IBNAModel model = getBNAModel();
				if (model != null) {
					model.beginBulkChange();
					updatingThings++;
					try {
						for (IThing t : model.getThingsByID(valuesLogic.getThingIDs(IHasObjRef.OBJREF_KEY, objRef,
								MAPPING_KEY, AbstractXADLToBNAThingLogic.this))) {
							Assemblies.removeRootAndParts(model, t);
						}
					}
					finally {
						updatingThings--;
						model.endBulkChange();
					}
				}
			}
		});
	}

	/**
	 * Monitors BNA model events to see if they represent changes to a BNA Assembly added by this logic. When found,
	 * updates the ObjRef data through calls to {@link #storeThingData(ObjRef, IThing, BNAPath, BNAModelEvent)}.
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void bnaModelChanged(final BNAModelEvent evt) {
		if (updatingThings == 0) {
			if (evt.getEventType() == EventType.THING_CHANGED) {
				IBNAModel model = getBNAModel();
				if (model != null) {
					IThing thing = evt.getTargetThing();
					List<IThingRefKey<?>> bnaPathSegments = Lists.newArrayList();
					while (thing != null) {
						// look for a BNA Assembly that we've mapped from an ObjRef
						if (thing.has(MAPPING_KEY, AbstractXADLToBNAThingLogic.this)) {
							ObjRef objRef = thing.get(IHasObjRef.OBJREF_KEY);
							if (objRef != null) {
								storeThingData(objRef, (T) thing, BNAPath.create(Lists.reverse(bnaPathSegments)), evt);
								break;
							}
						}
						// now check to see if this thing is part of an assembly
						bnaPathSegments.add(Assemblies.getPartName(thing));
						thing = Assemblies.getRootWithPart(model, thing);
					}
				}
			}
		}
	}

	/**
	 * Creates the BNA Assembly that represents the given ObjRef. Note that this class should merely create the assembly
	 * and not configure it with information from the ObjRef, that functionality is reserved for
	 * {@link #updateThing(List, XArchADTPath, ObjRef, XArchADTModelEvent, IThing)}
	 * 
	 * @param relativeLineageRefs
	 *            The ObjRefs from the rootObjRef to the objRef
	 * @param objRef
	 *            The objRef that is to be represented as a Thing in the BNA model
	 * @return The thing that was created to represent the ObjRef, or <code>null</code> if no thing should be added for
	 *         the given objRef.
	 */
	protected abstract @Nullable
	T addThing(List<ObjRef> relativeLineageRefs, ObjRef objRef);

	/**
	 * Updates the Thing returned from {@link #addThing(List, ObjRef)} to reflect information stored in the ObjRef and
	 * its children. This will be called upon initial creation of the BNA Assembly, and when the ObjRef or its children
	 * are modified.
	 * 
	 * @param relativeLineageRefs
	 *            The ObjRefs from the rootObjRef to the objRef
	 * @param relativePath
	 *            The {@link XArchADTPath} from the rootObjRef to the objRef, <code>null</code> when called initially
	 *            from {@link #addThing(List, ObjRef)}
	 * @param objRef
	 *            The objRef that is represented by the Thing in the BNA model
	 * @param evt
	 *            The event that caused the update, <code>null</code> when called initially from
	 *            {@link #addThing(List, ObjRef)}
	 * @param thing
	 *            The BNA Thing that represents the ObjRef.
	 */
	protected void updateThing(List<ObjRef> relativeLineageRefs, @Nullable String relativePath, ObjRef objRef,
			@Nullable XArchADTModelEvent evt, T thing) {
	}

	/**
	 * Updates the ObjRef to reflect information in the BNA Assembly returned from {@link #addThing(List, ObjRef)}. This
	 * will be called when the BNA Assembly's root thing or any of its parts are modified.
	 * 
	 * @param objRef
	 *            The objRef corresponding to the BNA Assembly
	 * @param thing
	 *            The BNA Assembly (or plain old Thing) that represents the ObjRef.
	 * @param relativeBNAPath
	 *            The path within the BNA Assembly to the specific thing that was modified, empty if the BNA Assembly's
	 *            root thing is modified
	 * @param evt
	 *            The original {@link BNAModelEvent} that triggered the update. Note that this event may be for a part
	 *            in the BNA Assembly and not for the BNA Assembly root thing.
	 */
	protected void storeThingData(ObjRef objRef, T thing, BNAPath relativeBNAPath, BNAModelEvent evt) {
	}
}
