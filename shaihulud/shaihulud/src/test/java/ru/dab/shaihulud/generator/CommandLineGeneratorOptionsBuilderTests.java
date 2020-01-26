package ru.dab.shaihulud.generator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandLineGeneratorOptionsBuilderTests {
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
          CommandLineGeneratorOptionsBuilder.build(args);
      assertEquals("spec.yaml", generatorOptions.getYamlSpecification());
      assertEquals("schema.json", generatorOptions.getSchema());
      assertEquals("template.mustache", generatorOptions.getTemplate());
      assertEquals("outDir", generatorOptions.getOutDirectory());
    }
    catch (CommandLineGeneratorOptionsBuilder.WrongOptionsException e) {
      fail(e.getMessage());
    }
    catch (CommandLineGeneratorOptionsBuilder.NeedHelpException e) {
      fail(e.getMessage());
    }
  }

  @Test
  void testNeedHelp() {
    String[] args = new String[0];
    try {
      GeneratorOptions generatorOptions =
          CommandLineGeneratorOptionsBuilder.build(args);
      fail();
    }
    catch (CommandLineGeneratorOptionsBuilder.WrongOptionsException e) {
      fail(e.getMessage());
    }
    catch (CommandLineGeneratorOptionsBuilder.NeedHelpException e) {
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
          CommandLineGeneratorOptionsBuilder.build(args);
      fail();
    }
    catch (CommandLineGeneratorOptionsBuilder.WrongOptionsException e) {
    }
    catch (CommandLineGeneratorOptionsBuilder.NeedHelpException e) {
      fail(e.getMessage());
    }
  }
}