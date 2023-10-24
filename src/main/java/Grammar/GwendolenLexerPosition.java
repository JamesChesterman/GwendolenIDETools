package Grammar;

import com.intellij.lexer.LexerPosition;

public class GwendolenLexerPosition implements LexerPosition {
    int offset;
    int state;

    public GwendolenLexerPosition(int offset, int state){
        this.offset = offset;
        this.state = state;
    }

    public int getOffset(){
        return offset;
    }

    public int getState(){
        return state;
    }
}
