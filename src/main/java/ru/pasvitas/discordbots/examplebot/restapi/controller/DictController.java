package ru.pasvitas.discordbots.examplebot.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.pasvitas.discordbots.examplebot.restapi.responsemodel.DictResponse;
import ru.pasvitas.discordbots.examplebot.service.DictService;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
public class DictController {

    private final DictService dictService;

    @GetMapping("/api/dict/{word}")
    public ResponseEntity<DictResponse> getDescription(@PathVariable("word") String word){

        String desc = dictService.getDescription(word);
        if(desc != null){
            return new ResponseEntity<>(new DictResponse(word, desc), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/api/dict")
    public ResponseEntity<List<DictResponse>> getAll() {
        return ok().body(dictService.getAll());
    }

    @PostMapping("/api/dict")
    public ResponseEntity<DictResponse> addWord(@RequestBody DictResponse dictResponse) {

        if(dictService.addWord(dictResponse.getWord(), dictResponse.getDescription())){
            return new ResponseEntity<>(dictResponse, HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/api/dict/{word}")
    public ResponseEntity<DictResponse> updateDescription(@PathVariable("word") String word, @RequestBody String description){

        if(dictService.updateDescription(word, description)){
            return new ResponseEntity<>(new DictResponse(word, description), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/api/dict/{word}")
    public ResponseEntity<HttpStatus> deleteWord(@PathVariable("word") String word){

        if(dictService.deleteWord(word)){
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
}
