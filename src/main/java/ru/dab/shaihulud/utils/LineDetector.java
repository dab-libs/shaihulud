package ru.dab.shaihulud.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LineDetector {
  private final Pattern      detectionPattern;
  private final String       targetReplacement;
  private final StringBuffer buffer = new StringBuffer();

  public LineDetector(@NotNull String detectionRegex,
                      @NotNull String targetReplacement) {
    detectionPattern = Pattern.compile(".*" + detectionRegex, Pattern.DOTALL);
    this.targetReplacement = targetReplacement;
  }

  public void reset() {
    buffer.delete(0, buffer.length());
  }

  public List<String> detect(char[] cbuf, int off, int len) {
    List<String> detected = new ArrayList<>();
    int length = off + len;
    for (int i = off; i < length; i++) {
      buffer.append(cbuf[i]);
      String e = detectInBuffer();
      if (e != null) {
        detected.add(e);
        buffer.delete(0, buffer.length());
      }
    }
    return detected;
  }

  private @Nullable String detectInBuffer() {
    String string = buffer.toString();
    Matcher matcher = detectionPattern.matcher(string);
    if (matcher.find()) {
      return matcher.replaceAll(targetReplacement);
    }
    return null;
  }
}
