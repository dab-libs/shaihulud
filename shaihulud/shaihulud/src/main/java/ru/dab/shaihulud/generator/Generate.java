package ru.dab.shaihulud.generator;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.specification.JsonParser;
import ru.dab.shaihulud.specification.ParserException;

import java.io.*;
import java.util.Map;

public class Generate {

  public static void main(String[] args) {
    GeneratorOptions options = createGeneratorOptions(args);
    if (options == null) {
      return;
    }

    try (InputStream schemaStream = createSchemaStream(options);
         InputStream specificationStream = createSpecStream(options)) {
      JsonParser parser = createParser(options);
      Map<String, Object> specificationMap =
          parser.parse(specificationStream, schemaStream);

      Writer writer = createWriter(options);
      MustacheFactory mf = new DefaultMustacheFactory();
      Mustache mustache = mf.compile(new StringReader(options.getTemplate()),
                                     "main");
      mustache.execute(writer, specificationMap);
      writer.flush();
    }
    catch (ParserException | IOException e) {
      System.err.println(e.getMessage());
    }
  }

  @NotNull
  private static OutputStreamWriter createWriter(
      @NotNull GeneratorOptions options) {
    return new OutputStreamWriter(System.out);
  }

  @Nullable
  private static GeneratorOptions createGeneratorOptions(String[] args) {
    GeneratorOptions options = null;
    try {
      options = GeneratorOptionsFactory.build(args);
    }
    catch (NeedHelpException e) {
      GeneratorOptionsFactory.printHelp();
    }
    catch (WrongOptionsException e) {
      System.err.println(e.getMessage());
      GeneratorOptionsFactory.printHelp();
    }
    return options;
  }

  @NotNull
  private static JsonParser createParser(GeneratorOptions options) {
    JsonParser parser;
    if (options.getSpecificationFormat() == SpecificationFormat.Json) {
      parser = new JsonParser();
    }
    else {
      throw new RuntimeException();
    }
    return parser;
  }

  @NotNull
  private static InputStream createSpecStream(GeneratorOptions options)
      throws FileNotFoundException {
    String specificationPath = options.getSpecification();
    return new FileInputStream(specificationPath);
  }

  @Nullable
  private static InputStream createSchemaStream(GeneratorOptions options)
      throws FileNotFoundException {
    InputStream schemaStream = null;
    String schemaPath = options.getSchema();
    if (schemaPath != null) {
      schemaStream = new FileInputStream(schemaPath);
    }
    return schemaStream;
  }
}
