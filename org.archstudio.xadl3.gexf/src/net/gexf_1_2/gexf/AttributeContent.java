/**
 */
package net.gexf_1_2.gexf;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link net.gexf_1_2.gexf.AttributeContent#getGroup <em>Group</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.AttributeContent#getDefault <em>Default</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.AttributeContent#getOptions <em>Options</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.AttributeContent#getId <em>Id</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.AttributeContent#getTitle <em>Title</em>}</li>
 *   <li>{@link net.gexf_1_2.gexf.AttributeContent#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.gexf_1_2.gexf.GexfPackage#getAttributeContent()
 * @model extendedMetaData="name='attribute-content' kind='elementOnly'"
 * @generated
 */
public interface AttributeContent extends EObject
{
  /**
   * Returns the value of the '<em><b>Group</b></em>' attribute list.
   * The list contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Group</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Group</em>' attribute list.
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributeContent_Group()
   * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
   *        extendedMetaData="kind='group' name='group:0'"
   * @generated
   */
  FeatureMap getGroup();

  /**
   * Returns the value of the '<em><b>Default</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Default</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Default</em>' attribute list.
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributeContent_Default()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='default' namespace='##targetNamespace' group='#group:0'"
   * @generated
   */
  EList<String> getDefault();

  /**
   * Returns the value of the '<em><b>Options</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Options</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Options</em>' attribute list.
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributeContent_Options()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" transient="true" volatile="true" derived="true"
   *        extendedMetaData="kind='element' name='options' namespace='##targetNamespace' group='#group:0'"
   * @generated
   */
  EList<String> getOptions();

  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(Object)
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributeContent_Id()
   * @model dataType="net.gexf_1_2.gexf.IdType" required="true"
   *        extendedMetaData="kind='attribute' name='id'"
   * @generated
   */
  Object getId();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.AttributeContent#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(Object value);

  /**
   * Returns the value of the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Title</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Title</em>' attribute.
   * @see #setTitle(String)
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributeContent_Title()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='attribute' name='title'"
   * @generated
   */
  String getTitle();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.AttributeContent#getTitle <em>Title</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Title</em>' attribute.
   * @see #getTitle()
   * @generated
   */
  void setTitle(String value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' attribute.
   * The literals are from the enumeration {@link net.gexf_1_2.gexf.AttrtypeType}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' attribute.
   * @see net.gexf_1_2.gexf.AttrtypeType
   * @see #isSetType()
   * @see #unsetType()
   * @see #setType(AttrtypeType)
   * @see net.gexf_1_2.gexf.GexfPackage#getAttributeContent_Type()
   * @model unsettable="true" required="true"
   *        extendedMetaData="kind='attribute' name='type'"
   * @generated
   */
  AttrtypeType getType();

  /**
   * Sets the value of the '{@link net.gexf_1_2.gexf.AttributeContent#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' attribute.
   * @see net.gexf_1_2.gexf.AttrtypeType
   * @see #isSetType()
   * @see #unsetType()
   * @see #getType()
   * @generated
   */
  void setType(AttrtypeType value);

  /**
   * Unsets the value of the '{@link net.gexf_1_2.gexf.AttributeContent#getType <em>Type</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetType()
   * @see #getType()
   * @see #setType(AttrtypeType)
   * @generated
   */
  void unsetType();

  /**
   * Returns whether the value of the '{@link net.gexf_1_2.gexf.AttributeContent#getType <em>Type</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Type</em>' attribute is set.
   * @see #unsetType()
   * @see #getType()
   * @see #setType(AttrtypeType)
   * @generated
   */
  boolean isSetType();

} // AttributeContent
