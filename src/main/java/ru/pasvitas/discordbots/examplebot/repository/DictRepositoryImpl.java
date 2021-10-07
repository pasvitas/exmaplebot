package ru.pasvitas.discordbots.examplebot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.pasvitas.discordbots.examplebot.repository.mapper.DictMapper;
import ru.pasvitas.discordbots.examplebot.repository.model.DictModel;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class DictRepositoryImpl implements DictRepository {

    public final DictMapper dictMapper;

    @Override
    public String getDescription(String word) {
        return dictMapper.getDescription(word);
    }

    @Override
    public String getWord(String description) {
        return dictMapper.getWord(description);
    }

    @Override
    public void addWord(String word, String description) {
        dictMapper.insertWord(word, description);
    }

    @Override
    public void deleteWord(String word) {
        dictMapper.deleteWord(word);
    }

    @Override
    public void updateDescription(String word, String description) {
        dictMapper.updateDescription(word, description);
    }

    @Override
    public List<DictModel> getAll() {
        return dictMapper.getAll();
    }
}
