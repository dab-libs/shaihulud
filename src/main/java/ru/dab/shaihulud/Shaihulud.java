package ru.dab.shaihulud;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.config.ConfigException;
import ru.dab.shaihulud.config.ConfigParserFactory;
import ru.dab.shaihulud.generator.Generator;
import ru.dab.shaihulud.io.ReaderFactory;
import ru.dab.shaihulud.specification.Parser;
import ru.dab.shaihulud.specification.ParserException;
import ru.dab.shaihulud.transfomer.Transformer;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

public class Shaihulud {
  private final @NotNull ShaihuludOptions options;
  private final @NotNull ReaderFactory    readerFactory;
  private final @NotNull Generator        generator;
  private final @NotNull Parser           parser;
  private final @NotNull Transformer      transformer;

  public Shaihulud(
      @NotNull ShaihuludOptions options,
      @NotNull ReaderFactory readerFactory,
      @NotNull Generator generator,
      @NotNull Parser parser,
      @NotNull Transformer transformer) {
    this.options = options;
    this.readerFactory = readerFactory;
    this.generator = generator;
    this.parser = parser;
    this.transformer = transformer;
  }

  @SuppressWarnings("unchecked")
  public void transform() throws IOException, ParserException, ConfigException {
    try (
        Reader schemaReader =
            createSchemaReader(options.getSchema());
        Reader specificationReader =
            readerFactory.create(options.getSpecification());
        Reader transformationReader =
            readerFactory.createOptional(options.getTransformation());
        Reader configReader =
            readerFactory.createOptional(options.getConfig())
    ) {
      Map<String, Object> specification =
          parser.parse(specificationReader, schemaReader);

      if (transformationReader != null) {
        specification = (Map<String, Object>) transformer
            .transform(transformationReader, specification);
      }

      Map<String, Object> config = readConfig(configReader);

      generator.generate(specification, config);
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
