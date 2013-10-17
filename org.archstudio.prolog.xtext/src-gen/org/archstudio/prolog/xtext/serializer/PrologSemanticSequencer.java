package org.archstudio.prolog.xtext.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import org.archstudio.prolog.xtext.prolog.AtomExpression;
import org.archstudio.prolog.xtext.prolog.Expression;
import org.archstudio.prolog.xtext.prolog.ListExpression;
import org.archstudio.prolog.xtext.prolog.Model;
import org.archstudio.prolog.xtext.prolog.NumberExpression;
import org.archstudio.prolog.xtext.prolog.PrologPackage;
import org.archstudio.prolog.xtext.prolog.StringExpression;
import org.archstudio.prolog.xtext.prolog.UnaryExpression;
import org.archstudio.prolog.xtext.prolog.VariableExpression;
import org.archstudio.prolog.xtext.services.PrologGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class PrologSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private PrologGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == PrologPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case PrologPackage.ATOM_EXPRESSION:
				if(context == grammarAccess.getExpression0Rule() ||
				   context == grammarAccess.getExpression200xfxRule() ||
				   context == grammarAccess.getExpression200xfxAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression200xfyRule() ||
				   context == grammarAccess.getExpression200xfyAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression400yfxRule() ||
				   context == grammarAccess.getExpression400yfxAccess().getExpressionLeftAction_1_0()) {
					sequence_Expression0(context, (AtomExpression) semanticObject); 
					return; 
				}
				else break;
			case PrologPackage.EXPRESSION:
				if(context == grammarAccess.getExpression1100xfyRule()) {
					sequence_Expression1000xfy_Expression1050xfy_Expression1100xfy_Expression954xfy(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression1050xfyRule() ||
				   context == grammarAccess.getExpression1100xfyAccess().getExpressionLeftAction_1_0()) {
					sequence_Expression1000xfy_Expression1050xfy_Expression954xfy(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression1000xfyRule() ||
				   context == grammarAccess.getExpression1050xfyAccess().getExpressionLeftAction_1_0()) {
					sequence_Expression1000xfy_Expression954xfy(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression0Rule() ||
				   context == grammarAccess.getExpression1200xfxRule() ||
				   context == grammarAccess.getExpression200xfyAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpressionInfinityRule()) {
					sequence_Expression1200xfx(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression200xfxRule()) {
					sequence_Expression1200xfx_Expression200xfx_Expression200xfy(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression400yfxRule() ||
				   context == grammarAccess.getExpression400yfxAccess().getExpressionLeftAction_1_0()) {
					sequence_Expression1200xfx_Expression200xfx_Expression200xfy_Expression400yfx(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression200xfxAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression200xfyRule()) {
					sequence_Expression1200xfx_Expression200xfy(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression500yfxRule() ||
				   context == grammarAccess.getExpression500yfxAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression600xfyAccess().getExpressionLeftAction_1_0()) {
					sequence_Expression500yfx(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression600xfyRule() ||
				   context == grammarAccess.getExpression700xfxAccess().getExpressionLeftAction_1_0()) {
					sequence_Expression500yfx_Expression600xfy(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression700xfxRule()) {
					sequence_Expression500yfx_Expression600xfy_Expression700xfx(context, (Expression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression1000xfyAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression954xfyRule()) {
					sequence_Expression954xfy(context, (Expression) semanticObject); 
					return; 
				}
				else break;
			case PrologPackage.LIST_EXPRESSION:
				if(context == grammarAccess.getExpression0Rule() ||
				   context == grammarAccess.getExpression200xfxRule() ||
				   context == grammarAccess.getExpression200xfxAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression200xfyRule() ||
				   context == grammarAccess.getExpression200xfyAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression400yfxRule() ||
				   context == grammarAccess.getExpression400yfxAccess().getExpressionLeftAction_1_0()) {
					sequence_Expression0(context, (ListExpression) semanticObject); 
					return; 
				}
				else break;
			case PrologPackage.MODEL:
				if(context == grammarAccess.getModelRule()) {
					sequence_Model(context, (Model) semanticObject); 
					return; 
				}
				else break;
			case PrologPackage.NUMBER_EXPRESSION:
				if(context == grammarAccess.getExpression0Rule() ||
				   context == grammarAccess.getExpression200xfxRule() ||
				   context == grammarAccess.getExpression200xfxAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression200xfyRule() ||
				   context == grammarAccess.getExpression200xfyAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression400yfxRule() ||
				   context == grammarAccess.getExpression400yfxAccess().getExpressionLeftAction_1_0()) {
					sequence_Expression0(context, (NumberExpression) semanticObject); 
					return; 
				}
				else break;
			case PrologPackage.STRING_EXPRESSION:
				if(context == grammarAccess.getExpression0Rule() ||
				   context == grammarAccess.getExpression200xfxRule() ||
				   context == grammarAccess.getExpression200xfxAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression200xfyRule() ||
				   context == grammarAccess.getExpression200xfyAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression400yfxRule() ||
				   context == grammarAccess.getExpression400yfxAccess().getExpressionLeftAction_1_0()) {
					sequence_Expression0(context, (StringExpression) semanticObject); 
					return; 
				}
				else break;
			case PrologPackage.UNARY_EXPRESSION:
				if(context == grammarAccess.getExpression1150fxRule()) {
					sequence_Expression1150fx(context, (UnaryExpression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression0Rule() ||
				   context == grammarAccess.getExpression1200fxRule() ||
				   context == grammarAccess.getExpression1200xfxRule() ||
				   context == grammarAccess.getExpression1200xfxAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression200xfxRule() ||
				   context == grammarAccess.getExpression200xfxAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression200xfyRule() ||
				   context == grammarAccess.getExpression200xfyAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression400yfxRule() ||
				   context == grammarAccess.getExpression400yfxAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpressionInfinityRule()) {
					sequence_Expression1200fx(context, (UnaryExpression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression500fxRule() ||
				   context == grammarAccess.getExpression500yfxRule() ||
				   context == grammarAccess.getExpression500yfxAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression600xfyRule() ||
				   context == grammarAccess.getExpression600xfyAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression700xfxRule() ||
				   context == grammarAccess.getExpression700xfxAccess().getExpressionLeftAction_1_0()) {
					sequence_Expression500fx(context, (UnaryExpression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression900fxRule()) {
					sequence_Expression900fx(context, (UnaryExpression) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getExpression1000xfyRule() ||
				   context == grammarAccess.getExpression1000xfyAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression1050xfyRule() ||
				   context == grammarAccess.getExpression1050xfyAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression1100xfyRule() ||
				   context == grammarAccess.getExpression1100xfyAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression900fyRule() ||
				   context == grammarAccess.getExpression954xfyRule() ||
				   context == grammarAccess.getExpression954xfyAccess().getExpressionLeftAction_1_0()) {
					sequence_Expression900fy(context, (UnaryExpression) semanticObject); 
					return; 
				}
				else break;
			case PrologPackage.VARIABLE_EXPRESSION:
				if(context == grammarAccess.getExpression0Rule() ||
				   context == grammarAccess.getExpression200xfxRule() ||
				   context == grammarAccess.getExpression200xfxAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression200xfyRule() ||
				   context == grammarAccess.getExpression200xfyAccess().getExpressionLeftAction_1_0() ||
				   context == grammarAccess.getExpression400yfxRule() ||
				   context == grammarAccess.getExpression400yfxAccess().getExpressionLeftAction_1_0()) {
					sequence_Expression0(context, (VariableExpression) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (atom=ATOMS terms=ExpressionInfinity?)
	 */
	protected void sequence_Expression0(EObject context, AtomExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((head=ExpressionInfinity tail=ExpressionInfinity?)?)
	 */
	protected void sequence_Expression0(EObject context, ListExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=NUMBER
	 */
	protected void sequence_Expression0(EObject context, NumberExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     value=STRING
	 */
	protected void sequence_Expression0(EObject context, StringExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     name=VARIABLE
	 */
	protected void sequence_Expression0(EObject context, VariableExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (left=Expression1000xfy_Expression_1_0 op=OP1000XFY right=Expression1000xfy) | 
	 *         (left=Expression954xfy_Expression_1_0 op=OP954XFY right=Expression954xfy) | 
	 *         (left=Expression1050xfy_Expression_1_0 op=OP1050XFY right=Expression1050xfy) | 
	 *         (left=Expression1100xfy_Expression_1_0 op=OP1100XFY right=Expression1100xfy)
	 *     )
	 */
	protected void sequence_Expression1000xfy_Expression1050xfy_Expression1100xfy_Expression954xfy(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (left=Expression1000xfy_Expression_1_0 op=OP1000XFY right=Expression1000xfy) | 
	 *         (left=Expression954xfy_Expression_1_0 op=OP954XFY right=Expression954xfy) | 
	 *         (left=Expression1050xfy_Expression_1_0 op=OP1050XFY right=Expression1050xfy)
	 *     )
	 */
	protected void sequence_Expression1000xfy_Expression1050xfy_Expression954xfy(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (left=Expression1000xfy_Expression_1_0 op=OP1000XFY right=Expression1000xfy) | 
	 *         (left=Expression954xfy_Expression_1_0 op=OP954XFY right=Expression954xfy)
	 *     )
	 */
	protected void sequence_Expression1000xfy_Expression954xfy(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (op=OP1150FX? right=Expression1100xfy)
	 */
	protected void sequence_Expression1150fx(EObject context, UnaryExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (op=OP1200FX? right=Expression1150fx)
	 */
	protected void sequence_Expression1200fx(EObject context, UnaryExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (left=Expression1200xfx_Expression_1_0 op=OP1200XFX right=Expression1200fx)
	 */
	protected void sequence_Expression1200xfx(EObject context, Expression semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PrologPackage.Literals.EXPRESSION__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PrologPackage.Literals.EXPRESSION__LEFT));
			if(transientValues.isValueTransient(semanticObject, PrologPackage.Literals.EXPRESSION__OP) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PrologPackage.Literals.EXPRESSION__OP));
			if(transientValues.isValueTransient(semanticObject, PrologPackage.Literals.EXPRESSION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PrologPackage.Literals.EXPRESSION__RIGHT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getExpression1200xfxAccess().getExpressionLeftAction_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getExpression1200xfxAccess().getOpOP1200XFXTerminalRuleCall_1_1_0(), semanticObject.getOp());
		feeder.accept(grammarAccess.getExpression1200xfxAccess().getRightExpression1200fxParserRuleCall_1_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (left=Expression200xfy_Expression_1_0 op=OP200XFY right=Expression200xfy) | 
	 *         (left=Expression1200xfx_Expression_1_0 op=OP1200XFX right=Expression1200fx) | 
	 *         (left=Expression200xfx_Expression_1_0 op=OP200XFX right=Expression200xfy)
	 *     )
	 */
	protected void sequence_Expression1200xfx_Expression200xfx_Expression200xfy(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (left=Expression200xfy_Expression_1_0 op=OP200XFY right=Expression200xfy) | 
	 *         (left=Expression1200xfx_Expression_1_0 op=OP1200XFX right=Expression1200fx) | 
	 *         (left=Expression200xfx_Expression_1_0 op=OP200XFX right=Expression200xfy) | 
	 *         (left=Expression400yfx_Expression_1_0 op=OP400YFX right=Expression200xfx)
	 *     )
	 */
	protected void sequence_Expression1200xfx_Expression200xfx_Expression200xfy_Expression400yfx(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (left=Expression200xfy_Expression_1_0 op=OP200XFY right=Expression200xfy) | 
	 *         (left=Expression1200xfx_Expression_1_0 op=OP1200XFX right=Expression1200fx)
	 *     )
	 */
	protected void sequence_Expression1200xfx_Expression200xfy(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (op=OP500FX? right=Expression400yfx)
	 */
	protected void sequence_Expression500fx(EObject context, UnaryExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (left=Expression500yfx_Expression_1_0 op=OP500YFX right=Expression500fx)
	 */
	protected void sequence_Expression500yfx(EObject context, Expression semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PrologPackage.Literals.EXPRESSION__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PrologPackage.Literals.EXPRESSION__LEFT));
			if(transientValues.isValueTransient(semanticObject, PrologPackage.Literals.EXPRESSION__OP) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PrologPackage.Literals.EXPRESSION__OP));
			if(transientValues.isValueTransient(semanticObject, PrologPackage.Literals.EXPRESSION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PrologPackage.Literals.EXPRESSION__RIGHT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getExpression500yfxAccess().getExpressionLeftAction_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getExpression500yfxAccess().getOpOP500YFXTerminalRuleCall_1_1_0(), semanticObject.getOp());
		feeder.accept(grammarAccess.getExpression500yfxAccess().getRightExpression500fxParserRuleCall_1_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (left=Expression600xfy_Expression_1_0 op=OP600XFY right=Expression600xfy) | 
	 *         (left=Expression500yfx_Expression_1_0 op=OP500YFX right=Expression500fx)
	 *     )
	 */
	protected void sequence_Expression500yfx_Expression600xfy(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         (left=Expression600xfy_Expression_1_0 op=OP600XFY right=Expression600xfy) | 
	 *         (left=Expression500yfx_Expression_1_0 op=OP500YFX right=Expression500fx) | 
	 *         (left=Expression700xfx_Expression_1_0 op=OP700XFX right=Expression600xfy)
	 *     )
	 */
	protected void sequence_Expression500yfx_Expression600xfy_Expression700xfx(EObject context, Expression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (op=OP900FX? right=Expression700xfx)
	 */
	protected void sequence_Expression900fx(EObject context, UnaryExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     ((op=OP900FY right=Expression900fy) | right=Expression900fx)
	 */
	protected void sequence_Expression900fy(EObject context, UnaryExpression semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (left=Expression954xfy_Expression_1_0 op=OP954XFY right=Expression954xfy)
	 */
	protected void sequence_Expression954xfy(EObject context, Expression semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PrologPackage.Literals.EXPRESSION__LEFT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PrologPackage.Literals.EXPRESSION__LEFT));
			if(transientValues.isValueTransient(semanticObject, PrologPackage.Literals.EXPRESSION__OP) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PrologPackage.Literals.EXPRESSION__OP));
			if(transientValues.isValueTransient(semanticObject, PrologPackage.Literals.EXPRESSION__RIGHT) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PrologPackage.Literals.EXPRESSION__RIGHT));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getExpression954xfyAccess().getExpressionLeftAction_1_0(), semanticObject.getLeft());
		feeder.accept(grammarAccess.getExpression954xfyAccess().getOpOP954XFYTerminalRuleCall_1_1_0(), semanticObject.getOp());
		feeder.accept(grammarAccess.getExpression954xfyAccess().getRightExpression954xfyParserRuleCall_1_2_0(), semanticObject.getRight());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     exps+=ExpressionInfinity+
	 */
	protected void sequence_Model(EObject context, Model semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
