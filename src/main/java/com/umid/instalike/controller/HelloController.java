package com.umid.instalike.controller;


import com.umid.instalike.model.HelloRequest;
import com.umid.instalike.model.HelloResponse;
import com.umid.instalike.service.HelloService;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService)
    {
        this.helloService = helloService;
    }

    @GetMapping("/")
    public String home()
    {
        return "InstaLike is running!";
    }

    @GetMapping("/hello")
    public HelloResponse sayHello(@RequestParam(required = false) String name)
    {
        return helloService.buildHelloResponse(name);
    }

    @GetMapping("/hello/{name}")
    public HelloResponse sayHelloWithPath(@PathVariable(required = false) String name)
    {
        return helloService.buildHelloResponse(name);
    }

    @PostMapping("/hello")
    public HelloResponse sayHelloPost(@RequestBody HelloRequest request)
    {
        return helloService.buildHelloResponse(request.name());
    }

}
