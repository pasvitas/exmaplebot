package ru.pasvitas.discordbots.examplebot.repository;

import ru.pasvitas.discordbots.examplebot.repository.model.DictModel;

import java.util.List;

public interface DictRepository {

    String getDescription(String word);
    String getWord(String description);

    void addWord(String word, String description);
    void deleteWord(String word);
    void updateDescription(String word, String description);

    List<DictModel> getAll();
}
