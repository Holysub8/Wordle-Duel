package com.wordleduel.wordleduel;

public class GuessHistoryResponse {
    public String gameID;
    public java.util.List<GuessHistoryItem> guesses;

    public GuessHistoryResponse(String gameID, java.util.List<GuessHistoryItem> guesses) {
        this.gameID = gameID;
        this.guesses = guesses;
    }
}