package ru.dab.shaihulud.templating;

import org.jetbrains.annotations.NotNull;

import java.io.StringReader;
import java.io.Writer;
import java.util.Map;

public interface TemplateProcessor {
  void process(
      @NotNull Map<String, Object> specification,
      @NotNull StringReader templateReader,
      @NotNull Writer writer);
}
