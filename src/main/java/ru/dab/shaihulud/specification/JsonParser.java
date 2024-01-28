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
  @Override
  public @NotNull Map<String, Object> parse(@NotNull Reader specificationReader,
                                            @NotNull Reader schemaReader)
      throws ParserException {
    JSONObject specification = parseJson(specificationReader);
    validateAndSetDefaults(specification, schemaReader);
    return specification.toMap();
  }

  @Override
  public @NotNull Map<String, Object> parse(@NotNull Reader specificationReader)
      throws ParserException {
    JSONObject specification = parseJson(specificationReader);
    return specification.toMap();
  }

  private void validateAndSetDefaults(JSONObject specification,
                                      @NotNull Reader schemaReader)
      throws ParserException {
    JSONObject schemaJson = parseJson(schemaReader);
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
