package com.wordleduel.wordleduel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private final GameSessionService gameSessionService;
    private final WordService wordService;
    private final GuessService guessService;
    private final DictionaryService dictionaryService;

    public GameController(
        GameSessionService gameSessionService,
        WordService wordService,
        GuessService guessService,
        DictionaryService dictionaryService) 
        {
        this.gameSessionService = gameSessionService;
        this.wordService = wordService;
        this.guessService = guessService;
        this.dictionaryService = dictionaryService;
    }
        
    @GetMapping("/start-game")
    public StartGameResponse startGame(){
        String secretWord = wordService.getRandomWord();

        GameSession session = gameSessionService.createGame(secretWord);

        return new StartGameResponse(session.getGameID(), secretWord.length());
    }

    @GetMapping("/join-game")
    public JoinGameResponse joinGame(
        @RequestParam String gameID,
        @RequestParam String playerName){

            boolean joined =  gameSessionService.joinGame(gameID, playerName);

            if (!joined){
                 return new JoinGameResponse(
                "FAILED",
                gameID,
                null,
                "Invalid gameID or game is full.");
            }

            GameSession session = gameSessionService.getGame(gameID);
            String role;
            if(playerName.equals(session.getPlayer1())){
                role = "player1";
            } else if (playerName.equals(session.getPlayer2())){
                role = "player2";
            } else {
                role = "unknown";
            }

             return new JoinGameResponse(
            "JOINED",
            gameID,
            role,
            "Successfully joined the game.");
            
        }

        @GetMapping("/play-guess")
        public GuessResponse playGuess(
            @RequestParam String gameID,
            @RequestParam String playerName,
            @RequestParam String guess){

                GameSession session = gameSessionService.getGame(gameID);
                if(session == null) {
                     return new GuessResponse(
                "INVALID_GAME",
                null,
                null,
                null,
                "Game ID not found.");
        
                }

                if (session.isGameOver()){
                    return new GuessResponse(
                "GAME_OVER",
                null,
                null,
                session.getWinner(),
                "Game is already finished.");
                }

                String currentTurn = session.getCurrentTurn();
                String expectedPlayerName = currentTurn.equals("player1") ? session.getPlayer1() : session.getPlayer2();

                if (expectedPlayerName == null) {
                    return new GuessResponse(
                "PLAYERS_NOT_READY",
                null,
                currentTurn,
                null,
                "Both players have not joined yet.");
                }

                if (!playerName.equals(expectedPlayerName)) {
                    return new GuessResponse(
                "NOT_YOUR_TURN",
                null,
                currentTurn,
                null,
                "It is " + currentTurn + "'s turn.");
                }

                if(!dictionaryService.isValidWord(guess)){
                    return new GuessResponse(
                "INVALID_WORD",
                null,
                currentTurn,
                null,
                "Guess is not a valid dictionary word.");
                }

                String secretWord = session.getSecretWord();
                String evaluation = guessService.guessChecker(secretWord, guess);

                session.addGuess(playerName, guess, evaluation);

                boolean allGreen = evaluation.chars().allMatch(ch -> ch =='G');
                
                if(allGreen) {
                    session.setWinner(playerName);
                    return new GuessResponse(
                "WIN",
                evaluation,
                null,
                playerName,
                "Correct! " + playerName + " wins.");
                }

                session.switchTurn();
                 return new GuessResponse(
                 "OK",
                 evaluation,
                 session.getCurrentTurn(),
                 null,
                 "Guess evaluated.");
                 }

            @GetMapping("/end-game")
                public String endGame(@RequestParam String gameID) {
                  GameSession session = gameSessionService.getGame(gameID);
                    if (session == null) {
                    return "INVALID_GAME";
                                             }

                    gameSessionService.endGame(gameID);
                     return "GAME_ENDED";
                }

                
            @GetMapping("/game-state")
                public GameStateResponse gameState(@RequestParam String gameID) {
                     GameSession session = gameSessionService.getGame(gameID);
                        if (session == null) {
        
                        return new GameStateResponse(
                                gameID,
                                null,
                                null,
                                false,
                                false,
                                null,
                                null
                        );
                    }

                    return new GameStateResponse(
                            session.getGameID(),
                            session.getPlayer1(),
                            session.getPlayer2(),
                            session.isGameStarted(),
                            session.isGameOver(),
                            session.getCurrentTurn(),
                            session.getWinner()
                    );
                }

                @GetMapping("/start-match")
                public StartMatchResponse startMatch(
                        @RequestParam String gameID,
                        @RequestParam String playerName
                ) {
                    GameSession session = gameSessionService.getGame(gameID);
                    if (session == null) {
                        return new StartMatchResponse(
                                "INVALID_GAME",
                                "Game not found.",
                                false,
                                null
                        );
                    }

                    if (session.getPlayer1() == null || session.getPlayer2() == null) {
                        return new StartMatchResponse(
                                "NOT_READY",
                                "Both players must join before starting.",
                                false,
                                null
                        );
                    }

                    if (!playerName.equals(session.getPlayer1())) {
                        return new StartMatchResponse(
                                "NOT_AUTHORIZED",
                                "Only player1/host can start the match.",
                                session.isGameStarted(),
                                session.getCurrentTurn()
                        );
                    }

                    session.setGameStarted(true);
                    return new StartMatchResponse(
                            "OK",
                            "Match started.",
                            true,
                            session.getCurrentTurn()
                    );
                }

                @GetMapping("/guess-history")
                public GuessHistoryResponse guessHistory(@RequestParam String gameID) {
                  GameSession session = gameSessionService.getGame(gameID);
                     if (session == null) {
                        return new GuessHistoryResponse(gameID, java.util.Collections.emptyList());
                     }

                     java.util.List<GuessHistoryItem> dto = new java.util.ArrayList<>();
                       for (GameSession.GuessRecord gr : session.getGuessHistory()) {
                         dto.add(new GuessHistoryItem(gr.getPlayerName(), gr.getGuess(), gr.getEvaluation()));
                           }
                         return new GuessHistoryResponse(gameID, dto);
                }

                @GetMapping("/rematch")
                    public RematchResponse rematch(@RequestParam String gameID) {
                        GameSession session = gameSessionService.getGame(gameID);
                        if (session == null) {
                            return new RematchResponse(
                                    "INVALID_GAME",
                                    "Game not found.",
                                    null,
                                    null
                            );
                        }
                        
                        String newSecret = wordService.getRandomWord();
                        session.resetForRematch(newSecret);

                        return new RematchResponse(
                                "OK",
                                "Rematch started.",
                                newSecret.length(),
                                session.getCurrentTurn()
                        );
                    }




 }
