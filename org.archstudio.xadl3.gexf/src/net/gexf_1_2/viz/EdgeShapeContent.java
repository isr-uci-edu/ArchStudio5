/**
 */
package net.gexf_1_2.viz;

import net.gexf_1_2.gexf.SpellsContent;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Edge Shape Content</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.gexf_1_2.viz.EdgeShapeContent#getSpells <em>Spells</em>}</li>
 * <li>{@link net.gexf_1_2.viz.EdgeShapeContent#getEnd <em>End</em>}</li>
 * <li>{@link net.gexf_1_2.viz.EdgeShapeContent#getEndopen <em>Endopen</em>}</li>
 * <li>{@link net.gexf_1_2.viz.EdgeShapeContent#getStart <em>Start</em>}</li>
 * <li>{@link net.gexf_1_2.viz.EdgeShapeContent#getStartopen <em>Startopen</em>}
 * </li>
 * <li>{@link net.gexf_1_2.viz.EdgeShapeContent#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.gexf_1_2.viz.VizPackage#getEdgeShapeContent()
 * @model extendedMetaData="name='edge-shape-content' kind='elementOnly'"
 * @generated
 */
public interface EdgeShapeContent extends EObject {
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
	 * @see net.gexf_1_2.viz.VizPackage#getEdgeShapeContent_Spells()
	 * @model containment="true" extendedMetaData=
	 *        "kind='element' name='spells' namespace='##targetNamespace'"
	 * @generated
	 */
	SpellsContent getSpells();

	/**
	 * Sets the value of the '
	 * {@link net.gexf_1_2.viz.EdgeShapeContent#getSpells <em>Spells</em>}'
	 * containment reference. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Spells</em>' containment reference.
	 * @see #getSpells()
	 * @generated
	 */
	void setSpells(SpellsContent value);

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
	 * @see net.gexf_1_2.viz.VizPackage#getEdgeShapeContent_End()
	 * @model dataType="net.gexf_1_2.gexf.TimeType"
	 *        extendedMetaData="kind='attribute' name='end'"
	 * @generated
	 */
	Object getEnd();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.viz.EdgeShapeContent#getEnd
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
	 * @see net.gexf_1_2.viz.VizPackage#getEdgeShapeContent_Endopen()
	 * @model dataType="net.gexf_1_2.gexf.TimeType"
	 *        extendedMetaData="kind='attribute' name='endopen'"
	 * @generated
	 */
	Object getEndopen();

	/**
	 * Sets the value of the '
	 * {@link net.gexf_1_2.viz.EdgeShapeContent#getEndopen <em>Endopen</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Endopen</em>' attribute.
	 * @see #getEndopen()
	 * @generated
	 */
	void setEndopen(Object value);

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
	 * @see net.gexf_1_2.viz.VizPackage#getEdgeShapeContent_Start()
	 * @model dataType="net.gexf_1_2.gexf.TimeType"
	 *        extendedMetaData="kind='attribute' name='start'"
	 * @generated
	 */
	Object getStart();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.viz.EdgeShapeContent#getStart
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
	 * @see net.gexf_1_2.viz.VizPackage#getEdgeShapeContent_Startopen()
	 * @model dataType="net.gexf_1_2.gexf.TimeType"
	 *        extendedMetaData="kind='attribute' name='startopen'"
	 * @generated
	 */
	Object getStartopen();

	/**
	 * Sets the value of the '
	 * {@link net.gexf_1_2.viz.EdgeShapeContent#getStartopen <em>Startopen</em>}
	 * ' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Startopen</em>' attribute.
	 * @see #getStartopen()
	 * @generated
	 */
	void setStartopen(Object value);

	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute. The literals
	 * are from the enumeration {@link net.gexf_1_2.viz.EdgeShapeType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Value</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see net.gexf_1_2.viz.EdgeShapeType
	 * @see #isSetValue()
	 * @see #unsetValue()
	 * @see #setValue(EdgeShapeType)
	 * @see net.gexf_1_2.viz.VizPackage#getEdgeShapeContent_Value()
	 * @model unsettable="true" required="true"
	 *        extendedMetaData="kind='attribute' name='value'"
	 * @generated
	 */
	EdgeShapeType getValue();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.viz.EdgeShapeContent#getValue
	 * <em>Value</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Value</em>' attribute.
	 * @see net.gexf_1_2.viz.EdgeShapeType
	 * @see #isSetValue()
	 * @see #unsetValue()
	 * @see #getValue()
	 * @generated
	 */
	void setValue(EdgeShapeType value);

	/**
	 * Unsets the value of the '
	 * {@link net.gexf_1_2.viz.EdgeShapeContent#getValue <em>Value</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetValue()
	 * @see #getValue()
	 * @see #setValue(EdgeShapeType)
	 * @generated
	 */
	void unsetValue();

	/**
	 * Returns whether the value of the '
	 * {@link net.gexf_1_2.viz.EdgeShapeContent#getValue <em>Value</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Value</em>' attribute is set.
	 * @see #unsetValue()
	 * @see #getValue()
	 * @see #setValue(EdgeShapeType)
	 * @generated
	 */
	boolean isSetValue();

} // EdgeShapeContent
