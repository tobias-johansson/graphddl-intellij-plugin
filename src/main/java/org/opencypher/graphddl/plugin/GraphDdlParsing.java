package org.opencypher.graphddl.plugin;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.lang.ASTNode;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.opencypher.graphddl.plugin.lexer.GraphDdlLexer;
import org.opencypher.graphddl.plugin.parser.GraphDdlParser;
import org.opencypher.graphddl.plugin.parser.psi.GraphDdlTypes;

import javax.swing.*;
import java.io.Reader;

public class GraphDdlParsing {

    public static class ParserDefinition implements com.intellij.lang.ParserDefinition {
      public static final TokenSet WHITE_SPACES = TokenSet.create(com.intellij.psi.TokenType.WHITE_SPACE);
      public static final TokenSet COMMENTS = TokenSet.create(GraphDdlTypes.T_LINE_COMMENT);

      public static final IFileElementType FILE = new IFileElementType(GraphDdlFileType.Language.INSTANCE);

      @NotNull
      public static Lexer createLexer() { return new FlexAdapter(new GraphDdlLexer((Reader) null)); }

      @Override @NotNull
      public Lexer createLexer(Project project) { return createLexer(); }

      @NotNull
      public TokenSet getWhitespaceTokens() { return WHITE_SPACES; }

      @NotNull
      public TokenSet getCommentTokens() { return COMMENTS; }

      @NotNull
      public TokenSet getStringLiteralElements() { return TokenSet.EMPTY; }

      @NotNull
      public PsiParser createParser(final Project project) { return new GraphDdlParser(); }

      @Override
      public IFileElementType getFileNodeType() { return FILE; }

      public PsiFile createFile(FileViewProvider viewProvider) { return new File(viewProvider); }

      public SpaceRequirements spaceExistanceTypeBetweenTokens(ASTNode left, ASTNode right) { return SpaceRequirements.MAY; }

      @NotNull
      public PsiElement createElement(ASTNode node) { return GraphDdlTypes.Factory.createElement(node); }
    }

    // -- Lexer/Parser types

    public static class File extends PsiFileBase {
      public File(@NotNull FileViewProvider viewProvider) { super(viewProvider, GraphDdlFileType.Language.INSTANCE); }

      @Override @NotNull
      public FileType getFileType() { return GraphDdlFileType.INSTANCE; }

      @Override
      public String toString() { return GraphDdlFileType.NAME; }

      @Override
      public Icon getIcon(int flags) { return super.getIcon(flags); }
    }

    public static class TokenType extends IElementType {
        public TokenType(@NotNull @NonNls String debugName) { super(debugName, GraphDdlFileType.Language.INSTANCE); }

        @Override
        public String toString() { return "TokenType." + super.toString(); }
    }

    public static class ElementType extends IElementType {
      public ElementType(@NotNull @NonNls String debugName) { super(debugName, GraphDdlFileType.Language.INSTANCE); }
    }

    public interface Element extends PsiElement { }
}
