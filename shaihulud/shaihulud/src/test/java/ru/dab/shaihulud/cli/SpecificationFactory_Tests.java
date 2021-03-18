package ru.dab.shaihulud.cli;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.dab.shaihulud.generator.GeneratorFactory;
import ru.dab.shaihulud.specification.SchemaFactory;
import ru.dab.shaihulud.specification.SpecificationFactory;
import ru.dab.shaihulud.specification.SpecificationOptions;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class SpecificationFactory_Tests {
  @Test
  public void create_stream() {
    SpecificationOptions options = Mockito.mock(SpecificationOptions.class);
    Mockito.when(options.getSpecification())
           .thenReturn("src/test/resources/swagger-default.json");
    try (InputStream schema = new SpecificationFactory(options).create()) {
      assertNotNull(schema);
    }
    catch (IOException e) {
      fail();
    }
  }

  @Test
  public void createSpecification_notFound() {
    SpecificationOptions options = Mockito.mock(SpecificationOptions.class);
    Mockito.when(options.getSpecification())
           .thenReturn("src/test/resources/not-found.json");
    try (InputStream schema = new SpecificationFactory(options).create()) {
      fail();
    }
    catch (IOException e) {
      assertInstanceOf(FileNotFoundException.class, e);
    }
  }
}
