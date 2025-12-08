package com.wordleduel.wordleduel;

public class StartMatchResponse {

    private String status;    
    private String message;
    private boolean gameStarted;
    private String currentTurn;

    public StartMatchResponse(String status, String message, boolean gameStarted, String currentTurn) {
        this.status = status;
        this.message = message;
        this.gameStarted = gameStarted;
        this.currentTurn = currentTurn;
    }

    public String getStatus() { return status; }
    public String getMessage() { return message; }
    public boolean isGameStarted() { return gameStarted; }
    public String getCurrentTurn() { return currentTurn; }
}
