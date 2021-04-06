package ru.dab.shaihulud.config;

import org.jetbrains.annotations.NotNull;

public class ConfigParserFactory {
  public @NotNull ConfigParser create() {
    return new JsonConfigParser();
  }
}
