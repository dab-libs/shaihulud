package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.TemplateBundle;
import ru.dab.shaihulud.generator.file.FileTemplateBundle;

import java.io.FileNotFoundException;

public class TemplateBundleFactory {
  private final @NotNull TemplateBundleOptions options;

  public TemplateBundleFactory(@NotNull TemplateBundleOptions options) {
    this.options = options;
  }

  public @NotNull TemplateBundle create() throws FileNotFoundException {
    return new FileTemplateBundle(options.getRoot(), options.getMain(),
                                  "twig");
  }
}
