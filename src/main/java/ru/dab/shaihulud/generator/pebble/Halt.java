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

public class Halt implements Function {
  private static final List<String> argumentNames =
      Collections.singletonList("message");

  @Override
  public Object execute(Map<String, Object> args, PebbleTemplate self,
                        EvaluationContext context, int lineNumber) {
    throw new RuntimeException(args.get(argumentNames.get(0)).toString());
  }

  @Override
  public List<String> getArgumentNames() {
    return argumentNames;
  }
}
