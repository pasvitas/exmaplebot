package ru.pasvitas.discordbots.examplebot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.pasvitas.discordbots.examplebot.repository.mapper.JokesMapper;

/**
 * Промежуточное звено между SQL и логикой
 */
@RequiredArgsConstructor //Втихаря делает конструктор со всеми параметрами, помеченными как Final
@Repository //Spring-магия.
public class JokesRepositoryImpl implements JokeRepository {

    private final JokesMapper jokesMapper; // Компонент для взаимодействия с БД через SQL

    /**
     * Возврат рандомной шутки из БД
     * @return Рандомная шутка
     */
    @Override
    public String getRandomJoke() {
        return jokesMapper.getRandomJoke();
    }
}
