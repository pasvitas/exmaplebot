package ru.pasvitas.discordbots.examplebot.repository.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import ru.pasvitas.discordbots.examplebot.repository.model.DictModel;

import java.util.List;

/**
 * SQL-Запросы к таблице словаря
 */
@Component
@Mapper
public interface DictMapper {
    /**
     * Запрос определения слова
     */
    @Select("SELECT description FROM dict WHERE word = #{word}") //Сам запрос
    String getDescription(String word);

    /**
     * Запрос слова по определению
     */
    @Select("SELECT word FROM dict WHERE description = #{description}") //Сам запрос
    String getWord(String description);

    /**
     * Добавление слова с определением
     */
    @Insert("INSERT INTO dict(word, description) VALUES (#{word}, #{description})") //Сам запрос
    void insertWord(String word, String description);

    /**
     * Удаление слова
     */
    @Delete("DELETE FROM dict WHERE word = #{word}") //Сам запрос
    void deleteWord(String word);

    /**
     * Запрос всех слов
     */
    @Select("SELECT * FROM dict ORDER BY id") //Сам запрос
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "word", column = "word"),
            @Result(property = "description", column = "description")
    })
    List<DictModel> getAll();

    /**
     * Добавление слова с определением
     */
    @Update("UPDATE dict SET description = #{description} WHERE word = #{word}") //Сам запрос
    void updateDescription(String word, String description);
}
