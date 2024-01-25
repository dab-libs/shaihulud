package ru.dab.shaihulud.generator.mustache;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.generator.Generator;
import ru.dab.shaihulud.generator.ResultStore;
import ru.dab.shaihulud.generator.TemplateBundle;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class MustacheGenerator implements Generator {
  @Override
  public void generate(@NotNull Map<String, Object> specification,
                       @NotNull TemplateBundle templateBundle,
                       @NotNull ResultStore store,
                       @Nullable Map<String, Object> options)
      throws IOException {
    MustacheFactory mf = new DefaultMustacheFactory(
        new MustacheTemplateResolver(templateBundle));
    Mustache mustache = mf.compile(templateBundle.getMain());
    Writer writer = store.getWriter();
    mustache.execute(writer, specification);
    writer.flush();
  }
}
