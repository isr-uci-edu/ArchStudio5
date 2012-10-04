/**
 */
package org.archstudio.xadl3.structure_3_0;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Brick</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				Brick is the abstract base type for components
 * 				and connectors.  A brick is a logical element
 * 				that has an ID, a human-readable name, and
 * 				zero or more interfaces.  Additionally, a brick
 * 				may have internal substructure.  This type is
 * 				defined so that, on a basic level, components
 * 				and connectors can be treated equally.
 * 				However, no Brick extension is defined,
 * 				since we assume that there will be very
 * 				semantically different extensions for
 * 				components, connectors, and future types of
 * 				bricks.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.archstudio.xadl3.structure_3_0.Brick#getInterface <em>Interface</em>}</li>
 *   <li>{@link org.archstudio.xadl3.structure_3_0.Brick#getSubStructure <em>Sub Structure</em>}</li>
 *   <li>{@link org.archstudio.xadl3.structure_3_0.Brick#getId <em>Id</em>}</li>
 *   <li>{@link org.archstudio.xadl3.structure_3_0.Brick#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getBrick()
 * @model abstract="true"
 *        extendedMetaData="name='Brick' kind='elementOnly'"
 * @generated
 */
public interface Brick extends EObject
{
  /**
   * Returns the value of the '<em><b>Interface</b></em>' containment reference list.
   * The list contents are of type {@link org.archstudio.xadl3.structure_3_0.Interface}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Interface</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Interface</em>' containment reference list.
   * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getBrick_Interface()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='interface' namespace='##targetNamespace'"
   * @generated
   */
  EList<Interface> getInterface();

  /**
   * Returns the value of the '<em><b>Sub Structure</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Sub Structure</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Sub Structure</em>' containment reference.
   * @see #setSubStructure(SubStructure)
   * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getBrick_SubStructure()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='subStructure' namespace='##targetNamespace'"
   * @generated
   */
  SubStructure getSubStructure();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.structure_3_0.Brick#getSubStructure <em>Sub Structure</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Sub Structure</em>' containment reference.
   * @see #getSubStructure()
   * @generated
   */
  void setSubStructure(SubStructure value);

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
   * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getBrick_Id()
   * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
   *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.structure_3_0.Brick#getId <em>Id</em>}' attribute.
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
   * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getBrick_Name()
   * @model dataType="org.eclipse.emf.ecore.xml.type.String"
   *        extendedMetaData="kind='attribute' name='name' namespace='##targetNamespace'"
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.structure_3_0.Brick#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

} // Brick
