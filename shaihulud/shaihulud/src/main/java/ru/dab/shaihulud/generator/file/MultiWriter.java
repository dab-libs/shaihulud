package ru.dab.shaihulud.generator.file;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

class MultiWriter extends Writer {
  private final Object monitor = new Object();

  private @NotNull Writer  writer;
  private          boolean ignoreFirstEmptyLine = false;

  public MultiWriter() {
    writer = createSystemOutWriter();
  }

  public void setWriter(@NotNull Writer writer) throws IOException {
    synchronized (monitor) {
      this.writer.flush();
      this.writer.close();

      this.writer = writer;
      ignoreFirstEmptyLine = true;
    }
  }

  public @NotNull Writer createSystemOutWriter() {
    return new OutputStreamWriter(System.out);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    synchronized (monitor) {
      if (ignoreFirstEmptyLine) {
        ignoreFirstEmptyLine = false;
        if (len >= 2 && cbuf[off] == '\r' && cbuf[off + 1] == '\n') {
          off += 2;
          len -= 2;
        }
        else if (len >= 1 && cbuf[off] == '\n') {
          off += 1;
          len -= 1;
        }
        if (len == 0) {
          return;
        }
      }
      writer.write(cbuf, off, len);
    }
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
