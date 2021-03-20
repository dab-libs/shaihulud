package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileTemplateBundle implements TemplateBundle {
  private final @NotNull File   root;
  private final @NotNull String mainTemplate;
  private final @NotNull String extension;

  public FileTemplateBundle(
      @NotNull String rootDir, @NotNull String mainTemplate,
      @NotNull String extension) {
    this.root = new File(rootDir);
    this.mainTemplate = mainTemplate;
    this.extension = extension;
  }

  @Override
  public @NotNull String getMain() {
    return mainTemplate;
  }

  @Override
  public @NotNull Reader getTemplate(@NotNull String name) throws IOException {
    String resourceName =
        name.replace('.', File.separatorChar) + '.' + extension;

    File templateFile = new File(root, resourceName);

    validateFileExists(templateFile);
    validateFileUnderFoot(templateFile);

    return new BufferedReader(
        new InputStreamReader(
            new FileInputStream(templateFile),
            StandardCharsets.UTF_8));
  }

  private void validateFileExists(File templateFile) throws IOException {
    if (!templateFile.exists()) {
      throw new IOException(
          "'" + templateFile.getAbsolutePath() + "' is not found");
    }
    if (!templateFile.isFile()) {
      throw new IOException(
          "'" + templateFile.getAbsolutePath() + "' is not a file");
    }
  }

  private void validateFileUnderFoot(File templateFile) throws IOException {
    File checkRoot = root.getCanonicalFile();
    File parent = templateFile.getCanonicalFile();
    while ((parent = parent.getParentFile()) != null) {
      if (parent.equals(checkRoot)) {
        break;
      }
    }
    if (parent == null) {
      throw new IOException(
          "File not under root: " + checkRoot.getAbsolutePath());
    }
  }
}
