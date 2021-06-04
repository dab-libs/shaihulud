package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.ArgumentConstraints;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;

import java.util.List;

public class Halt extends BaseFunction {
  public Halt() {
    super("halt",
          ArgumentConstraints.typeOf(JmesPathType.STRING));
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    throw new RuntimeException(runtime.toString(arguments.get(0).value()));
  }
}
