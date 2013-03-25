package org.archstudio.prolog.xtext.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.archstudio.prolog.xtext.prolog.Expression;
import org.archstudio.prolog.xtext.prolog.Program;
import org.archstudio.prolog.xtext.prolog.PrologPackage;
import org.archstudio.prolog.xtext.services.PrologGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;

@SuppressWarnings("all")
public class PrologSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private PrologGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == PrologPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case PrologPackage.EXPRESSION:
				if(context == grammarAccess.getExpression0Rule()) {
					sequence_Expression0(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression1000Rule()) {
					sequence_Expression1000(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression1050Rule()) {
					sequence_Expression1050(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression1100Rule()) {
					sequence_Expression1100(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression1200Rule() ||
				   context == grammarAccess.getExpressionINFRule()) {
					sequence_Expression1200(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression1200fxRule()) {
					sequence_Expression1200fx(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression200Rule()) {
					sequence_Expression200(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression400Rule()) {
					sequence_Expression400(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression500Rule()) {
					sequence_Expression500(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression600Rule()) {
					sequence_Expression600(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression700Rule()) {
					sequence_Expression700(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression900fxRule()) {
					sequence_Expression900fx(context, (Expression) semanticObject); 
					return; 
				}
				else break;
			case PrologPackage.PROGRAM:
				if(context == grammarAccess.getProgramRule()) {
					sequence_Program(context, (Program) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (
	 *         (
	 *             (
	 *                 atom='.' | 
	 *                 atom='!' | 
	 *                 atom=ATOM | 
	 *                 atom=EXPRESSION_1200 | 
	 *                 atom=EXPRESSION_1200FX | 
	 *                 atom=EXPRESSION_1100 | 
	 *                 atom=EXPRESSION_1050 | 
	 *                 atom=EXPRESSION_1000 | 
	 *                 atom=EXPRESSION_900FX | 
	 *                 atom=EXPRESSION_700 | 
	 *                 atom=EXPRESSION_600 | 
	 *                 atom=EXPRESSION_500 | 
	 *                 atom=EXPRESSION_400 | 
	 *                 atom=EXPRESSION_200
	 *             ) 
	 *             (prefix?='(' terms=ExpressionINF)?
	 *         ) | 
	 *         variable=VARIABLE | 
	 *         string=STRING | 
	 *         number=NUMBER | 
	 *         (list?='[' (head=ExpressionINF tail=ExpressionINF?)?) | 
	 *         (paren?='(' sub=ExpressionINF)
	 *     )
	 */
	protected void sequence_Expression0(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (exps+=Expression900fx (ops+=EXPRESSION_1000 exps+=Expression900fx)*)
	 */
	protected void sequence_Expression1000(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (exps+=Expression1000 (ops+=EXPRESSION_1050 exps+=Expression1000)?)
	 */
	protected void sequence_Expression1050(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (exps+=Expression1050 (ops+=EXPRESSION_1100 exps+=Expression1050)*)
	 */
	protected void sequence_Expression1100(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (exps+=Expression1200fx (ops+=EXPRESSION_1200 exps+=Expression1200fx)?)
	 */
	protected void sequence_Expression1200(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ops+=EXPRESSION_1200FX? exps+=Expression1100)
	 */
	protected void sequence_Expression1200fx(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (exps+=Expression0 (ops+=EXPRESSION_200 exps+=Expression0)?)
	 */
	protected void sequence_Expression200(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (exps+=Expression200 (ops+=EXPRESSION_400 exps+=Expression200)?)
	 */
	protected void sequence_Expression400(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (exps+=Expression400 (ops+=EXPRESSION_500 exps+=Expression400)?)
	 */
	protected void sequence_Expression500(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (exps+=Expression500 (ops+=EXPRESSION_600 exps+=Expression500)?)
	 */
	protected void sequence_Expression600(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (exps+=Expression600 (ops+=EXPRESSION_700 exps+=Expression600)?)
	 */
	protected void sequence_Expression700(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (ops+=EXPRESSION_900FX? exps+=Expression700)
	 */
	protected void sequence_Expression900fx(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     exps+=ExpressionINF+
	 */
	protected void sequence_Program(EObject context, Program semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
