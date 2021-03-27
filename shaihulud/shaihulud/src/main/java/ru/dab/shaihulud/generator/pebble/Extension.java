package ru.dab.shaihulud.generator.pebble;

import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.extension.Function;
import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.ResultStore;

import java.util.HashMap;
import java.util.Map;

public class Extension extends AbstractExtension {
  private final @NotNull Map<String, Function> functionsByName;
  private final @NotNull Map<String, Filter> filtersByName;

  public Extension(@NotNull ResultStore resultStore) {
    functionsByName = new HashMap<>();
    functionsByName.put("writeFile", new WriteFile(resultStore));
    filtersByName = new HashMap<>();
    filtersByName.put("replaceAll", new ReplaceAll());
  }

  @Override
  public Map<String, Function> getFunctions() {
    return functionsByName;
  }

  @Override
  public Map<String, Filter> getFilters() {
    return filtersByName;
  }

}
