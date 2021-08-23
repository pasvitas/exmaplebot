# EXAMPLE-BOT

> Небольшой бот для примера


## Стек:

* Java 16
* Spring Boot 2.5.4
* MyBatis
* Liquibase
* Lombok
* DVA/JDA

## Фичи(дискорда)

* Команды серверные - через префикс
* Команды приватные в лс - без префикса
* Анекдоты

## Фичи(архитектурные):

* Одна команда - один класс. 
* Дерево Abstract Command - Abstract Guild Command/Abstract Private Command - Конкретные команды
* Автоматическая регистрация команд
* Clean Architecture: Repository - Service - Commands
* Unit-тесты (WIP)
* Yaml-настройки: префикс, токен, активность, БД
* Liquibase-проливка БД
* DVA/JDA (https://github.com/DV8FromTheWorld/JDA) для взаимодействия с дискордом
