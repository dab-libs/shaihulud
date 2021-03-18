package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.io.Writer;
import java.util.Map;

public interface Generator {
  void generate(
      @NotNull Map<String, Object> specification,
      @NotNull Reader templateReader,
      @NotNull Writer writer);
}
