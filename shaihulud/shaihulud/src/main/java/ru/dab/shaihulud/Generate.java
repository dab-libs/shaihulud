package ru.dab.shaihulud;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import ru.dab.shaihulud.generator.CommandLineGeneratorOptionsBuilder;
import ru.dab.shaihulud.generator.CommandLineGeneratorOptionsBuilder.NeedHelpException;
import ru.dab.shaihulud.generator.CommandLineGeneratorOptionsBuilder.WrongOptionsException;
import ru.dab.shaihulud.generator.GeneratorOptions;
import ru.dab.shaihulud.specification.JsonParser;
import ru.dab.shaihulud.specification.ParserException;

import java.io.*;
import java.util.Map;

public class Generate {

  public static final String PROGRAM_NAME = "shaihulud";

  public static void main(String[] args) {
    try {
      GeneratorOptions options = CommandLineGeneratorOptionsBuilder.build(args);
      String jsonSpecificationPath = options.getJsonSpecification();
      String schemaPath = options.getSchema();
      InputStream schemaStream = null;
      if (schemaPath != null) {
        schemaStream = new FileInputStream(schemaPath);
      }
      JsonParser parser;
      InputStream specificationStream;
      if (jsonSpecificationPath != null) {
        specificationStream = new FileInputStream(jsonSpecificationPath);
        parser = new JsonParser();
      }
      else {
        throw new RuntimeException();
      }
      Map<String, Object> specificationMap =
          parser.parse(specificationStream, schemaStream);

      Writer writer = new OutputStreamWriter(System.out);
      MustacheFactory mf = new DefaultMustacheFactory();
      Mustache mustache = mf.compile(new StringReader("{{name}}, {{feature.description}}!"), "example");
      mustache.execute(writer, specificationMap);
      writer.flush();
    }
    catch (WrongOptionsException e) {
      System.err.println(e.getMessage());
      CommandLineGeneratorOptionsBuilder.printHelp();
    }
    catch (NeedHelpException e) {
      CommandLineGeneratorOptionsBuilder.printHelp();
    }
    catch (FileNotFoundException e) {
      System.err.println(e.getMessage());
    }
    catch (ParserException e) {
      System.err.println(e.getMessage());
    }
    catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
