/**
 */
package org.archstudio.xadl3.structure_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Structure</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				This type defines an architectural structure.
 * 				A structure consists of components, connectors,
 * 				and links.  Components and connectors internally
 * 				contain interfaces.  It is possible for one
 * 				structure to be nested within a component
 * 				or connector by reference through the use of
 * 				the subStructure element on components or
 * 				connectors.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.archstudio.xadl3.structure_3_0.Structure#getComponent <em>Component</em>}</li>
 *   <li>{@link org.archstudio.xadl3.structure_3_0.Structure#getConnector <em>Connector</em>}</li>
 *   <li>{@link org.archstudio.xadl3.structure_3_0.Structure#getLink <em>Link</em>}</li>
 *   <li>{@link org.archstudio.xadl3.structure_3_0.Structure#getExt <em>Ext</em>}</li>
 *   <li>{@link org.archstudio.xadl3.structure_3_0.Structure#getId <em>Id</em>}</li>
 *   <li>{@link org.archstudio.xadl3.structure_3_0.Structure#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getStructure()
 * @model extendedMetaData="name='Structure' kind='elementOnly'"
 * @generated
 */
public interface Structure extends EObject
{
  /**
   * Returns the value of the '<em><b>Component</b></em>' containment reference list.
   * The list contents are of type {@link org.archstudio.xadl3.structure_3_0.Component}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Component</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Component</em>' containment reference list.
   * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getStructure_Component()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='component' namespace='##targetNamespace'"
   * @generated
   */
  EList<Component> getComponent();

  /**
   * Returns the value of the '<em><b>Connector</b></em>' containment reference list.
   * The list contents are of type {@link org.archstudio.xadl3.structure_3_0.Connector}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Connector</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Connector</em>' containment reference list.
   * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getStructure_Connector()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='connector' namespace='##targetNamespace'"
   * @generated
   */
  EList<Connector> getConnector();

  /**
   * Returns the value of the '<em><b>Link</b></em>' containment reference list.
   * The list contents are of type {@link org.archstudio.xadl3.structure_3_0.Link}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Link</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Link</em>' containment reference list.
   * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getStructure_Link()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='link' namespace='##targetNamespace'"
   * @generated
   */
  EList<Link> getLink();

  /**
   * Returns the value of the '<em><b>Ext</b></em>' containment reference list.
   * The list contents are of type {@link org.archstudio.xadl3.xadlcore_3_0.Extension}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ext</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ext</em>' containment reference list.
   * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getStructure_Ext()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='ext' namespace='##targetNamespace'"
   * @generated
   */
  EList<Extension> getExt();

  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getStructure_Id()
   * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
   *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.structure_3_0.Structure#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getStructure_Name()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   *        extendedMetaData="kind='attribute' name='name' namespace='##targetNamespace'"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.structure_3_0.Structure#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // Structure
