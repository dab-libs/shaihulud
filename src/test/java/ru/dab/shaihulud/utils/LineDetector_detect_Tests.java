package ru.dab.shaihulud.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class LineDetector_detect_Tests {

  private LineDetector detector =
      new LineDetector("<--WriteFile\\s*\"([\\w\\.]+)\"\\s*-->", "$1");

  @Test
  public void testDetectOneLine() {
    String text = "Hello World!!!<--WriteFile \"new_file.txt\" -->\nNew world.";
    List<String> detected =
        detector.detect(text.toCharArray(), 0, text.length());
    Assertions.assertEquals(1, detected.size());
    Assertions.assertEquals("new_file.txt", detected.get(0));
  }

  @Test
  public void testDetectTwoLine() {
    String text = "Hello World!!!\n<--WriteFile \"new_file1.txt\" -->\nNew world.<--WriteFile \"new_file2.txt\" -->\nNew world.";
    List<String> detected =
        detector.detect(text.toCharArray(), 0, text.length());
    Assertions.assertEquals(2, detected.size());
    Assertions.assertEquals("new_file1.txt", detected.get(0));
    Assertions.assertEquals("new_file2.txt", detected.get(1));
  }

  @Test
  public void testDetectTwoInOneLine() {
    String text = "Hello World!!!\n<--WriteFile \"new_file1.txt\" -->New world.<--WriteFile \"new_file2.txt\" -->\nNew world.";
    List<String> detected =
        detector.detect(text.toCharArray(), 0, text.length());
    Assertions.assertEquals(2, detected.size());
    Assertions.assertEquals("new_file1.txt", detected.get(0));
    Assertions.assertEquals("new_file2.txt", detected.get(1));
  }
}
