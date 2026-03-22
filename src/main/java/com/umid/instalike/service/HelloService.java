package com.umid.instalike.service;


import com.umid.instalike.model.HelloResponse;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public HelloResponse buildHelloResponse(String name)
    {
        if (name ==null || name.isBlank())
        {
            return new HelloResponse("Umid","InstaLike");
        }
        return new HelloResponse(name,"InstaLike");
    }
    
}
