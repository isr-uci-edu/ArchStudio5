package org.archstudio.xarchadt.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.xml.xpath.XPathException;

import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.BasicXArchADTFeature;
import org.archstudio.xarchadt.BasicXArchADTPackageMetadata;
import org.archstudio.xarchadt.BasicXArchADTTypeMetadata;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTFeature.FeatureType;
import org.archstudio.xarchadt.IXArchADTFeature.ValueType;
import org.archstudio.xarchadt.IXArchADTFileListener;
import org.archstudio.xarchadt.IXArchADTModelListener;
import org.archstudio.xarchadt.IXArchADTPackageMetadata;
import org.archstudio.xarchadt.IXArchADTSubstitutionHint;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTFileEvent;
import org.archstudio.xarchadt.XArchADTFileEvent.EventType;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTPath;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.emf.xpath.EcoreXPathContextFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
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
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EContentAdapter;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.ElementHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.GenericXMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl;
import org.xml.sax.SAXException;

import com.google.common.base.Function;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Collections2;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;

public class XArchADTImpl implements IXArchADT {

	private static final boolean DEBUG = false;

	protected final List<IXArchADTModelListener> modelListeners = new CopyOnWriteArrayList<IXArchADTModelListener>();
	protected final List<IXArchADTFileListener> fileListeners = new CopyOnWriteArrayList<IXArchADTFileListener>();

	// All the resources here indexed by URI
	private final ResourceSet resourceSet;

	private static final Map<Object, Object> LOAD_OPTIONS_MAP = new HashMap<Object, Object>();
	static {
		// This allows root elements in other schema
		LOAD_OPTIONS_MAP.put(XMLResource.OPTION_EXTENDED_META_DATA, true);
		// optimizations from http://www.eclipse.org/modeling/emf/docs/performance/EMFPerformanceTips.html
		LOAD_OPTIONS_MAP.put(XMLResource.OPTION_DEFER_ATTACHMENT, true);
		LOAD_OPTIONS_MAP.put(XMLResource.OPTION_DEFER_IDREF_RESOLUTION, true);
		LOAD_OPTIONS_MAP.put(XMLResource.OPTION_USE_PARSER_POOL, new XMLParserPoolImpl());
		LOAD_OPTIONS_MAP.put(XMLResource.OPTION_USE_XML_NAME_TO_FEATURE_MAP, new HashMap<Object, Object>());
	}

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
		resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put("xml", new GenericXMLResourceFactoryImpl());
		resourceSet.getResourceFactoryRegistry().getContentTypeToFactoryMap()
				.put("xml", new GenericXMLResourceFactoryImpl());

		registerAllSchemaPackages();
	}

	/**
	 * This method causes the EPackage.eINSTANCE variable of each EMF-generated
	 * bundle (plugin) in Eclipse to be touched. This causes the package to
	 * spontaneously register itself with the EPackage Registry, which is how we
	 * find out what EPackages are available on the system. This is an EMF-ism
	 * that isn't easily avoided. If you are running outside of ArchStudio (like
	 * in org.archstudio.description.Main), then these bundles will not be
	 * available. What you have to do then is just read the eINSTANCE variable
	 * for all the EPackages you want to have available to you.
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
						/* EPackage ePackage = (EPackage) */instanceField.get(packageClass);
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
	private final Map<ObjRef, EObject> objRefToEObject = new MapMaker().weakKeys().makeMap();
	private final Map<EObject, ObjRef> eObjectToObjRef = new MapMaker().softValues().makeMap();

	private ObjRef put(EObject eObject) {
		synchronized (objRefToEObjectLock) {
			ObjRef objRef = eObjectToObjRef.get(eObject);
			if (objRef == null && eObject != null) {
				objRef = new ObjRef(DEBUG ? eObject : null);
				eObjectToObjRef.put(eObject, objRef);
				objRefToEObject.put(objRef, eObject);
			}
			return objRef;
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

	private Serializable check(Object object) {
		if (object instanceof EObject) {
			return put((EObject) object);
		}
		return (Serializable) object;
	}

	private Object uncheck(Serializable serializable) {
		if (serializable instanceof ObjRef) {
			return get((ObjRef) serializable);
		}
		return serializable;
	}

	// Dynamically maps feature names (either uppercase or lowercase) of an EClass to EStructuralFeatures.
	private static final LoadingCache<EClass, Map<String, EStructuralFeature>> caselessFeatureCache = CacheBuilder
			.newBuilder().build(new CacheLoader<EClass, Map<String, EStructuralFeature>>() {

				Map<String, EStructuralFeature> structuralFeatures = Maps.newHashMap();
				Map<Integer, EStructuralFeature> featureIDs = Maps.newHashMap();

				@Override
				public synchronized Map<String, EStructuralFeature> load(EClass eClass) throws Exception {
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
					for (EClass eSuperClass : eClass.getESuperTypes()) {
						apply0(eSuperClass);
					}
				}
			});

	private static final EStructuralFeature getEFeature(EClass eClass, String featureName) {
		EStructuralFeature eFeature = caselessFeatureCache.getUnchecked(eClass).get(featureName);
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
	private static final EList<Object> getEList(EObject eObject, String featureName) {
		try {
			return (EList<Object>) eObject.eGet(getEFeature(eObject.eClass(), featureName));
		}
		catch (ClassCastException e) {
			throw new RuntimeException(SystemUtils.message(//
					"EObject of type '$0:$1' does not have an EList for feature '$2'", //
					eObject.eClass().getEPackage().getNsURI(), eObject.eClass().getName(), featureName), e);
		}
	}

	@SuppressWarnings("unchecked")
	private static final EMap<Object, Object> getEMap(EObject eObject, String featureName) {
		try {
			return (EMap<Object, Object>) eObject.eGet(getEFeature(eObject.eClass(), featureName));
		}
		catch (ClassCastException e) {
			throw new RuntimeException(SystemUtils.message(//
					"EObject of type '$0:$1' does not have an EMap for feature '$2'", //
					eObject.eClass().getEPackage().getNsURI(), eObject.eClass().getName(), featureName), e);
		}
	}

	@Override
	public synchronized boolean isValidObjRef(ObjRef objRef) {
		return get(objRef) != null;
	}

	@Override
	public synchronized void set(ObjRef baseObjRef, String typeOfThing, Serializable value) {
		EObject baseEObject = get(baseObjRef);
		baseEObject.eSet(getEFeature(baseEObject, typeOfThing), uncheck(value));
	}

	@Override
	public synchronized Serializable get(ObjRef baseObjRef, String typeOfThing) {
		return get(baseObjRef, typeOfThing, true);
	}

	@Override
	public synchronized Serializable get(ObjRef baseObjRef, String typeOfThing, boolean resolve) {
		EObject baseEObject = get(baseObjRef);
		return check(baseEObject.eGet(getEFeature(baseEObject, typeOfThing), resolve));
	}

	@Override
	public synchronized Serializable resolve(ObjRef objRef) {
		EObject baseEObject = get(objRef);
		return check(EcoreUtil.resolve(baseEObject, baseEObject.eResource()));
	}

	@Override
	public synchronized void clear(ObjRef baseObjRef, String typeOfThing) {
		EObject baseEObject = get(baseObjRef);
		baseEObject.eUnset(getEFeature(baseEObject, typeOfThing));
	}

	@Override
	public synchronized void add(ObjRef baseObjRef, String typeOfThing, Serializable thingToAdd) {
		getEList(get(baseObjRef), typeOfThing).add(uncheck(thingToAdd));
	}

	@Override
	public synchronized void add(ObjRef baseObjRef, String typeOfThing, Collection<? extends Serializable> thingsToAdd) {
		for (Serializable thingToAdd : thingsToAdd) {
			getEList(get(baseObjRef), typeOfThing).add(uncheck(thingToAdd));
		}
	}

	@Override
	public synchronized List<Serializable> getAll(ObjRef baseObjRef, String typeOfThing) {
		EList<Object> list = getEList(get(baseObjRef), typeOfThing);
		List<Serializable> result = Lists.newArrayListWithCapacity(list.size());
		for (Object object : list) {
			result.add(check(object));
		}
		return result;
	}

	@Override
	public synchronized void remove(ObjRef baseObjRef, String typeOfThing, Serializable thingToRemove) {
		getEList(get(baseObjRef), typeOfThing).remove(uncheck(thingToRemove));
	}

	@Override
	public synchronized void remove(ObjRef baseObjRef, String typeOfThing,
			Collection<? extends Serializable> thingsToRemove) {
		for (Serializable thingToRemove : thingsToRemove) {
			getEList(get(baseObjRef), typeOfThing).remove(uncheck(thingToRemove));
		}
	}

	@Override
	public synchronized Serializable put(ObjRef baseObjRef, String typeOfThing, Serializable key, Serializable value) {
		return check(getEMap(get(baseObjRef), typeOfThing).put(uncheck(key), uncheck(value)));
	}

	@Override
	public synchronized Serializable getByKey(ObjRef baseObjRef, String typeOfThing, Serializable key) {
		return check(getEMap(get(baseObjRef), typeOfThing).get(uncheck(key)));
	}

	@Override
	public synchronized Serializable removeByKey(ObjRef baseObjRef, String typeOfThing, Serializable key) {
		return check(getEMap(get(baseObjRef), typeOfThing).removeKey(uncheck(key)));
	}

	@Override
	public synchronized ObjRef getByID(ObjRef documentRef, String id) {
		EObject objectByID = get(documentRef).eResource().getEObject(id);
		if (objectByID == null) {
			return null;
		}
		return put(objectByID);
	}

	@Override
	public synchronized ObjRef getByID(String id) {
		for (Resource r : resourceSet.getResources()) {
			EObject objectByID = r.getEObject(id);
			if (objectByID != null) {
				return put(objectByID);
			}
		}
		return null;
	}

	@Override
	public synchronized ObjRef getParent(ObjRef targetObjRef) {
		return put(get(targetObjRef).eContainer());
	}

	@Override
	public synchronized boolean hasAncestor(ObjRef childObjRef, ObjRef ancestorObjRef) {
		return EcoreUtil.isAncestor(get(ancestorObjRef), get(childObjRef));
	}

	@Override
	public synchronized List<ObjRef> getAllAncestors(ObjRef targetObjRef) {
		EObject eObject = get(targetObjRef);
		List<ObjRef> ancestorObjRefs = Lists.newArrayList();
		while (eObject != null) {
			ancestorObjRefs.add(put(eObject));
			eObject = eObject.eContainer();
		}
		return ancestorObjRefs;
	}

	@Override
	public synchronized List<ObjRef> getLineage(ObjRef targetObjRef) {
		return Lists.reverse(getAllAncestors(targetObjRef));
	}

	@Override
	public synchronized boolean equals(ObjRef ref1, ObjRef ref2) {
		return get(ref1).equals(get(ref2));
	}

	@Override
	public synchronized boolean isAttached(ObjRef targetObjRef) {
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

	private static final LoadingCache<String, EPackage> ePackageCache = CacheBuilder.newBuilder().build(
			new CacheLoader<String, EPackage>() {

				@Override
				public synchronized EPackage load(String nsURI) throws Exception {
					EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(nsURI);
					if (ePackage != null) {
						return ePackage;
					}
					throw new NullPointerException(SystemUtils.message("No EPackage with nsURI: $0", nsURI));
				}
			});

	private static final LoadingCache<EClass, Map<String, IXArchADTFeature>> featureMetadataCache = CacheBuilder
			.newBuilder().build(new CacheLoader<EClass, Map<String, IXArchADTFeature>>() {

				@Override
				public synchronized Map<String, IXArchADTFeature> load(EClass eClass) throws Exception {
					List<IXArchADTFeature> features = Lists.newArrayList();
					features.addAll(Collections2.transform(eClass.getEAllAttributes(),
							new Function<EAttribute, IXArchADTFeature>() {
								@Override
								public synchronized IXArchADTFeature apply(EAttribute eFeature) {
									return new BasicXArchADTFeature(eFeature.getName(), eFeature.getEType()
											.getEPackage().getNsURI(), eFeature.getEType().getName(),
											FeatureType.ATTRIBUTE, getValueType(eFeature), false);
								};
							}));
					features.addAll(Collections2.transform(eClass.getEAllReferences(),
							new Function<EReference, IXArchADTFeature>() {
								@Override
								public synchronized IXArchADTFeature apply(EReference eFeature) {
									return new BasicXArchADTFeature(eFeature.getName(), eFeature.getEType()
											.getEPackage().getNsURI(), eFeature.getEType().getName(),
											eFeature.isMany() ? FeatureType.ELEMENT_MULTIPLE
													: FeatureType.ELEMENT_SINGLE, getValueType(eFeature), !eFeature
													.isContainment());
								};
							}));
					return Maps.uniqueIndex(features, new Function<IXArchADTFeature, String>() {
						@Override
						public synchronized String apply(IXArchADTFeature feature) {
							return feature.getName();
						}
					});
				}
			});

	/**
	 * This is a map that generates type metadata for an EClass on demand,
	 * caching it after it's generated.
	 */
	private static final LoadingCache<EClass, IXArchADTTypeMetadata> typeMetadataCache = CacheBuilder.newBuilder()
			.build(new CacheLoader<EClass, IXArchADTTypeMetadata>() {

				@Override
				public synchronized IXArchADTTypeMetadata load(EClass eClass) throws Exception {
					return new BasicXArchADTTypeMetadata(eClass.getEPackage().getNsURI(), eClass.getName(),
							featureMetadataCache.get(eClass), eClass.isAbstract());
				}
			});

	/**
	 * This is a map that generates package metadata for an EPackage on demand,
	 * caching it after it's generated.
	 */
	private static final LoadingCache<EPackage, IXArchADTPackageMetadata> packageMetatadataCache = CacheBuilder
			.newBuilder().build(new CacheLoader<EPackage, IXArchADTPackageMetadata>() {

				@Override
				public synchronized IXArchADTPackageMetadata load(EPackage ePackage) throws Exception {
					return new BasicXArchADTPackageMetadata(ePackage.getNsURI(), Iterables.transform(
							Iterables.filter(ePackage.getEClassifiers(), EClass.class),
							new Function<EClass, IXArchADTTypeMetadata>() {
								@Override
								public synchronized IXArchADTTypeMetadata apply(EClass eClass) {
									return typeMetadataCache.getUnchecked(eClass);
								};
							}));
				}
			});

	private static final EClass getEClass(EPackage ePackage, String typeName) {
		EClassifier eClassifier = ePackage.getEClassifier(typeName);
		if (eClassifier == null) {
			eClassifier = ePackage.getEClassifier(SystemUtils.capFirst(typeName));
		}
		if (eClassifier == null) {
			throw new IllegalArgumentException(SystemUtils.message(
					"EPackage '$0' does not contain an EClass named '$1'", ePackage, typeName));
		}
		if (eClassifier instanceof EClass) {
			return (EClass) eClassifier;
		}
		throw new IllegalArgumentException("Classifier was not an instance of EClass");
	}

	@Override
	public synchronized IXArchADTPackageMetadata getPackageMetadata(String nsURI) {
		return packageMetatadataCache.getUnchecked(ePackageCache.getUnchecked(nsURI));
	}

	@Override
	public synchronized IXArchADTTypeMetadata getTypeMetadata(String nsURI, String typeName) {
		return typeMetadataCache.getUnchecked(getEClass(ePackageCache.getUnchecked(nsURI), typeName));
	}

	@Override
	public synchronized IXArchADTTypeMetadata getTypeMetadata(ObjRef objRef) {
		return typeMetadataCache.getUnchecked(get(objRef).eClass());
	}

	@Override
	public synchronized List<IXArchADTPackageMetadata> getAvailablePackageMetadata() {
		return Lists.newArrayList(Collections2.transform(EPackage.Registry.INSTANCE.keySet(),
				new Function<String, IXArchADTPackageMetadata>() {
					@Override
					public synchronized IXArchADTPackageMetadata apply(String nsURI) {
						return packageMetatadataCache.getUnchecked(ePackageCache.getUnchecked(nsURI));
					}
				}));
	}

	@Override
	public synchronized boolean isAssignable(String sourceNsURI, String sourceTypeName, String targetNsURI,
			String targetTypeName) {
		EClass eSourceClass = getEClass(ePackageCache.getUnchecked(sourceNsURI), sourceTypeName);
		EClass eTargetClass = getEClass(ePackageCache.getUnchecked(targetNsURI), targetTypeName);
		if (eSourceClass.equals(EcorePackage.Literals.EOBJECT)) {
			// This is a special case - all EWhatevers are inherently assignable
			// to EObject.  This comes up when a schema has an 'any' element.
			return true;
		}
		return eSourceClass.isSuperTypeOf(eTargetClass);
	}

	@Override
	public synchronized boolean isInstanceOf(ObjRef baseObjRef, String sourceNsURI, String sourceTypeName) {
		EObject baseEObject = get(baseObjRef);
		return baseEObject == null ? false : getEClass(ePackageCache.getUnchecked(sourceNsURI), sourceTypeName)
				.isSuperTypeOf(baseEObject.eClass());
	}

	//
	//
	//	
	//	TODO: Still have to review the methods below to use EMF reflective methods...
	//	
	//	
	//	

	//	static int copyNonce = 0;
	//
	//	@Override
	//	public synchronized ObjRef cloneElement(ObjRef targetObjectRef) {
	//		EObject eobject = get(targetObjectRef);
	//
	//		@SuppressWarnings("serial")
	//		EcoreUtil.Copier xadlCopier = new EcoreUtil.Copier() {
	//			@Override
	//			protected void copyAttribute(EAttribute eAttribute, EObject eObject, EObject copyEObject) {
	//				// don't clone IDs.
	//				if (eAttribute.getName().equals("id")) {
	//					String id = (String) eObject.eGet(eAttribute);
	//					if (id == null) {
	//						id = "";
	//					}
	//					id += "c" + copyNonce;
	//					copyNonce++;
	//					copyEObject.eSet(eAttribute, id);
	//				}
	//				else {
	//					super.copyAttribute(eAttribute, eObject, copyEObject);
	//				}
	//			}
	//		};
	//
	//		EObject result = xadlCopier.copy(eobject);
	//		xadlCopier.copyReferences();
	//
	//		ObjRef clonedRef = put(result);
	//		return clonedRef;
	//	}
	//
	//	@Override
	//	public synchronized ObjRef cloneDocument(URI oldURI, URI newURI) {
	//		try {
	//			load(newURI, serialize(oldURI));
	//			return getDocumentRootRef(newURI);
	//		}
	//		catch (SAXException ioe) {
	//			throw new RuntimeException("This shouldn't happen.");
	//		}
	//		catch (IOException ioe) {
	//			throw new RuntimeException("This shouldn't happen.");
	//		}
	//	}

	@Override
	public synchronized List<URI> getOpenURIs() {
		List<URI> uriList = new ArrayList<URI>();
		for (Resource r : resourceSet.getResources()) {
			uriList.add(r.getURI());
		}
		return uriList;
	}

	@Override
	public synchronized ObjRef getDocumentRootRef(URI uri) {
		Resource r = resourceSet.getResource(uri, false);
		if (r == null) {
			throw new IllegalArgumentException("No such resource at URI: " + uri);
		}
		return put(r.getContents().get(0));
	}

	@Override
	public synchronized ObjRef getDocumentRootRef(ObjRef objRef) {
		return getDocumentRootRef(get(objRef).eResource().getURI());
	}

	@Override
	public synchronized void close(URI uri) {
		Resource r = resourceSet.getResource(uri, false);
		setResourceFinishedLoading(r, false);
		r.unload();
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_CLOSED_EVENT, uri));
	}

	@Override
	public synchronized ObjRef create(String nsURI, String typeOfThing) {
		EPackage ePackage = ePackageCache.getUnchecked(nsURI);
		EClass eClass = getEClass(ePackage, typeOfThing);
		EObject eObject = ePackage.getEFactoryInstance().create(eClass);
		return put(eObject);
	}

	@Override
	public synchronized ObjRef createDocument(URI uri) {
		return createDocument(uri, Xadlcore_3_0Package.eINSTANCE.getNsURI(), "XADLType", "xADL");
	}

	@Override
	public synchronized ObjRef createDocument(URI uri, String nsURI, String typeOfThing, String rootElementName) {
		Resource r = resourceSet.getResource(uri, false);
		if (r != null) {
			return put(r.getContents().get(0));
		}
		r = resourceSet.createResource(uri, "xml");
		if (r instanceof ResourceImpl) {
			((ResourceImpl) r).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
		}

		ObjRef documentRootRef = create(nsURI, "DocumentRoot");
		ObjRef xADLRef = create(nsURI, typeOfThing);
		set(documentRootRef, rootElementName, xADLRef);

		setResourceFinishedLoading(r, true);

		EObject documentRootObject = get(documentRootRef);
		r.getContents().add(documentRootObject);

		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_CREATED_EVENT, uri, documentRootRef));
		return documentRootRef;
	}

	@Override
	public synchronized ObjRef load(URI uri) throws SAXException, IOException {
		for (Resource r : resourceSet.getResources()) {
			if (r.getURI().equals(uri)) {
				// Possibly already open
				if (r.isLoaded() && r.getContents().size() >= 1) {
					// Already open
					return put(r.getContents().get(0));
				}
			}
		}

		Resource newResource = null;
		try {
			newResource = resourceSet.getResource(uri, false);
			if (newResource == null) {
				newResource = resourceSet.createResource(uri);
				if (newResource instanceof ResourceImpl) {
					((ResourceImpl) newResource).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
				}
			}
			if (!newResource.isLoaded()) {
				newResource.load(LOAD_OPTIONS_MAP);
			}
		}
		catch (WrappedException e) {
			// This catches problems with the model itself, such as unresolved dependencies
			e.printStackTrace();
			// We still want to open the model (and eventually correct these problems)
			newResource = resourceSet.getResource(uri, false);
		}
		setResourceFinishedLoading(newResource, true);

		ObjRef rootElementRef = put(newResource.getContents().get(0));
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_OPENED_EVENT, uri, rootElementRef));
		return rootElementRef;
	}

	@Override
	public synchronized ObjRef load(URI uri, byte[] content) throws SAXException, IOException {
		Resource r = resourceSet.createResource(uri);
		if (r instanceof ResourceImpl) {
			((ResourceImpl) r).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
		}
		r.load(new ByteArrayInputStream(content), LOAD_OPTIONS_MAP);
		setResourceFinishedLoading(r, true);

		ObjRef rootElementRef = put(r.getContents().get(0));
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_OPENED_EVENT, uri, rootElementRef));
		return rootElementRef;
	}

	@Override
	public synchronized void renameXArch(String oldURI, String newURI) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();
	}

	@Override
	public synchronized URI getURI(ObjRef ref) {
		EObject obj = get(ref);
		if (obj.eResource() == null) {
			return null;
		}
		return obj.eResource().getURI();
	}

	@Override
	public synchronized void save(URI uri) throws IOException {
		Resource r = resourceSet.getResource(uri, false);
		r.save(SAVE_OPTIONS_MAP);
		fireXArchADTFileEvent(new XArchADTFileEvent(EventType.XARCH_SAVED_EVENT, uri, getDocumentRootRef(uri)));
	}

	@Override
	public synchronized byte[] serialize(URI uri) {
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
	public synchronized String getTagName(ObjRef objRef) {
		EObject eObject = get(objRef);
		EStructuralFeature sf = elementHandlerImpl.getRoot(ExtendedMetaData.INSTANCE, eObject.eClass());
		if (sf == null) {
			return null;
		}
		return sf.getName();
	}

	@Override
	public synchronized String getContainingFeatureName(ObjRef ref) {
		EObject eobject = get(ref);
		EStructuralFeature containingFeature = eobject.eContainingFeature();
		if (containingFeature == null) {
			return null;
		}
		return containingFeature.getName();
	}

	@Override
	public synchronized String getTagsOnlyPathString(ObjRef targetObjRef) {
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
	public synchronized XArchADTPath getPath(ObjRef ref) {
		List<String> containingFeatureNames = new ArrayList<String>();
		List<Integer> tagIndexes = new ArrayList<Integer>();
		List<String> ids = new ArrayList<String>();

		EObject currentObj = get(ref);

		while (currentObj != null) {

			EStructuralFeature containingFeature = currentObj.eContainingFeature();
			if (containingFeature == null) {
				break;
			}
			String containingFeatureName = containingFeature.getName();
			String id = null;
			EStructuralFeature idFeature = currentObj.eClass().getEStructuralFeature("id");
			if (idFeature != null) {
				Object idValue = currentObj.eGet(idFeature);
				if (idValue != null) {
					id = idValue.toString();
				}
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
	public synchronized ObjRef resolveHref(ObjRef documentRef, String href) {
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

	public synchronized void addXArchADTModelListener(IXArchADTModelListener l) {
		modelListeners.add(l);
	}

	public synchronized void removeXArchADTModelListener(IXArchADTModelListener l) {
		modelListeners.remove(l);
	}

	protected void fireXArchADTFileEvent(XArchADTFileEvent evt) {
		for (IXArchADTFileListener l : fileListeners) {
			l.handleXArchADTFileEvent(evt);
		}
	}

	public synchronized void addXArchADTFileListener(IXArchADTFileListener l) {
		fileListeners.add(l);
	}

	public synchronized void removeXArchADTFileListener(IXArchADTFileListener l) {
		fileListeners.remove(l);
	}

	// This stuff is necessary to suppress the event flurry when a document is loaded.
	private final Set<Resource> resourcesFinishedLoading = new CopyOnWriteArraySet<Resource>();

	private void setResourceFinishedLoading(Resource r, boolean isFinishedLoading) {
		if (isFinishedLoading) {
			resourcesFinishedLoading.add(r);
		}
		else {
			resourcesFinishedLoading.remove(r);
		}
	}

	class ContentAdapter extends EContentAdapter {
		@Override
		public synchronized void notifyChanged(Notification notification) {
			super.notifyChanged(notification);
			if (notification.isTouch()) {
				return;
			}

			Object notifier = notification.getNotifier();
			if (!(notifier instanceof EObject)) {
				return;
			}
			if (!resourcesFinishedLoading.contains(((EObject) notifier).eResource())) {
				return;
			}

			String featureName;
			Object feature = notification.getFeature();
			if (feature instanceof EStructuralFeature) {
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
				boolean hasId = ((EObject) oldValue).eClass().getEStructuralFeature("id") != null;
				Object valueId = hasId ? get(oldValueRef, "id") : null;
				oldValue = oldValueRef;
				oldValuePath = srcPath.getLength() == 0 ? new XArchADTPath(featureName) : new XArchADTPath(
						srcPath.toString() + "/" + featureName + (valueId == null ? "" : ":id=" + valueId));
			}

			Object newValue = notification.getNewValue();
			if (newValue instanceof FeatureMap.Entry) {
				newValue = ((FeatureMap.Entry) newValue).getValue();
			}
			XArchADTPath newValuePath = null;
			if (newValue instanceof EObject) {
				ObjRef newValueRef = put((EObject) newValue);
				boolean hasId = ((EObject) newValue).eClass().getEStructuralFeature("id") != null;
				Object valueId = hasId ? get(newValueRef, "id") : null;
				newValue = newValueRef;
				newValuePath = srcPath.getLength() == 0 ? new XArchADTPath(featureName) : new XArchADTPath(
						srcPath.toString() + "/" + featureName + (valueId == null ? "" : ":id=" + valueId));
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
				if (notification.getNewValue() != null) {
					evtType = XArchADTModelEvent.EventType.SET;
					break;
				}
			case Notification.UNSET:
				evtType = XArchADTModelEvent.EventType.CLEAR;
				break;
			default:
				return;
			}

			fireXArchADTModelEvent(new XArchADTModelEvent(evtType, srcRef, srcAncestors, srcPath, featureName,
					oldValue, oldValuePath, newValue, newValuePath));
		}
	}

	protected List<IXArchADTSubstitutionHint> allSubstitutionHints = null;

	@Override
	public synchronized List<IXArchADTSubstitutionHint> getAllSubstitutionHints() {
		if (allSubstitutionHints == null) {
			List<EPackage> allEPackages = Lists.newArrayList();
			for (String packageNsURI : EPackage.Registry.INSTANCE.keySet()) {
				allEPackages.add(EPackage.Registry.INSTANCE.getEPackage(packageNsURI));
			}
			allSubstitutionHints = Lists.newArrayList();
			allSubstitutionHints.addAll(ExtensionHintUtils.parseExtensionHints(allEPackages));
			allSubstitutionHints.addAll(SubstitutionHintUtils.parseSubstitutionHints(allEPackages));
		}

		return allSubstitutionHints;
	}

	@Override
	public synchronized List<IXArchADTSubstitutionHint> getSubstitutionHintsForSource(String sourceNsURI,
			String sourceTypeName) {
		List<IXArchADTSubstitutionHint> matchingHints = new ArrayList<IXArchADTSubstitutionHint>();
		for (IXArchADTSubstitutionHint hint : getAllSubstitutionHints()) {
			if (sourceNsURI.equals(hint.getSourceNsURI()) && sourceTypeName.equals(hint.getSourceTypeName())) {
				matchingHints.add(hint);
			}
		}
		return matchingHints;
	}

	@Override
	public synchronized List<IXArchADTSubstitutionHint> getSubstitutionHintsForTarget(String targetNsURI,
			String targetTypeName) {
		List<IXArchADTSubstitutionHint> matchingHints = new ArrayList<IXArchADTSubstitutionHint>();
		for (IXArchADTSubstitutionHint hint : getAllSubstitutionHints()) {
			if (targetNsURI.equals(hint.getTargetNsURI()) && targetTypeName.equals(hint.getTargetTypeName())) {
				matchingHints.add(hint);
			}
		}
		return matchingHints;
	}

	@Override
	public synchronized List<ObjRef> resolveObjRefs(ObjRef contextObjRef, String xPath) throws XPathException {
		Iterator<EObject> it = EcoreXPathContextFactory.newInstance().newContext(get(contextObjRef)).iterate(xPath);
		List<ObjRef> result = Lists.newArrayList();
		while (it.hasNext()) {
			result.add(put(it.next()));
		}
		return result;
	}
	
	@Override
	public synchronized List<Serializable> resolveSerializables(ObjRef contextObjRef, String xPath) throws XPathException {
		Iterator<EObject> it = EcoreXPathContextFactory.newInstance().newContext(get(contextObjRef)).iterate(xPath);
		List<Serializable> result = Lists.newArrayList();
		while (it.hasNext()) {
			result.add(check(it.next()));
		}
		return result;
	}
}