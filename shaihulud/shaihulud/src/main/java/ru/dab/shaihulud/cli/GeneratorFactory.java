package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.Generator;
import ru.dab.shaihulud.generator.mustache.MustacheGenerator;
import ru.dab.shaihulud.generator.pebble.PebbleGenerator;

public class GeneratorFactory {
  public @NotNull Generator create() {
    return new PebbleGenerator();
  }
}
