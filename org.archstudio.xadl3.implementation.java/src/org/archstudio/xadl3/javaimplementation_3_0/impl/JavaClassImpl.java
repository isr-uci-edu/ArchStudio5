/**
 */
package org.archstudio.xadl3.javaimplementation_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.javaimplementation_3_0.ClassPathEntry;
import org.archstudio.xadl3.javaimplementation_3_0.JavaClass;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Java Class</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.javaimplementation_3_0.impl.JavaClassImpl#getClassPathEntry <em>Class Path Entry
 * </em>}</li>
 * <li>{@link org.archstudio.xadl3.javaimplementation_3_0.impl.JavaClassImpl#getExt <em>Ext</em>}</li>
 * <li>{@link org.archstudio.xadl3.javaimplementation_3_0.impl.JavaClassImpl#getClassName <em>Class Name</em>}</li>
 * <li>{@link org.archstudio.xadl3.javaimplementation_3_0.impl.JavaClassImpl#getId <em>Id</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class JavaClassImpl extends MinimalEObjectImpl.Container implements JavaClass {
	/**
	 * The cached value of the '{@link #getClassPathEntry() <em>Class Path Entry</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getClassPathEntry()
	 * @generated
	 * @ordered
	 */
	protected EList<ClassPathEntry> classPathEntry;

	/**
	 * The cached value of the '{@link #getExt() <em>Ext</em>}' containment reference list. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getExt()
	 * @generated
	 * @ordered
	 */
	protected EList<Extension> ext;

	/**
	 * The default value of the '{@link #getClassName() <em>Class Name</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected static final String CLASS_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getClassName() <em>Class Name</em>}' attribute. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @see #getClassName()
	 * @generated
	 * @ordered
	 */
	protected String className = CLASS_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected static final String ID_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getId() <em>Id</em>}' attribute. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getId()
	 * @generated
	 * @ordered
	 */
	protected String id = ID_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected JavaClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Javaimplementation_3_0Package.Literals.JAVA_CLASS;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<ClassPathEntry> getClassPathEntry() {
		if (classPathEntry == null) {
			classPathEntry = new EObjectContainmentEList<ClassPathEntry>(ClassPathEntry.class, this,
					Javaimplementation_3_0Package.JAVA_CLASS__CLASS_PATH_ENTRY);
		}
		return classPathEntry;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<Extension> getExt() {
		if (ext == null) {
			ext = new EObjectContainmentEList<Extension>(Extension.class, this,
					Javaimplementation_3_0Package.JAVA_CLASS__EXT);
		}
		return ext;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getClassName() {
		return className;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setClassName(String newClassName) {
		String oldClassName = className;
		className = newClassName;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, Javaimplementation_3_0Package.JAVA_CLASS__CLASS_NAME,
					oldClassName, className));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public String getId() {
		return id;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void setId(String newId) {
		String oldId = id;
		id = newId;
		if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET, Javaimplementation_3_0Package.JAVA_CLASS__ID, oldId,
					id));
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
		case Javaimplementation_3_0Package.JAVA_CLASS__CLASS_PATH_ENTRY:
			return ((InternalEList<?>) getClassPathEntry()).basicRemove(otherEnd, msgs);
		case Javaimplementation_3_0Package.JAVA_CLASS__EXT:
			return ((InternalEList<?>) getExt()).basicRemove(otherEnd, msgs);
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
		case Javaimplementation_3_0Package.JAVA_CLASS__CLASS_PATH_ENTRY:
			return getClassPathEntry();
		case Javaimplementation_3_0Package.JAVA_CLASS__EXT:
			return getExt();
		case Javaimplementation_3_0Package.JAVA_CLASS__CLASS_NAME:
			return getClassName();
		case Javaimplementation_3_0Package.JAVA_CLASS__ID:
			return getId();
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
		case Javaimplementation_3_0Package.JAVA_CLASS__CLASS_PATH_ENTRY:
			getClassPathEntry().clear();
			getClassPathEntry().addAll((Collection<? extends ClassPathEntry>) newValue);
			return;
		case Javaimplementation_3_0Package.JAVA_CLASS__EXT:
			getExt().clear();
			getExt().addAll((Collection<? extends Extension>) newValue);
			return;
		case Javaimplementation_3_0Package.JAVA_CLASS__CLASS_NAME:
			setClassName((String) newValue);
			return;
		case Javaimplementation_3_0Package.JAVA_CLASS__ID:
			setId((String) newValue);
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
		case Javaimplementation_3_0Package.JAVA_CLASS__CLASS_PATH_ENTRY:
			getClassPathEntry().clear();
			return;
		case Javaimplementation_3_0Package.JAVA_CLASS__EXT:
			getExt().clear();
			return;
		case Javaimplementation_3_0Package.JAVA_CLASS__CLASS_NAME:
			setClassName(CLASS_NAME_EDEFAULT);
			return;
		case Javaimplementation_3_0Package.JAVA_CLASS__ID:
			setId(ID_EDEFAULT);
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
		case Javaimplementation_3_0Package.JAVA_CLASS__CLASS_PATH_ENTRY:
			return classPathEntry != null && !classPathEntry.isEmpty();
		case Javaimplementation_3_0Package.JAVA_CLASS__EXT:
			return ext != null && !ext.isEmpty();
		case Javaimplementation_3_0Package.JAVA_CLASS__CLASS_NAME:
			return CLASS_NAME_EDEFAULT == null ? className != null : !CLASS_NAME_EDEFAULT.equals(className);
		case Javaimplementation_3_0Package.JAVA_CLASS__ID:
			return ID_EDEFAULT == null ? id != null : !ID_EDEFAULT.equals(id);
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
		result.append(" (className: ");
		result.append(className);
		result.append(", id: ");
		result.append(id);
		result.append(')');
		return result.toString();
	}

} //JavaClassImpl
