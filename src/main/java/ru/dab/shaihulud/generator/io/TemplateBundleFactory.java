package ru.dab.shaihulud.generator.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.generator.TemplateBundle;
import ru.dab.shaihulud.generator.io.FileTemplateBundle;
import ru.dab.shaihulud.generator.io.ResourceTemplateBundle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

public class TemplateBundleFactory {
  public @NotNull TemplateBundle create(
      @NotNull String root, @NotNull String main)
      throws IOException {
    FileTemplateBundle fileBundle = new FileTemplateBundle(root, main);
    this.checkTemplateBundle(fileBundle);

    if (root.startsWith("/") || root.startsWith("\\")) {
      throw new FileNotFoundException("Invalid template root '" + root + "'");
    }

    TemplateBundle resourceBundle = new ResourceTemplateBundle(root, main);
    this.checkTemplateBundle(resourceBundle);
    return resourceBundle;
  }

  private void checkTemplateBundle(
      @NotNull TemplateBundle templateBundle) throws IOException {
    //noinspection EmptyTryBlock
    try (Reader reader = templateBundle.getTemplate(templateBundle.getMain())) {
    }
  }
}
