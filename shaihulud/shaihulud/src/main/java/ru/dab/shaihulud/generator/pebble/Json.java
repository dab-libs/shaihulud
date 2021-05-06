package ru.dab.shaihulud.generator.pebble;

import com.mitchellbosecke.pebble.error.PebbleException;
import com.mitchellbosecke.pebble.extension.Filter;
import com.mitchellbosecke.pebble.template.EvaluationContext;
import com.mitchellbosecke.pebble.template.PebbleTemplate;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Json implements Filter {
  @Override
  public Object apply(Object input, Map<String, Object> args,
                      PebbleTemplate self, EvaluationContext context,
                      int lineNumber) throws PebbleException {
    if (input instanceof Map) {
      return new JSONObject((Map<?, ?>) input).toString(2);
    }
    if (input instanceof Collection) {
      return new JSONArray((Collection<?>) input).toString(2);
    }
    if (input instanceof String) {
      return "\"" + input.toString() + "\"";
    }
    return input.toString();
  }

  @Override
  public List<String> getArgumentNames() {
    return null;
  }
}
