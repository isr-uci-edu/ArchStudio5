/**
 */
package org.archstudio.prolog.xtext.prolog;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.archstudio.prolog.xtext.prolog.PrologFactory
 * @model kind="package"
 * @generated
 */
public interface PrologPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "prolog";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.archstudio.org/prolog/xtext/Prolog";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "prolog";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  PrologPackage eINSTANCE = org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl.init();

  /**
   * The meta object id for the '{@link org.archstudio.prolog.xtext.prolog.impl.ProgramImpl <em>Program</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.prolog.xtext.prolog.impl.ProgramImpl
   * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getProgram()
   * @generated
   */
  int PROGRAM = 0;

  /**
   * The feature id for the '<em><b>Exps</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROGRAM__EXPS = 0;

  /**
   * The number of structural features of the '<em>Program</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROGRAM_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl
   * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getExpression()
   * @generated
   */
  int EXPRESSION = 1;

  /**
   * The feature id for the '<em><b>Ops</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__OPS = 0;

  /**
   * The feature id for the '<em><b>Exps</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__EXPS = 1;

  /**
   * The feature id for the '<em><b>Prefix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__PREFIX = 2;

  /**
   * The feature id for the '<em><b>Variable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__VARIABLE = 3;

  /**
   * The feature id for the '<em><b>String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__STRING = 4;

  /**
   * The feature id for the '<em><b>Number</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__NUMBER = 5;

  /**
   * The feature id for the '<em><b>List</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__LIST = 6;

  /**
   * The feature id for the '<em><b>Heads</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__HEADS = 7;

  /**
   * The feature id for the '<em><b>Tails</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION__TAILS = 8;

  /**
   * The number of structural features of the '<em>Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_FEATURE_COUNT = 9;


  /**
   * Returns the meta object for class '{@link org.archstudio.prolog.xtext.prolog.Program <em>Program</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Program</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Program
   * @generated
   */
  EClass getProgram();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.prolog.xtext.prolog.Program#getExps <em>Exps</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Exps</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Program#getExps()
   * @see #getProgram()
   * @generated
   */
  EReference getProgram_Exps();

  /**
   * Returns the meta object for class '{@link org.archstudio.prolog.xtext.prolog.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Expression
   * @generated
   */
  EClass getExpression();

  /**
   * Returns the meta object for the attribute list '{@link org.archstudio.prolog.xtext.prolog.Expression#getOps <em>Ops</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>Ops</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Expression#getOps()
   * @see #getExpression()
   * @generated
   */
  EAttribute getExpression_Ops();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.prolog.xtext.prolog.Expression#getExps <em>Exps</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Exps</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Expression#getExps()
   * @see #getExpression()
   * @generated
   */
  EReference getExpression_Exps();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.Expression#isPrefix <em>Prefix</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Prefix</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Expression#isPrefix()
   * @see #getExpression()
   * @generated
   */
  EAttribute getExpression_Prefix();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.Expression#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Variable</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Expression#getVariable()
   * @see #getExpression()
   * @generated
   */
  EAttribute getExpression_Variable();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.Expression#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>String</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Expression#getString()
   * @see #getExpression()
   * @generated
   */
  EAttribute getExpression_String();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.Expression#getNumber <em>Number</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Number</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Expression#getNumber()
   * @see #getExpression()
   * @generated
   */
  EAttribute getExpression_Number();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.Expression#isList <em>List</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>List</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Expression#isList()
   * @see #getExpression()
   * @generated
   */
  EAttribute getExpression_List();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.prolog.xtext.prolog.Expression#getHeads <em>Heads</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Heads</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Expression#getHeads()
   * @see #getExpression()
   * @generated
   */
  EReference getExpression_Heads();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.prolog.xtext.prolog.Expression#getTails <em>Tails</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Tails</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Expression#getTails()
   * @see #getExpression()
   * @generated
   */
  EReference getExpression_Tails();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  PrologFactory getPrologFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.archstudio.prolog.xtext.prolog.impl.ProgramImpl <em>Program</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.prolog.xtext.prolog.impl.ProgramImpl
     * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getProgram()
     * @generated
     */
    EClass PROGRAM = eINSTANCE.getProgram();

    /**
     * The meta object literal for the '<em><b>Exps</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROGRAM__EXPS = eINSTANCE.getProgram_Exps();

    /**
     * The meta object literal for the '{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl
     * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getExpression()
     * @generated
     */
    EClass EXPRESSION = eINSTANCE.getExpression();

    /**
     * The meta object literal for the '<em><b>Ops</b></em>' attribute list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION__OPS = eINSTANCE.getExpression_Ops();

    /**
     * The meta object literal for the '<em><b>Exps</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION__EXPS = eINSTANCE.getExpression_Exps();

    /**
     * The meta object literal for the '<em><b>Prefix</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION__PREFIX = eINSTANCE.getExpression_Prefix();

    /**
     * The meta object literal for the '<em><b>Variable</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION__VARIABLE = eINSTANCE.getExpression_Variable();

    /**
     * The meta object literal for the '<em><b>String</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION__STRING = eINSTANCE.getExpression_String();

    /**
     * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION__NUMBER = eINSTANCE.getExpression_Number();

    /**
     * The meta object literal for the '<em><b>List</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION__LIST = eINSTANCE.getExpression_List();

    /**
     * The meta object literal for the '<em><b>Heads</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION__HEADS = eINSTANCE.getExpression_Heads();

    /**
     * The meta object literal for the '<em><b>Tails</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION__TAILS = eINSTANCE.getExpression_Tails();

  }

} //PrologPackage
