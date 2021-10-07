package ru.pasvitas.discordbots.examplebot.service;

import ru.pasvitas.discordbots.examplebot.restapi.responsemodel.DictResponse;

import java.util.List;

/**
 * Инерфейс сервиса со словарем
 */
public interface DictService {

    String getDescription(String word);
    String getWord(String desc);

    void addWord(String word, String description);
    void deleteWord(String word);
    void updateDescription(String word, String description);

    List<DictResponse> getAll();
}
