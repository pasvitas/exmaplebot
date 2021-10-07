package ru.pasvitas.discordbots.examplebot.repository.mapper;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DictMapperTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private DictMapper dictMapper;

    @DisplayName("Get existing word description")
    @Test
    void getExistingDescription() {
        jdbcTemplate.execute("INSERT INTO dict(word, description) VALUES ('word1', 'desc1')");
        assertEquals("desc1", dictMapper.getDescription("word1"));
    }

    @DisplayName("Get non existing word description")
    @Test
    void getNonExistingDescription() {
        assertNull(dictMapper.getDescription("NaW"));
    }

    @DisplayName("Get null word description")
    @Test
    void getNullDescription() {
        assertNull(dictMapper.getDescription(null));
    }



    @DisplayName("Get existing word description")
    @Test
    void getExistingWord() {
        jdbcTemplate.execute("INSERT INTO dict(word, description) VALUES ('word2', 'desc2')");
        assertEquals("word2", dictMapper.getWord("desc2"));
    }

    @DisplayName("Get non existing word description")
    @Test
    void getNonExistingWord() {
        assertNull(dictMapper.getWord("NaW"));
    }

    @DisplayName("Get null word description")
    @Test
    void getNullWord() {
        assertNull(dictMapper.getWord(null));
    }
}