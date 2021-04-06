package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;

public interface TemplateBundleOptions {
  @NotNull String getRoot();

  @NotNull String getMain();
}
