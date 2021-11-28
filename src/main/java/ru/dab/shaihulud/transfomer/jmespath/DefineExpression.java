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

public class DefineExpression extends BaseFunction {
  private final @NotNull Map<String, Expression<?>> expressionsByName;

  public DefineExpression(@NotNull Map<String, Expression<?>> expressionsByName) {
    super("expression",
          typeOf(JmesPathType.STRING),
          anyValue(),
          expression(),
          anyValue());
    this.expressionsByName = expressionsByName;
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    String name = runtime.toString(arguments.get(0).value());
    Expression<T> expression = arguments.get(2).expression();
    if (expression != null) {
      expressionsByName.put(name, expression);
    }
    else {
      expressionsByName.remove(name);
    }
    return arguments.get(3).value();
  }
}
