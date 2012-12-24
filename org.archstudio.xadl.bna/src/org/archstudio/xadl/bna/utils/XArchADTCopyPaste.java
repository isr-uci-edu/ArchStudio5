package org.archstudio.xadl.bna.utils;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

public class XArchADTCopyPaste {

	/**
	 * Performs a deep clone of an ObjRef, keeping references to other ObjRefs,
	 * but not cloning the referenced ObjRefs. For a link this would mean that
	 * the cloned Link still points to the same interfaces, but does not clone
	 * the interfaces themselves.
	 */
	protected ObjRef clone(IXArchADT xarch, ObjRef objRef, Map<ObjRef, ObjRef> oldNewRefs) {
		IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(objRef);
		ObjRef cloneRef = xarch.create(typeMetadata.getNsURI(), typeMetadata.getTypeName());
		oldNewRefs.put(objRef, cloneRef);
		for (IXArchADTFeature feature : typeMetadata.getFeatures().values()) {
			switch (feature.getType()) {
			case ATTRIBUTE:
				xarch.set(cloneRef, feature.getName(), xarch.get(objRef, feature.getName()));
				break;
			case ELEMENT_SINGLE:
				Serializable s = xarch.get(objRef, feature.getName());
				if (s instanceof ObjRef && !feature.isReference()) {
					s = clone(xarch, (ObjRef) s, oldNewRefs);
				}
				xarch.set(cloneRef, feature.getName(), s);
				break;
			case ELEMENT_MULTIPLE:
				for (Serializable m : xarch.getAll(objRef, feature.getName())) {
					if (m instanceof ObjRef && !feature.isReference()) {
						m = clone(xarch, (ObjRef) m, oldNewRefs);
					}
					xarch.add(cloneRef, feature.getName(), m);
				}
				break;
			}
		}
		return cloneRef;
	}

	/**
	 * Goes through the ObjRef and replaces all IDs with new, unique
	 * identifiers. The goal is for the result to be inserted into a document
	 * without any ID conflicts.
	 */
	protected void randomizeIDs(IXArchADT xarch, ObjRef objRef) {
		IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(objRef);
		for (IXArchADTFeature feature : typeMetadata.getFeatures().values()) {
			switch (feature.getType()) {
			case ATTRIBUTE:
				if ("id".equals(feature.getName())) {
					xarch.set(objRef, feature.getName(), UIDGenerator.generateUID());
				}
				break;
			case ELEMENT_SINGLE:
				Serializable s = xarch.get(objRef, feature.getName());
				if (s instanceof ObjRef && !feature.isReference()) {
					randomizeIDs(xarch, (ObjRef) s);
				}
				break;
			case ELEMENT_MULTIPLE:
				for (Serializable m : xarch.getAll(objRef, feature.getName())) {
					if (m instanceof ObjRef && !feature.isReference()) {
						randomizeIDs(xarch, (ObjRef) m);
					}
				}
				break;
			}
		}
	}

	/**
	 * Goes through the Objref and replaces all references to old objRefs with
	 * references to the new objRefs.
	 */
	protected void updateReferences(IXArchADT xarch, ObjRef objRef, Map<ObjRef, ObjRef> oldNewRefs) {
		IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(objRef);
		for (IXArchADTFeature feature : typeMetadata.getFeatures().values()) {
			switch (feature.getType()) {
			case ATTRIBUTE:
				if ("id".equals(feature.getName())) {
					xarch.set(objRef, feature.getName(), UIDGenerator.generateUID());
				}
				break;
			case ELEMENT_SINGLE:
				Serializable s = xarch.get(objRef, feature.getName());
				if (s instanceof ObjRef && feature.isReference() && oldNewRefs.containsKey(s)) {
					xarch.set(objRef, feature.getName(), oldNewRefs.get(s));
				}
				break;
			case ELEMENT_MULTIPLE:
				for (Serializable m : xarch.getAll(objRef, feature.getName())) {
					if (m instanceof ObjRef && feature.isReference() && oldNewRefs.containsKey(m)) {
						xarch.remove(objRef, feature.getName(), m);
						xarch.add(objRef, feature.getName(), oldNewRefs.get(m));
					}
				}
				break;
			}
		}
	}

	Multimap<String, ObjRef> featureToCopiedObjRefs = ArrayListMultimap.create();

	/**
	 * Clones and copies the set of ObjRefs so that they can be pasted into a
	 * document later.
	 */
	public void copy(IXArchADT xarch, Iterable<ObjRef> objRefs) {
		featureToCopiedObjRefs.clear();
		Map<ObjRef, ObjRef> oldNewRefs = Maps.newHashMap();
		for (ObjRef objRef : objRefs) {
			featureToCopiedObjRefs.put(xarch.getContainingFeatureName(objRef), clone(xarch, objRef, oldNewRefs));
		}
		for (ObjRef objRef : oldNewRefs.values()) {
			updateReferences(xarch, objRef, oldNewRefs);
		}
	}

	/**
	 * Pastes the copied ObjRefs into a document after cloning them and
	 * randomizing their IDs so that there are no ID conflicts.
	 */
	public Collection<ObjRef> paste(IXArchADT xarch, ObjRef rootRef) {
		IXArchADTTypeMetadata rootType = xarch.getTypeMetadata(rootRef);
		Map<ObjRef, ObjRef> oldNewRefs = Maps.newHashMap();
		for (Entry<String, ObjRef> e : featureToCopiedObjRefs.entries()) {
			ObjRef objRef = clone(xarch, e.getValue(), oldNewRefs);
			randomizeIDs(xarch, objRef);

			IXArchADTFeature feature = rootType.getFeatures().get(e.getKey());
			if (feature == null) {
				continue;
			}

			if (!xarch.isInstanceOf(objRef, feature.getNsURI(), feature.getTypeName())) {
				continue;
			}

			switch (feature.getType()) {
			case ATTRIBUTE:
				throw new IllegalArgumentException();
			case ELEMENT_SINGLE:
				xarch.set(rootRef, feature.getName(), objRef);
				break;
			case ELEMENT_MULTIPLE:
				xarch.add(rootRef, feature.getName(), objRef);
			}
		}
		for (ObjRef objRef : oldNewRefs.values()) {
			updateReferences(xarch, objRef, oldNewRefs);
		}

		return oldNewRefs.values();
	}
}
