/**
 */
package org.archstudio.xadl3.myxgen_3_0;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.archstudio.xadl3.myxgen_3_0.Myxgen_3_0Package
 * @generated
 */
public interface Myxgen_3_0Factory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Myxgen_3_0Factory eINSTANCE = org.archstudio.xadl3.myxgen_3_0.impl.Myxgen_3_0FactoryImpl.init();

  /**
   * Returns a new object of class '<em>Myx Gen</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Myx Gen</em>'.
   * @generated
   */
  MyxGen createMyxGen();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  Myxgen_3_0Package getMyxgen_3_0Package();

} //Myxgen_3_0Factory
