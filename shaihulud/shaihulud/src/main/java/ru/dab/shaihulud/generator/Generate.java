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
      Parser parser = createParser(options, schemaStream);
      MustacheTemplateProcessor templateProcessor =
          new MustacheTemplateProcessor();
      new Generator(parser, templateProcessor)
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

  private static @NotNull Parser createParser(
      @NotNull GenerateOptions options, @Nullable InputStream schemaStream) {
    switch (options.getSpecificationFormat()) {
      case Json:
        return new JsonParser(schemaStream);
      case Yaml:
      default:
        throw new RuntimeException();
    }
  }
}
