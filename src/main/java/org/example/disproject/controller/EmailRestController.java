package org.example.disproject.controller;

import lombok.RequiredArgsConstructor;
import org.example.disproject.entity.UsedEmail;
import org.example.disproject.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/email/")
@RequiredArgsConstructor
public class EmailRestController {

    private final EmailService emailService;

    @GetMapping("/all")
    public List<UsedEmail> getAll() {
        return emailService.findAll();
    }

}
