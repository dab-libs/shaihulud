package ru.dab.shaihulud;

public class Console {
  public void print(String... messages) {
    for (String message : messages) {
      System.out.print(message);
    }
  }

  public void println(String... messages) {
    print(messages);
    System.out.println();
  }
}
