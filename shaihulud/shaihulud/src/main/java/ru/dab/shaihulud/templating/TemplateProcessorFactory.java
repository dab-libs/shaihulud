package ru.dab.shaihulud.templating;

import org.jetbrains.annotations.NotNull;

public class TemplateProcessorFactory {
  public @NotNull TemplateProcessor create() {
    return new MustacheTemplateProcessor();
  }
}
