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
    functionsByName = createFunctions(resultStore);
    filtersByName = createFilters();
    globalVariables = createGlobalVariables();
  }

  @NotNull
  private Map<String, Function> createFunctions(
      @NotNull ResultStore resultStore) {
    final Map<String, Function> functionsByName;
    functionsByName = new HashMap<>();
    functionsByName.put("writeFile", new WriteFile(resultStore));
    functionsByName.put("debug", new Debug());
    functionsByName.put("halt", new Halt());
    return functionsByName;
  }

  @NotNull
  private Map<String, Filter> createFilters() {
    final Map<String, Filter> filtersByName;
    filtersByName = new HashMap<>();
    filtersByName.put("replaceAll", new ReplaceAll());
    filtersByName.put("camelCase", new CamelCase());
    filtersByName.put("pascalCase", new PascalCase());
    filtersByName.put("upperCaseFirst", new UpperCaseFirst());
    filtersByName.put("quoteString", new QuoteString());
    filtersByName.put("sortKeys", new SortKeys());
    return filtersByName;
  }

  @NotNull
  private Map<String, Object> createGlobalVariables() {
    final Map<String, Object> globalVariables;
    globalVariables = new HashMap<>();
    globalVariables.put("SEPARATOR", File.separator);
    globalVariables.put("SLASH", "/");
    globalVariables.put("BACK_SLASH", "\\");
    return globalVariables;
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
