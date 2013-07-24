/**
 */
package net.gexf_1_2.gexf.impl;

import java.math.BigInteger;
import java.util.Collection;

import net.gexf_1_2.gexf.EdgeContent;
import net.gexf_1_2.gexf.EdgesContent;
import net.gexf_1_2.gexf.GexfPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Edges Content</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgesContentImpl#getEdge <em>Edge</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgesContentImpl#getCount <em>Count</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EdgesContentImpl extends MinimalEObjectImpl.Container implements EdgesContent {
	/**
	 * The cached value of the '{@link #getEdge() <em>Edge</em>}' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getEdge()
	 * @generated
	 * @ordered
	 */
	protected EList<EdgeContent> edge;

	/**
	 * The default value of the '{@link #getCount() <em>Count</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger COUNT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCount() <em>Count</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected BigInteger count = COUNT_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EdgesContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GexfPackage.Literals.EDGES_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EdgeContent> getEdge() {
		if (edge == null) {
			edge = new EObjectContainmentEList<EdgeContent>(EdgeContent.class, this, GexfPackage.EDGES_CONTENT__EDGE);
		}
		return edge;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public BigInteger getCount() {
		return count;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setCount(BigInteger newCount) {
		BigInteger oldCount = count;
		count = newCount;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.EDGES_CONTENT__COUNT, oldCount, count));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GexfPackage.EDGES_CONTENT__EDGE:
			return ((InternalEList<?>) getEdge()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GexfPackage.EDGES_CONTENT__EDGE:
			return getEdge();
		case GexfPackage.EDGES_CONTENT__COUNT:
			return getCount();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GexfPackage.EDGES_CONTENT__EDGE:
			getEdge().clear();
			getEdge().addAll((Collection<? extends EdgeContent>) newValue);
			return;
		case GexfPackage.EDGES_CONTENT__COUNT:
			setCount((BigInteger) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case GexfPackage.EDGES_CONTENT__EDGE:
			getEdge().clear();
			return;
		case GexfPackage.EDGES_CONTENT__COUNT:
			setCount(COUNT_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case GexfPackage.EDGES_CONTENT__EDGE:
			return edge != null && !edge.isEmpty();
		case GexfPackage.EDGES_CONTENT__COUNT:
			return COUNT_EDEFAULT == null ? count != null : !COUNT_EDEFAULT.equals(count);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (count: ");
		result.append(count);
		result.append(')');
		return result.toString();
	}

} //EdgesContentImpl
