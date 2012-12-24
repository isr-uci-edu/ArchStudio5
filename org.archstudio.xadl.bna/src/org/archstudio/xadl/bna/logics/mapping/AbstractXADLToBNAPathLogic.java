package org.archstudio.xadl.bna.logics.mapping;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IThing;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

/**
 * Synchronizes a xADL ObjRef with a single BNA Assembly (or plain Thing).
 * Changes to the xADL ObjRef and its children will be reflected in the BNA
 * Assembly, and changes to things in the BNA Assembly will be reflected in the
 * xADL ObjRef. As opposed to {@link AbstractXADLToBNAThingLogic} which
 * synchronizes ObjRefs and BNA Assemblies at a very coarse level, this class
 * breaks synchronization into little pieces--an attribute, an ancestor, etc.
 * 
 * @param <T>
 *            The type of BNA Assembly/Thing that will be created by this class
 *            to represent targeted ObjRefs, see {@link #addThing(List, ObjRef)}
 */
public abstract class AbstractXADLToBNAPathLogic<T extends IThing> extends AbstractXADLToBNAThingLogic<T> {

	/**
	 * Translates XADL attributes values (e.g., a Direction) to BNA values
	 * (e.g., a Flow) and vice versa.
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
	 * {@link #updateBNA(ObjRef, XArchADTPath, XArchADTModelEvent, IThing)} for
	 * a description.
	 */
	public abstract class IBNAUpdater {

		/**
		 * Updates the BNA Assembly/Thing from the xADL ObjRef
		 * 
		 * @see AbstractXADLToBNAThingLogic#updateThing(List, XArchADTPath,
		 *      ObjRef, XArchADTModelEvent, IThing)
		 */
		public void updateBNA(ObjRef objRef, String xadlPath, XArchADTModelEvent evt, T rootThing) {
		}

	}

	public abstract class IXADLUpdater {

		/**
		 * Updates the xADL ObjRef from the BNA Assembly/Thing
		 * 
		 * @see AbstractXADLToBNAThingLogic#storeThingData(ObjRef, IThing,
		 *      BNAPath, BNAModelEvent)
		 */
		public void updateXADL(T rootThing, BNAPath relativeBNAPath, BNAModelEvent evt, ObjRef objRef) {
		}

	}

	public AbstractXADLToBNAPathLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(xarch, rootObjRef, objRefPath);
	}

	private final Collection<IBNAUpdater> bnaUpdaters = Lists.newArrayList();
	private final Multimap<BNAPath, IXADLUpdater> bnaPathXADLUpdaters = ArrayListMultimap.create();

	protected void addBNAUpdater(IBNAUpdater bnaUpdater) {
		bnaUpdaters.add(bnaUpdater);
	}

	protected void addXADLUpdater(BNAPath bnaPath, IXADLUpdater xadlUpdater) {
		bnaPathXADLUpdaters.put(bnaPath, xadlUpdater);
	}

	/**
	 * Propagate the xADL event to all of the {@link IBNAUpdater}s registered
	 * with this class, which each do small updates.
	 * 
	 * @see AbstractXADLToBNAThingLogic#updateThing(List, XArchADTPath, ObjRef,
	 *      XArchADTModelEvent, IThing)
	 */

	protected void updateThing(List<ObjRef> relativeLineageRefs, String relativePath, ObjRef objRef,
			XArchADTModelEvent evt, T thing) {
		for (IBNAUpdater xadlUpdater : bnaUpdaters) {
			xadlUpdater.updateBNA(objRef, relativePath, evt, thing);
		}
	}

	/**
	 * Propagate the BNA event to the relevant {@link IXADLUpdater}s, each of
	 * which knows how to respond to a specific Thing within the BNA Assembly.
	 * 
	 * @see AbstractXADLToBNAThingLogic#storeThingData(ObjRef, IThing, BNAPath,
	 *      BNAModelEvent)
	 */

	protected void storeThingData(ObjRef objRef, T rootThing, BNAPath relativeBNAPath, BNAModelEvent evt) {
		for (IXADLUpdater xadlUpdater : bnaPathXADLUpdaters.get(relativeBNAPath)) {
			xadlUpdater.updateXADL(rootThing, relativeBNAPath, evt, objRef);
		}
	}

	/**
	 * Adds the appropriate updaters to synchronize a single xADL attribute or
	 * ObjRef with a BNA Thing property, possibly translating the value in the
	 * process. Cycles are prevented as described in
	 * {@link AbstractXADLToBNAThingLogic}
	 * 
	 * @param xADLAttributeName
	 *            The name of the xADL attribute to synchronize with the BNA
	 *            Thing's property
	 * @param translator
	 *            An optional translator that converts between xADL attributes
	 *            and BNA property values
	 * @param defaultBNAValue
	 *            The default BNA property value to use when the xADL attribute
	 *            is not defined
	 * @param targetThingPath
	 *            The path from the root BNA Assembly to the specific thing part
	 *            that is to store the translated property
	 * @param thingValueKey
	 *            The property name into which to store the value
	 * @param reverse
	 *            The BNA Thing's value is always updated when the xADL value is
	 *            modified, if <code>true</code> then the reverse mapping is
	 *            also performed--i.e., the xADL value is updated when the Thing
	 *            property is modified.
	 */
	protected <X extends Serializable, B> void syncValue(final String xADLAttributeName,
			@Nullable final IXADLToBNATranslator<X, B> translator, @Nullable final B defaultBNAValue,
			final BNAPath targetThingPath, final IThing.IThingKey<B> thingValueKey, final boolean reverse) {
		bnaUpdaters.add(new IBNAUpdater() {

			@SuppressWarnings("unchecked")
			public void updateBNA(ObjRef objRef, String xadlPath, XArchADTModelEvent evt, T rootThing) {

				// this maps updates from the xADL attribute to the BNA Thing's property

				IThing targetThing = BNAPath.resolve(getBNAModel(), rootThing, targetThingPath);
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
					targetThing.set(thingValueKey, thingValue);
				}
			}
		});

		if (reverse) {
			bnaPathXADLUpdaters.put(targetThingPath, new IXADLUpdater() {

				@SuppressWarnings("unchecked")
				public void updateXADL(T rootThing, BNAPath relativeBNAPath, BNAModelEvent evt, ObjRef objRef) {

					// this updates xADL attributes from the BNA Thing's property value

					if (thingValueKey.equals(evt.getThingEvent().getPropertyName())) {
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
				}
			});
		}
	}

	/**
	 * Specifies a specific value to set on all mapped things.
	 * 
	 * @param targetThingPath
	 *            The path from the root BNA Assembly to the specific thing part
	 *            that is to store the translated property
	 * @param thingValueKey
	 *            The key on which to set the value
	 * @param value
	 *            The value to set
	 */
	protected <V> void setValue(final BNAPath targetThingPath, final IThing.IThingKey<V> thingValueKey, final V value) {
		bnaUpdaters.add(new IBNAUpdater() {

			public void updateBNA(ObjRef objRef, String xadlPath, XArchADTModelEvent evt, T rootThing) {
				IThing targetThing = BNAPath.resolve(getBNAModel(), rootThing, targetThingPath);
				if (targetThing != null) {
					targetThing.set(thingValueKey, value);
				}
			}
		});
	}

	/**
	 * Sets the objRefKey property to the specified ancestor of the mapped
	 * thing.
	 * 
	 * @param targetThingPath
	 *            The path from the root BNA Assembly to the specific thing part
	 *            that is to store the translated property
	 * @param objRefKey
	 *            The key on which to set the ObjRef
	 * @param index
	 *            The ancestor index starting from the mapped objRef (&gt>=0) or
	 *            from the root ObjRef (&lt;0)
	 */
	protected void setAncestorObjRef(final BNAPath targetThingPath, final IThing.IThingKey<ObjRef> objRefKey,
			final int index) {
		bnaUpdaters.add(new IBNAUpdater() {

			public void updateBNA(ObjRef objRef, String xadlPath, XArchADTModelEvent evt, T rootThing) {
				IThing targetThing = BNAPath.resolve(getBNAModel(), rootThing, targetThingPath);
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

	protected <X extends Serializable, B> void syncXAttribute(final String xADLAttributeXPath,
			@Nullable final IXADLToBNATranslator<X, B> translator, final B defaultBNAValue,
			final BNAPath targetThingPath, final IThing.IThingKey<B> thingValueKey, final boolean reverse) {
		System.err.println("Warning: ignoring " + xADLAttributeXPath); // TODO: Implement xADLAttributeXPath
		//bnaUpdaters.add(new IBNAUpdater() {
		//
		//	@SuppressWarnings("unchecked")
		//	
		//	public void updateBNA(ObjRef objRef, XArchADTPath xadlPath, XArchADTModelEvent evt, IThing rootThing) {
		//		IThing targetThing = BNAPath.resolve(getBNAModel(), rootThing, targetThingPath);
		//		if (targetThing != null) {
		//			B thingValue;
		//			X xadlValue = (X) xarch.get(objRef, xADLAttributeName);
		//			if (xadlValue == null) {
		//				thingValue = defaultBNAValue;
		//			}
		//			else {
		//				if (translator != null) {
		//					thingValue = translator.toBNAValue(xadlValue);
		//				}
		//				else {
		//					thingValue = (B) xadlValue;
		//				}
		//			}
		//			targetThing.set(thingValueKey, thingValue);
		//		}
		//	}
		//});
		//
		//if (reverse) {
		//	bnaPathXADLUpdaters.put(targetThingPath, new IXADLUpdater() {
		//
		//		@SuppressWarnings("unchecked")
		//		
		//		public  void updateXADL(IThing rootThing,
		//				BNAPath relativeBNAPath, BNAModelEvent evt, ObjRef objRef) {
		//			if (thingValueKey.equals(evt.getThingEvent().getPropertyName())) {
		//				Object value = evt.getThingEvent().getNewPropertyValue();
		//				if (translator != null) {
		//					value = translator.toXadlValue((B) value);
		//				}
		//				if (value == null) {
		//					xarch.clear(objRef, xADLAttributeName);
		//				}
		//				else {
		//					xarch.set(objRef, xADLAttributeName, (Serializable) value);
		//				}
		//			}
		//		}
		//	});
		//}
	}

	//	/**
	//	 * Sets a property on the BNA Thing equal to the given ancestor of the thing.
	//	 * @param ancestorIndex
	//	 * @param targetThingPath
	//	 * @param thingValueKey
	//	 */
	//	
	//	protected void loadAncestor(final int ancestorIndex, final BNAPath targetThingPath,
	//			final IThing.IThingKey<ObjRef> thingValueKey) {
	//
	//		bnaUpdaters.add(new IBNAUpdater() {
	//
	//			
	//			public void updateBNA(ObjRef objRef, XArchADTPath xadlPath, XArchADTModelEvent evt, IThing rootThing) {
	//				IThing targetThing = BNAPath.resolve(getBNAModel(), rootThing, targetThingPath);
	//				if (targetThing != null) {
	//					List<ObjRef> ancestorRefs = xarch.getAllAncestors(objRef);
	//					ObjRef ancestorRef = null;
	//					if (ancestorIndex < ancestorRefs.size()) {
	//						ancestorRef = ancestorRefs.get(ancestorIndex);
	//					}
	//					targetThing.set(thingValueKey, ancestorRef);
	//				}
	//			}
	//		});
	//	}

}
