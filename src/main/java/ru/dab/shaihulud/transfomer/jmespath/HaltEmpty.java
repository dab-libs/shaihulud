package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.ArgumentConstraints;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;

import java.util.HashMap;
import java.util.List;

public class HaltEmpty extends BaseFunction {
  public HaltEmpty() {
    super("halt_empty",
          ArgumentConstraints.anyValue(),
          ArgumentConstraints.typeOf(JmesPathType.STRING));
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    T value = arguments.get(0).value();
    if (!runtime.isTruthy(value)) {
      throw new RuntimeException(arguments.get(1).toString());
    }
    return value;
  }
}
