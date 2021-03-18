package ru.dab.shaihulud.cli;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.dab.shaihulud.specification.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class SpecificationParserFactory_Tests {
  @Test
  public void create_json() {
    SpecificationParserOptions
        options = Mockito.mock(SpecificationParserOptions.class);
    Mockito.when(options.getSpecificationParserType())
           .thenReturn(SpecificationParserType.Json);
    InputStream stream = Mockito.mock(InputStream.class);
    SpecificationParser parser = 
        new SpecificationParserFactory(options).create(stream);
      assertInstanceOf(JsonSpecificationParser.class, parser);
  }
  
  @Test
  public void create_yaml() {
    SpecificationParserOptions
        options = Mockito.mock(SpecificationParserOptions.class);
    Mockito.when(options.getSpecificationParserType())
           .thenReturn(SpecificationParserType.Yaml);
    InputStream stream = Mockito.mock(InputStream.class);
    SpecificationParser parser = 
        new SpecificationParserFactory(options).create(stream);
      assertInstanceOf(YamlSpecificationParser.class, parser);
  }
}
