package ru.dab.shaihulud.generator.io;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.ResultStore;
import ru.dab.shaihulud.generator.io.FileResultStore;

import java.io.File;

public class ResultStoreFactory {
  public @NotNull ResultStore create(String rootPath) {
    return new FileResultStore(new File(rootPath));
  }
}
