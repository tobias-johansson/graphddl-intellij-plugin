package org.opencypher.graphddl.plugin;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.*;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.opencypher.graphddl.plugin.parser.psi.GraphDdlTypes;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class GraphDdlSyntaxHighlighter extends SyntaxHighlighterBase {
  public static final TextAttributesKey KEYWORD =
      createTextAttributesKey("SIMPLE_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
  public static final TextAttributesKey TYPE =
          createTextAttributesKey("SIMPLE_TYPE", DefaultLanguageHighlighterColors.CLASS_REFERENCE);
  public static final TextAttributesKey IDENTIFIER =
          createTextAttributesKey("SIMPLE_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER);
  public static final TextAttributesKey SPECIAL =
      createTextAttributesKey("SIMPLE_SPECIAL", DefaultLanguageHighlighterColors.BRACKETS);
  public static final TextAttributesKey COMMENT =
      createTextAttributesKey("SIMPLE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
  public static final TextAttributesKey BAD_CHARACTER =
      createTextAttributesKey("SIMPLE_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);


  private static final TextAttributesKey[] KEYWORD_KEYS    = new TextAttributesKey[]{KEYWORD};
  private static final TextAttributesKey[] TYPE_KEYS       = new TextAttributesKey[]{TYPE};
  private static final TextAttributesKey[] IDENTIFIER_KEYS = new TextAttributesKey[]{IDENTIFIER};
  private static final TextAttributesKey[] SPECIAL_KEYS    = new TextAttributesKey[]{SPECIAL};
  private static final TextAttributesKey[] COMMENT_KEYS    = new TextAttributesKey[]{COMMENT};
  private static final TextAttributesKey[] BAD_CHAR_KEYS   = new TextAttributesKey[]{BAD_CHARACTER};
  private static final TextAttributesKey[] EMPTY_KEYS      = new TextAttributesKey[0];

  @Override @NotNull
  public Lexer getHighlightingLexer() { return GraphDdlParsing.ParserDefinition.createLexer(); }

  @Override @NotNull
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    if      (tokenType.equals(GraphDdlTypes.T_KEYWORD))      return KEYWORD_KEYS;
    else if (tokenType.equals(GraphDdlTypes.T_TYPE))         return TYPE_KEYS;
    else if (tokenType.equals(GraphDdlTypes.T_IDENTIFIER))   return IDENTIFIER_KEYS;
    else if (tokenType.equals(GraphDdlTypes.T_SPECIAL))      return SPECIAL_KEYS;
    else if (tokenType.equals(GraphDdlTypes.T_LINE_COMMENT)) return COMMENT_KEYS;
    else if (tokenType.equals(TokenType.BAD_CHARACTER))      return BAD_CHAR_KEYS;
    else                                                     return EMPTY_KEYS;

  }

  public static class Factory extends SyntaxHighlighterFactory {

    @Override @NotNull
    public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
      return new GraphDdlSyntaxHighlighter();
    }
  }
}