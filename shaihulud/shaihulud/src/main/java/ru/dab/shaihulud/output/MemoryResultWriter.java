package ru.dab.shaihulud.output;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Writer;

public class MemoryResultWriter extends Writer {
  private StringBuffer buf = new StringBuffer();
  @Override
  public void write(@NotNull char[] cbuf, int off, int len) throws IOException {
    buf.append(cbuf);
  }

  @Override
  public void flush() throws IOException {
    System.out.print(buf.toString());
  }

  @Override
  public void close() throws IOException {
    flush();
  }
}
