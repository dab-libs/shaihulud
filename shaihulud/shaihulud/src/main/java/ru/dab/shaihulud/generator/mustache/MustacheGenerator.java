package ru.dab.shaihulud.generator.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.Generator;
import ru.dab.shaihulud.generator.ResultStore;
import ru.dab.shaihulud.generator.TemplateBundle;

import java.util.Map;

public class MustacheGenerator implements Generator {

  @Override
  public void generate(
      @NotNull Map<String, Object> specification,
      @NotNull TemplateBundle templateBundle,
      @NotNull ResultStore resultStore) {
    GeneratingContext context = new GeneratingContext(
        specification, new Globals());
    MustacheFactory mf = new DefaultMustacheFactory(
        new FileResolver(templateBundle));
    Mustache mustache = mf.compile(
        context.createReader(templateBundle.getMain()), context.getName());
    mustache.execute(resultStore.getWriter(), context);
  }
}
