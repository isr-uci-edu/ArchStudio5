/**
 */
package org.archstudio.xadl3.statechart_1_0.impl;

import org.archstudio.xadl3.statechart_1_0.Behavior;
import org.archstudio.xadl3.statechart_1_0.Constraint;
import org.archstudio.xadl3.statechart_1_0.DocumentRoot;
import org.archstudio.xadl3.statechart_1_0.FinalState;
import org.archstudio.xadl3.statechart_1_0.InitialState;
import org.archstudio.xadl3.statechart_1_0.PseudoState;
import org.archstudio.xadl3.statechart_1_0.State;
import org.archstudio.xadl3.statechart_1_0.StateType;
import org.archstudio.xadl3.statechart_1_0.Statechart;
import org.archstudio.xadl3.statechart_1_0.StatechartSpecification;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Factory;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xadl3.statechart_1_0.SubStatechart;
import org.archstudio.xadl3.statechart_1_0.Transition;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!-- end-user-doc -->
 * 
 * @generated
 */
public class Statechart_1_0FactoryImpl extends EFactoryImpl implements Statechart_1_0Factory {
	/**
	 * Creates the default factory implementation. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static Statechart_1_0Factory init() {
		try {
			Statechart_1_0Factory theStatechart_1_0Factory = (Statechart_1_0Factory) EPackage.Registry.INSTANCE
					.getEFactory(Statechart_1_0Package.eNS_URI);
			if (theStatechart_1_0Factory != null) {
				return theStatechart_1_0Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Statechart_1_0FactoryImpl();
	}

	/**
	 * Creates an instance of the factory. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Statechart_1_0FactoryImpl() {
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
		case Statechart_1_0Package.BEHAVIOR:
			return createBehavior();
		case Statechart_1_0Package.CONSTRAINT:
			return createConstraint();
		case Statechart_1_0Package.DOCUMENT_ROOT:
			return createDocumentRoot();
		case Statechart_1_0Package.FINAL_STATE:
			return createFinalState();
		case Statechart_1_0Package.INITIAL_STATE:
			return createInitialState();
		case Statechart_1_0Package.PSEUDO_STATE:
			return createPseudoState();
		case Statechart_1_0Package.STATE:
			return createState();
		case Statechart_1_0Package.STATECHART:
			return createStatechart();
		case Statechart_1_0Package.STATECHART_SPECIFICATION:
			return createStatechartSpecification();
		case Statechart_1_0Package.SUB_STATECHART:
			return createSubStatechart();
		case Statechart_1_0Package.TRANSITION:
			return createTransition();
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
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
		case Statechart_1_0Package.STATE_TYPE:
			return createStateTypeFromString(eDataType, initialValue);
		case Statechart_1_0Package.STATE_TYPE_OBJECT:
			return createStateTypeObjectFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
		case Statechart_1_0Package.STATE_TYPE:
			return convertStateTypeToString(eDataType, instanceValue);
		case Statechart_1_0Package.STATE_TYPE_OBJECT:
			return convertStateTypeObjectToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Behavior createBehavior() {
		BehaviorImpl behavior = new BehaviorImpl();
		return behavior;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Constraint createConstraint() {
		ConstraintImpl constraint = new ConstraintImpl();
		return constraint;
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
	public FinalState createFinalState() {
		FinalStateImpl finalState = new FinalStateImpl();
		return finalState;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public InitialState createInitialState() {
		InitialStateImpl initialState = new InitialStateImpl();
		return initialState;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public PseudoState createPseudoState() {
		PseudoStateImpl pseudoState = new PseudoStateImpl();
		return pseudoState;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public State createState() {
		StateImpl state = new StateImpl();
		return state;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Statechart createStatechart() {
		StatechartImpl statechart = new StatechartImpl();
		return statechart;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public StatechartSpecification createStatechartSpecification() {
		StatechartSpecificationImpl statechartSpecification = new StatechartSpecificationImpl();
		return statechartSpecification;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SubStatechart createSubStatechart() {
		SubStatechartImpl subStatechart = new SubStatechartImpl();
		return subStatechart;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Transition createTransition() {
		TransitionImpl transition = new TransitionImpl();
		return transition;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public StateType createStateTypeFromString(EDataType eDataType, String initialValue) {
		StateType result = StateType.get(initialValue);
		if (result == null) {
			throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '"
					+ eDataType.getName() + "'");
		}
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertStateTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public StateType createStateTypeObjectFromString(EDataType eDataType, String initialValue) {
		return createStateTypeFromString(Statechart_1_0Package.Literals.STATE_TYPE, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public String convertStateTypeObjectToString(EDataType eDataType, Object instanceValue) {
		return convertStateTypeToString(Statechart_1_0Package.Literals.STATE_TYPE, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Statechart_1_0Package getStatechart_1_0Package() {
		return (Statechart_1_0Package) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Statechart_1_0Package getPackage() {
		return Statechart_1_0Package.eINSTANCE;
	}

} //Statechart_1_0FactoryImpl
