package ru.dab.shaihulud.transfomer;

import ru.dab.shaihulud.Console;
import ru.dab.shaihulud.transfomer.jmespath.JmesPathTransformer;

public class TransformerFactory {
  public Transformer create(Console console) {
    return new JmesPathTransformer(console);
  }
}
