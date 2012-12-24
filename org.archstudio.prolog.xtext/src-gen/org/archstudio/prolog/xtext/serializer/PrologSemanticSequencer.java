package org.archstudio.prolog.xtext.serializer;

import org.archstudio.prolog.xtext.prolog.Clause;
import org.archstudio.prolog.xtext.prolog.Expression;
import org.archstudio.prolog.xtext.prolog.Program;
import org.archstudio.prolog.xtext.prolog.PrologPackage;
import org.archstudio.prolog.xtext.prolog.Query;
import org.archstudio.prolog.xtext.services.PrologGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;

import com.google.inject.Inject;

@SuppressWarnings("all")
public class PrologSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private PrologGrammarAccess grammarAccess;

	public void createSequence(EObject context, EObject semanticObject) {
		if (semanticObject.eClass().getEPackage() == PrologPackage.eINSTANCE) {
			switch (semanticObject.eClass().getClassifierID()) {
			case PrologPackage.CLAUSE:
				if (context == grammarAccess.getClauseRule()) {
					sequence_Clause(context, (Clause) semanticObject);
					return;
				}
				else {
					break;
				}
			case PrologPackage.EXPRESSION:
				if (context == grammarAccess.getExpression0Rule()) {
					sequence_Expression0(context, (Expression) semanticObject);
					return;
				}
				else if (context == grammarAccess.getExpression200Rule()) {
					sequence_Expression0_Expression200(context, (Expression) semanticObject);
					return;
				}
				else if (context == grammarAccess.getExpression400Rule()) {
					sequence_Expression0_Expression200_Expression400(context, (Expression) semanticObject);
					return;
				}
				else if (context == grammarAccess.getExpression500Rule()) {
					sequence_Expression0_Expression200_Expression400_Expression500(context, (Expression) semanticObject);
					return;
				}
				else if (context == grammarAccess.getExpression700Rule()) {
					sequence_Expression0_Expression200_Expression400_Expression500_Expression700(context,
							(Expression) semanticObject);
					return;
				}
				else if (context == grammarAccess.getExpression1100Rule()
						|| context == grammarAccess.getExpressionINFRule()) {
					sequence_Expression1100_Expression900(context, (Expression) semanticObject);
					return;
				}
				else if (context == grammarAccess.getExpression900Rule()) {
					sequence_Expression900(context, (Expression) semanticObject);
					return;
				}
				else {
					break;
				}
			case PrologPackage.PROGRAM:
				if (context == grammarAccess.getProgramRule()) {
					sequence_Program(context, (Program) semanticObject);
					return;
				}
				else {
					break;
				}
			case PrologPackage.QUERY:
				if (context == grammarAccess.getQueryRule()) {
					sequence_Query(context, (Query) semanticObject);
					return;
				}
				else {
					break;
				}
			}
		}
		if (errorAcceptor != null) {
			errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
		}
	}

	/**
	 * Constraint: (predicates+=ExpressionINF (predicates+=ExpressionINF
	 * predicates+=ExpressionINF*)?)
	 */
	protected void sequence_Clause(EObject context, Clause semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}

	/**
	 * Constraint: ( ( ( ops+=ATOM | ops+=EXPRESSION_1100 | ops+=EXPRESSION_900
	 * | ops+=EXPRESSION_700 | ops+=EXPRESSION_500 | ops+=EXPRESSION_400 |
	 * ops+=EXPRESSION_200 ) (complex?='(' (exps+=ExpressionINF
	 * exps+=ExpressionINF*)?)? ) | number=NUMBER | string=STRING |
	 * variable=VARIABLE | (list?='[' (head+=ExpressionINF head+=ExpressionINF*
	 * tail=ExpressionINF?)?) | (list?='.' head+=ExpressionINF
	 * tail=ExpressionINF) | exps+=ExpressionINF )
	 */
	protected void sequence_Expression0(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}

	/**
	 * Constraint: ( ( ( ( ops+=ATOM | ops+=EXPRESSION_1100 |
	 * ops+=EXPRESSION_900 | ops+=EXPRESSION_700 | ops+=EXPRESSION_500 |
	 * ops+=EXPRESSION_400 | ops+=EXPRESSION_200 ) (complex?='('
	 * (exps+=ExpressionINF exps+=ExpressionINF*)?)? ) | number=NUMBER |
	 * string=STRING | variable=VARIABLE | (list?='[' (head+=ExpressionINF
	 * head+=ExpressionINF* tail=ExpressionINF?)?) | (list?='.'
	 * head+=ExpressionINF tail=ExpressionINF) | exps+=ExpressionINF )
	 * (ops+=EXPRESSION_200 exps+=Expression0)* )
	 */
	protected void sequence_Expression0_Expression200(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}

	/**
	 * Constraint: ( ( ( ( ops+=ATOM | ops+=EXPRESSION_1100 |
	 * ops+=EXPRESSION_900 | ops+=EXPRESSION_700 | ops+=EXPRESSION_500 |
	 * ops+=EXPRESSION_400 | ops+=EXPRESSION_200 ) (complex?='('
	 * (exps+=ExpressionINF exps+=ExpressionINF*)?)? ) | number=NUMBER |
	 * string=STRING | variable=VARIABLE | (list?='[' (head+=ExpressionINF
	 * head+=ExpressionINF* tail=ExpressionINF?)?) | (list?='.'
	 * head+=ExpressionINF tail=ExpressionINF) | exps+=ExpressionINF )
	 * (ops+=EXPRESSION_200 exps+=Expression0)* (ops+=EXPRESSION_400
	 * exps+=Expression200)* )
	 */
	protected void sequence_Expression0_Expression200_Expression400(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}

	/**
	 * Constraint: ( ( ( ( ops+=ATOM | ops+=EXPRESSION_1100 |
	 * ops+=EXPRESSION_900 | ops+=EXPRESSION_700 | ops+=EXPRESSION_500 |
	 * ops+=EXPRESSION_400 | ops+=EXPRESSION_200 ) (complex?='('
	 * (exps+=ExpressionINF exps+=ExpressionINF*)?)? ) | number=NUMBER |
	 * string=STRING | variable=VARIABLE | (list?='[' (head+=ExpressionINF
	 * head+=ExpressionINF* tail=ExpressionINF?)?) | (list?='.'
	 * head+=ExpressionINF tail=ExpressionINF) | exps+=ExpressionINF )
	 * (ops+=EXPRESSION_200 exps+=Expression0)* (ops+=EXPRESSION_400
	 * exps+=Expression200)* (ops+=EXPRESSION_500 exps+=Expression400)* )
	 */
	protected void sequence_Expression0_Expression200_Expression400_Expression500(EObject context,
			Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}

	/**
	 * Constraint: ( ( ( ( ops+=ATOM | ops+=EXPRESSION_1100 |
	 * ops+=EXPRESSION_900 | ops+=EXPRESSION_700 | ops+=EXPRESSION_500 |
	 * ops+=EXPRESSION_400 | ops+=EXPRESSION_200 ) (complex?='('
	 * (exps+=ExpressionINF exps+=ExpressionINF*)?)? ) | number=NUMBER |
	 * string=STRING | variable=VARIABLE | (list?='[' (head+=ExpressionINF
	 * head+=ExpressionINF* tail=ExpressionINF?)?) | (list?='.'
	 * head+=ExpressionINF tail=ExpressionINF) | exps+=ExpressionINF )
	 * (ops+=EXPRESSION_200 exps+=Expression0)* (ops+=EXPRESSION_400
	 * exps+=Expression200)* (ops+=EXPRESSION_500 exps+=Expression400)*
	 * (ops+=EXPRESSION_700 exps+=Expression500)? )
	 */
	protected void sequence_Expression0_Expression200_Expression400_Expression500_Expression700(EObject context,
			Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}

	/**
	 * Constraint: (ops+=EXPRESSION_900? exps+=Expression700
	 * (ops+=EXPRESSION_1100 exps+=Expression900)*)
	 */
	protected void sequence_Expression1100_Expression900(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}

	/**
	 * Constraint: (ops+=EXPRESSION_900? exps+=Expression700)
	 */
	protected void sequence_Expression900(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}

	/**
	 * Constraint: (clauses+=Clause* query=Query?)
	 */
	protected void sequence_Program(EObject context, Program semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}

	/**
	 * Constraint: (predicates+=ExpressionINF predicates+=ExpressionINF*)
	 */
	protected void sequence_Query(EObject context, Query semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
