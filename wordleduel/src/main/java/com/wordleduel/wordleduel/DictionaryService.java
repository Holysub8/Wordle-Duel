package com.wordleduel.wordleduel;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class DictionaryService{

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean isValidWord(String guess){
        String url = "https://api.dictionaryapi.dev/api/v2/entries/en/" + guess.toLowerCase();

        try{
            restTemplate.getForObject(url, String.class);
            return true;
        }
        catch (HttpClientErrorException.NotFound e) {
            return false;
        }
        catch (Exception e){
            System.err.println("Dictionary API error: " + e.getMessage());
            return false;
        }
    }
}