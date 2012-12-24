/**
 */
package org.archstudio.xadl3.structure_3_0;

import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Link</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * 
 * A Link is an architectural construct that connects two elements. It connects
 * two interfaces.
 * 
 * Links are intended to have very limited semantics: they simply indicate an
 * association between two endpoints. If the connection needs to have deeper
 * semantics, consider interposing a connector between the endpoints, since
 * connectors are intended to be semantically-rich elements.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.structure_3_0.Link#getPoint1 <em>Point1</em>}
 * </li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.Link#getPoint2 <em>Point2</em>}
 * </li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.Link#getExt <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.Link#getId <em>Id</em>}</li>
 * <li>{@link org.archstudio.xadl3.structure_3_0.Link#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getLink()
 * @model extendedMetaData="name='Link' kind='elementOnly'"
 * @generated
 */
public interface Link extends EObject {
	/**
	 * Returns the value of the '<em><b>Point1</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Point1</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Point1</em>' reference.
	 * @see #setPoint1(Interface)
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getLink_Point1()
	 * @model resolveProxies="false" required="true" extendedMetaData=
	 *        "kind='element' name='point1' namespace='##targetNamespace'"
	 * @generated
	 */
	Interface getPoint1();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.structure_3_0.Link#getPoint1 <em>Point1</em>}
	 * ' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Point1</em>' reference.
	 * @see #getPoint1()
	 * @generated
	 */
	void setPoint1(Interface value);

	/**
	 * Returns the value of the '<em><b>Point2</b></em>' reference. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Point2</em>' reference isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Point2</em>' reference.
	 * @see #setPoint2(Interface)
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getLink_Point2()
	 * @model resolveProxies="false" required="true" extendedMetaData=
	 *        "kind='element' name='point2' namespace='##targetNamespace'"
	 * @generated
	 */
	Interface getPoint2();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.structure_3_0.Link#getPoint2 <em>Point2</em>}
	 * ' reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Point2</em>' reference.
	 * @see #getPoint2()
	 * @generated
	 */
	void setPoint2(Interface value);

	/**
	 * Returns the value of the '<em><b>Ext</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link org.archstudio.xadl3.xadlcore_3_0.Extension}. <!-- begin-user-doc
	 * -->
	 * <p>
	 * If the meaning of the '<em>Ext</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Ext</em>' containment reference list.
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getLink_Ext()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='ext' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<Extension> getExt();

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getLink_Id()
	 * @model id="true" dataType="org.eclipse.emf.ecore.xml.type.ID"
	 *        required="true" extendedMetaData=
	 *        "kind='attribute' name='id' namespace='##targetNamespace'"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.structure_3_0.Link#getId <em>Id</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see org.archstudio.xadl3.structure_3_0.Structure_3_0Package#getLink_Name()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" extendedMetaData=
	 *        "kind='attribute' name='name' namespace='##targetNamespace'"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.structure_3_0.Link#getName <em>Name</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // Link
