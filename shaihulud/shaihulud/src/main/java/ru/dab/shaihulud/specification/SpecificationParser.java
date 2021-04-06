package ru.dab.shaihulud.specification;

import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.util.Map;

public interface SpecificationParser {
  @NotNull Map<String, Object> parse(@NotNull Reader specificationReader)
      throws ParserException;
}
