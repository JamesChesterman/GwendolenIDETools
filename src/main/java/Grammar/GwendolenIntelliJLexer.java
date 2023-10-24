package Grammar;

import com.intellij.lexer.Lexer;
import com.intellij.lexer.LexerPosition;
import com.intellij.psi.tree.IElementType;

import groovyjarjarantlr4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.*;
import org.jetbrains.annotations.NotNull;
import psi.GwendolenTypes;

import java.io.IOException;

public class GwendolenIntelliJLexer extends Lexer {
    private GwendolenLexer antlrLexer;
    private CharSequence buffer;
    private int startOffset;
    private int endOffset;
    private int currentOffset;

    public GwendolenIntelliJLexer(CharSequence buffer) {
        this.buffer = buffer;
        CharStream charStream = CharStreams.fromString(buffer.toString());
        this.antlrLexer = new GwendolenLexer(charStream);
        this.startOffset = 0;
        this.currentOffset = 0;
        this.endOffset = buffer.length();
    }

    @Override
    public IElementType getTokenType(){
        Token antlrToken = antlrLexer.nextToken();
        //Map the ANTLR token types to the PSI token types (those in 'GwendolenTypes')
        int antlrTokenType = antlrToken.getType();

        //Returning null as the token type will finish the lexing process
        if(antlrToken.getText().equals("<EOF>")){
            return null;
        }

        return switch (antlrTokenType) {
            case GwendolenLexer.GWENDOLEN -> GwendolenTypes.GWENDOLEN;
            case GwendolenLexer.COMMENT -> GwendolenTypes.COMMENT;
            case GwendolenLexer.LINE_COMMENT -> GwendolenTypes.LINE_COMMENT;
            default -> GwendolenTypes.UNKNOWN;
        };

        /*
        switch(antlrTokenType){
            case GwendolenLexer.GWENDOLEN:
                System.out.println("GWENDOLEN");
                return GwendolenTypes.GWENDOLEN;
            case GwendolenLexer.COMMENT:
                System.out.println("COMMENT");
                return GwendolenTypes.COMMENT;
            case GwendolenLexer.LINE_COMMENT:
                System.out.println("LINE_COMMENT");
                return GwendolenTypes.LINE_COMMENT;
            default:
                //System.out.println("UNKNOWN");
                return GwendolenTypes.UNKNOWN;
        }
         */
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
        return startOffset + antlrLexer._tokenStartCharIndex + antlrLexer._text.length() + 1;

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
        //Already do a nextToken as part of the getTokenType method, so don't need it here.
    }

    @NotNull
    @Override
    public LexerPosition getCurrentPosition(){
        return new GwendolenLexerPosition(antlrLexer.getCharIndex(), antlrLexer.getState());
    }

    @Override
    public void restore(LexerPosition lexPos){
        //THIS WILL LIKELY NEED CHANGING!
        this.currentOffset = lexPos.getOffset();
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
