package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.specification.ParserException;
import ru.dab.shaihulud.specification.SpecificationParser;
import ru.dab.shaihulud.templating.TemplateProcessor;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;

public class Generate {
  public static void main(String[] args) {
    GenerateOptions options = createOptions(args);
    if (options == null) {
      return;
    }

    GenerateParametersFactory pf = new GenerateParametersFactory(options);
    try (
        InputStream schema = pf.createSchema();
        InputStream specification = pf.createSpecification();
        Reader template = pf.createTemplate();
        Writer result = pf.createResultWriter()
    ) {
      SpecificationParser parser = pf.createParser(schema);
      TemplateProcessor templateProcessor = pf.createTemplateProcessor();
      templateProcessor.process(parser.parse(specification), template, result);
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
}
