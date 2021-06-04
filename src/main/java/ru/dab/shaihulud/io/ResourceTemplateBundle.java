package ru.dab.shaihulud.io;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.TemplateBundle;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ResourceTemplateBundle implements TemplateBundle {
  private final @NotNull String prefix;
  private final @NotNull String mainTemplate;

  public ResourceTemplateBundle(
      @NotNull String prefix, @NotNull String mainTemplate) {
    prefix = prefix.replace('\\', '/');
    if (!prefix.endsWith("/"))
      prefix = prefix + '/';
    this.prefix = prefix;
    this.mainTemplate = this.prefix + mainTemplate.replace('\\', '/');
  }

  @Override
  public @NotNull String getMain() {
    return mainTemplate;
  }

  @Override
  public @NotNull Reader getTemplate(@NotNull String name) throws IOException {
    String path = prefix + name.replace('\\', '/');
    InputStream inputStream =
        ReaderFactory.class.getClassLoader().getResourceAsStream("/" + path);
    if (inputStream == null) {
      throw new FileNotFoundException("Invalid file path");
    }
    return new InputStreamReader(inputStream, StandardCharsets.UTF_8);
 }
}
