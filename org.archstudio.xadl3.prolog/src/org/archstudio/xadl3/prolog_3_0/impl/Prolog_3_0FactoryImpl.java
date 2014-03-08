/**
 */
package org.archstudio.xadl3.prolog_3_0.impl;

import org.archstudio.xadl3.prolog_3_0.PrologExtension;
import org.archstudio.xadl3.prolog_3_0.Prolog_3_0Factory;
import org.archstudio.xadl3.prolog_3_0.Prolog_3_0Package;
import org.archstudio.xadl3.prolog_3_0.Statement;
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
public class Prolog_3_0FactoryImpl extends EFactoryImpl implements Prolog_3_0Factory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static Prolog_3_0Factory init() {
		try {
			Prolog_3_0Factory theProlog_3_0Factory = (Prolog_3_0Factory) EPackage.Registry.INSTANCE
					.getEFactory(Prolog_3_0Package.eNS_URI);
			if (theProlog_3_0Factory != null) {
				return theProlog_3_0Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Prolog_3_0FactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Prolog_3_0FactoryImpl() {
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
		case Prolog_3_0Package.PROLOG_EXTENSION:
			return createPrologExtension();
		case Prolog_3_0Package.STATEMENT:
			return createStatement();
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
	public PrologExtension createPrologExtension() {
		PrologExtensionImpl prologExtension = new PrologExtensionImpl();
		return prologExtension;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Statement createStatement() {
		StatementImpl statement = new StatementImpl();
		return statement;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Prolog_3_0Package getProlog_3_0Package() {
		return (Prolog_3_0Package) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Prolog_3_0Package getPackage() {
		return Prolog_3_0Package.eINSTANCE;
	}

} //Prolog_3_0FactoryImpl
