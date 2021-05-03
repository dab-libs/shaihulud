package ru.dab.shaihulud.transfomer;

import ru.dab.shaihulud.transfomer.jmespath.JmesPathTransformer;

public class TransformerFactory {
  public Transformer create() {
    return new JmesPathTransformer();
  }
}
