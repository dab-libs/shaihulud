package ru.dab.shaihulud.generator;

import org.jetbrains.annotations.Nullable;

public class Generate {

  public static void main(String[] args) {
    GeneratorOptions options = createGeneratorOptions(args);
    if (options == null) {
      return;
    }
    new Generator(options).generate();
  }

  private static @Nullable GeneratorOptions createGeneratorOptions(
      String[] args) {
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
}
