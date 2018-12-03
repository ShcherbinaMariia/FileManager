// Generated from /Users/mariyashcherbina/Documents/FileManager2/src/main/Grammar.g4 by ANTLR 4.7
package main.tableProcessor.parser.main;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class GrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, DOUBLE_NUMBER=2, INT_NUMBER=3, LETTERS=4, CELL_NAME=5, LEFT_PARENTHESIS=6, 
		RIGHT_PARENTHESIS=7, ADD=8, SUBTRACT=9, MULTIPLY=10, DIVIDE=11, MIN=12, 
		MAX=13, COMMA=14;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"WS", "DOUBLE_NUMBER", "INT_NUMBER", "LETTERS", "CELL_NAME", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "ADD", "SUBTRACT", "MULTIPLY", "DIVIDE", "MIN", "MAX", 
		"COMMA"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, null, null, null, null, "'('", "')'", "'+'", "'-'", "'*'", 
		"'/'", "'min'", "'max'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "DOUBLE_NUMBER", "INT_NUMBER", "LETTERS", "CELL_NAME", "LEFT_PARENTHESIS", 
		"RIGHT_PARENTHESIS", "ADD", "SUBTRACT", "MULTIPLY", "DIVIDE", "MIN", "MAX", 
		"COMMA"
	};
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


	public GrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Grammar.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\20]\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\3\2\6\2!\n\2\r\2\16\2\"\3\2\3"+
		"\2\3\3\6\3(\n\3\r\3\16\3)\3\3\3\3\6\3.\n\3\r\3\16\3/\5\3\62\n\3\3\4\6"+
		"\4\65\n\4\r\4\16\4\66\3\5\6\5:\n\5\r\5\16\5;\3\6\6\6?\n\6\r\6\16\6@\3"+
		"\6\6\6D\n\6\r\6\16\6E\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3"+
		"\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\2\2\20\3\3\5\4\7\5\t"+
		"\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\3\2\5\5\2\13\f"+
		"\17\17\"\"\3\2\62;\3\2C\\\2d\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\3 \3"+
		"\2\2\2\5\'\3\2\2\2\7\64\3\2\2\2\t9\3\2\2\2\13>\3\2\2\2\rG\3\2\2\2\17I"+
		"\3\2\2\2\21K\3\2\2\2\23M\3\2\2\2\25O\3\2\2\2\27Q\3\2\2\2\31S\3\2\2\2\33"+
		"W\3\2\2\2\35[\3\2\2\2\37!\t\2\2\2 \37\3\2\2\2!\"\3\2\2\2\" \3\2\2\2\""+
		"#\3\2\2\2#$\3\2\2\2$%\b\2\2\2%\4\3\2\2\2&(\5\7\4\2\'&\3\2\2\2()\3\2\2"+
		"\2)\'\3\2\2\2)*\3\2\2\2*\61\3\2\2\2+-\7\60\2\2,.\5\7\4\2-,\3\2\2\2./\3"+
		"\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\62\3\2\2\2\61+\3\2\2\2\61\62\3\2\2\2\62"+
		"\6\3\2\2\2\63\65\t\3\2\2\64\63\3\2\2\2\65\66\3\2\2\2\66\64\3\2\2\2\66"+
		"\67\3\2\2\2\67\b\3\2\2\28:\t\4\2\298\3\2\2\2:;\3\2\2\2;9\3\2\2\2;<\3\2"+
		"\2\2<\n\3\2\2\2=?\5\t\5\2>=\3\2\2\2?@\3\2\2\2@>\3\2\2\2@A\3\2\2\2AC\3"+
		"\2\2\2BD\5\7\4\2CB\3\2\2\2DE\3\2\2\2EC\3\2\2\2EF\3\2\2\2F\f\3\2\2\2GH"+
		"\7*\2\2H\16\3\2\2\2IJ\7+\2\2J\20\3\2\2\2KL\7-\2\2L\22\3\2\2\2MN\7/\2\2"+
		"N\24\3\2\2\2OP\7,\2\2P\26\3\2\2\2QR\7\61\2\2R\30\3\2\2\2ST\7o\2\2TU\7"+
		"k\2\2UV\7p\2\2V\32\3\2\2\2WX\7o\2\2XY\7c\2\2YZ\7z\2\2Z\34\3\2\2\2[\\\7"+
		".\2\2\\\36\3\2\2\2\13\2\")/\61\66;@E\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}