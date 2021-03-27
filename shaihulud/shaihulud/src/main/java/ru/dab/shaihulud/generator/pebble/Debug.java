package ru.dab.shaihulud.generator.pebble;

import com.mitchellbosecke.pebble.extension.Function;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Debug implements Function {
  private static final List<String> argumentNames =
      Collections.singletonList("data");

  public Debug() {
  }

  @Override
  public Object execute(Map<String, Object> args, PebbleTemplate self,
                        EvaluationContext context, int lineNumber) {
    Object data = args.get(argumentNames.get(0));
    return null;
  }

  @Override
  public List<String> getArgumentNames() {
    return argumentNames;
  }
}
