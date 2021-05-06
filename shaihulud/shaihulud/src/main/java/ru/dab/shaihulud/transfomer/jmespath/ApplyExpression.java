package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.Expression;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;

import static io.burt.jmespath.function.ArgumentConstraints.*;

public class ApplyExpression extends BaseFunction {
  private final @NotNull Map<String, Expression<?>> expressionsByName;

  public ApplyExpression(@NotNull Map<String, Expression<?>> expressionsByName) {
    super("apply",
          typeOf(JmesPathType.STRING),
          anyValue());
    this.expressionsByName = expressionsByName;
  }

  @SuppressWarnings("unchecked")
  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    String name = runtime.toString(arguments.get(0).value());
    Expression<T> expression = (Expression<T>) expressionsByName.get(name);
    T result = expression.search(arguments.get(1).value());
    return result;
  }
}
