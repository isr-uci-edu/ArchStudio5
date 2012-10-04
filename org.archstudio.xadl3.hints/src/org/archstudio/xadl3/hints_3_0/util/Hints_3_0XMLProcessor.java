/**
 */
package org.archstudio.xadl3.hints_3_0.util;

import java.util.Map;

import org.archstudio.xadl3.hints_3_0.Hints_3_0Package;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class Hints_3_0XMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Hints_3_0XMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    Hints_3_0Package.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the Hints_3_0ResourceFactoryImpl factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected Map<String, Resource.Factory> getRegistrations()
  {
    if (registrations == null)
    {
      super.getRegistrations();
      registrations.put(XML_EXTENSION, new Hints_3_0ResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new Hints_3_0ResourceFactoryImpl());
    }
    return registrations;
  }

} //Hints_3_0XMLProcessor
