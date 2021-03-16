package ru.dab.shaihulud;

import org.junit.jupiter.api.Test;
import ru.dab.shaihulud.generator.GeneratorOptionsFactory;
import ru.dab.shaihulud.generator.GeneratorOptions;
import ru.dab.shaihulud.generator.NeedHelpException;
import ru.dab.shaihulud.generator.WrongOptionsException;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorOptionsFactoryTests {
  @Test
  void testTrivial() {
    String[] args = new String[]{
        "-yaml", "spec.yaml",
        "-sch", "schema.json",
        "-t", "template.mustache",
        "-out", "outDir"
    };
    try {
      GeneratorOptions generatorOptions =
          GeneratorOptionsFactory.build(args);
      assertEquals("spec.yaml", generatorOptions.getYamlSpecification());
      assertEquals("schema.json", generatorOptions.getSchema());
      assertEquals("template.mustache", generatorOptions.getTemplate());
      assertEquals("outDir", generatorOptions.getOutDirectory());
    }
    catch (WrongOptionsException e) {
      fail(e.getMessage());
    }
    catch (NeedHelpException e) {
      fail(e.getMessage());
    }
  }

  @Test
  void testNeedHelp() {
    String[] args = new String[0];
    try {
      GeneratorOptions generatorOptions =
          GeneratorOptionsFactory.build(args);
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
      GeneratorOptions generatorOptions =
          GeneratorOptionsFactory.build(args);
      fail();
    }
    catch (WrongOptionsException e) {
    }
    catch (NeedHelpException e) {
      fail(e.getMessage());
    }
  }
}