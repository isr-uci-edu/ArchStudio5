/**
 */
package net.gexf_1_2.gexf.impl;

import java.util.Collection;

import net.gexf_1_2.gexf.AttributeContent;
import net.gexf_1_2.gexf.AttrtypeType;
import net.gexf_1_2.gexf.GexfPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Attribute Content</b></em>'. <!-- end-user-doc
 * -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributeContentImpl#getGroup <em>Group</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributeContentImpl#getDefault <em>Default</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributeContentImpl#getOptions <em>Options</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributeContentImpl#getId <em>Id</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributeContentImpl#getTitle <em>Title</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.AttributeContentImpl#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class AttributeContentImpl extends MinimalEObjectImpl.Container implements AttributeContent {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final Object ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected Object id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getTitle() <em>Title</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected static final String TITLE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTitle() <em>Title</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getTitle()
	 * @generated
	 * @ordered
	 */
	protected String title = TITLE_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final AttrtypeType TYPE_EDEFAULT = AttrtypeType.INTEGER;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected AttrtypeType type = TYPE_EDEFAULT;

	/**
	 * This is true if the Type attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean typeESet;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected AttributeContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GexfPackage.Literals.ATTRIBUTE_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, GexfPackage.ATTRIBUTE_CONTENT__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<String> getDefault() {
		return getGroup().list(GexfPackage.Literals.ATTRIBUTE_CONTENT__DEFAULT);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<String> getOptions() {
		return getGroup().list(GexfPackage.Literals.ATTRIBUTE_CONTENT__OPTIONS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setId(Object newId) {
		Object oldId = id;
		id = newId;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTRIBUTE_CONTENT__ID, oldId, id));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setTitle(String newTitle) {
		String oldTitle = title;
		title = newTitle;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTRIBUTE_CONTENT__TITLE, oldTitle, title));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public AttrtypeType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setType(AttrtypeType newType) {
		AttrtypeType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		boolean oldTypeESet = typeESet;
		typeESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.ATTRIBUTE_CONTENT__TYPE, oldType, type,
					!oldTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetType() {
		AttrtypeType oldType = type;
		boolean oldTypeESet = typeESet;
		type = TYPE_EDEFAULT;
		typeESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, GexfPackage.ATTRIBUTE_CONTENT__TYPE, oldType,
					TYPE_EDEFAULT, oldTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetType() {
		return typeESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GexfPackage.ATTRIBUTE_CONTENT__GROUP:
			return ((InternalEList<?>) getGroup()).basicRemove(otherEnd, msgs);
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
		case GexfPackage.ATTRIBUTE_CONTENT__GROUP:
			if (coreType) {
				return getGroup();
			}
			return ((FeatureMap.Internal) getGroup()).getWrapper();
		case GexfPackage.ATTRIBUTE_CONTENT__DEFAULT:
			return getDefault();
		case GexfPackage.ATTRIBUTE_CONTENT__OPTIONS:
			return getOptions();
		case GexfPackage.ATTRIBUTE_CONTENT__ID:
			return getId();
		case GexfPackage.ATTRIBUTE_CONTENT__TITLE:
			return getTitle();
		case GexfPackage.ATTRIBUTE_CONTENT__TYPE:
			return getType();
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
		case GexfPackage.ATTRIBUTE_CONTENT__GROUP:
			((FeatureMap.Internal) getGroup()).set(newValue);
			return;
		case GexfPackage.ATTRIBUTE_CONTENT__DEFAULT:
			getDefault().clear();
			getDefault().addAll((Collection<? extends String>) newValue);
			return;
		case GexfPackage.ATTRIBUTE_CONTENT__OPTIONS:
			getOptions().clear();
			getOptions().addAll((Collection<? extends String>) newValue);
			return;
		case GexfPackage.ATTRIBUTE_CONTENT__ID:
			setId(newValue);
			return;
		case GexfPackage.ATTRIBUTE_CONTENT__TITLE:
			setTitle((String) newValue);
			return;
		case GexfPackage.ATTRIBUTE_CONTENT__TYPE:
			setType((AttrtypeType) newValue);
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
		case GexfPackage.ATTRIBUTE_CONTENT__GROUP:
			getGroup().clear();
			return;
		case GexfPackage.ATTRIBUTE_CONTENT__DEFAULT:
			getDefault().clear();
			return;
		case GexfPackage.ATTRIBUTE_CONTENT__OPTIONS:
			getOptions().clear();
			return;
		case GexfPackage.ATTRIBUTE_CONTENT__ID:
			setId(ID_EDEFAULT);
			return;
		case GexfPackage.ATTRIBUTE_CONTENT__TITLE:
			setTitle(TITLE_EDEFAULT);
			return;
		case GexfPackage.ATTRIBUTE_CONTENT__TYPE:
			unsetType();
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
		case GexfPackage.ATTRIBUTE_CONTENT__GROUP:
			return group != null && !group.isEmpty();
		case GexfPackage.ATTRIBUTE_CONTENT__DEFAULT:
			return !getDefault().isEmpty();
		case GexfPackage.ATTRIBUTE_CONTENT__OPTIONS:
			return !getOptions().isEmpty();
		case GexfPackage.ATTRIBUTE_CONTENT__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case GexfPackage.ATTRIBUTE_CONTENT__TITLE:
			return TITLE_EDEFAULT == null ? title != null : !TITLE_EDEFAULT.equals(title);
		case GexfPackage.ATTRIBUTE_CONTENT__TYPE:
			return isSetType();
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
		result.append(" (group: ");
		result.append(group);
		result.append(", id: ");
		result.append(id);
		result.append(", title: ");
		result.append(title);
		result.append(", type: ");
		if (typeESet) {
			result.append(type);
		}
		else {
			result.append("<unset>");
		}
		result.append(')');
		return result.toString();
	}

} //AttributeContentImpl
