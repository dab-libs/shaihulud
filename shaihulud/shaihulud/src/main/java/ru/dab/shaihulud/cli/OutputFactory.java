package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.output.MemoryResultWriter;

import java.io.Writer;

public class OutputFactory {
  private final @NotNull OutputOptions options;

  public OutputFactory(@NotNull OutputOptions options) {
    this.options = options;
  }

  public @NotNull Writer create() {
    return new MemoryResultWriter();
  }
}
