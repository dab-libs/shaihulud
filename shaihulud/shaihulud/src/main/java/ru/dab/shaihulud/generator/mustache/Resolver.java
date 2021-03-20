package ru.dab.shaihulud.generator.mustache;

import com.github.mustachejava.MustacheResolver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.generator.TemplateBundle;

import java.io.IOException;
import java.io.Reader;

public class Resolver implements MustacheResolver {
  private final @NotNull TemplateBundle templateBundle;

  public Resolver(@NotNull TemplateBundle templateBundle) {
    this.templateBundle = templateBundle;
  }

  @Override
  public @Nullable Reader getReader(@NotNull String name) {
    try {
      return templateBundle.getTemplate(name);
    }
    catch (IOException e) {
      return null;
    }
  }
}
