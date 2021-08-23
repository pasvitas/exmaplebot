package ru.pasvitas.discordbots.examplebot.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.pasvitas.discordbots.examplebot.repository.mapper.JokesMapper;

import static org.junit.jupiter.api.Assertions.*;

class JokesRepositoryImplTest {

    private final JokesMapper jokesMapper = Mockito.mock(JokesMapper.class);

    private final JokesRepositoryImpl jokesRepository = new JokesRepositoryImpl(jokesMapper);

    @BeforeEach
    void setUp() {
        Mockito.when(jokesMapper.getRandomJoke()).thenReturn(null).thenReturn("Joke");
    }

    @DisplayName("Test get random joke")
    @Test
    void getRandomJoke() {
        assertNull(jokesRepository.getRandomJoke());
        assertEquals("Joke", jokesRepository.getRandomJoke());
    }
}