package ru.pasvitas.discordbots.examplebot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.pasvitas.discordbots.examplebot.repository.JokeRepository;

import static org.junit.jupiter.api.Assertions.*;

class JokeServiceImplTest {

    private final JokeRepository jokeRepository = Mockito.mock(JokeRepository.class);

    private final JokeServiceImpl jokeService = new JokeServiceImpl(jokeRepository);

    @BeforeEach
    void setUp() {
        Mockito.when(jokeRepository.getRandomJoke()).thenReturn(null).thenReturn("Joke");
    }

    @DisplayName("Test get random joke")
    @Test
    void getRandomJoke() {
        assertNull(jokeService.getRandomJoke());
        assertEquals("Joke", jokeService.getRandomJoke());
    }
}