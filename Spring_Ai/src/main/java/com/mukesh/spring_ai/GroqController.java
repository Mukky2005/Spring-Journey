package com.mukesh.Spring_Ai;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/openai")
@CrossOrigin("*")
public class GroqController {

    private final ChatClient chatClient;

    public GroqController(OpenAiChatModel chatModel) {
        this.chatClient = ChatClient.create(chatModel);
    }

    @GetMapping("/{message}")
    public ResponseEntity<String> getAnswers(@PathVariable String message) {
        String response = chatClient.prompt(message).call().content();
        return ResponseEntity.ok(response);
    }

}
