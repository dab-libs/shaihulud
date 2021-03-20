package ru.dab.shaihulud.generator.file;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.Writer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MultiWriter_close_Tests {
  @Test
  public void testClose() throws IOException {
    Writer writer = mock(Writer.class);
    MultiWriter multiWriter = new MultiWriter(writer);
    multiWriter.close();

    verify(writer).close();
  }
}
