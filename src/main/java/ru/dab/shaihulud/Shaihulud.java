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
import ru.dab.shaihulud.specification.ParserException;
import ru.dab.shaihulud.specification.ParserFactory;
import ru.dab.shaihulud.transfomer.TransformerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class Shaihulud {
  private final @NotNull GeneratorFactory   generatorFactory;
  private final @NotNull ReaderFactory      readerFactory;
  private final @NotNull ParserFactory      parserFactory;
  private final @NotNull ResultStoreFactory resultStoreFactory;
  private final @NotNull TransformerFactory transformerFactory;

  public Shaihulud(
      @NotNull GeneratorFactory generatorFactory,
      @NotNull ReaderFactory readerFactory,
      @NotNull ParserFactory parserFactory,
      @NotNull ResultStoreFactory resultStoreFactory) {
    this.generatorFactory = generatorFactory;
    this.readerFactory = readerFactory;
    this.parserFactory = parserFactory;
    this.resultStoreFactory = resultStoreFactory;
    transformerFactory = new TransformerFactory();
  }

  @SuppressWarnings("unchecked")
  public void transform(ShaihuludOptions options)
      throws IOException, ParserException, ConfigException {
    try (
        Reader schemaReader =
            createSchemaReader(options.getSchema());
        Reader specificationReader =
            readerFactory.create(options.getSpecification());
        Reader transformationReader =
            readerFactory.createOptional(options.getTransformation());
        ResultStore resultStore =
            resultStoreFactory.create(options.getOutDirectory());
        Reader configReader =
            readerFactory.createOptional(options.getConfig())
    ) {
      Console console = new Console();
      Map<String, Object> specification = parserFactory
          .create(options.getParserType(), schemaReader)
          .parse(specificationReader);

      if (transformationReader != null) {
        specification = (Map<String, Object>) transformerFactory
            .create(console)
            .transform(transformationReader, specification);
      }

      TemplateBundle template = new TemplateBundleFactory()
          .create(options.getRoot(), options.getMain());

      Map<String, Object> config = readConfig(configReader);

      Generator generator = generatorFactory.create();
      generator.generate(specification, template, resultStore, config);
    }
  }

  @Nullable
  private Reader createSchemaReader(@Nullable String schemaPath)
      throws IOException {
    if (schemaPath != null) {
      return readerFactory.createOptional(schemaPath);
    }
    else {
      try {
        return readerFactory.createOptional("schema.json");
      }
      catch (FileNotFoundException e) {
        return null;
      }
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
