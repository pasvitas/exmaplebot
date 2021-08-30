package ru.pasvitas.discordbots.examplebot.service;

/**
 * Инерфейс сервиса со словарем
 */
public interface DictService {
    String getDescription(String word);
    String getWord(String desc);
}
