package ru.dab.shaihulud.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ReaderFactory {
  public @NotNull Reader create(@NotNull String path)
      throws IOException {
    path = path.replace('\\', '/');
    try {
      return new FileReader(path);
    }
    catch (FileNotFoundException e) {
      final URL url = ReaderFactory.class.getClassLoader().getResource(path);
      if (url == null) {
        throw e;
      }
      InputStream inputStream =
          ReaderFactory.class.getClassLoader().getResourceAsStream(path);
      if (inputStream == null) {
        throw new IOException("Can not open '" + url + "'");
      }
      return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
    }
  }

  public @Nullable Reader createOptional(@Nullable String path)
      throws IOException {
    if (path == null) {
      return null;
    }
    return create(path);
  }
}
