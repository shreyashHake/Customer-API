package com.shreyash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @RequestMapping("/jayShreeRam")
    private GreetResponse greet() {
        return new GreetResponse("Jay Shree Ram",
                List.of("Java", "Cpp", "C", "Python"),
                new Person("Shreyash")
        );
    }

    record Person(String name){};

    record GreetResponse(String greet,
                         List<String> favProgrammingLanguages,
                         Person person){}
}

