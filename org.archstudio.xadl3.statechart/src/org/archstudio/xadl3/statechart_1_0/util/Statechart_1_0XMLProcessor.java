/**
 */
package org.archstudio.xadl3.statechart_1_0.util;

import java.util.Map;

import org.archstudio.xadl3.statechart_1_0.Statechart_1_0Package;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class Statechart_1_0XMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Statechart_1_0XMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    Statechart_1_0Package.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the Statechart_1_0ResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new Statechart_1_0ResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new Statechart_1_0ResourceFactoryImpl());
    }
    return registrations;
  }

} //Statechart_1_0XMLProcessor
