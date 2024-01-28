package ru.dab.shaihulud.cli;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.Console;
import ru.dab.shaihulud.Shaihulud;
import ru.dab.shaihulud.ShaihuludOptions;
import ru.dab.shaihulud.generator.Generator;
import ru.dab.shaihulud.generator.ResultStore;
import ru.dab.shaihulud.generator.TemplateBundle;
import ru.dab.shaihulud.generator.io.FileResultStore;
import ru.dab.shaihulud.generator.io.FileTemplateBundle;
import ru.dab.shaihulud.generator.io.ResourceTemplateBundle;
import ru.dab.shaihulud.generator.mustache.MustacheGenerator;
import ru.dab.shaihulud.generator.pebble.PebbleGenerator;
import ru.dab.shaihulud.io.ReaderFactory;
import ru.dab.shaihulud.specification.JsonParser;
import ru.dab.shaihulud.specification.Parser;
import ru.dab.shaihulud.specification.YamlParser;
import ru.dab.shaihulud.transfomer.Transformer;
import ru.dab.shaihulud.transfomer.jmespath.JmesPathTransformer;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

public class BingingModule extends AbstractModule {
  private final @NotNull ShaihuludOptions options;

  public BingingModule(@NotNull ShaihuludOptions options) {
    this.options = options;
  }

  protected void configure() {
    bind(ShaihuludOptions.class).toInstance(options);
    bind(ReaderFactory.class);
    bindParser();
    bind(Console.class);
  }

  private void bindParser() {
    switch (options.getParserType()) {
      case Yaml:
        bind(Parser.class).to(YamlParser.class);
        break;
      case Json:
        bind(Parser.class).to(JsonParser.class);
        break;
      default:
        throw new RuntimeException("Unknown parse type");
    }
  }

  @Provides
  private Generator provideGenerator(@NotNull TemplateBundle templateBundle,
                                     @NotNull ResultStore resultStore) {
    switch (options.getMainKind()) {
      case Twig:
        return new PebbleGenerator(templateBundle, resultStore);
      case Mustache:
        return new MustacheGenerator(templateBundle, resultStore);
      default:
        throw new RuntimeException("Unknown template kind");
    }
  }

  @Provides
  protected Transformer provideTransformer(Console console) {
    return new JmesPathTransformer(console);
  }

  @Provides
  protected Shaihulud provideShaihulud(
      @NotNull ReaderFactory readerFactory,
      @NotNull Generator generator,
      @NotNull Parser parser,
      @NotNull Transformer transformer) {
    return new Shaihulud(options, readerFactory, generator, parser,
                         transformer);
  }

  @Provides
  protected ResultStore provodeResultStore() {
    return new FileResultStore(new File(options.getOutDirectory()));
  }

  @Provides
  protected TemplateBundle provideTemplateBundle() {
    String root = options.getRoot();
    String main = options.getMain();
    TemplateBundle fileBundle = new FileTemplateBundle(root, main);
    try {
      this.checkTemplateBundle(fileBundle);
      return fileBundle;
    }
    catch (IOException e1) {
      if (root.startsWith("/") || root.startsWith("\\")) {
        throw new RuntimeException("Invalid template root '" + root + "'");
      }

      TemplateBundle resourceBundle = new ResourceTemplateBundle(root, main);
      try {
        checkTemplateBundle(resourceBundle);
        return resourceBundle;
      }
      catch (IOException e2) {
        throw new RuntimeException(e2);
      }
    }
  }

  private void checkTemplateBundle(
      @NotNull TemplateBundle templateBundle) throws IOException {
    //noinspection EmptyTryBlock
    try (Reader reader = templateBundle.getTemplate(templateBundle.getMain())) {
    }
  }
}
