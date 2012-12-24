/**
 */
package net.gexf_1_2.gexf.impl;

import java.math.BigInteger;
import java.util.Collection;

import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.NodeContent;
import net.gexf_1_2.gexf.NodesContent;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Nodes Content</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.NodesContentImpl#getNode <em>Node</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodesContentImpl#getCount <em>Count</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class NodesContentImpl extends EObjectImpl implements NodesContent {
	/**
	 * The cached value of the '{@link #getNode() <em>Node</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getNode()
	 * @generated
	 * @ordered
	 */
	protected EList<NodeContent> node;

	/**
	 * The default value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getCount()
	 * @generated
	 * @ordered
	 */
	protected static final BigInteger COUNT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getCount() <em>Count</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
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
	protected NodesContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	protected EClass eStaticClass() {
		return GexfPackage.Literals.NODES_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<NodeContent> getNode() {
		if (node == null) {
			node = new EObjectContainmentEList<NodeContent>(NodeContent.class, this, GexfPackage.NODES_CONTENT__NODE);
		}
		return node;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public BigInteger getCount() {
		return count;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setCount(BigInteger newCount) {
		BigInteger oldCount = count;
		count = newCount;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.NODES_CONTENT__COUNT, oldCount, count));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GexfPackage.NODES_CONTENT__NODE:
			return ((InternalEList<?>) getNode()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GexfPackage.NODES_CONTENT__NODE:
			return getNode();
		case GexfPackage.NODES_CONTENT__COUNT:
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
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GexfPackage.NODES_CONTENT__NODE:
			getNode().clear();
			getNode().addAll((Collection<? extends NodeContent>) newValue);
			return;
		case GexfPackage.NODES_CONTENT__COUNT:
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

	public void eUnset(int featureID) {
		switch (featureID) {
		case GexfPackage.NODES_CONTENT__NODE:
			getNode().clear();
			return;
		case GexfPackage.NODES_CONTENT__COUNT:
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

	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case GexfPackage.NODES_CONTENT__NODE:
			return node != null && !node.isEmpty();
		case GexfPackage.NODES_CONTENT__COUNT:
			return COUNT_EDEFAULT == null ? count != null : !COUNT_EDEFAULT.equals(count);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

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

} //NodesContentImpl
