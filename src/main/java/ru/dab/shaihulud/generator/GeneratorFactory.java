package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;

public class GeneratorFactory {
  public @NotNull Generator create() {
    return new PebbleGenerator();
  }
}
