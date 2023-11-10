package Language;

import Language.Parser.GwendolenParser;
import Language.psi.GwendolenFile;
import Language.psi.GwendolenTokenSets;
import Language.psi.GwendolenTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.io.File;

final class GwendolenParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(GwendolenLanguage.INSTANCE);

    @NotNull
    @Override
    public Lexer createLexer(Project project){
        return new GwendolenLexerAdapter();
    }


    @NotNull
    @Override
    public TokenSet getCommentTokens(){
        TokenSet allComments = TokenSet.orSet(GwendolenTokenSets.COMMENT, GwendolenTokenSets.GL_COMMENT,
                GwendolenTokenSets.IB_COMMENT, GwendolenTokenSets.RR_COMMENT, GwendolenTokenSets.PL_COMMENT,
                GwendolenTokenSets.LINE_COMMENT, GwendolenTokenSets.IB_LINE_COMMENT,
                GwendolenTokenSets.GL_LINE_COMMENT, GwendolenTokenSets.RR_LINE_COMMENT,
                GwendolenTokenSets.PL_LINE_COMMENT);
        return allComments;
    }

    @Override
    @NotNull
    public TokenSet getWhitespaceTokens(){
        //Skips these tokens
        TokenSet allWhitespace = TokenSet.orSet(GwendolenTokenSets.WS, GwendolenTokenSets.GL_WS,
                GwendolenTokenSets.IB_WS, GwendolenTokenSets.RR_WS, GwendolenTokenSets.PL_WS,
                GwendolenTokenSets.NEWLINE, GwendolenTokenSets.GL_NEWLINE, GwendolenTokenSets.IB_NEWLINE,
                GwendolenTokenSets.PL_NEWLINE, GwendolenTokenSets.RR_NEWLINE);
        return allWhitespace;
    }


    @NotNull
    @Override
    public TokenSet getStringLiteralElements(){
        return GwendolenTokenSets.QUOTED_STRING;
    }

    @NotNull
    @Override
    public PsiParser createParser(final Project project){
        return new GwendolenParser();
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType(){
        return FILE;
    }

    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider){
        return new GwendolenFile(viewProvider);
    }

    @NotNull
    @Override
    public PsiElement createElement(ASTNode node){
        return GwendolenTypes.Factory.createElement(node);
    }

}
