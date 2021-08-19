package ru.dab.shaihulud.input;

import org.junit.jupiter.api.Test;
import ru.dab.shaihulud.generator.TemplateBundle;
import ru.dab.shaihulud.generator.io.TemplateBundleFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTemplateBundle_getMain_Tests {
  @Test
  public void testMain() throws IOException {
    TemplateBundle templateBundle = new TemplateBundleFactory().create(
        "src/test/resources/FileTemplateBundle_getTemplate_Tests",
        "template");
    assertEquals("template", templateBundle.getMain());
  }
}
