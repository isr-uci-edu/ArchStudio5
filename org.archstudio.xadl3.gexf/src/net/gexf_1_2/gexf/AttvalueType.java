/**
 */
package net.gexf_1_2.gexf;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Attvalue Type</b></em>'. <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.AttvalueType#getEnd <em>End</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.AttvalueType#getEndopen <em>Endopen</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.AttvalueType#getFor <em>For</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.AttvalueType#getStart <em>Start</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.AttvalueType#getStartopen <em>Startopen</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.AttvalueType#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see net.gexf_1_2.gexf.GexfPackage#getAttvalueType()
 * @model extendedMetaData="name='attvalue_._type' kind='empty'"
 * @generated
 */
public interface AttvalueType extends EObject {
	/**
	 * Returns the value of the '<em><b>End</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>End</em>' attribute.
	 * @see #setEnd(Object)
	 * @see net.gexf_1_2.gexf.GexfPackage#getAttvalueType_End()
	 * @model dataType="net.gexf_1_2.gexf.TimeType" extendedMetaData="kind='attribute' name='end'"
	 * @generated
	 */
	Object getEnd();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.AttvalueType#getEnd <em>End</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>End</em>' attribute.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(Object value);

	/**
	 * Returns the value of the '<em><b>Endopen</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Endopen</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Endopen</em>' attribute.
	 * @see #setEndopen(Object)
	 * @see net.gexf_1_2.gexf.GexfPackage#getAttvalueType_Endopen()
	 * @model dataType="net.gexf_1_2.gexf.TimeType" extendedMetaData="kind='attribute' name='endopen'"
	 * @generated
	 */
	Object getEndopen();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.AttvalueType#getEndopen <em>Endopen</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Endopen</em>' attribute.
	 * @see #getEndopen()
	 * @generated
	 */
	void setEndopen(Object value);

	/**
	 * Returns the value of the '<em><b>For</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>For</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>For</em>' attribute.
	 * @see #setFor(Object)
	 * @see net.gexf_1_2.gexf.GexfPackage#getAttvalueType_For()
	 * @model dataType="net.gexf_1_2.gexf.IdType" required="true" extendedMetaData="kind='attribute' name='for'"
	 * @generated
	 */
	Object getFor();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.AttvalueType#getFor <em>For</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>For</em>' attribute.
	 * @see #getFor()
	 * @generated
	 */
	void setFor(Object value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(Object)
	 * @see net.gexf_1_2.gexf.GexfPackage#getAttvalueType_Start()
	 * @model dataType="net.gexf_1_2.gexf.TimeType" extendedMetaData="kind='attribute' name='start'"
	 * @generated
	 */
	Object getStart();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.AttvalueType#getStart <em>Start</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(Object value);

	/**
	 * Returns the value of the '<em><b>Startopen</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Startopen</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Startopen</em>' attribute.
	 * @see #setStartopen(Object)
	 * @see net.gexf_1_2.gexf.GexfPackage#getAttvalueType_Startopen()
	 * @model dataType="net.gexf_1_2.gexf.TimeType" extendedMetaData="kind='attribute' name='startopen'"
	 * @generated
	 */
	Object getStartopen();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.AttvalueType#getStartopen <em>Startopen</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Startopen</em>' attribute.
	 * @see #getStartopen()
	 * @generated
	 */
	void setStartopen(Object value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see net.gexf_1_2.gexf.GexfPackage#getAttvalueType_Value()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
	 *        extendedMetaData="kind='attribute' name='value'"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.AttvalueType#getValue <em>Value</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // AttvalueType
