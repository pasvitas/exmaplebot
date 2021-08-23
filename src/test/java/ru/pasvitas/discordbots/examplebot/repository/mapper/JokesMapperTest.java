package ru.pasvitas.discordbots.examplebot.repository.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JokesMapperTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private JokesMapper jokesMapper;

    @DisplayName("Get joke")
    @Test
    void getRandomJoke() {
        assertNull(jokesMapper.getRandomJoke());
        jdbcTemplate.execute("INSERT INTO jokes(joke) VALUES ('joke')");
        assertEquals("joke", jokesMapper.getRandomJoke());
    }
}