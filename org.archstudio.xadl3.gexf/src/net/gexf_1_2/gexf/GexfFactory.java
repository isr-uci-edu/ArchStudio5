/**
 */
package net.gexf_1_2.gexf;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see net.gexf_1_2.gexf.GexfPackage
 * @generated
 */
public interface GexfFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  GexfFactory eINSTANCE = net.gexf_1_2.gexf.impl.GexfFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Attribute Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Attribute Content</em>'.
   * @generated
   */
  AttributeContent createAttributeContent();

  /**
   * Returns a new object of class '<em>Attributes Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Attributes Content</em>'.
   * @generated
   */
  AttributesContent createAttributesContent();

  /**
   * Returns a new object of class '<em>Attvalues Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Attvalues Content</em>'.
   * @generated
   */
  AttvaluesContent createAttvaluesContent();

  /**
   * Returns a new object of class '<em>Attvalue Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Attvalue Type</em>'.
   * @generated
   */
  AttvalueType createAttvalueType();

  /**
   * Returns a new object of class '<em>Document Root</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Document Root</em>'.
   * @generated
   */
  DocumentRoot createDocumentRoot();

  /**
   * Returns a new object of class '<em>Edge Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Edge Content</em>'.
   * @generated
   */
  EdgeContent createEdgeContent();

  /**
   * Returns a new object of class '<em>Edges Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Edges Content</em>'.
   * @generated
   */
  EdgesContent createEdgesContent();

  /**
   * Returns a new object of class '<em>Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Content</em>'.
   * @generated
   */
  GexfContent createGexfContent();

  /**
   * Returns a new object of class '<em>Graph Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Graph Content</em>'.
   * @generated
   */
  GraphContent createGraphContent();

  /**
   * Returns a new object of class '<em>Meta Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Meta Content</em>'.
   * @generated
   */
  MetaContent createMetaContent();

  /**
   * Returns a new object of class '<em>Node Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Node Content</em>'.
   * @generated
   */
  NodeContent createNodeContent();

  /**
   * Returns a new object of class '<em>Nodes Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Nodes Content</em>'.
   * @generated
   */
  NodesContent createNodesContent();

  /**
   * Returns a new object of class '<em>Parents Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parents Content</em>'.
   * @generated
   */
  ParentsContent createParentsContent();

  /**
   * Returns a new object of class '<em>Parent Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Parent Type</em>'.
   * @generated
   */
  ParentType createParentType();

  /**
   * Returns a new object of class '<em>Spells Content</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Spells Content</em>'.
   * @generated
   */
  SpellsContent createSpellsContent();

  /**
   * Returns a new object of class '<em>Spell Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Spell Type</em>'.
   * @generated
   */
  SpellType createSpellType();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  GexfPackage getGexfPackage();

} //GexfFactory
