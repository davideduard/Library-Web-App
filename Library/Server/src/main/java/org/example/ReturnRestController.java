package org.example;

import org.example.domain.Borrow;
import org.example.domain.Return;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/library/api/returns")
public class ReturnRestController {
    @Autowired
    private MyService service;

    @PostMapping("/add")
    public Return addReturn(@RequestParam Integer book_id, @RequestParam String subscriber_id, @RequestParam String librarian_id) {
        System.out.println("adding return");
        Return ret = new Return(0, librarian_id, book_id, subscriber_id);
        return service.addNewReturn(ret);
    }
}
