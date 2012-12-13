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

@SuppressWarnings("all")
public class InternalPrologParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_EXPRESSION_1100", "RULE_EXPRESSION_900", "RULE_EXPRESSION_700", "RULE_EXPRESSION_500", "RULE_EXPRESSION_400", "RULE_EXPRESSION_200", "RULE_LOWER_CASE_LETTER", "RULE_DIGIT", "RULE_UPPER_CASE_LETTER", "RULE_WHITESPACE", "RULE_SINGLE_LINE_COMMENT", "':-'", "','", "'.'", "'?-'", "'('", "')'", "'\\''", "'['", "'|'", "']'", "'_'", "'+'", "'-'", "'*'", "'/'", "'\\\\'", "'^'", "'~'", "':'", "'?'", "'@'", "'#'", "'$'", "'&'"
    };
    public static final int RULE_UPPER_CASE_LETTER=12;
    public static final int T__29=29;
    public static final int T__28=28;
    public static final int T__27=27;
    public static final int T__26=26;
    public static final int T__25=25;
    public static final int T__24=24;
    public static final int T__23=23;
    public static final int T__22=22;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_EXPRESSION_500=7;
    public static final int RULE_EXPRESSION_400=8;
    public static final int RULE_LOWER_CASE_LETTER=10;
    public static final int RULE_SINGLE_LINE_COMMENT=14;
    public static final int EOF=-1;
    public static final int RULE_EXPRESSION_900=5;
    public static final int T__19=19;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_EXPRESSION_200=9;
    public static final int T__32=32;
    public static final int T__33=33;
    public static final int T__16=16;
    public static final int T__34=34;
    public static final int T__15=15;
    public static final int T__35=35;
    public static final int T__18=18;
    public static final int T__36=36;
    public static final int T__17=17;
    public static final int T__37=37;
    public static final int T__38=38;
    public static final int RULE_EXPRESSION_1100=4;
    public static final int RULE_WHITESPACE=13;
    public static final int RULE_DIGIT=11;
    public static final int RULE_EXPRESSION_700=6;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:67:1: entryRuleProgram returns [EObject current=null] : iv_ruleProgram= ruleProgram EOF ;
    public final EObject entryRuleProgram() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProgram = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:68:2: (iv_ruleProgram= ruleProgram EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:69:2: iv_ruleProgram= ruleProgram EOF
            {
             newCompositeNode(grammarAccess.getProgramRule()); 
            pushFollow(FOLLOW_ruleProgram_in_entryRuleProgram75);
            iv_ruleProgram=ruleProgram();

            state._fsp--;

             current =iv_ruleProgram; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleProgram85); 

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:76:1: ruleProgram returns [EObject current=null] : ( ( (lv_clauses_0_0= ruleClause ) )* ( (lv_query_1_0= ruleQuery ) )? ) ;
    public final EObject ruleProgram() throws RecognitionException {
        EObject current = null;

        EObject lv_clauses_0_0 = null;

        EObject lv_query_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:79:28: ( ( ( (lv_clauses_0_0= ruleClause ) )* ( (lv_query_1_0= ruleQuery ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:80:1: ( ( (lv_clauses_0_0= ruleClause ) )* ( (lv_query_1_0= ruleQuery ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:80:1: ( ( (lv_clauses_0_0= ruleClause ) )* ( (lv_query_1_0= ruleQuery ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:80:2: ( (lv_clauses_0_0= ruleClause ) )* ( (lv_query_1_0= ruleQuery ) )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:80:2: ( (lv_clauses_0_0= ruleClause ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RULE_EXPRESSION_1100 && LA1_0<=RULE_UPPER_CASE_LETTER)||LA1_0==17||LA1_0==19||(LA1_0>=21 && LA1_0<=22)||LA1_0==25||LA1_0==27) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:81:1: (lv_clauses_0_0= ruleClause )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:81:1: (lv_clauses_0_0= ruleClause )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:82:3: lv_clauses_0_0= ruleClause
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProgramAccess().getClausesClauseParserRuleCall_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleClause_in_ruleProgram131);
            	    lv_clauses_0_0=ruleClause();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProgramRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"clauses",
            	            		lv_clauses_0_0, 
            	            		"Clause");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:98:3: ( (lv_query_1_0= ruleQuery ) )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==18) ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:99:1: (lv_query_1_0= ruleQuery )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:99:1: (lv_query_1_0= ruleQuery )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:100:3: lv_query_1_0= ruleQuery
                    {
                     
                    	        newCompositeNode(grammarAccess.getProgramAccess().getQueryQueryParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleQuery_in_ruleProgram153);
                    lv_query_1_0=ruleQuery();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getProgramRule());
                    	        }
                           		set(
                           			current, 
                           			"query",
                            		lv_query_1_0, 
                            		"Query");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }
                    break;

            }


            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleClause"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:124:1: entryRuleClause returns [EObject current=null] : iv_ruleClause= ruleClause EOF ;
    public final EObject entryRuleClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleClause = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:125:2: (iv_ruleClause= ruleClause EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:126:2: iv_ruleClause= ruleClause EOF
            {
             newCompositeNode(grammarAccess.getClauseRule()); 
            pushFollow(FOLLOW_ruleClause_in_entryRuleClause190);
            iv_ruleClause=ruleClause();

            state._fsp--;

             current =iv_ruleClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleClause200); 

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
    // $ANTLR end "entryRuleClause"


    // $ANTLR start "ruleClause"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:133:1: ruleClause returns [EObject current=null] : ( ( (lv_predicates_0_0= ruleExpressionINF ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )* )? otherlv_5= '.' ) ;
    public final EObject ruleClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_predicates_0_0 = null;

        EObject lv_predicates_2_0 = null;

        EObject lv_predicates_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:136:28: ( ( ( (lv_predicates_0_0= ruleExpressionINF ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )* )? otherlv_5= '.' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:137:1: ( ( (lv_predicates_0_0= ruleExpressionINF ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )* )? otherlv_5= '.' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:137:1: ( ( (lv_predicates_0_0= ruleExpressionINF ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )* )? otherlv_5= '.' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:137:2: ( (lv_predicates_0_0= ruleExpressionINF ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )* )? otherlv_5= '.'
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:137:2: ( (lv_predicates_0_0= ruleExpressionINF ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:138:1: (lv_predicates_0_0= ruleExpressionINF )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:138:1: (lv_predicates_0_0= ruleExpressionINF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:139:3: lv_predicates_0_0= ruleExpressionINF
            {
             
            	        newCompositeNode(grammarAccess.getClauseAccess().getPredicatesExpressionINFParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleExpressionINF_in_ruleClause246);
            lv_predicates_0_0=ruleExpressionINF();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getClauseRule());
            	        }
                   		add(
                   			current, 
                   			"predicates",
                    		lv_predicates_0_0, 
                    		"ExpressionINF");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:155:2: (otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )* )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==15) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:155:4: otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )*
                    {
                    otherlv_1=(Token)match(input,15,FOLLOW_15_in_ruleClause259); 

                        	newLeafNode(otherlv_1, grammarAccess.getClauseAccess().getColonHyphenMinusKeyword_1_0());
                        
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:159:1: ( (lv_predicates_2_0= ruleExpressionINF ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:160:1: (lv_predicates_2_0= ruleExpressionINF )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:160:1: (lv_predicates_2_0= ruleExpressionINF )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:161:3: lv_predicates_2_0= ruleExpressionINF
                    {
                     
                    	        newCompositeNode(grammarAccess.getClauseAccess().getPredicatesExpressionINFParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionINF_in_ruleClause280);
                    lv_predicates_2_0=ruleExpressionINF();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getClauseRule());
                    	        }
                           		add(
                           			current, 
                           			"predicates",
                            		lv_predicates_2_0, 
                            		"ExpressionINF");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:177:2: (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==16) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:177:4: otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) )
                    	    {
                    	    otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleClause293); 

                    	        	newLeafNode(otherlv_3, grammarAccess.getClauseAccess().getCommaKeyword_1_2_0());
                    	        
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:181:1: ( (lv_predicates_4_0= ruleExpressionINF ) )
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:182:1: (lv_predicates_4_0= ruleExpressionINF )
                    	    {
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:182:1: (lv_predicates_4_0= ruleExpressionINF )
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:183:3: lv_predicates_4_0= ruleExpressionINF
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getClauseAccess().getPredicatesExpressionINFParserRuleCall_1_2_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleExpressionINF_in_ruleClause314);
                    	    lv_predicates_4_0=ruleExpressionINF();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getClauseRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"predicates",
                    	            		lv_predicates_4_0, 
                    	            		"ExpressionINF");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }
                    break;

            }

            otherlv_5=(Token)match(input,17,FOLLOW_17_in_ruleClause330); 

                	newLeafNode(otherlv_5, grammarAccess.getClauseAccess().getFullStopKeyword_2());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleClause"


    // $ANTLR start "entryRuleQuery"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:211:1: entryRuleQuery returns [EObject current=null] : iv_ruleQuery= ruleQuery EOF ;
    public final EObject entryRuleQuery() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleQuery = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:212:2: (iv_ruleQuery= ruleQuery EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:213:2: iv_ruleQuery= ruleQuery EOF
            {
             newCompositeNode(grammarAccess.getQueryRule()); 
            pushFollow(FOLLOW_ruleQuery_in_entryRuleQuery366);
            iv_ruleQuery=ruleQuery();

            state._fsp--;

             current =iv_ruleQuery; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleQuery376); 

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
    // $ANTLR end "entryRuleQuery"


    // $ANTLR start "ruleQuery"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:220:1: ruleQuery returns [EObject current=null] : (otherlv_0= '?-' ( (lv_predicates_1_0= ruleExpressionINF ) ) (otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) ) )* otherlv_4= '.' ) ;
    public final EObject ruleQuery() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_predicates_1_0 = null;

        EObject lv_predicates_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:223:28: ( (otherlv_0= '?-' ( (lv_predicates_1_0= ruleExpressionINF ) ) (otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) ) )* otherlv_4= '.' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:224:1: (otherlv_0= '?-' ( (lv_predicates_1_0= ruleExpressionINF ) ) (otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) ) )* otherlv_4= '.' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:224:1: (otherlv_0= '?-' ( (lv_predicates_1_0= ruleExpressionINF ) ) (otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) ) )* otherlv_4= '.' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:224:3: otherlv_0= '?-' ( (lv_predicates_1_0= ruleExpressionINF ) ) (otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) ) )* otherlv_4= '.'
            {
            otherlv_0=(Token)match(input,18,FOLLOW_18_in_ruleQuery413); 

                	newLeafNode(otherlv_0, grammarAccess.getQueryAccess().getQuestionMarkHyphenMinusKeyword_0());
                
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:228:1: ( (lv_predicates_1_0= ruleExpressionINF ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:229:1: (lv_predicates_1_0= ruleExpressionINF )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:229:1: (lv_predicates_1_0= ruleExpressionINF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:230:3: lv_predicates_1_0= ruleExpressionINF
            {
             
            	        newCompositeNode(grammarAccess.getQueryAccess().getPredicatesExpressionINFParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleExpressionINF_in_ruleQuery434);
            lv_predicates_1_0=ruleExpressionINF();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getQueryRule());
            	        }
                   		add(
                   			current, 
                   			"predicates",
                    		lv_predicates_1_0, 
                    		"ExpressionINF");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:246:2: (otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==16) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:246:4: otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) )
            	    {
            	    otherlv_2=(Token)match(input,16,FOLLOW_16_in_ruleQuery447); 

            	        	newLeafNode(otherlv_2, grammarAccess.getQueryAccess().getCommaKeyword_2_0());
            	        
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:250:1: ( (lv_predicates_3_0= ruleExpressionINF ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:251:1: (lv_predicates_3_0= ruleExpressionINF )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:251:1: (lv_predicates_3_0= ruleExpressionINF )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:252:3: lv_predicates_3_0= ruleExpressionINF
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getQueryAccess().getPredicatesExpressionINFParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleExpressionINF_in_ruleQuery468);
            	    lv_predicates_3_0=ruleExpressionINF();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getQueryRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"predicates",
            	            		lv_predicates_3_0, 
            	            		"ExpressionINF");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            otherlv_4=(Token)match(input,17,FOLLOW_17_in_ruleQuery482); 

                	newLeafNode(otherlv_4, grammarAccess.getQueryAccess().getFullStopKeyword_3());
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleQuery"


    // $ANTLR start "entryRuleExpressionINF"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:280:1: entryRuleExpressionINF returns [EObject current=null] : iv_ruleExpressionINF= ruleExpressionINF EOF ;
    public final EObject entryRuleExpressionINF() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpressionINF = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:281:2: (iv_ruleExpressionINF= ruleExpressionINF EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:282:2: iv_ruleExpressionINF= ruleExpressionINF EOF
            {
             newCompositeNode(grammarAccess.getExpressionINFRule()); 
            pushFollow(FOLLOW_ruleExpressionINF_in_entryRuleExpressionINF518);
            iv_ruleExpressionINF=ruleExpressionINF();

            state._fsp--;

             current =iv_ruleExpressionINF; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpressionINF528); 

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:289:1: ruleExpressionINF returns [EObject current=null] : ( ( ( ( (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* otherlv_5= ')' ) | ( (lv_exps_6_0= ruleExpression1100 ) ) ) ;
    public final EObject ruleExpressionINF() throws RecognitionException {
        EObject current = null;

        Token lv_ops_0_2=null;
        Token lv_ops_0_3=null;
        Token lv_ops_0_4=null;
        Token lv_ops_0_5=null;
        Token lv_ops_0_6=null;
        Token lv_ops_0_7=null;
        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        AntlrDatatypeRuleToken lv_ops_0_1 = null;

        EObject lv_exps_2_0 = null;

        EObject lv_exps_4_0 = null;

        EObject lv_exps_6_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:292:28: ( ( ( ( ( (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* otherlv_5= ')' ) | ( (lv_exps_6_0= ruleExpression1100 ) ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:293:1: ( ( ( ( (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* otherlv_5= ')' ) | ( (lv_exps_6_0= ruleExpression1100 ) ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:293:1: ( ( ( ( (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* otherlv_5= ')' ) | ( (lv_exps_6_0= ruleExpression1100 ) ) )
            int alt8=2;
            alt8 = dfa8.predict(input);
            switch (alt8) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:293:2: ( ( ( (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* otherlv_5= ')' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:293:2: ( ( ( (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* otherlv_5= ')' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:293:3: ( ( (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* otherlv_5= ')'
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:293:3: ( ( (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:294:1: ( (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:294:1: ( (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:295:1: (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:295:1: (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 )
                    int alt6=7;
                    switch ( input.LA(1) ) {
                    case RULE_LOWER_CASE_LETTER:
                        {
                        alt6=1;
                        }
                        break;
                    case RULE_EXPRESSION_1100:
                        {
                        alt6=2;
                        }
                        break;
                    case RULE_EXPRESSION_900:
                        {
                        alt6=3;
                        }
                        break;
                    case RULE_EXPRESSION_700:
                        {
                        alt6=4;
                        }
                        break;
                    case RULE_EXPRESSION_500:
                        {
                        alt6=5;
                        }
                        break;
                    case RULE_EXPRESSION_400:
                        {
                        alt6=6;
                        }
                        break;
                    case RULE_EXPRESSION_200:
                        {
                        alt6=7;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("", 6, 0, input);

                        throw nvae;
                    }

                    switch (alt6) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:296:3: lv_ops_0_1= ruleAtom
                            {
                             
                            	        newCompositeNode(grammarAccess.getExpressionINFAccess().getOpsAtomParserRuleCall_0_0_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleAtom_in_ruleExpressionINF577);
                            lv_ops_0_1=ruleAtom();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getExpressionINFRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"ops",
                                    		lv_ops_0_1, 
                                    		"Atom");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }
                            break;
                        case 2 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:311:8: lv_ops_0_2= RULE_EXPRESSION_1100
                            {
                            lv_ops_0_2=(Token)match(input,RULE_EXPRESSION_1100,FOLLOW_RULE_EXPRESSION_1100_in_ruleExpressionINF592); 

                            			newLeafNode(lv_ops_0_2, grammarAccess.getExpressionINFAccess().getOpsEXPRESSION_1100TerminalRuleCall_0_0_0_1()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionINFRule());
                            	        }
                                   		addWithLastConsumed(
                                   			current, 
                                   			"ops",
                                    		lv_ops_0_2, 
                                    		"EXPRESSION_1100");
                            	    

                            }
                            break;
                        case 3 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:326:8: lv_ops_0_3= RULE_EXPRESSION_900
                            {
                            lv_ops_0_3=(Token)match(input,RULE_EXPRESSION_900,FOLLOW_RULE_EXPRESSION_900_in_ruleExpressionINF612); 

                            			newLeafNode(lv_ops_0_3, grammarAccess.getExpressionINFAccess().getOpsEXPRESSION_900TerminalRuleCall_0_0_0_2()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionINFRule());
                            	        }
                                   		addWithLastConsumed(
                                   			current, 
                                   			"ops",
                                    		lv_ops_0_3, 
                                    		"EXPRESSION_900");
                            	    

                            }
                            break;
                        case 4 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:341:8: lv_ops_0_4= RULE_EXPRESSION_700
                            {
                            lv_ops_0_4=(Token)match(input,RULE_EXPRESSION_700,FOLLOW_RULE_EXPRESSION_700_in_ruleExpressionINF632); 

                            			newLeafNode(lv_ops_0_4, grammarAccess.getExpressionINFAccess().getOpsEXPRESSION_700TerminalRuleCall_0_0_0_3()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionINFRule());
                            	        }
                                   		addWithLastConsumed(
                                   			current, 
                                   			"ops",
                                    		lv_ops_0_4, 
                                    		"EXPRESSION_700");
                            	    

                            }
                            break;
                        case 5 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:356:8: lv_ops_0_5= RULE_EXPRESSION_500
                            {
                            lv_ops_0_5=(Token)match(input,RULE_EXPRESSION_500,FOLLOW_RULE_EXPRESSION_500_in_ruleExpressionINF652); 

                            			newLeafNode(lv_ops_0_5, grammarAccess.getExpressionINFAccess().getOpsEXPRESSION_500TerminalRuleCall_0_0_0_4()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionINFRule());
                            	        }
                                   		addWithLastConsumed(
                                   			current, 
                                   			"ops",
                                    		lv_ops_0_5, 
                                    		"EXPRESSION_500");
                            	    

                            }
                            break;
                        case 6 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:371:8: lv_ops_0_6= RULE_EXPRESSION_400
                            {
                            lv_ops_0_6=(Token)match(input,RULE_EXPRESSION_400,FOLLOW_RULE_EXPRESSION_400_in_ruleExpressionINF672); 

                            			newLeafNode(lv_ops_0_6, grammarAccess.getExpressionINFAccess().getOpsEXPRESSION_400TerminalRuleCall_0_0_0_5()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionINFRule());
                            	        }
                                   		addWithLastConsumed(
                                   			current, 
                                   			"ops",
                                    		lv_ops_0_6, 
                                    		"EXPRESSION_400");
                            	    

                            }
                            break;
                        case 7 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:386:8: lv_ops_0_7= RULE_EXPRESSION_200
                            {
                            lv_ops_0_7=(Token)match(input,RULE_EXPRESSION_200,FOLLOW_RULE_EXPRESSION_200_in_ruleExpressionINF692); 

                            			newLeafNode(lv_ops_0_7, grammarAccess.getExpressionINFAccess().getOpsEXPRESSION_200TerminalRuleCall_0_0_0_6()); 
                            		

                            	        if (current==null) {
                            	            current = createModelElement(grammarAccess.getExpressionINFRule());
                            	        }
                                   		addWithLastConsumed(
                                   			current, 
                                   			"ops",
                                    		lv_ops_0_7, 
                                    		"EXPRESSION_200");
                            	    

                            }
                            break;

                    }


                    }


                    }

                    otherlv_1=(Token)match(input,19,FOLLOW_19_in_ruleExpressionINF712); 

                        	newLeafNode(otherlv_1, grammarAccess.getExpressionINFAccess().getLeftParenthesisKeyword_0_1());
                        
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:408:1: ( (lv_exps_2_0= ruleExpressionINF ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:409:1: (lv_exps_2_0= ruleExpressionINF )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:409:1: (lv_exps_2_0= ruleExpressionINF )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:410:3: lv_exps_2_0= ruleExpressionINF
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionINFAccess().getExpsExpressionINFParserRuleCall_0_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpressionINF733);
                    lv_exps_2_0=ruleExpressionINF();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionINFRule());
                    	        }
                           		add(
                           			current, 
                           			"exps",
                            		lv_exps_2_0, 
                            		"ExpressionINF");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:426:2: (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )*
                    loop7:
                    do {
                        int alt7=2;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0==16) ) {
                            alt7=1;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:426:4: otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) )
                    	    {
                    	    otherlv_3=(Token)match(input,16,FOLLOW_16_in_ruleExpressionINF746); 

                    	        	newLeafNode(otherlv_3, grammarAccess.getExpressionINFAccess().getCommaKeyword_0_3_0());
                    	        
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:430:1: ( (lv_exps_4_0= ruleExpressionINF ) )
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:431:1: (lv_exps_4_0= ruleExpressionINF )
                    	    {
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:431:1: (lv_exps_4_0= ruleExpressionINF )
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:432:3: lv_exps_4_0= ruleExpressionINF
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getExpressionINFAccess().getExpsExpressionINFParserRuleCall_0_3_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpressionINF767);
                    	    lv_exps_4_0=ruleExpressionINF();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getExpressionINFRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"exps",
                    	            		lv_exps_4_0, 
                    	            		"ExpressionINF");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop7;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,20,FOLLOW_20_in_ruleExpressionINF781); 

                        	newLeafNode(otherlv_5, grammarAccess.getExpressionINFAccess().getRightParenthesisKeyword_0_4());
                        

                    }


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:453:6: ( (lv_exps_6_0= ruleExpression1100 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:453:6: ( (lv_exps_6_0= ruleExpression1100 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:454:1: (lv_exps_6_0= ruleExpression1100 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:454:1: (lv_exps_6_0= ruleExpression1100 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:455:3: lv_exps_6_0= ruleExpression1100
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpressionINFAccess().getExpsExpression1100ParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpression1100_in_ruleExpressionINF809);
                    lv_exps_6_0=ruleExpression1100();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpressionINFRule());
                    	        }
                           		add(
                           			current, 
                           			"exps",
                            		lv_exps_6_0, 
                            		"Expression1100");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;

            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleExpression1100"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:479:1: entryRuleExpression1100 returns [EObject current=null] : iv_ruleExpression1100= ruleExpression1100 EOF ;
    public final EObject entryRuleExpression1100() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression1100 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:480:2: (iv_ruleExpression1100= ruleExpression1100 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:481:2: iv_ruleExpression1100= ruleExpression1100 EOF
            {
             newCompositeNode(grammarAccess.getExpression1100Rule()); 
            pushFollow(FOLLOW_ruleExpression1100_in_entryRuleExpression1100845);
            iv_ruleExpression1100=ruleExpression1100();

            state._fsp--;

             current =iv_ruleExpression1100; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression1100855); 

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:488:1: ruleExpression1100 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression900 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) ) )* ) ;
    public final EObject ruleExpression1100() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:491:28: ( ( ( (lv_exps_0_0= ruleExpression900 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) ) )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:492:1: ( ( (lv_exps_0_0= ruleExpression900 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) ) )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:492:1: ( ( (lv_exps_0_0= ruleExpression900 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) ) )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:492:2: ( (lv_exps_0_0= ruleExpression900 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) ) )*
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:492:2: ( (lv_exps_0_0= ruleExpression900 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:493:1: (lv_exps_0_0= ruleExpression900 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:493:1: (lv_exps_0_0= ruleExpression900 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:494:3: lv_exps_0_0= ruleExpression900
            {
             
            	        newCompositeNode(grammarAccess.getExpression1100Access().getExpsExpression900ParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression900_in_ruleExpression1100901);
            lv_exps_0_0=ruleExpression900();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getExpression1100Rule());
            	        }
                   		add(
                   			current, 
                   			"exps",
                    		lv_exps_0_0, 
                    		"Expression900");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:510:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_EXPRESSION_1100) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:510:3: ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:510:3: ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:511:1: (lv_ops_1_0= RULE_EXPRESSION_1100 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:511:1: (lv_ops_1_0= RULE_EXPRESSION_1100 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:512:3: lv_ops_1_0= RULE_EXPRESSION_1100
            	    {
            	    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_1100,FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression1100919); 

            	    			newLeafNode(lv_ops_1_0, grammarAccess.getExpression1100Access().getOpsEXPRESSION_1100TerminalRuleCall_1_0_0()); 
            	    		

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

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:528:2: ( (lv_exps_2_0= ruleExpression900 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:529:1: (lv_exps_2_0= ruleExpression900 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:529:1: (lv_exps_2_0= ruleExpression900 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:530:3: lv_exps_2_0= ruleExpression900
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getExpression1100Access().getExpsExpression900ParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleExpression900_in_ruleExpression1100945);
            	    lv_exps_2_0=ruleExpression900();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getExpression1100Rule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"exps",
            	            		lv_exps_2_0, 
            	            		"Expression900");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);


            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleExpression900"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:554:1: entryRuleExpression900 returns [EObject current=null] : iv_ruleExpression900= ruleExpression900 EOF ;
    public final EObject entryRuleExpression900() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression900 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:555:2: (iv_ruleExpression900= ruleExpression900 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:556:2: iv_ruleExpression900= ruleExpression900 EOF
            {
             newCompositeNode(grammarAccess.getExpression900Rule()); 
            pushFollow(FOLLOW_ruleExpression900_in_entryRuleExpression900983);
            iv_ruleExpression900=ruleExpression900();

            state._fsp--;

             current =iv_ruleExpression900; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression900993); 

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
    // $ANTLR end "entryRuleExpression900"


    // $ANTLR start "ruleExpression900"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:563:1: ruleExpression900 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression700 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_900 ) ) ( (lv_exps_2_0= ruleExpression700 ) ) )* ) ;
    public final EObject ruleExpression900() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:566:28: ( ( ( (lv_exps_0_0= ruleExpression700 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_900 ) ) ( (lv_exps_2_0= ruleExpression700 ) ) )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:567:1: ( ( (lv_exps_0_0= ruleExpression700 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_900 ) ) ( (lv_exps_2_0= ruleExpression700 ) ) )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:567:1: ( ( (lv_exps_0_0= ruleExpression700 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_900 ) ) ( (lv_exps_2_0= ruleExpression700 ) ) )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:567:2: ( (lv_exps_0_0= ruleExpression700 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_900 ) ) ( (lv_exps_2_0= ruleExpression700 ) ) )*
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:567:2: ( (lv_exps_0_0= ruleExpression700 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:568:1: (lv_exps_0_0= ruleExpression700 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:568:1: (lv_exps_0_0= ruleExpression700 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:569:3: lv_exps_0_0= ruleExpression700
            {
             
            	        newCompositeNode(grammarAccess.getExpression900Access().getExpsExpression700ParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression700_in_ruleExpression9001039);
            lv_exps_0_0=ruleExpression700();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getExpression900Rule());
            	        }
                   		add(
                   			current, 
                   			"exps",
                    		lv_exps_0_0, 
                    		"Expression700");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:585:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_900 ) ) ( (lv_exps_2_0= ruleExpression700 ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_EXPRESSION_900) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:585:3: ( (lv_ops_1_0= RULE_EXPRESSION_900 ) ) ( (lv_exps_2_0= ruleExpression700 ) )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:585:3: ( (lv_ops_1_0= RULE_EXPRESSION_900 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:586:1: (lv_ops_1_0= RULE_EXPRESSION_900 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:586:1: (lv_ops_1_0= RULE_EXPRESSION_900 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:587:3: lv_ops_1_0= RULE_EXPRESSION_900
            	    {
            	    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_900,FOLLOW_RULE_EXPRESSION_900_in_ruleExpression9001057); 

            	    			newLeafNode(lv_ops_1_0, grammarAccess.getExpression900Access().getOpsEXPRESSION_900TerminalRuleCall_1_0_0()); 
            	    		

            	    	        if (current==null) {
            	    	            current = createModelElement(grammarAccess.getExpression900Rule());
            	    	        }
            	           		addWithLastConsumed(
            	           			current, 
            	           			"ops",
            	            		lv_ops_1_0, 
            	            		"EXPRESSION_900");
            	    	    

            	    }


            	    }

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:603:2: ( (lv_exps_2_0= ruleExpression700 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:604:1: (lv_exps_2_0= ruleExpression700 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:604:1: (lv_exps_2_0= ruleExpression700 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:605:3: lv_exps_2_0= ruleExpression700
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getExpression900Access().getExpsExpression700ParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleExpression700_in_ruleExpression9001083);
            	    lv_exps_2_0=ruleExpression700();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getExpression900Rule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"exps",
            	            		lv_exps_2_0, 
            	            		"Expression700");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleExpression900"


    // $ANTLR start "entryRuleExpression700"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:629:1: entryRuleExpression700 returns [EObject current=null] : iv_ruleExpression700= ruleExpression700 EOF ;
    public final EObject entryRuleExpression700() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression700 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:630:2: (iv_ruleExpression700= ruleExpression700 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:631:2: iv_ruleExpression700= ruleExpression700 EOF
            {
             newCompositeNode(grammarAccess.getExpression700Rule()); 
            pushFollow(FOLLOW_ruleExpression700_in_entryRuleExpression7001121);
            iv_ruleExpression700=ruleExpression700();

            state._fsp--;

             current =iv_ruleExpression700; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression7001131); 

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:638:1: ruleExpression700 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression500 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? ) ;
    public final EObject ruleExpression700() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:641:28: ( ( ( (lv_exps_0_0= ruleExpression500 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:642:1: ( ( (lv_exps_0_0= ruleExpression500 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:642:1: ( ( (lv_exps_0_0= ruleExpression500 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:642:2: ( (lv_exps_0_0= ruleExpression500 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:642:2: ( (lv_exps_0_0= ruleExpression500 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:643:1: (lv_exps_0_0= ruleExpression500 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:643:1: (lv_exps_0_0= ruleExpression500 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:644:3: lv_exps_0_0= ruleExpression500
            {
             
            	        newCompositeNode(grammarAccess.getExpression700Access().getExpsExpression500ParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression500_in_ruleExpression7001177);
            lv_exps_0_0=ruleExpression500();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getExpression700Rule());
            	        }
                   		add(
                   			current, 
                   			"exps",
                    		lv_exps_0_0, 
                    		"Expression500");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:660:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==RULE_EXPRESSION_700) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:660:3: ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:660:3: ( (lv_ops_1_0= RULE_EXPRESSION_700 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:661:1: (lv_ops_1_0= RULE_EXPRESSION_700 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:661:1: (lv_ops_1_0= RULE_EXPRESSION_700 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:662:3: lv_ops_1_0= RULE_EXPRESSION_700
                    {
                    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_700,FOLLOW_RULE_EXPRESSION_700_in_ruleExpression7001195); 

                    			newLeafNode(lv_ops_1_0, grammarAccess.getExpression700Access().getOpsEXPRESSION_700TerminalRuleCall_1_0_0()); 
                    		

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

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:678:2: ( (lv_exps_2_0= ruleExpression500 ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:679:1: (lv_exps_2_0= ruleExpression500 )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:679:1: (lv_exps_2_0= ruleExpression500 )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:680:3: lv_exps_2_0= ruleExpression500
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpression700Access().getExpsExpression500ParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpression500_in_ruleExpression7001221);
                    lv_exps_2_0=ruleExpression500();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpression700Rule());
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
                    break;

            }


            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleExpression500"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:704:1: entryRuleExpression500 returns [EObject current=null] : iv_ruleExpression500= ruleExpression500 EOF ;
    public final EObject entryRuleExpression500() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression500 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:705:2: (iv_ruleExpression500= ruleExpression500 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:706:2: iv_ruleExpression500= ruleExpression500 EOF
            {
             newCompositeNode(grammarAccess.getExpression500Rule()); 
            pushFollow(FOLLOW_ruleExpression500_in_entryRuleExpression5001259);
            iv_ruleExpression500=ruleExpression500();

            state._fsp--;

             current =iv_ruleExpression500; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression5001269); 

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:713:1: ruleExpression500 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression400 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )* ) ;
    public final EObject ruleExpression500() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:716:28: ( ( ( (lv_exps_0_0= ruleExpression400 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:717:1: ( ( (lv_exps_0_0= ruleExpression400 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:717:1: ( ( (lv_exps_0_0= ruleExpression400 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:717:2: ( (lv_exps_0_0= ruleExpression400 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )*
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:717:2: ( (lv_exps_0_0= ruleExpression400 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:718:1: (lv_exps_0_0= ruleExpression400 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:718:1: (lv_exps_0_0= ruleExpression400 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:719:3: lv_exps_0_0= ruleExpression400
            {
             
            	        newCompositeNode(grammarAccess.getExpression500Access().getExpsExpression400ParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression400_in_ruleExpression5001315);
            lv_exps_0_0=ruleExpression400();

            state._fsp--;


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

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:735:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==RULE_EXPRESSION_500) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:735:3: ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:735:3: ( (lv_ops_1_0= RULE_EXPRESSION_500 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:736:1: (lv_ops_1_0= RULE_EXPRESSION_500 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:736:1: (lv_ops_1_0= RULE_EXPRESSION_500 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:737:3: lv_ops_1_0= RULE_EXPRESSION_500
            	    {
            	    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_500,FOLLOW_RULE_EXPRESSION_500_in_ruleExpression5001333); 

            	    			newLeafNode(lv_ops_1_0, grammarAccess.getExpression500Access().getOpsEXPRESSION_500TerminalRuleCall_1_0_0()); 
            	    		

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

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:753:2: ( (lv_exps_2_0= ruleExpression400 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:754:1: (lv_exps_2_0= ruleExpression400 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:754:1: (lv_exps_2_0= ruleExpression400 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:755:3: lv_exps_2_0= ruleExpression400
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getExpression500Access().getExpsExpression400ParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleExpression400_in_ruleExpression5001359);
            	    lv_exps_2_0=ruleExpression400();

            	    state._fsp--;


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
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);


            }


            }

             leaveRule(); 
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:779:1: entryRuleExpression400 returns [EObject current=null] : iv_ruleExpression400= ruleExpression400 EOF ;
    public final EObject entryRuleExpression400() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression400 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:780:2: (iv_ruleExpression400= ruleExpression400 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:781:2: iv_ruleExpression400= ruleExpression400 EOF
            {
             newCompositeNode(grammarAccess.getExpression400Rule()); 
            pushFollow(FOLLOW_ruleExpression400_in_entryRuleExpression4001397);
            iv_ruleExpression400=ruleExpression400();

            state._fsp--;

             current =iv_ruleExpression400; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression4001407); 

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:788:1: ruleExpression400 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression200 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )* ) ;
    public final EObject ruleExpression400() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:791:28: ( ( ( (lv_exps_0_0= ruleExpression200 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:792:1: ( ( (lv_exps_0_0= ruleExpression200 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:792:1: ( ( (lv_exps_0_0= ruleExpression200 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:792:2: ( (lv_exps_0_0= ruleExpression200 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )*
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:792:2: ( (lv_exps_0_0= ruleExpression200 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:793:1: (lv_exps_0_0= ruleExpression200 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:793:1: (lv_exps_0_0= ruleExpression200 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:794:3: lv_exps_0_0= ruleExpression200
            {
             
            	        newCompositeNode(grammarAccess.getExpression400Access().getExpsExpression200ParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression200_in_ruleExpression4001453);
            lv_exps_0_0=ruleExpression200();

            state._fsp--;


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

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:810:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( (LA13_0==RULE_EXPRESSION_400) ) {
                    alt13=1;
                }


                switch (alt13) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:810:3: ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:810:3: ( (lv_ops_1_0= RULE_EXPRESSION_400 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:811:1: (lv_ops_1_0= RULE_EXPRESSION_400 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:811:1: (lv_ops_1_0= RULE_EXPRESSION_400 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:812:3: lv_ops_1_0= RULE_EXPRESSION_400
            	    {
            	    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_400,FOLLOW_RULE_EXPRESSION_400_in_ruleExpression4001471); 

            	    			newLeafNode(lv_ops_1_0, grammarAccess.getExpression400Access().getOpsEXPRESSION_400TerminalRuleCall_1_0_0()); 
            	    		

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

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:828:2: ( (lv_exps_2_0= ruleExpression200 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:829:1: (lv_exps_2_0= ruleExpression200 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:829:1: (lv_exps_2_0= ruleExpression200 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:830:3: lv_exps_2_0= ruleExpression200
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getExpression400Access().getExpsExpression200ParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleExpression200_in_ruleExpression4001497);
            	    lv_exps_2_0=ruleExpression200();

            	    state._fsp--;


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
            	    break;

            	default :
            	    break loop13;
                }
            } while (true);


            }


            }

             leaveRule(); 
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:854:1: entryRuleExpression200 returns [EObject current=null] : iv_ruleExpression200= ruleExpression200 EOF ;
    public final EObject entryRuleExpression200() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression200 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:855:2: (iv_ruleExpression200= ruleExpression200 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:856:2: iv_ruleExpression200= ruleExpression200 EOF
            {
             newCompositeNode(grammarAccess.getExpression200Rule()); 
            pushFollow(FOLLOW_ruleExpression200_in_entryRuleExpression2001535);
            iv_ruleExpression200=ruleExpression200();

            state._fsp--;

             current =iv_ruleExpression200; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression2001545); 

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:863:1: ruleExpression200 returns [EObject current=null] : ( ( (lv_exps_0_0= ruleExpression0 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )* ) ;
    public final EObject ruleExpression200() throws RecognitionException {
        EObject current = null;

        Token lv_ops_1_0=null;
        EObject lv_exps_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:866:28: ( ( ( (lv_exps_0_0= ruleExpression0 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:867:1: ( ( (lv_exps_0_0= ruleExpression0 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:867:1: ( ( (lv_exps_0_0= ruleExpression0 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:867:2: ( (lv_exps_0_0= ruleExpression0 ) ) ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )*
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:867:2: ( (lv_exps_0_0= ruleExpression0 ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:868:1: (lv_exps_0_0= ruleExpression0 )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:868:1: (lv_exps_0_0= ruleExpression0 )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:869:3: lv_exps_0_0= ruleExpression0
            {
             
            	        newCompositeNode(grammarAccess.getExpression200Access().getExpsExpression0ParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleExpression0_in_ruleExpression2001591);
            lv_exps_0_0=ruleExpression0();

            state._fsp--;


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

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:885:2: ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0==RULE_EXPRESSION_200) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:885:3: ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:885:3: ( (lv_ops_1_0= RULE_EXPRESSION_200 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:886:1: (lv_ops_1_0= RULE_EXPRESSION_200 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:886:1: (lv_ops_1_0= RULE_EXPRESSION_200 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:887:3: lv_ops_1_0= RULE_EXPRESSION_200
            	    {
            	    lv_ops_1_0=(Token)match(input,RULE_EXPRESSION_200,FOLLOW_RULE_EXPRESSION_200_in_ruleExpression2001609); 

            	    			newLeafNode(lv_ops_1_0, grammarAccess.getExpression200Access().getOpsEXPRESSION_200TerminalRuleCall_1_0_0()); 
            	    		

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

            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:903:2: ( (lv_exps_2_0= ruleExpression0 ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:904:1: (lv_exps_2_0= ruleExpression0 )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:904:1: (lv_exps_2_0= ruleExpression0 )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:905:3: lv_exps_2_0= ruleExpression0
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getExpression200Access().getExpsExpression0ParserRuleCall_1_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleExpression0_in_ruleExpression2001635);
            	    lv_exps_2_0=ruleExpression0();

            	    state._fsp--;


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
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }


            }

             leaveRule(); 
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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:929:1: entryRuleExpression0 returns [EObject current=null] : iv_ruleExpression0= ruleExpression0 EOF ;
    public final EObject entryRuleExpression0() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExpression0 = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:930:2: (iv_ruleExpression0= ruleExpression0 EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:931:2: iv_ruleExpression0= ruleExpression0 EOF
            {
             newCompositeNode(grammarAccess.getExpression0Rule()); 
            pushFollow(FOLLOW_ruleExpression0_in_entryRuleExpression01673);
            iv_ruleExpression0=ruleExpression0();

            state._fsp--;

             current =iv_ruleExpression0; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExpression01683); 

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:938:1: ruleExpression0 returns [EObject current=null] : ( ( (lv_term_0_0= ruleTerm ) ) | (otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' ) ) ;
    public final EObject ruleExpression0() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        EObject lv_term_0_0 = null;

        EObject lv_exps_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:941:28: ( ( ( (lv_term_0_0= ruleTerm ) ) | (otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:942:1: ( ( (lv_term_0_0= ruleTerm ) ) | (otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:942:1: ( ( (lv_term_0_0= ruleTerm ) ) | (otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' ) )
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( ((LA15_0>=RULE_LOWER_CASE_LETTER && LA15_0<=RULE_UPPER_CASE_LETTER)||LA15_0==17||(LA15_0>=21 && LA15_0<=22)||LA15_0==25||LA15_0==27) ) {
                alt15=1;
            }
            else if ( (LA15_0==19) ) {
                alt15=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 15, 0, input);

                throw nvae;
            }
            switch (alt15) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:942:2: ( (lv_term_0_0= ruleTerm ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:942:2: ( (lv_term_0_0= ruleTerm ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:943:1: (lv_term_0_0= ruleTerm )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:943:1: (lv_term_0_0= ruleTerm )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:944:3: lv_term_0_0= ruleTerm
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpression0Access().getTermTermParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleTerm_in_ruleExpression01729);
                    lv_term_0_0=ruleTerm();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getExpression0Rule());
                    	        }
                           		set(
                           			current, 
                           			"term",
                            		lv_term_0_0, 
                            		"Term");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:961:6: (otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:961:6: (otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:961:8: otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) otherlv_3= ')'
                    {
                    otherlv_1=(Token)match(input,19,FOLLOW_19_in_ruleExpression01748); 

                        	newLeafNode(otherlv_1, grammarAccess.getExpression0Access().getLeftParenthesisKeyword_1_0());
                        
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:965:1: ( (lv_exps_2_0= ruleExpressionINF ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:966:1: (lv_exps_2_0= ruleExpressionINF )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:966:1: (lv_exps_2_0= ruleExpressionINF )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:967:3: lv_exps_2_0= ruleExpressionINF
                    {
                     
                    	        newCompositeNode(grammarAccess.getExpression0Access().getExpsExpressionINFParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression01769);
                    lv_exps_2_0=ruleExpressionINF();

                    state._fsp--;


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

                    otherlv_3=(Token)match(input,20,FOLLOW_20_in_ruleExpression01781); 

                        	newLeafNode(otherlv_3, grammarAccess.getExpression0Access().getRightParenthesisKeyword_1_2());
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
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


    // $ANTLR start "entryRuleTerm"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:995:1: entryRuleTerm returns [EObject current=null] : iv_ruleTerm= ruleTerm EOF ;
    public final EObject entryRuleTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTerm = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:996:2: (iv_ruleTerm= ruleTerm EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:997:2: iv_ruleTerm= ruleTerm EOF
            {
             newCompositeNode(grammarAccess.getTermRule()); 
            pushFollow(FOLLOW_ruleTerm_in_entryRuleTerm1818);
            iv_ruleTerm=ruleTerm();

            state._fsp--;

             current =iv_ruleTerm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTerm1828); 

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
    // $ANTLR end "entryRuleTerm"


    // $ANTLR start "ruleTerm"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1004:1: ruleTerm returns [EObject current=null] : ( ( (lv_atom_0_0= ruleAtom ) ) | ( (lv_number_1_0= ruleNumber ) ) | (otherlv_2= '\\'' ( (lv_string_3_0= ruleString ) ) otherlv_4= '\\'' ) | ( (lv_variable_5_0= ruleVariable ) ) | ( ( (lv_list_6_0= '[' ) ) ( ( (lv_head_7_0= ruleExpressionINF ) ) (otherlv_8= ',' ( (lv_head_9_0= ruleExpressionINF ) ) )* (otherlv_10= '|' ( (lv_tail_11_0= ruleExpressionINF ) ) )? )? otherlv_12= ']' ) | ( ( (lv_list_13_0= '.' ) ) otherlv_14= '(' ( (lv_head_15_0= ruleExpressionINF ) ) otherlv_16= ',' ( (lv_tail_17_0= ruleExpressionINF ) ) otherlv_18= ')' ) ) ;
    public final EObject ruleTerm() throws RecognitionException {
        EObject current = null;

        Token otherlv_2=null;
        Token otherlv_4=null;
        Token lv_list_6_0=null;
        Token otherlv_8=null;
        Token otherlv_10=null;
        Token otherlv_12=null;
        Token lv_list_13_0=null;
        Token otherlv_14=null;
        Token otherlv_16=null;
        Token otherlv_18=null;
        AntlrDatatypeRuleToken lv_atom_0_0 = null;

        AntlrDatatypeRuleToken lv_number_1_0 = null;

        AntlrDatatypeRuleToken lv_string_3_0 = null;

        AntlrDatatypeRuleToken lv_variable_5_0 = null;

        EObject lv_head_7_0 = null;

        EObject lv_head_9_0 = null;

        EObject lv_tail_11_0 = null;

        EObject lv_head_15_0 = null;

        EObject lv_tail_17_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1007:28: ( ( ( (lv_atom_0_0= ruleAtom ) ) | ( (lv_number_1_0= ruleNumber ) ) | (otherlv_2= '\\'' ( (lv_string_3_0= ruleString ) ) otherlv_4= '\\'' ) | ( (lv_variable_5_0= ruleVariable ) ) | ( ( (lv_list_6_0= '[' ) ) ( ( (lv_head_7_0= ruleExpressionINF ) ) (otherlv_8= ',' ( (lv_head_9_0= ruleExpressionINF ) ) )* (otherlv_10= '|' ( (lv_tail_11_0= ruleExpressionINF ) ) )? )? otherlv_12= ']' ) | ( ( (lv_list_13_0= '.' ) ) otherlv_14= '(' ( (lv_head_15_0= ruleExpressionINF ) ) otherlv_16= ',' ( (lv_tail_17_0= ruleExpressionINF ) ) otherlv_18= ')' ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1008:1: ( ( (lv_atom_0_0= ruleAtom ) ) | ( (lv_number_1_0= ruleNumber ) ) | (otherlv_2= '\\'' ( (lv_string_3_0= ruleString ) ) otherlv_4= '\\'' ) | ( (lv_variable_5_0= ruleVariable ) ) | ( ( (lv_list_6_0= '[' ) ) ( ( (lv_head_7_0= ruleExpressionINF ) ) (otherlv_8= ',' ( (lv_head_9_0= ruleExpressionINF ) ) )* (otherlv_10= '|' ( (lv_tail_11_0= ruleExpressionINF ) ) )? )? otherlv_12= ']' ) | ( ( (lv_list_13_0= '.' ) ) otherlv_14= '(' ( (lv_head_15_0= ruleExpressionINF ) ) otherlv_16= ',' ( (lv_tail_17_0= ruleExpressionINF ) ) otherlv_18= ')' ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1008:1: ( ( (lv_atom_0_0= ruleAtom ) ) | ( (lv_number_1_0= ruleNumber ) ) | (otherlv_2= '\\'' ( (lv_string_3_0= ruleString ) ) otherlv_4= '\\'' ) | ( (lv_variable_5_0= ruleVariable ) ) | ( ( (lv_list_6_0= '[' ) ) ( ( (lv_head_7_0= ruleExpressionINF ) ) (otherlv_8= ',' ( (lv_head_9_0= ruleExpressionINF ) ) )* (otherlv_10= '|' ( (lv_tail_11_0= ruleExpressionINF ) ) )? )? otherlv_12= ']' ) | ( ( (lv_list_13_0= '.' ) ) otherlv_14= '(' ( (lv_head_15_0= ruleExpressionINF ) ) otherlv_16= ',' ( (lv_tail_17_0= ruleExpressionINF ) ) otherlv_18= ')' ) )
            int alt19=6;
            switch ( input.LA(1) ) {
            case RULE_LOWER_CASE_LETTER:
                {
                alt19=1;
                }
                break;
            case RULE_DIGIT:
            case 27:
                {
                alt19=2;
                }
                break;
            case 21:
                {
                alt19=3;
                }
                break;
            case RULE_UPPER_CASE_LETTER:
            case 25:
                {
                alt19=4;
                }
                break;
            case 22:
                {
                alt19=5;
                }
                break;
            case 17:
                {
                alt19=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 19, 0, input);

                throw nvae;
            }

            switch (alt19) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1008:2: ( (lv_atom_0_0= ruleAtom ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1008:2: ( (lv_atom_0_0= ruleAtom ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1009:1: (lv_atom_0_0= ruleAtom )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1009:1: (lv_atom_0_0= ruleAtom )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1010:3: lv_atom_0_0= ruleAtom
                    {
                     
                    	        newCompositeNode(grammarAccess.getTermAccess().getAtomAtomParserRuleCall_0_0()); 
                    	    
                    pushFollow(FOLLOW_ruleAtom_in_ruleTerm1874);
                    lv_atom_0_0=ruleAtom();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTermRule());
                    	        }
                           		set(
                           			current, 
                           			"atom",
                            		lv_atom_0_0, 
                            		"Atom");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1027:6: ( (lv_number_1_0= ruleNumber ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1027:6: ( (lv_number_1_0= ruleNumber ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1028:1: (lv_number_1_0= ruleNumber )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1028:1: (lv_number_1_0= ruleNumber )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1029:3: lv_number_1_0= ruleNumber
                    {
                     
                    	        newCompositeNode(grammarAccess.getTermAccess().getNumberNumberParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleNumber_in_ruleTerm1901);
                    lv_number_1_0=ruleNumber();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTermRule());
                    	        }
                           		set(
                           			current, 
                           			"number",
                            		lv_number_1_0, 
                            		"Number");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1046:6: (otherlv_2= '\\'' ( (lv_string_3_0= ruleString ) ) otherlv_4= '\\'' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1046:6: (otherlv_2= '\\'' ( (lv_string_3_0= ruleString ) ) otherlv_4= '\\'' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1046:8: otherlv_2= '\\'' ( (lv_string_3_0= ruleString ) ) otherlv_4= '\\''
                    {
                    otherlv_2=(Token)match(input,21,FOLLOW_21_in_ruleTerm1920); 

                        	newLeafNode(otherlv_2, grammarAccess.getTermAccess().getApostropheKeyword_2_0());
                        
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1050:1: ( (lv_string_3_0= ruleString ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1051:1: (lv_string_3_0= ruleString )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1051:1: (lv_string_3_0= ruleString )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1052:3: lv_string_3_0= ruleString
                    {
                     
                    	        newCompositeNode(grammarAccess.getTermAccess().getStringStringParserRuleCall_2_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleString_in_ruleTerm1941);
                    lv_string_3_0=ruleString();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTermRule());
                    	        }
                           		set(
                           			current, 
                           			"string",
                            		lv_string_3_0, 
                            		"String");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_4=(Token)match(input,21,FOLLOW_21_in_ruleTerm1953); 

                        	newLeafNode(otherlv_4, grammarAccess.getTermAccess().getApostropheKeyword_2_2());
                        

                    }


                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1073:6: ( (lv_variable_5_0= ruleVariable ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1073:6: ( (lv_variable_5_0= ruleVariable ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1074:1: (lv_variable_5_0= ruleVariable )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1074:1: (lv_variable_5_0= ruleVariable )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1075:3: lv_variable_5_0= ruleVariable
                    {
                     
                    	        newCompositeNode(grammarAccess.getTermAccess().getVariableVariableParserRuleCall_3_0()); 
                    	    
                    pushFollow(FOLLOW_ruleVariable_in_ruleTerm1981);
                    lv_variable_5_0=ruleVariable();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTermRule());
                    	        }
                           		set(
                           			current, 
                           			"variable",
                            		lv_variable_5_0, 
                            		"Variable");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 5 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1092:6: ( ( (lv_list_6_0= '[' ) ) ( ( (lv_head_7_0= ruleExpressionINF ) ) (otherlv_8= ',' ( (lv_head_9_0= ruleExpressionINF ) ) )* (otherlv_10= '|' ( (lv_tail_11_0= ruleExpressionINF ) ) )? )? otherlv_12= ']' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1092:6: ( ( (lv_list_6_0= '[' ) ) ( ( (lv_head_7_0= ruleExpressionINF ) ) (otherlv_8= ',' ( (lv_head_9_0= ruleExpressionINF ) ) )* (otherlv_10= '|' ( (lv_tail_11_0= ruleExpressionINF ) ) )? )? otherlv_12= ']' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1092:7: ( (lv_list_6_0= '[' ) ) ( ( (lv_head_7_0= ruleExpressionINF ) ) (otherlv_8= ',' ( (lv_head_9_0= ruleExpressionINF ) ) )* (otherlv_10= '|' ( (lv_tail_11_0= ruleExpressionINF ) ) )? )? otherlv_12= ']'
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1092:7: ( (lv_list_6_0= '[' ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1093:1: (lv_list_6_0= '[' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1093:1: (lv_list_6_0= '[' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1094:3: lv_list_6_0= '['
                    {
                    lv_list_6_0=(Token)match(input,22,FOLLOW_22_in_ruleTerm2006); 

                            newLeafNode(lv_list_6_0, grammarAccess.getTermAccess().getListLeftSquareBracketKeyword_4_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTermRule());
                    	        }
                           		setWithLastConsumed(current, "list", true, "[");
                    	    

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1107:2: ( ( (lv_head_7_0= ruleExpressionINF ) ) (otherlv_8= ',' ( (lv_head_9_0= ruleExpressionINF ) ) )* (otherlv_10= '|' ( (lv_tail_11_0= ruleExpressionINF ) ) )? )?
                    int alt18=2;
                    int LA18_0 = input.LA(1);

                    if ( ((LA18_0>=RULE_EXPRESSION_1100 && LA18_0<=RULE_UPPER_CASE_LETTER)||LA18_0==17||LA18_0==19||(LA18_0>=21 && LA18_0<=22)||LA18_0==25||LA18_0==27) ) {
                        alt18=1;
                    }
                    switch (alt18) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1107:3: ( (lv_head_7_0= ruleExpressionINF ) ) (otherlv_8= ',' ( (lv_head_9_0= ruleExpressionINF ) ) )* (otherlv_10= '|' ( (lv_tail_11_0= ruleExpressionINF ) ) )?
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1107:3: ( (lv_head_7_0= ruleExpressionINF ) )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1108:1: (lv_head_7_0= ruleExpressionINF )
                            {
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1108:1: (lv_head_7_0= ruleExpressionINF )
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1109:3: lv_head_7_0= ruleExpressionINF
                            {
                             
                            	        newCompositeNode(grammarAccess.getTermAccess().getHeadExpressionINFParserRuleCall_4_1_0_0()); 
                            	    
                            pushFollow(FOLLOW_ruleExpressionINF_in_ruleTerm2041);
                            lv_head_7_0=ruleExpressionINF();

                            state._fsp--;


                            	        if (current==null) {
                            	            current = createModelElementForParent(grammarAccess.getTermRule());
                            	        }
                                   		add(
                                   			current, 
                                   			"head",
                                    		lv_head_7_0, 
                                    		"ExpressionINF");
                            	        afterParserOrEnumRuleCall();
                            	    

                            }


                            }

                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1125:2: (otherlv_8= ',' ( (lv_head_9_0= ruleExpressionINF ) ) )*
                            loop16:
                            do {
                                int alt16=2;
                                int LA16_0 = input.LA(1);

                                if ( (LA16_0==16) ) {
                                    alt16=1;
                                }


                                switch (alt16) {
                            	case 1 :
                            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1125:4: otherlv_8= ',' ( (lv_head_9_0= ruleExpressionINF ) )
                            	    {
                            	    otherlv_8=(Token)match(input,16,FOLLOW_16_in_ruleTerm2054); 

                            	        	newLeafNode(otherlv_8, grammarAccess.getTermAccess().getCommaKeyword_4_1_1_0());
                            	        
                            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1129:1: ( (lv_head_9_0= ruleExpressionINF ) )
                            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1130:1: (lv_head_9_0= ruleExpressionINF )
                            	    {
                            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1130:1: (lv_head_9_0= ruleExpressionINF )
                            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1131:3: lv_head_9_0= ruleExpressionINF
                            	    {
                            	     
                            	    	        newCompositeNode(grammarAccess.getTermAccess().getHeadExpressionINFParserRuleCall_4_1_1_1_0()); 
                            	    	    
                            	    pushFollow(FOLLOW_ruleExpressionINF_in_ruleTerm2075);
                            	    lv_head_9_0=ruleExpressionINF();

                            	    state._fsp--;


                            	    	        if (current==null) {
                            	    	            current = createModelElementForParent(grammarAccess.getTermRule());
                            	    	        }
                            	           		add(
                            	           			current, 
                            	           			"head",
                            	            		lv_head_9_0, 
                            	            		"ExpressionINF");
                            	    	        afterParserOrEnumRuleCall();
                            	    	    

                            	    }


                            	    }


                            	    }
                            	    break;

                            	default :
                            	    break loop16;
                                }
                            } while (true);

                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1147:4: (otherlv_10= '|' ( (lv_tail_11_0= ruleExpressionINF ) ) )?
                            int alt17=2;
                            int LA17_0 = input.LA(1);

                            if ( (LA17_0==23) ) {
                                alt17=1;
                            }
                            switch (alt17) {
                                case 1 :
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1147:6: otherlv_10= '|' ( (lv_tail_11_0= ruleExpressionINF ) )
                                    {
                                    otherlv_10=(Token)match(input,23,FOLLOW_23_in_ruleTerm2090); 

                                        	newLeafNode(otherlv_10, grammarAccess.getTermAccess().getVerticalLineKeyword_4_1_2_0());
                                        
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1151:1: ( (lv_tail_11_0= ruleExpressionINF ) )
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1152:1: (lv_tail_11_0= ruleExpressionINF )
                                    {
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1152:1: (lv_tail_11_0= ruleExpressionINF )
                                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1153:3: lv_tail_11_0= ruleExpressionINF
                                    {
                                     
                                    	        newCompositeNode(grammarAccess.getTermAccess().getTailExpressionINFParserRuleCall_4_1_2_1_0()); 
                                    	    
                                    pushFollow(FOLLOW_ruleExpressionINF_in_ruleTerm2111);
                                    lv_tail_11_0=ruleExpressionINF();

                                    state._fsp--;


                                    	        if (current==null) {
                                    	            current = createModelElementForParent(grammarAccess.getTermRule());
                                    	        }
                                           		set(
                                           			current, 
                                           			"tail",
                                            		lv_tail_11_0, 
                                            		"ExpressionINF");
                                    	        afterParserOrEnumRuleCall();
                                    	    

                                    }


                                    }


                                    }
                                    break;

                            }


                            }
                            break;

                    }

                    otherlv_12=(Token)match(input,24,FOLLOW_24_in_ruleTerm2127); 

                        	newLeafNode(otherlv_12, grammarAccess.getTermAccess().getRightSquareBracketKeyword_4_2());
                        

                    }


                    }
                    break;
                case 6 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1174:6: ( ( (lv_list_13_0= '.' ) ) otherlv_14= '(' ( (lv_head_15_0= ruleExpressionINF ) ) otherlv_16= ',' ( (lv_tail_17_0= ruleExpressionINF ) ) otherlv_18= ')' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1174:6: ( ( (lv_list_13_0= '.' ) ) otherlv_14= '(' ( (lv_head_15_0= ruleExpressionINF ) ) otherlv_16= ',' ( (lv_tail_17_0= ruleExpressionINF ) ) otherlv_18= ')' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1174:7: ( (lv_list_13_0= '.' ) ) otherlv_14= '(' ( (lv_head_15_0= ruleExpressionINF ) ) otherlv_16= ',' ( (lv_tail_17_0= ruleExpressionINF ) ) otherlv_18= ')'
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1174:7: ( (lv_list_13_0= '.' ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1175:1: (lv_list_13_0= '.' )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1175:1: (lv_list_13_0= '.' )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1176:3: lv_list_13_0= '.'
                    {
                    lv_list_13_0=(Token)match(input,17,FOLLOW_17_in_ruleTerm2153); 

                            newLeafNode(lv_list_13_0, grammarAccess.getTermAccess().getListFullStopKeyword_5_0_0());
                        

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getTermRule());
                    	        }
                           		setWithLastConsumed(current, "list", true, ".");
                    	    

                    }


                    }

                    otherlv_14=(Token)match(input,19,FOLLOW_19_in_ruleTerm2178); 

                        	newLeafNode(otherlv_14, grammarAccess.getTermAccess().getLeftParenthesisKeyword_5_1());
                        
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1193:1: ( (lv_head_15_0= ruleExpressionINF ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1194:1: (lv_head_15_0= ruleExpressionINF )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1194:1: (lv_head_15_0= ruleExpressionINF )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1195:3: lv_head_15_0= ruleExpressionINF
                    {
                     
                    	        newCompositeNode(grammarAccess.getTermAccess().getHeadExpressionINFParserRuleCall_5_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionINF_in_ruleTerm2199);
                    lv_head_15_0=ruleExpressionINF();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTermRule());
                    	        }
                           		add(
                           			current, 
                           			"head",
                            		lv_head_15_0, 
                            		"ExpressionINF");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_16=(Token)match(input,16,FOLLOW_16_in_ruleTerm2211); 

                        	newLeafNode(otherlv_16, grammarAccess.getTermAccess().getCommaKeyword_5_3());
                        
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1215:1: ( (lv_tail_17_0= ruleExpressionINF ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1216:1: (lv_tail_17_0= ruleExpressionINF )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1216:1: (lv_tail_17_0= ruleExpressionINF )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1217:3: lv_tail_17_0= ruleExpressionINF
                    {
                     
                    	        newCompositeNode(grammarAccess.getTermAccess().getTailExpressionINFParserRuleCall_5_4_0()); 
                    	    
                    pushFollow(FOLLOW_ruleExpressionINF_in_ruleTerm2232);
                    lv_tail_17_0=ruleExpressionINF();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getTermRule());
                    	        }
                           		set(
                           			current, 
                           			"tail",
                            		lv_tail_17_0, 
                            		"ExpressionINF");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    otherlv_18=(Token)match(input,20,FOLLOW_20_in_ruleTerm2244); 

                        	newLeafNode(otherlv_18, grammarAccess.getTermAccess().getRightParenthesisKeyword_5_5());
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTerm"


    // $ANTLR start "entryRuleAtom"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1245:1: entryRuleAtom returns [String current=null] : iv_ruleAtom= ruleAtom EOF ;
    public final String entryRuleAtom() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleAtom = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1246:2: (iv_ruleAtom= ruleAtom EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1247:2: iv_ruleAtom= ruleAtom EOF
            {
             newCompositeNode(grammarAccess.getAtomRule()); 
            pushFollow(FOLLOW_ruleAtom_in_entryRuleAtom2282);
            iv_ruleAtom=ruleAtom();

            state._fsp--;

             current =iv_ruleAtom.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleAtom2293); 

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
    // $ANTLR end "entryRuleAtom"


    // $ANTLR start "ruleAtom"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1254:1: ruleAtom returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_LOWER_CASE_LETTER_0= RULE_LOWER_CASE_LETTER (this_DIGIT_1= RULE_DIGIT | this_LOWER_CASE_LETTER_2= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_3= RULE_UPPER_CASE_LETTER | kw= '_' )* ) ;
    public final AntlrDatatypeRuleToken ruleAtom() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_LOWER_CASE_LETTER_0=null;
        Token this_DIGIT_1=null;
        Token this_LOWER_CASE_LETTER_2=null;
        Token this_UPPER_CASE_LETTER_3=null;
        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1257:28: ( (this_LOWER_CASE_LETTER_0= RULE_LOWER_CASE_LETTER (this_DIGIT_1= RULE_DIGIT | this_LOWER_CASE_LETTER_2= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_3= RULE_UPPER_CASE_LETTER | kw= '_' )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1258:1: (this_LOWER_CASE_LETTER_0= RULE_LOWER_CASE_LETTER (this_DIGIT_1= RULE_DIGIT | this_LOWER_CASE_LETTER_2= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_3= RULE_UPPER_CASE_LETTER | kw= '_' )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1258:1: (this_LOWER_CASE_LETTER_0= RULE_LOWER_CASE_LETTER (this_DIGIT_1= RULE_DIGIT | this_LOWER_CASE_LETTER_2= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_3= RULE_UPPER_CASE_LETTER | kw= '_' )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1258:6: this_LOWER_CASE_LETTER_0= RULE_LOWER_CASE_LETTER (this_DIGIT_1= RULE_DIGIT | this_LOWER_CASE_LETTER_2= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_3= RULE_UPPER_CASE_LETTER | kw= '_' )*
            {
            this_LOWER_CASE_LETTER_0=(Token)match(input,RULE_LOWER_CASE_LETTER,FOLLOW_RULE_LOWER_CASE_LETTER_in_ruleAtom2333); 

            		current.merge(this_LOWER_CASE_LETTER_0);
                
             
                newLeafNode(this_LOWER_CASE_LETTER_0, grammarAccess.getAtomAccess().getLOWER_CASE_LETTERTerminalRuleCall_0()); 
                
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1265:1: (this_DIGIT_1= RULE_DIGIT | this_LOWER_CASE_LETTER_2= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_3= RULE_UPPER_CASE_LETTER | kw= '_' )*
            loop20:
            do {
                int alt20=5;
                switch ( input.LA(1) ) {
                case RULE_DIGIT:
                    {
                    alt20=1;
                    }
                    break;
                case RULE_LOWER_CASE_LETTER:
                    {
                    alt20=2;
                    }
                    break;
                case RULE_UPPER_CASE_LETTER:
                    {
                    alt20=3;
                    }
                    break;
                case 25:
                    {
                    alt20=4;
                    }
                    break;

                }

                switch (alt20) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1265:6: this_DIGIT_1= RULE_DIGIT
            	    {
            	    this_DIGIT_1=(Token)match(input,RULE_DIGIT,FOLLOW_RULE_DIGIT_in_ruleAtom2354); 

            	    		current.merge(this_DIGIT_1);
            	        
            	     
            	        newLeafNode(this_DIGIT_1, grammarAccess.getAtomAccess().getDIGITTerminalRuleCall_1_0()); 
            	        

            	    }
            	    break;
            	case 2 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1273:10: this_LOWER_CASE_LETTER_2= RULE_LOWER_CASE_LETTER
            	    {
            	    this_LOWER_CASE_LETTER_2=(Token)match(input,RULE_LOWER_CASE_LETTER,FOLLOW_RULE_LOWER_CASE_LETTER_in_ruleAtom2380); 

            	    		current.merge(this_LOWER_CASE_LETTER_2);
            	        
            	     
            	        newLeafNode(this_LOWER_CASE_LETTER_2, grammarAccess.getAtomAccess().getLOWER_CASE_LETTERTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;
            	case 3 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1281:10: this_UPPER_CASE_LETTER_3= RULE_UPPER_CASE_LETTER
            	    {
            	    this_UPPER_CASE_LETTER_3=(Token)match(input,RULE_UPPER_CASE_LETTER,FOLLOW_RULE_UPPER_CASE_LETTER_in_ruleAtom2406); 

            	    		current.merge(this_UPPER_CASE_LETTER_3);
            	        
            	     
            	        newLeafNode(this_UPPER_CASE_LETTER_3, grammarAccess.getAtomAccess().getUPPER_CASE_LETTERTerminalRuleCall_1_2()); 
            	        

            	    }
            	    break;
            	case 4 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1290:2: kw= '_'
            	    {
            	    kw=(Token)match(input,25,FOLLOW_25_in_ruleAtom2430); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getAtomAccess().get_Keyword_1_3()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop20;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleAtom"


    // $ANTLR start "entryRuleCharacter"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1303:1: entryRuleCharacter returns [String current=null] : iv_ruleCharacter= ruleCharacter EOF ;
    public final String entryRuleCharacter() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleCharacter = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1304:2: (iv_ruleCharacter= ruleCharacter EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1305:2: iv_ruleCharacter= ruleCharacter EOF
            {
             newCompositeNode(grammarAccess.getCharacterRule()); 
            pushFollow(FOLLOW_ruleCharacter_in_entryRuleCharacter2473);
            iv_ruleCharacter=ruleCharacter();

            state._fsp--;

             current =iv_ruleCharacter.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleCharacter2484); 

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
    // $ANTLR end "entryRuleCharacter"


    // $ANTLR start "ruleCharacter"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1312:1: ruleCharacter returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_DIGIT_0= RULE_DIGIT | this_LOWER_CASE_LETTER_1= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_2= RULE_UPPER_CASE_LETTER | kw= '_' | kw= '+' | kw= '-' | kw= '*' | kw= '/' | kw= '\\\\' | kw= '^' | kw= '~' | kw= ':' | kw= '.' | kw= '?' | kw= '@' | kw= '#' | kw= '$' | kw= '&' ) ;
    public final AntlrDatatypeRuleToken ruleCharacter() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_DIGIT_0=null;
        Token this_LOWER_CASE_LETTER_1=null;
        Token this_UPPER_CASE_LETTER_2=null;
        Token kw=null;

         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1315:28: ( (this_DIGIT_0= RULE_DIGIT | this_LOWER_CASE_LETTER_1= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_2= RULE_UPPER_CASE_LETTER | kw= '_' | kw= '+' | kw= '-' | kw= '*' | kw= '/' | kw= '\\\\' | kw= '^' | kw= '~' | kw= ':' | kw= '.' | kw= '?' | kw= '@' | kw= '#' | kw= '$' | kw= '&' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1316:1: (this_DIGIT_0= RULE_DIGIT | this_LOWER_CASE_LETTER_1= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_2= RULE_UPPER_CASE_LETTER | kw= '_' | kw= '+' | kw= '-' | kw= '*' | kw= '/' | kw= '\\\\' | kw= '^' | kw= '~' | kw= ':' | kw= '.' | kw= '?' | kw= '@' | kw= '#' | kw= '$' | kw= '&' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1316:1: (this_DIGIT_0= RULE_DIGIT | this_LOWER_CASE_LETTER_1= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_2= RULE_UPPER_CASE_LETTER | kw= '_' | kw= '+' | kw= '-' | kw= '*' | kw= '/' | kw= '\\\\' | kw= '^' | kw= '~' | kw= ':' | kw= '.' | kw= '?' | kw= '@' | kw= '#' | kw= '$' | kw= '&' )
            int alt21=18;
            switch ( input.LA(1) ) {
            case RULE_DIGIT:
                {
                alt21=1;
                }
                break;
            case RULE_LOWER_CASE_LETTER:
                {
                alt21=2;
                }
                break;
            case RULE_UPPER_CASE_LETTER:
                {
                alt21=3;
                }
                break;
            case 25:
                {
                alt21=4;
                }
                break;
            case 26:
                {
                alt21=5;
                }
                break;
            case 27:
                {
                alt21=6;
                }
                break;
            case 28:
                {
                alt21=7;
                }
                break;
            case 29:
                {
                alt21=8;
                }
                break;
            case 30:
                {
                alt21=9;
                }
                break;
            case 31:
                {
                alt21=10;
                }
                break;
            case 32:
                {
                alt21=11;
                }
                break;
            case 33:
                {
                alt21=12;
                }
                break;
            case 17:
                {
                alt21=13;
                }
                break;
            case 34:
                {
                alt21=14;
                }
                break;
            case 35:
                {
                alt21=15;
                }
                break;
            case 36:
                {
                alt21=16;
                }
                break;
            case 37:
                {
                alt21=17;
                }
                break;
            case 38:
                {
                alt21=18;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 21, 0, input);

                throw nvae;
            }

            switch (alt21) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1316:6: this_DIGIT_0= RULE_DIGIT
                    {
                    this_DIGIT_0=(Token)match(input,RULE_DIGIT,FOLLOW_RULE_DIGIT_in_ruleCharacter2524); 

                    		current.merge(this_DIGIT_0);
                        
                     
                        newLeafNode(this_DIGIT_0, grammarAccess.getCharacterAccess().getDIGITTerminalRuleCall_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1324:10: this_LOWER_CASE_LETTER_1= RULE_LOWER_CASE_LETTER
                    {
                    this_LOWER_CASE_LETTER_1=(Token)match(input,RULE_LOWER_CASE_LETTER,FOLLOW_RULE_LOWER_CASE_LETTER_in_ruleCharacter2550); 

                    		current.merge(this_LOWER_CASE_LETTER_1);
                        
                     
                        newLeafNode(this_LOWER_CASE_LETTER_1, grammarAccess.getCharacterAccess().getLOWER_CASE_LETTERTerminalRuleCall_1()); 
                        

                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1332:10: this_UPPER_CASE_LETTER_2= RULE_UPPER_CASE_LETTER
                    {
                    this_UPPER_CASE_LETTER_2=(Token)match(input,RULE_UPPER_CASE_LETTER,FOLLOW_RULE_UPPER_CASE_LETTER_in_ruleCharacter2576); 

                    		current.merge(this_UPPER_CASE_LETTER_2);
                        
                     
                        newLeafNode(this_UPPER_CASE_LETTER_2, grammarAccess.getCharacterAccess().getUPPER_CASE_LETTERTerminalRuleCall_2()); 
                        

                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1341:2: kw= '_'
                    {
                    kw=(Token)match(input,25,FOLLOW_25_in_ruleCharacter2600); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().get_Keyword_3()); 
                        

                    }
                    break;
                case 5 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1348:2: kw= '+'
                    {
                    kw=(Token)match(input,26,FOLLOW_26_in_ruleCharacter2619); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getPlusSignKeyword_4()); 
                        

                    }
                    break;
                case 6 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1355:2: kw= '-'
                    {
                    kw=(Token)match(input,27,FOLLOW_27_in_ruleCharacter2638); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getHyphenMinusKeyword_5()); 
                        

                    }
                    break;
                case 7 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1362:2: kw= '*'
                    {
                    kw=(Token)match(input,28,FOLLOW_28_in_ruleCharacter2657); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getAsteriskKeyword_6()); 
                        

                    }
                    break;
                case 8 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1369:2: kw= '/'
                    {
                    kw=(Token)match(input,29,FOLLOW_29_in_ruleCharacter2676); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getSolidusKeyword_7()); 
                        

                    }
                    break;
                case 9 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1376:2: kw= '\\\\'
                    {
                    kw=(Token)match(input,30,FOLLOW_30_in_ruleCharacter2695); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getReverseSolidusKeyword_8()); 
                        

                    }
                    break;
                case 10 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1383:2: kw= '^'
                    {
                    kw=(Token)match(input,31,FOLLOW_31_in_ruleCharacter2714); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getCircumflexAccentKeyword_9()); 
                        

                    }
                    break;
                case 11 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1390:2: kw= '~'
                    {
                    kw=(Token)match(input,32,FOLLOW_32_in_ruleCharacter2733); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getTildeKeyword_10()); 
                        

                    }
                    break;
                case 12 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1397:2: kw= ':'
                    {
                    kw=(Token)match(input,33,FOLLOW_33_in_ruleCharacter2752); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getColonKeyword_11()); 
                        

                    }
                    break;
                case 13 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1404:2: kw= '.'
                    {
                    kw=(Token)match(input,17,FOLLOW_17_in_ruleCharacter2771); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getFullStopKeyword_12()); 
                        

                    }
                    break;
                case 14 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1411:2: kw= '?'
                    {
                    kw=(Token)match(input,34,FOLLOW_34_in_ruleCharacter2790); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getQuestionMarkKeyword_13()); 
                        

                    }
                    break;
                case 15 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1418:2: kw= '@'
                    {
                    kw=(Token)match(input,35,FOLLOW_35_in_ruleCharacter2809); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getCommercialAtKeyword_14()); 
                        

                    }
                    break;
                case 16 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1425:2: kw= '#'
                    {
                    kw=(Token)match(input,36,FOLLOW_36_in_ruleCharacter2828); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getNumberSignKeyword_15()); 
                        

                    }
                    break;
                case 17 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1432:2: kw= '$'
                    {
                    kw=(Token)match(input,37,FOLLOW_37_in_ruleCharacter2847); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getDollarSignKeyword_16()); 
                        

                    }
                    break;
                case 18 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1439:2: kw= '&'
                    {
                    kw=(Token)match(input,38,FOLLOW_38_in_ruleCharacter2866); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getCharacterAccess().getAmpersandKeyword_17()); 
                        

                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCharacter"


    // $ANTLR start "entryRuleNumber"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:1: entryRuleNumber returns [String current=null] : iv_ruleNumber= ruleNumber EOF ;
    public final String entryRuleNumber() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleNumber = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1453:2: (iv_ruleNumber= ruleNumber EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1454:2: iv_ruleNumber= ruleNumber EOF
            {
             newCompositeNode(grammarAccess.getNumberRule()); 
            pushFollow(FOLLOW_ruleNumber_in_entryRuleNumber2907);
            iv_ruleNumber=ruleNumber();

            state._fsp--;

             current =iv_ruleNumber.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNumber2918); 

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
    // $ANTLR end "entryRuleNumber"


    // $ANTLR start "ruleNumber"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1461:1: ruleNumber returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ ) ;
    public final AntlrDatatypeRuleToken ruleNumber() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_DIGIT_1=null;

         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1464:28: ( ( (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1465:1: ( (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1465:1: ( (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+ )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1465:2: (kw= '-' )? (this_DIGIT_1= RULE_DIGIT )+
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1465:2: (kw= '-' )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==27) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1466:2: kw= '-'
                    {
                    kw=(Token)match(input,27,FOLLOW_27_in_ruleNumber2957); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getNumberAccess().getHyphenMinusKeyword_0()); 
                        

                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1471:3: (this_DIGIT_1= RULE_DIGIT )+
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
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1471:8: this_DIGIT_1= RULE_DIGIT
            	    {
            	    this_DIGIT_1=(Token)match(input,RULE_DIGIT,FOLLOW_RULE_DIGIT_in_ruleNumber2975); 

            	    		current.merge(this_DIGIT_1);
            	        
            	     
            	        newLeafNode(this_DIGIT_1, grammarAccess.getNumberAccess().getDIGITTerminalRuleCall_1()); 
            	        

            	    }
            	    break;

            	default :
            	    if ( cnt23 >= 1 ) break loop23;
                        EarlyExitException eee =
                            new EarlyExitException(23, input);
                        throw eee;
                }
                cnt23++;
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleNumber"


    // $ANTLR start "entryRuleVariable"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1486:1: entryRuleVariable returns [String current=null] : iv_ruleVariable= ruleVariable EOF ;
    public final String entryRuleVariable() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVariable = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1487:2: (iv_ruleVariable= ruleVariable EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1488:2: iv_ruleVariable= ruleVariable EOF
            {
             newCompositeNode(grammarAccess.getVariableRule()); 
            pushFollow(FOLLOW_ruleVariable_in_entryRuleVariable3023);
            iv_ruleVariable=ruleVariable();

            state._fsp--;

             current =iv_ruleVariable.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVariable3034); 

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
    // $ANTLR end "entryRuleVariable"


    // $ANTLR start "ruleVariable"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1495:1: ruleVariable returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (this_UPPER_CASE_LETTER_0= RULE_UPPER_CASE_LETTER | kw= '_' ) (this_DIGIT_2= RULE_DIGIT | this_LOWER_CASE_LETTER_3= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_4= RULE_UPPER_CASE_LETTER | kw= '_' )* ) ;
    public final AntlrDatatypeRuleToken ruleVariable() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_UPPER_CASE_LETTER_0=null;
        Token kw=null;
        Token this_DIGIT_2=null;
        Token this_LOWER_CASE_LETTER_3=null;
        Token this_UPPER_CASE_LETTER_4=null;

         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1498:28: ( ( (this_UPPER_CASE_LETTER_0= RULE_UPPER_CASE_LETTER | kw= '_' ) (this_DIGIT_2= RULE_DIGIT | this_LOWER_CASE_LETTER_3= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_4= RULE_UPPER_CASE_LETTER | kw= '_' )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1499:1: ( (this_UPPER_CASE_LETTER_0= RULE_UPPER_CASE_LETTER | kw= '_' ) (this_DIGIT_2= RULE_DIGIT | this_LOWER_CASE_LETTER_3= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_4= RULE_UPPER_CASE_LETTER | kw= '_' )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1499:1: ( (this_UPPER_CASE_LETTER_0= RULE_UPPER_CASE_LETTER | kw= '_' ) (this_DIGIT_2= RULE_DIGIT | this_LOWER_CASE_LETTER_3= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_4= RULE_UPPER_CASE_LETTER | kw= '_' )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1499:2: (this_UPPER_CASE_LETTER_0= RULE_UPPER_CASE_LETTER | kw= '_' ) (this_DIGIT_2= RULE_DIGIT | this_LOWER_CASE_LETTER_3= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_4= RULE_UPPER_CASE_LETTER | kw= '_' )*
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1499:2: (this_UPPER_CASE_LETTER_0= RULE_UPPER_CASE_LETTER | kw= '_' )
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==RULE_UPPER_CASE_LETTER) ) {
                alt24=1;
            }
            else if ( (LA24_0==25) ) {
                alt24=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 24, 0, input);

                throw nvae;
            }
            switch (alt24) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1499:7: this_UPPER_CASE_LETTER_0= RULE_UPPER_CASE_LETTER
                    {
                    this_UPPER_CASE_LETTER_0=(Token)match(input,RULE_UPPER_CASE_LETTER,FOLLOW_RULE_UPPER_CASE_LETTER_in_ruleVariable3075); 

                    		current.merge(this_UPPER_CASE_LETTER_0);
                        
                     
                        newLeafNode(this_UPPER_CASE_LETTER_0, grammarAccess.getVariableAccess().getUPPER_CASE_LETTERTerminalRuleCall_0_0()); 
                        

                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1508:2: kw= '_'
                    {
                    kw=(Token)match(input,25,FOLLOW_25_in_ruleVariable3099); 

                            current.merge(kw);
                            newLeafNode(kw, grammarAccess.getVariableAccess().get_Keyword_0_1()); 
                        

                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1513:2: (this_DIGIT_2= RULE_DIGIT | this_LOWER_CASE_LETTER_3= RULE_LOWER_CASE_LETTER | this_UPPER_CASE_LETTER_4= RULE_UPPER_CASE_LETTER | kw= '_' )*
            loop25:
            do {
                int alt25=5;
                switch ( input.LA(1) ) {
                case RULE_DIGIT:
                    {
                    alt25=1;
                    }
                    break;
                case RULE_LOWER_CASE_LETTER:
                    {
                    alt25=2;
                    }
                    break;
                case RULE_UPPER_CASE_LETTER:
                    {
                    alt25=3;
                    }
                    break;
                case 25:
                    {
                    alt25=4;
                    }
                    break;

                }

                switch (alt25) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1513:7: this_DIGIT_2= RULE_DIGIT
            	    {
            	    this_DIGIT_2=(Token)match(input,RULE_DIGIT,FOLLOW_RULE_DIGIT_in_ruleVariable3116); 

            	    		current.merge(this_DIGIT_2);
            	        
            	     
            	        newLeafNode(this_DIGIT_2, grammarAccess.getVariableAccess().getDIGITTerminalRuleCall_1_0()); 
            	        

            	    }
            	    break;
            	case 2 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1521:10: this_LOWER_CASE_LETTER_3= RULE_LOWER_CASE_LETTER
            	    {
            	    this_LOWER_CASE_LETTER_3=(Token)match(input,RULE_LOWER_CASE_LETTER,FOLLOW_RULE_LOWER_CASE_LETTER_in_ruleVariable3142); 

            	    		current.merge(this_LOWER_CASE_LETTER_3);
            	        
            	     
            	        newLeafNode(this_LOWER_CASE_LETTER_3, grammarAccess.getVariableAccess().getLOWER_CASE_LETTERTerminalRuleCall_1_1()); 
            	        

            	    }
            	    break;
            	case 3 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1529:10: this_UPPER_CASE_LETTER_4= RULE_UPPER_CASE_LETTER
            	    {
            	    this_UPPER_CASE_LETTER_4=(Token)match(input,RULE_UPPER_CASE_LETTER,FOLLOW_RULE_UPPER_CASE_LETTER_in_ruleVariable3168); 

            	    		current.merge(this_UPPER_CASE_LETTER_4);
            	        
            	     
            	        newLeafNode(this_UPPER_CASE_LETTER_4, grammarAccess.getVariableAccess().getUPPER_CASE_LETTERTerminalRuleCall_1_2()); 
            	        

            	    }
            	    break;
            	case 4 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1538:2: kw= '_'
            	    {
            	    kw=(Token)match(input,25,FOLLOW_25_in_ruleVariable3192); 

            	            current.merge(kw);
            	            newLeafNode(kw, grammarAccess.getVariableAccess().get_Keyword_1_3()); 
            	        

            	    }
            	    break;

            	default :
            	    break loop25;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleVariable"


    // $ANTLR start "entryRuleString"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1551:1: entryRuleString returns [String current=null] : iv_ruleString= ruleString EOF ;
    public final String entryRuleString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleString = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1552:2: (iv_ruleString= ruleString EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1553:2: iv_ruleString= ruleString EOF
            {
             newCompositeNode(grammarAccess.getStringRule()); 
            pushFollow(FOLLOW_ruleString_in_entryRuleString3235);
            iv_ruleString=ruleString();

            state._fsp--;

             current =iv_ruleString.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleString3246); 

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
    // $ANTLR end "entryRuleString"


    // $ANTLR start "ruleString"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1560:1: ruleString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_Character_0= ruleCharacter )* ;
    public final AntlrDatatypeRuleToken ruleString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        AntlrDatatypeRuleToken this_Character_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1563:28: ( (this_Character_0= ruleCharacter )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1564:1: (this_Character_0= ruleCharacter )*
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1564:1: (this_Character_0= ruleCharacter )*
            loop26:
            do {
                int alt26=2;
                int LA26_0 = input.LA(1);

                if ( ((LA26_0>=RULE_LOWER_CASE_LETTER && LA26_0<=RULE_UPPER_CASE_LETTER)||LA26_0==17||(LA26_0>=25 && LA26_0<=38)) ) {
                    alt26=1;
                }


                switch (alt26) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1565:5: this_Character_0= ruleCharacter
            	    {
            	     
            	            newCompositeNode(grammarAccess.getStringAccess().getCharacterParserRuleCall()); 
            	        
            	    pushFollow(FOLLOW_ruleCharacter_in_ruleString3293);
            	    this_Character_0=ruleCharacter();

            	    state._fsp--;


            	    		current.merge(this_Character_0);
            	        
            	     
            	            afterParserOrEnumRuleCall();
            	        

            	    }
            	    break;

            	default :
            	    break loop26;
                }
            } while (true);


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleString"

    // Delegated rules


    protected DFA8 dfa8 = new DFA8(this);
    static final String DFA8_eotS =
        "\10\uffff";
    static final String DFA8_eofS =
        "\1\uffff\1\3\2\uffff\4\3";
    static final String DFA8_minS =
        "\2\4\2\uffff\4\4";
    static final String DFA8_maxS =
        "\1\33\1\31\2\uffff\4\31";
    static final String DFA8_acceptS =
        "\2\uffff\1\1\1\2\4\uffff";
    static final String DFA8_specialS =
        "\10\uffff}>";
    static final String[] DFA8_transitionS = {
            "\6\2\1\1\2\3\4\uffff\1\3\1\uffff\1\3\1\uffff\2\3\2\uffff\1"+
            "\3\1\uffff\1\3",
            "\6\3\1\5\1\4\1\6\2\uffff\3\3\1\uffff\1\2\1\3\2\uffff\2\3\1"+
            "\7",
            "",
            "",
            "\6\3\1\5\1\4\1\6\2\uffff\3\3\1\uffff\1\2\1\3\2\uffff\2\3\1"+
            "\7",
            "\6\3\1\5\1\4\1\6\2\uffff\3\3\1\uffff\1\2\1\3\2\uffff\2\3\1"+
            "\7",
            "\6\3\1\5\1\4\1\6\2\uffff\3\3\1\uffff\1\2\1\3\2\uffff\2\3\1"+
            "\7",
            "\6\3\1\5\1\4\1\6\2\uffff\3\3\1\uffff\1\2\1\3\2\uffff\2\3\1"+
            "\7"
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
            return "293:1: ( ( ( ( (lv_ops_0_1= ruleAtom | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) otherlv_1= '(' ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* otherlv_5= ')' ) | ( (lv_exps_6_0= ruleExpression1100 ) ) )";
        }
    }
 

    public static final BitSet FOLLOW_ruleProgram_in_entryRuleProgram75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleProgram85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClause_in_ruleProgram131 = new BitSet(new long[]{0x000000000A6E1FF2L});
    public static final BitSet FOLLOW_ruleQuery_in_ruleProgram153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleClause_in_entryRuleClause190 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleClause200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleClause246 = new BitSet(new long[]{0x0000000000028000L});
    public static final BitSet FOLLOW_15_in_ruleClause259 = new BitSet(new long[]{0x000000000A6F1FF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleClause280 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_16_in_ruleClause293 = new BitSet(new long[]{0x000000000A6F1FF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleClause314 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_17_in_ruleClause330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQuery_in_entryRuleQuery366 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQuery376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleQuery413 = new BitSet(new long[]{0x000000000A6F1FF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleQuery434 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_16_in_ruleQuery447 = new BitSet(new long[]{0x000000000A6F1FF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleQuery468 = new BitSet(new long[]{0x0000000000030000L});
    public static final BitSet FOLLOW_17_in_ruleQuery482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_entryRuleExpressionINF518 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpressionINF528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtom_in_ruleExpressionINF577 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1100_in_ruleExpressionINF592 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_900_in_ruleExpressionINF612 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_700_in_ruleExpressionINF632 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_500_in_ruleExpressionINF652 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_400_in_ruleExpressionINF672 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_200_in_ruleExpressionINF692 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleExpressionINF712 = new BitSet(new long[]{0x000000000A7F1FF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpressionINF733 = new BitSet(new long[]{0x0000000000110000L});
    public static final BitSet FOLLOW_16_in_ruleExpressionINF746 = new BitSet(new long[]{0x000000000A7F1FF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpressionINF767 = new BitSet(new long[]{0x0000000000110000L});
    public static final BitSet FOLLOW_20_in_ruleExpressionINF781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1100_in_ruleExpressionINF809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression1100_in_entryRuleExpression1100845 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression1100855 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression900_in_ruleExpression1100901 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression1100919 = new BitSet(new long[]{0x000000000A6E1FF0L});
    public static final BitSet FOLLOW_ruleExpression900_in_ruleExpression1100945 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_ruleExpression900_in_entryRuleExpression900983 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression900993 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression700_in_ruleExpression9001039 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_900_in_ruleExpression9001057 = new BitSet(new long[]{0x000000000A6E1FF0L});
    public static final BitSet FOLLOW_ruleExpression700_in_ruleExpression9001083 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_ruleExpression700_in_entryRuleExpression7001121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression7001131 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression500_in_ruleExpression7001177 = new BitSet(new long[]{0x0000000000000042L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_700_in_ruleExpression7001195 = new BitSet(new long[]{0x000000000A6E1FF0L});
    public static final BitSet FOLLOW_ruleExpression500_in_ruleExpression7001221 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression500_in_entryRuleExpression5001259 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression5001269 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression400_in_ruleExpression5001315 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_500_in_ruleExpression5001333 = new BitSet(new long[]{0x000000000A6E1FF0L});
    public static final BitSet FOLLOW_ruleExpression400_in_ruleExpression5001359 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_ruleExpression400_in_entryRuleExpression4001397 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression4001407 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression200_in_ruleExpression4001453 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_400_in_ruleExpression4001471 = new BitSet(new long[]{0x000000000A6E1FF0L});
    public static final BitSet FOLLOW_ruleExpression200_in_ruleExpression4001497 = new BitSet(new long[]{0x0000000000000102L});
    public static final BitSet FOLLOW_ruleExpression200_in_entryRuleExpression2001535 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression2001545 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleExpression0_in_ruleExpression2001591 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_RULE_EXPRESSION_200_in_ruleExpression2001609 = new BitSet(new long[]{0x000000000A6E1FF0L});
    public static final BitSet FOLLOW_ruleExpression0_in_ruleExpression2001635 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_ruleExpression0_in_entryRuleExpression01673 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExpression01683 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTerm_in_ruleExpression01729 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleExpression01748 = new BitSet(new long[]{0x000000000A7E1FF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression01769 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleExpression01781 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTerm_in_entryRuleTerm1818 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTerm1828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtom_in_ruleTerm1874 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumber_in_ruleTerm1901 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_ruleTerm1920 = new BitSet(new long[]{0x0000007FFE221C00L});
    public static final BitSet FOLLOW_ruleString_in_ruleTerm1941 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleTerm1953 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVariable_in_ruleTerm1981 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_ruleTerm2006 = new BitSet(new long[]{0x000000000BEF1FF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleTerm2041 = new BitSet(new long[]{0x0000000001810000L});
    public static final BitSet FOLLOW_16_in_ruleTerm2054 = new BitSet(new long[]{0x000000000BEF1FF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleTerm2075 = new BitSet(new long[]{0x0000000001810000L});
    public static final BitSet FOLLOW_23_in_ruleTerm2090 = new BitSet(new long[]{0x000000000B6E1FF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleTerm2111 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleTerm2127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleTerm2153 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleTerm2178 = new BitSet(new long[]{0x000000000A6F1FF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleTerm2199 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16_in_ruleTerm2211 = new BitSet(new long[]{0x000000000A7E1FF0L});
    public static final BitSet FOLLOW_ruleExpressionINF_in_ruleTerm2232 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleTerm2244 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleAtom_in_entryRuleAtom2282 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleAtom2293 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_LOWER_CASE_LETTER_in_ruleAtom2333 = new BitSet(new long[]{0x0000000002001C02L});
    public static final BitSet FOLLOW_RULE_DIGIT_in_ruleAtom2354 = new BitSet(new long[]{0x0000000002001C02L});
    public static final BitSet FOLLOW_RULE_LOWER_CASE_LETTER_in_ruleAtom2380 = new BitSet(new long[]{0x0000000002001C02L});
    public static final BitSet FOLLOW_RULE_UPPER_CASE_LETTER_in_ruleAtom2406 = new BitSet(new long[]{0x0000000002001C02L});
    public static final BitSet FOLLOW_25_in_ruleAtom2430 = new BitSet(new long[]{0x0000000002001C02L});
    public static final BitSet FOLLOW_ruleCharacter_in_entryRuleCharacter2473 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleCharacter2484 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_DIGIT_in_ruleCharacter2524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_LOWER_CASE_LETTER_in_ruleCharacter2550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UPPER_CASE_LETTER_in_ruleCharacter2576 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleCharacter2600 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_ruleCharacter2619 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleCharacter2638 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_ruleCharacter2657 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_ruleCharacter2676 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_ruleCharacter2695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleCharacter2714 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleCharacter2733 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleCharacter2752 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_17_in_ruleCharacter2771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleCharacter2790 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_35_in_ruleCharacter2809 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_36_in_ruleCharacter2828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_37_in_ruleCharacter2847 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_38_in_ruleCharacter2866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNumber_in_entryRuleNumber2907 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNumber2918 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_ruleNumber2957 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RULE_DIGIT_in_ruleNumber2975 = new BitSet(new long[]{0x0000000000000802L});
    public static final BitSet FOLLOW_ruleVariable_in_entryRuleVariable3023 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVariable3034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UPPER_CASE_LETTER_in_ruleVariable3075 = new BitSet(new long[]{0x0000000002001C02L});
    public static final BitSet FOLLOW_25_in_ruleVariable3099 = new BitSet(new long[]{0x0000000002001C02L});
    public static final BitSet FOLLOW_RULE_DIGIT_in_ruleVariable3116 = new BitSet(new long[]{0x0000000002001C02L});
    public static final BitSet FOLLOW_RULE_LOWER_CASE_LETTER_in_ruleVariable3142 = new BitSet(new long[]{0x0000000002001C02L});
    public static final BitSet FOLLOW_RULE_UPPER_CASE_LETTER_in_ruleVariable3168 = new BitSet(new long[]{0x0000000002001C02L});
    public static final BitSet FOLLOW_25_in_ruleVariable3192 = new BitSet(new long[]{0x0000000002001C02L});
    public static final BitSet FOLLOW_ruleString_in_entryRuleString3235 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleString3246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleCharacter_in_ruleString3293 = new BitSet(new long[]{0x0000007FFE021C02L});

}