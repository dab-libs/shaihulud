package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SchemaFactory {
  private final @NotNull SchemaOptions options;

  public SchemaFactory(@NotNull SchemaOptions options) {
    this.options = options;
  }

  public @Nullable InputStream create() throws FileNotFoundException {
    String name = options.getSchema();
    if (name == null) {
      return null;
    }
    return new FileInputStream(name);
  }
}