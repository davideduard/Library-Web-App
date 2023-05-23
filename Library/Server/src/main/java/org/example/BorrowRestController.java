package org.example;

import org.example.domain.Borrow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/api/borrows")
public class BorrowRestController {
    @Autowired
    private MyService service;

    @PostMapping("/add")
    public Borrow addBorrow(@RequestParam Integer book_id, @RequestParam String subscriber_id) {
        System.out.println("adding borrow");
        Borrow borrow = new Borrow(0, book_id, subscriber_id);
        return service.addNewBorrow(borrow);
    }

    @GetMapping("/{id}")
    public Iterable<Borrow> getAllBorrowsForSubscriber(@PathVariable String id){
        System.out.println("Getting all borrows for subscriber: " + id);
        return service.getAllBorrowsForSubscriber(id);
    }

    @DeleteMapping("/delete/{book_id}/{subscriber_id}")
    public Borrow removeBorrowByBookAndSubscriber(@PathVariable Integer book_id, @PathVariable String subscriber_id){
        System.out.println("Removing borrow for user: " + subscriber_id + " for the book: " + book_id);
        return service.removeBorrowByBookAndSubscriber(book_id, subscriber_id);
    }
}
