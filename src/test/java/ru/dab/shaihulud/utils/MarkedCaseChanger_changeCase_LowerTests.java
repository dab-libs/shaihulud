package ru.dab.shaihulud.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarkedCaseChanger_changeCase_LowerTests {
  @Test
  public void letterMarker() {
    String s = "HELLO\\lWORLD";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLOwORLD", r);
  }

  @Test
  public void letterMarkerSpace() {
    String s = "HELLO\\l WORLD";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLO WORLD", r);
  }

  @Test
  public void letterMarkerDigit() {
    String s = "HELLO\\l8WORLD";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLO8WORLD", r);
  }

  @Test
  public void letterMarkerUnderscore() {
    String s = "HELLO\\l_WORLD";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLO_WORLD", r);
  }

  @Test
  public void wordMarker() {
    String s = "HELLO\\LWORLD REG";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLOworld REG", r);
  }

  @Test
  public void wordMarkerEOL() {
    String s = "HELLO\\LWORLD";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLOworld", r);
  }

  @Test
  public void wordMarkerSpace() {
    String s = "HELLO\\L WORLD REG";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLO WORLD REG", r);
  }

  @Test
  public void wordMarkerDigit() {
    String s = "HELLO\\L8WORLD REG";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLO8world REG", r);
  }

  @Test
  public void wordMarkerUnderscore() {
    String s = "HELLO\\L_WORLD REG";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLO_world REG", r);
  }
}
