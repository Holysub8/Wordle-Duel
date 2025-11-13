package com.wordleduel.wordleduel;

import org.springframework.stereotype.Service;

@Service
public class GuessService {

    public String guessChecker (String word, String guess){

        word = word.toUpperCase();
        guess = guess.toUpperCase();
        char[] w = word.toCharArray();
        char[] g = guess.toCharArray();
        char[] result = new char[word.length()];

        int[] letterCount = new int[26];
        for(char c : w){
            letterCount[c - 'A']++;
        }

        for(int i = 0; i < word.length(); i++){
            if (w[i] == g[i]) {
                result[i] = 'G';
                letterCount[g[i] - 'A']--;
            }
        }

        for(int i = 0; i < word.length(); i++){
            if (result[i] == 'G') continue;

            if(letterCount[g[i] - 'A'] > 0 ) {
                result[i] = 'Y';
                letterCount[g[i] - 'A']--;
            }
            else{
                result[i] = 'N';
            }
    }
    return new String(result);
}
}