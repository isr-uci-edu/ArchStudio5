package org.archstudio.xarchadt.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.osgi.framework.Bundle;

public class Factories {
	
	private static final String EMF_EXTENSION_POINT_ID = "org.eclipse.emf.ecore.generated_package";
	public static final String MAIN_FACTORY_PACKAGE_NAME = "org.archstudio.xadl3.xadlcore_3_0";

	protected static final Factories instance = new Factories();
	
	protected EFactory mainFactory = null;
	protected Method createDocumentRootMethod = null;
	
	//Maps package names to bundle objects.
	protected final Set<Bundle> allBundles;
	
	//Maps package names to factory objects.
	protected final Map<String, EFactory> allFactories;
	
	private Factories() {
		allFactories = new HashMap<String, EFactory>();
		allBundles = new HashSet<Bundle>();
		findAllFactories();
	}
	
	public EFactory getMainFactory() {
		if (mainFactory == null) {
			mainFactory = allFactories.get(MAIN_FACTORY_PACKAGE_NAME);
			if (mainFactory == null) {
				throw new RuntimeException("Missing xADL core bindings.");
			}
		}
		return mainFactory;
	}
	
	public EObject createNewDocumentRoot() {
		if (createDocumentRootMethod == null ) {
			try {
				createDocumentRootMethod = getMainFactory().getClass().getMethod("createDocumentRoot");
			}
			catch (NoSuchMethodException nsme) {
				throw new RuntimeException("Missing xADL core binding document root creation method", nsme);
			}
		}

		try {
			EObject documentRootObject = (EObject)createDocumentRootMethod.invoke(mainFactory);
			return documentRootObject;
		}
		catch (InvocationTargetException ite) {
			throw new RuntimeException ("Can't create new document root", ite);
		}
		catch (IllegalAccessException iae) {
			throw new RuntimeException ("Can't create new document root", iae);
		}
	}
	
	public Map<String,EFactory> getFactoryMap() {
		return Collections.unmodifiableMap(allFactories);
	}
	
	private void findAllFactories() {
		IExtensionRegistry reg = Platform.getExtensionRegistry();
		if (reg != null) {
			// The Extension Registry can be null if we're running outside of Eclipse,
			// as happens in, e.g., org.archstudio.description.Main
			for (IConfigurationElement configurationElement : reg.getConfigurationElementsFor(EMF_EXTENSION_POINT_ID)) {
				String packageClassName = configurationElement.getAttribute("class");
				if (packageClassName != null) {
					String bundleName = configurationElement.getDeclaringExtension().getContributor().getName();
					try {
						Bundle bundle = Platform.getBundle(bundleName);
						allBundles.add(bundle);
						
						Class<?> packageClass = Platform.getBundle(bundleName).loadClass(packageClassName);
						Field instanceField = packageClass.getDeclaredField("eINSTANCE");
						EPackage ePackage = (EPackage)instanceField.get(packageClass);
						EFactory eFactory = ePackage.getEFactoryInstance();
						
						String javaPackageName = packageClass.getPackage().getName();
						
						allFactories.put(javaPackageName, eFactory);
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
	
	/* This method should be rarely used, except when running XArchADT outside of an Eclipse plugin */
	public void registerFactories(Map<String, EFactory> factoriesToRegister) {
		allFactories.putAll(factoriesToRegister);
	}
	
	public Class<?> findClass(String className) {
		for (Bundle bundle : allBundles) {
			try {
				return bundle.loadClass(className);
			}
			catch (ClassNotFoundException cnfe) {
			}
		}
		return null;
	}
	
	public static Factories getInstance() {
		return instance;
	}
}
