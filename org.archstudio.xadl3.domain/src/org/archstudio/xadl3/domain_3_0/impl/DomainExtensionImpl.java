/**
 */
package org.archstudio.xadl3.domain_3_0.impl;

import org.archstudio.xadl3.domain_3_0.Domain;
import org.archstudio.xadl3.domain_3_0.DomainExtension;
import org.archstudio.xadl3.domain_3_0.Domain_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.impl.ExtensionImpl;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Domain Extension</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link org.archstudio.xadl3.domain_3_0.impl.DomainExtensionImpl#getDomain
 * <em>Domain</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class DomainExtensionImpl extends ExtensionImpl implements DomainExtension {
	/**
	 * The cached value of the '{@link #getDomain() <em>Domain</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getDomain()
	 * @generated
	 * @ordered
	 */
	protected Domain domain;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DomainExtensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	protected EClass eStaticClass() {
		return Domain_3_0Package.Literals.DOMAIN_EXTENSION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public Domain getDomain() {
		return domain;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetDomain(Domain newDomain, NotificationChain msgs) {
		Domain oldDomain = domain;
		domain = newDomain;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					Domain_3_0Package.DOMAIN_EXTENSION__DOMAIN, oldDomain, newDomain);
			if (msgs == null) {
				msgs = notification;
			}
			else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setDomain(Domain newDomain) {
		if (newDomain != domain) {
			NotificationChain msgs = null;
			if (domain != null) {
				msgs = ((InternalEObject) domain).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- Domain_3_0Package.DOMAIN_EXTENSION__DOMAIN, null, msgs);
			}
			if (newDomain != null) {
				msgs = ((InternalEObject) newDomain).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- Domain_3_0Package.DOMAIN_EXTENSION__DOMAIN, null, msgs);
			}
			msgs = basicSetDomain(newDomain, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, Domain_3_0Package.DOMAIN_EXTENSION__DOMAIN,
					newDomain, newDomain));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Domain_3_0Package.DOMAIN_EXTENSION__DOMAIN:
			return basicSetDomain(null, msgs);
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
		case Domain_3_0Package.DOMAIN_EXTENSION__DOMAIN:
			return getDomain();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Domain_3_0Package.DOMAIN_EXTENSION__DOMAIN:
			setDomain((Domain) newValue);
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
		case Domain_3_0Package.DOMAIN_EXTENSION__DOMAIN:
			setDomain((Domain) null);
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
		case Domain_3_0Package.DOMAIN_EXTENSION__DOMAIN:
			return domain != null;
		}
		return super.eIsSet(featureID);
	}

} //DomainExtensionImpl
