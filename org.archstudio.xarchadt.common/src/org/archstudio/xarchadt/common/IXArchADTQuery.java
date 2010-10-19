package org.archstudio.xarchadt.common;

import java.util.List;

import org.eclipse.emf.common.util.URI;

public interface IXArchADTQuery {

	/**
	 * Determines if a given ObjRef is valid, that is, if there is an object
	 * associated with it.
	 * 
	 * @param ref
	 *            ObjRef to check.
	 * @return <CODE>true</CODE> if there is an associated object,
	 *         <CODE>false</CODE> otherwise.
	 */
	public boolean isValidObjRef(ObjRef ref);

	/**
	 * Gets a reference to a child or a single value from an xArch element.
	 * Roughly equivalent to: baseObject.get[TypeOfThing](); So, if the object
	 * referred to by baseObjectRef implements a method called
	 * <CODE>IDescription getDescription();</CODE> then that would be called
	 * here as:
	 * <CODE>ObjRef descriptionRef = (ObjRef)get(baseObjectRef, "Description");</CODE>
	 * where baseObjectRef is a reference to the base object, and the
	 * typeOfThing is shown as "Description".
	 * 
	 * @param baseObjectRef
	 *            Reference to a base element containing an
	 *            <CODE>get[typeOfThing]</CODE> method.
	 * @param typeOfThing
	 *            A string containing the type of thing to get. For instance, if
	 *            the object referred to by baseObjectRef contains a method
	 *            called <CODE>getDescription</CODE>, then typeOfThing would be
	 *            "Description".
	 * @return An <CODE>ObjRef</CODE> referring to the object gotten if the
	 *         object returned is an xArch element; otherwise a String. For
	 *         instance, if <CODE>typeOfThing</CODE> refers to an attribute or
	 *         is "Value" (when <CODE>baseObjectRef</CODE> refers to a simple
	 *         type) then this function will return a string.
	 */
	public Object get(ObjRef baseObjectRef, String typeOfThing);

	/**
	 * Gets a reference to a child from an xArch element, given the child's ID.
	 * Roughly equivalent to: baseObject.get[TypeOfThing](id); So, if the object
	 * referred to by baseObjectRef implements a method called
	 * <CODE>IDescription getDescription(String id);</CODE> then that would be
	 * called here as:
	 * <CODE>ObjRef descriptionRef = get(baseObjectRef, "Description", id);</CODE>
	 * where baseObjectRef is a reference to the base object, and the
	 * typeOfThing is shown as "Description". ID remains a string.
	 * 
	 * @param baseObjectRef
	 *            Reference to a base element containing an
	 *            <CODE>get[typeOfThing]</CODE> method.
	 * @param typeOfThing
	 *            A string containing the type of thing to get. For instance, if
	 *            the object referred to by baseObjectRef contains a method
	 *            called <CODE>getDescription</CODE>, then typeOfThing would be
	 *            "Description".
	 * @param id
	 *            Identifier of thing to get.
	 * @return An <CODE>ObjRef</CODE> referring to the object gotten.
	 */
	public ObjRef get(ObjRef baseObjectRef, String typeOfThing, String id);

	/**
	 * Gets a set of references to a set of children from an xArch element,
	 * given the children's IDs. Roughly equivalent to:
	 * baseObject.get[TypeOfThing]s(ids); So, if the object referred to by
	 * baseObjectRef implements a method called
	 * <CODE>Collection getDescriptions(String[] id);</CODE> then that would be
	 * called here as:
	 * <CODE>ObjRef[] descriptionRefs = get(baseObjectRef, "Description", ids);</CODE>
	 * where baseObjectRef is a reference to the base object, and the
	 * typeOfThing is shown as "Description". IDs remains strings.
	 * 
	 * @param baseObjectRef
	 *            Reference to a base element containing an
	 *            <CODE>get[typeOfThing]s</CODE> method.
	 * @param typeOfThing
	 *            A string containing the type of thing to get. For instance, if
	 *            the object referred to by baseObjectRef contains a method
	 *            called <CODE>getDescriptions</CODE>, then typeOfThing would be
	 *            "Description".
	 * @param ids
	 *            Identifiers of things to get.
	 * @return An array of <CODE>ObjRef</CODE>s referring to the objects gotten.
	 */
	public List<ObjRef> get(ObjRef baseObjectRef, String typeOfThing, List<String> ids);

	/**
	 * Gets a set of references to a set of children from an xArch element.
	 * Roughly equivalent to: baseObject.getAll[TypeOfThing]s(ids); So, if the
	 * object referred to by baseObjectRef implements a method called
	 * <CODE>Collection getAllDescriptions();</CODE> then that would be called
	 * here as:
	 * <CODE>ObjRef[] descriptionRefs = getAll(baseObjectRef, "Description");</CODE>
	 * where baseObjectRef is a reference to the base object, and the
	 * typeOfThing is shown as "Description".
	 * 
	 * @param baseObjectRef
	 *            Reference to a base element containing an
	 *            <CODE>getAll[typeOfThing]s</CODE> method.
	 * @param typeOfThing
	 *            A string containing the type of thing to get. For instance, if
	 *            the object referred to by baseObjectRef contains a method
	 *            called <CODE>getAllDescriptions</CODE>, then typeOfThing would
	 *            be "Description".
	 * @return An array of <CODE>ObjRef</CODE>s referring to the objects gotten.
	 */
	public List<ObjRef> getAll(ObjRef baseObjectRef, String typeOfThing);

	/**
	 * Gets metadata describing a compound type (context and simple type name).
	 * 
	 * @param type
	 *            The compound type (see:
	 *            {@link XArchMetadataUtils#getType(String, String)})
	 * @return Metadata describing the type of the xArch element.
	 * 
	 * @see XArchMetadataUtils#getType(String, String)
	 */
	//public IXArchTypeMetadata getTypeMetadata(String type);

	/**
	 * Gets metadata describing the type of an xArch element. Roughly equivalent
	 * to: baseObject.getTypeMetadata();
	 * 
	 * @param baseObjectRef
	 *            Reference to an xArch element.
	 * @return Metadata describing the type of the xArch element.
	 */
	//public IXArchTypeMetadata getTypeMetadata(ObjRef baseObjectRef);

	/**
	 * Gets metadata describing the instance of an xArch element.
	 * 
	 * @param baseObjRef
	 *            Reference to an xArch element.
	 * @return Metadata describing the instance of the xArch element.
	 */
	//public IXArchInstanceMetadata getInstanceMetadata(ObjRef baseObjRef);

	/**
	 * DEPRICATED: This method reveals the underlying implementing class. Use
	 * {@link #isInstanceOf(ObjRef, String)}.
	 * 
	 * Gets the class name of an xArch element. Roughly equivalent to:
	 * baseObject.getClass().getName();
	 * 
	 * @param baseObjectRef
	 *            Reference to an xArch element.
	 * @return The name of the class of that object.
	 * 
	 * @deprecated Replaced by {@link #isInstanceOf(ObjRef, String)}
	 */
	public String getType(ObjRef baseObjectRef);

	/**
	 * NOTE: className reveals implementation detail, replace with compound type
	 * described in {@link XArchMetadataUtils#getType(String, String)}. For
	 * example, before you would have called this method with
	 * "org.archstudio.xarch.types.IComponentType", but now the format described in
	 * {@link XArchMetadataUtils#getType(String, String)}
	 * 
	 * Determines if a given xArch element is an instance of a given class.
	 * Roughly equivalent to: (baseObject instanceof [type])
	 * 
	 * @param baseObjectRef
	 *            Reference to an xArch element.
	 * @param type
	 *            The compound type (see:
	 *            {@link XArchMetadataUtils#getType(String, String)})
	 * @return <CODE>true</CODE> if the base object was an instance of the given
	 *         type, <CODE>false</CODE> otherwise.
	 * 
	 * @see XArchMetadataUtils#getType(String, String)
	 */
	public boolean isInstanceOf(ObjRef baseObjectRef, String type);

	/**
	 * Determines if toType is either the same as, or a supertype of, the
	 * fromType parameter. Roughly equivalent to:
	 * ([toType].isAssignableFrom([fromType])
	 * 
	 * @param fromType
	 *            The from compound type to check (see:
	 *            {@link XArchMetadataUtils#getType(String, String)})
	 * @param toType
	 *            The potential subtype to check (see:
	 *            {@link XArchMetadataUtils#getType(String, String)})
	 * @return <CODE>true</CODE> if toType is the same as, or a supertype of,
	 *         fromType, <CODE>false</CODE> otherwise.
	 * 
	 * @see XArchMetadataUtils#getType(String, String)
	 */
	public boolean isAssignable(String toType, String fromType);

	/**
	 * Determines if one node in the XML tree is an ancestor of another. This
	 * method is more efficient than using the output of
	 * <CODE>getAllAncestors(...)</CODE>, since
	 * <CODE>getAllAncestors(...)</CODE> makes wrapper objects for each
	 * ancestor, and this one just looks at the underlying XML nodes.
	 * 
	 * @param childRef
	 *            The potential child node.
	 * @param ancestorRef
	 *            The potential ancestor node.
	 * @return <CODE>true</CODE> if the node referred to by
	 *         <CODE>ancestorRef</CODE> is an ancestor of the node referred to
	 *         by <CODE>childRef</CODE>, <CODE>false</CODE> otherwise.
	 */
	public boolean hasAncestor(ObjRef childRef, ObjRef ancestorRef);

	/**
	 * Determines if a node in the XML tree is attached to the root.
	 * 
	 * @param childRef
	 *            The node to check.
	 * @return <CODE>true</CODE> if the node referred to by
	 *         <CODE>childRef</CODE> is attached to the root node of the
	 *         document; (i.e. the root node is an ancestor of the node),
	 *         <CODE>false</CODE> otherwise.
	 */
	public boolean isAttached(ObjRef childRef);

	/**
	 * Gets all the ancestors (in the XML tree) of a given target object.
	 * 
	 * @param targetObjectRef
	 *            Reference to target object.
	 * @return Array of references to ancestors, in order starting with (and
	 *         including) the target object, then its parent, then its parent's
	 *         parent, etc.
	 */
	public List<ObjRef> getAllAncestors(ObjRef targetObjectRef);

	/**
	 * Gets the parent (in the XML tree) of a given target object.
	 * 
	 * @param targetObjectRef
	 *            Reference to target object.
	 * @return A reference to its parent in the XML tree, or <CODE>null</CODE>
	 *         if it has none.
	 */
	public ObjRef getParent(ObjRef targetObjectRef);

	/**
	 * Gets the <CODE>XArchPath</CODE> of the given element.
	 * 
	 * @param ref
	 *            Reference to the element whose path you want to get.
	 * @return <CODE>XArchPath</CODE> to that element.
	 */
	//public XArchPath getXArchPath(ObjRef ref);

	/**
	 * Gets an element by its ID within a given xArch tree. If no such element
	 * exists, returns <CODE>null</CODE>.
	 * 
	 * @param xArchRef
	 *            Reference to the IXArch object that is the root of the tree to
	 *            search.
	 * @param id
	 *            The ID to search for.
	 * @return reference to the object, or <CODE>null</CODE> if no such object
	 *         exists.
	 * @exception IllegalArgumentException
	 *                if <CODE>xArchRef</CODE> is invalid.
	 */
	public ObjRef getByID(ObjRef xArchRef, String id);

	/**
	 * Gets an element by its ID within ANY OPEN xArch tree. If no such element
	 * exists, returns <CODE>null</CODE>.
	 * 
	 * @param id
	 *            The ID to search for.
	 * @return reference to the object, or <CODE>null</CODE> if no such object
	 *         exists.
	 */
	public ObjRef getByID(String id);

	/**
	 * Resolves an href, as might be found in an XLink.
	 * 
	 * @param xArchRef
	 *            Reference to the IXArch object that provides the context in
	 *            which the href exists.
	 * @param href
	 *            The href to resolve. May be local or remote. <I>Note:</I> For
	 *            this version of the library, hrefs must be in the form
	 *            <CODE>#id</CODE> or <CODE>http://....#id</CODE>
	 * @return Reference to the referenced (by the href) object, or
	 *         <CODE>null</CODE>.
	 */
	public ObjRef resolveHref(ObjRef xArchRef, String href);

	/**
	 * Resolves an <CODE>XArchPath</CODE> in the context of the given document
	 * (in terms of an <CODE>ObjRef</CODE> to the top level xArch element of a
	 * document).
	 * 
	 * @param xArchRef
	 *            Reference to the top-level element of the document within
	 *            which to resolve the <CODE>XArchPath</CODE>.
	 * @param xArchPath
	 *            <CODE>XArchPath</CODE> to resolve.
	 * @return <CODE>ObjRef</CODE> to the referenced element, or
	 *         <CODE>null</CODE> if the path cannot be resolved
	 */
	//public ObjRef resolveXArchPath(ObjRef xArchRef, XArchPath xArchPath);

	/**
	 * Determines whether two ObjRefs refer to the same underlying object.
	 * 
	 * @param ref1
	 *            First ObjRef.
	 * @param ref2
	 *            Second ObjRef
	 * @return <code>true</code> if equal, false otherwise.
	 */
	public boolean equals(ObjRef ref1, ObjRef ref2);

	public XArchADTPath getPath(ObjRef ref);
	
	public List<String> getAvailableFactories();
	
	public ObjRef getDocumentRootRef(ObjRef ref);

	public String getTagName(ObjRef ref);
	public String getContainingFeatureName(ObjRef ref);
	public String getTagsOnlyPathString(ObjRef ref);
	public IXArchADTTypeMetadata getTypeMetadata(ObjRef ref);
	public IXArchADTFactoryMetadata getFactoryMetadata(String factoryName);

}
