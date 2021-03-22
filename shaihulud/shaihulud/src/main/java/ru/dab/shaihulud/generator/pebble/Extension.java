package ru.dab.shaihulud.generator.pebble;

import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.extension.Function;
import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.ResultStore;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Extension extends AbstractExtension {
  private final @NotNull ResultStore           resultStore;
  private final @NotNull Map<String, Function> functionsByName;

  public Extension(@NotNull ResultStore resultStore) {
    this.resultStore = resultStore;
    functionsByName = new HashMap<>();
    functionsByName.put("writeFile", new WriteFile(resultStore));
  }

  @Override
  public Map<String, Function> getFunctions() {
    return functionsByName;
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
