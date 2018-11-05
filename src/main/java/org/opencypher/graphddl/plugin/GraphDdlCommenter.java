package org.opencypher.graphddl.plugin;

import com.intellij.lang.Commenter;

import javax.annotation.Nullable;

public class GraphDdlCommenter implements Commenter {
    @Override @Nullable
    public String getLineCommentPrefix() {
        return "--";
    }

    @Override @Nullable
    public String getBlockCommentPrefix() {
        return null;
    }

    @Override @Nullable
    public String getBlockCommentSuffix() {
        return null;
    }

    @Override @Nullable
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    @Override @Nullable
    public String getCommentedBlockCommentSuffix() {
        return null;
    }
}
