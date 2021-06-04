package ru.dab.shaihulud.input;

import org.junit.jupiter.api.Test;
import ru.dab.shaihulud.io.FileTemplateBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTemplateBundle_getMain_Tests {
  @Test
  public void testMain() {
    FileTemplateBundle templateBundle = new FileTemplateBundle(
        "src/test/resources/FileTemplateBundle_getTemplate_Tests",
        "template");
    assertEquals("template", templateBundle.getMain());
  }
}
