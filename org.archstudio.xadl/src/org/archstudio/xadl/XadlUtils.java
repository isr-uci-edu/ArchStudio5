package org.archstudio.xadl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.archstudio.sysutils.SystemUtils;
import org.archstudio.xadl3.implementation_3_0.Implementation_3_0Package;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.archstudio.xarchadt.IXArchADT;
import org.archstudio.xarchadt.IXArchADTFeature;
import org.archstudio.xarchadt.IXArchADTQuery;
import org.archstudio.xarchadt.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.ObjRef;
import org.archstudio.xarchadt.XArchADTModelEvent;
import org.archstudio.xarchadt.XArchADTProxy;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

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
		for (ObjRef extRef : Iterables.filter(xarch.getAll(ref, "ext"), ObjRef.class)) {
			if (xarch.isInstanceOf(extRef, extensionNsURI, extensionTypeName)) {
				return extRef;
			}
		}
		return null;
	}

	public static ObjRef getExt(IXArchADTQuery xarch, ObjRef ref, EClass extClass) {
		return getExt(xarch, ref, extClass.getEPackage().getNsURI(), extClass.getName());
	}

	@SuppressWarnings("unchecked")
	public static <T extends Extension> T getExt(IXArchADT xarch, EObject eObject, EClass extClass) {
		return (T) XArchADTProxy.proxy(xarch, getExt(xarch, XArchADTProxy.unproxy(eObject), extClass));
	}

	public static ObjRef createExt(IXArchADT xarch, ObjRef ref, EClass extClass) {
		ObjRef extRef = getExt(xarch, ref, extClass);
		if (extRef == null) {
			extRef = create(xarch, extClass);
			xarch.add(ref, "ext", extRef);
		}
		return extRef;
	}

	@SuppressWarnings("unchecked")
	public static <T extends Extension> T createExt(IXArchADT xarch, EObject eObject, EClass extClass) {
		return (T) XArchADTProxy.proxy(xarch, createExt(xarch, XArchADTProxy.unproxy(eObject), extClass));
	}

	public static boolean isBrick(IXArchADTQuery xarch, ObjRef ref) {
		return isInstanceOf(xarch, ref, Structure_3_0Package.Literals.BRICK);
	}

	public static boolean isComponent(IXArchADTQuery xarch, ObjRef ref) {
		return isInstanceOf(xarch, ref, Structure_3_0Package.Literals.COMPONENT);
	}

	public static boolean isConnector(IXArchADTQuery xarch, ObjRef ref) {
		return isInstanceOf(xarch, ref, Structure_3_0Package.Literals.CONNECTOR);
	}

	public static ObjRef getImplementation(IXArchADTQuery xarch, ObjRef ref, EClass type) {
		ObjRef implementationExtRef = XadlUtils.getExt(xarch, ref,
				Implementation_3_0Package.Literals.IMPLEMENTATION_EXTENSION);
		if (implementationExtRef != null) {
			for (ObjRef implementationRef : Iterables.filter(xarch.getAll(implementationExtRef, "implementation"),
					ObjRef.class)) {
				if (XadlUtils.isInstanceOf(xarch, implementationRef, type)) {
					return implementationRef;
				}
			}
		}
		return null;
	}

	public static List<ObjRef> getAllSubstitutionGroupElementsByType(IXArchADTQuery xarch, ObjRef ref,
			String substitutionGroupName, String targetNsURI, String targetTypeName) {
		List<ObjRef> matchingRefs = Lists.newArrayList();
		for (ObjRef objRef : Iterables.filter(xarch.getAll(ref, substitutionGroupName), ObjRef.class)) {
			if (xarch.isInstanceOf(objRef, targetNsURI, targetTypeName)) {
				matchingRefs.add(objRef);
			}
		}
		return matchingRefs;
	}

	public static List<ObjRef> getAllSubstitutionGroupElementsByTag(IXArchADTQuery xarch, ObjRef ref,
			String substitutionGroupName, String tagName) {
		List<ObjRef> matchingRefs = Lists.newArrayList();
		for (ObjRef objRef : Iterables.filter(xarch.getAll(ref, substitutionGroupName), ObjRef.class)) {
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

	public static <DOCUMENT_ROOT extends EObject> DOCUMENT_ROOT createDocument(IXArchADT xarch, URI uri,
			EReference documentRootReference) {

		EClass eClass = documentRootReference.getEContainingClass();
		String eClassURI = eClass.getEPackage().getNsURI();
		ObjRef documentRootRef = xarch
				.createDocument(uri, eClassURI, eClass.getName(), documentRootReference.getName());

		return XArchADTProxy.proxy(xarch, documentRootRef);
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
	 * Returns true if an instance of the thing referred to by typeMetadata
	 * could be assigned to the feature.
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

	public static void remove(IXArchADT xarch, ObjRef objRef) {
		ObjRef parentRef = xarch.getParent(objRef);
		if (parentRef != null) {
			IXArchADTTypeMetadata type = xarch.getTypeMetadata(parentRef);
			String elementName = xarch.getContainingFeatureName(objRef);
			switch (type.getFeatures().get(elementName).getType()) {
			case ATTRIBUTE:
			case ELEMENT_SINGLE:
				xarch.clear(parentRef, elementName);
				break;
			case ELEMENT_MULTIPLE:
				xarch.remove(parentRef, elementName, objRef);
				break;
			}
		}
	}
}
