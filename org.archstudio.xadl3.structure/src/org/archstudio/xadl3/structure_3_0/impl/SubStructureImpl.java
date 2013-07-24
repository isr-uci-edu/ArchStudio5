/**
 */
package org.archstudio.xadl3.structure_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.structure_3_0.InterfaceMapping;
import org.archstudio.xadl3.structure_3_0.Structure;
import org.archstudio.xadl3.structure_3_0.Structure_3_0Package;
import org.archstudio.xadl3.structure_3_0.SubStructure;
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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Sub Structure</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.SubStructureImpl#getInnerStructureLink <em>Inner Structure Link
 * </em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.SubStructureImpl#getInterfaceMapping <em>Interface Mapping</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.SubStructureImpl#getExt <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.SubStructureImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class SubStructureImpl extends MinimalEObjectImpl.Container implements SubStructure {
	/**
	 * The cached value of the '{@link #getInnerStructureLink() <em>Inner Structure Link</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getInnerStructureLink()
	 * @generated
	 * @ordered
	 */
	protected Structure innerStructureLink;

	/**
	 * The cached value of the '{@link #getInterfaceMapping() <em>Interface Mapping</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getInterfaceMapping()
	 * @generated
	 * @ordered
	 */
	protected EList<InterfaceMapping> interfaceMapping;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected SubStructureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Structure_3_0Package.Literals.SUB_STRUCTURE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Structure getInnerStructureLink() {
		return innerStructureLink;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setInnerStructureLink(Structure newInnerStructureLink) {
		Structure oldInnerStructureLink = innerStructureLink;
		innerStructureLink = newInnerStructureLink;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					Structure_3_0Package.SUB_STRUCTURE__INNER_STRUCTURE_LINK, oldInnerStructureLink, innerStructureLink));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<InterfaceMapping> getInterfaceMapping() {
		if (interfaceMapping == null) {
			interfaceMapping = new EObjectContainmentEList<InterfaceMapping>(InterfaceMapping.class, this,
					Structure_3_0Package.SUB_STRUCTURE__INTERFACE_MAPPING);
		}
		return interfaceMapping;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Extension> getExt() {
		if (ext == null) {
			ext = new EObjectContainmentEList<Extension>(Extension.class, this, Structure_3_0Package.SUB_STRUCTURE__EXT);
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
			eNotify(new ENotificationImpl(this, Notification.SET, Structure_3_0Package.SUB_STRUCTURE__ID, oldId, id));
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
		case Structure_3_0Package.SUB_STRUCTURE__INTERFACE_MAPPING:
			return ((InternalEList<?>) getInterfaceMapping()).basicRemove(otherEnd, msgs);
		case Structure_3_0Package.SUB_STRUCTURE__EXT:
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
		case Structure_3_0Package.SUB_STRUCTURE__INNER_STRUCTURE_LINK:
			return getInnerStructureLink();
		case Structure_3_0Package.SUB_STRUCTURE__INTERFACE_MAPPING:
			return getInterfaceMapping();
		case Structure_3_0Package.SUB_STRUCTURE__EXT:
			return getExt();
		case Structure_3_0Package.SUB_STRUCTURE__ID:
			return getId();
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
		case Structure_3_0Package.SUB_STRUCTURE__INNER_STRUCTURE_LINK:
			setInnerStructureLink((Structure) newValue);
			return;
		case Structure_3_0Package.SUB_STRUCTURE__INTERFACE_MAPPING:
			getInterfaceMapping().clear();
			getInterfaceMapping().addAll((Collection<? extends InterfaceMapping>) newValue);
			return;
		case Structure_3_0Package.SUB_STRUCTURE__EXT:
			getExt().clear();
			getExt().addAll((Collection<? extends Extension>) newValue);
			return;
		case Structure_3_0Package.SUB_STRUCTURE__ID:
			setId((String) newValue);
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
		case Structure_3_0Package.SUB_STRUCTURE__INNER_STRUCTURE_LINK:
			setInnerStructureLink((Structure) null);
			return;
		case Structure_3_0Package.SUB_STRUCTURE__INTERFACE_MAPPING:
			getInterfaceMapping().clear();
			return;
		case Structure_3_0Package.SUB_STRUCTURE__EXT:
			getExt().clear();
			return;
		case Structure_3_0Package.SUB_STRUCTURE__ID:
			setId(ID_EDEFAULT);
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
		case Structure_3_0Package.SUB_STRUCTURE__INNER_STRUCTURE_LINK:
			return innerStructureLink != null;
		case Structure_3_0Package.SUB_STRUCTURE__INTERFACE_MAPPING:
			return interfaceMapping != null && !interfaceMapping.isEmpty();
		case Structure_3_0Package.SUB_STRUCTURE__EXT:
			return ext != null && !ext.isEmpty();
		case Structure_3_0Package.SUB_STRUCTURE__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(')');
		return result.toString();
	}

} //SubStructureImpl
