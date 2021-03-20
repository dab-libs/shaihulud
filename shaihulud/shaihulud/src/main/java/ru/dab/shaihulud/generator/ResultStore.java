package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;

import java.io.Closeable;
import java.io.IOException;
import java.io.Writer;

public interface ResultStore extends Closeable {
  @NotNull Writer getWriter();

  void switchTo(@NotNull String fileName) throws IOException;
}
