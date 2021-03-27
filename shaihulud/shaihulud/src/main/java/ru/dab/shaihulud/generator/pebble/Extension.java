package ru.dab.shaihulud.generator.pebble;

import com.mitchellbosecke.pebble.extension.AbstractExtension;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.extension.Function;
import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.ResultStore;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Extension extends AbstractExtension {
  private final Map<String, Function> functionsByName;
  private final Map<String, Filter>   filtersByName;
  private final Map<String, Object>   globalVariables;

  public Extension(@NotNull ResultStore resultStore) {
    functionsByName = new HashMap<>();
    functionsByName.put("writeFile", new WriteFile(resultStore));
    filtersByName = new HashMap<>();
    filtersByName.put("replaceAll", new ReplaceAll());
    filtersByName.put("camelCase", new CamelCase());
    filtersByName.put("pascalCase", new PascalCase());
    globalVariables = new HashMap<>();
    globalVariables.put("SEPARATOR", File.separator);
  }

  @Override
  public Map<String, Function> getFunctions() {
    return functionsByName;
  }

  @Override
  public Map<String, Filter> getFilters() {
    return filtersByName;
  }

  @Override
  public Map<String, Object> getGlobalVariables() {
    return globalVariables;
  }
}
