package ru.dab.shaihulud.generator.pebble;

import com.mitchellbosecke.pebble.extension.Function;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.ResultStore;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class WriteFile implements Function {
  private static final List<String> argumentNames =
      Collections.singletonList("fileName");

  private final @NotNull ResultStore resultStore;

  public WriteFile(@NotNull ResultStore resultStore) {
    this.resultStore = resultStore;
  }

  @Override
  public Object execute(Map<String, Object> args, PebbleTemplate self,
                        EvaluationContext context, int lineNumber) {
    try {
      resultStore.switchTo(args.get(argumentNames.get(0)).toString());
    }
    catch (IOException e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public List<String> getArgumentNames() {
    return argumentNames;
  }
}
