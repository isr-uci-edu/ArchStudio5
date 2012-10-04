/**
 */
package org.archstudio.xadl3.domain_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Domain Extension</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * 
 * 				This type is an extension that can be added
 * 				to elements to give them a domain.
 * 			
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.archstudio.xadl3.domain_3_0.DomainExtension#getDomain <em>Domain</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.archstudio.xadl3.domain_3_0.Domain_3_0Package#getDomainExtension()
 * @model extendedMetaData="name='DomainExtension' kind='elementOnly'"
 * @generated
 */
public interface DomainExtension extends Extension
{
  /**
   * Returns the value of the '<em><b>Domain</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Domain</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Domain</em>' containment reference.
   * @see #setDomain(Domain)
   * @see org.archstudio.xadl3.domain_3_0.Domain_3_0Package#getDomainExtension_Domain()
   * @model containment="true" required="true"
   *        extendedMetaData="kind='element' name='domain' namespace='##targetNamespace'"
   * @generated
   */
  Domain getDomain();

  /**
   * Sets the value of the '{@link org.archstudio.xadl3.domain_3_0.DomainExtension#getDomain <em>Domain</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Domain</em>' containment reference.
   * @see #getDomain()
   * @generated
   */
  void setDomain(Domain value);

} // DomainExtension
