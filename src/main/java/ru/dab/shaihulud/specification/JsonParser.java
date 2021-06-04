package ru.dab.shaihulud.specification;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.Reader;
import java.util.Map;

public class JsonParser implements Parser {
  private final @Nullable Reader     schemaReader;
  private @Nullable       JSONObject schemaJson = null;

  public JsonParser(@Nullable Reader schemaReader) {
    this.schemaReader = schemaReader;
  }

  @Override
  public @NotNull Map<String, Object> parse(@NotNull Reader specificationReader)
      throws ParserException {
    JSONObject specification = parseJson(specificationReader);
    if (schemaReader != null) {
      validateAndSetDefaults(specification);
    }
    return specification.toMap();
  }

  private void validateAndSetDefaults(JSONObject specification)
      throws ParserException {
    if (schemaJson == null && schemaReader != null) {
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

  private JSONObject parseJson(@NotNull Reader reader) throws ParserException {
    try {
      return new JSONObject(new JSONTokener(reader));
    }
    catch (JSONException e) {
      throw new ParserException(e.getMessage(), e);
    }
  }

}
