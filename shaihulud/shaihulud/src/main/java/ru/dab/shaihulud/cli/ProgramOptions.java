package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.generator.GeneratorOptions;
import ru.dab.shaihulud.output.OutputOptions;
import ru.dab.shaihulud.specification.SchemaOptions;
import ru.dab.shaihulud.specification.SpecificationParserOptions;
import ru.dab.shaihulud.specification.SpecificationParserType;
import ru.dab.shaihulud.specification.SpecificationOptions;
import ru.dab.shaihulud.generator.TemplateOptions;

class ProgramOptions
    implements SchemaOptions, SpecificationParserOptions, SpecificationOptions,
               TemplateOptions, OutputOptions, GeneratorOptions {
  private final @NotNull  String                  specification;
  private final @NotNull  SpecificationParserType specificationParserType;
  private final @Nullable String                  schema;
  private final @NotNull  String                  template;
  private final @Nullable String                  outDirectory;

  ProgramOptions(
      @NotNull String specification,
      @NotNull SpecificationParserType specificationParserType,
      @Nullable String schema, @NotNull String template,
      @Nullable String outDirectory) {
    this.specification = specification;
    this.specificationParserType = specificationParserType;
    this.schema = schema;
    this.template = template;
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
  public @NotNull String getTemplate() {
    return template;
  }

  @Override
  public @Nullable String getOutDirectory() {
    return outDirectory;
  }
}
