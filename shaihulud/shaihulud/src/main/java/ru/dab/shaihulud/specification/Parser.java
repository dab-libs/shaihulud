package ru.dab.shaihulud.specification;

import java.io.InputStream;
import java.util.Map;

public interface Parser {
  Map<String, Object> parse(InputStream specification, InputStream schema);
}
