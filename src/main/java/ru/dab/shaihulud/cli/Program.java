package ru.dab.shaihulud.cli;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.dab.shaihulud.Shaihulud;
import ru.dab.shaihulud.ShaihuludOptions;
import ru.dab.shaihulud.generator.GeneratorFactory;
import ru.dab.shaihulud.generator.io.TemplateBundleFactory;
import ru.dab.shaihulud.io.ReaderFactory;
import ru.dab.shaihulud.generator.io.ResultStoreFactory;
import ru.dab.shaihulud.specification.ParserFactory;
import ru.dab.shaihulud.transfomer.TransformerFactory;

import java.io.*;
import java.util.Scanner;

public class Program {
  public static void main(String[] args) {
    String jarName = getJarName();
    String version = getVersion();

    ShaihuludOptions options = createOptions(args, jarName, version);
    if (options == null) {
      return;
    }
    if (!options.isQuiet()) {
      printGreeting(jarName, version);
    }

    try {
      Shaihulud shaihulud = new Shaihulud(
          new GeneratorFactory(), new ReaderFactory(), new ParserFactory(),
          new TransformerFactory(), new TemplateBundleFactory(),
          new ResultStoreFactory());
      shaihulud.transform(options);
    }
    catch (Throwable e) {
      System.err.println(e.getMessage());
    }
  }

  private static void printGreeting(String jarName, String version) {
    if (version != null) {
      System.out.println(jarName + " " + version);
    }
    else {
      System.out.println(jarName);
    }
    System.out.println();
  }

  @Nullable
  private static String getVersion() {
    ReaderFactory readerFactory = new ReaderFactory();
    try (
        Reader versionReader = readerFactory.create("version");
        Scanner versionScanner = new Scanner(versionReader).useDelimiter("\\Z")
    ) {
      return versionScanner.next();
    }
    catch (IOException e) {
      return Program.class.getPackage().getImplementationVersion();
    }
  }

  @NotNull
  private static String getJarName() {
    String jarPath = Program.class
        .getProtectionDomain()
        .getCodeSource()
        .getLocation()
        .getPath();
    return new File(jarPath).getName().replace(".jar", "");
  }

  private static @Nullable ShaihuludOptions createOptions(String[] args,
                                                          String jarName,
                                                          String version) {
    ProgramOptionsFactory optionsFactory = new ProgramOptionsFactory(jarName);
    try {
      return optionsFactory.create(args);
    }
    catch (NeedHelpException e) {
      printGreeting(jarName, version);
      optionsFactory.printHelp();
    }
    catch (WrongOptionsException e) {
      System.err.println(e.getMessage());
      optionsFactory.printHelp();
    }
    return null;
  }
}
