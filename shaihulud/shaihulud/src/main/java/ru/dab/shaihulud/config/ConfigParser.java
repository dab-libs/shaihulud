package ru.dab.shaihulud.config;

import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.util.Map;

public interface ConfigParser {
  @NotNull Map<String, Object> parse(@NotNull Reader configReader)
      throws ConfigException;
}
