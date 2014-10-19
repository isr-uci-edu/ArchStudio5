/**
 */
package org.archstudio.xadl3.structure_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.structure_3_0.Component;
import org.archstudio.xadl3.structure_3_0.Connector;
import org.archstudio.xadl3.structure_3_0.Link;
import org.archstudio.xadl3.structure_3_0.Structure;
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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Structure</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.StructureImpl#getComponent <em>Component</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.StructureImpl#getConnector <em>Connector</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.StructureImpl#getLink <em>Link</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.StructureImpl#getExt <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.StructureImpl#getId <em>Id</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.impl.StructureImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StructureImpl extends MinimalEObjectImpl.Container implements Structure {
	/**
	 * The cached value of the '{@link #getComponent() <em>Component</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getComponent()
	 * @generated
	 * @ordered
	 */
	protected EList<Component> component;

	/**
	 * The cached value of the '{@link #getConnector() <em>Connector</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getConnector()
	 * @generated
	 * @ordered
	 */
	protected EList<Connector> connector;

	/**
	 * The cached value of the '{@link #getLink() <em>Link</em>}' containment reference list. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getLink()
	 * @generated
	 * @ordered
	 */
	protected EList<Link> link;

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
	protected StructureImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Structure_3_0Package.Literals.STRUCTURE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Component> getComponent() {
		if (component == null) {
			component = new EObjectContainmentEList<Component>(Component.class, this,
					Structure_3_0Package.STRUCTURE__COMPONENT);
		}
		return component;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Connector> getConnector() {
		if (connector == null) {
			connector = new EObjectContainmentEList<Connector>(Connector.class, this,
					Structure_3_0Package.STRUCTURE__CONNECTOR);
		}
		return connector;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Link> getLink() {
		if (link == null) {
			link = new EObjectContainmentEList<Link>(Link.class, this, Structure_3_0Package.STRUCTURE__LINK);
		}
		return link;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Extension> getExt() {
		if (ext == null) {
			ext = new EObjectContainmentEList<Extension>(Extension.class, this, Structure_3_0Package.STRUCTURE__EXT);
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
			eNotify(new ENotificationImpl(this, Notification.SET, Structure_3_0Package.STRUCTURE__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, Structure_3_0Package.STRUCTURE__NAME, oldName, name));
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
		case Structure_3_0Package.STRUCTURE__COMPONENT:
			return ((InternalEList<?>) getComponent()).basicRemove(otherEnd, msgs);
		case Structure_3_0Package.STRUCTURE__CONNECTOR:
			return ((InternalEList<?>) getConnector()).basicRemove(otherEnd, msgs);
		case Structure_3_0Package.STRUCTURE__LINK:
			return ((InternalEList<?>) getLink()).basicRemove(otherEnd, msgs);
		case Structure_3_0Package.STRUCTURE__EXT:
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
		case Structure_3_0Package.STRUCTURE__COMPONENT:
			return getComponent();
		case Structure_3_0Package.STRUCTURE__CONNECTOR:
			return getConnector();
		case Structure_3_0Package.STRUCTURE__LINK:
			return getLink();
		case Structure_3_0Package.STRUCTURE__EXT:
			return getExt();
		case Structure_3_0Package.STRUCTURE__ID:
			return getId();
		case Structure_3_0Package.STRUCTURE__NAME:
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
		case Structure_3_0Package.STRUCTURE__COMPONENT:
			getComponent().clear();
			getComponent().addAll((Collection<? extends Component>) newValue);
			return;
		case Structure_3_0Package.STRUCTURE__CONNECTOR:
			getConnector().clear();
			getConnector().addAll((Collection<? extends Connector>) newValue);
			return;
		case Structure_3_0Package.STRUCTURE__LINK:
			getLink().clear();
			getLink().addAll((Collection<? extends Link>) newValue);
			return;
		case Structure_3_0Package.STRUCTURE__EXT:
			getExt().clear();
			getExt().addAll((Collection<? extends Extension>) newValue);
			return;
		case Structure_3_0Package.STRUCTURE__ID:
			setId((String) newValue);
			return;
		case Structure_3_0Package.STRUCTURE__NAME:
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
		case Structure_3_0Package.STRUCTURE__COMPONENT:
			getComponent().clear();
			return;
		case Structure_3_0Package.STRUCTURE__CONNECTOR:
			getConnector().clear();
			return;
		case Structure_3_0Package.STRUCTURE__LINK:
			getLink().clear();
			return;
		case Structure_3_0Package.STRUCTURE__EXT:
			getExt().clear();
			return;
		case Structure_3_0Package.STRUCTURE__ID:
			setId(ID_EDEFAULT);
			return;
		case Structure_3_0Package.STRUCTURE__NAME:
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
		case Structure_3_0Package.STRUCTURE__COMPONENT:
			return component != null && !component.isEmpty();
		case Structure_3_0Package.STRUCTURE__CONNECTOR:
			return connector != null && !connector.isEmpty();
		case Structure_3_0Package.STRUCTURE__LINK:
			return link != null && !link.isEmpty();
		case Structure_3_0Package.STRUCTURE__EXT:
			return ext != null && !ext.isEmpty();
		case Structure_3_0Package.STRUCTURE__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case Structure_3_0Package.STRUCTURE__NAME:
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

} //StructureImpl
