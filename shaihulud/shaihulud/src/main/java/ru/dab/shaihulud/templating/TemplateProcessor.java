package ru.dab.shaihulud.templating;

import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.io.Writer;
import java.util.Map;

public interface TemplateProcessor {
  void process(
      @NotNull Map<String, Object> specification,
      @NotNull Reader templateReader,
      @NotNull Writer writer);
}
