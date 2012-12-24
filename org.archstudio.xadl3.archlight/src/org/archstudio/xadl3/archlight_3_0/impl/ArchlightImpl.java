/**
 */
package org.archstudio.xadl3.archlight_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.archlight_3_0.Archlight;
import org.archstudio.xadl3.archlight_3_0.Archlight_3_0Package;
import org.archstudio.xadl3.archlight_3_0.Test;
import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Archlight</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.archlight_3_0.impl.ArchlightImpl#getTest <em>
 * Test</em>}</li>
 * <li>{@link org.archstudio.xadl3.archlight_3_0.impl.ArchlightImpl#getExt <em>
 * Ext</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ArchlightImpl extends EObjectImpl implements Archlight {
	/**
	 * The cached value of the '{@link #getTest() <em>Test</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTest()
	 * @generated
	 * @ordered
	 */
	protected EList<Test> test;

	/**
	 * The cached value of the '{@link #getExt() <em>Ext</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getExt()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> ext;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected ArchlightImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	protected EClass eStaticClass() {
		return Archlight_3_0Package.Literals.ARCHLIGHT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Test> getTest() {
		if (test == null) {
			test = new EObjectContainmentEList<Test>(Test.class, this, Archlight_3_0Package.ARCHLIGHT__TEST);
		}
		return test;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<Extension> getExt() {
		if (ext == null) {
			ext = new EObjectContainmentEList<Extension>(Extension.class, this, Archlight_3_0Package.ARCHLIGHT__EXT);
		}
		return ext;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Archlight_3_0Package.ARCHLIGHT__TEST:
			return ((InternalEList<?>) getTest()).basicRemove(otherEnd, msgs);
		case Archlight_3_0Package.ARCHLIGHT__EXT:
			return ((InternalEList<?>) getExt()).basicRemove(otherEnd, msgs);
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
		case Archlight_3_0Package.ARCHLIGHT__TEST:
			return getTest();
		case Archlight_3_0Package.ARCHLIGHT__EXT:
			return getExt();
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
		case Archlight_3_0Package.ARCHLIGHT__TEST:
			getTest().clear();
			getTest().addAll((Collection<? extends Test>) newValue);
			return;
		case Archlight_3_0Package.ARCHLIGHT__EXT:
			getExt().clear();
			getExt().addAll((Collection<? extends Extension>) newValue);
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
		case Archlight_3_0Package.ARCHLIGHT__TEST:
			getTest().clear();
			return;
		case Archlight_3_0Package.ARCHLIGHT__EXT:
			getExt().clear();
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
		case Archlight_3_0Package.ARCHLIGHT__TEST:
			return test != null && !test.isEmpty();
		case Archlight_3_0Package.ARCHLIGHT__EXT:
			return ext != null && !ext.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ArchlightImpl
