package org.example.disproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.disproject.dto.SendWordFormDto;
import org.example.disproject.service.WordService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/word/")
@RequiredArgsConstructor
public class WordRestController {

    private final WordService wordService;

    @PostMapping("/save")
    public ResponseEntity<?> saveWord(@RequestBody String word){
        wordService.save(word);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendWord(@RequestBody SendWordFormDto sendWordFormDto){
        wordService.sendWord(sendWordFormDto);
        return ResponseEntity.ok().build();
    }

}
