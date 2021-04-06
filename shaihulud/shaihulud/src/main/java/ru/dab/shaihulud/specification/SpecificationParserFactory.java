package ru.dab.shaihulud.specification;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Reader;

public class SpecificationParserFactory {
  public @NotNull SpecificationParser create(
      @NotNull SpecificationParserType parserType, @Nullable Reader schema) {
    switch (parserType) {
      case Json:
        return new JsonSpecificationParser(schema);
      case Yaml:
        return new YamlSpecificationParser(schema);
      default:
        throw new RuntimeException();
    }
  }
}
