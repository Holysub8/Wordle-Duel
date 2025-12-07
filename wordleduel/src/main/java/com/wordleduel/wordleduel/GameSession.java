package com.wordleduel.wordleduel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameSession {

    private final String gameID;
    private String secretWord;

    private String player1;
    private String player2;
    private String currentTurn;

    private boolean isGameOver = false;
    private String winner = null;

    private boolean gameStarted = false;

    // ===== Guess history support =====
    public static class GuessRecord {
        private final String playerName;
        private final String guess;
        private final String evaluation; // e.g. "GNYNNN"

        public GuessRecord(String playerName, String guess, String evaluation) {
            this.playerName = playerName;
            this.guess = guess;
            this.evaluation = evaluation;
        }

        public String getPlayerName() {
            return playerName;
        }

        public String getGuess() {
            return guess;
        }

        public String getEvaluation() {
            return evaluation;
        }
    }

    private final List<GuessRecord> guessHistory = new ArrayList<>();

    public void addGuess(String playerName, String guess, String evaluation) {
        guessHistory.add(new GuessRecord(playerName, guess, evaluation));
    }

    public List<GuessRecord> getGuessHistory() {
        return Collections.unmodifiableList(guessHistory);
    }
    // =================================

    public GameSession(String gameID, String secretWord) {
        this.gameID = gameID;
        this.secretWord = secretWord;
        this.currentTurn = "player1";
    }

    public String getGameID() {
        return gameID;
    }

    public String getSecretWord() {
        return secretWord;
    }

    public String getCurrentTurn() {
        return currentTurn;
    }

    public String getPlayer1() {
        return player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void switchTurn() {
        if (currentTurn.equals("player1")) {
            currentTurn = "player2";
        } else {
            currentTurn = "player1";
        }
    }

    public void setPlayer1(String player) {
        this.player1 = player;
    }

    public void setPlayer2(String player) {
        this.player2 = player;
    }

    public void setWinner(String winner) {
        this.winner = winner;
        this.isGameOver = true;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public String getWinner() {
        return winner;
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        this.gameStarted = gameStarted;
    }

    public void resetForRematch(String newSecretWord) {
    this.secretWord = newSecretWord;
    this.currentTurn = "player1";
    this.isGameOver = false;
    this.winner = null;
    this.gameStarted = true;     
    this.guessHistory.clear();   
}

}
