// $ANTLR null G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g 2023-07-04 17:34:48
package parser;
import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class AlgebraRelacionalParser extends Parser {
	public static final String[] tokenNames = new String[] {
		"<invalid>", "<EOR>", "<DOWN>", "<UP>", "ASSIGN", "CAD", "NUM", "WS", 
		"'!='", "'('", "')'", "','", "'-'", "'.'", "'<'", "'<='", "'='", "'>'", 
		"'>= '", "'AGRUPAR'", "'AND'", "'ASC'", "'AVG'", "'COUNT'", "'CRUZ'", 
		"'DESC'", "'DIFERENCIA'", "'DIVISION'", "'INTER'", "'MAX'", "'MIN'", "'OR'", 
		"'ORDENAR'", "'PROYECTAR'", "'RENOMBRAR'", "'REUNION'", "'REUNION_EXT_DER'", 
		"'REUNION_EXT_FULL'", "'REUNION_EXT_IZQ'", "'REUNION_NATURAL'", "'SELECCIONAR'", 
		"'SUM'", "'UNION'", "'\\u0027'", "'agrupar'", "'and'", "'asc'", "'avg'", 
		"'count'", "'cruz'", "'desc'", "'diferencia'", "'division'", "'inter'", 
		"'max'", "'min'", "'or'", "'ordenar'", "'proyectar'", "'renombrar'", "'reunion'", 
		"'reunion_ext_der'", "'reunion_ext_full'", "'reunion_ext_izq'", "'reunion_natural'", 
		"'seleccionar'", "'sum'", "'union'"
	};
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
	public Parser[] getDelegates() {
		return new Parser[] {};
	}

	// delegators


	public AlgebraRelacionalParser(TokenStream input) {
		this(input, new RecognizerSharedState());
	}
	public AlgebraRelacionalParser(TokenStream input, RecognizerSharedState state) {
		super(input, state);
	}

	@Override public String[] getTokenNames() { return AlgebraRelacionalParser.tokenNames; }
	@Override public String getGrammarFileName() { return "G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g"; }



	// $ANTLR start "st"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:3:1: st : ( asg | con ) ;
	public final void st() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:3:4: ( ( asg | con ) )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:3:6: ( asg | con )
			{
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:3:6: ( asg | con )
			int alt1=2;
			int LA1_0 = input.LA(1);
			if ( (LA1_0==CAD) ) {
				int LA1_1 = input.LA(2);
				if ( (LA1_1==ASSIGN) ) {
					alt1=1;
				}
				else if ( (LA1_1==24||LA1_1==26||LA1_1==28||(LA1_1 >= 36 && LA1_1 <= 39)||LA1_1==42||LA1_1==49||LA1_1==51||LA1_1==53||(LA1_1 >= 61 && LA1_1 <= 64)||LA1_1==67) ) {
					alt1=2;
				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 1, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}
			else if ( (LA1_0==9||(LA1_0 >= 22 && LA1_0 <= 23)||(LA1_0 >= 29 && LA1_0 <= 30)||(LA1_0 >= 32 && LA1_0 <= 34)||(LA1_0 >= 40 && LA1_0 <= 41)||(LA1_0 >= 47 && LA1_0 <= 48)||(LA1_0 >= 54 && LA1_0 <= 55)||(LA1_0 >= 57 && LA1_0 <= 59)||(LA1_0 >= 65 && LA1_0 <= 66)) ) {
				alt1=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 1, 0, input);
				throw nvae;
			}

			switch (alt1) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:3:8: asg
					{
					pushFollow(FOLLOW_asg_in_st12);
					asg();
					state._fsp--;

					}
					break;
				case 2 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:3:14: con
					{
					pushFollow(FOLLOW_con_in_st16);
					con();
					state._fsp--;

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "st"



	// $ANTLR start "asg"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:4:1: asg : rel ASSIGN con ;
	public final void asg() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:4:5: ( rel ASSIGN con )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:4:7: rel ASSIGN con
			{
			pushFollow(FOLLOW_rel_in_asg25);
			rel();
			state._fsp--;

			match(input,ASSIGN,FOLLOW_ASSIGN_in_asg27); 
			pushFollow(FOLLOW_con_in_asg29);
			con();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "asg"



	// $ANTLR start "con"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:1: con : ( bin | select | proy | renom1 | renom2 | renom3 | join | groupby | orderby | agregation | division | reunatural | reuizq | reuder | reuext ) ;
	public final void con() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:5: ( ( bin | select | proy | renom1 | renom2 | renom3 | join | groupby | orderby | agregation | division | reunatural | reuizq | reuder | reuext ) )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:7: ( bin | select | proy | renom1 | renom2 | renom3 | join | groupby | orderby | agregation | division | reunatural | reuizq | reuder | reuext )
			{
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:7: ( bin | select | proy | renom1 | renom2 | renom3 | join | groupby | orderby | agregation | division | reunatural | reuizq | reuder | reuext )
			int alt2=15;
			switch ( input.LA(1) ) {
			case CAD:
				{
				switch ( input.LA(2) ) {
				case 24:
				case 26:
				case 28:
				case 42:
				case 49:
				case 51:
				case 53:
				case 67:
					{
					alt2=1;
					}
					break;
				case 39:
				case 64:
					{
					alt2=12;
					}
					break;
				case 38:
				case 63:
					{
					alt2=13;
					}
					break;
				case 36:
				case 61:
					{
					alt2=14;
					}
					break;
				case 37:
				case 62:
					{
					alt2=15;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 2, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
				}
				break;
			case 40:
			case 65:
				{
				alt2=2;
				}
				break;
			case 33:
			case 58:
				{
				alt2=3;
				}
				break;
			case 34:
			case 59:
				{
				int LA2_4 = input.LA(2);
				if ( (LA2_4==9) ) {
					alt2=6;
				}
				else if ( (LA2_4==CAD) ) {
					int LA2_14 = input.LA(3);
					if ( (LA2_14==9) ) {
						int LA2_16 = input.LA(4);
						if ( (LA2_16==CAD) ) {
							int LA2_19 = input.LA(5);
							if ( (LA2_19==11) ) {
								alt2=4;
							}
							else if ( (LA2_19==10) ) {
								int LA2_24 = input.LA(6);
								if ( (LA2_24==9) ) {
									alt2=4;
								}
								else if ( (LA2_24==EOF) ) {
									alt2=5;
								}

								else {
									int nvaeMark = input.mark();
									try {
										for (int nvaeConsume = 0; nvaeConsume < 6 - 1; nvaeConsume++) {
											input.consume();
										}
										NoViableAltException nvae =
											new NoViableAltException("", 2, 24, input);
										throw nvae;
									} finally {
										input.rewind(nvaeMark);
									}
								}

							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 2, 19, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 2, 16, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 2, 14, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 2, 4, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 9:
				{
				int LA2_5 = input.LA(2);
				if ( (LA2_5==CAD) ) {
					int LA2_15 = input.LA(3);
					if ( (LA2_15==10) ) {
						int LA2_17 = input.LA(4);
						if ( (LA2_17==35||LA2_17==60) ) {
							alt2=7;
						}
						else if ( (LA2_17==19||LA2_17==44) ) {
							alt2=8;
						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 2, 17, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}
					else if ( (LA2_15==11) ) {
						int LA2_18 = input.LA(4);
						if ( (LA2_18==CAD) ) {
							int LA2_22 = input.LA(5);
							if ( (LA2_22==10) ) {
								int LA2_25 = input.LA(6);
								if ( (LA2_25==19||LA2_25==44) ) {
									alt2=8;
								}
								else if ( (LA2_25==9) ) {
									alt2=11;
								}

								else {
									int nvaeMark = input.mark();
									try {
										for (int nvaeConsume = 0; nvaeConsume < 6 - 1; nvaeConsume++) {
											input.consume();
										}
										NoViableAltException nvae =
											new NoViableAltException("", 2, 25, input);
										throw nvae;
									} finally {
										input.rewind(nvaeMark);
									}
								}

							}
							else if ( (LA2_22==11) ) {
								alt2=8;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 2, 22, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 2, 18, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 2, 15, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 2, 5, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

				}
				break;
			case 32:
			case 57:
				{
				alt2=9;
				}
				break;
			case 22:
			case 23:
			case 29:
			case 30:
			case 41:
			case 47:
			case 48:
			case 54:
			case 55:
			case 66:
				{
				alt2=10;
				}
				break;
			default:
				NoViableAltException nvae =
					new NoViableAltException("", 2, 0, input);
				throw nvae;
			}
			switch (alt2) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:9: bin
					{
					pushFollow(FOLLOW_bin_in_con38);
					bin();
					state._fsp--;

					}
					break;
				case 2 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:15: select
					{
					pushFollow(FOLLOW_select_in_con42);
					select();
					state._fsp--;

					}
					break;
				case 3 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:24: proy
					{
					pushFollow(FOLLOW_proy_in_con46);
					proy();
					state._fsp--;

					}
					break;
				case 4 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:31: renom1
					{
					pushFollow(FOLLOW_renom1_in_con50);
					renom1();
					state._fsp--;

					}
					break;
				case 5 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:40: renom2
					{
					pushFollow(FOLLOW_renom2_in_con54);
					renom2();
					state._fsp--;

					}
					break;
				case 6 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:49: renom3
					{
					pushFollow(FOLLOW_renom3_in_con58);
					renom3();
					state._fsp--;

					}
					break;
				case 7 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:58: join
					{
					pushFollow(FOLLOW_join_in_con62);
					join();
					state._fsp--;

					}
					break;
				case 8 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:65: groupby
					{
					pushFollow(FOLLOW_groupby_in_con66);
					groupby();
					state._fsp--;

					}
					break;
				case 9 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:75: orderby
					{
					pushFollow(FOLLOW_orderby_in_con70);
					orderby();
					state._fsp--;

					}
					break;
				case 10 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:85: agregation
					{
					pushFollow(FOLLOW_agregation_in_con74);
					agregation();
					state._fsp--;

					}
					break;
				case 11 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:98: division
					{
					pushFollow(FOLLOW_division_in_con78);
					division();
					state._fsp--;

					}
					break;
				case 12 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:109: reunatural
					{
					pushFollow(FOLLOW_reunatural_in_con82);
					reunatural();
					state._fsp--;

					}
					break;
				case 13 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:122: reuizq
					{
					pushFollow(FOLLOW_reuizq_in_con86);
					reuizq();
					state._fsp--;

					}
					break;
				case 14 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:131: reuder
					{
					pushFollow(FOLLOW_reuder_in_con90);
					reuder();
					state._fsp--;

					}
					break;
				case 15 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:5:140: reuext
					{
					pushFollow(FOLLOW_reuext_in_con94);
					reuext();
					state._fsp--;

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "con"



	// $ANTLR start "bin"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:8:1: bin : ( rel ( 'UNION' | 'union' ) rel | rel ( 'INTER' | 'inter' ) rel | rel ( 'DIFERENCIA' | 'diferencia' ) rel | rel ( 'CRUZ' | 'cruz' ) rel );
	public final void bin() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:8:5: ( rel ( 'UNION' | 'union' ) rel | rel ( 'INTER' | 'inter' ) rel | rel ( 'DIFERENCIA' | 'diferencia' ) rel | rel ( 'CRUZ' | 'cruz' ) rel )
			int alt3=4;
			int LA3_0 = input.LA(1);
			if ( (LA3_0==CAD) ) {
				switch ( input.LA(2) ) {
				case 42:
				case 67:
					{
					alt3=1;
					}
					break;
				case 28:
				case 53:
					{
					alt3=2;
					}
					break;
				case 26:
				case 51:
					{
					alt3=3;
					}
					break;
				case 24:
				case 49:
					{
					alt3=4;
					}
					break;
				default:
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 3, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 3, 0, input);
				throw nvae;
			}

			switch (alt3) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:8:7: rel ( 'UNION' | 'union' ) rel
					{
					pushFollow(FOLLOW_rel_in_bin104);
					rel();
					state._fsp--;

					if ( input.LA(1)==42||input.LA(1)==67 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_rel_in_bin112);
					rel();
					state._fsp--;

					}
					break;
				case 2 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:9:3: rel ( 'INTER' | 'inter' ) rel
					{
					pushFollow(FOLLOW_rel_in_bin116);
					rel();
					state._fsp--;

					if ( input.LA(1)==28||input.LA(1)==53 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_rel_in_bin124);
					rel();
					state._fsp--;

					}
					break;
				case 3 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:10:3: rel ( 'DIFERENCIA' | 'diferencia' ) rel
					{
					pushFollow(FOLLOW_rel_in_bin128);
					rel();
					state._fsp--;

					if ( input.LA(1)==26||input.LA(1)==51 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_rel_in_bin136);
					rel();
					state._fsp--;

					}
					break;
				case 4 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:11:3: rel ( 'CRUZ' | 'cruz' ) rel
					{
					pushFollow(FOLLOW_rel_in_bin140);
					rel();
					state._fsp--;

					if ( input.LA(1)==24||input.LA(1)==49 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_rel_in_bin148);
					rel();
					state._fsp--;

					}
					break;

			}
		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "bin"



	// $ANTLR start "select"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:13:1: select : ( 'seleccionar' | 'SELECCIONAR' ) '(' conds1 ')' '(' rel ')' ;
	public final void select() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:13:8: ( ( 'seleccionar' | 'SELECCIONAR' ) '(' conds1 ')' '(' rel ')' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:13:10: ( 'seleccionar' | 'SELECCIONAR' ) '(' conds1 ')' '(' rel ')'
			{
			if ( input.LA(1)==40||input.LA(1)==65 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,9,FOLLOW_9_in_select161); 
			pushFollow(FOLLOW_conds1_in_select162);
			conds1();
			state._fsp--;

			match(input,10,FOLLOW_10_in_select163); 
			match(input,9,FOLLOW_9_in_select164); 
			pushFollow(FOLLOW_rel_in_select165);
			rel();
			state._fsp--;

			match(input,10,FOLLOW_10_in_select166); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "select"



	// $ANTLR start "proy"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:14:1: proy : ( 'proyectar' | 'PROYECTAR' ) '(' atts ')' '(' rel ')' ;
	public final void proy() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:14:6: ( ( 'proyectar' | 'PROYECTAR' ) '(' atts ')' '(' rel ')' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:14:8: ( 'proyectar' | 'PROYECTAR' ) '(' atts ')' '(' rel ')'
			{
			if ( input.LA(1)==33||input.LA(1)==58 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,9,FOLLOW_9_in_proy178); 
			pushFollow(FOLLOW_atts_in_proy179);
			atts();
			state._fsp--;

			match(input,10,FOLLOW_10_in_proy180); 
			match(input,9,FOLLOW_9_in_proy181); 
			pushFollow(FOLLOW_rel_in_proy182);
			rel();
			state._fsp--;

			match(input,10,FOLLOW_10_in_proy183); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "proy"



	// $ANTLR start "renom1"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:15:1: renom1 : ( 'renombrar' | 'RENOMBRAR' ) rel '(' atts ')' '(' rel ')' ;
	public final void renom1() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:15:8: ( ( 'renombrar' | 'RENOMBRAR' ) rel '(' atts ')' '(' rel ')' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:15:10: ( 'renombrar' | 'RENOMBRAR' ) rel '(' atts ')' '(' rel ')'
			{
			if ( input.LA(1)==34||input.LA(1)==59 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_rel_in_renom1196);
			rel();
			state._fsp--;

			match(input,9,FOLLOW_9_in_renom1198); 
			pushFollow(FOLLOW_atts_in_renom1199);
			atts();
			state._fsp--;

			match(input,10,FOLLOW_10_in_renom1200); 
			match(input,9,FOLLOW_9_in_renom1201); 
			pushFollow(FOLLOW_rel_in_renom1202);
			rel();
			state._fsp--;

			match(input,10,FOLLOW_10_in_renom1203); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "renom1"



	// $ANTLR start "renom2"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:16:1: renom2 : ( 'renombrar' | 'RENOMBRAR' ) rel '(' rel ')' ;
	public final void renom2() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:16:8: ( ( 'renombrar' | 'RENOMBRAR' ) rel '(' rel ')' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:16:10: ( 'renombrar' | 'RENOMBRAR' ) rel '(' rel ')'
			{
			if ( input.LA(1)==34||input.LA(1)==59 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_rel_in_renom2216);
			rel();
			state._fsp--;

			match(input,9,FOLLOW_9_in_renom2218); 
			pushFollow(FOLLOW_rel_in_renom2219);
			rel();
			state._fsp--;

			match(input,10,FOLLOW_10_in_renom2220); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "renom2"



	// $ANTLR start "renom3"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:17:1: renom3 : ( 'renombrar' | 'RENOMBRAR' ) '(' atts ')' '(' rel ')' ;
	public final void renom3() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:17:8: ( ( 'renombrar' | 'RENOMBRAR' ) '(' atts ')' '(' rel ')' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:17:10: ( 'renombrar' | 'RENOMBRAR' ) '(' atts ')' '(' rel ')'
			{
			if ( input.LA(1)==34||input.LA(1)==59 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,9,FOLLOW_9_in_renom3232); 
			pushFollow(FOLLOW_atts_in_renom3233);
			atts();
			state._fsp--;

			match(input,10,FOLLOW_10_in_renom3234); 
			match(input,9,FOLLOW_9_in_renom3235); 
			pushFollow(FOLLOW_rel_in_renom3236);
			rel();
			state._fsp--;

			match(input,10,FOLLOW_10_in_renom3237); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "renom3"



	// $ANTLR start "join"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:18:1: join : '(' rel ')' ( 'reunion' | 'REUNION' ) '(' conds2 ')' '(' rel ')' ;
	public final void join() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:18:6: ( '(' rel ')' ( 'reunion' | 'REUNION' ) '(' conds2 ')' '(' rel ')' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:18:8: '(' rel ')' ( 'reunion' | 'REUNION' ) '(' conds2 ')' '(' rel ')'
			{
			match(input,9,FOLLOW_9_in_join244); 
			pushFollow(FOLLOW_rel_in_join245);
			rel();
			state._fsp--;

			match(input,10,FOLLOW_10_in_join246); 
			if ( input.LA(1)==35||input.LA(1)==60 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,9,FOLLOW_9_in_join252); 
			pushFollow(FOLLOW_conds2_in_join253);
			conds2();
			state._fsp--;

			match(input,10,FOLLOW_10_in_join254); 
			match(input,9,FOLLOW_9_in_join255); 
			pushFollow(FOLLOW_rel_in_join256);
			rel();
			state._fsp--;

			match(input,10,FOLLOW_10_in_join257); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "join"



	// $ANTLR start "groupby"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:19:1: groupby : '(' atts ')' ( 'agrupar' | 'AGRUPAR' ) fagregas '(' rel ')' ;
	public final void groupby() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:19:9: ( '(' atts ')' ( 'agrupar' | 'AGRUPAR' ) fagregas '(' rel ')' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:19:11: '(' atts ')' ( 'agrupar' | 'AGRUPAR' ) fagregas '(' rel ')'
			{
			match(input,9,FOLLOW_9_in_groupby264); 
			pushFollow(FOLLOW_atts_in_groupby266);
			atts();
			state._fsp--;

			match(input,10,FOLLOW_10_in_groupby267); 
			if ( input.LA(1)==19||input.LA(1)==44 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_fagregas_in_groupby275);
			fagregas();
			state._fsp--;

			match(input,9,FOLLOW_9_in_groupby277); 
			pushFollow(FOLLOW_rel_in_groupby278);
			rel();
			state._fsp--;

			match(input,10,FOLLOW_10_in_groupby279); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "groupby"



	// $ANTLR start "orderby"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:20:1: orderby : ( 'ordenar' | 'ORDENAR' ) orders '(' rel ')' ;
	public final void orderby() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:20:9: ( ( 'ordenar' | 'ORDENAR' ) orders '(' rel ')' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:20:11: ( 'ordenar' | 'ORDENAR' ) orders '(' rel ')'
			{
			if ( input.LA(1)==32||input.LA(1)==57 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_orders_in_orderby294);
			orders();
			state._fsp--;

			match(input,9,FOLLOW_9_in_orderby296); 
			pushFollow(FOLLOW_rel_in_orderby297);
			rel();
			state._fsp--;

			match(input,10,FOLLOW_10_in_orderby298); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "orderby"



	// $ANTLR start "agregation"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:21:1: agregation : fagrega ( att '(' rel ')' ) ;
	public final void agregation() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:21:12: ( fagrega ( att '(' rel ')' ) )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:21:14: fagrega ( att '(' rel ')' )
			{
			pushFollow(FOLLOW_fagrega_in_agregation306);
			fagrega();
			state._fsp--;

			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:21:22: ( att '(' rel ')' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:21:24: att '(' rel ')'
			{
			pushFollow(FOLLOW_att_in_agregation310);
			att();
			state._fsp--;

			match(input,9,FOLLOW_9_in_agregation312); 
			pushFollow(FOLLOW_rel_in_agregation313);
			rel();
			state._fsp--;

			match(input,10,FOLLOW_10_in_agregation314); 
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "agregation"



	// $ANTLR start "division"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:22:1: division : '(' att ',' att2 ')' '(' rel ')' ( 'division' | 'DIVISION' ) '(' att ')' '(' rel ')' ;
	public final void division() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:22:9: ( '(' att ',' att2 ')' '(' rel ')' ( 'division' | 'DIVISION' ) '(' att ')' '(' rel ')' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:22:11: '(' att ',' att2 ')' '(' rel ')' ( 'division' | 'DIVISION' ) '(' att ')' '(' rel ')'
			{
			match(input,9,FOLLOW_9_in_division322); 
			pushFollow(FOLLOW_att_in_division323);
			att();
			state._fsp--;

			match(input,11,FOLLOW_11_in_division325); 
			pushFollow(FOLLOW_att2_in_division327);
			att2();
			state._fsp--;

			match(input,10,FOLLOW_10_in_division329); 
			match(input,9,FOLLOW_9_in_division330); 
			pushFollow(FOLLOW_rel_in_division331);
			rel();
			state._fsp--;

			match(input,10,FOLLOW_10_in_division332); 
			if ( input.LA(1)==27||input.LA(1)==52 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,9,FOLLOW_9_in_division338); 
			pushFollow(FOLLOW_att_in_division339);
			att();
			state._fsp--;

			match(input,10,FOLLOW_10_in_division340); 
			match(input,9,FOLLOW_9_in_division341); 
			pushFollow(FOLLOW_rel_in_division342);
			rel();
			state._fsp--;

			match(input,10,FOLLOW_10_in_division343); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "division"



	// $ANTLR start "reunatural"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:23:1: reunatural : rel ( 'REUNION_NATURAL' | 'reunion_natural' ) '(' conds2 ')' rel ;
	public final void reunatural() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:23:11: ( rel ( 'REUNION_NATURAL' | 'reunion_natural' ) '(' conds2 ')' rel )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:23:13: rel ( 'REUNION_NATURAL' | 'reunion_natural' ) '(' conds2 ')' rel
			{
			pushFollow(FOLLOW_rel_in_reunatural349);
			rel();
			state._fsp--;

			if ( input.LA(1)==39||input.LA(1)==64 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,9,FOLLOW_9_in_reunatural356); 
			pushFollow(FOLLOW_conds2_in_reunatural357);
			conds2();
			state._fsp--;

			match(input,10,FOLLOW_10_in_reunatural358); 
			pushFollow(FOLLOW_rel_in_reunatural360);
			rel();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "reunatural"



	// $ANTLR start "reuizq"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:24:1: reuizq : rel ( 'reunion_ext_izq' | 'REUNION_EXT_IZQ' ) '(' conds2 ')' rel ;
	public final void reuizq() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:24:7: ( rel ( 'reunion_ext_izq' | 'REUNION_EXT_IZQ' ) '(' conds2 ')' rel )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:24:9: rel ( 'reunion_ext_izq' | 'REUNION_EXT_IZQ' ) '(' conds2 ')' rel
			{
			pushFollow(FOLLOW_rel_in_reuizq366);
			rel();
			state._fsp--;

			if ( input.LA(1)==38||input.LA(1)==63 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,9,FOLLOW_9_in_reuizq373); 
			pushFollow(FOLLOW_conds2_in_reuizq374);
			conds2();
			state._fsp--;

			match(input,10,FOLLOW_10_in_reuizq375); 
			pushFollow(FOLLOW_rel_in_reuizq377);
			rel();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "reuizq"



	// $ANTLR start "reuder"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:25:1: reuder : rel ( 'reunion_ext_der' | 'REUNION_EXT_DER' ) '(' conds2 ')' rel ;
	public final void reuder() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:25:7: ( rel ( 'reunion_ext_der' | 'REUNION_EXT_DER' ) '(' conds2 ')' rel )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:25:9: rel ( 'reunion_ext_der' | 'REUNION_EXT_DER' ) '(' conds2 ')' rel
			{
			pushFollow(FOLLOW_rel_in_reuder383);
			rel();
			state._fsp--;

			if ( input.LA(1)==36||input.LA(1)==61 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,9,FOLLOW_9_in_reuder390); 
			pushFollow(FOLLOW_conds2_in_reuder391);
			conds2();
			state._fsp--;

			match(input,10,FOLLOW_10_in_reuder392); 
			pushFollow(FOLLOW_rel_in_reuder394);
			rel();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "reuder"



	// $ANTLR start "reuext"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:26:1: reuext : rel ( 'reunion_ext_full' | 'REUNION_EXT_FULL' ) '(' conds2 ')' rel ;
	public final void reuext() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:26:7: ( rel ( 'reunion_ext_full' | 'REUNION_EXT_FULL' ) '(' conds2 ')' rel )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:26:9: rel ( 'reunion_ext_full' | 'REUNION_EXT_FULL' ) '(' conds2 ')' rel
			{
			pushFollow(FOLLOW_rel_in_reuext400);
			rel();
			state._fsp--;

			if ( input.LA(1)==37||input.LA(1)==62 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			match(input,9,FOLLOW_9_in_reuext407); 
			pushFollow(FOLLOW_conds2_in_reuext408);
			conds2();
			state._fsp--;

			match(input,10,FOLLOW_10_in_reuext409); 
			pushFollow(FOLLOW_rel_in_reuext411);
			rel();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "reuext"



	// $ANTLR start "atts"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:29:1: atts : att ( ',' att )* ;
	public final void atts() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:29:6: ( att ( ',' att )* )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:29:8: att ( ',' att )*
			{
			pushFollow(FOLLOW_att_in_atts420);
			att();
			state._fsp--;

			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:29:12: ( ',' att )*
			loop4:
			while (true) {
				int alt4=2;
				int LA4_0 = input.LA(1);
				if ( (LA4_0==11) ) {
					alt4=1;
				}

				switch (alt4) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:29:13: ',' att
					{
					match(input,11,FOLLOW_11_in_atts423); 
					pushFollow(FOLLOW_att_in_atts425);
					att();
					state._fsp--;

					}
					break;

				default :
					break loop4;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "atts"



	// $ANTLR start "conds1"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:30:1: conds1 : cond1 ( ( 'and' | 'or' | 'AND' | 'OR' ) cond1 )* ;
	public final void conds1() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:30:8: ( cond1 ( ( 'and' | 'or' | 'AND' | 'OR' ) cond1 )* )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:30:10: cond1 ( ( 'and' | 'or' | 'AND' | 'OR' ) cond1 )*
			{
			pushFollow(FOLLOW_cond1_in_conds1434);
			cond1();
			state._fsp--;

			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:30:16: ( ( 'and' | 'or' | 'AND' | 'OR' ) cond1 )*
			loop5:
			while (true) {
				int alt5=2;
				int LA5_0 = input.LA(1);
				if ( (LA5_0==20||LA5_0==31||LA5_0==45||LA5_0==56) ) {
					alt5=1;
				}

				switch (alt5) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:30:17: ( 'and' | 'or' | 'AND' | 'OR' ) cond1
					{
					if ( input.LA(1)==20||input.LA(1)==31||input.LA(1)==45||input.LA(1)==56 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_cond1_in_conds1455);
					cond1();
					state._fsp--;

					}
					break;

				default :
					break loop5;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "conds1"



	// $ANTLR start "conds2"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:31:1: conds2 : cond2 ( ( 'and' | 'or' | 'AND' | 'OR' ) cond2 )* ;
	public final void conds2() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:31:8: ( cond2 ( ( 'and' | 'or' | 'AND' | 'OR' ) cond2 )* )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:31:10: cond2 ( ( 'and' | 'or' | 'AND' | 'OR' ) cond2 )*
			{
			pushFollow(FOLLOW_cond2_in_conds2464);
			cond2();
			state._fsp--;

			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:31:16: ( ( 'and' | 'or' | 'AND' | 'OR' ) cond2 )*
			loop6:
			while (true) {
				int alt6=2;
				int LA6_0 = input.LA(1);
				if ( (LA6_0==20||LA6_0==31||LA6_0==45||LA6_0==56) ) {
					alt6=1;
				}

				switch (alt6) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:31:17: ( 'and' | 'or' | 'AND' | 'OR' ) cond2
					{
					if ( input.LA(1)==20||input.LA(1)==31||input.LA(1)==45||input.LA(1)==56 ) {
						input.consume();
						state.errorRecovery=false;
					}
					else {
						MismatchedSetException mse = new MismatchedSetException(null,input);
						throw mse;
					}
					pushFollow(FOLLOW_cond2_in_conds2485);
					cond2();
					state._fsp--;

					}
					break;

				default :
					break loop6;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "conds2"



	// $ANTLR start "cond1"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:32:1: cond1 : ( con1 | con11 ) ;
	public final void cond1() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:32:7: ( ( con1 | con11 ) )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:32:9: ( con1 | con11 )
			{
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:32:9: ( con1 | con11 )
			int alt7=2;
			int LA7_0 = input.LA(1);
			if ( (LA7_0==CAD) ) {
				int LA7_1 = input.LA(2);
				if ( (LA7_1==8||(LA7_1 >= 14 && LA7_1 <= 18)) ) {
					int LA7_2 = input.LA(3);
					if ( (LA7_2==NUM||LA7_2==12||LA7_2==43) ) {
						alt7=1;
					}
					else if ( (LA7_2==CAD) ) {
						alt7=2;
					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 7, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 7, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 7, 0, input);
				throw nvae;
			}

			switch (alt7) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:32:11: con1
					{
					pushFollow(FOLLOW_con1_in_cond1496);
					con1();
					state._fsp--;

					}
					break;
				case 2 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:32:17: con11
					{
					pushFollow(FOLLOW_con11_in_cond1499);
					con11();
					state._fsp--;

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "cond1"



	// $ANTLR start "cond2"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:33:1: cond2 : ( con2 | con3 ) ;
	public final void cond2() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:33:7: ( ( con2 | con3 ) )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:33:9: ( con2 | con3 )
			{
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:33:9: ( con2 | con3 )
			int alt8=2;
			int LA8_0 = input.LA(1);
			if ( (LA8_0==CAD) ) {
				int LA8_1 = input.LA(2);
				if ( (LA8_1==13) ) {
					int LA8_2 = input.LA(3);
					if ( (LA8_2==CAD) ) {
						int LA8_3 = input.LA(4);
						if ( (LA8_3==8||(LA8_3 >= 14 && LA8_3 <= 18)) ) {
							int LA8_4 = input.LA(5);
							if ( (LA8_4==CAD) ) {
								alt8=1;
							}
							else if ( (LA8_4==NUM||LA8_4==12||LA8_4==43) ) {
								alt8=2;
							}

							else {
								int nvaeMark = input.mark();
								try {
									for (int nvaeConsume = 0; nvaeConsume < 5 - 1; nvaeConsume++) {
										input.consume();
									}
									NoViableAltException nvae =
										new NoViableAltException("", 8, 4, input);
									throw nvae;
								} finally {
									input.rewind(nvaeMark);
								}
							}

						}

						else {
							int nvaeMark = input.mark();
							try {
								for (int nvaeConsume = 0; nvaeConsume < 4 - 1; nvaeConsume++) {
									input.consume();
								}
								NoViableAltException nvae =
									new NoViableAltException("", 8, 3, input);
								throw nvae;
							} finally {
								input.rewind(nvaeMark);
							}
						}

					}

					else {
						int nvaeMark = input.mark();
						try {
							for (int nvaeConsume = 0; nvaeConsume < 3 - 1; nvaeConsume++) {
								input.consume();
							}
							NoViableAltException nvae =
								new NoViableAltException("", 8, 2, input);
							throw nvae;
						} finally {
							input.rewind(nvaeMark);
						}
					}

				}

				else {
					int nvaeMark = input.mark();
					try {
						input.consume();
						NoViableAltException nvae =
							new NoViableAltException("", 8, 1, input);
						throw nvae;
					} finally {
						input.rewind(nvaeMark);
					}
				}

			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 8, 0, input);
				throw nvae;
			}

			switch (alt8) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:33:11: con2
					{
					pushFollow(FOLLOW_con2_in_cond2510);
					con2();
					state._fsp--;

					}
					break;
				case 2 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:33:17: con3
					{
					pushFollow(FOLLOW_con3_in_cond2513);
					con3();
					state._fsp--;

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "cond2"



	// $ANTLR start "orders"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:34:1: orders : att '(' order ')' ( ',' att '(' order ')' )* ;
	public final void orders() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:34:8: ( att '(' order ')' ( ',' att '(' order ')' )* )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:34:10: att '(' order ')' ( ',' att '(' order ')' )*
			{
			pushFollow(FOLLOW_att_in_orders522);
			att();
			state._fsp--;

			match(input,9,FOLLOW_9_in_orders524); 
			pushFollow(FOLLOW_order_in_orders525);
			order();
			state._fsp--;

			match(input,10,FOLLOW_10_in_orders526); 
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:34:26: ( ',' att '(' order ')' )*
			loop9:
			while (true) {
				int alt9=2;
				int LA9_0 = input.LA(1);
				if ( (LA9_0==11) ) {
					alt9=1;
				}

				switch (alt9) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:34:27: ',' att '(' order ')'
					{
					match(input,11,FOLLOW_11_in_orders529); 
					pushFollow(FOLLOW_att_in_orders531);
					att();
					state._fsp--;

					match(input,9,FOLLOW_9_in_orders533); 
					pushFollow(FOLLOW_order_in_orders534);
					order();
					state._fsp--;

					match(input,10,FOLLOW_10_in_orders535); 
					}
					break;

				default :
					break loop9;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "orders"



	// $ANTLR start "order"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:35:1: order : ( 'asc' | 'ASC' | 'desc' | 'DESC' ) ;
	public final void order() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:35:7: ( ( 'asc' | 'ASC' | 'desc' | 'DESC' ) )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:
			{
			if ( input.LA(1)==21||input.LA(1)==25||input.LA(1)==46||input.LA(1)==50 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "order"



	// $ANTLR start "fagregas"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:36:1: fagregas : fagrega '(' att ')' ( ',' fagrega '(' att ')' )* ;
	public final void fagregas() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:36:9: ( fagrega '(' att ')' ( ',' fagrega '(' att ')' )* )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:36:11: fagrega '(' att ')' ( ',' fagrega '(' att ')' )*
			{
			pushFollow(FOLLOW_fagrega_in_fagregas569);
			fagrega();
			state._fsp--;

			match(input,9,FOLLOW_9_in_fagregas571); 
			pushFollow(FOLLOW_att_in_fagregas572);
			att();
			state._fsp--;

			match(input,10,FOLLOW_10_in_fagregas573); 
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:36:29: ( ',' fagrega '(' att ')' )*
			loop10:
			while (true) {
				int alt10=2;
				int LA10_0 = input.LA(1);
				if ( (LA10_0==11) ) {
					alt10=1;
				}

				switch (alt10) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:36:30: ',' fagrega '(' att ')'
					{
					match(input,11,FOLLOW_11_in_fagregas576); 
					pushFollow(FOLLOW_fagrega_in_fagregas578);
					fagrega();
					state._fsp--;

					match(input,9,FOLLOW_9_in_fagregas580); 
					pushFollow(FOLLOW_att_in_fagregas581);
					att();
					state._fsp--;

					match(input,10,FOLLOW_10_in_fagregas582); 
					}
					break;

				default :
					break loop10;
				}
			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "fagregas"



	// $ANTLR start "fagrega"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:37:1: fagrega : ( 'sum' | 'SUM' | 'avg' | 'AVG' | 'count' | 'COUNT' | 'min' | 'MIN' | 'max' | 'MAX' ) ;
	public final void fagrega() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:37:9: ( ( 'sum' | 'SUM' | 'avg' | 'AVG' | 'count' | 'COUNT' | 'min' | 'MIN' | 'max' | 'MAX' ) )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:
			{
			if ( (input.LA(1) >= 22 && input.LA(1) <= 23)||(input.LA(1) >= 29 && input.LA(1) <= 30)||input.LA(1)==41||(input.LA(1) >= 47 && input.LA(1) <= 48)||(input.LA(1) >= 54 && input.LA(1) <= 55)||input.LA(1)==66 ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "fagrega"



	// $ANTLR start "con1"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:38:1: con1 : att ( '=' | '<' | '<=' | '>' | '>= ' | '!=' ) cons ;
	public final void con1() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:38:6: ( att ( '=' | '<' | '<=' | '>' | '>= ' | '!=' ) cons )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:38:8: att ( '=' | '<' | '<=' | '>' | '>= ' | '!=' ) cons
			{
			pushFollow(FOLLOW_att_in_con1638);
			att();
			state._fsp--;

			if ( input.LA(1)==8||(input.LA(1) >= 14 && input.LA(1) <= 18) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_cons_in_con1664);
			cons();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "con1"



	// $ANTLR start "con11"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:39:1: con11 : att ( '=' | '<' | '<=' | '>' | '>= ' | '!=' ) att ;
	public final void con11() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:39:7: ( att ( '=' | '<' | '<=' | '>' | '>= ' | '!=' ) att )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:39:9: att ( '=' | '<' | '<=' | '>' | '>= ' | '!=' ) att
			{
			pushFollow(FOLLOW_att_in_con11671);
			att();
			state._fsp--;

			if ( input.LA(1)==8||(input.LA(1) >= 14 && input.LA(1) <= 18) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_att_in_con11697);
			att();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "con11"



	// $ANTLR start "con2"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:40:1: con2 : rel '.' att ( '=' | '<' | '<=' | '>' | '>= ' | '!=' ) rel '.' att ;
	public final void con2() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:40:6: ( rel '.' att ( '=' | '<' | '<=' | '>' | '>= ' | '!=' ) rel '.' att )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:40:8: rel '.' att ( '=' | '<' | '<=' | '>' | '>= ' | '!=' ) rel '.' att
			{
			pushFollow(FOLLOW_rel_in_con2704);
			rel();
			state._fsp--;

			match(input,13,FOLLOW_13_in_con2705); 
			pushFollow(FOLLOW_att_in_con2706);
			att();
			state._fsp--;

			if ( input.LA(1)==8||(input.LA(1) >= 14 && input.LA(1) <= 18) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_rel_in_con2732);
			rel();
			state._fsp--;

			match(input,13,FOLLOW_13_in_con2733); 
			pushFollow(FOLLOW_att_in_con2734);
			att();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "con2"



	// $ANTLR start "con3"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:41:1: con3 : rel '.' att ( '=' | '<' | '<=' | '>' | '>= ' | '!=' ) cons ;
	public final void con3() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:41:6: ( rel '.' att ( '=' | '<' | '<=' | '>' | '>= ' | '!=' ) cons )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:41:8: rel '.' att ( '=' | '<' | '<=' | '>' | '>= ' | '!=' ) cons
			{
			pushFollow(FOLLOW_rel_in_con3741);
			rel();
			state._fsp--;

			match(input,13,FOLLOW_13_in_con3742); 
			pushFollow(FOLLOW_att_in_con3743);
			att();
			state._fsp--;

			if ( input.LA(1)==8||(input.LA(1) >= 14 && input.LA(1) <= 18) ) {
				input.consume();
				state.errorRecovery=false;
			}
			else {
				MismatchedSetException mse = new MismatchedSetException(null,input);
				throw mse;
			}
			pushFollow(FOLLOW_cons_in_con3769);
			cons();
			state._fsp--;

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "con3"



	// $ANTLR start "cons"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:42:1: cons : ( num | cad ) ;
	public final void cons() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:42:6: ( ( num | cad ) )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:42:8: ( num | cad )
			{
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:42:8: ( num | cad )
			int alt11=2;
			int LA11_0 = input.LA(1);
			if ( (LA11_0==NUM||LA11_0==12) ) {
				alt11=1;
			}
			else if ( (LA11_0==43) ) {
				alt11=2;
			}

			else {
				NoViableAltException nvae =
					new NoViableAltException("", 11, 0, input);
				throw nvae;
			}

			switch (alt11) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:42:10: num
					{
					pushFollow(FOLLOW_num_in_cons778);
					num();
					state._fsp--;

					}
					break;
				case 2 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:42:16: cad
					{
					pushFollow(FOLLOW_cad_in_cons782);
					cad();
					state._fsp--;

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "cons"



	// $ANTLR start "num"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:1: num : ( '-' )? ( NUM )+ ( '.' ( NUM )+ )? ;
	public final void num() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:5: ( ( '-' )? ( NUM )+ ( '.' ( NUM )+ )? )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:7: ( '-' )? ( NUM )+ ( '.' ( NUM )+ )?
			{
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:7: ( '-' )?
			int alt12=2;
			int LA12_0 = input.LA(1);
			if ( (LA12_0==12) ) {
				alt12=1;
			}
			switch (alt12) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:8: '-'
					{
					match(input,12,FOLLOW_12_in_num792); 
					}
					break;

			}

			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:13: ( NUM )+
			int cnt13=0;
			loop13:
			while (true) {
				int alt13=2;
				int LA13_0 = input.LA(1);
				if ( (LA13_0==NUM) ) {
					alt13=1;
				}

				switch (alt13) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:14: NUM
					{
					match(input,NUM,FOLLOW_NUM_in_num796); 
					}
					break;

				default :
					if ( cnt13 >= 1 ) break loop13;
					EarlyExitException eee = new EarlyExitException(13, input);
					throw eee;
				}
				cnt13++;
			}

			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:19: ( '.' ( NUM )+ )?
			int alt15=2;
			int LA15_0 = input.LA(1);
			if ( (LA15_0==13) ) {
				alt15=1;
			}
			switch (alt15) {
				case 1 :
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:20: '.' ( NUM )+
					{
					match(input,13,FOLLOW_13_in_num800); 
					// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:23: ( NUM )+
					int cnt14=0;
					loop14:
					while (true) {
						int alt14=2;
						int LA14_0 = input.LA(1);
						if ( (LA14_0==NUM) ) {
							alt14=1;
						}

						switch (alt14) {
						case 1 :
							// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:43:24: NUM
							{
							match(input,NUM,FOLLOW_NUM_in_num802); 
							}
							break;

						default :
							if ( cnt14 >= 1 ) break loop14;
							EarlyExitException eee = new EarlyExitException(14, input);
							throw eee;
						}
						cnt14++;
					}

					}
					break;

			}

			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "num"



	// $ANTLR start "cad"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:44:1: cad : '\\u0027' CAD '\\u0027' ;
	public final void cad() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:44:5: ( '\\u0027' CAD '\\u0027' )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:44:7: '\\u0027' CAD '\\u0027'
			{
			match(input,43,FOLLOW_43_in_cad813); 
			match(input,CAD,FOLLOW_CAD_in_cad814); 
			match(input,43,FOLLOW_43_in_cad815); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "cad"



	// $ANTLR start "att"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:45:1: att : CAD ;
	public final void att() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:45:5: ( CAD )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:45:7: CAD
			{
			match(input,CAD,FOLLOW_CAD_in_att822); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "att"



	// $ANTLR start "att2"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:46:1: att2 : CAD ;
	public final void att2() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:46:6: ( CAD )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:46:8: CAD
			{
			match(input,CAD,FOLLOW_CAD_in_att2829); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "att2"



	// $ANTLR start "rel"
	// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:47:1: rel : CAD ;
	public final void rel() throws RecognitionException {
		try {
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:47:5: ( CAD )
			// G:\\Mi unidad\\GCAR\\gramatica\\AlgebraRelacional.g:47:7: CAD
			{
			match(input,CAD,FOLLOW_CAD_in_rel836); 
			}

		}
		catch (RecognitionException re) {
			reportError(re);
			recover(input,re);
		}
		finally {
			// do for sure before leaving
		}
	}
	// $ANTLR end "rel"

	// Delegated rules



	public static final BitSet FOLLOW_asg_in_st12 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_con_in_st16 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rel_in_asg25 = new BitSet(new long[]{0x0000000000000010L});
	public static final BitSet FOLLOW_ASSIGN_in_asg27 = new BitSet(new long[]{0x0EC1830760C00220L,0x0000000000000006L});
	public static final BitSet FOLLOW_con_in_asg29 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_bin_in_con38 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_select_in_con42 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_proy_in_con46 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_renom1_in_con50 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_renom2_in_con54 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_renom3_in_con58 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_join_in_con62 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_groupby_in_con66 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_orderby_in_con70 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_agregation_in_con74 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_division_in_con78 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reunatural_in_con82 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reuizq_in_con86 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reuder_in_con90 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_reuext_in_con94 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rel_in_bin104 = new BitSet(new long[]{0x0000040000000000L,0x0000000000000008L});
	public static final BitSet FOLLOW_set_in_bin106 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_bin112 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rel_in_bin116 = new BitSet(new long[]{0x0020000010000000L});
	public static final BitSet FOLLOW_set_in_bin118 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_bin124 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rel_in_bin128 = new BitSet(new long[]{0x0008000004000000L});
	public static final BitSet FOLLOW_set_in_bin130 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_bin136 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rel_in_bin140 = new BitSet(new long[]{0x0002000001000000L});
	public static final BitSet FOLLOW_set_in_bin142 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_bin148 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_select156 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_select161 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_conds1_in_select162 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_select163 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_select164 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_select165 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_select166 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_proy173 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_proy178 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_atts_in_proy179 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_proy180 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_proy181 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_proy182 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_proy183 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_renom1190 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_renom1196 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_renom1198 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_atts_in_renom1199 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_renom1200 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_renom1201 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_renom1202 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_renom1203 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_renom2210 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_renom2216 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_renom2218 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_renom2219 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_renom2220 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_renom3227 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_renom3232 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_atts_in_renom3233 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_renom3234 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_renom3235 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_renom3236 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_renom3237 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_join244 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_join245 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_join246 = new BitSet(new long[]{0x1000000800000000L});
	public static final BitSet FOLLOW_set_in_join247 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_join252 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_conds2_in_join253 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_join254 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_join255 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_join256 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_join257 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_groupby264 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_atts_in_groupby266 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_groupby267 = new BitSet(new long[]{0x0000100000080000L});
	public static final BitSet FOLLOW_set_in_groupby269 = new BitSet(new long[]{0x00C1820060C00000L,0x0000000000000004L});
	public static final BitSet FOLLOW_fagregas_in_groupby275 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_groupby277 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_groupby278 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_groupby279 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_set_in_orderby286 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_orders_in_orderby294 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_orderby296 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_orderby297 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_orderby298 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_fagrega_in_agregation306 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_att_in_agregation310 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_agregation312 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_agregation313 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_agregation314 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_9_in_division322 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_att_in_division323 = new BitSet(new long[]{0x0000000000000800L});
	public static final BitSet FOLLOW_11_in_division325 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_att2_in_division327 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_division329 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_division330 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_division331 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_division332 = new BitSet(new long[]{0x0010000008000000L});
	public static final BitSet FOLLOW_set_in_division333 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_division338 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_att_in_division339 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_division340 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_division341 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_division342 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_division343 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rel_in_reunatural349 = new BitSet(new long[]{0x0000008000000000L,0x0000000000000001L});
	public static final BitSet FOLLOW_set_in_reunatural351 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_reunatural356 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_conds2_in_reunatural357 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_reunatural358 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_reunatural360 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rel_in_reuizq366 = new BitSet(new long[]{0x8000004000000000L});
	public static final BitSet FOLLOW_set_in_reuizq368 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_reuizq373 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_conds2_in_reuizq374 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_reuizq375 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_reuizq377 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rel_in_reuder383 = new BitSet(new long[]{0x2000001000000000L});
	public static final BitSet FOLLOW_set_in_reuder385 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_reuder390 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_conds2_in_reuder391 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_reuder392 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_reuder394 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rel_in_reuext400 = new BitSet(new long[]{0x4000002000000000L});
	public static final BitSet FOLLOW_set_in_reuext402 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_reuext407 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_conds2_in_reuext408 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_reuext409 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_reuext411 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_att_in_atts420 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_11_in_atts423 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_att_in_atts425 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_cond1_in_conds1434 = new BitSet(new long[]{0x0100200080100002L});
	public static final BitSet FOLLOW_set_in_conds1437 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_cond1_in_conds1455 = new BitSet(new long[]{0x0100200080100002L});
	public static final BitSet FOLLOW_cond2_in_conds2464 = new BitSet(new long[]{0x0100200080100002L});
	public static final BitSet FOLLOW_set_in_conds2467 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_cond2_in_conds2485 = new BitSet(new long[]{0x0100200080100002L});
	public static final BitSet FOLLOW_con1_in_cond1496 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_con11_in_cond1499 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_con2_in_cond2510 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_con3_in_cond2513 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_att_in_orders522 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_orders524 = new BitSet(new long[]{0x0004400002200000L});
	public static final BitSet FOLLOW_order_in_orders525 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_orders526 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_11_in_orders529 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_att_in_orders531 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_orders533 = new BitSet(new long[]{0x0004400002200000L});
	public static final BitSet FOLLOW_order_in_orders534 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_orders535 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_fagrega_in_fagregas569 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_fagregas571 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_att_in_fagregas572 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_fagregas573 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_11_in_fagregas576 = new BitSet(new long[]{0x00C1820060C00000L,0x0000000000000004L});
	public static final BitSet FOLLOW_fagrega_in_fagregas578 = new BitSet(new long[]{0x0000000000000200L});
	public static final BitSet FOLLOW_9_in_fagregas580 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_att_in_fagregas581 = new BitSet(new long[]{0x0000000000000400L});
	public static final BitSet FOLLOW_10_in_fagregas582 = new BitSet(new long[]{0x0000000000000802L});
	public static final BitSet FOLLOW_att_in_con1638 = new BitSet(new long[]{0x000000000007C100L});
	public static final BitSet FOLLOW_set_in_con1640 = new BitSet(new long[]{0x0000080000001040L});
	public static final BitSet FOLLOW_cons_in_con1664 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_att_in_con11671 = new BitSet(new long[]{0x000000000007C100L});
	public static final BitSet FOLLOW_set_in_con11673 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_att_in_con11697 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rel_in_con2704 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_con2705 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_att_in_con2706 = new BitSet(new long[]{0x000000000007C100L});
	public static final BitSet FOLLOW_set_in_con2708 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_rel_in_con2732 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_con2733 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_att_in_con2734 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_rel_in_con3741 = new BitSet(new long[]{0x0000000000002000L});
	public static final BitSet FOLLOW_13_in_con3742 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_att_in_con3743 = new BitSet(new long[]{0x000000000007C100L});
	public static final BitSet FOLLOW_set_in_con3745 = new BitSet(new long[]{0x0000080000001040L});
	public static final BitSet FOLLOW_cons_in_con3769 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_num_in_cons778 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_cad_in_cons782 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_12_in_num792 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_NUM_in_num796 = new BitSet(new long[]{0x0000000000002042L});
	public static final BitSet FOLLOW_13_in_num800 = new BitSet(new long[]{0x0000000000000040L});
	public static final BitSet FOLLOW_NUM_in_num802 = new BitSet(new long[]{0x0000000000000042L});
	public static final BitSet FOLLOW_43_in_cad813 = new BitSet(new long[]{0x0000000000000020L});
	public static final BitSet FOLLOW_CAD_in_cad814 = new BitSet(new long[]{0x0000080000000000L});
	public static final BitSet FOLLOW_43_in_cad815 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CAD_in_att822 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CAD_in_att2829 = new BitSet(new long[]{0x0000000000000002L});
	public static final BitSet FOLLOW_CAD_in_rel836 = new BitSet(new long[]{0x0000000000000002L});
}
