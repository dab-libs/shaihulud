package ru.dab.shaihulud.specification;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;

public class YamlSpecificationParser implements SpecificationParser {
  private final @Nullable InputStream schemaStream;
  private @Nullable       JSONObject  schemaJson = null;

  public YamlSpecificationParser(@Nullable InputStream schemaStream) {
    this.schemaStream = schemaStream;
  }

  @Override
  public @NotNull Map<String, Object> parse(
      @NotNull InputStream specificationStream)
      throws ParserException {
    JSONObject specification = parseYaml(specificationStream);
    if (schemaStream != null) {
      validateAndSetDefaults(specification);
    }
    return specification.toMap();
  }

  private void validateAndSetDefaults(JSONObject specification)
      throws ParserException {
    if (schemaJson == null) {
      schemaJson = parseJson(schemaStream);
    }
    try {
      Schema schema = SchemaLoader
          .builder()
          .useDefaults(true)
          .schemaJson(schemaJson)
          .build()
          .load()
          .build();
      schema.validate(specification);
    }
    catch (RuntimeException e) {
      throw new ParserException(e.getMessage(), e);
    }
  }

  private JSONObject parseJson(InputStream inputStream) throws ParserException {
    try {
      if (inputStream != null) {
        return new JSONObject(new JSONTokener(inputStream));
      }
      else {
        return new JSONObject();
      }
    }
    catch (JSONException e) {
      throw new ParserException(e.getMessage(), e);
    }
  }

  private JSONObject parseYaml(InputStream inputStream) {
    Yaml yaml = new Yaml();
    Map<String, Object> objectMap = yaml.load(inputStream);
    return new JSONObject(objectMap);
  }

}