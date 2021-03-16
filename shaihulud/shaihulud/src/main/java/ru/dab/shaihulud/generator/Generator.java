package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.specification.Parser;
import ru.dab.shaihulud.specification.ParserException;
import ru.dab.shaihulud.specification.ParserFactory;
import ru.dab.shaihulud.templating.TemplateProcessorFactory;

import java.io.*;
import java.util.Map;

class Generator {
  private final @NotNull GeneratorOptions options;

  public Generator(@NotNull GeneratorOptions options) {
    this.options = options;
  }

  public void generate() {
    try (
        InputStream schemaStream = createSchemaStream();
        InputStream specificationStream = createSpecStream();
        StringReader templateReader = createTemplateReader();
        Writer resultWriter = createResultWriter()
    ) {
      Parser parser = ParserFactory.create(options.getSpecificationFormat());
      Map<String, Object> specification =
          parser.parse(specificationStream, schemaStream);

      new TemplateProcessorFactory()
          .create()
          .process(specification, templateReader, resultWriter);
    }
    catch (ParserException | IOException e) {
      System.err.println(e.getMessage());
    }
  }

  @NotNull
  private StringReader createTemplateReader() {
    return new StringReader(options.getTemplate());
  }

  private @NotNull OutputStreamWriter createResultWriter() {
    return new OutputStreamWriter(System.out);
  }

  private @NotNull InputStream createSpecStream()
      throws FileNotFoundException {
    String specificationPath = options.getSpecification();
    return new FileInputStream(specificationPath);
  }

  private @Nullable InputStream createSchemaStream()
      throws FileNotFoundException {
    InputStream schemaStream = null;
    String schemaPath = options.getSchema();
    if (schemaPath != null) {
      schemaStream = new FileInputStream(schemaPath);
    }
    return schemaStream;
  }

}
