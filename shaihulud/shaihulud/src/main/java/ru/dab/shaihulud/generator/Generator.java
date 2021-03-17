package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.specification.SpecificationParser;
import ru.dab.shaihulud.specification.ParserException;
import ru.dab.shaihulud.templating.TemplateProcessor;

import java.io.*;
import java.util.Map;

class Generator {
  private final @NotNull SpecificationParser specificationParser;
  private final @NotNull TemplateProcessor   templateProcessor;

  public Generator(
      @NotNull SpecificationParser specificationParser,
      @NotNull TemplateProcessor templateProcessor) {
    this.specificationParser = specificationParser;
    this.templateProcessor = templateProcessor;
  }

  public void generate(
      @NotNull InputStream specificationStream,
      @NotNull Reader templateReader, @NotNull Writer resultWriter)
      throws ParserException {
    Map<String, Object> specification =
        specificationParser.parse(specificationStream);
    templateProcessor
        .process(specification, templateReader, resultWriter);
  }
}
