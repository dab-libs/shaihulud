package ru.dab.shaihulud.generator.mustache;

import org.jetbrains.annotations.NotNull;

import java.io.StringReader;
import java.util.Map;

public class GeneratingContext {
  public final @NotNull Map<String, Object> $SPEC$;
  public final @NotNull AdditionalFunctions $GEN$;

  public GeneratingContext(
      @NotNull Map<String, Object> root,
      @NotNull AdditionalFunctions additionalFunctions) {
    $SPEC$ = root;
    $GEN$ = additionalFunctions;
  }

  public @NotNull String getName() {
    return "__CONTEXT__";
  }

  public @NotNull StringReader createReader(@NotNull String rootName) {
    return new StringReader("{{#$SPEC$}}{{>" + rootName + "}}{{/$SPEC$}}");
  }
}
