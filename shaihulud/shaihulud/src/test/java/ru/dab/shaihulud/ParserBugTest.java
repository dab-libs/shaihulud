package ru.dab.shaihulud;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;
import ru.dab.shaihulud.specification.JsonSpecificationParser;
import ru.dab.shaihulud.specification.ParserException;
import ru.dab.shaihulud.specification.SpecificationParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserBugTest {
  @Test
  public void testTrivial() throws IOException {
    try (InputStream schemaStream =
             getClass().getResourceAsStream("/bug-schema.json");
         InputStream specificationStream =
             getClass().getResourceAsStream("/bug.json")) {
      JSONObject jsonSchema = new JSONObject(new JSONTokener(schemaStream));
      JSONObject jsonSpecification =
          new JSONObject(new JSONTokener(specificationStream));
      Schema schema = SchemaLoader
          .builder()
          .useDefaults(true)
          .schemaJson(jsonSchema)
          .build()
          .load()
          .build();
      schema.validate(jsonSpecification);
    }
  }
}
