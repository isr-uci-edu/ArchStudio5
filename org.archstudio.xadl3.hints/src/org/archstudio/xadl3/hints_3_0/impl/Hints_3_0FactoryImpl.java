/**
 */
package org.archstudio.xadl3.hints_3_0.impl;

import org.archstudio.xadl3.hints_3_0.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Hints_3_0FactoryImpl extends EFactoryImpl implements Hints_3_0Factory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static Hints_3_0Factory init()
  {
    try
    {
      Hints_3_0Factory theHints_3_0Factory = (Hints_3_0Factory)EPackage.Registry.INSTANCE.getEFactory("http://www.archstudio.org/xadl3/schemas/hints-3.0.xsd"); 
      if (theHints_3_0Factory != null)
      {
        return theHints_3_0Factory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new Hints_3_0FactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Hints_3_0FactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case Hints_3_0Package.HINT: return createHint();
      case Hints_3_0Package.HINTS_EXTENSION: return createHintsExtension();
      case Hints_3_0Package.VALUE: return createValue();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Hint createHint()
  {
    HintImpl hint = new HintImpl();
    return hint;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HintsExtension createHintsExtension()
  {
    HintsExtensionImpl hintsExtension = new HintsExtensionImpl();
    return hintsExtension;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Value createValue()
  {
    ValueImpl value = new ValueImpl();
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Hints_3_0Package getHints_3_0Package()
  {
    return (Hints_3_0Package)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static Hints_3_0Package getPackage()
  {
    return Hints_3_0Package.eINSTANCE;
  }

} //Hints_3_0FactoryImpl
