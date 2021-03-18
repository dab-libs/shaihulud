package ru.dab.shaihulud.cli;

import org.junit.jupiter.api.Test;

public class Generate_main_Tests {
  @Test
  void generateFromJson() {
    Generate.main(new String[]{
        "-json", "src/test/resources/swagger-default.json",
        "-sch", "src/test/resources/swagger-2.0-schema.json",
        "-t", "src/test/resources/default.mustache",
        "-out", "outDir"
    });
  }

  @Test
  void generateFromYaml() {
    Generate.main(new String[]{
        "-yaml", "src/test/resources/swagger-default.yaml",
        "-sch", "src/test/resources/swagger-2.0-schema.json",
        "-t", "src/test/resources/default.mustache",
        "-out", "outDir"
    });
  }
}
