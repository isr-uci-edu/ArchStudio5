package org.archstudio.xadl.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package;
import org.archstudio.xadl3.lookupimplementation_3_0.Lookupimplementation_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package;
import org.archstudio.xarchadt.common.IXArchADT;
import org.archstudio.xarchadt.common.IXArchADTFeature;
import org.archstudio.xarchadt.common.IXArchADTQuery;
import org.archstudio.xarchadt.common.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.xarchadt.common.XArchADTModelEvent;
import org.archstudio.xarchadt.core.XArchADTProxy;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

public class XadlUtils {

	public static String getID(IXArchADTQuery xarch, ObjRef ref) {
		try {
			return (String) xarch.get(ref, "id");
		}
		catch (Exception e) {
			return null;
		}
	}

	public static String getName(IXArchADTQuery xarch, ObjRef ref) {
		try {
			String name = (String) xarch.get(ref, "name");
			return name == null ? "[No Name]" : name;
		}
		catch (Exception e) {
			return null;
		}
	}

	public static void setName(IXArchADT xarch, ObjRef ref, String newName) {
		xarch.set(ref, "name", newName);
	}

	public static ObjRef getExt(IXArchADTQuery xarch, ObjRef ref, String extensionNsURI, String extensionTypeName) {
		for (ObjRef extRef : xarch.getAll(ref, "ext")) {
			if (xarch.isInstanceOf(extRef, extensionNsURI, extensionTypeName)) {
				return extRef;
			}
		}
		return null;
	}

	public static boolean isExtension(IXArchADTQuery xarch, IXArchADTFeature feature) {
		EClass extensionEClass = Xadlcore_3_0Package.Literals.EXTENSION;
		return xarch.isAssignable(extensionEClass.getEPackage().getNsURI(), extensionEClass.getName(),
				feature.getNsURI(), feature.getTypeName());
	}

	public static ObjRef getLookupImplementation(IXArchADTQuery xarch, ObjRef ref) {
		ObjRef implementationExtRef = XadlUtils.getExt(xarch, ref, Implementation_3_0Package.eNS_URI,
				"ImplementationExtension");
		if (implementationExtRef != null) {
			List<ObjRef> implementationRefs = xarch.getAll(implementationExtRef, "implementation");
			for (ObjRef implementationRef : implementationRefs) {
				if (XadlUtils.isInstanceOf(xarch, implementationRef,
						Lookupimplementation_3_0Package.Literals.LOOKUP_IMPLEMENTATION)) {
					return implementationRef;
				}
			}
		}
		return null;
	}

	public static ObjRef getJavaImplementation(IXArchADTQuery xarch, ObjRef ref) {
		ObjRef implementationExtRef = XadlUtils.getExt(xarch, ref, Implementation_3_0Package.eNS_URI,
				"ImplementationExtension");
		if (implementationExtRef != null) {
			List<ObjRef> implementationRefs = xarch.getAll(implementationExtRef, "implementation");
			for (ObjRef implementationRef : implementationRefs) {
				if (XadlUtils.isInstanceOf(xarch, implementationRef,
						Javaimplementation_3_0Package.Literals.JAVA_IMPLEMENTATION)) {
					return implementationRef;
				}
			}
		}
		return null;
	}

	public static List<ObjRef> getAllSubstitutionGroupElementsByType(IXArchADTQuery xarch, ObjRef ref,
			String substitutionGroupName, String targetNsURI, String targetTypeName) {
		List<ObjRef> objRefs = xarch.getAll(ref, substitutionGroupName);

		List<ObjRef> matchingRefs = new ArrayList<ObjRef>(objRefs.size());
		for (ObjRef objRef : objRefs) {
			if (xarch.isInstanceOf(objRef, targetNsURI, targetTypeName)) {
				matchingRefs.add(objRef);
			}
		}
		return matchingRefs;
	}

	public static List<ObjRef> getAllSubstitutionGroupElementsByTag(IXArchADTQuery xarch, ObjRef ref,
			String substitutionGroupName, String tagName) {
		List<ObjRef> objRefs = xarch.getAll(ref, substitutionGroupName);

		List<ObjRef> matchingRefs = new ArrayList<ObjRef>(objRefs.size());
		for (ObjRef objRef : objRefs) {
			String refTagName = xarch.getTagName(objRef);
			if (tagName.equals(refTagName)) {
				matchingRefs.add(objRef);
			}
		}
		return matchingRefs;
	}

	public static boolean isTargetedToDocument(IXArchADTQuery xarch, ObjRef documentRootRef, XArchADTModelEvent evt) {
		if (evt.getSource() == null) {
			return false;
		}
		return xarch.equals(documentRootRef, xarch.getDocumentRootRef(evt.getSource()));
	}

	public static String getDisplayName(IXArchADTQuery xarch, ObjRef ref) {
		StringBuffer sb = new StringBuffer();

		String tagName = xarch.getTagName(ref);
		String containingFeatureName = xarch.getContainingFeatureName(ref);

		if (tagName == null) {
			sb.append(SystemUtils.capFirst(containingFeatureName));
		}
		else if (tagName.equals(containingFeatureName)) {
			sb.append(SystemUtils.capFirst(tagName));
		}
		else {
			sb.append(SystemUtils.capFirst(containingFeatureName));
			sb.append(" (");
			sb.append(SystemUtils.capFirst(tagName));
			sb.append(")");
		}
		return sb.toString();
	}

	public static boolean isEnumeratorClass(Class<?> classToCheck) {
		return Enumerator.class.isAssignableFrom(classToCheck);
	}

	public static Object getEnumeratorValue(Class<?> enumerationClass, String stringValue) {
		if (!XadlUtils.isEnumeratorClass(enumerationClass)) {
			throw new IllegalArgumentException("Class " + enumerationClass.getCanonicalName()
					+ " is not an enumerator class.");
		}
		try {
			Method m = enumerationClass.getMethod("get", java.lang.String.class);
			Object value = m.invoke(enumerationClass, stringValue);
			return value;
		}
		catch (NoSuchMethodException nsme) {
			return null;
		}
		catch (InvocationTargetException ite) {
			return null;
		}
		catch (IllegalAccessException iae) {
			return null;
		}
	}

	public static IXArchADTFeature getFeatureByName(IXArchADTQuery xarch, ObjRef ref, String name) {
		IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(ref);
		for (IXArchADTFeature feature : typeMetadata.getFeatures().values()) {
			if (feature.getName().equals(name)) {
				return feature;
			}
		}
		return null;
	}

	public static boolean isRootElement(IXArchADTQuery xarch, ObjRef ref) {
		// FIXME: This won't work for non-xADL documents
		return xarch.getTagsOnlyPathString(ref).equals("xADL");
	}

	public static ObjRef create(IXArchADT xarch, EClass eClass) {
		return xarch.create(eClass.getEPackage().getNsURI(), eClass.getName());
	}

	public static ObjRef createDocument(IXArchADT xarch, URI uri, EReference rootReference) {
		EClass eClass = rootReference.getEReferenceType();
		return xarch.createDocument(uri, eClass.getEPackage().getNsURI(), eClass.getName(), rootReference.getName());
	}

	public static <O extends EObject> O createDocumentEObject(IXArchADT xarch, URI uri, EReference rootReference) {
		EClass eClass = rootReference.getEReferenceType();
		ObjRef documentRootRef = xarch.createDocument(uri, eClass.getEPackage().getNsURI(), eClass.getName(),
				rootReference.getName());
		return XArchADTProxy.proxy(xarch, (ObjRef) xarch.get(documentRootRef, rootReference.getName()));
	}

	public static boolean isInstanceOf(IXArchADTQuery xarch, ObjRef baseObjRef, EClass eClass) {
		return xarch.isInstanceOf(baseObjRef, eClass.getEPackage().getNsURI(), eClass.getName());
	}

	public static boolean isInstanceOf(IXArchADTQuery xarch, IXArchADTFeature feature, EClass eClass) {
		return xarch.isAssignable(feature.getNsURI(), feature.getTypeName(), eClass.getEPackage().getNsURI(),
				eClass.getName());
	}

	public static boolean isInstanceOf(IXArchADTQuery xarch, EClass objectEClass, EClass eClass) {
		return xarch.isAssignable(objectEClass.getEPackage().getNsURI(), objectEClass.getName(), eClass.getEPackage()
				.getNsURI(), eClass.getName());
	}

	/**
	 * Returns true if an instance of the thing referred to by typeMetadata could be assigned to the feature.
	 * 
	 * @param xarch
	 *            xArchADT instance
	 * @param feature
	 *            The feature that is going to be assigned.
	 * @param typeMetadata
	 *            The thing you want to assign to the feature.
	 * @return
	 */
	public static boolean isAssignableTo(IXArchADTQuery xarch, IXArchADTFeature feature,
			IXArchADTTypeMetadata typeMetadata) {
		return xarch.isAssignable(feature.getNsURI(), feature.getTypeName(), typeMetadata.getNsURI(),
				typeMetadata.getTypeName());
	}
}
