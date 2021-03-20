package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public interface Generator {
  void generate(
      @NotNull Map<String, Object> specification,
      @NotNull TemplateBundle templateBundle,
      @NotNull ResultStore writer);
}
