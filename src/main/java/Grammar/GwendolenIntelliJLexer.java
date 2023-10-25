package Grammar;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;

import groovyjarjarantlr4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.*;
import org.jetbrains.annotations.NotNull;
import Language.psi.GwendolenTypes;

import java.io.IOException;

public class GwendolenIntelliJLexer extends Lexer {
    private GwendolenLexer antlrLexer;
    private CharSequence buffer;
    private int startOffset;
    private int endOffset;
    private int currentLine;
    private int currentPosInLine;
    private boolean emptyCharSequence;

    public GwendolenIntelliJLexer(CharSequence buffer) {
        this.buffer = buffer;
        CharStream charStream = CharStreams.fromString(buffer.toString());
        this.antlrLexer = new GwendolenLexer(charStream);
        this.startOffset = 0;
        this.endOffset = buffer.length();
    }

    @Override
    public IElementType getTokenType(){
        Token antlrToken = antlrLexer.nextToken();
        //Map the ANTLR token types to the PSI token types (those in 'GwendolenTypes')
        int antlrTokenType = antlrToken.getType();
        System.out.println(antlrToken.getText());

        //Returning null as the token type will finish the lexing process
        if(antlrToken.getText().equals("<EOF>")){
            return null;
        }

        return switch (antlrTokenType) {
            case GwendolenLexer.GOAL -> GwendolenTypes.GOAL;
            case GwendolenLexer.BELIEFRULES -> GwendolenTypes.BELIEFRULES;
            case GwendolenLexer.BELIEFS -> GwendolenTypes.BELIEFS;
            case GwendolenLexer.BELIEF_BLOCK -> GwendolenTypes.BELIEF_BLOCK;
            case GwendolenLexer.BELIEVE -> GwendolenTypes.BELIEVE;
            case GwendolenLexer.CLOSE -> GwendolenTypes.CLOSE;
            case GwendolenLexer.COLON -> GwendolenTypes.COLON;
            case GwendolenLexer.COMMA -> GwendolenTypes.COMMA;
            case GwendolenLexer.CONST -> GwendolenTypes.CONST;
            case GwendolenLexer.CURLYCLOSE -> GwendolenTypes.CURLYCLOSE;
            case GwendolenLexer.CURLYOPEN -> GwendolenTypes.CURLYOPEN;
            case GwendolenLexer.EQUAL -> GwendolenTypes.EQUAL;
            case GwendolenLexer.GL_ACHIEVEGOAL -> GwendolenTypes.GL_ACHIEVEGOAL;
            case GwendolenLexer.GL_PERFORMGOAL -> GwendolenTypes.GL_PERFORMGOAL;
            case GwendolenLexer.GL_SQCLOSE -> GwendolenTypes.GL_SQCLOSE;
            case GwendolenLexer.GL_SQOPEN -> GwendolenTypes.GL_SQOPEN;
            case GwendolenLexer.GOAL_BLOCK -> GwendolenTypes.GOAL_BLOCK;
            case GwendolenLexer.GOAL_IB -> GwendolenTypes.GOAL_IB;
            case GwendolenLexer.GOAL_RR -> GwendolenTypes.GOAL_RR;
            case GwendolenLexer.GWENDOLEN -> GwendolenTypes.GWENDOLEN;
            case GwendolenLexer.IDPUNCT -> GwendolenTypes.IDPUNCT;
            case GwendolenLexer.LESS -> GwendolenTypes.LESS;
            case GwendolenLexer.LOCK -> GwendolenTypes.LOCK;
            case GwendolenLexer.MINUS -> GwendolenTypes.MINUS;
            case GwendolenLexer.MULT -> GwendolenTypes.MULT;
            case GwendolenLexer.NAME -> GwendolenTypes.NAME;
            case GwendolenLexer.NAME_PM -> GwendolenTypes.NAME_PM;
            case GwendolenLexer.NOT -> GwendolenTypes.NOT;
            case GwendolenLexer.NUMBER -> GwendolenTypes.NUMBER;
            case GwendolenLexer.OPEN -> GwendolenTypes.OPEN;
            case GwendolenLexer.PLANS -> GwendolenTypes.PLANS;
            case GwendolenLexer.PLUS -> GwendolenTypes.PLUS;
            case GwendolenLexer.PL_ACHIEVEGOAL -> GwendolenTypes.PL_ACHIEVEGOAL;
            case GwendolenLexer.PL_BAR -> GwendolenTypes.PL_BAR;
            case GwendolenLexer.PL_CONST -> GwendolenTypes.PL_CONST;
            case GwendolenLexer.PL_PERFORMGOAL -> GwendolenTypes.PL_PERFORMGOAL;
            case GwendolenLexer.PL_SQCLOSE -> GwendolenTypes.PL_SQCLOSE;
            case GwendolenLexer.PL_SQOPEN -> GwendolenTypes.PL_SQOPEN;
            case GwendolenLexer.PL_VAR -> GwendolenTypes.PL_VAR;
            case GwendolenLexer.QUOTED_STRING -> GwendolenTypes.QUOTED_STRING;
            case GwendolenLexer.RECEIVED -> GwendolenTypes.RECEIVED;
            case GwendolenLexer.RR_BLOCK -> GwendolenTypes.RR_BLOCK;
            case GwendolenLexer.RR_NEWLINE -> GwendolenTypes.RR_NEWLINE;
            case GwendolenLexer.RULEARROW -> GwendolenTypes.RULEARROW;
            case GwendolenLexer.SEMI -> GwendolenTypes.SEMI;
            case GwendolenLexer.SEND -> GwendolenTypes.SEND;
            case GwendolenLexer.SENT -> GwendolenTypes.SENT;
            case GwendolenLexer.SHRIEK -> GwendolenTypes.SHRIEK;
            case GwendolenLexer.TELL -> GwendolenTypes.TELL;
            case GwendolenLexer.TRUE -> GwendolenTypes.TRUE;
            default -> GwendolenTypes.UNKNOWN;
        };

    }

    //Return the start offset of the current token
    @Override
    public int getTokenStart(){
        return startOffset + antlrLexer._tokenStartCharIndex;
    }

    //Return the end offset of the current token
    @Override
    public int getTokenEnd(){
        //Add 1 to include final character
        return startOffset + antlrLexer._tokenStartCharIndex + antlrLexer.getText().length() + 1;

    }

    @Override
    public void start(@NotNull CharSequence buffer, int startOffset, int endOffset, int initialState){
        this.buffer = buffer;
        this.startOffset = startOffset;
        this.endOffset = endOffset;
        //Changes state variables to defaults
        antlrLexer.reset();
    }

    @Override
    public int getState(){
        //Might correspond to the modes?
        return antlrLexer.getState();
    }

    @Override
    public void advance(){
    }

    @NotNull
    @Override
    public LexerPosition getCurrentPosition(){
        currentLine = antlrLexer.getLine();
        currentPosInLine = antlrLexer.getCharPositionInLine();
        return new GwendolenLexerPosition(antlrLexer.getCharIndex(), antlrLexer.getState());
    }

    @Override
    public void restore(LexerPosition lexPos){
        //THIS WILL LIKELY NEED CHANGING!
        antlrLexer.setLine(currentLine);
        antlrLexer.setCharPositionInLine(currentPosInLine);
        antlrLexer.setState(lexPos.getState());
    }

    @NotNull
    @Override
    public CharSequence getBufferSequence(){
        return buffer;
    }

    @Override
    public int getBufferEnd(){
        return endOffset;
    }

}
