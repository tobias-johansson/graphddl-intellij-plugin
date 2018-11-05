package org.opencypher.graphddl.plugin;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.fileTypes.FileTypeConsumer;
import com.intellij.openapi.fileTypes.FileTypeFactory;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.*;

import javax.swing.*;

public class GraphDdlFileType extends LanguageFileType {
  public static final GraphDdlFileType INSTANCE = new GraphDdlFileType();
  public static final String NAME              = "Graph DDL file";
  public static final String DESCRIPTION       = "Graph DDL language file";
  public static final String DEFAULT_EXTENSION = "ddl";

  private GraphDdlFileType() {
    super(Language.INSTANCE);
  }

  @Override @NotNull
  public String getName() {
    return NAME;
  }

  @Override @NotNull
  public String getDescription() {
    return DESCRIPTION;
  }

  @Override @NotNull
  public String getDefaultExtension() {
    return DEFAULT_EXTENSION;
  }

  @Override @Nullable
  public Icon getIcon() { return Icons.FILE; }

  public static class Factory extends FileTypeFactory {
    @Override
    public void createFileTypes(@NotNull FileTypeConsumer fileTypeConsumer) {
      fileTypeConsumer.consume(INSTANCE);
    }
  }

  public static class Icons {
    public static final Icon FILE = AllIcons.FileTypes.Custom;
  }

  public static class Language extends com.intellij.lang.Language {
    public static final Language INSTANCE = new Language();

    private Language() {
      super("GRAPHDDL");
    }
  }
}