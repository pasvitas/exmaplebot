package ru.pasvitas.discordbots.examplebot.restapi.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Модель ответа запроса из словаря (слово - определение)
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DictResponse {

    private String word;
    private String description;
}
