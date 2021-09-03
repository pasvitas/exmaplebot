package ru.pasvitas.discordbots.examplebot.restapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.pasvitas.discordbots.examplebot.restapi.responsemodel.DictResponse;
import ru.pasvitas.discordbots.examplebot.service.DictService;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DictControllerTest {

    @Autowired
    private MockMvc mvc;

    @DisplayName("Test add new word")
    @Test
    @Order(1)
    public void addWord() throws Exception
    {
        String json = (new ObjectMapper()).writeValueAsString(new DictResponse("testword", "testdesc"));

        mvc.perform( MockMvcRequestBuilders
                        .post("/api/dict")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.word").value("testword"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("testdesc"));
    }

    @DisplayName("Test add null value word")
    @Test
    @Order(2)
    public void addNullWord() throws Exception
    {
        String json = (new ObjectMapper()).writeValueAsString(new DictResponse(null, null));

        mvc.perform( MockMvcRequestBuilders
                        .post("/api/dict")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }



    @DisplayName("Test get word description")
    @Test
    @Order(3)
    public void getDescription() throws Exception {

        mvc.perform( MockMvcRequestBuilders
                        .get("/api/dict/{word}", "testword")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.word").value("testword"));
    }

    @DisplayName("Test get all words")
    @Test
    @Order(4)
    public void getAll() throws Exception {

        mvc.perform( MockMvcRequestBuilders
                        .get("/api/dict")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].word").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].description").isNotEmpty());
    }

    @DisplayName("Test get non-existing word description")
    @Test
    @Order(5)
    public void getNonExistingDescription() throws Exception {

        mvc.perform( MockMvcRequestBuilders
                        .get("/api/dict/{word}", "vipastas")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }



    @DisplayName("Test update word description")
    @Test
    @Order(6)
    public void updateWord() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .put("/api/dict/{word}", "testword")
                        .content("lolkekcheburek")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.word").value("testword"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.description").value("lolkekcheburek"));
    }

    @DisplayName("Test update word set null description")
    @Test
    @Order(8)
    public void updateWordNullDesc() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .put("/api/dict/{word}", "testword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }




    @DisplayName("Test delete word")
    @Test
    @Order(9)
    public void deleteWord() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .delete("/api/dict/{word}", "testword")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @DisplayName("Test delete non-existing word")
    @Test
    @Order(11)
    public void deleteNonExistingWord() throws Exception
    {
        mvc.perform( MockMvcRequestBuilders
                        .delete("/api/dict/{word}", "pasvitas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
