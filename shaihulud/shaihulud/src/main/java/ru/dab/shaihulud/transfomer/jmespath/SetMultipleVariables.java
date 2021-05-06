package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static io.burt.jmespath.function.ArgumentConstraints.*;

public class SetMultipleVariables extends BaseFunction {
  private final @NotNull Map<String, Object> variablesByName;

  public SetMultipleVariables(@NotNull Map<String, Object> variablesByName) {
    super("set_vars",
          typeOf(JmesPathType.OBJECT),
          anyValue());
    this.variablesByName = variablesByName;
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    T variablesByName = arguments.get(0).value();
    Collection<T> names = runtime.getPropertyNames(variablesByName);
    for (T name : names) {
      T value = runtime.getProperty(variablesByName, name);
      if (value != null) {
        this.variablesByName.put(runtime.toString(name), value);
      }
      else {
        this.variablesByName.remove(runtime.toString(name));
      }
    }
    return arguments.get(1).value();
  }
}
