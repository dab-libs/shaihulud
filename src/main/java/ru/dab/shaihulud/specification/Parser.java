package ru.dab.shaihulud.specification;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Reader;
import java.util.Map;

public interface Parser {
  @NotNull Map<String, Object> parse(@NotNull Reader specificationReader,
                                     @Nullable Reader schemaReader)
      throws ParserException;
  @NotNull Map<String, Object> parse(@NotNull Reader specificationReader)
      throws ParserException;
}
