package ru.dab.shaihulud.generator.pebble;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.generator.Generator;
import ru.dab.shaihulud.generator.ResultStore;
import ru.dab.shaihulud.generator.TemplateBundle;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PebbleGenerator implements Generator {
  @Override
  public void generate(@NotNull Map<String, Object> specification,
                       @NotNull TemplateBundle templateBundle,
                       @NotNull ResultStore resultStore,
                       @Nullable Map<String, Object> config)
      throws IOException {
    PebbleEngine engine = new PebbleEngine
        .Builder()
        .newLineTrimming(true)
        .loader(new Loader(templateBundle))
        .extension(new Extension(resultStore))
        .build();
    PebbleTemplate template = engine.getTemplate(templateBundle.getMain());
    Map<String, Object> context = new HashMap<>(specification);
    if (config != null) {
      context.put("$CONFIG$", config);
    }
    template.evaluate(resultStore.getWriter(), context);
  }
}
