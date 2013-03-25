/**
 */
package org.archstudio.prolog.xtext.prolog.impl;

import java.util.Collection;

import org.archstudio.prolog.xtext.prolog.Expression;
import org.archstudio.prolog.xtext.prolog.PrologPackage;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Expression</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getExps <em>Exps</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getOps <em>Ops</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getAtom <em>Atom</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#isPrefix <em>Prefix</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getTerms <em>Terms</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getVariable <em>Variable</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getString <em>String</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getNumber <em>Number</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#isList <em>List</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getHead <em>Head</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getTail <em>Tail</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#isParen <em>Paren</em>}</li>
 *   <li>{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl#getSub <em>Sub</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExpressionImpl extends MinimalEObjectImpl.Container implements Expression
{
  /**
   * The cached value of the '{@link #getExps() <em>Exps</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getExps()
   * @generated
   * @ordered
   */
  protected EList<Expression> exps;

  /**
   * The cached value of the '{@link #getOps() <em>Ops</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOps()
   * @generated
   * @ordered
   */
  protected EList<String> ops;

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
   * The default value of the '{@link #isPrefix() <em>Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPrefix()
   * @generated
   * @ordered
   */
  protected static final boolean PREFIX_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isPrefix() <em>Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPrefix()
   * @generated
   * @ordered
   */
  protected boolean prefix = PREFIX_EDEFAULT;

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
   * The default value of the '{@link #getVariable() <em>Variable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariable()
   * @generated
   * @ordered
   */
  protected static final String VARIABLE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getVariable() <em>Variable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariable()
   * @generated
   * @ordered
   */
  protected String variable = VARIABLE_EDEFAULT;

  /**
   * The default value of the '{@link #getString() <em>String</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getString()
   * @generated
   * @ordered
   */
  protected static final String STRING_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getString() <em>String</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getString()
   * @generated
   * @ordered
   */
  protected String string = STRING_EDEFAULT;

  /**
   * The default value of the '{@link #getNumber() <em>Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNumber()
   * @generated
   * @ordered
   */
  protected static final String NUMBER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNumber() <em>Number</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNumber()
   * @generated
   * @ordered
   */
  protected String number = NUMBER_EDEFAULT;

  /**
   * The default value of the '{@link #isList() <em>List</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isList()
   * @generated
   * @ordered
   */
  protected static final boolean LIST_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isList() <em>List</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isList()
   * @generated
   * @ordered
   */
  protected boolean list = LIST_EDEFAULT;

  /**
   * The cached value of the '{@link #getHead() <em>Head</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getHead()
   * @generated
   * @ordered
   */
  protected Expression head;

  /**
   * The cached value of the '{@link #getTail() <em>Tail</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTail()
   * @generated
   * @ordered
   */
  protected Expression tail;

  /**
   * The default value of the '{@link #isParen() <em>Paren</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isParen()
   * @generated
   * @ordered
   */
  protected static final boolean PAREN_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isParen() <em>Paren</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isParen()
   * @generated
   * @ordered
   */
  protected boolean paren = PAREN_EDEFAULT;

  /**
   * The cached value of the '{@link #getSub() <em>Sub</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSub()
   * @generated
   * @ordered
   */
  protected Expression sub;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ExpressionImpl()
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
    return PrologPackage.Literals.EXPRESSION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Expression> getExps()
  {
    if (exps == null)
    {
      exps = new EObjectContainmentEList<Expression>(Expression.class, this, PrologPackage.EXPRESSION__EXPS);
    }
    return exps;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<String> getOps()
  {
    if (ops == null)
    {
      ops = new EDataTypeEList<String>(String.class, this, PrologPackage.EXPRESSION__OPS);
    }
    return ops;
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
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__ATOM, oldAtom, atom));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isPrefix()
  {
    return prefix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPrefix(boolean newPrefix)
  {
    boolean oldPrefix = prefix;
    prefix = newPrefix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__PREFIX, oldPrefix, prefix));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__TERMS, oldTerms, newTerms);
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
        msgs = ((InternalEObject)terms).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PrologPackage.EXPRESSION__TERMS, null, msgs);
      if (newTerms != null)
        msgs = ((InternalEObject)newTerms).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PrologPackage.EXPRESSION__TERMS, null, msgs);
      msgs = basicSetTerms(newTerms, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__TERMS, newTerms, newTerms));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getVariable()
  {
    return variable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVariable(String newVariable)
  {
    String oldVariable = variable;
    variable = newVariable;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__VARIABLE, oldVariable, variable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getString()
  {
    return string;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setString(String newString)
  {
    String oldString = string;
    string = newString;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__STRING, oldString, string));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNumber()
  {
    return number;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNumber(String newNumber)
  {
    String oldNumber = number;
    number = newNumber;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__NUMBER, oldNumber, number));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isList()
  {
    return list;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setList(boolean newList)
  {
    boolean oldList = list;
    list = newList;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__LIST, oldList, list));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getHead()
  {
    return head;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetHead(Expression newHead, NotificationChain msgs)
  {
    Expression oldHead = head;
    head = newHead;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__HEAD, oldHead, newHead);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setHead(Expression newHead)
  {
    if (newHead != head)
    {
      NotificationChain msgs = null;
      if (head != null)
        msgs = ((InternalEObject)head).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PrologPackage.EXPRESSION__HEAD, null, msgs);
      if (newHead != null)
        msgs = ((InternalEObject)newHead).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PrologPackage.EXPRESSION__HEAD, null, msgs);
      msgs = basicSetHead(newHead, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__HEAD, newHead, newHead));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getTail()
  {
    return tail;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTail(Expression newTail, NotificationChain msgs)
  {
    Expression oldTail = tail;
    tail = newTail;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__TAIL, oldTail, newTail);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTail(Expression newTail)
  {
    if (newTail != tail)
    {
      NotificationChain msgs = null;
      if (tail != null)
        msgs = ((InternalEObject)tail).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PrologPackage.EXPRESSION__TAIL, null, msgs);
      if (newTail != null)
        msgs = ((InternalEObject)newTail).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PrologPackage.EXPRESSION__TAIL, null, msgs);
      msgs = basicSetTail(newTail, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__TAIL, newTail, newTail));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isParen()
  {
    return paren;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setParen(boolean newParen)
  {
    boolean oldParen = paren;
    paren = newParen;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__PAREN, oldParen, paren));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Expression getSub()
  {
    return sub;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSub(Expression newSub, NotificationChain msgs)
  {
    Expression oldSub = sub;
    sub = newSub;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__SUB, oldSub, newSub);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSub(Expression newSub)
  {
    if (newSub != sub)
    {
      NotificationChain msgs = null;
      if (sub != null)
        msgs = ((InternalEObject)sub).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PrologPackage.EXPRESSION__SUB, null, msgs);
      if (newSub != null)
        msgs = ((InternalEObject)newSub).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PrologPackage.EXPRESSION__SUB, null, msgs);
      msgs = basicSetSub(newSub, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PrologPackage.EXPRESSION__SUB, newSub, newSub));
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
      case PrologPackage.EXPRESSION__EXPS:
        return ((InternalEList<?>)getExps()).basicRemove(otherEnd, msgs);
      case PrologPackage.EXPRESSION__TERMS:
        return basicSetTerms(null, msgs);
      case PrologPackage.EXPRESSION__HEAD:
        return basicSetHead(null, msgs);
      case PrologPackage.EXPRESSION__TAIL:
        return basicSetTail(null, msgs);
      case PrologPackage.EXPRESSION__SUB:
        return basicSetSub(null, msgs);
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
      case PrologPackage.EXPRESSION__EXPS:
        return getExps();
      case PrologPackage.EXPRESSION__OPS:
        return getOps();
      case PrologPackage.EXPRESSION__ATOM:
        return getAtom();
      case PrologPackage.EXPRESSION__PREFIX:
        return isPrefix();
      case PrologPackage.EXPRESSION__TERMS:
        return getTerms();
      case PrologPackage.EXPRESSION__VARIABLE:
        return getVariable();
      case PrologPackage.EXPRESSION__STRING:
        return getString();
      case PrologPackage.EXPRESSION__NUMBER:
        return getNumber();
      case PrologPackage.EXPRESSION__LIST:
        return isList();
      case PrologPackage.EXPRESSION__HEAD:
        return getHead();
      case PrologPackage.EXPRESSION__TAIL:
        return getTail();
      case PrologPackage.EXPRESSION__PAREN:
        return isParen();
      case PrologPackage.EXPRESSION__SUB:
        return getSub();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case PrologPackage.EXPRESSION__EXPS:
        getExps().clear();
        getExps().addAll((Collection<? extends Expression>)newValue);
        return;
      case PrologPackage.EXPRESSION__OPS:
        getOps().clear();
        getOps().addAll((Collection<? extends String>)newValue);
        return;
      case PrologPackage.EXPRESSION__ATOM:
        setAtom((String)newValue);
        return;
      case PrologPackage.EXPRESSION__PREFIX:
        setPrefix((Boolean)newValue);
        return;
      case PrologPackage.EXPRESSION__TERMS:
        setTerms((Expression)newValue);
        return;
      case PrologPackage.EXPRESSION__VARIABLE:
        setVariable((String)newValue);
        return;
      case PrologPackage.EXPRESSION__STRING:
        setString((String)newValue);
        return;
      case PrologPackage.EXPRESSION__NUMBER:
        setNumber((String)newValue);
        return;
      case PrologPackage.EXPRESSION__LIST:
        setList((Boolean)newValue);
        return;
      case PrologPackage.EXPRESSION__HEAD:
        setHead((Expression)newValue);
        return;
      case PrologPackage.EXPRESSION__TAIL:
        setTail((Expression)newValue);
        return;
      case PrologPackage.EXPRESSION__PAREN:
        setParen((Boolean)newValue);
        return;
      case PrologPackage.EXPRESSION__SUB:
        setSub((Expression)newValue);
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
      case PrologPackage.EXPRESSION__EXPS:
        getExps().clear();
        return;
      case PrologPackage.EXPRESSION__OPS:
        getOps().clear();
        return;
      case PrologPackage.EXPRESSION__ATOM:
        setAtom(ATOM_EDEFAULT);
        return;
      case PrologPackage.EXPRESSION__PREFIX:
        setPrefix(PREFIX_EDEFAULT);
        return;
      case PrologPackage.EXPRESSION__TERMS:
        setTerms((Expression)null);
        return;
      case PrologPackage.EXPRESSION__VARIABLE:
        setVariable(VARIABLE_EDEFAULT);
        return;
      case PrologPackage.EXPRESSION__STRING:
        setString(STRING_EDEFAULT);
        return;
      case PrologPackage.EXPRESSION__NUMBER:
        setNumber(NUMBER_EDEFAULT);
        return;
      case PrologPackage.EXPRESSION__LIST:
        setList(LIST_EDEFAULT);
        return;
      case PrologPackage.EXPRESSION__HEAD:
        setHead((Expression)null);
        return;
      case PrologPackage.EXPRESSION__TAIL:
        setTail((Expression)null);
        return;
      case PrologPackage.EXPRESSION__PAREN:
        setParen(PAREN_EDEFAULT);
        return;
      case PrologPackage.EXPRESSION__SUB:
        setSub((Expression)null);
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
      case PrologPackage.EXPRESSION__EXPS:
        return exps != null && !exps.isEmpty();
      case PrologPackage.EXPRESSION__OPS:
        return ops != null && !ops.isEmpty();
      case PrologPackage.EXPRESSION__ATOM:
        return ATOM_EDEFAULT == null ? atom != null : !ATOM_EDEFAULT.equals(atom);
      case PrologPackage.EXPRESSION__PREFIX:
        return prefix != PREFIX_EDEFAULT;
      case PrologPackage.EXPRESSION__TERMS:
        return terms != null;
      case PrologPackage.EXPRESSION__VARIABLE:
        return VARIABLE_EDEFAULT == null ? variable != null : !VARIABLE_EDEFAULT.equals(variable);
      case PrologPackage.EXPRESSION__STRING:
        return STRING_EDEFAULT == null ? string != null : !STRING_EDEFAULT.equals(string);
      case PrologPackage.EXPRESSION__NUMBER:
        return NUMBER_EDEFAULT == null ? number != null : !NUMBER_EDEFAULT.equals(number);
      case PrologPackage.EXPRESSION__LIST:
        return list != LIST_EDEFAULT;
      case PrologPackage.EXPRESSION__HEAD:
        return head != null;
      case PrologPackage.EXPRESSION__TAIL:
        return tail != null;
      case PrologPackage.EXPRESSION__PAREN:
        return paren != PAREN_EDEFAULT;
      case PrologPackage.EXPRESSION__SUB:
        return sub != null;
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
    result.append(" (ops: ");
    result.append(ops);
    result.append(", atom: ");
    result.append(atom);
    result.append(", prefix: ");
    result.append(prefix);
    result.append(", variable: ");
    result.append(variable);
    result.append(", string: ");
    result.append(string);
    result.append(", number: ");
    result.append(number);
    result.append(", list: ");
    result.append(list);
    result.append(", paren: ");
    result.append(paren);
    result.append(')');
    return result.toString();
  }

} //ExpressionImpl
