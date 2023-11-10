// Generated by JFlex 1.9.1 http://jflex.de/  (tweaked for IntelliJ platform)
// source: Gwendolen.flex

package Language;

import Language.psi.GwendolenTypes;
import com.intellij.lexer.FlexLexer;
import Grammar.GwendolenElementType;
import Language.psi.GwendolenTypes;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;


class GwendolenLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int INITIAL_BELIEFS = 2;
  public static final int REASONING_RULES = 4;
  public static final int GOALS = 6;
  public static final int PLANS_MODE = 8;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = {
     0,  0,  1,  1,  2,  2,  3,  3,  4, 4
  };

  /**
   * Top-level table for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_TOP = zzUnpackcmap_top();

  private static final String ZZ_CMAP_TOP_PACKED_0 =
    "\1\0\u10ff\u0100";

  private static int [] zzUnpackcmap_top() {
    int [] result = new int[4352];
    int offset = 0;
    offset = zzUnpackcmap_top(ZZ_CMAP_TOP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_top(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Second-level tables for translating characters to character classes
   */
  private static final int [] ZZ_CMAP_BLOCKS = zzUnpackcmap_blocks();

  private static final String ZZ_CMAP_BLOCKS_PACKED_0 =
    "\11\0\1\1\1\2\2\0\1\3\22\0\1\4\1\5"+
    "\1\6\4\0\1\7\1\10\1\11\1\12\1\13\1\14"+
    "\1\15\1\16\1\17\12\20\1\21\1\22\1\23\1\24"+
    "\3\0\1\25\1\26\1\25\1\27\1\30\1\25\1\31"+
    "\1\25\1\32\2\25\1\33\1\25\1\34\1\35\1\36"+
    "\1\25\1\37\1\25\1\40\2\25\1\41\3\25\1\42"+
    "\1\0\1\43\1\0\1\25\1\0\1\44\1\45\1\46"+
    "\1\47\1\50\1\51\1\52\1\53\1\54\1\45\1\55"+
    "\1\56\1\57\1\60\1\61\1\62\1\45\1\63\1\64"+
    "\1\65\1\66\1\67\4\45\1\70\1\71\1\72\1\73"+
    "\u0181\0";

  private static int [] zzUnpackcmap_blocks() {
    int [] result = new int[512];
    int offset = 0;
    offset = zzUnpackcmap_blocks(ZZ_CMAP_BLOCKS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackcmap_blocks(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /**
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\5\0\1\1\1\2\1\3\2\1\1\4\1\1\1\4"+
    "\1\5\1\6\2\7\2\1\1\10\1\11\2\12\2\1"+
    "\1\13\1\14\1\1\1\13\1\15\2\1\1\16\1\17"+
    "\2\15\1\20\1\21\1\1\1\22\2\1\1\23\1\24"+
    "\1\25\1\26\1\27\1\30\1\31\1\1\1\32\1\33"+
    "\1\34\1\35\1\1\1\36\1\37\1\40\1\36\1\41"+
    "\1\42\3\43\1\44\1\45\1\46\1\47\1\0\1\50"+
    "\2\0\1\4\1\0\1\51\3\0\1\52\2\0\1\53"+
    "\1\0\2\15\1\0\1\54\1\0\1\37\1\40\4\0"+
    "\1\55\2\0\1\56\1\57\1\36\2\43\3\0\1\4"+
    "\7\0\2\15\6\0\1\36\2\43\1\60\2\0\1\4"+
    "\1\61\2\0\1\62\1\0\1\63\1\0\2\15\3\0"+
    "\1\64\2\0\1\65\2\43\2\0\1\4\4\0\2\15"+
    "\1\66\1\0\1\67\1\70\1\0\1\71\2\43\1\0"+
    "\1\72\1\4\4\0\2\15\1\0\1\73\2\43\1\0"+
    "\1\4\3\0\1\74\1\75\1\76\1\0\1\77\1\100"+
    "\1\0\1\4\5\0\1\101\3\0\1\102\25\0\1\103"+
    "\1\0\1\104\2\0\1\105\1\106";

  private static int [] zzUnpackAction() {
    int [] result = new int[227];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /**
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\74\0\170\0\264\0\360\0\u012c\0\u0168\0\u012c"+
    "\0\u01a4\0\u01e0\0\u021c\0\u0258\0\u0294\0\u02d0\0\u030c\0\u0348"+
    "\0\u030c\0\u0384\0\u03c0\0\u03fc\0\u0438\0\u0474\0\u0438\0\u04b0"+
    "\0\u04ec\0\u0528\0\u012c\0\u0564\0\u05a0\0\u05dc\0\u0618\0\u0654"+
    "\0\u012c\0\u012c\0\u0690\0\u06cc\0\u0708\0\u012c\0\u0744\0\u012c"+
    "\0\u0780\0\u07bc\0\u012c\0\u012c\0\u012c\0\u012c\0\u012c\0\u012c"+
    "\0\u07f8\0\u0834\0\u0870\0\u08ac\0\u012c\0\u08e8\0\u0924\0\u0960"+
    "\0\u0960\0\u0960\0\u099c\0\u012c\0\u012c\0\u09d8\0\u0a14\0\u0a50"+
    "\0\u012c\0\u012c\0\u012c\0\u012c\0\u0a8c\0\u0ac8\0\u0b04\0\u0b40"+
    "\0\u0b7c\0\u0bb8\0\u0bf4\0\u0c30\0\u0c6c\0\u0ca8\0\u0ce4\0\u0d20"+
    "\0\u0d5c\0\u0d98\0\u0dd4\0\u0e10\0\u0e4c\0\u0780\0\u012c\0\u07bc"+
    "\0\u012c\0\u012c\0\u0e88\0\u0ec4\0\u0f00\0\u0f3c\0\u0f78\0\u0fb4"+
    "\0\u0ff0\0\u012c\0\u012c\0\u102c\0\u1068\0\u10a4\0\u10e0\0\u111c"+
    "\0\u1158\0\u1194\0\u11d0\0\u120c\0\u1248\0\u1284\0\u12c0\0\u12fc"+
    "\0\u1338\0\u1374\0\u13b0\0\u13ec\0\u1428\0\u1464\0\u14a0\0\u14dc"+
    "\0\u1518\0\u1554\0\u1590\0\u15cc\0\u0a8c\0\u1608\0\u1644\0\u1680"+
    "\0\u0bb8\0\u16bc\0\u16f8\0\u0ca8\0\u1734\0\u0d5c\0\u1770\0\u17ac"+
    "\0\u17e8\0\u1824\0\u1860\0\u189c\0\u0f3c\0\u18d8\0\u1914\0\u0960"+
    "\0\u1950\0\u198c\0\u19c8\0\u1a04\0\u1a40\0\u1a7c\0\u1ab8\0\u1af4"+
    "\0\u1b30\0\u1b6c\0\u1ba8\0\u012c\0\u1be4\0\u012c\0\u012c\0\u1c20"+
    "\0\u012c\0\u1c5c\0\u1c98\0\u1cd4\0\u012c\0\u1d10\0\u1d4c\0\u1d88"+
    "\0\u1dc4\0\u1e00\0\u1e3c\0\u1e78\0\u1eb4\0\u012c\0\u1ef0\0\u1f2c"+
    "\0\u1f68\0\u1fa4\0\u1fe0\0\u201c\0\u2058\0\u012c\0\u05dc\0\u05dc"+
    "\0\u2094\0\u0a14\0\u0a14\0\u20d0\0\u210c\0\u2148\0\u2184\0\u21c0"+
    "\0\u21fc\0\u2238\0\u021c\0\u2274\0\u22b0\0\u22ec\0\u012c\0\u2328"+
    "\0\u2364\0\u23a0\0\u23dc\0\u2418\0\u2454\0\u2490\0\u24cc\0\u2508"+
    "\0\u2544\0\u2580\0\u25bc\0\u25f8\0\u2634\0\u2670\0\u26ac\0\u26e8"+
    "\0\u2724\0\u2760\0\u279c\0\u27d8\0\u012c\0\u2814\0\u012c\0\u2850"+
    "\0\u288c\0\u012c\0\u012c";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[227];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length() - 1;
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /**
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpacktrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\6\1\7\1\10\1\11\1\7\12\6\1\12\1\13"+
    "\1\14\3\6\4\13\1\15\10\13\2\6\24\13\5\6"+
    "\1\16\1\17\1\20\1\16\1\6\1\21\1\6\2\21"+
    "\2\6\3\21\1\22\1\21\1\23\3\6\43\21\1\6"+
    "\1\21\3\6\1\24\1\25\1\26\1\24\1\6\1\27"+
    "\1\6\2\27\2\6\3\27\1\30\1\27\1\31\3\6"+
    "\43\27\1\6\1\27\3\6\1\32\1\33\1\34\1\35"+
    "\3\6\2\36\2\6\1\36\1\6\1\36\1\37\1\36"+
    "\1\40\3\6\15\36\1\41\1\42\1\43\15\36\1\44"+
    "\5\36\5\6\1\45\1\46\1\47\1\45\1\50\1\51"+
    "\1\52\1\53\1\54\1\55\1\56\1\57\1\60\1\61"+
    "\1\62\1\63\1\64\1\65\1\66\1\67\1\70\1\71"+
    "\2\70\1\72\6\70\1\73\1\70\1\74\1\75\1\76"+
    "\15\77\1\100\5\77\1\101\1\102\1\103\1\104\75\0"+
    "\1\7\2\0\1\7\71\0\1\10\103\0\1\105\4\0"+
    "\1\106\74\0\1\13\4\0\15\13\2\0\24\13\36\0"+
    "\1\107\25\0\1\110\33\0\1\13\4\0\14\13\1\111"+
    "\2\0\24\13\5\0\1\16\2\21\1\16\1\0\1\21"+
    "\1\0\2\21\2\0\3\21\1\0\1\21\4\0\43\21"+
    "\1\0\1\21\3\0\4\21\1\0\1\21\1\0\2\21"+
    "\2\0\3\21\1\0\1\21\4\0\43\21\1\0\1\21"+
    "\3\0\1\21\1\17\2\21\1\0\1\21\1\0\2\21"+
    "\2\0\3\21\1\0\1\21\4\0\43\21\1\0\1\21"+
    "\14\0\1\112\4\0\1\113\106\0\1\114\4\0\1\115"+
    "\35\0\1\24\2\27\1\24\1\0\1\27\1\0\2\27"+
    "\2\0\3\27\1\0\1\27\4\0\43\27\1\0\1\27"+
    "\3\0\4\27\1\0\1\27\1\0\2\27\2\0\3\27"+
    "\1\0\1\27\4\0\43\27\1\0\1\27\3\0\1\27"+
    "\1\25\2\27\1\0\1\27\1\0\2\27\2\0\3\27"+
    "\1\0\1\27\4\0\43\27\1\0\1\27\14\0\1\116"+
    "\4\0\1\117\106\0\1\120\42\0\1\32\2\0\1\32"+
    "\71\0\1\33\72\0\1\32\2\0\1\35\3\0\2\36"+
    "\2\0\1\36\1\0\1\36\1\0\1\36\4\0\15\36"+
    "\2\0\24\36\10\0\1\36\3\0\2\36\2\0\1\36"+
    "\1\0\1\36\1\0\1\36\4\0\15\36\2\0\24\36"+
    "\16\0\1\121\4\0\1\122\112\0\1\123\41\0\1\36"+
    "\3\0\2\36\2\0\1\36\1\0\1\36\1\0\1\36"+
    "\4\0\15\36\2\0\2\36\1\124\21\36\10\0\1\36"+
    "\3\0\2\36\2\0\1\36\1\0\1\36\1\0\1\36"+
    "\4\0\15\36\2\0\4\36\1\125\17\36\5\0\1\45"+
    "\2\0\1\45\71\0\1\46\71\0\6\126\1\127\65\126"+
    "\7\130\1\127\64\130\26\0\1\131\2\0\1\132\24\0"+
    "\1\133\4\0\1\134\1\135\21\0\1\136\4\0\1\137"+
    "\72\0\1\63\1\0\1\63\133\0\1\140\4\0\1\141"+
    "\23\0\1\142\102\0\1\143\67\0\1\70\4\0\15\70"+
    "\2\0\24\70\24\0\1\70\4\0\15\70\2\0\17\70"+
    "\1\144\4\70\22\0\1\77\1\0\1\77\4\0\15\77"+
    "\2\0\2\77\1\145\21\77\22\0\1\77\1\0\1\77"+
    "\4\0\15\77\2\0\24\77\22\0\1\77\1\0\1\77"+
    "\4\0\15\77\2\0\4\77\1\146\17\77\4\0\12\105"+
    "\1\147\61\105\2\106\2\0\70\106\60\0\1\150\57\0"+
    "\1\151\47\0\1\13\4\0\3\13\1\152\11\13\2\0"+
    "\24\13\4\0\12\112\1\153\61\112\2\113\2\0\70\113"+
    "\60\0\1\154\63\0\1\155\23\0\12\116\1\156\61\116"+
    "\2\117\2\0\70\117\60\0\1\157\13\0\12\121\1\160"+
    "\61\121\2\122\2\0\70\122\56\0\1\161\21\0\1\36"+
    "\3\0\2\36\2\0\1\36\1\0\1\36\1\0\1\36"+
    "\4\0\15\36\2\0\7\36\1\162\14\36\10\0\1\36"+
    "\3\0\2\36\2\0\1\36\1\0\1\36\1\0\1\36"+
    "\4\0\15\36\2\0\17\36\1\163\4\36\65\0\1\164"+
    "\62\0\1\165\73\0\1\166\23\0\12\136\1\167\61\136"+
    "\2\137\2\0\70\137\44\0\1\170\77\0\1\171\43\0"+
    "\1\70\4\0\15\70\2\0\22\70\1\172\1\70\22\0"+
    "\1\77\1\0\1\77\4\0\15\77\2\0\7\77\1\173"+
    "\14\77\22\0\1\77\1\0\1\77\4\0\15\77\2\0"+
    "\17\77\1\174\4\77\4\0\12\105\1\147\4\105\1\175"+
    "\54\105\54\0\1\176\76\0\1\177\34\0\1\13\4\0"+
    "\7\13\1\200\5\13\2\0\24\13\4\0\12\112\1\153"+
    "\4\112\1\201\54\112\54\0\1\202\63\0\1\203\27\0"+
    "\12\116\1\156\4\116\1\204\54\116\54\0\1\205\17\0"+
    "\12\121\1\160\4\121\1\206\54\121\44\0\1\207\33\0"+
    "\1\36\3\0\2\36\2\0\1\36\1\0\1\36\1\0"+
    "\1\36\4\0\15\36\2\0\10\36\1\210\13\36\10\0"+
    "\1\36\3\0\2\36\2\0\1\36\1\0\1\36\1\0"+
    "\1\36\4\0\15\36\2\0\5\36\1\211\16\36\52\0"+
    "\1\212\73\0\1\213\105\0\1\214\13\0\12\136\1\167"+
    "\4\136\1\215\54\136\57\0\1\216\72\0\1\217\35\0"+
    "\1\70\4\0\15\70\2\0\4\70\1\220\17\70\22\0"+
    "\1\77\1\0\1\77\4\0\15\77\2\0\10\77\1\221"+
    "\13\77\22\0\1\77\1\0\1\77\4\0\15\77\2\0"+
    "\5\77\1\222\16\77\71\0\1\223\56\0\1\224\43\0"+
    "\1\13\4\0\2\13\1\225\12\13\2\0\24\13\71\0"+
    "\1\226\72\0\1\227\74\0\1\230\66\0\1\231\17\0"+
    "\1\36\3\0\2\36\2\0\1\36\1\0\1\36\1\0"+
    "\1\36\4\0\15\36\2\0\4\36\1\232\17\36\10\0"+
    "\1\36\3\0\2\36\2\0\1\36\1\0\1\36\1\0"+
    "\1\36\4\0\15\36\2\0\15\36\1\233\6\36\61\0"+
    "\1\234\66\0\1\235\72\0\1\236\15\0\1\237\56\0"+
    "\1\240\101\0\1\241\33\0\1\77\1\0\1\77\4\0"+
    "\15\77\2\0\4\77\1\242\17\77\22\0\1\77\1\0"+
    "\1\77\4\0\15\77\2\0\15\77\1\243\6\77\60\0"+
    "\1\244\40\0\1\245\72\0\1\13\4\0\10\13\1\246"+
    "\4\13\2\0\24\13\60\0\1\247\100\0\1\250\66\0"+
    "\1\251\103\0\1\252\13\0\1\36\3\0\2\36\2\0"+
    "\1\36\1\0\1\36\1\0\1\36\4\0\15\36\2\0"+
    "\23\36\1\253\10\0\1\36\3\0\2\36\2\0\1\36"+
    "\1\0\1\36\1\0\1\36\4\0\15\36\2\0\17\36"+
    "\1\254\4\36\60\0\1\255\40\0\1\256\70\0\1\77"+
    "\1\0\1\77\4\0\15\77\2\0\23\77\1\257\22\0"+
    "\1\77\1\0\1\77\4\0\15\77\2\0\17\77\1\260"+
    "\4\77\50\0\1\261\47\0\1\13\4\0\6\13\1\262"+
    "\6\13\2\0\24\13\50\0\1\263\107\0\1\264\57\0"+
    "\1\265\50\0\1\266\56\0\1\36\3\0\2\36\2\0"+
    "\1\36\1\0\1\36\1\0\1\36\4\0\15\36\2\0"+
    "\4\36\1\267\17\36\10\0\1\36\3\0\2\36\2\0"+
    "\1\36\1\0\1\36\1\0\1\36\4\0\15\36\2\0"+
    "\13\36\1\270\10\36\73\0\1\271\22\0\1\77\1\0"+
    "\1\77\4\0\15\77\2\0\4\77\1\272\17\77\22\0"+
    "\1\77\1\0\1\77\4\0\15\77\2\0\13\77\1\273"+
    "\10\77\62\0\1\274\35\0\1\13\4\0\3\13\1\275"+
    "\11\13\2\0\24\13\62\0\1\276\71\0\1\277\75\0"+
    "\1\300\65\0\1\301\27\0\1\302\107\0\1\13\4\0"+
    "\7\13\1\303\5\13\2\0\24\13\10\0\1\304\147\0"+
    "\1\305\17\0\1\306\136\0\1\307\52\0\1\310\76\0"+
    "\1\311\114\0\1\312\52\0\1\313\112\0\1\314\104\0"+
    "\1\315\16\0\1\316\150\0\1\317\70\0\1\320\61\0"+
    "\1\321\66\0\1\322\100\0\1\323\103\0\1\324\75\0"+
    "\1\325\103\0\1\326\63\0\1\327\65\0\1\330\107\0"+
    "\1\331\65\0\1\332\101\0\1\333\60\0\1\334\43\0"+
    "\1\335\122\0\1\336\44\0\1\337\136\0\1\340\73\0"+
    "\1\341\30\0\1\342\73\0\1\343\52\0";

  private static int [] zzUnpacktrans() {
    int [] result = new int[10440];
    int offset = 0;
    offset = zzUnpacktrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpacktrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state {@code aState}
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\5\0\1\11\1\1\1\11\22\1\1\11\5\1\2\11"+
    "\3\1\1\11\1\1\1\11\2\1\6\11\4\1\1\11"+
    "\6\1\2\11\3\1\4\11\1\0\1\1\2\0\1\1"+
    "\1\0\1\1\3\0\1\1\2\0\1\1\1\0\2\1"+
    "\1\0\1\11\1\0\2\11\4\0\1\1\2\0\2\11"+
    "\3\1\3\0\1\1\7\0\2\1\6\0\4\1\2\0"+
    "\2\1\2\0\1\1\1\0\1\1\1\0\2\1\3\0"+
    "\1\1\2\0\3\1\2\0\1\1\4\0\2\1\1\11"+
    "\1\0\2\11\1\0\1\11\2\1\1\0\1\11\1\1"+
    "\4\0\2\1\1\0\1\11\2\1\1\0\1\1\3\0"+
    "\1\11\2\1\1\0\2\1\1\0\1\1\5\0\1\1"+
    "\3\0\1\11\25\0\1\11\1\0\1\11\2\0\2\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[227];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** Number of newlines encountered up to the start of the matched text. */
  @SuppressWarnings("unused")
  private int yyline;

  /** Number of characters from the last newline up to the start of the matched text. */
  @SuppressWarnings("unused")
  protected int yycolumn;

  /** Number of characters up to the start of the matched text. */
  @SuppressWarnings("unused")
  private long yychar;

  /** Whether the scanner is currently at the beginning of a line. */
  @SuppressWarnings("unused")
  private boolean zzAtBOL = true;

  /** Whether the user-EOF-code has already been executed. */
  private boolean zzEOFDone;

  /* user code: */
    public int plain_nesting = 0;
    public int sq_nesting = 0;
    public int curly_nesting = 0;


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  GwendolenLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** Returns the maximum size of the scanner buffer, which limits the size of tokens. */
  private int zzMaxBufferLen() {
    return Integer.MAX_VALUE;
  }

  /**  Whether the scanner buffer can grow to accommodate a larger token. */
  private boolean zzCanGrow() {
    return true;
  }

  /**
   * Translates raw input code points to DFA table row
   */
  private static int zzCMap(int input) {
    int offset = input & 255;
    return offset == input ? ZZ_CMAP_BLOCKS[offset] : ZZ_CMAP_BLOCKS[ZZ_CMAP_TOP[input >> 8] | offset];
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      {@code false}, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position {@code pos} from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occurred while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() {
    if (!zzEOFDone) {
      zzEOFDone = true;
    
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException
  {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMap(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1:
            { return TokenType.BAD_CHARACTER;
            }
          // fall through
          case 71: break;
          case 2:
            { yybegin(YYINITIAL); return GwendolenTypes.WS;
            }
          // fall through
          case 72: break;
          case 3:
            { yybegin(YYINITIAL); return GwendolenTypes.NEWLINE;
            }
          // fall through
          case 73: break;
          case 4:
            { yybegin(YYINITIAL); return GwendolenTypes.CONST;
            }
          // fall through
          case 74: break;
          case 5:
            { yybegin(INITIAL_BELIEFS); return GwendolenTypes.IB_WS;
            }
          // fall through
          case 75: break;
          case 6:
            { yybegin(INITIAL_BELIEFS); return GwendolenTypes.IB_NEWLINE;
            }
          // fall through
          case 76: break;
          case 7:
            { yybegin(INITIAL_BELIEFS); return GwendolenTypes.BELIEF_BLOCK;
            }
          // fall through
          case 77: break;
          case 8:
            { yybegin(REASONING_RULES); return GwendolenTypes.RR_WS;
            }
          // fall through
          case 78: break;
          case 9:
            { yybegin(REASONING_RULES); return GwendolenTypes.RR_NEWLINE;
            }
          // fall through
          case 79: break;
          case 10:
            { yybegin(REASONING_RULES); return GwendolenTypes.RR_BLOCK;
            }
          // fall through
          case 80: break;
          case 11:
            { yybegin(GOALS); return GwendolenTypes.GL_WS;
            }
          // fall through
          case 81: break;
          case 12:
            { yybegin(GOALS); return GwendolenTypes.GL_NEWLINE;
            }
          // fall through
          case 82: break;
          case 13:
            { yybegin(GOALS); return GwendolenTypes.GOAL_BLOCK;
            }
          // fall through
          case 83: break;
          case 14:
            { sq_nesting++;
          return GwendolenTypes.GL_SQOPEN;
            }
          // fall through
          case 84: break;
          case 15:
            { sq_nesting--;
          return GwendolenTypes.GL_SQCLOSE;
            }
          // fall through
          case 85: break;
          case 16:
            { yybegin(PLANS_MODE); return GwendolenTypes.PL_WS;
            }
          // fall through
          case 86: break;
          case 17:
            { yybegin(PLANS_MODE); return GwendolenTypes.PL_NEWLINE;
            }
          // fall through
          case 87: break;
          case 18:
            { yybegin(PLANS_MODE); return GwendolenTypes.SHRIEK;
            }
          // fall through
          case 88: break;
          case 19:
            { plain_nesting++;
            yybegin(PLANS_MODE);
            return GwendolenTypes.OPEN;
            }
          // fall through
          case 89: break;
          case 20:
            { plain_nesting--;
            yybegin(PLANS_MODE);
            return GwendolenTypes.CLOSE;
            }
          // fall through
          case 90: break;
          case 21:
            { yybegin(PLANS_MODE); return GwendolenTypes.MULT;
            }
          // fall through
          case 91: break;
          case 22:
            { yybegin(PLANS_MODE); return GwendolenTypes.PLUS;
            }
          // fall through
          case 92: break;
          case 23:
            { yybegin(PLANS_MODE); return GwendolenTypes.COMMA;
            }
          // fall through
          case 93: break;
          case 24:
            { yybegin(PLANS_MODE); return GwendolenTypes.MINUS;
            }
          // fall through
          case 94: break;
          case 25:
            { yybegin(PLANS_MODE); return GwendolenTypes.IDPUNCT;
            }
          // fall through
          case 95: break;
          case 26:
            { yybegin(PLANS_MODE); return GwendolenTypes.NUMBER;
            }
          // fall through
          case 96: break;
          case 27:
            { yybegin(PLANS_MODE); return GwendolenTypes.COLON;
            }
          // fall through
          case 97: break;
          case 28:
            { yybegin(PLANS_MODE); return GwendolenTypes.SEMI;
            }
          // fall through
          case 98: break;
          case 29:
            { yybegin(PLANS_MODE); return GwendolenTypes.LESS;
            }
          // fall through
          case 99: break;
          case 30:
            { yybegin(PLANS_MODE); return GwendolenTypes.PL_VAR;
            }
          // fall through
          case 100: break;
          case 31:
            { if(curly_nesting > 0){
                yybegin(PLANS_MODE);
                return GwendolenTypes.BELIEVE;
            }
            }
          // fall through
          case 101: break;
          case 32:
            { if(curly_nesting > 0){
                yybegin(PLANS_MODE);
                return GwendolenTypes.GOAL;
            }
            }
          // fall through
          case 102: break;
          case 33:
            { yybegin(PLANS_MODE); return GwendolenTypes.PL_SQOPEN;
            }
          // fall through
          case 103: break;
          case 34:
            { yybegin(PLANS_MODE); return GwendolenTypes.PL_SQCLOSE;
            }
          // fall through
          case 104: break;
          case 35:
            { yybegin(PLANS_MODE); return GwendolenTypes.PL_CONST;
            }
          // fall through
          case 105: break;
          case 36:
            { curly_nesting++;
            yybegin(PLANS_MODE);
            return GwendolenTypes.CURLYOPEN;
            }
          // fall through
          case 106: break;
          case 37:
            { yybegin(PLANS_MODE); return GwendolenTypes.PL_BAR;
            }
          // fall through
          case 107: break;
          case 38:
            { curly_nesting--;
          yybegin(PLANS_MODE);
          return GwendolenTypes.CURLYCLOSE;
            }
          // fall through
          case 108: break;
          case 39:
            { yybegin(PLANS_MODE); return GwendolenTypes.NOT;
            }
          // fall through
          case 109: break;
          case 40:
            { yybegin(YYINITIAL); return GwendolenTypes.LINE_COMMENT;
            }
          // fall through
          case 110: break;
          case 41:
            { yybegin(INITIAL_BELIEFS); return GwendolenTypes.IB_LINE_COMMENT;
            }
          // fall through
          case 111: break;
          case 42:
            { yybegin(REASONING_RULES); return GwendolenTypes.RR_LINE_COMMENT;
            }
          // fall through
          case 112: break;
          case 43:
            { yybegin(GOALS); return GwendolenTypes.GL_LINE_COMMENT;
            }
          // fall through
          case 113: break;
          case 44:
            { yybegin(PLANS_MODE); return GwendolenTypes.QUOTED_STRING;
            }
          // fall through
          case 114: break;
          case 45:
            { yybegin(PLANS_MODE); return GwendolenTypes.PL_LINE_COMMENT;
            }
          // fall through
          case 115: break;
          case 46:
            { yybegin(PLANS_MODE); return GwendolenTypes.RULEARROW;
            }
          // fall through
          case 116: break;
          case 47:
            { yybegin(PLANS_MODE); return GwendolenTypes.EQUAL;
            }
          // fall through
          case 117: break;
          case 48:
            { yybegin(YYINITIAL); return GwendolenTypes.COMMENT;
            }
          // fall through
          case 118: break;
          case 49:
            { yybegin(INITIAL_BELIEFS); return GwendolenTypes.IB_COMMENT;
            }
          // fall through
          case 119: break;
          case 50:
            { yybegin(REASONING_RULES); return GwendolenTypes.RR_COMMENT;
            }
          // fall through
          case 120: break;
          case 51:
            { yybegin(GOALS); return GwendolenTypes.GL_COMMENT;
            }
          // fall through
          case 121: break;
          case 52:
            { yybegin(PLANS_MODE); return GwendolenTypes.PL_COMMENT;
            }
          // fall through
          case 122: break;
          case 53:
            { yybegin(PLANS_MODE); return GwendolenTypes.TRUE;
            }
          // fall through
          case 123: break;
          case 54:
            { yybegin(PLANS_MODE); return GwendolenTypes.LOCK;
            }
          // fall through
          case 124: break;
          case 55:
            { yybegin(PLANS_MODE); return GwendolenTypes.SEND;
            }
          // fall through
          case 125: break;
          case 56:
            { yybegin(PLANS_MODE); return GwendolenTypes.SENT;
            }
          // fall through
          case 126: break;
          case 57:
            { yybegin(PLANS_MODE); return GwendolenTypes.TELL;
            }
          // fall through
          case 127: break;
          case 58:
            { yybegin(YYINITIAL); return GwendolenTypes.NAME;
            }
          // fall through
          case 128: break;
          case 59:
            { yybegin(YYINITIAL); return GwendolenTypes.NAME_PM;
            }
          // fall through
          case 129: break;
          case 60:
            { yybegin(PLANS_MODE); return GwendolenTypes.PLANS;
            }
          // fall through
          case 130: break;
          case 61:
            { if(sq_nesting > 0){
              yybegin(GOALS);
              return GwendolenTypes.GL_ACHIEVEGOAL;
          }
            }
          // fall through
          case 131: break;
          case 62:
            { if(sq_nesting > 0){
              yybegin(GOALS);
              return GwendolenTypes.GL_PERFORMGOAL;
          }
            }
          // fall through
          case 132: break;
          case 63:
            { yybegin(PLANS_MODE); return GwendolenTypes.PL_ACHIEVEGOAL;
            }
          // fall through
          case 133: break;
          case 64:
            { yybegin(PLANS_MODE); return GwendolenTypes.PL_PERFORMGOAL;
            }
          // fall through
          case 134: break;
          case 65:
            { yybegin(YYINITIAL); return GwendolenTypes.GWENDOLEN;
            }
          // fall through
          case 135: break;
          case 66:
            { yybegin(PLANS_MODE); return GwendolenTypes.RECEIVED;
            }
          // fall through
          case 136: break;
          case 67:
            { yybegin(GOALS); return GwendolenTypes.GOAL_IB;
            }
          // fall through
          case 137: break;
          case 68:
            { yybegin(GOALS); return GwendolenTypes.GOAL_RR;
            }
          // fall through
          case 138: break;
          case 69:
            { yybegin(INITIAL_BELIEFS); return GwendolenTypes.BELIEFS;
            }
          // fall through
          case 139: break;
          case 70:
            { yybegin(REASONING_RULES); return GwendolenTypes.BELIEFRULES;
            }
          // fall through
          case 140: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
