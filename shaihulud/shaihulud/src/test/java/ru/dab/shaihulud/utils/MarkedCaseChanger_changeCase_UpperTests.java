package ru.dab.shaihulud.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarkedCaseChanger_changeCase_UpperTests {
  @Test
  public void letterMarker() {
    String s = "hello\\uworld";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("helloWorld", r);
  }

  @Test
  public void letterMarkerSpace() {
    String s = "hello\\u world";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("hello world", r);
  }

  @Test
  public void letterMarkerDigit() {
    String s = "hello\\u8world";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("hello8world", r);
  }

  @Test
  public void letterMarkerUnderscore() {
    String s = "hello\\u_world";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("hello_world", r);
  }

  @Test
  public void wordMarker() {
    String s = "hello\\Uworld REG";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("helloWORLD REG", r);
  }

  @Test
  public void wordMarkerEOL() {
    String s = "hello\\Uworld";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("helloWORLD", r);
  }

  @Test
  public void wordMarkerSpace() {
    String s = "hello\\U world REG";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("hello world REG", r);
  }

  @Test
  public void wordMarkerDigit() {
    String s = "hello\\U8world REG";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("hello8WORLD REG", r);
  }

  @Test
  public void wordMarkerUnderscore() {
    String s = "hello\\U_world REG";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("hello_WORLD REG", r);
  }
}
