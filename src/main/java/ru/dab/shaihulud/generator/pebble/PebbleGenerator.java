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
  private final @NotNull TemplateBundle templateBundle;
  private final @NotNull ResultStore    resultStore;

  public PebbleGenerator(@NotNull TemplateBundle templateBundle,
                         @NotNull ResultStore resultStore) {
    this.templateBundle = templateBundle;
    this.resultStore = resultStore;
  }

  @Override
  public void generate(@NotNull Map<String, Object> specification,
                       @Nullable Map<String, Object> config)
      throws IOException {
    PebbleEngine engine = new PebbleEngine
        .Builder()
        .newLineTrimming(true)
        .loader(new Loader(templateBundle))
        .extension(new Extension(resultStore, config))
        .build();
    PebbleTemplate template = engine.getTemplate(templateBundle.getMain());
    Map<String, Object> context = new HashMap<>(specification);
    template.evaluate(resultStore.getWriter(), context);
  }
}
