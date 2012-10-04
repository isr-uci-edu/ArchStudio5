/**
 */
package org.archstudio.xadl3.javaimplementation_3_0;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package
 * @generated
 */
public interface Javaimplementation_3_0Factory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Javaimplementation_3_0Factory eINSTANCE = org.archstudio.xadl3.javaimplementation_3_0.impl.Javaimplementation_3_0FactoryImpl.init();

  /**
   * Returns a new object of class '<em>Class Path Entry</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Class Path Entry</em>'.
   * @generated
   */
  ClassPathEntry createClassPathEntry();

  /**
   * Returns a new object of class '<em>Java Class</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Java Class</em>'.
   * @generated
   */
  JavaClass createJavaClass();

  /**
   * Returns a new object of class '<em>Java Implementation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Java Implementation</em>'.
   * @generated
   */
  JavaImplementation createJavaImplementation();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  Javaimplementation_3_0Package getJavaimplementation_3_0Package();

} //Javaimplementation_3_0Factory
