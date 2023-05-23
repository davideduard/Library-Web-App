package org.example;


import org.example.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/api/books")
public class BookRestController {
    @Autowired
    private MyService service;

    @GetMapping
    public Iterable<Book> getAllBooks(){
        System.out.println("Getting all books");
        return service.getAllAvailableBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Integer id){
        System.out.println("Getting book by id");
        Book book = service.searchBookById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/title/{title}")
    public ResponseEntity<?> getBookByTitle(@PathVariable String title){
        System.out.println("Getting book by title");
        Book book = service.searchUnreservedBookByTitle(title);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/update")
    public Book updateReservedStatus(@RequestBody Book book) {
        System.out.println("Updating reserved status");
        return service.updateReservedStatus(book);
    }
}

