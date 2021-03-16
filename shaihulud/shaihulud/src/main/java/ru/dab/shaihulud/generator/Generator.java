package ru.dab.shaihulud.generator;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.specification.*;

import java.io.*;
import java.util.Map;

class Generator {
  private final @NotNull GeneratorOptions options;

  public Generator(@NotNull GeneratorOptions options) {
    this.options = options;
  }

  public void generate() {
    try (
        InputStream schemaStream = createSchemaStream();
        InputStream specificationStream = createSpecStream();
        StringReader templateReader = createTemplateReader();
        Writer writer = createWriter()
    ) {

      Parser parser = ParserFactory.create(options.getSpecificationFormat());
      Map<String, Object> specificationMap =
          parser.parse(specificationStream, schemaStream);

      MustacheFactory mf = new DefaultMustacheFactory();

      Mustache mustache = mf.compile(templateReader, "main");
      mustache.execute(writer, specificationMap);
      writer.flush();
    }
    catch (ParserException | IOException e) {
      System.err.println(e.getMessage());
    }
  }

  @NotNull
  private StringReader createTemplateReader() {
    return new StringReader(options.getTemplate());
  }

  private @NotNull OutputStreamWriter createWriter() {
    return new OutputStreamWriter(System.out);
  }

  private @NotNull InputStream createSpecStream()
      throws FileNotFoundException {
    String specificationPath = options.getSpecification();
    return new FileInputStream(specificationPath);
  }

  private @Nullable InputStream createSchemaStream()
      throws FileNotFoundException {
    InputStream schemaStream = null;
    String schemaPath = options.getSchema();
    if (schemaPath != null) {
      schemaStream = new FileInputStream(schemaPath);
    }
    return schemaStream;
  }

}
