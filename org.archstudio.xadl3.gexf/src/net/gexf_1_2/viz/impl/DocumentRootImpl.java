/**
 */
package net.gexf_1_2.viz.impl;

import net.gexf_1_2.gexf.SpellsContent;
import net.gexf_1_2.viz.DocumentRoot;
import net.gexf_1_2.viz.VizPackage;

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
 * <li>{@link net.gexf_1_2.viz.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 * <li>{@link net.gexf_1_2.viz.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 * <li>{@link net.gexf_1_2.viz.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 * <li>{@link net.gexf_1_2.viz.impl.DocumentRootImpl#getSpells <em>Spells</em>}</li>
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
		return VizPackage.Literals.DOCUMENT_ROOT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FeatureMap getMixed() {
		if (mixed == null) {
			mixed = new BasicFeatureMap(this, VizPackage.DOCUMENT_ROOT__MIXED);
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
					EStringToStringMapEntryImpl.class, this, VizPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
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
					EStringToStringMapEntryImpl.class, this, VizPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
		}
		return xSISchemaLocation;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public SpellsContent getSpells() {
		return (SpellsContent) getMixed().get(VizPackage.Literals.DOCUMENT_ROOT__SPELLS, true);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetSpells(SpellsContent newSpells, NotificationChain msgs) {
		return ((FeatureMap.Internal) getMixed()).basicAdd(VizPackage.Literals.DOCUMENT_ROOT__SPELLS, newSpells, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setSpells(SpellsContent newSpells) {
		((FeatureMap.Internal) getMixed()).set(VizPackage.Literals.DOCUMENT_ROOT__SPELLS, newSpells);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case VizPackage.DOCUMENT_ROOT__MIXED:
			return ((InternalEList<?>) getMixed()).basicRemove(otherEnd, msgs);
		case VizPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			return ((InternalEList<?>) getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
		case VizPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			return ((InternalEList<?>) getXSISchemaLocation()).basicRemove(otherEnd, msgs);
		case VizPackage.DOCUMENT_ROOT__SPELLS:
			return basicSetSpells(null, msgs);
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
		case VizPackage.DOCUMENT_ROOT__MIXED:
			if (coreType) {
				return getMixed();
			}
			return ((FeatureMap.Internal) getMixed()).getWrapper();
		case VizPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			if (coreType) {
				return getXMLNSPrefixMap();
			}
			else {
				return getXMLNSPrefixMap().map();
			}
		case VizPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			if (coreType) {
				return getXSISchemaLocation();
			}
			else {
				return getXSISchemaLocation().map();
			}
		case VizPackage.DOCUMENT_ROOT__SPELLS:
			return getSpells();
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
		case VizPackage.DOCUMENT_ROOT__MIXED:
			((FeatureMap.Internal) getMixed()).set(newValue);
			return;
		case VizPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			((EStructuralFeature.Setting) getXMLNSPrefixMap()).set(newValue);
			return;
		case VizPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			((EStructuralFeature.Setting) getXSISchemaLocation()).set(newValue);
			return;
		case VizPackage.DOCUMENT_ROOT__SPELLS:
			setSpells((SpellsContent) newValue);
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
		case VizPackage.DOCUMENT_ROOT__MIXED:
			getMixed().clear();
			return;
		case VizPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			getXMLNSPrefixMap().clear();
			return;
		case VizPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			getXSISchemaLocation().clear();
			return;
		case VizPackage.DOCUMENT_ROOT__SPELLS:
			setSpells((SpellsContent) null);
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
		case VizPackage.DOCUMENT_ROOT__MIXED:
			return mixed != null && !mixed.isEmpty();
		case VizPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
			return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
		case VizPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
			return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
		case VizPackage.DOCUMENT_ROOT__SPELLS:
			return getSpells() != null;
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
