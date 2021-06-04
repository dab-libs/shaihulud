package ru.dab.shaihulud.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarkedCaseChanger_changeCase_MixedTests {
  @Test
  public void markerUpperLetterLowerWord() {
    String s = "\\u\\LHELLO\\u\\LWORLD";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HelloWorld", r);
  }
}
