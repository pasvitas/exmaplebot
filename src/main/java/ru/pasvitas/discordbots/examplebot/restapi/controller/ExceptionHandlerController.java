package ru.pasvitas.discordbots.examplebot.restapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.pasvitas.discordbots.examplebot.exceptions.InputDataException;
import ru.pasvitas.discordbots.examplebot.exceptions.WordNotExistsException;

@Component
@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(WordNotExistsException.class)
    public ResponseEntity<Void> handleWordNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(InputDataException.class)
    public ResponseEntity<Void> handleInputDataException() {
        return ResponseEntity.badRequest().build();
    }
}
