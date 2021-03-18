package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;

public class GeneratorFactory {
  private final @NotNull GeneratorOptions options;

  public GeneratorFactory(@NotNull GeneratorOptions options) {
    this.options = options;
  }

  public @NotNull Generator create() {
    return new MustacheGenerator();
  }
}
