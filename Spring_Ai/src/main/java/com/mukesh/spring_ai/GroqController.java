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
    private final MessageService service;

    public GroqController(OpenAiChatModel chatModel, MessageService service) {
        this.chatClient = ChatClient.create(chatModel);
        this.service = service;
    }

    @GetMapping("/{message}")
    public ResponseEntity<String> getAnswers(@PathVariable String message) {

        String pbPrompt = service.problemBreakdown(message);
        String pbOutput = cleanResponse(chatClient.prompt(pbPrompt).call().content());

        String researchPrompt = service.research(pbOutput);
        String researchOutput = cleanResponse(chatClient.prompt(researchPrompt).call().content());

        String solutionPrompt = service.solutionBuilder(pbOutput, researchOutput);
        String solutionOutput = cleanResponse(chatClient.prompt(solutionPrompt).call().content());

        String criticPrompt = service.criticAnalyzer(message, solutionOutput);
        String criticOutput = cleanResponse(chatClient.prompt(criticPrompt).call().content());

        String improverPrompt = service.improverAnalyzer(solutionOutput, criticOutput);
        String improverOutput = cleanResponse(chatClient.prompt(improverPrompt).call().content());

        String finalPrompt = service.finalDecision(
                message,
                pbOutput,
                solutionOutput,
                criticOutput,
                improverOutput
        );

        String finalAnswer = cleanResponse(chatClient.prompt(finalPrompt).call().content());

        return ResponseEntity.ok(finalAnswer);
    }

    private String cleanResponse(String response) {
        if (response == null) {
            return "";
        }

        return response
                // Remove markdown code blocks
                .replaceAll("```[a-zA-Z]*\\n?", "")
                .replaceAll("```", "")
                // Remove bold/italic markdown
                .replaceAll("\\*\\*\\*", "")
                .replaceAll("\\*\\*", "")
                .replaceAll("\\*", "")
                .replaceAll("___", "")
                .replaceAll("__", "")
                .replaceAll("_", "")
                // Remove strikethrough
                .replaceAll("~~", "")
                // Remove inline code backticks
                .replaceAll("`", "")
                // Remove headers
                .replaceAll("^#{1,6}\\s+", "")
                .replaceAll("\\n#{1,6}\\s+", "\n")
                // Clean up extra whitespace
                .replaceAll("\\n{3,}", "\n\n")
                .trim();
    }
}

