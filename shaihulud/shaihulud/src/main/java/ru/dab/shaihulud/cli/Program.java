package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.config.ConfigException;
import ru.dab.shaihulud.config.ConfigParserFactory;
import ru.dab.shaihulud.generator.*;
import ru.dab.shaihulud.io.ResultStoreFactory;
import ru.dab.shaihulud.io.ReaderFactory;
import ru.dab.shaihulud.io.TemplateBundleFactory;
import ru.dab.shaihulud.specification.SpecificationParser;
import ru.dab.shaihulud.specification.SpecificationParserFactory;

import java.io.Reader;
import java.util.Map;

public class Program {
  public static void main(String[] args) {
    ProgramOptions options = createOptions(args);
    if (options == null) {
      return;
    }

    ReaderFactory readerFactory = new ReaderFactory();
    ResultStoreFactory resultStoreFactory = new ResultStoreFactory();
    SpecificationParserFactory specificationParserFactory =
        new SpecificationParserFactory();
    try (
        Reader schemaReader =
            readerFactory.createOptional(options.getSchema());
        Reader specificationReader =
            readerFactory.create(options.getSpecificationPath());
        ResultStore resultStore =
            resultStoreFactory.create(options.getOutDirectory());
        Reader configReader =
            readerFactory.createOptional(options.getConfig())
    ) {
      SpecificationParser specificationParser = specificationParserFactory
          .create(options.getSpecificationParserType(), schemaReader);
      Map<String, Object> specification =
          specificationParser.parse(specificationReader);
      final TemplateBundleFactory templateBundleFactory =
          new TemplateBundleFactory();
      TemplateBundle template = templateBundleFactory
          .create(options.getRoot(), options.getMain());
      Generator generator = new GeneratorFactory().create();
      Map<String, Object> config = readConfig(configReader);
      generator.generate(
          specification, template, resultStore, config);
    }
    catch (Throwable e) {
      System.err.println(e.getMessage());
    }
  }

  @Nullable
  private static Map<String, Object> readConfig(Reader configReader)
      throws ConfigException {
    Map<String, Object> config = null;
    if (configReader != null) {
      config = new ConfigParserFactory().create().parse(configReader);
    }
    return config;
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
