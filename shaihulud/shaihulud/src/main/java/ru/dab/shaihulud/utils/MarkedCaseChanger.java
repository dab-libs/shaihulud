package ru.dab.shaihulud.utils;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MarkedCaseChanger {
  private boolean prevInWord = false;

  public static @NotNull String changeCase(@NotNull String str) {
    return new MarkedCaseChanger().changeMarkedCaseInner(str);
  }

  private final List<Changer> changers = new LinkedList<>();
  private final StringBuilder result   = new StringBuilder();
  boolean marker = false;

  private MarkedCaseChanger() {
  }

  private @NotNull String changeMarkedCaseInner(@NotNull String str) {
    boolean prevLexeme = true;
    for (int i = 0; i < str.length(); i++) {
      char ch = str.charAt(i);
      if (handleLexeme(ch)) {
        if (!prevLexeme) {
          applyChangers(' ');
        }
        prevLexeme = true;
      }
      else {
        result.append(applyChangers(ch));
        prevLexeme = false;
      }
    }

    return result.toString();
  }

  private boolean handleLexeme(char ch) {
    if (marker) {
      marker = false;
      switch (ch) {
        case 'l': {
          changers.add(0, this::changeLowerCaseLetter);
          return true;
        }
        case 'L': {
          changers.add(0, this::changeLowerCaseWord);
          return true;
        }
        case 'u': {
          changers.add(0, this::changeUpperCaseLetter);
          return true;
        }
        case 'U': {
          changers.add(0, this::changeUpperCaseWord);
          return true;
        }
        case '\\': {
          return false;
        }
        default: {
          result.append(ch);
          return true;
        }
      }
    }
    if (ch == '\\') {
      marker = true;
      return true;
    }
    return false;
  }

  private char applyChangers(char ch) {
    Iterator<Changer> iterator = changers.iterator();
    while (iterator.hasNext()) {
      ChangingResult changingResult = iterator.next().change(ch);
      ch = changingResult.result;
      if (changingResult.deleteMe) {
        iterator.remove();
      }
    }
    return ch;
  }

  private @NotNull ChangingResult changeLowerCaseLetter(char ch) {
    return new ChangingResult(Character.toLowerCase(ch), true);
  }

  private @NotNull ChangingResult changeLowerCaseWord(char ch) {
    return new ChangingResult(Character.toLowerCase(ch), !isWordPart(ch));
  }

  private @NotNull ChangingResult changeUpperCaseLetter(char ch) {
    return new ChangingResult(Character.toUpperCase(ch), true);
  }

  private @NotNull ChangingResult changeUpperCaseWord(char ch) {
    return new ChangingResult(Character.toUpperCase(ch), !isWordPart(ch));
  }

  private boolean isWordPart(char ch) {
    return Character.isLetterOrDigit(ch) ||
           Character.isAlphabetic(ch) || ch == '_';
  }

  private interface Changer {
    ChangingResult change(char ch);
  }

  private static class ChangingResult {
    public final char    result;
    public final boolean deleteMe;

    public ChangingResult(char result, boolean deleteMe) {
      this.result = result;
      this.deleteMe = deleteMe;
    }
  }
}
