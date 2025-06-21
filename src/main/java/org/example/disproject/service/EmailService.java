package org.example.disproject.service;

import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.example.disproject.entity.UsedEmail;
import org.example.disproject.repository.UsedEmailRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmailService {

    private static final String BREVO_URL = "https://api.brevo.com/v3/smtp/email";

    @Value("${app.email.brevo-api-key}")
    private String brevoApiKey;
    
    @Value("${app.email.sender}")
    private String senderEmail;

    private final UsedEmailRepository usedEmailRepository;

    public void sendWord(String email, String word) {

        OkHttpClient client = new OkHttpClient();

        String json = """
                {
                  "sender": {"name":"DISPROJECT","email":"%s"},
                  "to": [{"email":"%s"}],
                  "subject":"Word from DISProject",
                  "textContent":"Word: %s"
                }
        """.formatted(senderEmail, email, word);

        Request request = new Request.Builder()
                .url(BREVO_URL)
                .addHeader("Content-Type", "application/json")
                .addHeader("api-key", brevoApiKey)
                .post(RequestBody.create(MediaType.get("application/json"), json))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new RuntimeException("error " + response.code());
            }
            usedEmailRepository.save(new UsedEmail().setEmail(email).setUsedAt(LocalDateTime.now()));
        } catch (IOException e) {
            throw new RuntimeException("error brevo ", e);
        }
    }
}
