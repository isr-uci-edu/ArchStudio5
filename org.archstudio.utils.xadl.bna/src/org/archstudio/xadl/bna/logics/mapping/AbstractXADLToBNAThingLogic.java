package org.archstudio.xadl.bna.logics.mapping;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.WeakHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.BNAModelEvent.EventType;
import org.archstudio.bna.IBNAModelListener;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.keys.IThingRefKey;
import org.archstudio.bna.keys.ThingKey;
import org.archstudio.bna.logics.AbstractThingLogic;
import org.archstudio.bna.logics.hints.SynchronizeHintsLogic;
import org.archstudio.bna.logics.information.ProgressLogic;
import org.archstudio.bna.logics.tracking.ThingValueTrackingLogic;
import org.archstudio.bna.utils.Assemblies;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl.IXArchRelativePathTrackerListener;
import org.archstudio.xadl.XArchRelativePathTracker;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.jdt.annotation.Nullable;
import org.eclipse.jface.operation.IRunnableWithProgress;

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

	protected static final IThingKey<AbstractXADLToBNAThingLogic<?>> MAPPING_KEY = ThingKey
			.create(AbstractXADLToBNAThingLogic.class);

	protected final ThingValueTrackingLogic valueLogic;
	protected final ProgressLogic progressLogic;
	protected final IXArchADT xarch;
	/**
	 * There are two types of updates to things that can occur: (1) those that are produced as a result of a
	 * modification to the xADL document (i.e., from this logic), and (2) those that are produced as a result of the
	 * user's actions or another logic (i.e., not from this logic). This logic should ignore thing events that are
	 * produced from this logic rather than go through the effort of trying to update the xADL document with values that
	 * were just read to produce thing changes. The approach is to keep track of the threads that produce thing updates
	 * and when thing events from those threads are received, ignore them. Thus, all updates should be performed by the
	 * {@code executor} and the {@code executorThreads} keeps track of what threads are performing the updates.
	 */
	private final ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 5, 5L, TimeUnit.SECONDS,
			new LinkedBlockingQueue<Runnable>(), new ThreadFactory() {
				@Override
				public Thread newThread(Runnable r) {
					Thread t = new Thread(r);
					t.setName(AbstractXADLToBNAThingLogic.this.getClass().toString());
					executorThreads.put(t, null);
					return t;
				}
			});
	private final WeakHashMap<Thread, Object> executorThreads = new WeakHashMap<>();
	private SubMonitor progress;
	private AtomicInteger progressAdds = new AtomicInteger();

	private String description = SystemUtils.simpleName(this.getClass().getName());
	/**
	 * The {@link XArchRelativePathTracker} used to identify the set of ObjRefs that will be mapped to BNA Assemblies
	 */
	protected final XArchRelativePathTracker tracker;

	public AbstractXADLToBNAThingLogic(IBNAWorld world, IXArchADT xarch, ObjRef rootObjRef, String relativePath) {
		super(world);
		valueLogic = logics.addThingLogic(ThingValueTrackingLogic.class);
		progressLogic = logics.addThingLogic(ProgressLogic.class);
		this.xarch = xarch;
		this.tracker = new XArchRelativePathTracker(xarch, rootObjRef, relativePath, false);
		tracker.addTrackerListener(this);
	}

	protected void setProgressInfo(String description) {
		this.description = description;
	}

	@Override
	synchronized public void init() {
		super.init();
		model.beginBulkChange();
		try {
			progressLogic.run(description, new IRunnableWithProgress() {
				@Override
				public void run(final IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
					tracker.startScanning();
					synchronized (executor) {
						// set up progress and wait for completion
						int totalWork = tracker.getAddedObjRefs().size();
						progress = SubMonitor.convert(monitor, description, totalWork);
						while (progressAdds.get() < totalWork) {
							try {
								executor.wait();
							}
							catch (InterruptedException e) {
							}
						}
					}
					model.endBulkChange();
				}
			});
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	synchronized public void dispose() {
		tracker.stopScanning();
		super.dispose();
	}

	@Override
	synchronized public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		tracker.handleXArchADTModelEvent(evt);
	}

	/**
	 * Gets the things that have been added by this logic.
	 */
	@SuppressWarnings("unchecked")
	protected Collection<T> getAddedThings() {
		return (Collection<T>) valueLogic.getThings(MAPPING_KEY, this);
	}

	/**
	 * Takes the newly added ObjRef, translates it into a BNA Assembly through a call to {@link #addThing(List, ObjRef)}
	 * and updates the BNA Assembly through a call to
	 * {@link #updateThing(List, XArchADTPath, ObjRef, XArchADTModelEvent, IThing)}
	 */

	@Override
	synchronized public void processAdd(final List<ObjRef> relLineageRefs, final ObjRef objRef) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				model.beginBulkChange();
				try {
					// start by creating and updating the thing
					T thing = addThing(relLineageRefs, objRef);
					if (thing != null) {
						updateThing(relLineageRefs, null, objRef, null, thing);
						// note which ObjRef the thing represents
						thing.set(IHasObjRef.OBJREF_KEY, objRef);
						// mark the thing as originating from, and being synchronized by this logic
						thing.set(MAPPING_KEY, AbstractXADLToBNAThingLogic.this);
						// restore hints now, so as to avoid extra processing later
						SynchronizeHintsLogic hintsLogic = logics.getThingLogic(SynchronizeHintsLogic.class);
						if (hintsLogic != null) {
							Queue<IThing> restoreHintsThings = new LinkedList<>();
							restoreHintsThings.add(thing);
							while (!restoreHintsThings.isEmpty()) {
								IThing restoreHintsThing = restoreHintsThings.poll();
								restoreHintsThings.addAll(Assemblies.getParts(model, restoreHintsThing).values());
								hintsLogic.restoreHints(restoreHintsThing);
							}
						}
					}
				}
				finally {
					model.endBulkChange();
					progressAdds.incrementAndGet();
					if (progress != null) {
						synchronized (executor) {
							if (progress != null) {
								progress.worked(1);
							}
							executor.notifyAll();
						}
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
	synchronized public void processUpdate(final List<ObjRef> relLineageRefs, final String relPath,
			final ObjRef objRef, final XArchADTModelEvent evt) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				model.beginBulkChange();
				try {
					for (IThing t : valueLogic.getThings(IHasObjRef.OBJREF_KEY, objRef, MAPPING_KEY,
							AbstractXADLToBNAThingLogic.this)) {
						updateThing(relLineageRefs, relPath, objRef, evt, (T) t);
					}
				}
				finally {
					model.endBulkChange();
				}
			}
		});
	}

	/**
	 * Removes all BNA Assemblies that were created by this logic and correspond to the removed ObjRef
	 */

	@Override
	synchronized public void processRemove(final List<ObjRef> relLineageRefs, final ObjRef objRef) {
		executor.execute(new Runnable() {
			@Override
			public void run() {
				model.beginBulkChange();
				try {
					for (IThing t : valueLogic.getThings(IHasObjRef.OBJREF_KEY, objRef, MAPPING_KEY,
							AbstractXADLToBNAThingLogic.this)) {
						Assemblies.removeRootAndParts(model, t);
					}
				}
				finally {
					model.endBulkChange();
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
	synchronized public void bnaModelChanged(final BNAModelEvent evt) {
		if (!executorThreads.containsKey(evt.getThread())) {
			if (evt.getEventType() == EventType.THING_CHANGED) {
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
