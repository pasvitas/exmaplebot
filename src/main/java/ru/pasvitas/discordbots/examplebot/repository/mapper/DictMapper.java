package ru.pasvitas.discordbots.examplebot.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

/**
 * SQL-Запросы к таблице словаря
 */
@Component
@Mapper
public interface DictMapper {
    /**
     * Запрос определения слова
     * @return Определение в стринге из БД
     */
    @Select("SELECT description FROM dict WHERE word = #{word}") //Сам запрос
    String getDescription(String word);

    /**
     * Запрос слова по определению
     * @return Шутку в стринге из БД
     */
    @Select("SELECT word FROM dict WHERE description = #{desc}") //Сам запрос
    String getWord(String desc);
}
