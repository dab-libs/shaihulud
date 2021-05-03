package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.Shaihulud;
import ru.dab.shaihulud.ShaihuludOptions;
import ru.dab.shaihulud.generator.GeneratorFactory;
import ru.dab.shaihulud.io.ReaderFactory;
import ru.dab.shaihulud.io.ResultStoreFactory;
import ru.dab.shaihulud.specification.ParserFactory;

public class Program {
  public static void main(String[] args) {
    ShaihuludOptions options = createOptions(args);
    if (options == null) {
      return;
    }

    try {
      Shaihulud shaihulud = new Shaihulud(
          new GeneratorFactory(), new ReaderFactory(), new ParserFactory(),
          new ResultStoreFactory());
      shaihulud.transform(options);
    }
    catch (Throwable e) {
      System.err.println(e.getMessage());
    }
  }

  private static @Nullable ShaihuludOptions createOptions(String[] args) {
    ProgramOptionsFactory optionsFactory = new ProgramOptionsFactory();
    try {
      return optionsFactory.create(args);
    }
    catch (NeedHelpException e) {
      optionsFactory.printHelp();
    }
    catch (WrongOptionsException e) {
      System.err.println(e.getMessage());
      optionsFactory.printHelp();
    }
    return null;
  }
}
