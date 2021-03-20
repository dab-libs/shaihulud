package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.file.FileResultStore;
import ru.dab.shaihulud.generator.ResultStore;

import java.io.File;
import java.io.IOException;

public class FileResultStoreFactory {
  private final @NotNull FileResultStoreOptions options;

  public FileResultStoreFactory(@NotNull FileResultStoreOptions options) {
    this.options = options;
  }

  public @NotNull ResultStore create() {
    String root = getRoot();
    return new FileResultStore(root);
  }

  public @NotNull String getRoot() {
    String root = options.getOutDirectory();
    if (root == null) {
      root = "";
    }
    else {
      root = root
          .replace('/', File.separatorChar)
          .replace('\\', File.separatorChar);
    }
    File file = new File(root);
    try {
      return file.getCanonicalPath();
    }
    catch (IOException e) {
      return file.getAbsolutePath();
    }
  }
}
