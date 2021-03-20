package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.specification.SpecificationParserType;

public interface SpecificationParserOptions {
  @NotNull SpecificationParserType getSpecificationParserType();
}
