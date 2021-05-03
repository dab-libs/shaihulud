package ru.dab.shaihulud.transfomer.jmespath;

import io.burt.jmespath.Expression;
import io.burt.jmespath.JmesPath;
import io.burt.jmespath.RuntimeConfiguration;
import io.burt.jmespath.function.FunctionRegistry;
import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.transfomer.Transformer;

import java.io.IOException;
import java.io.Reader;

public class JmesPathTransformer implements Transformer {
  @Override
  public @NotNull Object transform(
      @NotNull Reader scriptReader, @NotNull Object data)
      throws IOException {
    FunctionRegistry customFunctions = FunctionRegistry
        .defaultRegistry()
        .extend(
            new Debug(),
            new ReplaceAll(),
            new PascalCase(),
            new KebabCase());
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
