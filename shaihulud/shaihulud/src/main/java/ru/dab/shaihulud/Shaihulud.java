package ru.dab.shaihulud;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.config.ConfigException;
import ru.dab.shaihulud.config.ConfigParserFactory;
import ru.dab.shaihulud.generator.Generator;
import ru.dab.shaihulud.generator.GeneratorFactory;
import ru.dab.shaihulud.generator.ResultStore;
import ru.dab.shaihulud.generator.TemplateBundle;
import ru.dab.shaihulud.io.ReaderFactory;
import ru.dab.shaihulud.io.ResultStoreFactory;
import ru.dab.shaihulud.io.TemplateBundleFactory;
import ru.dab.shaihulud.specification.Parser;
import ru.dab.shaihulud.specification.ParserException;
import ru.dab.shaihulud.specification.ParserFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class Shaihulud {
  private final @NotNull GeneratorFactory generatorFactory;
  private final @NotNull ReaderFactory readerFactory;
  private final @NotNull ParserFactory parserFactory;
  private final @NotNull ResultStoreFactory resultStoreFactory;

  public Shaihulud(
      @NotNull GeneratorFactory generatorFactory,
      @NotNull ReaderFactory readerFactory,
      @NotNull ParserFactory parserFactory,
      @NotNull ResultStoreFactory resultStoreFactory) throws IOException {
    this.generatorFactory = generatorFactory;
    this.readerFactory = readerFactory;
    this.parserFactory = parserFactory;
    this.resultStoreFactory = resultStoreFactory;
  }

  public void transform(ShaihuludOptions options)
      throws IOException, ParserException, ConfigException {
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
      Parser parser = parserFactory
          .create(options.getSpecificationParserType(), schemaReader);
      Map<String, Object> specification = parser.parse(specificationReader);
      final TemplateBundleFactory templateBundleFactory =
          new TemplateBundleFactory();
      TemplateBundle template = templateBundleFactory
          .create(options.getRoot(), options.getMain());
      Generator generator = new GeneratorFactory().create();
      Map<String, Object> config = readConfig(configReader);
      generator.generate(specification, template, resultStore, config);
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
}
