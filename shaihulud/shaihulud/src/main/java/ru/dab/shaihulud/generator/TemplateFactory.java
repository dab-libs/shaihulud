package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;

public class TemplateFactory {
  private final @NotNull TemplateOptions options;

  public TemplateFactory(@NotNull TemplateOptions options) {
    this.options = options;
  }

  public @NotNull Reader create() throws FileNotFoundException {
    return new FileReader(options.getTemplate());
  }
}
