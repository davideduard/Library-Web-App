package org.example;

import org.apache.catalina.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/api/librarians")
public class LibrarianRestController {

    @Autowired
    private MyService service;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String username, @RequestParam String password){
        System.out.println("logging in librarian");
        try{
            service.login(username, password);
            return new ResponseEntity<>("librarian " + username + " is logged in", HttpStatus.OK);
        } catch (Exception ex){
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
