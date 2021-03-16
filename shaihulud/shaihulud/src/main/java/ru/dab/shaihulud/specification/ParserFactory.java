package ru.dab.shaihulud.specification;

import org.jetbrains.annotations.NotNull;

public class ParserFactory {
  public static @NotNull Parser create(@NotNull SpecificationFormat format) {
    switch (format) {
      case Json:
        return new JsonParser();
      case Yaml:
      default:
        throw new RuntimeException();
    }

  }
}
