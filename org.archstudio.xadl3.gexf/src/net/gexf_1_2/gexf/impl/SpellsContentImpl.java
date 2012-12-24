/**
 */
package net.gexf_1_2.gexf.impl;

import java.util.Collection;

import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.SpellType;
import net.gexf_1_2.gexf.SpellsContent;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Spells Content</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.SpellsContentImpl#getSpell <em>Spell</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class SpellsContentImpl extends EObjectImpl implements SpellsContent {
	/**
	 * The cached value of the '{@link #getSpell() <em>Spell</em>}' containment
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSpell()
	 * @generated
	 * @ordered
	 */
	protected EList<SpellType> spell;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected SpellsContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	protected EClass eStaticClass() {
		return GexfPackage.Literals.SPELLS_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<SpellType> getSpell() {
		if (spell == null) {
			spell = new EObjectContainmentEList<SpellType>(SpellType.class, this, GexfPackage.SPELLS_CONTENT__SPELL);
		}
		return spell;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GexfPackage.SPELLS_CONTENT__SPELL:
			return ((InternalEList<?>) getSpell()).basicRemove(otherEnd, msgs);
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
		case GexfPackage.SPELLS_CONTENT__SPELL:
			return getSpell();
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
		case GexfPackage.SPELLS_CONTENT__SPELL:
			getSpell().clear();
			getSpell().addAll((Collection<? extends SpellType>) newValue);
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
		case GexfPackage.SPELLS_CONTENT__SPELL:
			getSpell().clear();
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
		case GexfPackage.SPELLS_CONTENT__SPELL:
			return spell != null && !spell.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SpellsContentImpl
