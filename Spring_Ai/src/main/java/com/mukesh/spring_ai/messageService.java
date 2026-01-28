package com.mukesh.Spring_Ai;

import org.springframework.stereotype.Service;

@Service
public class MessageService {


    public String problemBreakdown(String message) {
        return String.format(AgentsPrompt.PBPrompt, message);
    }

    public String research(String problemBreakdown) {
        return String.format(AgentsPrompt.RPrompt, problemBreakdown);
    }

    public String solutionBuilder(String problemBreakdown, String researchContent) {
        return String.format(
                AgentsPrompt.SBPrompt,
                problemBreakdown,
                researchContent
        );
    }

    public String criticAnalyzer(String originalMessage, String solutionOutput) {
        return String.format(
                AgentsPrompt.CPrompt,
                originalMessage,
                solutionOutput
        );
    }

    public String improverAnalyzer(String solutionOutput, String criticFeedback) {
        return String.format(
                AgentsPrompt.IPrompt,
                solutionOutput,
                criticFeedback
        );
    }

    public String finalDecision(
            String originalMessage,
            String problemBreakdown,
            String solutionOutput,
            String criticFeedback,
            String improverFeedback
    ) {
        return String.format(
                AgentsPrompt.FDPrompt,
                originalMessage,
                problemBreakdown,
                solutionOutput,
                criticFeedback,
                improverFeedback
        );
    }
}

