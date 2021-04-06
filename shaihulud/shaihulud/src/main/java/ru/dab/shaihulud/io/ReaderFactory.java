package ru.dab.shaihulud.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class ReaderFactory {
  public @NotNull Reader create(@NotNull String path)
      throws FileNotFoundException {
    return new FileReader(path);
  }

  public @Nullable Reader createOptional(@Nullable String path)
      throws FileNotFoundException {
    return path != null ? new FileReader(path) : null;
  }
}
