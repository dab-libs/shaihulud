package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.ArgumentConstraints;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;
import ru.dab.shaihulud.utils.MarkedCaseChanger;

import java.util.List;

public class CamelCase extends BaseFunction {
  public CamelCase() {
    super("camel_case",
          ArgumentConstraints.typeOf(JmesPathType.STRING, JmesPathType.NULL));
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    String string = runtime.toString(arguments.get(0).value());
    if (string == null) {
      return runtime.createNull();
    }
    String result = "\\l" + string
        .replaceAll("[^\\w]+", "\\\\u\\\\L");
    result = MarkedCaseChanger.changeCase(result);
    return runtime.createString(result);
  }
}
