package ru.dab.shaihulud.generator.pebble;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import ru.dab.shaihulud.utils.MarkedCaseChanger;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ReplaceAll implements Filter {
  private static final List<String> argumentNames =
      Arrays.asList("regexp", "replacement");

  @Override
  public Object apply(Object input, Map<String, Object> args,
                      PebbleTemplate self, EvaluationContext context,
                      int lineNumber) throws PebbleException {
    String string = input.toString();
    String regexp = args.get(argumentNames.get(0)).toString();
    String replacement = args.get(argumentNames.get(1)).toString();
    String result = string.replaceAll(regexp, replacement);
    result = MarkedCaseChanger.changeCase(result);
    return result;
  }

  @Override
  public List<String> getArgumentNames() {
    return argumentNames;
  }

}
