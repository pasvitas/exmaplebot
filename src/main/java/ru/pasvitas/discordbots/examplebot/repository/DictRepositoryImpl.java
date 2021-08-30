package ru.pasvitas.discordbots.examplebot.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.pasvitas.discordbots.examplebot.repository.mapper.DictMapper;

@RequiredArgsConstructor
@Repository
public class DictRepositoryImpl implements DictRepository{

    public final DictMapper dictMapper;

    @Override
    public String getDescription(String word) {
        return dictMapper.getDescription(word);
    }

    @Override
    public String getWord(String desc) {
        return dictMapper.getWord(desc);
    }
}
