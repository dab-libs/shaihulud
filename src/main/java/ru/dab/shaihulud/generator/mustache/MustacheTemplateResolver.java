package ru.dab.shaihulud.generator.mustache;

import com.github.mustachejava.MustacheResolver;
import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.TemplateBundle;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

public class MustacheTemplateResolver implements MustacheResolver {
  private final TemplateBundle templateBundle;

  public MustacheTemplateResolver(@NotNull TemplateBundle templateBundle) {
    this.templateBundle = templateBundle;
  }

  @Override
  public Reader getReader(String resourceName) {
    try {
      return templateBundle.getTemplate(resourceName);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}
