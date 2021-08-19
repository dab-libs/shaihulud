package ru.dab.shaihulud.generator.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

class MultiWriter extends Writer {
  private final Object monitor = new Object();

  private @Nullable Writer  writer;
  private           boolean ignoreFirstEmptyLine = false;

  private final @NotNull OutputStreamWriter systemOutWriter =
      new OutputStreamWriter(System.out);

  public MultiWriter() {
    super();
    writer = systemOutWriter;
  }

  public MultiWriter(@NotNull Writer writer) {
    super();
    this.writer = writer;
  }

  public void setWriter(@NotNull Writer writer) throws IOException {
    synchronized (monitor) {
      closeWriter();
      this.writer = writer;
      ignoreFirstEmptyLine = true;
    }
  }

  public @NotNull Writer createSystemOutWriter() {
    return systemOutWriter;
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
      if (writer == null) {
        throw new IOException("Write is closed");
      }
      writer.write(cbuf, off, len);
    }
  }

  @Override
  public void flush() throws IOException {
    synchronized (monitor) {
      if (writer == null) {
        throw new IOException("Write is closed");
      }
      writer.flush();
    }
  }

  @Override
  public void close() throws IOException {
    synchronized (monitor) {
      closeWriter();
    }
  }

  private void closeWriter() throws IOException {
    if (writer == null) {
      return;
    }

    if (writer == systemOutWriter) {
      try {
        writer.flush();
      }
      finally {
        writer = null;
      }
    }
    else {
      try (Writer w = writer) {
        w.flush();
      }
      finally {
        writer = null;
      }
    }
  }
}
