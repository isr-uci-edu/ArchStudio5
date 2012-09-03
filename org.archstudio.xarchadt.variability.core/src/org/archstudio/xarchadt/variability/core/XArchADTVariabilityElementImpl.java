package org.archstudio.xarchadt.variability.core;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.xml.xpath.XPathException;

import org.archstudio.xadl3.variability_3_0.AttributeChange;
import org.archstudio.xadl3.variability_3_0.Change;
import org.archstudio.xadl3.variability_3_0.ElementChange;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTPackageMetadata;
import org.archstudio.xarchadt.IXArchADTSubstitutionHint;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.Nullable;
import org.xml.sax.SAXException;


public class XArchADTVariabilityElementImpl implements IXArchADT {

	IXArchADT xarch;

	public XArchADTVariabilityElementImpl(IXArchADT xarch) {
		this.xarch = xarch;
	}

	public boolean isValidObjRef(ObjRef ref) {
		throw new UnsupportedOperationException();
	}

	public ObjRef createDocument(URI uri) {
		throw new UnsupportedOperationException();
	}

	public ObjRef createDocument(URI uri, String nsURI) {
		throw new UnsupportedOperationException();
	}

	@Nullable
	public Serializable get(ObjRef baseObjRef, String typeOfThing) {
		ElementChange elementChange = XArchADTProxy.proxy(xarch, baseObjRef);
		for (Change change : elementChange.getChange()) {
			if (typeOfThing.equals(change.getName())) {
				if (change instanceof AttributeChange) {
					return XArchADTVariabilityImpl.deserialize(((AttributeChange) change).getValue());
				}
				if (change instanceof ElementChange) {
					return XArchADTProxy.unproxy(change);
				}
				break;
			}
		}
		return null;
	}

	public ObjRef load(URI uri) throws SAXException, IOException {
		throw new UnsupportedOperationException();
	}

	public ObjRef load(URI uri, byte[] content) throws SAXException, IOException {
		throw new UnsupportedOperationException();
	}

	public void save(URI uri) throws IOException {
		throw new UnsupportedOperationException();
	}

	public void close(URI uri) {
		throw new UnsupportedOperationException();
	}

	public void add(ObjRef baseObjRef, String typeOfThing, Serializable thingToAddRef) {
		throw new UnsupportedOperationException();
	}

	public void add(ObjRef baseObjRef, String typeOfThing, Collection<? extends Serializable> thingsToAddRefs) {
		throw new UnsupportedOperationException();
	}

	public void clear(ObjRef baseObjRef, String typeOfThing) {
		throw new UnsupportedOperationException();
	}

	public void remove(ObjRef baseObjRef, String typeOfThing, Serializable valueToRemove) {
		throw new UnsupportedOperationException();
	}

	public Serializable resolve(ObjRef objRef) {
		throw new UnsupportedOperationException();
	}

	public List<Serializable> getAll(ObjRef baseObjRef, String typeOfThing) {
		throw new UnsupportedOperationException();
	}

	public void remove(ObjRef baseObjRef, String typeOfThing, Collection<? extends Serializable> thingsToRemove) {
		throw new UnsupportedOperationException();
	}

	public boolean hasAncestor(ObjRef childRef, ObjRef ancestorRef) {
		throw new UnsupportedOperationException();
	}

	public boolean isAttached(ObjRef childRef) {
		throw new UnsupportedOperationException();
	}

	public void set(ObjRef baseObjRef, String typeOfThing, @Nullable Serializable value) {
		throw new UnsupportedOperationException();
	}

	public List<ObjRef> getAllAncestors(ObjRef targetObjRef) {
		throw new UnsupportedOperationException();
	}

	public List<ObjRef> getLineage(ObjRef targetObjRef) {
		throw new UnsupportedOperationException();
	}

	public ObjRef getParent(ObjRef targetObjRef) {
		throw new UnsupportedOperationException();
	}

	public ObjRef getByID(ObjRef documentRootRef, String id) {
		throw new UnsupportedOperationException();
	}

	public ObjRef create(String nsURI, String typeOfThing) {
		throw new UnsupportedOperationException();
	}

	public void renameXArch(String oldURI, String newURI) {
		throw new UnsupportedOperationException();
	}

	public ObjRef getByID(String id) {
		throw new UnsupportedOperationException();
	}

	public ObjRef resolveHref(ObjRef documentRootRef, String href) {
		throw new UnsupportedOperationException();
	}

	public ObjRef getDocumentRootRef(ObjRef ref) {
		throw new UnsupportedOperationException();
	}

	public String getTagName(ObjRef ref) {
		throw new UnsupportedOperationException();
	}

	public String getContainingFeatureName(ObjRef ref) {
		throw new UnsupportedOperationException();
	}

	public String getTagsOnlyPathString(ObjRef ref) {
		throw new UnsupportedOperationException();
	}

	public IXArchADTPackageMetadata getPackageMetadata(String nsURI) {
		throw new UnsupportedOperationException();
	}

	public Collection<IXArchADTPackageMetadata> getAvailablePackageMetadata() {
		throw new UnsupportedOperationException();
	}

	public IXArchADTTypeMetadata getTypeMetadata(String nsURI, String typeName) {
		return xarch.getTypeMetadata(nsURI, typeName);
	}

	public IXArchADTTypeMetadata getTypeMetadata(ObjRef objRef) {
		ElementChange elementChange = XArchADTProxy.proxy(xarch, objRef);
		String[] typeParts = elementChange.getType().split("#", 2);
		return getTypeMetadata(typeParts[0], typeParts[1]);
	}

	public boolean isInstanceOf(@Nullable ObjRef baseObjRef, String sourceNsURI, String sourceTypeName) {
		IXArchADTTypeMetadata type = getTypeMetadata(baseObjRef);
		return xarch.isAssignable(sourceNsURI, sourceTypeName, type.getNsURI(), type.getTypeName());
	}

	public boolean isAssignable(String sourceNsURI, String sourceTypeName, String targetNsURI, String targetTypeName) {
		return xarch.isAssignable(sourceNsURI, sourceTypeName, targetNsURI, targetTypeName);
	}

	public List<IXArchADTSubstitutionHint> getAllSubstitutionHints() {
		throw new UnsupportedOperationException();
	}

	public List<IXArchADTSubstitutionHint> getSubstitutionHintsForSource(String sourceNsURI, String sourceTypeName) {
		throw new UnsupportedOperationException();
	}

	public List<IXArchADTSubstitutionHint> getSubstitutionHintsForTarget(String targetNsURI, String targetTypeName) {
		throw new UnsupportedOperationException();
	}

	public ObjRef getDocumentRootRef(URI uri) {
		throw new UnsupportedOperationException();
	}

	public Collection<URI> getOpenURIs() {
		throw new UnsupportedOperationException();
	}

	public URI getURI(ObjRef ref) {
		throw new UnsupportedOperationException();
	}

	public byte[] serialize(URI uri) {
		throw new UnsupportedOperationException();
	}

	public List<ObjRef> resolveObjRefs(ObjRef contextObjRef, String xPath) throws XPathException {
		throw new UnsupportedOperationException();
	}

	public List<Serializable> resolveSerializables(ObjRef contextObjRef, String xPath) throws XPathException {
		throw new UnsupportedOperationException();
	}
	
	public String getXPath(ObjRef toObjRef) {
		throw new UnsupportedOperationException();
	}

}
