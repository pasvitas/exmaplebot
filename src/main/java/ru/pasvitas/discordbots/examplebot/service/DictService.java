package ru.pasvitas.discordbots.examplebot.service;

import ru.pasvitas.discordbots.examplebot.restapi.responsemodel.DictResponse;

import java.util.List;

/**
 * Инерфейс сервиса со словарем
 */
public interface DictService {

    String getDescription(String word);
    String getWord(String desc);

    boolean addWord(String word, String description);
    boolean deleteWord(String word);
    boolean updateDescription(String word, String description);

    List<DictResponse> getAll();
}
