package org.archstudio.xadlbna.logics.mapping;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
import org.archstudio.bna.utils.BNAPath;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTPath;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

public abstract class AbstractXADLToBNAPathLogic<T extends IThing> extends AbstractXADLToBNAThingLogic<T> {

	public interface IXADLToBNATranslator<X extends Serializable, B> {

		public B toBNAValue(X xadlValue);

		public X toXadlValue(B value);

	}

	public interface IBNAUpdater {

		public void updateBNA(ObjRef objRef, XArchADTPath xadlPath, XArchADTModelEvent evt, IThing rootThing);

	}

	public interface IXADLUpdater {

		public <ET extends IThing, EK extends IThingKey<EV>, EV> void updateXADL(IThing rootThing,
				BNAPath relativeBNAPath, BNAModelEvent<ET, EK, EV> evt, ObjRef objRef);

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
	
	@Override
	protected void updateThing(List<ObjRef> relativeLineageRefs, XArchADTPath relativePath, ObjRef objRef,
			XArchADTModelEvent evt, T thing) {
		for (IBNAUpdater xadlUpdater : bnaUpdaters) {
			xadlUpdater.updateBNA(objRef, relativePath, evt, thing);
		}
	}

	@Override
	protected <ET extends IThing, EK extends IThing.IThingKey<EV>, EV> void storeThingData(ObjRef objRef, T rootThing,
			BNAPath relativeBNAPath, BNAModelEvent<ET, EK, EV> evt) {
		for (IXADLUpdater xadlUpdater : bnaPathXADLUpdaters.get(relativeBNAPath)) {
			xadlUpdater.updateXADL(rootThing, relativeBNAPath, evt, objRef);
		}
	}

	protected <X extends Serializable, B> void syncAttribute(final String xADLAttributeName,
			@Nullable final IXADLToBNATranslator<X, B> translator, final B defaultBNAValue,
			final BNAPath targetThingPath, final IThing.IThingKey<B> thingValueKey, final boolean reverse) {
		bnaUpdaters.add(new IBNAUpdater() {

			@SuppressWarnings("unchecked")
			@Override
			public void updateBNA(ObjRef objRef, XArchADTPath xadlPath, XArchADTModelEvent evt, IThing rootThing) {
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
				@Override
				public <ET extends IThing, EK extends IThingKey<EV>, EV> void updateXADL(IThing rootThing,
						BNAPath relativeBNAPath, BNAModelEvent<ET, EK, EV> evt, ObjRef objRef) {
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

	protected <X extends Serializable, B> void syncXAttribute(final String xADLAttributeXPath,
			@Nullable final IXADLToBNATranslator<X, B> translator, final B defaultBNAValue,
			final BNAPath targetThingPath, final IThing.IThingKey<B> thingValueKey, final boolean reverse) {
		System.err.println("Warning: ignoring " + xADLAttributeXPath); // TODO: Implement xADLAttributeXPath
		//bnaUpdaters.add(new IBNAUpdater() {
		//
		//	@SuppressWarnings("unchecked")
		//	@Override
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
		//		@Override
		//		public <ET extends IThing, EK extends IThingKey<EV>, EV> void updateXADL(IThing rootThing,
		//				BNAPath relativeBNAPath, BNAModelEvent<ET, EK, EV> evt, ObjRef objRef) {
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

	protected void loadAncestor(final int ancestorIndex, final BNAPath targetThingPath,
			final IThing.IThingKey<ObjRef> thingValueKey) {

		bnaUpdaters.add(new IBNAUpdater() {

			@Override
			public void updateBNA(ObjRef objRef, XArchADTPath xadlPath, XArchADTModelEvent evt, IThing rootThing) {
				IThing targetThing = BNAPath.resolve(getBNAModel(), rootThing, targetThingPath);
				if (targetThing != null) {
					List<ObjRef> ancestorRefs = xarch.getAllAncestors(objRef);
					ObjRef ancestorRef = null;
					if (ancestorIndex < ancestorRefs.size()) {
						ancestorRef = ancestorRefs.get(ancestorIndex);
					}
					targetThing.set(thingValueKey, ancestorRef);
				}
			}
		});
	}

}
