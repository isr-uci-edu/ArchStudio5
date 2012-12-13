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
public interface PrologPackage extends EPackage {
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
	 * The meta object id for the '{@link org.archstudio.prolog.xtext.prolog.impl.ClauseImpl <em>Clause</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.archstudio.prolog.xtext.prolog.impl.ClauseImpl
	 * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getClause()
	 * @generated
	 */
	int CLAUSE = 1;

	/**
	 * The feature id for the '<em><b>Predicates</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAUSE__PREDICATES = 0;

	/**
	 * The number of structural features of the '<em>Clause</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLAUSE_FEATURE_COUNT = 1;

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
	 * The meta object id for the '{@link org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl <em>Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.archstudio.prolog.xtext.prolog.impl.ExpressionImpl
	 * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getExpression()
	 * @generated
	 */
	int EXPRESSION = 3;

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
	 * The feature id for the '<em><b>Term</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION__TERM = 2;

	/**
	 * The number of structural features of the '<em>Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXPRESSION_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link org.archstudio.prolog.xtext.prolog.impl.TermImpl <em>Term</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.archstudio.prolog.xtext.prolog.impl.TermImpl
	 * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getTerm()
	 * @generated
	 */
	int TERM = 4;

	/**
	 * The feature id for the '<em><b>Atom</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM__ATOM = 0;

	/**
	 * The feature id for the '<em><b>Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM__NUMBER = 1;

	/**
	 * The feature id for the '<em><b>String</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM__STRING = 2;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM__VARIABLE = 3;

	/**
	 * The feature id for the '<em><b>List</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM__LIST = 4;

	/**
	 * The feature id for the '<em><b>Head</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM__HEAD = 5;

	/**
	 * The feature id for the '<em><b>Tail</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM__TAIL = 6;

	/**
	 * The number of structural features of the '<em>Term</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TERM_FEATURE_COUNT = 7;


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
	 * Returns the meta object for class '{@link org.archstudio.prolog.xtext.prolog.Clause <em>Clause</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Clause</em>'.
	 * @see org.archstudio.prolog.xtext.prolog.Clause
	 * @generated
	 */
	EClass getClause();

	/**
	 * Returns the meta object for the containment reference list '{@link org.archstudio.prolog.xtext.prolog.Clause#getPredicates <em>Predicates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Predicates</em>'.
	 * @see org.archstudio.prolog.xtext.prolog.Clause#getPredicates()
	 * @see #getClause()
	 * @generated
	 */
	EReference getClause_Predicates();

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
	 * Returns the meta object for the containment reference '{@link org.archstudio.prolog.xtext.prolog.Expression#getTerm <em>Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Term</em>'.
	 * @see org.archstudio.prolog.xtext.prolog.Expression#getTerm()
	 * @see #getExpression()
	 * @generated
	 */
	EReference getExpression_Term();

	/**
	 * Returns the meta object for class '{@link org.archstudio.prolog.xtext.prolog.Term <em>Term</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Term</em>'.
	 * @see org.archstudio.prolog.xtext.prolog.Term
	 * @generated
	 */
	EClass getTerm();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.Term#getAtom <em>Atom</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Atom</em>'.
	 * @see org.archstudio.prolog.xtext.prolog.Term#getAtom()
	 * @see #getTerm()
	 * @generated
	 */
	EAttribute getTerm_Atom();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.Term#getNumber <em>Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Number</em>'.
	 * @see org.archstudio.prolog.xtext.prolog.Term#getNumber()
	 * @see #getTerm()
	 * @generated
	 */
	EAttribute getTerm_Number();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.Term#getString <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String</em>'.
	 * @see org.archstudio.prolog.xtext.prolog.Term#getString()
	 * @see #getTerm()
	 * @generated
	 */
	EAttribute getTerm_String();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.Term#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Variable</em>'.
	 * @see org.archstudio.prolog.xtext.prolog.Term#getVariable()
	 * @see #getTerm()
	 * @generated
	 */
	EAttribute getTerm_Variable();

	/**
	 * Returns the meta object for the attribute '{@link org.archstudio.prolog.xtext.prolog.Term#isList <em>List</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>List</em>'.
	 * @see org.archstudio.prolog.xtext.prolog.Term#isList()
	 * @see #getTerm()
	 * @generated
	 */
	EAttribute getTerm_List();

	/**
	 * Returns the meta object for the containment reference list '{@link org.archstudio.prolog.xtext.prolog.Term#getHead <em>Head</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Head</em>'.
	 * @see org.archstudio.prolog.xtext.prolog.Term#getHead()
	 * @see #getTerm()
	 * @generated
	 */
	EReference getTerm_Head();

	/**
	 * Returns the meta object for the containment reference '{@link org.archstudio.prolog.xtext.prolog.Term#getTail <em>Tail</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Tail</em>'.
	 * @see org.archstudio.prolog.xtext.prolog.Term#getTail()
	 * @see #getTerm()
	 * @generated
	 */
	EReference getTerm_Tail();

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
	interface Literals {
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
		 * The meta object literal for the '{@link org.archstudio.prolog.xtext.prolog.impl.ClauseImpl <em>Clause</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.archstudio.prolog.xtext.prolog.impl.ClauseImpl
		 * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getClause()
		 * @generated
		 */
		EClass CLAUSE = eINSTANCE.getClause();

		/**
		 * The meta object literal for the '<em><b>Predicates</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLAUSE__PREDICATES = eINSTANCE.getClause_Predicates();

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
		 * The meta object literal for the '<em><b>Term</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EXPRESSION__TERM = eINSTANCE.getExpression_Term();

		/**
		 * The meta object literal for the '{@link org.archstudio.prolog.xtext.prolog.impl.TermImpl <em>Term</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.archstudio.prolog.xtext.prolog.impl.TermImpl
		 * @see org.archstudio.prolog.xtext.prolog.impl.PrologPackageImpl#getTerm()
		 * @generated
		 */
		EClass TERM = eINSTANCE.getTerm();

		/**
		 * The meta object literal for the '<em><b>Atom</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERM__ATOM = eINSTANCE.getTerm_Atom();

		/**
		 * The meta object literal for the '<em><b>Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERM__NUMBER = eINSTANCE.getTerm_Number();

		/**
		 * The meta object literal for the '<em><b>String</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERM__STRING = eINSTANCE.getTerm_String();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERM__VARIABLE = eINSTANCE.getTerm_Variable();

		/**
		 * The meta object literal for the '<em><b>List</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TERM__LIST = eINSTANCE.getTerm_List();

		/**
		 * The meta object literal for the '<em><b>Head</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TERM__HEAD = eINSTANCE.getTerm_Head();

		/**
		 * The meta object literal for the '<em><b>Tail</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TERM__TAIL = eINSTANCE.getTerm_Tail();

	}

} //PrologPackage
