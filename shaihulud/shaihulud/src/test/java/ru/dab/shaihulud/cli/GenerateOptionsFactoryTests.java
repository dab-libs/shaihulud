package ru.dab.shaihulud.cli;

import org.junit.jupiter.api.Test;
import ru.dab.shaihulud.specification.SpecificationFormat;

import static org.junit.jupiter.api.Assertions.*;

class GenerateOptionsFactoryTests {
  @Test
  void testTrivial() {
    String[] args = new String[]{
        "-yaml", "spec.yaml",
        "-sch", "schema.json",
        "-t", "template.mustache",
        "-out", "outDir"
    };
    try {
      GenerateOptions generateOptions =
          new GeneratorOptionsFactory().create(args);
      assertEquals("spec.yaml", generateOptions.getSpecification());
      assertEquals(SpecificationFormat.Yaml, generateOptions
          .getSpecificationFormat());
      assertEquals("schema.json", generateOptions.getSchema());
      assertEquals("template.mustache", generateOptions.getTemplate());
      assertEquals("outDir", generateOptions.getOutDirectory());
    }
    catch (WrongOptionsException | NeedHelpException e) {
      fail(e.getMessage());
    }
  }

  @Test
  void testNeedHelp() {
    String[] args = new String[0];
    try {
      new GeneratorOptionsFactory().create(args);
      fail();
    }
    catch (WrongOptionsException e) {
      fail(e.getMessage());
    }
    catch (NeedHelpException e) {
    }
  }

  @Test
  void testYamlJsonMutuallyExclusive() {
    String[] args = new String[]{
        "-yaml", "spec.yaml",
        "-json", "spec.json",
        "-t", "template.mustache"
    };
    parseWrongArguments(args);
  }

  @Test
  void testYamlJsonRequired() {
    String[] args = new String[]{
        "-t", "template.mustache"
    };
    parseWrongArguments(args);
  }

  @Test
  void testTemplateRequired() {
    String[] args = new String[]{
        "-yaml", "spec.yaml",
        };
    parseWrongArguments(args);
  }

  private void parseWrongArguments(String[] args) {
    try {
      new GeneratorOptionsFactory().create(args);
      fail();
    }
    catch (WrongOptionsException e) {
    }
    catch (NeedHelpException e) {
      fail(e.getMessage());
    }
  }
}