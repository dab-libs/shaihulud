package ru.dab.shaihulud.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReaderFactory {
  public @NotNull Reader create(@NotNull String path)
      throws FileNotFoundException {
    try {
      return new FileReader(path);
    }
    catch (FileNotFoundException e) {
      if (path.startsWith("/") || path.startsWith("\\")) {
        throw e;
      }
      InputStream inputStream =
          ReaderFactory.class.getClassLoader().getResourceAsStream("/" + path);
      if (inputStream == null) {
        throw e;
      }
      return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    }
  }

  public @Nullable Reader createOptional(@Nullable String path)
      throws FileNotFoundException {
    if (path == null) {
      return null;
    }
    return create(path);
  }
}
