package ru.dab.shaihulud.io;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.TemplateBundle;
import ru.dab.shaihulud.io.FileTemplateBundle;

import java.io.FileNotFoundException;

public class TemplateBundleFactory {
  public @NotNull TemplateBundle create(
      @NotNull String root, @NotNull String main)
      throws FileNotFoundException {
    return new FileTemplateBundle(root, main);
  }
}
