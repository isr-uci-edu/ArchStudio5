/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.archstudio.xadl3.xadlcore_3_0;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				This is an abstract base type for all extensions that
 * 				add content to an element.  In general, individual
 * 				types will create their own abstract Extension base
 * 				type (e.g., a type Foo will be accompanied by a
 * 				FooExtension base type that extends Extension)
 * 				and the type will include a list of zero or more
 * 				FooExtensions.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.archstudio.xadl3.xadlcore_3_0.Extension#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getExtension()
 * @model abstract="true"
 *        extendedMetaData="name='Extension' kind='empty'"
 * @generated
 */
public interface Extension extends EObject
{
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
   * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getExtension_Id()
   * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID" required="true"
   *        extendedMetaData="kind='attribute' name='id' namespace='##targetNamespace'"
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.xadlcore_3_0.Extension#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

} // Extension
