package ru.dab.shaihulud.cli;

import org.junit.jupiter.api.Test;
import ru.dab.shaihulud.specification.SpecificationParserType;

import static org.junit.jupiter.api.Assertions.*;

class ProgramOptionsFactory_Tests {
  @Test
  void testTrivial() {
    String[] args = new String[]{
        "-y", "spec.yaml",
        "-s", "schema.json",
        "-r", "templates",
        "-t", "template",
        "-out", "outDir"
    };
    try {
      ProgramOptions programOptions =
          new ProgramOptionsFactory().create(args);
      assertEquals("spec.yaml", programOptions.getSpecificationPath());
      assertEquals(SpecificationParserType.Yaml, programOptions
          .getSpecificationParserType());
      assertEquals("schema.json", programOptions.getSchema());
      assertEquals("templates", programOptions.getRoot());
      assertEquals("template", programOptions.getMain());
      assertEquals("outDir", programOptions.getOutDirectory());
    }
    catch (WrongOptionsException | NeedHelpException e) {
      fail(e.getMessage(), e);
    }
  }

  @Test
  void testNeedHelp() {
    String[] args = new String[0];
    try {
      new ProgramOptionsFactory().create(args);
      fail();
    }
    catch (WrongOptionsException e) {
      fail(e.getMessage(), e);
    }
    catch (NeedHelpException e) {
      assertTrue(true);
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

  // TODO: Сделать проверку обязательных параметров
  @Test
  void testYamlJsonRequired() {
    String[] args = new String[]{
        "-t", "template"
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
      new ProgramOptionsFactory().create(args);
      fail();
    }
    catch (WrongOptionsException e) {
    }
    catch (NeedHelpException e) {
      fail(e.getMessage());
    }
  }
}