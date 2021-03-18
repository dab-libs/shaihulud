package ru.dab.shaihulud.specification;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.InputStream;

public class SpecificationParserFactory {
  private final @NotNull SpecificationParserOptions options;

  public SpecificationParserFactory(
      @NotNull SpecificationParserOptions options) {
    this.options = options;
  }

  public @NotNull SpecificationParser create(@NotNull InputStream schema) {
    switch (options.getSpecificationParserType()) {
      case Json:
        return new JsonSpecificationParser(schema);
      case Yaml:
        return new YamlSpecificationParser(schema);
      default:
        throw new RuntimeException();
    }
  }
}
