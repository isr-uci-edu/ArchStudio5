/**
 */
package net.gexf_1_2.viz;

import java.math.BigInteger;

import net.gexf_1_2.gexf.SpellsContent;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Color Content</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.gexf_1_2.viz.ColorContent#getSpells <em>Spells</em>}</li>
 * <li>{@link net.gexf_1_2.viz.ColorContent#getA <em>A</em>}</li>
 * <li>{@link net.gexf_1_2.viz.ColorContent#getB <em>B</em>}</li>
 * <li>{@link net.gexf_1_2.viz.ColorContent#getEnd <em>End</em>}</li>
 * <li>{@link net.gexf_1_2.viz.ColorContent#getEndopen <em>Endopen</em>}</li>
 * <li>{@link net.gexf_1_2.viz.ColorContent#getG <em>G</em>}</li>
 * <li>{@link net.gexf_1_2.viz.ColorContent#getR <em>R</em>}</li>
 * <li>{@link net.gexf_1_2.viz.ColorContent#getStart <em>Start</em>}</li>
 * <li>{@link net.gexf_1_2.viz.ColorContent#getStartopen <em>Startopen</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.gexf_1_2.viz.VizPackage#getColorContent()
 * @model extendedMetaData="name='color-content' kind='elementOnly'"
 * @generated
 */
public interface ColorContent extends EObject {
	/**
	 * Returns the value of the '<em><b>Spells</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spells</em>' containment reference isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Spells</em>' containment reference.
	 * @see #setSpells(SpellsContent)
	 * @see net.gexf_1_2.viz.VizPackage#getColorContent_Spells()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='spells' namespace='##targetNamespace'"
	 * @generated
	 */
	SpellsContent getSpells();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.viz.ColorContent#getSpells
	 * <em>Spells</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Spells</em>' containment reference.
	 * @see #getSpells()
	 * @generated
	 */
	void setSpells(SpellsContent value);

	/**
	 * Returns the value of the '<em><b>A</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>A</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>A</em>' attribute.
	 * @see #isSetA()
	 * @see #unsetA()
	 * @see #setA(float)
	 * @see net.gexf_1_2.viz.VizPackage#getColorContent_A()
	 * @model unsettable="true" dataType="net.gexf_1_2.viz.AlphaChannel"
	 *        extendedMetaData="kind='attribute' name='a'"
	 * @generated
	 */
	float getA();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.viz.ColorContent#getA
	 * <em>A</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>A</em>' attribute.
	 * @see #isSetA()
	 * @see #unsetA()
	 * @see #getA()
	 * @generated
	 */
	void setA(float value);

	/**
	 * Unsets the value of the '{@link net.gexf_1_2.viz.ColorContent#getA
	 * <em>A</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetA()
	 * @see #getA()
	 * @see #setA(float)
	 * @generated
	 */
	void unsetA();

	/**
	 * Returns whether the value of the '
	 * {@link net.gexf_1_2.viz.ColorContent#getA <em>A</em>}' attribute is set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>A</em>' attribute is set.
	 * @see #unsetA()
	 * @see #getA()
	 * @see #setA(float)
	 * @generated
	 */
	boolean isSetA();

	/**
	 * Returns the value of the '<em><b>B</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>B</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>B</em>' attribute.
	 * @see #setB(BigInteger)
	 * @see net.gexf_1_2.viz.VizPackage#getColorContent_B()
	 * @model dataType="net.gexf_1_2.viz.ColorChannel" required="true"
	 *        extendedMetaData="kind='attribute' name='b'"
	 * @generated
	 */
	BigInteger getB();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.viz.ColorContent#getB
	 * <em>B</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>B</em>' attribute.
	 * @see #getB()
	 * @generated
	 */
	void setB(BigInteger value);

	/**
	 * Returns the value of the '<em><b>End</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>End</em>' attribute.
	 * @see #setEnd(Object)
	 * @see net.gexf_1_2.viz.VizPackage#getColorContent_End()
	 * @model dataType="net.gexf_1_2.gexf.TimeType"
	 *        extendedMetaData="kind='attribute' name='end'"
	 * @generated
	 */
	Object getEnd();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.viz.ColorContent#getEnd
	 * <em>End</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>End</em>' attribute.
	 * @see #getEnd()
	 * @generated
	 */
	void setEnd(Object value);

	/**
	 * Returns the value of the '<em><b>Endopen</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Endopen</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Endopen</em>' attribute.
	 * @see #setEndopen(Object)
	 * @see net.gexf_1_2.viz.VizPackage#getColorContent_Endopen()
	 * @model dataType="net.gexf_1_2.gexf.TimeType"
	 *        extendedMetaData="kind='attribute' name='endopen'"
	 * @generated
	 */
	Object getEndopen();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.viz.ColorContent#getEndopen
	 * <em>Endopen</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Endopen</em>' attribute.
	 * @see #getEndopen()
	 * @generated
	 */
	void setEndopen(Object value);

	/**
	 * Returns the value of the '<em><b>G</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>G</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>G</em>' attribute.
	 * @see #setG(BigInteger)
	 * @see net.gexf_1_2.viz.VizPackage#getColorContent_G()
	 * @model dataType="net.gexf_1_2.viz.ColorChannel" required="true"
	 *        extendedMetaData="kind='attribute' name='g'"
	 * @generated
	 */
	BigInteger getG();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.viz.ColorContent#getG
	 * <em>G</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>G</em>' attribute.
	 * @see #getG()
	 * @generated
	 */
	void setG(BigInteger value);

	/**
	 * Returns the value of the '<em><b>R</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>R</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>R</em>' attribute.
	 * @see #setR(BigInteger)
	 * @see net.gexf_1_2.viz.VizPackage#getColorContent_R()
	 * @model dataType="net.gexf_1_2.viz.ColorChannel" required="true"
	 *        extendedMetaData="kind='attribute' name='r'"
	 * @generated
	 */
	BigInteger getR();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.viz.ColorContent#getR
	 * <em>R</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>R</em>' attribute.
	 * @see #getR()
	 * @generated
	 */
	void setR(BigInteger value);

	/**
	 * Returns the value of the '<em><b>Start</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Start</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Start</em>' attribute.
	 * @see #setStart(Object)
	 * @see net.gexf_1_2.viz.VizPackage#getColorContent_Start()
	 * @model dataType="net.gexf_1_2.gexf.TimeType"
	 *        extendedMetaData="kind='attribute' name='start'"
	 * @generated
	 */
	Object getStart();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.viz.ColorContent#getStart
	 * <em>Start</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Start</em>' attribute.
	 * @see #getStart()
	 * @generated
	 */
	void setStart(Object value);

	/**
	 * Returns the value of the '<em><b>Startopen</b></em>' attribute. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Startopen</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Startopen</em>' attribute.
	 * @see #setStartopen(Object)
	 * @see net.gexf_1_2.viz.VizPackage#getColorContent_Startopen()
	 * @model dataType="net.gexf_1_2.gexf.TimeType"
	 *        extendedMetaData="kind='attribute' name='startopen'"
	 * @generated
	 */
	Object getStartopen();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.viz.ColorContent#getStartopen
	 * <em>Startopen</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Startopen</em>' attribute.
	 * @see #getStartopen()
	 * @generated
	 */
	void setStartopen(Object value);

} // ColorContent
