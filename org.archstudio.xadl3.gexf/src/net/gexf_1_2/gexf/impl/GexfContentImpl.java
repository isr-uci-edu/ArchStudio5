/**
 */
package net.gexf_1_2.gexf.impl;

import net.gexf_1_2.gexf.GexfContent;
import net.gexf_1_2.gexf.GexfPackage;
import net.gexf_1_2.gexf.GraphContent;
import net.gexf_1_2.gexf.MetaContent;
import net.gexf_1_2.gexf.VersionType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Content</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link net.gexf_1_2.gexf.impl.GexfContentImpl#getMeta <em>Meta</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GexfContentImpl#getGraph <em>Graph</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GexfContentImpl#getVariant <em>Variant</em>}</li>
 * <li>{@link net.gexf_1_2.gexf.impl.GexfContentImpl#getVersion <em>Version</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GexfContentImpl extends MinimalEObjectImpl.Container implements GexfContent {
	/**
	 * The cached value of the '{@link #getMeta() <em>Meta</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getMeta()
	 * @generated
	 * @ordered
	 */
	protected MetaContent meta;

	/**
	 * The cached value of the '{@link #getGraph() <em>Graph</em>}' containment reference. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getGraph()
	 * @generated
	 * @ordered
	 */
	protected GraphContent graph;

	/**
	 * The default value of the '{@link #getVariant() <em>Variant</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVariant()
	 * @generated
	 * @ordered
	 */
	protected static final String VARIANT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getVariant() <em>Variant</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVariant()
	 * @generated
	 * @ordered
	 */
	protected String variant = VARIANT_EDEFAULT;

	/**
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final VersionType VERSION_EDEFAULT = VersionType._12;

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected VersionType version = VERSION_EDEFAULT;

	/**
	 * This is true if the Version attribute has been set. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 * @ordered
	 */
	protected boolean versionESet;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected GexfContentImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return GexfPackage.Literals.GEXF_CONTENT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public MetaContent getMeta() {
		return meta;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetMeta(MetaContent newMeta, NotificationChain msgs) {
		MetaContent oldMeta = meta;
		meta = newMeta;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GexfPackage.GEXF_CONTENT__META, oldMeta, newMeta);
			if (msgs == null) {
				msgs = notification;
			}
			else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setMeta(MetaContent newMeta) {
		if (newMeta != meta) {
			NotificationChain msgs = null;
			if (meta != null) {
				msgs = ((InternalEObject) meta).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- GexfPackage.GEXF_CONTENT__META, null, msgs);
			}
			if (newMeta != null) {
				msgs = ((InternalEObject) newMeta).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- GexfPackage.GEXF_CONTENT__META, null, msgs);
			}
			msgs = basicSetMeta(newMeta, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.GEXF_CONTENT__META, newMeta, newMeta));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public GraphContent getGraph() {
		return graph;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetGraph(GraphContent newGraph, NotificationChain msgs) {
		GraphContent oldGraph = graph;
		graph = newGraph;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					GexfPackage.GEXF_CONTENT__GRAPH, oldGraph, newGraph);
			if (msgs == null) {
				msgs = notification;
			}
			else {
				msgs.add(notification);
			}
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setGraph(GraphContent newGraph) {
		if (newGraph != graph) {
			NotificationChain msgs = null;
			if (graph != null) {
				msgs = ((InternalEObject) graph).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- GexfPackage.GEXF_CONTENT__GRAPH, null, msgs);
			}
			if (newGraph != null) {
				msgs = ((InternalEObject) newGraph).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- GexfPackage.GEXF_CONTENT__GRAPH, null, msgs);
			}
			msgs = basicSetGraph(newGraph, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.GEXF_CONTENT__GRAPH, newGraph, newGraph));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getVariant() {
		return variant;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setVariant(String newVariant) {
		String oldVariant = variant;
		variant = newVariant;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.GEXF_CONTENT__VARIANT, oldVariant,
					variant));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public VersionType getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setVersion(VersionType newVersion) {
		VersionType oldVersion = version;
		version = newVersion == null ? VERSION_EDEFAULT : newVersion;
		boolean oldVersionESet = versionESet;
		versionESet = true;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, GexfPackage.GEXF_CONTENT__VERSION, oldVersion,
					version, !oldVersionESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void unsetVersion() {
		VersionType oldVersion = version;
		boolean oldVersionESet = versionESet;
		version = VERSION_EDEFAULT;
		versionESet = false;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.UNSET, GexfPackage.GEXF_CONTENT__VERSION, oldVersion,
					VERSION_EDEFAULT, oldVersionESet));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean isSetVersion() {
		return versionESet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case GexfPackage.GEXF_CONTENT__META:
			return basicSetMeta(null, msgs);
		case GexfPackage.GEXF_CONTENT__GRAPH:
			return basicSetGraph(null, msgs);
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
		case GexfPackage.GEXF_CONTENT__META:
			return getMeta();
		case GexfPackage.GEXF_CONTENT__GRAPH:
			return getGraph();
		case GexfPackage.GEXF_CONTENT__VARIANT:
			return getVariant();
		case GexfPackage.GEXF_CONTENT__VERSION:
			return getVersion();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case GexfPackage.GEXF_CONTENT__META:
			setMeta((MetaContent) newValue);
			return;
		case GexfPackage.GEXF_CONTENT__GRAPH:
			setGraph((GraphContent) newValue);
			return;
		case GexfPackage.GEXF_CONTENT__VARIANT:
			setVariant((String) newValue);
			return;
		case GexfPackage.GEXF_CONTENT__VERSION:
			setVersion((VersionType) newValue);
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
		case GexfPackage.GEXF_CONTENT__META:
			setMeta((MetaContent) null);
			return;
		case GexfPackage.GEXF_CONTENT__GRAPH:
			setGraph((GraphContent) null);
			return;
		case GexfPackage.GEXF_CONTENT__VARIANT:
			setVariant(VARIANT_EDEFAULT);
			return;
		case GexfPackage.GEXF_CONTENT__VERSION:
			unsetVersion();
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
		case GexfPackage.GEXF_CONTENT__META:
			return meta != null;
		case GexfPackage.GEXF_CONTENT__GRAPH:
			return graph != null;
		case GexfPackage.GEXF_CONTENT__VARIANT:
			return VARIANT_EDEFAULT == null ? variant != null : !VARIANT_EDEFAULT.equals(variant);
		case GexfPackage.GEXF_CONTENT__VERSION:
			return isSetVersion();
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
		result.append(" (variant: ");
		result.append(variant);
		result.append(", version: ");
		if (versionESet) {
			result.append(version);
		}
		else {
			result.append("<unset>");
		}
		result.append(')');
		return result.toString();
	}

} //GexfContentImpl
