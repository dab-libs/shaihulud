package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.BaseRuntime;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.RuntimeConfiguration;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class SimpleRuntime extends BaseRuntime<Object> {
  public SimpleRuntime() {
    this(RuntimeConfiguration.defaultConfiguration());
  }

  public SimpleRuntime(RuntimeConfiguration configuration) {
    super(configuration);
  }

  @Override
  public Object parseString(String s) {
    JSONObject jsonObject = new JSONObject(new JSONTokener(s));
    return jsonObject.toMap();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Object> toList(Object o) {
    if (o instanceof List) {
      return (List<Object>) o;
    }
    if (o instanceof Map) {
      return new ArrayList<>(((Map<Object, Object>) o).values());
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public String toString(Object o) {
    if (o == null) {
      return null;
    }
    if (o instanceof Map) {
      StringBuilder result = new StringBuilder();
      result.append('{');
      Map<Object, Object> map = (Map<Object, Object>) o;
      for (Map.Entry<Object, Object> entry : map.entrySet()) {
        result.append(toString(entry.getKey()));
        result.append(": ");
        result.append(toString(entry.getValue()));
        result.append(", ");
      }
      if (!map.isEmpty()) {
        result.setLength(result.length() - 2);
      }
      result.append('}');
      return result.toString();
    }
    if (o instanceof List) {
      StringBuilder result = new StringBuilder();
      result.append('[');
      List<Object> list = (List<Object>) o;
      for (Object item : list) {
        result.append(toString(item));
        result.append(", ");
      }
      if (!list.isEmpty()) {
        result.setLength(result.length() - 2);
      }
      result.append(']');
      return result.toString();
    }
    return o.toString();
  }

  @Override
  public Number toNumber(Object o) {
    if (o instanceof Number) {
      return (Number) o;
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean isTruthy(Object o) {
    if (o == null) {
      return false;
    }
    if (o instanceof Boolean) {
      return (Boolean) o;
    }
    if (o instanceof String) {
      return ((String) o).length() != 0;
    }
    if (o instanceof List) {
      return !((List<Object>) o).isEmpty();
    }
    if (o instanceof Map) {
      return !((Map<Object, Object>) o).isEmpty();
    }

    return true;
  }

  @Override
  public JmesPathType typeOf(Object o) {
    if (o == null) {
      return JmesPathType.NULL;
    }
    if (o instanceof List) {
      return JmesPathType.ARRAY;
    }
    if (o instanceof Map) {
      return JmesPathType.OBJECT;
    }
    if (o instanceof Boolean) {
      return JmesPathType.BOOLEAN;
    }
    if (o instanceof Number) {
      return JmesPathType.NUMBER;
    }
    if (o instanceof String) {
      return JmesPathType.STRING;
    }
    throw new IllegalStateException(
        String.format("Unknown object type encountered: %s", o.getClass()));
  }

  @SuppressWarnings("unchecked")
  @Override
  public Object getProperty(Object o, Object name) {
    if (o instanceof Map) {
      return ((Map<Object, Object>) o).get(name);
    }
    return null;
  }

  @SuppressWarnings("unchecked")
  @Override
  public Collection<Object> getPropertyNames(Object o) {
    if (o instanceof Map) {
      return ((Map<Object, Object>) o).keySet();
    }
    return null;
  }

  @Override
  public Object createNull() {
    return null;
  }

  @Override
  public Object createArray(Collection<Object> collection) {
    if (collection instanceof List) {
      return collection;
    }
    return new ArrayList<>(collection);
  }

  @Override
  public Object createString(String s) {
    return s;
  }

  @Override
  public Object createBoolean(boolean b) {
    return b;
  }

  @Override
  public Object createObject(Map<Object, Object> map) {
    return map;
  }

  @Override
  public Object createNumber(double v) {
    return v;
  }

  @Override
  public Object createNumber(long l) {
    return l;
  }
}
