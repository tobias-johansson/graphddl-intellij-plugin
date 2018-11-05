package org.opencypher.graphddl.plugin;


import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.psi.PsiElement;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class GraphDdlCompletions extends CompletionContributor {
    private final String[] KEYWORDS = new String[] {
            "CATALOG",
            "CREATE",
            "LABEL",
            "GRAPH",
            "KEY",
            "WITH",
            "FROM",
            "NODE",
            "NODES",
            "RELATIONSHIP",
            "SET",
            "SETS",
            "JOIN",
            "ON",
            "AND",
            "AS",
            "SCHEMA",
            "START",
            "END"
    };

    private final String[] TYPES = new String[] {
            "STRING",
            "INTEGER",
            "FLOAT",
            "BOOLEAN"
    };

    public GraphDdlCompletions() {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement(PsiElement.class).withLanguage(GraphDdlFileType.Language.INSTANCE),
            new CompletionProvider<CompletionParameters>() {
                public void addCompletions(@NotNull CompletionParameters parameters,
                                           ProcessingContext context,
                                           @NotNull CompletionResultSet resultSet) {
                    for (String keyword : KEYWORDS) {
                        resultSet.addElement(kwLookup(keyword));
                    }
                    for (String type : TYPES) {
                        resultSet.addElement(kwLookup(type));
                    }
                }
            }
        );
    }

    private static LookupElement kwLookup(String kw) {
        return LookupElementBuilder
                .create(kw)
                .withLookupString(kw.toLowerCase());
    }
}