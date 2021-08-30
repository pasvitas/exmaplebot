package ru.pasvitas.discordbots.examplebot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pasvitas.discordbots.examplebot.repository.DictRepository;

@RequiredArgsConstructor //Втихаря делает конструктор со всеми параметрами, помеченными как Final
@Service //Spring-Магия
public class DictServiceImpl implements DictService {

    private final DictRepository dictRepository; // Вставляет репозиторий благодаря Spring-магии

    @Override
    public String getDescription(String word) {
        return dictRepository.getDescription(word);
    }

    @Override
    public String getWord(String desc) {
        return dictRepository.getWord(desc);
    }
}
