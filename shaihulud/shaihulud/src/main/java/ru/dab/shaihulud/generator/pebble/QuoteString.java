package ru.dab.shaihulud.generator.pebble;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import ru.dab.shaihulud.utils.MarkedCaseChanger;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class QuoteString implements Filter {
  private final List<String> argumentNames = Collections.singletonList("mark");

  @Override
  public Object apply(Object input, Map<String, Object> args,
                      PebbleTemplate self, EvaluationContext context,
                      int lineNumber) throws PebbleException {
    if (!(input instanceof String)) {
      return input;
    }
    String mark = "\"";
    if (args.containsKey(argumentNames.get(0))) {
      mark = args.get(argumentNames.get(0)).toString();
    }
    return mark + input + mark;
  }

  @Override
  public List<String> getArgumentNames() {
    return argumentNames;
  }
}
