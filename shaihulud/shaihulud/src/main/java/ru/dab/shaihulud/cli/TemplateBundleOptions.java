package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;

public interface TemplateBundleOptions {
  @NotNull String getRoot();

  @NotNull String getMain();
}
