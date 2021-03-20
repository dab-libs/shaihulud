package ru.dab.shaihulud.generator.mustache;

import com.github.mustachejava.MustacheFactory;
import com.github.mustachejava.TemplateFunction;
import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.ResultStore;

import java.io.IOException;
import java.io.StringReader;
import java.util.function.Function;

public class AdditionalFunctions {
  public final Function<String, String> writeFile = this::writeFile;

  private final @NotNull MustacheFactory mustacheFactory;
  private final @NotNull ResultStore resultStore;

  public AdditionalFunctions(@NotNull MustacheFactory mustacheFactory,
                             @NotNull ResultStore resultStore) {
    this.mustacheFactory = mustacheFactory;
    this.resultStore = resultStore;
  }

  private String writeFile(String nameTemplate) {
    try {
      resultStore.switchTo(nameTemplate);
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }
}
