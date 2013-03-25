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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_EXPRESSION_1200", "RULE_EXPRESSION_1200FX", "RULE_EXPRESSION_1100", "RULE_EXPRESSION_1050", "RULE_EXPRESSION_1000", "RULE_EXPRESSION_900FX", "RULE_EXPRESSION_700", "RULE_EXPRESSION_600", "RULE_EXPRESSION_500", "RULE_EXPRESSION_400", "RULE_EXPRESSION_200", "RULE_ATOM", "RULE_VARIABLE", "RULE_STRING", "RULE_DIGIT", "RULE_WHITESPACE", "RULE_SINGLE_LINE_COMMENT", "'.'", "'!'", "'('", "')'", "'['", "'|'", "']'", "'-'", "'e'", "'E'", "'+'"
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
    public static final int RULE_EXPRESSION_600=11;
    public static final int RULE_EXPRESSION_500=12;
    public static final int RULE_EXPRESSION_400=13;
    public static final int RULE_SINGLE_LINE_COMMENT=20;
    public static final int RULE_ATOM=15;
    public static final int EOF=-1;
    public static final int RULE_EXPRESSION_900FX=9;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_STRING=17;
    public static final int RULE_EXPRESSION_200=14;
    public static final int RULE_VARIABLE=16;
    public static final int RULE_EXPRESSION_1000=8;
    public static final int RULE_EXPRESSION_1100=6;
    public static final int RULE_WHITESPACE=19;
    public static final int RULE_DIGIT=18;
    public static final int RULE_EXPRESSION_1050=7;
    public static final int RULE_EXPRESSION_700=10;

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

                if ( ((LA1_0>=RULE_EXPRESSION_1200 && LA1_0<=RULE_DIGIT)||(LA1_0>=21 && LA1_0<=23)||LA1_0==25||LA1_0==28) ) {
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

            	    otherlv_1=(Token)match(input,21,FOLLOW_21_in_ruleProgram149); if (state.failed) return current;
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:158:1: ruleExpression1200 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression1200fx ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) ) )? ) ;
    public final EObject ruleExpression1200() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:161:28: ( ( ( (lv_exps_0_0= ruleExpression1200fx ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:162:1: ( ( (lv_exps_0_0= ruleExpression1200fx ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:162:1: ( ( (lv_exps_0_0= ruleExpression1200fx ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:162:2: ( (lv_exps_0_0= ruleExpression1200fx ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) ) )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:162:2: ( (lv_exps_0_0= ruleExpression1200fx ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:163:1: (lv_exps_0_0= ruleExpression1200fx )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:163:1: (lv_exps_0_0= ruleExpression1200fx )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:164:3: lv_exps_0_0= ruleExpression1200fx
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression1200Access().getExpsExpression1200fxParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression1200fx_in_ruleExpression1200335);
            lv_exps_0_0=ruleExpression1200fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression1200Rule());
              	        }
                     		add(
                     			current, 
                     			"exps",
                      		lv_exps_0_0, 
                      		"Expression1200fx");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:180:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_EXPRESSION_1200) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:180:3: ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) ) ( (lv_exps_2_0= ruleExpression1200fx ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:180:3: ( (lv_ops_1_0= RULE_EXPRESSION_1200 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:181:1: (lv_ops_1_0= RULE_EXPRESSION_1200 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:181:1: (lv_ops_1_0= RULE_EXPRESSION_1200 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:182:3: lv_ops_1_0= RULE_EXPRESSION_1200
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_1200,FOLLOW_RULE_EXPRESSION_1200_in_ruleExpression1200353); if (state.failed) return current;
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

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:198:2: ( (lv_exps_2_0= ruleExpression1200fx ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:199:1: (lv_exps_2_0= ruleExpression1200fx )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:199:1: (lv_exps_2_0= ruleExpression1200fx )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:200:3: lv_exps_2_0= ruleExpression1200fx
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression1200Access().getExpsExpression1200fxParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression1200fx_in_ruleExpression1200379);
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:224:1: entryRuleExpression1200fx returns [EObject current=null] : iv_ruleExpression1200fx= ruleExpression1200fx EOF ;
    public final EObject entryRuleExpression1200fx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1200fx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:225:2: (iv_ruleExpression1200fx= ruleExpression1200fx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:226:2: iv_ruleExpression1200fx= ruleExpression1200fx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1200fxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression1200fx_in_entryRuleExpression1200fx417);
            iv_ruleExpression1200fx=ruleExpression1200fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1200fx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1200fx427); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:233:1: ruleExpression1200fx returns [EObject current=null] : ( ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )? ( (lv_exps_1_0= ruleExpression1100 ) ) ) ;
    public final EObject ruleExpression1200fx() throws RecognitionException {
        EObject current = null;

        Token lv_ops_0_0=null;
        EObject lv_exps_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:236:28: ( ( ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )? ( (lv_exps_1_0= ruleExpression1100 ) ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:237:1: ( ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )? ( (lv_exps_1_0= ruleExpression1100 ) ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:237:1: ( ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )? ( (lv_exps_1_0= ruleExpression1100 ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:237:2: ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )? ( (lv_exps_1_0= ruleExpression1100 ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:237:2: ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )?
            int alt3=2;
            alt3 = dfa3.predict(input);
            switch (alt3) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:238:1: (lv_ops_0_0= RULE_EXPRESSION_1200FX )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:238:1: (lv_ops_0_0= RULE_EXPRESSION_1200FX )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:239:3: lv_ops_0_0= RULE_EXPRESSION_1200FX
                    {
                    lv_ops_0_0=(Token)match(input,RULE_EXPRESSION_1200FX,FOLLOW_RULE_EXPRESSION_1200FX_in_ruleExpression1200fx469); if (state.failed) return current;
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

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:255:3: ( (lv_exps_1_0= ruleExpression1100 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:256:1: (lv_exps_1_0= ruleExpression1100 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:256:1: (lv_exps_1_0= ruleExpression1100 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:257:3: lv_exps_1_0= ruleExpression1100
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression1200fxAccess().getExpsExpression1100ParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression1100_in_ruleExpression1200fx496);
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:281:1: entryRuleExpression1100 returns [EObject current=null] : iv_ruleExpression1100= ruleExpression1100 EOF ;
    public final EObject entryRuleExpression1100() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1100 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:282:2: (iv_ruleExpression1100= ruleExpression1100 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:283:2: iv_ruleExpression1100= ruleExpression1100 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1100Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression1100_in_entryRuleExpression1100532);
            iv_ruleExpression1100=ruleExpression1100();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1100; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1100542); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:290:1: ruleExpression1100 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression1050 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1050 ) ) )* ) ;
    public final EObject ruleExpression1100() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:293:28: ( ( ( (lv_exps_0_0= ruleExpression1050 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1050 ) ) )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:294:1: ( ( (lv_exps_0_0= ruleExpression1050 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1050 ) ) )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:294:1: ( ( (lv_exps_0_0= ruleExpression1050 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1050 ) ) )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:294:2: ( (lv_exps_0_0= ruleExpression1050 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1050 ) ) )*
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:294:2: ( (lv_exps_0_0= ruleExpression1050 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:295:1: (lv_exps_0_0= ruleExpression1050 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:295:1: (lv_exps_0_0= ruleExpression1050 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:296:3: lv_exps_0_0= ruleExpression1050
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression1100Access().getExpsExpression1050ParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression1050_in_ruleExpression1100588);
            lv_exps_0_0=ruleExpression1050();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression1100Rule());
              	        }
                     		add(
                     			current, 
                     			"exps",
                      		lv_exps_0_0, 
                      		"Expression1050");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:312:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1050 ) ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==RULE_EXPRESSION_1100) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:312:3: ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression1050 ) )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:312:3: ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:313:1: (lv_ops_1_0= RULE_EXPRESSION_1100 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:313:1: (lv_ops_1_0= RULE_EXPRESSION_1100 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:314:3: lv_ops_1_0= RULE_EXPRESSION_1100
            	    {
            	    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_1100,FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression1100606); if (state.failed) return current;
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

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:330:2: ( (lv_exps_2_0= ruleExpression1050 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:331:1: (lv_exps_2_0= ruleExpression1050 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:331:1: (lv_exps_2_0= ruleExpression1050 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:332:3: lv_exps_2_0= ruleExpression1050
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExpression1100Access().getExpsExpression1050ParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpression1050_in_ruleExpression1100632);
            	    lv_exps_2_0=ruleExpression1050();

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
            	              		"Expression1050");
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


    // $ANTLR start "entryRuleExpression1050"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:356:1: entryRuleExpression1050 returns [EObject current=null] : iv_ruleExpression1050= ruleExpression1050 EOF ;
    public final EObject entryRuleExpression1050() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1050 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:357:2: (iv_ruleExpression1050= ruleExpression1050 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:358:2: iv_ruleExpression1050= ruleExpression1050 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1050Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression1050_in_entryRuleExpression1050670);
            iv_ruleExpression1050=ruleExpression1050();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1050; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1050680); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression1050"


    // $ANTLR start "ruleExpression1050"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:365:1: ruleExpression1050 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression1000 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1050 ) ) ( (lv_exps_2_0= ruleExpression1000 ) ) )? ) ;
    public final EObject ruleExpression1050() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:368:28: ( ( ( (lv_exps_0_0= ruleExpression1000 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1050 ) ) ( (lv_exps_2_0= ruleExpression1000 ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:369:1: ( ( (lv_exps_0_0= ruleExpression1000 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1050 ) ) ( (lv_exps_2_0= ruleExpression1000 ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:369:1: ( ( (lv_exps_0_0= ruleExpression1000 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1050 ) ) ( (lv_exps_2_0= ruleExpression1000 ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:369:2: ( (lv_exps_0_0= ruleExpression1000 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1050 ) ) ( (lv_exps_2_0= ruleExpression1000 ) ) )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:369:2: ( (lv_exps_0_0= ruleExpression1000 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:370:1: (lv_exps_0_0= ruleExpression1000 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:370:1: (lv_exps_0_0= ruleExpression1000 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:371:3: lv_exps_0_0= ruleExpression1000
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression1050Access().getExpsExpression1000ParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression1000_in_ruleExpression1050726);
            lv_exps_0_0=ruleExpression1000();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression1050Rule());
              	        }
                     		add(
                     			current, 
                     			"exps",
                      		lv_exps_0_0, 
                      		"Expression1000");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:387:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_1050 ) ) ( (lv_exps_2_0= ruleExpression1000 ) ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==RULE_EXPRESSION_1050) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:387:3: ( (lv_ops_1_0= RULE_EXPRESSION_1050 ) ) ( (lv_exps_2_0= ruleExpression1000 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:387:3: ( (lv_ops_1_0= RULE_EXPRESSION_1050 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:388:1: (lv_ops_1_0= RULE_EXPRESSION_1050 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:388:1: (lv_ops_1_0= RULE_EXPRESSION_1050 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:389:3: lv_ops_1_0= RULE_EXPRESSION_1050
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_1050,FOLLOW_RULE_EXPRESSION_1050_in_ruleExpression1050744); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_ops_1_0, grammarAccess.getExpression1050Access().getOpsEXPRESSION_1050TerminalRuleCall_1_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression1050Rule());
                      	        }
                             		addWithLastConsumed(
                             			current, 
                             			"ops",
                              		lv_ops_1_0, 
                              		"EXPRESSION_1050");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:405:2: ( (lv_exps_2_0= ruleExpression1000 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:406:1: (lv_exps_2_0= ruleExpression1000 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:406:1: (lv_exps_2_0= ruleExpression1000 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:407:3: lv_exps_2_0= ruleExpression1000
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression1050Access().getExpsExpression1000ParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression1000_in_ruleExpression1050770);
                    lv_exps_2_0=ruleExpression1000();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression1050Rule());
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
    // $ANTLR end "ruleExpression1050"


    // $ANTLR start "entryRuleExpression1000"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:431:1: entryRuleExpression1000 returns [EObject current=null] : iv_ruleExpression1000= ruleExpression1000 EOF ;
    public final EObject entryRuleExpression1000() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1000 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:432:2: (iv_ruleExpression1000= ruleExpression1000 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:433:2: iv_ruleExpression1000= ruleExpression1000 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1000Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression1000_in_entryRuleExpression1000808);
            iv_ruleExpression1000=ruleExpression1000();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1000; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1000818); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:440:1: ruleExpression1000 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression900fx ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) ) )* ) ;
    public final EObject ruleExpression1000() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:443:28: ( ( ( (lv_exps_0_0= ruleExpression900fx ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) ) )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:444:1: ( ( (lv_exps_0_0= ruleExpression900fx ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) ) )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:444:1: ( ( (lv_exps_0_0= ruleExpression900fx ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) ) )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:444:2: ( (lv_exps_0_0= ruleExpression900fx ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) ) )*
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:444:2: ( (lv_exps_0_0= ruleExpression900fx ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:445:1: (lv_exps_0_0= ruleExpression900fx )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:445:1: (lv_exps_0_0= ruleExpression900fx )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:446:3: lv_exps_0_0= ruleExpression900fx
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression1000Access().getExpsExpression900fxParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression900fx_in_ruleExpression1000864);
            lv_exps_0_0=ruleExpression900fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression1000Rule());
              	        }
                     		add(
                     			current, 
                     			"exps",
                      		lv_exps_0_0, 
                      		"Expression900fx");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:462:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==RULE_EXPRESSION_1000) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:462:3: ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) ) ( (lv_exps_2_0= ruleExpression900fx ) )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:462:3: ( (lv_ops_1_0= RULE_EXPRESSION_1000 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:463:1: (lv_ops_1_0= RULE_EXPRESSION_1000 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:463:1: (lv_ops_1_0= RULE_EXPRESSION_1000 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:464:3: lv_ops_1_0= RULE_EXPRESSION_1000
            	    {
            	    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_1000,FOLLOW_RULE_EXPRESSION_1000_in_ruleExpression1000882); if (state.failed) return current;
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

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:480:2: ( (lv_exps_2_0= ruleExpression900fx ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:481:1: (lv_exps_2_0= ruleExpression900fx )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:481:1: (lv_exps_2_0= ruleExpression900fx )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:482:3: lv_exps_2_0= ruleExpression900fx
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExpression1000Access().getExpsExpression900fxParserRuleCall_1_1_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpression900fx_in_ruleExpression1000908);
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
            	    break loop6;
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:506:1: entryRuleExpression900fx returns [EObject current=null] : iv_ruleExpression900fx= ruleExpression900fx EOF ;
    public final EObject entryRuleExpression900fx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression900fx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:507:2: (iv_ruleExpression900fx= ruleExpression900fx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:508:2: iv_ruleExpression900fx= ruleExpression900fx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression900fxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression900fx_in_entryRuleExpression900fx946);
            iv_ruleExpression900fx=ruleExpression900fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression900fx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression900fx956); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:515:1: ruleExpression900fx returns [EObject current=null] : ( ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )? ( (lv_exps_1_0= ruleExpression700 ) ) ) ;
    public final EObject ruleExpression900fx() throws RecognitionException {
        EObject current = null;

        Token lv_ops_0_0=null;
        EObject lv_exps_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:518:28: ( ( ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )? ( (lv_exps_1_0= ruleExpression700 ) ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:519:1: ( ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )? ( (lv_exps_1_0= ruleExpression700 ) ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:519:1: ( ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )? ( (lv_exps_1_0= ruleExpression700 ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:519:2: ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )? ( (lv_exps_1_0= ruleExpression700 ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:519:2: ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )?
            int alt7=2;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:520:1: (lv_ops_0_0= RULE_EXPRESSION_900FX )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:520:1: (lv_ops_0_0= RULE_EXPRESSION_900FX )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:521:3: lv_ops_0_0= RULE_EXPRESSION_900FX
                    {
                    lv_ops_0_0=(Token)match(input,RULE_EXPRESSION_900FX,FOLLOW_RULE_EXPRESSION_900FX_in_ruleExpression900fx998); if (state.failed) return current;
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

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:537:3: ( (lv_exps_1_0= ruleExpression700 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:538:1: (lv_exps_1_0= ruleExpression700 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:538:1: (lv_exps_1_0= ruleExpression700 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:539:3: lv_exps_1_0= ruleExpression700
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression900fxAccess().getExpsExpression700ParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression700_in_ruleExpression900fx1025);
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:563:1: entryRuleExpression700 returns [EObject current=null] : iv_ruleExpression700= ruleExpression700 EOF ;
    public final EObject entryRuleExpression700() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression700 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:564:2: (iv_ruleExpression700= ruleExpression700 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:565:2: iv_ruleExpression700= ruleExpression700 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression700Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression700_in_entryRuleExpression7001061);
            iv_ruleExpression700=ruleExpression700();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression700; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression7001071); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:572:1: ruleExpression700 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression600 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) ) )? ) ;
    public final EObject ruleExpression700() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:575:28: ( ( ( (lv_exps_0_0= ruleExpression600 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:576:1: ( ( (lv_exps_0_0= ruleExpression600 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:576:1: ( ( (lv_exps_0_0= ruleExpression600 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:576:2: ( (lv_exps_0_0= ruleExpression600 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) ) )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:576:2: ( (lv_exps_0_0= ruleExpression600 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:577:1: (lv_exps_0_0= ruleExpression600 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:577:1: (lv_exps_0_0= ruleExpression600 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:578:3: lv_exps_0_0= ruleExpression600
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression700Access().getExpsExpression600ParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression600_in_ruleExpression7001117);
            lv_exps_0_0=ruleExpression600();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression700Rule());
              	        }
                     		add(
                     			current, 
                     			"exps",
                      		lv_exps_0_0, 
                      		"Expression600");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:594:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) ) )?
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0==RULE_EXPRESSION_700) ) {
                alt8=1;
            }
            switch (alt8) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:594:3: ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression600 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:594:3: ( (lv_ops_1_0= RULE_EXPRESSION_700 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:595:1: (lv_ops_1_0= RULE_EXPRESSION_700 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:595:1: (lv_ops_1_0= RULE_EXPRESSION_700 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:596:3: lv_ops_1_0= RULE_EXPRESSION_700
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_700,FOLLOW_RULE_EXPRESSION_700_in_ruleExpression7001135); if (state.failed) return current;
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

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:612:2: ( (lv_exps_2_0= ruleExpression600 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:613:1: (lv_exps_2_0= ruleExpression600 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:613:1: (lv_exps_2_0= ruleExpression600 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:614:3: lv_exps_2_0= ruleExpression600
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression700Access().getExpsExpression600ParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression600_in_ruleExpression7001161);
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:638:1: entryRuleExpression600 returns [EObject current=null] : iv_ruleExpression600= ruleExpression600 EOF ;
    public final EObject entryRuleExpression600() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression600 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:639:2: (iv_ruleExpression600= ruleExpression600 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:640:2: iv_ruleExpression600= ruleExpression600 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression600Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression600_in_entryRuleExpression6001199);
            iv_ruleExpression600=ruleExpression600();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression600; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression6001209); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:647:1: ruleExpression600 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression500 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? ) ;
    public final EObject ruleExpression600() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:650:28: ( ( ( (lv_exps_0_0= ruleExpression500 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:651:1: ( ( (lv_exps_0_0= ruleExpression500 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:651:1: ( ( (lv_exps_0_0= ruleExpression500 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:651:2: ( (lv_exps_0_0= ruleExpression500 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:651:2: ( (lv_exps_0_0= ruleExpression500 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:652:1: (lv_exps_0_0= ruleExpression500 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:652:1: (lv_exps_0_0= ruleExpression500 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:653:3: lv_exps_0_0= ruleExpression500
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression600Access().getExpsExpression500ParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression500_in_ruleExpression6001255);
            lv_exps_0_0=ruleExpression500();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression600Rule());
              	        }
                     		add(
                     			current, 
                     			"exps",
                      		lv_exps_0_0, 
                      		"Expression500");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:669:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_EXPRESSION_600) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:669:3: ( (lv_ops_1_0= RULE_EXPRESSION_600 ) ) ( (lv_exps_2_0= ruleExpression500 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:669:3: ( (lv_ops_1_0= RULE_EXPRESSION_600 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:670:1: (lv_ops_1_0= RULE_EXPRESSION_600 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:670:1: (lv_ops_1_0= RULE_EXPRESSION_600 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:671:3: lv_ops_1_0= RULE_EXPRESSION_600
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_600,FOLLOW_RULE_EXPRESSION_600_in_ruleExpression6001273); if (state.failed) return current;
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

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:687:2: ( (lv_exps_2_0= ruleExpression500 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:688:1: (lv_exps_2_0= ruleExpression500 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:688:1: (lv_exps_2_0= ruleExpression500 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:689:3: lv_exps_2_0= ruleExpression500
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression600Access().getExpsExpression500ParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression500_in_ruleExpression6001299);
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:713:1: entryRuleExpression500 returns [EObject current=null] : iv_ruleExpression500= ruleExpression500 EOF ;
    public final EObject entryRuleExpression500() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression500 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:714:2: (iv_ruleExpression500= ruleExpression500 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:715:2: iv_ruleExpression500= ruleExpression500 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression500Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression500_in_entryRuleExpression5001337);
            iv_ruleExpression500=ruleExpression500();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression500; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression5001347); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:722:1: ruleExpression500 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression400 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )? ) ;
    public final EObject ruleExpression500() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:725:28: ( ( ( (lv_exps_0_0= ruleExpression400 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:726:1: ( ( (lv_exps_0_0= ruleExpression400 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:726:1: ( ( (lv_exps_0_0= ruleExpression400 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:726:2: ( (lv_exps_0_0= ruleExpression400 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:726:2: ( (lv_exps_0_0= ruleExpression400 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:727:1: (lv_exps_0_0= ruleExpression400 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:727:1: (lv_exps_0_0= ruleExpression400 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:728:3: lv_exps_0_0= ruleExpression400
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression500Access().getExpsExpression400ParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression400_in_ruleExpression5001393);
            lv_exps_0_0=ruleExpression400();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression500Rule());
              	        }
                     		add(
                     			current, 
                     			"exps",
                      		lv_exps_0_0, 
                      		"Expression400");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:744:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_EXPRESSION_500) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:744:3: ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:744:3: ( (lv_ops_1_0= RULE_EXPRESSION_500 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:745:1: (lv_ops_1_0= RULE_EXPRESSION_500 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:745:1: (lv_ops_1_0= RULE_EXPRESSION_500 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:746:3: lv_ops_1_0= RULE_EXPRESSION_500
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_500,FOLLOW_RULE_EXPRESSION_500_in_ruleExpression5001411); if (state.failed) return current;
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

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:762:2: ( (lv_exps_2_0= ruleExpression400 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:763:1: (lv_exps_2_0= ruleExpression400 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:763:1: (lv_exps_2_0= ruleExpression400 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:764:3: lv_exps_2_0= ruleExpression400
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression500Access().getExpsExpression400ParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression400_in_ruleExpression5001437);
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:788:1: entryRuleExpression400 returns [EObject current=null] : iv_ruleExpression400= ruleExpression400 EOF ;
    public final EObject entryRuleExpression400() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression400 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:789:2: (iv_ruleExpression400= ruleExpression400 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:790:2: iv_ruleExpression400= ruleExpression400 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression400Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression400_in_entryRuleExpression4001475);
            iv_ruleExpression400=ruleExpression400();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression400; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression4001485); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:797:1: ruleExpression400 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression200 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )? ) ;
    public final EObject ruleExpression400() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:800:28: ( ( ( (lv_exps_0_0= ruleExpression200 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:801:1: ( ( (lv_exps_0_0= ruleExpression200 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:801:1: ( ( (lv_exps_0_0= ruleExpression200 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:801:2: ( (lv_exps_0_0= ruleExpression200 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:801:2: ( (lv_exps_0_0= ruleExpression200 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:802:1: (lv_exps_0_0= ruleExpression200 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:802:1: (lv_exps_0_0= ruleExpression200 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:803:3: lv_exps_0_0= ruleExpression200
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression400Access().getExpsExpression200ParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression200_in_ruleExpression4001531);
            lv_exps_0_0=ruleExpression200();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression400Rule());
              	        }
                     		add(
                     			current, 
                     			"exps",
                      		lv_exps_0_0, 
                      		"Expression200");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:819:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_EXPRESSION_400) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:819:3: ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:819:3: ( (lv_ops_1_0= RULE_EXPRESSION_400 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:820:1: (lv_ops_1_0= RULE_EXPRESSION_400 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:820:1: (lv_ops_1_0= RULE_EXPRESSION_400 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:821:3: lv_ops_1_0= RULE_EXPRESSION_400
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_400,FOLLOW_RULE_EXPRESSION_400_in_ruleExpression4001549); if (state.failed) return current;
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

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:837:2: ( (lv_exps_2_0= ruleExpression200 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:838:1: (lv_exps_2_0= ruleExpression200 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:838:1: (lv_exps_2_0= ruleExpression200 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:839:3: lv_exps_2_0= ruleExpression200
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression400Access().getExpsExpression200ParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression200_in_ruleExpression4001575);
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:863:1: entryRuleExpression200 returns [EObject current=null] : iv_ruleExpression200= ruleExpression200 EOF ;
    public final EObject entryRuleExpression200() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression200 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:864:2: (iv_ruleExpression200= ruleExpression200 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:865:2: iv_ruleExpression200= ruleExpression200 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression200Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression200_in_entryRuleExpression2001613);
            iv_ruleExpression200=ruleExpression200();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression200; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression2001623); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:872:1: ruleExpression200 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression0 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )? ) ;
    public final EObject ruleExpression200() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:875:28: ( ( ( (lv_exps_0_0= ruleExpression0 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:876:1: ( ( (lv_exps_0_0= ruleExpression0 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:876:1: ( ( (lv_exps_0_0= ruleExpression0 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:876:2: ( (lv_exps_0_0= ruleExpression0 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:876:2: ( (lv_exps_0_0= ruleExpression0 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:877:1: (lv_exps_0_0= ruleExpression0 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:877:1: (lv_exps_0_0= ruleExpression0 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:878:3: lv_exps_0_0= ruleExpression0
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression200Access().getExpsExpression0ParserRuleCall_0_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression0_in_ruleExpression2001669);
            lv_exps_0_0=ruleExpression0();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression200Rule());
              	        }
                     		add(
                     			current, 
                     			"exps",
                      		lv_exps_0_0, 
                      		"Expression0");
              	        afterParserOrEnumRuleCall();
              	    
            }

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:894:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_EXPRESSION_200) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:894:3: ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:894:3: ( (lv_ops_1_0= RULE_EXPRESSION_200 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:895:1: (lv_ops_1_0= RULE_EXPRESSION_200 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:895:1: (lv_ops_1_0= RULE_EXPRESSION_200 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:896:3: lv_ops_1_0= RULE_EXPRESSION_200
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_200,FOLLOW_RULE_EXPRESSION_200_in_ruleExpression2001687); if (state.failed) return current;
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

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:912:2: ( (lv_exps_2_0= ruleExpression0 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:913:1: (lv_exps_2_0= ruleExpression0 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:913:1: (lv_exps_2_0= ruleExpression0 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:914:3: lv_exps_2_0= ruleExpression0
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression200Access().getExpsExpression0ParserRuleCall_1_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression0_in_ruleExpression2001713);
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:938:1: entryRuleExpression0 returns [EObject current=null] : iv_ruleExpression0= ruleExpression0 EOF ;
    public final EObject entryRuleExpression0() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression0 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:939:2: (iv_ruleExpression0= ruleExpression0 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:940:2: iv_ruleExpression0= ruleExpression0 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression0Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression0_in_entryRuleExpression01751);
            iv_ruleExpression0=ruleExpression0();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression0; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression01761); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:947:1: ruleExpression0 returns [EObject current=null] : ( ( ( ( (lv_atom_0_1= '.' | lv_atom_0_2= '!' | lv_atom_0_3= RULE_ATOM | lv_atom_0_4= RULE_EXPRESSION_1200 | lv_atom_0_5= RULE_EXPRESSION_1200FX | lv_atom_0_6= RULE_EXPRESSION_1100 | lv_atom_0_7= RULE_EXPRESSION_1050 | lv_atom_0_8= RULE_EXPRESSION_1000 | lv_atom_0_9= RULE_EXPRESSION_900FX | lv_atom_0_10= RULE_EXPRESSION_700 | lv_atom_0_11= RULE_EXPRESSION_600 | lv_atom_0_12= RULE_EXPRESSION_500 | lv_atom_0_13= RULE_EXPRESSION_400 | lv_atom_0_14= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_terms_2_0= ruleExpressionINF ) ) otherlv_3= ')' )? ) | ( (lv_variable_4_0= RULE_VARIABLE ) ) | ( (lv_string_5_0= RULE_STRING ) ) | ( (lv_number_6_0= ruleNUMBER ) ) | ( ( (lv_list_7_0= '[' ) ) ( ( (lv_head_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tail_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']' ) | ( ( (lv_paren_12_0= '(' ) ) ( (lv_sub_13_0= ruleExpressionINF ) ) otherlv_14= ')' ) ) ;
    public final EObject ruleExpression0() throws RecognitionException {
        EObject current = null;

        Token lv_atom_0_1=null;
        Token lv_atom_0_2=null;
        Token lv_atom_0_3=null;
        Token lv_atom_0_4=null;
        Token lv_atom_0_5=null;
        Token lv_atom_0_6=null;
        Token lv_atom_0_7=null;
        Token lv_atom_0_8=null;
        Token lv_atom_0_9=null;
        Token lv_atom_0_10=null;
        Token lv_atom_0_11=null;
        Token lv_atom_0_12=null;
        Token lv_atom_0_13=null;
        Token lv_atom_0_14=null;
        Token lv_prefix_1_0=null;
        Token otherlv_3=null;
        Token lv_variable_4_0=null;
        Token lv_string_5_0=null;
        Token lv_list_7_0=null;
        Token otherlv_9=null;
        Token otherlv_11=null;
        Token lv_paren_12_0=null;
        Token otherlv_14=null;
        EObject lv_terms_2_0 = null;

        AntlrDatatypeRuleToken lv_number_6_0 = null;

        EObject lv_head_8_0 = null;

        EObject lv_tail_10_0 = null;

        EObject lv_sub_13_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:950:28: ( ( ( ( ( (lv_atom_0_1= '.' | lv_atom_0_2= '!' | lv_atom_0_3= RULE_ATOM | lv_atom_0_4= RULE_EXPRESSION_1200 | lv_atom_0_5= RULE_EXPRESSION_1200FX | lv_atom_0_6= RULE_EXPRESSION_1100 | lv_atom_0_7= RULE_EXPRESSION_1050 | lv_atom_0_8= RULE_EXPRESSION_1000 | lv_atom_0_9= RULE_EXPRESSION_900FX | lv_atom_0_10= RULE_EXPRESSION_700 | lv_atom_0_11= RULE_EXPRESSION_600 | lv_atom_0_12= RULE_EXPRESSION_500 | lv_atom_0_13= RULE_EXPRESSION_400 | lv_atom_0_14= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_terms_2_0= ruleExpressionINF ) ) otherlv_3= ')' )? ) | ( (lv_variable_4_0= RULE_VARIABLE ) ) | ( (lv_string_5_0= RULE_STRING ) ) | ( (lv_number_6_0= ruleNUMBER ) ) | ( ( (lv_list_7_0= '[' ) ) ( ( (lv_head_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tail_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']' ) | ( ( (lv_paren_12_0= '(' ) ) ( (lv_sub_13_0= ruleExpressionINF ) ) otherlv_14= ')' ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:951:1: ( ( ( ( (lv_atom_0_1= '.' | lv_atom_0_2= '!' | lv_atom_0_3= RULE_ATOM | lv_atom_0_4= RULE_EXPRESSION_1200 | lv_atom_0_5= RULE_EXPRESSION_1200FX | lv_atom_0_6= RULE_EXPRESSION_1100 | lv_atom_0_7= RULE_EXPRESSION_1050 | lv_atom_0_8= RULE_EXPRESSION_1000 | lv_atom_0_9= RULE_EXPRESSION_900FX | lv_atom_0_10= RULE_EXPRESSION_700 | lv_atom_0_11= RULE_EXPRESSION_600 | lv_atom_0_12= RULE_EXPRESSION_500 | lv_atom_0_13= RULE_EXPRESSION_400 | lv_atom_0_14= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_terms_2_0= ruleExpressionINF ) ) otherlv_3= ')' )? ) | ( (lv_variable_4_0= RULE_VARIABLE ) ) | ( (lv_string_5_0= RULE_STRING ) ) | ( (lv_number_6_0= ruleNUMBER ) ) | ( ( (lv_list_7_0= '[' ) ) ( ( (lv_head_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tail_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']' ) | ( ( (lv_paren_12_0= '(' ) ) ( (lv_sub_13_0= ruleExpressionINF ) ) otherlv_14= ')' ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:951:1: ( ( ( ( (lv_atom_0_1= '.' | lv_atom_0_2= '!' | lv_atom_0_3= RULE_ATOM | lv_atom_0_4= RULE_EXPRESSION_1200 | lv_atom_0_5= RULE_EXPRESSION_1200FX | lv_atom_0_6= RULE_EXPRESSION_1100 | lv_atom_0_7= RULE_EXPRESSION_1050 | lv_atom_0_8= RULE_EXPRESSION_1000 | lv_atom_0_9= RULE_EXPRESSION_900FX | lv_atom_0_10= RULE_EXPRESSION_700 | lv_atom_0_11= RULE_EXPRESSION_600 | lv_atom_0_12= RULE_EXPRESSION_500 | lv_atom_0_13= RULE_EXPRESSION_400 | lv_atom_0_14= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_terms_2_0= ruleExpressionINF ) ) otherlv_3= ')' )? ) | ( (lv_variable_4_0= RULE_VARIABLE ) ) | ( (lv_string_5_0= RULE_STRING ) ) | ( (lv_number_6_0= ruleNUMBER ) ) | ( ( (lv_list_7_0= '[' ) ) ( ( (lv_head_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tail_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']' ) | ( ( (lv_paren_12_0= '(' ) ) ( (lv_sub_13_0= ruleExpressionINF ) ) otherlv_14= ')' ) )
            int alt17=6;
            switch ( input.LA(1) ) {
            case RULE_EXPRESSION_1200:
            case RULE_EXPRESSION_1200FX:
            case RULE_EXPRESSION_1100:
            case RULE_EXPRESSION_1050:
            case RULE_EXPRESSION_1000:
            case RULE_EXPRESSION_900FX:
            case RULE_EXPRESSION_700:
            case RULE_EXPRESSION_600:
            case RULE_EXPRESSION_500:
            case RULE_EXPRESSION_400:
            case RULE_EXPRESSION_200:
            case RULE_ATOM:
            case 21:
            case 22:
                {
                alt17=1;
                }
                break;
            case RULE_VARIABLE:
                {
                alt17=2;
                }
                break;
            case RULE_STRING:
                {
                alt17=3;
                }
                break;
            case RULE_DIGIT:
            case 28:
                {
                alt17=4;
                }
                break;
            case 25:
                {
                alt17=5;
                }
                break;
            case 23:
                {
                alt17=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 17, 0, input);

                throw nvae;
            }

            switch (alt17) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:951:2: ( ( ( (lv_atom_0_1= '.' | lv_atom_0_2= '!' | lv_atom_0_3= RULE_ATOM | lv_atom_0_4= RULE_EXPRESSION_1200 | lv_atom_0_5= RULE_EXPRESSION_1200FX | lv_atom_0_6= RULE_EXPRESSION_1100 | lv_atom_0_7= RULE_EXPRESSION_1050 | lv_atom_0_8= RULE_EXPRESSION_1000 | lv_atom_0_9= RULE_EXPRESSION_900FX | lv_atom_0_10= RULE_EXPRESSION_700 | lv_atom_0_11= RULE_EXPRESSION_600 | lv_atom_0_12= RULE_EXPRESSION_500 | lv_atom_0_13= RULE_EXPRESSION_400 | lv_atom_0_14= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_terms_2_0= ruleExpressionINF ) ) otherlv_3= ')' )? )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:951:2: ( ( ( (lv_atom_0_1= '.' | lv_atom_0_2= '!' | lv_atom_0_3= RULE_ATOM | lv_atom_0_4= RULE_EXPRESSION_1200 | lv_atom_0_5= RULE_EXPRESSION_1200FX | lv_atom_0_6= RULE_EXPRESSION_1100 | lv_atom_0_7= RULE_EXPRESSION_1050 | lv_atom_0_8= RULE_EXPRESSION_1000 | lv_atom_0_9= RULE_EXPRESSION_900FX | lv_atom_0_10= RULE_EXPRESSION_700 | lv_atom_0_11= RULE_EXPRESSION_600 | lv_atom_0_12= RULE_EXPRESSION_500 | lv_atom_0_13= RULE_EXPRESSION_400 | lv_atom_0_14= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_terms_2_0= ruleExpressionINF ) ) otherlv_3= ')' )? )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:951:3: ( ( (lv_atom_0_1= '.' | lv_atom_0_2= '!' | lv_atom_0_3= RULE_ATOM | lv_atom_0_4= RULE_EXPRESSION_1200 | lv_atom_0_5= RULE_EXPRESSION_1200FX | lv_atom_0_6= RULE_EXPRESSION_1100 | lv_atom_0_7= RULE_EXPRESSION_1050 | lv_atom_0_8= RULE_EXPRESSION_1000 | lv_atom_0_9= RULE_EXPRESSION_900FX | lv_atom_0_10= RULE_EXPRESSION_700 | lv_atom_0_11= RULE_EXPRESSION_600 | lv_atom_0_12= RULE_EXPRESSION_500 | lv_atom_0_13= RULE_EXPRESSION_400 | lv_atom_0_14= RULE_EXPRESSION_200 ) ) ) ( ( (lv_prefix_1_0= '(' ) ) ( (lv_terms_2_0= ruleExpressionINF ) ) otherlv_3= ')' )?
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:951:3: ( ( (lv_atom_0_1= '.' | lv_atom_0_2= '!' | lv_atom_0_3= RULE_ATOM | lv_atom_0_4= RULE_EXPRESSION_1200 | lv_atom_0_5= RULE_EXPRESSION_1200FX | lv_atom_0_6= RULE_EXPRESSION_1100 | lv_atom_0_7= RULE_EXPRESSION_1050 | lv_atom_0_8= RULE_EXPRESSION_1000 | lv_atom_0_9= RULE_EXPRESSION_900FX | lv_atom_0_10= RULE_EXPRESSION_700 | lv_atom_0_11= RULE_EXPRESSION_600 | lv_atom_0_12= RULE_EXPRESSION_500 | lv_atom_0_13= RULE_EXPRESSION_400 | lv_atom_0_14= RULE_EXPRESSION_200 ) ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:952:1: ( (lv_atom_0_1= '.' | lv_atom_0_2= '!' | lv_atom_0_3= RULE_ATOM | lv_atom_0_4= RULE_EXPRESSION_1200 | lv_atom_0_5= RULE_EXPRESSION_1200FX | lv_atom_0_6= RULE_EXPRESSION_1100 | lv_atom_0_7= RULE_EXPRESSION_1050 | lv_atom_0_8= RULE_EXPRESSION_1000 | lv_atom_0_9= RULE_EXPRESSION_900FX | lv_atom_0_10= RULE_EXPRESSION_700 | lv_atom_0_11= RULE_EXPRESSION_600 | lv_atom_0_12= RULE_EXPRESSION_500 | lv_atom_0_13= RULE_EXPRESSION_400 | lv_atom_0_14= RULE_EXPRESSION_200 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:952:1: ( (lv_atom_0_1= '.' | lv_atom_0_2= '!' | lv_atom_0_3= RULE_ATOM | lv_atom_0_4= RULE_EXPRESSION_1200 | lv_atom_0_5= RULE_EXPRESSION_1200FX | lv_atom_0_6= RULE_EXPRESSION_1100 | lv_atom_0_7= RULE_EXPRESSION_1050 | lv_atom_0_8= RULE_EXPRESSION_1000 | lv_atom_0_9= RULE_EXPRESSION_900FX | lv_atom_0_10= RULE_EXPRESSION_700 | lv_atom_0_11= RULE_EXPRESSION_600 | lv_atom_0_12= RULE_EXPRESSION_500 | lv_atom_0_13= RULE_EXPRESSION_400 | lv_atom_0_14= RULE_EXPRESSION_200 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:953:1: (lv_atom_0_1= '.' | lv_atom_0_2= '!' | lv_atom_0_3= RULE_ATOM | lv_atom_0_4= RULE_EXPRESSION_1200 | lv_atom_0_5= RULE_EXPRESSION_1200FX | lv_atom_0_6= RULE_EXPRESSION_1100 | lv_atom_0_7= RULE_EXPRESSION_1050 | lv_atom_0_8= RULE_EXPRESSION_1000 | lv_atom_0_9= RULE_EXPRESSION_900FX | lv_atom_0_10= RULE_EXPRESSION_700 | lv_atom_0_11= RULE_EXPRESSION_600 | lv_atom_0_12= RULE_EXPRESSION_500 | lv_atom_0_13= RULE_EXPRESSION_400 | lv_atom_0_14= RULE_EXPRESSION_200 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:953:1: (lv_atom_0_1= '.' | lv_atom_0_2= '!' | lv_atom_0_3= RULE_ATOM | lv_atom_0_4= RULE_EXPRESSION_1200 | lv_atom_0_5= RULE_EXPRESSION_1200FX | lv_atom_0_6= RULE_EXPRESSION_1100 | lv_atom_0_7= RULE_EXPRESSION_1050 | lv_atom_0_8= RULE_EXPRESSION_1000 | lv_atom_0_9= RULE_EXPRESSION_900FX | lv_atom_0_10= RULE_EXPRESSION_700 | lv_atom_0_11= RULE_EXPRESSION_600 | lv_atom_0_12= RULE_EXPRESSION_500 | lv_atom_0_13= RULE_EXPRESSION_400 | lv_atom_0_14= RULE_EXPRESSION_200 )
                    int alt13=14;
                    switch ( input.LA(1) ) {
                    case 21:
                        {
                        alt13=1;
                        }
                        break;
                    case 22:
                        {
                        alt13=2;
                        }
                        break;
                    case RULE_ATOM:
                        {
                        alt13=3;
                        }
                        break;
                    case RULE_EXPRESSION_1200:
                        {
                        alt13=4;
                        }
                        break;
                    case RULE_EXPRESSION_1200FX:
                        {
                        alt13=5;
                        }
                        break;
                    case RULE_EXPRESSION_1100:
                        {
                        alt13=6;
                        }
                        break;
                    case RULE_EXPRESSION_1050:
                        {
                        alt13=7;
                        }
                        break;
                    case RULE_EXPRESSION_1000:
                        {
                        alt13=8;
                        }
                        break;
                    case RULE_EXPRESSION_900FX:
                        {
                        alt13=9;
                        }
                        break;
                    case RULE_EXPRESSION_700:
                        {
                        alt13=10;
                        }
                        break;
                    case RULE_EXPRESSION_600:
                        {
                        alt13=11;
                        }
                        break;
                    case RULE_EXPRESSION_500:
                        {
                        alt13=12;
                        }
                        break;
                    case RULE_EXPRESSION_400:
                        {
                        alt13=13;
                        }
                        break;
                    case RULE_EXPRESSION_200:
                        {
                        alt13=14;
                        }
                        break;
                    default:
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 13, 0, input);

                        throw nvae;
                    }

                    switch (alt13) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:954:3: lv_atom_0_1= '.'
                            {
                            lv_atom_0_1=(Token)match(input,21,FOLLOW_21_in_ruleExpression01807); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_atom_0_1, grammarAccess.getExpression0Access().getAtomFullStopKeyword_0_0_0_0());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(current, "atom", lv_atom_0_1, null);
                              	    
                            }

                            }
                            break;
                        case 2 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:966:8: lv_atom_0_2= '!'
                            {
                            lv_atom_0_2=(Token)match(input,22,FOLLOW_22_in_ruleExpression01836); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      newLeafNode(lv_atom_0_2, grammarAccess.getExpression0Access().getAtomExclamationMarkKeyword_0_0_0_1());
                                  
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(current, "atom", lv_atom_0_2, null);
                              	    
                            }

                            }
                            break;
                        case 3 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:978:8: lv_atom_0_3= RULE_ATOM
                            {
                            lv_atom_0_3=(Token)match(input,RULE_ATOM,FOLLOW_RULE_ATOM_in_ruleExpression01864); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_atom_0_3, grammarAccess.getExpression0Access().getAtomATOMTerminalRuleCall_0_0_0_2()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"atom",
                                      		lv_atom_0_3, 
                                      		"ATOM");
                              	    
                            }

                            }
                            break;
                        case 4 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:993:8: lv_atom_0_4= RULE_EXPRESSION_1200
                            {
                            lv_atom_0_4=(Token)match(input,RULE_EXPRESSION_1200,FOLLOW_RULE_EXPRESSION_1200_in_ruleExpression01884); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_atom_0_4, grammarAccess.getExpression0Access().getAtomEXPRESSION_1200TerminalRuleCall_0_0_0_3()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"atom",
                                      		lv_atom_0_4, 
                                      		"EXPRESSION_1200");
                              	    
                            }

                            }
                            break;
                        case 5 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1008:8: lv_atom_0_5= RULE_EXPRESSION_1200FX
                            {
                            lv_atom_0_5=(Token)match(input,RULE_EXPRESSION_1200FX,FOLLOW_RULE_EXPRESSION_1200FX_in_ruleExpression01904); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_atom_0_5, grammarAccess.getExpression0Access().getAtomEXPRESSION_1200FXTerminalRuleCall_0_0_0_4()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"atom",
                                      		lv_atom_0_5, 
                                      		"EXPRESSION_1200FX");
                              	    
                            }

                            }
                            break;
                        case 6 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1023:8: lv_atom_0_6= RULE_EXPRESSION_1100
                            {
                            lv_atom_0_6=(Token)match(input,RULE_EXPRESSION_1100,FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression01924); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_atom_0_6, grammarAccess.getExpression0Access().getAtomEXPRESSION_1100TerminalRuleCall_0_0_0_5()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"atom",
                                      		lv_atom_0_6, 
                                      		"EXPRESSION_1100");
                              	    
                            }

                            }
                            break;
                        case 7 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1038:8: lv_atom_0_7= RULE_EXPRESSION_1050
                            {
                            lv_atom_0_7=(Token)match(input,RULE_EXPRESSION_1050,FOLLOW_RULE_EXPRESSION_1050_in_ruleExpression01944); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_atom_0_7, grammarAccess.getExpression0Access().getAtomEXPRESSION_1050TerminalRuleCall_0_0_0_6()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"atom",
                                      		lv_atom_0_7, 
                                      		"EXPRESSION_1050");
                              	    
                            }

                            }
                            break;
                        case 8 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1053:8: lv_atom_0_8= RULE_EXPRESSION_1000
                            {
                            lv_atom_0_8=(Token)match(input,RULE_EXPRESSION_1000,FOLLOW_RULE_EXPRESSION_1000_in_ruleExpression01964); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_atom_0_8, grammarAccess.getExpression0Access().getAtomEXPRESSION_1000TerminalRuleCall_0_0_0_7()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"atom",
                                      		lv_atom_0_8, 
                                      		"EXPRESSION_1000");
                              	    
                            }

                            }
                            break;
                        case 9 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1068:8: lv_atom_0_9= RULE_EXPRESSION_900FX
                            {
                            lv_atom_0_9=(Token)match(input,RULE_EXPRESSION_900FX,FOLLOW_RULE_EXPRESSION_900FX_in_ruleExpression01984); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_atom_0_9, grammarAccess.getExpression0Access().getAtomEXPRESSION_900FXTerminalRuleCall_0_0_0_8()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"atom",
                                      		lv_atom_0_9, 
                                      		"EXPRESSION_900FX");
                              	    
                            }

                            }
                            break;
                        case 10 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1083:8: lv_atom_0_10= RULE_EXPRESSION_700
                            {
                            lv_atom_0_10=(Token)match(input,RULE_EXPRESSION_700,FOLLOW_RULE_EXPRESSION_700_in_ruleExpression02004); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_atom_0_10, grammarAccess.getExpression0Access().getAtomEXPRESSION_700TerminalRuleCall_0_0_0_9()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"atom",
                                      		lv_atom_0_10, 
                                      		"EXPRESSION_700");
                              	    
                            }

                            }
                            break;
                        case 11 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1098:8: lv_atom_0_11= RULE_EXPRESSION_600
                            {
                            lv_atom_0_11=(Token)match(input,RULE_EXPRESSION_600,FOLLOW_RULE_EXPRESSION_600_in_ruleExpression02024); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_atom_0_11, grammarAccess.getExpression0Access().getAtomEXPRESSION_600TerminalRuleCall_0_0_0_10()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"atom",
                                      		lv_atom_0_11, 
                                      		"EXPRESSION_600");
                              	    
                            }

                            }
                            break;
                        case 12 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1113:8: lv_atom_0_12= RULE_EXPRESSION_500
                            {
                            lv_atom_0_12=(Token)match(input,RULE_EXPRESSION_500,FOLLOW_RULE_EXPRESSION_500_in_ruleExpression02044); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_atom_0_12, grammarAccess.getExpression0Access().getAtomEXPRESSION_500TerminalRuleCall_0_0_0_11()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"atom",
                                      		lv_atom_0_12, 
                                      		"EXPRESSION_500");
                              	    
                            }

                            }
                            break;
                        case 13 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1128:8: lv_atom_0_13= RULE_EXPRESSION_400
                            {
                            lv_atom_0_13=(Token)match(input,RULE_EXPRESSION_400,FOLLOW_RULE_EXPRESSION_400_in_ruleExpression02064); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_atom_0_13, grammarAccess.getExpression0Access().getAtomEXPRESSION_400TerminalRuleCall_0_0_0_12()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"atom",
                                      		lv_atom_0_13, 
                                      		"EXPRESSION_400");
                              	    
                            }

                            }
                            break;
                        case 14 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1143:8: lv_atom_0_14= RULE_EXPRESSION_200
                            {
                            lv_atom_0_14=(Token)match(input,RULE_EXPRESSION_200,FOLLOW_RULE_EXPRESSION_200_in_ruleExpression02084); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              			newLeafNode(lv_atom_0_14, grammarAccess.getExpression0Access().getAtomEXPRESSION_200TerminalRuleCall_0_0_0_13()); 
                              		
                            }
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElement(grammarAccess.getExpression0Rule());
                              	        }
                                     		setWithLastConsumed(
                                     			current, 
                                     			"atom",
                                      		lv_atom_0_14, 
                                      		"EXPRESSION_200");
                              	    
                            }

                            }
                            break;

                    }


                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1161:2: ( ( (lv_prefix_1_0= '(' ) ) ( (lv_terms_2_0= ruleExpressionINF ) ) otherlv_3= ')' )?
                    int alt14=2;
                    int LA14_0 = input.LA(1);

                    if ( (LA14_0==23) ) {
                        alt14=1;
                    }
                    switch (alt14) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1161:3: ( (lv_prefix_1_0= '(' ) ) ( (lv_terms_2_0= ruleExpressionINF ) ) otherlv_3= ')'
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1161:3: ( (lv_prefix_1_0= '(' ) )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1162:1: (lv_prefix_1_0= '(' )
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1162:1: (lv_prefix_1_0= '(' )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1163:3: lv_prefix_1_0= '('
                            {
                            lv_prefix_1_0=(Token)match(input,23,FOLLOW_23_in_ruleExpression02111); if (state.failed) return current;
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

                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1176:2: ( (lv_terms_2_0= ruleExpressionINF ) )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1177:1: (lv_terms_2_0= ruleExpressionINF )
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1177:1: (lv_terms_2_0= ruleExpressionINF )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1178:3: lv_terms_2_0= ruleExpressionINF
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getExpression0Access().getTermsExpressionINFParserRuleCall_0_1_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression02145);
                            lv_terms_2_0=ruleExpressionINF();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                              	        }
                                     		set(
                                     			current, 
                                     			"terms",
                                      		lv_terms_2_0, 
                                      		"ExpressionINF");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            otherlv_3=(Token)match(input,24,FOLLOW_24_in_ruleExpression02157); if (state.failed) return current;
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
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1199:6: ( (lv_variable_4_0= RULE_VARIABLE ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1199:6: ( (lv_variable_4_0= RULE_VARIABLE ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1200:1: (lv_variable_4_0= RULE_VARIABLE )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1200:1: (lv_variable_4_0= RULE_VARIABLE )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1201:3: lv_variable_4_0= RULE_VARIABLE
                    {
                    lv_variable_4_0=(Token)match(input,RULE_VARIABLE,FOLLOW_RULE_VARIABLE_in_ruleExpression02183); if (state.failed) return current;
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
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1218:6: ( (lv_string_5_0= RULE_STRING ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1218:6: ( (lv_string_5_0= RULE_STRING ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1219:1: (lv_string_5_0= RULE_STRING )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1219:1: (lv_string_5_0= RULE_STRING )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1220:3: lv_string_5_0= RULE_STRING
                    {
                    lv_string_5_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleExpression02211); if (state.failed) return current;
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
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1237:6: ( (lv_number_6_0= ruleNUMBER ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1237:6: ( (lv_number_6_0= ruleNUMBER ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1238:1: (lv_number_6_0= ruleNUMBER )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1238:1: (lv_number_6_0= ruleNUMBER )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1239:3: lv_number_6_0= ruleNUMBER
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression0Access().getNumberNUMBERParserRuleCall_3_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNUMBER_in_ruleExpression02243);
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
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1256:6: ( ( (lv_list_7_0= '[' ) ) ( ( (lv_head_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tail_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1256:6: ( ( (lv_list_7_0= '[' ) ) ( ( (lv_head_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tail_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1256:7: ( (lv_list_7_0= '[' ) ) ( ( (lv_head_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tail_10_0= ruleExpressionINF ) ) )? )? otherlv_11= ']'
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1256:7: ( (lv_list_7_0= '[' ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1257:1: (lv_list_7_0= '[' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1257:1: (lv_list_7_0= '[' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1258:3: lv_list_7_0= '['
                    {
                    lv_list_7_0=(Token)match(input,25,FOLLOW_25_in_ruleExpression02268); if (state.failed) return current;
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

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1271:2: ( ( (lv_head_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tail_10_0= ruleExpressionINF ) ) )? )?
                    int alt16=2;
                    int LA16_0 = input.LA(1);

                    if ( ((LA16_0>=RULE_EXPRESSION_1200 && LA16_0<=RULE_DIGIT)||(LA16_0>=21 && LA16_0<=23)||LA16_0==25||LA16_0==28) ) {
                        alt16=1;
                    }
                    switch (alt16) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1271:3: ( (lv_head_8_0= ruleExpressionINF ) ) (otherlv_9= '|' ( (lv_tail_10_0= ruleExpressionINF ) ) )?
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1271:3: ( (lv_head_8_0= ruleExpressionINF ) )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1272:1: (lv_head_8_0= ruleExpressionINF )
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1272:1: (lv_head_8_0= ruleExpressionINF )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1273:3: lv_head_8_0= ruleExpressionINF
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getExpression0Access().getHeadExpressionINFParserRuleCall_4_1_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression02303);
                            lv_head_8_0=ruleExpressionINF();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                              	        }
                                     		set(
                                     			current, 
                                     			"head",
                                      		lv_head_8_0, 
                                      		"ExpressionINF");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1289:2: (otherlv_9= '|' ( (lv_tail_10_0= ruleExpressionINF ) ) )?
                            int alt15=2;
                            int LA15_0 = input.LA(1);

                            if ( (LA15_0==26) ) {
                                alt15=1;
                            }
                            switch (alt15) {
                                case 1 :
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1289:4: otherlv_9= '|' ( (lv_tail_10_0= ruleExpressionINF ) )
                                    {
                                    otherlv_9=(Token)match(input,26,FOLLOW_26_in_ruleExpression02316); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_9, grammarAccess.getExpression0Access().getVerticalLineKeyword_4_1_1_0());
                                          
                                    }
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1293:1: ( (lv_tail_10_0= ruleExpressionINF ) )
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1294:1: (lv_tail_10_0= ruleExpressionINF )
                                    {
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1294:1: (lv_tail_10_0= ruleExpressionINF )
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1295:3: lv_tail_10_0= ruleExpressionINF
                                    {
                                    if ( state.backtracking==0 ) {
                                       
                                      	        newCompositeNode(grammarAccess.getExpression0Access().getTailExpressionINFParserRuleCall_4_1_1_1_0()); 
                                      	    
                                    }
                                    pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression02337);
                                    lv_tail_10_0=ruleExpressionINF();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      	        if (current==null) {
                                      	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                                      	        }
                                             		set(
                                             			current, 
                                             			"tail",
                                              		lv_tail_10_0, 
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

                    otherlv_11=(Token)match(input,27,FOLLOW_27_in_ruleExpression02353); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_11, grammarAccess.getExpression0Access().getRightSquareBracketKeyword_4_2());
                          
                    }

                    }


                    }
                    break;
                case 6 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1316:6: ( ( (lv_paren_12_0= '(' ) ) ( (lv_sub_13_0= ruleExpressionINF ) ) otherlv_14= ')' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1316:6: ( ( (lv_paren_12_0= '(' ) ) ( (lv_sub_13_0= ruleExpressionINF ) ) otherlv_14= ')' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1316:7: ( (lv_paren_12_0= '(' ) ) ( (lv_sub_13_0= ruleExpressionINF ) ) otherlv_14= ')'
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1316:7: ( (lv_paren_12_0= '(' ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1317:1: (lv_paren_12_0= '(' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1317:1: (lv_paren_12_0= '(' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1318:3: lv_paren_12_0= '('
                    {
                    lv_paren_12_0=(Token)match(input,23,FOLLOW_23_in_ruleExpression02379); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              newLeafNode(lv_paren_12_0, grammarAccess.getExpression0Access().getParenLeftParenthesisKeyword_5_0_0());
                          
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression0Rule());
                      	        }
                             		setWithLastConsumed(current, "paren", true, "(");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1331:2: ( (lv_sub_13_0= ruleExpressionINF ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1332:1: (lv_sub_13_0= ruleExpressionINF )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1332:1: (lv_sub_13_0= ruleExpressionINF )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1333:3: lv_sub_13_0= ruleExpressionINF
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression0Access().getSubExpressionINFParserRuleCall_5_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression02413);
                    lv_sub_13_0=ruleExpressionINF();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                      	        }
                             		set(
                             			current, 
                             			"sub",
                              		lv_sub_13_0, 
                              		"ExpressionINF");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    otherlv_14=(Token)match(input,24,FOLLOW_24_in_ruleExpression02425); if (state.failed) return current;
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1361:1: entryRuleNUMBER returns [String current=null] : iv_ruleNUMBER= ruleNUMBER EOF ;
    public final String entryRuleNUMBER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNUMBER = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1362:2: (iv_ruleNUMBER= ruleNUMBER EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1363:2: iv_ruleNUMBER= ruleNUMBER EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNUMBERRule()); 
            }
            pushFollow(FOLLOW_ruleNUMBER_in_entryRuleNUMBER2463);
            iv_ruleNUMBER=ruleNUMBER();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNUMBER.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNUMBER2474); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1370:1: ruleNUMBER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )? ( (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+ )? ) ;
    public final AntlrDatatypeRuleToken ruleNUMBER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_DIGIT_1=null;
        Token this_DIGIT_3=null;
        Token this_DIGIT_8=null;

         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1373:28: ( ( (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )? ( (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+ )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1374:1: ( (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )? ( (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+ )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1374:1: ( (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )? ( (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+ )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1374:2: (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )? ( (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+ )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1374:2: (kw= '-' )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==28) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1375:2: kw= '-'
                    {
                    kw=(Token)match(input,28,FOLLOW_28_in_ruleNUMBER2513); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getNUMBERAccess().getHyphenMinusKeyword_0()); 
                          
                    }

                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1380:3: (this_DIGIT_1= RULE_DIGIT )+
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
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1380:8: this_DIGIT_1= RULE_DIGIT
            	    {
            	    this_DIGIT_1=(Token)match(input,RULE_DIGIT,FOLLOW_RULE_DIGIT_in_ruleNUMBER2531); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      		current.merge(this_DIGIT_1);
            	          
            	    }
            	    if ( state.backtracking==0 ) {
            	       
            	          newLeafNode(this_DIGIT_1, grammarAccess.getNUMBERAccess().getDIGITTerminalRuleCall_1()); 
            	          
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

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1387:3: (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==21) ) {
                int LA21_1 = input.LA(2);

                if ( (LA21_1==RULE_DIGIT) ) {
                    int LA21_3 = input.LA(3);

                    if ( (synpred37_InternalProlog()) ) {
                        alt21=1;
                    }
                }
            }
            switch (alt21) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1388:2: kw= '.' (this_DIGIT_3= RULE_DIGIT )+
                    {
                    kw=(Token)match(input,21,FOLLOW_21_in_ruleNUMBER2552); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getNUMBERAccess().getFullStopKeyword_2_0()); 
                          
                    }
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1393:1: (this_DIGIT_3= RULE_DIGIT )+
                    int cnt20=0;
                    loop20:
                    do {
                        int alt20=2;
                        int LA20_0 = input.LA(1);

                        if ( (LA20_0==RULE_DIGIT) ) {
                            alt20=1;
                        }


                        switch (alt20) {
                    	case 1 :
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1393:6: this_DIGIT_3= RULE_DIGIT
                    	    {
                    	    this_DIGIT_3=(Token)match(input,RULE_DIGIT,FOLLOW_RULE_DIGIT_in_ruleNUMBER2568); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      		current.merge(this_DIGIT_3);
                    	          
                    	    }
                    	    if ( state.backtracking==0 ) {
                    	       
                    	          newLeafNode(this_DIGIT_3, grammarAccess.getNUMBERAccess().getDIGITTerminalRuleCall_2_1()); 
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt20 >= 1 ) break loop20;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(20, input);
                                throw eee;
                        }
                        cnt20++;
                    } while (true);


                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1400:5: ( (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+ )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( ((LA25_0>=29 && LA25_0<=30)) ) {
                alt25=1;
            }
            switch (alt25) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1400:6: (kw= 'e' | kw= 'E' ) (kw= '+' | kw= '-' )? (this_DIGIT_8= RULE_DIGIT )+
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1400:6: (kw= 'e' | kw= 'E' )
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==29) ) {
                        alt22=1;
                    }
                    else if ( (LA22_0==30) ) {
                        alt22=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 22, 0, input);

                        throw nvae;
                    }
                    switch (alt22) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1401:2: kw= 'e'
                            {
                            kw=(Token)match(input,29,FOLLOW_29_in_ruleNUMBER2592); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getNUMBERAccess().getEKeyword_3_0_0()); 
                                  
                            }

                            }
                            break;
                        case 2 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1408:2: kw= 'E'
                            {
                            kw=(Token)match(input,30,FOLLOW_30_in_ruleNUMBER2611); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getNUMBERAccess().getEKeyword_3_0_1()); 
                                  
                            }

                            }
                            break;

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1413:2: (kw= '+' | kw= '-' )?
                    int alt23=3;
                    int LA23_0 = input.LA(1);

                    if ( (LA23_0==31) ) {
                        alt23=1;
                    }
                    else if ( (LA23_0==28) ) {
                        alt23=2;
                    }
                    switch (alt23) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1414:2: kw= '+'
                            {
                            kw=(Token)match(input,31,FOLLOW_31_in_ruleNUMBER2626); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getNUMBERAccess().getPlusSignKeyword_3_1_0()); 
                                  
                            }

                            }
                            break;
                        case 2 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1421:2: kw= '-'
                            {
                            kw=(Token)match(input,28,FOLLOW_28_in_ruleNUMBER2645); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                      current.merge(kw);
                                      newLeafNode(kw, grammarAccess.getNUMBERAccess().getHyphenMinusKeyword_3_1_1()); 
                                  
                            }

                            }
                            break;

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1426:3: (this_DIGIT_8= RULE_DIGIT )+
                    int cnt24=0;
                    loop24:
                    do {
                        int alt24=2;
                        int LA24_0 = input.LA(1);

                        if ( (LA24_0==RULE_DIGIT) ) {
                            alt24=1;
                        }


                        switch (alt24) {
                    	case 1 :
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1426:8: this_DIGIT_8= RULE_DIGIT
                    	    {
                    	    this_DIGIT_8=(Token)match(input,RULE_DIGIT,FOLLOW_RULE_DIGIT_in_ruleNUMBER2663); if (state.failed) return current;
                    	    if ( state.backtracking==0 ) {

                    	      		current.merge(this_DIGIT_8);
                    	          
                    	    }
                    	    if ( state.backtracking==0 ) {
                    	       
                    	          newLeafNode(this_DIGIT_8, grammarAccess.getNUMBERAccess().getDIGITTerminalRuleCall_3_2()); 
                    	          
                    	    }

                    	    }
                    	    break;

                    	default :
                    	    if ( cnt24 >= 1 ) break loop24;
                    	    if (state.backtracking>0) {state.failed=true; return current;}
                                EarlyExitException eee =
                                    new EarlyExitException(24, input);
                                throw eee;
                        }
                        cnt24++;
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

        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:238:1: ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:238:1: (lv_ops_0_0= RULE_EXPRESSION_1200FX )
        {
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:238:1: (lv_ops_0_0= RULE_EXPRESSION_1200FX )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:239:3: lv_ops_0_0= RULE_EXPRESSION_1200FX
        {
        lv_ops_0_0=(Token)match(input,RULE_EXPRESSION_1200FX,FOLLOW_RULE_EXPRESSION_1200FX_in_synpred3_InternalProlog469); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred3_InternalProlog

    // $ANTLR start synpred7_InternalProlog
    public final void synpred7_InternalProlog_fragment() throws RecognitionException {   
        Token lv_ops_0_0=null;

        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:520:1: ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:520:1: (lv_ops_0_0= RULE_EXPRESSION_900FX )
        {
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:520:1: (lv_ops_0_0= RULE_EXPRESSION_900FX )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:521:3: lv_ops_0_0= RULE_EXPRESSION_900FX
        {
        lv_ops_0_0=(Token)match(input,RULE_EXPRESSION_900FX,FOLLOW_RULE_EXPRESSION_900FX_in_synpred7_InternalProlog998); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred7_InternalProlog

    // $ANTLR start synpred37_InternalProlog
    public final void synpred37_InternalProlog_fragment() throws RecognitionException {   
        Token kw=null;
        Token this_DIGIT_3=null;

        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1388:2: (kw= '.' (this_DIGIT_3= RULE_DIGIT )+ )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1388:2: kw= '.' (this_DIGIT_3= RULE_DIGIT )+
        {
        kw=(Token)match(input,21,FOLLOW_21_in_synpred37_InternalProlog2552); if (state.failed) return ;
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1393:1: (this_DIGIT_3= RULE_DIGIT )+
        int cnt31=0;
        loop31:
        do {
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==RULE_DIGIT) ) {
                alt31=1;
            }


            switch (alt31) {
        	case 1 :
        	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1393:6: this_DIGIT_3= RULE_DIGIT
        	    {
        	    this_DIGIT_3=(Token)match(input,RULE_DIGIT,FOLLOW_RULE_DIGIT_in_synpred37_InternalProlog2568); if (state.failed) return ;

        	    }
        	    break;

        	default :
        	    if ( cnt31 >= 1 ) break loop31;
        	    if (state.backtracking>0) {state.failed=true; return ;}
                    EarlyExitException eee =
                        new EarlyExitException(31, input);
                    throw eee;
            }
            cnt31++;
        } while (true);


        }
    }
    // $ANTLR end synpred37_InternalProlog

    // Delegated rules

    public final boolean synpred7_InternalProlog() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred7_InternalProlog_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
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
    public final boolean synpred37_InternalProlog() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred37_InternalProlog_fragment(); // can never throw exception
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
    protected DFA7 dfa7 = new DFA7(this);
    static final String DFA3_eotS =
        "\26\uffff";
    static final String DFA3_eofS =
        "\26\uffff";
    static final String DFA3_minS =
        "\1\4\1\0\24\uffff";
    static final String DFA3_maxS =
        "\1\34\1\0\24\uffff";
    static final String DFA3_acceptS =
        "\2\uffff\1\2\22\uffff\1\1";
    static final String DFA3_specialS =
        "\1\uffff\1\0\24\uffff}>";
    static final String[] DFA3_transitionS = {
            "\1\2\1\1\15\2\2\uffff\3\2\1\uffff\1\2\2\uffff\1\2",
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
            return "237:2: ( (lv_ops_0_0= RULE_EXPRESSION_1200FX ) )?";
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
                        if ( (synpred3_InternalProlog()) ) {s = 21;}

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
    static final String DFA7_eotS =
        "\26\uffff";
    static final String DFA7_eofS =
        "\26\uffff";
    static final String DFA7_minS =
        "\1\4\1\0\24\uffff";
    static final String DFA7_maxS =
        "\1\34\1\0\24\uffff";
    static final String DFA7_acceptS =
        "\2\uffff\1\2\22\uffff\1\1";
    static final String DFA7_specialS =
        "\1\uffff\1\0\24\uffff}>";
    static final String[] DFA7_transitionS = {
            "\5\2\1\1\11\2\2\uffff\3\2\1\uffff\1\2\2\uffff\1\2",
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
            "",
            "",
            ""
    };

    static final short[] DFA7_eot = DFA.unpackEncodedString(DFA7_eotS);
    static final short[] DFA7_eof = DFA.unpackEncodedString(DFA7_eofS);
    static final char[] DFA7_min = DFA.unpackEncodedStringToUnsignedChars(DFA7_minS);
    static final char[] DFA7_max = DFA.unpackEncodedStringToUnsignedChars(DFA7_maxS);
    static final short[] DFA7_accept = DFA.unpackEncodedString(DFA7_acceptS);
    static final short[] DFA7_special = DFA.unpackEncodedString(DFA7_specialS);
    static final short[][] DFA7_transition;

    static {
        int numStates = DFA7_transitionS.length;
        DFA7_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA7_transition[i] = DFA.unpackEncodedString(DFA7_transitionS[i]);
        }
    }

    class DFA7 extends DFA {

        public DFA7(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 7;
            this.eot = DFA7_eot;
            this.eof = DFA7_eof;
            this.min = DFA7_min;
            this.max = DFA7_max;
            this.accept = DFA7_accept;
            this.special = DFA7_special;
            this.transition = DFA7_transition;
        }
        public String getDescription() {
            return "519:2: ( (lv_ops_0_0= RULE_EXPRESSION_900FX ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA7_1 = input.LA(1);

                         
                        int index7_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred7_InternalProlog()) ) {s = 21;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index7_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 7, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_ruleProgram_in_entryRuleProgram81 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleProgram91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleProgram137 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleProgram149 = new BitSet(new long[]{0x0000000012E7FFF2L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_entryRuleExpressionINF186 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionINF196 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1200_in_ruleExpressionINF245 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1200_in_entryRuleExpression1200279 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1200289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1200fx_in_ruleExpression1200335 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1200_in_ruleExpression1200353 = new BitSet(new long[]{0x0000000012E7FFF0L});
    public static final BitSet FOLLOW_ruleExpression1200fx_in_ruleExpression1200379 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1200fx_in_entryRuleExpression1200fx417 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1200fx427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1200FX_in_ruleExpression1200fx469 = new BitSet(new long[]{0x0000000012E7FFF0L});
    public static final BitSet FOLLOW_ruleExpression1100_in_ruleExpression1200fx496 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1100_in_entryRuleExpression1100532 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1100542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1050_in_ruleExpression1100588 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression1100606 = new BitSet(new long[]{0x0000000012E7FFF0L});
    public static final BitSet FOLLOW_ruleExpression1050_in_ruleExpression1100632 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_ruleExpression1050_in_entryRuleExpression1050670 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1050680 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1000_in_ruleExpression1050726 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1050_in_ruleExpression1050744 = new BitSet(new long[]{0x0000000012E7FFF0L});
    public static final BitSet FOLLOW_ruleExpression1000_in_ruleExpression1050770 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1000_in_entryRuleExpression1000808 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1000818 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression900fx_in_ruleExpression1000864 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1000_in_ruleExpression1000882 = new BitSet(new long[]{0x0000000012E7FFF0L});
    public static final BitSet FOLLOW_ruleExpression900fx_in_ruleExpression1000908 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_ruleExpression900fx_in_entryRuleExpression900fx946 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression900fx956 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_900FX_in_ruleExpression900fx998 = new BitSet(new long[]{0x0000000012E7FFF0L});
    public static final BitSet FOLLOW_ruleExpression700_in_ruleExpression900fx1025 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression700_in_entryRuleExpression7001061 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression7001071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression600_in_ruleExpression7001117 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_700_in_ruleExpression7001135 = new BitSet(new long[]{0x0000000012E7FFF0L});
    public static final BitSet FOLLOW_ruleExpression600_in_ruleExpression7001161 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression600_in_entryRuleExpression6001199 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression6001209 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression500_in_ruleExpression6001255 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_600_in_ruleExpression6001273 = new BitSet(new long[]{0x0000000012E7FFF0L});
    public static final BitSet FOLLOW_ruleExpression500_in_ruleExpression6001299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression500_in_entryRuleExpression5001337 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression5001347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression400_in_ruleExpression5001393 = new BitSet(new long[]{0x0000000000001002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_500_in_ruleExpression5001411 = new BitSet(new long[]{0x0000000012E7FFF0L});
    public static final BitSet FOLLOW_ruleExpression400_in_ruleExpression5001437 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression400_in_entryRuleExpression4001475 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression4001485 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression200_in_ruleExpression4001531 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_400_in_ruleExpression4001549 = new BitSet(new long[]{0x0000000012E7FFF0L});
    public static final BitSet FOLLOW_ruleExpression200_in_ruleExpression4001575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression200_in_entryRuleExpression2001613 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression2001623 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression0_in_ruleExpression2001669 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_200_in_ruleExpression2001687 = new BitSet(new long[]{0x0000000012E7FFF0L});
    public static final BitSet FOLLOW_ruleExpression0_in_ruleExpression2001713 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression0_in_entryRuleExpression01751 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression01761 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleExpression01807 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_22_in_ruleExpression01836 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_RULE_ATOM_in_ruleExpression01864 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1200_in_ruleExpression01884 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1200FX_in_ruleExpression01904 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression01924 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1050_in_ruleExpression01944 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1000_in_ruleExpression01964 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_900FX_in_ruleExpression01984 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_700_in_ruleExpression02004 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_600_in_ruleExpression02024 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_500_in_ruleExpression02044 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_400_in_ruleExpression02064 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_200_in_ruleExpression02084 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_23_in_ruleExpression02111 = new BitSet(new long[]{0x0000000013E7FFF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression02145 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleExpression02157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_VARIABLE_in_ruleExpression02183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleExpression02211 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNUMBER_in_ruleExpression02243 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleExpression02268 = new BitSet(new long[]{0x000000001EE7FFF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression02303 = new BitSet(new long[]{0x000000000C000000L});
    public static final BitSet FOLLOW_26_in_ruleExpression02316 = new BitSet(new long[]{0x000000001AE7FFF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression02337 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleExpression02353 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_ruleExpression02379 = new BitSet(new long[]{0x0000000013E7FFF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression02413 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleExpression02425 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNUMBER_in_entryRuleNUMBER2463 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNUMBER2474 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleNUMBER2513 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_RULE_DIGIT_in_ruleNUMBER2531 = new BitSet(new long[]{0x0000000060240002L});
    public static final BitSet FOLLOW_21_in_ruleNUMBER2552 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_RULE_DIGIT_in_ruleNUMBER2568 = new BitSet(new long[]{0x0000000060040002L});
    public static final BitSet FOLLOW_29_in_ruleNUMBER2592 = new BitSet(new long[]{0x0000000090040000L});
    public static final BitSet FOLLOW_30_in_ruleNUMBER2611 = new BitSet(new long[]{0x0000000090040000L});
    public static final BitSet FOLLOW_31_in_ruleNUMBER2626 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_28_in_ruleNUMBER2645 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_RULE_DIGIT_in_ruleNUMBER2663 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1200FX_in_synpred3_InternalProlog469 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_900FX_in_synpred7_InternalProlog998 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_synpred37_InternalProlog2552 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_RULE_DIGIT_in_synpred37_InternalProlog2568 = new BitSet(new long[]{0x0000000000040002L});

}