package ru.dab.shaihulud.templating;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.jetbrains.annotations.NotNull;

import java.io.StringReader;
import java.io.Writer;
import java.util.Map;

public class MustacheTemplateProcessor implements TemplateProcessor {
  @Override
  public void process(@NotNull Map<String, Object> specification,
                      @NotNull StringReader templateReader,
                      @NotNull Writer writer) {
    MustacheFactory mf = new DefaultMustacheFactory();
    Mustache mustache = mf.compile(templateReader, "main");
    mustache.execute(writer, specification);
  }
}
