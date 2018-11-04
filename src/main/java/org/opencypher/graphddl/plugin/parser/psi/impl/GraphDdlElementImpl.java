package org.opencypher.graphddl.plugin.parser.psi.impl;

import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.opencypher.graphddl.plugin.GraphDdlParsing;
import org.jetbrains.annotations.NotNull;

public abstract class GraphDdlElementImpl extends ASTWrapperPsiElement implements GraphDdlParsing.Element {
  public GraphDdlElementImpl(@NotNull ASTNode node) {
    super(node);
  }
}