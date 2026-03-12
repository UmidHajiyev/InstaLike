package com.umid.instalike.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home()
    {
        return "InstaLike is running!";
    }

    @GetMapping("/hello")
    public String sayHello()
    {
        return "Hello from InstaLike";
    }
}
