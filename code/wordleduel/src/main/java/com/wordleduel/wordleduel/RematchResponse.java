package com.wordleduel.wordleduel;

public class RematchResponse {

    private final String status;
    private final String message;
    private final Integer wordLength;
    private final String currentTurn;

    public RematchResponse(String status, String message, Integer wordLength, String currentTurn) {
        this.status = status;
        this.message = message;
        this.wordLength = wordLength;
        this.currentTurn = currentTurn;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Integer getWordLength() {
        return wordLength;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }
}
