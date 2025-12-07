package com.wordleduel.wordleduel;

public class GuessHistoryItem {
    public String playerName;
    public String guess;
    public String pattern;

    public GuessHistoryItem(String playerName, String guess, String pattern) {
        this.playerName = playerName;
        this.guess = guess;
        this.pattern = pattern;
    }
}