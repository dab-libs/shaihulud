package ru.dab.shaihulud;

import org.everit.json.schema.EnumSchema;
import org.everit.json.schema.Schema;
import org.everit.json.schema.Validator;
import org.everit.json.schema.event.*;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

class EnumTests {
  @Test
  void testEnums() throws IOException {
    try (InputStream schemaInputStream =
             getClass().getResourceAsStream("/enums-schema.json");
         InputStream jsonInputStream =
             getClass().getResourceAsStream("/enums.json")) {
      JSONObject rawSchema = new JSONObject(new JSONTokener(schemaInputStream));
      Schema schema = SchemaLoader
          .builder()
          .useDefaults(true)
          .schemaJson(rawSchema)
          .build()
          .load()
          .build();
      JSONObject json = new JSONObject(new JSONTokener(jsonInputStream));
      Set<Integer> enumInstances = new HashSet<>();
      Validator
          .builder()
          .withListener(new Listener(enumInstances))
          .build().performValidation(schema, json);
      json.
    }
  }

  private Map<String, Object> mapJsonObjectToData(JSONObject object) {
    Map<String, Object> data = new HashMap<>();
    for (Map.Entry<String, Object> entry : object.toMap().entrySet()) {
      if (object.g)
    }
    return Collections.unmodifiableMap(data);
  }

  private List<Object> mapJsonArrayToData(JSONArray json) {
    Map<String, Object> data = new HashMap<>();
    for (Map.Entry<String, Object> entry : json.toMap().entrySet()) {
      if (json.g)
    }
    return Collections.unmodifiableMap(data);
  }

  private static class Listener implements ValidationListener {
    private final Set<Integer> enumInstances;

    private Listener(Set<Integer> enumInstances) {
      this.enumInstances = enumInstances;
    }

    public void combinedSchemaMatch(CombinedSchemaMatchEvent event) {
      if (event.getSubSchema() instanceof EnumSchema) {
        Object instance = event.toJSON(false, true).get("instance");
        int h = instance.hashCode();
        enumInstances.add(System.identityHashCode(instance));
        System.out.println("combinedSchemaMatch");
      }
    }

    public void combinedSchemaMismatch(CombinedSchemaMismatchEvent event) {
      System.out.println("combinedSchemaMismatch");
    }

    public void schemaReferenced(SchemaReferencedEvent event) {
      System.out.println("schemaReferenced");
    }

    public void ifSchemaMatch(ConditionalSchemaMatchEvent event) {
      System.out.println("ifSchemaMatch");
    }

    public void ifSchemaMismatch(ConditionalSchemaMismatchEvent event) {
      System.out.println("ifSchemaMismatch");
    }

    public void thenSchemaMatch(ConditionalSchemaMatchEvent event) {
      System.out.println("thenSchemaMatch");
    }

    public void thenSchemaMismatch(ConditionalSchemaMismatchEvent event) {
      System.out.println("thenSchemaMismatch");
    }

    public void elseSchemaMatch(ConditionalSchemaMatchEvent event) {
      System.out.println("elseSchemaMatch");
    }

    public void elseSchemaMismatch(ConditionalSchemaMismatchEvent event) {
      System.out.println("elseSchemaMismatch");
    }
  }
}