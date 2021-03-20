package ru.dab.shaihulud.generator;

import java.io.IOException;
import java.io.Reader;

public interface TemplateBundle {
  String getMain();

  Reader getTemplate(String name) throws IOException;
}
