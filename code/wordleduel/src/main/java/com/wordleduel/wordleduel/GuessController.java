package com.wordleduel.wordleduel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuessController {

private final GuessService guessService;
private final DictionaryService dictionaryService;

public GuessController(GuessService guessService, DictionaryService dictionaryService){
    this.guessService = guessService;
    this.dictionaryService = dictionaryService;
}

@GetMapping("/guess")
public String evaluateGuess(
    @RequestParam String word, 
    @RequestParam String guess) {

        if(!dictionaryService.isValidWord(guess)){
            return "INVALID";
        }


    return guessService.guessChecker(word, guess);
}
}