package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.ArgumentConstraints;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;
import ru.dab.shaihulud.utils.MarkedCaseChanger;

import java.util.List;

public class ReplaceAll extends BaseFunction {
  public ReplaceAll() {
    super("replace_all",
          ArgumentConstraints.typeOf(JmesPathType.STRING, JmesPathType.NULL),
          ArgumentConstraints.typeOf(JmesPathType.STRING),
          ArgumentConstraints.typeOf(JmesPathType.STRING));
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    String string = runtime.toString(arguments.get(0).value());
    if (string == null) {
      return runtime.createNull();
    }
    String regexp = runtime.toString(arguments.get(1).value());
    String replacement = runtime.toString(arguments.get(2).value());
    String result = string.replaceAll(regexp, replacement);
    result = MarkedCaseChanger.changeCase(result);
    return runtime.createString(result);
  }
}
