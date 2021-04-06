package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.specification.SpecificationParserType;

class ProgramOptions {
  private final @NotNull  String                  specification;
  private final @NotNull  SpecificationParserType specificationParserType;
  private final @Nullable String                  schema;
  private final @NotNull  String                  templateRoot;
  private final @NotNull  String                  mainTemplate;
  private final @Nullable String                  outDirectory;
  private final @Nullable String                  config;

  ProgramOptions(
      @NotNull String specification,
      @NotNull SpecificationParserType specificationParserType,
      @Nullable String schema,
      @NotNull String templateRoot,
      @NotNull String mainTemplate,
      @Nullable String outDirectory,
      @Nullable String config) {
    this.specification = specification;
    this.specificationParserType = specificationParserType;
    this.schema = schema;
    this.templateRoot = templateRoot;
    this.mainTemplate = mainTemplate;
    this.outDirectory = outDirectory;
    this.config = config;
  }

  public @NotNull String getSpecificationPath() {
    return specification;
  }

  public @NotNull SpecificationParserType getSpecificationParserType() {
    return specificationParserType;
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
}
