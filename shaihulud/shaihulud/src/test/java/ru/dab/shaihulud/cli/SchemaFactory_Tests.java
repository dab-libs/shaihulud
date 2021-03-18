package ru.dab.shaihulud.cli;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.dab.shaihulud.generator.GeneratorFactory;
import ru.dab.shaihulud.specification.SchemaFactory;
import ru.dab.shaihulud.specification.SchemaOptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class SchemaFactory_Tests {
  @Test
  public void create_stream() {
    SchemaOptions options = Mockito.mock(SchemaOptions.class);
    Mockito.when(options.getSchema())
           .thenReturn("src/test/resources/swagger-2.0-schema.json");
    try (InputStream schema = new SchemaFactory(options).create()) {
      assertNotNull(schema);
    }
    catch (IOException e) {
      fail();
    }
  }

  @Test
  public void create_null() {
    SchemaOptions options = Mockito.mock(SchemaOptions.class);
    Mockito.when(options.getSchema()).thenReturn(null);
    try (InputStream schema = new SchemaFactory(options).create()) {
      assertNull(schema);
    }
    catch (IOException e) {
      fail();
    }
  }

  @Test
  public void create_notFound() {
    SchemaOptions options = Mockito.mock(SchemaOptions.class);
    Mockito.when(options.getSchema())
           .thenReturn("src/test/resources/not-found.json");
    try (InputStream schema = new SchemaFactory(options).create()) {
      fail();
    }
    catch (IOException e) {
      assertInstanceOf(FileNotFoundException.class, e);
    }
  }
}
