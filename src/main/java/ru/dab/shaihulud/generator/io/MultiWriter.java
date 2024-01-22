package ru.dab.shaihulud.generator.io;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.utils.LineDetector;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class MultiWriter extends Writer {
  private final          Object                    monitor       = new Object();
  private final @NotNull Writer                    systemOut     =
      new OutputStreamWriter(System.out);
  private final          List<MultiWriterListener> listeners     =
      new ArrayList<>();
  private final          LineDetector              writeDetector =
      new LineDetector("<--Write\\s*\"([\\w\\.]+)\"\\s*-->", "$1");

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
      writeDetector.reset();
      ignoreFirstEmptyLine = true;
    }
  }

  public @NotNull Writer createSystemOutWriter() {
    return systemOut;
  }

  public void subscribe(MultiWriterListener listener) {
    synchronized (monitor) {
      listeners.add(listener);
    }
  }

  public void unsubscribe(MultiWriterListener listener) {
    synchronized (monitor) {
      listeners.remove(listener);
    }
  }

  @Override
  public void write(char @NotNull [] cbuf, int off, int len)
      throws IOException {
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
      detectAndNotifyEvents(cbuf, off, len);
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

  private void detectAndNotifyEvents(char[] cbuf, int off, int len)
      throws IOException {
    List<String> files = writeDetector.detect(cbuf, off, len);
    for (String file : files) {
      for (MultiWriterListener listener : listeners) {
        listener.fileChosen(file);
      }
    }
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
