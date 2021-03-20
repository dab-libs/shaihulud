package ru.dab.shaihulud.generator.file;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.ResultStore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileResultStore implements ResultStore {
  private final @NotNull File        root;
  private final @NotNull MultiWriter multiWriter;

  public FileResultStore(@NotNull String rootDir) {
    this.root = new File(rootDir);
    multiWriter = new MultiWriter();
  }

  @Override
  public @NotNull Writer getWriter() {
    return multiWriter;
  }

  @Override
  public void switchTo(@NotNull String fileName) throws IOException {
    String normalizedFileName = fileName
        .replace('/', File.separatorChar)
        .replace('\\', File.separatorChar);
    File file = new File(root, normalizedFileName);
    multiWriter.setWriter(new FileWriter(file));
  }

  public void switchToSystemOut() throws IOException {
    multiWriter.setWriter(multiWriter.createSystemOutWriter());
  }

  @Override
  public void close() throws IOException {
    multiWriter.flush();
    multiWriter.close();
  }
}
