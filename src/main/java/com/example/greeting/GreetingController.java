package com.example.greeting;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/hello")
    public String hello(@RequestParam(name = "name", required = false) String name) {
        if (name == null || name.isBlank()) {
            return "Привет";
        }
        return "Привет, " + name + "!";
    }
}
