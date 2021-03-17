package ru.dab.shaihulud.generator;

import org.junit.jupiter.api.Test;
import ru.dab.shaihulud.specification.SpecificationFormat;

public class Generate_main_Tests {
  @Test
  void generate() {
    Generate.main(new String[]{
        "-json", "src/test/resources/swagger-default.json",
        "-sch", "src/test/resources/swagger-2.0-schema.json",
        "-t", "src/test/resources/default.mustache",
        "-out", "outDir"
    });
  }
}
