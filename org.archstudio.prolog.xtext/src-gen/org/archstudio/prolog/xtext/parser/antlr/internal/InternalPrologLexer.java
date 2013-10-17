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
    public static final int RULE_OP400YFX=18;
    public static final int RULE_EXT_INT=22;
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
    public static final int T__32=32;
    public static final int RULE_STRING=24;
    public static final int T__33=33;
    public static final int T__34=34;
    public static final int RULE_OP600XFY=15;
    public static final int RULE_OP900FY=12;
    public static final int RULE_VARIABLE=23;
    public static final int RULE_OP900FX=13;
    public static final int RULE_OP200XFY=20;
    public static final int RULE_OP200XFX=19;
    public static final int RULE_OP1100XFY=8;
    public static final int RULE_INT=21;
    public static final int RULE_OP1000XFY=10;
    public static final int RULE_OP1150FX=7;
    public static final int RULE_WHITESPACE=25;
    public static final int RULE_OP500FX=17;

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

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:11:7: ( '.' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:11:9: '.'
            {
            match('.'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:12:7: ( '!' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:12:9: '!'
            {
            match('!'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:13:7: ( '-' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:13:9: '-'
            {
            match('-'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:14:7: ( '(' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:14:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:15:7: ( ')' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:15:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "T__32"
    public final void mT__32() throws RecognitionException {
        try {
            int _type = T__32;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:16:7: ( '[' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:16:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__32"

    // $ANTLR start "T__33"
    public final void mT__33() throws RecognitionException {
        try {
            int _type = T__33;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:17:7: ( '|' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:17:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__33"

    // $ANTLR start "T__34"
    public final void mT__34() throws RecognitionException {
        try {
            int _type = T__34;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:18:7: ( ']' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:18:9: ']'
            {
            match(']'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__34"

    // $ANTLR start "RULE_WHITESPACE"
    public final void mRULE_WHITESPACE() throws RecognitionException {
        try {
            int _type = RULE_WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1782:17: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1782:19: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1782:19: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='\t' && LA1_0<='\n')||LA1_0=='\r'||LA1_0==' ') ) {
                    alt1=1;
                }


                switch (alt1) {
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
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WHITESPACE"

    // $ANTLR start "RULE_SINGLE_LINE_COMMENT"
    public final void mRULE_SINGLE_LINE_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SINGLE_LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1784:26: ( '%' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1784:28: '%' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match('%'); 
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1784:32: (~ ( ( '\\n' | '\\r' ) ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='\u0000' && LA2_0<='\t')||(LA2_0>='\u000B' && LA2_0<='\f')||(LA2_0>='\u000E' && LA2_0<='\uFFFF')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1784:32: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop2;
                }
            } while (true);

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1784:48: ( ( '\\r' )? '\\n' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='\n'||LA4_0=='\r') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1784:49: ( '\\r' )? '\\n'
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1784:49: ( '\\r' )?
                    int alt3=2;
                    int LA3_0 = input.LA(1);

                    if ( (LA3_0=='\r') ) {
                        alt3=1;
                    }
                    switch (alt3) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1784:49: '\\r'
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
    // $ANTLR end "RULE_SINGLE_LINE_COMMENT"

    // $ANTLR start "RULE_OP1200XFX"
    public final void mRULE_OP1200XFX() throws RecognitionException {
        try {
            int _type = RULE_OP1200XFX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1786:16: ( ( '-->' | ':-' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1786:18: ( '-->' | ':-' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1786:18: ( '-->' | ':-' )
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0=='-') ) {
                alt5=1;
            }
            else if ( (LA5_0==':') ) {
                alt5=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }
            switch (alt5) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1786:19: '-->'
                    {
                    match("-->"); 


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1786:25: ':-'
                    {
                    match(":-"); 


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
    // $ANTLR end "RULE_OP1200XFX"

    // $ANTLR start "RULE_OP1200FX"
    public final void mRULE_OP1200FX() throws RecognitionException {
        try {
            int _type = RULE_OP1200FX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1788:15: ( ( ':-' | '?-' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1788:17: ( ':-' | '?-' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1788:17: ( ':-' | '?-' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0==':') ) {
                alt6=1;
            }
            else if ( (LA6_0=='?') ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1788:18: ':-'
                    {
                    match(":-"); 


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1788:23: '?-'
                    {
                    match("?-"); 


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
    // $ANTLR end "RULE_OP1200FX"

    // $ANTLR start "RULE_OP1150FX"
    public final void mRULE_OP1150FX() throws RecognitionException {
        try {
            int _type = RULE_OP1150FX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1790:15: ( ( 'dynamic' | 'discontiguous' | 'initialization' | 'module_transparent' | 'multifile' | 'thread_local' | 'volatile' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1790:17: ( 'dynamic' | 'discontiguous' | 'initialization' | 'module_transparent' | 'multifile' | 'thread_local' | 'volatile' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1790:17: ( 'dynamic' | 'discontiguous' | 'initialization' | 'module_transparent' | 'multifile' | 'thread_local' | 'volatile' )
            int alt7=7;
            alt7 = dfa7.predict(input);
            switch (alt7) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1790:18: 'dynamic'
                    {
                    match("dynamic"); 


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1790:28: 'discontiguous'
                    {
                    match("discontiguous"); 


                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1790:44: 'initialization'
                    {
                    match("initialization"); 


                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1790:61: 'module_transparent'
                    {
                    match("module_transparent"); 


                    }
                    break;
                case 5 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1790:82: 'multifile'
                    {
                    match("multifile"); 


                    }
                    break;
                case 6 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1790:94: 'thread_local'
                    {
                    match("thread_local"); 


                    }
                    break;
                case 7 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1790:109: 'volatile'
                    {
                    match("volatile"); 


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
    // $ANTLR end "RULE_OP1150FX"

    // $ANTLR start "RULE_OP1100XFY"
    public final void mRULE_OP1100XFY() throws RecognitionException {
        try {
            int _type = RULE_OP1100XFY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1792:16: ( ( ';' | '|' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1792:18: ( ';' | '|' )
            {
            if ( input.LA(1)==';'||input.LA(1)=='|' ) {
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
    // $ANTLR end "RULE_OP1100XFY"

    // $ANTLR start "RULE_OP1050XFY"
    public final void mRULE_OP1050XFY() throws RecognitionException {
        try {
            int _type = RULE_OP1050XFY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1794:16: ( ( '->' | '*->' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1794:18: ( '->' | '*->' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1794:18: ( '->' | '*->' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='-') ) {
                alt8=1;
            }
            else if ( (LA8_0=='*') ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1794:19: '->'
                    {
                    match("->"); 


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1794:24: '*->'
                    {
                    match("*->"); 


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
    // $ANTLR end "RULE_OP1050XFY"

    // $ANTLR start "RULE_OP1000XFY"
    public final void mRULE_OP1000XFY() throws RecognitionException {
        try {
            int _type = RULE_OP1000XFY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1796:16: ( ',' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1796:18: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_OP1000XFY"

    // $ANTLR start "RULE_OP954XFY"
    public final void mRULE_OP954XFY() throws RecognitionException {
        try {
            int _type = RULE_OP954XFY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1798:15: ( '\\\\' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1798:17: '\\\\'
            {
            match('\\'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_OP954XFY"

    // $ANTLR start "RULE_OP900FY"
    public final void mRULE_OP900FY() throws RecognitionException {
        try {
            int _type = RULE_OP900FY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1800:14: ( '\\\\+' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1800:16: '\\\\+'
            {
            match("\\+"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_OP900FY"

    // $ANTLR start "RULE_OP900FX"
    public final void mRULE_OP900FX() throws RecognitionException {
        try {
            int _type = RULE_OP900FX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1802:14: ( '~' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1802:16: '~'
            {
            match('~'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_OP900FX"

    // $ANTLR start "RULE_OP700XFX"
    public final void mRULE_OP700XFX() throws RecognitionException {
        try {
            int _type = RULE_OP700XFX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:15: ( ( '<' | '=' | '=..' | '=@=' | '=:=' | '=<' | '==' | '=\\\\=' | '>' | '>=' | '@<' | '@=<' | '@>' | '@>=' | '\\\\=' | '\\\\==' | 'is' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:17: ( '<' | '=' | '=..' | '=@=' | '=:=' | '=<' | '==' | '=\\\\=' | '>' | '>=' | '@<' | '@=<' | '@>' | '@>=' | '\\\\=' | '\\\\==' | 'is' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:17: ( '<' | '=' | '=..' | '=@=' | '=:=' | '=<' | '==' | '=\\\\=' | '>' | '>=' | '@<' | '@=<' | '@>' | '@>=' | '\\\\=' | '\\\\==' | 'is' )
            int alt9=17;
            alt9 = dfa9.predict(input);
            switch (alt9) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:18: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:22: '='
                    {
                    match('='); 

                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:26: '=..'
                    {
                    match("=.."); 


                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:32: '=@='
                    {
                    match("=@="); 


                    }
                    break;
                case 5 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:38: '=:='
                    {
                    match("=:="); 


                    }
                    break;
                case 6 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:44: '=<'
                    {
                    match("=<"); 


                    }
                    break;
                case 7 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:49: '=='
                    {
                    match("=="); 


                    }
                    break;
                case 8 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:54: '=\\\\='
                    {
                    match("=\\="); 


                    }
                    break;
                case 9 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:61: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 10 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:65: '>='
                    {
                    match(">="); 


                    }
                    break;
                case 11 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:70: '@<'
                    {
                    match("@<"); 


                    }
                    break;
                case 12 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:75: '@=<'
                    {
                    match("@=<"); 


                    }
                    break;
                case 13 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:81: '@>'
                    {
                    match("@>"); 


                    }
                    break;
                case 14 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:86: '@>='
                    {
                    match("@>="); 


                    }
                    break;
                case 15 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:92: '\\\\='
                    {
                    match("\\="); 


                    }
                    break;
                case 16 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:98: '\\\\=='
                    {
                    match("\\=="); 


                    }
                    break;
                case 17 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1804:105: 'is'
                    {
                    match("is"); 


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
    // $ANTLR end "RULE_OP700XFX"

    // $ANTLR start "RULE_OP600XFY"
    public final void mRULE_OP600XFY() throws RecognitionException {
        try {
            int _type = RULE_OP600XFY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1806:15: ( ':' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1806:17: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_OP600XFY"

    // $ANTLR start "RULE_OP500YFX"
    public final void mRULE_OP500YFX() throws RecognitionException {
        try {
            int _type = RULE_OP500YFX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1808:15: ( ( '+' | '-' | '/\\\\' | '\\\\/' | 'xor' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1808:17: ( '+' | '-' | '/\\\\' | '\\\\/' | 'xor' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1808:17: ( '+' | '-' | '/\\\\' | '\\\\/' | 'xor' )
            int alt10=5;
            switch ( input.LA(1) ) {
            case '+':
                {
                alt10=1;
                }
                break;
            case '-':
                {
                alt10=2;
                }
                break;
            case '/':
                {
                alt10=3;
                }
                break;
            case '\\':
                {
                alt10=4;
                }
                break;
            case 'x':
                {
                alt10=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1808:18: '+'
                    {
                    match('+'); 

                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1808:22: '-'
                    {
                    match('-'); 

                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1808:26: '/\\\\'
                    {
                    match("/\\"); 


                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1808:32: '\\\\/'
                    {
                    match("\\/"); 


                    }
                    break;
                case 5 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1808:38: 'xor'
                    {
                    match("xor"); 


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
    // $ANTLR end "RULE_OP500YFX"

    // $ANTLR start "RULE_OP500FX"
    public final void mRULE_OP500FX() throws RecognitionException {
        try {
            int _type = RULE_OP500FX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1810:14: ( ( '+' | '-' | '?' | '\\\\' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1810:16: ( '+' | '-' | '?' | '\\\\' )
            {
            if ( input.LA(1)=='+'||input.LA(1)=='-'||input.LA(1)=='?'||input.LA(1)=='\\' ) {
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
    // $ANTLR end "RULE_OP500FX"

    // $ANTLR start "RULE_OP400YFX"
    public final void mRULE_OP400YFX() throws RecognitionException {
        try {
            int _type = RULE_OP400YFX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1812:15: ( ( '*' | '/' | '//' | 'rdiv' | '<<' | '>>' | 'mod' | 'rem' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1812:17: ( '*' | '/' | '//' | 'rdiv' | '<<' | '>>' | 'mod' | 'rem' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1812:17: ( '*' | '/' | '//' | 'rdiv' | '<<' | '>>' | 'mod' | 'rem' )
            int alt11=8;
            alt11 = dfa11.predict(input);
            switch (alt11) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1812:18: '*'
                    {
                    match('*'); 

                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1812:22: '/'
                    {
                    match('/'); 

                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1812:26: '//'
                    {
                    match("//"); 


                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1812:31: 'rdiv'
                    {
                    match("rdiv"); 


                    }
                    break;
                case 5 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1812:38: '<<'
                    {
                    match("<<"); 


                    }
                    break;
                case 6 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1812:43: '>>'
                    {
                    match(">>"); 


                    }
                    break;
                case 7 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1812:48: 'mod'
                    {
                    match("mod"); 


                    }
                    break;
                case 8 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1812:54: 'rem'
                    {
                    match("rem"); 


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
    // $ANTLR end "RULE_OP400YFX"

    // $ANTLR start "RULE_OP200XFX"
    public final void mRULE_OP200XFX() throws RecognitionException {
        try {
            int _type = RULE_OP200XFX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1814:15: ( '**' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1814:17: '**'
            {
            match("**"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_OP200XFX"

    // $ANTLR start "RULE_OP200XFY"
    public final void mRULE_OP200XFY() throws RecognitionException {
        try {
            int _type = RULE_OP200XFY;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1816:15: ( '^' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1816:17: '^'
            {
            match('^'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_OP200XFY"

    // $ANTLR start "RULE_ATOM"
    public final void mRULE_ATOM() throws RecognitionException {
        try {
            int _type = RULE_ATOM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1818:11: ( 'a' .. 'z' ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1818:13: 'a' .. 'z' ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' )*
            {
            matchRange('a','z'); 
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1818:22: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='0' && LA12_0<='9')||(LA12_0>='A' && LA12_0<='Z')||LA12_0=='_'||(LA12_0>='a' && LA12_0<='z')) ) {
                    alt12=1;
                }


                switch (alt12) {
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
            	    break loop12;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ATOM"

    // $ANTLR start "RULE_VARIABLE"
    public final void mRULE_VARIABLE() throws RecognitionException {
        try {
            int _type = RULE_VARIABLE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1820:15: ( ( 'A' .. 'Z' | '_' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1820:17: ( 'A' .. 'Z' | '_' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1820:32: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '_' )*
            loop13:
            do {
                int alt13=2;
                int LA13_0 = input.LA(1);

                if ( ((LA13_0>='0' && LA13_0<='9')||(LA13_0>='A' && LA13_0<='Z')||LA13_0=='_'||(LA13_0>='a' && LA13_0<='z')) ) {
                    alt13=1;
                }


                switch (alt13) {
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
            	    break loop13;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_VARIABLE"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1822:13: ( '\\'' ( options {greedy=false; } : . )* '\\'' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1822:15: '\\'' ( options {greedy=false; } : . )* '\\''
            {
            match('\''); 
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1822:20: ( options {greedy=false; } : . )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0=='\'') ) {
                    alt14=2;
                }
                else if ( ((LA14_0>='\u0000' && LA14_0<='&')||(LA14_0>='(' && LA14_0<='\uFFFF')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1822:48: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop14;
                }
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

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1824:10: ( ( '0' .. '9' )+ )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1824:12: ( '0' .. '9' )+
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1824:12: ( '0' .. '9' )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='0' && LA15_0<='9')) ) {
                    alt15=1;
                }


                switch (alt15) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1824:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_EXT_INT"
    public final void mRULE_EXT_INT() throws RecognitionException {
        try {
            int _type = RULE_EXT_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1826:14: ( RULE_INT ( 'e' | 'E' ) ( '-' | '+' )? RULE_INT )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1826:16: RULE_INT ( 'e' | 'E' ) ( '-' | '+' )? RULE_INT
            {
            mRULE_INT(); 
            if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1826:35: ( '-' | '+' )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0=='+'||LA16_0=='-') ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:
                    {
                    if ( input.LA(1)=='+'||input.LA(1)=='-' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}


                    }
                    break;

            }

            mRULE_INT(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_EXT_INT"

    public void mTokens() throws RecognitionException {
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:8: ( T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | RULE_WHITESPACE | RULE_SINGLE_LINE_COMMENT | RULE_OP1200XFX | RULE_OP1200FX | RULE_OP1150FX | RULE_OP1100XFY | RULE_OP1050XFY | RULE_OP1000XFY | RULE_OP954XFY | RULE_OP900FY | RULE_OP900FX | RULE_OP700XFX | RULE_OP600XFY | RULE_OP500YFX | RULE_OP500FX | RULE_OP400YFX | RULE_OP200XFX | RULE_OP200XFY | RULE_ATOM | RULE_VARIABLE | RULE_STRING | RULE_INT | RULE_EXT_INT )
        int alt17=31;
        alt17 = dfa17.predict(input);
        switch (alt17) {
            case 1 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:10: T__27
                {
                mT__27(); 

                }
                break;
            case 2 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:16: T__28
                {
                mT__28(); 

                }
                break;
            case 3 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:22: T__29
                {
                mT__29(); 

                }
                break;
            case 4 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:28: T__30
                {
                mT__30(); 

                }
                break;
            case 5 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:34: T__31
                {
                mT__31(); 

                }
                break;
            case 6 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:40: T__32
                {
                mT__32(); 

                }
                break;
            case 7 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:46: T__33
                {
                mT__33(); 

                }
                break;
            case 8 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:52: T__34
                {
                mT__34(); 

                }
                break;
            case 9 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:58: RULE_WHITESPACE
                {
                mRULE_WHITESPACE(); 

                }
                break;
            case 10 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:74: RULE_SINGLE_LINE_COMMENT
                {
                mRULE_SINGLE_LINE_COMMENT(); 

                }
                break;
            case 11 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:99: RULE_OP1200XFX
                {
                mRULE_OP1200XFX(); 

                }
                break;
            case 12 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:114: RULE_OP1200FX
                {
                mRULE_OP1200FX(); 

                }
                break;
            case 13 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:128: RULE_OP1150FX
                {
                mRULE_OP1150FX(); 

                }
                break;
            case 14 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:142: RULE_OP1100XFY
                {
                mRULE_OP1100XFY(); 

                }
                break;
            case 15 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:157: RULE_OP1050XFY
                {
                mRULE_OP1050XFY(); 

                }
                break;
            case 16 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:172: RULE_OP1000XFY
                {
                mRULE_OP1000XFY(); 

                }
                break;
            case 17 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:187: RULE_OP954XFY
                {
                mRULE_OP954XFY(); 

                }
                break;
            case 18 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:201: RULE_OP900FY
                {
                mRULE_OP900FY(); 

                }
                break;
            case 19 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:214: RULE_OP900FX
                {
                mRULE_OP900FX(); 

                }
                break;
            case 20 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:227: RULE_OP700XFX
                {
                mRULE_OP700XFX(); 

                }
                break;
            case 21 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:241: RULE_OP600XFY
                {
                mRULE_OP600XFY(); 

                }
                break;
            case 22 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:255: RULE_OP500YFX
                {
                mRULE_OP500YFX(); 

                }
                break;
            case 23 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:269: RULE_OP500FX
                {
                mRULE_OP500FX(); 

                }
                break;
            case 24 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:282: RULE_OP400YFX
                {
                mRULE_OP400YFX(); 

                }
                break;
            case 25 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:296: RULE_OP200XFX
                {
                mRULE_OP200XFX(); 

                }
                break;
            case 26 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:310: RULE_OP200XFY
                {
                mRULE_OP200XFY(); 

                }
                break;
            case 27 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:324: RULE_ATOM
                {
                mRULE_ATOM(); 

                }
                break;
            case 28 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:334: RULE_VARIABLE
                {
                mRULE_VARIABLE(); 

                }
                break;
            case 29 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:348: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 30 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:360: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 31 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:369: RULE_EXT_INT
                {
                mRULE_EXT_INT(); 

                }
                break;

        }

    }


    protected DFA7 dfa7 = new DFA7(this);
    protected DFA9 dfa9 = new DFA9(this);
    protected DFA11 dfa11 = new DFA11(this);
    protected DFA17 dfa17 = new DFA17(this);
    static final String DFA7_eotS =
        "\12\uffff";
    static final String DFA7_eofS =
        "\12\uffff";
    static final String DFA7_minS =
        "\1\144\1\151\1\uffff\1\157\6\uffff";
    static final String DFA7_maxS =
        "\1\166\1\171\1\uffff\1\165\6\uffff";
    static final String DFA7_acceptS =
        "\2\uffff\1\3\1\uffff\1\6\1\7\1\1\1\2\1\4\1\5";
    static final String DFA7_specialS =
        "\12\uffff}>";
    static final String[] DFA7_transitionS = {
            "\1\1\4\uffff\1\2\3\uffff\1\3\6\uffff\1\4\1\uffff\1\5",
            "\1\7\17\uffff\1\6",
            "",
            "\1\10\5\uffff\1\11",
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
            return "1790:17: ( 'dynamic' | 'discontiguous' | 'initialization' | 'module_transparent' | 'multifile' | 'thread_local' | 'volatile' )";
        }
    }
    static final String DFA9_eotS =
        "\2\uffff\1\15\1\17\16\uffff\1\25\1\27\4\uffff";
    static final String DFA9_eofS =
        "\30\uffff";
    static final String DFA9_minS =
        "\1\74\1\uffff\1\56\1\75\1\74\1\75\14\uffff\2\75\4\uffff";
    static final String DFA9_maxS =
        "\1\151\1\uffff\1\134\1\75\1\76\1\75\14\uffff\2\75\4\uffff";
    static final String DFA9_acceptS =
        "\1\uffff\1\1\4\uffff\1\21\1\3\1\4\1\5\1\6\1\7\1\10\1\2\1\12\1\11"+
        "\1\13\1\14\2\uffff\1\16\1\15\1\20\1\17";
    static final String DFA9_specialS =
        "\30\uffff}>";
    static final String[] DFA9_transitionS = {
            "\1\1\1\2\1\3\1\uffff\1\4\33\uffff\1\5\14\uffff\1\6",
            "",
            "\1\7\13\uffff\1\11\1\uffff\1\12\1\13\2\uffff\1\10\33\uffff"+
            "\1\14",
            "\1\16",
            "\1\20\1\21\1\22",
            "\1\23",
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
            "\1\24",
            "\1\26",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA9_eot = DFA.unpackEncodedString(DFA9_eotS);
    static final short[] DFA9_eof = DFA.unpackEncodedString(DFA9_eofS);
    static final char[] DFA9_min = DFA.unpackEncodedStringToUnsignedChars(DFA9_minS);
    static final char[] DFA9_max = DFA.unpackEncodedStringToUnsignedChars(DFA9_maxS);
    static final short[] DFA9_accept = DFA.unpackEncodedString(DFA9_acceptS);
    static final short[] DFA9_special = DFA.unpackEncodedString(DFA9_specialS);
    static final short[][] DFA9_transition;

    static {
        int numStates = DFA9_transitionS.length;
        DFA9_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA9_transition[i] = DFA.unpackEncodedString(DFA9_transitionS[i]);
        }
    }

    class DFA9 extends DFA {

        public DFA9(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 9;
            this.eot = DFA9_eot;
            this.eof = DFA9_eof;
            this.min = DFA9_min;
            this.max = DFA9_max;
            this.accept = DFA9_accept;
            this.special = DFA9_special;
            this.transition = DFA9_transition;
        }
        public String getDescription() {
            return "1804:17: ( '<' | '=' | '=..' | '=@=' | '=:=' | '=<' | '==' | '=\\\\=' | '>' | '>=' | '@<' | '@=<' | '@>' | '@>=' | '\\\\=' | '\\\\==' | 'is' )";
        }
    }
    static final String DFA11_eotS =
        "\2\uffff\1\10\10\uffff";
    static final String DFA11_eofS =
        "\13\uffff";
    static final String DFA11_minS =
        "\1\52\1\uffff\1\57\1\144\7\uffff";
    static final String DFA11_maxS =
        "\1\162\1\uffff\1\57\1\145\7\uffff";
    static final String DFA11_acceptS =
        "\1\uffff\1\1\2\uffff\1\5\1\6\1\7\1\3\1\2\1\4\1\10";
    static final String DFA11_specialS =
        "\13\uffff}>";
    static final String[] DFA11_transitionS = {
            "\1\1\4\uffff\1\2\14\uffff\1\4\1\uffff\1\5\56\uffff\1\6\4\uffff"+
            "\1\3",
            "",
            "\1\7",
            "\1\11\1\12",
            "",
            "",
            "",
            "",
            "",
            "",
            ""
    };

    static final short[] DFA11_eot = DFA.unpackEncodedString(DFA11_eotS);
    static final short[] DFA11_eof = DFA.unpackEncodedString(DFA11_eofS);
    static final char[] DFA11_min = DFA.unpackEncodedStringToUnsignedChars(DFA11_minS);
    static final char[] DFA11_max = DFA.unpackEncodedStringToUnsignedChars(DFA11_maxS);
    static final short[] DFA11_accept = DFA.unpackEncodedString(DFA11_acceptS);
    static final short[] DFA11_special = DFA.unpackEncodedString(DFA11_specialS);
    static final short[][] DFA11_transition;

    static {
        int numStates = DFA11_transitionS.length;
        DFA11_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA11_transition[i] = DFA.unpackEncodedString(DFA11_transitionS[i]);
        }
    }

    class DFA11 extends DFA {

        public DFA11(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 11;
            this.eot = DFA11_eot;
            this.eof = DFA11_eof;
            this.min = DFA11_min;
            this.max = DFA11_max;
            this.accept = DFA11_accept;
            this.special = DFA11_special;
            this.transition = DFA11_transition;
        }
        public String getDescription() {
            return "1812:17: ( '*' | '/' | '//' | 'rdiv' | '<<' | '>>' | 'mod' | 'rem' )";
        }
    }
    static final String DFA17_eotS =
        "\3\uffff\1\45\7\uffff\1\50\1\52\5\37\1\uffff\1\64\1\uffff\1\67"+
        "\1\uffff\1\30\1\uffff\1\30\1\uffff\1\64\2\37\4\uffff\1\73\10\uffff"+
        "\3\37\1\30\4\37\5\uffff\3\37\2\uffff\3\37\1\64\3\37\1\66\1\37\1"+
        "\64\7\37\1\64\16\37\1\144\6\37\1\uffff\5\37\1\144\3\37\1\144\14"+
        "\37\2\144\2\37\1\144\4\37\1\144";
    static final String DFA17_eofS =
        "\u0085\uffff";
    static final String DFA17_minS =
        "\1\11\2\uffff\1\55\7\uffff\2\55\1\151\1\156\1\157\1\150\1\157\1"+
        "\uffff\1\52\1\uffff\1\53\1\uffff\1\74\1\uffff\1\76\1\uffff\1\134"+
        "\1\157\1\144\4\uffff\1\60\10\uffff\1\156\1\163\1\151\1\60\1\144"+
        "\1\154\1\162\1\154\5\uffff\1\162\1\151\1\155\2\uffff\1\141\1\143"+
        "\1\164\1\60\1\164\1\145\1\141\1\60\1\166\1\60\1\155\1\157\1\151"+
        "\1\154\1\151\1\141\1\164\1\60\1\151\1\156\1\141\1\145\1\146\1\144"+
        "\1\151\1\143\1\164\1\154\1\137\1\151\1\137\1\154\1\60\2\151\1\164"+
        "\2\154\1\145\1\uffff\1\147\1\172\1\162\1\145\1\157\1\60\1\165\2"+
        "\141\1\60\1\143\1\157\1\164\1\156\1\141\1\165\1\151\1\163\1\154"+
        "\1\163\1\157\1\160\2\60\1\156\1\141\1\60\1\162\1\145\1\156\1\164"+
        "\1\60";
    static final String DFA17_maxS =
        "\1\176\2\uffff\1\76\7\uffff\2\55\1\171\1\163\1\165\1\150\1\157"+
        "\1\uffff\1\55\1\uffff\1\75\1\uffff\1\74\1\uffff\1\76\1\uffff\1\134"+
        "\1\157\1\145\4\uffff\1\145\10\uffff\1\156\1\163\1\151\1\172\1\144"+
        "\1\154\1\162\1\154\5\uffff\1\162\1\151\1\155\2\uffff\1\141\1\143"+
        "\1\164\1\172\1\164\1\145\1\141\1\172\1\166\1\172\1\155\1\157\1\151"+
        "\1\154\1\151\1\141\1\164\1\172\1\151\1\156\1\141\1\145\1\146\1\144"+
        "\1\151\1\143\1\164\1\154\1\137\1\151\1\137\1\154\1\172\2\151\1\164"+
        "\2\154\1\145\1\uffff\1\147\1\172\1\162\1\145\1\157\1\172\1\165\2"+
        "\141\1\172\1\143\1\157\1\164\1\156\1\141\1\165\1\151\1\163\1\154"+
        "\1\163\1\157\1\160\2\172\1\156\1\141\1\172\1\162\1\145\1\156\1\164"+
        "\1\172";
    static final String DFA17_acceptS =
        "\1\uffff\1\1\1\2\1\uffff\1\4\1\5\1\6\1\7\1\10\1\11\1\12\7\uffff"+
        "\1\16\1\uffff\1\20\1\uffff\1\23\1\uffff\1\24\1\uffff\1\26\3\uffff"+
        "\1\32\1\33\1\34\1\35\1\uffff\1\13\1\17\1\3\1\7\1\13\1\25\1\14\1"+
        "\27\10\uffff\1\31\1\30\1\22\1\26\1\21\3\uffff\1\36\1\37\47\uffff"+
        "\1\15\40\uffff";
    static final String DFA17_specialS =
        "\u0085\uffff}>";
    static final String[] DFA17_transitionS = {
            "\2\11\2\uffff\1\11\22\uffff\1\11\1\2\3\uffff\1\12\1\uffff\1"+
            "\41\1\4\1\5\1\23\1\32\1\24\1\3\1\1\1\33\12\42\1\13\1\22\1\27"+
            "\1\30\1\31\1\14\1\30\32\40\1\6\1\25\1\10\1\36\1\40\1\uffff\3"+
            "\37\1\15\4\37\1\16\3\37\1\17\4\37\1\35\1\37\1\20\1\37\1\21\1"+
            "\37\1\34\2\37\1\uffff\1\7\1\uffff\1\26",
            "",
            "",
            "\1\43\20\uffff\1\44",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\47",
            "\1\51",
            "\1\54\17\uffff\1\53",
            "\1\55\4\uffff\1\56",
            "\1\57\5\uffff\1\60",
            "\1\61",
            "\1\62",
            "",
            "\1\63\2\uffff\1\44",
            "",
            "\1\65\3\uffff\1\66\15\uffff\1\30",
            "",
            "\1\64",
            "",
            "\1\64",
            "",
            "\1\66",
            "\1\70",
            "\1\71\1\72",
            "",
            "",
            "",
            "",
            "\12\42\13\uffff\1\74\37\uffff\1\74",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\75",
            "\1\76",
            "\1\77",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "",
            "",
            "",
            "",
            "",
            "\1\104",
            "\1\105",
            "\1\106",
            "",
            "",
            "\1\107",
            "\1\110",
            "\1\111",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\24\37\1\112\5\37",
            "\1\113",
            "\1\114",
            "\1\115",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\116",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\126",
            "\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\143",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\145",
            "\1\146",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "",
            "\1\153",
            "\1\154",
            "\1\155",
            "\1\156",
            "\1\157",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\160",
            "\1\161",
            "\1\162",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\163",
            "\1\164",
            "\1\165",
            "\1\166",
            "\1\167",
            "\1\170",
            "\1\171",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\175",
            "\1\176",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\177",
            "\1\u0080",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37",
            "\1\u0081",
            "\1\u0082",
            "\1\u0083",
            "\1\u0084",
            "\12\37\7\uffff\32\37\4\uffff\1\37\1\uffff\32\37"
    };

    static final short[] DFA17_eot = DFA.unpackEncodedString(DFA17_eotS);
    static final short[] DFA17_eof = DFA.unpackEncodedString(DFA17_eofS);
    static final char[] DFA17_min = DFA.unpackEncodedStringToUnsignedChars(DFA17_minS);
    static final char[] DFA17_max = DFA.unpackEncodedStringToUnsignedChars(DFA17_maxS);
    static final short[] DFA17_accept = DFA.unpackEncodedString(DFA17_acceptS);
    static final short[] DFA17_special = DFA.unpackEncodedString(DFA17_specialS);
    static final short[][] DFA17_transition;

    static {
        int numStates = DFA17_transitionS.length;
        DFA17_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA17_transition[i] = DFA.unpackEncodedString(DFA17_transitionS[i]);
        }
    }

    class DFA17 extends DFA {

        public DFA17(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 17;
            this.eot = DFA17_eot;
            this.eof = DFA17_eof;
            this.min = DFA17_min;
            this.max = DFA17_max;
            this.accept = DFA17_accept;
            this.special = DFA17_special;
            this.transition = DFA17_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | RULE_WHITESPACE | RULE_SINGLE_LINE_COMMENT | RULE_OP1200XFX | RULE_OP1200FX | RULE_OP1150FX | RULE_OP1100XFY | RULE_OP1050XFY | RULE_OP1000XFY | RULE_OP954XFY | RULE_OP900FY | RULE_OP900FX | RULE_OP700XFX | RULE_OP600XFY | RULE_OP500YFX | RULE_OP500FX | RULE_OP400YFX | RULE_OP200XFX | RULE_OP200XFY | RULE_ATOM | RULE_VARIABLE | RULE_STRING | RULE_INT | RULE_EXT_INT );";
        }
    }
 

}