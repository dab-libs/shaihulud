package ru.dab.shaihulud.generator;

import com.github.mustachejava.MustacheException;
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
  public @NotNull Reader getTemplate(@NotNull String name)
      throws IOException {
    String resourceName =
        name.replace('.', File.separatorChar) + '.' + extension;
    InputStream is = null;
    File file = new File(root, resourceName);
    if (file.exists() && file.isFile()) {
      File checkRoot = root.getCanonicalFile();
      File parent = file.getCanonicalFile();
      while ((parent = parent.getParentFile()) != null) {
        if (parent.equals(checkRoot)) {
          break;
        }
      }
      if (parent == null) {
        throw new IOException(
            "File not under root: " + checkRoot.getAbsolutePath());
      }
      is = new FileInputStream(file);
    }
    if (is != null) {
      return new BufferedReader(
          new InputStreamReader(is, StandardCharsets.UTF_8));
    }
    else {
      throw new IOException(
          file.getAbsolutePath() + " is not a file");
    }
  }
}
