package ru.dab.shaihulud.templating;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.TemplateFunction;
import org.jetbrains.annotations.NotNull;

import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

public class MustacheTemplateProcessor implements TemplateProcessor {
  @Override
  public void process(@NotNull Map<String, Object> specification,
                      @NotNull Reader templateReader,
                      @NotNull Writer writer) {
    MustacheFactory mf = new DefaultMustacheFactory();
    Mustache mustache = mf.compile(templateReader, "main");
    Map<String, Object> specificationCopy = new HashMap<>(specification);
    specificationCopy.put("__func", new TemplateFunction() {
      @Override
      public String apply(String s) {
        return s;
      }
    });
    mustache.execute(writer, specificationCopy);
  }
}
