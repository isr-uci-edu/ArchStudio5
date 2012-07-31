package org.archstudio.eclipse.ui;

import java.io.Serializable;
import java.util.Map.Entry;

import org.archstudio.sysutils.UIDGenerator;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class XArchADTCopyPaste {

	/**
	 * Performs a deep clone of an ObjRef, keeping references to other ObjRefs,
	 * but not cloning the referenced ObjRefs. For a link this would mean that
	 * the cloned Link still points to the same interfaces, but does not clone
	 * the interfaces themselves.
	 */
	protected ObjRef clone(IXArchADT xarch, ObjRef objRef) {
		IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(objRef);
		ObjRef cloneRef = xarch.create(typeMetadata.getNsURI(), typeMetadata.getTypeName());
		for (IXArchADTFeature feature : typeMetadata.getFeatures().values()) {
			switch (feature.getType()) {
			case ATTRIBUTE:
				xarch.set(cloneRef, feature.getName(), xarch.get(objRef, feature.getName()));
				break;
			case ELEMENT_SINGLE:
				Serializable s = xarch.get(objRef, feature.getName());
				if (s instanceof ObjRef && !feature.isReference())
					s = clone(xarch, (ObjRef) s);
				xarch.set(cloneRef, feature.getName(), s);
				break;
			case ELEMENT_MULTIPLE:
				for (Serializable m : xarch.getAll(objRef, feature.getName())) {
					if (m instanceof ObjRef && !feature.isReference())
						m = clone(xarch, (ObjRef) m);
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

	Multimap<String, ObjRef> featureToCopiedObjRefs = ArrayListMultimap.create();

	/**
	 * Clones and copies the set of ObjRefs so that they can be pasted into a
	 * document later.
	 */
	public void copy(IXArchADT xarch, Iterable<ObjRef> objRefs) {
		featureToCopiedObjRefs.clear();
		for (ObjRef objRef : objRefs) {
			featureToCopiedObjRefs.put(xarch.getContainingFeatureName(objRef), clone(xarch, objRef));
		}
	}

	/**
	 * Pastes the copied ObjRefs into a document after cloning them and
	 * randomizing their IDs so that there are no ID conflicts.
	 */
	public void paste(IXArchADT xarch, ObjRef rootRef) {
		IXArchADTTypeMetadata rootType = xarch.getTypeMetadata(rootRef);
		for (Entry<String, ObjRef> e : featureToCopiedObjRefs.entries()) {
			ObjRef objRef = clone(xarch, e.getValue());
			randomizeIDs(xarch, objRef);

			IXArchADTFeature feature = rootType.getFeatures().get(e.getKey());
			if (feature == null)
				continue;

			if (!xarch.isInstanceOf(objRef, feature.getNsURI(), feature.getTypeName()))
				continue;

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
	}
}
