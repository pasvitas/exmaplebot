package ru.pasvitas.discordbots.examplebot.service;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pasvitas.discordbots.examplebot.exceptions.InputDataException;
import ru.pasvitas.discordbots.examplebot.exceptions.WordNotExistsException;
import ru.pasvitas.discordbots.examplebot.repository.DictRepository;
import ru.pasvitas.discordbots.examplebot.repository.model.DictModel;
import ru.pasvitas.discordbots.examplebot.restapi.controller.DictController;
import ru.pasvitas.discordbots.examplebot.restapi.responsemodel.DictResponse;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor //Втихаря делает конструктор со всеми параметрами, помеченными как Final
@Service //Spring-Магия
public class DictServiceImpl implements DictService {

    private final DictRepository dictRepository; // Вставляет репозиторий благодаря Spring-магии

    @Override
    public String getDescription(String word) {
        if (word == null) {
            throw new InputDataException();
        }
        String description = dictRepository.getDescription(word);
        if (description == null) {
            throw new WordNotExistsException();
        }
        return description;
    }

    @Override
    public String getWord(String description) {
        return dictRepository.getWord(description);
    }

    @Override
    public void addWord(String word, String description) {

        if (word == null || description == null) {
            throw new InputDataException();
        }
        dictRepository.addWord(word, description);
    }

    @Override
    public void deleteWord(String word) {

        if (word == null) {
            throw new InputDataException();
        }
        dictRepository.deleteWord(word);
    }

    @Override
    public List<DictResponse> getAll() {
        return dictRepository.getAll()
                .stream()
                .map(el -> new DictResponse(el.getWord(), el.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public void updateDescription(String word, String description) {
        if (word == null || description == null) {
            throw new InputDataException();
        }
        dictRepository.updateDescription(word, description);
    }
}
