package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.ArgumentConstraints;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;

import java.util.*;

import static io.burt.jmespath.function.ArgumentConstraints.listOf;
import static io.burt.jmespath.function.ArgumentConstraints.typeOf;

public class Entries extends BaseFunction {
  public Entries() {
    super("entries",
          typeOf(JmesPathType.OBJECT),
          typeOf(JmesPathType.STRING),
          typeOf(JmesPathType.STRING));
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    T obj = arguments.get(0).value();
    Collection<T> names = runtime.getPropertyNames(obj);
    T key = arguments.get(1).value();
    T value = arguments.get(2).value();
    List<T> result = new ArrayList<>();
    for (T name : names) {
      Map<T, T> entry = new HashMap<>();
      entry.put(key, name);
      entry.put(value, runtime.getProperty(obj, name));
      result.add(runtime.createObject(entry));
    }
    return runtime.createArray(result);
  }
}
