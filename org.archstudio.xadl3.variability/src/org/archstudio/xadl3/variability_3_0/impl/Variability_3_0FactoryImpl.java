/**
 */
package org.archstudio.xadl3.variability_3_0.impl;

import org.archstudio.xadl3.variability_3_0.AttributeChange;
import org.archstudio.xadl3.variability_3_0.Change;
import org.archstudio.xadl3.variability_3_0.ChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.DocumentRoot;
import org.archstudio.xadl3.variability_3_0.ElementChange;
import org.archstudio.xadl3.variability_3_0.ElementManyChange;
import org.archstudio.xadl3.variability_3_0.JavaTransformChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.TransformChangeSetOfChanges;
import org.archstudio.xadl3.variability_3_0.Variability;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Factory;
import org.archstudio.xadl3.variability_3_0.Variability_3_0Package;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * 
 * @generated
 */
public class Variability_3_0FactoryImpl extends EFactoryImpl implements Variability_3_0Factory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public static Variability_3_0Factory init() {
		try {
			Variability_3_0Factory theVariability_3_0Factory = (Variability_3_0Factory) EPackage.Registry.INSTANCE
					.getEFactory("http://www.archstudio.org/xadl3/schemas/variability-3.0.xsd");
			if (theVariability_3_0Factory != null) {
				return theVariability_3_0Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Variability_3_0FactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public Variability_3_0FactoryImpl() {
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
		case Variability_3_0Package.ATTRIBUTE_CHANGE:
			return createAttributeChange();
		case Variability_3_0Package.CHANGE:
			return createChange();
		case Variability_3_0Package.CHANGE_SET_OF_CHANGES:
			return createChangeSetOfChanges();
		case Variability_3_0Package.DOCUMENT_ROOT:
			return createDocumentRoot();
		case Variability_3_0Package.ELEMENT_CHANGE:
			return createElementChange();
		case Variability_3_0Package.ELEMENT_MANY_CHANGE:
			return createElementManyChange();
		case Variability_3_0Package.JAVA_TRANSFORM_CHANGE_SET_OF_CHANGES:
			return createJavaTransformChangeSetOfChanges();
		case Variability_3_0Package.TRANSFORM_CHANGE_SET_OF_CHANGES:
			return createTransformChangeSetOfChanges();
		case Variability_3_0Package.VARIABILITY:
			return createVariability();
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
	public AttributeChange createAttributeChange() {
		AttributeChangeImpl attributeChange = new AttributeChangeImpl();
		return attributeChange;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Change createChange() {
		ChangeImpl change = new ChangeImpl();
		return change;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ChangeSetOfChanges createChangeSetOfChanges() {
		ChangeSetOfChangesImpl changeSetOfChanges = new ChangeSetOfChangesImpl();
		return changeSetOfChanges;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public DocumentRoot createDocumentRoot() {
		DocumentRootImpl documentRoot = new DocumentRootImpl();
		return documentRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ElementChange createElementChange() {
		ElementChangeImpl elementChange = new ElementChangeImpl();
		return elementChange;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ElementManyChange createElementManyChange() {
		ElementManyChangeImpl elementManyChange = new ElementManyChangeImpl();
		return elementManyChange;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public JavaTransformChangeSetOfChanges createJavaTransformChangeSetOfChanges() {
		JavaTransformChangeSetOfChangesImpl javaTransformChangeSetOfChanges = new JavaTransformChangeSetOfChangesImpl();
		return javaTransformChangeSetOfChanges;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public TransformChangeSetOfChanges createTransformChangeSetOfChanges() {
		TransformChangeSetOfChangesImpl transformChangeSetOfChanges = new TransformChangeSetOfChangesImpl();
		return transformChangeSetOfChanges;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Variability createVariability() {
		VariabilityImpl variability = new VariabilityImpl();
		return variability;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Variability_3_0Package getVariability_3_0Package() {
		return (Variability_3_0Package) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Variability_3_0Package getPackage() {
		return Variability_3_0Package.eINSTANCE;
	}

} //Variability_3_0FactoryImpl
