package ru.pasvitas.discordbots.examplebot.service;

import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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
        return dictRepository.getDescription(word);
    }

    @Override
    public String getWord(String description) {
        return dictRepository.getWord(description);
    }

    @Override
    public boolean addWord(String word, String description) {

        if (word == null || description == null) {
            return false;
        }
        dictRepository.addWord(word, description);
        return true;
    }

    @Override
    public boolean deleteWord(String word) {

        if (word == null) {
            return false;
        }
        dictRepository.deleteWord(word);

        return dictRepository.getDescription(word) == null;
    }

    @Override
    public List<DictResponse> getAll() {
        return dictRepository.getAll()
                .stream()
                .map(el -> new DictResponse(el.getWord(), el.getDescription()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean updateDescription(String word, String description) {
        if (word == null || description == null) {
            return false;
        }
        dictRepository.updateDescription(word, description);
        return true;
    }
}
