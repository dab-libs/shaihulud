package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.ArgumentConstraints;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;

import java.util.ArrayList;
import java.util.List;

public class EmptyArray extends BaseFunction {
  public EmptyArray() {
    super("empty_array");
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    return runtime.createArray(new ArrayList<>());
  }
}
