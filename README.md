[Русская версия](README_ru.md)

# Code generator, based on JSON and YAML 

shaihulud is a tool written in Java designed to generate code (or text) from JSON or YAML data according to a set of templates. shaihulud generates a code in the following order:
1. Parses the given json (yaml) file, obtaining a tree data structure (below: *original data*).
1. Validates the *original data* against the specified [*json schema*](https://json-schema.org/).
   * If a *json schema* is not specified, the step is skipped.
1. Sets default values in the *orginal data* tree according to the specified *json schema*.
   * If a *json-schema* is not specified, the step is skipped.
1. Executes a query on the *orginal data* in the [JEMSpath language](https://jmespath.org/), obtaining the *transformed data*.
   * If the file with the JEMSpath query is not specified, then the *original data* is taken as the *transformed data*.
1. Creates one or more text files in the specified folder using the [Pebble template engine](https://pebbletemplates.io/), to which it uses the *transformed data* and a bundle of TWIG templates.


# Usage

shaihulud is distributed as a jar file. It is launched in the command line as follows:

`$ java -jar shaihulud.jar [-c <PATH>] (-j <PATH> | -y <PATH>) [-s <PATH>] -r <DIR> -t <NAME> [-out <DIR>]`

Параматры командной строки представлены в таблице:

Параметр | Назначение
---------|----------------------------------------
-h,--help | Отображает справку по запуску
-c,--config <PATH> | Использует <PATH> как путь для чтения кофигурационных данных из JSON-файла
-j,--json <PATH> | Использует <PATH> как путь для чтения данных из JSON-файла
-y,--yaml <PATH> | Использует <PATH> как путь для чтения данных из YAML-файла
-s,--schema <PATH> | Использует <PATH> как путь для чтения JSON-схему из файла
-t,--transform <PATH> | Использует <PATH> как путь для чтения запроса на языке [JEMSpath](https://jmespath.org/)
-r,--root <DIR> | Использует <DIR> как папку из которой будут читаться файлы шаблонов [Twig](https://pebbletemplates.io/)
-m,--main <NAME> | Использует <NAME> как название главного файла шаблонов [Twig](https://pebbletemplates.io/)
-o,--out <DIR> | Использует <DIR> как папку, куда будут записаны сгенерированные текстовые файлы
