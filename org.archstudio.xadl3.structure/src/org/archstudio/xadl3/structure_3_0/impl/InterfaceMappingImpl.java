/**
 */
package org.archstudio.xadl3.structure_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.structure_3_0.Interface;
import org.archstudio.xadl3.structure_3_0.InterfaceMapping;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.Extension;
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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Interface Mapping</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.InterfaceMappingImpl#getOuterInterfaceLink <em>Outer Interface
 * Link</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.InterfaceMappingImpl#getInnerInterfaceLink <em>Inner Interface
 * Link</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.InterfaceMappingImpl#getExt <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.InterfaceMappingImpl#getId <em>Id</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.InterfaceMappingImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InterfaceMappingImpl extends MinimalEObjectImpl.Container implements InterfaceMapping {
	/**
	 * The cached value of the '{@link #getOuterInterfaceLink() <em>Outer Interface Link</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOuterInterfaceLink()
	 * @generated
	 * @ordered
	 */
	protected Interface outerInterfaceLink;

	/**
	 * The cached value of the '{@link #getInnerInterfaceLink() <em>Inner Interface Link</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getInnerInterfaceLink()
	 * @generated
	 * @ordered
	 */
	protected Interface innerInterfaceLink;

	/**
	 * The cached value of the '{@link #getExt() <em>Ext</em>}' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getExt()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> ext;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected InterfaceMappingImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Structure_3_0Package.Literals.INTERFACE_MAPPING;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Interface getOuterInterfaceLink() {
		return outerInterfaceLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setOuterInterfaceLink(Interface newOuterInterfaceLink) {
		Interface oldOuterInterfaceLink = outerInterfaceLink;
		outerInterfaceLink = newOuterInterfaceLink;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					Structure_3_0Package.INTERFACE_MAPPING__OUTER_INTERFACE_LINK, oldOuterInterfaceLink,
					outerInterfaceLink));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Interface getInnerInterfaceLink() {
		return innerInterfaceLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setInnerInterfaceLink(Interface newInnerInterfaceLink) {
		Interface oldInnerInterfaceLink = innerInterfaceLink;
		innerInterfaceLink = newInnerInterfaceLink;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					Structure_3_0Package.INTERFACE_MAPPING__INNER_INTERFACE_LINK, oldInnerInterfaceLink,
					innerInterfaceLink));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Extension> getExt() {
		if (ext == null) {
			ext = new EObjectContainmentEList<Extension>(Extension.class, this,
					Structure_3_0Package.INTERFACE_MAPPING__EXT);
		}
		return ext;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, Structure_3_0Package.INTERFACE_MAPPING__ID, oldId, id));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, Structure_3_0Package.INTERFACE_MAPPING__NAME,
					oldName, name));
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
		case Structure_3_0Package.INTERFACE_MAPPING__EXT:
			return ((InternalEList<?>) getExt()).basicRemove(otherEnd, msgs);
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
		case Structure_3_0Package.INTERFACE_MAPPING__OUTER_INTERFACE_LINK:
			return getOuterInterfaceLink();
		case Structure_3_0Package.INTERFACE_MAPPING__INNER_INTERFACE_LINK:
			return getInnerInterfaceLink();
		case Structure_3_0Package.INTERFACE_MAPPING__EXT:
			return getExt();
		case Structure_3_0Package.INTERFACE_MAPPING__ID:
			return getId();
		case Structure_3_0Package.INTERFACE_MAPPING__NAME:
			return getName();
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
		case Structure_3_0Package.INTERFACE_MAPPING__OUTER_INTERFACE_LINK:
			setOuterInterfaceLink((Interface) newValue);
			return;
		case Structure_3_0Package.INTERFACE_MAPPING__INNER_INTERFACE_LINK:
			setInnerInterfaceLink((Interface) newValue);
			return;
		case Structure_3_0Package.INTERFACE_MAPPING__EXT:
			getExt().clear();
			getExt().addAll((Collection<? extends Extension>) newValue);
			return;
		case Structure_3_0Package.INTERFACE_MAPPING__ID:
			setId((String) newValue);
			return;
		case Structure_3_0Package.INTERFACE_MAPPING__NAME:
			setName((String) newValue);
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
		case Structure_3_0Package.INTERFACE_MAPPING__OUTER_INTERFACE_LINK:
			setOuterInterfaceLink((Interface) null);
			return;
		case Structure_3_0Package.INTERFACE_MAPPING__INNER_INTERFACE_LINK:
			setInnerInterfaceLink((Interface) null);
			return;
		case Structure_3_0Package.INTERFACE_MAPPING__EXT:
			getExt().clear();
			return;
		case Structure_3_0Package.INTERFACE_MAPPING__ID:
			setId(ID_EDEFAULT);
			return;
		case Structure_3_0Package.INTERFACE_MAPPING__NAME:
			setName(NAME_EDEFAULT);
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
		case Structure_3_0Package.INTERFACE_MAPPING__OUTER_INTERFACE_LINK:
			return outerInterfaceLink != null;
		case Structure_3_0Package.INTERFACE_MAPPING__INNER_INTERFACE_LINK:
			return innerInterfaceLink != null;
		case Structure_3_0Package.INTERFACE_MAPPING__EXT:
			return ext != null && !ext.isEmpty();
		case Structure_3_0Package.INTERFACE_MAPPING__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case Structure_3_0Package.INTERFACE_MAPPING__NAME:
			return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (id: ");
		result.append(id);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //InterfaceMappingImpl
