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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_NUMERAL", "RULE_STRING", "RULE_LOWER_CASE_LETTER", "RULE_CHARACTER", "RULE_UPPER_CASE_LETTER", "RULE_DIGIT", "RULE_SPECIAL", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "':-'", "','", "'.'", "'?-'", "'('", "')'"
    };
    public static final int RULE_UPPER_CASE_LETTER=8;
    public static final int RULE_ID=11;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=16;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_CHARACTER=7;
    public static final int RULE_LOWER_CASE_LETTER=6;
    public static final int RULE_SL_COMMENT=14;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=13;
    public static final int T__19=19;
    public static final int RULE_STRING=5;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int RULE_SPECIAL=10;
    public static final int RULE_INT=12;
    public static final int RULE_WS=15;
    public static final int RULE_NUMERAL=4;
    public static final int RULE_DIGIT=9;

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:76:1: ruleProgram returns [EObject current=null] : ( ( (lv_clauses_0_0= ruleSingleClause ) )* ( (lv_query_1_0= ruleQuery ) )? ) ;
    public final EObject ruleProgram() throws RecognitionException {
        EObject current = null;

        EObject lv_clauses_0_0 = null;

        EObject lv_query_1_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:79:28: ( ( ( (lv_clauses_0_0= ruleSingleClause ) )* ( (lv_query_1_0= ruleQuery ) )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:80:1: ( ( (lv_clauses_0_0= ruleSingleClause ) )* ( (lv_query_1_0= ruleQuery ) )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:80:1: ( ( (lv_clauses_0_0= ruleSingleClause ) )* ( (lv_query_1_0= ruleQuery ) )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:80:2: ( (lv_clauses_0_0= ruleSingleClause ) )* ( (lv_query_1_0= ruleQuery ) )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:80:2: ( (lv_clauses_0_0= ruleSingleClause ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>=RULE_NUMERAL && LA1_0<=RULE_LOWER_CASE_LETTER)||LA1_0==RULE_UPPER_CASE_LETTER) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:81:1: (lv_clauses_0_0= ruleSingleClause )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:81:1: (lv_clauses_0_0= ruleSingleClause )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:82:3: lv_clauses_0_0= ruleSingleClause
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getProgramAccess().getClausesSingleClauseParserRuleCall_0_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleSingleClause_in_ruleProgram131);
            	    lv_clauses_0_0=ruleSingleClause();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getProgramRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"clauses",
            	            		lv_clauses_0_0, 
            	            		"SingleClause");
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

            if ( (LA2_0==20) ) {
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


    // $ANTLR start "entryRuleSingleClause"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:124:1: entryRuleSingleClause returns [EObject current=null] : iv_ruleSingleClause= ruleSingleClause EOF ;
    public final EObject entryRuleSingleClause() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSingleClause = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:125:2: (iv_ruleSingleClause= ruleSingleClause EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:126:2: iv_ruleSingleClause= ruleSingleClause EOF
            {
             newCompositeNode(grammarAccess.getSingleClauseRule()); 
            pushFollow(FOLLOW_ruleSingleClause_in_entryRuleSingleClause190);
            iv_ruleSingleClause=ruleSingleClause();

            state._fsp--;

             current =iv_ruleSingleClause; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSingleClause200); 

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
    // $ANTLR end "entryRuleSingleClause"


    // $ANTLR start "ruleSingleClause"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:133:1: ruleSingleClause returns [EObject current=null] : ( ( (lv_predicates_0_0= rulePredicate ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= rulePredicate ) ) (otherlv_3= ',' ( (lv_predicates_4_0= rulePredicate ) ) )* )? otherlv_5= '.' ) ;
    public final EObject ruleSingleClause() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_predicates_0_0 = null;

        EObject lv_predicates_2_0 = null;

        EObject lv_predicates_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:136:28: ( ( ( (lv_predicates_0_0= rulePredicate ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= rulePredicate ) ) (otherlv_3= ',' ( (lv_predicates_4_0= rulePredicate ) ) )* )? otherlv_5= '.' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:137:1: ( ( (lv_predicates_0_0= rulePredicate ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= rulePredicate ) ) (otherlv_3= ',' ( (lv_predicates_4_0= rulePredicate ) ) )* )? otherlv_5= '.' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:137:1: ( ( (lv_predicates_0_0= rulePredicate ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= rulePredicate ) ) (otherlv_3= ',' ( (lv_predicates_4_0= rulePredicate ) ) )* )? otherlv_5= '.' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:137:2: ( (lv_predicates_0_0= rulePredicate ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= rulePredicate ) ) (otherlv_3= ',' ( (lv_predicates_4_0= rulePredicate ) ) )* )? otherlv_5= '.'
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:137:2: ( (lv_predicates_0_0= rulePredicate ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:138:1: (lv_predicates_0_0= rulePredicate )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:138:1: (lv_predicates_0_0= rulePredicate )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:139:3: lv_predicates_0_0= rulePredicate
            {
             
            	        newCompositeNode(grammarAccess.getSingleClauseAccess().getPredicatesPredicateParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_rulePredicate_in_ruleSingleClause246);
            lv_predicates_0_0=rulePredicate();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getSingleClauseRule());
            	        }
                   		add(
                   			current, 
                   			"predicates",
                    		lv_predicates_0_0, 
                    		"Predicate");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:155:2: (otherlv_1= ':-' ( (lv_predicates_2_0= rulePredicate ) ) (otherlv_3= ',' ( (lv_predicates_4_0= rulePredicate ) ) )* )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==17) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:155:4: otherlv_1= ':-' ( (lv_predicates_2_0= rulePredicate ) ) (otherlv_3= ',' ( (lv_predicates_4_0= rulePredicate ) ) )*
                    {
                    otherlv_1=(Token)match(input,17,FOLLOW_17_in_ruleSingleClause259); 

                        	newLeafNode(otherlv_1, grammarAccess.getSingleClauseAccess().getColonHyphenMinusKeyword_1_0());
                        
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:159:1: ( (lv_predicates_2_0= rulePredicate ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:160:1: (lv_predicates_2_0= rulePredicate )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:160:1: (lv_predicates_2_0= rulePredicate )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:161:3: lv_predicates_2_0= rulePredicate
                    {
                     
                    	        newCompositeNode(grammarAccess.getSingleClauseAccess().getPredicatesPredicateParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_rulePredicate_in_ruleSingleClause280);
                    lv_predicates_2_0=rulePredicate();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSingleClauseRule());
                    	        }
                           		add(
                           			current, 
                           			"predicates",
                            		lv_predicates_2_0, 
                            		"Predicate");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:177:2: (otherlv_3= ',' ( (lv_predicates_4_0= rulePredicate ) ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==18) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:177:4: otherlv_3= ',' ( (lv_predicates_4_0= rulePredicate ) )
                    	    {
                    	    otherlv_3=(Token)match(input,18,FOLLOW_18_in_ruleSingleClause293); 

                    	        	newLeafNode(otherlv_3, grammarAccess.getSingleClauseAccess().getCommaKeyword_1_2_0());
                    	        
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:181:1: ( (lv_predicates_4_0= rulePredicate ) )
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:182:1: (lv_predicates_4_0= rulePredicate )
                    	    {
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:182:1: (lv_predicates_4_0= rulePredicate )
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:183:3: lv_predicates_4_0= rulePredicate
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getSingleClauseAccess().getPredicatesPredicateParserRuleCall_1_2_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_rulePredicate_in_ruleSingleClause314);
                    	    lv_predicates_4_0=rulePredicate();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getSingleClauseRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"predicates",
                    	            		lv_predicates_4_0, 
                    	            		"Predicate");
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

            otherlv_5=(Token)match(input,19,FOLLOW_19_in_ruleSingleClause330); 

                	newLeafNode(otherlv_5, grammarAccess.getSingleClauseAccess().getFullStopKeyword_2());
                

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
    // $ANTLR end "ruleSingleClause"


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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:220:1: ruleQuery returns [EObject current=null] : (otherlv_0= '?-' ( (lv_predicates_1_0= rulePredicate ) ) (otherlv_2= ',' ( (lv_predicates_3_0= rulePredicate ) ) )* otherlv_4= '.' ) ;
    public final EObject ruleQuery() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_2=null;
        Token otherlv_4=null;
        EObject lv_predicates_1_0 = null;

        EObject lv_predicates_3_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:223:28: ( (otherlv_0= '?-' ( (lv_predicates_1_0= rulePredicate ) ) (otherlv_2= ',' ( (lv_predicates_3_0= rulePredicate ) ) )* otherlv_4= '.' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:224:1: (otherlv_0= '?-' ( (lv_predicates_1_0= rulePredicate ) ) (otherlv_2= ',' ( (lv_predicates_3_0= rulePredicate ) ) )* otherlv_4= '.' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:224:1: (otherlv_0= '?-' ( (lv_predicates_1_0= rulePredicate ) ) (otherlv_2= ',' ( (lv_predicates_3_0= rulePredicate ) ) )* otherlv_4= '.' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:224:3: otherlv_0= '?-' ( (lv_predicates_1_0= rulePredicate ) ) (otherlv_2= ',' ( (lv_predicates_3_0= rulePredicate ) ) )* otherlv_4= '.'
            {
            otherlv_0=(Token)match(input,20,FOLLOW_20_in_ruleQuery413); 

                	newLeafNode(otherlv_0, grammarAccess.getQueryAccess().getQuestionMarkHyphenMinusKeyword_0());
                
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:228:1: ( (lv_predicates_1_0= rulePredicate ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:229:1: (lv_predicates_1_0= rulePredicate )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:229:1: (lv_predicates_1_0= rulePredicate )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:230:3: lv_predicates_1_0= rulePredicate
            {
             
            	        newCompositeNode(grammarAccess.getQueryAccess().getPredicatesPredicateParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulePredicate_in_ruleQuery434);
            lv_predicates_1_0=rulePredicate();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getQueryRule());
            	        }
                   		add(
                   			current, 
                   			"predicates",
                    		lv_predicates_1_0, 
                    		"Predicate");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:246:2: (otherlv_2= ',' ( (lv_predicates_3_0= rulePredicate ) ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==18) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:246:4: otherlv_2= ',' ( (lv_predicates_3_0= rulePredicate ) )
            	    {
            	    otherlv_2=(Token)match(input,18,FOLLOW_18_in_ruleQuery447); 

            	        	newLeafNode(otherlv_2, grammarAccess.getQueryAccess().getCommaKeyword_2_0());
            	        
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:250:1: ( (lv_predicates_3_0= rulePredicate ) )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:251:1: (lv_predicates_3_0= rulePredicate )
            	    {
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:251:1: (lv_predicates_3_0= rulePredicate )
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:252:3: lv_predicates_3_0= rulePredicate
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getQueryAccess().getPredicatesPredicateParserRuleCall_2_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_rulePredicate_in_ruleQuery468);
            	    lv_predicates_3_0=rulePredicate();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getQueryRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"predicates",
            	            		lv_predicates_3_0, 
            	            		"Predicate");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            otherlv_4=(Token)match(input,19,FOLLOW_19_in_ruleQuery482); 

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


    // $ANTLR start "entryRulePredicate"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:280:1: entryRulePredicate returns [EObject current=null] : iv_rulePredicate= rulePredicate EOF ;
    public final EObject entryRulePredicate() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePredicate = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:281:2: (iv_rulePredicate= rulePredicate EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:282:2: iv_rulePredicate= rulePredicate EOF
            {
             newCompositeNode(grammarAccess.getPredicateRule()); 
            pushFollow(FOLLOW_rulePredicate_in_entryRulePredicate518);
            iv_rulePredicate=rulePredicate();

            state._fsp--;

             current =iv_rulePredicate; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePredicate528); 

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
    // $ANTLR end "entryRulePredicate"


    // $ANTLR start "rulePredicate"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:289:1: rulePredicate returns [EObject current=null] : ( ( (lv_value_0_0= ruleSingleTerm ) ) (otherlv_1= '(' ( (lv_terms_2_0= ruleSingleTerm ) ) (otherlv_3= ',' ( (lv_terms_4_0= ruleSingleTerm ) ) )* otherlv_5= ')' )? ) ;
    public final EObject rulePredicate() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_3=null;
        Token otherlv_5=null;
        EObject lv_value_0_0 = null;

        EObject lv_terms_2_0 = null;

        EObject lv_terms_4_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:292:28: ( ( ( (lv_value_0_0= ruleSingleTerm ) ) (otherlv_1= '(' ( (lv_terms_2_0= ruleSingleTerm ) ) (otherlv_3= ',' ( (lv_terms_4_0= ruleSingleTerm ) ) )* otherlv_5= ')' )? ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:293:1: ( ( (lv_value_0_0= ruleSingleTerm ) ) (otherlv_1= '(' ( (lv_terms_2_0= ruleSingleTerm ) ) (otherlv_3= ',' ( (lv_terms_4_0= ruleSingleTerm ) ) )* otherlv_5= ')' )? )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:293:1: ( ( (lv_value_0_0= ruleSingleTerm ) ) (otherlv_1= '(' ( (lv_terms_2_0= ruleSingleTerm ) ) (otherlv_3= ',' ( (lv_terms_4_0= ruleSingleTerm ) ) )* otherlv_5= ')' )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:293:2: ( (lv_value_0_0= ruleSingleTerm ) ) (otherlv_1= '(' ( (lv_terms_2_0= ruleSingleTerm ) ) (otherlv_3= ',' ( (lv_terms_4_0= ruleSingleTerm ) ) )* otherlv_5= ')' )?
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:293:2: ( (lv_value_0_0= ruleSingleTerm ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:294:1: (lv_value_0_0= ruleSingleTerm )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:294:1: (lv_value_0_0= ruleSingleTerm )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:295:3: lv_value_0_0= ruleSingleTerm
            {
             
            	        newCompositeNode(grammarAccess.getPredicateAccess().getValueSingleTermParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleSingleTerm_in_rulePredicate574);
            lv_value_0_0=ruleSingleTerm();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getPredicateRule());
            	        }
                   		set(
                   			current, 
                   			"value",
                    		lv_value_0_0, 
                    		"SingleTerm");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:311:2: (otherlv_1= '(' ( (lv_terms_2_0= ruleSingleTerm ) ) (otherlv_3= ',' ( (lv_terms_4_0= ruleSingleTerm ) ) )* otherlv_5= ')' )?
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==21) ) {
                alt7=1;
            }
            switch (alt7) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:311:4: otherlv_1= '(' ( (lv_terms_2_0= ruleSingleTerm ) ) (otherlv_3= ',' ( (lv_terms_4_0= ruleSingleTerm ) ) )* otherlv_5= ')'
                    {
                    otherlv_1=(Token)match(input,21,FOLLOW_21_in_rulePredicate587); 

                        	newLeafNode(otherlv_1, grammarAccess.getPredicateAccess().getLeftParenthesisKeyword_1_0());
                        
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:315:1: ( (lv_terms_2_0= ruleSingleTerm ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:316:1: (lv_terms_2_0= ruleSingleTerm )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:316:1: (lv_terms_2_0= ruleSingleTerm )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:317:3: lv_terms_2_0= ruleSingleTerm
                    {
                     
                    	        newCompositeNode(grammarAccess.getPredicateAccess().getTermsSingleTermParserRuleCall_1_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSingleTerm_in_rulePredicate608);
                    lv_terms_2_0=ruleSingleTerm();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getPredicateRule());
                    	        }
                           		add(
                           			current, 
                           			"terms",
                            		lv_terms_2_0, 
                            		"SingleTerm");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }

                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:333:2: (otherlv_3= ',' ( (lv_terms_4_0= ruleSingleTerm ) ) )*
                    loop6:
                    do {
                        int alt6=2;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0==18) ) {
                            alt6=1;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:333:4: otherlv_3= ',' ( (lv_terms_4_0= ruleSingleTerm ) )
                    	    {
                    	    otherlv_3=(Token)match(input,18,FOLLOW_18_in_rulePredicate621); 

                    	        	newLeafNode(otherlv_3, grammarAccess.getPredicateAccess().getCommaKeyword_1_2_0());
                    	        
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:337:1: ( (lv_terms_4_0= ruleSingleTerm ) )
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:338:1: (lv_terms_4_0= ruleSingleTerm )
                    	    {
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:338:1: (lv_terms_4_0= ruleSingleTerm )
                    	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:339:3: lv_terms_4_0= ruleSingleTerm
                    	    {
                    	     
                    	    	        newCompositeNode(grammarAccess.getPredicateAccess().getTermsSingleTermParserRuleCall_1_2_1_0()); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleSingleTerm_in_rulePredicate642);
                    	    lv_terms_4_0=ruleSingleTerm();

                    	    state._fsp--;


                    	    	        if (current==null) {
                    	    	            current = createModelElementForParent(grammarAccess.getPredicateRule());
                    	    	        }
                    	           		add(
                    	           			current, 
                    	           			"terms",
                    	            		lv_terms_4_0, 
                    	            		"SingleTerm");
                    	    	        afterParserOrEnumRuleCall();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    otherlv_5=(Token)match(input,22,FOLLOW_22_in_rulePredicate656); 

                        	newLeafNode(otherlv_5, grammarAccess.getPredicateAccess().getRightParenthesisKeyword_1_3());
                        

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
    // $ANTLR end "rulePredicate"


    // $ANTLR start "entryRuleSingleTerm"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:367:1: entryRuleSingleTerm returns [EObject current=null] : iv_ruleSingleTerm= ruleSingleTerm EOF ;
    public final EObject entryRuleSingleTerm() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSingleTerm = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:368:2: (iv_ruleSingleTerm= ruleSingleTerm EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:369:2: iv_ruleSingleTerm= ruleSingleTerm EOF
            {
             newCompositeNode(grammarAccess.getSingleTermRule()); 
            pushFollow(FOLLOW_ruleSingleTerm_in_entryRuleSingleTerm694);
            iv_ruleSingleTerm=ruleSingleTerm();

            state._fsp--;

             current =iv_ruleSingleTerm; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSingleTerm704); 

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
    // $ANTLR end "entryRuleSingleTerm"


    // $ANTLR start "ruleSingleTerm"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:376:1: ruleSingleTerm returns [EObject current=null] : ( ( (lv_numeral_0_0= RULE_NUMERAL ) ) | ( (lv_atom_1_0= ruleSmallAtom ) ) | ( (lv_variable_2_0= ruleVariable ) ) | ( (lv_string_3_0= RULE_STRING ) ) ) ;
    public final EObject ruleSingleTerm() throws RecognitionException {
        EObject current = null;

        Token lv_numeral_0_0=null;
        Token lv_string_3_0=null;
        AntlrDatatypeRuleToken lv_atom_1_0 = null;

        AntlrDatatypeRuleToken lv_variable_2_0 = null;


         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:379:28: ( ( ( (lv_numeral_0_0= RULE_NUMERAL ) ) | ( (lv_atom_1_0= ruleSmallAtom ) ) | ( (lv_variable_2_0= ruleVariable ) ) | ( (lv_string_3_0= RULE_STRING ) ) ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:380:1: ( ( (lv_numeral_0_0= RULE_NUMERAL ) ) | ( (lv_atom_1_0= ruleSmallAtom ) ) | ( (lv_variable_2_0= ruleVariable ) ) | ( (lv_string_3_0= RULE_STRING ) ) )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:380:1: ( ( (lv_numeral_0_0= RULE_NUMERAL ) ) | ( (lv_atom_1_0= ruleSmallAtom ) ) | ( (lv_variable_2_0= ruleVariable ) ) | ( (lv_string_3_0= RULE_STRING ) ) )
            int alt8=4;
            switch ( input.LA(1) ) {
            case RULE_NUMERAL:
                {
                alt8=1;
                }
                break;
            case RULE_LOWER_CASE_LETTER:
                {
                alt8=2;
                }
                break;
            case RULE_UPPER_CASE_LETTER:
                {
                alt8=3;
                }
                break;
            case RULE_STRING:
                {
                alt8=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:380:2: ( (lv_numeral_0_0= RULE_NUMERAL ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:380:2: ( (lv_numeral_0_0= RULE_NUMERAL ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:381:1: (lv_numeral_0_0= RULE_NUMERAL )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:381:1: (lv_numeral_0_0= RULE_NUMERAL )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:382:3: lv_numeral_0_0= RULE_NUMERAL
                    {
                    lv_numeral_0_0=(Token)match(input,RULE_NUMERAL,FOLLOW_RULE_NUMERAL_in_ruleSingleTerm746); 

                    			newLeafNode(lv_numeral_0_0, grammarAccess.getSingleTermAccess().getNumeralNUMERALTerminalRuleCall_0_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSingleTermRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"numeral",
                            		lv_numeral_0_0, 
                            		"NUMERAL");
                    	    

                    }


                    }


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:399:6: ( (lv_atom_1_0= ruleSmallAtom ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:399:6: ( (lv_atom_1_0= ruleSmallAtom ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:400:1: (lv_atom_1_0= ruleSmallAtom )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:400:1: (lv_atom_1_0= ruleSmallAtom )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:401:3: lv_atom_1_0= ruleSmallAtom
                    {
                     
                    	        newCompositeNode(grammarAccess.getSingleTermAccess().getAtomSmallAtomParserRuleCall_1_0()); 
                    	    
                    pushFollow(FOLLOW_ruleSmallAtom_in_ruleSingleTerm778);
                    lv_atom_1_0=ruleSmallAtom();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSingleTermRule());
                    	        }
                           		set(
                           			current, 
                           			"atom",
                            		lv_atom_1_0, 
                            		"SmallAtom");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:418:6: ( (lv_variable_2_0= ruleVariable ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:418:6: ( (lv_variable_2_0= ruleVariable ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:419:1: (lv_variable_2_0= ruleVariable )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:419:1: (lv_variable_2_0= ruleVariable )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:420:3: lv_variable_2_0= ruleVariable
                    {
                     
                    	        newCompositeNode(grammarAccess.getSingleTermAccess().getVariableVariableParserRuleCall_2_0()); 
                    	    
                    pushFollow(FOLLOW_ruleVariable_in_ruleSingleTerm805);
                    lv_variable_2_0=ruleVariable();

                    state._fsp--;


                    	        if (current==null) {
                    	            current = createModelElementForParent(grammarAccess.getSingleTermRule());
                    	        }
                           		set(
                           			current, 
                           			"variable",
                            		lv_variable_2_0, 
                            		"Variable");
                    	        afterParserOrEnumRuleCall();
                    	    

                    }


                    }


                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:437:6: ( (lv_string_3_0= RULE_STRING ) )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:437:6: ( (lv_string_3_0= RULE_STRING ) )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:438:1: (lv_string_3_0= RULE_STRING )
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:438:1: (lv_string_3_0= RULE_STRING )
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:439:3: lv_string_3_0= RULE_STRING
                    {
                    lv_string_3_0=(Token)match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleSingleTerm828); 

                    			newLeafNode(lv_string_3_0, grammarAccess.getSingleTermAccess().getStringSTRINGTerminalRuleCall_3_0()); 
                    		

                    	        if (current==null) {
                    	            current = createModelElement(grammarAccess.getSingleTermRule());
                    	        }
                           		setWithLastConsumed(
                           			current, 
                           			"string",
                            		lv_string_3_0, 
                            		"STRING");
                    	    

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
    // $ANTLR end "ruleSingleTerm"


    // $ANTLR start "entryRuleSmallAtom"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:463:1: entryRuleSmallAtom returns [String current=null] : iv_ruleSmallAtom= ruleSmallAtom EOF ;
    public final String entryRuleSmallAtom() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleSmallAtom = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:464:2: (iv_ruleSmallAtom= ruleSmallAtom EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:465:2: iv_ruleSmallAtom= ruleSmallAtom EOF
            {
             newCompositeNode(grammarAccess.getSmallAtomRule()); 
            pushFollow(FOLLOW_ruleSmallAtom_in_entryRuleSmallAtom870);
            iv_ruleSmallAtom=ruleSmallAtom();

            state._fsp--;

             current =iv_ruleSmallAtom.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSmallAtom881); 

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
    // $ANTLR end "entryRuleSmallAtom"


    // $ANTLR start "ruleSmallAtom"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:472:1: ruleSmallAtom returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_LOWER_CASE_LETTER_0= RULE_LOWER_CASE_LETTER (this_CHARACTER_1= RULE_CHARACTER )* ) ;
    public final AntlrDatatypeRuleToken ruleSmallAtom() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_LOWER_CASE_LETTER_0=null;
        Token this_CHARACTER_1=null;

         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:475:28: ( (this_LOWER_CASE_LETTER_0= RULE_LOWER_CASE_LETTER (this_CHARACTER_1= RULE_CHARACTER )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:476:1: (this_LOWER_CASE_LETTER_0= RULE_LOWER_CASE_LETTER (this_CHARACTER_1= RULE_CHARACTER )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:476:1: (this_LOWER_CASE_LETTER_0= RULE_LOWER_CASE_LETTER (this_CHARACTER_1= RULE_CHARACTER )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:476:6: this_LOWER_CASE_LETTER_0= RULE_LOWER_CASE_LETTER (this_CHARACTER_1= RULE_CHARACTER )*
            {
            this_LOWER_CASE_LETTER_0=(Token)match(input,RULE_LOWER_CASE_LETTER,FOLLOW_RULE_LOWER_CASE_LETTER_in_ruleSmallAtom921); 

            		current.merge(this_LOWER_CASE_LETTER_0);
                
             
                newLeafNode(this_LOWER_CASE_LETTER_0, grammarAccess.getSmallAtomAccess().getLOWER_CASE_LETTERTerminalRuleCall_0()); 
                
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:483:1: (this_CHARACTER_1= RULE_CHARACTER )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_CHARACTER) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:483:6: this_CHARACTER_1= RULE_CHARACTER
            	    {
            	    this_CHARACTER_1=(Token)match(input,RULE_CHARACTER,FOLLOW_RULE_CHARACTER_in_ruleSmallAtom942); 

            	    		current.merge(this_CHARACTER_1);
            	        
            	     
            	        newLeafNode(this_CHARACTER_1, grammarAccess.getSmallAtomAccess().getCHARACTERTerminalRuleCall_1()); 
            	        

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
    // $ANTLR end "ruleSmallAtom"


    // $ANTLR start "entryRuleVariable"
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:498:1: entryRuleVariable returns [String current=null] : iv_ruleVariable= ruleVariable EOF ;
    public final String entryRuleVariable() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleVariable = null;


        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:499:2: (iv_ruleVariable= ruleVariable EOF )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:500:2: iv_ruleVariable= ruleVariable EOF
            {
             newCompositeNode(grammarAccess.getVariableRule()); 
            pushFollow(FOLLOW_ruleVariable_in_entryRuleVariable990);
            iv_ruleVariable=ruleVariable();

            state._fsp--;

             current =iv_ruleVariable.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVariable1001); 

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
    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:507:1: ruleVariable returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_UPPER_CASE_LETTER_0= RULE_UPPER_CASE_LETTER (this_CHARACTER_1= RULE_CHARACTER )* ) ;
    public final AntlrDatatypeRuleToken ruleVariable() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_UPPER_CASE_LETTER_0=null;
        Token this_CHARACTER_1=null;

         enterRule(); 
            
        try {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:510:28: ( (this_UPPER_CASE_LETTER_0= RULE_UPPER_CASE_LETTER (this_CHARACTER_1= RULE_CHARACTER )* ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:511:1: (this_UPPER_CASE_LETTER_0= RULE_UPPER_CASE_LETTER (this_CHARACTER_1= RULE_CHARACTER )* )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:511:1: (this_UPPER_CASE_LETTER_0= RULE_UPPER_CASE_LETTER (this_CHARACTER_1= RULE_CHARACTER )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:511:6: this_UPPER_CASE_LETTER_0= RULE_UPPER_CASE_LETTER (this_CHARACTER_1= RULE_CHARACTER )*
            {
            this_UPPER_CASE_LETTER_0=(Token)match(input,RULE_UPPER_CASE_LETTER,FOLLOW_RULE_UPPER_CASE_LETTER_in_ruleVariable1041); 

            		current.merge(this_UPPER_CASE_LETTER_0);
                
             
                newLeafNode(this_UPPER_CASE_LETTER_0, grammarAccess.getVariableAccess().getUPPER_CASE_LETTERTerminalRuleCall_0()); 
                
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:518:1: (this_CHARACTER_1= RULE_CHARACTER )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_CHARACTER) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:518:6: this_CHARACTER_1= RULE_CHARACTER
            	    {
            	    this_CHARACTER_1=(Token)match(input,RULE_CHARACTER,FOLLOW_RULE_CHARACTER_in_ruleVariable1062); 

            	    		current.merge(this_CHARACTER_1);
            	        
            	     
            	        newLeafNode(this_CHARACTER_1, grammarAccess.getVariableAccess().getCHARACTERTerminalRuleCall_1()); 
            	        

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
    // $ANTLR end "ruleVariable"

    // Delegated rules


 

    public static final BitSet FOLLOW_ruleProgram_in_entryRuleProgram75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleProgram85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSingleClause_in_ruleProgram131 = new BitSet(new long[]{0x0000000000100172L});
    public static final BitSet FOLLOW_ruleQuery_in_ruleProgram153 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSingleClause_in_entryRuleSingleClause190 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSingleClause200 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePredicate_in_ruleSingleClause246 = new BitSet(new long[]{0x00000000000A0000L});
    public static final BitSet FOLLOW_17_in_ruleSingleClause259 = new BitSet(new long[]{0x00000000001C0170L});
    public static final BitSet FOLLOW_rulePredicate_in_ruleSingleClause280 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_18_in_ruleSingleClause293 = new BitSet(new long[]{0x00000000001C0170L});
    public static final BitSet FOLLOW_rulePredicate_in_ruleSingleClause314 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_19_in_ruleSingleClause330 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleQuery_in_entryRuleQuery366 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleQuery376 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_ruleQuery413 = new BitSet(new long[]{0x00000000001C0170L});
    public static final BitSet FOLLOW_rulePredicate_in_ruleQuery434 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_18_in_ruleQuery447 = new BitSet(new long[]{0x00000000001C0170L});
    public static final BitSet FOLLOW_rulePredicate_in_ruleQuery468 = new BitSet(new long[]{0x00000000000C0000L});
    public static final BitSet FOLLOW_19_in_ruleQuery482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePredicate_in_entryRulePredicate518 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePredicate528 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSingleTerm_in_rulePredicate574 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_21_in_rulePredicate587 = new BitSet(new long[]{0x0000000000540170L});
    public static final BitSet FOLLOW_ruleSingleTerm_in_rulePredicate608 = new BitSet(new long[]{0x0000000000440000L});
    public static final BitSet FOLLOW_18_in_rulePredicate621 = new BitSet(new long[]{0x0000000000540170L});
    public static final BitSet FOLLOW_ruleSingleTerm_in_rulePredicate642 = new BitSet(new long[]{0x0000000000440000L});
    public static final BitSet FOLLOW_22_in_rulePredicate656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSingleTerm_in_entryRuleSingleTerm694 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSingleTerm704 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_NUMERAL_in_ruleSingleTerm746 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSmallAtom_in_ruleSingleTerm778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleVariable_in_ruleSingleTerm805 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleSingleTerm828 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSmallAtom_in_entryRuleSmallAtom870 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSmallAtom881 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_LOWER_CASE_LETTER_in_ruleSmallAtom921 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_RULE_CHARACTER_in_ruleSmallAtom942 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_ruleVariable_in_entryRuleVariable990 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVariable1001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_UPPER_CASE_LETTER_in_ruleVariable1041 = new BitSet(new long[]{0x0000000000000082L});
    public static final BitSet FOLLOW_RULE_CHARACTER_in_ruleVariable1062 = new BitSet(new long[]{0x0000000000000082L});

}