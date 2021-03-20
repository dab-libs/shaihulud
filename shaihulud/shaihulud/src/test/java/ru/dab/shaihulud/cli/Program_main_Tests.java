package ru.dab.shaihulud.cli;

import org.junit.jupiter.api.Test;

public class Program_main_Tests {
  @Test
  void generateFromJson() {
    Program.main(new String[]{
        "-j", "src/test/resources/swagger-default.json",
        "-s", "src/test/resources/swagger-2.0-schema.json",
        "-r", "src/test/resources",
        "-t", "default",
        "-o", "out"
    });
  }

  @Test
  void generateFromYaml() {
    Program.main(new String[]{
        "-y", "src/test/resources/swagger-default.yaml",
        "-s", "src/test/resources/swagger-2.0-schema.json",
        "-r", "src/test/resources",
        "-t", "default",
        "-o", "out"
    });
  }
}
