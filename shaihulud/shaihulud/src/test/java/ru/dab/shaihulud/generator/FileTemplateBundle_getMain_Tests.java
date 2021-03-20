package ru.dab.shaihulud.generator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.*;

public class FileTemplateBundle_getMain_Tests {
  private FileTemplateBundle templateBundle;

  @BeforeEach
  public void setup() {
    templateBundle = new FileTemplateBundle(
        "src/test/resources/FileTemplateBundle_getTemplate_Tests",
        "template", "mustache");
  }

  @Test
  public void testMain() {
    assertEquals("template", templateBundle.getMain());
  }
}
