package ru.dab.shaihulud.generator.file;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTemplateBundle_getMain_Tests {
  @Test
  public void testMain() {
    FileTemplateBundle templateBundle = new FileTemplateBundle(
        "src/test/resources/FileTemplateBundle_getTemplate_Tests",
        "template", "mustache");
    assertEquals("template", templateBundle.getMain());
  }
}
