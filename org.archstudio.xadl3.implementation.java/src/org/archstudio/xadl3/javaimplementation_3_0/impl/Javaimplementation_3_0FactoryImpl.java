/**
 */
package org.archstudio.xadl3.javaimplementation_3_0.impl;

import org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry;
import org.archstudio.xadl3.javaimplementation_3_0.JavaClass;
import org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Factory;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class Javaimplementation_3_0FactoryImpl extends EFactoryImpl implements Javaimplementation_3_0Factory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static Javaimplementation_3_0Factory init() {
		try {
			Javaimplementation_3_0Factory theJavaimplementation_3_0Factory = (Javaimplementation_3_0Factory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.archstudio.org/xadl3/schemas/javaimplementation-3.0.xsd");
			if (theJavaimplementation_3_0Factory != null) {
				return theJavaimplementation_3_0Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Javaimplementation_3_0FactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Javaimplementation_3_0FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
		case Javaimplementation_3_0Package.CLASS_PATH_ENTRY:
			return createClassPathEntry();
		case Javaimplementation_3_0Package.JAVA_CLASS:
			return createJavaClass();
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION:
			return createJavaImplementation();
		default:
			throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ClassPathEntry createClassPathEntry() {
		ClassPathEntryImpl classPathEntry = new ClassPathEntryImpl();
		return classPathEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public JavaClass createJavaClass() {
		JavaClassImpl javaClass = new JavaClassImpl();
		return javaClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public JavaImplementation createJavaImplementation() {
		JavaImplementationImpl javaImplementation = new JavaImplementationImpl();
		return javaImplementation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Javaimplementation_3_0Package getJavaimplementation_3_0Package() {
		return (Javaimplementation_3_0Package) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Javaimplementation_3_0Package getPackage() {
		return Javaimplementation_3_0Package.eINSTANCE;
	}

} //Javaimplementation_3_0FactoryImpl
