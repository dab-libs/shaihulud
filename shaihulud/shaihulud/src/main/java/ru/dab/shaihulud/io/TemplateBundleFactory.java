package ru.dab.shaihulud.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.generator.TemplateBundle;
import ru.dab.shaihulud.io.FileTemplateBundle;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;

public class TemplateBundleFactory {
  public @NotNull TemplateBundle create(
      @NotNull String root, @NotNull String main)
      throws IOException {
    FileTemplateBundle fileBundle = new FileTemplateBundle(root, main);
    IOException fileException = this.checkTemplateBundle(fileBundle);
    if (fileException == null) {
      return fileBundle;
    }
    if (root.startsWith("/") || root.startsWith("\\")) {
      throw fileException;
    }

    ResourceTemplateBundle resourceBundle =
        new ResourceTemplateBundle("/" + root, main);
    IOException resourceException =
        this.checkTemplateBundle(resourceBundle);
    if (resourceException == null) {
      return resourceBundle;
    }
    throw fileException;
  }

  private @Nullable IOException checkTemplateBundle(
      @NotNull TemplateBundle templateBundle) {
    try (Reader reader = templateBundle.getTemplate(templateBundle.getMain())) {
      return null;
    }
    catch (IOException e) {
      return e;
    }
  }
}
