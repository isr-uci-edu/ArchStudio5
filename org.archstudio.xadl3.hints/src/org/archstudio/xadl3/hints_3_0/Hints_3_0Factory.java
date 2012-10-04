/**
 */
package org.archstudio.xadl3.hints_3_0;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.archstudio.xadl3.hints_3_0.Hints_3_0Package
 * @generated
 */
public interface Hints_3_0Factory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Hints_3_0Factory eINSTANCE = org.archstudio.xadl3.hints_3_0.impl.Hints_3_0FactoryImpl.init();

  /**
   * Returns a new object of class '<em>Hint</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Hint</em>'.
   * @generated
   */
  Hint createHint();

  /**
   * Returns a new object of class '<em>Hints Extension</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Hints Extension</em>'.
   * @generated
   */
  HintsExtension createHintsExtension();

  /**
   * Returns a new object of class '<em>Value</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Value</em>'.
   * @generated
   */
  Value createValue();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  Hints_3_0Package getHints_3_0Package();

} //Hints_3_0Factory
