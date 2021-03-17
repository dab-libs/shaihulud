package ru.dab.shaihulud.input;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class FileInputStreamFactory {
  public @NotNull InputStream create(@NotNull String name)
      throws FileNotFoundException {
    return new FileInputStream(name);
  }

  public @Nullable InputStream createOptional(@Nullable String name)
      throws FileNotFoundException {
    if (name == null) {
      return null;
    }
    return new FileInputStream(name);
  }
}
