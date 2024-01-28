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
import java.util.HashMap;
import java.util.Map;

public class MustacheGenerator implements Generator {
  private final TemplateBundle templateBundle;
  private final ResultStore    resultStore;

  public MustacheGenerator(@NotNull TemplateBundle templateBundle,
                           @NotNull ResultStore resultStore) {
    this.templateBundle = templateBundle;
    this.resultStore = resultStore;
  }

  @Override
  public void generate(@NotNull Map<String, Object> specification,
                       @Nullable Map<String, Object> options)
      throws IOException {
    MustacheFactory mf = new DefaultMustacheFactory(
        new MustacheTemplateResolver(templateBundle));
    Mustache mustache = mf.compile(templateBundle.getMain());
    Writer writer = resultStore.getWriter();
    putExtensions(specification);
    mustache.execute(writer, specification);
    writer.flush();
  }

  private void putExtensions(@NotNull Map<String, Object> specification) {
    Extension extension = new Extension(resultStore);
    specification.put("$GEN$", new HashMap<String, Object>() {{
      put("writeFile", extension.getWriteFileFunction());
    }});
  }
}
