package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.generator.Generator;
import ru.dab.shaihulud.generator.GeneratorFactory;
import ru.dab.shaihulud.generator.TemplateFactory;
import ru.dab.shaihulud.output.OutputFactory;
import ru.dab.shaihulud.specification.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;

public class Program {
  public static void main(String[] args) {
    ProgramOptions options = createOptions(args);
    if (options == null) {
      return;
    }

    try (
        InputStream schema = new SchemaFactory(options).create();
        InputStream specification = new SpecificationFactory(options).create();
        Reader template = new TemplateFactory(options).create();
        Writer result = new OutputFactory(options).create()
    ) {
      SpecificationParser parser = new SpecificationParserFactory(options)
          .create(schema);
      Generator generator = new GeneratorFactory(options).create();

      generator.generate(parser.parse(specification), template, result);
    }
    catch (ParserException | IOException e) {
      System.err.println(e.getMessage());
    }
  }

  private static @Nullable ProgramOptions createOptions(String[] args) {
    ProgramOptionsFactory optionsFactory = new ProgramOptionsFactory();
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
}
