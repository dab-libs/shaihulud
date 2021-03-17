package ru.dab.shaihulud.specification;

import org.jetbrains.annotations.NotNull;

import java.io.InputStream;
import java.util.Map;

public interface Parser {
  @NotNull Map<String, Object> parse(@NotNull InputStream specification)
      throws ParserException;
}
