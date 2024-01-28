package ru.dab.shaihulud.generator.io;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.ResultStore;

import java.io.*;

public class FileResultStore implements ResultStore {
  public static final String STD_OUT = "std_out";
  private final @NotNull File root;
  private final @NotNull MultiWriter multiWriter;

  public FileResultStore(@NotNull File root) {
    this.root = root;
    multiWriter = new MultiWriter();
    multiWriter.subscribe(this::switchTo);
  }

  @Override
  public @NotNull Writer getWriter() {
    return multiWriter;
  }

  @Override
  public void switchTo(@NotNull String fileName) throws IOException {
    if (fileName.equals(STD_OUT)) {
      multiWriter.setWriter(multiWriter.createSystemOutWriter());
    }
    String normalizedFileName = fileName
        .replace('/', File.separatorChar)
        .replace('\\', File.separatorChar);
    File file = new File(root, normalizedFileName);
    File parent = file.getParentFile();
    if (!parent.exists() && !parent.mkdirs()) {
      throw new IOException("Couldn't create dir: " + parent);
    }
    multiWriter.setWriter(new BufferedWriter(new FileWriter(file)));
  }

  @Override
  public void close() throws IOException {
    multiWriter.unsubscribe(this::switchTo);
    multiWriter.flush();
    multiWriter.close();
  }
}
