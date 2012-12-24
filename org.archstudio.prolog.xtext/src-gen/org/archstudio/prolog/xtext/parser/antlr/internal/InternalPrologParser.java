package org.archstudio.prolog.xtext.parser.antlr.internal;

import org.antlr.runtime.BaseRecognizer;
import org.antlr.runtime.BitSet;
import org.antlr.runtime.DFA;
import org.antlr.runtime.IntStream;
import org.antlr.runtime.NoViableAltException;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.RecognizerSharedState;
import org.antlr.runtime.Token;
import org.antlr.runtime.TokenStream;
import org.archstudio.prolog.xtext.services.PrologGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;

@SuppressWarnings("all")
public class InternalPrologParser extends AbstractInternalAntlrParser {
	public static final String[] tokenNames = new String[] { "<invalid>", "<EOR>", "<DOWN>", "<UP>",
			"RULE_EXPRESSION_1100", "RULE_EXPRESSION_900", "RULE_EXPRESSION_700", "RULE_EXPRESSION_500",
			"RULE_EXPRESSION_400", "RULE_EXPRESSION_200", "RULE_ATOM", "RULE_NUMBER", "RULE_STRING", "RULE_VARIABLE",
			"RULE_WHITESPACE", "RULE_SINGLE_LINE_COMMENT", "':-'", "','", "'.'", "'?-'", "'('", "')'", "'['", "'|'",
			"']'" };
	public static final int T__23 = 23;
	public static final int RULE_NUMBER = 11;
	public static final int T__20 = 20;
	public static final int RULE_STRING = 12;
	public static final int RULE_EXPRESSION_200 = 9;
	public static final int RULE_EXPRESSION_500 = 7;
	public static final int T__21 = 21;
	public static final int RULE_SINGLE_LINE_COMMENT = 15;
	public static final int T__19 = 19;
	public static final int T__22 = 22;
	public static final int RULE_EXPRESSION_900 = 5;
	public static final int RULE_EXPRESSION_400 = 8;
	public static final int RULE_WHITESPACE = 14;
	public static final int RULE_VARIABLE = 13;
	public static final int T__17 = 17;
	public static final int EOF = -1;
	public static final int RULE_EXPRESSION_700 = 6;
	public static final int T__16 = 16;
	public static final int T__24 = 24;
	public static final int RULE_EXPRESSION_1100 = 4;
	public static final int T__18 = 18;
	public static final int RULE_ATOM = 10;

	// delegates
	// delegators

	public InternalPrologParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}

	public InternalPrologParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);

	}

	@Override
	public String[] getTokenNames() {
		return InternalPrologParser.tokenNames;
	}

	@Override
	public String getGrammarFileName() {
		return "../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g";
	}

	/*
	 * This grammar contains a lot of empty actions to work around a bug in
	 * ANTLR. Otherwise the ANTLR tool will create synpreds that cannot be
	 * compiled in some rare cases.
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
				if (state.backtracking == 0) {
					newCompositeNode(grammarAccess.getProgramRule());
				}
				pushFollow(FOLLOW_ruleProgram_in_entryRuleProgram81);
				iv_ruleProgram = ruleProgram();

				state._fsp--;
				if (state.failed) {
					return current;
				}
				if (state.backtracking == 0) {
					current = iv_ruleProgram;
				}
				match(input, EOF, FOLLOW_EOF_in_entryRuleProgram91);
				if (state.failed) {
					return current;
				}

			}

		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "entryRuleProgram"

	// $ANTLR start "ruleProgram"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:82:1: ruleProgram returns [EObject current=null] : ( ( (lv_clauses_0_0= ruleClause ) )* ( (lv_query_1_0= ruleQuery ) )? ) ;
	public final EObject ruleProgram() throws RecognitionException {
		EObject current = null;

		EObject lv_clauses_0_0 = null;

		EObject lv_query_1_0 = null;

		enterRule();

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:85:28: ( ( ( (lv_clauses_0_0= ruleClause ) )* ( (lv_query_1_0= ruleQuery ) )? ) )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:86:1: ( ( (lv_clauses_0_0= ruleClause ) )* ( (lv_query_1_0= ruleQuery ) )? )
			{
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:86:1: ( ( (lv_clauses_0_0= ruleClause ) )* ( (lv_query_1_0= ruleQuery ) )? )
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:86:2: ( (lv_clauses_0_0= ruleClause ) )* ( (lv_query_1_0= ruleQuery ) )?
				{
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:86:2: ( (lv_clauses_0_0= ruleClause ) )*
					loop1: do {
						int alt1 = 2;
						int LA1_0 = input.LA(1);

						if (LA1_0 >= RULE_EXPRESSION_1100 && LA1_0 <= RULE_VARIABLE || LA1_0 == 18 || LA1_0 == 20
								|| LA1_0 == 22) {
							alt1 = 1;
						}

						switch (alt1) {
						case 1:
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:87:1: (lv_clauses_0_0= ruleClause )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:87:1: (lv_clauses_0_0= ruleClause )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:88:3: lv_clauses_0_0= ruleClause
							{
								if (state.backtracking == 0) {

									newCompositeNode(grammarAccess.getProgramAccess()
											.getClausesClauseParserRuleCall_0_0());

								}
								pushFollow(FOLLOW_ruleClause_in_ruleProgram137);
								lv_clauses_0_0 = ruleClause();

								state._fsp--;
								if (state.failed) {
									return current;
								}
								if (state.backtracking == 0) {

									if (current == null) {
										current = createModelElementForParent(grammarAccess.getProgramRule());
									}
									add(current, "clauses", lv_clauses_0_0, "Clause");
									afterParserOrEnumRuleCall();

								}

							}

						}
							break;

						default:
							break loop1;
						}
					} while (true);

					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:104:3: ( (lv_query_1_0= ruleQuery ) )?
					int alt2 = 2;
					int LA2_0 = input.LA(1);

					if (LA2_0 == 19) {
						alt2 = 1;
					}
					switch (alt2) {
					case 1:
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:105:1: (lv_query_1_0= ruleQuery )
					{
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:105:1: (lv_query_1_0= ruleQuery )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:106:3: lv_query_1_0= ruleQuery
						{
							if (state.backtracking == 0) {

								newCompositeNode(grammarAccess.getProgramAccess().getQueryQueryParserRuleCall_1_0());

							}
							pushFollow(FOLLOW_ruleQuery_in_ruleProgram159);
							lv_query_1_0 = ruleQuery();

							state._fsp--;
							if (state.failed) {
								return current;
							}
							if (state.backtracking == 0) {

								if (current == null) {
									current = createModelElementForParent(grammarAccess.getProgramRule());
								}
								set(current, "query", lv_query_1_0, "Query");
								afterParserOrEnumRuleCall();

							}

						}

					}
						break;

					}

				}

			}

			if (state.backtracking == 0) {
				leaveRule();
			}
		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "ruleProgram"

	// $ANTLR start "entryRuleClause"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:130:1: entryRuleClause returns [EObject current=null] : iv_ruleClause= ruleClause EOF ;
	public final EObject entryRuleClause() throws RecognitionException {
		EObject current = null;

		EObject iv_ruleClause = null;

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:131:2: (iv_ruleClause= ruleClause EOF )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:132:2: iv_ruleClause= ruleClause EOF
			{
				if (state.backtracking == 0) {
					newCompositeNode(grammarAccess.getClauseRule());
				}
				pushFollow(FOLLOW_ruleClause_in_entryRuleClause196);
				iv_ruleClause = ruleClause();

				state._fsp--;
				if (state.failed) {
					return current;
				}
				if (state.backtracking == 0) {
					current = iv_ruleClause;
				}
				match(input, EOF, FOLLOW_EOF_in_entryRuleClause206);
				if (state.failed) {
					return current;
				}

			}

		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "entryRuleClause"

	// $ANTLR start "ruleClause"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:139:1: ruleClause returns [EObject current=null] : ( ( (lv_predicates_0_0= ruleExpressionINF ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )* )? otherlv_5= '.' ) ;
	public final EObject ruleClause() throws RecognitionException {
		EObject current = null;

		Token otherlv_1 = null;
		Token otherlv_3 = null;
		Token otherlv_5 = null;
		EObject lv_predicates_0_0 = null;

		EObject lv_predicates_2_0 = null;

		EObject lv_predicates_4_0 = null;

		enterRule();

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:142:28: ( ( ( (lv_predicates_0_0= ruleExpressionINF ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )* )? otherlv_5= '.' ) )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:143:1: ( ( (lv_predicates_0_0= ruleExpressionINF ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )* )? otherlv_5= '.' )
			{
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:143:1: ( ( (lv_predicates_0_0= ruleExpressionINF ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )* )? otherlv_5= '.' )
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:143:2: ( (lv_predicates_0_0= ruleExpressionINF ) ) (otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )* )? otherlv_5= '.'
				{
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:143:2: ( (lv_predicates_0_0= ruleExpressionINF ) )
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:144:1: (lv_predicates_0_0= ruleExpressionINF )
					{
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:144:1: (lv_predicates_0_0= ruleExpressionINF )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:145:3: lv_predicates_0_0= ruleExpressionINF
						{
							if (state.backtracking == 0) {

								newCompositeNode(grammarAccess.getClauseAccess()
										.getPredicatesExpressionINFParserRuleCall_0_0());

							}
							pushFollow(FOLLOW_ruleExpressionINF_in_ruleClause252);
							lv_predicates_0_0 = ruleExpressionINF();

							state._fsp--;
							if (state.failed) {
								return current;
							}
							if (state.backtracking == 0) {

								if (current == null) {
									current = createModelElementForParent(grammarAccess.getClauseRule());
								}
								add(current, "predicates", lv_predicates_0_0, "ExpressionINF");
								afterParserOrEnumRuleCall();

							}

						}

					}

					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:161:2: (otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )* )?
					int alt4 = 2;
					int LA4_0 = input.LA(1);

					if (LA4_0 == 16) {
						alt4 = 1;
					}
					switch (alt4) {
					case 1:
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:161:4: otherlv_1= ':-' ( (lv_predicates_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )*
					{
						otherlv_1 = (Token) match(input, 16, FOLLOW_16_in_ruleClause265);
						if (state.failed) {
							return current;
						}
						if (state.backtracking == 0) {

							newLeafNode(otherlv_1, grammarAccess.getClauseAccess().getColonHyphenMinusKeyword_1_0());

						}
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:165:1: ( (lv_predicates_2_0= ruleExpressionINF ) )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:166:1: (lv_predicates_2_0= ruleExpressionINF )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:166:1: (lv_predicates_2_0= ruleExpressionINF )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:167:3: lv_predicates_2_0= ruleExpressionINF
							{
								if (state.backtracking == 0) {

									newCompositeNode(grammarAccess.getClauseAccess()
											.getPredicatesExpressionINFParserRuleCall_1_1_0());

								}
								pushFollow(FOLLOW_ruleExpressionINF_in_ruleClause286);
								lv_predicates_2_0 = ruleExpressionINF();

								state._fsp--;
								if (state.failed) {
									return current;
								}
								if (state.backtracking == 0) {

									if (current == null) {
										current = createModelElementForParent(grammarAccess.getClauseRule());
									}
									add(current, "predicates", lv_predicates_2_0, "ExpressionINF");
									afterParserOrEnumRuleCall();

								}

							}

						}

						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:183:2: (otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) ) )*
						loop3: do {
							int alt3 = 2;
							int LA3_0 = input.LA(1);

							if (LA3_0 == 17) {
								alt3 = 1;
							}

							switch (alt3) {
							case 1:
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:183:4: otherlv_3= ',' ( (lv_predicates_4_0= ruleExpressionINF ) )
							{
								otherlv_3 = (Token) match(input, 17, FOLLOW_17_in_ruleClause299);
								if (state.failed) {
									return current;
								}
								if (state.backtracking == 0) {

									newLeafNode(otherlv_3, grammarAccess.getClauseAccess().getCommaKeyword_1_2_0());

								}
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:187:1: ( (lv_predicates_4_0= ruleExpressionINF ) )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:188:1: (lv_predicates_4_0= ruleExpressionINF )
								{
									// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:188:1: (lv_predicates_4_0= ruleExpressionINF )
									// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:189:3: lv_predicates_4_0= ruleExpressionINF
									{
										if (state.backtracking == 0) {

											newCompositeNode(grammarAccess.getClauseAccess()
													.getPredicatesExpressionINFParserRuleCall_1_2_1_0());

										}
										pushFollow(FOLLOW_ruleExpressionINF_in_ruleClause320);
										lv_predicates_4_0 = ruleExpressionINF();

										state._fsp--;
										if (state.failed) {
											return current;
										}
										if (state.backtracking == 0) {

											if (current == null) {
												current = createModelElementForParent(grammarAccess.getClauseRule());
											}
											add(current, "predicates", lv_predicates_4_0, "ExpressionINF");
											afterParserOrEnumRuleCall();

										}

									}

								}

							}
								break;

							default:
								break loop3;
							}
						} while (true);

					}
						break;

					}

					otherlv_5 = (Token) match(input, 18, FOLLOW_18_in_ruleClause336);
					if (state.failed) {
						return current;
					}
					if (state.backtracking == 0) {

						newLeafNode(otherlv_5, grammarAccess.getClauseAccess().getFullStopKeyword_2());

					}

				}

			}

			if (state.backtracking == 0) {
				leaveRule();
			}
		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "ruleClause"

	// $ANTLR start "entryRuleQuery"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:217:1: entryRuleQuery returns [EObject current=null] : iv_ruleQuery= ruleQuery EOF ;
	public final EObject entryRuleQuery() throws RecognitionException {
		EObject current = null;

		EObject iv_ruleQuery = null;

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:218:2: (iv_ruleQuery= ruleQuery EOF )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:219:2: iv_ruleQuery= ruleQuery EOF
			{
				if (state.backtracking == 0) {
					newCompositeNode(grammarAccess.getQueryRule());
				}
				pushFollow(FOLLOW_ruleQuery_in_entryRuleQuery372);
				iv_ruleQuery = ruleQuery();

				state._fsp--;
				if (state.failed) {
					return current;
				}
				if (state.backtracking == 0) {
					current = iv_ruleQuery;
				}
				match(input, EOF, FOLLOW_EOF_in_entryRuleQuery382);
				if (state.failed) {
					return current;
				}

			}

		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "entryRuleQuery"

	// $ANTLR start "ruleQuery"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:226:1: ruleQuery returns [EObject current=null] : (otherlv_0= '?-' ( (lv_predicates_1_0= ruleExpressionINF ) ) (otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) ) )* otherlv_4= '.' ) ;
	public final EObject ruleQuery() throws RecognitionException {
		EObject current = null;

		Token otherlv_0 = null;
		Token otherlv_2 = null;
		Token otherlv_4 = null;
		EObject lv_predicates_1_0 = null;

		EObject lv_predicates_3_0 = null;

		enterRule();

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:229:28: ( (otherlv_0= '?-' ( (lv_predicates_1_0= ruleExpressionINF ) ) (otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) ) )* otherlv_4= '.' ) )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:230:1: (otherlv_0= '?-' ( (lv_predicates_1_0= ruleExpressionINF ) ) (otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) ) )* otherlv_4= '.' )
			{
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:230:1: (otherlv_0= '?-' ( (lv_predicates_1_0= ruleExpressionINF ) ) (otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) ) )* otherlv_4= '.' )
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:230:3: otherlv_0= '?-' ( (lv_predicates_1_0= ruleExpressionINF ) ) (otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) ) )* otherlv_4= '.'
				{
					otherlv_0 = (Token) match(input, 19, FOLLOW_19_in_ruleQuery419);
					if (state.failed) {
						return current;
					}
					if (state.backtracking == 0) {

						newLeafNode(otherlv_0, grammarAccess.getQueryAccess().getQuestionMarkHyphenMinusKeyword_0());

					}
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:234:1: ( (lv_predicates_1_0= ruleExpressionINF ) )
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:235:1: (lv_predicates_1_0= ruleExpressionINF )
					{
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:235:1: (lv_predicates_1_0= ruleExpressionINF )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:236:3: lv_predicates_1_0= ruleExpressionINF
						{
							if (state.backtracking == 0) {

								newCompositeNode(grammarAccess.getQueryAccess()
										.getPredicatesExpressionINFParserRuleCall_1_0());

							}
							pushFollow(FOLLOW_ruleExpressionINF_in_ruleQuery440);
							lv_predicates_1_0 = ruleExpressionINF();

							state._fsp--;
							if (state.failed) {
								return current;
							}
							if (state.backtracking == 0) {

								if (current == null) {
									current = createModelElementForParent(grammarAccess.getQueryRule());
								}
								add(current, "predicates", lv_predicates_1_0, "ExpressionINF");
								afterParserOrEnumRuleCall();

							}

						}

					}

					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:252:2: (otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) ) )*
					loop5: do {
						int alt5 = 2;
						int LA5_0 = input.LA(1);

						if (LA5_0 == 17) {
							alt5 = 1;
						}

						switch (alt5) {
						case 1:
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:252:4: otherlv_2= ',' ( (lv_predicates_3_0= ruleExpressionINF ) )
						{
							otherlv_2 = (Token) match(input, 17, FOLLOW_17_in_ruleQuery453);
							if (state.failed) {
								return current;
							}
							if (state.backtracking == 0) {

								newLeafNode(otherlv_2, grammarAccess.getQueryAccess().getCommaKeyword_2_0());

							}
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:256:1: ( (lv_predicates_3_0= ruleExpressionINF ) )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:257:1: (lv_predicates_3_0= ruleExpressionINF )
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:257:1: (lv_predicates_3_0= ruleExpressionINF )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:258:3: lv_predicates_3_0= ruleExpressionINF
								{
									if (state.backtracking == 0) {

										newCompositeNode(grammarAccess.getQueryAccess()
												.getPredicatesExpressionINFParserRuleCall_2_1_0());

									}
									pushFollow(FOLLOW_ruleExpressionINF_in_ruleQuery474);
									lv_predicates_3_0 = ruleExpressionINF();

									state._fsp--;
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElementForParent(grammarAccess.getQueryRule());
										}
										add(current, "predicates", lv_predicates_3_0, "ExpressionINF");
										afterParserOrEnumRuleCall();

									}

								}

							}

						}
							break;

						default:
							break loop5;
						}
					} while (true);

					otherlv_4 = (Token) match(input, 18, FOLLOW_18_in_ruleQuery488);
					if (state.failed) {
						return current;
					}
					if (state.backtracking == 0) {

						newLeafNode(otherlv_4, grammarAccess.getQueryAccess().getFullStopKeyword_3());

					}

				}

			}

			if (state.backtracking == 0) {
				leaveRule();
			}
		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "ruleQuery"

	// $ANTLR start "entryRuleExpressionINF"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:286:1: entryRuleExpressionINF returns [EObject current=null] : iv_ruleExpressionINF= ruleExpressionINF EOF ;
	public final EObject entryRuleExpressionINF() throws RecognitionException {
		EObject current = null;

		EObject iv_ruleExpressionINF = null;

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:287:2: (iv_ruleExpressionINF= ruleExpressionINF EOF )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:288:2: iv_ruleExpressionINF= ruleExpressionINF EOF
			{
				if (state.backtracking == 0) {
					newCompositeNode(grammarAccess.getExpressionINFRule());
				}
				pushFollow(FOLLOW_ruleExpressionINF_in_entryRuleExpressionINF524);
				iv_ruleExpressionINF = ruleExpressionINF();

				state._fsp--;
				if (state.failed) {
					return current;
				}
				if (state.backtracking == 0) {
					current = iv_ruleExpressionINF;
				}
				match(input, EOF, FOLLOW_EOF_in_entryRuleExpressionINF534);
				if (state.failed) {
					return current;
				}

			}

		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "entryRuleExpressionINF"

	// $ANTLR start "ruleExpressionINF"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:295:1: ruleExpressionINF returns [EObject current=null] : this_Expression1100_0= ruleExpression1100 ;
	public final EObject ruleExpressionINF() throws RecognitionException {
		EObject current = null;

		EObject this_Expression1100_0 = null;

		enterRule();

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:298:28: (this_Expression1100_0= ruleExpression1100 )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:300:2: this_Expression1100_0= ruleExpression1100
			{
				if (state.backtracking == 0) {

					/* */

				}
				if (state.backtracking == 0) {

					newCompositeNode(grammarAccess.getExpressionINFAccess().getExpression1100ParserRuleCall());

				}
				pushFollow(FOLLOW_ruleExpression1100_in_ruleExpressionINF583);
				this_Expression1100_0 = ruleExpression1100();

				state._fsp--;
				if (state.failed) {
					return current;
				}
				if (state.backtracking == 0) {

					current = this_Expression1100_0;
					afterParserOrEnumRuleCall();

				}

			}

			if (state.backtracking == 0) {
				leaveRule();
			}
		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "ruleExpressionINF"

	// $ANTLR start "entryRuleExpression1100"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:319:1: entryRuleExpression1100 returns [EObject current=null] : iv_ruleExpression1100= ruleExpression1100 EOF ;
	public final EObject entryRuleExpression1100() throws RecognitionException {
		EObject current = null;

		EObject iv_ruleExpression1100 = null;

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:320:2: (iv_ruleExpression1100= ruleExpression1100 EOF )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:321:2: iv_ruleExpression1100= ruleExpression1100 EOF
			{
				if (state.backtracking == 0) {
					newCompositeNode(grammarAccess.getExpression1100Rule());
				}
				pushFollow(FOLLOW_ruleExpression1100_in_entryRuleExpression1100617);
				iv_ruleExpression1100 = ruleExpression1100();

				state._fsp--;
				if (state.failed) {
					return current;
				}
				if (state.backtracking == 0) {
					current = iv_ruleExpression1100;
				}
				match(input, EOF, FOLLOW_EOF_in_entryRuleExpression1100627);
				if (state.failed) {
					return current;
				}

			}

		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "entryRuleExpression1100"

	// $ANTLR start "ruleExpression1100"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:328:1: ruleExpression1100 returns [EObject current=null] : (this_Expression900_0= ruleExpression900 ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) ) )* ) ;
	public final EObject ruleExpression1100() throws RecognitionException {
		EObject current = null;

		Token lv_ops_1_0 = null;
		EObject this_Expression900_0 = null;

		EObject lv_exps_2_0 = null;

		enterRule();

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:331:28: ( (this_Expression900_0= ruleExpression900 ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) ) )* ) )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:332:1: (this_Expression900_0= ruleExpression900 ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) ) )* )
			{
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:332:1: (this_Expression900_0= ruleExpression900 ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) ) )* )
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:333:2: this_Expression900_0= ruleExpression900 ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) ) )*
				{
					if (state.backtracking == 0) {

						/* */

					}
					if (state.backtracking == 0) {

						newCompositeNode(grammarAccess.getExpression1100Access().getExpression900ParserRuleCall_0());

					}
					pushFollow(FOLLOW_ruleExpression900_in_ruleExpression1100677);
					this_Expression900_0 = ruleExpression900();

					state._fsp--;
					if (state.failed) {
						return current;
					}
					if (state.backtracking == 0) {

						current = this_Expression900_0;
						afterParserOrEnumRuleCall();

					}
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:344:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) ) )*
					loop6: do {
						int alt6 = 2;
						int LA6_0 = input.LA(1);

						if (LA6_0 == RULE_EXPRESSION_1100) {
							alt6 = 1;
						}

						switch (alt6) {
						case 1:
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:344:2: ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) ) ( (lv_exps_2_0= ruleExpression900 ) )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:344:2: ( (lv_ops_1_0= RULE_EXPRESSION_1100 ) )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:345:1: (lv_ops_1_0= RULE_EXPRESSION_1100 )
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:345:1: (lv_ops_1_0= RULE_EXPRESSION_1100 )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:346:3: lv_ops_1_0= RULE_EXPRESSION_1100
								{
									lv_ops_1_0 = (Token) match(input, RULE_EXPRESSION_1100,
											FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression1100694);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(lv_ops_1_0, grammarAccess.getExpression1100Access()
												.getOpsEXPRESSION_1100TerminalRuleCall_1_0_0());

									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElement(grammarAccess.getExpression1100Rule());
										}
										addWithLastConsumed(current, "ops", lv_ops_1_0, "EXPRESSION_1100");

									}

								}

							}

							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:362:2: ( (lv_exps_2_0= ruleExpression900 ) )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:363:1: (lv_exps_2_0= ruleExpression900 )
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:363:1: (lv_exps_2_0= ruleExpression900 )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:364:3: lv_exps_2_0= ruleExpression900
								{
									if (state.backtracking == 0) {

										newCompositeNode(grammarAccess.getExpression1100Access()
												.getExpsExpression900ParserRuleCall_1_1_0());

									}
									pushFollow(FOLLOW_ruleExpression900_in_ruleExpression1100720);
									lv_exps_2_0 = ruleExpression900();

									state._fsp--;
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElementForParent(grammarAccess.getExpression1100Rule());
										}
										add(current, "exps", lv_exps_2_0, "Expression900");
										afterParserOrEnumRuleCall();

									}

								}

							}

						}
							break;

						default:
							break loop6;
						}
					} while (true);

				}

			}

			if (state.backtracking == 0) {
				leaveRule();
			}
		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "ruleExpression1100"

	// $ANTLR start "entryRuleExpression900"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:388:1: entryRuleExpression900 returns [EObject current=null] : iv_ruleExpression900= ruleExpression900 EOF ;
	public final EObject entryRuleExpression900() throws RecognitionException {
		EObject current = null;

		EObject iv_ruleExpression900 = null;

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:389:2: (iv_ruleExpression900= ruleExpression900 EOF )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:390:2: iv_ruleExpression900= ruleExpression900 EOF
			{
				if (state.backtracking == 0) {
					newCompositeNode(grammarAccess.getExpression900Rule());
				}
				pushFollow(FOLLOW_ruleExpression900_in_entryRuleExpression900758);
				iv_ruleExpression900 = ruleExpression900();

				state._fsp--;
				if (state.failed) {
					return current;
				}
				if (state.backtracking == 0) {
					current = iv_ruleExpression900;
				}
				match(input, EOF, FOLLOW_EOF_in_entryRuleExpression900768);
				if (state.failed) {
					return current;
				}

			}

		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "entryRuleExpression900"

	// $ANTLR start "ruleExpression900"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:397:1: ruleExpression900 returns [EObject current=null] : ( ( (lv_ops_0_0= RULE_EXPRESSION_900 ) )? ( (lv_exps_1_0= ruleExpression700 ) ) ) ;
	public final EObject ruleExpression900() throws RecognitionException {
		EObject current = null;

		Token lv_ops_0_0 = null;
		EObject lv_exps_1_0 = null;

		enterRule();

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:400:28: ( ( ( (lv_ops_0_0= RULE_EXPRESSION_900 ) )? ( (lv_exps_1_0= ruleExpression700 ) ) ) )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:401:1: ( ( (lv_ops_0_0= RULE_EXPRESSION_900 ) )? ( (lv_exps_1_0= ruleExpression700 ) ) )
			{
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:401:1: ( ( (lv_ops_0_0= RULE_EXPRESSION_900 ) )? ( (lv_exps_1_0= ruleExpression700 ) ) )
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:401:2: ( (lv_ops_0_0= RULE_EXPRESSION_900 ) )? ( (lv_exps_1_0= ruleExpression700 ) )
				{
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:401:2: ( (lv_ops_0_0= RULE_EXPRESSION_900 ) )?
					int alt7 = 2;
					alt7 = dfa7.predict(input);
					switch (alt7) {
					case 1:
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:402:1: (lv_ops_0_0= RULE_EXPRESSION_900 )
					{
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:402:1: (lv_ops_0_0= RULE_EXPRESSION_900 )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:403:3: lv_ops_0_0= RULE_EXPRESSION_900
						{
							lv_ops_0_0 = (Token) match(input, RULE_EXPRESSION_900,
									FOLLOW_RULE_EXPRESSION_900_in_ruleExpression900810);
							if (state.failed) {
								return current;
							}
							if (state.backtracking == 0) {

								newLeafNode(lv_ops_0_0, grammarAccess.getExpression900Access()
										.getOpsEXPRESSION_900TerminalRuleCall_0_0());

							}
							if (state.backtracking == 0) {

								if (current == null) {
									current = createModelElement(grammarAccess.getExpression900Rule());
								}
								addWithLastConsumed(current, "ops", lv_ops_0_0, "EXPRESSION_900");

							}

						}

					}
						break;

					}

					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:419:3: ( (lv_exps_1_0= ruleExpression700 ) )
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:420:1: (lv_exps_1_0= ruleExpression700 )
					{
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:420:1: (lv_exps_1_0= ruleExpression700 )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:421:3: lv_exps_1_0= ruleExpression700
						{
							if (state.backtracking == 0) {

								newCompositeNode(grammarAccess.getExpression900Access()
										.getExpsExpression700ParserRuleCall_1_0());

							}
							pushFollow(FOLLOW_ruleExpression700_in_ruleExpression900837);
							lv_exps_1_0 = ruleExpression700();

							state._fsp--;
							if (state.failed) {
								return current;
							}
							if (state.backtracking == 0) {

								if (current == null) {
									current = createModelElementForParent(grammarAccess.getExpression900Rule());
								}
								add(current, "exps", lv_exps_1_0, "Expression700");
								afterParserOrEnumRuleCall();

							}

						}

					}

				}

			}

			if (state.backtracking == 0) {
				leaveRule();
			}
		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "ruleExpression900"

	// $ANTLR start "entryRuleExpression700"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:445:1: entryRuleExpression700 returns [EObject current=null] : iv_ruleExpression700= ruleExpression700 EOF ;
	public final EObject entryRuleExpression700() throws RecognitionException {
		EObject current = null;

		EObject iv_ruleExpression700 = null;

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:446:2: (iv_ruleExpression700= ruleExpression700 EOF )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:447:2: iv_ruleExpression700= ruleExpression700 EOF
			{
				if (state.backtracking == 0) {
					newCompositeNode(grammarAccess.getExpression700Rule());
				}
				pushFollow(FOLLOW_ruleExpression700_in_entryRuleExpression700873);
				iv_ruleExpression700 = ruleExpression700();

				state._fsp--;
				if (state.failed) {
					return current;
				}
				if (state.backtracking == 0) {
					current = iv_ruleExpression700;
				}
				match(input, EOF, FOLLOW_EOF_in_entryRuleExpression700883);
				if (state.failed) {
					return current;
				}

			}

		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "entryRuleExpression700"

	// $ANTLR start "ruleExpression700"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:454:1: ruleExpression700 returns [EObject current=null] : (this_Expression500_0= ruleExpression500 ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? ) ;
	public final EObject ruleExpression700() throws RecognitionException {
		EObject current = null;

		Token lv_ops_1_0 = null;
		EObject this_Expression500_0 = null;

		EObject lv_exps_2_0 = null;

		enterRule();

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:457:28: ( (this_Expression500_0= ruleExpression500 ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? ) )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:458:1: (this_Expression500_0= ruleExpression500 ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? )
			{
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:458:1: (this_Expression500_0= ruleExpression500 ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )? )
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:459:2: this_Expression500_0= ruleExpression500 ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )?
				{
					if (state.backtracking == 0) {

						/* */

					}
					if (state.backtracking == 0) {

						newCompositeNode(grammarAccess.getExpression700Access().getExpression500ParserRuleCall_0());

					}
					pushFollow(FOLLOW_ruleExpression500_in_ruleExpression700933);
					this_Expression500_0 = ruleExpression500();

					state._fsp--;
					if (state.failed) {
						return current;
					}
					if (state.backtracking == 0) {

						current = this_Expression500_0;
						afterParserOrEnumRuleCall();

					}
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:470:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) ) )?
					int alt8 = 2;
					int LA8_0 = input.LA(1);

					if (LA8_0 == RULE_EXPRESSION_700) {
						alt8 = 1;
					}
					switch (alt8) {
					case 1:
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:470:2: ( (lv_ops_1_0= RULE_EXPRESSION_700 ) ) ( (lv_exps_2_0= ruleExpression500 ) )
					{
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:470:2: ( (lv_ops_1_0= RULE_EXPRESSION_700 ) )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:471:1: (lv_ops_1_0= RULE_EXPRESSION_700 )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:471:1: (lv_ops_1_0= RULE_EXPRESSION_700 )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:472:3: lv_ops_1_0= RULE_EXPRESSION_700
							{
								lv_ops_1_0 = (Token) match(input, RULE_EXPRESSION_700,
										FOLLOW_RULE_EXPRESSION_700_in_ruleExpression700950);
								if (state.failed) {
									return current;
								}
								if (state.backtracking == 0) {

									newLeafNode(lv_ops_1_0, grammarAccess.getExpression700Access()
											.getOpsEXPRESSION_700TerminalRuleCall_1_0_0());

								}
								if (state.backtracking == 0) {

									if (current == null) {
										current = createModelElement(grammarAccess.getExpression700Rule());
									}
									addWithLastConsumed(current, "ops", lv_ops_1_0, "EXPRESSION_700");

								}

							}

						}

						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:488:2: ( (lv_exps_2_0= ruleExpression500 ) )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:489:1: (lv_exps_2_0= ruleExpression500 )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:489:1: (lv_exps_2_0= ruleExpression500 )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:490:3: lv_exps_2_0= ruleExpression500
							{
								if (state.backtracking == 0) {

									newCompositeNode(grammarAccess.getExpression700Access()
											.getExpsExpression500ParserRuleCall_1_1_0());

								}
								pushFollow(FOLLOW_ruleExpression500_in_ruleExpression700976);
								lv_exps_2_0 = ruleExpression500();

								state._fsp--;
								if (state.failed) {
									return current;
								}
								if (state.backtracking == 0) {

									if (current == null) {
										current = createModelElementForParent(grammarAccess.getExpression700Rule());
									}
									add(current, "exps", lv_exps_2_0, "Expression500");
									afterParserOrEnumRuleCall();

								}

							}

						}

					}
						break;

					}

				}

			}

			if (state.backtracking == 0) {
				leaveRule();
			}
		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "ruleExpression700"

	// $ANTLR start "entryRuleExpression500"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:514:1: entryRuleExpression500 returns [EObject current=null] : iv_ruleExpression500= ruleExpression500 EOF ;
	public final EObject entryRuleExpression500() throws RecognitionException {
		EObject current = null;

		EObject iv_ruleExpression500 = null;

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:515:2: (iv_ruleExpression500= ruleExpression500 EOF )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:516:2: iv_ruleExpression500= ruleExpression500 EOF
			{
				if (state.backtracking == 0) {
					newCompositeNode(grammarAccess.getExpression500Rule());
				}
				pushFollow(FOLLOW_ruleExpression500_in_entryRuleExpression5001014);
				iv_ruleExpression500 = ruleExpression500();

				state._fsp--;
				if (state.failed) {
					return current;
				}
				if (state.backtracking == 0) {
					current = iv_ruleExpression500;
				}
				match(input, EOF, FOLLOW_EOF_in_entryRuleExpression5001024);
				if (state.failed) {
					return current;
				}

			}

		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "entryRuleExpression500"

	// $ANTLR start "ruleExpression500"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:523:1: ruleExpression500 returns [EObject current=null] : (this_Expression400_0= ruleExpression400 ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )* ) ;
	public final EObject ruleExpression500() throws RecognitionException {
		EObject current = null;

		Token lv_ops_1_0 = null;
		EObject this_Expression400_0 = null;

		EObject lv_exps_2_0 = null;

		enterRule();

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:526:28: ( (this_Expression400_0= ruleExpression400 ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )* ) )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:527:1: (this_Expression400_0= ruleExpression400 ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )* )
			{
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:527:1: (this_Expression400_0= ruleExpression400 ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )* )
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:528:2: this_Expression400_0= ruleExpression400 ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )*
				{
					if (state.backtracking == 0) {

						/* */

					}
					if (state.backtracking == 0) {

						newCompositeNode(grammarAccess.getExpression500Access().getExpression400ParserRuleCall_0());

					}
					pushFollow(FOLLOW_ruleExpression400_in_ruleExpression5001074);
					this_Expression400_0 = ruleExpression400();

					state._fsp--;
					if (state.failed) {
						return current;
					}
					if (state.backtracking == 0) {

						current = this_Expression400_0;
						afterParserOrEnumRuleCall();

					}
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:539:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) ) )*
					loop9: do {
						int alt9 = 2;
						int LA9_0 = input.LA(1);

						if (LA9_0 == RULE_EXPRESSION_500) {
							alt9 = 1;
						}

						switch (alt9) {
						case 1:
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:539:2: ( (lv_ops_1_0= RULE_EXPRESSION_500 ) ) ( (lv_exps_2_0= ruleExpression400 ) )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:539:2: ( (lv_ops_1_0= RULE_EXPRESSION_500 ) )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:540:1: (lv_ops_1_0= RULE_EXPRESSION_500 )
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:540:1: (lv_ops_1_0= RULE_EXPRESSION_500 )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:541:3: lv_ops_1_0= RULE_EXPRESSION_500
								{
									lv_ops_1_0 = (Token) match(input, RULE_EXPRESSION_500,
											FOLLOW_RULE_EXPRESSION_500_in_ruleExpression5001091);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(lv_ops_1_0, grammarAccess.getExpression500Access()
												.getOpsEXPRESSION_500TerminalRuleCall_1_0_0());

									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElement(grammarAccess.getExpression500Rule());
										}
										addWithLastConsumed(current, "ops", lv_ops_1_0, "EXPRESSION_500");

									}

								}

							}

							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:557:2: ( (lv_exps_2_0= ruleExpression400 ) )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:558:1: (lv_exps_2_0= ruleExpression400 )
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:558:1: (lv_exps_2_0= ruleExpression400 )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:559:3: lv_exps_2_0= ruleExpression400
								{
									if (state.backtracking == 0) {

										newCompositeNode(grammarAccess.getExpression500Access()
												.getExpsExpression400ParserRuleCall_1_1_0());

									}
									pushFollow(FOLLOW_ruleExpression400_in_ruleExpression5001117);
									lv_exps_2_0 = ruleExpression400();

									state._fsp--;
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElementForParent(grammarAccess.getExpression500Rule());
										}
										add(current, "exps", lv_exps_2_0, "Expression400");
										afterParserOrEnumRuleCall();

									}

								}

							}

						}
							break;

						default:
							break loop9;
						}
					} while (true);

				}

			}

			if (state.backtracking == 0) {
				leaveRule();
			}
		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "ruleExpression500"

	// $ANTLR start "entryRuleExpression400"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:583:1: entryRuleExpression400 returns [EObject current=null] : iv_ruleExpression400= ruleExpression400 EOF ;
	public final EObject entryRuleExpression400() throws RecognitionException {
		EObject current = null;

		EObject iv_ruleExpression400 = null;

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:584:2: (iv_ruleExpression400= ruleExpression400 EOF )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:585:2: iv_ruleExpression400= ruleExpression400 EOF
			{
				if (state.backtracking == 0) {
					newCompositeNode(grammarAccess.getExpression400Rule());
				}
				pushFollow(FOLLOW_ruleExpression400_in_entryRuleExpression4001155);
				iv_ruleExpression400 = ruleExpression400();

				state._fsp--;
				if (state.failed) {
					return current;
				}
				if (state.backtracking == 0) {
					current = iv_ruleExpression400;
				}
				match(input, EOF, FOLLOW_EOF_in_entryRuleExpression4001165);
				if (state.failed) {
					return current;
				}

			}

		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "entryRuleExpression400"

	// $ANTLR start "ruleExpression400"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:592:1: ruleExpression400 returns [EObject current=null] : (this_Expression200_0= ruleExpression200 ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )* ) ;
	public final EObject ruleExpression400() throws RecognitionException {
		EObject current = null;

		Token lv_ops_1_0 = null;
		EObject this_Expression200_0 = null;

		EObject lv_exps_2_0 = null;

		enterRule();

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:595:28: ( (this_Expression200_0= ruleExpression200 ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )* ) )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:596:1: (this_Expression200_0= ruleExpression200 ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )* )
			{
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:596:1: (this_Expression200_0= ruleExpression200 ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )* )
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:597:2: this_Expression200_0= ruleExpression200 ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )*
				{
					if (state.backtracking == 0) {

						/* */

					}
					if (state.backtracking == 0) {

						newCompositeNode(grammarAccess.getExpression400Access().getExpression200ParserRuleCall_0());

					}
					pushFollow(FOLLOW_ruleExpression200_in_ruleExpression4001215);
					this_Expression200_0 = ruleExpression200();

					state._fsp--;
					if (state.failed) {
						return current;
					}
					if (state.backtracking == 0) {

						current = this_Expression200_0;
						afterParserOrEnumRuleCall();

					}
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:608:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) ) )*
					loop10: do {
						int alt10 = 2;
						int LA10_0 = input.LA(1);

						if (LA10_0 == RULE_EXPRESSION_400) {
							alt10 = 1;
						}

						switch (alt10) {
						case 1:
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:608:2: ( (lv_ops_1_0= RULE_EXPRESSION_400 ) ) ( (lv_exps_2_0= ruleExpression200 ) )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:608:2: ( (lv_ops_1_0= RULE_EXPRESSION_400 ) )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:609:1: (lv_ops_1_0= RULE_EXPRESSION_400 )
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:609:1: (lv_ops_1_0= RULE_EXPRESSION_400 )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:610:3: lv_ops_1_0= RULE_EXPRESSION_400
								{
									lv_ops_1_0 = (Token) match(input, RULE_EXPRESSION_400,
											FOLLOW_RULE_EXPRESSION_400_in_ruleExpression4001232);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(lv_ops_1_0, grammarAccess.getExpression400Access()
												.getOpsEXPRESSION_400TerminalRuleCall_1_0_0());

									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElement(grammarAccess.getExpression400Rule());
										}
										addWithLastConsumed(current, "ops", lv_ops_1_0, "EXPRESSION_400");

									}

								}

							}

							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:626:2: ( (lv_exps_2_0= ruleExpression200 ) )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:627:1: (lv_exps_2_0= ruleExpression200 )
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:627:1: (lv_exps_2_0= ruleExpression200 )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:628:3: lv_exps_2_0= ruleExpression200
								{
									if (state.backtracking == 0) {

										newCompositeNode(grammarAccess.getExpression400Access()
												.getExpsExpression200ParserRuleCall_1_1_0());

									}
									pushFollow(FOLLOW_ruleExpression200_in_ruleExpression4001258);
									lv_exps_2_0 = ruleExpression200();

									state._fsp--;
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElementForParent(grammarAccess.getExpression400Rule());
										}
										add(current, "exps", lv_exps_2_0, "Expression200");
										afterParserOrEnumRuleCall();

									}

								}

							}

						}
							break;

						default:
							break loop10;
						}
					} while (true);

				}

			}

			if (state.backtracking == 0) {
				leaveRule();
			}
		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "ruleExpression400"

	// $ANTLR start "entryRuleExpression200"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:652:1: entryRuleExpression200 returns [EObject current=null] : iv_ruleExpression200= ruleExpression200 EOF ;
	public final EObject entryRuleExpression200() throws RecognitionException {
		EObject current = null;

		EObject iv_ruleExpression200 = null;

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:653:2: (iv_ruleExpression200= ruleExpression200 EOF )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:654:2: iv_ruleExpression200= ruleExpression200 EOF
			{
				if (state.backtracking == 0) {
					newCompositeNode(grammarAccess.getExpression200Rule());
				}
				pushFollow(FOLLOW_ruleExpression200_in_entryRuleExpression2001296);
				iv_ruleExpression200 = ruleExpression200();

				state._fsp--;
				if (state.failed) {
					return current;
				}
				if (state.backtracking == 0) {
					current = iv_ruleExpression200;
				}
				match(input, EOF, FOLLOW_EOF_in_entryRuleExpression2001306);
				if (state.failed) {
					return current;
				}

			}

		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "entryRuleExpression200"

	// $ANTLR start "ruleExpression200"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:661:1: ruleExpression200 returns [EObject current=null] : (this_Expression0_0= ruleExpression0 ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )* ) ;
	public final EObject ruleExpression200() throws RecognitionException {
		EObject current = null;

		Token lv_ops_1_0 = null;
		EObject this_Expression0_0 = null;

		EObject lv_exps_2_0 = null;

		enterRule();

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:664:28: ( (this_Expression0_0= ruleExpression0 ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )* ) )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:665:1: (this_Expression0_0= ruleExpression0 ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )* )
			{
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:665:1: (this_Expression0_0= ruleExpression0 ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )* )
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:666:2: this_Expression0_0= ruleExpression0 ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )*
				{
					if (state.backtracking == 0) {

						/* */

					}
					if (state.backtracking == 0) {

						newCompositeNode(grammarAccess.getExpression200Access().getExpression0ParserRuleCall_0());

					}
					pushFollow(FOLLOW_ruleExpression0_in_ruleExpression2001356);
					this_Expression0_0 = ruleExpression0();

					state._fsp--;
					if (state.failed) {
						return current;
					}
					if (state.backtracking == 0) {

						current = this_Expression0_0;
						afterParserOrEnumRuleCall();

					}
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:677:1: ( ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) ) )*
					loop11: do {
						int alt11 = 2;
						int LA11_0 = input.LA(1);

						if (LA11_0 == RULE_EXPRESSION_200) {
							alt11 = 1;
						}

						switch (alt11) {
						case 1:
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:677:2: ( (lv_ops_1_0= RULE_EXPRESSION_200 ) ) ( (lv_exps_2_0= ruleExpression0 ) )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:677:2: ( (lv_ops_1_0= RULE_EXPRESSION_200 ) )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:678:1: (lv_ops_1_0= RULE_EXPRESSION_200 )
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:678:1: (lv_ops_1_0= RULE_EXPRESSION_200 )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:679:3: lv_ops_1_0= RULE_EXPRESSION_200
								{
									lv_ops_1_0 = (Token) match(input, RULE_EXPRESSION_200,
											FOLLOW_RULE_EXPRESSION_200_in_ruleExpression2001373);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(lv_ops_1_0, grammarAccess.getExpression200Access()
												.getOpsEXPRESSION_200TerminalRuleCall_1_0_0());

									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElement(grammarAccess.getExpression200Rule());
										}
										addWithLastConsumed(current, "ops", lv_ops_1_0, "EXPRESSION_200");

									}

								}

							}

							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:695:2: ( (lv_exps_2_0= ruleExpression0 ) )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:696:1: (lv_exps_2_0= ruleExpression0 )
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:696:1: (lv_exps_2_0= ruleExpression0 )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:697:3: lv_exps_2_0= ruleExpression0
								{
									if (state.backtracking == 0) {

										newCompositeNode(grammarAccess.getExpression200Access()
												.getExpsExpression0ParserRuleCall_1_1_0());

									}
									pushFollow(FOLLOW_ruleExpression0_in_ruleExpression2001399);
									lv_exps_2_0 = ruleExpression0();

									state._fsp--;
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElementForParent(grammarAccess.getExpression200Rule());
										}
										add(current, "exps", lv_exps_2_0, "Expression0");
										afterParserOrEnumRuleCall();

									}

								}

							}

						}
							break;

						default:
							break loop11;
						}
					} while (true);

				}

			}

			if (state.backtracking == 0) {
				leaveRule();
			}
		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "ruleExpression200"

	// $ANTLR start "entryRuleExpression0"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:721:1: entryRuleExpression0 returns [EObject current=null] : iv_ruleExpression0= ruleExpression0 EOF ;
	public final EObject entryRuleExpression0() throws RecognitionException {
		EObject current = null;

		EObject iv_ruleExpression0 = null;

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:722:2: (iv_ruleExpression0= ruleExpression0 EOF )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:723:2: iv_ruleExpression0= ruleExpression0 EOF
			{
				if (state.backtracking == 0) {
					newCompositeNode(grammarAccess.getExpression0Rule());
				}
				pushFollow(FOLLOW_ruleExpression0_in_entryRuleExpression01437);
				iv_ruleExpression0 = ruleExpression0();

				state._fsp--;
				if (state.failed) {
					return current;
				}
				if (state.backtracking == 0) {
					current = iv_ruleExpression0;
				}
				match(input, EOF, FOLLOW_EOF_in_entryRuleExpression01447);
				if (state.failed) {
					return current;
				}

			}

		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "entryRuleExpression0"

	// $ANTLR start "ruleExpression0"
	// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:730:1: ruleExpression0 returns [EObject current=null] : ( ( ( ( (lv_ops_0_1= RULE_ATOM | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) ( ( (lv_complex_1_0= '(' ) ) ( ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* )? otherlv_5= ')' )? ) | ( (lv_number_6_0= RULE_NUMBER ) ) | ( (lv_string_7_0= RULE_STRING ) ) | ( (lv_variable_8_0= RULE_VARIABLE ) ) | ( ( (lv_list_9_0= '[' ) ) ( ( (lv_head_10_0= ruleExpressionINF ) ) (otherlv_11= ',' ( (lv_head_12_0= ruleExpressionINF ) ) )* (otherlv_13= '|' ( (lv_tail_14_0= ruleExpressionINF ) ) )? )? otherlv_15= ']' ) | ( ( (lv_list_16_0= '.' ) ) otherlv_17= '(' ( (lv_head_18_0= ruleExpressionINF ) ) otherlv_19= ',' ( (lv_tail_20_0= ruleExpressionINF ) ) otherlv_21= ')' ) | (otherlv_22= '(' ( (lv_exps_23_0= ruleExpressionINF ) ) otherlv_24= ')' ) ) ;
	public final EObject ruleExpression0() throws RecognitionException {
		EObject current = null;

		Token lv_ops_0_1 = null;
		Token lv_ops_0_2 = null;
		Token lv_ops_0_3 = null;
		Token lv_ops_0_4 = null;
		Token lv_ops_0_5 = null;
		Token lv_ops_0_6 = null;
		Token lv_ops_0_7 = null;
		Token lv_complex_1_0 = null;
		Token otherlv_3 = null;
		Token otherlv_5 = null;
		Token lv_number_6_0 = null;
		Token lv_string_7_0 = null;
		Token lv_variable_8_0 = null;
		Token lv_list_9_0 = null;
		Token otherlv_11 = null;
		Token otherlv_13 = null;
		Token otherlv_15 = null;
		Token lv_list_16_0 = null;
		Token otherlv_17 = null;
		Token otherlv_19 = null;
		Token otherlv_21 = null;
		Token otherlv_22 = null;
		Token otherlv_24 = null;
		EObject lv_exps_2_0 = null;

		EObject lv_exps_4_0 = null;

		EObject lv_head_10_0 = null;

		EObject lv_head_12_0 = null;

		EObject lv_tail_14_0 = null;

		EObject lv_head_18_0 = null;

		EObject lv_tail_20_0 = null;

		EObject lv_exps_23_0 = null;

		enterRule();

		try {
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:733:28: ( ( ( ( ( (lv_ops_0_1= RULE_ATOM | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) ( ( (lv_complex_1_0= '(' ) ) ( ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* )? otherlv_5= ')' )? ) | ( (lv_number_6_0= RULE_NUMBER ) ) | ( (lv_string_7_0= RULE_STRING ) ) | ( (lv_variable_8_0= RULE_VARIABLE ) ) | ( ( (lv_list_9_0= '[' ) ) ( ( (lv_head_10_0= ruleExpressionINF ) ) (otherlv_11= ',' ( (lv_head_12_0= ruleExpressionINF ) ) )* (otherlv_13= '|' ( (lv_tail_14_0= ruleExpressionINF ) ) )? )? otherlv_15= ']' ) | ( ( (lv_list_16_0= '.' ) ) otherlv_17= '(' ( (lv_head_18_0= ruleExpressionINF ) ) otherlv_19= ',' ( (lv_tail_20_0= ruleExpressionINF ) ) otherlv_21= ')' ) | (otherlv_22= '(' ( (lv_exps_23_0= ruleExpressionINF ) ) otherlv_24= ')' ) ) )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:734:1: ( ( ( ( (lv_ops_0_1= RULE_ATOM | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) ( ( (lv_complex_1_0= '(' ) ) ( ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* )? otherlv_5= ')' )? ) | ( (lv_number_6_0= RULE_NUMBER ) ) | ( (lv_string_7_0= RULE_STRING ) ) | ( (lv_variable_8_0= RULE_VARIABLE ) ) | ( ( (lv_list_9_0= '[' ) ) ( ( (lv_head_10_0= ruleExpressionINF ) ) (otherlv_11= ',' ( (lv_head_12_0= ruleExpressionINF ) ) )* (otherlv_13= '|' ( (lv_tail_14_0= ruleExpressionINF ) ) )? )? otherlv_15= ']' ) | ( ( (lv_list_16_0= '.' ) ) otherlv_17= '(' ( (lv_head_18_0= ruleExpressionINF ) ) otherlv_19= ',' ( (lv_tail_20_0= ruleExpressionINF ) ) otherlv_21= ')' ) | (otherlv_22= '(' ( (lv_exps_23_0= ruleExpressionINF ) ) otherlv_24= ')' ) )
			{
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:734:1: ( ( ( ( (lv_ops_0_1= RULE_ATOM | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) ( ( (lv_complex_1_0= '(' ) ) ( ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* )? otherlv_5= ')' )? ) | ( (lv_number_6_0= RULE_NUMBER ) ) | ( (lv_string_7_0= RULE_STRING ) ) | ( (lv_variable_8_0= RULE_VARIABLE ) ) | ( ( (lv_list_9_0= '[' ) ) ( ( (lv_head_10_0= ruleExpressionINF ) ) (otherlv_11= ',' ( (lv_head_12_0= ruleExpressionINF ) ) )* (otherlv_13= '|' ( (lv_tail_14_0= ruleExpressionINF ) ) )? )? otherlv_15= ']' ) | ( ( (lv_list_16_0= '.' ) ) otherlv_17= '(' ( (lv_head_18_0= ruleExpressionINF ) ) otherlv_19= ',' ( (lv_tail_20_0= ruleExpressionINF ) ) otherlv_21= ')' ) | (otherlv_22= '(' ( (lv_exps_23_0= ruleExpressionINF ) ) otherlv_24= ')' ) )
				int alt19 = 7;
				switch (input.LA(1)) {
				case RULE_EXPRESSION_1100:
				case RULE_EXPRESSION_900:
				case RULE_EXPRESSION_700:
				case RULE_EXPRESSION_500:
				case RULE_EXPRESSION_400:
				case RULE_EXPRESSION_200:
				case RULE_ATOM: {
					alt19 = 1;
				}
					break;
				case RULE_NUMBER: {
					alt19 = 2;
				}
					break;
				case RULE_STRING: {
					alt19 = 3;
				}
					break;
				case RULE_VARIABLE: {
					alt19 = 4;
				}
					break;
				case 22: {
					alt19 = 5;
				}
					break;
				case 18: {
					alt19 = 6;
				}
					break;
				case 20: {
					alt19 = 7;
				}
					break;
				default:
					if (state.backtracking > 0) {
						state.failed = true;
						return current;
					}
					NoViableAltException nvae = new NoViableAltException("", 19, 0, input);

					throw nvae;
				}

				switch (alt19) {
				case 1:
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:734:2: ( ( ( (lv_ops_0_1= RULE_ATOM | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) ( ( (lv_complex_1_0= '(' ) ) ( ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* )? otherlv_5= ')' )? )
				{
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:734:2: ( ( ( (lv_ops_0_1= RULE_ATOM | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) ( ( (lv_complex_1_0= '(' ) ) ( ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* )? otherlv_5= ')' )? )
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:734:3: ( ( (lv_ops_0_1= RULE_ATOM | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) ) ( ( (lv_complex_1_0= '(' ) ) ( ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* )? otherlv_5= ')' )?
					{
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:734:3: ( ( (lv_ops_0_1= RULE_ATOM | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) ) )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:735:1: ( (lv_ops_0_1= RULE_ATOM | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:735:1: ( (lv_ops_0_1= RULE_ATOM | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 ) )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:736:1: (lv_ops_0_1= RULE_ATOM | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 )
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:736:1: (lv_ops_0_1= RULE_ATOM | lv_ops_0_2= RULE_EXPRESSION_1100 | lv_ops_0_3= RULE_EXPRESSION_900 | lv_ops_0_4= RULE_EXPRESSION_700 | lv_ops_0_5= RULE_EXPRESSION_500 | lv_ops_0_6= RULE_EXPRESSION_400 | lv_ops_0_7= RULE_EXPRESSION_200 )
								int alt12 = 7;
								switch (input.LA(1)) {
								case RULE_ATOM: {
									alt12 = 1;
								}
									break;
								case RULE_EXPRESSION_1100: {
									alt12 = 2;
								}
									break;
								case RULE_EXPRESSION_900: {
									alt12 = 3;
								}
									break;
								case RULE_EXPRESSION_700: {
									alt12 = 4;
								}
									break;
								case RULE_EXPRESSION_500: {
									alt12 = 5;
								}
									break;
								case RULE_EXPRESSION_400: {
									alt12 = 6;
								}
									break;
								case RULE_EXPRESSION_200: {
									alt12 = 7;
								}
									break;
								default:
									if (state.backtracking > 0) {
										state.failed = true;
										return current;
									}
									NoViableAltException nvae = new NoViableAltException("", 12, 0, input);

									throw nvae;
								}

								switch (alt12) {
								case 1:
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:737:3: lv_ops_0_1= RULE_ATOM
								{
									lv_ops_0_1 = (Token) match(input, RULE_ATOM,
											FOLLOW_RULE_ATOM_in_ruleExpression01492);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(lv_ops_0_1, grammarAccess.getExpression0Access()
												.getOpsATOMTerminalRuleCall_0_0_0_0());

									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElement(grammarAccess.getExpression0Rule());
										}
										addWithLastConsumed(current, "ops", lv_ops_0_1, "ATOM");

									}

								}
									break;
								case 2:
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:752:8: lv_ops_0_2= RULE_EXPRESSION_1100
								{
									lv_ops_0_2 = (Token) match(input, RULE_EXPRESSION_1100,
											FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression01512);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(lv_ops_0_2, grammarAccess.getExpression0Access()
												.getOpsEXPRESSION_1100TerminalRuleCall_0_0_0_1());

									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElement(grammarAccess.getExpression0Rule());
										}
										addWithLastConsumed(current, "ops", lv_ops_0_2, "EXPRESSION_1100");

									}

								}
									break;
								case 3:
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:767:8: lv_ops_0_3= RULE_EXPRESSION_900
								{
									lv_ops_0_3 = (Token) match(input, RULE_EXPRESSION_900,
											FOLLOW_RULE_EXPRESSION_900_in_ruleExpression01532);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(lv_ops_0_3, grammarAccess.getExpression0Access()
												.getOpsEXPRESSION_900TerminalRuleCall_0_0_0_2());

									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElement(grammarAccess.getExpression0Rule());
										}
										addWithLastConsumed(current, "ops", lv_ops_0_3, "EXPRESSION_900");

									}

								}
									break;
								case 4:
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:782:8: lv_ops_0_4= RULE_EXPRESSION_700
								{
									lv_ops_0_4 = (Token) match(input, RULE_EXPRESSION_700,
											FOLLOW_RULE_EXPRESSION_700_in_ruleExpression01552);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(lv_ops_0_4, grammarAccess.getExpression0Access()
												.getOpsEXPRESSION_700TerminalRuleCall_0_0_0_3());

									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElement(grammarAccess.getExpression0Rule());
										}
										addWithLastConsumed(current, "ops", lv_ops_0_4, "EXPRESSION_700");

									}

								}
									break;
								case 5:
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:797:8: lv_ops_0_5= RULE_EXPRESSION_500
								{
									lv_ops_0_5 = (Token) match(input, RULE_EXPRESSION_500,
											FOLLOW_RULE_EXPRESSION_500_in_ruleExpression01572);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(lv_ops_0_5, grammarAccess.getExpression0Access()
												.getOpsEXPRESSION_500TerminalRuleCall_0_0_0_4());

									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElement(grammarAccess.getExpression0Rule());
										}
										addWithLastConsumed(current, "ops", lv_ops_0_5, "EXPRESSION_500");

									}

								}
									break;
								case 6:
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:812:8: lv_ops_0_6= RULE_EXPRESSION_400
								{
									lv_ops_0_6 = (Token) match(input, RULE_EXPRESSION_400,
											FOLLOW_RULE_EXPRESSION_400_in_ruleExpression01592);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(lv_ops_0_6, grammarAccess.getExpression0Access()
												.getOpsEXPRESSION_400TerminalRuleCall_0_0_0_5());

									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElement(grammarAccess.getExpression0Rule());
										}
										addWithLastConsumed(current, "ops", lv_ops_0_6, "EXPRESSION_400");

									}

								}
									break;
								case 7:
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:827:8: lv_ops_0_7= RULE_EXPRESSION_200
								{
									lv_ops_0_7 = (Token) match(input, RULE_EXPRESSION_200,
											FOLLOW_RULE_EXPRESSION_200_in_ruleExpression01612);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(lv_ops_0_7, grammarAccess.getExpression0Access()
												.getOpsEXPRESSION_200TerminalRuleCall_0_0_0_6());

									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElement(grammarAccess.getExpression0Rule());
										}
										addWithLastConsumed(current, "ops", lv_ops_0_7, "EXPRESSION_200");

									}

								}
									break;

								}

							}

						}

						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:845:2: ( ( (lv_complex_1_0= '(' ) ) ( ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* )? otherlv_5= ')' )?
						int alt15 = 2;
						int LA15_0 = input.LA(1);

						if (LA15_0 == 20) {
							alt15 = 1;
						}
						switch (alt15) {
						case 1:
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:845:3: ( (lv_complex_1_0= '(' ) ) ( ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* )? otherlv_5= ')'
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:845:3: ( (lv_complex_1_0= '(' ) )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:846:1: (lv_complex_1_0= '(' )
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:846:1: (lv_complex_1_0= '(' )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:847:3: lv_complex_1_0= '('
								{
									lv_complex_1_0 = (Token) match(input, 20, FOLLOW_20_in_ruleExpression01639);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(lv_complex_1_0, grammarAccess.getExpression0Access()
												.getComplexLeftParenthesisKeyword_0_1_0_0());

									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElement(grammarAccess.getExpression0Rule());
										}
										setWithLastConsumed(current, "complex", true, "(");

									}

								}

							}

							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:860:2: ( ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )* )?
							int alt14 = 2;
							int LA14_0 = input.LA(1);

							if (LA14_0 >= RULE_EXPRESSION_1100 && LA14_0 <= RULE_VARIABLE || LA14_0 == 18
									|| LA14_0 == 20 || LA14_0 == 22) {
								alt14 = 1;
							}
							switch (alt14) {
							case 1:
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:860:3: ( (lv_exps_2_0= ruleExpressionINF ) ) (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )*
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:860:3: ( (lv_exps_2_0= ruleExpressionINF ) )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:861:1: (lv_exps_2_0= ruleExpressionINF )
								{
									// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:861:1: (lv_exps_2_0= ruleExpressionINF )
									// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:862:3: lv_exps_2_0= ruleExpressionINF
									{
										if (state.backtracking == 0) {

											newCompositeNode(grammarAccess.getExpression0Access()
													.getExpsExpressionINFParserRuleCall_0_1_1_0_0());

										}
										pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression01674);
										lv_exps_2_0 = ruleExpressionINF();

										state._fsp--;
										if (state.failed) {
											return current;
										}
										if (state.backtracking == 0) {

											if (current == null) {
												current = createModelElementForParent(grammarAccess
														.getExpression0Rule());
											}
											add(current, "exps", lv_exps_2_0, "ExpressionINF");
											afterParserOrEnumRuleCall();

										}

									}

								}

								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:878:2: (otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) ) )*
								loop13: do {
									int alt13 = 2;
									int LA13_0 = input.LA(1);

									if (LA13_0 == 17) {
										alt13 = 1;
									}

									switch (alt13) {
									case 1:
									// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:878:4: otherlv_3= ',' ( (lv_exps_4_0= ruleExpressionINF ) )
									{
										otherlv_3 = (Token) match(input, 17, FOLLOW_17_in_ruleExpression01687);
										if (state.failed) {
											return current;
										}
										if (state.backtracking == 0) {

											newLeafNode(otherlv_3, grammarAccess.getExpression0Access()
													.getCommaKeyword_0_1_1_1_0());

										}
										// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:882:1: ( (lv_exps_4_0= ruleExpressionINF ) )
										// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:883:1: (lv_exps_4_0= ruleExpressionINF )
										{
											// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:883:1: (lv_exps_4_0= ruleExpressionINF )
											// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:884:3: lv_exps_4_0= ruleExpressionINF
											{
												if (state.backtracking == 0) {

													newCompositeNode(grammarAccess.getExpression0Access()
															.getExpsExpressionINFParserRuleCall_0_1_1_1_1_0());

												}
												pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression01708);
												lv_exps_4_0 = ruleExpressionINF();

												state._fsp--;
												if (state.failed) {
													return current;
												}
												if (state.backtracking == 0) {

													if (current == null) {
														current = createModelElementForParent(grammarAccess
																.getExpression0Rule());
													}
													add(current, "exps", lv_exps_4_0, "ExpressionINF");
													afterParserOrEnumRuleCall();

												}

											}

										}

									}
										break;

									default:
										break loop13;
									}
								} while (true);

							}
								break;

							}

							otherlv_5 = (Token) match(input, 21, FOLLOW_21_in_ruleExpression01724);
							if (state.failed) {
								return current;
							}
							if (state.backtracking == 0) {

								newLeafNode(otherlv_5, grammarAccess.getExpression0Access()
										.getRightParenthesisKeyword_0_1_2());

							}

						}
							break;

						}

					}

				}
					break;
				case 2:
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:905:6: ( (lv_number_6_0= RULE_NUMBER ) )
				{
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:905:6: ( (lv_number_6_0= RULE_NUMBER ) )
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:906:1: (lv_number_6_0= RULE_NUMBER )
					{
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:906:1: (lv_number_6_0= RULE_NUMBER )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:907:3: lv_number_6_0= RULE_NUMBER
						{
							lv_number_6_0 = (Token) match(input, RULE_NUMBER, FOLLOW_RULE_NUMBER_in_ruleExpression01750);
							if (state.failed) {
								return current;
							}
							if (state.backtracking == 0) {

								newLeafNode(lv_number_6_0, grammarAccess.getExpression0Access()
										.getNumberNUMBERTerminalRuleCall_1_0());

							}
							if (state.backtracking == 0) {

								if (current == null) {
									current = createModelElement(grammarAccess.getExpression0Rule());
								}
								setWithLastConsumed(current, "number", lv_number_6_0, "NUMBER");

							}

						}

					}

				}
					break;
				case 3:
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:924:6: ( (lv_string_7_0= RULE_STRING ) )
				{
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:924:6: ( (lv_string_7_0= RULE_STRING ) )
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:925:1: (lv_string_7_0= RULE_STRING )
					{
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:925:1: (lv_string_7_0= RULE_STRING )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:926:3: lv_string_7_0= RULE_STRING
						{
							lv_string_7_0 = (Token) match(input, RULE_STRING, FOLLOW_RULE_STRING_in_ruleExpression01778);
							if (state.failed) {
								return current;
							}
							if (state.backtracking == 0) {

								newLeafNode(lv_string_7_0, grammarAccess.getExpression0Access()
										.getStringSTRINGTerminalRuleCall_2_0());

							}
							if (state.backtracking == 0) {

								if (current == null) {
									current = createModelElement(grammarAccess.getExpression0Rule());
								}
								setWithLastConsumed(current, "string", lv_string_7_0, "STRING");

							}

						}

					}

				}
					break;
				case 4:
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:943:6: ( (lv_variable_8_0= RULE_VARIABLE ) )
				{
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:943:6: ( (lv_variable_8_0= RULE_VARIABLE ) )
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:944:1: (lv_variable_8_0= RULE_VARIABLE )
					{
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:944:1: (lv_variable_8_0= RULE_VARIABLE )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:945:3: lv_variable_8_0= RULE_VARIABLE
						{
							lv_variable_8_0 = (Token) match(input, RULE_VARIABLE,
									FOLLOW_RULE_VARIABLE_in_ruleExpression01806);
							if (state.failed) {
								return current;
							}
							if (state.backtracking == 0) {

								newLeafNode(lv_variable_8_0, grammarAccess.getExpression0Access()
										.getVariableVARIABLETerminalRuleCall_3_0());

							}
							if (state.backtracking == 0) {

								if (current == null) {
									current = createModelElement(grammarAccess.getExpression0Rule());
								}
								setWithLastConsumed(current, "variable", lv_variable_8_0, "VARIABLE");

							}

						}

					}

				}
					break;
				case 5:
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:962:6: ( ( (lv_list_9_0= '[' ) ) ( ( (lv_head_10_0= ruleExpressionINF ) ) (otherlv_11= ',' ( (lv_head_12_0= ruleExpressionINF ) ) )* (otherlv_13= '|' ( (lv_tail_14_0= ruleExpressionINF ) ) )? )? otherlv_15= ']' )
				{
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:962:6: ( ( (lv_list_9_0= '[' ) ) ( ( (lv_head_10_0= ruleExpressionINF ) ) (otherlv_11= ',' ( (lv_head_12_0= ruleExpressionINF ) ) )* (otherlv_13= '|' ( (lv_tail_14_0= ruleExpressionINF ) ) )? )? otherlv_15= ']' )
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:962:7: ( (lv_list_9_0= '[' ) ) ( ( (lv_head_10_0= ruleExpressionINF ) ) (otherlv_11= ',' ( (lv_head_12_0= ruleExpressionINF ) ) )* (otherlv_13= '|' ( (lv_tail_14_0= ruleExpressionINF ) ) )? )? otherlv_15= ']'
					{
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:962:7: ( (lv_list_9_0= '[' ) )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:963:1: (lv_list_9_0= '[' )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:963:1: (lv_list_9_0= '[' )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:964:3: lv_list_9_0= '['
							{
								lv_list_9_0 = (Token) match(input, 22, FOLLOW_22_in_ruleExpression01836);
								if (state.failed) {
									return current;
								}
								if (state.backtracking == 0) {

									newLeafNode(lv_list_9_0, grammarAccess.getExpression0Access()
											.getListLeftSquareBracketKeyword_4_0_0());

								}
								if (state.backtracking == 0) {

									if (current == null) {
										current = createModelElement(grammarAccess.getExpression0Rule());
									}
									setWithLastConsumed(current, "list", true, "[");

								}

							}

						}

						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:977:2: ( ( (lv_head_10_0= ruleExpressionINF ) ) (otherlv_11= ',' ( (lv_head_12_0= ruleExpressionINF ) ) )* (otherlv_13= '|' ( (lv_tail_14_0= ruleExpressionINF ) ) )? )?
						int alt18 = 2;
						int LA18_0 = input.LA(1);

						if (LA18_0 >= RULE_EXPRESSION_1100 && LA18_0 <= RULE_VARIABLE || LA18_0 == 18 || LA18_0 == 20
								|| LA18_0 == 22) {
							alt18 = 1;
						}
						switch (alt18) {
						case 1:
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:977:3: ( (lv_head_10_0= ruleExpressionINF ) ) (otherlv_11= ',' ( (lv_head_12_0= ruleExpressionINF ) ) )* (otherlv_13= '|' ( (lv_tail_14_0= ruleExpressionINF ) ) )?
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:977:3: ( (lv_head_10_0= ruleExpressionINF ) )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:978:1: (lv_head_10_0= ruleExpressionINF )
							{
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:978:1: (lv_head_10_0= ruleExpressionINF )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:979:3: lv_head_10_0= ruleExpressionINF
								{
									if (state.backtracking == 0) {

										newCompositeNode(grammarAccess.getExpression0Access()
												.getHeadExpressionINFParserRuleCall_4_1_0_0());

									}
									pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression01871);
									lv_head_10_0 = ruleExpressionINF();

									state._fsp--;
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										if (current == null) {
											current = createModelElementForParent(grammarAccess.getExpression0Rule());
										}
										add(current, "head", lv_head_10_0, "ExpressionINF");
										afterParserOrEnumRuleCall();

									}

								}

							}

							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:995:2: (otherlv_11= ',' ( (lv_head_12_0= ruleExpressionINF ) ) )*
							loop16: do {
								int alt16 = 2;
								int LA16_0 = input.LA(1);

								if (LA16_0 == 17) {
									alt16 = 1;
								}

								switch (alt16) {
								case 1:
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:995:4: otherlv_11= ',' ( (lv_head_12_0= ruleExpressionINF ) )
								{
									otherlv_11 = (Token) match(input, 17, FOLLOW_17_in_ruleExpression01884);
									if (state.failed) {
										return current;
									}
									if (state.backtracking == 0) {

										newLeafNode(otherlv_11, grammarAccess.getExpression0Access()
												.getCommaKeyword_4_1_1_0());

									}
									// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:999:1: ( (lv_head_12_0= ruleExpressionINF ) )
									// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1000:1: (lv_head_12_0= ruleExpressionINF )
									{
										// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1000:1: (lv_head_12_0= ruleExpressionINF )
										// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1001:3: lv_head_12_0= ruleExpressionINF
										{
											if (state.backtracking == 0) {

												newCompositeNode(grammarAccess.getExpression0Access()
														.getHeadExpressionINFParserRuleCall_4_1_1_1_0());

											}
											pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression01905);
											lv_head_12_0 = ruleExpressionINF();

											state._fsp--;
											if (state.failed) {
												return current;
											}
											if (state.backtracking == 0) {

												if (current == null) {
													current = createModelElementForParent(grammarAccess
															.getExpression0Rule());
												}
												add(current, "head", lv_head_12_0, "ExpressionINF");
												afterParserOrEnumRuleCall();

											}

										}

									}

								}
									break;

								default:
									break loop16;
								}
							} while (true);

							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1017:4: (otherlv_13= '|' ( (lv_tail_14_0= ruleExpressionINF ) ) )?
							int alt17 = 2;
							int LA17_0 = input.LA(1);

							if (LA17_0 == 23) {
								alt17 = 1;
							}
							switch (alt17) {
							case 1:
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1017:6: otherlv_13= '|' ( (lv_tail_14_0= ruleExpressionINF ) )
							{
								otherlv_13 = (Token) match(input, 23, FOLLOW_23_in_ruleExpression01920);
								if (state.failed) {
									return current;
								}
								if (state.backtracking == 0) {

									newLeafNode(otherlv_13, grammarAccess.getExpression0Access()
											.getVerticalLineKeyword_4_1_2_0());

								}
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1021:1: ( (lv_tail_14_0= ruleExpressionINF ) )
								// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1022:1: (lv_tail_14_0= ruleExpressionINF )
								{
									// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1022:1: (lv_tail_14_0= ruleExpressionINF )
									// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1023:3: lv_tail_14_0= ruleExpressionINF
									{
										if (state.backtracking == 0) {

											newCompositeNode(grammarAccess.getExpression0Access()
													.getTailExpressionINFParserRuleCall_4_1_2_1_0());

										}
										pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression01941);
										lv_tail_14_0 = ruleExpressionINF();

										state._fsp--;
										if (state.failed) {
											return current;
										}
										if (state.backtracking == 0) {

											if (current == null) {
												current = createModelElementForParent(grammarAccess
														.getExpression0Rule());
											}
											set(current, "tail", lv_tail_14_0, "ExpressionINF");
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

						otherlv_15 = (Token) match(input, 24, FOLLOW_24_in_ruleExpression01957);
						if (state.failed) {
							return current;
						}
						if (state.backtracking == 0) {

							newLeafNode(otherlv_15, grammarAccess.getExpression0Access()
									.getRightSquareBracketKeyword_4_2());

						}

					}

				}
					break;
				case 6:
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1044:6: ( ( (lv_list_16_0= '.' ) ) otherlv_17= '(' ( (lv_head_18_0= ruleExpressionINF ) ) otherlv_19= ',' ( (lv_tail_20_0= ruleExpressionINF ) ) otherlv_21= ')' )
				{
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1044:6: ( ( (lv_list_16_0= '.' ) ) otherlv_17= '(' ( (lv_head_18_0= ruleExpressionINF ) ) otherlv_19= ',' ( (lv_tail_20_0= ruleExpressionINF ) ) otherlv_21= ')' )
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1044:7: ( (lv_list_16_0= '.' ) ) otherlv_17= '(' ( (lv_head_18_0= ruleExpressionINF ) ) otherlv_19= ',' ( (lv_tail_20_0= ruleExpressionINF ) ) otherlv_21= ')'
					{
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1044:7: ( (lv_list_16_0= '.' ) )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1045:1: (lv_list_16_0= '.' )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1045:1: (lv_list_16_0= '.' )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1046:3: lv_list_16_0= '.'
							{
								lv_list_16_0 = (Token) match(input, 18, FOLLOW_18_in_ruleExpression01983);
								if (state.failed) {
									return current;
								}
								if (state.backtracking == 0) {

									newLeafNode(lv_list_16_0, grammarAccess.getExpression0Access()
											.getListFullStopKeyword_5_0_0());

								}
								if (state.backtracking == 0) {

									if (current == null) {
										current = createModelElement(grammarAccess.getExpression0Rule());
									}
									setWithLastConsumed(current, "list", true, ".");

								}

							}

						}

						otherlv_17 = (Token) match(input, 20, FOLLOW_20_in_ruleExpression02008);
						if (state.failed) {
							return current;
						}
						if (state.backtracking == 0) {

							newLeafNode(otherlv_17, grammarAccess.getExpression0Access()
									.getLeftParenthesisKeyword_5_1());

						}
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1063:1: ( (lv_head_18_0= ruleExpressionINF ) )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1064:1: (lv_head_18_0= ruleExpressionINF )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1064:1: (lv_head_18_0= ruleExpressionINF )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1065:3: lv_head_18_0= ruleExpressionINF
							{
								if (state.backtracking == 0) {

									newCompositeNode(grammarAccess.getExpression0Access()
											.getHeadExpressionINFParserRuleCall_5_2_0());

								}
								pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression02029);
								lv_head_18_0 = ruleExpressionINF();

								state._fsp--;
								if (state.failed) {
									return current;
								}
								if (state.backtracking == 0) {

									if (current == null) {
										current = createModelElementForParent(grammarAccess.getExpression0Rule());
									}
									add(current, "head", lv_head_18_0, "ExpressionINF");
									afterParserOrEnumRuleCall();

								}

							}

						}

						otherlv_19 = (Token) match(input, 17, FOLLOW_17_in_ruleExpression02041);
						if (state.failed) {
							return current;
						}
						if (state.backtracking == 0) {

							newLeafNode(otherlv_19, grammarAccess.getExpression0Access().getCommaKeyword_5_3());

						}
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1085:1: ( (lv_tail_20_0= ruleExpressionINF ) )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1086:1: (lv_tail_20_0= ruleExpressionINF )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1086:1: (lv_tail_20_0= ruleExpressionINF )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1087:3: lv_tail_20_0= ruleExpressionINF
							{
								if (state.backtracking == 0) {

									newCompositeNode(grammarAccess.getExpression0Access()
											.getTailExpressionINFParserRuleCall_5_4_0());

								}
								pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression02062);
								lv_tail_20_0 = ruleExpressionINF();

								state._fsp--;
								if (state.failed) {
									return current;
								}
								if (state.backtracking == 0) {

									if (current == null) {
										current = createModelElementForParent(grammarAccess.getExpression0Rule());
									}
									set(current, "tail", lv_tail_20_0, "ExpressionINF");
									afterParserOrEnumRuleCall();

								}

							}

						}

						otherlv_21 = (Token) match(input, 21, FOLLOW_21_in_ruleExpression02074);
						if (state.failed) {
							return current;
						}
						if (state.backtracking == 0) {

							newLeafNode(otherlv_21, grammarAccess.getExpression0Access()
									.getRightParenthesisKeyword_5_5());

						}

					}

				}
					break;
				case 7:
				// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1108:6: (otherlv_22= '(' ( (lv_exps_23_0= ruleExpressionINF ) ) otherlv_24= ')' )
				{
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1108:6: (otherlv_22= '(' ( (lv_exps_23_0= ruleExpressionINF ) ) otherlv_24= ')' )
					// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1108:8: otherlv_22= '(' ( (lv_exps_23_0= ruleExpressionINF ) ) otherlv_24= ')'
					{
						otherlv_22 = (Token) match(input, 20, FOLLOW_20_in_ruleExpression02094);
						if (state.failed) {
							return current;
						}
						if (state.backtracking == 0) {

							newLeafNode(otherlv_22, grammarAccess.getExpression0Access()
									.getLeftParenthesisKeyword_6_0());

						}
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1112:1: ( (lv_exps_23_0= ruleExpressionINF ) )
						// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1113:1: (lv_exps_23_0= ruleExpressionINF )
						{
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1113:1: (lv_exps_23_0= ruleExpressionINF )
							// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1114:3: lv_exps_23_0= ruleExpressionINF
							{
								if (state.backtracking == 0) {

									newCompositeNode(grammarAccess.getExpression0Access()
											.getExpsExpressionINFParserRuleCall_6_1_0());

								}
								pushFollow(FOLLOW_ruleExpressionINF_in_ruleExpression02115);
								lv_exps_23_0 = ruleExpressionINF();

								state._fsp--;
								if (state.failed) {
									return current;
								}
								if (state.backtracking == 0) {

									if (current == null) {
										current = createModelElementForParent(grammarAccess.getExpression0Rule());
									}
									add(current, "exps", lv_exps_23_0, "ExpressionINF");
									afterParserOrEnumRuleCall();

								}

							}

						}

						otherlv_24 = (Token) match(input, 21, FOLLOW_21_in_ruleExpression02127);
						if (state.failed) {
							return current;
						}
						if (state.backtracking == 0) {

							newLeafNode(otherlv_24, grammarAccess.getExpression0Access()
									.getRightParenthesisKeyword_6_2());

						}

					}

				}
					break;

				}

			}

			if (state.backtracking == 0) {
				leaveRule();
			}
		}

		catch (RecognitionException re) {
			recover(input, re);
			appendSkippedTokens();
		}
		finally {
		}
		return current;
	}

	// $ANTLR end "ruleExpression0"

	// $ANTLR start synpred7_InternalProlog
	public final void synpred7_InternalProlog_fragment() throws RecognitionException {
		Token lv_ops_0_0 = null;

		// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:402:1: ( (lv_ops_0_0= RULE_EXPRESSION_900 ) )
		// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:402:1: (lv_ops_0_0= RULE_EXPRESSION_900 )
		{
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:402:1: (lv_ops_0_0= RULE_EXPRESSION_900 )
			// ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:403:3: lv_ops_0_0= RULE_EXPRESSION_900
			{
				lv_ops_0_0 = (Token) match(input, RULE_EXPRESSION_900,
						FOLLOW_RULE_EXPRESSION_900_in_synpred7_InternalProlog810);
				if (state.failed) {
					return;
				}

			}

		}
	}

	// $ANTLR end synpred7_InternalProlog

	// Delegated rules

	public final boolean synpred7_InternalProlog() {
		state.backtracking++;
		int start = input.mark();
		try {
			synpred7_InternalProlog_fragment(); // can never throw exception
		}
		catch (RecognitionException re) {
			System.err.println("impossible: " + re);
		}
		boolean success = !state.failed;
		input.rewind(start);
		state.backtracking--;
		state.failed = false;
		return success;
	}

	protected DFA7 dfa7 = new DFA7(this);
	static final String DFA7_eotS = "\17\uffff";
	static final String DFA7_eofS = "\17\uffff";
	static final String DFA7_minS = "\1\4\1\0\15\uffff";
	static final String DFA7_maxS = "\1\26\1\0\15\uffff";
	static final String DFA7_acceptS = "\2\uffff\1\2\13\uffff\1\1";
	static final String DFA7_specialS = "\1\uffff\1\0\15\uffff}>";
	static final String[] DFA7_transitionS = { "\1\2\1\1\10\2\4\uffff\1\2\1\uffff\1\2\1\uffff\1\2", "\1\uffff", "", "",
			"", "", "", "", "", "", "", "", "", "", "" };

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
		for (int i = 0; i < numStates; i++) {
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

		@Override
		public String getDescription() {
			return "401:2: ( (lv_ops_0_0= RULE_EXPRESSION_900 ) )?";
		}

		@Override
		public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
			TokenStream input = (TokenStream) _input;
			int _s = s;
			switch (s) {
			case 0:
				int LA7_1 = input.LA(1);

				int index7_1 = input.index();
				input.rewind();
				s = -1;
				if (synpred7_InternalProlog()) {
					s = 14;
				}

				else if (true) {
					s = 2;
				}

				input.seek(index7_1);
				if (s >= 0) {
					return s;
				}
				break;
			}
			if (state.backtracking > 0) {
				state.failed = true;
				return -1;
			}
			NoViableAltException nvae = new NoViableAltException(getDescription(), 7, _s, input);
			error(nvae);
			throw nvae;
		}
	}

	public static final BitSet FOLLOW_ruleProgram_in_entryRuleProgram81 = new BitSet(new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_EOF_in_entryRuleProgram91 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleClause_in_ruleProgram137 = new BitSet(new long[] { 0x00000000005C3FF2L });
	public static final BitSet FOLLOW_ruleQuery_in_ruleProgram159 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleClause_in_entryRuleClause196 = new BitSet(new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_EOF_in_entryRuleClause206 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleClause252 = new BitSet(
			new long[] { 0x0000000000050000L });
	public static final BitSet FOLLOW_16_in_ruleClause265 = new BitSet(new long[] { 0x00000000005E3FF0L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleClause286 = new BitSet(
			new long[] { 0x0000000000060000L });
	public static final BitSet FOLLOW_17_in_ruleClause299 = new BitSet(new long[] { 0x00000000005E3FF0L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleClause320 = new BitSet(
			new long[] { 0x0000000000060000L });
	public static final BitSet FOLLOW_18_in_ruleClause336 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleQuery_in_entryRuleQuery372 = new BitSet(new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_EOF_in_entryRuleQuery382 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_19_in_ruleQuery419 = new BitSet(new long[] { 0x00000000005E3FF0L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleQuery440 = new BitSet(new long[] { 0x0000000000060000L });
	public static final BitSet FOLLOW_17_in_ruleQuery453 = new BitSet(new long[] { 0x00000000005E3FF0L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleQuery474 = new BitSet(new long[] { 0x0000000000060000L });
	public static final BitSet FOLLOW_18_in_ruleQuery488 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_entryRuleExpressionINF524 = new BitSet(
			new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_EOF_in_entryRuleExpressionINF534 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleExpression1100_in_ruleExpressionINF583 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleExpression1100_in_entryRuleExpression1100617 = new BitSet(
			new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_EOF_in_entryRuleExpression1100627 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleExpression900_in_ruleExpression1100677 = new BitSet(
			new long[] { 0x0000000000000012L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression1100694 = new BitSet(
			new long[] { 0x00000000005C3FF0L });
	public static final BitSet FOLLOW_ruleExpression900_in_ruleExpression1100720 = new BitSet(
			new long[] { 0x0000000000000012L });
	public static final BitSet FOLLOW_ruleExpression900_in_entryRuleExpression900758 = new BitSet(
			new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_EOF_in_entryRuleExpression900768 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_900_in_ruleExpression900810 = new BitSet(
			new long[] { 0x00000000005C3FF0L });
	public static final BitSet FOLLOW_ruleExpression700_in_ruleExpression900837 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleExpression700_in_entryRuleExpression700873 = new BitSet(
			new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_EOF_in_entryRuleExpression700883 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleExpression500_in_ruleExpression700933 = new BitSet(
			new long[] { 0x0000000000000042L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_700_in_ruleExpression700950 = new BitSet(
			new long[] { 0x00000000005C3FF0L });
	public static final BitSet FOLLOW_ruleExpression500_in_ruleExpression700976 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleExpression500_in_entryRuleExpression5001014 = new BitSet(
			new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_EOF_in_entryRuleExpression5001024 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleExpression400_in_ruleExpression5001074 = new BitSet(
			new long[] { 0x0000000000000082L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_500_in_ruleExpression5001091 = new BitSet(
			new long[] { 0x00000000005C3FF0L });
	public static final BitSet FOLLOW_ruleExpression400_in_ruleExpression5001117 = new BitSet(
			new long[] { 0x0000000000000082L });
	public static final BitSet FOLLOW_ruleExpression400_in_entryRuleExpression4001155 = new BitSet(
			new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_EOF_in_entryRuleExpression4001165 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleExpression200_in_ruleExpression4001215 = new BitSet(
			new long[] { 0x0000000000000102L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_400_in_ruleExpression4001232 = new BitSet(
			new long[] { 0x00000000005C3FF0L });
	public static final BitSet FOLLOW_ruleExpression200_in_ruleExpression4001258 = new BitSet(
			new long[] { 0x0000000000000102L });
	public static final BitSet FOLLOW_ruleExpression200_in_entryRuleExpression2001296 = new BitSet(
			new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_EOF_in_entryRuleExpression2001306 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_ruleExpression0_in_ruleExpression2001356 = new BitSet(
			new long[] { 0x0000000000000202L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_200_in_ruleExpression2001373 = new BitSet(
			new long[] { 0x00000000005C3FF0L });
	public static final BitSet FOLLOW_ruleExpression0_in_ruleExpression2001399 = new BitSet(
			new long[] { 0x0000000000000202L });
	public static final BitSet FOLLOW_ruleExpression0_in_entryRuleExpression01437 = new BitSet(
			new long[] { 0x0000000000000000L });
	public static final BitSet FOLLOW_EOF_in_entryRuleExpression01447 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_RULE_ATOM_in_ruleExpression01492 = new BitSet(new long[] { 0x0000000000100002L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_1100_in_ruleExpression01512 = new BitSet(
			new long[] { 0x0000000000100002L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_900_in_ruleExpression01532 = new BitSet(
			new long[] { 0x0000000000100002L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_700_in_ruleExpression01552 = new BitSet(
			new long[] { 0x0000000000100002L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_500_in_ruleExpression01572 = new BitSet(
			new long[] { 0x0000000000100002L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_400_in_ruleExpression01592 = new BitSet(
			new long[] { 0x0000000000100002L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_200_in_ruleExpression01612 = new BitSet(
			new long[] { 0x0000000000100002L });
	public static final BitSet FOLLOW_20_in_ruleExpression01639 = new BitSet(new long[] { 0x00000000007E3FF0L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression01674 = new BitSet(
			new long[] { 0x0000000000220000L });
	public static final BitSet FOLLOW_17_in_ruleExpression01687 = new BitSet(new long[] { 0x00000000007E3FF0L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression01708 = new BitSet(
			new long[] { 0x0000000000220000L });
	public static final BitSet FOLLOW_21_in_ruleExpression01724 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_RULE_NUMBER_in_ruleExpression01750 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_RULE_STRING_in_ruleExpression01778 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_RULE_VARIABLE_in_ruleExpression01806 = new BitSet(
			new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_22_in_ruleExpression01836 = new BitSet(new long[] { 0x0000000001DE3FF0L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression01871 = new BitSet(
			new long[] { 0x0000000001820000L });
	public static final BitSet FOLLOW_17_in_ruleExpression01884 = new BitSet(new long[] { 0x0000000001DE3FF0L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression01905 = new BitSet(
			new long[] { 0x0000000001820000L });
	public static final BitSet FOLLOW_23_in_ruleExpression01920 = new BitSet(new long[] { 0x00000000015C3FF0L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression01941 = new BitSet(
			new long[] { 0x0000000001000000L });
	public static final BitSet FOLLOW_24_in_ruleExpression01957 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_18_in_ruleExpression01983 = new BitSet(new long[] { 0x0000000000100000L });
	public static final BitSet FOLLOW_20_in_ruleExpression02008 = new BitSet(new long[] { 0x00000000005E3FF0L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression02029 = new BitSet(
			new long[] { 0x0000000000020000L });
	public static final BitSet FOLLOW_17_in_ruleExpression02041 = new BitSet(new long[] { 0x00000000007C3FF0L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression02062 = new BitSet(
			new long[] { 0x0000000000200000L });
	public static final BitSet FOLLOW_21_in_ruleExpression02074 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_20_in_ruleExpression02094 = new BitSet(new long[] { 0x00000000007C3FF0L });
	public static final BitSet FOLLOW_ruleExpressionINF_in_ruleExpression02115 = new BitSet(
			new long[] { 0x0000000000200000L });
	public static final BitSet FOLLOW_21_in_ruleExpression02127 = new BitSet(new long[] { 0x0000000000000002L });
	public static final BitSet FOLLOW_RULE_EXPRESSION_900_in_synpred7_InternalProlog810 = new BitSet(
			new long[] { 0x0000000000000002L });

}