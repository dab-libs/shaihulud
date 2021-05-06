package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;

import java.util.List;

import static io.burt.jmespath.function.ArgumentConstraints.*;

public class Concat extends BaseFunction {
  public Concat() {
    super("concat",
          listOf(2, anyValue()));
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    StringBuilder result = new StringBuilder();
    for (FunctionArgument<T> argument: arguments) {
      T value = argument.value();
      result.append(runtime.toString(value));
    }
    return runtime.createString(result.toString());
  }
}
