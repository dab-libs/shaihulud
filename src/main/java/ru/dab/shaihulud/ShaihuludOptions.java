package ru.dab.shaihulud;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.generator.MainTemplateKind;
import ru.dab.shaihulud.specification.ParserType;

public class ShaihuludOptions {
  private final @Nullable String     schema;
  private final @NotNull  ParserType parserType;
  private final @NotNull  String     specification;
  private final @Nullable String     transformation;
  private final @NotNull  String     templateRoot;
  private final @NotNull  String     mainTemplate;
  private final @Nullable String     outDirectory;
  private final @Nullable String     config;
  private final           boolean    quiet;

  public ShaihuludOptions(
      @Nullable String schema, @NotNull ParserType parserType,
      @NotNull String specification, @Nullable String transformation,
      @NotNull String templateRoot, @NotNull String mainTemplate,
      @Nullable String outDirectory, @Nullable String config, boolean quiet) {
    this.specification = specification;
    this.parserType = parserType;
    this.schema = schema;
    this.transformation = transformation;
    this.templateRoot = templateRoot;
    this.mainTemplate = mainTemplate;
    this.outDirectory = outDirectory;
    this.config = config;
    this.quiet = quiet;
  }

  public @NotNull String getSpecification() {
    return specification;
  }

  public @NotNull ParserType getParserType() {
    return parserType;
  }

  public @Nullable String getSchema() {
    return schema;
  }

  public @NotNull String getRoot() {
    return templateRoot;
  }

  public @NotNull String getMain() {
    return mainTemplate;
  }

  public MainTemplateKind getMainKind() {
    switch (getExtension(getMain()).toLowerCase()) {
      case "twig":
        return MainTemplateKind.Twig;
      case "mustache":
        return MainTemplateKind.Mustache;
      default:
        return MainTemplateKind.Unknown;
    }
  }

  public @Nullable String getOutDirectory() {
    return outDirectory;
  }

  public @Nullable String getConfig() {
    return config;
  }

  public @Nullable String getTransformation() {
    return transformation;
  }

  public boolean isQuiet() {
    return quiet;
  }

  private String getExtension(String fileName) {
    int i = fileName.lastIndexOf('.');
    if (i < 0) {
      return "";
    }
    return fileName.substring(i+1);
  }

}
