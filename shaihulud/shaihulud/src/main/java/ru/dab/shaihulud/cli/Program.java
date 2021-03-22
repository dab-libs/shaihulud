package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.generator.Generator;
import ru.dab.shaihulud.generator.ResultStore;
import ru.dab.shaihulud.generator.TemplateBundle;
import ru.dab.shaihulud.specification.ParserException;
import ru.dab.shaihulud.specification.SpecificationParser;

import java.io.IOException;
import java.io.InputStream;

public class Program {
  public static void main(String[] args) {
    ProgramOptions options = createOptions(args);
    if (options == null) {
      return;
    }

    try (
        InputStream schema = new SchemaFactory(options).create();
        InputStream specification = new SpecificationFactory(options).create();
        ResultStore resultStore = new FileResultStoreFactory(options).create()
    ) {
      SpecificationParser parser = new SpecificationParserFactory(options)
          .create(schema);
      TemplateBundle template = new TemplateBundleFactory(options).create();
      Generator generator = new GeneratorFactory().create();
      generator.generate(parser.parse(specification), template, resultStore);
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
