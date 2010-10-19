package org.archstudio.myx.fw;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.myx.fw.MyxRegistryEvent.EventType;


public class MyxRegistry {

	/**
	 * Maps bricks to lists of Objects to which they correspond. Because the
	 * lists tend to be iterated over and modified also, CopyOnWriteArrayLists
	 * should generally be used.
	 */

	protected Map<IMyxBrick, List<Object>> brickToObjectMap = Collections.synchronizedMap(new HashMap<IMyxBrick, List<Object>>());

	private MyxRegistry() {
		// to prevent instantiation other than from getSharedInstance()
	}

	public synchronized void register(IMyxBrick b) {
		Object o = brickToObjectMap.get(b);
		if (o != null) {
			throw new IllegalArgumentException("Aleady registered this Myx brick in the Myx Registry");
		}
		brickToObjectMap.put(b, new CopyOnWriteArrayList<Object>());
		fireMyxRegistryEvent(MyxRegistryEvent.EventType.BRICK_REGISTERED, b, null);
		notifyAll();
	}

	public synchronized void unregister(IMyxBrick b) {
		brickToObjectMap.remove(b);
		fireMyxRegistryEvent(MyxRegistryEvent.EventType.BRICK_UNREGISTERED, b, null);
		notifyAll();
	}

	public synchronized IMyxBrick getBrick(IMyxName name) {
		for (IMyxBrick b : brickToObjectMap.keySet()) {
			if (name.equals(MyxUtils.getName(b))) {
				return b;
			}
		}
		return null;
	}

	public synchronized IMyxBrick waitForBrick(IMyxName name) {
		IMyxBrick b = null;
		while ((b = getBrick(name)) == null) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public synchronized IMyxBrick getBrick(Class<?> brickClass) {
		for (IMyxBrick b : brickToObjectMap.keySet()) {
			if (brickClass.equals(b.getClass())) {
				return b;
			}
		}
		return null;
	}

	public synchronized IMyxBrick waitForBrick(Class<?> brickClass) {
		IMyxBrick b = null;
		while ((b = getBrick(brickClass)) == null) {
			try {
				wait();
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return b;
	}

	public synchronized void map(IMyxBrick b, Object o) {
		List<Object> objectList = brickToObjectMap.get(b);
		if (objectList == null) {
			throw new IllegalArgumentException("No such brick registered: " + MyxUtils.getName(b));
		}
		if (objectList.contains(o)) {
			throw new IllegalArgumentException("Object " + o + " is already registered with that brick.");
		}
		objectList.add(o);
		fireMyxRegistryEvent(MyxRegistryEvent.EventType.OBJECT_REGISTERED, b, o);
		notifyAll();
	}

	public synchronized void unmap(IMyxBrick b, Object o) {
		List<Object> objectList = brickToObjectMap.get(b);
		if (objectList == null) {
			throw new IllegalArgumentException("No such brick registered: " + MyxUtils.getName(b));
		}
		if (!objectList.contains(o)) {
			throw new IllegalArgumentException("Object " + o + " is not registered with that brick.");
		}
		if (objectList.remove(o)) {
			fireMyxRegistryEvent(MyxRegistryEvent.EventType.OBJECT_UNREGISTERED, b, o);
		}
		notifyAll();
	}

	public synchronized List<? extends Object> getObjects(IMyxBrick b) {
		List<Object> objectList = brickToObjectMap.get(b);
		if (objectList == null) {
			return Collections.emptyList();
		}
		return Collections.unmodifiableList(brickToObjectMap.get(b));
	}

	List<IMyxRegistryListener> listeners = new CopyOnWriteArrayList<IMyxRegistryListener>();

	public synchronized void addMyxRegistryListener(IMyxRegistryListener l) {
		listeners.add(l);
	}

	public synchronized void removeMyxRegistryListener(IMyxRegistryListener l) {
		listeners.remove(l);
	}

	public synchronized void fireMyxRegistryEvent(EventType eventType, IMyxBrick brick, Object object) {
		MyxRegistryEvent evt = new MyxRegistryEvent(eventType, brick, object);
		for (IMyxRegistryListener l : listeners) {
			l.handleMyxRegistryEvent(evt);
		}
	}

	protected static MyxRegistry sharedInstance = new MyxRegistry();

	public static MyxRegistry getSharedInstance() {
		return sharedInstance;
	}
}
