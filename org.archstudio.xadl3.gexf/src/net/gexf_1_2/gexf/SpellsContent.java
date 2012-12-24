/**
 */
package net.gexf_1_2.gexf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Spells Content</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.SpellsContent#getSpell <em>Spell</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.gexf_1_2.gexf.GexfPackage#getSpellsContent()
 * @model extendedMetaData="name='spells-content' kind='elementOnly'"
 * @generated
 */
public interface SpellsContent extends EObject {
	/**
	 * Returns the value of the '<em><b>Spell</b></em>' containment reference
	 * list. The list contents are of type {@link net.gexf_1_2.gexf.SpellType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spell</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Spell</em>' containment reference list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getSpellsContent_Spell()
	 * @model containment="true" required="true" extendedMetaData=
	 *        "kind='element' name='spell' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<SpellType> getSpell();

} // SpellsContent
