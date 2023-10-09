// Generated from C:/Users/richa/OneDrive/Email attachments/Documenten/School/Herkansingen/APP/APP Compiler opdracht/APP-Compiler-Beroepsproduct/icss2022-sep-main/icss2022-sep-main/startcode/src/main/antlr4/nl/han/ica/icss/parser\ICSS.g4 by ANTLR 4.12.0
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class ICSSLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.12.0", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		IF=1, ELSE=2, BOX_BRACKET_OPEN=3, BOX_BRACKET_CLOSE=4, TRUE=5, FALSE=6, 
		PIXELSIZE=7, PERCENTAGE=8, SCALAR=9, COLOR=10, ID_IDENT=11, CLASS_IDENT=12, 
		LOWER_IDENT=13, CAPITAL_IDENT=14, WS=15, OPEN_BRACE=16, CLOSE_BRACE=17, 
		SEMICOLON=18, COMMA=19, COLON=20, PLUS=21, MIN=22, MUL=23, DIV=24, ASSIGNMENT_OPERATOR=25;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"IF", "ELSE", "BOX_BRACKET_OPEN", "BOX_BRACKET_CLOSE", "TRUE", "FALSE", 
			"PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", 
			"LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
			"COMMA", "COLON", "PLUS", "MIN", "MUL", "DIV", "ASSIGNMENT_OPERATOR"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'if'", "'else'", "'['", "']'", "'TRUE'", "'FALSE'", null, null, 
			null, null, null, null, null, null, null, "'{'", "'}'", "';'", "','", 
			"':'", "'+'", "'-'", "'*'", "'/'", "':='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "IF", "ELSE", "BOX_BRACKET_OPEN", "BOX_BRACKET_CLOSE", "TRUE", 
			"FALSE", "PIXELSIZE", "PERCENTAGE", "SCALAR", "COLOR", "ID_IDENT", "CLASS_IDENT", 
			"LOWER_IDENT", "CAPITAL_IDENT", "WS", "OPEN_BRACE", "CLOSE_BRACE", "SEMICOLON", 
			"COMMA", "COLON", "PLUS", "MIN", "MUL", "DIV", "ASSIGNMENT_OPERATOR"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public ICSSLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "ICSS.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0019\u009c\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002"+
		"\u0001\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002"+
		"\u0004\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002"+
		"\u0007\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002"+
		"\u000b\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e"+
		"\u0002\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011"+
		"\u0002\u0012\u0007\u0012\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014"+
		"\u0002\u0015\u0007\u0015\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017"+
		"\u0002\u0018\u0007\u0018\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0004\u0006L\b\u0006\u000b\u0006\f\u0006M\u0001"+
		"\u0006\u0001\u0006\u0001\u0006\u0001\u0007\u0004\u0007T\b\u0007\u000b"+
		"\u0007\f\u0007U\u0001\u0007\u0001\u0007\u0001\b\u0004\b[\b\b\u000b\b\f"+
		"\b\\\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\n\u0001\n\u0004\ni\b\n\u000b\n\f\nj\u0001\u000b\u0001\u000b\u0004\u000b"+
		"o\b\u000b\u000b\u000b\f\u000bp\u0001\f\u0001\f\u0005\fu\b\f\n\f\f\fx\t"+
		"\f\u0001\r\u0001\r\u0005\r|\b\r\n\r\f\r\u007f\t\r\u0001\u000e\u0004\u000e"+
		"\u0082\b\u000e\u000b\u000e\f\u000e\u0083\u0001\u000e\u0001\u000e\u0001"+
		"\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0011\u0001\u0011\u0001"+
		"\u0012\u0001\u0012\u0001\u0013\u0001\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0015\u0001\u0015\u0001\u0016\u0001\u0016\u0001\u0017\u0001\u0017\u0001"+
		"\u0018\u0001\u0018\u0001\u0018\u0000\u0000\u0019\u0001\u0001\u0003\u0002"+
		"\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011\t\u0013"+
		"\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010!\u0011"+
		"#\u0012%\u0013\'\u0014)\u0015+\u0016-\u0017/\u00181\u0019\u0001\u0000"+
		"\u0007\u0001\u000009\u0002\u000009af\u0003\u0000--09az\u0001\u0000az\u0001"+
		"\u0000AZ\u0004\u000009AZ__az\u0003\u0000\t\n\r\r  \u00a3\u0000\u0001\u0001"+
		"\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000\u0000\u0000\u0005\u0001"+
		"\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000\u0000\u0000\t\u0001\u0000"+
		"\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000\u0000\r\u0001\u0000\u0000"+
		"\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000\u0011\u0001\u0000\u0000"+
		"\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000\u0015\u0001\u0000\u0000"+
		"\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000\u0019\u0001\u0000\u0000"+
		"\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000\u001d\u0001\u0000\u0000"+
		"\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000!\u0001\u0000\u0000\u0000"+
		"\u0000#\u0001\u0000\u0000\u0000\u0000%\u0001\u0000\u0000\u0000\u0000\'"+
		"\u0001\u0000\u0000\u0000\u0000)\u0001\u0000\u0000\u0000\u0000+\u0001\u0000"+
		"\u0000\u0000\u0000-\u0001\u0000\u0000\u0000\u0000/\u0001\u0000\u0000\u0000"+
		"\u00001\u0001\u0000\u0000\u0000\u00013\u0001\u0000\u0000\u0000\u00036"+
		"\u0001\u0000\u0000\u0000\u0005;\u0001\u0000\u0000\u0000\u0007=\u0001\u0000"+
		"\u0000\u0000\t?\u0001\u0000\u0000\u0000\u000bD\u0001\u0000\u0000\u0000"+
		"\rK\u0001\u0000\u0000\u0000\u000fS\u0001\u0000\u0000\u0000\u0011Z\u0001"+
		"\u0000\u0000\u0000\u0013^\u0001\u0000\u0000\u0000\u0015f\u0001\u0000\u0000"+
		"\u0000\u0017l\u0001\u0000\u0000\u0000\u0019r\u0001\u0000\u0000\u0000\u001b"+
		"y\u0001\u0000\u0000\u0000\u001d\u0081\u0001\u0000\u0000\u0000\u001f\u0087"+
		"\u0001\u0000\u0000\u0000!\u0089\u0001\u0000\u0000\u0000#\u008b\u0001\u0000"+
		"\u0000\u0000%\u008d\u0001\u0000\u0000\u0000\'\u008f\u0001\u0000\u0000"+
		"\u0000)\u0091\u0001\u0000\u0000\u0000+\u0093\u0001\u0000\u0000\u0000-"+
		"\u0095\u0001\u0000\u0000\u0000/\u0097\u0001\u0000\u0000\u00001\u0099\u0001"+
		"\u0000\u0000\u000034\u0005i\u0000\u000045\u0005f\u0000\u00005\u0002\u0001"+
		"\u0000\u0000\u000067\u0005e\u0000\u000078\u0005l\u0000\u000089\u0005s"+
		"\u0000\u00009:\u0005e\u0000\u0000:\u0004\u0001\u0000\u0000\u0000;<\u0005"+
		"[\u0000\u0000<\u0006\u0001\u0000\u0000\u0000=>\u0005]\u0000\u0000>\b\u0001"+
		"\u0000\u0000\u0000?@\u0005T\u0000\u0000@A\u0005R\u0000\u0000AB\u0005U"+
		"\u0000\u0000BC\u0005E\u0000\u0000C\n\u0001\u0000\u0000\u0000DE\u0005F"+
		"\u0000\u0000EF\u0005A\u0000\u0000FG\u0005L\u0000\u0000GH\u0005S\u0000"+
		"\u0000HI\u0005E\u0000\u0000I\f\u0001\u0000\u0000\u0000JL\u0007\u0000\u0000"+
		"\u0000KJ\u0001\u0000\u0000\u0000LM\u0001\u0000\u0000\u0000MK\u0001\u0000"+
		"\u0000\u0000MN\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000OP\u0005"+
		"p\u0000\u0000PQ\u0005x\u0000\u0000Q\u000e\u0001\u0000\u0000\u0000RT\u0007"+
		"\u0000\u0000\u0000SR\u0001\u0000\u0000\u0000TU\u0001\u0000\u0000\u0000"+
		"US\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000"+
		"\u0000WX\u0005%\u0000\u0000X\u0010\u0001\u0000\u0000\u0000Y[\u0007\u0000"+
		"\u0000\u0000ZY\u0001\u0000\u0000\u0000[\\\u0001\u0000\u0000\u0000\\Z\u0001"+
		"\u0000\u0000\u0000\\]\u0001\u0000\u0000\u0000]\u0012\u0001\u0000\u0000"+
		"\u0000^_\u0005#\u0000\u0000_`\u0007\u0001\u0000\u0000`a\u0007\u0001\u0000"+
		"\u0000ab\u0007\u0001\u0000\u0000bc\u0007\u0001\u0000\u0000cd\u0007\u0001"+
		"\u0000\u0000de\u0007\u0001\u0000\u0000e\u0014\u0001\u0000\u0000\u0000"+
		"fh\u0005#\u0000\u0000gi\u0007\u0002\u0000\u0000hg\u0001\u0000\u0000\u0000"+
		"ij\u0001\u0000\u0000\u0000jh\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000"+
		"\u0000k\u0016\u0001\u0000\u0000\u0000ln\u0005.\u0000\u0000mo\u0007\u0002"+
		"\u0000\u0000nm\u0001\u0000\u0000\u0000op\u0001\u0000\u0000\u0000pn\u0001"+
		"\u0000\u0000\u0000pq\u0001\u0000\u0000\u0000q\u0018\u0001\u0000\u0000"+
		"\u0000rv\u0007\u0003\u0000\u0000su\u0007\u0002\u0000\u0000ts\u0001\u0000"+
		"\u0000\u0000ux\u0001\u0000\u0000\u0000vt\u0001\u0000\u0000\u0000vw\u0001"+
		"\u0000\u0000\u0000w\u001a\u0001\u0000\u0000\u0000xv\u0001\u0000\u0000"+
		"\u0000y}\u0007\u0004\u0000\u0000z|\u0007\u0005\u0000\u0000{z\u0001\u0000"+
		"\u0000\u0000|\u007f\u0001\u0000\u0000\u0000}{\u0001\u0000\u0000\u0000"+
		"}~\u0001\u0000\u0000\u0000~\u001c\u0001\u0000\u0000\u0000\u007f}\u0001"+
		"\u0000\u0000\u0000\u0080\u0082\u0007\u0006\u0000\u0000\u0081\u0080\u0001"+
		"\u0000\u0000\u0000\u0082\u0083\u0001\u0000\u0000\u0000\u0083\u0081\u0001"+
		"\u0000\u0000\u0000\u0083\u0084\u0001\u0000\u0000\u0000\u0084\u0085\u0001"+
		"\u0000\u0000\u0000\u0085\u0086\u0006\u000e\u0000\u0000\u0086\u001e\u0001"+
		"\u0000\u0000\u0000\u0087\u0088\u0005{\u0000\u0000\u0088 \u0001\u0000\u0000"+
		"\u0000\u0089\u008a\u0005}\u0000\u0000\u008a\"\u0001\u0000\u0000\u0000"+
		"\u008b\u008c\u0005;\u0000\u0000\u008c$\u0001\u0000\u0000\u0000\u008d\u008e"+
		"\u0005,\u0000\u0000\u008e&\u0001\u0000\u0000\u0000\u008f\u0090\u0005:"+
		"\u0000\u0000\u0090(\u0001\u0000\u0000\u0000\u0091\u0092\u0005+\u0000\u0000"+
		"\u0092*\u0001\u0000\u0000\u0000\u0093\u0094\u0005-\u0000\u0000\u0094,"+
		"\u0001\u0000\u0000\u0000\u0095\u0096\u0005*\u0000\u0000\u0096.\u0001\u0000"+
		"\u0000\u0000\u0097\u0098\u0005/\u0000\u0000\u00980\u0001\u0000\u0000\u0000"+
		"\u0099\u009a\u0005:\u0000\u0000\u009a\u009b\u0005=\u0000\u0000\u009b2"+
		"\u0001\u0000\u0000\u0000\t\u0000MU\\jpv}\u0083\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}