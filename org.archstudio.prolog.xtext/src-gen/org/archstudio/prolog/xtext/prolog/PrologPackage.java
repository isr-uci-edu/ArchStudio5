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
   * The feature id for the '<em><b>Clauses</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROGRAM__CLAUSES = 0;

  /**
   * The feature id for the '<em><b>Query</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROGRAM__QUERY = 1;

  /**
   * The number of structural features of the '<em>Program</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROGRAM_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.archstudio.prolog.xtext.prolog.impl.SingleClauseImpl <em>Single Clause</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.prolog.xtext.prolog.impl.SingleClauseImpl
   * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getSingleClause()
   * @generated
   */
  int SINGLE_CLAUSE = 1;

  /**
   * The feature id for the '<em><b>Predicates</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SINGLE_CLAUSE__PREDICATES = 0;

  /**
   * The number of structural features of the '<em>Single Clause</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SINGLE_CLAUSE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.archstudio.prolog.xtext.prolog.impl.QueryImpl <em>Query</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.prolog.xtext.prolog.impl.QueryImpl
   * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getQuery()
   * @generated
   */
  int QUERY = 2;

  /**
   * The feature id for the '<em><b>Predicates</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY__PREDICATES = 0;

  /**
   * The number of structural features of the '<em>Query</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUERY_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.archstudio.prolog.xtext.prolog.impl.PredicateImpl <em>Predicate</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.prolog.xtext.prolog.impl.PredicateImpl
   * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getPredicate()
   * @generated
   */
  int PREDICATE = 3;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREDICATE__VALUE = 0;

  /**
   * The feature id for the '<em><b>Terms</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREDICATE__TERMS = 1;

  /**
   * The number of structural features of the '<em>Predicate</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PREDICATE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.archstudio.prolog.xtext.prolog.impl.SingleTermImpl <em>Single Term</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.archstudio.prolog.xtext.prolog.impl.SingleTermImpl
   * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getSingleTerm()
   * @generated
   */
  int SINGLE_TERM = 4;

  /**
   * The feature id for the '<em><b>Numeral</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SINGLE_TERM__NUMERAL = 0;

  /**
   * The feature id for the '<em><b>Atom</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SINGLE_TERM__ATOM = 1;

  /**
   * The feature id for the '<em><b>Variable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SINGLE_TERM__VARIABLE = 2;

  /**
   * The feature id for the '<em><b>String</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SINGLE_TERM__STRING = 3;

  /**
   * The number of structural features of the '<em>Single Term</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int SINGLE_TERM_FEATURE_COUNT = 4;


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
   * Returns the meta object for the containment reference list '{@link org.archstudio.prolog.xtext.prolog.Program#getClauses <em>Clauses</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Clauses</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Program#getClauses()
   * @see #getProgram()
   * @generated
   */
  EReference getProgram_Clauses();

  /**
   * Returns the meta object for the containment reference '{@link org.archstudio.prolog.xtext.prolog.Program#getQuery <em>Query</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Query</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Program#getQuery()
   * @see #getProgram()
   * @generated
   */
  EReference getProgram_Query();

  /**
   * Returns the meta object for class '{@link org.archstudio.prolog.xtext.prolog.SingleClause <em>Single Clause</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Single Clause</em>'.
   * @see org.archstudio.prolog.xtext.prolog.SingleClause
   * @generated
   */
  EClass getSingleClause();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.prolog.xtext.prolog.SingleClause#getPredicates <em>Predicates</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Predicates</em>'.
   * @see org.archstudio.prolog.xtext.prolog.SingleClause#getPredicates()
   * @see #getSingleClause()
   * @generated
   */
  EReference getSingleClause_Predicates();

  /**
   * Returns the meta object for class '{@link org.archstudio.prolog.xtext.prolog.Query <em>Query</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Query</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Query
   * @generated
   */
  EClass getQuery();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.prolog.xtext.prolog.Query#getPredicates <em>Predicates</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Predicates</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Query#getPredicates()
   * @see #getQuery()
   * @generated
   */
  EReference getQuery_Predicates();

  /**
   * Returns the meta object for class '{@link org.archstudio.prolog.xtext.prolog.Predicate <em>Predicate</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Predicate</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Predicate
   * @generated
   */
  EClass getPredicate();

  /**
   * Returns the meta object for the containment reference '{@link org.archstudio.prolog.xtext.prolog.Predicate#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Predicate#getValue()
   * @see #getPredicate()
   * @generated
   */
  EReference getPredicate_Value();

  /**
   * Returns the meta object for the containment reference list '{@link org.archstudio.prolog.xtext.prolog.Predicate#getTerms <em>Terms</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Terms</em>'.
   * @see org.archstudio.prolog.xtext.prolog.Predicate#getTerms()
   * @see #getPredicate()
   * @generated
   */
  EReference getPredicate_Terms();

  /**
   * Returns the meta object for class '{@link org.archstudio.prolog.xtext.prolog.SingleTerm <em>Single Term</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Single Term</em>'.
   * @see org.archstudio.prolog.xtext.prolog.SingleTerm
   * @generated
   */
  EClass getSingleTerm();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.SingleTerm#getNumeral <em>Numeral</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Numeral</em>'.
   * @see org.archstudio.prolog.xtext.prolog.SingleTerm#getNumeral()
   * @see #getSingleTerm()
   * @generated
   */
  EAttribute getSingleTerm_Numeral();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.SingleTerm#getAtom <em>Atom</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Atom</em>'.
   * @see org.archstudio.prolog.xtext.prolog.SingleTerm#getAtom()
   * @see #getSingleTerm()
   * @generated
   */
  EAttribute getSingleTerm_Atom();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.SingleTerm#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Variable</em>'.
   * @see org.archstudio.prolog.xtext.prolog.SingleTerm#getVariable()
   * @see #getSingleTerm()
   * @generated
   */
  EAttribute getSingleTerm_Variable();

  /**
   * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.SingleTerm#getString <em>String</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>String</em>'.
   * @see org.archstudio.prolog.xtext.prolog.SingleTerm#getString()
   * @see #getSingleTerm()
   * @generated
   */
  EAttribute getSingleTerm_String();

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
     * The meta object literal for the '<em><b>Clauses</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROGRAM__CLAUSES = eINSTANCE.getProgram_Clauses();

    /**
     * The meta object literal for the '<em><b>Query</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROGRAM__QUERY = eINSTANCE.getProgram_Query();

    /**
     * The meta object literal for the '{@link org.archstudio.prolog.xtext.prolog.impl.SingleClauseImpl <em>Single Clause</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.prolog.xtext.prolog.impl.SingleClauseImpl
     * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getSingleClause()
     * @generated
     */
    EClass SINGLE_CLAUSE = eINSTANCE.getSingleClause();

    /**
     * The meta object literal for the '<em><b>Predicates</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference SINGLE_CLAUSE__PREDICATES = eINSTANCE.getSingleClause_Predicates();

    /**
     * The meta object literal for the '{@link org.archstudio.prolog.xtext.prolog.impl.QueryImpl <em>Query</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.prolog.xtext.prolog.impl.QueryImpl
     * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getQuery()
     * @generated
     */
    EClass QUERY = eINSTANCE.getQuery();

    /**
     * The meta object literal for the '<em><b>Predicates</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference QUERY__PREDICATES = eINSTANCE.getQuery_Predicates();

    /**
     * The meta object literal for the '{@link org.archstudio.prolog.xtext.prolog.impl.PredicateImpl <em>Predicate</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.prolog.xtext.prolog.impl.PredicateImpl
     * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getPredicate()
     * @generated
     */
    EClass PREDICATE = eINSTANCE.getPredicate();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PREDICATE__VALUE = eINSTANCE.getPredicate_Value();

    /**
     * The meta object literal for the '<em><b>Terms</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PREDICATE__TERMS = eINSTANCE.getPredicate_Terms();

    /**
     * The meta object literal for the '{@link org.archstudio.prolog.xtext.prolog.impl.SingleTermImpl <em>Single Term</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.archstudio.prolog.xtext.prolog.impl.SingleTermImpl
     * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getSingleTerm()
     * @generated
     */
    EClass SINGLE_TERM = eINSTANCE.getSingleTerm();

    /**
     * The meta object literal for the '<em><b>Numeral</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SINGLE_TERM__NUMERAL = eINSTANCE.getSingleTerm_Numeral();

    /**
     * The meta object literal for the '<em><b>Atom</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SINGLE_TERM__ATOM = eINSTANCE.getSingleTerm_Atom();

    /**
     * The meta object literal for the '<em><b>Variable</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SINGLE_TERM__VARIABLE = eINSTANCE.getSingleTerm_Variable();

    /**
     * The meta object literal for the '<em><b>String</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute SINGLE_TERM__STRING = eINSTANCE.getSingleTerm_String();

  }

} //PrologPackage
