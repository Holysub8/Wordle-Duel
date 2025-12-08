package com.wordleduel.wordleduel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
public class WordleduelApplication {

	public static void main(String[] args) {
		SpringApplication.run(WordleduelApplication.class, args);
	}

}

@RestController
class HelloController {
    @GetMapping("/test")
    public String hello() {
        return "Spring Boot is running!";
    }
}