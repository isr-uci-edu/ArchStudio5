/**
 */
package org.archstudio.xadl3.javaimplementation_3_0.impl;

import java.util.Collection;

import org.archstudio.xadl3.implementation_3_0.impl.ImplementationImpl;
import org.archstudio.xadl3.javaimplementation_3_0.JavaClass;
import org.archstudio.xadl3.javaimplementation_3_0.JavaImplementation;
import org.archstudio.xadl3.javaimplementation_3_0.Javaimplementation_3_0Package;
import org.archstudio.xadl3.xadlcore_3_0.Extension;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Java Implementation</b></em>'. <!--
 * end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link org.archstudio.xadl3.javaimplementation_3_0.impl.JavaImplementationImpl#getMainClass <em>Main Class</em>}</li>
 * <li>{@link org.archstudio.xadl3.javaimplementation_3_0.impl.JavaImplementationImpl#getAuxClass <em>Aux Class</em>}</li>
 * <li>{@link org.archstudio.xadl3.javaimplementation_3_0.impl.JavaImplementationImpl#getExt <em>Ext</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class JavaImplementationImpl extends ImplementationImpl implements JavaImplementation {
	/**
	 * The cached value of the '{@link #getMainClass() <em>Main Class</em>}' containment reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @see #getMainClass()
	 * @generated
	 * @ordered
	 */
	protected JavaClass mainClass;

	/**
	 * The cached value of the '{@link #getAuxClass() <em>Aux Class</em>}' containment reference list. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getAuxClass()
	 * @generated
	 * @ordered
	 */
	protected EList<JavaClass> auxClass;

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
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected JavaImplementationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Javaimplementation_3_0Package.Literals.JAVA_IMPLEMENTATION;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public JavaClass getMainClass() {
		return mainClass;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NotificationChain basicSetMainClass(JavaClass newMainClass, NotificationChain msgs) {
		JavaClass oldMainClass = mainClass;
		mainClass = newMainClass;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET,
					Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__MAIN_CLASS, oldMainClass, newMainClass);
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
	public void setMainClass(JavaClass newMainClass) {
		if (newMainClass != mainClass) {
			NotificationChain msgs = null;
			if (mainClass != null) {
				msgs = ((InternalEObject) mainClass).eInverseRemove(this, EOPPOSITE_FEATURE_BASE
						- Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__MAIN_CLASS, null, msgs);
			}
			if (newMainClass != null) {
				msgs = ((InternalEObject) newMainClass).eInverseAdd(this, EOPPOSITE_FEATURE_BASE
						- Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__MAIN_CLASS, null, msgs);
			}
			msgs = basicSetMainClass(newMainClass, msgs);
			if (msgs != null) {
				msgs.dispatch();
			}
		}
		else if (eNotificationRequired()) {
			eNotify(new ENotificationImpl(this, Notification.SET,
					Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__MAIN_CLASS, newMainClass, newMainClass));
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public EList<JavaClass> getAuxClass() {
		if (auxClass == null) {
			auxClass = new EObjectContainmentEList<JavaClass>(JavaClass.class, this,
					Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__AUX_CLASS);
		}
		return auxClass;
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
					Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__EXT);
		}
		return ext;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__MAIN_CLASS:
			return basicSetMainClass(null, msgs);
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__AUX_CLASS:
			return ((InternalEList<?>) getAuxClass()).basicRemove(otherEnd, msgs);
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__EXT:
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
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__MAIN_CLASS:
			return getMainClass();
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__AUX_CLASS:
			return getAuxClass();
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__EXT:
			return getExt();
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
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__MAIN_CLASS:
			setMainClass((JavaClass) newValue);
			return;
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__AUX_CLASS:
			getAuxClass().clear();
			getAuxClass().addAll((Collection<? extends JavaClass>) newValue);
			return;
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__EXT:
			getExt().clear();
			getExt().addAll((Collection<? extends Extension>) newValue);
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
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__MAIN_CLASS:
			setMainClass((JavaClass) null);
			return;
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__AUX_CLASS:
			getAuxClass().clear();
			return;
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__EXT:
			getExt().clear();
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
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__MAIN_CLASS:
			return mainClass != null;
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__AUX_CLASS:
			return auxClass != null && !auxClass.isEmpty();
		case Javaimplementation_3_0Package.JAVA_IMPLEMENTATION__EXT:
			return ext != null && !ext.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //JavaImplementationImpl
