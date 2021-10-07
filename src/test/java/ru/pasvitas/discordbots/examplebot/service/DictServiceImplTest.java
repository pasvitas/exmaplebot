package ru.pasvitas.discordbots.examplebot.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.pasvitas.discordbots.examplebot.exceptions.InputDataException;
import ru.pasvitas.discordbots.examplebot.exceptions.WordNotExistsException;
import ru.pasvitas.discordbots.examplebot.repository.DictRepository;
import ru.pasvitas.discordbots.examplebot.repository.JokeRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DictServiceImplTest {

    private final DictRepository dictRepository = Mockito.mock(DictRepository.class);

    private final DictServiceImpl dictService = new DictServiceImpl(dictRepository);

    @BeforeEach
    void setUp() {
        Mockito.when(dictRepository.getDescription("хер")).thenReturn("desc");
        Mockito.when(dictRepository.getDescription("NaW")).thenReturn(null);
        Mockito.when(dictRepository.getDescription(null)).thenReturn(null);

        Mockito.when(dictRepository.getWord("петушарня")).thenReturn("word");
        Mockito.when(dictRepository.getWord("NaD")).thenReturn(null);
        Mockito.when(dictRepository.getWord(null)).thenReturn(null);
    }

    @DisplayName("Test get existing word description")
    @Test
    void getExistingDescription() {
        assertEquals("desc", dictService.getDescription("хер"));
    }

    @DisplayName("Test get non existing word description")
    @Test
    void getNonExistingDescription() {
        assertThrows(WordNotExistsException.class, () -> dictService.getDescription("NaW"));
    }

    @DisplayName("Test get null word description")
    @Test
    void getNullDescription() {
        assertThrows(InputDataException.class, () -> dictService.getDescription(null));
    }

    @DisplayName("Test get description of existing word")
    @Test
    void getExistingWord() {
        assertEquals("word", dictService.getWord("петушарня"));
    }

    @DisplayName("Test get description of non existing word")
    @Test
    void getNonExistingWord() {
        assertNull(dictService.getWord("NaD"));
    }

    @DisplayName("Test get description of null word")
    @Test
    void getNullWord() {
        assertNull(dictService.getWord(null));
    }
}