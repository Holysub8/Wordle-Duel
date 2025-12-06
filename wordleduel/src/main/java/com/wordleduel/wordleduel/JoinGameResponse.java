package com.wordleduel.wordleduel;

public class JoinGameResponse {

    private String status;   // "JOINED" or "FAILED"
    private String gameID;
    private String role;     // "player1", "player2", or null
    private String message;  // explanation

    public JoinGameResponse(String status, String gameID, String role, String message) {
        this.status = status;
        this.gameID = gameID;
        this.role = role;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getGameID() {
        return gameID;
    }

    public String getRole() {
        return role;
    }

    public String getMessage() {
        return message;
    }
}
