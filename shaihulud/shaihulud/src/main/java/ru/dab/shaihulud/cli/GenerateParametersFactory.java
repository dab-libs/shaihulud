package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.result.MemoryResultWriter;
import ru.dab.shaihulud.specification.JsonSpecificationParser;
import ru.dab.shaihulud.specification.SpecificationParser;
import ru.dab.shaihulud.specification.YamlSpecificationParser;
import ru.dab.shaihulud.templating.MustacheTemplateProcessor;
import ru.dab.shaihulud.templating.TemplateProcessor;

import java.io.*;

public class GenerateParametersFactory {
  private final @NotNull GenerateOptions options;

  public GenerateParametersFactory(@NotNull GenerateOptions options) {
    this.options = options;
  }

  public @Nullable InputStream createSchema()
      throws FileNotFoundException {
    String name = options.getSchema();
    if (name == null) {
      return null;
    }
    return new FileInputStream(name);
  }

  public @NotNull InputStream createSpecification()
      throws FileNotFoundException {
    return new FileInputStream(options.getSpecification());
  }

  public @NotNull Reader createTemplate() throws FileNotFoundException {
    return new FileReader(options.getTemplate());
  }

  public @NotNull Writer createResultWriter() {
    return new MemoryResultWriter();
  }

  public @NotNull SpecificationParser createParser(InputStream schema) {
    switch (options.getSpecificationFormat()) {
      case Json:
        return new JsonSpecificationParser(schema);
      case Yaml:
        return new YamlSpecificationParser(schema);
      default:
        throw new RuntimeException();
    }
  }

  public @NotNull TemplateProcessor createTemplateProcessor() {
    return new MustacheTemplateProcessor();
  }
}
