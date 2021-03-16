package ru.dab.shaihulud.specification;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;
import java.util.Map;

public class JsonParser implements Parser {
  @Override
  public Map<String, Object> parse(
      @NotNull InputStream specificationStream,
      @Nullable InputStream schemaStream)
      throws ParserException {
    JSONObject jsonSpecification = parseJson(specificationStream);
    JSONObject jsonSchema = parseJson(schemaStream);
    try {
      Schema schema = SchemaLoader
          .builder()
          .useDefaults(true)
          .schemaJson(jsonSchema)
          .build()
          .load()
          .build();
      schema.validate(jsonSpecification);
    }
    catch (RuntimeException e) {
      throw new ParserException(e.getMessage(), e, specificationStream);
    }
    return null;
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
      throw new ParserException(e.getMessage(), e, inputStream);
    }
  }

}
