package com.wordleduel.wordleduel;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class GameSessionService {
    
    private final Map<String, GameSession> activeGames = new HashMap<>();

    public GameSession createGame(String secretWord) {
        String gameID = UUID.randomUUID().toString().substring(0, 6).toUpperCase();

        GameSession session = new GameSession(gameID, secretWord);
        activeGames.put(gameID, session);

        return session;
    }

    public boolean joinGame(String gameID, String playerName) {
        GameSession session = activeGames.get(gameID);

        if(session == null) {
            return false;
        }

        if(session.getPlayer1() == null){
            session.setPlayer1(playerName);
            return true;
        }

        else if(session.getPlayer2() == null) {
            session.setPlayer2(playerName);
            return true;
        }
        return false;
    }

    public GameSession getGame(String gameID) {
        return activeGames.get(gameID);
    }

    public void endGame(String gameID) {
    activeGames.remove(gameID);
}

}