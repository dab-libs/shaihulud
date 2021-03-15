package ru.dab.shaihulud.specification;

import java.io.InputStream;

public class ParserException extends Exception {
  private final InputStream stream;

  public ParserException(String message, Exception cause, InputStream stream) {
    super(message, cause);
    this.stream = stream;
  }

  public InputStream getStream() {
    return stream;
  }
}
