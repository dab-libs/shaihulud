package ru.dab.shaihulud.generator.pebble;

import com.mitchellbosecke.pebble.loader.FileLoader;
import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.TemplateBundle;

import java.io.IOException;
import java.io.Reader;

public class Loader extends FileLoader {
  private final TemplateBundle templateBundle;

  public Loader(@NotNull TemplateBundle templateBundle) {
    this.templateBundle = templateBundle;
  }

  @Override
  public Reader getReader(String templateName) {
    try {
      return templateBundle.getTemplate(templateName);
    }
    catch (IOException e) {
      return super.getReader(templateName);
    }
  }
}
