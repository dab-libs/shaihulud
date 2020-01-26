package ru.dab.shaihulud.generator;

public class GeneratorOptions {
  private final String yamlTemplate;
  private final String jsonTemplate;
  private final String jsonSchema;
  private final String outDirectory;

  GeneratorOptions(String yamlTemplate, String jsonTemplate,
                   String jsonSchema, String outDirectory) {
    this.yamlTemplate = yamlTemplate;
    this.jsonTemplate = jsonTemplate;
    this.jsonSchema = jsonSchema;
    this.outDirectory = outDirectory;
  }

  public String getYamlTemplate() {
    return yamlTemplate;
  }

  public String getJsonTemplate() {
    return jsonTemplate;
  }

  public String getJsonSchema() {
    return jsonSchema;
  }

  public String getOutDirectory() {
    return outDirectory;
  }
}
