package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.Map;

public interface Generator {
  void generate(
      @NotNull Map<String, Object> specification,
      @NotNull TemplateBundle templateBundle,
      @NotNull ResultStore writer,
      @Nullable Map<String, Object> options) throws IOException;
}
