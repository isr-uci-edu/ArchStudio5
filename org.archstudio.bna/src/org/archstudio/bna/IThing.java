package org.archstudio.bna;

import java.util.*;

/**
 * Describes a BNA Thing that has one name (a String) and zero or more
 * properties (String to Object mappings).
 */
public interface IThing extends java.io.Serializable, Cloneable {

	/**
	 * Gets the class that maintains and draws the graphical representation of
	 * this Thing.
	 * 
	 * @return The Thing's Peer's class.
	 */
	public Class<? extends IThingPeer> getPeerClass();

	public Object clone() throws CloneNotSupportedException;

	/**
	 * Every Thing has a unique ID that identifies it; this should preferably be
	 * globally unique.
	 * 
	 * @param id
	 *            New ID for this Thing.
	 */
	public void setID(String id);

	/**
	 * Gets the unique ID of this Thing.
	 * 
	 * @return Thing ID.
	 */
	public String getID();

	/**
	 * Gets the property lock for this Thing. You can synchronize on this lock
	 * if you want to make multiple changes to the Thing's properties without
	 * interference from concurrent threads.
	 * 
	 * @return Property lock object for this Thing.
	 */
	public Object getPropertyLockObject();

	/**
	 * Adds a listener to this Thing that will be notified when the Thing
	 * changes.
	 * 
	 * @param tl
	 *            Thing listener to add.
	 */
	public void addThingListener(IThingListener tl);

	/**
	 * Removes a listener to this Thing that will no longer be notified when the
	 * Thing changes.
	 * 
	 * @param tl
	 *            Thing listener to remove.
	 */
	public void removeThingListener(IThingListener tl);

	/**
	 * Adds a String-Object mapping to the property list of this thing.
	 * Autoboxing takes care of the case where the property is a simple type
	 * such as an int or float.
	 * 
	 * @param name
	 *            Name of the property.
	 * @param value
	 *            Value of the property.
	 */
	public void setProperty(String s, Object value);

	/**
	 * Gets a property from this thing.
	 * 
	 * @param name
	 *            Name of the property.
	 * @return Value of the property, or <code>null</code> if there is no such
	 *         property.
	 */
	public <T> T getProperty(String name);

	public boolean hasProperty(String name);

	public void addSetPropertyValue(String name, Object value);

	public void replaceSetPropertyValues(String name, Set s);

	public void removeSetPropertyValue(String name, Object value);

	public Set getSetProperty(String name);

	/**
	 * Removes a property from this thing. Does nothing if the property does not
	 * exist.
	 * 
	 * @param name
	 *            Name of the property to remove.
	 */
	public void removeProperty(String name);

	/**
	 * Gets a Map representation of the name/value pair properties in this
	 * thing.
	 * 
	 * @return Map representation of name/value pair properties.
	 */
	public Map getPropertyMap();

	/**
	 * Gets all the property names in this thing as a collection of strings.
	 * 
	 * @return All property names in a collection.
	 */
	public Collection<String> getAllPropertyNames();
}
