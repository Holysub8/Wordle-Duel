package com.wordleduel.wordleduel;

import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Random;


@Service
public class WordService {

private final List<String> wordList;
private final Random random = new Random();

public WordService(){
    List<String> tempList;
    try {
        tempList = Files.readAllLines(Paths.get("src/main/resources/Words.txt"));
    }
    catch (IOException e) {
        System.out.println("Words.txt failed to load: " + e.getMessage() + " Using fallback wordList");
        tempList = List.of( "PUZZLE", "LETTER", "STREAM", "OBJECT", "FIGURE",
            "TARGET", "PLAYER", "NUMBER", "ACTION", "CHANGE",
            "DEVICE", "GLOBAL", "HANDLE", "MARKET", "NATION",
            "OUTPUT", "PARENT", "POLICE", "RESULT", "SYSTEM");
    }
    wordList = tempList;
}

public String getRandomWord() {
    return wordList.get(random.nextInt(wordList.size()));
}
}