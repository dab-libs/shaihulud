package ru.dab.shaihulud.generator.pebble;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import ru.dab.shaihulud.utils.MarkedCaseChanger;

import java.util.*;

public class OneTime implements Filter {
  private static final List<String> argumentNames =
      Collections.singletonList("setName");

  private final Map<String, Set<String>> setsByName = new HashMap<>();

  @Override
  public Object apply(Object input, Map<String, Object> args,
                      PebbleTemplate self, EvaluationContext context,
                      int lineNumber) throws PebbleException {
    String string = input.toString();
    String setName = args.get(argumentNames.get(0)).toString();
    if (!setsByName.containsKey(setName)) {
      setsByName.put(setName, new HashSet<>());
    }
    Set<String> set = setsByName.get(setName);
    if (set.contains(string)) {
      return null;
    }
    set.add(string);
    return input;
  }

  @Override
  public List<String> getArgumentNames() {
    return argumentNames;
  }
}
