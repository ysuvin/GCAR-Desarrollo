// $ANTLR null G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g 2023-07-04 17:34:48
package parser;
import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class AlgebraRelacionalLexer extends Lexer {
	public static final int EOF=-1;
	public static final int T__8=8;
	public static final int T__9=9;
	public static final int T__10=10;
	public static final int T__11=11;
	public static final int T__12=12;
	public static final int T__13=13;
	public static final int T__14=14;
	public static final int T__15=15;
	public static final int T__16=16;
	public static final int T__17=17;
	public static final int T__18=18;
	public static final int T__19=19;
	public static final int T__20=20;
	public static final int T__21=21;
	public static final int T__22=22;
	public static final int T__23=23;
	public static final int T__24=24;
	public static final int T__25=25;
	public static final int T__26=26;
	public static final int T__27=27;
	public static final int T__28=28;
	public static final int T__29=29;
	public static final int T__30=30;
	public static final int T__31=31;
	public static final int T__32=32;
	public static final int T__33=33;
	public static final int T__34=34;
	public static final int T__35=35;
	public static final int T__36=36;
	public static final int T__37=37;
	public static final int T__38=38;
	public static final int T__39=39;
	public static final int T__40=40;
	public static final int T__41=41;
	public static final int T__42=42;
	public static final int T__43=43;
	public static final int T__44=44;
	public static final int T__45=45;
	public static final int T__46=46;
	public static final int T__47=47;
	public static final int T__48=48;
	public static final int T__49=49;
	public static final int T__50=50;
	public static final int T__51=51;
	public static final int T__52=52;
	public static final int T__53=53;
	public static final int T__54=54;
	public static final int T__55=55;
	public static final int T__56=56;
	public static final int T__57=57;
	public static final int T__58=58;
	public static final int T__59=59;
	public static final int T__60=60;
	public static final int T__61=61;
	public static final int T__62=62;
	public static final int T__63=63;
	public static final int T__64=64;
	public static final int T__65=65;
	public static final int T__66=66;
	public static final int T__67=67;
	public static final int ASSIGN=4;
	public static final int CAD=5;
	public static final int NUM=6;
	public static final int WS=7;

	// delegates
	// delegators
	public Lexer[] getDelegates() {
		return new Lexer[] {};
	}

	public AlgebraRelacionalLexer() {} 
	public AlgebraRelacionalLexer(CharStream input) {
		this(input, new RecognizerSharedState());
	}
	public AlgebraRelacionalLexer(CharStream input, RecognizerSharedState state) {
		super(input,state);
	}
	@Override public String getGrammarFileName() { return "G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g"; }

	// $ANTLR start "T__8"
	public final void mT__8() throws RecognitionException {
		try {
			int _type = T__8;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:2:6: ( '!=' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:2:8: '!='
			{
			match("!="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__8"

	// $ANTLR start "T__9"
	public final void mT__9() throws RecognitionException {
		try {
			int _type = T__9;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:3:6: ( '(' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:3:8: '('
			{
			match('('); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__9"

	// $ANTLR start "T__10"
	public final void mT__10() throws RecognitionException {
		try {
			int _type = T__10;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:4:7: ( ')' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:4:9: ')'
			{
			match(')'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__10"

	// $ANTLR start "T__11"
	public final void mT__11() throws RecognitionException {
		try {
			int _type = T__11;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:7: ( ',' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:9: ','
			{
			match(','); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__11"

	// $ANTLR start "T__12"
	public final void mT__12() throws RecognitionException {
		try {
			int _type = T__12;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:6:7: ( '-' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:6:9: '-'
			{
			match('-'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__12"

	// $ANTLR start "T__13"
	public final void mT__13() throws RecognitionException {
		try {
			int _type = T__13;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:7:7: ( '.' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:7:9: '.'
			{
			match('.'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__13"

	// $ANTLR start "T__14"
	public final void mT__14() throws RecognitionException {
		try {
			int _type = T__14;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:8:7: ( '<' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:8:9: '<'
			{
			match('<'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__14"

	// $ANTLR start "T__15"
	public final void mT__15() throws RecognitionException {
		try {
			int _type = T__15;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:9:7: ( '<=' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:9:9: '<='
			{
			match("<="); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__15"

	// $ANTLR start "T__16"
	public final void mT__16() throws RecognitionException {
		try {
			int _type = T__16;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:10:7: ( '=' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:10:9: '='
			{
			match('='); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__16"

	// $ANTLR start "T__17"
	public final void mT__17() throws RecognitionException {
		try {
			int _type = T__17;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:11:7: ( '>' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:11:9: '>'
			{
			match('>'); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__17"

	// $ANTLR start "T__18"
	public final void mT__18() throws RecognitionException {
		try {
			int _type = T__18;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:12:7: ( '>= ' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:12:9: '>= '
			{
			match(">= "); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__18"

	// $ANTLR start "T__19"
	public final void mT__19() throws RecognitionException {
		try {
			int _type = T__19;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:13:7: ( 'AGRUPAR' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:13:9: 'AGRUPAR'
			{
			match("AGRUPAR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__19"

	// $ANTLR start "T__20"
	public final void mT__20() throws RecognitionException {
		try {
			int _type = T__20;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:14:7: ( 'AND' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:14:9: 'AND'
			{
			match("AND"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__20"

	// $ANTLR start "T__21"
	public final void mT__21() throws RecognitionException {
		try {
			int _type = T__21;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:15:7: ( 'ASC' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:15:9: 'ASC'
			{
			match("ASC"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__21"

	// $ANTLR start "T__22"
	public final void mT__22() throws RecognitionException {
		try {
			int _type = T__22;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:16:7: ( 'AVG' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:16:9: 'AVG'
			{
			match("AVG"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__22"

	// $ANTLR start "T__23"
	public final void mT__23() throws RecognitionException {
		try {
			int _type = T__23;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:17:7: ( 'COUNT' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:17:9: 'COUNT'
			{
			match("COUNT"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__23"

	// $ANTLR start "T__24"
	public final void mT__24() throws RecognitionException {
		try {
			int _type = T__24;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:18:7: ( 'CRUZ' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:18:9: 'CRUZ'
			{
			match("CRUZ"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__24"

	// $ANTLR start "T__25"
	public final void mT__25() throws RecognitionException {
		try {
			int _type = T__25;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:19:7: ( 'DESC' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:19:9: 'DESC'
			{
			match("DESC"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__25"

	// $ANTLR start "T__26"
	public final void mT__26() throws RecognitionException {
		try {
			int _type = T__26;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:20:7: ( 'DIFERENCIA' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:20:9: 'DIFERENCIA'
			{
			match("DIFERENCIA"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__26"

	// $ANTLR start "T__27"
	public final void mT__27() throws RecognitionException {
		try {
			int _type = T__27;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:21:7: ( 'DIVISION' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:21:9: 'DIVISION'
			{
			match("DIVISION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__27"

	// $ANTLR start "T__28"
	public final void mT__28() throws RecognitionException {
		try {
			int _type = T__28;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:22:7: ( 'INTER' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:22:9: 'INTER'
			{
			match("INTER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__28"

	// $ANTLR start "T__29"
	public final void mT__29() throws RecognitionException {
		try {
			int _type = T__29;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:23:7: ( 'MAX' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:23:9: 'MAX'
			{
			match("MAX"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__29"

	// $ANTLR start "T__30"
	public final void mT__30() throws RecognitionException {
		try {
			int _type = T__30;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:24:7: ( 'MIN' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:24:9: 'MIN'
			{
			match("MIN"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__30"

	// $ANTLR start "T__31"
	public final void mT__31() throws RecognitionException {
		try {
			int _type = T__31;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:25:7: ( 'OR' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:25:9: 'OR'
			{
			match("OR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__31"

	// $ANTLR start "T__32"
	public final void mT__32() throws RecognitionException {
		try {
			int _type = T__32;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:26:7: ( 'ORDENAR' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:26:9: 'ORDENAR'
			{
			match("ORDENAR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__32"

	// $ANTLR start "T__33"
	public final void mT__33() throws RecognitionException {
		try {
			int _type = T__33;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:27:7: ( 'PROYECTAR' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:27:9: 'PROYECTAR'
			{
			match("PROYECTAR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__33"

	// $ANTLR start "T__34"
	public final void mT__34() throws RecognitionException {
		try {
			int _type = T__34;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:28:7: ( 'RENOMBRAR' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:28:9: 'RENOMBRAR'
			{
			match("RENOMBRAR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__34"

	// $ANTLR start "T__35"
	public final void mT__35() throws RecognitionException {
		try {
			int _type = T__35;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:29:7: ( 'REUNION' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:29:9: 'REUNION'
			{
			match("REUNION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__35"

	// $ANTLR start "T__36"
	public final void mT__36() throws RecognitionException {
		try {
			int _type = T__36;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:30:7: ( 'REUNION_EXT_DER' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:30:9: 'REUNION_EXT_DER'
			{
			match("REUNION_EXT_DER"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__36"

	// $ANTLR start "T__37"
	public final void mT__37() throws RecognitionException {
		try {
			int _type = T__37;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:31:7: ( 'REUNION_EXT_FULL' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:31:9: 'REUNION_EXT_FULL'
			{
			match("REUNION_EXT_FULL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__37"

	// $ANTLR start "T__38"
	public final void mT__38() throws RecognitionException {
		try {
			int _type = T__38;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:32:7: ( 'REUNION_EXT_IZQ' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:32:9: 'REUNION_EXT_IZQ'
			{
			match("REUNION_EXT_IZQ"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__38"

	// $ANTLR start "T__39"
	public final void mT__39() throws RecognitionException {
		try {
			int _type = T__39;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:33:7: ( 'REUNION_NATURAL' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:33:9: 'REUNION_NATURAL'
			{
			match("REUNION_NATURAL"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__39"

	// $ANTLR start "T__40"
	public final void mT__40() throws RecognitionException {
		try {
			int _type = T__40;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:34:7: ( 'SELECCIONAR' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:34:9: 'SELECCIONAR'
			{
			match("SELECCIONAR"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__40"

	// $ANTLR start "T__41"
	public final void mT__41() throws RecognitionException {
		try {
			int _type = T__41;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:35:7: ( 'SUM' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:35:9: 'SUM'
			{
			match("SUM"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__41"

	// $ANTLR start "T__42"
	public final void mT__42() throws RecognitionException {
		try {
			int _type = T__42;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:36:7: ( 'UNION' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:36:9: 'UNION'
			{
			match("UNION"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__42"

	// $ANTLR start "T__43"
	public final void mT__43() throws RecognitionException {
		try {
			int _type = T__43;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:37:7: ( '\\u0027' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:37:9: '\\u0027'
			{
			match('\''); 
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__43"

	// $ANTLR start "T__44"
	public final void mT__44() throws RecognitionException {
		try {
			int _type = T__44;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:38:7: ( 'agrupar' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:38:9: 'agrupar'
			{
			match("agrupar"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__44"

	// $ANTLR start "T__45"
	public final void mT__45() throws RecognitionException {
		try {
			int _type = T__45;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:39:7: ( 'and' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:39:9: 'and'
			{
			match("and"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__45"

	// $ANTLR start "T__46"
	public final void mT__46() throws RecognitionException {
		try {
			int _type = T__46;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:40:7: ( 'asc' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:40:9: 'asc'
			{
			match("asc"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__46"

	// $ANTLR start "T__47"
	public final void mT__47() throws RecognitionException {
		try {
			int _type = T__47;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:41:7: ( 'avg' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:41:9: 'avg'
			{
			match("avg"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__47"

	// $ANTLR start "T__48"
	public final void mT__48() throws RecognitionException {
		try {
			int _type = T__48;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:42:7: ( 'count' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:42:9: 'count'
			{
			match("count"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__48"

	// $ANTLR start "T__49"
	public final void mT__49() throws RecognitionException {
		try {
			int _type = T__49;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:7: ( 'cruz' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:9: 'cruz'
			{
			match("cruz"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__49"

	// $ANTLR start "T__50"
	public final void mT__50() throws RecognitionException {
		try {
			int _type = T__50;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:44:7: ( 'desc' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:44:9: 'desc'
			{
			match("desc"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__50"

	// $ANTLR start "T__51"
	public final void mT__51() throws RecognitionException {
		try {
			int _type = T__51;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:45:7: ( 'diferencia' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:45:9: 'diferencia'
			{
			match("diferencia"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__51"

	// $ANTLR start "T__52"
	public final void mT__52() throws RecognitionException {
		try {
			int _type = T__52;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:46:7: ( 'division' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:46:9: 'division'
			{
			match("division"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__52"

	// $ANTLR start "T__53"
	public final void mT__53() throws RecognitionException {
		try {
			int _type = T__53;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:47:7: ( 'inter' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:47:9: 'inter'
			{
			match("inter"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__53"

	// $ANTLR start "T__54"
	public final void mT__54() throws RecognitionException {
		try {
			int _type = T__54;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:48:7: ( 'max' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:48:9: 'max'
			{
			match("max"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__54"

	// $ANTLR start "T__55"
	public final void mT__55() throws RecognitionException {
		try {
			int _type = T__55;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:49:7: ( 'min' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:49:9: 'min'
			{
			match("min"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__55"

	// $ANTLR start "T__56"
	public final void mT__56() throws RecognitionException {
		try {
			int _type = T__56;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:50:7: ( 'or' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:50:9: 'or'
			{
			match("or"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__56"

	// $ANTLR start "T__57"
	public final void mT__57() throws RecognitionException {
		try {
			int _type = T__57;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:51:7: ( 'ordenar' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:51:9: 'ordenar'
			{
			match("ordenar"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__57"

	// $ANTLR start "T__58"
	public final void mT__58() throws RecognitionException {
		try {
			int _type = T__58;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:52:7: ( 'proyectar' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:52:9: 'proyectar'
			{
			match("proyectar"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__58"

	// $ANTLR start "T__59"
	public final void mT__59() throws RecognitionException {
		try {
			int _type = T__59;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:53:7: ( 'renombrar' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:53:9: 'renombrar'
			{
			match("renombrar"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__59"

	// $ANTLR start "T__60"
	public final void mT__60() throws RecognitionException {
		try {
			int _type = T__60;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:54:7: ( 'reunion' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:54:9: 'reunion'
			{
			match("reunion"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__60"

	// $ANTLR start "T__61"
	public final void mT__61() throws RecognitionException {
		try {
			int _type = T__61;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:55:7: ( 'reunion_ext_der' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:55:9: 'reunion_ext_der'
			{
			match("reunion_ext_der"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__61"

	// $ANTLR start "T__62"
	public final void mT__62() throws RecognitionException {
		try {
			int _type = T__62;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:56:7: ( 'reunion_ext_full' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:56:9: 'reunion_ext_full'
			{
			match("reunion_ext_full"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__62"

	// $ANTLR start "T__63"
	public final void mT__63() throws RecognitionException {
		try {
			int _type = T__63;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:57:7: ( 'reunion_ext_izq' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:57:9: 'reunion_ext_izq'
			{
			match("reunion_ext_izq"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__63"

	// $ANTLR start "T__64"
	public final void mT__64() throws RecognitionException {
		try {
			int _type = T__64;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:58:7: ( 'reunion_natural' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:58:9: 'reunion_natural'
			{
			match("reunion_natural"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__64"

	// $ANTLR start "T__65"
	public final void mT__65() throws RecognitionException {
		try {
			int _type = T__65;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:59:7: ( 'seleccionar' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:59:9: 'seleccionar'
			{
			match("seleccionar"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__65"

	// $ANTLR start "T__66"
	public final void mT__66() throws RecognitionException {
		try {
			int _type = T__66;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:60:7: ( 'sum' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:60:9: 'sum'
			{
			match("sum"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__66"

	// $ANTLR start "T__67"
	public final void mT__67() throws RecognitionException {
		try {
			int _type = T__67;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:61:7: ( 'union' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:61:9: 'union'
			{
			match("union"); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "T__67"

	// $ANTLR start "NUM"
	public final void mNUM() throws RecognitionException {
		try {
			int _type = NUM;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:48:5: ( ( '0' .. '9' )+ )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:48:7: ( '0' .. '9' )+
			{
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:48:7: ( '0' .. '9' )+
			int cnt1=0;
			loop1:
			while (true) {
				int alt1=2;
				int LA1_0 = input.LA(1);
				if ( ((LA1_0 >= '0' && LA1_0 <= '9')) ) {
					alt1=1;
				}

				switch (alt1) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt1 >= 1 ) break loop1;
					EarlyExitException eee = new EarlyExitException(1, input);
					throw eee;
				}
				cnt1++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "NUM"

	// $ANTLR start "CAD"
	public final void mCAD() throws RecognitionException {
		try {
			int _type = CAD;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:49:5: ( ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | ( '0' .. '9' ) | '_' )+ )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:49:7: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | ( '0' .. '9' ) | '_' )+
			{
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:49:7: ( ( 'a' .. 'z' ) | ( 'A' .. 'Z' ) | ( '0' .. '9' ) | '_' )+
			int cnt2=0;
			loop2:
			while (true) {
				int alt2=2;
				int LA2_0 = input.LA(1);
				if ( ((LA2_0 >= '0' && LA2_0 <= '9')||(LA2_0 >= 'A' && LA2_0 <= 'Z')||LA2_0=='_'||(LA2_0 >= 'a' && LA2_0 <= 'z')) ) {
					alt2=1;
				}

				switch (alt2) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:
					{
					if ( (input.LA(1) >= '0' && input.LA(1) <= '9')||(input.LA(1) >= 'A' && input.LA(1) <= 'Z')||input.LA(1)=='_'||(input.LA(1) >= 'a' && input.LA(1) <= 'z') ) {
						input.consume();
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						recover(mse);
						throw mse;
					}
					}
					break;

				default :
					if ( cnt2 >= 1 ) break loop2;
					EarlyExitException eee = new EarlyExitException(2, input);
					throw eee;
				}
				cnt2++;
			}

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "CAD"

	// $ANTLR start "WS"
	public final void mWS() throws RecognitionException {
		try {
			int _type = WS;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:50:4: ( ( ' ' | '\\t' | '\\r' | '\\n' ) )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:50:6: ( ' ' | '\\t' | '\\r' | '\\n' )
			{
			if ( (input.LA(1) >= '\t' && input.LA(1) <= '\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
				input.consume();
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				recover(mse);
				throw mse;
			}
			_channel=HIDDEN;
			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "WS"

	// $ANTLR start "ASSIGN"
	public final void mASSIGN() throws RecognitionException {
		try {
			int _type = ASSIGN;
			int _channel = DEFAULT_TOKEN_CHANNEL;
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:51:8: ( ' := ' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:51:10: ' := '
			{
			match(" := "); 

			}

			state.type = _type;
			state.channel = _channel;
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "ASSIGN"

	@Override
	public void mTokens() throws RecognitionException {
		// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:8: ( T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | NUM | CAD | WS | ASSIGN )
		int alt3=64;
		alt3 = dfa3.predict(input);
		switch (alt3) {
			case 1 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:10: T__8
				{
				mT__8(); 

				}
				break;
			case 2 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:15: T__9
				{
				mT__9(); 

				}
				break;
			case 3 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:20: T__10
				{
				mT__10(); 

				}
				break;
			case 4 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:26: T__11
				{
				mT__11(); 

				}
				break;
			case 5 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:32: T__12
				{
				mT__12(); 

				}
				break;
			case 6 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:38: T__13
				{
				mT__13(); 

				}
				break;
			case 7 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:44: T__14
				{
				mT__14(); 

				}
				break;
			case 8 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:50: T__15
				{
				mT__15(); 

				}
				break;
			case 9 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:56: T__16
				{
				mT__16(); 

				}
				break;
			case 10 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:62: T__17
				{
				mT__17(); 

				}
				break;
			case 11 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:68: T__18
				{
				mT__18(); 

				}
				break;
			case 12 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:74: T__19
				{
				mT__19(); 

				}
				break;
			case 13 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:80: T__20
				{
				mT__20(); 

				}
				break;
			case 14 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:86: T__21
				{
				mT__21(); 

				}
				break;
			case 15 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:92: T__22
				{
				mT__22(); 

				}
				break;
			case 16 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:98: T__23
				{
				mT__23(); 

				}
				break;
			case 17 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:104: T__24
				{
				mT__24(); 

				}
				break;
			case 18 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:110: T__25
				{
				mT__25(); 

				}
				break;
			case 19 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:116: T__26
				{
				mT__26(); 

				}
				break;
			case 20 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:122: T__27
				{
				mT__27(); 

				}
				break;
			case 21 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:128: T__28
				{
				mT__28(); 

				}
				break;
			case 22 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:134: T__29
				{
				mT__29(); 

				}
				break;
			case 23 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:140: T__30
				{
				mT__30(); 

				}
				break;
			case 24 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:146: T__31
				{
				mT__31(); 

				}
				break;
			case 25 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:152: T__32
				{
				mT__32(); 

				}
				break;
			case 26 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:158: T__33
				{
				mT__33(); 

				}
				break;
			case 27 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:164: T__34
				{
				mT__34(); 

				}
				break;
			case 28 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:170: T__35
				{
				mT__35(); 

				}
				break;
			case 29 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:176: T__36
				{
				mT__36(); 

				}
				break;
			case 30 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:182: T__37
				{
				mT__37(); 

				}
				break;
			case 31 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:188: T__38
				{
				mT__38(); 

				}
				break;
			case 32 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:194: T__39
				{
				mT__39(); 

				}
				break;
			case 33 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:200: T__40
				{
				mT__40(); 

				}
				break;
			case 34 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:206: T__41
				{
				mT__41(); 

				}
				break;
			case 35 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:212: T__42
				{
				mT__42(); 

				}
				break;
			case 36 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:218: T__43
				{
				mT__43(); 

				}
				break;
			case 37 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:224: T__44
				{
				mT__44(); 

				}
				break;
			case 38 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:230: T__45
				{
				mT__45(); 

				}
				break;
			case 39 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:236: T__46
				{
				mT__46(); 

				}
				break;
			case 40 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:242: T__47
				{
				mT__47(); 

				}
				break;
			case 41 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:248: T__48
				{
				mT__48(); 

				}
				break;
			case 42 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:254: T__49
				{
				mT__49(); 

				}
				break;
			case 43 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:260: T__50
				{
				mT__50(); 

				}
				break;
			case 44 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:266: T__51
				{
				mT__51(); 

				}
				break;
			case 45 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:272: T__52
				{
				mT__52(); 

				}
				break;
			case 46 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:278: T__53
				{
				mT__53(); 

				}
				break;
			case 47 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:284: T__54
				{
				mT__54(); 

				}
				break;
			case 48 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:290: T__55
				{
				mT__55(); 

				}
				break;
			case 49 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:296: T__56
				{
				mT__56(); 

				}
				break;
			case 50 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:302: T__57
				{
				mT__57(); 

				}
				break;
			case 51 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:308: T__58
				{
				mT__58(); 

				}
				break;
			case 52 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:314: T__59
				{
				mT__59(); 

				}
				break;
			case 53 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:320: T__60
				{
				mT__60(); 

				}
				break;
			case 54 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:326: T__61
				{
				mT__61(); 

				}
				break;
			case 55 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:332: T__62
				{
				mT__62(); 

				}
				break;
			case 56 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:338: T__63
				{
				mT__63(); 

				}
				break;
			case 57 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:344: T__64
				{
				mT__64(); 

				}
				break;
			case 58 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:350: T__65
				{
				mT__65(); 

				}
				break;
			case 59 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:356: T__66
				{
				mT__66(); 

				}
				break;
			case 60 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:362: T__67
				{
				mT__67(); 

				}
				break;
			case 61 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:368: NUM
				{
				mNUM(); 

				}
				break;
			case 62 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:372: CAD
				{
				mCAD(); 

				}
				break;
			case 63 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:376: WS
				{
				mWS(); 

				}
				break;
			case 64 :
				// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:1:379: ASSIGN
				{
				mASSIGN(); 

				}
				break;

		}
	}


	protected DFA3 dfa3 = new DFA3(this);
	static final String DFA3_eotS =
		"\7\uffff\1\44\1\uffff\1\46\12\40\1\uffff\12\40\1\111\1\uffff\1\42\5\uffff"+
		"\13\40\1\130\20\40\1\154\5\40\2\uffff\1\40\1\164\1\165\1\166\6\40\1\175"+
		"\1\176\1\40\1\uffff\4\40\1\u0084\2\40\1\u0087\1\u0088\1\u0089\6\40\1\u0090"+
		"\1\u0091\1\40\1\uffff\4\40\1\u0097\2\40\3\uffff\1\40\1\u009b\1\u009c\3"+
		"\40\2\uffff\5\40\1\uffff\2\40\3\uffff\1\40\1\u00a8\1\u00a9\3\40\2\uffff"+
		"\5\40\1\uffff\2\40\1\u00b4\2\uffff\2\40\1\u00b7\5\40\1\u00bd\1\40\1\u00bf"+
		"\2\uffff\2\40\1\u00c2\5\40\1\u00c8\1\40\1\uffff\2\40\1\uffff\5\40\1\uffff"+
		"\1\40\1\uffff\2\40\1\uffff\5\40\1\uffff\1\u00d9\2\40\1\u00dc\2\40\1\u00e0"+
		"\1\40\1\u00e2\2\40\1\u00e5\2\40\1\u00e9\1\40\1\uffff\1\40\1\u00ec\1\uffff"+
		"\3\40\1\uffff\1\40\1\uffff\1\40\1\u00f3\1\uffff\3\40\1\uffff\2\40\1\uffff"+
		"\1\u00fa\1\u00fb\4\40\1\uffff\1\u0100\1\u0101\3\40\1\u0105\2\uffff\3\40"+
		"\1\u0109\2\uffff\3\40\1\uffff\2\40\1\u010f\1\uffff\2\40\1\u0112\2\40\1"+
		"\uffff\2\40\1\uffff\20\40\1\u012b\1\40\1\u012d\1\u012e\1\u012f\1\40\1"+
		"\u0131\1\u0132\1\uffff\1\u0133\3\uffff\1\u0134\4\uffff";
	static final String DFA3_eofS =
		"\u0135\uffff";
	static final String DFA3_minS =
		"\1\11\6\uffff\1\75\1\uffff\1\75\1\107\1\117\1\105\1\116\1\101\2\122\2"+
		"\105\1\116\1\uffff\1\147\1\157\1\145\1\156\1\141\2\162\2\145\1\156\1\60"+
		"\1\uffff\1\72\5\uffff\1\122\1\104\1\103\1\107\2\125\1\123\1\106\1\124"+
		"\1\130\1\116\1\60\1\117\1\116\1\114\1\115\1\111\1\162\1\144\1\143\1\147"+
		"\2\165\1\163\1\146\1\164\1\170\1\156\1\60\1\157\1\156\1\154\1\155\1\151"+
		"\2\uffff\1\125\3\60\1\116\1\132\1\103\1\105\1\111\1\105\2\60\1\105\1\uffff"+
		"\1\131\1\117\1\116\1\105\1\60\1\117\1\165\3\60\1\156\1\172\1\143\1\145"+
		"\1\151\1\145\2\60\1\145\1\uffff\1\171\1\157\1\156\1\145\1\60\1\157\1\120"+
		"\3\uffff\1\124\2\60\1\122\1\123\1\122\2\uffff\1\116\1\105\1\115\1\111"+
		"\1\103\1\uffff\1\116\1\160\3\uffff\1\164\2\60\1\162\1\163\1\162\2\uffff"+
		"\1\156\1\145\1\155\1\151\1\143\1\uffff\1\156\1\101\1\60\2\uffff\1\105"+
		"\1\111\1\60\1\101\1\103\1\102\1\117\1\103\1\60\1\141\1\60\2\uffff\1\145"+
		"\1\151\1\60\1\141\1\143\1\142\1\157\1\143\1\60\1\122\1\uffff\1\116\1\117"+
		"\1\uffff\1\122\1\124\1\122\1\116\1\111\1\uffff\1\162\1\uffff\1\156\1\157"+
		"\1\uffff\1\162\1\164\1\162\1\156\1\151\1\uffff\1\60\1\103\1\116\1\60\2"+
		"\101\1\60\1\117\1\60\1\143\1\156\1\60\2\141\1\60\1\157\1\uffff\1\111\1"+
		"\60\1\uffff\2\122\1\105\1\uffff\1\116\1\uffff\1\151\1\60\1\uffff\2\162"+
		"\1\145\1\uffff\1\156\1\101\1\uffff\2\60\1\130\2\101\1\141\1\uffff\2\60"+
		"\1\170\2\141\1\60\2\uffff\2\124\1\122\1\60\2\uffff\2\164\1\162\1\uffff"+
		"\1\137\1\125\1\60\1\uffff\1\137\1\165\1\60\1\104\1\122\1\uffff\1\144\1"+
		"\162\1\uffff\1\105\1\125\1\132\1\101\1\145\1\165\1\172\1\141\1\122\1\114"+
		"\1\121\1\114\1\162\1\154\1\161\1\154\1\60\1\114\3\60\1\154\2\60\1\uffff"+
		"\1\60\3\uffff\1\60\4\uffff";
	static final String DFA3_maxS =
		"\1\172\6\uffff\1\75\1\uffff\1\75\1\126\1\122\1\111\1\116\1\111\2\122\1"+
		"\105\1\125\1\116\1\uffff\1\166\1\162\1\151\1\156\1\151\2\162\1\145\1\165"+
		"\1\156\1\172\1\uffff\1\72\5\uffff\1\122\1\104\1\103\1\107\2\125\1\123"+
		"\1\126\1\124\1\130\1\116\1\172\1\117\1\125\1\114\1\115\1\111\1\162\1\144"+
		"\1\143\1\147\2\165\1\163\1\166\1\164\1\170\1\156\1\172\1\157\1\165\1\154"+
		"\1\155\1\151\2\uffff\1\125\3\172\1\116\1\132\1\103\1\105\1\111\1\105\2"+
		"\172\1\105\1\uffff\1\131\1\117\1\116\1\105\1\172\1\117\1\165\3\172\1\156"+
		"\1\172\1\143\1\145\1\151\1\145\2\172\1\145\1\uffff\1\171\1\157\1\156\1"+
		"\145\1\172\1\157\1\120\3\uffff\1\124\2\172\1\122\1\123\1\122\2\uffff\1"+
		"\116\1\105\1\115\1\111\1\103\1\uffff\1\116\1\160\3\uffff\1\164\2\172\1"+
		"\162\1\163\1\162\2\uffff\1\156\1\145\1\155\1\151\1\143\1\uffff\1\156\1"+
		"\101\1\172\2\uffff\1\105\1\111\1\172\1\101\1\103\1\102\1\117\1\103\1\172"+
		"\1\141\1\172\2\uffff\1\145\1\151\1\172\1\141\1\143\1\142\1\157\1\143\1"+
		"\172\1\122\1\uffff\1\116\1\117\1\uffff\1\122\1\124\1\122\1\116\1\111\1"+
		"\uffff\1\162\1\uffff\1\156\1\157\1\uffff\1\162\1\164\1\162\1\156\1\151"+
		"\1\uffff\1\172\1\103\1\116\1\172\2\101\1\172\1\117\1\172\1\143\1\156\1"+
		"\172\2\141\1\172\1\157\1\uffff\1\111\1\172\1\uffff\2\122\1\116\1\uffff"+
		"\1\116\1\uffff\1\151\1\172\1\uffff\2\162\1\156\1\uffff\1\156\1\101\1\uffff"+
		"\2\172\1\130\2\101\1\141\1\uffff\2\172\1\170\2\141\1\172\2\uffff\2\124"+
		"\1\122\1\172\2\uffff\2\164\1\162\1\uffff\1\137\1\125\1\172\1\uffff\1\137"+
		"\1\165\1\172\1\111\1\122\1\uffff\1\151\1\162\1\uffff\1\105\1\125\1\132"+
		"\1\101\1\145\1\165\1\172\1\141\1\122\1\114\1\121\1\114\1\162\1\154\1\161"+
		"\1\154\1\172\1\114\3\172\1\154\2\172\1\uffff\1\172\3\uffff\1\172\4\uffff";
	static final String DFA3_acceptS =
		"\1\uffff\1\1\1\2\1\3\1\4\1\5\1\6\1\uffff\1\11\13\uffff\1\44\13\uffff\1"+
		"\76\1\uffff\1\77\1\10\1\7\1\13\1\12\42\uffff\1\75\1\100\15\uffff\1\30"+
		"\23\uffff\1\61\7\uffff\1\15\1\16\1\17\6\uffff\1\26\1\27\5\uffff\1\42\2"+
		"\uffff\1\46\1\47\1\50\6\uffff\1\57\1\60\5\uffff\1\73\3\uffff\1\21\1\22"+
		"\13\uffff\1\52\1\53\12\uffff\1\20\2\uffff\1\25\5\uffff\1\43\1\uffff\1"+
		"\51\2\uffff\1\56\5\uffff\1\74\20\uffff\1\14\2\uffff\1\31\3\uffff\1\34"+
		"\1\uffff\1\45\2\uffff\1\62\3\uffff\1\65\2\uffff\1\24\6\uffff\1\55\6\uffff"+
		"\1\32\1\33\4\uffff\1\63\1\64\3\uffff\1\23\3\uffff\1\54\5\uffff\1\41\2"+
		"\uffff\1\72\30\uffff\1\35\1\uffff\1\37\1\40\1\66\1\uffff\1\70\1\71\1\36"+
		"\1\67";
	static final String DFA3_specialS =
		"\u0135\uffff}>";
	static final String[] DFA3_transitionS = {
			"\2\42\2\uffff\1\42\22\uffff\1\41\1\1\5\uffff\1\24\1\2\1\3\2\uffff\1\4"+
			"\1\5\1\6\1\uffff\12\37\2\uffff\1\7\1\10\1\11\2\uffff\1\12\1\40\1\13\1"+
			"\14\4\40\1\15\3\40\1\16\1\40\1\17\1\20\1\40\1\21\1\22\1\40\1\23\5\40"+
			"\4\uffff\1\40\1\uffff\1\25\1\40\1\26\1\27\4\40\1\30\3\40\1\31\1\40\1"+
			"\32\1\33\1\40\1\34\1\35\1\40\1\36\5\40",
			"",
			"",
			"",
			"",
			"",
			"",
			"\1\43",
			"",
			"\1\45",
			"\1\47\6\uffff\1\50\4\uffff\1\51\2\uffff\1\52",
			"\1\53\2\uffff\1\54",
			"\1\55\3\uffff\1\56",
			"\1\57",
			"\1\60\7\uffff\1\61",
			"\1\62",
			"\1\63",
			"\1\64",
			"\1\65\17\uffff\1\66",
			"\1\67",
			"",
			"\1\70\6\uffff\1\71\4\uffff\1\72\2\uffff\1\73",
			"\1\74\2\uffff\1\75",
			"\1\76\3\uffff\1\77",
			"\1\100",
			"\1\101\7\uffff\1\102",
			"\1\103",
			"\1\104",
			"\1\105",
			"\1\106\17\uffff\1\107",
			"\1\110",
			"\12\37\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"",
			"\1\112",
			"",
			"",
			"",
			"",
			"",
			"\1\113",
			"\1\114",
			"\1\115",
			"\1\116",
			"\1\117",
			"\1\120",
			"\1\121",
			"\1\122\17\uffff\1\123",
			"\1\124",
			"\1\125",
			"\1\126",
			"\12\40\7\uffff\3\40\1\127\26\40\4\uffff\1\40\1\uffff\32\40",
			"\1\131",
			"\1\132\6\uffff\1\133",
			"\1\134",
			"\1\135",
			"\1\136",
			"\1\137",
			"\1\140",
			"\1\141",
			"\1\142",
			"\1\143",
			"\1\144",
			"\1\145",
			"\1\146\17\uffff\1\147",
			"\1\150",
			"\1\151",
			"\1\152",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\3\40\1\153\26\40",
			"\1\155",
			"\1\156\6\uffff\1\157",
			"\1\160",
			"\1\161",
			"\1\162",
			"",
			"",
			"\1\163",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\167",
			"\1\170",
			"\1\171",
			"\1\172",
			"\1\173",
			"\1\174",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\177",
			"",
			"\1\u0080",
			"\1\u0081",
			"\1\u0082",
			"\1\u0083",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u0085",
			"\1\u0086",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u008a",
			"\1\u008b",
			"\1\u008c",
			"\1\u008d",
			"\1\u008e",
			"\1\u008f",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u0092",
			"",
			"\1\u0093",
			"\1\u0094",
			"\1\u0095",
			"\1\u0096",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u0098",
			"\1\u0099",
			"",
			"",
			"",
			"\1\u009a",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u009d",
			"\1\u009e",
			"\1\u009f",
			"",
			"",
			"\1\u00a0",
			"\1\u00a1",
			"\1\u00a2",
			"\1\u00a3",
			"\1\u00a4",
			"",
			"\1\u00a5",
			"\1\u00a6",
			"",
			"",
			"",
			"\1\u00a7",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u00aa",
			"\1\u00ab",
			"\1\u00ac",
			"",
			"",
			"\1\u00ad",
			"\1\u00ae",
			"\1\u00af",
			"\1\u00b0",
			"\1\u00b1",
			"",
			"\1\u00b2",
			"\1\u00b3",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"",
			"",
			"\1\u00b5",
			"\1\u00b6",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u00b8",
			"\1\u00b9",
			"\1\u00ba",
			"\1\u00bb",
			"\1\u00bc",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u00be",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"",
			"",
			"\1\u00c0",
			"\1\u00c1",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u00c3",
			"\1\u00c4",
			"\1\u00c5",
			"\1\u00c6",
			"\1\u00c7",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u00c9",
			"",
			"\1\u00ca",
			"\1\u00cb",
			"",
			"\1\u00cc",
			"\1\u00cd",
			"\1\u00ce",
			"\1\u00cf",
			"\1\u00d0",
			"",
			"\1\u00d1",
			"",
			"\1\u00d2",
			"\1\u00d3",
			"",
			"\1\u00d4",
			"\1\u00d5",
			"\1\u00d6",
			"\1\u00d7",
			"\1\u00d8",
			"",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u00da",
			"\1\u00db",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u00dd",
			"\1\u00de",
			"\12\40\7\uffff\32\40\4\uffff\1\u00df\1\uffff\32\40",
			"\1\u00e1",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u00e3",
			"\1\u00e4",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u00e6",
			"\1\u00e7",
			"\12\40\7\uffff\32\40\4\uffff\1\u00e8\1\uffff\32\40",
			"\1\u00ea",
			"",
			"\1\u00eb",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"",
			"\1\u00ed",
			"\1\u00ee",
			"\1\u00ef\10\uffff\1\u00f0",
			"",
			"\1\u00f1",
			"",
			"\1\u00f2",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"",
			"\1\u00f4",
			"\1\u00f5",
			"\1\u00f6\10\uffff\1\u00f7",
			"",
			"\1\u00f8",
			"\1\u00f9",
			"",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u00fc",
			"\1\u00fd",
			"\1\u00fe",
			"\1\u00ff",
			"",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u0102",
			"\1\u0103",
			"\1\u0104",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"",
			"",
			"\1\u0106",
			"\1\u0107",
			"\1\u0108",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"",
			"",
			"\1\u010a",
			"\1\u010b",
			"\1\u010c",
			"",
			"\1\u010d",
			"\1\u010e",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"",
			"\1\u0110",
			"\1\u0111",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u0113\1\uffff\1\u0114\2\uffff\1\u0115",
			"\1\u0116",
			"",
			"\1\u0117\1\uffff\1\u0118\2\uffff\1\u0119",
			"\1\u011a",
			"",
			"\1\u011b",
			"\1\u011c",
			"\1\u011d",
			"\1\u011e",
			"\1\u011f",
			"\1\u0120",
			"\1\u0121",
			"\1\u0122",
			"\1\u0123",
			"\1\u0124",
			"\1\u0125",
			"\1\u0126",
			"\1\u0127",
			"\1\u0128",
			"\1\u0129",
			"\1\u012a",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u012c",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\1\u0130",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
			"",
			"",
			"",
			"\12\40\7\uffff\32\40\4\uffff\1\40\1\uffff\32\40",
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

	protected class DFA3 extends DFA {

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
		@Override
		public String getDescription() {
			return "1:1: Tokens : ( T__8 | T__9 | T__10 | T__11 | T__12 | T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | T__32 | T__33 | T__34 | T__35 | T__36 | T__37 | T__38 | T__39 | T__40 | T__41 | T__42 | T__43 | T__44 | T__45 | T__46 | T__47 | T__48 | T__49 | T__50 | T__51 | T__52 | T__53 | T__54 | T__55 | T__56 | T__57 | T__58 | T__59 | T__60 | T__61 | T__62 | T__63 | T__64 | T__65 | T__66 | T__67 | NUM | CAD | WS | ASSIGN );";
		}
	}

}
