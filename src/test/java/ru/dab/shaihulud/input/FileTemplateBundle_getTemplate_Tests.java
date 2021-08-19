package ru.dab.shaihulud.input;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import ru.dab.shaihulud.generator.TemplateBundle;
import ru.dab.shaihulud.generator.io.TemplateBundleFactory;

import java.io.IOException;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.*;

public class FileTemplateBundle_getTemplate_Tests {
  private TemplateBundle templateBundle;

  @BeforeEach
  public void setup() {
    try {
      templateBundle = new TemplateBundleFactory().create(
          "src/test/resources/FileTemplateBundle_getTemplate_Tests",
          "template");
    }
    catch (IOException e) {
      fail();
    }
  }

  @Test
  public void testTemplateInRoot() {
    assertEquals("template", templateBundle.getMain());

    try {
      Reader template = templateBundle.getTemplate("template");
      assertNotNull(template);
      assertTrue(template.read(new char[1024]) > 0);
    }
    catch (IOException e) {
      Assertions.fail(e.getMessage(), e);
    }
  }

  @Test
  public void testTemplateInSubDir() {
    assertEquals("template", templateBundle.getMain());

    try {
      Reader template = templateBundle.getTemplate("dir/template");
      assertNotNull(template);
      assertTrue(template.read(new char[1024]) > 0);
    }
    catch (IOException e) {
      Assertions.fail(e.getMessage(), e);
    }
  }

  @Test
  public void testTemplateIsNotFound() {
    try {
      Reader template = templateBundle.getTemplate("not_found_template");
    }
    catch (IOException e) {
      Assertions.assertTrue(e.getMessage().endsWith("is not found"));
    }
  }

  @Test
  public void testTemplateIsNotFile() {
    try {
      Reader template = templateBundle.getTemplate("dir");
    }
    catch (IOException e) {
      Assertions.assertTrue(e.getMessage().endsWith("is not a file"));
    }
  }
}
