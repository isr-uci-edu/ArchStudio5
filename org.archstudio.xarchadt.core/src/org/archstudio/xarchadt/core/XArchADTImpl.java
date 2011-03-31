package org.archstudio.xarchadt.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.common.BasicXArchADTFeature;
import org.archstudio.xarchadt.common.BasicXArchADTPackageMetadata;
import org.archstudio.xarchadt.common.BasicXArchADTTypeMetadata;
import org.archstudio.xarchadt.common.IXArchADT;
import org.archstudio.xarchadt.common.IXArchADTExtensionHint;
import org.archstudio.xarchadt.common.IXArchADTFeature;
import org.archstudio.xarchadt.common.IXArchADTFeature.FeatureType;
import org.archstudio.xarchadt.common.IXArchADTFeature.ValueType;
import org.archstudio.xarchadt.common.IXArchADTFileListener;
import org.archstudio.xarchadt.common.IXArchADTModelListener;
import org.archstudio.xarchadt.common.IXArchADTPackageMetadata;
import org.archstudio.xarchadt.common.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.xarchadt.common.XArchADTFileEvent;
import org.archstudio.xarchadt.common.XArchADTFileEvent.EventType;
import org.archstudio.xarchadt.common.XArchADTModelEvent;
import org.archstudio.xarchadt.common.XArchADTPath;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
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

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.collect.BiMap;
import com.google.common.collect.Collections2;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;

public class XArchADTImpl implements IXArchADT {
	protected final List<IXArchADTModelListener> modelListeners = new CopyOnWriteArrayList<IXArchADTModelListener>();
	protected final List<IXArchADTFileListener> fileListeners = new CopyOnWriteArrayList<IXArchADTFileListener>();

	// All the resources here indexed by URI
	private final ResourceSet resourceSet;

	private static final Map<Object, Object> LOAD_OPTIONS_MAP = new HashMap<Object, Object>();

	private static final ElementHandlerImpl elementHandlerImpl = new ElementHandlerImpl(false);
	private static final Map<Object, Object> SAVE_OPTIONS_MAP = new HashMap<Object, Object>();
	static {
		// This voodoo supports substitution groups properly.
		SAVE_OPTIONS_MAP.put(XMLResource.OPTION_ELEMENT_HANDLER, elementHandlerImpl);
	}

	private static final String EMF_EXTENSION_POINT_ID = "org.eclipse.emf.ecore.generated_package"; 
	
	public XArchADTImpl() {
		resourceSet = new ResourceSetImpl();
		resourceSet.eAdapters().add(new ContentAdapter());
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new GenericXMLResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getContentTypeToFactoryMap().put("xml", new GenericXMLResourceFactoryImpl());
	
		registerAllSchemaPackages();
	}
	
	/**
	 * This method causes the EPackage.eINSTANCE variable of each EMF-generated bundle (plugin)
	 * in Eclipse to be touched.  This causes the package to spontaneously register
	 * itself with the EPackage Registry, which is how we find out what EPackages
	 * are available on the system.  This is an EMF-ism that isn't easily avoided.
	 * 
	 * If you are running outside of ArchStudio (like in org.archstudio.description.Main,
	 * then these bundles will not be available.  What you have to do then is just read
	 * the eINSTANCE variable for all the EPackages you want to have available to you.
	 */
    private void registerAllSchemaPackages() {
    	IExtensionRegistry reg = Platform.getExtensionRegistry();
    	if (reg != null) {
    		// The Extension Registry can be null if we're running outside of Eclipse,
    		// as happens in, e.g., org.archstudio.description.Main
    		for (IConfigurationElement configurationElement : reg.getConfigurationElementsFor(EMF_EXTENSION_POINT_ID)) {
    			String packageClassName = configurationElement.getAttribute("class");
    			if (packageClassName != null) {
    				String bundleName = configurationElement.getDeclaringExtension().getContributor().getName();
    				try {
    					Class<?> packageClass = Platform.getBundle(bundleName).loadClass(packageClassName);
    					Field instanceField = packageClass.getDeclaredField("eINSTANCE");
    					/* EPackage ePackage = (EPackage) */ instanceField.get(packageClass);
    				}
    				catch (ClassNotFoundException cnfe) {
    					System.err.println(cnfe);
    				}
    				catch (NoSuchFieldException nsfe) {
    					System.err.println(nsfe);
    				}
    				catch (IllegalAccessException iae) {
    					System.err.println(iae);
    				}
    			}
    		}
    	}
	} 

	// Map ObjRef <-> EObjects
	private final Object objRefToEObjectLock = new Object();
	
	// The BiMap is a two-way map
	private final Map<ObjRef, EObject> objRefToEObject = HashBiMap.create();
	
	// Alias the BiMap's inverse rather than maintaining it internally.
	private final Map<EObject, ObjRef> eObjectToObjRef = ((BiMap<ObjRef, EObject>)objRefToEObject).inverse();

	private ObjRef put(EObject eObject) {
		synchronized (objRefToEObjectLock) {
			ObjRef objRef = eObjectToObjRef.get(eObject);
			if (objRef == null && eObject != null) {
				eObjectToObjRef.put(eObject, objRef = new ObjRef());
			}
			return objRef;
		}
	}

	private List<ObjRef> put(Collection<EObject> eObjects) {
		List<ObjRef> objRefs = Lists.newArrayListWithCapacity(eObjects.size());
		synchronized (objRefToEObjectLock) {
			for (EObject eObject : eObjects)
				objRefs.add(put(eObject));
			return objRefs;
		}
	}

	private EObject get(ObjRef objRef) {
		synchronized (objRefToEObjectLock) {
			EObject eObject = objRefToEObject.get(objRef);
			if (eObject == null && objRef != null) {
				throw new IllegalArgumentException("No such object: " + objRef);
			}
			return eObject;
		}
	}

	private List<EObject> get(Collection<ObjRef> objRefs) {
		List<EObject> eObjects = Lists.newArrayListWithCapacity(objRefs.size());
		synchronized (objRefToEObjectLock) {
			for (ObjRef objRef : objRefs)
				eObjects.add(get(objRef));
			return eObjects;
		}
	}

	private Object check(Object object) {
		if (object instanceof EObject) {
			return put((EObject) object);
		}
		return object;
	}

	// Dynamically maps feature names (either uppercase or lowercase) of an EClass to EStructuralFeatures.
	private static final ConcurrentMap<EClass, Map<String, EStructuralFeature>> autoCaselessFeature = new MapMaker().softValues().makeComputingMap(new Function<EClass, Map<String, EStructuralFeature>>() {
			Map<String, EStructuralFeature> structuralFeatures = Maps.newHashMap();
			Map<Integer, EStructuralFeature> featureIDs = Maps.newHashMap();

			@Override
			public Map<String, EStructuralFeature> apply(EClass eClass) {
				structuralFeatures = Maps.newHashMap();
				featureIDs = Maps.newHashMap();
				apply0(eClass);
				return structuralFeatures;
			}

			private void apply0(EClass eClass) {
				for (EStructuralFeature eStructuralFeature : eClass.getEStructuralFeatures()) {
					EStructuralFeature conflictingEStructuralFeature = featureIDs.put(
							eStructuralFeature.getFeatureID(), eStructuralFeature);
					if (conflictingEStructuralFeature != null) {
						throw new RuntimeException("Unexpected structural feature");
					}
					String name = eStructuralFeature.getName();
					structuralFeatures.put(SystemUtils.capFirst(name), eStructuralFeature);
					structuralFeatures.put(SystemUtils.uncapFirst(name), eStructuralFeature);
				}
				for (EClass eSuperClass : eClass.getESuperTypes())
					apply0(eSuperClass);
			}
		});

	private static final EStructuralFeature getEFeature(EClass eClass, String featureName) {
		EStructuralFeature eFeature = autoCaselessFeature.get(eClass).get(featureName);
		if (eFeature == null) {
			throw new IllegalArgumentException(SystemUtils.message(//
					"EClass '$0' does not contain EFeature '$1' in EPackage '$2'.",//
					eClass.getName(), featureName, eClass.getEPackage().getNsURI()));
		}
		return eFeature;
	}

	private static final EStructuralFeature getEFeature(EObject eObject, String featureName) {
		return getEFeature(eObject.eClass(), featureName);
	}

	@SuppressWarnings("unchecked")
	private static final EList<EObject> getEList(EObject eObject, String featureName) {
		try {
			return (EList<EObject>) (eObject.eGet(getEFeature(eObject.eClass(), featureName)));
		}
		catch (ClassCastException e) {
			throw new RuntimeException(SystemUtils.message(//
					"EObject of type '$0:$1' does not have an EList for feature '$2'", //
					eObject.eClass().getEPackage().getNsURI(), eObject.eClass().getName(), featureName), e);
		}
	}

	private void invalidateAllObjRefs(URI uri) {
		// TODO: invalid objRefs
		// this is one case where having two separate maps works better
	}

	@Override
	public boolean isValidObjRef(ObjRef objRef) {
		return objRefToEObject.containsKey(objRef);
	}

	@Override
	public void add(ObjRef baseObjRef, String typeOfThing, ObjRef thingToAddObjRef) {
		getEList(get(baseObjRef), typeOfThing).add(get(thingToAddObjRef));
	}

	@Override
	public void add(ObjRef baseObjRef, String typeOfThing, Collection<ObjRef> thingsToAddObjRefs) {
		getEList(get(baseObjRef), typeOfThing).addAll(get(thingsToAddObjRefs));
	}

	@Override
	public void remove(ObjRef baseObjRef, String typeOfThing, ObjRef thingToRemoveObjRef) {
		getEList(get(baseObjRef), typeOfThing).remove(get(thingToRemoveObjRef));
	}

	@Override
	public void remove(ObjRef baseObjRef, String typeOfThing, Collection<ObjRef> thingsToRemoveObjRefs) {
		getEList(get(baseObjRef), typeOfThing).removeAll(get(thingsToRemoveObjRefs));
	}

	@Override
	public void set(ObjRef baseObjRef, String typeOfThing, ObjRef valueObjRef) {
		EObject baseEObject = get(baseObjRef);
		baseEObject.eSet(getEFeature(baseEObject, typeOfThing), get(valueObjRef));
	}
	
	@Override
	public void set(ObjRef baseObjRef, String typeOfThing, Object value) {
		EObject baseEObject = get(baseObjRef);
		
		// If the feature is an enumerated type but instead you passed in a string,
		// then we will try to convert the string to an enumerated type automatically.
		// If that doesn't work, we throw IllegalArgumentException.
		EStructuralFeature feature = getEFeature(baseEObject, typeOfThing);
		if (getValueType(feature).equals(IXArchADTFeature.ValueType.ENUMERATION) && (value != null) && (value instanceof String)) {
			try {
				Class<?> enumClass = feature.getEType().getInstanceClass();
				if (enumClass == null) {
					throw new IllegalArgumentException("Expected enumeration but got String");
				}
				Method m = enumClass.getMethod("get", java.lang.String.class);
				Object newValue = m.invoke(enumClass, (String)value);
				if (newValue == null) {
					throw new IllegalArgumentException("String value " + value.toString() + " not valid for " + enumClass.getCanonicalName());
				}
				value = newValue;
			}
			catch (NoSuchMethodException nsme) {
			}
			catch (InvocationTargetException ite) {
			}
			catch (IllegalAccessException iae) {
			}
		}
		baseEObject.eSet(getEFeature(baseEObject, typeOfThing), value);
	}

	@Override
	public void clear(ObjRef baseObjRef, String typeOfThing) {
		EObject baseEObject = get(baseObjRef);
		baseEObject.eSet(getEFeature(baseEObject, typeOfThing), null);
	}

	@Override
	public Object get(ObjRef baseObjRef, String typeOfThing) {
		EObject baseEObject = get(baseObjRef);
		return check(baseEObject.eGet(getEFeature(baseEObject, typeOfThing)));
	}

	@Override
	public List<ObjRef> getAll(ObjRef baseObjRef, String typeOfThing) {
		return put(getEList(get(baseObjRef), typeOfThing));
	}

	@Override
	public ObjRef get(ObjRef baseObjRef, String typeOfThing, String id) {
		EObject baseEObject = get(baseObjRef);
		EObject eFeature = getEFeature(baseEObject, typeOfThing);
		Resource resource = baseEObject.eResource();
		EObject eObjectByID = resource.getEObject(id);
		if (eObjectByID != null) {
			if (baseEObject.equals(eObjectByID.eContainer())) {
				if (eFeature.equals(eObjectByID.eContainingFeature())) {
					return put(eObjectByID);
				}
			}
		}
		return null;
	}

	@Override
	public List<ObjRef> getAll(ObjRef baseObjRef, String typeOfThing, List<String> ids) {
		EObject baseEObject = get(baseObjRef);
		EObject eFeature = getEFeature(baseEObject, typeOfThing);
		Resource resource = baseEObject.eResource();
		List<EObject> eObjectsByID = Lists.newArrayListWithCapacity(ids.size());
		for (String id : ids) {
			EObject eObjectByID = resource.getEObject(id);
			if (eObjectByID != null) {
				if (baseEObject.equals(eObjectByID.eContainer())) {
					if (eFeature.equals(eObjectByID.eContainingFeature())) {
						eObjectsByID.add(eObjectByID);
					}
				}
			}
		}
		return put(eObjectsByID);
	}

	@Override
	public ObjRef getByID(ObjRef documentRef, String id) {
		EObject objectByID = get(documentRef).eResource().getEObject(id);
		if (objectByID == null) {
			return null;
		}
		return put(objectByID);
	}

	@Override
	public ObjRef getByID(String id) {
		for (Resource r : resourceSet.getResources()) {
			EObject objectByID = r.getEObject(id);
			if (objectByID != null) {
				return put(objectByID);
			}
		}
		return null;
	}

	@Override
	public ObjRef getParent(ObjRef targetObjRef) {
		return put(get(targetObjRef).eContainer());
	}

	@Override
	public boolean hasAncestor(ObjRef childObjRef, ObjRef ancestorObjRef) {
		return EcoreUtil.isAncestor(get(ancestorObjRef), get(childObjRef));
	}

	@Override
	public List<ObjRef> getAllAncestors(ObjRef targetObjRef) {
		EObject eObject = get(targetObjRef);
		List<ObjRef> ancestorObjRefs = Lists.newArrayList();
		while (eObject != null) {
			ancestorObjRefs.add(put(eObject));
			eObject = eObject.eContainer();
		}
		return ancestorObjRefs;
	}

	@Override
	public boolean equals(ObjRef ref1, ObjRef ref2) {
		return get(ref1).equals(get(ref2));
	}

	@Override
	public boolean isAttached(ObjRef targetObjRef) {
		EObject eObject = get(targetObjRef);
		Resource resource = eObject.eResource();
		if (resource != null) {
			return EcoreUtil.isAncestor(resource, eObject);
		}
		return false;
	}
	
	private static IXArchADTFeature.ValueType getValueType(EStructuralFeature feature) {
		Class<?> c = feature.getEType().getInstanceClass();
		if (c == null) {
			return ValueType.OBJECT;
		}
		else if (Enumerator.class.isAssignableFrom(c)) {
			return ValueType.ENUMERATION;
		}
		else if (String.class.isAssignableFrom(c)) {
			return ValueType.STRING;
		}
		else {
			return ValueType.OBJECT;
		}
	}

	private static final ConcurrentMap<String, EPackage> autoEPackage = new MapMaker()
			.softValues().makeComputingMap(new Function<String, EPackage>() {
				@Override
				public EPackage apply(String nsURI) {
					EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
					if (ePackage != null) {
						return ePackage;
					}
					// TODO: Include instructions on registering EPackages
					throw new NullPointerException(SystemUtils.message("No EPackage with nsURI: $0", nsURI));
				}
			});

	private static final ConcurrentMap<EClass, Map<String, IXArchADTFeature>> autoFeatureMedata = new MapMaker()//
			.softValues().makeComputingMap(new Function<EClass, Map<String, IXArchADTFeature>>() {
				@Override
				public Map<String, IXArchADTFeature> apply(EClass eClass) {
					List<IXArchADTFeature> features = Lists.newArrayList();
					features.addAll(Collections2.transform(eClass.getEAllAttributes(),
							new Function<EAttribute, IXArchADTFeature>() {
								public IXArchADTFeature apply(EAttribute eFeature) {
									return new BasicXArchADTFeature(
											eFeature.getName(),
											eFeature.getEType().getEPackage().getNsURI(), 
											eFeature.getEType().getName(),
											FeatureType.ATTRIBUTE,
											getValueType(eFeature),
											false);
								};
							}));
					features.addAll(Collections2.transform(eClass.getEAllReferences(),
							new Function<EReference, IXArchADTFeature>() {
								public IXArchADTFeature apply(EReference eFeature) {
									return new BasicXArchADTFeature(
											eFeature.getName(),
											eFeature.getEType().getEPackage().getNsURI(), 
											eFeature.getEType().getName(),
											eFeature.isMany() ? FeatureType.ELEMENT_MULTIPLE : FeatureType.ELEMENT_SINGLE,
											getValueType(eFeature),
											!eFeature.isContainment());
								};
							}));
					return Maps.uniqueIndex(features, new Function<IXArchADTFeature, String>() {
						@Override
						public String apply(IXArchADTFeature feature) {
							return feature.getName();
						}
					});
				}
			});

	/**
	 * This is a map that generates type metadata for an EClass on demand,
	 * caching it after it's generated.
	 */
	private static final ConcurrentMap<EClass, IXArchADTTypeMetadata> autoTypeMetadata = new MapMaker()//
			.softValues().makeComputingMap(new Function<EClass, IXArchADTTypeMetadata>() {
				@Override
				public IXArchADTTypeMetadata apply(EClass eClass) {
					return new BasicXArchADTTypeMetadata(
							eClass.getEPackage().getNsURI(),
							eClass.getName(),
							autoFeatureMedata.get(eClass),
							eClass.isAbstract());
				}
			});

	/**
	 * This is a map that generates package metadata for an EPackage on demand,
	 * caching it after it's generated.
	 */
	private static final ConcurrentMap<EPackage, IXArchADTPackageMetadata> autoPackageMetatadata = new MapMaker()//
			.softValues().makeComputingMap(new Function<EPackage, IXArchADTPackageMetadata>() {
				@Override
				public IXArchADTPackageMetadata apply(EPackage ePackage) {
					return new BasicXArchADTPackageMetadata(
							ePackage.getNsURI(),
							Iterables.transform(Iterables.filter(ePackage.getEClassifiers(), EClass.class),
									new Function<EClass, IXArchADTTypeMetadata>() {
										public IXArchADTTypeMetadata apply(EClass eClass) {
											return autoTypeMetadata.get(eClass);
										};
									}));
				}
			});

	private static final EClass getEClass(EPackage ePackage, String typeName) {
		EClassifier eClassifier = ePackage.getEClassifier(typeName);
		if (eClassifier instanceof EClass) {
			return (EClass) eClassifier;
		}
		throw new NullPointerException(SystemUtils.message(//
				"EPacakge '$0' does not contain an EClass named '$1'", ePackage, typeName));
	}

	@Override
	public IXArchADTPackageMetadata getPackageMetadata(String nsURI) {
		return autoPackageMetatadata.get(autoEPackage.get(nsURI));
	}

	@Override
	public IXArchADTTypeMetadata getTypeMetadata(String nsURI, String typeName) {
		return autoTypeMetadata.get(getEClass(autoEPackage.get(nsURI), typeName));
	}

	@Override
	public IXArchADTTypeMetadata getTypeMetadata(ObjRef objRef) {
		return autoTypeMetadata.get(get(objRef).eClass());
	}

	@Override
	public List<IXArchADTPackageMetadata> getAvailablePackageMetadata() {
		return Lists.newArrayList(Collections2.transform(Collections2.transform(
			EPackage.Registry.INSTANCE.keySet(),
			Functions.forMap(autoEPackage)),
			Functions.forMap(autoPackageMetatadata)));
	}

	@Override
	public boolean isAssignable(String sourceNsURI, String sourceTypeName, String targetNsURI, String targetTypeName) {
		EClass eSourceClass = getEClass(autoEPackage.get(sourceNsURI), sourceTypeName);
		EClass eTargetClass = getEClass(autoEPackage.get(targetNsURI), targetTypeName);
		if (eSourceClass.equals(EcorePackage.Literals.EOBJECT)) {
			// This is a special case - all EWhatevers are inherently assignable
			// to EObject.  This comes up when a schema has an 'any' element.
			return true;
		}
		return eSourceClass.isSuperTypeOf(eTargetClass);
	}

	@Override
	public boolean isInstanceOf(ObjRef baseObjRef, String sourceNsURI, String sourceTypeName) {
		EObject baseEObject = get(baseObjRef);
		return getEClass(autoEPackage.get(sourceNsURI), sourceTypeName).isSuperTypeOf(baseEObject.eClass());
	}

	//
	//
	//	
	//	TODO: Still have to review the methods below to use EMF reflective methods...
	//	
	//	
	//	

	static int copyNonce = 0;

	@Override
	public ObjRef cloneElement(ObjRef targetObjectRef) {
		EObject eobject = get(targetObjectRef);

		@SuppressWarnings("serial")
		EcoreUtil.Copier xadlCopier = new EcoreUtil.Copier() {
			@Override
			protected void copyAttribute(EAttribute eAttribute, EObject eObject, EObject copyEObject) {
				// don't clone IDs.
				if (eAttribute.getName().equals("id")) {
					String id = (String) eObject.eGet(eAttribute);
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
			load(newURI, serialize(oldURI));
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
	public ObjRef getDocumentRootRef(ObjRef objRef) {
		return getDocumentRootRef(get(objRef).eResource().getURI());
	}

	@Override
	public void close(URI uri) {
		invalidateAllObjRefs(uri);
		Resource r = resourceSet.getResource(uri, false);
		setResourceFinishedLoading(r, false);
		r.unload();
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_CLOSED_EVENT, uri));
	}

	@Override
	public ObjRef create(String nsURI, String typeOfThing) {
		EPackage ePackage = autoEPackage.get(nsURI);
		EClass eClass = getEClass(ePackage, typeOfThing);
		EObject eObject = ePackage.getEFactoryInstance().create(eClass);
		return put(eObject);
	}

	@Override
	public ObjRef createDocument(URI uri) {
		return createDocument(uri, Xadlcore_3_0Package.eNS_URI, "XADLType", "xADL");
	}

	@Override
	public ObjRef createDocument(URI uri, String factoryName, String typeOfThing, String rootElementName) {
		Resource r = resourceSet.getResource(uri, false);
		if (r != null)
			return put(r.getContents().get(0));
		r = resourceSet.createResource(uri, "xml");

		ObjRef documentRootRef = create(factoryName, "DocumentRoot");
		ObjRef xADLRef = create(factoryName, typeOfThing);
		set(documentRootRef, rootElementName, xADLRef);

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
				if (r.isLoaded()) {
					// Already open
					return put(r.getContents().get(0));
				}
			}
		}

		Resource newResource = resourceSet.getResource(uri, true);
		newResource.load(LOAD_OPTIONS_MAP);
		setResourceFinishedLoading(newResource, true);

		ObjRef rootElementRef = put(newResource.getContents().get(0));
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_OPENED_EVENT, uri, rootElementRef));
		return rootElementRef;
	}

	public ObjRef load(URI uri, byte[] content) throws SAXException, IOException {
		Resource r = resourceSet.createResource(uri);
		r.load(new ByteArrayInputStream(content), LOAD_OPTIONS_MAP);
		setResourceFinishedLoading(r, true);

		ObjRef rootElementRef = put(r.getContents().get(0));
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_OPENED_EVENT, uri, rootElementRef));
		return rootElementRef;
	}

	@Override
	public void renameXArch(String oldURI, String newURI) {
		// TODO Auto-generated method stub
	}

	@Override
	public URI getURI(ObjRef ref) {
		EObject obj = get(ref);
		if (obj.eResource() == null)
			return null;
		return obj.eResource().getURI();
	}

	@Override
	public void save(URI uri) throws IOException {
		Resource r = resourceSet.getResource(uri, false);
		r.save(SAVE_OPTIONS_MAP);
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_SAVED_EVENT, uri, getDocumentRootRef(uri)));
	}

	@Override
	public byte[] serialize(URI uri) {
		Resource r = resourceSet.getResource(uri, false);

		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			r.save(baos, SAVE_OPTIONS_MAP);
			return baos.toByteArray();
		}
		catch (IOException ioe) {
			throw new RuntimeException("This shouldn't happen", ioe);
		}
	}

	@Override
	public String getTagName(ObjRef objRef) {
		EObject eObject = get(objRef);
		EStructuralFeature sf = elementHandlerImpl.getRoot(ExtendedMetaData.INSTANCE, eObject.eClass());
		if (sf == null) {
			return null;
		}
		return sf.getName();
	}

	@Override
	public String getContainingFeatureName(ObjRef ref) {
		EObject eobject = get(ref);
		EStructuralFeature containingFeature = eobject.eContainingFeature();
		if (containingFeature == null) {
			return null;
		}
		return containingFeature.getName();
	}

	@Override
	public String getTagsOnlyPathString(ObjRef targetObjRef) {
		List<String> tags = new ArrayList<String>();
		EObject eObject = get(targetObjRef);
		while (eObject != null) {
			EStructuralFeature sf = elementHandlerImpl.getRoot(ExtendedMetaData.INSTANCE, eObject.eClass());
			if (sf != null) {
				tags.add(sf.getName());
			}
			else if (eObject.eContainer() != null) {
				tags.add(eObject.eContainingFeature().getName());
			}
			else {
				break;
			}
			eObject = eObject.eContainer();
		}
		StringBuilder sb = new StringBuilder();
		for (int i = tags.size() - 1; i >= 0; i--) {
			sb.append(tags.get(i));
			if (i > 0) {
				sb.append("/");
			}
		}
		return sb.toString();
	}

	@Override
	public XArchADTPath getPath(ObjRef ref) {
		List<String> containingFeatureNames = new ArrayList<String>();
		List<Integer> tagIndexes = new ArrayList<Integer>();
		List<String> ids = new ArrayList<String>();

		EObject currentObj = get(ref);

		while (currentObj != null) {

			EStructuralFeature containingFeature = currentObj.eContainingFeature();
			if (containingFeature == null)
				break;
			String containingFeatureName = containingFeature.getName();
			String id = null;
			EStructuralFeature idFeature = currentObj.eClass().getEStructuralFeature("id");
			if (idFeature != null) {
				id = (String) currentObj.eGet(idFeature);
			}
			int index = -1;
			EObject parentObj = currentObj.eContainer();
			if (parentObj != null) {
				Object containingObject = parentObj.eGet(containingFeature);
				if (containingObject instanceof EList) {
					EList<?> list = (EList<?>) containingObject;
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
		for (IXArchADTModelListener l : modelListeners) {
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
		for (IXArchADTFileListener l : fileListeners) {
			l.handleXArchADTFileEvent(evt);
		}
	}

	public void addXArchADTFileListener(IXArchADTFileListener l) {
		fileListeners.add(l);
	}

	public void removeXArchADTFileListener(IXArchADTFileListener l) {
		fileListeners.remove(l);
	}

	// This stuff is necessary to suppress the event flurry when a document is
	// loaded.
	private Set<Resource> resourcesFinishedLoading = new CopyOnWriteArraySet<Resource>();

	private void setResourceFinishedLoading(Resource r, boolean isFinishedLoading) {
		if (isFinishedLoading) {
			resourcesFinishedLoading.add(r);
		}
		else {
			resourcesFinishedLoading.remove(r);
		}
	}

	class ContentAdapter extends EContentAdapter {
		public void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			if (notification.isTouch())
				return;

			Object notifier = notification.getNotifier();
			if (!(notifier instanceof EObject)) {
				return;
			}
			if (!resourcesFinishedLoading.contains(((EObject) notifier).eResource())) {
				return;
			}

			XArchADTModelEvent.EventType evtType;
			switch (notification.getEventType()) {
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
			default:
				return;
			}

			String featureName;
			Object feature = notification.getFeature();
			if (feature instanceof EReference) {
				featureName = ((EReference) feature).getName();
			}
			else if (feature instanceof EStructuralFeature) {
				featureName = ((EStructuralFeature) feature).getName();
			}
			else {
				return;
			}

			ObjRef srcRef = put((EObject) notifier);

			List<ObjRef> srcAncestors = getAllAncestors(srcRef);
			XArchADTPath srcPath = getPath(srcRef);

			Object oldValue = notification.getOldValue();
			XArchADTPath oldValuePath = null;

			if (oldValue instanceof EObject) {
				ObjRef oldValueRef = put((EObject) oldValue);
				oldValue = oldValueRef;
				oldValuePath = getPath(oldValueRef);
			}

			Object newValue = notification.getNewValue();
			XArchADTPath newValuePath = null;

			if (newValue instanceof EObject) {
				ObjRef newValueRef = put((EObject) newValue);
				newValue = newValueRef;
				newValuePath = getPath(newValueRef);
			}

			fireXArchADTModelEvent(new XArchADTModelEvent(
				evtType, 
				srcRef, srcAncestors, srcPath,
				featureName, 
				oldValue, oldValuePath, 
				newValue, newValuePath));
		}
	}

	protected List<IXArchADTExtensionHint> allExtensionHints = null;

	public synchronized List<IXArchADTExtensionHint> getAllExtensionHints() {
		if (allExtensionHints == null) {
			List<EPackage> allEPackages = Lists.newArrayList();
			for (String packageNsURI : EPackage.Registry.INSTANCE.keySet()) {
				allEPackages.add(EPackage.Registry.INSTANCE.getEPackage(packageNsURI));
			}
			allExtensionHints = ExtensionHintUtils.parseExtensionHints(allEPackages);
		}
		return allExtensionHints;
	}

	public List<IXArchADTExtensionHint> getExtensionHintsForExtension(String extensionNsURI, String extensionTypeName) {
		List<IXArchADTExtensionHint> matchingHints = new ArrayList<IXArchADTExtensionHint>();
		for (IXArchADTExtensionHint hint : getAllExtensionHints()) {
			if (extensionNsURI.equals(hint.getExtensionNsURI()) && extensionTypeName.equals(hint.getExtensionTypeName())) {
				matchingHints.add(hint);
			}
		}
		return matchingHints;
	}

	public List<IXArchADTExtensionHint> getExtensionHintsForTarget(String targetNsURI, String targetTypeName) {
		List<IXArchADTExtensionHint> matchingHints = new ArrayList<IXArchADTExtensionHint>();
		for (IXArchADTExtensionHint hint : getAllExtensionHints()) {
			if (targetNsURI.equals(hint.getTargetNsURI()) && targetTypeName.equals(hint.getTargetTypeName())) {
				matchingHints.add(hint);
			}
		}
		return matchingHints;
	}
}