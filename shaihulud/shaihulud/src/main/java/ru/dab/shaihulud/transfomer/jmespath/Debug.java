package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Adapter;
import io.burt.jmespath.JmesPathType;
import io.burt.jmespath.function.ArgumentConstraints;
import io.burt.jmespath.function.BaseFunction;
import io.burt.jmespath.function.FunctionArgument;
import ru.dab.shaihulud.Console;
import ru.dab.shaihulud.utils.MarkedCaseChanger;

import java.util.HashMap;
import java.util.List;

import static io.burt.jmespath.function.ArgumentConstraints.anyValue;
import static io.burt.jmespath.function.ArgumentConstraints.listOf;

public class Debug extends BaseFunction {
  private final Console console;

  public Debug(Console console) {
    super("debug",
          listOf(1, anyValue()));
    this.console = console;
  }

  @Override
  protected <T> T callFunction(Adapter<T> runtime,
                               List<FunctionArgument<T>> arguments) {
    String indent = "DEBUG: ";
    for (FunctionArgument<T> argument: arguments){
      console.println(indent, runtime.toString(argument.value()));
      indent = "       ";
    }
    return arguments.get(arguments.size() - 1).value();
  }
}
