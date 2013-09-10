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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ATOM", "RULE_OP1200XFX", "RULE_OP1200FX", "RULE_OP1150FX", "RULE_OP1100XFY", "RULE_OP1050XFY", "RULE_OP1000XFY", "RULE_OP954XFY", "RULE_OP900FY", "RULE_OP900FX", "RULE_OP700XFX", "RULE_OP600XFY", "RULE_OP500YFX", "RULE_OP500FX", "RULE_OP400YFX", "RULE_OP200XFX", "RULE_OP200XFY", "RULE_INT", "RULE_EXT_INT", "RULE_VARIABLE", "RULE_STRING", "RULE_WHITESPACE", "RULE_SINGLE_LINE_COMMENT", "'.'", "'!'", "'-'", "'('", "')'", "'['", "'|'", "']'"
    };
    public static final int RULE_EXT_INT=22;
    public static final int RULE_OP400YFX=18;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int RULE_OP700XFX=14;
    public static final int RULE_OP1200FX=6;
    public static final int RULE_SINGLE_LINE_COMMENT=26;
    public static final int EOF=-1;
    public static final int RULE_ATOM=4;
    public static final int RULE_OP500YFX=16;
    public static final int RULE_OP1050XFY=9;
    public static final int RULE_OP954XFY=11;
    public static final int RULE_OP1200XFX=5;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_STRING=24;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int RULE_OP600XFY=15;
    public static final int T__34=34;
    public static final int RULE_OP900FY=12;
    public static final int RULE_VARIABLE=23;
    public static final int RULE_OP900FX=13;
    public static final int RULE_OP200XFY=20;
    public static final int RULE_OP200XFX=19;
    public static final int RULE_OP1100XFY=8;
    public static final int RULE_INT=21;
    public static final int RULE_WHITESPACE=25;
    public static final int RULE_OP1150FX=7;
    public static final int RULE_OP1000XFY=10;
    public static final int RULE_OP500FX=17;

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
        	return "Model";	
       	}
       	
       	@Override
       	protected PrologGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRuleModel"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:73:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:74:2: (iv_ruleModel= ruleModel EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:75:2: iv_ruleModel= ruleModel EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getModelRule()); 
            }
            pushFollow(FOLLOW_ruleModel_in_entryRuleModel81);
            iv_ruleModel=ruleModel();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleModel; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleModel91); if (state.failed) return current;

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
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:82:1: ruleModel returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpressionInfinity ) ) otherlv_1= '.' )+ ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        EObject lv_exps_0_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:85:28: ( ( ( (lv_exps_0_0= ruleExpressionInfinity ) ) otherlv_1= '.' )+ )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:86:1: ( ( (lv_exps_0_0= ruleExpressionInfinity ) ) otherlv_1= '.' )+
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:86:1: ( ( (lv_exps_0_0= ruleExpressionInfinity ) ) otherlv_1= '.' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RULE_ATOM && LA1_0<=RULE_INT)||(LA1_0>=RULE_VARIABLE && LA1_0<=RULE_STRING)||(LA1_0>=27 && LA1_0<=30)||LA1_0==32) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:86:2: ( (lv_exps_0_0= ruleExpressionInfinity ) ) otherlv_1= '.'
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:86:2: ( (lv_exps_0_0= ruleExpressionInfinity ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:87:1: (lv_exps_0_0= ruleExpressionInfinity )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:87:1: (lv_exps_0_0= ruleExpressionInfinity )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:88:3: lv_exps_0_0= ruleExpressionInfinity
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getModelAccess().getExpsExpressionInfinityParserRuleCall_0_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpressionInfinity_in_ruleModel137);
            	    lv_exps_0_0=ruleExpressionInfinity();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getModelRule());
            	      	        }
            	             		add(
            	             			current, 
            	             			"exps",
            	              		lv_exps_0_0, 
            	              		"ExpressionInfinity");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }

            	    otherlv_1=(Token)match(input,27,FOLLOW_27_in_ruleModel149); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	          	newLeafNode(otherlv_1, grammarAccess.getModelAccess().getFullStopKeyword_1());
            	          
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
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleATOMS"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:116:1: entryRuleATOMS returns [String current=null] : iv_ruleATOMS= ruleATOMS EOF ;
    public final String entryRuleATOMS() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleATOMS = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:117:2: (iv_ruleATOMS= ruleATOMS EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:118:2: iv_ruleATOMS= ruleATOMS EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getATOMSRule()); 
            }
            pushFollow(FOLLOW_ruleATOMS_in_entryRuleATOMS187);
            iv_ruleATOMS=ruleATOMS();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleATOMS.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleATOMS198); if (state.failed) return current;

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
    // $ANTLR end "entryRuleATOMS"


    // $ANTLR start "ruleATOMS"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:125:1: ruleATOMS returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= '.' | kw= '!' | this_ATOM_2= RULE_ATOM | this_OP1200XFX_3= RULE_OP1200XFX | this_OP1200FX_4= RULE_OP1200FX | this_OP1150FX_5= RULE_OP1150FX | this_OP1100XFY_6= RULE_OP1100XFY | this_OP1050XFY_7= RULE_OP1050XFY | this_OP1000XFY_8= RULE_OP1000XFY | this_OP954XFY_9= RULE_OP954XFY | this_OP900FY_10= RULE_OP900FY | this_OP900FX_11= RULE_OP900FX | this_OP700XFX_12= RULE_OP700XFX | this_OP600XFY_13= RULE_OP600XFY | this_OP500YFX_14= RULE_OP500YFX | this_OP500FX_15= RULE_OP500FX | this_OP400YFX_16= RULE_OP400YFX | this_OP200XFX_17= RULE_OP200XFX | this_OP200XFY_18= RULE_OP200XFY ) ;
    public final AntlrDatatypeRuleToken ruleATOMS() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_ATOM_2=null;
        Token this_OP1200XFX_3=null;
        Token this_OP1200FX_4=null;
        Token this_OP1150FX_5=null;
        Token this_OP1100XFY_6=null;
        Token this_OP1050XFY_7=null;
        Token this_OP1000XFY_8=null;
        Token this_OP954XFY_9=null;
        Token this_OP900FY_10=null;
        Token this_OP900FX_11=null;
        Token this_OP700XFX_12=null;
        Token this_OP600XFY_13=null;
        Token this_OP500YFX_14=null;
        Token this_OP500FX_15=null;
        Token this_OP400YFX_16=null;
        Token this_OP200XFX_17=null;
        Token this_OP200XFY_18=null;

         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:128:28: ( (kw= '.' | kw= '!' | this_ATOM_2= RULE_ATOM | this_OP1200XFX_3= RULE_OP1200XFX | this_OP1200FX_4= RULE_OP1200FX | this_OP1150FX_5= RULE_OP1150FX | this_OP1100XFY_6= RULE_OP1100XFY | this_OP1050XFY_7= RULE_OP1050XFY | this_OP1000XFY_8= RULE_OP1000XFY | this_OP954XFY_9= RULE_OP954XFY | this_OP900FY_10= RULE_OP900FY | this_OP900FX_11= RULE_OP900FX | this_OP700XFX_12= RULE_OP700XFX | this_OP600XFY_13= RULE_OP600XFY | this_OP500YFX_14= RULE_OP500YFX | this_OP500FX_15= RULE_OP500FX | this_OP400YFX_16= RULE_OP400YFX | this_OP200XFX_17= RULE_OP200XFX | this_OP200XFY_18= RULE_OP200XFY ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:129:1: (kw= '.' | kw= '!' | this_ATOM_2= RULE_ATOM | this_OP1200XFX_3= RULE_OP1200XFX | this_OP1200FX_4= RULE_OP1200FX | this_OP1150FX_5= RULE_OP1150FX | this_OP1100XFY_6= RULE_OP1100XFY | this_OP1050XFY_7= RULE_OP1050XFY | this_OP1000XFY_8= RULE_OP1000XFY | this_OP954XFY_9= RULE_OP954XFY | this_OP900FY_10= RULE_OP900FY | this_OP900FX_11= RULE_OP900FX | this_OP700XFX_12= RULE_OP700XFX | this_OP600XFY_13= RULE_OP600XFY | this_OP500YFX_14= RULE_OP500YFX | this_OP500FX_15= RULE_OP500FX | this_OP400YFX_16= RULE_OP400YFX | this_OP200XFX_17= RULE_OP200XFX | this_OP200XFY_18= RULE_OP200XFY )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:129:1: (kw= '.' | kw= '!' | this_ATOM_2= RULE_ATOM | this_OP1200XFX_3= RULE_OP1200XFX | this_OP1200FX_4= RULE_OP1200FX | this_OP1150FX_5= RULE_OP1150FX | this_OP1100XFY_6= RULE_OP1100XFY | this_OP1050XFY_7= RULE_OP1050XFY | this_OP1000XFY_8= RULE_OP1000XFY | this_OP954XFY_9= RULE_OP954XFY | this_OP900FY_10= RULE_OP900FY | this_OP900FX_11= RULE_OP900FX | this_OP700XFX_12= RULE_OP700XFX | this_OP600XFY_13= RULE_OP600XFY | this_OP500YFX_14= RULE_OP500YFX | this_OP500FX_15= RULE_OP500FX | this_OP400YFX_16= RULE_OP400YFX | this_OP200XFX_17= RULE_OP200XFX | this_OP200XFY_18= RULE_OP200XFY )
            int alt2=19;
            switch ( input.LA(1) ) {
            case 27:
                {
                alt2=1;
                }
                break;
            case 28:
                {
                alt2=2;
                }
                break;
            case RULE_ATOM:
                {
                alt2=3;
                }
                break;
            case RULE_OP1200XFX:
                {
                alt2=4;
                }
                break;
            case RULE_OP1200FX:
                {
                alt2=5;
                }
                break;
            case RULE_OP1150FX:
                {
                alt2=6;
                }
                break;
            case RULE_OP1100XFY:
                {
                alt2=7;
                }
                break;
            case RULE_OP1050XFY:
                {
                alt2=8;
                }
                break;
            case RULE_OP1000XFY:
                {
                alt2=9;
                }
                break;
            case RULE_OP954XFY:
                {
                alt2=10;
                }
                break;
            case RULE_OP900FY:
                {
                alt2=11;
                }
                break;
            case RULE_OP900FX:
                {
                alt2=12;
                }
                break;
            case RULE_OP700XFX:
                {
                alt2=13;
                }
                break;
            case RULE_OP600XFY:
                {
                alt2=14;
                }
                break;
            case RULE_OP500YFX:
                {
                alt2=15;
                }
                break;
            case RULE_OP500FX:
                {
                alt2=16;
                }
                break;
            case RULE_OP400YFX:
                {
                alt2=17;
                }
                break;
            case RULE_OP200XFX:
                {
                alt2=18;
                }
                break;
            case RULE_OP200XFY:
                {
                alt2=19;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:130:2: kw= '.'
                    {
                    kw=(Token)match(input,27,FOLLOW_27_in_ruleATOMS236); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getATOMSAccess().getFullStopKeyword_0()); 
                          
                    }

                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:137:2: kw= '!'
                    {
                    kw=(Token)match(input,28,FOLLOW_28_in_ruleATOMS255); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getATOMSAccess().getExclamationMarkKeyword_1()); 
                          
                    }

                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:143:10: this_ATOM_2= RULE_ATOM
                    {
                    this_ATOM_2=(Token)match(input,RULE_ATOM,FOLLOW_RULE_ATOM_in_ruleATOMS276); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_ATOM_2);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_ATOM_2, grammarAccess.getATOMSAccess().getATOMTerminalRuleCall_2()); 
                          
                    }

                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:151:10: this_OP1200XFX_3= RULE_OP1200XFX
                    {
                    this_OP1200XFX_3=(Token)match(input,RULE_OP1200XFX,FOLLOW_RULE_OP1200XFX_in_ruleATOMS302); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP1200XFX_3);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP1200XFX_3, grammarAccess.getATOMSAccess().getOP1200XFXTerminalRuleCall_3()); 
                          
                    }

                    }
                    break;
                case 5 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:159:10: this_OP1200FX_4= RULE_OP1200FX
                    {
                    this_OP1200FX_4=(Token)match(input,RULE_OP1200FX,FOLLOW_RULE_OP1200FX_in_ruleATOMS328); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP1200FX_4);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP1200FX_4, grammarAccess.getATOMSAccess().getOP1200FXTerminalRuleCall_4()); 
                          
                    }

                    }
                    break;
                case 6 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:167:10: this_OP1150FX_5= RULE_OP1150FX
                    {
                    this_OP1150FX_5=(Token)match(input,RULE_OP1150FX,FOLLOW_RULE_OP1150FX_in_ruleATOMS354); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP1150FX_5);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP1150FX_5, grammarAccess.getATOMSAccess().getOP1150FXTerminalRuleCall_5()); 
                          
                    }

                    }
                    break;
                case 7 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:175:10: this_OP1100XFY_6= RULE_OP1100XFY
                    {
                    this_OP1100XFY_6=(Token)match(input,RULE_OP1100XFY,FOLLOW_RULE_OP1100XFY_in_ruleATOMS380); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP1100XFY_6);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP1100XFY_6, grammarAccess.getATOMSAccess().getOP1100XFYTerminalRuleCall_6()); 
                          
                    }

                    }
                    break;
                case 8 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:183:10: this_OP1050XFY_7= RULE_OP1050XFY
                    {
                    this_OP1050XFY_7=(Token)match(input,RULE_OP1050XFY,FOLLOW_RULE_OP1050XFY_in_ruleATOMS406); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP1050XFY_7);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP1050XFY_7, grammarAccess.getATOMSAccess().getOP1050XFYTerminalRuleCall_7()); 
                          
                    }

                    }
                    break;
                case 9 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:191:10: this_OP1000XFY_8= RULE_OP1000XFY
                    {
                    this_OP1000XFY_8=(Token)match(input,RULE_OP1000XFY,FOLLOW_RULE_OP1000XFY_in_ruleATOMS432); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP1000XFY_8);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP1000XFY_8, grammarAccess.getATOMSAccess().getOP1000XFYTerminalRuleCall_8()); 
                          
                    }

                    }
                    break;
                case 10 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:199:10: this_OP954XFY_9= RULE_OP954XFY
                    {
                    this_OP954XFY_9=(Token)match(input,RULE_OP954XFY,FOLLOW_RULE_OP954XFY_in_ruleATOMS458); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP954XFY_9);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP954XFY_9, grammarAccess.getATOMSAccess().getOP954XFYTerminalRuleCall_9()); 
                          
                    }

                    }
                    break;
                case 11 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:207:10: this_OP900FY_10= RULE_OP900FY
                    {
                    this_OP900FY_10=(Token)match(input,RULE_OP900FY,FOLLOW_RULE_OP900FY_in_ruleATOMS484); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP900FY_10);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP900FY_10, grammarAccess.getATOMSAccess().getOP900FYTerminalRuleCall_10()); 
                          
                    }

                    }
                    break;
                case 12 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:215:10: this_OP900FX_11= RULE_OP900FX
                    {
                    this_OP900FX_11=(Token)match(input,RULE_OP900FX,FOLLOW_RULE_OP900FX_in_ruleATOMS510); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP900FX_11);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP900FX_11, grammarAccess.getATOMSAccess().getOP900FXTerminalRuleCall_11()); 
                          
                    }

                    }
                    break;
                case 13 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:223:10: this_OP700XFX_12= RULE_OP700XFX
                    {
                    this_OP700XFX_12=(Token)match(input,RULE_OP700XFX,FOLLOW_RULE_OP700XFX_in_ruleATOMS536); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP700XFX_12);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP700XFX_12, grammarAccess.getATOMSAccess().getOP700XFXTerminalRuleCall_12()); 
                          
                    }

                    }
                    break;
                case 14 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:231:10: this_OP600XFY_13= RULE_OP600XFY
                    {
                    this_OP600XFY_13=(Token)match(input,RULE_OP600XFY,FOLLOW_RULE_OP600XFY_in_ruleATOMS562); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP600XFY_13);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP600XFY_13, grammarAccess.getATOMSAccess().getOP600XFYTerminalRuleCall_13()); 
                          
                    }

                    }
                    break;
                case 15 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:239:10: this_OP500YFX_14= RULE_OP500YFX
                    {
                    this_OP500YFX_14=(Token)match(input,RULE_OP500YFX,FOLLOW_RULE_OP500YFX_in_ruleATOMS588); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP500YFX_14);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP500YFX_14, grammarAccess.getATOMSAccess().getOP500YFXTerminalRuleCall_14()); 
                          
                    }

                    }
                    break;
                case 16 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:247:10: this_OP500FX_15= RULE_OP500FX
                    {
                    this_OP500FX_15=(Token)match(input,RULE_OP500FX,FOLLOW_RULE_OP500FX_in_ruleATOMS614); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP500FX_15);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP500FX_15, grammarAccess.getATOMSAccess().getOP500FXTerminalRuleCall_15()); 
                          
                    }

                    }
                    break;
                case 17 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:255:10: this_OP400YFX_16= RULE_OP400YFX
                    {
                    this_OP400YFX_16=(Token)match(input,RULE_OP400YFX,FOLLOW_RULE_OP400YFX_in_ruleATOMS640); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP400YFX_16);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP400YFX_16, grammarAccess.getATOMSAccess().getOP400YFXTerminalRuleCall_16()); 
                          
                    }

                    }
                    break;
                case 18 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:263:10: this_OP200XFX_17= RULE_OP200XFX
                    {
                    this_OP200XFX_17=(Token)match(input,RULE_OP200XFX,FOLLOW_RULE_OP200XFX_in_ruleATOMS666); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP200XFX_17);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP200XFX_17, grammarAccess.getATOMSAccess().getOP200XFXTerminalRuleCall_17()); 
                          
                    }

                    }
                    break;
                case 19 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:271:10: this_OP200XFY_18= RULE_OP200XFY
                    {
                    this_OP200XFY_18=(Token)match(input,RULE_OP200XFY,FOLLOW_RULE_OP200XFY_in_ruleATOMS692); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      		current.merge(this_OP200XFY_18);
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                          newLeafNode(this_OP200XFY_18, grammarAccess.getATOMSAccess().getOP200XFYTerminalRuleCall_18()); 
                          
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
    // $ANTLR end "ruleATOMS"


    // $ANTLR start "entryRuleNUMBER"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:286:1: entryRuleNUMBER returns [String current=null] : iv_ruleNUMBER= ruleNUMBER EOF ;
    public final String entryRuleNUMBER() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNUMBER = null;


         
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
        	
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:290:2: (iv_ruleNUMBER= ruleNUMBER EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:291:2: iv_ruleNUMBER= ruleNUMBER EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getNUMBERRule()); 
            }
            pushFollow(FOLLOW_ruleNUMBER_in_entryRuleNUMBER744);
            iv_ruleNUMBER=ruleNUMBER();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleNUMBER.getText(); 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleNUMBER755); if (state.failed) return current;

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "entryRuleNUMBER"


    // $ANTLR start "ruleNUMBER"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:301:1: ruleNUMBER returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' (this_EXT_INT_3= RULE_EXT_INT | this_INT_4= RULE_INT ) )? ) ;
    public final AntlrDatatypeRuleToken ruleNUMBER() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;
        Token this_EXT_INT_3=null;
        Token this_INT_4=null;

         enterRule(); 
        		HiddenTokens myHiddenTokenState = ((XtextTokenStream)input).setHiddenTokens();
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:305:28: ( ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' (this_EXT_INT_3= RULE_EXT_INT | this_INT_4= RULE_INT ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:306:1: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' (this_EXT_INT_3= RULE_EXT_INT | this_INT_4= RULE_INT ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:306:1: ( (kw= '-' )? this_INT_1= RULE_INT (kw= '.' (this_EXT_INT_3= RULE_EXT_INT | this_INT_4= RULE_INT ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:306:2: (kw= '-' )? this_INT_1= RULE_INT (kw= '.' (this_EXT_INT_3= RULE_EXT_INT | this_INT_4= RULE_INT ) )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:306:2: (kw= '-' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0==29) ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:307:2: kw= '-'
                    {
                    kw=(Token)match(input,29,FOLLOW_29_in_ruleNUMBER798); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getNUMBERAccess().getHyphenMinusKeyword_0()); 
                          
                    }

                    }
                    break;

            }

            this_INT_1=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleNUMBER815); if (state.failed) return current;
            if ( state.backtracking==0 ) {

              		current.merge(this_INT_1);
                  
            }
            if ( state.backtracking==0 ) {
               
                  newLeafNode(this_INT_1, grammarAccess.getNUMBERAccess().getINTTerminalRuleCall_1()); 
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:319:1: (kw= '.' (this_EXT_INT_3= RULE_EXT_INT | this_INT_4= RULE_INT ) )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==27) ) {
                int LA5_1 = input.LA(2);

                if ( (LA5_1==RULE_INT) ) {
                    int LA5_3 = input.LA(3);

                    if ( (synpred22_InternalProlog()) ) {
                        alt5=1;
                    }
                }
                else if ( (LA5_1==RULE_EXT_INT) ) {
                    alt5=1;
                }
            }
            switch (alt5) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:320:2: kw= '.' (this_EXT_INT_3= RULE_EXT_INT | this_INT_4= RULE_INT )
                    {
                    kw=(Token)match(input,27,FOLLOW_27_in_ruleNUMBER834); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                              current.merge(kw);
                              newLeafNode(kw, grammarAccess.getNUMBERAccess().getFullStopKeyword_2_0()); 
                          
                    }
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:325:1: (this_EXT_INT_3= RULE_EXT_INT | this_INT_4= RULE_INT )
                    int alt4=2;
                    int LA4_0 = input.LA(1);

                    if ( (LA4_0==RULE_EXT_INT) ) {
                        alt4=1;
                    }
                    else if ( (LA4_0==RULE_INT) ) {
                        alt4=2;
                    }
                    else {
                        if (state.backtracking>0) {state.failed=true; return current;}
                        NoViableAltException nvae =
                            new NoViableAltException("", 4, 0, input);

                        throw nvae;
                    }
                    switch (alt4) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:325:6: this_EXT_INT_3= RULE_EXT_INT
                            {
                            this_EXT_INT_3=(Token)match(input,RULE_EXT_INT,FOLLOW_RULE_EXT_INT_in_ruleNUMBER850); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              		current.merge(this_EXT_INT_3);
                                  
                            }
                            if ( state.backtracking==0 ) {
                               
                                  newLeafNode(this_EXT_INT_3, grammarAccess.getNUMBERAccess().getEXT_INTTerminalRuleCall_2_1_0()); 
                                  
                            }

                            }
                            break;
                        case 2 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:333:10: this_INT_4= RULE_INT
                            {
                            this_INT_4=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleNUMBER876); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              		current.merge(this_INT_4);
                                  
                            }
                            if ( state.backtracking==0 ) {
                               
                                  newLeafNode(this_INT_4, grammarAccess.getNUMBERAccess().getINTTerminalRuleCall_2_1_1()); 
                                  
                            }

                            }
                            break;

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

            	myHiddenTokenState.restore();

        }
        return current;
    }
    // $ANTLR end "ruleNUMBER"


    // $ANTLR start "entryRuleExpressionInfinity"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:351:1: entryRuleExpressionInfinity returns [EObject current=null] : iv_ruleExpressionInfinity= ruleExpressionInfinity EOF ;
    public final EObject entryRuleExpressionInfinity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionInfinity = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:352:2: (iv_ruleExpressionInfinity= ruleExpressionInfinity EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:353:2: iv_ruleExpressionInfinity= ruleExpressionInfinity EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpressionInfinityRule()); 
            }
            pushFollow(FOLLOW_ruleExpressionInfinity_in_entryRuleExpressionInfinity928);
            iv_ruleExpressionInfinity=ruleExpressionInfinity();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpressionInfinity; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionInfinity938); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpressionInfinity"


    // $ANTLR start "ruleExpressionInfinity"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:360:1: ruleExpressionInfinity returns [EObject current=null] : this_Expression1200xfx_0= ruleExpression1200xfx ;
    public final EObject ruleExpressionInfinity() throws RecognitionException {
        EObject current = null;

        EObject this_Expression1200xfx_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:363:28: (this_Expression1200xfx_0= ruleExpression1200xfx )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:365:2: this_Expression1200xfx_0= ruleExpression1200xfx
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpressionInfinityAccess().getExpression1200xfxParserRuleCall()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression1200xfx_in_ruleExpressionInfinity987);
            this_Expression1200xfx_0=ruleExpression1200xfx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression1200xfx_0; 
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
    // $ANTLR end "ruleExpressionInfinity"


    // $ANTLR start "entryRuleExpression1200xfx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:384:1: entryRuleExpression1200xfx returns [EObject current=null] : iv_ruleExpression1200xfx= ruleExpression1200xfx EOF ;
    public final EObject entryRuleExpression1200xfx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1200xfx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:385:2: (iv_ruleExpression1200xfx= ruleExpression1200xfx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:386:2: iv_ruleExpression1200xfx= ruleExpression1200xfx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1200xfxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression1200xfx_in_entryRuleExpression1200xfx1021);
            iv_ruleExpression1200xfx=ruleExpression1200xfx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1200xfx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1200xfx1031); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression1200xfx"


    // $ANTLR start "ruleExpression1200xfx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:393:1: ruleExpression1200xfx returns [EObject current=null] : (this_Expression1200fx_0= ruleExpression1200fx ( () ( (lv_op_2_0= RULE_OP1200XFX ) ) ( (lv_right_3_0= ruleExpression1200fx ) ) )? ) ;
    public final EObject ruleExpression1200xfx() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_Expression1200fx_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:396:28: ( (this_Expression1200fx_0= ruleExpression1200fx ( () ( (lv_op_2_0= RULE_OP1200XFX ) ) ( (lv_right_3_0= ruleExpression1200fx ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:397:1: (this_Expression1200fx_0= ruleExpression1200fx ( () ( (lv_op_2_0= RULE_OP1200XFX ) ) ( (lv_right_3_0= ruleExpression1200fx ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:397:1: (this_Expression1200fx_0= ruleExpression1200fx ( () ( (lv_op_2_0= RULE_OP1200XFX ) ) ( (lv_right_3_0= ruleExpression1200fx ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:398:2: this_Expression1200fx_0= ruleExpression1200fx ( () ( (lv_op_2_0= RULE_OP1200XFX ) ) ( (lv_right_3_0= ruleExpression1200fx ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression1200xfxAccess().getExpression1200fxParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression1200fx_in_ruleExpression1200xfx1081);
            this_Expression1200fx_0=ruleExpression1200fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression1200fx_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:409:1: ( () ( (lv_op_2_0= RULE_OP1200XFX ) ) ( (lv_right_3_0= ruleExpression1200fx ) ) )?
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==RULE_OP1200XFX) ) {
                alt6=1;
            }
            switch (alt6) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:409:2: () ( (lv_op_2_0= RULE_OP1200XFX ) ) ( (lv_right_3_0= ruleExpression1200fx ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:409:2: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:410:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getExpression1200xfxAccess().getExpressionLeftAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:418:2: ( (lv_op_2_0= RULE_OP1200XFX ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:419:1: (lv_op_2_0= RULE_OP1200XFX )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:419:1: (lv_op_2_0= RULE_OP1200XFX )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:420:3: lv_op_2_0= RULE_OP1200XFX
                    {
                    lv_op_2_0=(Token)match(input,RULE_OP1200XFX,FOLLOW_RULE_OP1200XFX_in_ruleExpression1200xfx1110); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_2_0, grammarAccess.getExpression1200xfxAccess().getOpOP1200XFXTerminalRuleCall_1_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression1200xfxRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_2_0, 
                              		"OP1200XFX");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:436:2: ( (lv_right_3_0= ruleExpression1200fx ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:437:1: (lv_right_3_0= ruleExpression1200fx )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:437:1: (lv_right_3_0= ruleExpression1200fx )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:438:3: lv_right_3_0= ruleExpression1200fx
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression1200xfxAccess().getRightExpression1200fxParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression1200fx_in_ruleExpression1200xfx1136);
                    lv_right_3_0=ruleExpression1200fx();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression1200xfxRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_3_0, 
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
    // $ANTLR end "ruleExpression1200xfx"


    // $ANTLR start "entryRuleExpression1200fx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:462:1: entryRuleExpression1200fx returns [EObject current=null] : iv_ruleExpression1200fx= ruleExpression1200fx EOF ;
    public final EObject entryRuleExpression1200fx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1200fx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:463:2: (iv_ruleExpression1200fx= ruleExpression1200fx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:464:2: iv_ruleExpression1200fx= ruleExpression1200fx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1200fxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression1200fx_in_entryRuleExpression1200fx1174);
            iv_ruleExpression1200fx=ruleExpression1200fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1200fx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1200fx1184); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:471:1: ruleExpression1200fx returns [EObject current=null] : ( ( (lv_op_0_0= RULE_OP1200FX ) )? ( (lv_right_1_0= ruleExpression1150fx ) ) ) ;
    public final EObject ruleExpression1200fx() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_0=null;
        EObject lv_right_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:474:28: ( ( ( (lv_op_0_0= RULE_OP1200FX ) )? ( (lv_right_1_0= ruleExpression1150fx ) ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:475:1: ( ( (lv_op_0_0= RULE_OP1200FX ) )? ( (lv_right_1_0= ruleExpression1150fx ) ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:475:1: ( ( (lv_op_0_0= RULE_OP1200FX ) )? ( (lv_right_1_0= ruleExpression1150fx ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:475:2: ( (lv_op_0_0= RULE_OP1200FX ) )? ( (lv_right_1_0= ruleExpression1150fx ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:475:2: ( (lv_op_0_0= RULE_OP1200FX ) )?
            int alt7=2;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:476:1: (lv_op_0_0= RULE_OP1200FX )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:476:1: (lv_op_0_0= RULE_OP1200FX )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:477:3: lv_op_0_0= RULE_OP1200FX
                    {
                    lv_op_0_0=(Token)match(input,RULE_OP1200FX,FOLLOW_RULE_OP1200FX_in_ruleExpression1200fx1226); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_0_0, grammarAccess.getExpression1200fxAccess().getOpOP1200FXTerminalRuleCall_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression1200fxRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_0_0, 
                              		"OP1200FX");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:493:3: ( (lv_right_1_0= ruleExpression1150fx ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:494:1: (lv_right_1_0= ruleExpression1150fx )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:494:1: (lv_right_1_0= ruleExpression1150fx )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:495:3: lv_right_1_0= ruleExpression1150fx
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression1200fxAccess().getRightExpression1150fxParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression1150fx_in_ruleExpression1200fx1253);
            lv_right_1_0=ruleExpression1150fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression1200fxRule());
              	        }
                     		set(
                     			current, 
                     			"right",
                      		lv_right_1_0, 
                      		"Expression1150fx");
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


    // $ANTLR start "entryRuleExpression1150fx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:519:1: entryRuleExpression1150fx returns [EObject current=null] : iv_ruleExpression1150fx= ruleExpression1150fx EOF ;
    public final EObject entryRuleExpression1150fx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1150fx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:520:2: (iv_ruleExpression1150fx= ruleExpression1150fx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:521:2: iv_ruleExpression1150fx= ruleExpression1150fx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1150fxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression1150fx_in_entryRuleExpression1150fx1289);
            iv_ruleExpression1150fx=ruleExpression1150fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1150fx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1150fx1299); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression1150fx"


    // $ANTLR start "ruleExpression1150fx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:528:1: ruleExpression1150fx returns [EObject current=null] : ( ( (lv_op_0_0= RULE_OP1150FX ) )? ( (lv_right_1_0= ruleExpression1100xfy ) ) ) ;
    public final EObject ruleExpression1150fx() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_0=null;
        EObject lv_right_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:531:28: ( ( ( (lv_op_0_0= RULE_OP1150FX ) )? ( (lv_right_1_0= ruleExpression1100xfy ) ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:532:1: ( ( (lv_op_0_0= RULE_OP1150FX ) )? ( (lv_right_1_0= ruleExpression1100xfy ) ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:532:1: ( ( (lv_op_0_0= RULE_OP1150FX ) )? ( (lv_right_1_0= ruleExpression1100xfy ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:532:2: ( (lv_op_0_0= RULE_OP1150FX ) )? ( (lv_right_1_0= ruleExpression1100xfy ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:532:2: ( (lv_op_0_0= RULE_OP1150FX ) )?
            int alt8=2;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:533:1: (lv_op_0_0= RULE_OP1150FX )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:533:1: (lv_op_0_0= RULE_OP1150FX )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:534:3: lv_op_0_0= RULE_OP1150FX
                    {
                    lv_op_0_0=(Token)match(input,RULE_OP1150FX,FOLLOW_RULE_OP1150FX_in_ruleExpression1150fx1341); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_0_0, grammarAccess.getExpression1150fxAccess().getOpOP1150FXTerminalRuleCall_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression1150fxRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_0_0, 
                              		"OP1150FX");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:550:3: ( (lv_right_1_0= ruleExpression1100xfy ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:551:1: (lv_right_1_0= ruleExpression1100xfy )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:551:1: (lv_right_1_0= ruleExpression1100xfy )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:552:3: lv_right_1_0= ruleExpression1100xfy
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression1150fxAccess().getRightExpression1100xfyParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression1100xfy_in_ruleExpression1150fx1368);
            lv_right_1_0=ruleExpression1100xfy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression1150fxRule());
              	        }
                     		set(
                     			current, 
                     			"right",
                      		lv_right_1_0, 
                      		"Expression1100xfy");
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
    // $ANTLR end "ruleExpression1150fx"


    // $ANTLR start "entryRuleExpression1100xfy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:576:1: entryRuleExpression1100xfy returns [EObject current=null] : iv_ruleExpression1100xfy= ruleExpression1100xfy EOF ;
    public final EObject entryRuleExpression1100xfy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1100xfy = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:577:2: (iv_ruleExpression1100xfy= ruleExpression1100xfy EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:578:2: iv_ruleExpression1100xfy= ruleExpression1100xfy EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1100xfyRule()); 
            }
            pushFollow(FOLLOW_ruleExpression1100xfy_in_entryRuleExpression1100xfy1404);
            iv_ruleExpression1100xfy=ruleExpression1100xfy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1100xfy; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1100xfy1414); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression1100xfy"


    // $ANTLR start "ruleExpression1100xfy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:585:1: ruleExpression1100xfy returns [EObject current=null] : (this_Expression1050xfy_0= ruleExpression1050xfy ( () ( (lv_op_2_0= RULE_OP1100XFY ) ) ( (lv_right_3_0= ruleExpression1100xfy ) ) )? ) ;
    public final EObject ruleExpression1100xfy() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_Expression1050xfy_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:588:28: ( (this_Expression1050xfy_0= ruleExpression1050xfy ( () ( (lv_op_2_0= RULE_OP1100XFY ) ) ( (lv_right_3_0= ruleExpression1100xfy ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:589:1: (this_Expression1050xfy_0= ruleExpression1050xfy ( () ( (lv_op_2_0= RULE_OP1100XFY ) ) ( (lv_right_3_0= ruleExpression1100xfy ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:589:1: (this_Expression1050xfy_0= ruleExpression1050xfy ( () ( (lv_op_2_0= RULE_OP1100XFY ) ) ( (lv_right_3_0= ruleExpression1100xfy ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:590:2: this_Expression1050xfy_0= ruleExpression1050xfy ( () ( (lv_op_2_0= RULE_OP1100XFY ) ) ( (lv_right_3_0= ruleExpression1100xfy ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression1100xfyAccess().getExpression1050xfyParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression1050xfy_in_ruleExpression1100xfy1464);
            this_Expression1050xfy_0=ruleExpression1050xfy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression1050xfy_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:601:1: ( () ( (lv_op_2_0= RULE_OP1100XFY ) ) ( (lv_right_3_0= ruleExpression1100xfy ) ) )?
            int alt9=2;
            int LA9_0 = input.LA(1);

            if ( (LA9_0==RULE_OP1100XFY) ) {
                alt9=1;
            }
            switch (alt9) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:601:2: () ( (lv_op_2_0= RULE_OP1100XFY ) ) ( (lv_right_3_0= ruleExpression1100xfy ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:601:2: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:602:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getExpression1100xfyAccess().getExpressionLeftAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:610:2: ( (lv_op_2_0= RULE_OP1100XFY ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:611:1: (lv_op_2_0= RULE_OP1100XFY )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:611:1: (lv_op_2_0= RULE_OP1100XFY )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:612:3: lv_op_2_0= RULE_OP1100XFY
                    {
                    lv_op_2_0=(Token)match(input,RULE_OP1100XFY,FOLLOW_RULE_OP1100XFY_in_ruleExpression1100xfy1493); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_2_0, grammarAccess.getExpression1100xfyAccess().getOpOP1100XFYTerminalRuleCall_1_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression1100xfyRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_2_0, 
                              		"OP1100XFY");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:628:2: ( (lv_right_3_0= ruleExpression1100xfy ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:629:1: (lv_right_3_0= ruleExpression1100xfy )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:629:1: (lv_right_3_0= ruleExpression1100xfy )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:630:3: lv_right_3_0= ruleExpression1100xfy
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression1100xfyAccess().getRightExpression1100xfyParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression1100xfy_in_ruleExpression1100xfy1519);
                    lv_right_3_0=ruleExpression1100xfy();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression1100xfyRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_3_0, 
                              		"Expression1100xfy");
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
    // $ANTLR end "ruleExpression1100xfy"


    // $ANTLR start "entryRuleExpression1050xfy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:654:1: entryRuleExpression1050xfy returns [EObject current=null] : iv_ruleExpression1050xfy= ruleExpression1050xfy EOF ;
    public final EObject entryRuleExpression1050xfy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1050xfy = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:655:2: (iv_ruleExpression1050xfy= ruleExpression1050xfy EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:656:2: iv_ruleExpression1050xfy= ruleExpression1050xfy EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1050xfyRule()); 
            }
            pushFollow(FOLLOW_ruleExpression1050xfy_in_entryRuleExpression1050xfy1557);
            iv_ruleExpression1050xfy=ruleExpression1050xfy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1050xfy; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1050xfy1567); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression1050xfy"


    // $ANTLR start "ruleExpression1050xfy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:663:1: ruleExpression1050xfy returns [EObject current=null] : (this_Expression1000xfy_0= ruleExpression1000xfy ( () ( (lv_op_2_0= RULE_OP1050XFY ) ) ( (lv_right_3_0= ruleExpression1050xfy ) ) )? ) ;
    public final EObject ruleExpression1050xfy() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_Expression1000xfy_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:666:28: ( (this_Expression1000xfy_0= ruleExpression1000xfy ( () ( (lv_op_2_0= RULE_OP1050XFY ) ) ( (lv_right_3_0= ruleExpression1050xfy ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:667:1: (this_Expression1000xfy_0= ruleExpression1000xfy ( () ( (lv_op_2_0= RULE_OP1050XFY ) ) ( (lv_right_3_0= ruleExpression1050xfy ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:667:1: (this_Expression1000xfy_0= ruleExpression1000xfy ( () ( (lv_op_2_0= RULE_OP1050XFY ) ) ( (lv_right_3_0= ruleExpression1050xfy ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:668:2: this_Expression1000xfy_0= ruleExpression1000xfy ( () ( (lv_op_2_0= RULE_OP1050XFY ) ) ( (lv_right_3_0= ruleExpression1050xfy ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression1050xfyAccess().getExpression1000xfyParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression1000xfy_in_ruleExpression1050xfy1617);
            this_Expression1000xfy_0=ruleExpression1000xfy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression1000xfy_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:679:1: ( () ( (lv_op_2_0= RULE_OP1050XFY ) ) ( (lv_right_3_0= ruleExpression1050xfy ) ) )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_OP1050XFY) ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:679:2: () ( (lv_op_2_0= RULE_OP1050XFY ) ) ( (lv_right_3_0= ruleExpression1050xfy ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:679:2: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:680:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getExpression1050xfyAccess().getExpressionLeftAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:688:2: ( (lv_op_2_0= RULE_OP1050XFY ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:689:1: (lv_op_2_0= RULE_OP1050XFY )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:689:1: (lv_op_2_0= RULE_OP1050XFY )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:690:3: lv_op_2_0= RULE_OP1050XFY
                    {
                    lv_op_2_0=(Token)match(input,RULE_OP1050XFY,FOLLOW_RULE_OP1050XFY_in_ruleExpression1050xfy1646); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_2_0, grammarAccess.getExpression1050xfyAccess().getOpOP1050XFYTerminalRuleCall_1_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression1050xfyRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_2_0, 
                              		"OP1050XFY");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:706:2: ( (lv_right_3_0= ruleExpression1050xfy ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:707:1: (lv_right_3_0= ruleExpression1050xfy )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:707:1: (lv_right_3_0= ruleExpression1050xfy )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:708:3: lv_right_3_0= ruleExpression1050xfy
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression1050xfyAccess().getRightExpression1050xfyParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression1050xfy_in_ruleExpression1050xfy1672);
                    lv_right_3_0=ruleExpression1050xfy();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression1050xfyRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_3_0, 
                              		"Expression1050xfy");
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
    // $ANTLR end "ruleExpression1050xfy"


    // $ANTLR start "entryRuleExpression1000xfy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:732:1: entryRuleExpression1000xfy returns [EObject current=null] : iv_ruleExpression1000xfy= ruleExpression1000xfy EOF ;
    public final EObject entryRuleExpression1000xfy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1000xfy = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:733:2: (iv_ruleExpression1000xfy= ruleExpression1000xfy EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:734:2: iv_ruleExpression1000xfy= ruleExpression1000xfy EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression1000xfyRule()); 
            }
            pushFollow(FOLLOW_ruleExpression1000xfy_in_entryRuleExpression1000xfy1710);
            iv_ruleExpression1000xfy=ruleExpression1000xfy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression1000xfy; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1000xfy1720); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression1000xfy"


    // $ANTLR start "ruleExpression1000xfy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:741:1: ruleExpression1000xfy returns [EObject current=null] : (this_Expression954xfy_0= ruleExpression954xfy ( () ( (lv_op_2_0= RULE_OP1000XFY ) ) ( (lv_right_3_0= ruleExpression1000xfy ) ) )? ) ;
    public final EObject ruleExpression1000xfy() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_Expression954xfy_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:744:28: ( (this_Expression954xfy_0= ruleExpression954xfy ( () ( (lv_op_2_0= RULE_OP1000XFY ) ) ( (lv_right_3_0= ruleExpression1000xfy ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:745:1: (this_Expression954xfy_0= ruleExpression954xfy ( () ( (lv_op_2_0= RULE_OP1000XFY ) ) ( (lv_right_3_0= ruleExpression1000xfy ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:745:1: (this_Expression954xfy_0= ruleExpression954xfy ( () ( (lv_op_2_0= RULE_OP1000XFY ) ) ( (lv_right_3_0= ruleExpression1000xfy ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:746:2: this_Expression954xfy_0= ruleExpression954xfy ( () ( (lv_op_2_0= RULE_OP1000XFY ) ) ( (lv_right_3_0= ruleExpression1000xfy ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression1000xfyAccess().getExpression954xfyParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression954xfy_in_ruleExpression1000xfy1770);
            this_Expression954xfy_0=ruleExpression954xfy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression954xfy_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:757:1: ( () ( (lv_op_2_0= RULE_OP1000XFY ) ) ( (lv_right_3_0= ruleExpression1000xfy ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_OP1000XFY) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:757:2: () ( (lv_op_2_0= RULE_OP1000XFY ) ) ( (lv_right_3_0= ruleExpression1000xfy ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:757:2: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:758:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getExpression1000xfyAccess().getExpressionLeftAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:766:2: ( (lv_op_2_0= RULE_OP1000XFY ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:767:1: (lv_op_2_0= RULE_OP1000XFY )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:767:1: (lv_op_2_0= RULE_OP1000XFY )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:768:3: lv_op_2_0= RULE_OP1000XFY
                    {
                    lv_op_2_0=(Token)match(input,RULE_OP1000XFY,FOLLOW_RULE_OP1000XFY_in_ruleExpression1000xfy1799); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_2_0, grammarAccess.getExpression1000xfyAccess().getOpOP1000XFYTerminalRuleCall_1_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression1000xfyRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_2_0, 
                              		"OP1000XFY");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:784:2: ( (lv_right_3_0= ruleExpression1000xfy ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:785:1: (lv_right_3_0= ruleExpression1000xfy )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:785:1: (lv_right_3_0= ruleExpression1000xfy )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:786:3: lv_right_3_0= ruleExpression1000xfy
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression1000xfyAccess().getRightExpression1000xfyParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression1000xfy_in_ruleExpression1000xfy1825);
                    lv_right_3_0=ruleExpression1000xfy();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression1000xfyRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_3_0, 
                              		"Expression1000xfy");
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
    // $ANTLR end "ruleExpression1000xfy"


    // $ANTLR start "entryRuleExpression954xfy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:810:1: entryRuleExpression954xfy returns [EObject current=null] : iv_ruleExpression954xfy= ruleExpression954xfy EOF ;
    public final EObject entryRuleExpression954xfy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression954xfy = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:811:2: (iv_ruleExpression954xfy= ruleExpression954xfy EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:812:2: iv_ruleExpression954xfy= ruleExpression954xfy EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression954xfyRule()); 
            }
            pushFollow(FOLLOW_ruleExpression954xfy_in_entryRuleExpression954xfy1863);
            iv_ruleExpression954xfy=ruleExpression954xfy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression954xfy; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression954xfy1873); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression954xfy"


    // $ANTLR start "ruleExpression954xfy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:819:1: ruleExpression954xfy returns [EObject current=null] : (this_Expression900fy_0= ruleExpression900fy ( () ( (lv_op_2_0= RULE_OP954XFY ) ) ( (lv_right_3_0= ruleExpression954xfy ) ) )? ) ;
    public final EObject ruleExpression954xfy() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_Expression900fy_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:822:28: ( (this_Expression900fy_0= ruleExpression900fy ( () ( (lv_op_2_0= RULE_OP954XFY ) ) ( (lv_right_3_0= ruleExpression954xfy ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:823:1: (this_Expression900fy_0= ruleExpression900fy ( () ( (lv_op_2_0= RULE_OP954XFY ) ) ( (lv_right_3_0= ruleExpression954xfy ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:823:1: (this_Expression900fy_0= ruleExpression900fy ( () ( (lv_op_2_0= RULE_OP954XFY ) ) ( (lv_right_3_0= ruleExpression954xfy ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:824:2: this_Expression900fy_0= ruleExpression900fy ( () ( (lv_op_2_0= RULE_OP954XFY ) ) ( (lv_right_3_0= ruleExpression954xfy ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression954xfyAccess().getExpression900fyParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression900fy_in_ruleExpression954xfy1923);
            this_Expression900fy_0=ruleExpression900fy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression900fy_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:835:1: ( () ( (lv_op_2_0= RULE_OP954XFY ) ) ( (lv_right_3_0= ruleExpression954xfy ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==RULE_OP954XFY) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:835:2: () ( (lv_op_2_0= RULE_OP954XFY ) ) ( (lv_right_3_0= ruleExpression954xfy ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:835:2: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:836:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getExpression954xfyAccess().getExpressionLeftAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:844:2: ( (lv_op_2_0= RULE_OP954XFY ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:845:1: (lv_op_2_0= RULE_OP954XFY )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:845:1: (lv_op_2_0= RULE_OP954XFY )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:846:3: lv_op_2_0= RULE_OP954XFY
                    {
                    lv_op_2_0=(Token)match(input,RULE_OP954XFY,FOLLOW_RULE_OP954XFY_in_ruleExpression954xfy1952); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_2_0, grammarAccess.getExpression954xfyAccess().getOpOP954XFYTerminalRuleCall_1_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression954xfyRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_2_0, 
                              		"OP954XFY");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:862:2: ( (lv_right_3_0= ruleExpression954xfy ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:863:1: (lv_right_3_0= ruleExpression954xfy )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:863:1: (lv_right_3_0= ruleExpression954xfy )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:864:3: lv_right_3_0= ruleExpression954xfy
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression954xfyAccess().getRightExpression954xfyParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression954xfy_in_ruleExpression954xfy1978);
                    lv_right_3_0=ruleExpression954xfy();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression954xfyRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_3_0, 
                              		"Expression954xfy");
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
    // $ANTLR end "ruleExpression954xfy"


    // $ANTLR start "entryRuleExpression900fy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:888:1: entryRuleExpression900fy returns [EObject current=null] : iv_ruleExpression900fy= ruleExpression900fy EOF ;
    public final EObject entryRuleExpression900fy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression900fy = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:889:2: (iv_ruleExpression900fy= ruleExpression900fy EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:890:2: iv_ruleExpression900fy= ruleExpression900fy EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression900fyRule()); 
            }
            pushFollow(FOLLOW_ruleExpression900fy_in_entryRuleExpression900fy2016);
            iv_ruleExpression900fy=ruleExpression900fy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression900fy; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression900fy2026); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression900fy"


    // $ANTLR start "ruleExpression900fy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:897:1: ruleExpression900fy returns [EObject current=null] : ( ( (lv_op_0_0= RULE_OP900FY ) )? ( (lv_right_1_0= ruleExpression900fx ) ) ) ;
    public final EObject ruleExpression900fy() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_0=null;
        EObject lv_right_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:900:28: ( ( ( (lv_op_0_0= RULE_OP900FY ) )? ( (lv_right_1_0= ruleExpression900fx ) ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:901:1: ( ( (lv_op_0_0= RULE_OP900FY ) )? ( (lv_right_1_0= ruleExpression900fx ) ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:901:1: ( ( (lv_op_0_0= RULE_OP900FY ) )? ( (lv_right_1_0= ruleExpression900fx ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:901:2: ( (lv_op_0_0= RULE_OP900FY ) )? ( (lv_right_1_0= ruleExpression900fx ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:901:2: ( (lv_op_0_0= RULE_OP900FY ) )?
            int alt13=2;
            alt13 = dfa13.predict(input);
            switch (alt13) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:902:1: (lv_op_0_0= RULE_OP900FY )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:902:1: (lv_op_0_0= RULE_OP900FY )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:903:3: lv_op_0_0= RULE_OP900FY
                    {
                    lv_op_0_0=(Token)match(input,RULE_OP900FY,FOLLOW_RULE_OP900FY_in_ruleExpression900fy2068); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_0_0, grammarAccess.getExpression900fyAccess().getOpOP900FYTerminalRuleCall_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression900fyRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_0_0, 
                              		"OP900FY");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:919:3: ( (lv_right_1_0= ruleExpression900fx ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:920:1: (lv_right_1_0= ruleExpression900fx )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:920:1: (lv_right_1_0= ruleExpression900fx )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:921:3: lv_right_1_0= ruleExpression900fx
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression900fyAccess().getRightExpression900fxParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression900fx_in_ruleExpression900fy2095);
            lv_right_1_0=ruleExpression900fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression900fyRule());
              	        }
                     		set(
                     			current, 
                     			"right",
                      		lv_right_1_0, 
                      		"Expression900fx");
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
    // $ANTLR end "ruleExpression900fy"


    // $ANTLR start "entryRuleExpression900fx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:945:1: entryRuleExpression900fx returns [EObject current=null] : iv_ruleExpression900fx= ruleExpression900fx EOF ;
    public final EObject entryRuleExpression900fx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression900fx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:946:2: (iv_ruleExpression900fx= ruleExpression900fx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:947:2: iv_ruleExpression900fx= ruleExpression900fx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression900fxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression900fx_in_entryRuleExpression900fx2131);
            iv_ruleExpression900fx=ruleExpression900fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression900fx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression900fx2141); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:954:1: ruleExpression900fx returns [EObject current=null] : ( ( (lv_op_0_0= RULE_OP900FX ) )? ( (lv_right_1_0= ruleExpression700xfx ) ) ) ;
    public final EObject ruleExpression900fx() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_0=null;
        EObject lv_right_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:957:28: ( ( ( (lv_op_0_0= RULE_OP900FX ) )? ( (lv_right_1_0= ruleExpression700xfx ) ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:958:1: ( ( (lv_op_0_0= RULE_OP900FX ) )? ( (lv_right_1_0= ruleExpression700xfx ) ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:958:1: ( ( (lv_op_0_0= RULE_OP900FX ) )? ( (lv_right_1_0= ruleExpression700xfx ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:958:2: ( (lv_op_0_0= RULE_OP900FX ) )? ( (lv_right_1_0= ruleExpression700xfx ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:958:2: ( (lv_op_0_0= RULE_OP900FX ) )?
            int alt14=2;
            alt14 = dfa14.predict(input);
            switch (alt14) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:959:1: (lv_op_0_0= RULE_OP900FX )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:959:1: (lv_op_0_0= RULE_OP900FX )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:960:3: lv_op_0_0= RULE_OP900FX
                    {
                    lv_op_0_0=(Token)match(input,RULE_OP900FX,FOLLOW_RULE_OP900FX_in_ruleExpression900fx2183); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_0_0, grammarAccess.getExpression900fxAccess().getOpOP900FXTerminalRuleCall_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression900fxRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_0_0, 
                              		"OP900FX");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:976:3: ( (lv_right_1_0= ruleExpression700xfx ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:977:1: (lv_right_1_0= ruleExpression700xfx )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:977:1: (lv_right_1_0= ruleExpression700xfx )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:978:3: lv_right_1_0= ruleExpression700xfx
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression900fxAccess().getRightExpression700xfxParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression700xfx_in_ruleExpression900fx2210);
            lv_right_1_0=ruleExpression700xfx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression900fxRule());
              	        }
                     		set(
                     			current, 
                     			"right",
                      		lv_right_1_0, 
                      		"Expression700xfx");
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


    // $ANTLR start "entryRuleExpression700xfx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1002:1: entryRuleExpression700xfx returns [EObject current=null] : iv_ruleExpression700xfx= ruleExpression700xfx EOF ;
    public final EObject entryRuleExpression700xfx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression700xfx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1003:2: (iv_ruleExpression700xfx= ruleExpression700xfx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1004:2: iv_ruleExpression700xfx= ruleExpression700xfx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression700xfxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression700xfx_in_entryRuleExpression700xfx2246);
            iv_ruleExpression700xfx=ruleExpression700xfx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression700xfx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression700xfx2256); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression700xfx"


    // $ANTLR start "ruleExpression700xfx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1011:1: ruleExpression700xfx returns [EObject current=null] : (this_Expression600xfy_0= ruleExpression600xfy ( () ( (lv_op_2_0= RULE_OP700XFX ) ) ( (lv_right_3_0= ruleExpression600xfy ) ) )? ) ;
    public final EObject ruleExpression700xfx() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_Expression600xfy_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1014:28: ( (this_Expression600xfy_0= ruleExpression600xfy ( () ( (lv_op_2_0= RULE_OP700XFX ) ) ( (lv_right_3_0= ruleExpression600xfy ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1015:1: (this_Expression600xfy_0= ruleExpression600xfy ( () ( (lv_op_2_0= RULE_OP700XFX ) ) ( (lv_right_3_0= ruleExpression600xfy ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1015:1: (this_Expression600xfy_0= ruleExpression600xfy ( () ( (lv_op_2_0= RULE_OP700XFX ) ) ( (lv_right_3_0= ruleExpression600xfy ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1016:2: this_Expression600xfy_0= ruleExpression600xfy ( () ( (lv_op_2_0= RULE_OP700XFX ) ) ( (lv_right_3_0= ruleExpression600xfy ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression700xfxAccess().getExpression600xfyParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression600xfy_in_ruleExpression700xfx2306);
            this_Expression600xfy_0=ruleExpression600xfy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression600xfy_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1027:1: ( () ( (lv_op_2_0= RULE_OP700XFX ) ) ( (lv_right_3_0= ruleExpression600xfy ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==RULE_OP700XFX) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1027:2: () ( (lv_op_2_0= RULE_OP700XFX ) ) ( (lv_right_3_0= ruleExpression600xfy ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1027:2: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1028:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getExpression700xfxAccess().getExpressionLeftAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1036:2: ( (lv_op_2_0= RULE_OP700XFX ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1037:1: (lv_op_2_0= RULE_OP700XFX )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1037:1: (lv_op_2_0= RULE_OP700XFX )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1038:3: lv_op_2_0= RULE_OP700XFX
                    {
                    lv_op_2_0=(Token)match(input,RULE_OP700XFX,FOLLOW_RULE_OP700XFX_in_ruleExpression700xfx2335); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_2_0, grammarAccess.getExpression700xfxAccess().getOpOP700XFXTerminalRuleCall_1_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression700xfxRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_2_0, 
                              		"OP700XFX");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1054:2: ( (lv_right_3_0= ruleExpression600xfy ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1055:1: (lv_right_3_0= ruleExpression600xfy )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1055:1: (lv_right_3_0= ruleExpression600xfy )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1056:3: lv_right_3_0= ruleExpression600xfy
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression700xfxAccess().getRightExpression600xfyParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression600xfy_in_ruleExpression700xfx2361);
                    lv_right_3_0=ruleExpression600xfy();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression700xfxRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_3_0, 
                              		"Expression600xfy");
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
    // $ANTLR end "ruleExpression700xfx"


    // $ANTLR start "entryRuleExpression600xfy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1080:1: entryRuleExpression600xfy returns [EObject current=null] : iv_ruleExpression600xfy= ruleExpression600xfy EOF ;
    public final EObject entryRuleExpression600xfy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression600xfy = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1081:2: (iv_ruleExpression600xfy= ruleExpression600xfy EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1082:2: iv_ruleExpression600xfy= ruleExpression600xfy EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression600xfyRule()); 
            }
            pushFollow(FOLLOW_ruleExpression600xfy_in_entryRuleExpression600xfy2399);
            iv_ruleExpression600xfy=ruleExpression600xfy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression600xfy; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression600xfy2409); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression600xfy"


    // $ANTLR start "ruleExpression600xfy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1089:1: ruleExpression600xfy returns [EObject current=null] : (this_Expression500yfx_0= ruleExpression500yfx ( () ( (lv_op_2_0= RULE_OP600XFY ) ) ( (lv_right_3_0= ruleExpression600xfy ) ) )? ) ;
    public final EObject ruleExpression600xfy() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_Expression500yfx_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1092:28: ( (this_Expression500yfx_0= ruleExpression500yfx ( () ( (lv_op_2_0= RULE_OP600XFY ) ) ( (lv_right_3_0= ruleExpression600xfy ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1093:1: (this_Expression500yfx_0= ruleExpression500yfx ( () ( (lv_op_2_0= RULE_OP600XFY ) ) ( (lv_right_3_0= ruleExpression600xfy ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1093:1: (this_Expression500yfx_0= ruleExpression500yfx ( () ( (lv_op_2_0= RULE_OP600XFY ) ) ( (lv_right_3_0= ruleExpression600xfy ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1094:2: this_Expression500yfx_0= ruleExpression500yfx ( () ( (lv_op_2_0= RULE_OP600XFY ) ) ( (lv_right_3_0= ruleExpression600xfy ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression600xfyAccess().getExpression500yfxParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression500yfx_in_ruleExpression600xfy2459);
            this_Expression500yfx_0=ruleExpression500yfx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression500yfx_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1105:1: ( () ( (lv_op_2_0= RULE_OP600XFY ) ) ( (lv_right_3_0= ruleExpression600xfy ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==RULE_OP600XFY) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1105:2: () ( (lv_op_2_0= RULE_OP600XFY ) ) ( (lv_right_3_0= ruleExpression600xfy ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1105:2: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1106:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getExpression600xfyAccess().getExpressionLeftAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1114:2: ( (lv_op_2_0= RULE_OP600XFY ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1115:1: (lv_op_2_0= RULE_OP600XFY )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1115:1: (lv_op_2_0= RULE_OP600XFY )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1116:3: lv_op_2_0= RULE_OP600XFY
                    {
                    lv_op_2_0=(Token)match(input,RULE_OP600XFY,FOLLOW_RULE_OP600XFY_in_ruleExpression600xfy2488); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_2_0, grammarAccess.getExpression600xfyAccess().getOpOP600XFYTerminalRuleCall_1_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression600xfyRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_2_0, 
                              		"OP600XFY");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1132:2: ( (lv_right_3_0= ruleExpression600xfy ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1133:1: (lv_right_3_0= ruleExpression600xfy )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1133:1: (lv_right_3_0= ruleExpression600xfy )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1134:3: lv_right_3_0= ruleExpression600xfy
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression600xfyAccess().getRightExpression600xfyParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression600xfy_in_ruleExpression600xfy2514);
                    lv_right_3_0=ruleExpression600xfy();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression600xfyRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_3_0, 
                              		"Expression600xfy");
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
    // $ANTLR end "ruleExpression600xfy"


    // $ANTLR start "entryRuleExpression500yfx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1158:1: entryRuleExpression500yfx returns [EObject current=null] : iv_ruleExpression500yfx= ruleExpression500yfx EOF ;
    public final EObject entryRuleExpression500yfx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression500yfx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1159:2: (iv_ruleExpression500yfx= ruleExpression500yfx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1160:2: iv_ruleExpression500yfx= ruleExpression500yfx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression500yfxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression500yfx_in_entryRuleExpression500yfx2552);
            iv_ruleExpression500yfx=ruleExpression500yfx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression500yfx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression500yfx2562); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression500yfx"


    // $ANTLR start "ruleExpression500yfx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1167:1: ruleExpression500yfx returns [EObject current=null] : (this_Expression500fx_0= ruleExpression500fx ( () ( (lv_op_2_0= RULE_OP500YFX ) ) ( (lv_right_3_0= ruleExpression500fx ) ) )* ) ;
    public final EObject ruleExpression500yfx() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_Expression500fx_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1170:28: ( (this_Expression500fx_0= ruleExpression500fx ( () ( (lv_op_2_0= RULE_OP500YFX ) ) ( (lv_right_3_0= ruleExpression500fx ) ) )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1171:1: (this_Expression500fx_0= ruleExpression500fx ( () ( (lv_op_2_0= RULE_OP500YFX ) ) ( (lv_right_3_0= ruleExpression500fx ) ) )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1171:1: (this_Expression500fx_0= ruleExpression500fx ( () ( (lv_op_2_0= RULE_OP500YFX ) ) ( (lv_right_3_0= ruleExpression500fx ) ) )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1172:2: this_Expression500fx_0= ruleExpression500fx ( () ( (lv_op_2_0= RULE_OP500YFX ) ) ( (lv_right_3_0= ruleExpression500fx ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression500yfxAccess().getExpression500fxParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression500fx_in_ruleExpression500yfx2612);
            this_Expression500fx_0=ruleExpression500fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression500fx_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1183:1: ( () ( (lv_op_2_0= RULE_OP500YFX ) ) ( (lv_right_3_0= ruleExpression500fx ) ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==RULE_OP500YFX) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1183:2: () ( (lv_op_2_0= RULE_OP500YFX ) ) ( (lv_right_3_0= ruleExpression500fx ) )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1183:2: ()
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1184:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getExpression500yfxAccess().getExpressionLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1192:2: ( (lv_op_2_0= RULE_OP500YFX ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1193:1: (lv_op_2_0= RULE_OP500YFX )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1193:1: (lv_op_2_0= RULE_OP500YFX )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1194:3: lv_op_2_0= RULE_OP500YFX
            	    {
            	    lv_op_2_0=(Token)match(input,RULE_OP500YFX,FOLLOW_RULE_OP500YFX_in_ruleExpression500yfx2641); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      			newLeafNode(lv_op_2_0, grammarAccess.getExpression500yfxAccess().getOpOP500YFXTerminalRuleCall_1_1_0()); 
            	      		
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getExpression500yfxRule());
            	      	        }
            	             		setWithLastConsumed(
            	             			current, 
            	             			"op",
            	              		lv_op_2_0, 
            	              		"OP500YFX");
            	      	    
            	    }

            	    }


            	    }

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1210:2: ( (lv_right_3_0= ruleExpression500fx ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1211:1: (lv_right_3_0= ruleExpression500fx )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1211:1: (lv_right_3_0= ruleExpression500fx )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1212:3: lv_right_3_0= ruleExpression500fx
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExpression500yfxAccess().getRightExpression500fxParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpression500fx_in_ruleExpression500yfx2667);
            	    lv_right_3_0=ruleExpression500fx();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExpression500yfxRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"Expression500fx");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop17;
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
    // $ANTLR end "ruleExpression500yfx"


    // $ANTLR start "entryRuleExpression500fx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1236:1: entryRuleExpression500fx returns [EObject current=null] : iv_ruleExpression500fx= ruleExpression500fx EOF ;
    public final EObject entryRuleExpression500fx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression500fx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1237:2: (iv_ruleExpression500fx= ruleExpression500fx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1238:2: iv_ruleExpression500fx= ruleExpression500fx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression500fxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression500fx_in_entryRuleExpression500fx2705);
            iv_ruleExpression500fx=ruleExpression500fx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression500fx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression500fx2715); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression500fx"


    // $ANTLR start "ruleExpression500fx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1245:1: ruleExpression500fx returns [EObject current=null] : ( ( (lv_op_0_0= RULE_OP500FX ) )? ( (lv_right_1_0= ruleExpression400yfx ) ) ) ;
    public final EObject ruleExpression500fx() throws RecognitionException {
        EObject current = null;

        Token lv_op_0_0=null;
        EObject lv_right_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1248:28: ( ( ( (lv_op_0_0= RULE_OP500FX ) )? ( (lv_right_1_0= ruleExpression400yfx ) ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1249:1: ( ( (lv_op_0_0= RULE_OP500FX ) )? ( (lv_right_1_0= ruleExpression400yfx ) ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1249:1: ( ( (lv_op_0_0= RULE_OP500FX ) )? ( (lv_right_1_0= ruleExpression400yfx ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1249:2: ( (lv_op_0_0= RULE_OP500FX ) )? ( (lv_right_1_0= ruleExpression400yfx ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1249:2: ( (lv_op_0_0= RULE_OP500FX ) )?
            int alt18=2;
            alt18 = dfa18.predict(input);
            switch (alt18) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1250:1: (lv_op_0_0= RULE_OP500FX )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1250:1: (lv_op_0_0= RULE_OP500FX )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1251:3: lv_op_0_0= RULE_OP500FX
                    {
                    lv_op_0_0=(Token)match(input,RULE_OP500FX,FOLLOW_RULE_OP500FX_in_ruleExpression500fx2757); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_0_0, grammarAccess.getExpression500fxAccess().getOpOP500FXTerminalRuleCall_0_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression500fxRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_0_0, 
                              		"OP500FX");
                      	    
                    }

                    }


                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1267:3: ( (lv_right_1_0= ruleExpression400yfx ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1268:1: (lv_right_1_0= ruleExpression400yfx )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1268:1: (lv_right_1_0= ruleExpression400yfx )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1269:3: lv_right_1_0= ruleExpression400yfx
            {
            if ( state.backtracking==0 ) {
               
              	        newCompositeNode(grammarAccess.getExpression500fxAccess().getRightExpression400yfxParserRuleCall_1_0()); 
              	    
            }
            pushFollow(FOLLOW_ruleExpression400yfx_in_ruleExpression500fx2784);
            lv_right_1_0=ruleExpression400yfx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {

              	        if (current==null) {
              	            current = createModelElementForParent(grammarAccess.getExpression500fxRule());
              	        }
                     		set(
                     			current, 
                     			"right",
                      		lv_right_1_0, 
                      		"Expression400yfx");
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
    // $ANTLR end "ruleExpression500fx"


    // $ANTLR start "entryRuleExpression400yfx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1293:1: entryRuleExpression400yfx returns [EObject current=null] : iv_ruleExpression400yfx= ruleExpression400yfx EOF ;
    public final EObject entryRuleExpression400yfx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression400yfx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1294:2: (iv_ruleExpression400yfx= ruleExpression400yfx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1295:2: iv_ruleExpression400yfx= ruleExpression400yfx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression400yfxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression400yfx_in_entryRuleExpression400yfx2820);
            iv_ruleExpression400yfx=ruleExpression400yfx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression400yfx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression400yfx2830); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression400yfx"


    // $ANTLR start "ruleExpression400yfx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1302:1: ruleExpression400yfx returns [EObject current=null] : (this_Expression200xfx_0= ruleExpression200xfx ( () ( (lv_op_2_0= RULE_OP400YFX ) ) ( (lv_right_3_0= ruleExpression200xfx ) ) )* ) ;
    public final EObject ruleExpression400yfx() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_Expression200xfx_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1305:28: ( (this_Expression200xfx_0= ruleExpression200xfx ( () ( (lv_op_2_0= RULE_OP400YFX ) ) ( (lv_right_3_0= ruleExpression200xfx ) ) )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1306:1: (this_Expression200xfx_0= ruleExpression200xfx ( () ( (lv_op_2_0= RULE_OP400YFX ) ) ( (lv_right_3_0= ruleExpression200xfx ) ) )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1306:1: (this_Expression200xfx_0= ruleExpression200xfx ( () ( (lv_op_2_0= RULE_OP400YFX ) ) ( (lv_right_3_0= ruleExpression200xfx ) ) )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1307:2: this_Expression200xfx_0= ruleExpression200xfx ( () ( (lv_op_2_0= RULE_OP400YFX ) ) ( (lv_right_3_0= ruleExpression200xfx ) ) )*
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression400yfxAccess().getExpression200xfxParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression200xfx_in_ruleExpression400yfx2880);
            this_Expression200xfx_0=ruleExpression200xfx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression200xfx_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1318:1: ( () ( (lv_op_2_0= RULE_OP400YFX ) ) ( (lv_right_3_0= ruleExpression200xfx ) ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==RULE_OP400YFX) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1318:2: () ( (lv_op_2_0= RULE_OP400YFX ) ) ( (lv_right_3_0= ruleExpression200xfx ) )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1318:2: ()
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1319:2: 
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	  /* */ 
            	      	
            	    }
            	    if ( state.backtracking==0 ) {

            	              current = forceCreateModelElementAndSet(
            	                  grammarAccess.getExpression400yfxAccess().getExpressionLeftAction_1_0(),
            	                  current);
            	          
            	    }

            	    }

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1327:2: ( (lv_op_2_0= RULE_OP400YFX ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1328:1: (lv_op_2_0= RULE_OP400YFX )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1328:1: (lv_op_2_0= RULE_OP400YFX )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1329:3: lv_op_2_0= RULE_OP400YFX
            	    {
            	    lv_op_2_0=(Token)match(input,RULE_OP400YFX,FOLLOW_RULE_OP400YFX_in_ruleExpression400yfx2909); if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      			newLeafNode(lv_op_2_0, grammarAccess.getExpression400yfxAccess().getOpOP400YFXTerminalRuleCall_1_1_0()); 
            	      		
            	    }
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElement(grammarAccess.getExpression400yfxRule());
            	      	        }
            	             		setWithLastConsumed(
            	             			current, 
            	             			"op",
            	              		lv_op_2_0, 
            	              		"OP400YFX");
            	      	    
            	    }

            	    }


            	    }

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1345:2: ( (lv_right_3_0= ruleExpression200xfx ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1346:1: (lv_right_3_0= ruleExpression200xfx )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1346:1: (lv_right_3_0= ruleExpression200xfx )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1347:3: lv_right_3_0= ruleExpression200xfx
            	    {
            	    if ( state.backtracking==0 ) {
            	       
            	      	        newCompositeNode(grammarAccess.getExpression400yfxAccess().getRightExpression200xfxParserRuleCall_1_2_0()); 
            	      	    
            	    }
            	    pushFollow(FOLLOW_ruleExpression200xfx_in_ruleExpression400yfx2935);
            	    lv_right_3_0=ruleExpression200xfx();

            	    state._fsp--;
            	    if (state.failed) return current;
            	    if ( state.backtracking==0 ) {

            	      	        if (current==null) {
            	      	            current = createModelElementForParent(grammarAccess.getExpression400yfxRule());
            	      	        }
            	             		set(
            	             			current, 
            	             			"right",
            	              		lv_right_3_0, 
            	              		"Expression200xfx");
            	      	        afterParserOrEnumRuleCall();
            	      	    
            	    }

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
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
    // $ANTLR end "ruleExpression400yfx"


    // $ANTLR start "entryRuleExpression200xfx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1371:1: entryRuleExpression200xfx returns [EObject current=null] : iv_ruleExpression200xfx= ruleExpression200xfx EOF ;
    public final EObject entryRuleExpression200xfx() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression200xfx = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1372:2: (iv_ruleExpression200xfx= ruleExpression200xfx EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1373:2: iv_ruleExpression200xfx= ruleExpression200xfx EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression200xfxRule()); 
            }
            pushFollow(FOLLOW_ruleExpression200xfx_in_entryRuleExpression200xfx2973);
            iv_ruleExpression200xfx=ruleExpression200xfx();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression200xfx; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression200xfx2983); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression200xfx"


    // $ANTLR start "ruleExpression200xfx"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1380:1: ruleExpression200xfx returns [EObject current=null] : (this_Expression200xfy_0= ruleExpression200xfy ( () ( (lv_op_2_0= RULE_OP200XFX ) ) ( (lv_right_3_0= ruleExpression200xfy ) ) )? ) ;
    public final EObject ruleExpression200xfx() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_Expression200xfy_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1383:28: ( (this_Expression200xfy_0= ruleExpression200xfy ( () ( (lv_op_2_0= RULE_OP200XFX ) ) ( (lv_right_3_0= ruleExpression200xfy ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1384:1: (this_Expression200xfy_0= ruleExpression200xfy ( () ( (lv_op_2_0= RULE_OP200XFX ) ) ( (lv_right_3_0= ruleExpression200xfy ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1384:1: (this_Expression200xfy_0= ruleExpression200xfy ( () ( (lv_op_2_0= RULE_OP200XFX ) ) ( (lv_right_3_0= ruleExpression200xfy ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1385:2: this_Expression200xfy_0= ruleExpression200xfy ( () ( (lv_op_2_0= RULE_OP200XFX ) ) ( (lv_right_3_0= ruleExpression200xfy ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression200xfxAccess().getExpression200xfyParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression200xfy_in_ruleExpression200xfx3033);
            this_Expression200xfy_0=ruleExpression200xfy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression200xfy_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1396:1: ( () ( (lv_op_2_0= RULE_OP200XFX ) ) ( (lv_right_3_0= ruleExpression200xfy ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==RULE_OP200XFX) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1396:2: () ( (lv_op_2_0= RULE_OP200XFX ) ) ( (lv_right_3_0= ruleExpression200xfy ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1396:2: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1397:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getExpression200xfxAccess().getExpressionLeftAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1405:2: ( (lv_op_2_0= RULE_OP200XFX ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1406:1: (lv_op_2_0= RULE_OP200XFX )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1406:1: (lv_op_2_0= RULE_OP200XFX )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1407:3: lv_op_2_0= RULE_OP200XFX
                    {
                    lv_op_2_0=(Token)match(input,RULE_OP200XFX,FOLLOW_RULE_OP200XFX_in_ruleExpression200xfx3062); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_2_0, grammarAccess.getExpression200xfxAccess().getOpOP200XFXTerminalRuleCall_1_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression200xfxRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_2_0, 
                              		"OP200XFX");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1423:2: ( (lv_right_3_0= ruleExpression200xfy ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1424:1: (lv_right_3_0= ruleExpression200xfy )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1424:1: (lv_right_3_0= ruleExpression200xfy )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1425:3: lv_right_3_0= ruleExpression200xfy
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression200xfxAccess().getRightExpression200xfyParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression200xfy_in_ruleExpression200xfx3088);
                    lv_right_3_0=ruleExpression200xfy();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression200xfxRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_3_0, 
                              		"Expression200xfy");
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
    // $ANTLR end "ruleExpression200xfx"


    // $ANTLR start "entryRuleExpression200xfy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1449:1: entryRuleExpression200xfy returns [EObject current=null] : iv_ruleExpression200xfy= ruleExpression200xfy EOF ;
    public final EObject entryRuleExpression200xfy() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression200xfy = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1450:2: (iv_ruleExpression200xfy= ruleExpression200xfy EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1451:2: iv_ruleExpression200xfy= ruleExpression200xfy EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression200xfyRule()); 
            }
            pushFollow(FOLLOW_ruleExpression200xfy_in_entryRuleExpression200xfy3126);
            iv_ruleExpression200xfy=ruleExpression200xfy();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression200xfy; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression200xfy3136); if (state.failed) return current;

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
    // $ANTLR end "entryRuleExpression200xfy"


    // $ANTLR start "ruleExpression200xfy"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1458:1: ruleExpression200xfy returns [EObject current=null] : (this_Expression0_0= ruleExpression0 ( () ( (lv_op_2_0= RULE_OP200XFY ) ) ( (lv_right_3_0= ruleExpression200xfy ) ) )? ) ;
    public final EObject ruleExpression200xfy() throws RecognitionException {
        EObject current = null;

        Token lv_op_2_0=null;
        EObject this_Expression0_0 = null;

        EObject lv_right_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1461:28: ( (this_Expression0_0= ruleExpression0 ( () ( (lv_op_2_0= RULE_OP200XFY ) ) ( (lv_right_3_0= ruleExpression200xfy ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1462:1: (this_Expression0_0= ruleExpression0 ( () ( (lv_op_2_0= RULE_OP200XFY ) ) ( (lv_right_3_0= ruleExpression200xfy ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1462:1: (this_Expression0_0= ruleExpression0 ( () ( (lv_op_2_0= RULE_OP200XFY ) ) ( (lv_right_3_0= ruleExpression200xfy ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1463:2: this_Expression0_0= ruleExpression0 ( () ( (lv_op_2_0= RULE_OP200XFY ) ) ( (lv_right_3_0= ruleExpression200xfy ) ) )?
            {
            if ( state.backtracking==0 ) {
               
              	  /* */ 
              	
            }
            if ( state.backtracking==0 ) {
               
                      newCompositeNode(grammarAccess.getExpression200xfyAccess().getExpression0ParserRuleCall_0()); 
                  
            }
            pushFollow(FOLLOW_ruleExpression0_in_ruleExpression200xfy3186);
            this_Expression0_0=ruleExpression0();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               
                      current = this_Expression0_0; 
                      afterParserOrEnumRuleCall();
                  
            }
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1474:1: ( () ( (lv_op_2_0= RULE_OP200XFY ) ) ( (lv_right_3_0= ruleExpression200xfy ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==RULE_OP200XFY) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1474:2: () ( (lv_op_2_0= RULE_OP200XFY ) ) ( (lv_right_3_0= ruleExpression200xfy ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1474:2: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1475:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElementAndSet(
                                  grammarAccess.getExpression200xfyAccess().getExpressionLeftAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1483:2: ( (lv_op_2_0= RULE_OP200XFY ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1484:1: (lv_op_2_0= RULE_OP200XFY )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1484:1: (lv_op_2_0= RULE_OP200XFY )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1485:3: lv_op_2_0= RULE_OP200XFY
                    {
                    lv_op_2_0=(Token)match(input,RULE_OP200XFY,FOLLOW_RULE_OP200XFY_in_ruleExpression200xfy3215); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_op_2_0, grammarAccess.getExpression200xfyAccess().getOpOP200XFYTerminalRuleCall_1_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression200xfyRule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"op",
                              		lv_op_2_0, 
                              		"OP200XFY");
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1501:2: ( (lv_right_3_0= ruleExpression200xfy ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1502:1: (lv_right_3_0= ruleExpression200xfy )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1502:1: (lv_right_3_0= ruleExpression200xfy )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1503:3: lv_right_3_0= ruleExpression200xfy
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression200xfyAccess().getRightExpression200xfyParserRuleCall_1_2_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleExpression200xfy_in_ruleExpression200xfy3241);
                    lv_right_3_0=ruleExpression200xfy();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression200xfyRule());
                      	        }
                             		set(
                             			current, 
                             			"right",
                              		lv_right_3_0, 
                              		"Expression200xfy");
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
    // $ANTLR end "ruleExpression200xfy"


    // $ANTLR start "entryRuleExpression0"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1527:1: entryRuleExpression0 returns [EObject current=null] : iv_ruleExpression0= ruleExpression0 EOF ;
    public final EObject entryRuleExpression0() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression0 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1528:2: (iv_ruleExpression0= ruleExpression0 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1529:2: iv_ruleExpression0= ruleExpression0 EOF
            {
            if ( state.backtracking==0 ) {
               newCompositeNode(grammarAccess.getExpression0Rule()); 
            }
            pushFollow(FOLLOW_ruleExpression0_in_entryRuleExpression03279);
            iv_ruleExpression0=ruleExpression0();

            state._fsp--;
            if (state.failed) return current;
            if ( state.backtracking==0 ) {
               current =iv_ruleExpression0; 
            }
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression03289); if (state.failed) return current;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1536:1: ruleExpression0 returns [EObject current=null] : ( ( () ( (lv_atom_1_0= ruleATOMS ) ) (otherlv_2= '(' ( (lv_terms_3_0= ruleExpressionInfinity ) ) otherlv_4= ')' )? ) | ( () ( (lv_name_6_0= RULE_VARIABLE ) ) ) | ( () ( (lv_value_8_0= RULE_STRING ) ) ) | ( () ( (lv_value_10_0= ruleNUMBER ) ) ) | ( () otherlv_12= '[' ( ( (lv_head_13_0= ruleExpressionInfinity ) ) (otherlv_14= '|' ( (lv_tail_15_0= ruleExpressionInfinity ) ) )? )? otherlv_16= ']' ) | (otherlv_17= '(' this_ExpressionInfinity_18= ruleExpressionInfinity otherlv_19= ')' ) ) ;
    public final EObject ruleExpression0() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_name_6_0=null;
        Token lv_value_8_0=null;
        Token otherlv_12=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_17=null;
        Token otherlv_19=null;
        AntlrDatatypeRuleToken lv_atom_1_0 = null;

        EObject lv_terms_3_0 = null;

        AntlrDatatypeRuleToken lv_value_10_0 = null;

        EObject lv_head_13_0 = null;

        EObject lv_tail_15_0 = null;

        EObject this_ExpressionInfinity_18 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1539:28: ( ( ( () ( (lv_atom_1_0= ruleATOMS ) ) (otherlv_2= '(' ( (lv_terms_3_0= ruleExpressionInfinity ) ) otherlv_4= ')' )? ) | ( () ( (lv_name_6_0= RULE_VARIABLE ) ) ) | ( () ( (lv_value_8_0= RULE_STRING ) ) ) | ( () ( (lv_value_10_0= ruleNUMBER ) ) ) | ( () otherlv_12= '[' ( ( (lv_head_13_0= ruleExpressionInfinity ) ) (otherlv_14= '|' ( (lv_tail_15_0= ruleExpressionInfinity ) ) )? )? otherlv_16= ']' ) | (otherlv_17= '(' this_ExpressionInfinity_18= ruleExpressionInfinity otherlv_19= ')' ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1540:1: ( ( () ( (lv_atom_1_0= ruleATOMS ) ) (otherlv_2= '(' ( (lv_terms_3_0= ruleExpressionInfinity ) ) otherlv_4= ')' )? ) | ( () ( (lv_name_6_0= RULE_VARIABLE ) ) ) | ( () ( (lv_value_8_0= RULE_STRING ) ) ) | ( () ( (lv_value_10_0= ruleNUMBER ) ) ) | ( () otherlv_12= '[' ( ( (lv_head_13_0= ruleExpressionInfinity ) ) (otherlv_14= '|' ( (lv_tail_15_0= ruleExpressionInfinity ) ) )? )? otherlv_16= ']' ) | (otherlv_17= '(' this_ExpressionInfinity_18= ruleExpressionInfinity otherlv_19= ')' ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1540:1: ( ( () ( (lv_atom_1_0= ruleATOMS ) ) (otherlv_2= '(' ( (lv_terms_3_0= ruleExpressionInfinity ) ) otherlv_4= ')' )? ) | ( () ( (lv_name_6_0= RULE_VARIABLE ) ) ) | ( () ( (lv_value_8_0= RULE_STRING ) ) ) | ( () ( (lv_value_10_0= ruleNUMBER ) ) ) | ( () otherlv_12= '[' ( ( (lv_head_13_0= ruleExpressionInfinity ) ) (otherlv_14= '|' ( (lv_tail_15_0= ruleExpressionInfinity ) ) )? )? otherlv_16= ']' ) | (otherlv_17= '(' this_ExpressionInfinity_18= ruleExpressionInfinity otherlv_19= ')' ) )
            int alt25=6;
            switch ( input.LA(1) ) {
            case RULE_ATOM:
            case RULE_OP1200XFX:
            case RULE_OP1200FX:
            case RULE_OP1150FX:
            case RULE_OP1100XFY:
            case RULE_OP1050XFY:
            case RULE_OP1000XFY:
            case RULE_OP954XFY:
            case RULE_OP900FY:
            case RULE_OP900FX:
            case RULE_OP700XFX:
            case RULE_OP600XFY:
            case RULE_OP500YFX:
            case RULE_OP500FX:
            case RULE_OP400YFX:
            case RULE_OP200XFX:
            case RULE_OP200XFY:
            case 27:
            case 28:
                {
                alt25=1;
                }
                break;
            case RULE_VARIABLE:
                {
                alt25=2;
                }
                break;
            case RULE_STRING:
                {
                alt25=3;
                }
                break;
            case RULE_INT:
            case 29:
                {
                alt25=4;
                }
                break;
            case 32:
                {
                alt25=5;
                }
                break;
            case 30:
                {
                alt25=6;
                }
                break;
            default:
                if (state.backtracking>0) {state.failed=true; return current;}
                NoViableAltException nvae =
                    new NoViableAltException("", 25, 0, input);

                throw nvae;
            }

            switch (alt25) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1540:2: ( () ( (lv_atom_1_0= ruleATOMS ) ) (otherlv_2= '(' ( (lv_terms_3_0= ruleExpressionInfinity ) ) otherlv_4= ')' )? )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1540:2: ( () ( (lv_atom_1_0= ruleATOMS ) ) (otherlv_2= '(' ( (lv_terms_3_0= ruleExpressionInfinity ) ) otherlv_4= ')' )? )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1540:3: () ( (lv_atom_1_0= ruleATOMS ) ) (otherlv_2= '(' ( (lv_terms_3_0= ruleExpressionInfinity ) ) otherlv_4= ')' )?
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1540:3: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1541:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getExpression0Access().getAtomExpressionAction_0_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1549:2: ( (lv_atom_1_0= ruleATOMS ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1550:1: (lv_atom_1_0= ruleATOMS )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1550:1: (lv_atom_1_0= ruleATOMS )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1551:3: lv_atom_1_0= ruleATOMS
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression0Access().getAtomATOMSParserRuleCall_0_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleATOMS_in_ruleExpression03348);
                    lv_atom_1_0=ruleATOMS();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                      	        }
                             		set(
                             			current, 
                             			"atom",
                              		lv_atom_1_0, 
                              		"ATOMS");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1567:2: (otherlv_2= '(' ( (lv_terms_3_0= ruleExpressionInfinity ) ) otherlv_4= ')' )?
                    int alt22=2;
                    int LA22_0 = input.LA(1);

                    if ( (LA22_0==30) ) {
                        alt22=1;
                    }
                    switch (alt22) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1567:4: otherlv_2= '(' ( (lv_terms_3_0= ruleExpressionInfinity ) ) otherlv_4= ')'
                            {
                            otherlv_2=(Token)match(input,30,FOLLOW_30_in_ruleExpression03361); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_2, grammarAccess.getExpression0Access().getLeftParenthesisKeyword_0_2_0());
                                  
                            }
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1571:1: ( (lv_terms_3_0= ruleExpressionInfinity ) )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1572:1: (lv_terms_3_0= ruleExpressionInfinity )
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1572:1: (lv_terms_3_0= ruleExpressionInfinity )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1573:3: lv_terms_3_0= ruleExpressionInfinity
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getExpression0Access().getTermsExpressionInfinityParserRuleCall_0_2_1_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpressionInfinity_in_ruleExpression03382);
                            lv_terms_3_0=ruleExpressionInfinity();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                              	        }
                                     		set(
                                     			current, 
                                     			"terms",
                                      		lv_terms_3_0, 
                                      		"ExpressionInfinity");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            otherlv_4=(Token)match(input,31,FOLLOW_31_in_ruleExpression03394); if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                                  	newLeafNode(otherlv_4, grammarAccess.getExpression0Access().getRightParenthesisKeyword_0_2_2());
                                  
                            }

                            }
                            break;

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1594:6: ( () ( (lv_name_6_0= RULE_VARIABLE ) ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1594:6: ( () ( (lv_name_6_0= RULE_VARIABLE ) ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1594:7: () ( (lv_name_6_0= RULE_VARIABLE ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1594:7: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1595:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getExpression0Access().getVariableExpressionAction_1_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1603:2: ( (lv_name_6_0= RULE_VARIABLE ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1604:1: (lv_name_6_0= RULE_VARIABLE )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1604:1: (lv_name_6_0= RULE_VARIABLE )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1605:3: lv_name_6_0= RULE_VARIABLE
                    {
                    lv_name_6_0=(Token)match(input,RULE_VARIABLE,FOLLOW_RULE_VARIABLE_in_ruleExpression03433); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_name_6_0, grammarAccess.getExpression0Access().getNameVARIABLETerminalRuleCall_1_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression0Rule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"name",
                              		lv_name_6_0, 
                              		"VARIABLE");
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1622:6: ( () ( (lv_value_8_0= RULE_STRING ) ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1622:6: ( () ( (lv_value_8_0= RULE_STRING ) ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1622:7: () ( (lv_value_8_0= RULE_STRING ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1622:7: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1623:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getExpression0Access().getStringExpressionAction_2_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1631:2: ( (lv_value_8_0= RULE_STRING ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1632:1: (lv_value_8_0= RULE_STRING )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1632:1: (lv_value_8_0= RULE_STRING )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1633:3: lv_value_8_0= RULE_STRING
                    {
                    lv_value_8_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleExpression03475); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      			newLeafNode(lv_value_8_0, grammarAccess.getExpression0Access().getValueSTRINGTerminalRuleCall_2_1_0()); 
                      		
                    }
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElement(grammarAccess.getExpression0Rule());
                      	        }
                             		setWithLastConsumed(
                             			current, 
                             			"value",
                              		lv_value_8_0, 
                              		"STRING");
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1650:6: ( () ( (lv_value_10_0= ruleNUMBER ) ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1650:6: ( () ( (lv_value_10_0= ruleNUMBER ) ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1650:7: () ( (lv_value_10_0= ruleNUMBER ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1650:7: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1651:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getExpression0Access().getNumberExpressionAction_3_0(),
                                  current);
                          
                    }

                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1659:2: ( (lv_value_10_0= ruleNUMBER ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1660:1: (lv_value_10_0= ruleNUMBER )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1660:1: (lv_value_10_0= ruleNUMBER )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1661:3: lv_value_10_0= ruleNUMBER
                    {
                    if ( state.backtracking==0 ) {
                       
                      	        newCompositeNode(grammarAccess.getExpression0Access().getValueNUMBERParserRuleCall_3_1_0()); 
                      	    
                    }
                    pushFollow(FOLLOW_ruleNUMBER_in_ruleExpression03521);
                    lv_value_10_0=ruleNUMBER();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                      	        if (current==null) {
                      	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                      	        }
                             		set(
                             			current, 
                             			"value",
                              		lv_value_10_0, 
                              		"NUMBER");
                      	        afterParserOrEnumRuleCall();
                      	    
                    }

                    }


                    }


                    }


                    }
                    break;
                case 5 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1678:6: ( () otherlv_12= '[' ( ( (lv_head_13_0= ruleExpressionInfinity ) ) (otherlv_14= '|' ( (lv_tail_15_0= ruleExpressionInfinity ) ) )? )? otherlv_16= ']' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1678:6: ( () otherlv_12= '[' ( ( (lv_head_13_0= ruleExpressionInfinity ) ) (otherlv_14= '|' ( (lv_tail_15_0= ruleExpressionInfinity ) ) )? )? otherlv_16= ']' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1678:7: () otherlv_12= '[' ( ( (lv_head_13_0= ruleExpressionInfinity ) ) (otherlv_14= '|' ( (lv_tail_15_0= ruleExpressionInfinity ) ) )? )? otherlv_16= ']'
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1678:7: ()
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1679:2: 
                    {
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {

                              current = forceCreateModelElement(
                                  grammarAccess.getExpression0Access().getListExpressionAction_4_0(),
                                  current);
                          
                    }

                    }

                    otherlv_12=(Token)match(input,32,FOLLOW_32_in_ruleExpression03553); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_12, grammarAccess.getExpression0Access().getLeftSquareBracketKeyword_4_1());
                          
                    }
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1691:1: ( ( (lv_head_13_0= ruleExpressionInfinity ) ) (otherlv_14= '|' ( (lv_tail_15_0= ruleExpressionInfinity ) ) )? )?
                    int alt24=2;
                    int LA24_0 = input.LA(1);

                    if ( ((LA24_0>=RULE_ATOM && LA24_0<=RULE_INT)||(LA24_0>=RULE_VARIABLE && LA24_0<=RULE_STRING)||(LA24_0>=27 && LA24_0<=30)||LA24_0==32) ) {
                        alt24=1;
                    }
                    switch (alt24) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1691:2: ( (lv_head_13_0= ruleExpressionInfinity ) ) (otherlv_14= '|' ( (lv_tail_15_0= ruleExpressionInfinity ) ) )?
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1691:2: ( (lv_head_13_0= ruleExpressionInfinity ) )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1692:1: (lv_head_13_0= ruleExpressionInfinity )
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1692:1: (lv_head_13_0= ruleExpressionInfinity )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1693:3: lv_head_13_0= ruleExpressionInfinity
                            {
                            if ( state.backtracking==0 ) {
                               
                              	        newCompositeNode(grammarAccess.getExpression0Access().getHeadExpressionInfinityParserRuleCall_4_2_0_0()); 
                              	    
                            }
                            pushFollow(FOLLOW_ruleExpressionInfinity_in_ruleExpression03575);
                            lv_head_13_0=ruleExpressionInfinity();

                            state._fsp--;
                            if (state.failed) return current;
                            if ( state.backtracking==0 ) {

                              	        if (current==null) {
                              	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                              	        }
                                     		set(
                                     			current, 
                                     			"head",
                                      		lv_head_13_0, 
                                      		"ExpressionInfinity");
                              	        afterParserOrEnumRuleCall();
                              	    
                            }

                            }


                            }

                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1709:2: (otherlv_14= '|' ( (lv_tail_15_0= ruleExpressionInfinity ) ) )?
                            int alt23=2;
                            int LA23_0 = input.LA(1);

                            if ( (LA23_0==33) ) {
                                alt23=1;
                            }
                            switch (alt23) {
                                case 1 :
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1709:4: otherlv_14= '|' ( (lv_tail_15_0= ruleExpressionInfinity ) )
                                    {
                                    otherlv_14=(Token)match(input,33,FOLLOW_33_in_ruleExpression03588); if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                          	newLeafNode(otherlv_14, grammarAccess.getExpression0Access().getVerticalLineKeyword_4_2_1_0());
                                          
                                    }
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1713:1: ( (lv_tail_15_0= ruleExpressionInfinity ) )
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1714:1: (lv_tail_15_0= ruleExpressionInfinity )
                                    {
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1714:1: (lv_tail_15_0= ruleExpressionInfinity )
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1715:3: lv_tail_15_0= ruleExpressionInfinity
                                    {
                                    if ( state.backtracking==0 ) {
                                       
                                      	        newCompositeNode(grammarAccess.getExpression0Access().getTailExpressionInfinityParserRuleCall_4_2_1_1_0()); 
                                      	    
                                    }
                                    pushFollow(FOLLOW_ruleExpressionInfinity_in_ruleExpression03609);
                                    lv_tail_15_0=ruleExpressionInfinity();

                                    state._fsp--;
                                    if (state.failed) return current;
                                    if ( state.backtracking==0 ) {

                                      	        if (current==null) {
                                      	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                                      	        }
                                             		set(
                                             			current, 
                                             			"tail",
                                              		lv_tail_15_0, 
                                              		"ExpressionInfinity");
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

                    otherlv_16=(Token)match(input,34,FOLLOW_34_in_ruleExpression03625); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_16, grammarAccess.getExpression0Access().getRightSquareBracketKeyword_4_3());
                          
                    }

                    }


                    }
                    break;
                case 6 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1736:6: (otherlv_17= '(' this_ExpressionInfinity_18= ruleExpressionInfinity otherlv_19= ')' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1736:6: (otherlv_17= '(' this_ExpressionInfinity_18= ruleExpressionInfinity otherlv_19= ')' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1736:8: otherlv_17= '(' this_ExpressionInfinity_18= ruleExpressionInfinity otherlv_19= ')'
                    {
                    otherlv_17=(Token)match(input,30,FOLLOW_30_in_ruleExpression03645); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_17, grammarAccess.getExpression0Access().getLeftParenthesisKeyword_5_0());
                          
                    }
                    if ( state.backtracking==0 ) {
                       
                      	  /* */ 
                      	
                    }
                    if ( state.backtracking==0 ) {
                       
                              newCompositeNode(grammarAccess.getExpression0Access().getExpressionInfinityParserRuleCall_5_1()); 
                          
                    }
                    pushFollow(FOLLOW_ruleExpressionInfinity_in_ruleExpression03670);
                    this_ExpressionInfinity_18=ruleExpressionInfinity();

                    state._fsp--;
                    if (state.failed) return current;
                    if ( state.backtracking==0 ) {
                       
                              current = this_ExpressionInfinity_18; 
                              afterParserOrEnumRuleCall();
                          
                    }
                    otherlv_19=(Token)match(input,31,FOLLOW_31_in_ruleExpression03681); if (state.failed) return current;
                    if ( state.backtracking==0 ) {

                          	newLeafNode(otherlv_19, grammarAccess.getExpression0Access().getRightParenthesisKeyword_5_2());
                          
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

    // $ANTLR start synpred22_InternalProlog
    public final void synpred22_InternalProlog_fragment() throws RecognitionException {   
        Token kw=null;
        Token this_EXT_INT_3=null;
        Token this_INT_4=null;

        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:320:2: (kw= '.' (this_EXT_INT_3= RULE_EXT_INT | this_INT_4= RULE_INT ) )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:320:2: kw= '.' (this_EXT_INT_3= RULE_EXT_INT | this_INT_4= RULE_INT )
        {
        kw=(Token)match(input,27,FOLLOW_27_in_synpred22_InternalProlog834); if (state.failed) return ;
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:325:1: (this_EXT_INT_3= RULE_EXT_INT | this_INT_4= RULE_INT )
        int alt26=2;
        int LA26_0 = input.LA(1);

        if ( (LA26_0==RULE_EXT_INT) ) {
            alt26=1;
        }
        else if ( (LA26_0==RULE_INT) ) {
            alt26=2;
        }
        else {
            if (state.backtracking>0) {state.failed=true; return ;}
            NoViableAltException nvae =
                new NoViableAltException("", 26, 0, input);

            throw nvae;
        }
        switch (alt26) {
            case 1 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:325:6: this_EXT_INT_3= RULE_EXT_INT
                {
                this_EXT_INT_3=(Token)match(input,RULE_EXT_INT,FOLLOW_RULE_EXT_INT_in_synpred22_InternalProlog850); if (state.failed) return ;

                }
                break;
            case 2 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:333:10: this_INT_4= RULE_INT
                {
                this_INT_4=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_synpred22_InternalProlog876); if (state.failed) return ;

                }
                break;

        }


        }
    }
    // $ANTLR end synpred22_InternalProlog

    // $ANTLR start synpred24_InternalProlog
    public final void synpred24_InternalProlog_fragment() throws RecognitionException {   
        Token lv_op_0_0=null;

        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:476:1: ( (lv_op_0_0= RULE_OP1200FX ) )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:476:1: (lv_op_0_0= RULE_OP1200FX )
        {
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:476:1: (lv_op_0_0= RULE_OP1200FX )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:477:3: lv_op_0_0= RULE_OP1200FX
        {
        lv_op_0_0=(Token)match(input,RULE_OP1200FX,FOLLOW_RULE_OP1200FX_in_synpred24_InternalProlog1226); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred24_InternalProlog

    // $ANTLR start synpred25_InternalProlog
    public final void synpred25_InternalProlog_fragment() throws RecognitionException {   
        Token lv_op_0_0=null;

        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:533:1: ( (lv_op_0_0= RULE_OP1150FX ) )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:533:1: (lv_op_0_0= RULE_OP1150FX )
        {
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:533:1: (lv_op_0_0= RULE_OP1150FX )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:534:3: lv_op_0_0= RULE_OP1150FX
        {
        lv_op_0_0=(Token)match(input,RULE_OP1150FX,FOLLOW_RULE_OP1150FX_in_synpred25_InternalProlog1341); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred25_InternalProlog

    // $ANTLR start synpred30_InternalProlog
    public final void synpred30_InternalProlog_fragment() throws RecognitionException {   
        Token lv_op_0_0=null;

        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:902:1: ( (lv_op_0_0= RULE_OP900FY ) )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:902:1: (lv_op_0_0= RULE_OP900FY )
        {
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:902:1: (lv_op_0_0= RULE_OP900FY )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:903:3: lv_op_0_0= RULE_OP900FY
        {
        lv_op_0_0=(Token)match(input,RULE_OP900FY,FOLLOW_RULE_OP900FY_in_synpred30_InternalProlog2068); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred30_InternalProlog

    // $ANTLR start synpred31_InternalProlog
    public final void synpred31_InternalProlog_fragment() throws RecognitionException {   
        Token lv_op_0_0=null;

        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:959:1: ( (lv_op_0_0= RULE_OP900FX ) )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:959:1: (lv_op_0_0= RULE_OP900FX )
        {
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:959:1: (lv_op_0_0= RULE_OP900FX )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:960:3: lv_op_0_0= RULE_OP900FX
        {
        lv_op_0_0=(Token)match(input,RULE_OP900FX,FOLLOW_RULE_OP900FX_in_synpred31_InternalProlog2183); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred31_InternalProlog

    // $ANTLR start synpred35_InternalProlog
    public final void synpred35_InternalProlog_fragment() throws RecognitionException {   
        Token lv_op_0_0=null;

        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1250:1: ( (lv_op_0_0= RULE_OP500FX ) )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1250:1: (lv_op_0_0= RULE_OP500FX )
        {
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1250:1: (lv_op_0_0= RULE_OP500FX )
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1251:3: lv_op_0_0= RULE_OP500FX
        {
        lv_op_0_0=(Token)match(input,RULE_OP500FX,FOLLOW_RULE_OP500FX_in_synpred35_InternalProlog2757); if (state.failed) return ;

        }


        }
    }
    // $ANTLR end synpred35_InternalProlog

    // Delegated rules

    public final boolean synpred25_InternalProlog() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred25_InternalProlog_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred24_InternalProlog() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred24_InternalProlog_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred22_InternalProlog() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred22_InternalProlog_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred35_InternalProlog() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred35_InternalProlog_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred31_InternalProlog() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred31_InternalProlog_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }
    public final boolean synpred30_InternalProlog() {
        state.backtracking++;
        int start = input.mark();
        try {
            synpred30_InternalProlog_fragment(); // can never throw exception
        } catch (RecognitionException re) {
            System.err.println("impossible: "+re);
        }
        boolean success = !state.failed;
        input.rewind(start);
        state.backtracking--;
        state.failed=false;
        return success;
    }


    protected DFA7 dfa7 = new DFA7(this);
    protected DFA8 dfa8 = new DFA8(this);
    protected DFA13 dfa13 = new DFA13(this);
    protected DFA14 dfa14 = new DFA14(this);
    protected DFA18 dfa18 = new DFA18(this);
    static final String DFA7_eotS =
        "\33\uffff";
    static final String DFA7_eofS =
        "\33\uffff";
    static final String DFA7_minS =
        "\1\4\1\0\31\uffff";
    static final String DFA7_maxS =
        "\1\40\1\0\31\uffff";
    static final String DFA7_acceptS =
        "\2\uffff\1\2\27\uffff\1\1";
    static final String DFA7_specialS =
        "\1\uffff\1\0\31\uffff}>";
    static final String[] DFA7_transitionS = {
            "\2\2\1\1\17\2\1\uffff\2\2\2\uffff\4\2\1\uffff\1\2",
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
            return "475:2: ( (lv_op_0_0= RULE_OP1200FX ) )?";
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
                        if ( (synpred24_InternalProlog()) ) {s = 26;}

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
    static final String DFA8_eotS =
        "\33\uffff";
    static final String DFA8_eofS =
        "\33\uffff";
    static final String DFA8_minS =
        "\1\4\1\0\31\uffff";
    static final String DFA8_maxS =
        "\1\40\1\0\31\uffff";
    static final String DFA8_acceptS =
        "\2\uffff\1\2\27\uffff\1\1";
    static final String DFA8_specialS =
        "\1\uffff\1\0\31\uffff}>";
    static final String[] DFA8_transitionS = {
            "\3\2\1\1\16\2\1\uffff\2\2\2\uffff\4\2\1\uffff\1\2",
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
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA8_eot = DFA.unpackEncodedString(DFA8_eotS);
    static final short[] DFA8_eof = DFA.unpackEncodedString(DFA8_eofS);
    static final char[] DFA8_min = DFA.unpackEncodedStringToUnsignedChars(DFA8_minS);
    static final char[] DFA8_max = DFA.unpackEncodedStringToUnsignedChars(DFA8_maxS);
    static final short[] DFA8_accept = DFA.unpackEncodedString(DFA8_acceptS);
    static final short[] DFA8_special = DFA.unpackEncodedString(DFA8_specialS);
    static final short[][] DFA8_transition;

    static {
        int numStates = DFA8_transitionS.length;
        DFA8_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA8_transition[i] = DFA.unpackEncodedString(DFA8_transitionS[i]);
        }
    }

    class DFA8 extends DFA {

        public DFA8(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 8;
            this.eot = DFA8_eot;
            this.eof = DFA8_eof;
            this.min = DFA8_min;
            this.max = DFA8_max;
            this.accept = DFA8_accept;
            this.special = DFA8_special;
            this.transition = DFA8_transition;
        }
        public String getDescription() {
            return "532:2: ( (lv_op_0_0= RULE_OP1150FX ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA8_1 = input.LA(1);

                         
                        int index8_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred25_InternalProlog()) ) {s = 26;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index8_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 8, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA13_eotS =
        "\33\uffff";
    static final String DFA13_eofS =
        "\33\uffff";
    static final String DFA13_minS =
        "\1\4\1\0\31\uffff";
    static final String DFA13_maxS =
        "\1\40\1\0\31\uffff";
    static final String DFA13_acceptS =
        "\2\uffff\1\2\27\uffff\1\1";
    static final String DFA13_specialS =
        "\1\uffff\1\0\31\uffff}>";
    static final String[] DFA13_transitionS = {
            "\10\2\1\1\11\2\1\uffff\2\2\2\uffff\4\2\1\uffff\1\2",
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
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "901:2: ( (lv_op_0_0= RULE_OP900FY ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_1 = input.LA(1);

                         
                        int index13_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred30_InternalProlog()) ) {s = 26;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index13_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA14_eotS =
        "\33\uffff";
    static final String DFA14_eofS =
        "\33\uffff";
    static final String DFA14_minS =
        "\1\4\1\0\31\uffff";
    static final String DFA14_maxS =
        "\1\40\1\0\31\uffff";
    static final String DFA14_acceptS =
        "\2\uffff\1\2\27\uffff\1\1";
    static final String DFA14_specialS =
        "\1\uffff\1\0\31\uffff}>";
    static final String[] DFA14_transitionS = {
            "\11\2\1\1\10\2\1\uffff\2\2\2\uffff\4\2\1\uffff\1\2",
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
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA14_eot = DFA.unpackEncodedString(DFA14_eotS);
    static final short[] DFA14_eof = DFA.unpackEncodedString(DFA14_eofS);
    static final char[] DFA14_min = DFA.unpackEncodedStringToUnsignedChars(DFA14_minS);
    static final char[] DFA14_max = DFA.unpackEncodedStringToUnsignedChars(DFA14_maxS);
    static final short[] DFA14_accept = DFA.unpackEncodedString(DFA14_acceptS);
    static final short[] DFA14_special = DFA.unpackEncodedString(DFA14_specialS);
    static final short[][] DFA14_transition;

    static {
        int numStates = DFA14_transitionS.length;
        DFA14_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA14_transition[i] = DFA.unpackEncodedString(DFA14_transitionS[i]);
        }
    }

    class DFA14 extends DFA {

        public DFA14(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 14;
            this.eot = DFA14_eot;
            this.eof = DFA14_eof;
            this.min = DFA14_min;
            this.max = DFA14_max;
            this.accept = DFA14_accept;
            this.special = DFA14_special;
            this.transition = DFA14_transition;
        }
        public String getDescription() {
            return "958:2: ( (lv_op_0_0= RULE_OP900FX ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA14_1 = input.LA(1);

                         
                        int index14_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred31_InternalProlog()) ) {s = 26;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index14_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 14, _s, input);
            error(nvae);
            throw nvae;
        }
    }
    static final String DFA18_eotS =
        "\33\uffff";
    static final String DFA18_eofS =
        "\33\uffff";
    static final String DFA18_minS =
        "\1\4\1\0\31\uffff";
    static final String DFA18_maxS =
        "\1\40\1\0\31\uffff";
    static final String DFA18_acceptS =
        "\2\uffff\1\2\27\uffff\1\1";
    static final String DFA18_specialS =
        "\1\uffff\1\0\31\uffff}>";
    static final String[] DFA18_transitionS = {
            "\15\2\1\1\4\2\1\uffff\2\2\2\uffff\4\2\1\uffff\1\2",
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
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA18_eot = DFA.unpackEncodedString(DFA18_eotS);
    static final short[] DFA18_eof = DFA.unpackEncodedString(DFA18_eofS);
    static final char[] DFA18_min = DFA.unpackEncodedStringToUnsignedChars(DFA18_minS);
    static final char[] DFA18_max = DFA.unpackEncodedStringToUnsignedChars(DFA18_maxS);
    static final short[] DFA18_accept = DFA.unpackEncodedString(DFA18_acceptS);
    static final short[] DFA18_special = DFA.unpackEncodedString(DFA18_specialS);
    static final short[][] DFA18_transition;

    static {
        int numStates = DFA18_transitionS.length;
        DFA18_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA18_transition[i] = DFA.unpackEncodedString(DFA18_transitionS[i]);
        }
    }

    class DFA18 extends DFA {

        public DFA18(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 18;
            this.eot = DFA18_eot;
            this.eof = DFA18_eof;
            this.min = DFA18_min;
            this.max = DFA18_max;
            this.accept = DFA18_accept;
            this.special = DFA18_special;
            this.transition = DFA18_transition;
        }
        public String getDescription() {
            return "1249:2: ( (lv_op_0_0= RULE_OP500FX ) )?";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            TokenStream input = (TokenStream)_input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA18_1 = input.LA(1);

                         
                        int index18_1 = input.index();
                        input.rewind();
                        s = -1;
                        if ( (synpred35_InternalProlog()) ) {s = 26;}

                        else if ( (true) ) {s = 2;}

                         
                        input.seek(index18_1);
                        if ( s>=0 ) return s;
                        break;
            }
            if (state.backtracking>0) {state.failed=true; return -1;}
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 18, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

    public static final BitSet FOLLOW_ruleModel_in_entryRuleModel81 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleModel91 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionInfinity_in_ruleModel137 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleModel149 = new BitSet(new long[]{0x0000000179BFFFF2L});
    public static final BitSet FOLLOW_ruleATOMS_in_entryRuleATOMS187 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleATOMS198 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleATOMS236 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleATOMS255 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ATOM_in_ruleATOMS276 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP1200XFX_in_ruleATOMS302 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP1200FX_in_ruleATOMS328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP1150FX_in_ruleATOMS354 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP1100XFY_in_ruleATOMS380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP1050XFY_in_ruleATOMS406 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP1000XFY_in_ruleATOMS432 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP954XFY_in_ruleATOMS458 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP900FY_in_ruleATOMS484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP900FX_in_ruleATOMS510 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP700XFX_in_ruleATOMS536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP600XFY_in_ruleATOMS562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP500YFX_in_ruleATOMS588 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP500FX_in_ruleATOMS614 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP400YFX_in_ruleATOMS640 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP200XFX_in_ruleATOMS666 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP200XFY_in_ruleATOMS692 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNUMBER_in_entryRuleNUMBER744 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNUMBER755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleNUMBER798 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleNUMBER815 = new BitSet(new long[]{0x0000000008000002L});
    public static final BitSet FOLLOW_27_in_ruleNUMBER834 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_RULE_EXT_INT_in_ruleNUMBER850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleNUMBER876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionInfinity_in_entryRuleExpressionInfinity928 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionInfinity938 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1200xfx_in_ruleExpressionInfinity987 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1200xfx_in_entryRuleExpression1200xfx1021 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1200xfx1031 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1200fx_in_ruleExpression1200xfx1081 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_RULE_OP1200XFX_in_ruleExpression1200xfx1110 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression1200fx_in_ruleExpression1200xfx1136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1200fx_in_entryRuleExpression1200fx1174 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1200fx1184 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP1200FX_in_ruleExpression1200fx1226 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression1150fx_in_ruleExpression1200fx1253 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1150fx_in_entryRuleExpression1150fx1289 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1150fx1299 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP1150FX_in_ruleExpression1150fx1341 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression1100xfy_in_ruleExpression1150fx1368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1100xfy_in_entryRuleExpression1100xfy1404 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1100xfy1414 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1050xfy_in_ruleExpression1100xfy1464 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_RULE_OP1100XFY_in_ruleExpression1100xfy1493 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression1100xfy_in_ruleExpression1100xfy1519 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1050xfy_in_entryRuleExpression1050xfy1557 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1050xfy1567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1000xfy_in_ruleExpression1050xfy1617 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_RULE_OP1050XFY_in_ruleExpression1050xfy1646 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression1050xfy_in_ruleExpression1050xfy1672 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1000xfy_in_entryRuleExpression1000xfy1710 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1000xfy1720 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression954xfy_in_ruleExpression1000xfy1770 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_RULE_OP1000XFY_in_ruleExpression1000xfy1799 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression1000xfy_in_ruleExpression1000xfy1825 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression954xfy_in_entryRuleExpression954xfy1863 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression954xfy1873 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression900fy_in_ruleExpression954xfy1923 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_RULE_OP954XFY_in_ruleExpression954xfy1952 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression954xfy_in_ruleExpression954xfy1978 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression900fy_in_entryRuleExpression900fy2016 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression900fy2026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP900FY_in_ruleExpression900fy2068 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression900fx_in_ruleExpression900fy2095 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression900fx_in_entryRuleExpression900fx2131 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression900fx2141 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP900FX_in_ruleExpression900fx2183 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression700xfx_in_ruleExpression900fx2210 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression700xfx_in_entryRuleExpression700xfx2246 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression700xfx2256 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression600xfy_in_ruleExpression700xfx2306 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_RULE_OP700XFX_in_ruleExpression700xfx2335 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression600xfy_in_ruleExpression700xfx2361 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression600xfy_in_entryRuleExpression600xfy2399 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression600xfy2409 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression500yfx_in_ruleExpression600xfy2459 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_RULE_OP600XFY_in_ruleExpression600xfy2488 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression600xfy_in_ruleExpression600xfy2514 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression500yfx_in_entryRuleExpression500yfx2552 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression500yfx2562 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression500fx_in_ruleExpression500yfx2612 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_RULE_OP500YFX_in_ruleExpression500yfx2641 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression500fx_in_ruleExpression500yfx2667 = new BitSet(new long[]{0x0000000000010002L});
    public static final BitSet FOLLOW_ruleExpression500fx_in_entryRuleExpression500fx2705 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression500fx2715 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP500FX_in_ruleExpression500fx2757 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression400yfx_in_ruleExpression500fx2784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression400yfx_in_entryRuleExpression400yfx2820 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression400yfx2830 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression200xfx_in_ruleExpression400yfx2880 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_RULE_OP400YFX_in_ruleExpression400yfx2909 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression200xfx_in_ruleExpression400yfx2935 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_ruleExpression200xfx_in_entryRuleExpression200xfx2973 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression200xfx2983 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression200xfy_in_ruleExpression200xfx3033 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_RULE_OP200XFX_in_ruleExpression200xfx3062 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression200xfy_in_ruleExpression200xfx3088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression200xfy_in_entryRuleExpression200xfy3126 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression200xfy3136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression0_in_ruleExpression200xfy3186 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_RULE_OP200XFY_in_ruleExpression200xfy3215 = new BitSet(new long[]{0x0000000179BFFFF0L});
    public static final BitSet FOLLOW_ruleExpression200xfy_in_ruleExpression200xfy3241 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression0_in_entryRuleExpression03279 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression03289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleATOMS_in_ruleExpression03348 = new BitSet(new long[]{0x0000000040000002L});
    public static final BitSet FOLLOW_30_in_ruleExpression03361 = new BitSet(new long[]{0x00000001F9BFFFF0L});
    public static final BitSet FOLLOW_ruleExpressionInfinity_in_ruleExpression03382 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_ruleExpression03394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_VARIABLE_in_ruleExpression03433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleExpression03475 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNUMBER_in_ruleExpression03521 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleExpression03553 = new BitSet(new long[]{0x0000000779BFFFF0L});
    public static final BitSet FOLLOW_ruleExpressionInfinity_in_ruleExpression03575 = new BitSet(new long[]{0x0000000600000000L});
    public static final BitSet FOLLOW_33_in_ruleExpression03588 = new BitSet(new long[]{0x0000000579BFFFF0L});
    public static final BitSet FOLLOW_ruleExpressionInfinity_in_ruleExpression03609 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleExpression03625 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleExpression03645 = new BitSet(new long[]{0x00000001F9BFFFF0L});
    public static final BitSet FOLLOW_ruleExpressionInfinity_in_ruleExpression03670 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_31_in_ruleExpression03681 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_synpred22_InternalProlog834 = new BitSet(new long[]{0x0000000000600000L});
    public static final BitSet FOLLOW_RULE_EXT_INT_in_synpred22_InternalProlog850 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_synpred22_InternalProlog876 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP1200FX_in_synpred24_InternalProlog1226 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP1150FX_in_synpred25_InternalProlog1341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP900FY_in_synpred30_InternalProlog2068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP900FX_in_synpred31_InternalProlog2183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_OP500FX_in_synpred35_InternalProlog2757 = new BitSet(new long[]{0x0000000000000002L});

}