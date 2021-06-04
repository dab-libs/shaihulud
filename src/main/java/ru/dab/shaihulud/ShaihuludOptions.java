package ru.dab.shaihulud;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
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

  public ShaihuludOptions(
      @Nullable String schema, @NotNull ParserType parserType,
      @NotNull String specification, @Nullable String transformation,
      @NotNull String templateRoot, @NotNull String mainTemplate,
      @Nullable String outDirectory, @Nullable String config) {
    this.specification = specification;
    this.parserType = parserType;
    this.schema = schema;
    this.transformation = transformation;
    this.templateRoot = templateRoot;
    this.mainTemplate = mainTemplate;
    this.outDirectory = outDirectory;
    this.config = config;
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

  public @Nullable String getOutDirectory() {
    return outDirectory;
  }

  public @Nullable String getConfig() {
    return config;
  }

  public @Nullable String getTransformation() {
    return transformation;
  }
}
