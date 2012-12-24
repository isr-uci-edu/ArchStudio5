/**
 */
package net.gexf_1_2.gexf.impl;

import java.util.Collection;

import javax.xml.datatype.XMLGregorianCalendar;

import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.MetaContent;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Meta Content</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.MetaContentImpl#getGroup <em>Group</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.MetaContentImpl#getCreator <em>Creator
 * </em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.MetaContentImpl#getKeywords <em>Keywords
 * </em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.MetaContentImpl#getDescription <em>
 * Description</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.MetaContentImpl#getLastmodifieddate <em>
 * Lastmodifieddate</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class MetaContentImpl extends EObjectImpl implements MetaContent {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getLastmodifieddate()
	 * <em>Lastmodifieddate</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLastmodifieddate()
	 * @generated
	 * @ordered
	 */
	protected static final XMLGregorianCalendar LASTMODIFIEDDATE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLastmodifieddate()
	 * <em>Lastmodifieddate</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLastmodifieddate()
	 * @generated
	 * @ordered
	 */
	protected XMLGregorianCalendar lastmodifieddate = LASTMODIFIEDDATE_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected MetaContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	protected EClass eStaticClass() {
		return GexfPackage.Literals.META_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, GexfPackage.META_CONTENT__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<String> getCreator() {
		return getGroup().list(GexfPackage.Literals.META_CONTENT__CREATOR);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<String> getKeywords() {
		return getGroup().list(GexfPackage.Literals.META_CONTENT__KEYWORDS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<String> getDescription() {
		return getGroup().list(GexfPackage.Literals.META_CONTENT__DESCRIPTION);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public XMLGregorianCalendar getLastmodifieddate() {
		return lastmodifieddate;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public void setLastmodifieddate(XMLGregorianCalendar newLastmodifieddate) {
		XMLGregorianCalendar oldLastmodifieddate = lastmodifieddate;
		lastmodifieddate = newLastmodifieddate;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.META_CONTENT__LASTMODIFIEDDATE,
					oldLastmodifieddate, lastmodifieddate));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GexfPackage.META_CONTENT__GROUP:
			return ((InternalEList<?>) getGroup()).basicRemove(otherEnd, msgs);
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
		case GexfPackage.META_CONTENT__GROUP:
			if (coreType) {
				return getGroup();
			}
			return ((FeatureMap.Internal) getGroup()).getWrapper();
		case GexfPackage.META_CONTENT__CREATOR:
			return getCreator();
		case GexfPackage.META_CONTENT__KEYWORDS:
			return getKeywords();
		case GexfPackage.META_CONTENT__DESCRIPTION:
			return getDescription();
		case GexfPackage.META_CONTENT__LASTMODIFIEDDATE:
			return getLastmodifieddate();
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
		case GexfPackage.META_CONTENT__GROUP:
			((FeatureMap.Internal) getGroup()).set(newValue);
			return;
		case GexfPackage.META_CONTENT__CREATOR:
			getCreator().clear();
			getCreator().addAll((Collection<? extends String>) newValue);
			return;
		case GexfPackage.META_CONTENT__KEYWORDS:
			getKeywords().clear();
			getKeywords().addAll((Collection<? extends String>) newValue);
			return;
		case GexfPackage.META_CONTENT__DESCRIPTION:
			getDescription().clear();
			getDescription().addAll((Collection<? extends String>) newValue);
			return;
		case GexfPackage.META_CONTENT__LASTMODIFIEDDATE:
			setLastmodifieddate((XMLGregorianCalendar) newValue);
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
		case GexfPackage.META_CONTENT__GROUP:
			getGroup().clear();
			return;
		case GexfPackage.META_CONTENT__CREATOR:
			getCreator().clear();
			return;
		case GexfPackage.META_CONTENT__KEYWORDS:
			getKeywords().clear();
			return;
		case GexfPackage.META_CONTENT__DESCRIPTION:
			getDescription().clear();
			return;
		case GexfPackage.META_CONTENT__LASTMODIFIEDDATE:
			setLastmodifieddate(LASTMODIFIEDDATE_EDEFAULT);
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
		case GexfPackage.META_CONTENT__GROUP:
			return group != null && !group.isEmpty();
		case GexfPackage.META_CONTENT__CREATOR:
			return !getCreator().isEmpty();
		case GexfPackage.META_CONTENT__KEYWORDS:
			return !getKeywords().isEmpty();
		case GexfPackage.META_CONTENT__DESCRIPTION:
			return !getDescription().isEmpty();
		case GexfPackage.META_CONTENT__LASTMODIFIEDDATE:
			return LASTMODIFIEDDATE_EDEFAULT == null ? lastmodifieddate != null : !LASTMODIFIEDDATE_EDEFAULT
					.equals(lastmodifieddate);
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
		result.append(" (group: ");
		result.append(group);
		result.append(", lastmodifieddate: ");
		result.append(lastmodifieddate);
		result.append(')');
		return result.toString();
	}

} //MetaContentImpl
