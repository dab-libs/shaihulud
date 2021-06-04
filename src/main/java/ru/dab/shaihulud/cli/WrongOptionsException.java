package ru.dab.shaihulud.cli;

class WrongOptionsException extends Exception {
  public WrongOptionsException(String message) {
    this(message, null);
  }

  public WrongOptionsException(String message, Throwable e) {
    super(message, e);
  }
}
