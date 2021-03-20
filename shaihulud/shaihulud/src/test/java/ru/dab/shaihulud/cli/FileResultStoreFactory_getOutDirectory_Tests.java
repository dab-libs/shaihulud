package ru.dab.shaihulud.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

public class FileResultStoreFactory_getOutDirectory_Tests {
  @Test
  public void testRelativeDown() throws IOException {
    FileResultStoreOptions options = Mockito.mock(FileResultStoreOptions.class);
    Mockito.when(options.getOutDirectory()).thenReturn("template.mustache");

    String root = new FileResultStoreFactory(options).getRoot();
    String expected = new File("template.mustache").getCanonicalPath();
    Assertions.assertEquals(expected, root);
  }

  @Test
  public void testRelativeUp() throws IOException {
    FileResultStoreOptions options = Mockito.mock(FileResultStoreOptions.class);
    Mockito.when(options.getOutDirectory()).thenReturn("../template.mustache");

    String root = new FileResultStoreFactory(options).getRoot();
    String expected = new File("../template.mustache").getCanonicalPath();
    Assertions.assertEquals(expected, root);
  }

  @Test
  public void testAbsolute() throws IOException {
    FileResultStoreOptions options = Mockito.mock(FileResultStoreOptions.class);
    Mockito.when(options.getOutDirectory()).thenReturn("/dir/template.mustache");

    String root = new FileResultStoreFactory(options).getRoot();
    String expected = new File("/dir/template.mustache").getCanonicalPath();
    Assertions.assertEquals(expected, root);
  }

  @Test
  public void testNull() throws IOException {
    FileResultStoreOptions options = Mockito.mock(FileResultStoreOptions.class);
    Mockito.when(options.getOutDirectory()).thenReturn(null);

    String root = new FileResultStoreFactory(options).getRoot();
    String expected = new File("").getCanonicalPath();
    Assertions.assertEquals(expected, root);
  }
}
