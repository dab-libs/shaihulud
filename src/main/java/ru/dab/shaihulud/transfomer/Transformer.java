package ru.dab.shaihulud.transfomer;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Reader;

public interface Transformer {
  @NotNull Object transform(
      @NotNull Reader scriptReader, @NotNull Object data)
      throws IOException;
}
