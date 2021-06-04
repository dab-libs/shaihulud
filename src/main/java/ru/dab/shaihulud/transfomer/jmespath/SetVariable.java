package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import static io.burt.jmespath.function.ArgumentConstraints.*;

public class SetVariable extends BaseFunction {
  private final @NotNull Map<String, Object> variablesByName;

  public SetVariable(@NotNull Map<String, Object> variablesByName) {
    super("set_var",
          typeOf(JmesPathType.STRING),
          listOf(1, 2, anyValue()));
    this.variablesByName = variablesByName;
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    String name = runtime.toString(arguments.get(0).value());
    T value = arguments.get(1).value();
    if (value != null) {
      variablesByName.put(name, value);
    }
    else {
      variablesByName.remove(name);
    }
    if (arguments.size() == 3) {
      return arguments.get(2).value();
    }
    else {
      return value;
    }
  }
}
