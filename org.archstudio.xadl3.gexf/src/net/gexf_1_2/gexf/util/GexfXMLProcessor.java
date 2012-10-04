/**
 */
package net.gexf_1_2.gexf.util;

import java.util.Map;

import net.gexf_1_2.gexf.GexfPackage;

import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.resource.Resource;

import org.eclipse.emf.ecore.xmi.util.XMLProcessor;

/**
 * This class contains helper methods to serialize and deserialize XML documents
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class GexfXMLProcessor extends XMLProcessor
{

  /**
   * Public constructor to instantiate the helper.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GexfXMLProcessor()
  {
    super((EPackage.Registry.INSTANCE));
    GexfPackage.eINSTANCE.eClass();
  }
  
  /**
   * Register for "*" and "xml" file extensions the GexfResourceFactoryImpl factory.
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
      registrations.put(XML_EXTENSION, new GexfResourceFactoryImpl());
      registrations.put(STAR_EXTENSION, new GexfResourceFactoryImpl());
    }
    return registrations;
  }

} //GexfXMLProcessor
