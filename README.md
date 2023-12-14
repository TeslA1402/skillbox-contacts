# Contacts

## Описание

Консольное приложение «Контакты»

## Команды

| Команда | Описание                                                                               |
|---------|----------------------------------------------------------------------------------------|
| ADD     | Добавить контакт в формате `Иванов Иван Иванович;+890999999;someEmail@example.example` |
| LIST    | Вывод списка контактов                                                                 |
| DELETE  | Удаление контакта по email                                                             |
| SAVE    | Сохранение в файл, по умолчанию сохранят в файл `./contacts.txt`                       |
| EXIT    | Выход из программы                                                                     |

## Настройки

Настройки находятся в `src/main/resources/application.properties`

| Настройка              | Описание                                                                                                                                                           | Значение по-умолчанию |
|------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------|
| spring.profiles.active | Профиль запуска приложения. При установке любого значения, отличного от `init` не будет произведена инициализация контактов из файла указанного в `path.file.init` | init                  |
| path.file.save         | Путь до файла, куда будут помещены контакты при вызове команды `SAVE`                                                                                              | ./contacts.txt        |
| path.file.init         | Путь до файла для инициализации контактов. По-умолчанию используется файл находящийся в ресурсах приложения                                                        |                       |

## Сборка

Сборка производится с помощью MAVEN

```shell
mvn install
```

## Запуск программы

```shell
java -jar .\contacts-1.0-SNAPSHOT.jar
```
