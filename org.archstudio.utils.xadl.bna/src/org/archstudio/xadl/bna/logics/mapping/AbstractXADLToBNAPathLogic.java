package org.archstudio.xadl.bna.logics.mapping;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IBNAWorld;
import org.archstudio.bna.IThing;
import org.archstudio.bna.keys.IThingKey;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.bna.utils.BNAPathKey;
import org.archstudio.bna.utils.BNAUtils;
import org.archstudio.sysutils.Finally;
import org.archstudio.xadl.IXArchRelativePathTrackerListener;
import org.archstudio.xadl.XArchRelativePathTracker;
import org.archstudio.xadl.bna.facets.IHasObjRef;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.eclipse.jdt.annotation.Nullable;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

/**
 * Synchronizes a xADL ObjRef with a single BNA Assembly (or plain Thing). Changes to the xADL ObjRef and its children
 * will be reflected in the BNA Assembly, and changes to things in the BNA Assembly will be reflected in the xADL
 * ObjRef. As opposed to {@link AbstractXADLToBNAThingLogic} which synchronizes ObjRefs and BNA Assemblies at a very
 * coarse level, this class breaks synchronization into little pieces--an attribute, an ancestor, etc.
 *
 * @param <T>
 *            The type of BNA Assembly/Thing that will be created by this class to represent targeted ObjRefs, see
 *            {@link #addThing(List, ObjRef)}
 */
public abstract class AbstractXADLToBNAPathLogic<T extends IThing> extends AbstractXADLToBNAThingLogic<T> {

	/**
	 * Translates XADL attribute values (e.g., a Direction) to BNA values (e.g., a Flow) and vice versa.
	 *
	 * @param <X>
	 *            the xADL type
	 * @param <B>
	 *            the BNA type
	 */
	public static interface IXADLToBNATranslator<X extends Serializable, B> {

		public B toBNAValue(X xadlValue);

		public X toXadlValue(B value);

	}

	/**
	 * Updates the BNA Assembly/Thing from the xADL ObjRef, see
	 * {@link #updateBNA(ObjRef, String, XArchADTModelEvent, IThing)} for a description.
	 */
	public abstract class IBNAUpdater {

		/**
		 * Updates the BNA Assembly/Thing from the xADL ObjRef
		 *
		 * @see AbstractXADLToBNAThingLogic#updateThing(List, ObjRef, XArchADTModelEvent, IThing)
		 */
		public void updateBNA(ObjRef objRef, String xadlPath, XArchADTModelEvent evt, T rootThing) {
		}

	}

	public abstract class IXADLUpdater {

		/**
		 * Updates the xADL ObjRef from the BNA Assembly/Thing
		 *
		 * @see AbstractXADLToBNAThingLogic#storeThingData(ObjRef, IThing, BNAPath, BNAModelEvent)
		 */
		public void updateXADL(T rootThing, BNAPath relativeBNAPath, BNAModelEvent evt, ObjRef objRef) {
		}

	}

	public AbstractXADLToBNAPathLogic(IBNAWorld world, IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(world, xarch, rootObjRef, objRefPath);
	}

	private final Multimap<String, IBNAUpdater> xADLPathBNAUpdaters = ArrayListMultimap.create();
	private final Map<XArchRelativePathTracker, IBNAUpdater> xADLXPathBNAUpdaters = Maps.newHashMap();
	private final Multimap<BNAPathKey, IXADLUpdater> bnaPathXADLUpdaters = ArrayListMultimap.create();

	protected void addBNAUpdater(String xADLPath, IBNAUpdater bnaUpdater) {
		xADLPathBNAUpdaters.put(xADLPath, bnaUpdater);
	}

	protected void addXADLUpdater(BNAPath bnaPath, IThingKey<?> key, IXADLUpdater xadlUpdater) {
		bnaPathXADLUpdaters.put(BNAPathKey.create(bnaPath, key), xadlUpdater);
	}

	protected void addXPathBNAUpdater(final String xADLXPath, final IBNAUpdater bnaUpdater) {
		XArchRelativePathTracker subTracker = new XArchRelativePathTracker(xarch, tracker.getRootObjRef(),
				tracker.getXPath() + "/" + xADLXPath, false);
		subTracker.addTrackerListener(new IXArchRelativePathTrackerListener() {
			@Override
			public void processUpdate(List<ObjRef> descendantRefs, ObjRef modifiedRef, XArchADTModelEvent relativeEvt) {
				fireUpdate(descendantRefs, relativeEvt);
			}

			@Override
			public void processRemove(List<ObjRef> descendantRefs, ObjRef removedRef) {
				fireUpdate(descendantRefs, null);
			}

			@Override
			public void processAdd(List<ObjRef> descendantRefs, ObjRef addedRef) {
				fireUpdate(descendantRefs, null);
			}

			@SuppressWarnings("unchecked")
			private void fireUpdate(List<ObjRef> descendantRefs, XArchADTModelEvent relativeEvt) {
				try (Finally lock = BNAUtils.lock()) {
					ObjRef objRef = descendantRefs.get(tracker.getNumXPathSegments());
					for (IThing t : valueLogic.getThings(IHasObjRef.OBJREF_KEY, objRef, MAPPING_KEY,
							AbstractXADLToBNAPathLogic.this)) {
						bnaUpdater.updateBNA(objRef, xADLXPath, relativeEvt, (T) t);
					}
				}
			}
		});
		xADLXPathBNAUpdaters.put(subTracker, bnaUpdater);
	}

	@Override
	public void init() {
		super.init();
		for (XArchRelativePathTracker subTracker : xADLXPathBNAUpdaters.keySet()) {
			subTracker.startScanning();
		}
	}

	@Override
	public void handleXArchADTModelEvent(XArchADTModelEvent evt) {
		super.handleXArchADTModelEvent(evt);
		for (XArchRelativePathTracker subTracker : xADLXPathBNAUpdaters.keySet()) {
			subTracker.handleXArchADTModelEvent(evt);
		}
	}

	/**
	 * Propagate the xADL event to all of the {@link IBNAUpdater}s registered with this class, which each do small
	 * updates.
	 *
	 * @see AbstractXADLToBNAThingLogic#updateThing(List, ObjRef, XArchADTModelEvent, IThing)
	 */

	@Override
	protected void updateThing(List<ObjRef> descendantRefs, ObjRef objRef, XArchADTModelEvent evt, T thing) {
		if (evt == null) {
			for (IBNAUpdater bnaUpdater : xADLPathBNAUpdaters.values()) {
				bnaUpdater.updateBNA(objRef, null, evt, thing);
			}
			for (IBNAUpdater bnaUpdater : xADLXPathBNAUpdaters.values()) {
				bnaUpdater.updateBNA(objRef, null, evt, thing);
			}
		}
		else {
			String path = evt.getSourcePath() + "/" + evt.getFeatureName() + "/";
			if (path.charAt(0) == '/') {
				path = path.substring(1);
			}
			while (path.length() > 0) {
				int lastDelimiter = path.lastIndexOf('/');
				if (lastDelimiter > 0) {
					path = path.substring(0, lastDelimiter);
				}
				else {
					path = "";
				}
				for (IBNAUpdater bnaUpdater : xADLPathBNAUpdaters.get(path)) {
					bnaUpdater.updateBNA(objRef, path, evt, thing);
				}
			}
		}
	}

	/**
	 * Propagate the BNA event to the relevant {@link IXADLUpdater}s, each of which knows how to respond to a specific
	 * Thing within the BNA Assembly.
	 *
	 * @see AbstractXADLToBNAThingLogic#storeThingData(ObjRef, IThing, BNAPath, BNAModelEvent)
	 */

	@Override
	protected void storeThingData(ObjRef objRef, T rootThing, BNAPath relativeBNAPath, BNAModelEvent evt) {
		for (IXADLUpdater xadlUpdater : bnaPathXADLUpdaters
				.get(BNAPathKey.create(relativeBNAPath, evt.getThingEvent().getPropertyName()))) {
			xadlUpdater.updateXADL(rootThing, relativeBNAPath, evt, objRef);
		}
	}

	/**
	 * Adds the appropriate updaters to synchronize a single xADL attribute or ObjRef with a BNA Thing property,
	 * possibly translating the value in the process. Cycles are prevented as described in
	 * {@link AbstractXADLToBNAThingLogic}
	 *
	 * @param xADLAttributeName
	 *            The name of the xADL attribute to synchronize with the BNA Thing's property
	 * @param translator
	 *            An optional translator that converts between xADL attributes and BNA property values
	 * @param defaultBNAValue
	 *            The default BNA property value to use when the xADL attribute is not defined
	 * @param targetThingPath
	 *            The path from the root BNA Assembly to the specific thing part that is to store the translated
	 *            property
	 * @param thingValueKey
	 *            The property name into which to store the value
	 * @param reverse
	 *            The BNA Thing's value is always updated when the xADL value is modified, if <code>true</code> then the
	 *            reverse mapping is also performed--i.e., the xADL value is updated when the Thing property is
	 *            modified.
	 */
	protected <X extends Serializable, B> void syncValue(final String xADLAttributeName,
			@Nullable final IXADLToBNATranslator<X, B> translator, @Nullable final B defaultBNAValue,
			final BNAPath targetThingPath, final IThingKey<B> thingValueKey, final boolean reverse) {
		xADLPathBNAUpdaters.put(xADLAttributeName, new IBNAUpdater() {

			@Override
			@SuppressWarnings("unchecked")
			public void updateBNA(ObjRef objRef, String xadlPath, XArchADTModelEvent evt, T rootThing) {

				// this maps updates from the xADL attribute to the BNA Thing's property

				IThing targetThing = BNAPath.resolve(model, rootThing, targetThingPath);
				if (targetThing != null) {
					B thingValue;
					X xadlValue = (X) xarch.get(objRef, xADLAttributeName);
					if (xadlValue == null) {
						thingValue = defaultBNAValue;
					}
					else {
						if (translator != null) {
							thingValue = translator.toBNAValue(xadlValue);
						}
						else {
							thingValue = (B) xadlValue;
						}
					}
					if (thingValue != null) {
						targetThing.set(thingValueKey, thingValue);
					}
					else {
						targetThing.remove(thingValueKey);
					}
				}
			}
		});

		if (reverse) {
			bnaPathXADLUpdaters.put(BNAPathKey.create(targetThingPath, thingValueKey), new IXADLUpdater() {

				@Override
				@SuppressWarnings("unchecked")
				public void updateXADL(T rootThing, BNAPath relativeBNAPath, BNAModelEvent evt, ObjRef objRef) {

					// this updates xADL attributes from the BNA Thing's property value

					Object value = evt.getThingEvent().getNewPropertyValue();
					if (translator != null) {
						value = translator.toXadlValue((B) value);
					}
					if (value == null) {
						xarch.clear(objRef, xADLAttributeName);
					}
					else {
						xarch.set(objRef, xADLAttributeName, (Serializable) value);
					}
				}
			});
		}
	}

	/**
	 * Specifies a specific value to set on all mapped things.
	 *
	 * @param targetThingPath
	 *            The path from the root BNA Assembly to the specific thing part that is to store the translated
	 *            property
	 * @param thingValueKey
	 *            The key on which to set the value
	 * @param value
	 *            The value to set
	 */
	protected <V> void setValue(final BNAPath targetThingPath, final IThingKey<V> thingValueKey, final V value) {
		xADLPathBNAUpdaters.put("", new IBNAUpdater() {

			@Override
			public void updateBNA(ObjRef objRef, String xadlPath, XArchADTModelEvent evt, T rootThing) {
				IThing targetThing = BNAPath.resolve(model, rootThing, targetThingPath);
				if (targetThing != null) {
					targetThing.set(thingValueKey, value);
				}
			}
		});
	}

	/**
	 * Sets the objRefKey property to the specified ancestor of the mapped thing.
	 *
	 * @param targetThingPath
	 *            The path from the root BNA Assembly to the specific thing part that is to store the translated
	 *            property
	 * @param objRefKey
	 *            The key on which to set the ObjRef
	 * @param index
	 *            The ancestor index starting from the mapped objRef (&gt>=0) or from the root ObjRef (&lt;0)
	 */
	protected void setAncestorObjRef(final BNAPath targetThingPath, final IThingKey<ObjRef> objRefKey,
			final int index) {
		xADLPathBNAUpdaters.put("", new IBNAUpdater() {

			@Override
			public void updateBNA(ObjRef objRef, String xadlPath, XArchADTModelEvent evt, T rootThing) {
				IThing targetThing = BNAPath.resolve(model, rootThing, targetThingPath);
				if (targetThing != null) {
					List<ObjRef> ancestors = xarch.getAllAncestors(objRef);
					int actualIndex = index;
					if (actualIndex < 0) {
						ancestors = Lists.reverse(ancestors);
						actualIndex = -actualIndex - 1;
					}
					targetThing.set(objRefKey, ancestors.get(actualIndex));
				}
			}
		});
	}
}
