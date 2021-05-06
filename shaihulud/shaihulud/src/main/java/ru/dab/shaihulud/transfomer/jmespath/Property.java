package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import static io.burt.jmespath.function.ArgumentConstraints.*;

public class Property extends BaseFunction {
  public Property() {
    super("property",
          typeOf(JmesPathType.OBJECT),
          anyValue());
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    return runtime.getProperty(arguments.get(0).value(), arguments.get(1).value());
  }
}
