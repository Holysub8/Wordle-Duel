package com.wordleduel.wordleduel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GuessController {

private final GuessService guessService;

public GuessController(GuessService guessService){
    this.guessService = guessService;
}

@GetMapping("/guess")
public String evaluateGuess(@RequestParam String word, @RequestParam String guess){
    return guessService.guessChecker(word, guess);
}

}