package ru.pasvitas.discordbots.examplebot.repository.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;


/**
 * SQL-Запросы к таблице анекдотов
 */
@Component
@Mapper
public interface JokesMapper {

    /**
     * Запрос рандомной шутки
     * @return Шутку в стринге из БД
     */
    @Select("SELECT joke FROM jokes ORDER BY random() LIMIT 1") //Сам запрос
    String getRandomJoke();

    /**
     * Возвращает шутку по номеру
     * @param number Номер шутки
     * @return Шутка по номеру
     */
    @Select("SELECT joke FROM jokes WHERE id = #{number}")
    String getRandomJokeNumber(long number);
}
