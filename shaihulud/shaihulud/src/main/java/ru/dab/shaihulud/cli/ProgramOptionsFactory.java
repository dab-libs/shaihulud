package ru.dab.shaihulud.cli;

import org.apache.commons.cli.*;
import org.jetbrains.annotations.NotNull;
import ru.dab.shaihulud.specification.SpecificationParserType;

class ProgramOptionsFactory {
  public static final String HELP   = "help";
  public static final String YAML   = "yaml";
  public static final String JSON   = "json";
  public static final String SCHEMA = "schema";
  public static final String ROOT   = "root";
  public static final String MAIN   = "main";
  public static final String OUT    = "out";

  public @NotNull ProgramOptions create(
      @NotNull String[] commandLineArguments)
      throws WrongOptionsException, NeedHelpException {
    if (commandLineArguments.length == 0) {
      throw new NeedHelpException();
    }

    Options options = createOptions();
    CommandLine commandLine = parseCommandLine(commandLineArguments, options);
    if (commandLine.hasOption(HELP)) {
      throw new NeedHelpException();
    }

    return new ProgramOptions(
        getSpecification(commandLine), getSpecificationFormat(commandLine),
        commandLine.getOptionValue(SCHEMA),
        commandLine.getOptionValue(ROOT),
        commandLine.getOptionValue(MAIN),
        commandLine.getOptionValue(OUT));
  }

  private @NotNull String getSpecification(
      @NotNull CommandLine commandLine)
      throws WrongOptionsException {
    String yamlSpecification = commandLine.getOptionValue(YAML);
    String jsonSpecification = commandLine.getOptionValue(JSON);

    if (yamlSpecification == null && jsonSpecification == null) {
      throw new WrongOptionsException(
          "One of the options '" + YAML + "' or '" +
          JSON + "' is required");
    }
    else if (yamlSpecification == null) {
      return jsonSpecification;
    }
    else {
      return yamlSpecification;
    }
  }

  private @NotNull SpecificationParserType getSpecificationFormat(
      @NotNull CommandLine commandLine)
      throws WrongOptionsException {
    String yamlSpecification = commandLine.getOptionValue(YAML);
    String jsonSpecification = commandLine.getOptionValue(JSON);

    if (yamlSpecification == null && jsonSpecification == null) {
      throw new WrongOptionsException(
          "One of the options '" + YAML + "' or '" +
          JSON + "' is required");
    }
    else if (yamlSpecification == null) {
      return SpecificationParserType.Json;
    }
    else {
      return SpecificationParserType.Yaml;
    }
  }

  private @NotNull CommandLine parseCommandLine(
      @NotNull String[] commandLineArguments, @NotNull Options options)
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

  public void printHelp() {
    Options options = createOptions();
    HelpFormatter helpFormatter = new HelpFormatter();
    helpFormatter.printHelp(
        "java -jar shaihulud.jar [-s <PATH>] (-j <PATH> | -y <PATH>)" +
        "          -r <DIR> -t <NAME> [-out <DIR>]",
        options);
  }

  private Options createOptions() {
    Options options = new Options();
    options.addOption(
        Option.builder("h")
              .longOpt(HELP)
              .desc("print this message.")
              .build());
    OptionGroup spec = new OptionGroup()
        .addOption(
            Option.builder("y")
                  .longOpt(YAML)
                  .hasArg()
                  .argName("PATH")
                  .type(String.class)
                  .desc(
                      "use a given PATH as a path to read an " +
                      "YAML specification file")
                  .build())
        .addOption(
            Option.builder("j")
                  .longOpt(JSON)
                  .hasArg()
                  .argName("PATH")
                  .type(String.class)
                  .desc(
                      "use a given PATH as a path to read a JSON" +
                      " specification file")
                  .build());
    spec.setRequired(true);
    options.addOptionGroup(spec);
    options.addOption(
        Option.builder("s")
              .longOpt(SCHEMA)
              .required()
              .hasArg()
              .argName("PATH")
              .type(String.class)
              .desc("use a given PATH as a path to read a JSON-schema file")
              .build());
    options.addOption(
        Option.builder("r")
              .longOpt(ROOT)
              .required()
              .hasArg()
              .argName("DIR")
              .type(String.class)
              .required()
              .desc("use a given DIR as a directory " +
                    "where template files will be read")
              .build());
    options.addOption(
        Option.builder("t")
              .longOpt(MAIN)
              .required()
              .hasArg()
              .argName("NAME")
              .type(String.class)
              .required()
              .desc("use a given NAME as a main template file name")
              .build());
    options.addOption(
        Option.builder("o")
              .longOpt(OUT)
              .required(false)
              .hasArg()
              .argName("DIR")
              .type(String.class)
              .desc("use a given DIR as a directory " +
                    "where generated files will be written")
              .build());
    return options;
  }

}
