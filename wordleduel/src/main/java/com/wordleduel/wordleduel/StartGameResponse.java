package com.wordleduel.wordleduel;

public class StartGameResponse {

    private String gameID;
    private int wordLength;

    public StartGameResponse(String gameID, int wordLength) {
        this.gameID = gameID;
        this.wordLength = wordLength;
    }

    public String getGameID() {
        return gameID;
    }

    public int getWordLength() {
        return wordLength;
    }
}
