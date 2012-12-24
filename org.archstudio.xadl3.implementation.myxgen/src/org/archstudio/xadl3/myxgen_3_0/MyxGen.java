/**
 */
package org.archstudio.xadl3.myxgen_3_0;

import org.archstudio.xadl3.implementation_3_0.Implementation;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Myx Gen</b></em>'. <!-- end-user-doc -->
 * 
 * <!-- begin-model-doc -->
 * 
 * This type defines a reference to a MyxGen brick.
 * 
 * <!-- end-model-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link org.archstudio.xadl3.myxgen_3_0.MyxGen#getBrickID <em>Brick ID
 * </em>}</li>
 * </ul>
 * </p>
 * 
 * @see org.archstudio.xadl3.myxgen_3_0.Myxgen_3_0Package#getMyxGen()
 * @model extendedMetaData="name='MyxGen' kind='elementOnly'"
 * @generated
 */
public interface MyxGen extends Implementation {
	/**
	 * Returns the value of the '<em><b>Brick ID</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Brick ID</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Brick ID</em>' attribute.
	 * @see #setBrickID(String)
	 * @see org.archstudio.xadl3.myxgen_3_0.Myxgen_3_0Package#getMyxGen_BrickID()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.IDREF" required="true"
	 *        extendedMetaData
	 *        ="kind='element' name='brickID' namespace='##targetNamespace'"
	 * @generated
	 */
	String getBrickID();

	/**
	 * Sets the value of the '
	 * {@link org.archstudio.xadl3.myxgen_3_0.MyxGen#getBrickID
	 * <em>Brick ID</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Brick ID</em>' attribute.
	 * @see #getBrickID()
	 * @generated
	 */
	void setBrickID(String value);

} // MyxGen
