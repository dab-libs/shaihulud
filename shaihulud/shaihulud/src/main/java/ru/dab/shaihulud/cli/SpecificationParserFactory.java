package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.specification.JsonSpecificationParser;
import ru.dab.shaihulud.specification.SpecificationParser;
import ru.dab.shaihulud.specification.YamlSpecificationParser;

import java.io.InputStream;

public class SpecificationParserFactory {
  private final @NotNull SpecificationParserOptions options;

  public SpecificationParserFactory(
      @NotNull SpecificationParserOptions options) {
    this.options = options;
  }

  public @NotNull SpecificationParser create(@Nullable InputStream schema) {
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
