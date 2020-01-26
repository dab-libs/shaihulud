package ru.dab.shaihulud.generator;

public class GeneratorOptions {
  private final String yamlSpecification;
  private final String jsonSpecification;
  private final String schema;
  private final String template;
  private final String outDirectory;

  GeneratorOptions(String yamlSpecification, String jsonSpecification,
                   String schema, String template,
                   String outDirectory) {
    this.yamlSpecification = yamlSpecification;
    this.jsonSpecification = jsonSpecification;
    this.schema = schema;
    this.template = template;
    this.outDirectory = outDirectory;
  }

  public String getYamlSpecification() {
    return yamlSpecification;
  }

  public String getJsonSpecification() {
    return jsonSpecification;
  }

  public String getSchema() {
    return schema;
  }

  public String getTemplate() {
    return template;
  }

  public String getOutDirectory() {
    return outDirectory;
  }
}
