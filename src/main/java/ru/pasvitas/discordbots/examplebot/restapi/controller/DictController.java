package ru.pasvitas.discordbots.examplebot.restapi.controller;

import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.pasvitas.discordbots.examplebot.restapi.responsemodel.DictResponse;
import ru.pasvitas.discordbots.examplebot.service.DictService;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
public class DictController {

    private final DictService dictService;

    @GetMapping("/api/dict/{word}")
    public ResponseEntity<DictResponse> getDescription(@PathVariable("word") String word) {
        return ResponseEntity.ok(new DictResponse(word, dictService.getDescription(word)));
    }

    @GetMapping("/api/dict")
    public ResponseEntity<List<DictResponse>> getAll() {
        return ResponseEntity.ok(dictService.getAll());
    }

    @PostMapping("/api/dict")
    public ResponseEntity<DictResponse> addWord(@RequestBody DictResponse dictResponse) {

        dictService.addWord(dictResponse.getWord(), dictResponse.getDescription());
        return ResponseEntity.created(URI.create("/api/dict/" + dictResponse.getWord())).body(dictResponse);
    }

    @PutMapping("/api/dict/{word}")
    public ResponseEntity<DictResponse> updateDescription(@PathVariable("word") String word, @RequestBody String description) {

        dictService.updateDescription(word, description);
        return ResponseEntity.ok(new DictResponse(word, description));
    }

    @DeleteMapping("/api/dict/{word}")
    public ResponseEntity<Void> deleteWord(@PathVariable("word") String word) {
        dictService.deleteWord(word);
        return ResponseEntity.ok().build();
    }
}
