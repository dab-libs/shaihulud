package ru.dab.shaihulud.generator.mustache;

import org.jetbrains.annotations.NotNull;

import java.io.StringReader;
import java.util.Map;

public class GeneratingContext {
  public final @NotNull Map<String, Object> $SPEC$;
  public final @NotNull Globals             $GLOBALS$;

  public GeneratingContext(
      @NotNull Map<String, Object> root, @NotNull Globals globals) {
    $SPEC$ = root;
    $GLOBALS$ = globals;
  }

  public @NotNull String getName() {
    return "__CONTEXT__";
  }

  public @NotNull StringReader createReader(@NotNull String rootName) {
    return new StringReader("{{#$SPEC$}}{{>" + rootName + "}}{{/$SPEC$}}");
  }
}
