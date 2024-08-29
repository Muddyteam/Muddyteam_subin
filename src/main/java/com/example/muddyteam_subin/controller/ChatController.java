package com.example.muddyteam_subin.controller;

import com.google.cloud.dialogflow.v2.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Value("${dialogflow.project.id}")
    private String projectId;

    @PostMapping
    public String chat(@RequestBody String message) throws Exception {
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            SessionName session = SessionName.of(projectId, "unique-session-id");
            TextInput textInput = TextInput.newBuilder().setText(message).setLanguageCode("ko").build();
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            return response.getQueryResult().getFulfillmentText();
        }
    }
}
