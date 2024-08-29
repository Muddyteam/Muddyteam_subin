package com.example.muddyteam_subin.service;

import com.google.cloud.dialogflow.v2.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatBotService {

    @Value("${dialogflow.project.id}")
    private String projectId;

    // 메시지를 ChatBot으로 보내고 응답을 반환
    public String detectIntent(String message) {
        try (SessionsClient sessionsClient = SessionsClient.create()) {
            SessionName session = SessionName.of(projectId, "unique-session-id");
            TextInput textInput = TextInput.newBuilder().setText(message).setLanguageCode("ko-KR").build();
            QueryInput queryInput = QueryInput.newBuilder().setText(textInput).build();
            DetectIntentResponse response = sessionsClient.detectIntent(session, queryInput);
            return response.getQueryResult().getFulfillmentText();
        } catch (Exception e) {
            // 예외 발생 시 예외 처리
            return "An error occurred while processing your request: " + e.getMessage();
        }
    }
}
