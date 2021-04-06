package ru.dab.shaihulud.io;

import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.generator.ResultStore;
import ru.dab.shaihulud.io.FileResultStore;

import java.io.File;
import java.io.IOException;

public class ResultStoreFactory {
  public @NotNull ResultStore create(String rootPath) {
    return new FileResultStore(new File(rootPath));
  }
}
