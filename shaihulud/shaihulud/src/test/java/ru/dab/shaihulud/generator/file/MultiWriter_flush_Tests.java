package ru.dab.shaihulud.generator.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Writer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MultiWriter_flush_Tests {
  @Test
  public void testFlush() throws IOException {
    Writer writer = mock(Writer.class);
    MultiWriter multiWriter = new MultiWriter(writer);
    multiWriter.flush();

    verify(writer).flush();
  }

  @Test
  public void testClose() throws IOException {
    Writer writer = mock(Writer.class);
    MultiWriter multiWriter = new MultiWriter(writer);
    multiWriter.close();

    verify(writer).flush();
  }
}
