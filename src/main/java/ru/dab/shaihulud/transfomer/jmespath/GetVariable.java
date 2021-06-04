package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.ArgumentConstraints;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

public class GetVariable extends BaseFunction {
  private final @NotNull Map<String, Object> variablesByName;

  public GetVariable(@NotNull Map<String, Object> variablesByName) {
    super("get_var",
          ArgumentConstraints.typeOf(JmesPathType.STRING));
    this.variablesByName = variablesByName;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    String name = runtime.toString(arguments.get(0).value());
    Object value = variablesByName.get(name);
    if (value == null) {
      return runtime.createNull();
    }
    return (T) value;
  }
}
