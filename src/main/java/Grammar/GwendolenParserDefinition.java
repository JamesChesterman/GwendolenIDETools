package Grammar;

import Language.GwendolenLanguage;
import Language.psi.GwendolenTypes;
import Language.Parser.GwendolenParser;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.*;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;


public class GwendolenParserDefinition implements ParserDefinition {
    public static final IFileElementType FILE = new IFileElementType(GwendolenLanguage.INSTANCE);


    //Gets contents of what should be the currently active file, then initialises a lexer with that text input.
    @NotNull
    @Override
    public Lexer createLexer(Project project){
        /*
        System.out.println(Objects.requireNonNull(project.getWorkspaceFile()).getName());
        //Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
        FileEditorManager fEditorManager = FileEditorManager.getInstance(project);
        System.out.println("HERE");
        Editor editor = fEditorManager.getSelectedTextEditor();
        System.out.println("HERE2");

        if(editor != null){
            Document currentDoc = editor.getDocument();
            VirtualFile virtualFile = FileDocumentManager.getInstance().getFile(currentDoc);
            if(virtualFile != null){
                PsiManager psiManager = PsiManager.getInstance(project);
                SingleRootFileViewProvider fileViewProvider = new SingleRootFileViewProvider(psiManager, virtualFile);
                CharSequence charSequence = fileViewProvider.getContents();
                return new GwendolenIntelliJLexer(charSequence);
            }
        }
        CharSequence empty = "";
        return new GwendolenIntelliJLexer(empty);
         */

        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        VirtualFile[] selectedFiles = fileEditorManager.getSelectedFiles();
        CharSequence empty = "";
        if(selectedFiles.length > 0){
            VirtualFile virtualFile = selectedFiles[0];
            FileDocumentManager fileDocumentManager = FileDocumentManager.getInstance();
            CharSequence charSequence = (fileDocumentManager.getDocument(virtualFile)).getText();
            if(charSequence.isEmpty()){
                return new GwendolenIntelliJLexer(empty);
            }else{
                return new GwendolenIntelliJLexer(charSequence);
            }
        }else{
            return new GwendolenIntelliJLexer(empty);
        }

    }

    @NotNull
    @Override
    public TokenSet getCommentTokens(){
        return GwendolenTokenSets.EQUAL;
    }

    @NotNull
    @Override
    public TokenSet getStringLiteralElements(){
        return TokenSet.EMPTY;
    }



    @Override
    public PsiParser createParser(final Project project){
        return new GwendolenParser();
    }


    @NotNull
    @Override
    public PsiFile createFile(@NotNull FileViewProvider viewProvider){
        return new GwendolenFile(viewProvider);
    }


    public PsiElement createElement(ASTNode node){
        return GwendolenTypes.Factory.createElement(node);
    }

    @NotNull
    @Override
    public IFileElementType getFileNodeType(){
        return FILE;
    }


}
