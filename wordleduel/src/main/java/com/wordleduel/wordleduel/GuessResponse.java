package com.wordleduel.wordleduel;

public class GuessResponse {

    private String status;    // "OK", "WIN", "INVALID_GAME", "NOT_YOUR_TURN", etc.
    private String pattern;   // e.g. "GYNYYN"
    private String nextTurn;  // "player1"/"player2" or null
    private String winner;    // winner name or null
    private String message;   // human-readable description

    public GuessResponse(String status, String pattern, String nextTurn, String winner, String message) {
        this.status = status;
        this.pattern = pattern;
        this.nextTurn = nextTurn;
        this.winner = winner;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getPattern() {
        return pattern;
    }

    public String getNextTurn() {
        return nextTurn;
    }

    public String getWinner() {
        return winner;
    }

    public String getMessage() {
        return message;
    }
}
