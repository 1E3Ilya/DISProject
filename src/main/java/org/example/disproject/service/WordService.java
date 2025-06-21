package org.example.disproject.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.disproject.dto.SendWordFormDto;
import org.example.disproject.entity.Word;
import org.example.disproject.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WordService {

    private final WordRepository wordRepository;
    private final EmailService emailService;

    public void save(String word) {
        Word wordEntity = new Word();
        wordEntity.setWord(word);
        wordEntity.setCreatedAt(LocalDateTime.now());
        wordRepository.save(wordEntity);
    }

    public void sendWord(SendWordFormDto formDto) {
        emailService.sendWord(formDto.getEmail(), formDto.getWord());
    }
    public List<Word> findAll() {
        return wordRepository.findAll();
    }
}
