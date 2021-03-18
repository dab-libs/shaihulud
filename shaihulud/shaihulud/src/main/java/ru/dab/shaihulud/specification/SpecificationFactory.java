package ru.dab.shaihulud.specification;

import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class SpecificationFactory {
  private final @NotNull SpecificationOptions options;

  public SpecificationFactory(@NotNull SpecificationOptions options) {
    this.options = options;
  }

  public @NotNull InputStream create() throws FileNotFoundException {
    return new FileInputStream(options.getSpecification());
  }
}