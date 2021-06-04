package ru.dab.shaihulud.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarkedCaseChanger_changeCase_EscapedTests {
  @Test
  public void markerEscaped() {
    String s = "HELLO\\\\lWORLD";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLO\\lWORLD", r);
  }

  @Test
  public void markerEscapedLetter() {
    String s = "HELLO \\l\\WORLD";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLO WoRLD", r);
  }

  @Test
  public void markerEscapedLetterInWord() {
    String s = "HELLO \\L\\WORLD";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLO World", r);
  }
}
