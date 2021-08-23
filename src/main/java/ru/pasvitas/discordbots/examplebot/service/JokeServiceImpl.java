package ru.pasvitas.discordbots.examplebot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pasvitas.discordbots.examplebot.repository.JokeRepository;

/**
 * По-хорошему, тут обычно пишутся все преобрзования или какая-либо логика обработки данных
 */
@RequiredArgsConstructor //Втихаря делает конструктор со всеми параметрами, помеченными как Final
@Service //Spring-Магия
public class JokeServiceImpl implements JokeService {

    private final JokeRepository jokeRepository; // Вставляет репозиторий благодаря Spring-магии

    /**
     * Возвращает рандомную шутку
     * @return Рандомная шутка
     */
    @Override
    public String getRandomJoke() {
        return jokeRepository.getRandomJoke();
    }
}
