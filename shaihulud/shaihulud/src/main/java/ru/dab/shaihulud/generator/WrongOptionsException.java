package ru.dab.shaihulud.generator;

public class WrongOptionsException extends Exception {
  public WrongOptionsException(String message) {
    this(message, null);
  }

  public WrongOptionsException(String message, Throwable e) {
    super(message, e);
  }
}
