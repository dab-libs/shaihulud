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
  private final Map<String, Object>   globalVariables;

  public Extension(@NotNull ResultStore resultStore) {
    functionsByName = new HashMap<>();
    functionsByName.put("writeFile", new WriteFile(resultStore));
    functionsByName.put("replaceAll", new ReplaceAll());
    functionsByName.put("camelCase", new CamelCase());
    functionsByName.put("pascalCase", new PascalCase());
    functionsByName.put("debug", new Debug());
    globalVariables = new HashMap<>();
    globalVariables.put("SEPARATOR", File.separator);
  }

  @Override
  public Map<String, Function> getFunctions() {
    return functionsByName;
  }

  @Override
  public Map<String, Object> getGlobalVariables() {
    return globalVariables;
  }
}
