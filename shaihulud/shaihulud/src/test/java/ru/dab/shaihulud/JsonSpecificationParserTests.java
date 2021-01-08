package ru.dab.shaihulud;

import org.junit.jupiter.api.Test;
import ru.dab.shaihulud.specification.JsonSpecificationParser;
import ru.dab.shaihulud.specification.ParserException;
import ru.dab.shaihulud.specification.Parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JsonSpecificationParserTests {
  @Test
  public void testTrivial() throws IOException, ParserException {
    try (InputStream schema =
             getClass().getResourceAsStream("/swagger-2.0-schema.json");
         InputStream specification =
             getClass().getResourceAsStream("/swagger-default.json")) {
      Parser specificationParser = new JsonSpecificationParser();
      Map<String, Object> parsedSpecification = specificationParser.parse(specification, schema);
      assertEquals("2.0", parsedSpecification.get("swagger"));
    }
  }
}
