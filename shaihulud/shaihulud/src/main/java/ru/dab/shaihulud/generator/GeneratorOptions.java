package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.specification.SpecificationFormat;

class GeneratorOptions {
  private final @NotNull  String              specification;
  private final @NotNull  SpecificationFormat specificationFormat;
  private final @Nullable String              schema;
  private final @NotNull  String              template;
  private final @Nullable String              outDirectory;

  GeneratorOptions(
      @NotNull String specification,
      @NotNull SpecificationFormat specificationFormat,
      @Nullable String schema, @NotNull String template,
      @Nullable String outDirectory) {
    this.specification = specification;
    this.specificationFormat = specificationFormat;
    this.schema = schema;
    this.template = template;
    this.outDirectory = outDirectory;
  }

  public @NotNull String getSpecification() {
    return specification;
  }

  public @NotNull SpecificationFormat getSpecificationFormat() {
    return specificationFormat;
  }

  public @Nullable String getSchema() {
    return schema;
  }

  public @NotNull String getTemplate() {
    return template;
  }

  public @Nullable String getOutDirectory() {
    return outDirectory;
  }
}
