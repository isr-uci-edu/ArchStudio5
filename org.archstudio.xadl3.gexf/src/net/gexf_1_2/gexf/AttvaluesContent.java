/**
 */
package net.gexf_1_2.gexf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Attvalues Content</b></em>'. <!-- end-user-doc
 * -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.AttvaluesContent#getAttvalue <em>Attvalue</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.gexf_1_2.gexf.GexfPackage#getAttvaluesContent()
 * @model extendedMetaData="name='attvalues-content' kind='elementOnly'"
 * @generated
 */
public interface AttvaluesContent extends EObject {
	/**
	 * Returns the value of the '<em><b>Attvalue</b></em>' containment reference list. The list contents are of type
	 * {@link net.gexf_1_2.gexf.AttvalueType}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attvalue</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attvalue</em>' containment reference list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getAttvaluesContent_Attvalue()
	 * @model containment="true" extendedMetaData="kind='element' name='attvalue' namespace='##targetNamespace'"
	 * @generated
	 */
	EList<AttvalueType> getAttvalue();

} // AttvaluesContent
