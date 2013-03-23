package org.archstudio.prolog.xtext.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.archstudio.prolog.xtext.services.PrologGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
@SuppressWarnings("all")
public class InternalPrologParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_EXPRESSION_1200", "RULE_EXPRESSION_1200FX", "RULE_EXPRESSION_1100", "RULE_EXPRESSION_1000", "RULE_EXPRESSION_900FX", "RULE_EXPRESSION_700", "RULE_EXPRESSION_600", "RULE_EXPRESSION_500", "RULE_EXPRESSION_400", "RULE_EXPRESSION_200", "RULE_ATOM", "RULE_VARIABLE", "RULE_STRING", "RULE_DIGIT", "RULE_WHITESPACE", "RULE_SINGLE_LINE_COMMENT", "'.'", "'('", "')'", "'['", "'|'", "']'", "'-'", "'e'", "'E'", "'+'"
    };
    public static final int RULE_EXPRESSION_1200FX=5;
    public static final int RULE_EXPRESSION_1200=4;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int RULE_EXPRESSION_600=10;
    public static final int T__20=20;
    public static final int RULE_EXPRESSION_500=11;
    public static final int RULE_EXPRESSION_400=12;
    public static final int RULE_SINGLE_LINE_COMMENT=19;
    public static final int RULE_ATOM=14;
    public static final int EOF=-1;
    public static final int RULE_EXPRESSION_900FX=8;
    public static final int RULE_STRING=16;
    public static final int RULE_EXPRESSION_200=13;
    public static final int RULE_VARIABLE=15;
    public static final int RULE_EXPRESSION_1000=7;
    public static final int RULE_EXPRESSION_1100=6;
    public static final int RULE_WHITESPACE=18;
    public static final int RULE_DIGIT=17;
    public static final int RULE_EXPRESSION_700=9;

    // delegates
    // delegators


        public InternalPrologParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalPrologParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalPrologParser.tokenNames; }
    public String getGrammarFileName() { return "../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g"; }



    /*
      This grammar contains a lot of empty actions to work around a bug in ANTLR.
      Otherwise the ANTLR tool will create synpreds that cannot be compiled in some rare cases.
    */
     
     	private PrologGrammarAccess grammarAccess;
     	
        public InternalPrologParser(TokenStream input, PrologGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "Program";	
       	}
       	
       	@Override
       	protected PrologGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleProgram"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:73:1: entryRuleProgram returns [EObject current=null] : iv_ruleProgram= ruleProgram EOF ;
    public final EObject entryRuleProgram() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProgram = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:74:2: (iv_ruleProgram= ruleProgram EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:75:2: iv_ruleProgram= ruleProgram EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getProgramRule()); 
            }
            pushFollow(FOLLOW_ruleProgram_in_entryRuleProgram81);
            iv_ruleProgram=ruleProgram();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleProgram; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleProgram91); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleProgram"


    // $ANTLR start "ruleProgram"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:82:1: ruleProgram returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpressionINF ) ) otherlv_1= '.' )+ ;
    public final EObject ruleProgram() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_exps_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:85:28: ( ( ( (lv_exps_0_0= ruleExpressionINF ) ) otherlv_1= '.' )+ )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:86:1: ( ( (lv_exps_0_0= ruleExpressionINF ) ) otherlv_1= '.' )+
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:86:1: ( ( (lv_exps_0_0= ruleExpressionINF ) ) otherlv_1= '.' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RULE_EXPRESSION_1200 && LA1_0<=RULE_DIGIT)||(LA1_0>=20 && LA1_0<=21)||LA1_0==23||LA1_0==26) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:86:2: ( (lv_exps_0_0= ruleExpressionINF ) ) otherlv_1= '.'
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:86:2: ( (lv_exps_0_0= ruleExpressionINF ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:87:1: (lv_exps_0_0= ruleExpressionINF )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:87:1: (lv_exps_0_0= ruleExpressionINF )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:88:3: lv_exps_0_0= ruleExpressionINF
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getProgramAccess().getExpsExpressionINFParserRuleCall_0_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpressionINF_in_ruleProgram137);
            	    lv_exps_0_0=ruleExpressionINF();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getProgramRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"exps",
            	              		lv_exps_0_0, 
            	              		"ExpressionINF");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    otherlv_1=(Token)match(input,20,FOLLOW_20_in_ruleProgram149); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getProgramAccess().getFullStopKeyword_1());
            	          
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleProgram"


    // $ANTLR start "entryRuleExpressionINF"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:116:1: entryRuleExpressionINF returns [EObject current=null] : iv_ruleExpressionINF= ruleExpressionINF EOF ;
    public final EObject entryRuleExpressionINF() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionINF = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:117:2: (iv_ruleExpressionINF= ruleExpressionINF EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:118:2: iv_ruleExpressionINF= ruleExpressionINF EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionINFRule()); 
            }
            pushFollow(FOLLOW_ruleExpressionINF_in_entryRuleExpressionINF186);
            iv_ruleExpressionINF=ruleExpressionINF();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpressionINF; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionINF196); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpressionINF"


    // $ANTLR start "ruleExpressionINF"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:125:1: ruleExpressionINF returns [EObject current=null] : this_Expression1200_0= ruleExpression1200 ;
    public final EObject ruleExpressionINF() throws RecognitionException {
        EObject current = null;

        EObject this_Expression1200_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:128:28: (this_Expression1200_0= ruleExpression1200 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:130:2: this_Expression1200_0= ruleExpression1200
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpressionINFAccess().getExpression1200ParserRuleCall()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression1200_in_ruleExpressionINF245);
            this_Expression1200_0=ruleExpression1200();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression1200_0; 
                      afterParserOrEnumRuleCall();
                  
            }

            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpressionINF"


    // $ANTLR start "entryRuleExpression1200"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:149:1: entryRuleExpression1200 returns [EObject current=null] : iv_ruleExpression1200= ruleExpression1200 EOF ;
    public final EObject entryRuleExpression1200() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1200 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:150:2: (iv_ruleExpression1200= ruleExpression1200 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:151:2: iv_ruleExpression1200= ruleExpression1200 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1200Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression1200_in_entryRuleExpression1200279);
            iv_ruleExpression1200=ruleExpression1200();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1200; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1200289); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression1200"


    // $ANTLR start "ruleExpression1200"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:158:1: ruleExpression1200 returns [EObject current=null] : (this_Expression1200fx_0= ruleExpression1200fx ( ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) ) )? ) ;
    public final EObject ruleExpression1200() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject this_Expression1200fx_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:161:28: ( (this_Expression1200fx_0= ruleExpression1200fx ( ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:162:1: (this_Expression1200fx_0= ruleExpression1200fx ( ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:162:1: (this_Expression1200fx_0= ruleExpression1200fx ( ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:163:2: this_Expression1200fx_0= ruleExpression1200fx ( ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression1200Access().getExpression1200fxParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression1200fx_in_ruleExpression1200339);
            this_Expression1200fx_0=ruleExpression1200fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression1200fx_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:174:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_EXPRESSION_1200) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:174:2: ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:174:2: ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:175:1: (lv_ops_1_0= RULE_EXPRESSION_1200 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:175:1: (lv_ops_1_0= RULE_EXPRESSION_1200 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:176:3: lv_ops_1_0= RULE_EXPRESSION_1200
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_1200,FOLLOW_RULE_EXPRESSION_1200_in_ruleExpression1200356); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_ops_1_0, grammarAccess.getExpression1200Access().getOpsEXPRESSION_1200TerminalRuleCall_1_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression1200Rule());
                      	        }
                             		addWithLastConsumed(
                             			current, 
                             			"ops",
                              		lv_ops_1_0, 
                              		"EXPRESSION_1200");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:192:2: ( (lv_exps_2_0= ruleExpression1200fx ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:193:1: (lv_exps_2_0= ruleExpression1200fx )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:193:1: (lv_exps_2_0= ruleExpression1200fx )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:194:3: lv_exps_2_0= ruleExpression1200fx
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression1200Access().getExpsExpression1200fxParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression1200fx_in_ruleExpression1200382);
                    lv_exps_2_0=ruleExpression1200fx();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression1200Rule());
                      	        }
                             		add(
                             			current, 
                             			"exps",
                              		lv_exps_2_0, 
                              		"Expression1200fx");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression1200"


    // $ANTLR start "entryRuleExpression1200fx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:218:1: entryRuleExpression1200fx returns [EObject current=null] : iv_ruleExpression1200fx= ruleExpression1200fx EOF ;
    public final EObject entryRuleExpression1200fx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1200fx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:219:2: (iv_ruleExpression1200fx= ruleExpression1200fx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:220:2: iv_ruleExpression1200fx= ruleExpression1200fx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1200fxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression1200fx_in_entryRuleExpression1200fx420);
            iv_ruleExpression1200fx=ruleExpression1200fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1200fx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1200fx430); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression1200fx"


    // $ANTLR start "ruleExpression1200fx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:227:1: ruleExpression1200fx returns [EObject current=null] : ( ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )? ( (lv_exps_1_0= ruleExpression1100 ) ) ) ;
    public final EObject ruleExpression1200fx() throws RecognitionException {
        EObject current = null;

        Token lv_ops_0_0=null;
        EObject lv_exps_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:230:28: ( ( ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )? ( (lv_exps_1_0= ruleExpression1100 ) ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:231:1: ( ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )? ( (lv_exps_1_0= ruleExpression1100 ) ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:231:1: ( ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )? ( (lv_exps_1_0= ruleExpression1100 ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:231:2: ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )? ( (lv_exps_1_0= ruleExpression1100 ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:231:2: ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )?
            int alt3=2;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:232:1: (lv_ops_0_0= RULE_EXPRESSION_1200FX )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:232:1: (lv_ops_0_0= RULE_EXPRESSION_1200FX )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:233:3: lv_ops_0_0= RULE_EXPRESSION_1200FX
                    {
                    lv_ops_0_0=(Token)match(input,RULE_EXPRESSION_1200FX,FOLLOW_RULE_EXPRESSION_1200FX_in_ruleExpression1200fx472); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_ops_0_0, grammarAccess.getExpression1200fxAccess().getOpsEXPRESSION_1200FXTerminalRuleCall_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression1200fxRule());
                      	        }
                             		addWithLastConsumed(
                             			current, 
                             			"ops",
                              		lv_ops_0_0, 
                              		"EXPRESSION_1200FX");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:249:3: ( (lv_exps_1_0= ruleExpression1100 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:250:1: (lv_exps_1_0= ruleExpression1100 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:250:1: (lv_exps_1_0= ruleExpression1100 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:251:3: lv_exps_1_0= ruleExpression1100
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression1200fxAccess().getExpsExpression1100ParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression1100_in_ruleExpression1200fx499);
            lv_exps_1_0=ruleExpression1100();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression1200fxRule());
              	        }
                     		add(
                     			current, 
                     			"exps",
                      		lv_exps_1_0, 
                      		"Expression1100");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression1200fx"


    // $ANTLR start "entryRuleExpression1100"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:275:1: entryRuleExpression1100 returns [EObject current=null] : iv_ruleExpression1100= ruleExpression1100 EOF ;
    public final EObject entryRuleExpression1100() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1100 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:276:2: (iv_ruleExpression1100= ruleExpression1100 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:277:2: iv_ruleExpression1100= ruleExpression1100 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1100Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression1100_in_entryRuleExpression1100535);
            iv_ruleExpression1100=ruleExpression1100();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1100; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1100545); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression1100"


    // $ANTLR start "ruleExpression1100"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:284:1: ruleExpression1100 returns [EObject current=null] : (this_Expression1000_0= ruleExpression1000 ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1000 ) ) )* ) ;
    public final EObject ruleExpression1100() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject this_Expression1000_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:287:28: ( (this_Expression1000_0= ruleExpression1000 ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1000 ) ) )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:288:1: (this_Expression1000_0= ruleExpression1000 ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1000 ) ) )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:288:1: (this_Expression1000_0= ruleExpression1000 ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1000 ) ) )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:289:2: this_Expression1000_0= ruleExpression1000 ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1000 ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression1100Access().getExpression1000ParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression1000_in_ruleExpression1100595);
            this_Expression1000_0=ruleExpression1000();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression1000_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:300:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1000 ) ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==RULE_EXPRESSION_1100) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:300:2: ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1000 ) )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:300:2: ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:301:1: (lv_ops_1_0= RULE_EXPRESSION_1100 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:301:1: (lv_ops_1_0= RULE_EXPRESSION_1100 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:302:3: lv_ops_1_0= RULE_EXPRESSION_1100
            	    {
            	    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_1100,FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression1100612); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      			newLeafNode(lv_ops_1_0, grammarAccess.getExpression1100Access().getOpsEXPRESSION_1100TerminalRuleCall_1_0_0()); 
            	      		
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getExpression1100Rule());
            	      	        }
            	             		addWithLastConsumed(
            	             			current, 
            	             			"ops",
            	              		lv_ops_1_0, 
            	              		"EXPRESSION_1100");
            	      	    
            	    }

            	    }


            	    }

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:318:2: ( (lv_exps_2_0= ruleExpression1000 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:319:1: (lv_exps_2_0= ruleExpression1000 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:319:1: (lv_exps_2_0= ruleExpression1000 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:320:3: lv_exps_2_0= ruleExpression1000
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExpression1100Access().getExpsExpression1000ParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpression1000_in_ruleExpression1100638);
            	    lv_exps_2_0=ruleExpression1000();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExpression1100Rule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"exps",
            	              		lv_exps_2_0, 
            	              		"Expression1000");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression1100"


    // $ANTLR start "entryRuleExpression1000"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:344:1: entryRuleExpression1000 returns [EObject current=null] : iv_ruleExpression1000= ruleExpression1000 EOF ;
    public final EObject entryRuleExpression1000() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1000 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:345:2: (iv_ruleExpression1000= ruleExpression1000 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:346:2: iv_ruleExpression1000= ruleExpression1000 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1000Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression1000_in_entryRuleExpression1000676);
            iv_ruleExpression1000=ruleExpression1000();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1000; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1000686); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression1000"


    // $ANTLR start "ruleExpression1000"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:353:1: ruleExpression1000 returns [EObject current=null] : (this_Expression900fx_0= ruleExpression900fx ( ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) ) )* ) ;
    public final EObject ruleExpression1000() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject this_Expression900fx_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:356:28: ( (this_Expression900fx_0= ruleExpression900fx ( ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) ) )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:357:1: (this_Expression900fx_0= ruleExpression900fx ( ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) ) )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:357:1: (this_Expression900fx_0= ruleExpression900fx ( ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) ) )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:358:2: this_Expression900fx_0= ruleExpression900fx ( ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression1000Access().getExpression900fxParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression900fx_in_ruleExpression1000736);
            this_Expression900fx_0=ruleExpression900fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression900fx_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:369:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==RULE_EXPRESSION_1000) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:369:2: ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:369:2: ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:370:1: (lv_ops_1_0= RULE_EXPRESSION_1000 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:370:1: (lv_ops_1_0= RULE_EXPRESSION_1000 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:371:3: lv_ops_1_0= RULE_EXPRESSION_1000
            	    {
            	    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_1000,FOLLOW_RULE_EXPRESSION_1000_in_ruleExpression1000753); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      			newLeafNode(lv_ops_1_0, grammarAccess.getExpression1000Access().getOpsEXPRESSION_1000TerminalRuleCall_1_0_0()); 
            	      		
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getExpression1000Rule());
            	      	        }
            	             		addWithLastConsumed(
            	             			current, 
            	             			"ops",
            	              		lv_ops_1_0, 
            	              		"EXPRESSION_1000");
            	      	    
            	    }

            	    }


            	    }

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:387:2: ( (lv_exps_2_0= ruleExpression900fx ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:388:1: (lv_exps_2_0= ruleExpression900fx )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:388:1: (lv_exps_2_0= ruleExpression900fx )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:389:3: lv_exps_2_0= ruleExpression900fx
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExpression1000Access().getExpsExpression900fxParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpression900fx_in_ruleExpression1000779);
            	    lv_exps_2_0=ruleExpression900fx();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExpression1000Rule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"exps",
            	              		lv_exps_2_0, 
            	              		"Expression900fx");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression1000"


    // $ANTLR start "entryRuleExpression900fx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:413:1: entryRuleExpression900fx returns [EObject current=null] : iv_ruleExpression900fx= ruleExpression900fx EOF ;
    public final EObject entryRuleExpression900fx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression900fx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:414:2: (iv_ruleExpression900fx= ruleExpression900fx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:415:2: iv_ruleExpression900fx= ruleExpression900fx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression900fxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression900fx_in_entryRuleExpression900fx817);
            iv_ruleExpression900fx=ruleExpression900fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression900fx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression900fx827); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression900fx"


    // $ANTLR start "ruleExpression900fx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:422:1: ruleExpression900fx returns [EObject current=null] : ( ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )? ( (lv_exps_1_0= ruleExpression700 ) ) ) ;
    public final EObject ruleExpression900fx() throws RecognitionException {
        EObject current = null;

        Token lv_ops_0_0=null;
        EObject lv_exps_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:425:28: ( ( ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )? ( (lv_exps_1_0= ruleExpression700 ) ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:426:1: ( ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )? ( (lv_exps_1_0= ruleExpression700 ) ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:426:1: ( ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )? ( (lv_exps_1_0= ruleExpression700 ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:426:2: ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )? ( (lv_exps_1_0= ruleExpression700 ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:426:2: ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )?
            int alt6=2;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:427:1: (lv_ops_0_0= RULE_EXPRESSION_900FX )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:427:1: (lv_ops_0_0= RULE_EXPRESSION_900FX )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:428:3: lv_ops_0_0= RULE_EXPRESSION_900FX
                    {
                    lv_ops_0_0=(Token)match(input,RULE_EXPRESSION_900FX,FOLLOW_RULE_EXPRESSION_900FX_in_ruleExpression900fx869); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_ops_0_0, grammarAccess.getExpression900fxAccess().getOpsEXPRESSION_900FXTerminalRuleCall_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression900fxRule());
                      	        }
                             		addWithLastConsumed(
                             			current, 
                             			"ops",
                              		lv_ops_0_0, 
                              		"EXPRESSION_900FX");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:444:3: ( (lv_exps_1_0= ruleExpression700 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:445:1: (lv_exps_1_0= ruleExpression700 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:445:1: (lv_exps_1_0= ruleExpression700 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:446:3: lv_exps_1_0= ruleExpression700
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression900fxAccess().getExpsExpression700ParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression700_in_ruleExpression900fx896);
            lv_exps_1_0=ruleExpression700();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression900fxRule());
              	        }
                     		add(
                     			current, 
                     			"exps",
                      		lv_exps_1_0, 
                      		"Expression700");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression900fx"


    // $ANTLR start "entryRuleExpression700"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:470:1: entryRuleExpression700 returns [EObject current=null] : iv_ruleExpression700= ruleExpression700 EOF ;
    public final EObject entryRuleExpression700() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression700 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:471:2: (iv_ruleExpression700= ruleExpression700 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:472:2: iv_ruleExpression700= ruleExpression700 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression700Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression700_in_entryRuleExpression700932);
            iv_ruleExpression700=ruleExpression700();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression700; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression700942); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression700"


    // $ANTLR start "ruleExpression700"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:479:1: ruleExpression700 returns [EObject current=null] : (this_Expression600_0= ruleExpression600 ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) ) )? ) ;
    public final EObject ruleExpression700() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject this_Expression600_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:482:28: ( (this_Expression600_0= ruleExpression600 ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:483:1: (this_Expression600_0= ruleExpression600 ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:483:1: (this_Expression600_0= ruleExpression600 ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:484:2: this_Expression600_0= ruleExpression600 ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression700Access().getExpression600ParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression600_in_ruleExpression700992);
            this_Expression600_0=ruleExpression600();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression600_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:495:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) ) )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==RULE_EXPRESSION_700) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:495:2: ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:495:2: ( (lv_ops_1_0= RULE_EXPRESSION_700 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:496:1: (lv_ops_1_0= RULE_EXPRESSION_700 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:496:1: (lv_ops_1_0= RULE_EXPRESSION_700 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:497:3: lv_ops_1_0= RULE_EXPRESSION_700
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_700,FOLLOW_RULE_EXPRESSION_700_in_ruleExpression7001009); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_ops_1_0, grammarAccess.getExpression700Access().getOpsEXPRESSION_700TerminalRuleCall_1_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression700Rule());
                      	        }
                             		addWithLastConsumed(
                             			current, 
                             			"ops",
                              		lv_ops_1_0, 
                              		"EXPRESSION_700");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:513:2: ( (lv_exps_2_0= ruleExpression600 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:514:1: (lv_exps_2_0= ruleExpression600 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:514:1: (lv_exps_2_0= ruleExpression600 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:515:3: lv_exps_2_0= ruleExpression600
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression700Access().getExpsExpression600ParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression600_in_ruleExpression7001035);
                    lv_exps_2_0=ruleExpression600();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression700Rule());
                      	        }
                             		add(
                             			current, 
                             			"exps",
                              		lv_exps_2_0, 
                              		"Expression600");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression700"


    // $ANTLR start "entryRuleExpression600"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:539:1: entryRuleExpression600 returns [EObject current=null] : iv_ruleExpression600= ruleExpression600 EOF ;
    public final EObject entryRuleExpression600() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression600 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:540:2: (iv_ruleExpression600= ruleExpression600 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:541:2: iv_ruleExpression600= ruleExpression600 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression600Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression600_in_entryRuleExpression6001073);
            iv_ruleExpression600=ruleExpression600();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression600; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression6001083); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression600"


    // $ANTLR start "ruleExpression600"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:548:1: ruleExpression600 returns [EObject current=null] : (this_Expression500_0= ruleExpression500 ( ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? ) ;
    public final EObject ruleExpression600() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject this_Expression500_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:551:28: ( (this_Expression500_0= ruleExpression500 ( ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:552:1: (this_Expression500_0= ruleExpression500 ( ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:552:1: (this_Expression500_0= ruleExpression500 ( ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:553:2: this_Expression500_0= ruleExpression500 ( ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression600Access().getExpression500ParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression500_in_ruleExpression6001133);
            this_Expression500_0=ruleExpression500();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression500_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:564:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_EXPRESSION_600) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:564:2: ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:564:2: ( (lv_ops_1_0= RULE_EXPRESSION_600 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:565:1: (lv_ops_1_0= RULE_EXPRESSION_600 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:565:1: (lv_ops_1_0= RULE_EXPRESSION_600 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:566:3: lv_ops_1_0= RULE_EXPRESSION_600
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_600,FOLLOW_RULE_EXPRESSION_600_in_ruleExpression6001150); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_ops_1_0, grammarAccess.getExpression600Access().getOpsEXPRESSION_600TerminalRuleCall_1_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression600Rule());
                      	        }
                             		addWithLastConsumed(
                             			current, 
                             			"ops",
                              		lv_ops_1_0, 
                              		"EXPRESSION_600");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:582:2: ( (lv_exps_2_0= ruleExpression500 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:583:1: (lv_exps_2_0= ruleExpression500 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:583:1: (lv_exps_2_0= ruleExpression500 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:584:3: lv_exps_2_0= ruleExpression500
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression600Access().getExpsExpression500ParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression500_in_ruleExpression6001176);
                    lv_exps_2_0=ruleExpression500();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression600Rule());
                      	        }
                             		add(
                             			current, 
                             			"exps",
                              		lv_exps_2_0, 
                              		"Expression500");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression600"


    // $ANTLR start "entryRuleExpression500"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:608:1: entryRuleExpression500 returns [EObject current=null] : iv_ruleExpression500= ruleExpression500 EOF ;
    public final EObject entryRuleExpression500() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression500 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:609:2: (iv_ruleExpression500= ruleExpression500 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:610:2: iv_ruleExpression500= ruleExpression500 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression500Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression500_in_entryRuleExpression5001214);
            iv_ruleExpression500=ruleExpression500();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression500; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression5001224); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression500"


    // $ANTLR start "ruleExpression500"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:617:1: ruleExpression500 returns [EObject current=null] : (this_Expression400_0= ruleExpression400 ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )? ) ;
    public final EObject ruleExpression500() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject this_Expression400_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:620:28: ( (this_Expression400_0= ruleExpression400 ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:621:1: (this_Expression400_0= ruleExpression400 ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:621:1: (this_Expression400_0= ruleExpression400 ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:622:2: this_Expression400_0= ruleExpression400 ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression500Access().getExpression400ParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression400_in_ruleExpression5001274);
            this_Expression400_0=ruleExpression400();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression400_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:633:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_EXPRESSION_500) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:633:2: ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:633:2: ( (lv_ops_1_0= RULE_EXPRESSION_500 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:634:1: (lv_ops_1_0= RULE_EXPRESSION_500 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:634:1: (lv_ops_1_0= RULE_EXPRESSION_500 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:635:3: lv_ops_1_0= RULE_EXPRESSION_500
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_500,FOLLOW_RULE_EXPRESSION_500_in_ruleExpression5001291); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_ops_1_0, grammarAccess.getExpression500Access().getOpsEXPRESSION_500TerminalRuleCall_1_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression500Rule());
                      	        }
                             		addWithLastConsumed(
                             			current, 
                             			"ops",
                              		lv_ops_1_0, 
                              		"EXPRESSION_500");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:651:2: ( (lv_exps_2_0= ruleExpression400 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:652:1: (lv_exps_2_0= ruleExpression400 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:652:1: (lv_exps_2_0= ruleExpression400 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:653:3: lv_exps_2_0= ruleExpression400
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression500Access().getExpsExpression400ParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression400_in_ruleExpression5001317);
                    lv_exps_2_0=ruleExpression400();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression500Rule());
                      	        }
                             		add(
                             			current, 
                             			"exps",
                              		lv_exps_2_0, 
                              		"Expression400");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression500"


    // $ANTLR start "entryRuleExpression400"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:677:1: entryRuleExpression400 returns [EObject current=null] : iv_ruleExpression400= ruleExpression400 EOF ;
    public final EObject entryRuleExpression400() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression400 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:678:2: (iv_ruleExpression400= ruleExpression400 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:679:2: iv_ruleExpression400= ruleExpression400 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression400Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression400_in_entryRuleExpression4001355);
            iv_ruleExpression400=ruleExpression400();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression400; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression4001365); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression400"


    // $ANTLR start "ruleExpression400"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:686:1: ruleExpression400 returns [EObject current=null] : (this_Expression200_0= ruleExpression200 ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )? ) ;
    public final EObject ruleExpression400() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject this_Expression200_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:689:28: ( (this_Expression200_0= ruleExpression200 ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:690:1: (this_Expression200_0= ruleExpression200 ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:690:1: (this_Expression200_0= ruleExpression200 ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:691:2: this_Expression200_0= ruleExpression200 ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression400Access().getExpression200ParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression200_in_ruleExpression4001415);
            this_Expression200_0=ruleExpression200();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression200_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:702:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_EXPRESSION_400) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:702:2: ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:702:2: ( (lv_ops_1_0= RULE_EXPRESSION_400 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:703:1: (lv_ops_1_0= RULE_EXPRESSION_400 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:703:1: (lv_ops_1_0= RULE_EXPRESSION_400 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:704:3: lv_ops_1_0= RULE_EXPRESSION_400
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_400,FOLLOW_RULE_EXPRESSION_400_in_ruleExpression4001432); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_ops_1_0, grammarAccess.getExpression400Access().getOpsEXPRESSION_400TerminalRuleCall_1_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression400Rule());
                      	        }
                             		addWithLastConsumed(
                             			current, 
                             			"ops",
                              		lv_ops_1_0, 
                              		"EXPRESSION_400");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:720:2: ( (lv_exps_2_0= ruleExpression200 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:721:1: (lv_exps_2_0= ruleExpression200 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:721:1: (lv_exps_2_0= ruleExpression200 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:722:3: lv_exps_2_0= ruleExpression200
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression400Access().getExpsExpression200ParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression200_in_ruleExpression4001458);
                    lv_exps_2_0=ruleExpression200();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression400Rule());
                      	        }
                             		add(
                             			current, 
                             			"exps",
                              		lv_exps_2_0, 
                              		"Expression200");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression400"


    // $ANTLR start "entryRuleExpression200"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:746:1: entryRuleExpression200 returns [EObject current=null] : iv_ruleExpression200= ruleExpression200 EOF ;
    public final EObject entryRuleExpression200() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression200 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:747:2: (iv_ruleExpression200= ruleExpression200 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:748:2: iv_ruleExpression200= ruleExpression200 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression200Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression200_in_entryRuleExpression2001496);
            iv_ruleExpression200=ruleExpression200();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression200; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression2001506); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression200"


    // $ANTLR start "ruleExpression200"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:755:1: ruleExpression200 returns [EObject current=null] : (this_Expression0_0= ruleExpression0 ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )? ) ;
    public final EObject ruleExpression200() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject this_Expression0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:758:28: ( (this_Expression0_0= ruleExpression0 ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:759:1: (this_Expression0_0= ruleExpression0 ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:759:1: (this_Expression0_0= ruleExpression0 ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:760:2: this_Expression0_0= ruleExpression0 ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression200Access().getExpression0ParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression0_in_ruleExpression2001556);
            this_Expression0_0=ruleExpression0();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression0_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:771:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_EXPRESSION_200) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:771:2: ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:771:2: ( (lv_ops_1_0= RULE_EXPRESSION_200 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:772:1: (lv_ops_1_0= RULE_EXPRESSION_200 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:772:1: (lv_ops_1_0= RULE_EXPRESSION_200 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:773:3: lv_ops_1_0= RULE_EXPRESSION_200
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_200,FOLLOW_RULE_EXPRESSION_200_in_ruleExpression2001573); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_ops_1_0, grammarAccess.getExpression200Access().getOpsEXPRESSION_200TerminalRuleCall_1_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression200Rule());
                      	        }
                             		addWithLastConsumed(
                             			current, 
                             			"ops",
                              		lv_ops_1_0, 
                              		"EXPRESSION_200");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:789:2: ( (lv_exps_2_0= ruleExpression0 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:790:1: (lv_exps_2_0= ruleExpression0 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:790:1: (lv_exps_2_0= ruleExpression0 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:791:3: lv_exps_2_0= ruleExpression0
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression200Access().getExpsExpression0ParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression0_in_ruleExpression2001599);
                    lv_exps_2_0=ruleExpression0();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression200Rule());
                      	        }
                             		add(
                             			current, 
                             			"exps",
                              		lv_exps_2_0, 
                              		"Expression0");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression200"


    // $ANTLR start "entryRuleExpression0"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:815:1: entryRuleExpression0 returns [EObject current=null] : iv_ruleExpression0= ruleExpression0 EOF ;
    public final EObject entryRuleExpression0() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression0 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:816:2: (iv_ruleExpression0= ruleExpression0 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:817:2: iv_ruleExpression0= ruleExpression0 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression0Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression0_in_entryRuleExpression01637);
            iv_ruleExpression0=ruleExpression0();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression0; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression01647); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleExpression0"


    // $ANTLR start "ruleExpression0"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:824:1: ruleExpression0 returns [EObject current=null] : ( ( ( ( (lv_ops_0_1= '.' | lv_ops_0_2= RULE_ATOM | lv_ops_0_3= RULE_EXPRESSION_1200 | lv_ops_0_4= RULE_EXPRESSION_1200FX | lv_ops_0_5= RULE_EXPRESSION_1100 | lv_ops_0_6= RULE_EXPRESSION_1000 | lv_ops_0_7= RULE_EXPRESSION_900FX | lv_ops_0_8= RULE_EXPRESSION_700 | lv_ops_0_9= RULE_EXPRESSION_600 | lv_ops_0_10= RULE_EXPRESSION_500 | lv_ops_0_11= RULE_EXPRESSION_400 | lv_ops_0_12= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' )? ) | ( (lv_variable_4_0= RULE_VARIABLE ) ) | ( (lv_string_5_0= RULE_STRING ) ) | ( (lv_number_6_0= ruleNUMBER ) ) | ( ( (lv_list_7_0= '[' ) ) ( ( (lv_heads_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tails_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']' ) | (otherlv_12= '(' ( (lv_exps_13_0= ruleExpressionINF ) ) otherlv_14= ')' ) ) ;
    public final EObject ruleExpression0() throws RecognitionException {
        EObject current = null;

        Token lv_ops_0_1=null;
        Token lv_ops_0_2=null;
        Token lv_ops_0_3=null;
        Token lv_ops_0_4=null;
        Token lv_ops_0_5=null;
        Token lv_ops_0_6=null;
        Token lv_ops_0_7=null;
        Token lv_ops_0_8=null;
        Token lv_ops_0_9=null;
        Token lv_ops_0_10=null;
        Token lv_ops_0_11=null;
        Token lv_ops_0_12=null;
        Token lv_prefix_1_0=null;
        Token otherlv_3=null;
        Token lv_variable_4_0=null;
        Token lv_string_5_0=null;
        Token lv_list_7_0=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        EObject lv_exps_2_0 = null;

        AntlrDatatypeRuleToken lv_number_6_0 = null;

        EObject lv_heads_8_0 = null;

        EObject lv_tails_10_0 = null;

        EObject lv_exps_13_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:827:28: ( ( ( ( ( (lv_ops_0_1= '.' | lv_ops_0_2= RULE_ATOM | lv_ops_0_3= RULE_EXPRESSION_1200 | lv_ops_0_4= RULE_EXPRESSION_1200FX | lv_ops_0_5= RULE_EXPRESSION_1100 | lv_ops_0_6= RULE_EXPRESSION_1000 | lv_ops_0_7= RULE_EXPRESSION_900FX | lv_ops_0_8= RULE_EXPRESSION_700 | lv_ops_0_9= RULE_EXPRESSION_600 | lv_ops_0_10= RULE_EXPRESSION_500 | lv_ops_0_11= RULE_EXPRESSION_400 | lv_ops_0_12= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' )? ) | ( (lv_variable_4_0= RULE_VARIABLE ) ) | ( (lv_string_5_0= RULE_STRING ) ) | ( (lv_number_6_0= ruleNUMBER ) ) | ( ( (lv_list_7_0= '[' ) ) ( ( (lv_heads_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tails_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']' ) | (otherlv_12= '(' ( (lv_exps_13_0= ruleExpressionINF ) ) otherlv_14= ')' ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:828:1: ( ( ( ( (lv_ops_0_1= '.' | lv_ops_0_2= RULE_ATOM | lv_ops_0_3= RULE_EXPRESSION_1200 | lv_ops_0_4= RULE_EXPRESSION_1200FX | lv_ops_0_5= RULE_EXPRESSION_1100 | lv_ops_0_6= RULE_EXPRESSION_1000 | lv_ops_0_7= RULE_EXPRESSION_900FX | lv_ops_0_8= RULE_EXPRESSION_700 | lv_ops_0_9= RULE_EXPRESSION_600 | lv_ops_0_10= RULE_EXPRESSION_500 | lv_ops_0_11= RULE_EXPRESSION_400 | lv_ops_0_12= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' )? ) | ( (lv_variable_4_0= RULE_VARIABLE ) ) | ( (lv_string_5_0= RULE_STRING ) ) | ( (lv_number_6_0= ruleNUMBER ) ) | ( ( (lv_list_7_0= '[' ) ) ( ( (lv_heads_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tails_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']' ) | (otherlv_12= '(' ( (lv_exps_13_0= ruleExpressionINF ) ) otherlv_14= ')' ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:828:1: ( ( ( ( (lv_ops_0_1= '.' | lv_ops_0_2= RULE_ATOM | lv_ops_0_3= RULE_EXPRESSION_1200 | lv_ops_0_4= RULE_EXPRESSION_1200FX | lv_ops_0_5= RULE_EXPRESSION_1100 | lv_ops_0_6= RULE_EXPRESSION_1000 | lv_ops_0_7= RULE_EXPRESSION_900FX | lv_ops_0_8= RULE_EXPRESSION_700 | lv_ops_0_9= RULE_EXPRESSION_600 | lv_ops_0_10= RULE_EXPRESSION_500 | lv_ops_0_11= RULE_EXPRESSION_400 | lv_ops_0_12= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' )? ) | ( (lv_variable_4_0= RULE_VARIABLE ) ) | ( (lv_string_5_0= RULE_STRING ) ) | ( (lv_number_6_0= ruleNUMBER ) ) | ( ( (lv_list_7_0= '[' ) ) ( ( (lv_heads_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tails_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']' ) | (otherlv_12= '(' ( (lv_exps_13_0= ruleExpressionINF ) ) otherlv_14= ')' ) )
            int alt16=6;
            switch ( input.LA(1) ) {
            case RULE_EXPRESSION_1200:
            case RULE_EXPRESSION_1200FX:
            case RULE_EXPRESSION_1100:
            case RULE_EXPRESSION_1000:
            case RULE_EXPRESSION_900FX:
            case RULE_EXPRESSION_700:
            case RULE_EXPRESSION_600:
            case RULE_EXPRESSION_500:
            case RULE_EXPRESSION_400:
            case RULE_EXPRESSION_200:
            case RULE_ATOM:
            case 20:
                {
                alt16=1;
                }
                break;
            case RULE_VARIABLE:
                {
                alt16=2;
                }
                break;
            case RULE_STRING:
                {
                alt16=3;
                }
                break;
            case RULE_DIGIT:
            case 26:
                {
                alt16=4;
                }
                break;
            case 23:
                {
                alt16=5;
                }
                break;
            case 21:
                {
                alt16=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 16, 0, input);

                throw nvae;
            }

            switch (alt16) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:828:2: ( ( ( (lv_ops_0_1= '.' | lv_ops_0_2= RULE_ATOM | lv_ops_0_3= RULE_EXPRESSION_1200 | lv_ops_0_4= RULE_EXPRESSION_1200FX | lv_ops_0_5= RULE_EXPRESSION_1100 | lv_ops_0_6= RULE_EXPRESSION_1000 | lv_ops_0_7= RULE_EXPRESSION_900FX | lv_ops_0_8= RULE_EXPRESSION_700 | lv_ops_0_9= RULE_EXPRESSION_600 | lv_ops_0_10= RULE_EXPRESSION_500 | lv_ops_0_11= RULE_EXPRESSION_400 | lv_ops_0_12= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' )? )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:828:2: ( ( ( (lv_ops_0_1= '.' | lv_ops_0_2= RULE_ATOM | lv_ops_0_3= RULE_EXPRESSION_1200 | lv_ops_0_4= RULE_EXPRESSION_1200FX | lv_ops_0_5= RULE_EXPRESSION_1100 | lv_ops_0_6= RULE_EXPRESSION_1000 | lv_ops_0_7= RULE_EXPRESSION_900FX | lv_ops_0_8= RULE_EXPRESSION_700 | lv_ops_0_9= RULE_EXPRESSION_600 | lv_ops_0_10= RULE_EXPRESSION_500 | lv_ops_0_11= RULE_EXPRESSION_400 | lv_ops_0_12= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' )? )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:828:3: ( ( (lv_ops_0_1= '.' | lv_ops_0_2= RULE_ATOM | lv_ops_0_3= RULE_EXPRESSION_1200 | lv_ops_0_4= RULE_EXPRESSION_1200FX | lv_ops_0_5= RULE_EXPRESSION_1100 | lv_ops_0_6= RULE_EXPRESSION_1000 | lv_ops_0_7= RULE_EXPRESSION_900FX | lv_ops_0_8= RULE_EXPRESSION_700 | lv_ops_0_9= RULE_EXPRESSION_600 | lv_ops_0_10= RULE_EXPRESSION_500 | lv_ops_0_11= RULE_EXPRESSION_400 | lv_ops_0_12= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' )?
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:828:3: ( ( (lv_ops_0_1= '.' | lv_ops_0_2= RULE_ATOM | lv_ops_0_3= RULE_EXPRESSION_1200 | lv_ops_0_4= RULE_EXPRESSION_1200FX | lv_ops_0_5= RULE_EXPRESSION_1100 | lv_ops_0_6= RULE_EXPRESSION_1000 | lv_ops_0_7= RULE_EXPRESSION_900FX | lv_ops_0_8= RULE_EXPRESSION_700 | lv_ops_0_9= RULE_EXPRESSION_600 | lv_ops_0_10= RULE_EXPRESSION_500 | lv_ops_0_11= RULE_EXPRESSION_400 | lv_ops_0_12= RULE_EXPRESSION_200 ) ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:829:1: ( (lv_ops_0_1= '.' | lv_ops_0_2= RULE_ATOM | lv_ops_0_3= RULE_EXPRESSION_1200 | lv_ops_0_4= RULE_EXPRESSION_1200FX | lv_ops_0_5= RULE_EXPRESSION_1100 | lv_ops_0_6= RULE_EXPRESSION_1000 | lv_ops_0_7= RULE_EXPRESSION_900FX | lv_ops_0_8= RULE_EXPRESSION_700 | lv_ops_0_9= RULE_EXPRESSION_600 | lv_ops_0_10= RULE_EXPRESSION_500 | lv_ops_0_11= RULE_EXPRESSION_400 | lv_ops_0_12= RULE_EXPRESSION_200 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:829:1: ( (lv_ops_0_1= '.' | lv_ops_0_2= RULE_ATOM | lv_ops_0_3= RULE_EXPRESSION_1200 | lv_ops_0_4= RULE_EXPRESSION_1200FX | lv_ops_0_5= RULE_EXPRESSION_1100 | lv_ops_0_6= RULE_EXPRESSION_1000 | lv_ops_0_7= RULE_EXPRESSION_900FX | lv_ops_0_8= RULE_EXPRESSION_700 | lv_ops_0_9= RULE_EXPRESSION_600 | lv_ops_0_10= RULE_EXPRESSION_500 | lv_ops_0_11= RULE_EXPRESSION_400 | lv_ops_0_12= RULE_EXPRESSION_200 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:830:1: (lv_ops_0_1= '.' | lv_ops_0_2= RULE_ATOM | lv_ops_0_3= RULE_EXPRESSION_1200 | lv_ops_0_4= RULE_EXPRESSION_1200FX | lv_ops_0_5= RULE_EXPRESSION_1100 | lv_ops_0_6= RULE_EXPRESSION_1000 | lv_ops_0_7= RULE_EXPRESSION_900FX | lv_ops_0_8= RULE_EXPRESSION_700 | lv_ops_0_9= RULE_EXPRESSION_600 | lv_ops_0_10= RULE_EXPRESSION_500 | lv_ops_0_11= RULE_EXPRESSION_400 | lv_ops_0_12= RULE_EXPRESSION_200 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:830:1: (lv_ops_0_1= '.' | lv_ops_0_2= RULE_ATOM | lv_ops_0_3= RULE_EXPRESSION_1200 | lv_ops_0_4= RULE_EXPRESSION_1200FX | lv_ops_0_5= RULE_EXPRESSION_1100 | lv_ops_0_6= RULE_EXPRESSION_1000 | lv_ops_0_7= RULE_EXPRESSION_900FX | lv_ops_0_8= RULE_EXPRESSION_700 | lv_ops_0_9= RULE_EXPRESSION_600 | lv_ops_0_10= RULE_EXPRESSION_500 | lv_ops_0_11= RULE_EXPRESSION_400 | lv_ops_0_12= RULE_EXPRESSION_200 )
                    int alt12=12;
                    switch ( input.LA(1) ) {
                    case 20:
                        {
                        alt12=1;
                        }
                        break;
                    case RULE_ATOM:
                        {
                        alt12=2;
                        }
                        break;
                    case RULE_EXPRESSION_1200:
                        {
                        alt12=3;
                        }
                        break;
                    case RULE_EXPRESSION_1200FX:
                        {
                        alt12=4;
                        }
                        break;
                    case RULE_EXPRESSION_1100:
                        {
                        alt12=5;
                        }
                        break;
                    case RULE_EXPRESSION_1000:
                        {
                        alt12=6;
                        }
                        break;
                    case RULE_EXPRESSION_900FX:
                        {
                        alt12=7;
                        }
                        break;
                    case RULE_EXPRESSION_700:
                        {
                        alt12=8;
                        }
                        break;
                    case RULE_EXPRESSION_600:
                        {
                        alt12=9;
                        }
                        break;
                    case RULE_EXPRESSION_500:
                        {
                        alt12=10;
                        }
                        break;
                    case RULE_EXPRESSION_400:
                        {
                        alt12=11;
                        }
                        break;
                    case RULE_EXPRESSION_200:
                        {
                        alt12=12;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 12, 0, input);

                        throw nvae;
                    }

                    switch (alt12) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:831:3: lv_ops_0_1= '.'
                            {
                            lv_ops_0_1=(Token)match(input,20,FOLLOW_20_in_ruleExpression01693); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_ops_0_1, grammarAccess.getExpression0Access().getOpsFullStopKeyword_0_0_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		addWithLastConsumed(current, "ops", lv_ops_0_1, null);
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:843:8: lv_ops_0_2= RULE_ATOM
                            {
                            lv_ops_0_2=(Token)match(input,RULE_ATOM,FOLLOW_RULE_ATOM_in_ruleExpression01721); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_ops_0_2, grammarAccess.getExpression0Access().getOpsATOMTerminalRuleCall_0_0_0_1()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		addWithLastConsumed(
                                     			current, 
                                     			"ops",
                                      		lv_ops_0_2, 
                                      		"ATOM");
                              	    
                            }

                            }
                            break;
                        case 3 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:858:8: lv_ops_0_3= RULE_EXPRESSION_1200
                            {
                            lv_ops_0_3=(Token)match(input,RULE_EXPRESSION_1200,FOLLOW_RULE_EXPRESSION_1200_in_ruleExpression01741); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_ops_0_3, grammarAccess.getExpression0Access().getOpsEXPRESSION_1200TerminalRuleCall_0_0_0_2()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		addWithLastConsumed(
                                     			current, 
                                     			"ops",
                                      		lv_ops_0_3, 
                                      		"EXPRESSION_1200");
                              	    
                            }

                            }
                            break;
                        case 4 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:873:8: lv_ops_0_4= RULE_EXPRESSION_1200FX
                            {
                            lv_ops_0_4=(Token)match(input,RULE_EXPRESSION_1200FX,FOLLOW_RULE_EXPRESSION_1200FX_in_ruleExpression01761); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_ops_0_4, grammarAccess.getExpression0Access().getOpsEXPRESSION_1200FXTerminalRuleCall_0_0_0_3()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		addWithLastConsumed(
                                     			current, 
                                     			"ops",
                                      		lv_ops_0_4, 
                                      		"EXPRESSION_1200FX");
                              	    
                            }

                            }
                            break;
                        case 5 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:888:8: lv_ops_0_5= RULE_EXPRESSION_1100
                            {
                            lv_ops_0_5=(Token)match(input,RULE_EXPRESSION_1100,FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression01781); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_ops_0_5, grammarAccess.getExpression0Access().getOpsEXPRESSION_1100TerminalRuleCall_0_0_0_4()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		addWithLastConsumed(
                                     			current, 
                                     			"ops",
                                      		lv_ops_0_5, 
                                      		"EXPRESSION_1100");
                              	    
                            }

                            }
                            break;
                        case 6 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:903:8: lv_ops_0_6= RULE_EXPRESSION_1000
                            {
                            lv_ops_0_6=(Token)match(input,RULE_EXPRESSION_1000,FOLLOW_RULE_EXPRESSION_1000_in_ruleExpression01801); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_ops_0_6, grammarAccess.getExpression0Access().getOpsEXPRESSION_1000TerminalRuleCall_0_0_0_5()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		addWithLastConsumed(
                                     			current, 
                                     			"ops",
                                      		lv_ops_0_6, 
                                      		"EXPRESSION_1000");
                              	    
                            }

                            }
                            break;
                        case 7 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:918:8: lv_ops_0_7= RULE_EXPRESSION_900FX
                            {
                            lv_ops_0_7=(Token)match(input,RULE_EXPRESSION_900FX,FOLLOW_RULE_EXPRESSION_900FX_in_ruleExpression01821); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_ops_0_7, grammarAccess.getExpression0Access().getOpsEXPRESSION_900FXTerminalRuleCall_0_0_0_6()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		addWithLastConsumed(
                                     			current, 
                                     			"ops",
                                      		lv_ops_0_7, 
                                      		"EXPRESSION_900FX");
                              	    
                            }

                            }
                            break;
                        case 8 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:933:8: lv_ops_0_8= RULE_EXPRESSION_700
                            {
                            lv_ops_0_8=(Token)match(input,RULE_EXPRESSION_700,FOLLOW_RULE_EXPRESSION_700_in_ruleExpression01841); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_ops_0_8, grammarAccess.getExpression0Access().getOpsEXPRESSION_700TerminalRuleCall_0_0_0_7()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		addWithLastConsumed(
                                     			current, 
                                     			"ops",
                                      		lv_ops_0_8, 
                                      		"EXPRESSION_700");
                              	    
                            }

                            }
                            break;
                        case 9 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:948:8: lv_ops_0_9= RULE_EXPRESSION_600
                            {
                            lv_ops_0_9=(Token)match(input,RULE_EXPRESSION_600,FOLLOW_RULE_EXPRESSION_600_in_ruleExpression01861); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_ops_0_9, grammarAccess.getExpression0Access().getOpsEXPRESSION_600TerminalRuleCall_0_0_0_8()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		addWithLastConsumed(
                                     			current, 
                                     			"ops",
                                      		lv_ops_0_9, 
                                      		"EXPRESSION_600");
                              	    
                            }

                            }
                            break;
                        case 10 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:963:8: lv_ops_0_10= RULE_EXPRESSION_500
                            {
                            lv_ops_0_10=(Token)match(input,RULE_EXPRESSION_500,FOLLOW_RULE_EXPRESSION_500_in_ruleExpression01881); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_ops_0_10, grammarAccess.getExpression0Access().getOpsEXPRESSION_500TerminalRuleCall_0_0_0_9()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		addWithLastConsumed(
                                     			current, 
                                     			"ops",
                                      		lv_ops_0_10, 
                                      		"EXPRESSION_500");
                              	    
                            }

                            }
                            break;
                        case 11 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:978:8: lv_ops_0_11= RULE_EXPRESSION_400
                            {
                            lv_ops_0_11=(Token)match(input,RULE_EXPRESSION_400,FOLLOW_RULE_EXPRESSION_400_in_ruleExpression01901); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_ops_0_11, grammarAccess.getExpression0Access().getOpsEXPRESSION_400TerminalRuleCall_0_0_0_10()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		addWithLastConsumed(
                                     			current, 
                                     			"ops",
                                      		lv_ops_0_11, 
                                      		"EXPRESSION_400");
                              	    
                            }

                            }
                            break;
                        case 12 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:993:8: lv_ops_0_12= RULE_EXPRESSION_200
                            {
                            lv_ops_0_12=(Token)match(input,RULE_EXPRESSION_200,FOLLOW_RULE_EXPRESSION_200_in_ruleExpression01921); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_ops_0_12, grammarAccess.getExpression0Access().getOpsEXPRESSION_200TerminalRuleCall_0_0_0_11()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		addWithLastConsumed(
                                     			current, 
                                     			"ops",
                                      		lv_ops_0_12, 
                                      		"EXPRESSION_200");
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1011:2: ( ( (lv_prefix_1_0= '(' ) ) ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0==21) ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1011:3: ( (lv_prefix_1_0= '(' ) ) ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')'
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1011:3: ( (lv_prefix_1_0= '(' ) )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1012:1: (lv_prefix_1_0= '(' )
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1012:1: (lv_prefix_1_0= '(' )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1013:3: lv_prefix_1_0= '('
                            {
                            lv_prefix_1_0=(Token)match(input,21,FOLLOW_21_in_ruleExpression01948); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_prefix_1_0, grammarAccess.getExpression0Access().getPrefixLeftParenthesisKeyword_0_1_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(current, "prefix", true, "(");
                              	    
                            }

                            }


                            }

                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1026:2: ( (lv_exps_2_0= ruleExpressionINF ) )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1027:1: (lv_exps_2_0= ruleExpressionINF )
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1027:1: (lv_exps_2_0= ruleExpressionINF )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1028:3: lv_exps_2_0= ruleExpressionINF
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getExpression0Access().getExpsExpressionINFParserRuleCall_0_1_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression01982);
                            lv_exps_2_0=ruleExpressionINF();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                              	        }
                                     		add(
                                     			current, 
                                     			"exps",
                                      		lv_exps_2_0, 
                                      		"ExpressionINF");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            otherlv_3=(Token)match(input,22,FOLLOW_22_in_ruleExpression01994); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_3, grammarAccess.getExpression0Access().getRightParenthesisKeyword_0_1_2());
                                  
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1049:6: ( (lv_variable_4_0= RULE_VARIABLE ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1049:6: ( (lv_variable_4_0= RULE_VARIABLE ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1050:1: (lv_variable_4_0= RULE_VARIABLE )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1050:1: (lv_variable_4_0= RULE_VARIABLE )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1051:3: lv_variable_4_0= RULE_VARIABLE
                    {
                    lv_variable_4_0=(Token)match(input,RULE_VARIABLE,FOLLOW_RULE_VARIABLE_in_ruleExpression02020); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_variable_4_0, grammarAccess.getExpression0Access().getVariableVARIABLETerminalRuleCall_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression0Rule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"variable",
                              		lv_variable_4_0, 
                              		"VARIABLE");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1068:6: ( (lv_string_5_0= RULE_STRING ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1068:6: ( (lv_string_5_0= RULE_STRING ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1069:1: (lv_string_5_0= RULE_STRING )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1069:1: (lv_string_5_0= RULE_STRING )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1070:3: lv_string_5_0= RULE_STRING
                    {
                    lv_string_5_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleExpression02048); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_string_5_0, grammarAccess.getExpression0Access().getStringSTRINGTerminalRuleCall_2_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression0Rule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"string",
                              		lv_string_5_0, 
                              		"STRING");
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1087:6: ( (lv_number_6_0= ruleNUMBER ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1087:6: ( (lv_number_6_0= ruleNUMBER ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1088:1: (lv_number_6_0= ruleNUMBER )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1088:1: (lv_number_6_0= ruleNUMBER )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1089:3: lv_number_6_0= ruleNUMBER
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression0Access().getNumberNUMBERParserRuleCall_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNUMBER_in_ruleExpression02080);
                    lv_number_6_0=ruleNUMBER();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                      	        }
                             		set(
                             			current, 
                             			"number",
                              		lv_number_6_0, 
                              		"NUMBER");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1106:6: ( ( (lv_list_7_0= '[' ) ) ( ( (lv_heads_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tails_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1106:6: ( ( (lv_list_7_0= '[' ) ) ( ( (lv_heads_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tails_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1106:7: ( (lv_list_7_0= '[' ) ) ( ( (lv_heads_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tails_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']'
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1106:7: ( (lv_list_7_0= '[' ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1107:1: (lv_list_7_0= '[' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1107:1: (lv_list_7_0= '[' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1108:3: lv_list_7_0= '['
                    {
                    lv_list_7_0=(Token)match(input,23,FOLLOW_23_in_ruleExpression02105); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_list_7_0, grammarAccess.getExpression0Access().getListLeftSquareBracketKeyword_4_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression0Rule());
                      	        }
                             		setWithLastConsumed(current, "list", true, "[");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1121:2: ( ( (lv_heads_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tails_10_0= ruleExpressionINF ) ) )? )?
                    int alt15=2;
                    int LA15_0 = input.LA(1);

                    if ( ((LA15_0>=RULE_EXPRESSION_1200 && LA15_0<=RULE_DIGIT)||(LA15_0>=20 && LA15_0<=21)||LA15_0==23||LA15_0==26) ) {
                        alt15=1;
                    }
                    switch (alt15) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1121:3: ( (lv_heads_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tails_10_0= ruleExpressionINF ) ) )?
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1121:3: ( (lv_heads_8_0= ruleExpressionINF ) )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1122:1: (lv_heads_8_0= ruleExpressionINF )
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1122:1: (lv_heads_8_0= ruleExpressionINF )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1123:3: lv_heads_8_0= ruleExpressionINF
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getExpression0Access().getHeadsExpressionINFParserRuleCall_4_1_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression02140);
                            lv_heads_8_0=ruleExpressionINF();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                              	        }
                                     		add(
                                     			current, 
                                     			"heads",
                                      		lv_heads_8_0, 
                                      		"ExpressionINF");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1139:2: (otherlv_9= '|' ( (lv_tails_10_0= ruleExpressionINF ) ) )?
                            int alt14=2;
                            int LA14_0 = input.LA(1);

                            if ( (LA14_0==24) ) {
                                alt14=1;
                            }
                            switch (alt14) {
                                case 1 :
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1139:4: otherlv_9= '|' ( (lv_tails_10_0= ruleExpressionINF ) )
                                    {
                                    otherlv_9=(Token)match(input,24,FOLLOW_24_in_ruleExpression02153); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_9, grammarAccess.getExpression0Access().getVerticalLineKeyword_4_1_1_0());
                                          
                                    }
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1143:1: ( (lv_tails_10_0= ruleExpressionINF ) )
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1144:1: (lv_tails_10_0= ruleExpressionINF )
                                    {
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1144:1: (lv_tails_10_0= ruleExpressionINF )
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1145:3: lv_tails_10_0= ruleExpressionINF
                                    {
                                    if ( state.backtracking==0 ) {
                                       
                                      	        newCompositeNode(grammarAccess.getExpression0Access().getTailsExpressionINFParserRuleCall_4_1_1_1_0()); 
                                      	    
                                    }
                                    pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression02174);
                                    lv_tails_10_0=ruleExpressionINF();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      	        if (current==null) {
                                      	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                                      	        }
                                             		add(
                                             			current, 
                                             			"tails",
                                              		lv_tails_10_0, 
                                              		"ExpressionINF");
                                      	        afterParserOrEnumRuleCall();
                                      	    
                                    }

                                    }


                                    }


                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    otherlv_11=(Token)match(input,25,FOLLOW_25_in_ruleExpression02190); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_11, grammarAccess.getExpression0Access().getRightSquareBracketKeyword_4_2());
                          
                    }

                    }


                    }
                    break;
                case 6 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1166:6: (otherlv_12= '(' ( (lv_exps_13_0= ruleExpressionINF ) ) otherlv_14= ')' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1166:6: (otherlv_12= '(' ( (lv_exps_13_0= ruleExpressionINF ) ) otherlv_14= ')' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1166:8: otherlv_12= '(' ( (lv_exps_13_0= ruleExpressionINF ) ) otherlv_14= ')'
                    {
                    otherlv_12=(Token)match(input,21,FOLLOW_21_in_ruleExpression02210); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getExpression0Access().getLeftParenthesisKeyword_5_0());
                          
                    }
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1170:1: ( (lv_exps_13_0= ruleExpressionINF ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1171:1: (lv_exps_13_0= ruleExpressionINF )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1171:1: (lv_exps_13_0= ruleExpressionINF )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1172:3: lv_exps_13_0= ruleExpressionINF
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression0Access().getExpsExpressionINFParserRuleCall_5_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression02231);
                    lv_exps_13_0=ruleExpressionINF();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                      	        }
                             		add(
                             			current, 
                             			"exps",
                              		lv_exps_13_0, 
                              		"ExpressionINF");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_14=(Token)match(input,22,FOLLOW_22_in_ruleExpression02243); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_14, grammarAccess.getExpression0Access().getRightParenthesisKeyword_5_2());
                          
                    }

                    }


                    }
                    break;

            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression0"


    // $ANTLR start "entryRuleNUMBER"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1200:1: entryRuleNUMBER returns [String current=null] : iv_ruleNUMBER= ruleNUMBER EOF ;
    public final String entryRuleNUMBER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNUMBER = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1201:2: (iv_ruleNUMBER= ruleNUMBER EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1202:2: iv_ruleNUMBER= ruleNUMBER EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNUMBERRule()); 
            }
            pushFollow(FOLLOW_ruleNUMBER_in_entryRuleNUMBER2281);
            iv_ruleNUMBER=ruleNUMBER();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNUMBER.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNUMBER2292); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleNUMBER"


    // $ANTLR start "ruleNUMBER"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1209:1: ruleNUMBER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )? ( (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+ )? ) ;
    public final AntlrDatatypeRuleToken ruleNUMBER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_DIGIT_1=null;
        Token this_DIGIT_3=null;
        Token this_DIGIT_8=null;

         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1212:28: ( ( (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )? ( (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+ )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1213:1: ( (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )? ( (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+ )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1213:1: ( (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )? ( (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+ )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1213:2: (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )? ( (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+ )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1213:2: (kw= '-' )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==26) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1214:2: kw= '-'
                    {
                    kw=(Token)match(input,26,FOLLOW_26_in_ruleNUMBER2331); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getNUMBERAccess().getHyphenMinusKeyword_0()); 
                          
                    }

                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1219:3: (this_DIGIT_1= RULE_DIGIT )+
            int cnt18=0;
            loop18:
            do {
                int alt18=2;
                int LA18_0 = input.LA(1);

                if ( (LA18_0==RULE_DIGIT) ) {
                    alt18=1;
                }


                switch (alt18) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1219:8: this_DIGIT_1= RULE_DIGIT
            	    {
            	    this_DIGIT_1=(Token)match(input,RULE_DIGIT,FOLLOW_RULE_DIGIT_in_ruleNUMBER2349); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_DIGIT_1);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	          newLeafNode(this_DIGIT_1, grammarAccess.getNUMBERAccess().getDIGITTerminalRuleCall_1()); 
            	          
            	    }

            	    }
            	    break;

            	default :
            	    if ( cnt18 >= 1 ) break loop18;
            	    if (state.backtracking>0) {state.failed=true; return current;}
                        EarlyExitException eee =
                            new EarlyExitException(18, input);
                        throw eee;
                }
                cnt18++;
            } while (true);

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1226:3: (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==20) ) {
                int LA20_1 = input.LA(2);

                if ( (LA20_1==RULE_DIGIT) ) {
                    int LA20_3 = input.LA(3);

                    if ( (synpred34_InternalProlog()) ) {
                        alt20=1;
                    }
                }
            }
            switch (alt20) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1227:2: kw= '.' (this_DIGIT_3= RULE_DIGIT )+
                    {
                    kw=(Token)match(input,20,FOLLOW_20_in_ruleNUMBER2370); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getNUMBERAccess().getFullStopKeyword_2_0()); 
                          
                    }
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1232:1: (this_DIGIT_3= RULE_DIGIT )+
                    int cnt19=0;
                    loop19:
                    do {
                        int alt19=2;
                        int LA19_0 = input.LA(1);

                        if ( (LA19_0==RULE_DIGIT) ) {
                            alt19=1;
                        }


                        switch (alt19) {
                    	case 1 :
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1232:6: this_DIGIT_3= RULE_DIGIT
                    	    {
                    	    this_DIGIT_3=(Token)match(input,RULE_DIGIT,FOLLOW_RULE_DIGIT_in_ruleNUMBER2386); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      		current.merge(this_DIGIT_3);
                    	          
                    	    }
                    	    if ( state.backtracking==0 ) {
                    	       
                    	          newLeafNode(this_DIGIT_3, grammarAccess.getNUMBERAccess().getDIGITTerminalRuleCall_2_1()); 
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt19 >= 1 ) break loop19;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(19, input);
                                throw eee;
                        }
                        cnt19++;
                    } while (true);


                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1239:5: ( (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+ )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( ((LA24_0>=27 && LA24_0<=28)) ) {
                alt24=1;
            }
            switch (alt24) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1239:6: (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1239:6: (kw= 'e' | kw= 'E' )
                    int alt21=2;
                    int LA21_0 = input.LA(1);

                    if ( (LA21_0==27) ) {
                        alt21=1;
                    }
                    else if ( (LA21_0==28) ) {
                        alt21=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 21, 0, input);

                        throw nvae;
                    }
                    switch (alt21) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1240:2: kw= 'e'
                            {
                            kw=(Token)match(input,27,FOLLOW_27_in_ruleNUMBER2410); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getNUMBERAccess().getEKeyword_3_0_0()); 
                                  
                            }

                            }
                            break;
                        case 2 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1247:2: kw= 'E'
                            {
                            kw=(Token)match(input,28,FOLLOW_28_in_ruleNUMBER2429); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getNUMBERAccess().getEKeyword_3_0_1()); 
                                  
                            }

                            }
                            break;

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1252:2: (kw= '+' | kw= '-' )?
                    int alt22=3;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==29) ) {
                        alt22=1;
                    }
                    else if ( (LA22_0==26) ) {
                        alt22=2;
                    }
                    switch (alt22) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1253:2: kw= '+'
                            {
                            kw=(Token)match(input,29,FOLLOW_29_in_ruleNUMBER2444); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getNUMBERAccess().getPlusSignKeyword_3_1_0()); 
                                  
                            }

                            }
                            break;
                        case 2 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1260:2: kw= '-'
                            {
                            kw=(Token)match(input,26,FOLLOW_26_in_ruleNUMBER2463); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getNUMBERAccess().getHyphenMinusKeyword_3_1_1()); 
                                  
                            }

                            }
                            break;

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1265:3: (this_DIGIT_8= RULE_DIGIT )+
                    int cnt23=0;
                    loop23:
                    do {
                        int alt23=2;
                        int LA23_0 = input.LA(1);

                        if ( (LA23_0==RULE_DIGIT) ) {
                            alt23=1;
                        }


                        switch (alt23) {
                    	case 1 :
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1265:8: this_DIGIT_8= RULE_DIGIT
                    	    {
                    	    this_DIGIT_8=(Token)match(input,RULE_DIGIT,FOLLOW_RULE_DIGIT_in_ruleNUMBER2481); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      		current.merge(this_DIGIT_8);
                    	          
                    	    }
                    	    if ( state.backtracking==0 ) {
                    	       
                    	          newLeafNode(this_DIGIT_8, grammarAccess.getNUMBERAccess().getDIGITTerminalRuleCall_3_2()); 
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt23 >= 1 ) break loop23;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(23, input);
                                throw eee;
                        }
                        cnt23++;
                    } while (true);


                    }
                    break;

            }


            }


            }

            if ( state.backtracking==0 ) {
               leaveRule(); 
            }
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNUMBER"

    // $ANTLR start synpred3_InternalProlog
    public final void synpred3_InternalProlog_fragment() throws RecognitionException {   
        Token lv_ops_0_0=null;

        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:232:1: ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:232:1: (lv_ops_0_0= RULE_EXPRESSION_1200FX )
        {
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:232:1: (lv_ops_0_0= RULE_EXPRESSION_1200FX )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:233:3: lv_ops_0_0= RULE_EXPRESSION_1200FX
        {
        lv_ops_0_0=(Token)match(input,RULE_EXPRESSION_1200FX,FOLLOW_RULE_EXPRESSION_1200FX_in_synpred3_InternalProlog472); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred3_InternalProlog

    // $ANTLR start synpred6_InternalProlog
    public final void synpred6_InternalProlog_fragment() throws RecognitionException {   
        Token lv_ops_0_0=null;

        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:427:1: ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:427:1: (lv_ops_0_0= RULE_EXPRESSION_900FX )
        {
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:427:1: (lv_ops_0_0= RULE_EXPRESSION_900FX )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:428:3: lv_ops_0_0= RULE_EXPRESSION_900FX
        {
        lv_ops_0_0=(Token)match(input,RULE_EXPRESSION_900FX,FOLLOW_RULE_EXPRESSION_900FX_in_synpred6_InternalProlog869); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred6_InternalProlog

    // $ANTLR start synpred34_InternalProlog
    public final void synpred34_InternalProlog_fragment() throws RecognitionException {   
        Token kw=null;
        Token this_DIGIT_3=null;

        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1227:2: (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1227:2: kw= '.' (this_DIGIT_3= RULE_DIGIT )+
        {
        kw=(Token)match(input,20,FOLLOW_20_in_synpred34_InternalProlog2370); if (state.failed) return ;
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1232:1: (this_DIGIT_3= RULE_DIGIT )+
        int cnt30=0;
        loop30:
        do {
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==RULE_DIGIT) ) {
                alt30=1;
            }


            switch (alt30) {
        	case 1 :
        	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1232:6: this_DIGIT_3= RULE_DIGIT
        	    {
        	    this_DIGIT_3=(Token)match(input,RULE_DIGIT,FOLLOW_RULE_DIGIT_in_synpred34_InternalProlog2386); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    if ( cnt30 >= 1 ) break loop30;
        	    if (state.backtracking>0) {state.failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(30, input);
                    throw eee;
            }
            cnt30++;
        } while (true);


        }
    }
    // $ANTLR end synpred34_InternalProlog

    // Delegated rules

    public final boolean synpred3_InternalProlog() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred3_InternalProlog_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred6_InternalProlog() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred6_InternalProlog_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred34_InternalProlog() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred34_InternalProlog_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA3 dfa3 = new DFA3(this);
    protected DFA6 dfa6 = new DFA6(this);
    static final String DFA3_eotS =
        "\24\uffff";
    static final String DFA3_eofS =
        "\24\uffff";
    static final String DFA3_minS =
        "\1\4\1\0\22\uffff";
    static final String DFA3_maxS =
        "\1\32\1\0\22\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\2\20\uffff\1\1";
    static final String DFA3_specialS =
        "\1\uffff\1\0\22\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\2\1\1\14\2\2\uffff\2\2\1\uffff\1\2\2\uffff\1\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA3_eot = DFA.unpackEncodedString(DFA3_eotS);
    static final short[] DFA3_eof = DFA.unpackEncodedString(DFA3_eofS);
    static final char[] DFA3_min = DFA.unpackEncodedStringToUnsignedChars(DFA3_minS);
    static final char[] DFA3_max = DFA.unpackEncodedStringToUnsignedChars(DFA3_maxS);
    static final short[] DFA3_accept = DFA.unpackEncodedString(DFA3_acceptS);
    static final short[] DFA3_special = DFA.unpackEncodedString(DFA3_specialS);
    static final short[][] DFA3_transition;

    static {
        int numStates = DFA3_transitionS.length;
        DFA3_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA3_transition[i] = DFA.unpackEncodedString(DFA3_transitionS[i]);
        }
    }

    class DFA3 extends DFA {

        public DFA3(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 3;
            this.eot = DFA3_eot;
            this.eof = DFA3_eof;
            this.min = DFA3_min;
            this.max = DFA3_max;
            this.accept = DFA3_accept;
            this.special = DFA3_special;
            this.transition = DFA3_transition;
        }
        public String getDescription() {
            return "231:2: ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA3_1 = input.LA(1);

                         
                        int index3_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred3_InternalProlog()) ) {s = 19;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index3_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 3, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA6_eotS =
        "\24\uffff";
    static final String DFA6_eofS =
        "\24\uffff";
    static final String DFA6_minS =
        "\1\4\1\0\22\uffff";
    static final String DFA6_maxS =
        "\1\32\1\0\22\uffff";
    static final String DFA6_acceptS =
        "\2\uffff\1\2\20\uffff\1\1";
    static final String DFA6_specialS =
        "\1\uffff\1\0\22\uffff}>";
    static final String[] DFA6_transitionS = {
            "\4\2\1\1\11\2\2\uffff\2\2\1\uffff\1\2\2\uffff\1\2",
            "\1\uffff",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA6_eot = DFA.unpackEncodedString(DFA6_eotS);
    static final short[] DFA6_eof = DFA.unpackEncodedString(DFA6_eofS);
    static final char[] DFA6_min = DFA.unpackEncodedStringToUnsignedChars(DFA6_minS);
    static final char[] DFA6_max = DFA.unpackEncodedStringToUnsignedChars(DFA6_maxS);
    static final short[] DFA6_accept = DFA.unpackEncodedString(DFA6_acceptS);
    static final short[] DFA6_special = DFA.unpackEncodedString(DFA6_specialS);
    static final short[][] DFA6_transition;

    static {
        int numStates = DFA6_transitionS.length;
        DFA6_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA6_transition[i] = DFA.unpackEncodedString(DFA6_transitionS[i]);
        }
    }

    class DFA6 extends DFA {

        public DFA6(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 6;
            this.eot = DFA6_eot;
            this.eof = DFA6_eof;
            this.min = DFA6_min;
            this.max = DFA6_max;
            this.accept = DFA6_accept;
            this.special = DFA6_special;
            this.transition = DFA6_transition;
        }
        public String getDescription() {
            return "426:2: ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA6_1 = input.LA(1);

                         
                        int index6_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred6_InternalProlog()) ) {s = 19;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index6_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 6, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_ruleProgram_in_entryRuleProgram81 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleProgram91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleProgram137 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleProgram149 = new BitSet(new long[]{0x0000000004B3FFF2L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_entryRuleExpressionINF186 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionINF196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1200_in_ruleExpressionINF245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1200_in_entryRuleExpression1200279 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1200289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1200fx_in_ruleExpression1200339 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1200_in_ruleExpression1200356 = new BitSet(new long[]{0x0000000004B3FFF0L});
    public static final BitSet FOLLOW_ruleExpression1200fx_in_ruleExpression1200382 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1200fx_in_entryRuleExpression1200fx420 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1200fx430 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1200FX_in_ruleExpression1200fx472 = new BitSet(new long[]{0x0000000004B3FFF0L});
    public static final BitSet FOLLOW_ruleExpression1100_in_ruleExpression1200fx499 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1100_in_entryRuleExpression1100535 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1100545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1000_in_ruleExpression1100595 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression1100612 = new BitSet(new long[]{0x0000000004B3FFF0L});
    public static final BitSet FOLLOW_ruleExpression1000_in_ruleExpression1100638 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_ruleExpression1000_in_entryRuleExpression1000676 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1000686 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression900fx_in_ruleExpression1000736 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1000_in_ruleExpression1000753 = new BitSet(new long[]{0x0000000004B3FFF0L});
    public static final BitSet FOLLOW_ruleExpression900fx_in_ruleExpression1000779 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_ruleExpression900fx_in_entryRuleExpression900fx817 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression900fx827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_900FX_in_ruleExpression900fx869 = new BitSet(new long[]{0x0000000004B3FFF0L});
    public static final BitSet FOLLOW_ruleExpression700_in_ruleExpression900fx896 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression700_in_entryRuleExpression700932 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression700942 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression600_in_ruleExpression700992 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_700_in_ruleExpression7001009 = new BitSet(new long[]{0x0000000004B3FFF0L});
    public static final BitSet FOLLOW_ruleExpression600_in_ruleExpression7001035 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression600_in_entryRuleExpression6001073 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression6001083 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression500_in_ruleExpression6001133 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_600_in_ruleExpression6001150 = new BitSet(new long[]{0x0000000004B3FFF0L});
    public static final BitSet FOLLOW_ruleExpression500_in_ruleExpression6001176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression500_in_entryRuleExpression5001214 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression5001224 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression400_in_ruleExpression5001274 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_500_in_ruleExpression5001291 = new BitSet(new long[]{0x0000000004B3FFF0L});
    public static final BitSet FOLLOW_ruleExpression400_in_ruleExpression5001317 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression400_in_entryRuleExpression4001355 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression4001365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression200_in_ruleExpression4001415 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_400_in_ruleExpression4001432 = new BitSet(new long[]{0x0000000004B3FFF0L});
    public static final BitSet FOLLOW_ruleExpression200_in_ruleExpression4001458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression200_in_entryRuleExpression2001496 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression2001506 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression0_in_ruleExpression2001556 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_200_in_ruleExpression2001573 = new BitSet(new long[]{0x0000000004B3FFF0L});
    public static final BitSet FOLLOW_ruleExpression0_in_ruleExpression2001599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression0_in_entryRuleExpression01637 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression01647 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleExpression01693 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_RULE_ATOM_in_ruleExpression01721 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1200_in_ruleExpression01741 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1200FX_in_ruleExpression01761 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression01781 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1000_in_ruleExpression01801 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_900FX_in_ruleExpression01821 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_700_in_ruleExpression01841 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_600_in_ruleExpression01861 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_500_in_ruleExpression01881 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_400_in_ruleExpression01901 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_200_in_ruleExpression01921 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_ruleExpression01948 = new BitSet(new long[]{0x0000000004F3FFF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression01982 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleExpression01994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_VARIABLE_in_ruleExpression02020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleExpression02048 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNUMBER_in_ruleExpression02080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleExpression02105 = new BitSet(new long[]{0x0000000007B3FFF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression02140 = new BitSet(new long[]{0x0000000003000000L});
    public static final BitSet FOLLOW_24_in_ruleExpression02153 = new BitSet(new long[]{0x0000000006B3FFF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression02174 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleExpression02190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleExpression02210 = new BitSet(new long[]{0x0000000004F3FFF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression02231 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleExpression02243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNUMBER_in_entryRuleNUMBER2281 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNUMBER2292 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleNUMBER2331 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RULE_DIGIT_in_ruleNUMBER2349 = new BitSet(new long[]{0x0000000018120002L});
    public static final BitSet FOLLOW_20_in_ruleNUMBER2370 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RULE_DIGIT_in_ruleNUMBER2386 = new BitSet(new long[]{0x0000000018020002L});
    public static final BitSet FOLLOW_27_in_ruleNUMBER2410 = new BitSet(new long[]{0x0000000024020000L});
    public static final BitSet FOLLOW_28_in_ruleNUMBER2429 = new BitSet(new long[]{0x0000000024020000L});
    public static final BitSet FOLLOW_29_in_ruleNUMBER2444 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_26_in_ruleNUMBER2463 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RULE_DIGIT_in_ruleNUMBER2481 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1200FX_in_synpred3_InternalProlog472 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_900FX_in_synpred6_InternalProlog869 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_synpred34_InternalProlog2370 = new BitSet(new long[]{0x0000000000020000L});
    public static final BitSet FOLLOW_RULE_DIGIT_in_synpred34_InternalProlog2386 = new BitSet(new long[]{0x0000000000020002L});

}