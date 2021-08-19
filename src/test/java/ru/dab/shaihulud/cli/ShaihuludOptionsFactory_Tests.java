package ru.dab.shaihulud.cli;

import org.junit.jupiter.api.Test;
import ru.dab.shaihulud.ShaihuludOptions;
import ru.dab.shaihulud.specification.ParserType;

import static org.junit.jupiter.api.Assertions.*;

class ShaihuludOptionsFactory_Tests {
  @Test
  void testTrivial() {
    String[] args = new String[]{
        "-y", "spec.yaml",
        "-s", "schema.json",
        "-r", "templates",
        "-m", "main_template",
        "-out", "outDir"
    };
    try {
      ShaihuludOptions shaihuludOptions =
          new ProgramOptionsFactory().create(args);
      assertEquals("spec.yaml", shaihuludOptions.getSpecification());
      assertEquals(ParserType.Yaml, shaihuludOptions
          .getParserType());
      assertEquals("schema.json", shaihuludOptions.getSchema());
      assertEquals("templates", shaihuludOptions.getRoot());
      assertEquals("main_template", shaihuludOptions.getMain());
      assertEquals("outDir", shaihuludOptions.getOutDirectory());
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