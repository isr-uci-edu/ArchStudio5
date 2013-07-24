/**
 */
package net.gexf_1_2.gexf.impl;

import java.util.Collection;

import net.gexf_1_2.gexf.AttributesContent;
import net.gexf_1_2.gexf.DefaultedgetypeType;
import net.gexf_1_2.gexf.EdgesContent;
import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.GraphContent;
import net.gexf_1_2.gexf.IdtypeType;
import net.gexf_1_2.gexf.ModeType;
import net.gexf_1_2.gexf.NodesContent;
import net.gexf_1_2.gexf.TimeformatType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Graph Content</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.GraphContentImpl#getGroup <em>Group</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GraphContentImpl#getAttributes <em>Attributes</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GraphContentImpl#getNodes <em>Nodes</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GraphContentImpl#getEdges <em>Edges</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GraphContentImpl#getDefaultedgetype <em>Defaultedgetype</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GraphContentImpl#getEnd <em>End</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GraphContentImpl#getEndopen <em>Endopen</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GraphContentImpl#getIdtype <em>Idtype</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GraphContentImpl#getMode <em>Mode</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GraphContentImpl#getStart <em>Start</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GraphContentImpl#getStartopen <em>Startopen</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GraphContentImpl#getTimeformat <em>Timeformat</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class GraphContentImpl extends MinimalEObjectImpl.Container implements GraphContent {
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
	 * The default value of the '{@link #getDefaultedgetype() <em>Defaultedgetype</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getDefaultedgetype()
	 * @generated
	 * @ordered
	 */
	protected static final DefaultedgetypeType DEFAULTEDGETYPE_EDEFAULT = DefaultedgetypeType.DIRECTED;

	/**
	 * The cached value of the '{@link #getDefaultedgetype() <em>Defaultedgetype</em>}' attribute. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getDefaultedgetype()
	 * @generated
	 * @ordered
	 */
	protected DefaultedgetypeType defaultedgetype = DEFAULTEDGETYPE_EDEFAULT;

	/**
	 * This is true if the Defaultedgetype attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean defaultedgetypeESet;

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
	 * The default value of the '{@link #getIdtype() <em>Idtype</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIdtype()
	 * @generated
	 * @ordered
	 */
	protected static final IdtypeType IDTYPE_EDEFAULT = IdtypeType.INTEGER;

	/**
	 * The cached value of the '{@link #getIdtype() <em>Idtype</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getIdtype()
	 * @generated
	 * @ordered
	 */
	protected IdtypeType idtype = IDTYPE_EDEFAULT;

	/**
	 * This is true if the Idtype attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean idtypeESet;

	/**
	 * The default value of the '{@link #getMode() <em>Mode</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected static final ModeType MODE_EDEFAULT = ModeType.STATIC;

	/**
	 * The cached value of the '{@link #getMode() <em>Mode</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc
	 * -->
	 * 
	 * @see #getMode()
	 * @generated
	 * @ordered
	 */
	protected ModeType mode = MODE_EDEFAULT;

	/**
	 * This is true if the Mode attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean modeESet;

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
	 * The default value of the '{@link #getTimeformat() <em>Timeformat</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getTimeformat()
	 * @generated
	 * @ordered
	 */
	protected static final TimeformatType TIMEFORMAT_EDEFAULT = TimeformatType.INTEGER;

	/**
	 * The cached value of the '{@link #getTimeformat() <em>Timeformat</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getTimeformat()
	 * @generated
	 * @ordered
	 */
	protected TimeformatType timeformat = TIMEFORMAT_EDEFAULT;

	/**
	 * This is true if the Timeformat attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean timeformatESet;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected GraphContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GexfPackage.Literals.GRAPH_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public FeatureMap getGroup() {
		if (group == null) {
			group = new BasicFeatureMap(this, GexfPackage.GRAPH_CONTENT__GROUP);
		}
		return group;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<AttributesContent> getAttributes() {
		return getGroup().list(GexfPackage.Literals.GRAPH_CONTENT__ATTRIBUTES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<NodesContent> getNodes() {
		return getGroup().list(GexfPackage.Literals.GRAPH_CONTENT__NODES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<EdgesContent> getEdges() {
		return getGroup().list(GexfPackage.Literals.GRAPH_CONTENT__EDGES);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public DefaultedgetypeType getDefaultedgetype() {
		return defaultedgetype;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setDefaultedgetype(DefaultedgetypeType newDefaultedgetype) {
		DefaultedgetypeType oldDefaultedgetype = defaultedgetype;
		defaultedgetype = newDefaultedgetype == null ? DEFAULTEDGETYPE_EDEFAULT : newDefaultedgetype;
		boolean oldDefaultedgetypeESet = defaultedgetypeESet;
		defaultedgetypeESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.GRAPH_CONTENT__DEFAULTEDGETYPE,
					oldDefaultedgetype, defaultedgetype, !oldDefaultedgetypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetDefaultedgetype() {
		DefaultedgetypeType oldDefaultedgetype = defaultedgetype;
		boolean oldDefaultedgetypeESet = defaultedgetypeESet;
		defaultedgetype = DEFAULTEDGETYPE_EDEFAULT;
		defaultedgetypeESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, GexfPackage.GRAPH_CONTENT__DEFAULTEDGETYPE,
					oldDefaultedgetype, DEFAULTEDGETYPE_EDEFAULT, oldDefaultedgetypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetDefaultedgetype() {
		return defaultedgetypeESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.GRAPH_CONTENT__END, oldEnd, end));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.GRAPH_CONTENT__ENDOPEN, oldEndopen,
					endopen));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public IdtypeType getIdtype() {
		return idtype;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setIdtype(IdtypeType newIdtype) {
		IdtypeType oldIdtype = idtype;
		idtype = newIdtype == null ? IDTYPE_EDEFAULT : newIdtype;
		boolean oldIdtypeESet = idtypeESet;
		idtypeESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.GRAPH_CONTENT__IDTYPE, oldIdtype, idtype,
					!oldIdtypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetIdtype() {
		IdtypeType oldIdtype = idtype;
		boolean oldIdtypeESet = idtypeESet;
		idtype = IDTYPE_EDEFAULT;
		idtypeESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, GexfPackage.GRAPH_CONTENT__IDTYPE, oldIdtype,
					IDTYPE_EDEFAULT, oldIdtypeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetIdtype() {
		return idtypeESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public ModeType getMode() {
		return mode;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMode(ModeType newMode) {
		ModeType oldMode = mode;
		mode = newMode == null ? MODE_EDEFAULT : newMode;
		boolean oldModeESet = modeESet;
		modeESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.GRAPH_CONTENT__MODE, oldMode, mode,
					!oldModeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetMode() {
		ModeType oldMode = mode;
		boolean oldModeESet = modeESet;
		mode = MODE_EDEFAULT;
		modeESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, GexfPackage.GRAPH_CONTENT__MODE, oldMode,
					MODE_EDEFAULT, oldModeESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetMode() {
		return modeESet;
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
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.GRAPH_CONTENT__START, oldStart, start));
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
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.GRAPH_CONTENT__STARTOPEN, oldStartopen,
					startopen));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public TimeformatType getTimeformat() {
		return timeformat;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setTimeformat(TimeformatType newTimeformat) {
		TimeformatType oldTimeformat = timeformat;
		timeformat = newTimeformat == null ? TIMEFORMAT_EDEFAULT : newTimeformat;
		boolean oldTimeformatESet = timeformatESet;
		timeformatESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.GRAPH_CONTENT__TIMEFORMAT, oldTimeformat,
					timeformat, !oldTimeformatESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetTimeformat() {
		TimeformatType oldTimeformat = timeformat;
		boolean oldTimeformatESet = timeformatESet;
		timeformat = TIMEFORMAT_EDEFAULT;
		timeformatESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, GexfPackage.GRAPH_CONTENT__TIMEFORMAT,
					oldTimeformat, TIMEFORMAT_EDEFAULT, oldTimeformatESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetTimeformat() {
		return timeformatESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GexfPackage.GRAPH_CONTENT__GROUP:
			return ((InternalEList<?>) getGroup()).basicRemove(otherEnd, msgs);
		case GexfPackage.GRAPH_CONTENT__ATTRIBUTES:
			return ((InternalEList<?>) getAttributes()).basicRemove(otherEnd, msgs);
		case GexfPackage.GRAPH_CONTENT__NODES:
			return ((InternalEList<?>) getNodes()).basicRemove(otherEnd, msgs);
		case GexfPackage.GRAPH_CONTENT__EDGES:
			return ((InternalEList<?>) getEdges()).basicRemove(otherEnd, msgs);
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
		case GexfPackage.GRAPH_CONTENT__GROUP:
			if (coreType) {
				return getGroup();
			}
			return ((FeatureMap.Internal) getGroup()).getWrapper();
		case GexfPackage.GRAPH_CONTENT__ATTRIBUTES:
			return getAttributes();
		case GexfPackage.GRAPH_CONTENT__NODES:
			return getNodes();
		case GexfPackage.GRAPH_CONTENT__EDGES:
			return getEdges();
		case GexfPackage.GRAPH_CONTENT__DEFAULTEDGETYPE:
			return getDefaultedgetype();
		case GexfPackage.GRAPH_CONTENT__END:
			return getEnd();
		case GexfPackage.GRAPH_CONTENT__ENDOPEN:
			return getEndopen();
		case GexfPackage.GRAPH_CONTENT__IDTYPE:
			return getIdtype();
		case GexfPackage.GRAPH_CONTENT__MODE:
			return getMode();
		case GexfPackage.GRAPH_CONTENT__START:
			return getStart();
		case GexfPackage.GRAPH_CONTENT__STARTOPEN:
			return getStartopen();
		case GexfPackage.GRAPH_CONTENT__TIMEFORMAT:
			return getTimeformat();
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
		case GexfPackage.GRAPH_CONTENT__GROUP:
			((FeatureMap.Internal) getGroup()).set(newValue);
			return;
		case GexfPackage.GRAPH_CONTENT__ATTRIBUTES:
			getAttributes().clear();
			getAttributes().addAll((Collection<? extends AttributesContent>) newValue);
			return;
		case GexfPackage.GRAPH_CONTENT__NODES:
			getNodes().clear();
			getNodes().addAll((Collection<? extends NodesContent>) newValue);
			return;
		case GexfPackage.GRAPH_CONTENT__EDGES:
			getEdges().clear();
			getEdges().addAll((Collection<? extends EdgesContent>) newValue);
			return;
		case GexfPackage.GRAPH_CONTENT__DEFAULTEDGETYPE:
			setDefaultedgetype((DefaultedgetypeType) newValue);
			return;
		case GexfPackage.GRAPH_CONTENT__END:
			setEnd(newValue);
			return;
		case GexfPackage.GRAPH_CONTENT__ENDOPEN:
			setEndopen(newValue);
			return;
		case GexfPackage.GRAPH_CONTENT__IDTYPE:
			setIdtype((IdtypeType) newValue);
			return;
		case GexfPackage.GRAPH_CONTENT__MODE:
			setMode((ModeType) newValue);
			return;
		case GexfPackage.GRAPH_CONTENT__START:
			setStart(newValue);
			return;
		case GexfPackage.GRAPH_CONTENT__STARTOPEN:
			setStartopen(newValue);
			return;
		case GexfPackage.GRAPH_CONTENT__TIMEFORMAT:
			setTimeformat((TimeformatType) newValue);
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
		case GexfPackage.GRAPH_CONTENT__GROUP:
			getGroup().clear();
			return;
		case GexfPackage.GRAPH_CONTENT__ATTRIBUTES:
			getAttributes().clear();
			return;
		case GexfPackage.GRAPH_CONTENT__NODES:
			getNodes().clear();
			return;
		case GexfPackage.GRAPH_CONTENT__EDGES:
			getEdges().clear();
			return;
		case GexfPackage.GRAPH_CONTENT__DEFAULTEDGETYPE:
			unsetDefaultedgetype();
			return;
		case GexfPackage.GRAPH_CONTENT__END:
			setEnd(END_EDEFAULT);
			return;
		case GexfPackage.GRAPH_CONTENT__ENDOPEN:
			setEndopen(ENDOPEN_EDEFAULT);
			return;
		case GexfPackage.GRAPH_CONTENT__IDTYPE:
			unsetIdtype();
			return;
		case GexfPackage.GRAPH_CONTENT__MODE:
			unsetMode();
			return;
		case GexfPackage.GRAPH_CONTENT__START:
			setStart(START_EDEFAULT);
			return;
		case GexfPackage.GRAPH_CONTENT__STARTOPEN:
			setStartopen(STARTOPEN_EDEFAULT);
			return;
		case GexfPackage.GRAPH_CONTENT__TIMEFORMAT:
			unsetTimeformat();
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
		case GexfPackage.GRAPH_CONTENT__GROUP:
			return group != null && !group.isEmpty();
		case GexfPackage.GRAPH_CONTENT__ATTRIBUTES:
			return !getAttributes().isEmpty();
		case GexfPackage.GRAPH_CONTENT__NODES:
			return !getNodes().isEmpty();
		case GexfPackage.GRAPH_CONTENT__EDGES:
			return !getEdges().isEmpty();
		case GexfPackage.GRAPH_CONTENT__DEFAULTEDGETYPE:
			return isSetDefaultedgetype();
		case GexfPackage.GRAPH_CONTENT__END:
			return END_EDEFAULT == null ? end != null : !END_EDEFAULT.equals(end);
		case GexfPackage.GRAPH_CONTENT__ENDOPEN:
			return ENDOPEN_EDEFAULT == null ? endopen != null : !ENDOPEN_EDEFAULT.equals(endopen);
		case GexfPackage.GRAPH_CONTENT__IDTYPE:
			return isSetIdtype();
		case GexfPackage.GRAPH_CONTENT__MODE:
			return isSetMode();
		case GexfPackage.GRAPH_CONTENT__START:
			return START_EDEFAULT == null ? start != null : !START_EDEFAULT.equals(start);
		case GexfPackage.GRAPH_CONTENT__STARTOPEN:
			return STARTOPEN_EDEFAULT == null ? startopen != null : !STARTOPEN_EDEFAULT.equals(startopen);
		case GexfPackage.GRAPH_CONTENT__TIMEFORMAT:
			return isSetTimeformat();
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
		result.append(", defaultedgetype: ");
		if (defaultedgetypeESet) {
			result.append(defaultedgetype);
		}
		else {
			result.append("<unset>");
		}
		result.append(", end: ");
		result.append(end);
		result.append(", endopen: ");
		result.append(endopen);
		result.append(", idtype: ");
		if (idtypeESet) {
			result.append(idtype);
		}
		else {
			result.append("<unset>");
		}
		result.append(", mode: ");
		if (modeESet) {
			result.append(mode);
		}
		else {
			result.append("<unset>");
		}
		result.append(", start: ");
		result.append(start);
		result.append(", startopen: ");
		result.append(startopen);
		result.append(", timeformat: ");
		if (timeformatESet) {
			result.append(timeformat);
		}
		else {
			result.append("<unset>");
		}
		result.append(')');
		return result.toString();
	}

} //GraphContentImpl
