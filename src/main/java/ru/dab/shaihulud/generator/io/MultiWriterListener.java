package ru.dab.shaihulud.generator.io;

import java.io.IOException;

public interface MultiWriterListener {
  void fileChosen(String file) throws IOException;
}
