package ru.dab.shaihulud.generator.mustache;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.ResultStore;

import java.io.IOException;
import java.util.function.Function;

public class Extension {
  private final @NotNull ResultStore resultStore;

  public Extension(@NotNull ResultStore resultStore) {
    this.resultStore = resultStore;
  }

  public Function<String, String> getWriteFileFunction() {
    return this::writeFile;
  }

  public String writeFile(String fileName) {
    try {
      resultStore.switchTo(fileName);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    return "";
  }
}
