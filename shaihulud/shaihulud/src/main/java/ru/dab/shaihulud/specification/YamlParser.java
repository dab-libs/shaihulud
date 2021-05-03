package ru.dab.shaihulud.specification;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.yaml.snakeyaml.Yaml;

import java.io.Reader;
import java.util.Map;

public class YamlParser implements Parser {
  private final @Nullable Reader     schemaReader;
  private @Nullable       JSONObject schemaJson = null;

  public YamlParser(@Nullable Reader schemaReader) {
    this.schemaReader = schemaReader;
  }

  @Override
  public @NotNull Map<String, Object> parse(
      @NotNull Reader specificationStream)
      throws ParserException {
    JSONObject specification = parseYaml(specificationStream);
    if (schemaReader != null) {
      validateAndSetDefaults(specification);
    }
    return specification.toMap();
  }

  private void validateAndSetDefaults(JSONObject specification)
      throws ParserException {
    if (schemaJson == null) {
      schemaJson = parseJson(schemaReader);
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

  private JSONObject parseJson(Reader reader) throws ParserException {
    try {
      if (reader != null) {
        return new JSONObject(new JSONTokener(reader));
      }
      else {
        return new JSONObject();
      }
    }
    catch (JSONException e) {
      throw new ParserException(e.getMessage(), e);
    }
  }

  private JSONObject parseYaml(Reader inputStream) {
    Yaml yaml = new Yaml();
    Map<String, Object> objectMap = yaml.load(inputStream);
    return new JSONObject(objectMap);
  }

}
