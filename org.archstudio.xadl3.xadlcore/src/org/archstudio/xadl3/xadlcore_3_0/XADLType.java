/**
 */
package org.archstudio.xadl3.xadlcore_3_0;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XADL Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.archstudio.xadl3.xadlcore_3_0.XADLType#getTopLevelElement <em>Top Level Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getXADLType()
 * @model extendedMetaData="name='xADL_._type' kind='elementOnly'"
 * @generated
 */
public interface XADLType extends EObject
{
  /**
   * Returns the value of the '<em><b>Top Level Element</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * 
   * 				This is a placeholder element for xADL top-level
   * 				elements (i.e., elements that are direct children
   * 				of the document root element.  Elements that
   * 				are intended to be top-level elements should be
   * 				in the topLevelElement substitutionGroup to
   * 				declare their intent to be used as top-level
   * 				elements.
   * 			
   * <!-- end-model-doc -->
   * @return the value of the '<em>Top Level Element</em>' containment reference list.
   * @see org.archstudio.xadl3.xadlcore_3_0.Xadlcore_3_0Package#getXADLType_TopLevelElement()
   * @model containment="true"
   *        extendedMetaData="kind='element' name='topLevelElement' namespace='##targetNamespace'"
   * @generated
   */
  EList<EObject> getTopLevelElement();

} // XADLType
