package ru.dab.shaihulud.generator;

import org.apache.commons.cli.*;

public class CommandLineGeneratorOptionsBuilder {

  public static final String HELP          = "help";
  public static final String YAML_TEMPLATE = "yamlTemplate";
  public static final String JSON_TEMPLATE = "jsonTemplate";
  public static final String JSON_SCHEMA   = "jsonSchema";
  public static final String OUT_DIRECTORY = "outDirectory";

  public static GeneratorOptions build(String[] commandLineArguments)
      throws WrongOptionsException, NeedHelpException {
    if (commandLineArguments.length == 0) {
      throw new NeedHelpException();
    }

    Options options = createOptions();
    CommandLineParser parser = new DefaultParser();
    try {
      CommandLine commandLine = parser.parse(options, commandLineArguments);
      if (commandLine.hasOption(HELP)) {
        throw new NeedHelpException();
      }

      String yamlTemplate = commandLine.getOptionValue(YAML_TEMPLATE);
      String jsonTemplate = commandLine.getOptionValue(JSON_TEMPLATE);
      String jsonSchema = commandLine.getOptionValue(JSON_SCHEMA);
      String outDirectory = commandLine.getOptionValue(OUT_DIRECTORY);

      if (yamlTemplate == null && jsonTemplate == null) {
        throw new WrongOptionsException(
            "One of the options '" + YAML_TEMPLATE + "' or '" + JSON_TEMPLATE +
            "' is required");
      }

      return new GeneratorOptions(yamlTemplate, jsonTemplate, jsonSchema,
                                  outDirectory);
    }
    catch (ParseException e) {
      throw new WrongOptionsException(e.getMessage(), e);
    }
  }

  public static void printHelp(String cmdLineSyntax) {
    Options options = createOptions();
    HelpFormatter helpFormatter = new HelpFormatter();
    helpFormatter.printHelp(cmdLineSyntax, options);
  }

  private static Options createOptions() {
    Options options = new Options();
    options.addOption(
        Option.builder("h")
              .longOpt(HELP)
              .desc("print this message.")
              .build());
    options.addOption(
        Option.builder("yaml")
              .longOpt(YAML_TEMPLATE)
              .hasArg()
              .argName("PATH")
              .type(String.class)
              .desc("use a given PATH to read an YAML template file")
              .build());
    options.addOption(
        Option.builder("json")
              .longOpt(JSON_TEMPLATE)
              .hasArg()
              .argName("PATH")
              .type(String.class)
              .desc("use a given PATH to read an JSON template file")
              .build());
    options.addOption(
        Option.builder("s")
              .longOpt(JSON_SCHEMA)
              .hasArg()
              .argName("PATH")
              .type(String.class)
              .desc("use a given PATH to read a JSON-schema file")
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
