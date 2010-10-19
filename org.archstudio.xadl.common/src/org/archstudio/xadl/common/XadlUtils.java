package org.archstudio.xadl.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

import org.archstudio.xarchadt.common.IXArchADT;
import org.archstudio.xarchadt.common.IXArchADTFeature;
import org.archstudio.xarchadt.common.IXArchADTQuery;
import org.archstudio.xarchadt.common.IXArchADTTypeMetadata;
import org.archstudio.xarchadt.common.ObjRef;
import org.archstudio.xarchadt.common.XArchADTModelEvent;
import org.archstudio.sysutils.SystemUtils;

public class XadlUtils {

	public static String getID(IXArchADTQuery xarch, ObjRef ref){
		try{
			return (String)xarch.get(ref, "id");
		}
		catch(Exception e){
			return null;
		}
	}
	
	public static String getName(IXArchADTQuery xarch, ObjRef ref){
		try{
			String name = (String)xarch.get(ref, "name");
			return name == null ? "[No Name]" : name;
		}
		catch(Exception e){
			return null;
		}
	}
	
	public static void setName(IXArchADT xarch, ObjRef ref, String newName) {
		xarch.set(ref, "name", newName);
	}
	
	public static ObjRef getExt(IXArchADTQuery xarch, ObjRef ref, String extType){
		for(ObjRef extRef : xarch.getAll(ref, "ext")){
			if(xarch.isInstanceOf(extRef, extType)){
				return extRef;
			}
		}
		return null;
	}
	
	public static ObjRef getLookupImplementation(IXArchADTQuery xarch, ObjRef ref){
		ObjRef implementationExtRef = XadlUtils.getExt(xarch, ref, "org.archstudio.xadl3.implementation_3_0.ImplementationExtension");
		if(implementationExtRef != null){
			List<ObjRef> implementationRefs = xarch.getAll(implementationExtRef, "implementation");
			for(ObjRef implementationRef : implementationRefs){
				if(xarch.isInstanceOf(implementationRef, "org.archstudio.xadl3.lookupimplementation_3_0.LookupImplementation")){
					return implementationRef;
				}
			}
		}
		return null;
	}

	public static ObjRef getJavaImplementation(IXArchADTQuery xarch, ObjRef ref){
		ObjRef implementationExtRef = XadlUtils.getExt(xarch, ref, "org.archstudio.xadl3.implementation_3_0.ImplementationExtension");
		if(implementationExtRef != null){
			List<ObjRef> implementationRefs = xarch.getAll(implementationExtRef, "implementation");
			for(ObjRef implementationRef : implementationRefs){
				if(xarch.isInstanceOf(implementationRef, "org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation")){
					return implementationRef;
				}
			}
		}
		return null;
	}
	
	public static List<ObjRef> getAllSubstitutionGroupElementsByType(IXArchADTQuery xarch, ObjRef ref, String substitutionGroupName, String targetElementClass){
		List<ObjRef> objRefs = xarch.getAll(ref, substitutionGroupName);
		
		List<ObjRef> matchingRefs = new ArrayList<ObjRef>(objRefs.size());
		for(ObjRef objRef : objRefs){
			if(xarch.isInstanceOf(objRef, targetElementClass)){
				matchingRefs.add(objRef);
			}
		}
		return matchingRefs;
	}
	
	public static List<ObjRef> getAllSubstitutionGroupElementsByTag(IXArchADTQuery xarch, ObjRef ref, String substitutionGroupName, String tagName){
		List<ObjRef> objRefs = xarch.getAll(ref, substitutionGroupName);
		
		List<ObjRef> matchingRefs = new ArrayList<ObjRef>(objRefs.size());
		for(ObjRef objRef : objRefs){
			String refTagName = xarch.getTagName(objRef);
			if(tagName.equals(refTagName)){
				matchingRefs.add(objRef);
			}
		}
		return matchingRefs;
	}
	
	public static boolean isTargetedToDocument(IXArchADTQuery xarch, ObjRef documentRootRef, XArchADTModelEvent evt){
		if(evt.getSource() == null) return false;
		return xarch.equals(documentRootRef, xarch.getDocumentRootRef(evt.getSource()));
	}
	
	public static String getDisplayName(IXArchADTQuery xarch, ObjRef ref){
		StringBuffer sb = new StringBuffer();
		
		String tagName = xarch.getTagName(ref);
		String containingFeatureName = xarch.getContainingFeatureName(ref);
		
		if(tagName == null){
			sb.append(SystemUtils.capFirst(containingFeatureName));
		}
		else if(tagName.equals(containingFeatureName)){
			sb.append(SystemUtils.capFirst(tagName));
		}
		else{
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
		if(!isEnumeratorClass(enumerationClass)){
			throw new IllegalArgumentException("Class " + enumerationClass.getCanonicalName() + " is not an enumerator class.");
		}
		try {
			Method m = enumerationClass.getMethod("get", java.lang.String.class);
			Object value = m.invoke(enumerationClass, stringValue);
			return value;
		}
		catch(NoSuchMethodException nsme){
			return null;
		}
		catch(InvocationTargetException ite){
			return null;
		}
		catch(IllegalAccessException iae){
			return null;
		}
	}
	
	public static IXArchADTFeature getFeatureByName(IXArchADTQuery xarch, ObjRef ref, String name) {
		IXArchADTTypeMetadata typeMetadata = xarch.getTypeMetadata(ref);
		for(IXArchADTFeature feature : typeMetadata.getFeatures()){
			if(feature.getName().equals(name)){
				return feature;
			}
		}
		return null;
	}
	
	public static boolean isRootElement(IXArchADTQuery xarch, ObjRef ref){
		return xarch.getTagsOnlyPathString(ref).equals("xADL");
	}
}
