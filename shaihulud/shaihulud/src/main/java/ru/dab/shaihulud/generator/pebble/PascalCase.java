package ru.dab.shaihulud.generator.pebble;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.extension.Function;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import ru.dab.shaihulud.utils.MarkedCaseChanger;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PascalCase implements Filter {
  private static final List<String> argumentNames =
      Collections.singletonList("string");

  @Override
  public Object apply(Object input, Map<String, Object> args,
                        PebbleTemplate self, EvaluationContext context,
                        int lineNumber) throws PebbleException {
    String string = input.toString();
    String result = "\\u"+string.replaceAll("[^\\w]+", "\\\\u\\\\L");
    result = MarkedCaseChanger.changeCase(result);
    return result;
  }

  @Override
  public List<String> getArgumentNames() {
    return null;
  }
}
