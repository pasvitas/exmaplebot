package ru.pasvitas.discordbots.examplebot.repository;

public interface DictRepository {
    String getDescription(String word);
    String getWord(String desc);
}
