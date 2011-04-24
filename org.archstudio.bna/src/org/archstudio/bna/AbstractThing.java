package org.archstudio.bna;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.sysutils.UIDGenerator;

/**
 * Describes a C2 Thing that has one name (a String) and zero or more properties
 * (String to Object mappings).
 */
public class AbstractThing implements IThing {

	/** ID of the Thing (Unique, not its label!) */
	protected String id;

	/** Thing Properties */
	private Map<String, Object> props;

	public Object propertyLockObject;

	public AbstractThing(String id) {
		if (id == null) {
			this.id = UIDGenerator.generateUID(getClass().getName() + "-");
		}
		else {
			this.id = id;
		}
		this.props = Collections.synchronizedMap(new HashMap<String, Object>());
		this.propertyLockObject = props;
	}

	public Object getPropertyLockObject() {
		return propertyLockObject;
	}

	final public String getPeerClassname() {
		return getPeerClass().getName();
	}

	@SuppressWarnings("unchecked")
	public Class<? extends IThingPeer> getPeerClass() {
		Class thingClass = this.getClass();
		while (thingClass != null) {
			try {
				String peerClassName = thingClass.getName() + "Peer";
				Class<? extends IThingPeer> peerClass = (Class<? extends IThingPeer>) Class.forName(peerClassName, false, thingClass.getClassLoader());
				return (Class<? extends IThingPeer>) peerClass;
			}
			catch (ClassNotFoundException e) {
			}
			thingClass = thingClass.getSuperclass();
		}
		throw new RuntimeException(new ClassNotFoundException("Peer not found for " + this.getClass().getName()));
	}

	public Object clone() throws CloneNotSupportedException {
		AbstractThing t = (AbstractThing) super.clone();
		t.props = Collections.synchronizedMap(new HashMap<String, Object>(t.props));
		t.propertyLockObject = t.props;
		return t;
	}

	private int getHashCode(Object o) {
		if (o == null) {
			return 0;
		}
		Class<?> c = o.getClass();

		if (c.isArray()) {
			return Array.getLength(o);
		}
		else {
			return o.hashCode();
		}
	}

	private boolean realEquals(Object o1, Object o2) {
		if ((o1 == null) && (o2 == null)) {
			return true;
		}
		if ((o1 == null) || (o2 == null)) {
			return false;
		}

		Class<?> o1Class = o1.getClass();
		Class<?> o2Class = o2.getClass();

		if ((o1Class.isArray()) && (o2Class.isArray())) {
			Class<?> o1ct = o1Class.getComponentType();
			Class<?> o2ct = o2Class.getComponentType();

			if (o1ct.isPrimitive() && o2ct.isPrimitive()) {
				if (!o1ct.equals(o2ct)) {
					return false;
				}
				if (o1ct.equals(boolean.class)) {
					return Arrays.equals((boolean[]) o1, (boolean[]) o2);
				}
				if (o1ct.equals(byte.class)) {
					return Arrays.equals((byte[]) o1, (byte[]) o2);
				}
				if (o1ct.equals(short.class)) {
					return Arrays.equals((short[]) o1, (short[]) o2);
				}
				if (o1ct.equals(int.class)) {
					return Arrays.equals((int[]) o1, (int[]) o2);
				}
				if (o1ct.equals(char.class)) {
					return Arrays.equals((char[]) o1, (char[]) o2);
				}
				if (o1ct.equals(long.class)) {
					return Arrays.equals((long[]) o1, (long[]) o2);
				}
				if (o1ct.equals(float.class)) {
					return Arrays.equals((float[]) o1, (float[]) o2);
				}
				if (o1ct.equals(double.class)) {
					return Arrays.equals((double[]) o1, (double[]) o2);
				}
				throw new RuntimeException("Bad primitive type mojo!");
			}
			if (o1ct.isPrimitive() || o2ct.isPrimitive()) {
				return false;
			}
			//They're both not primitive
			//Check to see if it's an array-of-arrays
			if (o1ct.isArray() && o2ct.isArray()) {
				Object[] arr1 = (Object[]) o1;
				Object[] arr2 = (Object[]) o2;
				if (arr1.length != arr2.length) {
					return false;
				}
				for (int i = 0; i < arr1.length; i++) {
					if (!realEquals(arr1[i], arr2[i])) {
						return false;
					}
				}
				//The contents are equal
				return true;
			}
			else if (o1ct.isArray() || (o2ct.isArray())) {
				//Only one component type is an array
				return false;
			}
			else {
				//Neither component type is an array
				return Arrays.equals((Object[]) o1, (Object[]) o2);
			}
		}
		else if (o1Class.isArray() || o2Class.isArray()) {
			//Only one of the two objects is an array
			return false;
		}
		else {
			//Neither one is an array.
			return o1.equals(o2);
		}
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;

		if (!(o instanceof IThing)) {
			return false;
		}
		IThing om = (IThing) o;
		if ((id == null) && (om.getID() != null)) {
			return false;
		}
		if ((id != null) && (om.getID() == null)) {
			return false;
		}
		if ((id != null) && (om.getID() != null) && (!id.equals(om.getID()))) {
			return false;
		}

		Map ht1 = props;
		Map ht2 = om.getPropertyMap();

		synchronized (props) {
			if (ht1.size() != ht2.size()) {
				return false;
			}
			for (Object key : ht1.keySet()) {
				Object ht1Value = ht1.get(key);
				Object ht2Value = ht2.get(key);
				if (ht2Value == null) {
					return false;
				}

				if (!realEquals(ht1Value, ht2Value)) {
					return false;
				}
			}
			return true;
		}
	}

	public int hashCode() {
		int hc = getHashCode(id);
		return hc;
	}

	public void setID(String id) {
		this.id = id;
	}

	public String getID() {
		return id;
	}

	protected List<IThingListener> thingListeners = new CopyOnWriteArrayList<IThingListener>();

	protected void fireThingEvent(ThingEvent.EventType eventType, String propertyName, Object oldValue, Object newValue) {
		if (propertyName.startsWith("#")) {
			return;
		}
		if ((oldValue == null) && (newValue == null)) {
			return;
		}
		if ((oldValue != null) && (newValue != null)) {
			if (oldValue.equals(newValue)) {
				return;
			}
		}
		ThingEvent te = new ThingEvent(eventType, this, propertyName, oldValue, newValue);
		for (IThingListener l : thingListeners) {
			l.thingChanged(te);
		}
	}

	public void addThingListener(IThingListener tl) {
		thingListeners.add(tl);
	}

	public void removeThingListener(IThingListener tl) {
		thingListeners.remove(tl);
	}

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
	public void setProperty(String name, Object value) {
		Object oldPropValue = getProperty(name);
		if (value == null) {
			props.put(name, new NullPropertyValue());
		}
		else {
			props.put(name, value);
		}
		fireThingEvent(ThingEvent.EventType.PROPERTY_SET, name, oldPropValue, value);
	}

	/**
	 * Gets a property from this thing.
	 * 
	 * @param name
	 *            Name of the property.
	 * @return Value of the property, or <code>null</code> if there is no such
	 *         property.
	 */
	@SuppressWarnings( { "unchecked" })
	public <T> T getProperty(String name) {
		Object o = props.get(name);
		if (o instanceof NullPropertyValue) {
			return null;
		}
		return (T) o; //props.get(name);
	}

	public boolean hasProperty(String name) {
		Object o = props.get(name);
		return (o != null);
	}

	@SuppressWarnings( { "unchecked" })
	public void addSetPropertyValue(String name, Object value) {
		synchronized (props) {
			Set s = (Set) getProperty(name);
			if (s == null) {
				s = Collections.synchronizedSet(new HashSet());
			}
			else
				s = new HashSet(s);
			s.add(value);
			setProperty(name, s);
		}
	}

	@SuppressWarnings( { "unchecked" })
	public void replaceSetPropertyValues(String name, Set s) {
		HashSet ns = new HashSet(s);
		setProperty(name, ns);
	}

	@SuppressWarnings( { "unchecked" })
	public void removeSetPropertyValue(String name, Object value) {
		synchronized (props) {
			Set s = (Set) getProperty(name);
			if (s == null) {
				return;
			}
			s = new HashSet(s);
			s.remove(value);
			setProperty(name, s);
		}
	}

	@SuppressWarnings( { "unchecked" })
	public Set getSetProperty(String name) {
		synchronized (props) {
			Set s = (Set) getProperty(name);
			if (s == null) {
				return Collections.EMPTY_SET;
			}
			else {
				return Collections.unmodifiableSet(s);
			}
		}
	}

	/**
	 * Removes a property from this thing. Does nothing if the property does not
	 * exist.
	 * 
	 * @param name
	 *            Name of the property to remove.
	 */
	public void removeProperty(String name) {
		Object oldPropValue = getProperty(name);
		props.remove(name);
		fireThingEvent(ThingEvent.EventType.PROPERTY_REMOVED, name, oldPropValue, null);
	}

	/**
	 * Returns a String representation of this thing.
	 * 
	 * @return String representation of this thing.
	 */
	public String toString() {
		return "Thing={" + "id=" + id + "," + props.toString() + "}";
	}

	/**
	 * Gets a Map representation of the name/value pair properties in this
	 * thing.
	 * 
	 * @return Map representation of name/value pair properties.
	 */
	public Map<String, Object> getPropertyMap() {
		synchronized (props) {
			return new HashMap<String, Object>(props);
		}
		//return (Map)props.clone();
	}

	/**
	 * Gets all the property names in this thing as an array of strings.
	 * 
	 * @return All property names in an array.
	 */
	public Collection<String> getAllPropertyNames() {
		return Collections.unmodifiableSet(props.keySet());
	}

	static class NullPropertyValue implements java.io.Serializable {
		public boolean equals(Object o) {
			if (o instanceof NullPropertyValue) {
				return true;
			}
			return false;
		}

		public int hashCode() {
			return 0;
		}
	};

}
