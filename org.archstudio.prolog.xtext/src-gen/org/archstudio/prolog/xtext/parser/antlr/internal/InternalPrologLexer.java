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
    public static final int EOF=-1;
    public static final int RULE_ATOM=15;
    public static final int RULE_EXPRESSION_900FX=9;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_EXPRESSION_200=14;
    public static final int RULE_STRING=17;
    public static final int RULE_VARIABLE=16;
    public static final int RULE_EXPRESSION_1000=8;
    public static final int RULE_EXPRESSION_1100=6;
    public static final int RULE_WHITESPACE=19;
    public static final int RULE_DIGIT=18;
    public static final int RULE_EXPRESSION_1050=7;
    public static final int RULE_EXPRESSION_700=10;

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

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
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
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
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
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:13:7: ( '(' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:13:9: '('
            {
            match('('); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:14:7: ( ')' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:14:9: ')'
            {
            match(')'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:15:7: ( '[' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:15:9: '['
            {
            match('['); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:16:7: ( '|' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:16:9: '|'
            {
            match('|'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:17:7: ( ']' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:17:9: ']'
            {
            match(']'); 

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
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:18:7: ( '-' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:18:9: '-'
            {
            match('-'); 

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
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:19:7: ( 'e' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:19:9: 'e'
            {
            match('e'); 

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
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:20:7: ( 'E' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:20:9: 'E'
            {
            match('E'); 

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
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:21:7: ( '+' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:21:9: '+'
            {
            match('+'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "RULE_EXPRESSION_1200"
    public final void mRULE_EXPRESSION_1200() throws RecognitionException {
        try {
            int _type = RULE_EXPRESSION_1200;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1440:22: ( ( '-->' | ':-' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1440:24: ( '-->' | ':-' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1440:24: ( '-->' | ':-' )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            else if ( (LA1_0==':') ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1440:25: '-->'
                    {
                    match("-->"); 


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1440:31: ':-'
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
    // $ANTLR end "RULE_EXPRESSION_1200"

    // $ANTLR start "RULE_EXPRESSION_1200FX"
    public final void mRULE_EXPRESSION_1200FX() throws RecognitionException {
        try {
            int _type = RULE_EXPRESSION_1200FX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1442:24: ( '?-' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1442:26: '?-'
            {
            match("?-"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_EXPRESSION_1200FX"

    // $ANTLR start "RULE_EXPRESSION_1100"
    public final void mRULE_EXPRESSION_1100() throws RecognitionException {
        try {
            int _type = RULE_EXPRESSION_1100;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1444:22: ( ( ';' | '|' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1444:24: ( ';' | '|' )
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
    // $ANTLR end "RULE_EXPRESSION_1100"

    // $ANTLR start "RULE_EXPRESSION_1050"
    public final void mRULE_EXPRESSION_1050() throws RecognitionException {
        try {
            int _type = RULE_EXPRESSION_1050;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1446:22: ( ( '->' | '*->' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1446:24: ( '->' | '*->' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1446:24: ( '->' | '*->' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='-') ) {
                alt2=1;
            }
            else if ( (LA2_0=='*') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1446:25: '->'
                    {
                    match("->"); 


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1446:30: '*->'
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
    // $ANTLR end "RULE_EXPRESSION_1050"

    // $ANTLR start "RULE_EXPRESSION_1000"
    public final void mRULE_EXPRESSION_1000() throws RecognitionException {
        try {
            int _type = RULE_EXPRESSION_1000;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1448:22: ( ',' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1448:24: ','
            {
            match(','); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_EXPRESSION_1000"

    // $ANTLR start "RULE_EXPRESSION_900FX"
    public final void mRULE_EXPRESSION_900FX() throws RecognitionException {
        try {
            int _type = RULE_EXPRESSION_900FX;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1450:23: ( ( '\\\\+' | 'not' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1450:25: ( '\\\\+' | 'not' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1450:25: ( '\\\\+' | 'not' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='\\') ) {
                alt3=1;
            }
            else if ( (LA3_0=='n') ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1450:26: '\\\\+'
                    {
                    match("\\+"); 


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1450:32: 'not'
                    {
                    match("not"); 


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
    // $ANTLR end "RULE_EXPRESSION_900FX"

    // $ANTLR start "RULE_EXPRESSION_700"
    public final void mRULE_EXPRESSION_700() throws RecognitionException {
        try {
            int _type = RULE_EXPRESSION_700;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:21: ( ( '<' | '=' | '=..' | '=@=' | '=:=' | '=<' | '==' | '=\\\\=' | '>' | '>=' | '@<' | '@=<' | '@>' | '@>=' | '\\\\=' | '\\\\==' | 'is' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:23: ( '<' | '=' | '=..' | '=@=' | '=:=' | '=<' | '==' | '=\\\\=' | '>' | '>=' | '@<' | '@=<' | '@>' | '@>=' | '\\\\=' | '\\\\==' | 'is' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:23: ( '<' | '=' | '=..' | '=@=' | '=:=' | '=<' | '==' | '=\\\\=' | '>' | '>=' | '@<' | '@=<' | '@>' | '@>=' | '\\\\=' | '\\\\==' | 'is' )
            int alt4=17;
            alt4 = dfa4.predict(input);
            switch (alt4) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:24: '<'
                    {
                    match('<'); 

                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:28: '='
                    {
                    match('='); 

                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:32: '=..'
                    {
                    match("=.."); 


                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:38: '=@='
                    {
                    match("=@="); 


                    }
                    break;
                case 5 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:44: '=:='
                    {
                    match("=:="); 


                    }
                    break;
                case 6 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:50: '=<'
                    {
                    match("=<"); 


                    }
                    break;
                case 7 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:55: '=='
                    {
                    match("=="); 


                    }
                    break;
                case 8 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:60: '=\\\\='
                    {
                    match("=\\="); 


                    }
                    break;
                case 9 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:67: '>'
                    {
                    match('>'); 

                    }
                    break;
                case 10 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:71: '>='
                    {
                    match(">="); 


                    }
                    break;
                case 11 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:76: '@<'
                    {
                    match("@<"); 


                    }
                    break;
                case 12 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:81: '@=<'
                    {
                    match("@=<"); 


                    }
                    break;
                case 13 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:87: '@>'
                    {
                    match("@>"); 


                    }
                    break;
                case 14 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:92: '@>='
                    {
                    match("@>="); 


                    }
                    break;
                case 15 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:98: '\\\\='
                    {
                    match("\\="); 


                    }
                    break;
                case 16 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:104: '\\\\=='
                    {
                    match("\\=="); 


                    }
                    break;
                case 17 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1452:111: 'is'
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
    // $ANTLR end "RULE_EXPRESSION_700"

    // $ANTLR start "RULE_EXPRESSION_600"
    public final void mRULE_EXPRESSION_600() throws RecognitionException {
        try {
            int _type = RULE_EXPRESSION_600;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1454:21: ( ':' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1454:23: ':'
            {
            match(':'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_EXPRESSION_600"

    // $ANTLR start "RULE_EXPRESSION_500"
    public final void mRULE_EXPRESSION_500() throws RecognitionException {
        try {
            int _type = RULE_EXPRESSION_500;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1456:21: ( ( '+' | '-' | '/\\\\' | '\\\\/' | 'xor' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1456:23: ( '+' | '-' | '/\\\\' | '\\\\/' | 'xor' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1456:23: ( '+' | '-' | '/\\\\' | '\\\\/' | 'xor' )
            int alt5=5;
            switch ( input.LA(1) ) {
            case '+':
                {
                alt5=1;
                }
                break;
            case '-':
                {
                alt5=2;
                }
                break;
            case '/':
                {
                alt5=3;
                }
                break;
            case '\\':
                {
                alt5=4;
                }
                break;
            case 'x':
                {
                alt5=5;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 5, 0, input);

                throw nvae;
            }

            switch (alt5) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1456:24: '+'
                    {
                    match('+'); 

                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1456:28: '-'
                    {
                    match('-'); 

                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1456:32: '/\\\\'
                    {
                    match("/\\"); 


                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1456:38: '\\\\/'
                    {
                    match("\\/"); 


                    }
                    break;
                case 5 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1456:44: 'xor'
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
    // $ANTLR end "RULE_EXPRESSION_500"

    // $ANTLR start "RULE_EXPRESSION_400"
    public final void mRULE_EXPRESSION_400() throws RecognitionException {
        try {
            int _type = RULE_EXPRESSION_400;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1458:21: ( ( '*' | '/' | '//' | 'rdiv' | '<<' | '>>' | 'mod' | 'rem' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1458:23: ( '*' | '/' | '//' | 'rdiv' | '<<' | '>>' | 'mod' | 'rem' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1458:23: ( '*' | '/' | '//' | 'rdiv' | '<<' | '>>' | 'mod' | 'rem' )
            int alt6=8;
            alt6 = dfa6.predict(input);
            switch (alt6) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1458:24: '*'
                    {
                    match('*'); 

                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1458:28: '/'
                    {
                    match('/'); 

                    }
                    break;
                case 3 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1458:32: '//'
                    {
                    match("//"); 


                    }
                    break;
                case 4 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1458:37: 'rdiv'
                    {
                    match("rdiv"); 


                    }
                    break;
                case 5 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1458:44: '<<'
                    {
                    match("<<"); 


                    }
                    break;
                case 6 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1458:49: '>>'
                    {
                    match(">>"); 


                    }
                    break;
                case 7 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1458:54: 'mod'
                    {
                    match("mod"); 


                    }
                    break;
                case 8 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1458:60: 'rem'
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
    // $ANTLR end "RULE_EXPRESSION_400"

    // $ANTLR start "RULE_EXPRESSION_200"
    public final void mRULE_EXPRESSION_200() throws RecognitionException {
        try {
            int _type = RULE_EXPRESSION_200;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1460:21: ( ( '**' | '^' ) )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1460:23: ( '**' | '^' )
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1460:23: ( '**' | '^' )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='*') ) {
                alt7=1;
            }
            else if ( (LA7_0=='^') ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1460:24: '**'
                    {
                    match("**"); 


                    }
                    break;
                case 2 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1460:29: '^'
                    {
                    match('^'); 

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
    // $ANTLR end "RULE_EXPRESSION_200"

    // $ANTLR start "RULE_ATOM"
    public final void mRULE_ATOM() throws RecognitionException {
        try {
            int _type = RULE_ATOM;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1462:11: ( 'a' .. 'z' ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1462:13: 'a' .. 'z' ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )*
            {
            matchRange('a','z'); 
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1462:22: ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='0' && LA8_0<='9')||(LA8_0>='A' && LA8_0<='Z')||LA8_0=='_'||(LA8_0>='a' && LA8_0<='z')) ) {
                    alt8=1;
                }


                switch (alt8) {
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
            	    break loop8;
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
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1464:15: ( ( 'A' .. 'Z' | '_' ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )* )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1464:17: ( 'A' .. 'Z' | '_' ) ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )*
            {
            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_' ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1464:32: ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='0' && LA9_0<='9')||(LA9_0>='A' && LA9_0<='Z')||LA9_0=='_'||(LA9_0>='a' && LA9_0<='z')) ) {
                    alt9=1;
                }


                switch (alt9) {
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
            	    break loop9;
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
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1466:13: ( '\\'' ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' | '+' | '-' | '*' | '/' | '\\\\' | '^' | '~' | ':' | '.' | '?' | '@' | '#' | '$' | '&' | ' ' )* '\\'' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1466:15: '\\'' ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' | '+' | '-' | '*' | '/' | '\\\\' | '^' | '~' | ':' | '.' | '?' | '@' | '#' | '$' | '&' | ' ' )* '\\''
            {
            match('\''); 
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1466:20: ( '0' .. '9' | 'a' .. 'z' | 'A' .. 'Z' | '_' | '+' | '-' | '*' | '/' | '\\\\' | '^' | '~' | ':' | '.' | '?' | '@' | '#' | '$' | '&' | ' ' )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==' '||(LA10_0>='#' && LA10_0<='$')||LA10_0=='&'||(LA10_0>='*' && LA10_0<='+')||(LA10_0>='-' && LA10_0<=':')||(LA10_0>='?' && LA10_0<='Z')||LA10_0=='\\'||(LA10_0>='^' && LA10_0<='_')||(LA10_0>='a' && LA10_0<='z')||LA10_0=='~') ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:
            	    {
            	    if ( input.LA(1)==' '||(input.LA(1)>='#' && input.LA(1)<='$')||input.LA(1)=='&'||(input.LA(1)>='*' && input.LA(1)<='+')||(input.LA(1)>='-' && input.LA(1)<=':')||(input.LA(1)>='?' && input.LA(1)<='Z')||input.LA(1)=='\\'||(input.LA(1)>='^' && input.LA(1)<='_')||(input.LA(1)>='a' && input.LA(1)<='z')||input.LA(1)=='~' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop10;
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

    // $ANTLR start "RULE_DIGIT"
    public final void mRULE_DIGIT() throws RecognitionException {
        try {
            int _type = RULE_DIGIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1468:12: ( '0' .. '9' )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1468:14: '0' .. '9'
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

    // $ANTLR start "RULE_WHITESPACE"
    public final void mRULE_WHITESPACE() throws RecognitionException {
        try {
            int _type = RULE_WHITESPACE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1470:17: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1470:19: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1470:19: ( ' ' | '\\t' | '\\r' | '\\n' )+
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
    // $ANTLR end "RULE_WHITESPACE"

    // $ANTLR start "RULE_SINGLE_LINE_COMMENT"
    public final void mRULE_SINGLE_LINE_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SINGLE_LINE_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1472:26: ( '%' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1472:28: '%' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match('%'); 
            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1472:32: (~ ( ( '\\n' | '\\r' ) ) )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\u0000' && LA12_0<='\t')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\uFFFF')) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1472:32: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop12;
                }
            } while (true);

            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1472:48: ( ( '\\r' )? '\\n' )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0=='\n'||LA14_0=='\r') ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1472:49: ( '\\r' )? '\\n'
                    {
                    // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1472:49: ( '\\r' )?
                    int alt13=2;
                    int LA13_0 = input.LA(1);

                    if ( (LA13_0=='\r') ) {
                        alt13=1;
                    }
                    switch (alt13) {
                        case 1 :
                            // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1472:49: '\\r'
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

    public void mTokens() throws RecognitionException {
        // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:8: ( T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | RULE_EXPRESSION_1200 | RULE_EXPRESSION_1200FX | RULE_EXPRESSION_1100 | RULE_EXPRESSION_1050 | RULE_EXPRESSION_1000 | RULE_EXPRESSION_900FX | RULE_EXPRESSION_700 | RULE_EXPRESSION_600 | RULE_EXPRESSION_500 | RULE_EXPRESSION_400 | RULE_EXPRESSION_200 | RULE_ATOM | RULE_VARIABLE | RULE_STRING | RULE_DIGIT | RULE_WHITESPACE | RULE_SINGLE_LINE_COMMENT )
        int alt15=28;
        alt15 = dfa15.predict(input);
        switch (alt15) {
            case 1 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:10: T__21
                {
                mT__21(); 

                }
                break;
            case 2 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:16: T__22
                {
                mT__22(); 

                }
                break;
            case 3 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:22: T__23
                {
                mT__23(); 

                }
                break;
            case 4 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:28: T__24
                {
                mT__24(); 

                }
                break;
            case 5 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:34: T__25
                {
                mT__25(); 

                }
                break;
            case 6 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:40: T__26
                {
                mT__26(); 

                }
                break;
            case 7 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:46: T__27
                {
                mT__27(); 

                }
                break;
            case 8 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:52: T__28
                {
                mT__28(); 

                }
                break;
            case 9 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:58: T__29
                {
                mT__29(); 

                }
                break;
            case 10 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:64: T__30
                {
                mT__30(); 

                }
                break;
            case 11 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:70: T__31
                {
                mT__31(); 

                }
                break;
            case 12 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:76: RULE_EXPRESSION_1200
                {
                mRULE_EXPRESSION_1200(); 

                }
                break;
            case 13 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:97: RULE_EXPRESSION_1200FX
                {
                mRULE_EXPRESSION_1200FX(); 

                }
                break;
            case 14 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:120: RULE_EXPRESSION_1100
                {
                mRULE_EXPRESSION_1100(); 

                }
                break;
            case 15 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:141: RULE_EXPRESSION_1050
                {
                mRULE_EXPRESSION_1050(); 

                }
                break;
            case 16 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:162: RULE_EXPRESSION_1000
                {
                mRULE_EXPRESSION_1000(); 

                }
                break;
            case 17 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:183: RULE_EXPRESSION_900FX
                {
                mRULE_EXPRESSION_900FX(); 

                }
                break;
            case 18 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:205: RULE_EXPRESSION_700
                {
                mRULE_EXPRESSION_700(); 

                }
                break;
            case 19 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:225: RULE_EXPRESSION_600
                {
                mRULE_EXPRESSION_600(); 

                }
                break;
            case 20 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:245: RULE_EXPRESSION_500
                {
                mRULE_EXPRESSION_500(); 

                }
                break;
            case 21 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:265: RULE_EXPRESSION_400
                {
                mRULE_EXPRESSION_400(); 

                }
                break;
            case 22 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:285: RULE_EXPRESSION_200
                {
                mRULE_EXPRESSION_200(); 

                }
                break;
            case 23 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:305: RULE_ATOM
                {
                mRULE_ATOM(); 

                }
                break;
            case 24 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:315: RULE_VARIABLE
                {
                mRULE_VARIABLE(); 

                }
                break;
            case 25 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:329: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 26 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:341: RULE_DIGIT
                {
                mRULE_DIGIT(); 

                }
                break;
            case 27 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:352: RULE_WHITESPACE
                {
                mRULE_WHITESPACE(); 

                }
                break;
            case 28 :
                // ../org.archstudio.prolog.xtext/src-gen/org/archstudio/prolog/xtext/parser/antlr/internal/InternalProlog.g:1:368: RULE_SINGLE_LINE_COMMENT
                {
                mRULE_SINGLE_LINE_COMMENT(); 

                }
                break;

        }

    }


    protected DFA4 dfa4 = new DFA4(this);
    protected DFA6 dfa6 = new DFA6(this);
    protected DFA15 dfa15 = new DFA15(this);
    static final String DFA4_eotS =
        "\2\uffff\1\15\1\17\16\uffff\1\25\1\27\4\uffff";
    static final String DFA4_eofS =
        "\30\uffff";
    static final String DFA4_minS =
        "\1\74\1\uffff\1\56\1\75\1\74\1\75\14\uffff\2\75\4\uffff";
    static final String DFA4_maxS =
        "\1\151\1\uffff\1\134\1\75\1\76\1\75\14\uffff\2\75\4\uffff";
    static final String DFA4_acceptS =
        "\1\uffff\1\1\4\uffff\1\21\1\3\1\4\1\5\1\6\1\7\1\10\1\2\1\12\1\11"+
        "\1\13\1\14\2\uffff\1\16\1\15\1\20\1\17";
    static final String DFA4_specialS =
        "\30\uffff}>";
    static final String[] DFA4_transitionS = {
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

    static final short[] DFA4_eot = DFA.unpackEncodedString(DFA4_eotS);
    static final short[] DFA4_eof = DFA.unpackEncodedString(DFA4_eofS);
    static final char[] DFA4_min = DFA.unpackEncodedStringToUnsignedChars(DFA4_minS);
    static final char[] DFA4_max = DFA.unpackEncodedStringToUnsignedChars(DFA4_maxS);
    static final short[] DFA4_accept = DFA.unpackEncodedString(DFA4_acceptS);
    static final short[] DFA4_special = DFA.unpackEncodedString(DFA4_specialS);
    static final short[][] DFA4_transition;

    static {
        int numStates = DFA4_transitionS.length;
        DFA4_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA4_transition[i] = DFA.unpackEncodedString(DFA4_transitionS[i]);
        }
    }

    class DFA4 extends DFA {

        public DFA4(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 4;
            this.eot = DFA4_eot;
            this.eof = DFA4_eof;
            this.min = DFA4_min;
            this.max = DFA4_max;
            this.accept = DFA4_accept;
            this.special = DFA4_special;
            this.transition = DFA4_transition;
        }
        public String getDescription() {
            return "1452:23: ( '<' | '=' | '=..' | '=@=' | '=:=' | '=<' | '==' | '=\\\\=' | '>' | '>=' | '@<' | '@=<' | '@>' | '@>=' | '\\\\=' | '\\\\==' | 'is' )";
        }
    }
    static final String DFA6_eotS =
        "\2\uffff\1\10\10\uffff";
    static final String DFA6_eofS =
        "\13\uffff";
    static final String DFA6_minS =
        "\1\52\1\uffff\1\57\1\144\7\uffff";
    static final String DFA6_maxS =
        "\1\162\1\uffff\1\57\1\145\7\uffff";
    static final String DFA6_acceptS =
        "\1\uffff\1\1\2\uffff\1\5\1\6\1\7\1\3\1\2\1\4\1\10";
    static final String DFA6_specialS =
        "\13\uffff}>";
    static final String[] DFA6_transitionS = {
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
            return "1458:23: ( '*' | '/' | '//' | 'rdiv' | '<<' | '>>' | 'mod' | 'rem' )";
        }
    }
    static final String DFA15_eotS =
        "\10\uffff\1\45\1\46\1\47\1\uffff\1\51\2\uffff\1\52\2\uffff\1\34"+
        "\1\24\1\uffff\1\24\1\34\1\52\3\34\22\uffff\1\34\1\24\4\34\1\53\1"+
        "\54\1\34\3\52";
    static final String DFA15_eofS =
        "\71\uffff";
    static final String DFA15_minS =
        "\1\11\7\uffff\1\55\2\60\1\uffff\1\55\2\uffff\1\52\1\uffff\1\53"+
        "\1\157\1\74\1\uffff\1\76\1\163\1\134\1\157\1\144\1\157\22\uffff"+
        "\1\164\1\60\1\162\1\151\1\155\1\144\2\60\1\166\3\60";
    static final String DFA15_maxS =
        "\1\174\7\uffff\1\76\2\172\1\uffff\1\55\2\uffff\1\55\1\uffff\1\75"+
        "\1\157\1\74\1\uffff\1\76\1\163\1\134\1\157\1\145\1\157\22\uffff"+
        "\1\164\1\172\1\162\1\151\1\155\1\144\2\172\1\166\3\172";
    static final String DFA15_acceptS =
        "\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\7\3\uffff\1\13\1\uffff\1\15"+
        "\1\16\1\uffff\1\20\3\uffff\1\22\6\uffff\1\26\1\27\1\30\1\31\1\32"+
        "\1\33\1\34\1\6\1\14\1\17\1\10\1\11\1\12\1\13\1\23\1\25\1\21\1\24"+
        "\14\uffff";
    static final String DFA15_specialS =
        "\71\uffff}>";
    static final String[] DFA15_transitionS = {
            "\2\40\2\uffff\1\40\22\uffff\1\40\1\2\3\uffff\1\41\1\uffff\1"+
            "\36\1\3\1\4\1\17\1\13\1\20\1\10\1\1\1\27\12\37\1\14\1\16\1\23"+
            "\1\24\1\25\1\15\1\24\4\35\1\12\25\35\1\5\1\21\1\7\1\33\1\35"+
            "\1\uffff\4\34\1\11\3\34\1\26\3\34\1\32\1\22\3\34\1\31\5\34\1"+
            "\30\2\34\1\uffff\1\6",
            "",
            "",
            "",
            "",
            "",
            "",
            "",
            "\1\43\20\uffff\1\44",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\12\35\7\uffff\32\35\4\uffff\1\35\1\uffff\32\35",
            "",
            "\1\43",
            "",
            "",
            "\1\33\2\uffff\1\44",
            "",
            "\1\53\3\uffff\1\54\15\uffff\1\24",
            "\1\55",
            "\1\52",
            "",
            "\1\52",
            "\1\56",
            "\1\54",
            "\1\57",
            "\1\60\1\61",
            "\1\62",
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
            "\1\63",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\1\70",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34",
            "\12\34\7\uffff\32\34\4\uffff\1\34\1\uffff\32\34"
    };

    static final short[] DFA15_eot = DFA.unpackEncodedString(DFA15_eotS);
    static final short[] DFA15_eof = DFA.unpackEncodedString(DFA15_eofS);
    static final char[] DFA15_min = DFA.unpackEncodedStringToUnsignedChars(DFA15_minS);
    static final char[] DFA15_max = DFA.unpackEncodedStringToUnsignedChars(DFA15_maxS);
    static final short[] DFA15_accept = DFA.unpackEncodedString(DFA15_acceptS);
    static final short[] DFA15_special = DFA.unpackEncodedString(DFA15_specialS);
    static final short[][] DFA15_transition;

    static {
        int numStates = DFA15_transitionS.length;
        DFA15_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA15_transition[i] = DFA.unpackEncodedString(DFA15_transitionS[i]);
        }
    }

    class DFA15 extends DFA {

        public DFA15(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 15;
            this.eot = DFA15_eot;
            this.eof = DFA15_eof;
            this.min = DFA15_min;
            this.max = DFA15_max;
            this.accept = DFA15_accept;
            this.special = DFA15_special;
            this.transition = DFA15_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | RULE_EXPRESSION_1200 | RULE_EXPRESSION_1200FX | RULE_EXPRESSION_1100 | RULE_EXPRESSION_1050 | RULE_EXPRESSION_1000 | RULE_EXPRESSION_900FX | RULE_EXPRESSION_700 | RULE_EXPRESSION_600 | RULE_EXPRESSION_500 | RULE_EXPRESSION_400 | RULE_EXPRESSION_200 | RULE_ATOM | RULE_VARIABLE | RULE_STRING | RULE_DIGIT | RULE_WHITESPACE | RULE_SINGLE_LINE_COMMENT );";
        }
    }
 

}