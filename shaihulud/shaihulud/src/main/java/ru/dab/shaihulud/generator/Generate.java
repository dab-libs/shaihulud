package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.input.FileInputStreamFactory;
import ru.dab.shaihulud.result.MemoryResultWriter;
import ru.dab.shaihulud.specification.*;
import ru.dab.shaihulud.templating.MustacheTemplateProcessor;

import java.io.*;

public class Generate {
  public static void main(String[] args) {
    GenerateOptions options = createOptions(args);
    if (options == null) {
      return;
    }
    FileInputStreamFactory isf = new FileInputStreamFactory();
    try (
        InputStream schemaStream = isf.createOptional(options.getSchema());
        InputStream specification = isf.create(options.getSpecification());
        Reader template = new FileReader(options.getTemplate());
        Writer result = new MemoryResultWriter()
    ) {
      SpecificationParser
          specificationParser = createParser(options, schemaStream);
      MustacheTemplateProcessor templateProcessor =
          new MustacheTemplateProcessor();
      new Generator(specificationParser, templateProcessor)
          .generate(specification, template, result);
    }
    catch (ParserException | IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private static @Nullable GenerateOptions createOptions(String[] args) {
    GeneratorOptionsFactory optionsFactory = new GeneratorOptionsFactory();
    try {
      return optionsFactory.create(args);
    }
    catch (NeedHelpException e) {
      optionsFactory.printHelp();
    }
    catch (WrongOptionsException e) {
      System.err.println(e.getMessage());
      optionsFactory.printHelp();
    }
    return null;
  }

  private static @NotNull SpecificationParser createParser(
      @NotNull GenerateOptions options, @Nullable InputStream schemaStream) {
    switch (options.getSpecificationFormat()) {
      case Json:
        return new JsonSpecificationParser(schemaStream);
      case Yaml:
        return new YamlSpecificationParser(schemaStream);
      default:
        throw new RuntimeException();
    }
  }
}
