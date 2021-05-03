package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.ArgumentConstraints;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;
import ru.dab.shaihulud.utils.MarkedCaseChanger;

import java.util.HashMap;
import java.util.List;

public class Debug extends BaseFunction {
  public Debug() {
    super("debug",
          ArgumentConstraints.typeOf(JmesPathType.OBJECT));
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    return runtime.createObject(new HashMap<>());
  }
}
