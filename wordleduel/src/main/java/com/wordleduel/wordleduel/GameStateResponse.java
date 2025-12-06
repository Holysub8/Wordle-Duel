package com.wordleduel.wordleduel;

public class GameStateResponse {

    private String gameID;
    private String player1Name;
    private String player2Name;
    private boolean gameStarted;
    private boolean gameOver;
    private String currentTurn; 
    private String winner;

    public GameStateResponse(String gameID,
                             String player1Name,
                             String player2Name,
                             boolean gameStarted,
                             boolean gameOver,
                             String currentTurn,
                             String winner) {
        this.gameID = gameID;
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.gameStarted = gameStarted;
        this.gameOver = gameOver;
        this.currentTurn = currentTurn;
        this.winner = winner;
    }

    public String getGameID() { return gameID; }
    public String getPlayer1Name() { return player1Name; }
    public String getPlayer2Name() { return player2Name; }
    public boolean isGameStarted() { return gameStarted; }
    public boolean isGameOver() { return gameOver; }
    public String getCurrentTurn() { return currentTurn; }
    public String getWinner() { return winner; }
}