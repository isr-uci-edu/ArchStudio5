package org.archstudio.xarchadt;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.xml.sax.SAXException;

/**
 * An interface that provides a single point of service for the entire set of
 * DOM-based xArch libraries. All parameters to this interface are serializable
 * and do not contain direct pointers into the structure of an xArch document.
 */

@NonNullByDefault
public interface IXArchADT extends IXArchADTQuery {

	public ObjRef createDocument(URI uri);

	public ObjRef createDocument(URI uri, String nsURI);

	public ObjRef load(URI uri) throws SAXException, IOException;

	public ObjRef load(URI uri, byte[] content) throws SAXException, IOException;

	// public ObjRef cloneDocument(URI oldURI, URI newURI);

	public void save(URI uri) throws IOException;

	public void close(URI uri);

	/**
	 * Adds a child to an xArch element. Roughly equivalent to:
	 * baseObject.add[TypeOfThing](thingToAdd); So, if the object referred to by
	 * baseObjRef implements a method called
	 * <CODE>void addComponent(IComponent c);</CODE> then that would be called
	 * here as: <CODE>add(baseObjRef, "Component", cRef);</CODE> where
	 * baseObjRef is a reference to the base object, the typeOfThing is shown as
	 * "Component", and the thingToAdd is a reference to an
	 * <CODE>IComponent</CODE>.
	 * 
	 * @param baseObjRef
	 *            Reference to a base element containing an
	 *            <CODE>add[typeOfThing]</CODE> method.
	 * @param typeOfThing
	 *            A string containing the type of thing to add. For instance, if
	 *            the object referred to by baseObjRef contains a method called
	 *            <CODE>addComponent</CODE>, then typeOfThing would be
	 *            "Component".
	 * @param thingToAddRef
	 *            Reference to the object to add as a child of baseObject.
	 */
	public void add(ObjRef baseObjRef, String typeOfThing, Serializable thingToAddRef);

	/**
	 * Adds a set of children to an xArch element. Roughly equivalent to:
	 * baseObject.add[TypeOfThing](thingsToAdd); So, if the object referred to
	 * by baseObjRef implements a method called
	 * <CODE>void addComponents(Collection c);</CODE> then that would be called
	 * here as: <CODE>add(baseObjRef, "Component", cRefs);</CODE> where
	 * baseObjRef is a reference to the base object, the typeOfThing is shown as
	 * "Component", and the thingToAdd is a array of references to
	 * <CODE>IComponent</CODE>s.
	 * 
	 * @param baseObjRef
	 *            Reference to a base element containing an
	 *            <CODE>add[typeOfThing]s</CODE> method.
	 * @param typeOfThing
	 *            A string containing the type of thing to add. For instance, if
	 *            the object referred to by baseObjRef contains a method called
	 *            <CODE>addComponents</CODE>, then typeOfThing would be
	 *            "Component".
	 * @param thingToAddRefs
	 *            References to the objects to add as children of baseObject.
	 */
	public void add(ObjRef baseObjRef, String typeOfThing, Collection<? extends Serializable> thingsToAddRefs);

	/**
	 * Clears a child or a set of children from an xArch element. Roughly
	 * equivalent to: baseObject.clear[TypeOfThing](); or
	 * baseObject.clear[TypeOfThing]s(); So, if the object referred to by
	 * baseObjRef implements a method called
	 * <CODE>void clearDescription();</CODE> then that would be called here as:
	 * <CODE>clear(baseObjRef, "Description");</CODE> where baseObjRef is a
	 * reference to the base object, and the typeOfThing is shown as
	 * "Description".
	 * 
	 * @param baseObjRef
	 *            Reference to a base element containing an
	 *            <CODE>clear[typeOfThing](s)</CODE> method.
	 * @param typeOfThing
	 *            A string containing the type of thing to clear. For instance,
	 *            if the object referred to by baseObjRef contains a method
	 *            called <CODE>clearDescription</CODE> or
	 *            <CODE>clearDescriptions</CODE> , then typeOfThing would be
	 *            "Description".
	 */
	public void clear(ObjRef baseObjRef, String typeOfThing);

	/**
	 * Removes a child element from a parent element. Roughly equivalent to:
	 * baseObject.remove[typeOfThing](thingToRemove); So, if the object referred
	 * to by baseObjRef implements a method called
	 * <CODE>void removeComponent(IComponent thingToRemove);</CODE> then that
	 * would be called here as:
	 * <CODE>remove(baseObjRef, "Component", thingToRemove);</CODE> where
	 * baseObjRef is a reference to the base object, and the typeOfThing is
	 * shown as "Component". The value to remove is passed by object reference.
	 * 
	 * @param baseObjRef
	 *            Reference to a base element containing an
	 *            <CODE>remove[typeOfThing](...)</CODE> method.
	 * @param typeOfThing
	 *            A string containing the type of thing where the value will be
	 *            removed. For instance, if the object referred to by baseObjRef
	 *            contains a method called <CODE>removeComponent</CODE>, then
	 *            typeOfThing would be "Component".
	 * @param thingToRemove
	 *            A reference to the object to remove.
	 */
	public void remove(ObjRef baseObjRef, String typeOfThing, Serializable valueToRemove);

	/**
	 * Removes a set of children from a parent element. Roughly equivalent to:
	 * baseObject.remove[typeOfThing]s(thingsToRemove); So, if the object
	 * referred to by baseObjRef implements a method called
	 * <CODE>void removeComponents(Collection thingsToRemove);</CODE> then that
	 * would be called here as:
	 * <CODE>remove(baseObjRef, "Component", thingsToRemove);</CODE> where
	 * baseObjRef is a reference to the base object, and the typeOfThing is
	 * shown as "Component". The values to remove are passed as an array of
	 * object references.
	 * 
	 * @param baseObjRef
	 *            Reference to a base element containing an
	 *            <CODE>remove[typeOfThing]s(Collection)</CODE> method.
	 * @param typeOfThing
	 *            A string containing the type of thing where the value will be
	 *            removed. For instance, if the object referred to by baseObjRef
	 *            contains a method called <CODE>removeComponents</CODE>, then
	 *            typeOfThing would be "Component".
	 * @param thingToRemove
	 *            An array of references to the objects to remove.
	 */
	public void remove(ObjRef baseObjRef, String typeOfThing, Collection<? extends Serializable> thingsToRemove);

	/**
	 * Sets the value of an attribute, simple-value element, or complex-type
	 * child element. Roughly equivalent to: baseObject.set[typeOfThing](value);
	 * So, if the object referred to by baseObjRef implements a method called
	 * <CODE>void setValue(String value);</CODE> then that would be called here
	 * as: <CODE>set(baseObjRef, "Value", value);</CODE> where baseObjRef is a
	 * reference to the base object, and the typeOfThing is shown as "Value".
	 * The value to set is passed as a {@link Serializable} object.
	 * 
	 * @param baseObjRef
	 *            Reference to a base element containing an
	 *            <CODE>set[typeOfThing](String)</CODE> method.
	 * @param typeOfThing
	 *            A string containing the type of thing where the value will be
	 *            set. For instance, if the object referred to by baseObjRef
	 *            contains a method called <CODE>setValue</CODE>, then
	 *            typeOfThing would be "Value".
	 * @param value
	 *            The value to set.
	 */
	public void set(ObjRef baseObjRef, String typeOfThing, @Nullable Serializable value);

	public ObjRef create(String nsURI, String typeOfThing);

	///**
	// * Clones an element with the given depth, changing IDs in the copy if
	// * necessary to maintain consistency.
	// * 
	// * @param targetObjRef
	// *            Element to clone.
	// * @return <CODE>ObjRef</CODE> to the cloned element.
	// */
	//public ObjRef cloneElement(ObjRef targetObjRef);

	/**
	 * Changes the URI of an open document. All ObjRefs remain valid. An
	 * <code>XArchFileEvent</code> is emitted to notify listeners of the URI
	 * change.
	 * 
	 * @param oldURI
	 *            URI of some currently open document.
	 * @param newURI
	 *            The new URI for the document.
	 */
	public void renameXArch(String oldURI, String newURI);
}
