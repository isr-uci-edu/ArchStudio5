package org.archstudio.xadlbna.logics.mapping;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.annotation.Nullable;

import org.archstudio.bna.BNAModelEvent;
import org.archstudio.bna.IThing;
import org.archstudio.bna.IThing.IThingKey;
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

		public void updateBNA(//
				ObjRef objRef, XArchADTPath xadlPath, XArchADTModelEvent evt, //
				IThing rootThing);

	}

	public interface IXADLUpdater {

		public <ET extends IThing, EK extends IThingKey<EV>, EV> void updateXADL(//
				IThing rootThing, BNAPath relativeBNAPath, BNAModelEvent<ET, EK, EV> evt, //
				ObjRef objRef);

	}

	public AbstractXADLToBNAPathLogic(IXArchADT xarch, ObjRef rootObjRef, String objRefPath) {
		super(xarch, rootObjRef, objRefPath);
	}

	private final Collection<IBNAUpdater> bnaUpdaters = Lists.newArrayList();
	private final Multimap<BNAPath, IXADLUpdater> bnaPathXADLUpdaters = ArrayListMultimap.create();

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

	protected <X extends Serializable, B> void mapAttribute(final String xADLAttributeName,
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

	protected void mapReference(final String xADLReferenceName, final BNAPath targetThingPath,
			final IThing.IThingKey<ObjRef> thingValueKey) {
		bnaUpdaters.add(new IBNAUpdater() {

			@Override
			public void updateBNA(ObjRef objRef, XArchADTPath xadlPath, XArchADTModelEvent evt, IThing rootThing) {
				IThing targetThing = BNAPath.resolve(getBNAModel(), rootThing, targetThingPath);
				if (targetThing != null) {
					targetThing.set(thingValueKey, (ObjRef) xarch.get(objRef, xADLReferenceName));
				}
			}
		});

		// TODO: reverse 
	}

	protected void mapAncestor(final int ancestorIndex, final BNAPath targetThingPath,
			final IThing.IThingKey<ObjRef> thingValueKey) {
		bnaUpdaters.add(new IBNAUpdater() {

			@Override
			public void updateBNA(ObjRef objRef, XArchADTPath xadlPath, XArchADTModelEvent evt, IThing rootThing) {
				IThing targetThing = BNAPath.resolve(getBNAModel(), rootThing, targetThingPath);
				if (targetThing != null) {
					List<ObjRef> ancestorRefs = xarch.getAllAncestors(objRef);
					targetThing.set(thingValueKey,
							ancestorIndex < ancestorRefs.size() ? ancestorRefs.get(ancestorIndex) : null);
				}
			}
		});

		// TODO: reverse 
	}

	//	protected <X, B> void mapXADLAttributeToBNA(final String xADLAttributeName,
	//			final IXADLToBNAUpdater<X, B> translator, final B defaultBNAValue, final BNAPath targetThingPath,
	//			final IThing.IThingKey<B> thingValueKey) {
	//		bnaUpdaters.put(targetThingPath, new IXADLToBNAUpdater() {
	//
	//			@SuppressWarnings("unchecked")
	//			@Override
	//			public void updateToThing(IBNAModel model, ObjRef objRef, IThing rootThing, XArchADTModelEvent evt,
	//					XArchADTPath relativeSourceTargetPath) {
	//				IThing targetThing = BNAPath.resolve(model, rootThing, targetThingPath);
	//				if (targetThing != null) {
	//					B thingValue;
	//					X xadlValue = (X) xarch.get(objRef, xADLAttributeName);
	//					if (xadlValue == null) {
	//						thingValue = defaultBNAValue;
	//					}
	//					else {
	//						if (translator != null) {
	//							thingValue = translator.toBNAValue(xadlValue);
	//						}
	//						else {
	//							thingValue = (B) xadlValue;
	//						}
	//					}
	//					targetThing.set(thingValueKey, thingValue);
	//				}
	//			}
	//
	//			//			@SuppressWarnings("unchecked")
	//			//			@Override
	//			//			public void updateToThing(IBNAModel model, ObjRef objRef, IThing rootThing, XArchADTModelEvent evt,
	//			//					XArchADTPath relativeSourceTargetPath) {
	//			//				IThing targetThing = BNAPath.resolve(model, rootThing, targetThingPath);
	//			//				if (targetThing != null) {
	//			//					V value = (V) xarch.get(objRef, xADLAttributeName);
	//			//					if (value == null) {
	//			//						value = defaultXADLValue;
	//			//					}
	//			//					targetThing.set(thingKey, value);
	//			//				}
	//			//			}
	//		});
	//	}
	//
	//	@Override
	//	protected void updateThing(IBNAModel model, ObjRef objRef, List<ObjRef> relativeAncestorRefs, T thing,
	//			XArchADTModelEvent evt, XArchADTPath relativeSourceTargetPath) {
	//		if (relativeSourceTargetPath == null) {
	//			for (IMapping<T> mapping : allMappings) {
	//				mapping.updateThing(model, objRef, thing, evt, relativeSourceTargetPath);
	//			}
	//		}
	//		else {
	//			String tagsOnlyString = relativeSourceTargetPath.toTagsOnlyString();
	//			for (Map.Entry<String, Collection<IMapping<T>>> entry : mapByTagsOnlyPrefix.entrySet()) {
	//				if (tagsOnlyString.startsWith(entry.getKey())) {
	//					for (IMapping<T> mapping : entry.getValue()) {
	//						mapping.updateThing(model, objRef, thing, evt, relativeSourceTargetPath);
	//					}
	//				}
	//			}
	//		}
	//	}
	//
	//	@Override
	//	protected <ET extends IThing, EK extends IThing.IThingKey<EV>, EV> void storeThingData(IBNAModel model,
	//			ObjRef objRef, T thing, BNAModelEvent<ET, EK, EV> evt) {
	//		Collection<IMapping<T>> mappings = mapByBNAPath.get(relativeBNAPath);
	//		if (mappings != null) {
	//			for (IMapping<T> mapping : mappings) {
	//				mapping.storeThing(model, objRef, thing, evt, relativeBNAPath);
	//			}
	//		}
	//	}
	//
	//	protected void addTagsOnlyPrefixMapping(String tagsOnlyPrefix, IMapping<T> mapping) {
	//		allMappings.add(mapping);
	//		Collection<IMapping<T>> mappings = mapByTagsOnlyPrefix.get(tagsOnlyPrefix);
	//		if (mappings == null) {
	//			mapByTagsOnlyPrefix.put(tagsOnlyPrefix, mappings = new ArrayList<IMapping<T>>());
	//		}
	//		mappings.add(mapping);
	//	}
	//
	//	protected void automapSimpleValue(String elementName, Object defaultBNAValue, String partsPath,
	//			String propertyName, boolean reverse) {
	//		automapSimpleValue(elementName, "instance#Description", defaultBNAValue, partsPath, propertyName, reverse, null);
	//	}
	//
	//	protected void automapSimpleValue(final String elementName, final String elementType, final Object defaultBNAValue,
	//			final String partsPath, final String propertyName, final boolean reverse,
	//			final IValueTranslator valueTranslator) {
	//		final String[] parts = partsPath.split("\\/");
	//		IXADLToBNAMapping mapping = new IXADLToBNAMapping() {
	//
	//			public void updateAssembly(IBNAModel model, ObjRef objRef, T assembly, XArchFlatEvent evt,
	//					XArchPath relativeSourceTargetPath) {
	//				IThing thing = AssemblyUtils.getThing(assembly, parts);
	//				if (thing != null) {
	//					Object value = null;
	//					ObjRef valueRef = (ObjRef) xarch.get(objRef, elementName);
	//					if (valueRef != null) {
	//						value = xarch.get(valueRef, "Value");
	//					}
	//					if (valueTranslator != null) {
	//						value = valueTranslator.toBNAValue(value);
	//					}
	//					thing.setProperty(propertyName, value == null ? defaultBNAValue : value);
	//				}
	//			}
	//
	//			@Override
	//			public void storeAssemblyData(IBNAModel model, ObjRef objRef, T assembly, BNAModelEvent evt,
	//					BNAPath relativeBNAPath) {
	//				if (reverse) {
	//					Object value = evt.getThingEvent().getNewPropertyValue();
	//					if (valueTranslator != null) {
	//						value = valueTranslator.toXadlValue(value);
	//					}
	//					if (value == null) {
	//						ObjRef valueRef = (ObjRef) xarch.get(objRef, elementName);
	//						if (valueRef != null) {
	//							if (xarch.get(valueRef, "Value") != null) { // FIXME: xarch flat shouldn't send out an event if it's already cleared -- but it does
	//								xarch.clear(valueRef, "Value");
	//							}
	//						}
	//					}
	//					else {
	//						ObjRef valueRef = (ObjRef) xarch.get(objRef, elementName);
	//						if (valueRef == null) {
	//							ObjRef contextRef = xarch.createContext(xArchRef,
	//									XArchMetadataUtils.getTypeContext(elementType));
	//							valueRef = xarch.create(contextRef, XArchMetadataUtils.getTypeName(elementType));
	//							xarch.set(valueRef, "Value", (String) value);
	//							xarch.set(objRef, elementName, valueRef);
	//						}
	//						else {
	//							xarch.set(valueRef, "Value", (String) value);
	//						}
	//					}
	//				}
	//			}
	//		};
	//		addTagsOnlyPrefixMapping(elementName, mapping);
	//		addBNAPathMapping(new BNAPath(parts, propertyName), mapping);
	//	}
	//
	//	protected void automapXLinkToStuckPoint(final String elementName, final String partsPath,
	//			final String pointPropertyName, final boolean reverse) {
	//		final String[] parts = partsPath.split("\\/");
	//		IXADLToBNAMapping<T> mapping = new IXADLToBNAMapping<T>() {
	//
	//			public void updateAssembly(IBNAModel model, ObjRef objRef, T assembly, XArchFlatEvent evt,
	//					XArchPath relativeSourceTargetPath) {
	//				IThing thing = AssemblyUtils.getThing(assembly, parts);
	//				if (thing != null) {
	//					if (relativeSourceTargetPath == null) {
	//						thing.setProperty(MaintainStickyPointLogic.getStickyModeName(pointPropertyName),
	//								StickyMode.EDGE_FROM_CENTER);
	//					}
	//					MaintainXadlLinksLogic.updateThingIDByXArchID(xarch, (ObjRef) xarch.get(objRef, elementName),
	//							MaintainStickyPointLogic.getReferenceName(pointPropertyName), thing);
	//				}
	//			}
	//
	//			@Override
	//			public void storeAssemblyData(IBNAModel model, ObjRef objRef, T assembly, BNAModelEvent evt,
	//					BNAPath relativeBNAPath) {
	//				if (reverse) {
	//					IThing thing = AssemblyUtils.getThing(assembly, parts);
	//					if (thing != null) {
	//						setLinkEndpoint(model, MaintainStickyPointLogic.getStuckToThingId(pointPropertyName, thing),
	//								objRef, elementName);
	//					}
	//				}
	//			}
	//
	//			void setLinkEndpoint(IBNAModel model, String targetThingID, ObjRef objRef, String linkName) {
	//				String xArchID = null;
	//				if (targetThingID != null) {
	//					IAssembly assembly = AssemblyUtils.getAssemblyWithPart(model.getThing(targetThingID));
	//					if (assembly != null) {
	//						xArchID = assembly.getRootThing().getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
	//					}
	//				}
	//				if (xArchID != null) {
	//					XadlUtils.setXLink(xarch, objRef, linkName, xArchID);
	//				}
	//				else {
	//					if (xarch.get(objRef, linkName) != null) { // FIXME: xarch flat shouldn't send out an event if it's already cleared -- but it does
	//						xarch.clear(objRef, linkName);
	//					}
	//				}
	//			}
	//		};
	//		addTagsOnlyPrefixMapping(elementName, mapping);
	//		addBNAPathMapping(new BNAPath(parts, MaintainStickyPointLogic.getReferenceName(pointPropertyName)), mapping);
	//	}
	//
	//	protected void automapLinkPointsToSplineEndpoints(final String partsPath, final boolean reverse) {
	//		final String[] parts = partsPath.split("\\/");
	//		IXADLToBNAMapping<T> updateAssemblyEndpoints = new IXADLToBNAMapping<T>() {
	//
	//			public void updateAssembly(IBNAModel model, ObjRef objRef, T assembly, XArchFlatEvent evt,
	//					XArchPath relativeSourceTargetPath) {
	//				// this updates both points
	//				IThing thing = AssemblyUtils.getThing(assembly, parts);
	//				if (thing != null) {
	//
	//					ObjRef[] pointRefs = xarch.getAll(objRef, "point");
	//					if (pointRefs.length >= 1
	//							&& (relativeSourceTargetPath == null || relativeSourceTargetPath.getTagIndex(0) == 0)) {
	//						String targetId = getPointTargetId(pointRefs[0]);
	//						if (relativeSourceTargetPath == null) {
	//							thing.setProperty(
	//									MaintainStickyPointLogic.getStickyModeName(IHasEndpoints.ENDPOINT_1_PROPERTY_NAME),
	//									StickyMode.EDGE_FROM_CENTER);
	//						}
	//						MaintainXadlLinksLogic.updateThingIDByXArchID(targetId,
	//								MaintainStickyPointLogic.getReferenceName(IHasEndpoints.ENDPOINT_1_PROPERTY_NAME),
	//								thing);
	//					}
	//					if (pointRefs.length >= 2
	//							&& (relativeSourceTargetPath == null || relativeSourceTargetPath.getTagIndex(0) == 1)) {
	//						String targetId = getPointTargetId(pointRefs[1]);
	//						if (relativeSourceTargetPath == null) {
	//							thing.setProperty(
	//									MaintainStickyPointLogic.getStickyModeName(IHasEndpoints.ENDPOINT_2_PROPERTY_NAME),
	//									StickyMode.EDGE_FROM_CENTER);
	//						}
	//						MaintainXadlLinksLogic.updateThingIDByXArchID(targetId,
	//								MaintainStickyPointLogic.getReferenceName(IHasEndpoints.ENDPOINT_2_PROPERTY_NAME),
	//								thing);
	//					}
	//				}
	//			}
	//
	//			@Override
	//			public void storeAssemblyData(IBNAModel model, ObjRef objRef, T assembly, BNAModelEvent evt,
	//					BNAPath relativeBNAPath) {
	//			}
	//
	//			String getPointTargetId(ObjRef pointRef) {
	//				ObjRef linkRef = (ObjRef) xarch.get(pointRef, "anchorOnInterface");
	//				if (linkRef != null) {
	//					String href = (String) xarch.get(linkRef, "Href");
	//					if (href != null) {
	//						int poundIndex = href.indexOf('#');
	//						if (poundIndex >= 0) {
	//							return href.substring(poundIndex + 1);
	//						}
	//					}
	//				}
	//				return null;
	//			}
	//		};
	//
	//		addTagsOnlyPrefixMapping("point", updateAssemblyEndpoints);
	//
	//		if (reverse) {
	//			IXADLToBNAMapping<T> updateXadlPoints = new IXADLToBNAMapping<T>() {
	//
	//				public void updateAssembly(IBNAModel model, ObjRef objRef, T assembly, XArchFlatEvent evt,
	//						XArchPath relativeSourceTargetPath) {
	//				}
	//
	//				@Override
	//				public void storeAssemblyData(IBNAModel model, ObjRef objRef, T assembly, BNAModelEvent evt,
	//						BNAPath relativeBNAPath) {
	//					if (reverse) {
	//						IThing thing = AssemblyUtils.getThing(assembly, parts);
	//						if (thing != null) {
	//							ObjRef xArchRef = xarch.getXArch(objRef);
	//							List<ObjRef> pointRefs = new ArrayList<ObjRef>(Arrays.asList(xarch.getAll(objRef, "point")));
	//							ObjRef point1Ref = pointRefs.size() > 0 ? pointRefs.get(0) : null;
	//							if (evt == null
	//									|| point1Ref == null
	//									|| evt.getThingEvent()
	//											.getPropertyName()
	//											.equals(MaintainStickyPointLogic
	//													.getReferenceName(IHasEndpoints.ENDPOINT_1_PROPERTY_NAME))) {
	//								point1Ref = setPointTargetId(model, MaintainStickyPointLogic.getStuckToThingId(
	//										IHasEndpoints.ENDPOINT_1_PROPERTY_NAME, thing), xArchRef, point1Ref,
	//										"anchorOnInterface");
	//							}
	//
	//							ObjRef point2Ref = pointRefs.size() > 1 ? pointRefs.get(1) : null;
	//							if (evt == null
	//									|| point2Ref == null
	//									|| evt.getThingEvent()
	//											.getPropertyName()
	//											.equals(MaintainStickyPointLogic
	//													.getReferenceName(IHasEndpoints.ENDPOINT_2_PROPERTY_NAME))) {
	//								point2Ref = setPointTargetId(model, MaintainStickyPointLogic.getStuckToThingId(
	//										IHasEndpoints.ENDPOINT_2_PROPERTY_NAME, thing), xArchRef, point2Ref,
	//										"anchorOnInterface");
	//							}
	//							Set<ObjRef> pointRefsToRemove = new HashSet<ObjRef>(pointRefs);
	//							if (!pointRefsToRemove.contains(point1Ref)) {
	//								xarch.add(objRef, "point", point1Ref);
	//							}
	//							if (!pointRefsToRemove.contains(point2Ref)) {
	//								xarch.add(objRef, "point", point2Ref);
	//							}
	//							pointRefsToRemove.remove(point1Ref);
	//							pointRefsToRemove.remove(point2Ref);
	//							if (pointRefsToRemove.size() > 0) {
	//								xarch.remove(objRef, "point",
	//										pointRefsToRemove.toArray(new ObjRef[pointRefsToRemove.size()]));
	//							}
	//						}
	//					}
	//				}
	//
	//				ObjRef setPointTargetId(IBNAModel model, String targetThingID, ObjRef xArchRef, ObjRef objRef,
	//						String linkName) {
	//					String xArchID = null;
	//					if (targetThingID != null) {
	//						IAssembly assembly = AssemblyUtils.getAssemblyWithPart(model.getThing(targetThingID));
	//						if (assembly != null) {
	//							xArchID = assembly.getRootThing().getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
	//						}
	//					}
	//					if (objRef == null) {
	//						ObjRef typesContextRef = xarch.createContext(xArchRef, "types");
	//						objRef = xarch.create(typesContextRef, "Point");
	//					}
	//					if (xArchID != null) {
	//						XadlUtils.setXLink(xarch, objRef, linkName, xArchID);
	//					}
	//					else {
	//						if (xarch.get(objRef, linkName) != null) { // FIXME: xarch flat shouldn't send out an event if it's already cleared -- but it does
	//							xarch.clear(objRef, linkName);
	//						}
	//					}
	//					return objRef;
	//				}
	//			};
	//
	//			addBNAPathMapping(
	//					new BNAPath(parts,
	//							MaintainStickyPointLogic.getReferenceName(IHasEndpoints.ENDPOINT_1_PROPERTY_NAME)),
	//					updateXadlPoints);
	//			addBNAPathMapping(
	//					new BNAPath(parts,
	//							MaintainStickyPointLogic.getReferenceName(IHasEndpoints.ENDPOINT_2_PROPERTY_NAME)),
	//					updateXadlPoints);
	//		}
	//	}

}
