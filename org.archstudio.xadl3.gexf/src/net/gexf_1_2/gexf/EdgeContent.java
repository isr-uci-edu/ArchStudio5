/**
 */
package net.gexf_1_2.gexf;

import net.gexf_1_2.viz.ColorContent;
import net.gexf_1_2.viz.EdgeShapeContent;
import net.gexf_1_2.viz.ThicknessContent;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Edge Content</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getGroup <em>Group</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getAttvalues <em>Attvalues</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getSpells <em>Spells</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getColor <em>Color</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getThickness <em>Thickness</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getShape <em>Shape</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getEnd <em>End</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getEndopen <em>Endopen</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getId <em>Id</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getLabel <em>Label</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getSource <em>Source</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getStart <em>Start</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getStartopen <em>Startopen</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getTarget <em>Target</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getType <em>Type</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.EdgeContent#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent()
 * @model extendedMetaData="name='edge-content' kind='elementOnly'"
 * @generated
 */
public interface EdgeContent extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list. The list contents are of type
	 * {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry" many="true"
	 *        extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Attvalues</b></em>' containment reference list. The list contents are of type
	 * {@link net.gexf_1_2.gexf.AttvaluesContent}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attvalues</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attvalues</em>' containment reference list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Attvalues()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='attvalues' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<AttvaluesContent> getAttvalues();

	/**
	 * Returns the value of the '<em><b>Spells</b></em>' containment reference list. The list contents are of type
	 * {@link net.gexf_1_2.gexf.SpellsContent}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Spells</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Spells</em>' containment reference list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Spells()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='spells' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<SpellsContent> getSpells();

	/**
	 * Returns the value of the '<em><b>Color</b></em>' containment reference list. The list contents are of type
	 * {@link net.gexf_1_2.viz.ColorContent}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Color</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Color</em>' containment reference list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Color()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='color' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ColorContent> getColor();

	/**
	 * Returns the value of the '<em><b>Thickness</b></em>' containment reference list. The list contents are of type
	 * {@link net.gexf_1_2.viz.ThicknessContent}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Thickness</em>' containment reference list isn't clear, there really should be more of
	 * a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Thickness</em>' containment reference list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Thickness()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='thickness' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<ThicknessContent> getThickness();

	/**
	 * Returns the value of the '<em><b>Shape</b></em>' containment reference list. The list contents are of type
	 * {@link net.gexf_1_2.viz.EdgeShapeContent}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Shape</em>' containment reference list isn't clear, there really should be more of a
	 * description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Shape</em>' containment reference list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Shape()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData="kind='element' name='shape' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<EdgeShapeContent> getShape();

	/**
	 * Returns the value of the '<em><b>End</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>End</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>End</em>' attribute.
	 * @see #setEnd(Object)
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_End()
	 * @model dataType="net.gexf_1_2.gexf.TimeType" extendedMetaData="kind='attribute' name='end'"
	 * @generated
	 */
	Object getEnd();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getEnd <em>End</em>}' attribute. <!-- begin-user-doc
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
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Endopen()
	 * @model dataType="net.gexf_1_2.gexf.TimeType" extendedMetaData="kind='attribute' name='endopen'"
	 * @generated
	 */
	Object getEndopen();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getEndopen <em>Endopen</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Endopen</em>' attribute.
	 * @see #getEndopen()
	 * @generated
	 */
	void setEndopen(Object value);

	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(Object)
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Id()
	 * @model dataType="net.gexf_1_2.gexf.IdType" required="true" extendedMetaData="kind='attribute' name='id'"
	 * @generated
	 */
	Object getId();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getId <em>Id</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(Object value);

	/**
	 * Returns the value of the '<em><b>Label</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Label</em>' attribute.
	 * @see #setLabel(String)
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Label()
	 * @model dataType="org.eclipse.emf.ecore.xml.type.Token" extendedMetaData="kind='attribute' name='label'"
	 * @generated
	 */
	String getLabel();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getLabel <em>Label</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Label</em>' attribute.
	 * @see #getLabel()
	 * @generated
	 */
	void setLabel(String value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Source</em>' attribute.
	 * @see #setSource(Object)
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Source()
	 * @model dataType="net.gexf_1_2.gexf.IdType" required="true" extendedMetaData="kind='attribute' name='source'"
	 * @generated
	 */
	Object getSource();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getSource <em>Source</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Source</em>' attribute.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Object value);

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
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Start()
	 * @model dataType="net.gexf_1_2.gexf.TimeType" extendedMetaData="kind='attribute' name='start'"
	 * @generated
	 */
	Object getStart();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getStart <em>Start</em>}' attribute. <!--
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
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Startopen()
	 * @model dataType="net.gexf_1_2.gexf.TimeType" extendedMetaData="kind='attribute' name='startopen'"
	 * @generated
	 */
	Object getStartopen();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getStartopen <em>Startopen</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Startopen</em>' attribute.
	 * @see #getStartopen()
	 * @generated
	 */
	void setStartopen(Object value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Target</em>' attribute.
	 * @see #setTarget(Object)
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Target()
	 * @model dataType="net.gexf_1_2.gexf.IdType" required="true" extendedMetaData="kind='attribute' name='target'"
	 * @generated
	 */
	Object getTarget();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getTarget <em>Target</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Target</em>' attribute.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Object value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute. The literals are from the enumeration
	 * {@link net.gexf_1_2.gexf.EdgetypeType}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see net.gexf_1_2.gexf.EdgetypeType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #setType(EdgetypeType)
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Type()
	 * @model unsettable="true" extendedMetaData="kind='attribute' name='type'"
	 * @generated
	 */
	EdgetypeType getType();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getType <em>Type</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Type</em>' attribute.
	 * @see net.gexf_1_2.gexf.EdgetypeType
	 * @see #isSetType()
	 * @see #unsetType()
	 * @see #getType()
	 * @generated
	 */
	void setType(EdgetypeType value);

	/**
	 * Unsets the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getType <em>Type</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetType()
	 * @see #getType()
	 * @see #setType(EdgetypeType)
	 * @generated
	 */
	void unsetType();

	/**
	 * Returns whether the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getType <em>Type</em>}' attribute is set.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Type</em>' attribute is set.
	 * @see #unsetType()
	 * @see #getType()
	 * @see #setType(EdgetypeType)
	 * @generated
	 */
	boolean isSetType();

	/**
	 * Returns the value of the '<em><b>Weight</b></em>' attribute. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Weight</em>' attribute isn't clear, there really should be more of a description
	 * here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Weight</em>' attribute.
	 * @see #isSetWeight()
	 * @see #unsetWeight()
	 * @see #setWeight(float)
	 * @see net.gexf_1_2.gexf.GexfPackage#getEdgeContent_Weight()
	 * @model unsettable="true" dataType="net.gexf_1_2.gexf.WeightType"
	 *        extendedMetaData="kind='attribute' name='weight'"
	 * @generated
	 */
	float getWeight();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getWeight <em>Weight</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Weight</em>' attribute.
	 * @see #isSetWeight()
	 * @see #unsetWeight()
	 * @see #getWeight()
	 * @generated
	 */
	void setWeight(float value);

	/**
	 * Unsets the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getWeight <em>Weight</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetWeight()
	 * @see #getWeight()
	 * @see #setWeight(float)
	 * @generated
	 */
	void unsetWeight();

	/**
	 * Returns whether the value of the '{@link net.gexf_1_2.gexf.EdgeContent#getWeight <em>Weight</em>}' attribute is
	 * set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Weight</em>' attribute is set.
	 * @see #unsetWeight()
	 * @see #getWeight()
	 * @see #setWeight(float)
	 * @generated
	 */
	boolean isSetWeight();

} // EdgeContent
