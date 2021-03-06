package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Expression;
import io.burt.jmespath.JmesPath;
import io.burt.jmespath.RuntimeConfiguration;
import io.burt.jmespath.function.FunctionRegistry;
import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.Console;
import ru.dab.shaihulud.transfomer.Transformer;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class JmesPathTransformer implements Transformer {
  private final Console console;

  public JmesPathTransformer(Console console) {
    this.console = console;
  }

  @Override
  public @NotNull Object transform(
      @NotNull Reader scriptReader, @NotNull Object data)
      throws IOException {
    HashMap<String, Object> variablesByName = new HashMap<>();
    Map<String, Expression<?>> expressionsByName = new HashMap<>();
    FunctionRegistry customFunctions = FunctionRegistry
        .defaultRegistry()
        .extend(
            new Concat(),
            new Debug(console),
            new CreateSet(),
            new Property(),
            new Entries(),
            new EmptyArray(),
            new If(),
            new Halt(),
            new HaltEmpty(),
            new ReplaceAll(),
            new UpperCaseFirst(),
            new CamelCase(),
            new PascalCase(),
            new KebabCase(),
            new SetVariable(variablesByName),
            new SetMultipleVariables(variablesByName),
            new GetVariable(variablesByName),
            new DefineExpression(expressionsByName),
            new ApplyExpression(expressionsByName));
    RuntimeConfiguration configuration = new RuntimeConfiguration.Builder()
        .withFunctionRegistry(customFunctions)
        .build();
    JmesPath<Object> jmespath = new SimpleRuntime(configuration);
    Expression<Object> expression = jmespath.compile(readAll(scriptReader));
    return expression.search(data);
  }

  private @NotNull String readAll(@NotNull Reader reader) throws IOException {
    int valChar;
    StringBuilder stringBuilder = new StringBuilder();
    while ((valChar = reader.read()) != -1) {
      stringBuilder.append((char) valChar);
    }

    return stringBuilder.toString();
  }
}
