package edu.uci.isr.archstudio4.comp.archipelago.generic.logics.hints;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class XAdlHintRepository implements IHintRepository, XArchFlatListener {

	private static final boolean DEBUG = false;

	private static final boolean equalz(Object o1, Object o2) {
		return o1 == null ? o2 == null : o1.equals(o2);
	}

	protected final IPropertyCoder propertyCoder = MasterPropertyCoder.getSingleton();

	protected final XArchFlatInterface xarch;
	protected final ObjRef xArchRef;
	protected final ObjRef hintsContextRef;
	protected final ObjRef rootElementRef;
	protected final String maintainer;
	protected final String version;
	protected final ThingPropertyTrackingLogic tptl;
	protected final Collection<ObjRef> ignoreObjRefUpdates = Collections.synchronizedCollection(new HashBag<ObjRef>());

	protected final XArchRelativePathTracker hintedElementRefTracker;
	protected final BidirectionalMap<String, ObjRef> idToHintedElementRefs = new BidirectionalHashMap<String, ObjRef>();

	protected final XArchRelativePathTracker propertyRefTracker;
	protected final BidirectionalMap<Tuple, ObjRef> hintedElementRefNameToPropertyRefs = new BidirectionalHashMap<Tuple, ObjRef>();

	ObjRef rootHintedElementRef = null;

	public XAdlHintRepository(XArchFlatInterface xarch, ObjRef rootElementRef, String maintainer, String version,
			ThingPropertyTrackingLogic tptl) {
		this.xarch = xarch;
		this.xArchRef = xarch.getXArch(rootElementRef);
		this.hintsContextRef = xarch.createContext(xArchRef, "hints3");
		this.rootElementRef = rootElementRef;
		this.maintainer = maintainer;
		this.version = version;
		this.tptl = tptl;

		hintedElementRefTracker = new XArchRelativePathTracker(xarch, null, "hintedElement/target", false);
		hintedElementRefTracker.addTrackerListener(new IXArchRelativePathTrackerListener() {

			public void processAdd(ObjRef objRef, ObjRef[] relativeAncestorRefs) {
				if (XAdlHintRepository.DEBUG) {
					System.err.println("Adding   H: " + relativeAncestorRefs[1]);
				}
				update(objRef, relativeAncestorRefs[1]);
			}

			public void processUpdate(ObjRef objRef, ObjRef[] relativeAncestorRefs, XArchFlatEvent evt,
					XArchPath relativeSourceTargetPath) {
				if (relativeSourceTargetPath.toTagsOnlyString().startsWith("href")) {
					if (XAdlHintRepository.DEBUG) {
						System.err.println("Updating H: " + relativeAncestorRefs[1]);
					}
					update(objRef, relativeAncestorRefs[1]);
				}
			}

			public void processRemove(ObjRef objRef, ObjRef[] relativeAncestorRefs) {
				synchronized (idToHintedElementRefs) {
					if (XAdlHintRepository.DEBUG) {
						System.err.println("Removing H: " + relativeAncestorRefs[1]);
					}
					idToHintedElementRefs.values().remove(relativeAncestorRefs[1]);
				}
			}

			protected void update(ObjRef targetRef, ObjRef hintedElementRef) {
				synchronized (idToHintedElementRefs) {
					if (hintedElementRef != null) {
						String href = (String) XAdlHintRepository.this.xarch.get(targetRef, "href");
						if (href != null && href.startsWith("#")) {
							String id = href.substring(1);
							idToHintedElementRefs.put(id, hintedElementRef);
							return;
						}
					}
					idToHintedElementRefs.removeValue(hintedElementRef);
				}
			}
		});

		propertyRefTracker = new XArchRelativePathTracker(xarch, null, "hintedElement/property", false);
		propertyRefTracker.addTrackerListener(new IXArchRelativePathTrackerListener() {

			public void processAdd(ObjRef objRef, ObjRef[] relativeAncestorRefs) {
				if (XAdlHintRepository.DEBUG) {
					System.err.println("Adding   P: " + relativeAncestorRefs[1] + " "
							+ (String) XAdlHintRepository.this.xarch.get(relativeAncestorRefs[0], "name"));
				}
				ObjRef hintedElementRef = relativeAncestorRefs[1];
				update(objRef, hintedElementRef);
				if (!ignoreObjRefUpdates.remove(objRef)) {
					fireHintRepositoryChangeEvent(hintedElementRef, objRef);
				}
			}

			public void processUpdate(ObjRef objRef, ObjRef[] relativeAncestorRefs, XArchFlatEvent evt,
					XArchPath relativeSourceTargetPath) {
				ObjRef hintedElementRef = relativeAncestorRefs[1];
				if (relativeSourceTargetPath.toTagsOnlyString().startsWith("name")) {
					if (XAdlHintRepository.DEBUG) {
						System.err.println("Updating P: " + relativeAncestorRefs[1] + " "
								+ (String) XAdlHintRepository.this.xarch.get(relativeAncestorRefs[0], "name"));
					}
					update(objRef, hintedElementRef);
					if (!ignoreObjRefUpdates.remove(objRef)) {
						fireHintRepositoryChangeEvent(hintedElementRef, objRef);
					}
				}
				else if (relativeSourceTargetPath.toTagsOnlyString().startsWith("value")) {
					if (XAdlHintRepository.DEBUG) {
						System.err.println("Updating P: " + relativeAncestorRefs[1] + " "
								+ (String) XAdlHintRepository.this.xarch.get(relativeAncestorRefs[0], "name"));
					}
					update(objRef, hintedElementRef);
					if (!ignoreObjRefUpdates.remove(objRef)) {
						fireHintRepositoryChangeEvent(hintedElementRef, objRef);
					}
				}
			}

			public void processRemove(ObjRef objRef, ObjRef[] relativeAncestorRefs) {
				if (XAdlHintRepository.DEBUG) {
					System.err.println("Removing P: " + relativeAncestorRefs[1] + " "
							+ (String) XAdlHintRepository.this.xarch.get(relativeAncestorRefs[0], "name"));
				}
				ObjRef hintedElementRef = relativeAncestorRefs[1];
				boolean fireUpdate;
				synchronized (hintedElementRefNameToPropertyRefs) {
					fireUpdate = hintedElementRefNameToPropertyRefs.values().remove(objRef);
				}
				if (!ignoreObjRefUpdates.remove(objRef)) {
					if (fireUpdate) {
						fireHintRepositoryChangeEvent(hintedElementRef, objRef);
					}
				}
			}

			protected void update(ObjRef propertyRef, ObjRef hintedElementRef) {
				synchronized (hintedElementRefNameToPropertyRefs) {
					if (hintedElementRef != null) {
						String name = (String) XAdlHintRepository.this.xarch.get(propertyRef, "name");
						if (XAdlHintRepository.DEBUG) {
							System.err.println("Mapping  P: " + propertyRef + " " + name);
						}
						if (name != null) {
							Tuple newTuple = new Tuple(hintedElementRef, name);
							hintedElementRefNameToPropertyRefs.put(newTuple, propertyRef);
							return;
						}
					}
					hintedElementRefNameToPropertyRefs.removeValue(propertyRef);
				}
			}
		});

		setRootHintedElementRef(true);
	}

	public void handleXArchFlatEvent(XArchFlatEvent evt) {
		hintedElementRefTracker.handleXArchFlatEvent(evt);
		propertyRefTracker.handleXArchFlatEvent(evt);

		if (!evt.getXArchRef().equals(xArchRef) || evt.getIsExtra()) {
			return;
		}

		XArchPath sourceTargetPath = evt.getSourceTargetPath();
		if (sourceTargetPath.toTagsOnlyString().startsWith("xArch/renderingHints3")) {
			if ("xArch/renderingHints3/hintedElement".startsWith(sourceTargetPath.toTagsOnlyString())) {
				setRootHintedElementRef(false);
			}
		}
	}

	private void setRootHintedElementRef(boolean create) {
		ObjRef rootHintedElementRef = getRootHintedElementRef(create);
		if (!XAdlHintRepository.equalz(rootHintedElementRef, hintedElementRefTracker.getRootObjRef())) {
			hintedElementRefTracker.setTrackInfo(rootHintedElementRef, hintedElementRefTracker.getRelativePath());
			propertyRefTracker.setTrackInfo(rootHintedElementRef, propertyRefTracker.getRelativePath());
			if (rootHintedElementRef != null) {
				hintedElementRefTracker.startScanning();
				propertyRefTracker.startScanning();
			}
		}
	}

	private ObjRef getRootHintedElementRef(boolean create) {
		ObjRef rootHintedElementRef = null;
		ObjRef renderingHints3ElementRef = xarch.getElement(hintsContextRef, "RenderingHints3", xArchRef);
		if (renderingHints3ElementRef == null) {
			if (!create) {
				return null;
			}
			renderingHints3ElementRef = xarch.createElement(hintsContextRef, "RenderingHints3");
			xarch.add(xArchRef, "Object", renderingHints3ElementRef);
		}
		ObjRef hintsBundleRef = null;
		for (ObjRef objRef : xarch.getAll(renderingHints3ElementRef, "hintedElement")) {
			if (xarch.isInstanceOf(objRef, "hints3#HintBundle")) {
				if (xarch.has(objRef, "maintainer", maintainer)) {
					if (xarch.has(objRef, "version", version)) {
						hintsBundleRef = objRef;
						break;
					}
				}
			}
		}
		if (hintsBundleRef == null) {
			if (!create) {
				return null;
			}
			hintsBundleRef = xarch.create(hintsContextRef, "HintBundle");
			xarch.set(hintsBundleRef, "maintainer", maintainer);
			xarch.set(hintsBundleRef, "version", version);
			xarch.add(renderingHints3ElementRef, "hintedElement", hintsBundleRef);
		}
		String rootElementId = XadlUtils.getID(xarch, rootElementRef);
		for (ObjRef objRef : xarch.getAll(hintsBundleRef, "hintedElement")) {
			ObjRef targetRef = (ObjRef) xarch.get(objRef, "target");
			if (targetRef != null) {
				String href = (String) xarch.get(targetRef, "href");
				if (href != null && href.startsWith("#") && href.substring(1).equals(rootElementId)) {
					rootHintedElementRef = objRef;
					break;
				}
			}
		}
		if (rootHintedElementRef == null) {
			if (!create) {
				return null;
			}
			rootHintedElementRef = xarch.create(hintsContextRef, "HintedElement");
			XadlUtils.setXLink(xarch, rootHintedElementRef, "target", rootElementRef);
			xarch.add(hintsBundleRef, "hintedElement", rootHintedElementRef);
		}
		return rootHintedElementRef;
	}

	private ObjRef getHintedElementRef(String id, boolean create) {
		synchronized (idToHintedElementRefs) {
			ObjRef hintedElementRef = idToHintedElementRefs.get(id);
			if (hintedElementRef != null) {
				return hintedElementRef;
			}

			/*
			 * We check for new values since we may not have gotten the flat event yet that would inform us that the
			 * hint is available.
			 */
			ObjRef rootObjRef = hintedElementRefTracker.getRootObjRef();
			if (rootObjRef != null) {
				hintedElementRefTracker.rescan();
				hintedElementRef = idToHintedElementRefs.get(id);
				if (hintedElementRef != null && xarch.hasAncestor(hintedElementRef, rootObjRef)) {
					return hintedElementRef;
				}

				if (create) {
					hintedElementRef = xarch.create(hintsContextRef, "HintedElement");
					XadlUtils.setXLink(xarch, hintedElementRef, "target", id);
					idToHintedElementRefs.put(id, hintedElementRef);
					xarch.add(rootObjRef, "hintedElement", hintedElementRef);
				}
			}
			return hintedElementRef;
		}
	}

	private ObjRef getPropertyRef(String id, String name, boolean create) {
		synchronized (hintedElementRefNameToPropertyRefs) {
			ObjRef hintedElementRef = getHintedElementRef(id, create);
			if (hintedElementRef != null) {

				//TODO: remove following line once synchronous flat listeners are enabled
				propertyRefTracker.rescan(hintedElementRef);

				Tuple key = new Tuple(hintedElementRef, name);
				ObjRef propertyRef = hintedElementRefNameToPropertyRefs.get(key);
				if (propertyRef != null && propertyRefTracker.getRootObjRef() != null
						&& xarch.hasAncestor(propertyRef, propertyRefTracker.getRootObjRef())) {
					return propertyRef;
				}

				if (create) {
					propertyRef = xarch.create(hintsContextRef, "Property");
					xarch.set(propertyRef, "name", name);
					hintedElementRefNameToPropertyRefs.put(key, propertyRef);
					xarch.add(hintedElementRef, "property", propertyRef);
					return propertyRef;
				}
			}
			return null;
		}
	}

	private Object getValue(String xArchID, String name) throws PropertyDecodeException {
		ObjRef propertyRef = getPropertyRef(xArchID, name, false);
		if (propertyRef != null) {
			ObjRef valueRef = (ObjRef) xarch.get(propertyRef, "value");
			if (valueRef != null) {
				String type = (String) xarch.get(valueRef, "type");
				String data = (String) xarch.get(valueRef, "data");
				return propertyCoder.decode(null, new BasicEncodedValue(type, data));
			}
		}
		return null;
	}

	private boolean setValue(String xArchID, String name, Object value) {
		ObjRef propertyRef = getPropertyRef(xArchID, name, true);
		if (propertyRef != null) {
			ObjRef valueRef = (ObjRef) xarch.get(propertyRef, "value");
			if (valueRef == null) {
				valueRef = xarch.create(hintsContextRef, "PropertyValue");
				ignoreObjRefUpdates.add(propertyRef);
				xarch.set(propertyRef, "value", valueRef);
			}

			IEncodedValue ev = new BasicEncodedValue();
			if (propertyCoder.encode(null, ev, value)) {
				if (!xarch.has(valueRef, "type", ev.getType())) {
					ignoreObjRefUpdates.add(propertyRef);
					xarch.set(valueRef, "type", ev.getType());
				}
				if (!xarch.has(valueRef, "data", ev.getData())) {
					ignoreObjRefUpdates.add(propertyRef);
					xarch.set(valueRef, "data", ev.getData());
				}
				return true;
			}
		}
		return false;
	}

	public synchronized Object getContextForAssembly(IBNAWorld world, IAssembly assembly) {
		IThing rootThing = assembly.getRootThing();
		Object context = rootThing.getProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME);
		if (context == null && rootThing.hasProperty(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME)) {
			IAssembly partAssembly = AssemblyUtils.getAssemblyWithPart(rootThing);
			if (partAssembly != null) {
				context = getContextForAssembly(world, partAssembly);
			}
		}
		return context;
	}

	public synchronized IAssembly[] getAssembliesForContext(IBNAWorld world, Object xArchID) {
		return AssemblyUtils.getAssembliesWithRoots(tptl.getThings(ArchipelagoUtils.XARCH_ID_PROPERTY_NAME, xArchID));
	}

	public synchronized String[] getStoredHintNames(Object xArchID) {
		Collection<String> hintNames = new ArrayList<String>();
		ObjRef hintedElementRef = getHintedElementRef((String) xArchID, false);
		if (hintedElementRef != null) {
			for (ObjRef objRef : xarch.getAll(hintedElementRef, "property")) {
				String name = (String) xarch.get(objRef, "name");
				if (name != null) {
					hintNames.add(name);
				}
			}
		}
		return hintNames.toArray(new String[hintNames.size()]);
	}

	public synchronized Object getHint(Object xArchID, String hintName) {
		try {
			if (XAdlHintRepository.DEBUG) {
				System.err.println("Getting   : " + hintName);
			}
			Object value = getValue((String) xArchID, hintName);
			if (XAdlHintRepository.DEBUG) {
				System.err.println("Got       : " + hintName + " " + value);
			}
			return value;
		}
		catch (PropertyDecodeException e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized void storeHint(Object xArchID, String hintName, Object value) {
		if (XAdlHintRepository.DEBUG) {
			System.err.println("Storing   : " + hintName + " " + value + " for " + xArchID);
		}
		setValue((String) xArchID, hintName, value);
	}

	ListenerList<IHintRepositoryChangeListener> changeListeners = new ListenerList<IHintRepositoryChangeListener>(
			IHintRepositoryChangeListener.class);

	public void addHintRepositoryChangeListener(IHintRepositoryChangeListener l) {
		changeListeners.add(l);
	}

	public void removeHintRepositoryChangeListener(IHintRepositoryChangeListener l) {
		changeListeners.remove(l);
	}

	Executor e = null;

	protected void fireHintRepositoryChangeEvent(final Object context, final String hintName) {
		if (e == null) {
			e = Executors.newSingleThreadExecutor();
		}
		e.execute(new Runnable() {

			public void run() {
				for (IHintRepositoryChangeListener changeListener : changeListeners.getListeners()) {
					changeListener.hintRepositoryChanged(XAdlHintRepository.this, context, hintName);
				}
			}
		});
	}

	protected void fireHintRepositoryChangeEvent(ObjRef hintedElementRef, ObjRef propertyRef) {
		ObjRef targetRef = (ObjRef) xarch.get(hintedElementRef, "target");
		if (targetRef != null) {
			String href = (String) xarch.get(targetRef, "href");
			if (href != null && href.startsWith("#")) {
				String context = href.substring(1);
				fireHintRepositoryChangeEvent(context, propertyRef != null ? (String) xarch.get(propertyRef, "name")
						: null);
			}
		}
	}
}
