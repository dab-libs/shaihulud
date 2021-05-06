package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.ArgumentConstraints;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CreateSet extends BaseFunction {
  public CreateSet() {
    super("create_set",
          ArgumentConstraints.typeOf(JmesPathType.ARRAY));
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    List<T> list = runtime.toList(arguments.get(0).value());
    Set<T> result = new HashSet<>(list);
    return runtime.createArray(result);
  }
}
