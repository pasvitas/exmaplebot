package ru.pasvitas.discordbots.examplebot.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.pasvitas.discordbots.examplebot.repository.mapper.DictMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class DictRepositoryImplTest {

    private final DictMapper dictMapper = Mockito.mock(DictMapper.class);

    private final DictRepositoryImpl dictRepository = new DictRepositoryImpl(dictMapper);

    @BeforeEach
    void setUp() {
        Mockito.when(dictMapper.getDescription("хер")).thenReturn("desc");
        Mockito.when(dictMapper.getDescription("NaW")).thenReturn(null);
        Mockito.when(dictMapper.getDescription(null)).thenReturn(null);

        Mockito.when(dictMapper.getWord("петушарня")).thenReturn("word");
        Mockito.when(dictMapper.getWord("NaD")).thenReturn(null);
        Mockito.when(dictMapper.getWord(null)).thenReturn(null);
    }

    @DisplayName("Test get existing word description")
    @Test
    void getExistingDescription() {
        assertEquals("desc", dictRepository.getDescription("хер"));
    }

    @DisplayName("Test get non existing word description")
    @Test
    void getNonExistingDescription() {
        assertNull(dictRepository.getDescription("NaW"));
    }

    @DisplayName("Test get null word description")
    @Test
    void getNullDescription() {
        assertNull(dictRepository.getDescription(null));
    }



    @DisplayName("Test get existing word description")
    @Test
    void getExistingWord() {
        assertEquals("word", dictRepository.getWord("петушарня"));
    }

    @DisplayName("Test get non existing word description")
    @Test
    void getNonExistingWord() {
        assertNull(dictRepository.getWord("NaD"));
    }

    @DisplayName("Test get null word description")
    @Test
    void getNullWord() {
        assertNull(dictRepository.getWord(null));
    }
}