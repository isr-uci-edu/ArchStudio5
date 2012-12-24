/**
 */
package net.gexf_1_2.gexf.impl;

import java.util.Collection;

import net.gexf_1_2.gexf.AttvaluesContent;
import net.gexf_1_2.gexf.EdgeContent;
import net.gexf_1_2.gexf.EdgetypeType;
import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.SpellsContent;
import net.gexf_1_2.viz.ColorContent;
import net.gexf_1_2.viz.EdgeShapeContent;
import net.gexf_1_2.viz.ThicknessContent;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Edge Content</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getGroup <em>Group</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getAttvalues <em>Attvalues
 * </em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getSpells <em>Spells</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getColor <em>Color</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getThickness <em>Thickness
 * </em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getShape <em>Shape</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getEnd <em>End</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getEndopen <em>Endopen
 * </em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getId <em>Id</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getLabel <em>Label</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getSource <em>Source</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getStart <em>Start</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getStartopen <em>Startopen
 * </em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getTarget <em>Target</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getType <em>Type</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.EdgeContentImpl#getWeight <em>Weight</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class EdgeContentImpl extends EObjectImpl implements EdgeContent {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute
	 * list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getEnd() <em>End</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected static final Object END_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnd() <em>End</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected Object end = END_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndopen() <em>Endopen</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEndopen()
	 * @generated
	 * @ordered
	 */
	protected static final Object ENDOPEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEndopen() <em>Endopen</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEndopen()
	 * @generated
	 * @ordered
	 */
	protected Object endopen = ENDOPEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final Object ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected Object id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getSource() <em>Source</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected static final Object SOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Object source = SOURCE_EDEFAULT;

	/**
	 * The default value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected static final Object START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStart() <em>Start</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected Object start = START_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartopen() <em>Startopen</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStartopen()
	 * @generated
	 * @ordered
	 */
	protected static final Object STARTOPEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartopen() <em>Startopen</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStartopen()
	 * @generated
	 * @ordered
	 */
	protected Object startopen = STARTOPEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getTarget() <em>Target</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected static final Object TARGET_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Object target = TARGET_EDEFAULT;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final EdgetypeType TYPE_EDEFAULT = EdgetypeType.DIRECTED;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected EdgetypeType type = TYPE_EDEFAULT;

	/**
	 * This is true if the Type attribute has been set. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean typeESet;

	/**
	 * The default value of the '{@link #getWeight() <em>Weight</em>}'
	 * attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected static final float WEIGHT_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getWeight() <em>Weight</em>}' attribute.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getWeight()
	 * @generated
	 * @ordered
	 */
	protected float weight = WEIGHT_EDEFAULT;

	/**
	 * This is true if the Weight attribute has been set. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean weightESet;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected EdgeContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	protected EClass eStaticClass() {
		return GexfPackage.Literals.EDGE_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, GexfPackage.EDGE_CONTENT__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<AttvaluesContent> getAttvalues() {
		return getGroup().list(GexfPackage.Literals.EDGE_CONTENT__ATTVALUES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<SpellsContent> getSpells() {
		return getGroup().list(GexfPackage.Literals.EDGE_CONTENT__SPELLS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<ColorContent> getColor() {
		return getGroup().list(GexfPackage.Literals.EDGE_CONTENT__COLOR);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<ThicknessContent> getThickness() {
		return getGroup().list(GexfPackage.Literals.EDGE_CONTENT__THICKNESS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EdgeShapeContent> getShape() {
		return getGroup().list(GexfPackage.Literals.EDGE_CONTENT__SHAPE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getEnd() {
		return end;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEnd(Object newEnd) {
		Object oldEnd = end;
		end = newEnd;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.EDGE_CONTENT__END, oldEnd, end));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getEndopen() {
		return endopen;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setEndopen(Object newEndopen) {
		Object oldEndopen = endopen;
		endopen = newEndopen;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.EDGE_CONTENT__ENDOPEN, oldEndopen,
					endopen));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setId(Object newId) {
		Object oldId = id;
		id = newId;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.EDGE_CONTENT__ID, oldId, id));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getLabel() {
		return label;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setLabel(String newLabel) {
		String oldLabel = label;
		label = newLabel;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.EDGE_CONTENT__LABEL, oldLabel, label));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setSource(Object newSource) {
		Object oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.EDGE_CONTENT__SOURCE, oldSource, source));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getStart() {
		return start;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setStart(Object newStart) {
		Object oldStart = start;
		start = newStart;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.EDGE_CONTENT__START, oldStart, start));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getStartopen() {
		return startopen;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setStartopen(Object newStartopen) {
		Object oldStartopen = startopen;
		startopen = newStartopen;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.EDGE_CONTENT__STARTOPEN, oldStartopen,
					startopen));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setTarget(Object newTarget) {
		Object oldTarget = target;
		target = newTarget;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.EDGE_CONTENT__TARGET, oldTarget, target));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EdgetypeType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setType(EdgetypeType newType) {
		EdgetypeType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		boolean oldTypeESet = typeESet;
		typeESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.EDGE_CONTENT__TYPE, oldType, type,
					!oldTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetType() {
		EdgetypeType oldType = type;
		boolean oldTypeESet = typeESet;
		type = TYPE_EDEFAULT;
		typeESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, GexfPackage.EDGE_CONTENT__TYPE, oldType,
					TYPE_EDEFAULT, oldTypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetType() {
		return typeESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public float getWeight() {
		return weight;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setWeight(float newWeight) {
		float oldWeight = weight;
		weight = newWeight;
		boolean oldWeightESet = weightESet;
		weightESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.EDGE_CONTENT__WEIGHT, oldWeight, weight,
					!oldWeightESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetWeight() {
		float oldWeight = weight;
		boolean oldWeightESet = weightESet;
		weight = WEIGHT_EDEFAULT;
		weightESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, GexfPackage.EDGE_CONTENT__WEIGHT, oldWeight,
					WEIGHT_EDEFAULT, oldWeightESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetWeight() {
		return weightESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GexfPackage.EDGE_CONTENT__GROUP:
			return ((InternalEList<?>) getGroup()).basicRemove(otherEnd, msgs);
		case GexfPackage.EDGE_CONTENT__ATTVALUES:
			return ((InternalEList<?>) getAttvalues()).basicRemove(otherEnd, msgs);
		case GexfPackage.EDGE_CONTENT__SPELLS:
			return ((InternalEList<?>) getSpells()).basicRemove(otherEnd, msgs);
		case GexfPackage.EDGE_CONTENT__COLOR:
			return ((InternalEList<?>) getColor()).basicRemove(otherEnd, msgs);
		case GexfPackage.EDGE_CONTENT__THICKNESS:
			return ((InternalEList<?>) getThickness()).basicRemove(otherEnd, msgs);
		case GexfPackage.EDGE_CONTENT__SHAPE:
			return ((InternalEList<?>) getShape()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case GexfPackage.EDGE_CONTENT__GROUP:
			if (coreType) {
				return getGroup();
			}
			return ((FeatureMap.Internal) getGroup()).getWrapper();
		case GexfPackage.EDGE_CONTENT__ATTVALUES:
			return getAttvalues();
		case GexfPackage.EDGE_CONTENT__SPELLS:
			return getSpells();
		case GexfPackage.EDGE_CONTENT__COLOR:
			return getColor();
		case GexfPackage.EDGE_CONTENT__THICKNESS:
			return getThickness();
		case GexfPackage.EDGE_CONTENT__SHAPE:
			return getShape();
		case GexfPackage.EDGE_CONTENT__END:
			return getEnd();
		case GexfPackage.EDGE_CONTENT__ENDOPEN:
			return getEndopen();
		case GexfPackage.EDGE_CONTENT__ID:
			return getId();
		case GexfPackage.EDGE_CONTENT__LABEL:
			return getLabel();
		case GexfPackage.EDGE_CONTENT__SOURCE:
			return getSource();
		case GexfPackage.EDGE_CONTENT__START:
			return getStart();
		case GexfPackage.EDGE_CONTENT__STARTOPEN:
			return getStartopen();
		case GexfPackage.EDGE_CONTENT__TARGET:
			return getTarget();
		case GexfPackage.EDGE_CONTENT__TYPE:
			return getType();
		case GexfPackage.EDGE_CONTENT__WEIGHT:
			return getWeight();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	@SuppressWarnings("unchecked")
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GexfPackage.EDGE_CONTENT__GROUP:
			((FeatureMap.Internal) getGroup()).set(newValue);
			return;
		case GexfPackage.EDGE_CONTENT__ATTVALUES:
			getAttvalues().clear();
			getAttvalues().addAll((Collection<? extends AttvaluesContent>) newValue);
			return;
		case GexfPackage.EDGE_CONTENT__SPELLS:
			getSpells().clear();
			getSpells().addAll((Collection<? extends SpellsContent>) newValue);
			return;
		case GexfPackage.EDGE_CONTENT__COLOR:
			getColor().clear();
			getColor().addAll((Collection<? extends ColorContent>) newValue);
			return;
		case GexfPackage.EDGE_CONTENT__THICKNESS:
			getThickness().clear();
			getThickness().addAll((Collection<? extends ThicknessContent>) newValue);
			return;
		case GexfPackage.EDGE_CONTENT__SHAPE:
			getShape().clear();
			getShape().addAll((Collection<? extends EdgeShapeContent>) newValue);
			return;
		case GexfPackage.EDGE_CONTENT__END:
			setEnd(newValue);
			return;
		case GexfPackage.EDGE_CONTENT__ENDOPEN:
			setEndopen(newValue);
			return;
		case GexfPackage.EDGE_CONTENT__ID:
			setId(newValue);
			return;
		case GexfPackage.EDGE_CONTENT__LABEL:
			setLabel((String) newValue);
			return;
		case GexfPackage.EDGE_CONTENT__SOURCE:
			setSource(newValue);
			return;
		case GexfPackage.EDGE_CONTENT__START:
			setStart(newValue);
			return;
		case GexfPackage.EDGE_CONTENT__STARTOPEN:
			setStartopen(newValue);
			return;
		case GexfPackage.EDGE_CONTENT__TARGET:
			setTarget(newValue);
			return;
		case GexfPackage.EDGE_CONTENT__TYPE:
			setType((EdgetypeType) newValue);
			return;
		case GexfPackage.EDGE_CONTENT__WEIGHT:
			setWeight((Float) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case GexfPackage.EDGE_CONTENT__GROUP:
			getGroup().clear();
			return;
		case GexfPackage.EDGE_CONTENT__ATTVALUES:
			getAttvalues().clear();
			return;
		case GexfPackage.EDGE_CONTENT__SPELLS:
			getSpells().clear();
			return;
		case GexfPackage.EDGE_CONTENT__COLOR:
			getColor().clear();
			return;
		case GexfPackage.EDGE_CONTENT__THICKNESS:
			getThickness().clear();
			return;
		case GexfPackage.EDGE_CONTENT__SHAPE:
			getShape().clear();
			return;
		case GexfPackage.EDGE_CONTENT__END:
			setEnd(END_EDEFAULT);
			return;
		case GexfPackage.EDGE_CONTENT__ENDOPEN:
			setEndopen(ENDOPEN_EDEFAULT);
			return;
		case GexfPackage.EDGE_CONTENT__ID:
			setId(ID_EDEFAULT);
			return;
		case GexfPackage.EDGE_CONTENT__LABEL:
			setLabel(LABEL_EDEFAULT);
			return;
		case GexfPackage.EDGE_CONTENT__SOURCE:
			setSource(SOURCE_EDEFAULT);
			return;
		case GexfPackage.EDGE_CONTENT__START:
			setStart(START_EDEFAULT);
			return;
		case GexfPackage.EDGE_CONTENT__STARTOPEN:
			setStartopen(STARTOPEN_EDEFAULT);
			return;
		case GexfPackage.EDGE_CONTENT__TARGET:
			setTarget(TARGET_EDEFAULT);
			return;
		case GexfPackage.EDGE_CONTENT__TYPE:
			unsetType();
			return;
		case GexfPackage.EDGE_CONTENT__WEIGHT:
			unsetWeight();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case GexfPackage.EDGE_CONTENT__GROUP:
			return group != null && !group.isEmpty();
		case GexfPackage.EDGE_CONTENT__ATTVALUES:
			return !getAttvalues().isEmpty();
		case GexfPackage.EDGE_CONTENT__SPELLS:
			return !getSpells().isEmpty();
		case GexfPackage.EDGE_CONTENT__COLOR:
			return !getColor().isEmpty();
		case GexfPackage.EDGE_CONTENT__THICKNESS:
			return !getThickness().isEmpty();
		case GexfPackage.EDGE_CONTENT__SHAPE:
			return !getShape().isEmpty();
		case GexfPackage.EDGE_CONTENT__END:
			return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
		case GexfPackage.EDGE_CONTENT__ENDOPEN:
			return ENDOPEN_EDEFAULT == null ? endopen != null : !ENDOPEN_EDEFAULT.equals(endopen);
		case GexfPackage.EDGE_CONTENT__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case GexfPackage.EDGE_CONTENT__LABEL:
			return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
		case GexfPackage.EDGE_CONTENT__SOURCE:
			return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
		case GexfPackage.EDGE_CONTENT__START:
			return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
		case GexfPackage.EDGE_CONTENT__STARTOPEN:
			return STARTOPEN_EDEFAULT == null ? startopen != null : !STARTOPEN_EDEFAULT.equals(startopen);
		case GexfPackage.EDGE_CONTENT__TARGET:
			return TARGET_EDEFAULT == null ? target != null : !TARGET_EDEFAULT.equals(target);
		case GexfPackage.EDGE_CONTENT__TYPE:
			return isSetType();
		case GexfPackage.EDGE_CONTENT__WEIGHT:
			return isSetWeight();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */

	@Override
	public String toString() {
		if (eIsProxy()) {
			return super.toString();
		}

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (group: ");
		result.append(group);
		result.append(", end: ");
		result.append(end);
		result.append(", endopen: ");
		result.append(endopen);
		result.append(", id: ");
		result.append(id);
		result.append(", label: ");
		result.append(label);
		result.append(", source: ");
		result.append(source);
		result.append(", start: ");
		result.append(start);
		result.append(", startopen: ");
		result.append(startopen);
		result.append(", target: ");
		result.append(target);
		result.append(", type: ");
		if (typeESet) {
			result.append(type);
		}
		else {
			result.append("<unset>");
		}
		result.append(", weight: ");
		if (weightESet) {
			result.append(weight);
		}
		else {
			result.append("<unset>");
		}
		result.append(')');
		return result.toString();
	}

} //EdgeContentImpl
