package ru.dab.shaihulud.generator.file;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import static org.mockito.Mockito.*;

public class MultiWriter_setWriter_Tests {
  @Test
  public void testPreviousWriterIsClosed() throws IOException {
    Writer prevWriter = Mockito.mock(Writer.class);
    MultiWriter multiWriter = new MultiWriter(prevWriter);
    multiWriter.setWriter(Mockito.mock(Writer.class));

    verify(prevWriter, times(1)).flush();
    verify(prevWriter, times(1)).close();
  }

  @Test
  public void testPreviousWriterIsNotWritten() throws IOException {
    Writer prevWriter = new StringWriter();
    MultiWriter multiWriter = new MultiWriter(prevWriter);
    String line1 = "test1";
    multiWriter.write(line1);

    Writer writer = new StringWriter();
    multiWriter.setWriter(writer);
    String line2 = "test2";
    multiWriter.write(line2);

    multiWriter.flush();

    Assertions.assertEquals(line1, prevWriter.toString());
    Assertions.assertEquals(line2, writer.toString());
  }
}
