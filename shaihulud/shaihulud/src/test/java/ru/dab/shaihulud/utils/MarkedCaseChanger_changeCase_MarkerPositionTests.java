package ru.dab.shaihulud.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MarkedCaseChanger_changeCase_MarkerPositionTests {
  @Test
  public void markerInBegin() {
    String s = "\\lWORLD";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("wORLD", r);
  }

  @Test
  public void markerInMiddle() {
    String s = "HELLO\\lWORLD";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("HELLOwORLD", r);
  }

  @Test
  public void markerInEnd() {
    String s = "REGEX\\l";
    String r = MarkedCaseChanger.changeCase(s);
    Assertions.assertEquals("REGEX", r);
  }

}
