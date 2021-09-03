package ru.pasvitas.discordbots.examplebot.repository.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictModel {

    private int id;
    private String word;
    private String description;
}
