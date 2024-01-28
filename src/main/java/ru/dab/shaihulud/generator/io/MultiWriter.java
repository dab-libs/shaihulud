package ru.dab.shaihulud.generator.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Objects;

class MultiWriter extends Writer {
  private final Object monitor   = new Object();
  private final Writer systemOut = new OutputStreamWriter(System.out);

  private           boolean ignoreFirstEmptyLine = false;
  private @Nullable Writer  writer;

  public MultiWriter() {
    super();
    writer = systemOut;
  }

  public void setWriter(@NotNull Writer writer) throws IOException {
    synchronized (monitor) {
      closeWriter();
      this.writer = writer;
      ignoreFirstEmptyLine = true;
    }
  }

  public @NotNull Writer createSystemOutWriter() {
    return systemOut;
  }

  @Override
  public void write(char @NotNull [] cbuf, int off, int len)
      throws IOException {
    if (len <= 0) {
      return;
    }
    checkWriter();
    synchronized (monitor) {
      if (ignoreFirstEmptyLine) {
        ignoreFirstEmptyLine = false;
        int lineTerminatorLength = checkLineTerminator(cbuf, off, len);
        if (lineTerminatorLength > 0) {
          off += lineTerminatorLength;
          len -= lineTerminatorLength;
        }
        if (len <= 0) {
          return;
        }
      }
      checkWriter();
      Objects.requireNonNull(writer).write(cbuf, off, len);
    }
  }

  private int checkLineTerminator(char[] cbuf, int off, int len) {
    if (len >= 2 && cbuf[off] == '\r' && cbuf[off + 1] == '\n') {
      return 2;
    }
    else if (len >= 1 && cbuf[off] == '\n') {
      return 1;
    }
    return 0;
  }

  @Override
  public void flush() throws IOException {
    checkWriter();
    synchronized (monitor) {
      checkWriter();
      Objects.requireNonNull(writer).flush();
    }
  }

  private void checkWriter() throws IOException {
    if (writer == null) {
      throw new IOException("Write is closed");
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

    if (writer == systemOut) {
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
