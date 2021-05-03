package ru.dab.shaihulud.specification;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Reader;

public class ParserFactory {
  public @NotNull Parser create(
      @NotNull SpecificationParserType parserType, @Nullable Reader schema) {
    switch (parserType) {
      case Json:
        return new JsonParser(schema);
      case Yaml:
        return new YamlParser(schema);
      default:
        throw new RuntimeException();
    }
  }
}
