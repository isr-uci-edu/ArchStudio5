package org.archstudio.myx.fw;

import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.archstudio.myx.fw.MyxRegistryEvent.EventType;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * A thread safe registry for managing arbitrary objects associated with Myx bricks.
 * <p/>
 * The Myx brick and its objects are stored using weak references and thus garbage collected when no longer reachable.
 * The registry is used, among other things, as a source of listeners for service objects passed through "out"
 * interfaces.
 *
 * @author sahendrickson@gmail.com (Scott A. Hendrickson)
 */
public final class MyxRegistry {
	/** The singleton MyxRegistry object. */
	private static final MyxRegistry singleton = new MyxRegistry();

	/** Returns the singleton MyxRegistry. */
	public static final MyxRegistry getSharedInstance() {
		return singleton;
	}

	/** Maintains the collection of Myx bricks and objects associated with them. */
	private final Map<IMyxBrick, List<WeakReference<Object>>> brickToObjectsMap =
			Collections.synchronizedMap(new WeakHashMap<IMyxBrick, List<WeakReference<Object>>>());

	/** The listeners to be informed of changes to the MyxRegistry. */
	private List<IMyxRegistryListener> listeners = new CopyOnWriteArrayList<IMyxRegistryListener>();

	/** Prevent instantiation. Instead use {@link #getSharedInstance()}. */
	private MyxRegistry() {
	}

	/**
	 * Registers a brick with the registry.
	 *
	 * @param brick The brick to register.
	 */
	public void registerBrick(IMyxBrick brick) {
		Preconditions.checkNotNull(brick);
		synchronized (this) {
			if (brickToObjectsMap.get(brick) == null) {
				brickToObjectsMap.put(brick, new CopyOnWriteArrayList<WeakReference<Object>>());
			}
			notifyAll();
			fireMyxRegistryEvent(MyxRegistryEvent.EventType.BRICK_REGISTERED, brick, null);
		}
	}

	/**
	 * Use {@link #registerBrick(IMyxBrick)}.
	 */
	@Deprecated
	public void register(IMyxBrick brick) {
		registerBrick(brick);
	}

	/**
	 * Unregisters a brick with the registry.
	 *
	 * @param brick The brick to unregister.
	 */
	public void unregisterBrick(IMyxBrick brick) {
		Preconditions.checkNotNull(brick);
		synchronized (this) {
			brickToObjectsMap.remove(brick);
		}
	}

	/**
	 * User {@link #unregisterBrick(IMyxBrick)}.
	 */
	@Deprecated
	public void unregister(IMyxBrick brick) {
		unregisterBrick(brick);
	}

	/**
	 * Returns the brick with the given name, or <code>null</code> if none with that name are registered.
	 *
	 * @param name The Myx name to look for.
	 * @return the brick with the given name, or <code>null</code> if none with that name are registered.
	 */
	public synchronized IMyxBrick getBrick(IMyxName name) {
		Preconditions.checkNotNull(name);
		Preconditions.checkNotNull(name.getName());
		for (IMyxBrick brick : brickToObjectsMap.keySet()) {
			if (name.equals(MyxUtils.getName(brick))) {
				return brick;
			}
		}
		return null;
	}

	/**
	 * Waits for and returns the brick with the given name, blocking until available.
	 *
	 * @param name The Myx name to look for.
	 * @return the brick with the given name.
	 */
	@SuppressWarnings("unchecked")
	public <B extends IMyxBrick> B waitForBrick(IMyxName name) {
		Preconditions.checkNotNull(name);
		Preconditions.checkNotNull(name.getName());
		IMyxBrick brick = null;
		while ((brick = getBrick(name)) == null) {
			synchronized (this) {
				try {
					wait();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return (B) brick;
	}

	/**
	 * Returns the brick of the given type, or <code>null</code> if none with that type are registered.
	 *
	 * @param brickClass The type of the brick to retrieve.
	 * @return the brick of the given type, or <code>null</code> if none with that type are registered.
	 */
	@SuppressWarnings("unchecked")
	public synchronized <B extends IMyxBrick> B getBrick(Class<B> brickClass) {
		Preconditions.checkNotNull(brickClass);
		for (IMyxBrick brick : brickToObjectsMap.keySet()) {
			if (brickClass.equals(brick.getClass())) {
				return (B) brick;
			}
		}
		return null;
	}

	/**
	 * Returns the brick of the given type, blocking until available.
	 *
	 * @param brickClass The type of the brick to retrieve.
	 * @return the brick of the given type.
	 */
	public <B extends IMyxBrick> B waitForBrick(Class<B> brickClass) {
		Preconditions.checkNotNull(brickClass);
		B brick = null;
		while ((brick = getBrick(brickClass)) == null) {
			synchronized (this) {
				try {
					wait();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return brick;
	}

	/**
	 * Adds an object to the given brick, registering the brick if it is not already registered.
	 *
	 * @param brick The brick to associate the object with.
	 * @param object The object to associate with the brick.
	 */
	public void registerObject(IMyxBrick brick, Object object) {
		Preconditions.checkNotNull(brick);
		Preconditions.checkNotNull(object);
		synchronized (this) {
			registerBrick(brick);
			List<WeakReference<Object>> objectsList = brickToObjectsMap.get(brick);
			objectsList.add(new WeakReference<>(object));
			notifyAll();
			fireMyxRegistryEvent(MyxRegistryEvent.EventType.OBJECT_REGISTERED, brick, object);
		}
	}

	/**
	 * Use {@link #registerObject(IMyxBrick, Object)}.
	 */
	@Deprecated
	public void map(IMyxBrick brick, Object object) {
		registerObject(brick, object);
	}

	/**
	 * Removes an object from the given brick, registering the brick if it is not already registered.
	 *
	 * @param brick The brick with the associated object.
	 * @param object The object to remove.
	 */
	public void unregisterObject(IMyxBrick brick, Object object) {
		Preconditions.checkNotNull(brick);
		Preconditions.checkNotNull(object);
		synchronized (this) {
			registerBrick(brick);
			List<WeakReference<Object>> weakObjects = brickToObjectsMap.get(brick);
			for (WeakReference<Object> weakReference : weakObjects) {
				Object weakObject = weakReference.get();
				// Clean up garbage collected references.
				if (weakObject == null) {
					weakObjects.remove(weakReference);
					continue;
				}
				if (object.equals(weakObject)) {
					weakObjects.remove(weakReference);
					continue;
				}
			}
		}
	}

	/**
	 * Use {@link #unregisterObject(IMyxBrick, Object)}.
	 */
	@Deprecated
	public void unmap(IMyxBrick brick, Object object) {
		unregisterObject(brick, object);
	}

	/**
	 * Returns the objects associated with the brick, registering the brick if it is not already registered.
	 *
	 * @param brick The brick with the associated objects.
	 * @return a list of all objects associated with the brick.
	 */
	public synchronized List<? extends Object> getObjects(IMyxBrick brick) {
		Preconditions.checkNotNull(brick);
		registerBrick(brick);
		List<WeakReference<Object>> weakObjects = brickToObjectsMap.get(brick);
		List<Object> objects = Lists.newArrayListWithCapacity(weakObjects.size());
		for (Iterator<WeakReference<Object>> i = weakObjects.iterator(); i.hasNext();) {
			WeakReference<Object> weakReference = i.next();
			Object weakObject = weakReference.get();
			// Clean up garbage collected references.
			if (weakObject == null) {
				weakObjects.remove(weakReference);
				continue;
			}
			objects.add(weakObject);
		}
		return objects;
	}

	/**
	 * Returns the objects associated with the brick of the given type, registering the brick if it is not already
	 * registered.
	 *
	 * @param brick The brick with the associated objects.
	 * @param objectClass The required type for filtered objects.
	 * @return a list of all objects associated with the brick.
	 */
	@SuppressWarnings("unchecked")
	public <T> Iterable<T> getObjects(IMyxBrick brick, Class<T> objectClass) {
		Preconditions.checkNotNull(brick);
		Preconditions.checkNotNull(objectClass);
		registerBrick(brick);
		List<WeakReference<Object>> weakObjects = brickToObjectsMap.get(brick);
		List<T> objects = Lists.newArrayListWithCapacity(weakObjects.size());
		for (Iterator<WeakReference<Object>> i = weakObjects.iterator(); i.hasNext();) {
			WeakReference<Object> weakReference = i.next();
			Object weakObject = weakReference.get();
			// Clean up garbage collected references.
			if (weakObject == null) {
				weakObjects.remove(weakReference);
				continue;
			}
			if (objectClass.isInstance(weakObject)) {
				objects.add((T) weakObject);
			}
		}
		return objects;
	}

	/**
	 * Adds the listener to the collection of listeners that will be notified when a brick or object is registered with
	 * the registry.
	 *
	 * @param listener The listener.
	 */
	public synchronized void addMyxRegistryListener(IMyxRegistryListener listener) {
		Preconditions.checkNotNull(listeners);
		listeners.add(listener);
	}

	/**
	 * Removes the listener from the collection of listeners that will be notified when a brick or object is registered
	 * with the registry.
	 *
	 * @param listener The listener.
	 */
	public synchronized void removeMyxRegistryListener(IMyxRegistryListener l) {
		Preconditions.checkNotNull(listeners);
		listeners.remove(l);
	}

	/**
	 * Fires a event to listeners that should be notified when a brick or object is registered with the registry.
	 *
	 * @param eventType The type of event.
	 * @param brick The brick that was modified.
	 * @param object The object that was added.
	 */
	private void fireMyxRegistryEvent(EventType eventType, IMyxBrick brick, Object object) {
		Preconditions.checkNotNull(eventType);
		Preconditions.checkNotNull(brick);
		Preconditions.checkState(Thread.holdsLock(this));
		MyxRegistryEvent evt = new MyxRegistryEvent(eventType, brick, object);
		for (IMyxRegistryListener listener : listeners) {
			listener.handleMyxRegistryEvent(evt);
		}
	}
}
