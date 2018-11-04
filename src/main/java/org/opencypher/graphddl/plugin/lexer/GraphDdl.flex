package org.opencypher.graphddl.plugin.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import static org.opencypher.graphddl.plugin.parser.psi.GraphDdlTypes.*;

%%

%{
  public GraphDdlLexer() {
    this((java.io.Reader)null);
  }
%}

%public
%class GraphDdlLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode

EOL=\R

WHITE_SPACE=\s+

SPECIAL = ";"|","|"("|")"|"{"|"}"|"["|"]"|"$"|":"|"."|"="|"?"|"-"|">"

KEYWORD =
  "CATALOG"      | "catalog"      |
  "CREATE"       | "create"       |
  "LABEL"        | "label"        |
  "GRAPH"        | "graph"        |
  "KEY"          | "key"          |
  "WITH"         | "with"         |
  "FROM"         | "from"         |
  "NODE"         | "node"         |
  "NODES"        | "nodes"        |
  "RELATIONSHIP" | "relationship" |
  "SET"          | "set"          |
  "SETS"         | "sets"         |
  "JOIN"         | "join"         |
  "ON"           | "on"           |
  "AND"          | "and"          |
  "AS"           | "as"           |
  "SCHEMA"       | "schema"       |
  "START"        | "start"        |
  "END"          | "end"

TYPE =
  "STRING"  | "string"  |
  "INTEGER" | "integer" |
  "FLOAT"   | "float"   |
  "BOOLEAN" | "boolean"

LINE_COMMENT = "--" [^\r\n]*

IDENTIFIER = \w+

%%
<YYINITIAL> {
  {WHITE_SPACE}  { return com.intellij.psi.TokenType.WHITE_SPACE; }
  {LINE_COMMENT} { return T_LINE_COMMENT; }
  {SPECIAL}      { return T_SPECIAL; }
  {KEYWORD}      { return T_KEYWORD; }
  {TYPE}         { return T_TYPE; }
  {IDENTIFIER}   { return T_IDENTIFIER; }
}

[^]              { return TokenType.BAD_CHARACTER; }