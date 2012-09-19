package org.archstudio.prolog.xtext.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalPrologLexer extends Lexer {
    public static final int RULE_ID=11;
    public static final int RULE_UPPER_CASE_LETTER=8;
    public static final int T__22=22;
    public static final int RULE_ANY_OTHER=16;
    public static final int T__21=21;
    public static final int T__20=20;
    public static final int RULE_CHARACTER=7;
    public static final int RULE_LOWER_CASE_LETTER=6;
    public static final int EOF=-1;
    public static final int RULE_SL_COMMENT=14;
    public static final int RULE_ML_COMMENT=13;
    public static final int T__19=19;
    public static final int RULE_STRING=5;
    public static final int T__18=18;
    public static final int T__17=17;
    public static final int RULE_SPECIAL=10;
    public static final int RULE_INT=12;
    public static final int RULE_WS=15;
    public static final int RULE_DIGIT=9;
    public static final int RULE_NUMERAL=4;

    // delegates
    // delegators

    public InternalPrologLexer() {;} 
    public InternalPrologLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalPrologLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g"; }

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:11:7: ( ':-' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:11:9: ':-'
            {
            match(":-"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:12:7: ( ',' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:12:9: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:13:7: ( '.' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:13:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:14:7: ( '?-' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:14:9: '?-'
            {
            match("?-"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:15:7: ( '(' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:15:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:16:7: ( ')' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:16:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "RULE_NUMERAL"
    public final void mRULE_NUMERAL() throws RecognitionException {
        try {
            int _type = RULE_NUMERAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:532:14: ( ( '-' )? ( RULE_DIGIT )+ )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:532:16: ( '-' )? ( RULE_DIGIT )+
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:532:16: ( '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:532:16: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:532:21: ( RULE_DIGIT )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:532:21: RULE_DIGIT
            	    {
            	    mRULE_DIGIT(); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_NUMERAL"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:534:13: ( '\\'' ( RULE_CHARACTER )+ '\\'' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:534:15: '\\'' ( RULE_CHARACTER )+ '\\''
            {
            match('\''); 
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:534:20: ( RULE_CHARACTER )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='#' && LA3_0<='$')||LA3_0=='&'||(LA3_0>='*' && LA3_0<='+')||(LA3_0>='-' && LA3_0<=':')||(LA3_0>='?' && LA3_0<='Z')||LA3_0=='\\'||(LA3_0>='^' && LA3_0<='_')||(LA3_0>='a' && LA3_0<='z')||LA3_0=='~') ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:534:20: RULE_CHARACTER
            	    {
            	    mRULE_CHARACTER(); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);

            match('\''); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_DIGIT"
    public final void mRULE_DIGIT() throws RecognitionException {
        try {
            int _type = RULE_DIGIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:536:12: ( '0' .. '9' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:536:14: '0' .. '9'
            {
            matchRange('0','9'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_DIGIT"

    // $ANTLR start "RULE_LOWER_CASE_LETTER"
    public final void mRULE_LOWER_CASE_LETTER() throws RecognitionException {
        try {
            int _type = RULE_LOWER_CASE_LETTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:538:24: ( 'a' .. 'z' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:538:26: 'a' .. 'z'
            {
            matchRange('a','z'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_LOWER_CASE_LETTER"

    // $ANTLR start "RULE_UPPER_CASE_LETTER"
    public final void mRULE_UPPER_CASE_LETTER() throws RecognitionException {
        try {
            int _type = RULE_UPPER_CASE_LETTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:540:24: ( ( 'A' .. 'Z' | '_' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:540:26: ( 'A' .. 'Z' | '_' )
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_UPPER_CASE_LETTER"

    // $ANTLR start "RULE_SPECIAL"
    public final void mRULE_SPECIAL() throws RecognitionException {
        try {
            int _type = RULE_SPECIAL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:542:14: ( ( '+' | '-' | '*' | '/' | '\\\\' | '^' | '~' | ':' | '.' | '?' | '@' | '#' | '$' | '&' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:542:16: ( '+' | '-' | '*' | '/' | '\\\\' | '^' | '~' | ':' | '.' | '?' | '@' | '#' | '$' | '&' )
            {
            if ( (input.LA(1)>='#' && input.LA(1)<='$')||input.LA(1)=='&'||(input.LA(1)>='*' && input.LA(1)<='+')||(input.LA(1)>='-' && input.LA(1)<='/')||input.LA(1)==':'||(input.LA(1)>='?' && input.LA(1)<='@')||input.LA(1)=='\\'||input.LA(1)=='^'||input.LA(1)=='~' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SPECIAL"

    // $ANTLR start "RULE_CHARACTER"
    public final void mRULE_CHARACTER() throws RecognitionException {
        try {
            int _type = RULE_CHARACTER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:544:16: ( ( RULE_LOWER_CASE_LETTER | RULE_UPPER_CASE_LETTER | RULE_DIGIT | RULE_SPECIAL ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:544:18: ( RULE_LOWER_CASE_LETTER | RULE_UPPER_CASE_LETTER | RULE_DIGIT | RULE_SPECIAL )
            {
            if ( (input.LA(1)>='#' && input.LA(1)<='$')||input.LA(1)=='&'||(input.LA(1)>='*' && input.LA(1)<='+')||(input.LA(1)>='-' && input.LA(1)<=':')||(input.LA(1)>='?' && input.LA(1)<='Z')||input.LA(1)=='\\'||(input.LA(1)>='^' && input.LA(1)<='_')||(input.LA(1)>='a' && input.LA(1)<='z')||input.LA(1)=='~' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_CHARACTER"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:546:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:546:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:546:11: ( '^' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='^') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:546:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:546:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')||(LA5_0>='A' && LA5_0<='Z')||LA5_0=='_'||(LA5_0>='a' && LA5_0<='z')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:548:10: ( ( '0' .. '9' )+ )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:548:12: ( '0' .. '9' )+
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:548:12: ( '0' .. '9' )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>='0' && LA6_0<='9')) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:548:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:550:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:550:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:550:24: ( options {greedy=false; } : . )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='/') ) {
                        alt7=2;
                    }
                    else if ( ((LA7_1>='\u0000' && LA7_1<='.')||(LA7_1>='0' && LA7_1<='\uFFFF')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0>='\u0000' && LA7_0<=')')||(LA7_0>='+' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:550:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:552:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:552:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:552:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:552:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:552:40: ( ( '\\r' )? '\\n' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\n'||LA10_0=='\r') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:552:41: ( '\\r' )? '\\n'
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:552:41: ( '\\r' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='\r') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:552:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:554:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:554:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:554:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='\t' && LA11_0<='\n')||LA11_0=='\r'||LA11_0==' ') ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:556:16: ( . )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:556:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:8: ( T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | RULE_NUMERAL | RULE_STRING | RULE_DIGIT | RULE_LOWER_CASE_LETTER | RULE_UPPER_CASE_LETTER | RULE_SPECIAL | RULE_CHARACTER | RULE_ID | RULE_INT | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt12=19;
        alt12 = dfa12.predict(input);
        switch (alt12) {
            case 1 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:10: T__17
                {
                mT__17(); 

                }
                break;
            case 2 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:16: T__18
                {
                mT__18(); 

                }
                break;
            case 3 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:22: T__19
                {
                mT__19(); 

                }
                break;
            case 4 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:28: T__20
                {
                mT__20(); 

                }
                break;
            case 5 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:34: T__21
                {
                mT__21(); 

                }
                break;
            case 6 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:40: T__22
                {
                mT__22(); 

                }
                break;
            case 7 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:46: RULE_NUMERAL
                {
                mRULE_NUMERAL(); 

                }
                break;
            case 8 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:59: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 9 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:71: RULE_DIGIT
                {
                mRULE_DIGIT(); 

                }
                break;
            case 10 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:82: RULE_LOWER_CASE_LETTER
                {
                mRULE_LOWER_CASE_LETTER(); 

                }
                break;
            case 11 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:105: RULE_UPPER_CASE_LETTER
                {
                mRULE_UPPER_CASE_LETTER(); 

                }
                break;
            case 12 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:128: RULE_SPECIAL
                {
                mRULE_SPECIAL(); 

                }
                break;
            case 13 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:141: RULE_CHARACTER
                {
                mRULE_CHARACTER(); 

                }
                break;
            case 14 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:156: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 15 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:164: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 16 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:173: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 17 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:189: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 18 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:205: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 19 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:213: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA12 dfa12 = new DFA12(this);
    static final String DFA12_eotS =
        "\1\uffff\1\22\2\uffff\1\22\2\uffff\1\22\1\30\1\20\1\33\1\35\2\22"+
        "\13\uffff\1\30\7\uffff";
    static final String DFA12_eofS =
        "\41\uffff";
    static final String DFA12_minS =
        "\1\0\1\55\2\uffff\1\55\2\uffff\2\60\1\43\2\60\1\101\1\52\13\uffff"+
        "\1\60\7\uffff";
    static final String DFA12_maxS =
        "\1\uffff\1\55\2\uffff\1\55\2\uffff\2\71\1\176\3\172\1\57\13\uffff"+
        "\1\71\7\uffff";
    static final String DFA12_acceptS =
        "\2\uffff\1\2\1\3\1\uffff\1\5\1\6\7\uffff\1\14\1\22\1\23\1\1\1\14"+
        "\1\2\1\3\1\4\1\5\1\6\1\7\1\uffff\1\10\1\12\1\16\1\13\1\20\1\21\1"+
        "\22";
    static final String DFA12_specialS =
        "\1\0\40\uffff}>";
    static final String[] DFA12_transitionS = {
            "\11\20\2\17\2\20\1\17\22\20\1\17\2\20\2\16\1\20\1\16\1\11\1"+
            "\5\1\6\2\16\1\2\1\7\1\3\1\15\12\10\1\1\4\20\1\4\1\16\32\13\1"+
            "\20\1\16\1\20\1\14\1\13\1\20\32\12\3\20\1\16\uff81\20",
            "\1\21",
            "",
            "",
            "\1\25",
            "",
            "",
            "\12\30",
            "\12\31",
            "\2\32\1\uffff\1\32\3\uffff\2\32\1\uffff\16\32\4\uffff\34\32"+
            "\1\uffff\1\32\1\uffff\2\32\1\uffff\32\32\3\uffff\1\32",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\36\4\uffff\1\37",
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
            "\12\31",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA12_eot = DFA.unpackEncodedString(DFA12_eotS);
    static final short[] DFA12_eof = DFA.unpackEncodedString(DFA12_eofS);
    static final char[] DFA12_min = DFA.unpackEncodedStringToUnsignedChars(DFA12_minS);
    static final char[] DFA12_max = DFA.unpackEncodedStringToUnsignedChars(DFA12_maxS);
    static final short[] DFA12_accept = DFA.unpackEncodedString(DFA12_acceptS);
    static final short[] DFA12_special = DFA.unpackEncodedString(DFA12_specialS);
    static final short[][] DFA12_transition;

    static {
        int numStates = DFA12_transitionS.length;
        DFA12_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA12_transition[i] = DFA.unpackEncodedString(DFA12_transitionS[i]);
        }
    }

    class DFA12 extends DFA {

        public DFA12(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 12;
            this.eot = DFA12_eot;
            this.eof = DFA12_eof;
            this.min = DFA12_min;
            this.max = DFA12_max;
            this.accept = DFA12_accept;
            this.special = DFA12_special;
            this.transition = DFA12_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | RULE_NUMERAL | RULE_STRING | RULE_DIGIT | RULE_LOWER_CASE_LETTER | RULE_UPPER_CASE_LETTER | RULE_SPECIAL | RULE_CHARACTER | RULE_ID | RULE_INT | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA12_0 = input.LA(1);

                        s = -1;
                        if ( (LA12_0==':') ) {s = 1;}

                        else if ( (LA12_0==',') ) {s = 2;}

                        else if ( (LA12_0=='.') ) {s = 3;}

                        else if ( (LA12_0=='?') ) {s = 4;}

                        else if ( (LA12_0=='(') ) {s = 5;}

                        else if ( (LA12_0==')') ) {s = 6;}

                        else if ( (LA12_0=='-') ) {s = 7;}

                        else if ( ((LA12_0>='0' && LA12_0<='9')) ) {s = 8;}

                        else if ( (LA12_0=='\'') ) {s = 9;}

                        else if ( ((LA12_0>='a' && LA12_0<='z')) ) {s = 10;}

                        else if ( ((LA12_0>='A' && LA12_0<='Z')||LA12_0=='_') ) {s = 11;}

                        else if ( (LA12_0=='^') ) {s = 12;}

                        else if ( (LA12_0=='/') ) {s = 13;}

                        else if ( ((LA12_0>='#' && LA12_0<='$')||LA12_0=='&'||(LA12_0>='*' && LA12_0<='+')||LA12_0=='@'||LA12_0=='\\'||LA12_0=='~') ) {s = 14;}

                        else if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {s = 15;}

                        else if ( ((LA12_0>='\u0000' && LA12_0<='\b')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\u001F')||(LA12_0>='!' && LA12_0<='\"')||LA12_0=='%'||(LA12_0>=';' && LA12_0<='>')||LA12_0=='['||LA12_0==']'||LA12_0=='`'||(LA12_0>='{' && LA12_0<='}')||(LA12_0>='\u007F' && LA12_0<='\uFFFF')) ) {s = 16;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 12, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}