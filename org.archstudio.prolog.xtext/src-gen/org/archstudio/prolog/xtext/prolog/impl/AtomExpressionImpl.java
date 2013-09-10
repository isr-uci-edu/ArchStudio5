/**
 */
package org.archstudio.prolog.xtext.prolog.impl;

import org.archstudio.prolog.xtext.prolog.AtomExpression;
import org.archstudio.prolog.xtext.prolog.Expression;
import org.archstudio.prolog.xtext.prolog.PrologPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Atom Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.AtomExpressionImpl#getAtom <em>Atom</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.AtomExpressionImpl#getTerms <em>Terms</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AtomExpressionImpl extends ExpressionImpl implements AtomExpression
{
  /**
   * The default value of the '{@link #getAtom() <em>Atom</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAtom()
   * @generated
   * @ordered
   */
  protected static final String ATOM_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAtom() <em>Atom</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAtom()
   * @generated
   * @ordered
   */
  protected String atom = ATOM_EDEFAULT;

  /**
   * The cached value of the '{@link #getTerms() <em>Terms</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTerms()
   * @generated
   * @ordered
   */
  protected Expression terms;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AtomExpressionImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return PrologPackage.Literals.ATOM_EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getAtom()
  {
    return atom;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAtom(String newAtom)
  {
    String oldAtom = atom;
    atom = newAtom;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.ATOM_EXPRESSION__ATOM, oldAtom, atom));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getTerms()
  {
    return terms;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTerms(Expression newTerms, NotificationChain msgs)
  {
    Expression oldTerms = terms;
    terms = newTerms;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PrologPackage.ATOM_EXPRESSION__TERMS, oldTerms, newTerms);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTerms(Expression newTerms)
  {
    if (newTerms != terms)
    {
      NotificationChain msgs = null;
      if (terms != null)
        msgs = ((InternalEObject)terms).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PrologPackage.ATOM_EXPRESSION__TERMS, null, msgs);
      if (newTerms != null)
        msgs = ((InternalEObject)newTerms).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PrologPackage.ATOM_EXPRESSION__TERMS, null, msgs);
      msgs = basicSetTerms(newTerms, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.ATOM_EXPRESSION__TERMS, newTerms, newTerms));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case PrologPackage.ATOM_EXPRESSION__TERMS:
        return basicSetTerms(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case PrologPackage.ATOM_EXPRESSION__ATOM:
        return getAtom();
      case PrologPackage.ATOM_EXPRESSION__TERMS:
        return getTerms();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case PrologPackage.ATOM_EXPRESSION__ATOM:
        setAtom((String)newValue);
        return;
      case PrologPackage.ATOM_EXPRESSION__TERMS:
        setTerms((Expression)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case PrologPackage.ATOM_EXPRESSION__ATOM:
        setAtom(ATOM_EDEFAULT);
        return;
      case PrologPackage.ATOM_EXPRESSION__TERMS:
        setTerms((Expression)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case PrologPackage.ATOM_EXPRESSION__ATOM:
        return ATOM_EDEFAULT == null ? atom != null : !ATOM_EDEFAULT.equals(atom);
      case PrologPackage.ATOM_EXPRESSION__TERMS:
        return terms != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (atom: ");
    result.append(atom);
    result.append(')');
    return result.toString();
  }

} //AtomExpressionImpl
