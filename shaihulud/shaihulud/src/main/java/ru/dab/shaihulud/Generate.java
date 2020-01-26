package ru.dab.shaihulud;

import ru.dab.shaihulud.generator.CommandLineGeneratorOptionsBuilder;
import ru.dab.shaihulud.generator.GeneratorOptions;

public class Generate {

  public static final String PROGRAM_NAME = "shaihulud";

  public static void main(String[] args) {
    try {
      GeneratorOptions options = CommandLineGeneratorOptionsBuilder.build(args);
    }
    catch (CommandLineGeneratorOptionsBuilder.WrongOptionsException e) {
      System.err.println(e.getMessage());
      CommandLineGeneratorOptionsBuilder.printHelp(PROGRAM_NAME);
    }
    catch (CommandLineGeneratorOptionsBuilder.NeedHelpException e) {
      CommandLineGeneratorOptionsBuilder.printHelp(PROGRAM_NAME);
    }
  }
}
