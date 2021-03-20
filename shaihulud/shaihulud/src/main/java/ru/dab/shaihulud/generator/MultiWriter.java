package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

class MultiWriter extends Writer {
  private volatile @NotNull Writer writer;

  public MultiWriter() {
    writer = createSystemOutWriter();
  }

  public @NotNull Writer getWriter() {
    return writer;
  }

  public void setWriter(@NotNull Writer writer) throws IOException {
    Writer oldWriter = this.writer;
    this.writer = writer;

    oldWriter.flush();
    oldWriter.close();
  }

  public @NotNull Writer createSystemOutWriter() {
    return new OutputStreamWriter(System.out);
  }

  @Override
  public void write(char[] cbuf, int off, int len)
      throws IOException {
    writer.write(cbuf, off, len);
  }

  @Override
  public void flush() throws IOException {
    writer.flush();
  }

  @Override
  public void close() throws IOException {
    writer.close();
  }
}
