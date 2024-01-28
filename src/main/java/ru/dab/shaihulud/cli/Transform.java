package ru.dab.shaihulud.cli;

import org.json.JSONObject;
import ru.dab.shaihulud.Console;
import ru.dab.shaihulud.io.ReaderFactory;
import ru.dab.shaihulud.specification.JsonParser;
import ru.dab.shaihulud.specification.Parser;
import ru.dab.shaihulud.specification.ParserException;
import ru.dab.shaihulud.transfomer.Transformer;
import ru.dab.shaihulud.transfomer.jmespath.JmesPathTransformer;

import java.io.IOException;
import java.util.Map;

public class Transform {
  public static void main(String[] args) throws IOException, ParserException {
    Parser parser = new JsonParser();
    final ReaderFactory readerFactory = new ReaderFactory();
    final Map<String, Object> data =
        parser.parse(readerFactory.create(args[1]));
    final Transformer transformer =
        new JmesPathTransformer(new Console());
    final Object transformedData =
        transformer.transform(readerFactory.create(args[0]), data);

    String jsonString;
    if (transformedData instanceof Map) {
      jsonString = new JSONObject((Map<?, ?>) transformedData).toString(2);
    }
    else if (transformedData instanceof String) {
      jsonString = "\"" + transformedData + "\"";
    }
    else {
      jsonString = transformedData.toString();
    }
    System.out.println(jsonString);
  }
}
