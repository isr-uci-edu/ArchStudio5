package org.archstudio.xarchadt;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import javax.xml.xpath.XPathException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;

@NonNullByDefault
public interface IXArchADTQuery {
	/**
	 * Determines if a given ObjRef is valid, that is, if there is an object associated with it.
	 * 
	 * @param ref
	 *            ObjRef to check.
	 * @return <CODE>true</CODE> if there is an associated object, <CODE>false</CODE> otherwise.
	 */
	public boolean isValidObjRef(ObjRef ref);

	/**
	 * Gets a reference to a child or a single value from an xArch element. Roughly equivalent to:
	 * baseObject.get[TypeOfThing](); So, if the object referred to by baseObjRef implements a method called
	 * <CODE>IDescription getDescription();</CODE> then that would be called here as:
	 * <CODE>ObjRef descriptionRef = (ObjRef)get(baseObjRef, "Description");</CODE> where baseObjRef is a reference to
	 * the base object, and the typeOfThing is shown as "Description".
	 * 
	 * @param baseObjRef
	 *            Reference to a base element containing an <CODE>get[typeOfThing]</CODE> method.
	 * @param typeOfThing
	 *            A string containing the type of thing to get. For instance, if the object referred to by baseObjRef
	 *            contains a method called <CODE>getDescription</CODE>, then typeOfThing would be "Description".
	 * @return A <CODE>Object</CODE> referring to the object gotten if the object returned is an xArch element;
	 *         otherwise a {@link Serializable} object. For instance, if <CODE>typeOfThing</CODE> refers to an attribute
	 *         or is "Value" (when <CODE>baseObjRef</CODE> refers to a simple type) then this function will return a
	 *         string.
	 */
	@Nullable
	public Serializable get(ObjRef baseObjRef, String typeOfThing);

	///**
	// * Gets a reference to a child from an xArch element, given the child's ID. Roughly equivalent to:
	// * baseObject.get[TypeOfThing](id); So, if the object referred to by baseObjRef implements a method called
	// * <CODE>IDescription getDescription(String id);</CODE> then that would be called here as:
	// * <CODE>ObjRef descriptionRef = get(baseObjRef, "Description", id);</CODE> where baseObjRef is a reference to the
	// * base object, and the typeOfThing is shown as "Description". ID remains a string.
	// * 
	// * @param baseObjRef
	// *            Reference to a base element containing an <CODE>get[typeOfThing]</CODE> method.
	// * @param typeOfThing
	// *            A string containing the type of thing to get. For instance, if the object referred to by baseObjRef
	// *            contains a method called <CODE>getDescription</CODE>, then typeOfThing would be "Description".
	// * @param id
	// *            Identifier of thing to get.
	// * @return An <CODE>ObjRef</CODE> referring to the object gotten.
	// */
	//public ObjRef get(ObjRef baseObjRef, String typeOfThing, String id);

	///**
	// * Gets a set of references to a set of children from an xArch element, given the children's IDs. Roughly equivalent
	// * to: baseObject.get[TypeOfThing]s(ids); So, if the object referred to by baseObjRef implements a method called
	// * <CODE>Collection getDescriptions(String[] id);</CODE> then that would be called here as:
	// * <CODE>ObjRef[] descriptionRefs = get(baseObjRef, "Description", ids);</CODE> where baseObjRef is a reference to
	// * the base object, and the typeOfThing is shown as "Description". IDs remains strings.
	// * 
	// * @param baseObjRef
	// *            Reference to a base element containing an <CODE>get[typeOfThing]s</CODE> method.
	// * @param typeOfThing
	// *            A string containing the type of thing to get. For instance, if the object referred to by baseObjRef
	// *            contains a method called <CODE>getDescriptions</CODE>, then typeOfThing would be "Description".
	// * @param ids
	// *            Identifiers of things to get.
	// * @return An array of <CODE>ObjRef</CODE>s referring to the objects gotten.
	// */
	//public List<ObjRef> getAll(ObjRef baseObjRef, String typeOfThing, List<String> ids);

	@Nullable
	public Serializable resolve(ObjRef objRef);

	/**
	 * Gets a set of references to a set of children from an xArch element. Roughly equivalent to:
	 * baseObject.getAll[TypeOfThing]s(ids); So, if the object referred to by baseObjRef implements a method called
	 * <CODE>Collection getAllDescriptions();</CODE> then that would be called here as:
	 * <CODE>ObjRef[] descriptionRefs = getAll(baseObjRef, "Description");</CODE> where baseObjRef is a reference to the
	 * base object, and the typeOfThing is shown as "Description".
	 * 
	 * @param baseObjRef
	 *            Reference to a base element containing an <CODE>getAll[typeOfThing]s</CODE> method.
	 * @param typeOfThing
	 *            A string containing the type of thing to get. For instance, if the object referred to by baseObjRef
	 *            contains a method called <CODE>getAllDescriptions</CODE>, then typeOfThing would be "Description".
	 * @return An array of <CODE>ObjRef</CODE>s referring to the objects gotten.
	 */
	public List<Serializable> getAll(ObjRef baseObjRef, String typeOfThing);

	/**
	 * Determines if one node in the XML tree is an ancestor of another. This method is more efficient than using the
	 * output of <CODE>getAllAncestors(...)</CODE>, since <CODE>getAllAncestors(...)</CODE> makes wrapper objects for
	 * each ancestor, and this one just looks at the underlying XML nodes.
	 * 
	 * @param childRef
	 *            The potential child node.
	 * @param ancestorRef
	 *            The potential ancestor node.
	 * @return <CODE>true</CODE> if the node referred to by <CODE>ancestorRef</CODE> is an ancestor of the node referred
	 *         to by <CODE>childRef</CODE>, <CODE>false</CODE> otherwise.
	 */
	public boolean hasAncestor(ObjRef childRef, ObjRef ancestorRef);

	/**
	 * Determines if a node in the XML tree is attached to the root.
	 * 
	 * @param childRef
	 *            The node to check.
	 * @return <CODE>true</CODE> if the node referred to by <CODE>childRef</CODE> is attached to the root node of the
	 *         document; (i.e. the root node is an ancestor of the node), <CODE>false</CODE> otherwise.
	 */
	public boolean isAttached(ObjRef childRef);

	/**
	 * Gets all the ancestors (in the XML tree) of a given target object.
	 * 
	 * @param targetObjRef
	 *            Reference to target object.
	 * @return Array of references to ancestors, in order starting with (and including) the target object, then its
	 *         parent, then its parent's parent, etc.
	 */
	public List<ObjRef> getAllAncestors(ObjRef targetObjRef);

	public List<ObjRef> getLineage(ObjRef targetObjRef);

	/**
	 * Gets the parent (in the XML tree) of a given target object.
	 * 
	 * @param targetObjRef
	 *            Reference to target object.
	 * @return A reference to its parent in the XML tree, or <CODE>null</CODE> if it has none.
	 */
	@Nullable
	public ObjRef getParent(ObjRef targetObjRef);

	///**
	// * Gets the <CODE>XArchADTPath</CODE> of the given element.
	// * 
	// * @param ref
	// *            Reference to the element whose path you want to get.
	// * @return <CODE>XArchADTPath</CODE> to that element.
	// */
	//public XArchADTPath getXArchADTPath(ObjRef ref);

	/**
	 * Gets an element by its ID within a given xArch tree. If no such element exists, returns <CODE>null</CODE>.
	 * 
	 * @param documentRootRef
	 *            Reference to the IXArch object that is the root of the tree to search.
	 * @param id
	 *            The ID to search for.
	 * @return reference to the object, or <CODE>null</CODE> if no such object exists.
	 * @exception IllegalArgumentException
	 *                if <CODE>documentRootRef</CODE> is invalid.
	 */
	@Nullable
	public ObjRef getByID(ObjRef documentRootRef, String id);

	/**
	 * Gets an element by its ID within ANY OPEN xArch tree. If no such element exists, returns <CODE>null</CODE>.
	 * 
	 * @param id
	 *            The ID to search for.
	 * @return reference to the object, or <CODE>null</CODE> if no such object exists.
	 */
	@Nullable
	public ObjRef getByID(String id);

	/**
	 * Resolves an href, as might be found in an XLink.
	 * 
	 * @param documentRootRef
	 *            Reference to the IXArch object that provides the context in which the href exists.
	 * @param href
	 *            The href to resolve. May be local or remote. <I>Note:</I> For this version of the library, hrefs must
	 *            be in the form <CODE>#id</CODE> or <CODE>http://....#id</CODE>
	 * @return Reference to the referenced (by the href) object, or <CODE>null</CODE>.
	 */
	@Nullable
	public ObjRef resolveHref(ObjRef documentRootRef, String href);

	//	/**
	//	 * Resolves an <CODE>XArchADTPath</CODE> in the context of the given
	//	 * document (in terms of an <CODE>ObjRef</CODE> to the top level xArch
	//	 * element of a document).
	//	 * 
	//	 * @param documentRootRef
	//	 *            Reference to the top-level element of the document within
	//	 *            which to resolve the <CODE>XArchADTPath</CODE>.
	//	 * @param XArchADTPath
	//	 *            <CODE>XArchADTPath</CODE> to resolve.
	//	 * @return <CODE>ObjRef</CODE> to the referenced element, or
	//	 *         <CODE>null</CODE> if the path cannot be resolved
	//	 */
	//	public ObjRef resolveXArchADTPath(ObjRef documentRootRef, XArchADTPath XArchADTPath);

	@Nullable
	public ObjRef getDocumentRootRef(ObjRef ref);

	@Nullable
	public String getTagName(ObjRef ref);

	@Nullable
	public String getContainingFeatureName(ObjRef ref);

	public String getTagsOnlyPathString(ObjRef ref);

	public IXArchADTPackageMetadata getPackageMetadata(String nsURI);

	public Collection<IXArchADTPackageMetadata> getAvailablePackageMetadata();

	public IXArchADTTypeMetadata getTypeMetadata(String nsURI, String typeName);

	public IXArchADTTypeMetadata getTypeMetadata(ObjRef objRef);

	public boolean isInstanceOf(@Nullable ObjRef baseObjRef, String sourceNsURI, String sourceTypeName);

	/**
	 * Determines if an object of the target type can be assigned to a variable of the source type. In other words,
	 * determines if the source type is the same as, or a supertype or superinterface of, the target type.
	 * 
	 * @param sourceNsURI
	 *            source namespace URI
	 * @param sourceTypeName
	 *            source type name
	 * @param targetNsURI
	 *            target namespace URI
	 * @param targetTypeName
	 *            target type name
	 * @return true if an object of the target type can be assigned to a variable of the source type
	 */
	public boolean isAssignable(String sourceNsURI, String sourceTypeName, String targetNsURI, String targetTypeName);

	public List<IXArchADTSubstitutionHint> getAllSubstitutionHints();

	public List<IXArchADTSubstitutionHint> getSubstitutionHintsForSource(String sourceNsURI, String sourceTypeName);

	public List<IXArchADTSubstitutionHint> getSubstitutionHintsForTarget(String targetNsURI, String targetTypeName);

	@Nullable
	public ObjRef getDocumentRootRef(URI uri);

	public Collection<URI> getOpenURIs();

	@Nullable
	public URI getURI(ObjRef ref);

	public byte[] serialize(URI uri);

	public String getXPath(ObjRef toObjRef);

	public List<ObjRef> resolveObjRefs(ObjRef contextObjRef, String xPath) throws XPathException;

	public List<Serializable> resolveSerializables(ObjRef contextObjRef, String xPath) throws XPathException;
}
