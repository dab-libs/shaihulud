package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.specification.SpecificationParserType;

class ProgramOptions
    implements SchemaOptions, SpecificationParserOptions, SpecificationOptions,
               TemplateBundleOptions, FileResultStoreOptions {
  private final @NotNull  String                  specification;
  private final @NotNull  SpecificationParserType specificationParserType;
  private final @Nullable String                  schema;
  private final @NotNull  String                  templateRoot;
  private final @NotNull  String                  mainTemplate;
  private final @Nullable String                  outDirectory;

  ProgramOptions(
      @NotNull String specification,
      @NotNull SpecificationParserType specificationParserType,
      @Nullable String schema,
      @NotNull String templateRoot,
      @NotNull String mainTemplate,
      @Nullable String outDirectory) {
    this.specification = specification;
    this.specificationParserType = specificationParserType;
    this.schema = schema;
    this.templateRoot = templateRoot;
    this.mainTemplate = mainTemplate;
    this.outDirectory = outDirectory;
  }

  @Override
  public @NotNull String getSpecification() {
    return specification;
  }

  @Override
  public @NotNull SpecificationParserType getSpecificationParserType() {
    return specificationParserType;
  }

  @Override
  public @Nullable String getSchema() {
    return schema;
  }

  @Override
  public @NotNull String getRoot() {
    return templateRoot;
  }

  @Override
  public @NotNull String getMain() {
    return mainTemplate;
  }

  @Override
  public @Nullable String getOutDirectory() {
    return outDirectory;
  }
}
