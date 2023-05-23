package org.example;

import org.example.domain.Subscriber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/api/subscribers")
public class SubscriberRestController {
    @Autowired
    private MyService service;

    @GetMapping
    public Iterable<Subscriber> getAllSubscribers(){
        System.out.println("Getting all subscribers");
        return service.getAllSubscribers();
    }
}
