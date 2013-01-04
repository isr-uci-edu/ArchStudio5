/**
 */
package net.gexf_1_2.gexf.impl;

import java.util.Collection;

import net.gexf_1_2.gexf.AttvaluesContent;
import net.gexf_1_2.gexf.EdgesContent;
import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.NodeContent;
import net.gexf_1_2.gexf.NodesContent;
import net.gexf_1_2.gexf.ParentsContent;
import net.gexf_1_2.gexf.SpellsContent;
import net.gexf_1_2.viz.ColorContent;
import net.gexf_1_2.viz.NodeShapeContent;
import net.gexf_1_2.viz.PositionContent;
import net.gexf_1_2.viz.SizeContent;

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
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Node Content</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getGroup <em>Group</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getAttvalues <em>Attvalues</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getSpells <em>Spells</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getNodes <em>Nodes</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getEdges <em>Edges</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getParents <em>Parents</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getColor <em>Color</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getPosition <em>Position</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getSize <em>Size</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getShape <em>Shape</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getEnd <em>End</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getEndopen <em>Endopen</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getId <em>Id</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getLabel <em>Label</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getPid <em>Pid</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getStart <em>Start</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.NodeContentImpl#getStartopen <em>Startopen</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class NodeContentImpl extends EObjectImpl implements NodeContent {
	/**
	 * The cached value of the '{@link #getGroup() <em>Group</em>}' attribute list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGroup()
	 * @generated
	 * @ordered
	 */
	protected FeatureMap group;

	/**
	 * The default value of the '{@link #getEnd() <em>End</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected static final Object END_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEnd() <em>End</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getEnd()
	 * @generated
	 * @ordered
	 */
	protected Object end = END_EDEFAULT;

	/**
	 * The default value of the '{@link #getEndopen() <em>Endopen</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEndopen()
	 * @generated
	 * @ordered
	 */
	protected static final Object ENDOPEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getEndopen() <em>Endopen</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getEndopen()
	 * @generated
	 * @ordered
	 */
	protected Object endopen = ENDOPEN_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final Object ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected Object id = ID_EDEFAULT;

	/**
	 * The default value of the '{@link #getLabel() <em>Label</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected static final String LABEL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getLabel() <em>Label</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getLabel()
	 * @generated
	 * @ordered
	 */
	protected String label = LABEL_EDEFAULT;

	/**
	 * The default value of the '{@link #getPid() <em>Pid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getPid()
	 * @generated
	 * @ordered
	 */
	protected static final Object PID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPid() <em>Pid</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getPid()
	 * @generated
	 * @ordered
	 */
	protected Object pid = PID_EDEFAULT;

	/**
	 * The default value of the '{@link #getStart() <em>Start</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected static final Object START_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStart() <em>Start</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getStart()
	 * @generated
	 * @ordered
	 */
	protected Object start = START_EDEFAULT;

	/**
	 * The default value of the '{@link #getStartopen() <em>Startopen</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStartopen()
	 * @generated
	 * @ordered
	 */
	protected static final Object STARTOPEN_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStartopen() <em>Startopen</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getStartopen()
	 * @generated
	 * @ordered
	 */
	protected Object startopen = STARTOPEN_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected NodeContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GexfPackage.Literals.NODE_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, GexfPackage.NODE_CONTENT__GROUP);
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
		return getGroup().list(GexfPackage.Literals.NODE_CONTENT__ATTVALUES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<SpellsContent> getSpells() {
		return getGroup().list(GexfPackage.Literals.NODE_CONTENT__SPELLS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<NodesContent> getNodes() {
		return getGroup().list(GexfPackage.Literals.NODE_CONTENT__NODES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EdgesContent> getEdges() {
		return getGroup().list(GexfPackage.Literals.NODE_CONTENT__EDGES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<ParentsContent> getParents() {
		return getGroup().list(GexfPackage.Literals.NODE_CONTENT__PARENTS);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<ColorContent> getColor() {
		return getGroup().list(GexfPackage.Literals.NODE_CONTENT__COLOR);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<PositionContent> getPosition() {
		return getGroup().list(GexfPackage.Literals.NODE_CONTENT__POSITION);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<SizeContent> getSize() {
		return getGroup().list(GexfPackage.Literals.NODE_CONTENT__SIZE);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<NodeShapeContent> getShape() {
		return getGroup().list(GexfPackage.Literals.NODE_CONTENT__SHAPE);
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
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.NODE_CONTENT__END, oldEnd, end));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.NODE_CONTENT__ENDOPEN, oldEndopen,
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
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.NODE_CONTENT__ID, oldId, id));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.NODE_CONTENT__LABEL, oldLabel, label));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object getPid() {
		return pid;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setPid(Object newPid) {
		Object oldPid = pid;
		pid = newPid;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.NODE_CONTENT__PID, oldPid, pid));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.NODE_CONTENT__START, oldStart, start));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.NODE_CONTENT__STARTOPEN, oldStartopen,
					startopen));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GexfPackage.NODE_CONTENT__GROUP:
			return ((InternalEList<?>) getGroup()).basicRemove(otherEnd, msgs);
		case GexfPackage.NODE_CONTENT__ATTVALUES:
			return ((InternalEList<?>) getAttvalues()).basicRemove(otherEnd, msgs);
		case GexfPackage.NODE_CONTENT__SPELLS:
			return ((InternalEList<?>) getSpells()).basicRemove(otherEnd, msgs);
		case GexfPackage.NODE_CONTENT__NODES:
			return ((InternalEList<?>) getNodes()).basicRemove(otherEnd, msgs);
		case GexfPackage.NODE_CONTENT__EDGES:
			return ((InternalEList<?>) getEdges()).basicRemove(otherEnd, msgs);
		case GexfPackage.NODE_CONTENT__PARENTS:
			return ((InternalEList<?>) getParents()).basicRemove(otherEnd, msgs);
		case GexfPackage.NODE_CONTENT__COLOR:
			return ((InternalEList<?>) getColor()).basicRemove(otherEnd, msgs);
		case GexfPackage.NODE_CONTENT__POSITION:
			return ((InternalEList<?>) getPosition()).basicRemove(otherEnd, msgs);
		case GexfPackage.NODE_CONTENT__SIZE:
			return ((InternalEList<?>) getSize()).basicRemove(otherEnd, msgs);
		case GexfPackage.NODE_CONTENT__SHAPE:
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
		case GexfPackage.NODE_CONTENT__GROUP:
			if (coreType) {
				return getGroup();
			}
			return ((FeatureMap.Internal) getGroup()).getWrapper();
		case GexfPackage.NODE_CONTENT__ATTVALUES:
			return getAttvalues();
		case GexfPackage.NODE_CONTENT__SPELLS:
			return getSpells();
		case GexfPackage.NODE_CONTENT__NODES:
			return getNodes();
		case GexfPackage.NODE_CONTENT__EDGES:
			return getEdges();
		case GexfPackage.NODE_CONTENT__PARENTS:
			return getParents();
		case GexfPackage.NODE_CONTENT__COLOR:
			return getColor();
		case GexfPackage.NODE_CONTENT__POSITION:
			return getPosition();
		case GexfPackage.NODE_CONTENT__SIZE:
			return getSize();
		case GexfPackage.NODE_CONTENT__SHAPE:
			return getShape();
		case GexfPackage.NODE_CONTENT__END:
			return getEnd();
		case GexfPackage.NODE_CONTENT__ENDOPEN:
			return getEndopen();
		case GexfPackage.NODE_CONTENT__ID:
			return getId();
		case GexfPackage.NODE_CONTENT__LABEL:
			return getLabel();
		case GexfPackage.NODE_CONTENT__PID:
			return getPid();
		case GexfPackage.NODE_CONTENT__START:
			return getStart();
		case GexfPackage.NODE_CONTENT__STARTOPEN:
			return getStartopen();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GexfPackage.NODE_CONTENT__GROUP:
			((FeatureMap.Internal) getGroup()).set(newValue);
			return;
		case GexfPackage.NODE_CONTENT__ATTVALUES:
			getAttvalues().clear();
			getAttvalues().addAll((Collection<? extends AttvaluesContent>) newValue);
			return;
		case GexfPackage.NODE_CONTENT__SPELLS:
			getSpells().clear();
			getSpells().addAll((Collection<? extends SpellsContent>) newValue);
			return;
		case GexfPackage.NODE_CONTENT__NODES:
			getNodes().clear();
			getNodes().addAll((Collection<? extends NodesContent>) newValue);
			return;
		case GexfPackage.NODE_CONTENT__EDGES:
			getEdges().clear();
			getEdges().addAll((Collection<? extends EdgesContent>) newValue);
			return;
		case GexfPackage.NODE_CONTENT__PARENTS:
			getParents().clear();
			getParents().addAll((Collection<? extends ParentsContent>) newValue);
			return;
		case GexfPackage.NODE_CONTENT__COLOR:
			getColor().clear();
			getColor().addAll((Collection<? extends ColorContent>) newValue);
			return;
		case GexfPackage.NODE_CONTENT__POSITION:
			getPosition().clear();
			getPosition().addAll((Collection<? extends PositionContent>) newValue);
			return;
		case GexfPackage.NODE_CONTENT__SIZE:
			getSize().clear();
			getSize().addAll((Collection<? extends SizeContent>) newValue);
			return;
		case GexfPackage.NODE_CONTENT__SHAPE:
			getShape().clear();
			getShape().addAll((Collection<? extends NodeShapeContent>) newValue);
			return;
		case GexfPackage.NODE_CONTENT__END:
			setEnd(newValue);
			return;
		case GexfPackage.NODE_CONTENT__ENDOPEN:
			setEndopen(newValue);
			return;
		case GexfPackage.NODE_CONTENT__ID:
			setId(newValue);
			return;
		case GexfPackage.NODE_CONTENT__LABEL:
			setLabel((String) newValue);
			return;
		case GexfPackage.NODE_CONTENT__PID:
			setPid(newValue);
			return;
		case GexfPackage.NODE_CONTENT__START:
			setStart(newValue);
			return;
		case GexfPackage.NODE_CONTENT__STARTOPEN:
			setStartopen(newValue);
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
		case GexfPackage.NODE_CONTENT__GROUP:
			getGroup().clear();
			return;
		case GexfPackage.NODE_CONTENT__ATTVALUES:
			getAttvalues().clear();
			return;
		case GexfPackage.NODE_CONTENT__SPELLS:
			getSpells().clear();
			return;
		case GexfPackage.NODE_CONTENT__NODES:
			getNodes().clear();
			return;
		case GexfPackage.NODE_CONTENT__EDGES:
			getEdges().clear();
			return;
		case GexfPackage.NODE_CONTENT__PARENTS:
			getParents().clear();
			return;
		case GexfPackage.NODE_CONTENT__COLOR:
			getColor().clear();
			return;
		case GexfPackage.NODE_CONTENT__POSITION:
			getPosition().clear();
			return;
		case GexfPackage.NODE_CONTENT__SIZE:
			getSize().clear();
			return;
		case GexfPackage.NODE_CONTENT__SHAPE:
			getShape().clear();
			return;
		case GexfPackage.NODE_CONTENT__END:
			setEnd(END_EDEFAULT);
			return;
		case GexfPackage.NODE_CONTENT__ENDOPEN:
			setEndopen(ENDOPEN_EDEFAULT);
			return;
		case GexfPackage.NODE_CONTENT__ID:
			setId(ID_EDEFAULT);
			return;
		case GexfPackage.NODE_CONTENT__LABEL:
			setLabel(LABEL_EDEFAULT);
			return;
		case GexfPackage.NODE_CONTENT__PID:
			setPid(PID_EDEFAULT);
			return;
		case GexfPackage.NODE_CONTENT__START:
			setStart(START_EDEFAULT);
			return;
		case GexfPackage.NODE_CONTENT__STARTOPEN:
			setStartopen(STARTOPEN_EDEFAULT);
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
		case GexfPackage.NODE_CONTENT__GROUP:
			return group != null && !group.isEmpty();
		case GexfPackage.NODE_CONTENT__ATTVALUES:
			return !getAttvalues().isEmpty();
		case GexfPackage.NODE_CONTENT__SPELLS:
			return !getSpells().isEmpty();
		case GexfPackage.NODE_CONTENT__NODES:
			return !getNodes().isEmpty();
		case GexfPackage.NODE_CONTENT__EDGES:
			return !getEdges().isEmpty();
		case GexfPackage.NODE_CONTENT__PARENTS:
			return !getParents().isEmpty();
		case GexfPackage.NODE_CONTENT__COLOR:
			return !getColor().isEmpty();
		case GexfPackage.NODE_CONTENT__POSITION:
			return !getPosition().isEmpty();
		case GexfPackage.NODE_CONTENT__SIZE:
			return !getSize().isEmpty();
		case GexfPackage.NODE_CONTENT__SHAPE:
			return !getShape().isEmpty();
		case GexfPackage.NODE_CONTENT__END:
			return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
		case GexfPackage.NODE_CONTENT__ENDOPEN:
			return ENDOPEN_EDEFAULT == null ? endopen != null : !ENDOPEN_EDEFAULT.equals(endopen);
		case GexfPackage.NODE_CONTENT__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
		case GexfPackage.NODE_CONTENT__LABEL:
			return LABEL_EDEFAULT == null ? label != null : !LABEL_EDEFAULT.equals(label);
		case GexfPackage.NODE_CONTENT__PID:
			return PID_EDEFAULT == null ? pid != null : !PID_EDEFAULT.equals(pid);
		case GexfPackage.NODE_CONTENT__START:
			return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
		case GexfPackage.NODE_CONTENT__STARTOPEN:
			return STARTOPEN_EDEFAULT == null ? startopen != null : !STARTOPEN_EDEFAULT.equals(startopen);
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
		result.append(", pid: ");
		result.append(pid);
		result.append(", start: ");
		result.append(start);
		result.append(", startopen: ");
		result.append(startopen);
		result.append(')');
		return result.toString();
	}

} //NodeContentImpl
