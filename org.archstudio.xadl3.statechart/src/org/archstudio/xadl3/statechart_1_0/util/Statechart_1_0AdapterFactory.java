/**
 */
package org.archstudio.xadl3.statechart_1_0.util;

import org.archstudio.xadl3.statechart_1_0.Behavior;
import org.archstudio.xadl3.statechart_1_0.Constraint;
import org.archstudio.xadl3.statechart_1_0.DocumentRoot;
import org.archstudio.xadl3.statechart_1_0.FinalState;
import org.archstudio.xadl3.statechart_1_0.InitialState;
import org.archstudio.xadl3.statechart_1_0.PseudoState;
import org.archstudio.xadl3.statechart_1_0.State;
import org.archstudio.xadl3.statechart_1_0.Statechart;
import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;
import org.archstudio.xadl3.statechart_1_0.SubStatechart;
import org.archstudio.xadl3.statechart_1_0.Transition;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package
 * @generated
 */
public class Statechart_1_0AdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static Statechart_1_0Package modelPackage;

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public Statechart_1_0AdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Statechart_1_0Package.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc --> This implementation returns <code>true</code> if
	 * the object is either the model's package or is an instance object of the
	 * model. <!-- end-user-doc -->
	 * 
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */

	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject) object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected Statechart_1_0Switch<Adapter> modelSwitch = new Statechart_1_0Switch<Adapter>() {

		public Adapter caseBehavior(Behavior object) {
			return createBehaviorAdapter();
		}

		public Adapter caseConstraint(Constraint object) {
			return createConstraintAdapter();
		}

		public Adapter caseDocumentRoot(DocumentRoot object) {
			return createDocumentRootAdapter();
		}

		public Adapter caseFinalState(FinalState object) {
			return createFinalStateAdapter();
		}

		public Adapter caseInitialState(InitialState object) {
			return createInitialStateAdapter();
		}

		public Adapter casePseudoState(PseudoState object) {
			return createPseudoStateAdapter();
		}

		public Adapter caseState(State object) {
			return createStateAdapter();
		}

		public Adapter caseStatechart(Statechart object) {
			return createStatechartAdapter();
		}

		public Adapter caseSubStatechart(SubStatechart object) {
			return createSubStatechartAdapter();
		}

		public Adapter caseTransition(Transition object) {
			return createTransitionAdapter();
		}

		public Adapter defaultCase(EObject object) {
			return createEObjectAdapter();
		}
	};

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param target
	 *            the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */

	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.statechart_1_0.Behavior <em>Behavior</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.statechart_1_0.Behavior
	 * @generated
	 */
	public Adapter createBehaviorAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.statechart_1_0.Constraint
	 * <em>Constraint</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.statechart_1_0.Constraint
	 * @generated
	 */
	public Adapter createConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.statechart_1_0.DocumentRoot
	 * <em>Document Root</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.statechart_1_0.DocumentRoot
	 * @generated
	 */
	public Adapter createDocumentRootAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.statechart_1_0.FinalState
	 * <em>Final State</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.statechart_1_0.FinalState
	 * @generated
	 */
	public Adapter createFinalStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.statechart_1_0.InitialState
	 * <em>Initial State</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.statechart_1_0.InitialState
	 * @generated
	 */
	public Adapter createInitialStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.statechart_1_0.PseudoState
	 * <em>Pseudo State</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.statechart_1_0.PseudoState
	 * @generated
	 */
	public Adapter createPseudoStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.statechart_1_0.State <em>State</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.statechart_1_0.State
	 * @generated
	 */
	public Adapter createStateAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.statechart_1_0.Statechart
	 * <em>Statechart</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.statechart_1_0.Statechart
	 * @generated
	 */
	public Adapter createStatechartAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.statechart_1_0.SubStatechart
	 * <em>Sub Statechart</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.statechart_1_0.SubStatechart
	 * @generated
	 */
	public Adapter createSubStatechartAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link org.archstudio.xadl3.statechart_1_0.Transition
	 * <em>Transition</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's
	 * useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see org.archstudio.xadl3.statechart_1_0.Transition
	 * @generated
	 */
	public Adapter createTransitionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //Statechart_1_0AdapterFactory
