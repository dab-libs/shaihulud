[English version](README_en.md)

# Преобразователь JSON или YAML

shaihulud - это инструмент, предназначенный для преобразования данных из формата JSON или YAML в один или несколько текстовых файлов в соответствии с набором шаблонов. shaihulud выполняет такое преобразование преобразование в следующем порядке:
1. Разбирает данный json (yaml) файл, получая древовидную структуру данных (далее: *исходные данные*).
1. Проверяет (валидирует) *исходные данные* на соответствие заданной [*json-схеме*](https://json-schema.org/).
   * Если *json-схема* не задана, то этап пропускается.
1. Устанавливает в дереве *исходных данных* значения по умолчанию в соответствии с заданной *json-схемой* (этап пропускается, если *json-схема* не задана).
   * Если *json-схема* не задана, то этап пропускается.
1. Выполняет на *исходных данных* запрос на языке [JEMSpath](https://jmespath.org/), получая *преобразованные данные*.
   * Если файл с запросом JEMSpath не задан, то в качестве *преобразованных данных* берет *исходные данные*.
1. Создает в заданной папке один или несколько текстовых файлов с помощью одного из шаблонизаторов: [Mustache](https://github.com/spullara/mustache.java) или [Pebble](https://pebbletemplates.io/), которому передает *преобразованные данные* и набор шаблонов.

# Запуск

shaihulud распрастраняется в виде jar-файла. Он запускается из комадной строки следующим образом:

`$ java -jar shaihulud.jar [-c <PATH>] (-j <PATH> | -y <PATH>) [-s <PATH>] -r <DIR> -t <NAME> [-out <DIR>]`

Параматры командной строки представлены в таблице:

Параметр | Назначение
---------|----------------------------------------
-h,--help | Отображает справку по запуску
-c,--config `<PATH>` | Использует `<PATH>` как путь для чтения кофигурационных данных из JSON-файла
-j,--json `<PATH>` | Использует `<PATH>` как путь для чтения данных из JSON-файла
-y,--yaml `<PATH>` | Использует `<PATH>` как путь для чтения данных из YAML-файла
-s,--schema `<PATH>` | Использует `<PATH>` как путь для чтения JSON-схему из файла
-t,--transform `<PATH>` | Использует `<PATH>` как путь для чтения запроса на языке [JEMSpath](https://jmespath.org/)
-r,--root `<DIR>` | Использует `<DIR>` как папку из которой будут читаться файлы шаблонов
-m,--main `<NAME>` | Использует `<NAME>` как название главного файла шаблонов. Если этот файл с расширением `.mustache`, то он используется с шаблонизатором [Mustache](https://github.com/spullara/mustache.java). Если этот файл с расширением `.twig`, то он используется с шаблонизатором [Pebble](https://pebbletemplates.io/)
-o,--out `<DIR>` | Использует `<DIR>` как папку, куда будут записаны текстовые файлы, полученные в результате работы программы
