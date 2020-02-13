package ru.dab.shaihulud.generator;

import org.apache.commons.cli.*;

public class CommandLineGeneratorOptionsBuilder {
  public static final String HELP               = "help";
  public static final String YAML_SPECIFICATION = "yamlSpecification";
  public static final String JSON_SPECIFICATION = "jsonSpecification";
  public static final String SCHEMA             = "schema";
  public static final String TEMPLATE           = "template";
  public static final String OUT_DIRECTORY      = "outDirectory";

  public static GeneratorOptions build(String[] commandLineArguments)
      throws WrongOptionsException, NeedHelpException {
    if (commandLineArguments.length == 0) {
      throw new NeedHelpException();
    }

    Options options = createOptions();
    CommandLine commandLine = parseCommandLine(commandLineArguments, options);
    if (commandLine.hasOption(HELP)) {
      throw new NeedHelpException();
    }

    String yamlSpecification = commandLine.getOptionValue(YAML_SPECIFICATION);
    String jsonSpecification = commandLine.getOptionValue(JSON_SPECIFICATION);

    if (yamlSpecification == null && jsonSpecification == null) {
      throw new WrongOptionsException(
          "One of the options '" + YAML_SPECIFICATION + "' or '" +
          JSON_SPECIFICATION + "' is required");
    }

    return new GeneratorOptions(
        yamlSpecification, jsonSpecification,
        commandLine.getOptionValue(SCHEMA),
        commandLine.getOptionValue(TEMPLATE),
        commandLine.getOptionValue(OUT_DIRECTORY));
  }

  private static CommandLine parseCommandLine(String[] commandLineArguments,
                                              Options options)
      throws WrongOptionsException {
    CommandLine commandLine;
    try {
      CommandLineParser parser = new DefaultParser();
      commandLine = parser.parse(options, commandLineArguments);
    }
    catch (ParseException e) {
      throw new WrongOptionsException(e.getMessage(), e);
    }
    return commandLine;
  }

  public static void printHelp() {
    Options options = createOptions();
    HelpFormatter helpFormatter = new HelpFormatter();
    helpFormatter.printHelp(
        "java -jar shaihulud.jar (-json <PATH> | -yaml <PATH>) -s <PATH>" +
        " -t <PATH> -out <PATH>",
        options);
  }

  private static Options createOptions() {
    Options options = new Options();
    options.addOption(
        Option.builder("h")
              .longOpt(HELP)
              .desc("print this message.")
              .build());
    OptionGroup specificationOptions = new OptionGroup();
    specificationOptions.addOption(
        Option.builder("yaml")
              .longOpt(YAML_SPECIFICATION)
              .hasArg()
              .argName("PATH")
              .type(String.class)
              .desc("use a given PATH to read an YAML specification file")
              .build());
    specificationOptions.addOption(
        Option.builder("json")
              .longOpt(JSON_SPECIFICATION)
              .hasArg()
              .argName("PATH")
              .type(String.class)
              .desc("use a given PATH to read a JSON specification file")
              .build());
    options.addOptionGroup(specificationOptions);
    options.addOption(
        Option.builder("s")
              .longOpt(SCHEMA)
              .hasArg()
              .argName("PATH")
              .type(String.class)
              .desc("use a given PATH to read a JSON-schema file")
              .build());
    options.addOption(
        Option.builder("t")
              .longOpt(TEMPLATE)
              .hasArg()
              .argName("PATH")
              .type(String.class)
              .required()
              .desc("use a given PATH to read a template file")
              .build());
    options.addOption(
        Option.builder("out")
              .longOpt(OUT_DIRECTORY)
              .hasArg()
              .argName("PATH")
              .type(String.class)
              .desc("use a given PATH as a directory " +
                    "where generated files will be written.")
              .build());
    return options;
  }

  public static class WrongOptionsException extends Exception {
    public WrongOptionsException(String message) {
      this(message, null);
    }

    public WrongOptionsException(String message, Throwable e) {
      super(message, e);
    }
  }

  public static class NeedHelpException extends Exception {
  }
}
