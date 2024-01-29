[По-русски](README.md)

# Code generator, based on JSON and YAML

**shaihulud** is a tool designed to generate code (or text) from JSON or YAML data according to a set of templates. **shaihulud** generates a code in the following order:
1. Parses the given json (yaml) file, obtaining a tree data structure (below: *original data*).
1. Validates the *original data* against the specified [*json schema*](https://json-schema.org/).
   * If a *json schema* is not specified, the step is skipped.
1. Sets default values in the *orginal data* tree according to the specified *json schema*.
   * If a *json-schema* is not specified, the step is skipped.
1. Executes a query on the *orginal data* in the [JEMSpath language](https://jmespath.org/), obtaining the *transformed data*.
   * If the file with the JEMSpath query is not specified, then the *original data* is taken as the *transformed data*.
1. Creates one or more text files in the specified folder using the [Pebble template engine](https://pebbletemplates.io/), to which it uses the *transformed data* and a bundle of TWIG templates.


# Usage

**shaihulud** is distributed as a jar file. It is launched in the command line as follows:

`$ java -jar shaihulud.jar [-c <PATH>] (-j <PATH> | -y <PATH>) [-s <PATH>] -r <DIR> -t <NAME> [-out <DIR>]`

Command line parameters are introduced in the table:

Parameter | Description
---------|----------------------------------------
-h,--help | Prints a help
-j,--json `<PATH>` | Uses a given `<PATH>` as a path to read a JSON specification file
-y,--yaml `<PATH>` | Uses a given `<PATH>` as a path to read an YAML specification file
-s,--schema `<PATH>` | Uses a given `<PATH>` as a path to read a JSON-schema file
-t,--transform `<PATH>` | Uses a given `<PATH>` as a path to a transformation script in [JEMSpath](https://jmespath.org/)
-r,--root `<DIR>` | Uses a given `<DIR>` as a directory where template files will be read
-m,--main `<NAME>` | Uses `<NAME>` as the name of the main template file. If this file has the extension `.mustache`, then it is used with the [Mustache](https://github.com/spullara/mustache.java) template engine. If this file has a `.twig` extension, then it is used with the [Pebble](https://pebbletemplates.io/) template engine
-o,--out `<DIR>` | Uses a given `<DIR>` as a directory where generated files will be written
-c,--config `<PATH>` | Uses a given `<PATH>` as a path to read a JSON config file
