package ru.dab.shaihulud.generator.file;

import org.junit.jupiter.api.*;

import java.io.IOException;
import java.io.StringWriter;

public class MultiWriter_ignoreFirstEmptyLine_Tests {
  private static final String LINE_1 = "test line 1";
  private static final String LINE_2 = "test line 2";

  private MultiWriter  multiWriter;
  private StringWriter writer1;
  private StringWriter writer2;

  @BeforeEach
  public void setupBeforeEach() throws IOException {
    writer1 = new StringWriter();
    writer2 = new StringWriter();
    multiWriter = new MultiWriter();
    multiWriter.setWriter(writer1);
  }

  @AfterEach
  public void AfterEach() throws IOException {
    multiWriter.close();
  }

  @Test
  public void testRN() throws IOException {
    multiWriter.write(LINE_1);
    multiWriter.setWriter(writer2);
    multiWriter.write("\r\n");
    multiWriter.write(LINE_2);
    multiWriter.write("\r\n");
    multiWriter.write(LINE_2);

    Assertions.assertEquals(LINE_1, writer1.toString());
    Assertions.assertEquals(LINE_2 + "\r\n" + LINE_2, writer2.toString());
  }

  @Test
  public void testN() throws IOException {
    multiWriter.write(LINE_1);
    multiWriter.setWriter(writer2);
    multiWriter.write("\n");
    multiWriter.write(LINE_2);
    multiWriter.write("\n");
    multiWriter.write(LINE_2);

    Assertions.assertEquals(LINE_1, writer1.toString());
    Assertions.assertEquals(LINE_2 + "\n" + LINE_2, writer2.toString());
  }

  @Test
  public void testLine() throws IOException {
    multiWriter.write(LINE_1);
    multiWriter.setWriter(writer2);
    multiWriter.write(LINE_2);
    multiWriter.write("\n");
    multiWriter.write(LINE_2);

    Assertions.assertEquals(LINE_1, writer1.toString());
    Assertions.assertEquals(LINE_2 + "\n" + LINE_2, writer2.toString());
  }
}
