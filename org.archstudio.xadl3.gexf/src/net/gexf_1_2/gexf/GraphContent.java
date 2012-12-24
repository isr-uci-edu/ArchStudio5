/**
 */
package net.gexf_1_2.gexf;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.FeatureMap;

/**
 * <!-- begin-user-doc --> A representation of the model object '
 * <em><b>Graph Content</b></em>'. <!-- end-user-doc -->
 * 
 * <p>
 * The following features are supported:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.GraphContent#getGroup <em>Group</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GraphContent#getAttributes <em>Attributes</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GraphContent#getNodes <em>Nodes</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GraphContent#getEdges <em>Edges</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GraphContent#getDefaultedgetype <em>
 * Defaultedgetype</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GraphContent#getEnd <em>End</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GraphContent#getEndopen <em>Endopen</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GraphContent#getIdtype <em>Idtype</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GraphContent#getMode <em>Mode</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GraphContent#getStart <em>Start</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GraphContent#getStartopen <em>Startopen</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.GraphContent#getTimeformat <em>Timeformat</em>}</li>
 * </ul>
 * </p>
 * 
 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent()
 * @model extendedMetaData="name='graph-content' kind='elementOnly'"
 * @generated
 */
public interface GraphContent extends EObject {
	/**
	 * Returns the value of the '<em><b>Group</b></em>' attribute list. The list
	 * contents are of type {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Group</em>' attribute list isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Group</em>' attribute list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent_Group()
	 * @model unique="false" dataType="org.eclipse.emf.ecore.EFeatureMapEntry"
	 *        many="true" extendedMetaData="kind='group' name='group:0'"
	 * @generated
	 */
	FeatureMap getGroup();

	/**
	 * Returns the value of the '<em><b>Attributes</b></em>' containment
	 * reference list. The list contents are of type
	 * {@link net.gexf_1_2.gexf.AttributesContent}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Attributes</em>' containment reference list
	 * isn't clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Attributes</em>' containment reference
	 *         list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent_Attributes()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData=
	 *        "kind='element' name='attributes' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<AttributesContent> getAttributes();

	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link net.gexf_1_2.gexf.NodesContent}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent_Nodes()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData=
	 *        "kind='element' name='nodes' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<NodesContent> getNodes();

	/**
	 * Returns the value of the '<em><b>Edges</b></em>' containment reference
	 * list. The list contents are of type
	 * {@link net.gexf_1_2.gexf.EdgesContent}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edges</em>' containment reference list isn't
	 * clear, there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Edges</em>' containment reference list.
	 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent_Edges()
	 * @model containment="true" transient="true" volatile="true" derived="true"
	 *        extendedMetaData=
	 *        "kind='element' name='edges' namespace='##targetNamespace' group='#group:0'"
	 * @generated
	 */
	EList<EdgesContent> getEdges();

	/**
	 * Returns the value of the '<em><b>Defaultedgetype</b></em>' attribute. The
	 * literals are from the enumeration
	 * {@link net.gexf_1_2.gexf.DefaultedgetypeType}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Defaultedgetype</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Defaultedgetype</em>' attribute.
	 * @see net.gexf_1_2.gexf.DefaultedgetypeType
	 * @see #isSetDefaultedgetype()
	 * @see #unsetDefaultedgetype()
	 * @see #setDefaultedgetype(DefaultedgetypeType)
	 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent_Defaultedgetype()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='defaultedgetype'"
	 * @generated
	 */
	DefaultedgetypeType getDefaultedgetype();

	/**
	 * Sets the value of the '
	 * {@link net.gexf_1_2.gexf.GraphContent#getDefaultedgetype
	 * <em>Defaultedgetype</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Defaultedgetype</em>' attribute.
	 * @see net.gexf_1_2.gexf.DefaultedgetypeType
	 * @see #isSetDefaultedgetype()
	 * @see #unsetDefaultedgetype()
	 * @see #getDefaultedgetype()
	 * @generated
	 */
	void setDefaultedgetype(DefaultedgetypeType value);

	/**
	 * Unsets the value of the '
	 * {@link net.gexf_1_2.gexf.GraphContent#getDefaultedgetype
	 * <em>Defaultedgetype</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #isSetDefaultedgetype()
	 * @see #getDefaultedgetype()
	 * @see #setDefaultedgetype(DefaultedgetypeType)
	 * @generated
	 */
	void unsetDefaultedgetype();

	/**
	 * Returns whether the value of the '
	 * {@link net.gexf_1_2.gexf.GraphContent#getDefaultedgetype
	 * <em>Defaultedgetype</em>}' attribute is set. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Defaultedgetype</em>' attribute is
	 *         set.
	 * @see #unsetDefaultedgetype()
	 * @see #getDefaultedgetype()
	 * @see #setDefaultedgetype(DefaultedgetypeType)
	 * @generated
	 */
	boolean isSetDefaultedgetype();

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
	 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent_End()
	 * @model dataType="net.gexf_1_2.gexf.TimeType"
	 *        extendedMetaData="kind='attribute' name='end'"
	 * @generated
	 */
	Object getEnd();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.GraphContent#getEnd
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
	 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent_Endopen()
	 * @model dataType="net.gexf_1_2.gexf.TimeType"
	 *        extendedMetaData="kind='attribute' name='endopen'"
	 * @generated
	 */
	Object getEndopen();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.GraphContent#getEndopen
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
	 * Returns the value of the '<em><b>Idtype</b></em>' attribute. The literals
	 * are from the enumeration {@link net.gexf_1_2.gexf.IdtypeType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Idtype</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Idtype</em>' attribute.
	 * @see net.gexf_1_2.gexf.IdtypeType
	 * @see #isSetIdtype()
	 * @see #unsetIdtype()
	 * @see #setIdtype(IdtypeType)
	 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent_Idtype()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='idtype'"
	 * @generated
	 */
	IdtypeType getIdtype();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.GraphContent#getIdtype
	 * <em>Idtype</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @param value
	 *            the new value of the '<em>Idtype</em>' attribute.
	 * @see net.gexf_1_2.gexf.IdtypeType
	 * @see #isSetIdtype()
	 * @see #unsetIdtype()
	 * @see #getIdtype()
	 * @generated
	 */
	void setIdtype(IdtypeType value);

	/**
	 * Unsets the value of the '{@link net.gexf_1_2.gexf.GraphContent#getIdtype
	 * <em>Idtype</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #isSetIdtype()
	 * @see #getIdtype()
	 * @see #setIdtype(IdtypeType)
	 * @generated
	 */
	void unsetIdtype();

	/**
	 * Returns whether the value of the '
	 * {@link net.gexf_1_2.gexf.GraphContent#getIdtype <em>Idtype</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Idtype</em>' attribute is set.
	 * @see #unsetIdtype()
	 * @see #getIdtype()
	 * @see #setIdtype(IdtypeType)
	 * @generated
	 */
	boolean isSetIdtype();

	/**
	 * Returns the value of the '<em><b>Mode</b></em>' attribute. The literals
	 * are from the enumeration {@link net.gexf_1_2.gexf.ModeType}. <!--
	 * begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mode</em>' attribute isn't clear, there really
	 * should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Mode</em>' attribute.
	 * @see net.gexf_1_2.gexf.ModeType
	 * @see #isSetMode()
	 * @see #unsetMode()
	 * @see #setMode(ModeType)
	 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent_Mode()
	 * @model unsettable="true" extendedMetaData="kind='attribute' name='mode'"
	 * @generated
	 */
	ModeType getMode();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.GraphContent#getMode
	 * <em>Mode</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Mode</em>' attribute.
	 * @see net.gexf_1_2.gexf.ModeType
	 * @see #isSetMode()
	 * @see #unsetMode()
	 * @see #getMode()
	 * @generated
	 */
	void setMode(ModeType value);

	/**
	 * Unsets the value of the '{@link net.gexf_1_2.gexf.GraphContent#getMode
	 * <em>Mode</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetMode()
	 * @see #getMode()
	 * @see #setMode(ModeType)
	 * @generated
	 */
	void unsetMode();

	/**
	 * Returns whether the value of the '
	 * {@link net.gexf_1_2.gexf.GraphContent#getMode <em>Mode</em>}' attribute
	 * is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Mode</em>' attribute is set.
	 * @see #unsetMode()
	 * @see #getMode()
	 * @see #setMode(ModeType)
	 * @generated
	 */
	boolean isSetMode();

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
	 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent_Start()
	 * @model dataType="net.gexf_1_2.gexf.TimeType"
	 *        extendedMetaData="kind='attribute' name='start'"
	 * @generated
	 */
	Object getStart();

	/**
	 * Sets the value of the '{@link net.gexf_1_2.gexf.GraphContent#getStart
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
	 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent_Startopen()
	 * @model dataType="net.gexf_1_2.gexf.TimeType"
	 *        extendedMetaData="kind='attribute' name='startopen'"
	 * @generated
	 */
	Object getStartopen();

	/**
	 * Sets the value of the '
	 * {@link net.gexf_1_2.gexf.GraphContent#getStartopen <em>Startopen</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Startopen</em>' attribute.
	 * @see #getStartopen()
	 * @generated
	 */
	void setStartopen(Object value);

	/**
	 * Returns the value of the '<em><b>Timeformat</b></em>' attribute. The
	 * literals are from the enumeration
	 * {@link net.gexf_1_2.gexf.TimeformatType}. <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Timeformat</em>' attribute isn't clear, there
	 * really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * 
	 * @return the value of the '<em>Timeformat</em>' attribute.
	 * @see net.gexf_1_2.gexf.TimeformatType
	 * @see #isSetTimeformat()
	 * @see #unsetTimeformat()
	 * @see #setTimeformat(TimeformatType)
	 * @see net.gexf_1_2.gexf.GexfPackage#getGraphContent_Timeformat()
	 * @model unsettable="true"
	 *        extendedMetaData="kind='attribute' name='timeformat'"
	 * @generated
	 */
	TimeformatType getTimeformat();

	/**
	 * Sets the value of the '
	 * {@link net.gexf_1_2.gexf.GraphContent#getTimeformat <em>Timeformat</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @param value
	 *            the new value of the '<em>Timeformat</em>' attribute.
	 * @see net.gexf_1_2.gexf.TimeformatType
	 * @see #isSetTimeformat()
	 * @see #unsetTimeformat()
	 * @see #getTimeformat()
	 * @generated
	 */
	void setTimeformat(TimeformatType value);

	/**
	 * Unsets the value of the '
	 * {@link net.gexf_1_2.gexf.GraphContent#getTimeformat <em>Timeformat</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #isSetTimeformat()
	 * @see #getTimeformat()
	 * @see #setTimeformat(TimeformatType)
	 * @generated
	 */
	void unsetTimeformat();

	/**
	 * Returns whether the value of the '
	 * {@link net.gexf_1_2.gexf.GraphContent#getTimeformat <em>Timeformat</em>}'
	 * attribute is set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @return whether the value of the '<em>Timeformat</em>' attribute is set.
	 * @see #unsetTimeformat()
	 * @see #getTimeformat()
	 * @see #setTimeformat(TimeformatType)
	 * @generated
	 */
	boolean isSetTimeformat();

} // GraphContent
