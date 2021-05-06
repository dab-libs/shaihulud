package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.ArgumentConstraints;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;
import ru.dab.shaihulud.utils.MarkedCaseChanger;

import java.util.List;

import static io.burt.jmespath.function.ArgumentConstraints.*;

public class If extends BaseFunction {
  public If() {
    super("if",
          listOf(anyValue(), anyValue()),
          listOf(1, 2, expression()));
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    if (runtime.isTruthy(arguments.get(0).value())) {
      return arguments.get(2).expression().search(arguments.get(1).value());
    }
    if (arguments.size() > 3) {
      return arguments.get(3).expression().search(arguments.get(1).value());
    }
    return runtime.createNull();
  }
}
