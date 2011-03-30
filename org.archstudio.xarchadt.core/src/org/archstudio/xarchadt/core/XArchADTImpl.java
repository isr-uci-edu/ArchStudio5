package org.archstudio.xarchadt.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringBufferInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.xml.sax.SAXException;

import org.archstudio.xarchadt.common.BasicXArchADTFactoryElementMetadata;
import org.archstudio.xarchadt.common.BasicXArchADTFactoryMetadata;
import org.archstudio.xarchadt.common.BasicXArchADTFeature;
import org.archstudio.xarchadt.common.BasicXArchADTTypeMetadata;
import org.archstudio.xarchadt.common.IXArchADT;
import org.archstudio.xarchadt.common.IXArchADTExtensionHint;
import org.archstudio.xarchadt.common.IXArchADTFactoryElementMetadata;
import org.archstudio.xarchadt.common.IXArchADTFactoryMetadata;
import org.archstudio.xarchadt.common.IXArchADTFeature;
import org.archstudio.xarchadt.common.IXArchADTFileListener;
import org.archstudio.xarchadt.common.IXArchADTModelListener;
import org.archstudio.xarchadt.common.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.xarchadt.common.XArchADTFileEvent;
import org.archstudio.xarchadt.common.XArchADTModelEvent;
import org.archstudio.xarchadt.common.XArchADTPath;
import org.archstudio.xarchadt.common.IXArchADTFeature.FeatureType;
import org.archstudio.xarchadt.common.XArchADTFileEvent.EventType;
import org.archstudio.sysutils.SystemUtils;

public class XArchADTImpl implements IXArchADT {
	protected Collection<IXArchADTModelListener> modelListeners = new CopyOnWriteArrayList<IXArchADTModelListener>();
	protected Collection<IXArchADTFileListener> fileListeners = new CopyOnWriteArrayList<IXArchADTFileListener>();

	// All the resources here indexed by URI
	private final ResourceSet resourceSet;

	private final Object tableLock = new Object();

	// Map ObjRef -> Objects
	// private HashMap objects = new HashMap(5000, 0.60f);
	private final Map<ObjRef, EObject> objects = new HashMap<ObjRef, EObject>(5000, 0.60f);

	// Map Objects -> ObjRefs (for reverse-lookup purposes)...
	// private HashMap reverseObjects = new HashMap(5000, 0.60f);
	private final Map<EObject, ObjRef> reverseObjects = new HashMap<EObject, ObjRef>(5000, 0.60f);

	private static final Map<Object,Object> LOAD_MAP = Collections.emptyMap();

	private static final ElementHandlerImpl elementHandlerImpl = new ElementHandlerImpl(false);
	private static final Map<Object,Object> SAVE_MAP = new HashMap<Object,Object>();
	static{
		// This voodoo supports substitution groups properly.
		SAVE_MAP.put(XMLResource.OPTION_ELEMENT_HANDLER, elementHandlerImpl);
	}
	

	public XArchADTImpl() {
		resourceSet =  new ResourceSetImpl();
		resourceSet.eAdapters().add(new ContentAdapter());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new GenericXMLResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getContentTypeToFactoryMap().put("xml", new GenericXMLResourceFactoryImpl());
	}

	private ObjRef put(EObject o) {
		return put(o, null);
	}

	private void invalidateAllObjRefs(URI uri) {
		synchronized (tableLock) {
			Collection<ObjRef> markedForDeath = new HashSet<ObjRef>();

			for (ObjRef objRef : objects.keySet()) {
				EObject obj = get(objRef);
				if ((obj.eResource() != null) && (obj.eResource().getURI().equals(uri))) {
					markedForDeath.add(objRef);
				}
			}
			for (ObjRef objRef : markedForDeath) {
				reverseObjects.remove(objects.get(objRef));
				objects.remove(objRef);
			}
		}
	}

	private ObjRef put(EObject o, String objRefUid) {
		synchronized (tableLock) {
			ObjRef alreadyInThere = (ObjRef) reverseObjects.get(o);
			if (alreadyInThere != null) {
				// System.out.println("That was already in there! : " + o);
				return alreadyInThere;
			}
			ObjRef objRef = new ObjRef();
			objects.put(objRef, o);
			reverseObjects.put(o, objRef);
			return objRef;
		}
	}

	private EObject get(ObjRef ref) {
		synchronized (tableLock) {
			EObject o = objects.get(ref);
			if (o == null) {
				// System.out.println("Table is: " + objects);
				throw new IllegalArgumentException("No such object: " + ref);
			}
			return o;
		}
	}

	public boolean isValidObjRef(ObjRef ref) {
		synchronized (tableLock) {
			Object o = objects.get(ref);
			if (o != null) {
				return true;
			}
			else {
				return false;
			}
		}
	}

	@Override
	public void add(ObjRef baseObjectRef, String typeOfThing, ObjRef thingToAddRef) {
		add(baseObjectRef, typeOfThing, Collections.singletonList(thingToAddRef));
	}

	@Override
	@SuppressWarnings("unchecked")
	public void add(ObjRef baseObjectRef, String typeOfThing, Collection<ObjRef> thingsToAddRefs) {
		Object baseObject = get(baseObjectRef);

		// We get everything in the list here so we have a basically atomic
		// operation
		// and we don't abort halfway through adding things if some ObjRef is
		// invalid.
		List<Object> thingsToAddList = new ArrayList<Object>(thingsToAddRefs.size());
		for (ObjRef thingToAddRef : thingsToAddRefs) {
			thingsToAddList.add(get(thingToAddRef));
		}

		String methodName = "get" + SystemUtils.capFirst(typeOfThing);

		try {
			Method m = getMethod(baseObject.getClass(), methodName);

			// Get the list of things
			Object listObject = m.invoke(baseObject);

			if (!(listObject instanceof EList)) {
				throw new IllegalArgumentException("Method: " + methodName + " on type " + baseObject.getClass().getCanonicalName()
				        + " does not return a value of type EList");
			}
			for (Object thingToAdd : thingsToAddList) {
				((EList) listObject).add(thingToAdd);
			}
		}
		catch (NoSuchMethodException nsme) {
			throw new IllegalArgumentException("Can't find method: " + methodName + " on object of class " + baseObject.getClass().getCanonicalName());
		}
		catch (InvocationTargetException ite) {
			throw new RuntimeException(ite);
		}
		catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}

	}

	@Override
	public void clear(ObjRef baseObjectRef, String typeOfThing) {
		Object baseObject = get(baseObjectRef);

		Class<?> factoryClass = baseObject.getClass();
		String methodName = "set" + SystemUtils.capFirst(typeOfThing);

		try {
			Method m = getMethod(factoryClass, methodName);
			m.invoke(baseObject, (Object) null);
		}
		catch (NoSuchMethodException nsme) {
			throw new IllegalArgumentException("Can't find method: " + methodName + " on object of class " + factoryClass.getName());
		}
		catch (InvocationTargetException ite) {
			throw new RuntimeException(ite);
		}
		catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}
	}

	static int copyNonce = 0;
	
	@Override
	public ObjRef cloneElement(ObjRef targetObjectRef) {
		EObject eobject = get(targetObjectRef);
		
		EcoreUtil.Copier xadlCopier = new EcoreUtil.Copier() {
			@Override
			protected void copyAttribute(EAttribute eAttribute, EObject eObject, EObject copyEObject) {
				// don't clone IDs.
				if (eAttribute.getName().equals("id")) {
					String id = (String)eObject.eGet(eAttribute);
					if (id == null) {
						id = "";
					}
					id += "c" + copyNonce;
					copyNonce++;
					copyEObject.eSet(eAttribute, id);
				}
				else {
					super.copyAttribute(eAttribute, eObject, copyEObject);
				}
			}
		};
		
		EObject result = xadlCopier.copy(eobject);
		xadlCopier.copyReferences();
		
		ObjRef clonedRef = put(result);
		return clonedRef;
	}

	@Override
	public ObjRef cloneDocument(URI oldURI, URI newURI) {
		try {
			loadFromString(newURI, serialize(oldURI));
			return getDocumentRootRef(newURI);
		}
		catch (SAXException ioe) {
			throw new RuntimeException("This shouldn't happen.");
		}
		catch (IOException ioe) {
			throw new RuntimeException("This shouldn't happen.");
		}
	}

	@Override
	public List<URI> getOpenURIs() {
		List<URI> uriList = new ArrayList<URI>();
		for (Resource r : resourceSet.getResources()) {
			uriList.add(r.getURI());
		}
		return uriList;
	}
	
	@Override
	public ObjRef getDocumentRootRef(URI uri) {
		Resource r = resourceSet.getResource(uri, false);
		if (r == null) {
			throw new IllegalArgumentException("No such resource at URI: " + uri);
		}
		return put(r.getContents().get(0));
	}

	@Override
	public ObjRef getDocumentRootRef(ObjRef ref) {
		EObject obj = get(ref);
		return getDocumentRootRef(obj.eResource().getURI());
	}
	
	@Override
	public void close(URI uri) {
		invalidateAllObjRefs(uri);
		Resource r = resourceSet.getResource(uri, false);
		setResourceFinishedLoading(r, false);
		r.unload();
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_CLOSED_EVENT, uri));
	}
	
	protected Map<String, EFactory> getFactoryMap() {
		return Factories.getInstance().getFactoryMap();
	}

	@Override
	public ObjRef create(String factoryName, String typeOfThing) {
		Map<String, EFactory> factoryMap = getFactoryMap();
		Object factory = factoryMap.get(factoryName);

		if (factory == null) {
			throw new IllegalArgumentException("Invalid factory name: " + factoryName);
		}

		Class<?> factoryClass = factory.getClass();
		String methodName = "create" + SystemUtils.capFirst(typeOfThing);

		try {
			Method m = factoryClass.getMethod(methodName);
			EObject eobject = (EObject) m.invoke(factory);
			return put(eobject);
		}
		catch (NoSuchMethodException nsme) {
			throw new IllegalArgumentException("Can't find method: " + methodName + " on object of class " + factoryClass.getName());
		}
		catch (InvocationTargetException ite) {
			throw new RuntimeException(ite);
		}
		catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}
	}

	@Override
	public ObjRef createDocument(URI uri) {
		for (Resource r : resourceSet.getResources()) {
			if (r.getURI().equals(uri)) {
				// It already exists
				return put(r.getContents().get(0));
			}
		}

		Resource r = resourceSet.createResource(uri, "xml");
		
		EObject newDocumentRoot = Factories.getInstance().createNewDocumentRoot();
		ObjRef documentRootRef = put(newDocumentRoot);
		ObjRef xADLRef = create(Factories.MAIN_FACTORY_PACKAGE_NAME, "XADLType");
		set(documentRootRef, "xADL", xADLRef);

		setResourceFinishedLoading(r, true);

		EObject documentRootObject = get(documentRootRef);
		r.getContents().add(documentRootObject);
		
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_CREATED_EVENT, uri, documentRootRef));
		return documentRootRef;
	}

	@Override
	public ObjRef load(URI uri) throws SAXException, IOException {
		for (Resource r : resourceSet.getResources()) {
			if (r.getURI().equals(uri)) {
				// Possibly already open
				if(r.isLoaded()){
					// Already open
					return put(r.getContents().get(0));
				}
			}
		}

		Resource newResource = resourceSet.getResource(uri, true);
		newResource.load(LOAD_MAP);
		setResourceFinishedLoading(newResource, true);

		System.err.println("newresource type = " + newResource.getContents().get(0).getClass());
		
		ObjRef rootElementRef = put(newResource.getContents().get(0));
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_OPENED_EVENT, uri, rootElementRef));
		return rootElementRef;
	}

	public ObjRef loadFromString(URI uri, String s) throws SAXException, IOException {
		Resource r = resourceSet.createResource(uri);
		r.load(new StringBufferInputStream(s), LOAD_MAP);
		setResourceFinishedLoading(r, true);

		ObjRef rootElementRef = put(r.getContents().get(0));
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_OPENED_EVENT, uri, rootElementRef));
		return rootElementRef;
	}

	@Override
	public void remove(ObjRef baseObjectRef, String typeOfThing, ObjRef thingToRemove) {
		remove(baseObjectRef, typeOfThing, Collections.singleton(thingToRemove));
	}

	@Override
	@SuppressWarnings("unchecked")
	public void remove(ObjRef baseObjectRef, String typeOfThing, Collection<ObjRef> thingsToRemoveRefs) {
		Object baseObject = get(baseObjectRef);

		// We get everything in the list here so we have a basically atomic
		// operation
		// and we don't abort halfway through adding things if some ObjRef is
		// invalid.
		List<Object> thingsToRemoveList = new ArrayList<Object>(thingsToRemoveRefs.size());
		for (ObjRef thingToRemoveRef : thingsToRemoveRefs) {
			thingsToRemoveList.add(get(thingToRemoveRef));
		}

		String methodName = "get" + SystemUtils.capFirst(typeOfThing);

		try {
			Method m = getMethod(baseObject.getClass(), methodName);

			// Get the list of things
			Object listObject = m.invoke(baseObject);

			if (!(listObject instanceof EList)) {
				throw new IllegalArgumentException("Method: " + methodName + " on type " + baseObject.getClass().getCanonicalName()
				        + " does not return a value of type EList");
			}
			for (Object thingToRemove : thingsToRemoveList) {
				((EList) listObject).remove(thingToRemove);
			}
		}
		catch (NoSuchMethodException nsme) {
			throw new IllegalArgumentException("Can't find method: " + methodName + " on object of class " + baseObject.getClass().getCanonicalName());
		}
		catch (InvocationTargetException ite) {
			throw new RuntimeException(ite);
		}
		catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}

	}

	@Override
	public void renameXArch(String oldURI, String newURI) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public URI getURI(ObjRef ref){
		EObject obj = get(ref);
		if(obj.eResource() == null) return null;
		return obj.eResource().getURI();
	}

	@Override
	public void save(URI uri) throws IOException {
		Resource r = resourceSet.getResource(uri, false);
		r.save(SAVE_MAP);
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_SAVED_EVENT, uri, getDocumentRootRef(uri)));
	}

	@Override
	public String serialize(URI uri) {
		Resource r = resourceSet.getResource(uri, false);

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			r.save(baos, SAVE_MAP);
			return new String(baos.toByteArray());
		}
		catch (IOException ioe) {
			throw new RuntimeException("This shouldn't happen", ioe);
		}
	}

	@Override
	public void set(ObjRef baseObjectRef, String typeOfThing, ObjRef valueRef) {
		Object baseObject = get(baseObjectRef);
		Object value = get(valueRef);

		Class<?> factoryClass = baseObject.getClass();
		String methodName = "set" + SystemUtils.capFirst(typeOfThing);

		try {
			Method m = getMethod(factoryClass, methodName);
			// Method m = factoryClass.getMethod(methodName, value.getClass());
			m.invoke(baseObject, value);
		}
		catch (NoSuchMethodException nsme) {
			throw new IllegalArgumentException("Can't find method: " + methodName + " on object of class " + factoryClass.getName());
		}
		catch (InvocationTargetException ite) {
			throw new RuntimeException(ite);
		}
		catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}
	}

	@Override
	public void set(ObjRef baseObjectRef, String typeOfThing, Object value) {
		Object baseObject = get(baseObjectRef);

		Class<?> factoryClass = baseObject.getClass();
		String methodName = "set" + SystemUtils.capFirst(typeOfThing);

		try {
			Method m = getMethod(factoryClass, methodName);
			m.invoke(baseObject, value);
		}
		catch (NoSuchMethodException nsme) {
			throw new IllegalArgumentException("Can't find method: " + methodName + " on object of class " + factoryClass.getName());
		}
		catch (InvocationTargetException ite) {
			throw new RuntimeException(ite);
		}
		catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}
	}

	@Override
	public boolean equals(ObjRef ref1, ObjRef ref2) {
		EObject o1 = get(ref1);
		EObject o2 = get(ref2);
		
		return o1.equals(o2);
	}
	
	@Override
	public List<ObjRef> get(ObjRef baseObjectRef, String typeOfThing, List<String> ids) {
		List<ObjRef> matchingRefs = new ArrayList<ObjRef>();
		
		for (String id : ids) {
			List<ObjRef> allRefs = getAll(baseObjectRef, typeOfThing);
			for (ObjRef ref : allRefs) {
				Object idObject = get(ref, "id");
				if ((idObject instanceof String) && (id.equals((String)idObject))) {
					matchingRefs.add(ref);
					break;
				}
			}
			matchingRefs.add(null);
		}
		return matchingRefs;
	}

	@Override
	public ObjRef get(ObjRef baseObjectRef, String typeOfThing, String id) {
		List<ObjRef> allRefs = getAll(baseObjectRef, typeOfThing);
		for (ObjRef ref : allRefs) {
			Object idObject = get(ref, "id");
			if ((idObject instanceof String) && (id.equals((String)idObject))) {
				return ref;
			}
		}
		return null;
	}

	@Override
	public Object get(ObjRef baseObjectRef, String typeOfThing) {
		Object baseObject = get(baseObjectRef);

		Class<?> factoryClass = baseObject.getClass();
		String methodName = "get" + SystemUtils.capFirst(typeOfThing);

		try {
			Method m = null;
			try {
				m = getMethod(baseObject.getClass(), methodName);
			}
			catch(NoSuchMethodException nsme2) {
				methodName = "is" + SystemUtils.capFirst(typeOfThing);
				m = getMethod(baseObject.getClass(), methodName);
			}
			
			Object retVal = m.invoke(baseObject);
			if (retVal instanceof EList) {
				throw new IllegalArgumentException("Call getAll() for methods that return value lists.");
			}
			else if (retVal instanceof EObject) {
				return put((EObject) retVal);
			}
			else {
				return retVal;
			}
		}
		catch (NoSuchMethodException nsme) {
			throw new IllegalArgumentException("Can't find method: " + methodName + " on object of class " + factoryClass.getName());
		}
		catch (InvocationTargetException ite) {
			throw new RuntimeException(ite);
		}
		catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}
	}

	@Override
	public List<ObjRef> getAll(ObjRef baseObjectRef, String typeOfThing) {
		Object baseObject = get(baseObjectRef);

		String methodName = "get" + SystemUtils.capFirst(typeOfThing);

		try {
			Method m = getMethod(baseObject.getClass(), methodName);
			Object retVal = m.invoke(baseObject);
			if (!(retVal instanceof EList)) {
				throw new IllegalArgumentException("Method " + methodName + " on class " + baseObject.getClass().getCanonicalName()
				        + " did not return an EList");
			}

			List<ObjRef> refList = new ArrayList<ObjRef>(((EList) retVal).size());

			for (Object o : ((EList) retVal)) {
				refList.add(put((EObject) o));
			}
			return refList;
		}
		catch (NoSuchMethodException nsme) {
			throw new IllegalArgumentException("Can't find method: " + methodName + " on object of class " + baseObject.getClass().getName());
		}
		catch (InvocationTargetException ite) {
			throw new RuntimeException(ite);
		}
		catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}
	}

	@Override
	public List<ObjRef> getAllAncestors(ObjRef targetObjectRef) {
		List<ObjRef> ancestorList = new ArrayList<ObjRef>();
		
		EObject currentObject = get(targetObjectRef);
		while (currentObject != null) {
			ancestorList.add(put(currentObject));
			currentObject = currentObject.eContainer();
		}
		return ancestorList;
	}

	@Override
	public ObjRef getByID(ObjRef documentRef, String id) {
		EObject documentObject = get(documentRef);
		Resource r = documentObject.eResource();
		EObject objectByID = r.getEObject(id);
		if(objectByID == null) {
			return null;
		}
		return put(objectByID);
	}

	@Override
	public ObjRef getByID(String id) {
		for (Resource r : resourceSet.getResources()) {
			EObject objectByID = r.getEObject(id);
			if(objectByID != null){
				return put(objectByID);
			}
		}
		return null;
	}

	@Override
	public List<String> getAvailableFactories() {
		List<String> factoryList = new ArrayList<String>();
		factoryList.addAll(getFactoryMap().keySet());
		return factoryList;
	}

	@Override
	public ObjRef getParent(ObjRef targetObjectRef) {
		EObject targetObject = get(targetObjectRef);
		EObject container = targetObject.eContainer();
		return put(container);
	}

	@Override
	public String getType(ObjRef baseObjectRef) {
		EObject eo = get(baseObjectRef);
		return eo.getClass().getCanonicalName();
	}

	@Override
	public boolean hasAncestor(ObjRef childRef, ObjRef ancestorRef) {
		EObject childObject = get(childRef);
		EObject ancestorObject = get(ancestorRef);
		
		EObject targetObject = childObject;
		while(targetObject != null){
			if(targetObject.equals(ancestorObject)){
				return true;
			}
			targetObject = targetObject.eContainer();
		}
		return false;
	}

	@Override
	public boolean isAssignable(String toType, String fromType) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAttached(ObjRef childRef) {
		EObject obj = get(childRef);
		while (obj != null) {
			for (Class<?> interfaceClass : obj.getClass().getInterfaces()) {
				if (interfaceClass.getName().equals("org.archstudio.xadl3.xadlcore_3_0.XADLType")) {
					return true;
				}
			}
			obj = obj.eContainer();
		}
		return false;
	}
	
	private Class<?> findClass(String type) throws ClassNotFoundException {
		try {
			return Class.forName(type);
		}
		catch (ClassNotFoundException cnfe) {
			Class<?> c = Factories.getInstance().findClass(type);
			if (c == null) {
				throw cnfe;
			}
			return c;
		}
	}

	@Override
	public boolean isInstanceOf(ObjRef baseObjectRef, String type) {
		Object o = get(baseObjectRef);
		try{
			Class<?> c = findClass(type);
			return c.isAssignableFrom(o.getClass());
		}
		catch(ClassNotFoundException cnfe) {
			throw new IllegalArgumentException("Can't find type: " + type);
		}
	}
	
	@Override
	public boolean isInstanceOf(IXArchADTFeature feature, String type) {
		try{
			Class<?> c = findClass(type);
			return c.isAssignableFrom(feature.getFeatureClass());
		}
		catch(ClassNotFoundException cnfe) {
			throw new IllegalArgumentException("Can't find type: " + type);
		}
	}

	@Override
	public String getTagName(ObjRef ref){
		//NB: Sometimes returns null!
		EObject eobject = get(ref);
		EStructuralFeature sf = elementHandlerImpl.getRoot(ExtendedMetaData.INSTANCE, eobject.eClass());
		if(sf == null){
			return null;
		}
		return sf.getName();
	}
	
	@Override
	public String getContainingFeatureName(ObjRef ref){
		EObject eobject = get(ref);
		EStructuralFeature containingFeature = eobject.eContainingFeature();
		if(containingFeature == null){
			return null;
		}
		return containingFeature.getName();
	}
	
	@Override
	public String getTagsOnlyPathString(ObjRef ref){
		List<String> tags = new ArrayList<String>();
		EObject eobject = get(ref);
		while(eobject != null){
			EStructuralFeature sf = elementHandlerImpl.getRoot(ExtendedMetaData.INSTANCE, eobject.eClass());
			if(sf != null){
				tags.add(sf.getName());
			}
			else if(eobject.eContainer() != null){
				tags.add(eobject.eContainingFeature().getName());
			}
			else{
				break;
			}
			eobject = eobject.eContainer();
		}
		StringBuilder sb = new StringBuilder();
		for(int i = tags.size() - 1; i >= 0; i--) {
			sb.append(tags.get(i));
			if(i > 0){
				sb.append("/");
			}
		}
		return sb.toString();
	}
	
	@Override
	public XArchADTPath getPath(ObjRef ref){
		List<String> containingFeatureNames = new ArrayList<String>();
		List<Integer> tagIndexes = new ArrayList<Integer>();
		List<String> ids = new ArrayList<String>();
		
		EObject currentObj = get(ref);
		
		while(currentObj != null){
			
			EStructuralFeature containingFeature = currentObj.eContainingFeature();
			if(containingFeature == null) break;
			String containingFeatureName = containingFeature.getName();
			String id = null;
			EStructuralFeature idFeature = currentObj.eClass().getEStructuralFeature("id");
			if(idFeature != null){
				id = (String)currentObj.eGet(idFeature);
			}
			int index = -1;
			EObject parentObj = currentObj.eContainer();
			if(parentObj != null){
				Object containingObject = parentObj.eGet(containingFeature);
				if(containingObject instanceof EList){
					EList list = (EList)containingObject;
					index = list.indexOf(currentObj);
				}
			}
			
			containingFeatureNames.add(containingFeatureName);
			ids.add(id);
			tagIndexes.add(index);
			
			currentObj = parentObj;
		}
		Collections.reverse(containingFeatureNames);
		Collections.reverse(tagIndexes);
		Collections.reverse(ids);
		
		return new XArchADTPath(containingFeatureNames, tagIndexes, ids);
	}

	@Override
	public ObjRef resolveHref(ObjRef documentRef, String href) {
		// TODO Do this right
		if (href.startsWith("#")) {
			String id = href.substring(1);
			return getByID(documentRef, id);
		}
		throw new IllegalArgumentException("resolveHref only supports local hrefs right now.");
	}

	protected void fireXArchADTModelEvent(XArchADTModelEvent evt) {
		for(IXArchADTModelListener l : modelListeners) {
			l.handleXArchADTModelEvent(evt);
		}
	}
	
	public void addXArchADTModelListener(IXArchADTModelListener l) {
		modelListeners.add(l);
	}

	public void removeXArchADTModelListener(IXArchADTModelListener l) {
		modelListeners.remove(l);
	}
	
	protected void fireXArchADTFileEvent(XArchADTFileEvent evt) {
		for(IXArchADTFileListener l : fileListeners) {
			l.handleXArchADTFileEvent(evt);
		}
	}

	public void addXArchADTFileListener(IXArchADTFileListener l) {
		fileListeners.add(l);
	}

	public void removeXArchADTFileListener(IXArchADTFileListener l) {
		fileListeners.remove(l);
	}

	private static Method getMethod(Class c, String name) throws NoSuchMethodException {
		Method[] methods = c.getMethods();
		for (Method m : methods) {
			if (m.getName().equals(name)) {
				return m;
			}
		}
		throw new NoSuchMethodException(name);
	}
	
	//This stuff is necessary to suppress the event flurry when a document is loaded.
	private Set<Resource> resourcesFinishedLoading = new CopyOnWriteArraySet<Resource>();
	
	private void setResourceFinishedLoading(Resource r, boolean isFinishedLoading){
		if(isFinishedLoading){
			resourcesFinishedLoading.add(r);
		}
		else{
			resourcesFinishedLoading.remove(r);
		}
	}
	
	class ContentAdapter extends EContentAdapter {
		public void notifyChanged(Notification notification) {
		    super.notifyChanged(notification);
		    if(notification.isTouch()) return;
		    
		    Object notifier = notification.getNotifier();
		    if(!(notifier instanceof EObject)){
		    	return;
		    }
		    if(!resourcesFinishedLoading.contains(((EObject)notifier).eResource())){
		    	return;
		    }
		    
		    ObjRef srcRef = put((EObject)notifier);
		    
		    XArchADTModelEvent.EventType evtType = null;
		    switch(notification.getEventType()){
		    case Notification.ADD:
		    	evtType = XArchADTModelEvent.EventType.ADD;
		    	break;
		    case Notification.REMOVE:
		    	evtType = XArchADTModelEvent.EventType.REMOVE;
		    	break;
		    case Notification.SET:
		    	evtType = XArchADTModelEvent.EventType.SET;
		    	break;
		    case Notification.UNSET:
		    	evtType = XArchADTModelEvent.EventType.CLEAR;
		    	break;
		    }
		    if(evtType == null){
		    	return;
		    }
		    
		    List<ObjRef> srcAncestors = getAllAncestors(srcRef);
		    XArchADTPath srcPath = getPath(srcRef);

		    String featureName = null;
		    Object feature = notification.getFeature();
		    if(feature instanceof EReference){
		    	featureName = ((EReference)feature).getName();
		    }
		    else if(feature instanceof EStructuralFeature){
		    	featureName = ((EStructuralFeature)feature).getName();
		    }
		    else{
		    	return;
		    }
		    
		    Object oldValue = notification.getOldValue();
		    XArchADTPath oldValuePath = null; 
		    
		    if(oldValue instanceof EObject){
		    	ObjRef oldValueRef = put((EObject)oldValue);
		    	oldValue = oldValueRef;
		    	
		    	oldValuePath = getPath(oldValueRef);
		    }		    
		    
		    Object newValue = notification.getNewValue();
		    XArchADTPath newValuePath = null; 
		    
		    if(newValue instanceof EObject){
		    	ObjRef newValueRef = put((EObject)newValue);
		    	newValue = newValueRef;
		    	
		    	newValuePath = getPath(newValueRef);
		    }
		    
		    XArchADTModelEvent evt = new XArchADTModelEvent(evtType, srcRef, srcAncestors, srcPath, featureName, oldValue, oldValuePath, newValue, newValuePath);
		    fireXArchADTModelEvent(evt);
		}
	}
	
	private String getFactoryName(EPackage ePackage) {
		String ePackageNsURI = ePackage.getNsURI();
		for (Map.Entry<String,EFactory> e : getFactoryMap().entrySet()) {
			if (e.getValue().getEPackage().getNsURI().equals(ePackageNsURI)) {
				return e.getKey();
			}
		}
		return null;
	}
	
	private IXArchADTTypeMetadata getTypeMetadata(EClass eclass) {
		String factoryName = getFactoryName(eclass.getEPackage());
		String instanceTypeName = eclass.getInstanceTypeName();
		String instanceClassName = eclass.getInstanceClassName();
		Class<?> instanceClass = eclass.getInstanceClass();
		List<IXArchADTFeature> featureList = new ArrayList<IXArchADTFeature>();
		
		for(EAttribute attribute : eclass.getEAllAttributes()) {
			String name = attribute.getName();
			FeatureType featureType = FeatureType.ATTRIBUTE;
			String featureClassName = attribute.getEType().getInstanceClassName();
			Class<?> featureClass  = attribute.getEType().getInstanceClass();
			featureList.add(new BasicXArchADTFeature(name, featureType, false, null, featureClassName, featureClass));
		}
		for(EReference reference : eclass.getEAllReferences()) {
			String name = reference.getName();
			FeatureType featureType = reference.isMany() ? FeatureType.ELEMENT_MULTIPLE : FeatureType.ELEMENT_SINGLE;
			String featureFactoryName = getFactoryName(reference.getEType().getEPackage());
			String featureClassName = reference.getEType().getInstanceClassName();
			Class<?> featureClass  = reference.getEType().getInstanceClass();
			featureList.add(new BasicXArchADTFeature(name, featureType, !reference.isContainment(), featureFactoryName, featureClassName, featureClass));
		}
		
		return new BasicXArchADTTypeMetadata(factoryName, instanceTypeName, instanceClassName, instanceClass, featureList);
	}
	
	@Override
	public IXArchADTTypeMetadata getTypeMetadata(ObjRef ref) {
		EObject o = get(ref);
		return getTypeMetadata(o.eClass());
	}
	
	@Override
	public IXArchADTFactoryMetadata getFactoryMetadata(String factoryName) {
		Object factory = getFactoryMap().get(factoryName);
		if(factory == null) {
			throw new IllegalArgumentException("Invalid factory name: " + factoryName);
		}
		
		List<IXArchADTFactoryElementMetadata> factoryElementMetadataList = new ArrayList<IXArchADTFactoryElementMetadata>();
		for(Method m : factory.getClass().getMethods()){
			if(m.getName().startsWith("create") && (m.getParameterTypes().length == 0)){
				String elementName = m.getName().substring("create".length());
				Class<?> elementClass = m.getReturnType();
				factoryElementMetadataList.add(new BasicXArchADTFactoryElementMetadata(elementName, elementClass));
			}
		}
		return new BasicXArchADTFactoryMetadata(factoryName, factoryElementMetadataList);
	}
	
	protected List<IXArchADTExtensionHint> allExtensionHints = null;

	public synchronized List<IXArchADTExtensionHint> getAllExtensionHints() {
		if (allExtensionHints == null) {
			allExtensionHints = ExtensionHintUtils.parseExtensionHints(getFactoryMap()); 
			System.err.println("extension hints = " + allExtensionHints);
		}
		return allExtensionHints;
	}
	
	public List<IXArchADTExtensionHint> getExtensionHintsForExtension(String extensionFactoryName, String extensionTypeName) {
		List<IXArchADTExtensionHint> matchingHints = new ArrayList<IXArchADTExtensionHint>();
		for (IXArchADTExtensionHint hint : getAllExtensionHints()) {
			if (extensionFactoryName.equals(hint.getExtensionFactoryName()) && extensionTypeName.equals(hint.getExtensionTypeName())) {
				matchingHints.add(hint);
			}
		}
		return matchingHints;
	}
	
	public List<IXArchADTExtensionHint> getExtensionHintsForTarget(String targetFactoryName, String targetTypeName) {
		List<IXArchADTExtensionHint> matchingHints = new ArrayList<IXArchADTExtensionHint>();
		for (IXArchADTExtensionHint hint : getAllExtensionHints()) {
			if (targetFactoryName.equals(hint.getTargetFactoryName()) && targetTypeName.equals(hint.getTargetTypeName())) {
				matchingHints.add(hint);
			}
		}
		return matchingHints;
	}

}
