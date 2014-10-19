/**
 */
package org.archstudio.xadl3.archlight_3_0.impl;

import org.archstudio.xadl3.archlight_3_0.Archlight;
import org.archstudio.xadl3.archlight_3_0.Archlight_3_0Package;
import org.archstudio.xadl3.archlight_3_0.DocumentRoot;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Document Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.archlight_3_0.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 * <li>{@link org.archstudio.xadl3.archlight_3_0.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 * <li>{@link org.archstudio.xadl3.archlight_3_0.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location
 * </em>}</li>
 * <li>{@link org.archstudio.xadl3.archlight_3_0.impl.DocumentRootImpl#getArchlight <em>Archlight</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends MinimalEObjectImpl.Container implements DocumentRoot {
	/**
	 * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getMixed()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap mixed;

	/**
	 * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @see #getXMLNSPrefixMap()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xMLNSPrefixMap;

	/**
	 * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getXSISchemaLocation()
	 * @generated
	 * @ordered
	 */
	protected EMap<String, String> xSISchemaLocation;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected DocumentRootImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Archlight_3_0Package.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, Archlight_3_0Package.DOCUMENT_ROOT__MIXED);
		}
		return mixed;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMap<String, String> getXMLNSPrefixMap() {
		if (xMLNSPrefixMap == null) {
			xMLNSPrefixMap = new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY,
					EStringToStringMapEntryImpl.class, this, Archlight_3_0Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
		}
		return xMLNSPrefixMap;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EMap<String, String> getXSISchemaLocation() {
		if (xSISchemaLocation == null) {
			xSISchemaLocation = new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY,
					EStringToStringMapEntryImpl.class, this, Archlight_3_0Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Archlight getArchlight() {
		return (Archlight) getMixed().get(Archlight_3_0Package.Literals.DOCUMENT_ROOT__ARCHLIGHT, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetArchlight(Archlight newArchlight, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(Archlight_3_0Package.Literals.DOCUMENT_ROOT__ARCHLIGHT,
				newArchlight, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setArchlight(Archlight newArchlight) {
		((FeatureMap.Internal) getMixed()).set(Archlight_3_0Package.Literals.DOCUMENT_ROOT__ARCHLIGHT, newArchlight);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Archlight_3_0Package.DOCUMENT_ROOT__MIXED:
			return ((InternalEList<?>) getMixed()).basicRemove(otherEnd, msgs);
		case Archlight_3_0Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			return ((InternalEList<?>) getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
		case Archlight_3_0Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			return ((InternalEList<?>) getXSISchemaLocation()).basicRemove(otherEnd, msgs);
		case Archlight_3_0Package.DOCUMENT_ROOT__ARCHLIGHT:
			return basicSetArchlight(null, msgs);
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
		case Archlight_3_0Package.DOCUMENT_ROOT__MIXED:
			if (coreType) {
				return getMixed();
			}
			return ((FeatureMap.Internal) getMixed()).getWrapper();
		case Archlight_3_0Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			if (coreType) {
				return getXMLNSPrefixMap();
			}
			else {
				return getXMLNSPrefixMap().map();
			}
		case Archlight_3_0Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			if (coreType) {
				return getXSISchemaLocation();
			}
			else {
				return getXSISchemaLocation().map();
			}
		case Archlight_3_0Package.DOCUMENT_ROOT__ARCHLIGHT:
			return getArchlight();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case Archlight_3_0Package.DOCUMENT_ROOT__MIXED:
			((FeatureMap.Internal) getMixed()).set(newValue);
			return;
		case Archlight_3_0Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			((EStructuralFeature.Setting) getXMLNSPrefixMap()).set(newValue);
			return;
		case Archlight_3_0Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			((EStructuralFeature.Setting) getXSISchemaLocation()).set(newValue);
			return;
		case Archlight_3_0Package.DOCUMENT_ROOT__ARCHLIGHT:
			setArchlight((Archlight) newValue);
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
		case Archlight_3_0Package.DOCUMENT_ROOT__MIXED:
			getMixed().clear();
			return;
		case Archlight_3_0Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			getXMLNSPrefixMap().clear();
			return;
		case Archlight_3_0Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			getXSISchemaLocation().clear();
			return;
		case Archlight_3_0Package.DOCUMENT_ROOT__ARCHLIGHT:
			setArchlight((Archlight) null);
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
		case Archlight_3_0Package.DOCUMENT_ROOT__MIXED:
			return mixed != null && !mixed.isEmpty();
		case Archlight_3_0Package.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
		case Archlight_3_0Package.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
		case Archlight_3_0Package.DOCUMENT_ROOT__ARCHLIGHT:
			return getArchlight() != null;
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
		result.append(" (mixed: ");
		result.append(mixed);
		result.append(')');
		return result.toString();
	}

} //DocumentRootImpl
